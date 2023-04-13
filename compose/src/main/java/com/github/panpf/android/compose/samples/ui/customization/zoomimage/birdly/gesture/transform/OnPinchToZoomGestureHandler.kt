package com.github.panpf.android.compose.samples.ui.customization.zoomimage.birdly.gesture.transform

import android.util.Log
import androidx.compose.ui.geometry.Offset
import com.github.panpf.android.compose.samples.ui.customization.zoomimage.birdly.BirdlyZoomableState
import com.github.panpf.android.compose.samples.ui.customization.zoomimage.birdly.util.minMax

class OnPinchToZoomGestureHandler : OnPinchGestureHandler {

    override fun invoke(
        // The position in pixels of the centre zoom position where 0,0 is the top left corner
        centroid: Offset,
        zoomRange: ClosedFloatingPointRange<Float>,
        pan: Offset,
        zoomState: BirdlyZoomableState,
        gestureZoom: Float,
        onZoomUpdated: (BirdlyZoomableState) -> Unit
    ) {
        val newScale = minMax(
            zoomRange.start,
            zoomRange.endInclusive,
            gestureZoom * zoomState.scale
        )
        val addPanOffset = Offset(
            x = -pan.x * newScale,
            y = -pan.y * newScale
        )
        val addCentroidOffset = Offset(
            x = (newScale - zoomState.scale) * centroid.x,
            y = (newScale - zoomState.scale) * centroid.y
        )
        val newOffset = zoomState.offset + addPanOffset + addCentroidOffset
//        val newOffset = Offset(
//            zoomState.offset.x + -pan.x * newScale + (newScale - zoomState.scale) * centroid.x,
//            zoomState.offset.y + -pan.y * newScale + (newScale - zoomState.scale) * centroid.y,
//        )
        Log.d(
            "BirdlyZoomable",
            "OnPinchToZoomGesture. newScale=$newScale, centroid=$centroid, addPanOffset=$addPanOffset, addCentroidOffset=$addCentroidOffset, newOffset=$newOffset"
        )
        onZoomUpdated(zoomState.copy(scale = newScale, offset = newOffset))
    }
}