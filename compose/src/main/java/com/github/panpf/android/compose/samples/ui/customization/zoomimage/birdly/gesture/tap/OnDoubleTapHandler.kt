package com.github.panpf.android.compose.samples.ui.customization.zoomimage.birdly.gesture.tap

import androidx.compose.foundation.gestures.TransformableState
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.PointerInputScope
import com.github.panpf.android.compose.samples.ui.customization.zoomimage.birdly.BirdlyZoomableState
import kotlinx.coroutines.CoroutineScope

interface OnDoubleTapHandler {

    operator fun invoke(
        scope: CoroutineScope,
        pointerInputScope: PointerInputScope,
        state: TransformableState,
        zoomRange: ClosedFloatingPointRange<Float>,
        offset: Offset,
        zoomStateProvider: () -> BirdlyZoomableState,
        onZoomUpdated: (BirdlyZoomableState) -> Unit
    )
}