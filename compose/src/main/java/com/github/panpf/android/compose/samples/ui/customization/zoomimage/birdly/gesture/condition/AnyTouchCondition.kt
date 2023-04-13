package com.github.panpf.android.compose.samples.ui.customization.zoomimage.birdly.gesture.condition

import androidx.compose.ui.input.pointer.PointerEvent
import androidx.compose.ui.input.pointer.PointerInputScope
import com.github.panpf.android.compose.samples.ui.customization.zoomimage.birdly.BirdlyZoomableState

class AnyTouchCondition : TouchCondition {

    override fun invoke(
        zoomStateProvider: () -> BirdlyZoomableState,
        pointerInputScope: PointerInputScope,
        pointerEvent: PointerEvent
    ): Boolean = true
}