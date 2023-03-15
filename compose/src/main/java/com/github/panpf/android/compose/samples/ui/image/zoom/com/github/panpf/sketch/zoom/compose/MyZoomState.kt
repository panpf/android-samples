package com.github.panpf.sketch.zoom.compose

import android.util.Log
import androidx.annotation.FloatRange
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.exponentialDecay
import androidx.compose.animation.core.spring
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.isSpecified
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.util.VelocityTracker
import androidx.compose.ui.input.pointer.util.addPointerInputChange
import androidx.compose.ui.unit.Velocity
import com.github.panpf.android.compose.samples.ui.image.zoom.com.github.panpf.sketch.zoom.compose.computeContentScaleTranslation
import com.github.panpf.android.compose.samples.ui.image.zoom.com.github.panpf.sketch.zoom.compose.computeTranslationBoundsWithTopLeftScale
import com.github.panpf.android.compose.samples.ui.image.zoom.com.github.panpf.sketch.zoom.compose.computeVisibleCenterOfContent
import kotlinx.coroutines.async
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
    private val _translation =
        Animatable(Offset(initialTranslateX, initialTranslateY), Offset.VectorConverter)
    private val _scale = Animatable(initialScale)
    // todo rotate

    val transformOrigin = TransformOrigin(0f, 0f)

    var spaceSize: Size = Size.Unspecified
        internal set(value) {
            val changed = value != field
            field = value
            if (changed) {
                updateTranslationBounds("spaceSizeChanged")
            }
        }
    var contentSize: Size = Size.Unspecified
        internal set(value) {
            val changed = value != field
            field = value
            if (changed) {
                updateTranslationBounds("contentSizeChanged")
            }
        }
    var realContentSize: Size = Size.Unspecified
        internal set(value) {
            val changed = value != field
            field = value
            if (changed) {
                updateTranslationBounds("realContentSizeChanged")
            }
        }

    init {
        require(minScale < maxScale) { "minScale must be < maxScale" }
    }

    /**
     * The current scale value for [MyZoomImage]
     */
    @get:FloatRange(from = 0.0)
    val scale: Float
        get() = _scale.value

    /**
     * The current translation value for [MyZoomImage]
     */
    val translation: Offset
        get() = _translation.value


    val centerOfContent: Offset
        get() = computeVisibleCenterOfContent(
            spaceSize = spaceSize,
            contentSize = contentSize,
            scale = scale,
            translation = translation
        )

    internal val zooming: Boolean
        get() = scale > minScale

    /**
     * Instantly sets scale of [MyZoomImage] to given [scale]
     */
    suspend fun snapScaleTo(newScale: Float, centroidInContent: Offset = Offset(0.5f, 0.5f)) {
        // todo centroidInContent
        Log.d(
            "MyZoomState",
            "snapScaleTo. $scale -> $newScale, centroidInContent=$centroidInContent"
        )
        coroutineScope {
            _scale.snapTo(newScale.coerceIn(minimumValue = minScale, maximumValue = maxScale))
            updateTranslationBounds("snapScaleTo")
        }
    }

    /**
     * Animates scale of [MyZoomImage] to given [newScale]
     */
    suspend fun animateScaleTo(
        newScale: Float,
        centroidInContent: Offset = Offset(0.5f, 0.5f),
        animationSpec: AnimationSpec<Float> = spring(stiffness = Spring.StiffnessLow),
        initialVelocity: Float = 0f,
    ) {
        val spaceSize = contentSize.takeIf { it.isSpecified } ?: return
        val contentSize = contentSize.takeIf { it.isSpecified } ?: return

        val translation =
            computeContentScaleTranslation(
                scale = scale,
                spaceSize = spaceSize,
                contentSize = contentSize,
                translation = translation,
                newScale = newScale,
                contentScaleCenterPercentage = centroidInContent
            )
        Log.d(
            "MyZoomState",
            "animateScaleTo. $scale -> $newScale, centroidInContent=$centroidInContent, translation=$translation"
        )
        coroutineScope {
            async {
                _scale.animateTo(
                    targetValue = newScale.coerceIn(
                        minimumValue = minScale,
                        maximumValue = maxScale
                    ),
                    animationSpec = animationSpec,
                    initialVelocity = initialVelocity,
                ) {
                    Log.d("MyZoomState", "animateScaleTo. running. scale=${this.value}")
                    updateTranslationBounds("animateScaleToScaling")
                }
            }
            async {
                _translation.animateTo(
                    targetValue = _translation.value + translation,
                    animationSpec = spring(stiffness = Spring.StiffnessLow)
                ) {
                    Log.d("MyZoomState", "animateScaleTo. running. translation=${this.value}")
                }
            }
        }
    }

    suspend fun snapDoubleTapScale(centroidInContent: Offset = Offset(0.5f, 0.5f)) {
        val nextDoubleTapScale = nextDoubleTapScale()
        Log.d("MyZoomState", "snapDoubleTapScale. nextDoubleTapScale=$nextDoubleTapScale")
        snapScaleTo(nextDoubleTapScale, centroidInContent)
    }

    suspend fun animateDoubleTapScale(centroidInContent: Offset = Offset(0.5f, 0.5f)) {
        val nextDoubleTapScale = nextDoubleTapScale()
        Log.d("MyZoomState", "animateDoubleTapScale. nextDoubleTapScale=$nextDoubleTapScale")
        animateScaleTo(nextDoubleTapScale(), centroidInContent)
    }

    fun touchPointToCentroidInContent(touchPoint: Offset): Offset {
        val contentSize = contentSize
        return if (contentSize.isSpecified) {
            // todo 现在只是默认最小比例的情况下，还得兼容已经有缩放和位移的情况
            Offset(x = touchPoint.x / contentSize.width, y = touchPoint.y / contentSize.height)
        } else {
            Offset.Zero
        }
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
        Log.d("MyZoomState", "dragStart. resetTracking")
        velocityTracker.resetTracking()
    }

    internal suspend fun drag(change: PointerInputChange, dragAmount: Offset) {
        val newTranslation = (_translation.value + dragAmount)
        Log.d("MyZoomState", "drag. dragAmount=$dragAmount, newTranslation=$newTranslation")
        velocityTracker.addPointerInputChange(change)
        coroutineScope {
            launch {
                _translation.snapTo(newTranslation)
            }
        }
    }

    internal suspend fun dragEnd() {
        Log.d("MyZoomState", "dragEnd")
        fling(velocityTracker.calculateVelocity())
    }

    internal fun dragCancel() {
//        Log.d("MyZoomState", "dragCancel")
    }

    internal suspend fun transform(zoomChange: Float, panChange: Offset, rotationChange: Float) =
        snapScaleTo(scale * zoomChange)

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
        Log.d("MyZoomState", "fling. velocity=$velocity, translation=$translation")
        launch {
            _translation.animateDecay(Offset(velocity.x, velocity.y), exponentialDecay()) {
                Log.d("MyZoomState", "fling. running. velocity=$velocity, translation=$translation")
            }
        }
    }

    override fun toString(): String =
        "ZoomableState(minScale=$minScale, maxScale=$maxScale, scale=$scale, translation=$translation"

    private fun updateTranslationBounds(caller: String) {
        val bounds = computeTranslationBoundsWithTopLeftScale(spaceSize, contentSize, scale)
        _translation.updateBounds(
            lowerBound = Offset(bounds.left, bounds.top),
            upperBound = Offset(bounds.right, bounds.bottom)
        )
//        _translationY.updateBounds(lowerBound = bounds.top, upperBound = bounds.bottom)
        Log.d(
            "MyZoomState",
            "updateTranslationBounds. $caller. bounds=$bounds, spaceSize=$spaceSize, contentSize=${contentSize}, scale=$scale"
        )
    }

    companion object {
        /**
         * The default [Saver] implementation for [MyZoomState].
         */
        val Saver: Saver<MyZoomState, *> = listSaver(
            save = {
                listOf(
                    it.translation.x,
                    it.translation.y,
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