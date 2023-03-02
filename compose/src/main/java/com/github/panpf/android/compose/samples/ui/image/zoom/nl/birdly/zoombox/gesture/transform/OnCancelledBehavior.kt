package nl.birdly.zoombox.gesture.transform

import androidx.compose.foundation.gestures.TransformableState
import androidx.compose.ui.input.pointer.PointerInputScope
import kotlinx.coroutines.CoroutineScope
import nl.birdly.zoombox.BirdlyZoomableState

interface OnCancelledBehavior {

    operator fun invoke(
        scope: CoroutineScope,
        state: TransformableState,
        pointerInputScope: PointerInputScope,
        zoomState: BirdlyZoomableState,
        onZoomUpdated: (BirdlyZoomableState) -> Unit
    )
}