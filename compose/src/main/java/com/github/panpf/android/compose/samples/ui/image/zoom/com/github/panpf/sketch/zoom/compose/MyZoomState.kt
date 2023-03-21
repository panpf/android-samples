package com.github.panpf.sketch.zoom.compose

import android.util.Log
import androidx.annotation.FloatRange
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationConstants
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.exponentialDecay
import androidx.compose.animation.core.tween
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.isSpecified
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.util.VelocityTracker
import androidx.compose.ui.input.pointer.util.addPointerInputChange
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Velocity
import com.github.panpf.android.compose.samples.BuildConfig
import com.github.panpf.android.compose.samples.ui.image.zoom.com.github.panpf.sketch.zoom.compose.computeContentScaleTranslation
import com.github.panpf.android.compose.samples.ui.image.zoom.com.github.panpf.sketch.zoom.compose.computePercentageCentroidOfContentByTouchPoint
import com.github.panpf.android.compose.samples.ui.image.zoom.com.github.panpf.sketch.zoom.compose.computeScaleFactor
import com.github.panpf.android.compose.samples.ui.image.zoom.com.github.panpf.sketch.zoom.compose.computeScaledContentVisibleCenter
import com.github.panpf.android.compose.samples.ui.image.zoom.com.github.panpf.sketch.zoom.compose.computeScaledContentVisibleRectWithTopLeftScale
import com.github.panpf.android.compose.samples.ui.image.zoom.com.github.panpf.sketch.zoom.compose.computeScaledCoreRectOfContent
import com.github.panpf.android.compose.samples.ui.image.zoom.com.github.panpf.sketch.zoom.compose.computeScaledCoreVisibleRect
import com.github.panpf.android.compose.samples.ui.image.zoom.com.github.panpf.sketch.zoom.compose.computeTranslationBoundsWithTopLeftScale
import com.github.panpf.android.compose.samples.ui.image.zoom.com.github.panpf.sketch.zoom.compose.restoreScale
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class MyZoomState(
    @FloatRange(from = 0.0) val minScale: Float = 1f,
    @FloatRange(from = 0.0) val maxScale: Float = 4f,
    @FloatRange(from = 0.0) initialTranslateX: Float = 0f,
    @FloatRange(from = 0.0) initialTranslateY: Float = 0f,
    @FloatRange(from = 0.0) initialScale: Float = minScale,
) {

    private val velocityTracker = VelocityTracker()
    private val _translationX = Animatable(initialTranslateX)
    private val _translationY = Animatable(initialTranslateY)
    private val _scale = Animatable(initialScale)
    // todo rotate

    val transformOrigin = TransformOrigin(0f, 0f)

    var spaceSize: Size = Size.Unspecified
        internal set(value) {
            val changed = value != field
            field = value
            if (changed) {
                updateTranslationBounds("spaceSizeChanged")
                resetFixedInfos()
                resetTransformInfos()
            }
        }
    var contentSize: Size = Size.Unspecified
        internal set(value) {
            val changed = value != field
            field = value
            if (changed) {
                updateTranslationBounds("contentSizeChanged")
                resetFixedInfos()
                resetTransformInfos()
            }
        }
    var coreSize: Size = Size.Unspecified
        internal set(value) {
            val changed = value != field
            field = value
            if (changed) {
                updateTranslationBounds("coreSizeChanged")
                resetFixedInfos()
                resetTransformInfos()
            }
        }
    var coreScale: ContentScale = ContentScale.Fit
        internal set(value) {
            val changed = value != field
            field = value
            if (changed) {
                updateTranslationBounds("coreScaleChanged")
                resetFixedInfos()
                resetTransformInfos()
            }
        }

    val zooming: Boolean
        get() = scale > minScale

    /**
     * The current scale value for [MyZoomImage]
     */
    @get:FloatRange(from = 0.0)
    val scale: Float
        get() = _scale.value

    /**
     * The current translation x value for [MyZoomImage]
     */
    val translationX: Float
        get() = _translationX.value

    /**
     * The current translation y value for [MyZoomImage]
     */
    val translationY: Float
        get() = _translationY.value

    /**
     * The current translation value for [MyZoomImage]
     */
    val translation: Offset
        get() = Offset(_translationX.value, _translationY.value)


    var scaledContentVisibleCenter: Offset = Offset.Zero
        private set
    var contentVisibleCenter: Offset = Offset.Zero
        private set

    var scaledContentVisibleRect: Rect = Rect.Zero
        private set
    var contentVisibleRect: Rect = Rect.Zero
        private set

    var coreRectOfContent: Rect = Rect.Zero
        private set
    var scaledCoreVisibleRect: Rect = Rect.Zero
        private set
    var coreVisibleRect: Rect = Rect.Zero
        private set

    init {
        require(minScale < maxScale) { "minScale must be < maxScale" }
    }

    /**
     * Instantly sets scale of [MyZoomImage] to given [scale]
     */
    suspend fun snapScaleTo(newScale: Float, percentageCentroidOfContent: Offset = Offset(0.5f, 0.5f)) {
        val spaceSize = contentSize.takeIf { it.isSpecified } ?: return
        val contentSize = contentSize.takeIf { it.isSpecified } ?: return
        val translation = computeContentScaleTranslation(
            scale = scale,
            spaceSize = spaceSize,
            contentSize = contentSize,
            translation = translation,
            newScale = newScale,
            contentScaleCenterPercentage = percentageCentroidOfContent
        )
        Log.d(
            "MyZoomState",
            "snapScaleTo. $scale -> $newScale, percentageCentroidOfContent=$percentageCentroidOfContent, translation=$translation"
        )
        coroutineScope {
            _scale.snapTo(newScale.coerceIn(minimumValue = minScale, maximumValue = maxScale))
            updateTranslationBounds("snapScaleTo")
            _translationX.snapTo(targetValue = _translationX.value + translation.x)
            _translationY.snapTo(targetValue = _translationY.value + translation.y)
            resetTransformInfos()
        }
    }

    /**
     * Animates scale of [MyZoomImage] to given [newScale]
     */
    suspend fun animateScaleTo(
        newScale: Float,
        percentageCentroidOfContent: Offset = Offset(0.5f, 0.5f),
        animationDurationMillis: Int = if (BuildConfig.DEBUG) 3000 else AnimationConstants.DefaultDurationMillis,
        animationEasing: Easing = FastOutSlowInEasing,
        initialVelocity: Float = 0f,
    ) {
        val spaceSize = contentSize.takeIf { it.isSpecified } ?: return
        val contentSize = contentSize.takeIf { it.isSpecified } ?: return
        val translation = computeContentScaleTranslation(
            scale = scale,
            spaceSize = spaceSize,
            contentSize = contentSize,
            translation = translation,
            newScale = newScale,
            contentScaleCenterPercentage = percentageCentroidOfContent
        )
        Log.i(
            "MyZoomState",
            "animateScaleTo. $scale -> $newScale, percentageCentroidOfContent=$percentageCentroidOfContent, translation=$translation"
        )
        coroutineScope {
            launch {
                _scale.animateTo(
                    targetValue = newScale.coerceIn(minScale, maxScale),
                    animationSpec = tween(
                        durationMillis = animationDurationMillis,
                        easing = animationEasing
                    ),
                    initialVelocity = initialVelocity,
                ) {
                    Log.d("MyZoomState", "animateScaleTo. running. scale=${this.value}")
                    updateTranslationBounds("animateScaleToScaling")
                    resetTransformInfos()
                }
            }
            launch {
                _translationX.animateTo(
                    targetValue = _translationX.value + translation.x,
                    animationSpec = tween(
                        durationMillis = animationDurationMillis,
                        easing = animationEasing
                    )
                ) {
                    Log.d("MyZoomState", "animateScaleTo. running. translationX=${this.value}")
                    resetTransformInfos()
                }
            }
            launch {
                _translationY.animateTo(
                    targetValue = _translationY.value + translation.y,
                    animationSpec = tween(
                        durationMillis = animationDurationMillis,
                        easing = animationEasing
                    )
                ) {
                    Log.d("MyZoomState", "animateScaleTo. running. translationY=${this.value}")
                    resetTransformInfos()
                }
            }
        }
    }

    suspend fun snapDoubleTapScale(percentageCentroidOfContent: Offset = Offset(0.5f, 0.5f)) {
        val nextDoubleTapScale = nextDoubleTapScale()
        Log.i("MyZoomState", "snapDoubleTapScale. nextDoubleTapScale=$nextDoubleTapScale, percentageCentroidOfContent=$percentageCentroidOfContent")
        snapScaleTo(nextDoubleTapScale, percentageCentroidOfContent)
    }

    suspend fun animateDoubleTapScale(percentageCentroidOfContent: Offset = Offset(0.5f, 0.5f)) {
        val nextDoubleTapScale = nextDoubleTapScale()
        Log.i("MyZoomState", "animateDoubleTapScale. nextDoubleTapScale=$nextDoubleTapScale, percentageCentroidOfContent=$percentageCentroidOfContent")
        animateScaleTo(nextDoubleTapScale(), percentageCentroidOfContent)
    }

    fun touchPointToPercentageCentroidOfContent(touchPoint: Offset): Offset {
        return computePercentageCentroidOfContentByTouchPoint(
            spaceSize = spaceSize,
            contentSize = contentSize,
            scale = scale,
            translation = translation,
            touchPoint = touchPoint
        )
    }

    fun nextDoubleTapScale(): Float {
        val scaleSteps = arrayOf(minScale, maxScale)
        val currentScale = scale
        val currentScaleIndex =
            scaleSteps.findLast { currentScale >= (it - 0.1f) }?.let { scaleSteps.indexOf(it) }
                ?: -1
        return if (currentScaleIndex != -1) {
            scaleSteps[(currentScaleIndex + 1) % scaleSteps.size]
        } else {
            scaleSteps.first()
        }
    }

    internal fun dragStart() {
        Log.i("MyZoomState", "drag. start. resetTracking")
        velocityTracker.resetTracking()
    }

    internal suspend fun drag(change: PointerInputChange, dragAmount: Offset) {
        val newTranslation = Offset(
            x = _translationX.value + dragAmount.x,
            y = _translationY.value + dragAmount.y
        )
        Log.d(
            "MyZoomState",
            "drag. running. dragAmount=$dragAmount, newTranslation=$newTranslation"
        )
        velocityTracker.addPointerInputChange(change)
        coroutineScope {
            launch {
                _translationX.snapTo(newTranslation.x)
                _translationY.snapTo(newTranslation.y)
                resetTransformInfos()
            }
        }
    }

    internal suspend fun dragEnd() {
        Log.i("MyZoomState", "drag. end")
        fling(velocityTracker.calculateVelocity())
    }

    internal fun dragCancel() {
//        Log.d("MyZoomState", "dragCancel")
    }

    internal suspend fun transform(
        zoomChange: Float,
        @Suppress("UNUSED_PARAMETER") panChange: Offset,
        @Suppress("UNUSED_PARAMETER") rotationChange: Float,
        percentageCentroidOfContent: Offset = Offset(0.5f, 0.5f),   // todo 尚未利用
    ) = snapScaleTo(scale * zoomChange, percentageCentroidOfContent = Offset(0.5f, 0.5f))

//    internal fun isHorizontalDragFinish(dragDistance: Offset): Boolean {
//        val lowerBounds = _translationX.lowerBound ?: return false
//        val upperBounds = _translationX.upperBound ?: return false
//        if (lowerBounds == 0f && upperBounds == 0f) return true
//
//        val newPosition = _translationX.value + dragDistance.x
//        if (newPosition <= lowerBounds) {
//            return true
//        }
//
//        if (newPosition >= upperBounds) {
//            return true
//        }
//        return false
//    }
//
//    internal fun isVerticalDragFinish(dragDistance: Offset): Boolean {
//        val lowerBounds = _translationY.lowerBound ?: return false
//        val upperBounds = _translationY.upperBound ?: return false
//        if (lowerBounds == 0f && upperBounds == 0f) return true
//
//        val newPosition = _translationY.value + dragDistance.y
//        if (newPosition <= lowerBounds) {
//            return true
//        }
//
//        if (newPosition >= upperBounds) {
//            return true
//        }
//        return false
//    }

    private suspend fun fling(velocity: Velocity) = coroutineScope {
        Log.i("MyZoomState", "fling. velocity=$velocity, translation=$translation")
        launch {
            _translationX.animateDecay(velocity.x, exponentialDecay()) {
                Log.d(
                    "MyZoomState",
                    "fling. running. velocity=$velocity, translationX=${this.value}"
                )
                resetTransformInfos()
            }
        }
        launch {
            _translationY.animateDecay(velocity.y, exponentialDecay()) {
                Log.d(
                    "MyZoomState",
                    "fling. running. velocity=$velocity, translationY=${this.value}"
                )
                resetTransformInfos()
            }
        }
    }

    override fun toString(): String =
        "ZoomableState(minScale=$minScale, maxScale=$maxScale, scale=$scale, translation=$translation"

    private fun updateTranslationBounds(caller: String) {
        // todo 使用 coreSize
        val bounds = computeTranslationBoundsWithTopLeftScale(spaceSize, contentSize, scale)
        _translationX.updateBounds(lowerBound = bounds.left, upperBound = bounds.right)
        _translationY.updateBounds(lowerBound = bounds.top, upperBound = bounds.bottom)
        Log.d(
            "MyZoomState",
            "updateTranslationBounds. $caller. bounds=$bounds, spaceSize=$spaceSize, contentSize=${contentSize}, scale=$scale"
        )
    }

    private fun resetFixedInfos() {
        coreRectOfContent = computeScaledCoreRectOfContent(
            contentSize = contentSize,
            coreSize = coreSize,
            coreScale = coreScale
        )
    }

    private fun resetTransformInfos() {
        scaledContentVisibleCenter = computeScaledContentVisibleCenter(
            spaceSize = spaceSize,
            contentSize = contentSize,
            scale = scale,
            translation = translation
        )
        contentVisibleCenter = scaledContentVisibleCenter / scale

        scaledContentVisibleRect = computeScaledContentVisibleRectWithTopLeftScale(
            spaceSize = spaceSize,
            contentSize = contentSize,
            scale = scale,
            translation = translation
        )
        contentVisibleRect = scaledContentVisibleRect.restoreScale(scale)

        scaledCoreVisibleRect = computeScaledCoreVisibleRect(
            contentSize = contentSize,
            visibleRectOfContent = contentVisibleRect,
            coreSize = coreSize,
            coreScale = coreScale,
        )

        val coreScaleFactor = computeScaleFactor(
            contentSize = contentSize,
            coreSize = coreSize,
            coreScale = coreScale
        )
        coreVisibleRect = computeScaledCoreVisibleRect(
            contentSize = contentSize,
            visibleRectOfContent = contentVisibleRect,
            coreSize = coreSize,
            coreScale = coreScale,
        ).restoreScale(coreScaleFactor)
    }

    companion object {
        /**
         * The default [Saver] implementation for [MyZoomState].
         */
        val Saver: Saver<MyZoomState, *> = listSaver(
            save = {
                listOf(
                    it.translationX,
                    it.translationY,
                    it.scale,
                    it.minScale,
                    it.maxScale,
                )
            },
            restore = {
                MyZoomState(
                    initialTranslateX = it[0],
                    initialTranslateY = it[1],
                    initialScale = it[2],
                    minScale = it[3],
                    maxScale = it[4],
                )
            }
        )
    }
}