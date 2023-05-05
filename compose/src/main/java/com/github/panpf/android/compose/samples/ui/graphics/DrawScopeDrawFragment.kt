package com.github.panpf.android.compose.samples.ui.graphics

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.toBitmap
import com.github.panpf.android.compose.samples.R
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem3
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
import com.github.panpf.android.compose.samples.ui.base.SubtitleText
import com.github.panpf.android.compose.samples.ui.base.computePentagramPath
import com.github.panpf.android.compose.samples.ui.base.rainbowColorsBrush
import com.github.panpf.android.compose.samples.ui.base.theme.MyThemeColors3
import com.github.panpf.android.compose.samples.ui.customization.grid.VerticalGrid
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlin.math.roundToInt

class DrawScopeDrawFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "DrawScope - draw*"
    }

    @Composable
    override fun DrawContent() {
        ExpandableLayout { allExpandFlow ->
            Text(
                text = """
                    draw* 系列的方法用于在画布上绘制几何图形或图片、文字
                    
                    draw* 系列方法的 alpha、style、stroke、blendMode、shader、colorFilter 等通用参数请参考 Paint 页面的示例
                    
                    drawIntoCanvas() 方法提供一个和 Android 层 Canvas API 一模一样的接口，用于兼容旧的用 Android 层 Canvas 绘制的代码 
                """.trimIndent(),
                modifier = Modifier.padding(20.dp)
            )
            DrawScopeDrawArcSample(allExpandFlow)
            DrawScopeDrawCircleSample(allExpandFlow)
            DrawScopeDrawImageSample(allExpandFlow)
            DrawScopeDrawLineSample(allExpandFlow)
            DrawScopeDrawOutlineSample(allExpandFlow)
            DrawScopeDrawOvalSample(allExpandFlow)
            DrawScopeDrawPathSample(allExpandFlow)
            DrawScopeDrawPointsSample(allExpandFlow)
            DrawScopeDrawRectSample(allExpandFlow)
            DrawScopeDrawRoundRectSample(allExpandFlow)
            DrawScopeDrawTextSample(allExpandFlow)
        }
    }
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun DrawScopeDrawArcSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyThemeColors3.current
    ExpandableItem3(title = "DrawScope - drawArc", allExpandFlow, padding = 20.dp) {
        Text(text = "半透明的圆底是单独绘制的，只是为了突出弧形在圆中的位置，便于理解")

        Spacer(modifier = Modifier.size(20.dp))
        VerticalGrid(
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            val smallCanvasModifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .border(1.dp, colors.primaryTranslucency)

            Column {
                SubtitleText(text = "默认配置，大小会充满画布", line = 3)
                Canvas(modifier = smallCanvasModifier) {
                    drawCircle(
                        color = colors.tertiaryTranslucency
                    )
                    drawArc(
                        color = colors.tertiary,
                        startAngle = 270f,
                        sweepAngle = 90f,
                        useCenter = false
                    )
                }
            }

            Column {
                SubtitleText(text = "style 改为描边样式就是弧线", line = 3)
                val stroke = Stroke(with(LocalDensity.current) { 3.dp.toPx() })
                Canvas(modifier = smallCanvasModifier) {
                    drawCircle(
                        color = colors.tertiaryTranslucency
                    )
                    drawArc(
                        color = colors.tertiary,
                        startAngle = 270f,
                        sweepAngle = 90f,
                        useCenter = false,
                        style = stroke
                    )
                }
            }

            Column {
                SubtitleText(text = "useCenter 改为 true，就是扇形", line = 3)
                Canvas(modifier = smallCanvasModifier) {
                    drawCircle(
                        color = colors.tertiaryTranslucency
                    )
                    drawArc(
                        color = colors.tertiary,
                        startAngle = 270f,
                        sweepAngle = 90f,
                        useCenter = true
                    )
                }
            }

            Column {
                SubtitleText(text = "修改 size 参数，让扇形变小", line = 3)
                Canvas(modifier = smallCanvasModifier) {
                    drawCircle(
                        color = colors.tertiaryTranslucency,
                        radius = size.minDimension / 4.0f,
                        center = Offset(size.minDimension / 4.0f, size.minDimension / 4.0f)
                    )
                    drawArc(
                        color = colors.tertiary,
                        startAngle = 270f,
                        sweepAngle = 90f,
                        useCenter = true,
                        size = size / 2f
                    )
                }
            }

            Column {
                SubtitleText(text = "修改 topLeft 参数让扇形居中", line = 3)
                Canvas(modifier = smallCanvasModifier) {
                    drawCircle(
                        color = colors.tertiaryTranslucency,
                        radius = size.minDimension / 4.0f,
                    )
                    val rectSize = size / 2f
                    drawArc(
                        color = colors.tertiary,
                        startAngle = 270f,
                        sweepAngle = 90f,
                        useCenter = true,
                        topLeft = Offset(
                            x = (size.width - rectSize.width) / 2f,
                            y = (size.height - rectSize.height) / 2f
                        ),
                        size = rectSize
                    )
                }
            }

            Column {
                SubtitleText(text = "通过 brush 参数，可以使用渐变色", line = 3)
                Canvas(modifier = smallCanvasModifier) {
                    drawCircle(
                        color = colors.tertiaryTranslucency
                    )
                    drawArc(
                        brush = rainbowColorsBrush,
                        startAngle = 270f,
                        sweepAngle = 90f,
                        useCenter = true
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun DrawScopeDrawArcSamplePreview() {
    DrawScopeDrawArcSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun DrawScopeDrawCircleSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyThemeColors3.current
    ExpandableItem3(title = "DrawScope - drawCircle", allExpandFlow, padding = 20.dp) {
        VerticalGrid(
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            val smallCanvasModifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .border(1.dp, colors.primaryTranslucency)

            Column {
                SubtitleText(text = "默认配置，大小会充满画布，并且居中", line = 3)
                Canvas(modifier = smallCanvasModifier) {
                    drawCircle(color = colors.tertiary)
                }
            }

            Column {
                SubtitleText(text = "修改 radius 参数，让圆形变小", line = 3)
                Canvas(modifier = smallCanvasModifier) {
                    drawCircle(
                        color = colors.tertiary,
                        radius = size.minDimension / 4.0f,
                    )
                }
            }

            Column {
                SubtitleText(text = "修改 center 参数让圆形位于左上角", line = 3)
                Canvas(modifier = smallCanvasModifier) {
                    drawCircle(
                        color = colors.tertiary,
                        radius = size.minDimension / 4.0f,
                        center = Offset(size.minDimension / 4.0f, size.minDimension / 4.0f)
                    )
                }
            }

            Column {
                SubtitleText(text = "修改 style 参数，改为描边样式", line = 3)
                val stroke = Stroke(with(LocalDensity.current) { 3.dp.toPx() })
                Canvas(modifier = smallCanvasModifier) {
                    drawCircle(
                        color = colors.tertiary,
                        style = stroke
                    )
                }
            }

            Column {
                SubtitleText(text = "通过 brush 参数，可以使用渐变色", line = 3)
                Canvas(modifier = smallCanvasModifier) {
                    drawCircle(brush = rainbowColorsBrush)
                }
            }

            Column {
                SubtitleText(text = "通过 brush 参数，可以使用渐变色", line = 3)
                val stroke = Stroke(with(LocalDensity.current) { 3.dp.toPx() })
                Canvas(modifier = smallCanvasModifier) {
                    drawCircle(
                        brush = rainbowColorsBrush,
                        style = stroke
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun DrawScopeDrawCircleSamplePreview() {
    DrawScopeDrawCircleSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun DrawScopeDrawImageSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyThemeColors3.current
    ExpandableItem3(title = "DrawScope - drawImage", allExpandFlow, padding = 20.dp) {
        VerticalGrid(
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            val context = LocalContext.current
            val dividerPx = with(LocalDensity.current) { 20.dp.toPx() }
            val imageHor = remember {
                val dogDrawable =
                    ResourcesCompat.getDrawable(context.resources, R.drawable.dog_hor, null)!!
                val imageWidthPx: Int =
                    ((context.resources.displayMetrics.widthPixels - dividerPx * 2) / 3f * 0.6f).roundToInt()
                val imageHeightPx: Int =
                    (dogDrawable.intrinsicHeight * (imageWidthPx.toFloat() / dogDrawable.intrinsicWidth)).roundToInt()
                dogDrawable.toBitmap(imageWidthPx, imageHeightPx).asImageBitmap()
            }
            val imageVer = remember {
                val dogDrawable =
                    ResourcesCompat.getDrawable(context.resources, R.drawable.dog_ver, null)!!
                val imageHeightPx: Int =
                    ((context.resources.displayMetrics.widthPixels - dividerPx * 2) / 3f * 0.6f).roundToInt()
                val imageWidthPx: Int =
                    (dogDrawable.intrinsicWidth * (imageHeightPx.toFloat() / dogDrawable.intrinsicHeight)).roundToInt()
                dogDrawable.toBitmap(imageWidthPx, imageHeightPx).asImageBitmap()
            }

            val smallCanvasModifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .border(1.dp, colors.primaryTranslucency)
            Column {
                SubtitleText(text = "默认配置", line = 3)
                Canvas(modifier = smallCanvasModifier) {
                    drawImage(imageHor)
                }
                Spacer(modifier = Modifier.size(10.dp))
                Canvas(modifier = smallCanvasModifier) {
                    drawImage(imageVer)
                }
            }

            Column {
                SubtitleText(text = "修改 topLeft 参数，让图片居中", line = 3)
                Canvas(modifier = smallCanvasModifier) {
                    drawImage(
                        image = imageHor,
                        topLeft = Offset(
                            x = (size.width - imageHor.width) / 2f,
                            y = (size.height - imageHor.height) / 2f
                        )
                    )
                }
                Spacer(modifier = Modifier.size(10.dp))
                Canvas(modifier = smallCanvasModifier) {
                    drawImage(
                        image = imageVer,
                        topLeft = Offset(
                            x = (size.width - imageVer.width) / 2f,
                            y = (size.height - imageVer.height) / 2f
                        )
                    )
                }
            }

            Column {
                SubtitleText(text = "通过 src 和 dst，让图片居中且充满画布", line = 3)
                Canvas(modifier = smallCanvasModifier) {
                    drawImage(
                        image = imageHor,
                        srcOffset = IntOffset(x = (imageHor.width - imageHor.height) / 2, y = 0),
                        srcSize = IntSize(imageHor.height, imageHor.height),
                        dstOffset = IntOffset.Zero,
                        dstSize = IntSize(size.width.roundToInt(), size.height.roundToInt()),
                    )
                }
                Spacer(modifier = Modifier.size(10.dp))
                Canvas(modifier = smallCanvasModifier) {
                    drawImage(
                        image = imageVer,
                        srcOffset = IntOffset(x = 0, y = (imageVer.height - imageVer.width) / 2),
                        srcSize = IntSize(imageVer.width, imageVer.width),
                        dstOffset = IntOffset.Zero,
                        dstSize = IntSize(size.width.roundToInt(), size.height.roundToInt()),
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun DrawScopeDrawImageSamplePreview() {
    DrawScopeDrawImageSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun DrawScopeDrawLineSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyThemeColors3.current
    ExpandableItem3(title = "DrawScope - drawLine", allExpandFlow, padding = 20.dp) {
        VerticalGrid(
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            val smallCanvasModifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .border(1.dp, colors.primaryTranslucency)
            val strokeWidthBig = with(LocalDensity.current) { 6.dp.toPx() }

            Column {
                SubtitleText(text = "默认配置，宽度是 1 个像素", line = 3)
                Canvas(modifier = smallCanvasModifier) {
                    drawLine(
                        color = colors.tertiary,
                        start = Offset(0f, 0f),
                        end = Offset(size.width, size.height)
                    )
                }
            }

            Column {
                SubtitleText(text = "修改 strokeWidth 参数为 5 个像素", line = 3)
                Canvas(modifier = smallCanvasModifier) {
                    drawLine(
                        color = colors.tertiary,
                        start = Offset(0f, 0f),
                        end = Offset(size.width, size.height),
                        strokeWidth = strokeWidthBig
                    )
                }
            }

            Column {
                SubtitleText(text = "修改 cap 参数让线条变的圆润", line = 3)
                Canvas(modifier = smallCanvasModifier) {
                    drawLine(
                        color = colors.tertiary,
                        start = Offset(0f, 0f),
                        end = Offset(size.width, size.height),
                        strokeWidth = strokeWidthBig,
                        cap = StrokeCap.Round
                    )
                }
            }

            Column {
                SubtitleText(text = "通过 brush 参数，可以使用渐变色", line = 3)
                Canvas(modifier = smallCanvasModifier) {
                    drawLine(
                        brush = Brush.linearGradient(
                            listOf(colors.primary, colors.tertiary, colors.primary)
                        ),
                        start = Offset(0f, 0f),
                        end = Offset(size.width, size.height),
                        strokeWidth = strokeWidthBig,
                    )
                }
            }

            Column {
                SubtitleText(text = "通过 brush 参数，可以使用渐变色", line = 3)
                Canvas(modifier = smallCanvasModifier) {
                    drawLine(
                        brush = Brush.radialGradient(
                            listOf(colors.primary, colors.tertiary, colors.primary)
                        ),
                        start = Offset(0f, 0f),
                        end = Offset(size.width, size.height),
                        strokeWidth = strokeWidthBig,
                    )
                }
            }

            Column {
                SubtitleText(text = "通过 brush 参数，可以使用渐变色", line = 3)
                Canvas(modifier = smallCanvasModifier) {
                    drawLine(
                        brush = rainbowColorsBrush,
                        start = Offset(0f, 0f),
                        end = Offset(size.width, size.height),
                        strokeWidth = strokeWidthBig,
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun DrawScopeDrawLineSamplePreview() {
    DrawScopeDrawLineSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun DrawScopeDrawOutlineSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyThemeColors3.current
    ExpandableItem3(title = "DrawScope - drawOutline", allExpandFlow, padding = 20.dp) {
        VerticalGrid(
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            val smallCanvasModifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .border(1.dp, colors.primaryTranslucency)
            val commonSizePx = with(LocalDensity.current) { 5.dp.toPx() }

            Column {
                SubtitleText(text = "矩形", line = 1)
                Canvas(modifier = smallCanvasModifier) {
                    drawOutline(
                        outline = Outline.Rectangle(
                            Rect(
                                commonSizePx,
                                commonSizePx,
                                size.width - commonSizePx,
                                size.height - commonSizePx
                            )
                        ),
                        color = colors.tertiary,
                    )
                }
            }

            Column {
                SubtitleText(text = "圆角矩形", line = 1)
                Canvas(modifier = smallCanvasModifier) {
                    drawOutline(
                        outline = Outline.Rounded(
                            RoundRect(
                                commonSizePx,
                                commonSizePx,
                                size.width - commonSizePx,
                                size.height - commonSizePx,
                                CornerRadius(commonSizePx * 2)
                            )
                        ),
                        color = colors.tertiary,
                    )
                }
            }

            Column {
                SubtitleText(text = "Generic", line = 1)
                Canvas(modifier = smallCanvasModifier) {
                    val path = Path().apply {
                        moveTo(size.width * 0.25f, 0f)
                        lineTo(size.width * 0.75f, 0f)
                        lineTo(size.width, size.width * 0.25f)
                        lineTo(size.width, size.width * 0.75f)
                        lineTo(size.width * 0.75f, size.height)
                        lineTo(size.width * 0.25f, size.height)
                        lineTo(0f, size.width * 0.75f)
                        lineTo(0f, size.width * 0.25f)
                        close()
                    }
                    drawOutline(
                        outline = Outline.Generic(path),
                        color = colors.tertiary,
                    )
                }
            }

            Column {
                SubtitleText(text = "修改 style 为描边样式", line = 3)
                val stroke = Stroke(with(LocalDensity.current) { 3.dp.toPx() })
                Canvas(modifier = smallCanvasModifier) {
                    drawOutline(
                        outline = Outline.Rectangle(
                            Rect(
                                commonSizePx,
                                commonSizePx,
                                size.width - commonSizePx,
                                size.height - commonSizePx
                            )
                        ),
                        color = colors.tertiary,
                        style = stroke,
                    )
                }
            }

            Column {
                SubtitleText(text = "通过 brush 参数，可以使用渐变色", line = 3)
                Canvas(modifier = smallCanvasModifier) {
                    drawOutline(
                        outline = Outline.Rounded(
                            RoundRect(
                                commonSizePx,
                                commonSizePx,
                                size.width - commonSizePx,
                                size.height - commonSizePx,
                                CornerRadius(commonSizePx * 2)
                            )
                        ),
                        brush = rainbowColorsBrush,
                    )
                }
            }

            Column {
                SubtitleText(text = "通过 brush 参数，可以使用渐变色", line = 3)
                val stroke = Stroke(with(LocalDensity.current) { 3.dp.toPx() })
                Canvas(modifier = smallCanvasModifier) {
                    val path = Path().apply {
                        moveTo(size.width * 0.25f, 0f)
                        lineTo(size.width * 0.75f, 0f)
                        lineTo(size.width, size.width * 0.25f)
                        lineTo(size.width, size.width * 0.75f)
                        lineTo(size.width * 0.75f, size.height)
                        lineTo(size.width * 0.25f, size.height)
                        lineTo(0f, size.width * 0.75f)
                        lineTo(0f, size.width * 0.25f)
                        close()
                    }
                    drawOutline(
                        outline = Outline.Generic(path),
                        brush = rainbowColorsBrush,
                        style = stroke,
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun DrawScopeDrawOutlineSamplePreview() {
    DrawScopeDrawOutlineSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun DrawScopeDrawOvalSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyThemeColors3.current
    ExpandableItem3(title = "DrawScope - drawOval", allExpandFlow, padding = 20.dp) {
        VerticalGrid(
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            val smallCanvasModifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1.5f)
                .border(1.dp, colors.primaryTranslucency)

            Column {
                SubtitleText(text = "默认配置，大小会充满画布", line = 3)
                Canvas(modifier = smallCanvasModifier) {
                    drawOval(color = colors.tertiary)
                }
            }

            Column {
                SubtitleText(text = "修改 size 参数，让椭圆变小", line = 3)
                Canvas(modifier = smallCanvasModifier) {
                    drawOval(color = colors.tertiary, size = size / 2f)
                }
            }

            Column {
                SubtitleText(text = "修改 topLeft 参数让椭圆居中", line = 3)
                Canvas(modifier = smallCanvasModifier) {
                    val rectSize = size / 2f
                    drawOval(
                        color = colors.tertiary,
                        topLeft = Offset(
                            x = (size.width - rectSize.width) / 2f,
                            y = (size.height - rectSize.height) / 2f
                        ),
                        size = rectSize
                    )
                }
            }

            Column {
                SubtitleText(text = "修改 style 参数，改为描边样式", line = 3)
                val stroke = Stroke(with(LocalDensity.current) { 3.dp.toPx() })
                Canvas(modifier = smallCanvasModifier) {
                    drawOval(
                        color = colors.tertiary,
                        style = stroke
                    )
                }
            }

            Column {
                SubtitleText(text = "通过 brush 参数，可以使用渐变色", line = 3)
                Canvas(modifier = smallCanvasModifier) {
                    drawOval(brush = rainbowColorsBrush)
                }
            }

            Column {
                SubtitleText(text = "通过 brush 参数，可以使用渐变色", line = 3)
                val stroke = Stroke(with(LocalDensity.current) { 3.dp.toPx() })
                Canvas(modifier = smallCanvasModifier) {
                    drawOval(
                        brush = rainbowColorsBrush,
                        style = stroke
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun DrawScopeDrawOvalSamplePreview() {
    DrawScopeDrawOvalSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun DrawScopeDrawPathSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyThemeColors3.current
    ExpandableItem3(title = "DrawScope - drawPath", allExpandFlow, padding = 20.dp) {
        VerticalGrid(
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            val smallCanvasModifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .border(1.dp, colors.primaryTranslucency)

            Column {
                SubtitleText(text = "默认配置", line = 3)
                Canvas(modifier = smallCanvasModifier) {
                    drawPath(path = computePentagramPath(size), color = colors.tertiary)
                }
            }

            Column {
                SubtitleText(text = "修改 style 参数为描边样式", line = 3)
                val stroke = Stroke(with(LocalDensity.current) { 3.dp.toPx() })
                Canvas(modifier = smallCanvasModifier) {
                    drawPath(
                        path = computePentagramPath(size),
                        color = colors.tertiary,
                        style = stroke
                    )
                }
            }

            Column {
                SubtitleText(text = "通过 brush 参数，可以使用渐变色", line = 3)
                Canvas(modifier = smallCanvasModifier) {
                    drawPath(path = computePentagramPath(size), brush = rainbowColorsBrush)
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun DrawScopeDrawPathSamplePreview() {
    DrawScopeDrawPathSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun DrawScopeDrawPointsSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyThemeColors3.current
    ExpandableItem3(title = "DrawScope - drawPoints", allExpandFlow, padding = 20.dp) {
        VerticalGrid(
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            val smallCanvasModifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .border(1.dp, colors.primaryTranslucency)

            val getPoints: (size: Size) -> List<Offset> = { size ->
                listOf(
                    Offset(size.width / 2, 0f),
                    Offset(size.width, size.height / 2),
                    Offset(size.width / 2, size.height),
                    Offset(0f, size.height / 2),
                )
            }
            val pointSizePx = with(LocalDensity.current) { 5.dp.toPx() }

            Column {
                SubtitleText(text = "默认配置", line = 3)
                Canvas(modifier = smallCanvasModifier) {
                    drawPoints(
                        points = getPoints(size),
                        pointMode = PointMode.Points,
                        color = colors.tertiary,
                        strokeWidth = pointSizePx,
                    )
                }
            }

            Column {
                SubtitleText(text = "修改 pointMode 参数为 Lines", line = 3)
                Canvas(modifier = smallCanvasModifier) {
                    drawPoints(
                        points = getPoints(size),
                        pointMode = PointMode.Lines,
                        color = colors.tertiary,
                        strokeWidth = pointSizePx,
                    )
                }
            }

            Column {
                SubtitleText(text = "修改 pointMode 参数为 Polygon", line = 3)
                Canvas(modifier = smallCanvasModifier) {
                    drawPoints(
                        points = getPoints(size),
                        pointMode = PointMode.Polygon,
                        color = colors.tertiary,
                        strokeWidth = pointSizePx,
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun DrawScopeDrawPointsSamplePreview() {
    DrawScopeDrawPointsSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun DrawScopeDrawRectSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyThemeColors3.current
    ExpandableItem3(title = "DrawScope - drawRect", allExpandFlow, padding = 20.dp) {
        VerticalGrid(
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            val smallCanvasModifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .border(1.dp, colors.primaryTranslucency)

            Column {
                SubtitleText(text = "默认配置，大小会充满画布", line = 3)
                Canvas(modifier = smallCanvasModifier) {
                    drawRect(color = colors.tertiary)
                }
            }

            Column {
                SubtitleText(text = "修改 size 参数，让矩形变小", line = 3)
                Canvas(modifier = smallCanvasModifier) {
                    drawRect(color = colors.tertiary, size = size / 2f)
                }
            }

            Column {
                SubtitleText(text = "修改 topLeft 参数让矩形居中", line = 3)
                Canvas(modifier = smallCanvasModifier) {
                    val rectSize = size / 2f
                    drawRect(
                        color = colors.tertiary,
                        topLeft = Offset(
                            x = (size.width - rectSize.width) / 2f,
                            y = (size.height - rectSize.height) / 2f
                        ),
                        size = rectSize
                    )
                }
            }

            Column {
                SubtitleText(text = "修改 style 参数，改为描边样式", line = 3)
                val stroke = Stroke(with(LocalDensity.current) { 3.dp.toPx() })
                Canvas(modifier = smallCanvasModifier) {
                    drawRect(
                        color = colors.tertiary,
                        style = stroke
                    )
                }
            }

            Column {
                SubtitleText(text = "通过 brush 参数，可以使用渐变色", line = 3)
                Canvas(modifier = smallCanvasModifier) {
                    drawRect(brush = rainbowColorsBrush)
                }
            }

            Column {
                SubtitleText(text = "通过 brush 参数，可以使用渐变色", line = 3)
                val stroke = Stroke(with(LocalDensity.current) { 3.dp.toPx() })
                Canvas(modifier = smallCanvasModifier) {
                    drawRect(
                        brush = rainbowColorsBrush,
                        style = stroke
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun DrawScopeDrawRectSamplePreview() {
    DrawScopeDrawRectSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun DrawScopeDrawRoundRectSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyThemeColors3.current
    ExpandableItem3(title = "DrawScope - drawRoundRect", allExpandFlow, padding = 20.dp) {
        VerticalGrid(
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            val smallCanvasModifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .border(1.dp, colors.primaryTranslucency)

            Column {
                SubtitleText(text = "默认配置，大小会充满画布", line = 3)
                val cornerRadiusPx = with(LocalDensity.current) { 20.dp.toPx() }
                Canvas(modifier = smallCanvasModifier) {
                    drawRoundRect(
                        color = colors.tertiary,
                        cornerRadius = CornerRadius(cornerRadiusPx)
                    )
                }
            }

            Column {
                SubtitleText(text = "修改 size 参数，让矩形变小", line = 3)
                val cornerRadiusPx = with(LocalDensity.current) { 10.dp.toPx() }
                Canvas(modifier = smallCanvasModifier) {
                    drawRoundRect(
                        color = colors.tertiary, size = size / 2f,
                        cornerRadius = CornerRadius(cornerRadiusPx)
                    )
                }
            }

            Column {
                SubtitleText(text = "修改 topLeft 参数让矩形居中", line = 3)
                val cornerRadiusPx = with(LocalDensity.current) { 10.dp.toPx() }
                Canvas(modifier = smallCanvasModifier) {
                    val rectSize = size / 2f
                    drawRoundRect(
                        color = colors.tertiary,
                        topLeft = Offset(
                            x = (size.width - rectSize.width) / 2f,
                            y = (size.height - rectSize.height) / 2f
                        ),
                        size = rectSize,
                        cornerRadius = CornerRadius(cornerRadiusPx)
                    )
                }
            }

            Column {
                SubtitleText(text = "修改 style 参数，改为描边样式", line = 3)
                val stroke = Stroke(with(LocalDensity.current) { 3.dp.toPx() })
                val cornerRadiusPx = with(LocalDensity.current) { 20.dp.toPx() }
                Canvas(modifier = smallCanvasModifier) {
                    drawRoundRect(
                        color = colors.tertiary,
                        style = stroke,
                        cornerRadius = CornerRadius(cornerRadiusPx)
                    )
                }
            }

            Column {
                SubtitleText(text = "通过 brush 参数，可以使用渐变色", line = 3)
                val cornerRadiusPx = with(LocalDensity.current) { 20.dp.toPx() }
                Canvas(modifier = smallCanvasModifier) {
                    drawRoundRect(
                        brush = rainbowColorsBrush,
                        cornerRadius = CornerRadius(cornerRadiusPx)
                    )
                }
            }

            Column {
                SubtitleText(text = "通过 brush 参数，可以使用渐变色", line = 3)
                val stroke = Stroke(with(LocalDensity.current) { 3.dp.toPx() })
                val cornerRadiusPx = with(LocalDensity.current) { 20.dp.toPx() }
                Canvas(modifier = smallCanvasModifier) {
                    drawRoundRect(
                        brush = rainbowColorsBrush,
                        style = stroke,
                        cornerRadius = CornerRadius(cornerRadiusPx)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun DrawScopeDrawRoundRectSamplePreview() {
    DrawScopeDrawRoundRectSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalTextApi::class, ExperimentalLayoutApi::class)
@Composable
private fun DrawScopeDrawTextSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyThemeColors3.current
    ExpandableItem3(title = "DrawScope - drawText", allExpandFlow, padding = 20.dp) {
        Text(text = "更多文字相关属性 fontSize、fontStyle、letterSpacing、textDecoration、lineHeight 等请参考 Material 和 Material3 中 Text 页面的示例")

        Spacer(modifier = Modifier.size(20.dp))
        VerticalGrid(
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            val smallCanvasModifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .border(1.dp, colors.primaryTranslucency)

            Column {
                SubtitleText(text = "默认配置", line = 3)
                val textMeasurer = rememberTextMeasurer()
                Canvas(modifier = smallCanvasModifier) {
                    drawText(
                        textMeasurer = textMeasurer,
                        text = "锄禾日当午，汗滴禾下土。\n谁知盘中餐，粒粒皆辛苦。",
                    )
                }
            }

            Column {
                SubtitleText(text = "修改 topLeft 参数，左边上边留出边距", line = 3)
                val textMeasurer = rememberTextMeasurer()
                val topLeftPx = with(LocalDensity.current) { 10.dp.toPx() }
                Canvas(modifier = smallCanvasModifier) {
                    drawText(
                        textMeasurer = textMeasurer,
                        text = "锄禾日当午，汗滴禾下土。\n谁知盘中餐，粒粒皆辛苦。",
                        topLeft = Offset(topLeftPx, topLeftPx)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun DrawScopeDrawTextSamplePreview() {
    DrawScopeDrawTextSample(remember { MutableStateFlow(true) })
}