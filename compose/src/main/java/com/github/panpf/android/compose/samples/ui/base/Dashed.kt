package com.github.panpf.android.compose.samples.ui.base

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowRow
import kotlin.math.roundToInt

fun Modifier.dashedBackground(color: Color, tileSize: Dp = 5.dp, dividerSize: Dp = 5.dp) =
    this.background(color, DashedShape(tileSize = tileSize, dividerSize = dividerSize))

@Composable
fun HorizontalDashedDivider(
    modifier: Modifier = Modifier,
    thickness: Dp = DividerDefaults.Thickness,
    tileSize: Dp = 5.dp,
    dividerSize: Dp = 5.dp,
    color: Color
) {
    val targetThickness = if (thickness == Dp.Hairline) {
        (1f / LocalDensity.current.density).dp
    } else {
        thickness
    }
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(targetThickness)
            .background(color, DashedShape(tileSize = tileSize, dividerSize = dividerSize))
    )
}

@Composable
fun VerticalDashedDivider(
    modifier: Modifier = Modifier,
    thickness: Dp = DividerDefaults.Thickness,
    tileSize: Dp = 5.dp,
    dividerSize: Dp = 5.dp,
    color: Color
) {
    val targetThickness = if (thickness == Dp.Hairline) {
        (1f / LocalDensity.current.density).dp
    } else {
        thickness
    }
    Box(
        modifier = modifier
            .width(targetThickness)
            .fillMaxHeight()
            .background(color, DashedShape(tileSize = tileSize, dividerSize = dividerSize))
    )
}

data class DashedShape(val tileSize: Dp = 5.dp, val dividerSize: Dp = 5.dp) : Shape {

    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ) = Outline.Generic(Path().apply {
        val tileSizePx = with(density) { tileSize.toPx() }
        val dividerSizePx = with(density) { dividerSize.toPx() }
        val stepPx = tileSizePx + dividerSizePx
        if (size.width >= size.height) {
            val stepsCount = (size.width / stepPx).roundToInt()
            val actualStep = size.width / stepsCount
            val tileSize = Size(width = tileSizePx, height = size.height)
            for (i in 0 until stepsCount) {
                addRect(
                    Rect(
                        offset = Offset(x = i * actualStep, y = 0f),
                        size = tileSize
                    )
                )
            }
        } else {
            val stepsCount = (size.height / stepPx).roundToInt()
            val actualStep = size.height / stepsCount
            val tileSize = Size(width = size.width, height = tileSizePx)
            for (i in 0 until stepsCount) {
                addRect(
                    Rect(
                        offset = Offset(x = 0f, y = i * actualStep),
                        size = tileSize
                    )
                )
            }
        }
        close()
    })
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
fun DashedShapePreview() {
    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        mainAxisSpacing = 10.dp,
        crossAxisSpacing = 10.dp
    ) {
        listOf(
            "Default" to (null to null),
            "tileSize=20.dp" to (20.dp to null),
            "dividerSize=20.dp" to (null to 20.dp),
            "tileSize=20.dp\ndividerSize=20.dp" to (20.dp to 20.dp),
        ).forEach { pair ->
            Column {
                Text(
                    text = pair.first,
                    modifier = Modifier
                        .height(46.dp)
                        .align(Alignment.CenterHorizontally),
                )
                Box(
                    modifier = Modifier
                        .width(150.dp)
                        .height(150.dp)
                        .border(2.dp, Color.Red)
                        .padding(2.dp)
                ) {
                    HorizontalDashedDivider(
                        modifier = Modifier
                            .height(2.dp)
                            .align(Alignment.Center),
                        color = Color.Gray,
                        tileSize = pair.second.first ?: 5.dp,
                        dividerSize = pair.second.second ?: 5.dp,
                    )
                    VerticalDashedDivider(
                        modifier = Modifier
                            .width(2.dp)
                            .align(Alignment.Center),
                        color = Color.Gray,
                        tileSize = pair.second.first ?: 5.dp,
                        dividerSize = pair.second.second ?: 5.dp,
                    )
                }
            }
        }
    }
}

// dashed line
//You can simply use a Canvas with the method drawLine applying as pathEffect a PathEffect.dashPathEffect:
//val pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
//Canvas(Modifier.fillMaxWidth().height(1.dp)) {
//    drawLine(
//        color = Color.Red,
//        start = Offset(0f, 0f),
//        end = Offset(size.width, 0f),
//        pathEffect = pathEffect
//    )
//}
//
// dashed rect
//You can also apply the same pathEffect to other method as:
//
//val stroke = Stroke(width = 2f,
//    pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
//)
//Canvas(Modifier.fillMaxWidth().height(70.dp)){
//    drawRoundRect(color = Color.Red,style = stroke)
//}