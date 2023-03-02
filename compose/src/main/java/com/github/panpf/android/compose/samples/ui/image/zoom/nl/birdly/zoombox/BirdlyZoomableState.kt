package nl.birdly.zoombox

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.structuralEqualityPolicy
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect

@Stable
class MutableBirdlyZoomState(initial: BirdlyZoomableState) {

    var value: BirdlyZoomableState by mutableStateOf(initial, structuralEqualityPolicy())
}

data class BirdlyZoomableState(
    val scale: Float = 1f,
    val offset: Offset = Offset.Zero,
    internal val childRect: Rect? = null
)

@Composable
fun rememberMutableBirdlyZoomState(initial: BirdlyZoomableState = BirdlyZoomableState()): MutableBirdlyZoomState {
    val state by remember { mutableStateOf(MutableBirdlyZoomState(initial)) }
    return state
}