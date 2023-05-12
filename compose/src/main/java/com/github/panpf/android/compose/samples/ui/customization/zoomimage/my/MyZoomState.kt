package com.github.panpf.android.compose.samples.ui.customization.zoomimage.my

import android.util.Log
import androidx.annotation.FloatRange
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.exponentialDecay
import androidx.compose.animation.core.tween
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.mapSaver
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
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class MyZoomState(
    @FloatRange(from = 0.0) val minScale: Float = 1f,
    @FloatRange(from = 0.0) val maxScale: Float = 4f,
    @FloatRange(from = 0.0) initialTranslateX: Float = 0f,
    @FloatRange(from = 0.0) initialTranslateY: Float = 0f,
    @FloatRange(from = 0.0) initialScale: Float = minScale,
    val debugMode: Boolean = false
) {

    private val velocityTracker = VelocityTracker()
    private val _translationX = Animatable(initialTranslateX)
    private val _translationY = Animatable(initialTranslateY)
    private val _scale = Animatable(initialScale)
    // todo rotate

    val transformOrigin = TransformOrigin(0f, 0f)

    var containerSize: Size = Size.Unspecified
        internal set(value) {
            val changed = value != field
            field = value
            if (changed) {
                updateTranslationBounds("containerSizeChanged")
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
    var contentScale: ContentScale = ContentScale.Fit
        internal set(value) {
            val changed = value != field
            field = value
            if (changed) {
                updateTranslationBounds("contentScaleChanged")
                resetFixedInfos()
                resetTransformInfos()
            }
        }

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

    val zooming: Boolean
        get() = scale > minScale


    var visibleRect: Rect = Rect.Zero
        private set
    var contentVisibleRect: Rect = Rect.Zero
        private set
    var contentOfContainerRect: Rect = Rect.Zero
        private set

    init {
        require(minScale < maxScale) { "minScale must be < maxScale" }
    }

    suspend fun snapScaleToByRelativelyCentroid(
        newScale: Float,
        relativelyCentroid: RelativelyCentroid = RelativelyCentroid(0.5f, 0.5f)
    ) {
        val containerSize = containerSize.takeIf { it.isSpecified } ?: return
        val currentScale = scale
        val finalRelativelyCentroid = if (newScale < currentScale)
            RelativelyCentroid(0.5f, 0.5f) else relativelyCentroid
        val scaleTranslation = computeScaleTranslation(
            currentScale = currentScale,
            containerSize = containerSize,
            translation = translation,
            newScale = newScale,
            relativelyCentroid = finalRelativelyCentroid
        )
        if (debugMode) {
            Log.d(
                "MyZoomState",
                "snapScaleToByRelativelyCentroid. $currentScale -> $newScale, finalRelativelyCentroid=$finalRelativelyCentroid, scaleTranslation=$scaleTranslation"
            )
        }
        coroutineScope {
            _scale.snapTo(newScale.coerceIn(minimumValue = minScale, maximumValue = maxScale))
            updateTranslationBounds("snapScaleTo")
            _translationX.snapTo(targetValue = _translationX.value + scaleTranslation.x)
            _translationY.snapTo(targetValue = _translationY.value + scaleTranslation.y)
            resetTransformInfos()
        }
    }

    /**
     * Instantly sets scale of [MyZoomImage] to given [scale]
     */
    suspend fun snapScaleToByTouchPosition(newScale: Float, touchPosition: Offset) {
        val currentScale = scale
        if (debugMode) {
            Log.d(
                "MyZoomState",
                "snapScaleToByTouchPosition. $currentScale -> $newScale, touchPosition=$touchPosition"
            )
        }
        val relativelyCentroid = computeRelativelyCentroidOfContainerByTouchPosition(
            containerSize = containerSize,
            scale = scale,
            translation = translation,
            touchPosition = touchPosition
        )
        snapScaleToByRelativelyCentroid(
            newScale = newScale,
            relativelyCentroid = relativelyCentroid
        )
    }

    /**
     * Animates scale of [MyZoomImage] to given [newScale]
     */
    suspend fun animateScaleToByRelativelyCentroid(
        newScale: Float,
        relativelyCentroid: RelativelyCentroid = RelativelyCentroid(0.5f, 0.5f),
        animationDurationMillis: Int = ScaleAnimationConfig.DefaultDurationMillis,
        animationEasing: Easing = ScaleAnimationConfig.DefaultEasing,
        initialVelocity: Float = ScaleAnimationConfig.DefaultInitialVelocity,
    ) {
        val containerSize = containerSize.takeIf { it.isSpecified } ?: return
        val currentScale = scale
        val finalRelativelyCentroid = if (newScale < currentScale)
            RelativelyCentroid(0.5f, 0.5f) else relativelyCentroid
        if (newScale > currentScale) {
            updateTranslationBounds("animateScaleToScaling", newScale)
            val scaleTranslation = computeScaleTranslation(
                currentScale = currentScale,
                containerSize = containerSize,
                translation = translation,
                newScale = newScale,
                relativelyCentroid = finalRelativelyCentroid
            ).let {
                it.copy(
                    x = it.x.coerceIn(_translationX.lowerBound, _translationX.upperBound),
                    y = it.y.coerceIn(_translationY.lowerBound, _translationY.upperBound),
                )
            }
            if (debugMode) {
                Log.i(
                    "MyZoomState",
                    "animateScaleTo. $currentScale -> $newScale, finalRelativelyCentroid=$finalRelativelyCentroid, scaleTranslation=$scaleTranslation"
                )
            }
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
                        if (debugMode) {
                            Log.d("MyZoomState", "animateScaleTo. running. scale=${this.value}")
                        }
                        resetTransformInfos()
                    }
                }
                launch {
                    _translationX.animateTo(
                        targetValue = _translationX.value + scaleTranslation.x,
                        animationSpec = tween(
                            durationMillis = animationDurationMillis,
                            easing = animationEasing
                        )
                    ) {
                        if (debugMode) {
                            Log.d(
                                "MyZoomState",
                                "animateScaleTo. running. translationX=${this.value}"
                            )
                        }
                        resetTransformInfos()
                    }
                }
                launch {
                    _translationY.animateTo(
                        targetValue = _translationY.value + scaleTranslation.y,
                        animationSpec = tween(
                            durationMillis = animationDurationMillis,
                            easing = animationEasing
                        )
                    ) {
                        if (debugMode) {
                            Log.d(
                                "MyZoomState",
                                "animateScaleTo. running. translationY=${this.value}"
                            )
                        }
                        resetTransformInfos()
                    }
                }
            }
        } else {
            val translation = computeScaleTranslation(
                currentScale = currentScale,
                containerSize = containerSize,
                translation = translation,
                newScale = newScale,
                relativelyCentroid = finalRelativelyCentroid
            )
            if (debugMode) {
                Log.i(
                    "MyZoomState",
                    "animateScaleTo. $currentScale -> $newScale, percentageCentroidOfContent=$finalRelativelyCentroid, translation=$translation"
                )
            }
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
                        if (debugMode) {
                            Log.d("MyZoomState", "animateScaleTo. running. scale=${this.value}")
                        }
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
                        if (debugMode) {
                            Log.d(
                                "MyZoomState",
                                "animateScaleTo. running. translationX=${this.value}"
                            )
                        }
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
                        if (debugMode) {
                            Log.d(
                                "MyZoomState",
                                "animateScaleTo. running. translationY=${this.value}"
                            )
                        }
                        resetTransformInfos()
                    }
                }
            }
        }
    }

    /**
     * Animates scale of [MyZoomImage] to given [newScale]
     */
    suspend fun animateScaleToByTouchPosition(
        newScale: Float,
        touchPosition: Offset,
        animationDurationMillis: Int = ScaleAnimationConfig.DefaultDurationMillis,
        animationEasing: Easing = ScaleAnimationConfig.DefaultEasing,
        initialVelocity: Float = ScaleAnimationConfig.DefaultInitialVelocity,
    ) {
        val currentScale = scale
        if (debugMode) {
            Log.d(
                "MyZoomState",
                "animateScaleToByTouchPosition. $currentScale -> $newScale, touchPosition=$touchPosition"
            )
        }
        val relativelyCentroid = computeRelativelyCentroidOfContainerByTouchPosition(
            containerSize = containerSize,
            scale = scale,
            translation = translation,
            touchPosition = touchPosition
        )
        animateScaleToByRelativelyCentroid(
            newScale = newScale,
            relativelyCentroid = relativelyCentroid,
            animationDurationMillis = animationDurationMillis,
            animationEasing = animationEasing,
            initialVelocity = initialVelocity,
        )
    }

    suspend fun snapDoubleTapScaleByRelativelyCentroid(
        relativelyCentroid: RelativelyCentroid = RelativelyCentroid(0.5f, 0.5f)
    ) {
        val nextDoubleTapScale = nextDoubleTapScale()
        if (debugMode) {
            Log.i(
                "MyZoomState",
                "snapDoubleTapScaleByRelativelyCentroid. nextDoubleTapScale=$nextDoubleTapScale, relativelyCentroid=$relativelyCentroid"
            )
        }
        snapScaleToByRelativelyCentroid(
            newScale = nextDoubleTapScale,
            relativelyCentroid = relativelyCentroid
        )
    }

    suspend fun snapDoubleTapScaleByTouchPosition(touchPosition: Offset) {
        val nextDoubleTapScale = nextDoubleTapScale()
        if (debugMode) {
            Log.i(
                "MyZoomState",
                "snapDoubleTapScaleByTouchPosition. nextDoubleTapScale=$nextDoubleTapScale, touchPosition=$touchPosition"
            )
        }
        snapScaleToByTouchPosition(newScale = nextDoubleTapScale, touchPosition = touchPosition)
    }

    suspend fun animateDoubleTapScaleByRelativelyCentroid(
        relativelyCentroid: RelativelyCentroid = RelativelyCentroid(0.5f, 0.5f),
        animationDurationMillis: Int = ScaleAnimationConfig.DefaultDurationMillis,
        animationEasing: Easing = ScaleAnimationConfig.DefaultEasing,
        initialVelocity: Float = ScaleAnimationConfig.DefaultInitialVelocity,
    ) {
        val nextDoubleTapScale = nextDoubleTapScale()
        if (debugMode) {
            Log.i(
                "MyZoomState",
                "animateDoubleTapScaleByRelativelyCentroid. nextDoubleTapScale=$nextDoubleTapScale, relativelyCentroid=$relativelyCentroid"
            )
        }
        animateScaleToByRelativelyCentroid(
            newScale = nextDoubleTapScale,
            relativelyCentroid = relativelyCentroid,
            animationDurationMillis = animationDurationMillis,
            animationEasing = animationEasing,
            initialVelocity = initialVelocity,
        )
    }

    suspend fun animateDoubleTapScaleByTouchPosition(
        touchPosition: Offset,
        animationDurationMillis: Int = ScaleAnimationConfig.DefaultDurationMillis,
        animationEasing: Easing = ScaleAnimationConfig.DefaultEasing,
        initialVelocity: Float = ScaleAnimationConfig.DefaultInitialVelocity,
    ) {
        val nextDoubleTapScale = nextDoubleTapScale()
        if (debugMode) {
            Log.i(
                "MyZoomState",
                "animateDoubleTapScaleByTouchPosition. nextDoubleTapScale=$nextDoubleTapScale, touchPosition=$touchPosition"
            )
        }
        animateScaleToByTouchPosition(
            newScale = nextDoubleTapScale,
            touchPosition = touchPosition,
            animationDurationMillis = animationDurationMillis,
            animationEasing = animationEasing,
            initialVelocity = initialVelocity,
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
        if (debugMode) {
            Log.i("MyZoomState", "drag. start. resetTracking")
        }
        velocityTracker.resetTracking()
    }

    internal suspend fun drag(change: PointerInputChange, dragAmount: Offset) {
        val newTranslation = Offset(
            x = _translationX.value + dragAmount.x,
            y = _translationY.value + dragAmount.y
        )
        if (debugMode) {
            Log.d(
                "MyZoomState",
                "drag. running. dragAmount=$dragAmount, newTranslation=$newTranslation"
            )
        }
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
        if (debugMode) {
            Log.i("MyZoomState", "drag. end")
        }
        fling(velocityTracker.calculateVelocity())
    }

    internal fun dragCancel() {
        if (debugMode) {
            Log.i("MyZoomState", "drag. cancel")
        }
    }

    internal suspend fun transform(zoomChange: Float, touchCentroid: Offset) {
        val currentScale = scale
        val newScale =
            (currentScale * zoomChange).coerceIn(minimumValue = minScale, maximumValue = maxScale)
        val addCentroidOffset = Offset(
            x = (newScale - currentScale) * touchCentroid.x * -1,
            y = (newScale - currentScale) * touchCentroid.y * -1
        )
        val newTranslation = Offset(
            x = _translationX.value + addCentroidOffset.x,
            y = _translationY.value + addCentroidOffset.y
        )
        if (debugMode) {
            Log.d(
                "MyZoomState",
                "transform. zoomChange=$zoomChange, " +
                        "touchCentroid=$touchCentroid, " +
                        "newScale=$newScale, " +
                        "addCentroidOffset=$addCentroidOffset, " +
                        "newTranslation=$newTranslation"
            )
        }
        coroutineScope {
            _scale.snapTo(newScale)
            updateTranslationBounds("snapScaleTo")
            _translationX.snapTo(targetValue = newTranslation.x)
            _translationY.snapTo(targetValue = newTranslation.y)
            resetTransformInfos()
        }
    }

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
        if (debugMode) {
            Log.i("MyZoomState", "fling. velocity=$velocity, translation=$translation")
        }
        launch {
            _translationX.animateDecay(velocity.x, exponentialDecay()) {
                if (debugMode) {
                    Log.d(
                        "MyZoomState",
                        "fling. running. velocity=$velocity, translationX=${this.value}"
                    )
                }
                resetTransformInfos()
            }
        }
        launch {
            _translationY.animateDecay(velocity.y, exponentialDecay()) {
                if (debugMode) {
                    Log.d(
                        "MyZoomState",
                        "fling. running. velocity=$velocity, translationY=${this.value}"
                    )
                }
                resetTransformInfos()
            }
        }
    }

    override fun toString(): String =
        "MyZoomState(minScale=$minScale, maxScale=$maxScale, scale=$scale, translation=$translation"

    private fun updateTranslationBounds(caller: String, newScale: Float? = null) {
        val finalScale = newScale ?: scale
        val contentSize = containerSize // todo 使用 contentSize
        val bounds = computeTranslationBounds(containerSize, containerSize, finalScale)
        _translationX.updateBounds(lowerBound = bounds.left, upperBound = bounds.right)
        _translationY.updateBounds(lowerBound = bounds.top, upperBound = bounds.bottom)
        if (debugMode) {
            Log.d(
                "MyZoomState",
                "updateTranslationBounds. $caller. bounds=$bounds, containerSize=$containerSize, contentSize=${contentSize}, scale=$finalScale"
            )
        }
    }

    private fun resetFixedInfos() {
        contentOfContainerRect = computeContentOfContainerRect(
            containerSize = containerSize,
            contentSize = contentSize,
            contentScale = contentScale
        )
    }

    private fun resetTransformInfos() {
        val scaledVisibleRect = computeScaledVisibleRect(
            containerSize = containerSize,
            scale = scale,
            translation = translation
        )
        val scale = scale
        visibleRect = scaledVisibleRect.restoreScale(scale)

        val scaledContentVisibleRect = computeScaledContentVisibleRect(
            containerSize = containerSize,
            visibleRectOfContent = visibleRect,
            contentSize = contentSize,
            contentScale = contentScale,
        )
        val contentScaleFactor = computeScaleFactor(
            containerSize = containerSize,
            contentSize = contentSize,
            contentScale = contentScale
        )
        contentVisibleRect = scaledContentVisibleRect.restoreScale(contentScaleFactor)
    }

    companion object {

        /**
         * The default [Saver] implementation for [MyZoomState].
         */
        val Saver: Saver<MyZoomState, *> = mapSaver(
            save = {
                mapOf(
                    "translationX" to it.translationX,
                    "translationY" to it.translationY,
                    "scale" to it.scale,
                    "minScale" to it.minScale,
                    "maxScale" to it.maxScale,
                    "debugMode" to it.debugMode,
                )
            },
            restore = {
                MyZoomState(
                    initialTranslateX = it["translationX"] as Float,
                    initialTranslateY = it["translationY"] as Float,
                    initialScale = it["scale"] as Float,
                    minScale = it["minScale"] as Float,
                    maxScale = it["maxScale"] as Float,
                    debugMode = it["debugMode"] as Boolean,
                )
            }
        )
    }
}