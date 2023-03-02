package nl.birdly.zoombox.gesture.tap

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.SpringSpec
import androidx.compose.foundation.gestures.TransformableState
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.PointerInputScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import nl.birdly.zoombox.BirdlyZoomableState
import nl.birdly.zoombox.util.animateZoomBy

class ZoomOnDoubleTapHandler(
    private val zoomAnimationSpec: AnimationSpec<Float> = SpringSpec(
        stiffness = Spring.StiffnessMediumLow
    ),
) : OnDoubleTapHandler {

    override fun invoke(
        scope: CoroutineScope,
        pointerInputScope: PointerInputScope,
        state: TransformableState,
        zoomRange: ClosedFloatingPointRange<Float>,
        offset: Offset,
        zoomStateProvider: () -> BirdlyZoomableState,
        onZoomUpdated: (BirdlyZoomableState) -> Unit
    ) {
        val zoom = zoomStateProvider()
        val futureScale = if (zoom.scale >= zoomRange.endInclusive - 0.1f) {
            zoomRange.start
        } else {
            zoomRange.endInclusive
        }

        zoom.childRect ?: return
        scope.launch {
            state.animateZoomBy(
                zoom,
                futureScale,
                offset,
                pointerInputScope.size,
                zoom.childRect,
                onZoomUpdated = onZoomUpdated,
                zoomAnimationSpec = zoomAnimationSpec
            )
        }
    }
}