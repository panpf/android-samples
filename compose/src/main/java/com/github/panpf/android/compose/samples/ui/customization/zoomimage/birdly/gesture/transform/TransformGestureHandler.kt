package com.github.panpf.android.compose.samples.ui.customization.zoomimage.birdly.gesture.transform

import androidx.compose.foundation.gestures.TransformableState
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.PointerInputScope
import com.github.panpf.android.compose.samples.ui.customization.zoomimage.birdly.BirdlyZoomableState
import com.github.panpf.android.compose.samples.ui.customization.zoomimage.birdly.gesture.condition.AnyTouchCondition
import com.github.panpf.android.compose.samples.ui.customization.zoomimage.birdly.gesture.condition.TouchCondition
import com.github.panpf.android.compose.samples.ui.customization.zoomimage.birdly.util.detectTransformGestures
import kotlinx.coroutines.CoroutineScope

class TransformGestureHandler(
    private val onCancelledBehavior: OnCancelledBehavior = KeepWithinBoundsOnCancelledBehavior(),
    private val onCondition: TouchCondition = AnyTouchCondition(),
    private val onPinchGesture: OnPinchGestureHandler = OnPinchToZoomGestureHandler()
) {

    suspend operator fun invoke(
        scope: CoroutineScope,
        pointerInputScope: PointerInputScope,
        state: TransformableState,
        zoomRange: ClosedFloatingPointRange<Float>,
        zoomStateProvider: () -> BirdlyZoomableState,
        onZoomUpdated: (BirdlyZoomableState) -> Unit
    ) {
        pointerInputScope.detectTransformGestures(
            zoomStateProvider,
            pointerInputScope,
            onCancelled = {
                onCancelledBehavior(
                    scope,
                    state,
                    pointerInputScope,
                    zoomStateProvider(),
                    onZoomUpdated
                )
            },
            onCondition = onCondition
        ) { centroid: Offset, pan: Offset, gestureZoom: Float ->
            onPinchGesture(
                centroid,
                zoomRange,
                pan,
                zoomStateProvider(),
                gestureZoom,
                onZoomUpdated
            )
        }
    }
}
