package com.github.panpf.android.compose.samples.ui.image.zoom.com.github.panpf.sketch.zoom.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.isUnspecified
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.times
import com.github.panpf.sketch.zoom.compose.MyZoomState

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
                    val visibleRectOfContent = state.visibleRectOfContent
                    val contentSize = state.contentSize
                    val drawSize = size
                    val validSize = computeValidSizeOfContent(contentSize, painter.intrinsicSize)
                    val validVisibleRectOfDraw = computeValidVisibleRectOfContent(
                        contentSize = contentSize,
                        validSize = validSize,
                        visibleRectOfContent = visibleRectOfContent,
                    ).scale(drawSize.width / validSize.width)
                    drawRect(
                        color = Color.Red,
                        topLeft = validVisibleRectOfDraw.topLeft,
                        size = validVisibleRectOfDraw.size,
                        alpha = 0.5f
//                        style = Stroke(width = 2.dp.toPx())
                    )
                }
        )
    }
}

fun computeValidSizeOfContent(
    contentSize: Size,
    imageSize: Size,
    contentScale: ContentScale = ContentScale.Fit
): Size {
    if (contentSize.isUnspecified || imageSize.isUnspecified) {
        return Size.Zero
    }
    return imageSize.times(contentScale.computeScaleFactor(imageSize, contentSize))
}

fun computeValidVisibleRectOfContent(
    contentSize: Size,
    validSize: Size,
    visibleRectOfContent: Rect,
): Rect {
    if (contentSize.isUnspecified || validSize.isUnspecified) {
        return Rect.Zero
    }
    val horSpace = (contentSize.width - validSize.width) / 2
    val verSpace = (contentSize.height - validSize.height) / 2
//    val validRectOfContent = Rect(
//        left = horSpace,
//        top = verSpace,
//        right = horSpace + scaledImageSize.width,
//        bottom = verSpace + scaledImageSize.height
//    )
    val left = (visibleRectOfContent.left - horSpace).coerceAtLeast(0f)
    val top = (visibleRectOfContent.top - verSpace).coerceAtLeast(0f)
    val right = (visibleRectOfContent.right - horSpace).coerceAtMost(validSize.width)
    val bottom = (visibleRectOfContent.bottom - verSpace).coerceAtMost(validSize.height)
    return Rect(left, top, right, bottom)
}