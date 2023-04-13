package com.github.panpf.android.compose.samples.ui.customization.zoomimage.birdly.gesture.condition

import androidx.compose.ui.input.pointer.PointerEvent
import androidx.compose.ui.input.pointer.PointerInputScope
import com.github.panpf.android.compose.samples.ui.customization.zoomimage.birdly.BirdlyZoomableState
import com.github.panpf.android.compose.samples.ui.customization.zoomimage.birdly.util.Calculator

class WithinYBoundsTouchCondition : TouchCondition {

    override fun invoke(
        zoomStateProvider: () -> BirdlyZoomableState,
        pointerInputScope: PointerInputScope,
        pointerEvent: PointerEvent
    ): Boolean {
        val zoomState = zoomStateProvider()

        val translationY = pointerEvent.changes.first().previousPosition.y -
                pointerEvent.changes.first().position.y
        val maxTranslationY = Calculator.calculateMaxTranslation(
            zoomState.scale,
            pointerInputScope.size.height
        )
        zoomState.offset.y

        return pointerEvent.changes.size > 1 ||
                zoomState.offset.y + translationY in 0.0..maxTranslationY.toDouble()
    }
}