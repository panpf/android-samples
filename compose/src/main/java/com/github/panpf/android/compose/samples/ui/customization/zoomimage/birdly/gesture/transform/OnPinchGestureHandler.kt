package com.github.panpf.android.compose.samples.ui.customization.zoomimage.birdly.gesture.transform

import androidx.compose.ui.geometry.Offset
import com.github.panpf.android.compose.samples.ui.customization.zoomimage.birdly.BirdlyZoomableState

interface OnPinchGestureHandler {
    operator fun invoke(
        // The position in pixels of the centre zoom position where 0,0 is the top left corner
        centroid: Offset,
        zoomRange: ClosedFloatingPointRange<Float>,
        pan: Offset,
        zoomState: BirdlyZoomableState,
        gestureZoom: Float,
        onZoomUpdated: (BirdlyZoomableState) -> Unit
    )
}