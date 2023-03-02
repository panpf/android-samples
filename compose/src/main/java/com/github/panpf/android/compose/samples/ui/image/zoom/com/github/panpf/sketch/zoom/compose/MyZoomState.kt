package com.github.panpf.sketch.zoom.compose

import android.util.Log
import androidx.annotation.FloatRange
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.exponentialDecay
import androidx.compose.animation.core.spring
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.util.VelocityTracker
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
    private val _translateX = Animatable(initialTranslateX)
    private val _translateY = Animatable(initialTranslateY)
    private val _scale = Animatable(initialScale)
    // todo rotate

    init {
        require(minScale < maxScale) { "minScale must be < maxScale" }
    }

    /**
     * The current scale value for [Zoomable]
     */
    @get:FloatRange(from = 0.0)
    val scale: Float
        get() = _scale.value

    /**
     * The current translateX value for [Zoomable]
     */
    @get:FloatRange(from = 0.0)
    val translateX: Float
        get() = _translateX.value

    /**
     * The current translateY value for [Zoomable]
     */
    @get:FloatRange(from = 0.0)
    val translateY: Float
        get() = _translateY.value

    internal val zooming: Boolean
        get() = scale > minScale

    /**
     * Instantly sets scale of [Zoomable] to given [scale]
     */
    suspend fun snapScaleTo(scale: Float) = coroutineScope {
        _scale.snapTo(scale.coerceIn(minimumValue = minScale, maximumValue = maxScale))
    }

    /**
     * Animates scale of [Zoomable] to given [scale]
     */
    suspend fun animateScaleTo(
        scale: Float,
        animationSpec: AnimationSpec<Float> = spring(),
        initialVelocity: Float = 0f,
    ) = coroutineScope {
        _scale.animateTo(
            targetValue = scale.coerceIn(minimumValue = minScale, maximumValue = maxScale),
            animationSpec = animationSpec,
            initialVelocity = initialVelocity,
        )
    }

    private suspend fun fling(velocity: Offset) = coroutineScope {
        Log.d("MyZoomState", "velocity: $velocity, translateX: $translateX, translateY: $translateY")
        launch {
            _translateX.animateDecay(
//                velocity.x / 2f,
                velocity.x,
                exponentialDecay()
            )
        }
        launch {
            _translateY.animateDecay(
                velocity.y,
//                velocity.y / 2f,
                exponentialDecay()
            )
        }
    }

    internal suspend fun drag(dragDistance: Offset) = coroutineScope {
        launch {
            _translateX.snapTo((_translateX.value + dragDistance.x))
        }
        launch {
            _translateY.snapTo((_translateY.value + dragDistance.y))
        }
    }

    internal suspend fun dragEnd() {
        val velocity = velocityTracker.calculateVelocity()
        fling(Offset(velocity.x * -1, velocity.y * -1))
    }

    internal suspend fun updateBounds(maxX: Float, maxY: Float) = coroutineScope {
        _translateY.updateBounds(-maxY, maxY)
        _translateX.updateBounds(-maxX, maxX)
    }

    internal suspend fun onZoomChange(zoomChange: Float) = snapScaleTo(scale * zoomChange)

    internal fun addPosition(timeMillis: Long, position: Offset) {
        velocityTracker.addPosition(timeMillis = timeMillis, position = position)
    }

    internal fun resetTracking() {
        velocityTracker.resetTracking()
    }

    internal fun isHorizontalDragFinish(dragDistance: Offset): Boolean {
        val lowerBounds = _translateX.lowerBound ?: return false
        val upperBounds = _translateX.upperBound ?: return false
        if (lowerBounds == 0f && upperBounds == 0f) return true

        val newPosition = _translateX.value + dragDistance.x
        if (newPosition <= lowerBounds) {
            return true
        }

        if (newPosition >= upperBounds) {
            return true
        }
        return false
    }

    internal fun isVerticalDragFinish(dragDistance: Offset): Boolean {
        val lowerBounds = _translateY.lowerBound ?: return false
        val upperBounds = _translateY.upperBound ?: return false
        if (lowerBounds == 0f && upperBounds == 0f) return true

        val newPosition = _translateY.value + dragDistance.y
        if (newPosition <= lowerBounds) {
            return true
        }

        if (newPosition >= upperBounds) {
            return true
        }
        return false
    }

    internal suspend fun onDoubleTap() {
        animateScaleTo(nextScale())
    }

    private fun nextScale(): Float {
        val scaleSteps = arrayOf(minScale, maxScale)
        val currentScale = scale
        val currentScaleIndex =
            scaleSteps.find { (it + 0.1f) >= currentScale }?.let { scaleSteps.indexOf(it) } ?: -1
        return if (currentScaleIndex != -1) {
            scaleSteps[(currentScaleIndex + 1) % scaleSteps.size]
        } else {
            scaleSteps.first()
        }
    }

    override fun toString(): String = "ZoomableState(" +
            "minScale=$minScale, " +
            "maxScale=$maxScale, " +
            "translateX=$translateX, " +
            "translateY=$translateY, " +
            "scale=$scale" +
            ")"

    companion object {
        /**
         * The default [Saver] implementation for [ZoomableState].
         */
        val Saver: Saver<MyZoomState, *> = listSaver(
            save = {
                listOf(
                    it.translateX,
                    it.translateY,
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