package com.github.panpf.android.compose.samples.ui.customization.zoomimage.birdly.gesture.transform

import androidx.compose.foundation.gestures.TransformableState
import androidx.compose.ui.input.pointer.PointerInputScope
import com.github.panpf.android.compose.samples.ui.customization.zoomimage.birdly.BirdlyZoomableState
import kotlinx.coroutines.CoroutineScope

interface OnCancelledBehavior {

    operator fun invoke(
        scope: CoroutineScope,
        state: TransformableState,
        pointerInputScope: PointerInputScope,
        zoomState: BirdlyZoomableState,
        onZoomUpdated: (BirdlyZoomableState) -> Unit
    )
}