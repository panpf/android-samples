package com.github.panpf.sketch.zoom.compose

import android.util.Log
import androidx.annotation.FloatRange
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.exponentialDecay
import androidx.compose.animation.core.spring
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.util.VelocityTracker
import androidx.compose.ui.input.pointer.util.addPointerInputChange
import androidx.compose.ui.unit.Velocity
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
     * The current translationX value for [MyZoomImage]
     */
    @get:FloatRange(from = 0.0)
    val translationX: Float
        get() = _translationX.value

    /**
     * The current translationY value for [MyZoomImage]
     */
    @get:FloatRange(from = 0.0)
    val translationY: Float
        get() = _translationY.value

    internal val zooming: Boolean
        get() = scale > minScale

    /**
     * Instantly sets scale of [MyZoomImage] to given [scale]
     */
    suspend fun snapScaleTo(scale: Float) {
        Log.d("MyZoomState", "snapScaleTo. scale=$scale")
        coroutineScope {
            _scale.snapTo(scale.coerceIn(minimumValue = minScale, maximumValue = maxScale))
        }
    }

    /**
     * Animates scale of [MyZoomImage] to given [scale]
     */
    suspend fun animateScaleTo(
        scale: Float,
        animationSpec: AnimationSpec<Float> = spring(stiffness = Spring.StiffnessLow),
        initialVelocity: Float = 0f,
    ) {
        Log.d("MyZoomState", "animateScaleTo. scale=$scale")
        coroutineScope {
            _scale.animateTo(
                targetValue = scale.coerceIn(minimumValue = minScale, maximumValue = maxScale),
                animationSpec = animationSpec,
                initialVelocity = initialVelocity,
            )
        }
    }

    suspend fun snapDoubleTapScale(offset: Offset? = null) {
        snapScaleTo(nextDoubleTapScale())
    }

    suspend fun animateDoubleTapScale(offset: Offset? = null) {
        animateScaleTo(nextDoubleTapScale())
    }

    fun nextDoubleTapScale(): Float {
        val scaleSteps = arrayOf(minScale, maxScale)
        val currentScale = scale
        val currentScaleIndex =
            scaleSteps.findLast { currentScale >= (it - 0.1f) }?.let { scaleSteps.indexOf(it) } ?: -1
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
        val newTranslateX = (_translationX.value + dragAmount.x)
        val newTranslateY = (_translationY.value + dragAmount.y)
        Log.d(
            "MyZoomState",
            "drag. dragAmount=$dragAmount, newTranslate=Offset(${newTranslateX}, ${newTranslateY})"
        )
        velocityTracker.addPointerInputChange(change)
        coroutineScope {
            launch {
                _translationX.snapTo(newTranslateX)
            }
            launch {
                _translationY.snapTo(newTranslateY)
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

    internal suspend fun updateBounds(maxX: Float, maxY: Float) = coroutineScope {
        // todo updateBounds
        _translationY.updateBounds(-maxY, maxY)
        _translationX.updateBounds(-maxX, maxX)
    }

    internal fun isHorizontalDragFinish(dragDistance: Offset): Boolean {
        val lowerBounds = _translationX.lowerBound ?: return false
        val upperBounds = _translationX.upperBound ?: return false
        if (lowerBounds == 0f && upperBounds == 0f) return true

        val newPosition = _translationX.value + dragDistance.x
        if (newPosition <= lowerBounds) {
            return true
        }

        if (newPosition >= upperBounds) {
            return true
        }
        return false
    }

    internal fun isVerticalDragFinish(dragDistance: Offset): Boolean {
        val lowerBounds = _translationY.lowerBound ?: return false
        val upperBounds = _translationY.upperBound ?: return false
        if (lowerBounds == 0f && upperBounds == 0f) return true

        val newPosition = _translationY.value + dragDistance.y
        if (newPosition <= lowerBounds) {
            return true
        }

        if (newPosition >= upperBounds) {
            return true
        }
        return false
    }

    private suspend fun fling(velocity: Velocity) = coroutineScope {
        Log.d(
            "MyZoomState",
            "fling. start. velocity=$velocity, translation=(${_translationX.value}, ${_translationY.value})"
        )
        launch {
            _translationX.animateDecay(velocity.x, exponentialDecay()) {
                Log.d(
                    "MyZoomState",
                    "fling. running. velocity=$velocity, translation=(${_translationX.value}, ${_translationY.value})"
                )
            }
        }
        launch {
            _translationY.animateDecay(velocity.y, exponentialDecay()) {
                Log.d(
                    "MyZoomState",
                    "fling. running. velocity=$velocity, translation=(${_translationX.value}, ${_translationY.value})"
                )
            }
        }
    }

    override fun toString(): String =
        "ZoomableState(minScale=$minScale, maxScale=$maxScale, scale=$scale, translationX=$translationX, translationY=$translationY)"

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