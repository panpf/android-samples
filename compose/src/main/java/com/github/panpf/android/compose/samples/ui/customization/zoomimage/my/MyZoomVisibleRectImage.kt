package com.github.panpf.android.compose.samples.ui.customization.zoomimage.my

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp

@Composable
fun MyZoomVisibleRectImage(
    painter: Painter,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    state: MyZoomState,
) {
    BoxWithConstraints(modifier = modifier.then(Modifier.fillMaxWidth(0.4f))) {
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
                    val coreVisibleRect = state.coreVisibleRect
                    val drawScaleWithCore = drawSize.width / coreSize.width
                    val drawVisibleRect = coreVisibleRect.scale(drawScaleWithCore)
                    drawRect(
                        color = Color.Red,
                        topLeft = drawVisibleRect.topLeft,
                        size = drawVisibleRect.size,
                        style = Stroke(width = 2.dp.toPx())
                    )
                }
        )
    }
}