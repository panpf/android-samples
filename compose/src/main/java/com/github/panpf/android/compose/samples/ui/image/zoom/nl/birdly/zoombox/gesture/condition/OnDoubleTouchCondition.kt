package nl.birdly.zoombox.gesture.condition

import androidx.compose.ui.input.pointer.PointerEvent
import androidx.compose.ui.input.pointer.PointerInputScope
import nl.birdly.zoombox.BirdlyZoomableState

class OnDoubleTouchCondition : TouchCondition {

    override fun invoke(
        zoomStateProvider: () -> BirdlyZoomableState,
        pointerInputScope: PointerInputScope,
        pointerEvent: PointerEvent
    ) = pointerEvent.changes.size > 1

}
