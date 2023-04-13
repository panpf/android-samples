package com.github.panpf.android.compose.samples.ui.customization.zoomimage.birdly.gesture.transform

import androidx.compose.foundation.gestures.TransformableState
import androidx.compose.ui.input.pointer.PointerInputScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import com.github.panpf.android.compose.samples.ui.customization.zoomimage.birdly.BirdlyZoomableState
import com.github.panpf.android.compose.samples.ui.customization.zoomimage.birdly.util.animateZoomBy

class ResetToOriginalPositionOnCancelledBehavior : OnCancelledBehavior {

    override fun invoke(
        scope: CoroutineScope,
        state: TransformableState,
        pointerInputScope: PointerInputScope,
        zoomState: BirdlyZoomableState,
        onZoomUpdated: (BirdlyZoomableState) -> Unit
    ) {
        scope.launch {
            state.animateZoomBy(
                zoomState,
                BirdlyZoomableState(),
                onZoomUpdated = onZoomUpdated
            )
        }
    }
}