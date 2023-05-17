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
import com.github.panpf.android.compose.samples.tools.toShortString
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
    // todo 处理动画冲突，双击缩放中，手指滑动要先停止双击缩放动画，然后再开始滑动

    val transformOrigin = TransformOrigin(0f, 0f)

    var containerSize: Size = Size.Unspecified
        internal set(value) {
            val changed = value != field
            field = value
            if (changed) {
                updateTranslationBounds("containerSizeChanged")
                resetFixedInfo()
                resetTransformInfo()
            }
        }
    var contentSize: Size = Size.Unspecified
        internal set(value) {
            val changed = value != field
            field = value
            if (changed) {
                updateTranslationBounds("contentSizeChanged")
                resetFixedInfo()
                resetTransformInfo()
            }
        }
    var contentScale: ContentScale = ContentScale.Fit
        internal set(value) {
            val changed = value != field
            field = value
            if (changed) {
                updateTranslationBounds("contentScaleChanged")
                resetFixedInfo()
                resetTransformInfo()
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
    var translationBounds: Rect = Rect.Zero
        private set

    init {
        require(minScale < maxScale) { "minScale must be < maxScale" }
    }

    /**
     * Animates scale of [MyZoomImage] to given [newScale]
     */
    suspend fun animateScaleTo(
        newScale: Float,
        newScaleCentroid: Centroid = Centroid(0.5f, 0.5f),
        animationDurationMillis: Int = ScaleAnimationConfig.DefaultDurationMillis,
        animationEasing: Easing = ScaleAnimationConfig.DefaultEasing,
        initialVelocity: Float = ScaleAnimationConfig.DefaultInitialVelocity,
    ) {
        val containerSize = containerSize.takeIf { it.isSpecified } ?: return
        val contentSize = contentSize.takeIf { it.isSpecified } ?: return
        val contentScale = contentScale
        val currentScale = scale
        val currentTranslation = translation

        val animationSpec = tween<Float>(
            durationMillis = animationDurationMillis,
            easing = animationEasing
        )
        val futureTranslationBounds = computeTranslationBounds(
            containerSize = containerSize,
            contentSize = contentSize,
            contentScale = contentScale,
            scale = newScale
        )
        val targetTranslation = computeScaleTargetTranslation(
            containerSize = containerSize,
            scale = newScale,
            centroid = newScaleCentroid
        ).let {
            it.copy(
                x = it.x.coerceIn(futureTranslationBounds.left, futureTranslationBounds.right),
                y = it.y.coerceIn(futureTranslationBounds.top, futureTranslationBounds.bottom),
            )
        }
        logI {
            """animateScaleTo. size: containerSize=${containerSize.toShortString()}, contentSize=${contentSize.toShortString()}
                scale: $currentScale -> $newScale, centroid=${newScaleCentroid.toShortString()} 
                translation: ${currentTranslation.toShortString()} -> ${targetTranslation.toShortString()}, bounds=${futureTranslationBounds.toShortString()}
            """.trimIndent()
        }
        clearTranslationBounds("animateScaleTo. before")
        coroutineScope {
            launch {
                _scale.animateTo(
                    targetValue = newScale.coerceIn(minScale, maxScale),
                    animationSpec = animationSpec,
                    initialVelocity = initialVelocity,
                ) {
                    logD { "animateScaleTo. running. scale=${this.value}, translation=${translation}" }
                    resetTransformInfo()
                }
                updateTranslationBounds("animateScaleTo. end")
                logD { "animateScaleTo. end. scale=${scale}, translation=${translation}" }
            }
            launch {
                _translationX.animateTo(
                    targetValue = targetTranslation.x,
                    animationSpec = animationSpec,
                    block = { resetTransformInfo() }
                )
            }
            launch {
                _translationY.animateTo(
                    targetValue = targetTranslation.y,
                    animationSpec = animationSpec,
                    block = { resetTransformInfo() }
                )
            }
        }
    }

    /**
     * Animates scale of [MyZoomImage] to given [newScale]
     */
    suspend fun animateScaleTo(
        newScale: Float,
        touchPosition: Offset,
        animationDurationMillis: Int = ScaleAnimationConfig.DefaultDurationMillis,
        animationEasing: Easing = ScaleAnimationConfig.DefaultEasing,
        initialVelocity: Float = ScaleAnimationConfig.DefaultInitialVelocity,
    ) {
        val containerSize = containerSize.takeIf { it.isSpecified } ?: return
        val currentScale = scale
        val currentTranslation = translation
        val newScaleCentroid = computeScaleCentroidByTouchPosition(
            containerSize = containerSize,
            scale = currentScale,
            translation = currentTranslation,
            touchPosition = touchPosition
        )
        logI { "animateScaleTo. newScale=$newScale, touchPosition=$touchPosition, newScaleCentroid=$newScaleCentroid" }
        animateScaleTo(
            newScale = newScale,
            newScaleCentroid = newScaleCentroid,
            animationDurationMillis = animationDurationMillis,
            animationEasing = animationEasing,
            initialVelocity = initialVelocity,
        )
    }

    /**
     * Instantly sets scale of [MyZoomImage] to given [newScale]
     */
    suspend fun snapScaleTo(newScale: Float, newScaleCentroid: Centroid = Centroid(0.5f, 0.5f)) {
        val containerSize = containerSize.takeIf { it.isSpecified } ?: return
        val contentSize = contentSize.takeIf { it.isSpecified } ?: return
        val contentScale = contentScale
        val currentScale = scale
        val currentTranslation = translation

        val futureTranslationBounds = computeTranslationBounds(
            containerSize = containerSize,
            contentSize = contentSize,
            contentScale = contentScale,
            scale = newScale
        )
        val targetTranslation = computeScaleTargetTranslation(
            containerSize = containerSize,
            scale = newScale,
            centroid = newScaleCentroid
        ).let {
            it.copy(
                x = it.x.coerceIn(futureTranslationBounds.left, futureTranslationBounds.right),
                y = it.y.coerceIn(futureTranslationBounds.top, futureTranslationBounds.bottom),
            )
        }
        logI {
            """snapScaleTo. size: containerSize=${containerSize.toShortString()}, contentSize=${contentSize.toShortString()} 
                scale: $currentScale -> $newScale, centroid=${newScaleCentroid.toShortString()}
                translation: ${currentTranslation.toShortString()} -> ${targetTranslation.toShortString()}, bounds=${futureTranslationBounds.toShortString()}
            """.trimIndent()
        }
        coroutineScope {
            _scale.snapTo(newScale.coerceIn(minimumValue = minScale, maximumValue = maxScale))
            updateTranslationBounds("snapScaleTo")
            _translationX.snapTo(targetValue = targetTranslation.x)
            _translationY.snapTo(targetValue = targetTranslation.y)
            resetTransformInfo()
        }
    }

    /**
     * Instantly sets scale of [MyZoomImage] to given [newScale]
     */
    suspend fun snapScaleTo(newScale: Float, touchPosition: Offset) {
        val containerSize = containerSize.takeIf { it.isSpecified } ?: return
        val currentScale = scale
        val currentTranslation = translation
        val newScaleCentroid = computeScaleCentroidByTouchPosition(
            containerSize = containerSize,
            scale = currentScale,
            translation = currentTranslation,
            touchPosition = touchPosition
        )
        logI { "snapScaleTo. newScale=$newScale, touchPosition=$touchPosition, newScaleCentroid=$newScaleCentroid" }
        snapScaleTo(
            newScale = newScale,
            newScaleCentroid = newScaleCentroid
        )
    }

    fun nextScale(): Float {
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
        logI { "drag. start. resetTracking" }
        velocityTracker.resetTracking()
    }

    internal suspend fun drag(change: PointerInputChange, dragAmount: Offset) {
        val newTranslation = Offset(
            x = _translationX.value + dragAmount.x,
            y = _translationY.value + dragAmount.y
        )
        logD { "drag. running. dragAmount=$dragAmount, newTranslation=$newTranslation" }
        velocityTracker.addPointerInputChange(change)
        coroutineScope {
            launch {
                _translationX.snapTo(newTranslation.x)
                _translationY.snapTo(newTranslation.y)
                resetTransformInfo()
            }
        }
    }

    internal suspend fun dragEnd() {
        logI { "drag. end" }
        fling(velocityTracker.calculateVelocity())
    }

    internal fun dragCancel() {
        logI { "drag. cancel" }
    }

    internal suspend fun transform(zoomChange: Float, touchCentroid: Offset) {
        val currentScale = scale
        val newScale =
            (currentScale * zoomChange).coerceIn(minimumValue = minScale, maximumValue = maxScale)
        val addCentroidOffset = Offset(
            x = (newScale - currentScale) * touchCentroid.x * -1,
            y = (newScale - currentScale) * touchCentroid.y * -1
        )
        val targetTranslation = Offset(
            x = _translationX.value + addCentroidOffset.x,
            y = _translationY.value + addCentroidOffset.y
        )
        logD { "transform. zoomChange=$zoomChange, touchCentroid=$touchCentroid, newScale=$newScale, addCentroidOffset=$addCentroidOffset, targetTranslation=$targetTranslation" }
        coroutineScope {
            _scale.snapTo(newScale)
            updateTranslationBounds("snapScaleTo")
            _translationX.snapTo(targetValue = targetTranslation.x)
            _translationY.snapTo(targetValue = targetTranslation.y)
            resetTransformInfo()
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
        logI { "fling. velocity=$velocity, translation=$translation" }
        launch {
            _translationX.animateDecay(velocity.x, exponentialDecay()) {
                logD { "fling. running. velocity=$velocity, translationX=${this.value}" }
                resetTransformInfo()
            }
        }
        launch {
            _translationY.animateDecay(velocity.y, exponentialDecay()) {
                logD { "fling. running. velocity=$velocity, translationY=${this.value}" }
                resetTransformInfo()
            }
        }
    }

    override fun toString(): String =
        "MyZoomState(minScale=$minScale, maxScale=$maxScale, scale=$scale, translation=$translation"

    private fun updateTranslationBounds(caller: String) {
        val containerSize = containerSize.takeIf { it.isSpecified } ?: return
        val contentSize = contentSize.takeIf { it.isSpecified } ?: return
        val contentScale = contentScale
        val currentScale = scale
        val bounds = computeTranslationBounds(
            containerSize = containerSize,
            contentSize = contentSize,
            contentScale = contentScale,
            scale = currentScale
        )
        this.translationBounds = bounds
        _translationX.updateBounds(lowerBound = bounds.left, upperBound = bounds.right)
        _translationY.updateBounds(lowerBound = bounds.top, upperBound = bounds.bottom)
        logD { "updateTranslationBounds. $caller. bounds=$bounds, containerSize=$containerSize, contentSize=${contentSize}, scale=$currentScale" }
    }

    private fun clearTranslationBounds(@Suppress("SameParameterValue") caller: String) {
        logD { "updateTranslationBounds. ${caller}. clear" }
        _translationX.updateBounds(lowerBound = null, upperBound = null)
        _translationY.updateBounds(lowerBound = null, upperBound = null)
    }

    private fun resetFixedInfo() {
        val containerSize = containerSize.takeIf { it.isSpecified } ?: return
        val contentSize = contentSize.takeIf { it.isSpecified } ?: return
        val contentScale = contentScale
        contentOfContainerRect = computeContentOfContainerRect(
            containerSize = containerSize,
            contentSize = contentSize,
            contentScale = contentScale
        )
    }

    private fun resetTransformInfo() {
        val containerSize = containerSize.takeIf { it.isSpecified } ?: return
        val contentSize = contentSize.takeIf { it.isSpecified } ?: return
        val contentScale = contentScale
        val currentScale = scale
        val currentTranslation = translation
        val scaledVisibleRect = computeScaledVisibleRect(
            containerSize = containerSize,
            scale = currentScale,
            translation = currentTranslation
        )
        visibleRect = scaledVisibleRect.restoreScale(currentScale)

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

    private fun logD(message: () -> String) {
        if (debugMode) {
            Log.d("MyZoomState", message())
        }
    }

    private fun logI(message: () -> String) {
        if (debugMode) {
            Log.i("MyZoomState", message())
        }
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