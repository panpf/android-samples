package com.github.panpf.android.compose.samples.ui.customization.zoomimage.my

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import kotlinx.coroutines.launch

@Composable
fun MyZoomVisibleRectImage(
    painter: Painter,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    state: MyZoomState,
    animateScaleState: State<Boolean>,
    animationDurationMillisState: State<Int>,
) {
    val coroutineScope = rememberCoroutineScope()
    BoxWithConstraints(modifier = modifier.then(Modifier.fillMaxWidth(0.4f))) {
        val imageNodeSizeState = remember { mutableStateOf(Size.Zero) }
        Image(
            painter = painter,
            contentDescription = contentDescription ?: "Visible Rect",
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(painter.intrinsicSize.let { it.width / it.height })
                .drawWithContent {
                    drawContent()
                    val drawSize = size
                    val coreSize = painter.intrinsicSize
                    val coreVisibleRect = state.contentVisibleRect
                    val drawScaleWithCore = drawSize.width / coreSize.width
                    val drawVisibleRect = coreVisibleRect.scale(drawScaleWithCore)
                    drawRect(
                        color = Color.Red,
                        topLeft = drawVisibleRect.topLeft,
                        size = drawVisibleRect.size,
                        style = Stroke(width = 2.dp.toPx())
                    )
                }
                .onSizeChanged {
                    imageNodeSizeState.value = it.toSize()
                }
                .pointerInput(animateScaleState.value, animationDurationMillisState.value) {
                    detectTapGestures(
                        onTap = {
                            val imageNodeSize = imageNodeSizeState.value
                            if (!imageNodeSize.isEmpty()) {
                                coroutineScope.launch {
                                    if (animateScaleState.value) {
                                        state.animateScaleTo(
                                            newScale = state.maxScale,
                                            newScaleCentroid = Centroid(
                                                x = it.x / imageNodeSize.width,
                                                y = it.y / imageNodeSize.height
                                            ),
                                            animationDurationMillis = animationDurationMillisState.value
                                        )
                                    } else {
                                        state.snapScaleTo(
                                            newScale = state.maxScale,
                                            newScaleCentroid = Centroid(
                                                x = it.x / imageNodeSize.width,
                                                y = it.y / imageNodeSize.height
                                            )
                                        )
                                    }
                                }
                            }
                        }
                    )
                },
        )
    }
}