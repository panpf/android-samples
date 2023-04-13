package com.github.panpf.android.compose.samples.ui.customization.zoomimage.birdly.gesture.tap

import androidx.compose.foundation.gestures.PressGestureScope
import androidx.compose.foundation.gestures.TransformableState
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.PointerInputScope
import com.github.panpf.android.compose.samples.ui.customization.zoomimage.birdly.BirdlyZoomableState
import kotlinx.coroutines.CoroutineScope

class TapHandler(
    private val onDoubleTap: OnDoubleTapHandler? = ZoomOnDoubleTapHandler(),
    private val onLongPress: ((Offset) -> Unit)? = null,
    private val onPress: suspend PressGestureScope.(Offset) -> Unit = { },
    private val onTap: (() -> Unit)? = null
) {

    suspend operator fun invoke(
        scope: CoroutineScope,
        pointerInputScope: PointerInputScope,
        state: TransformableState,
        zoomRange: ClosedFloatingPointRange<Float>,
        zoomStateProvider: () -> BirdlyZoomableState,
        onZoomUpdated: (BirdlyZoomableState) -> Unit
    ) {
        pointerInputScope.detectTapGestures(
            onDoubleTap = onDoubleTap?.let { onDoubleTap ->
                { offset: Offset ->
                    onDoubleTap.invoke(
                        scope,
                        pointerInputScope,
                        state,
                        zoomRange,
                        offset,
                        zoomStateProvider,
                        onZoomUpdated
                    )
                }
            },
            onLongPress = onLongPress,
            onPress = onPress,
            onTap = onTap?.let { onTap -> { onTap() } }
        )
    }
}