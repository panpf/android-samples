package com.github.panpf.android.compose.samples.ui.graphics

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
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
import com.github.panpf.android.compose.samples.ui.base.rainbowColorsBrush
import com.github.panpf.android.compose.samples.ui.base.theme.MyThemeColors3
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
                """.trimIndent(),
                modifier = Modifier.padding(20.dp)
            )
            DrawScopeDrawArcSample(allExpandFlow)
            DrawScopeDrawCircleSample(allExpandFlow)
            DrawScopeDrawImageSample(allExpandFlow)
            // todo DrawScopeDrawIntoCanvasSample(allExpandFlow)
            // todo DrawScopeDrawLineSample(allExpandFlow)
            // todo DrawScopeDrawOutlineSample(allExpandFlow)
            // todo DrawScopeDrawOvalSample(allExpandFlow)
            // todo DrawScopeDrawPathSample(allExpandFlow)
            // todo DrawScopeDrawPointsSample(allExpandFlow)
            DrawScopeDrawRectSample(allExpandFlow)
            // todo DrawScopeDrawRoundRectSample(allExpandFlow)
            // todo DrawScopeDrawTextSample(allExpandFlow)
        }
    }
}


@Composable
private fun DrawScopeDrawArcSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyThemeColors3.current
    ExpandableItem3(title = "DrawScope - drawArc", allExpandFlow, padding = 20.dp) {
        Text(text = "半透明的圆底是单独绘制的，只是为了突出弧形在圆中的位置，便于理解")

        val smallCanvasModifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .border(1.dp, colors.primaryTranslucency)
        Spacer(modifier = Modifier.size(20.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.weight(1f)) {
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

            Spacer(modifier = Modifier.size(20.dp))
            Column(modifier = Modifier.weight(1f)) {
                SubtitleText(text = "style 改为描边样式就是弧线", line = 3)
                Canvas(modifier = smallCanvasModifier) {
                    drawCircle(
                        color = colors.tertiaryTranslucency
                    )
                    drawArc(
                        color = colors.tertiary,
                        startAngle = 270f,
                        sweepAngle = 90f,
                        useCenter = false,
                        style = Stroke(5f)
                    )
                }
            }

            Spacer(modifier = Modifier.size(20.dp))
            Column(modifier = Modifier.weight(1f)) {
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
        }

        Spacer(modifier = Modifier.size(20.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.weight(1f)) {
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

            Spacer(modifier = Modifier.size(20.dp))
            Column(modifier = Modifier.weight(1f)) {
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

            Spacer(modifier = Modifier.size(20.dp))
            Column(modifier = Modifier.weight(1f)) {
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


@Composable
private fun DrawScopeDrawCircleSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyThemeColors3.current
    ExpandableItem3(title = "DrawScope - drawCircle", allExpandFlow, padding = 20.dp) {
        val smallCanvasModifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .border(1.dp, colors.primaryTranslucency)

        Row(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.weight(1f)) {
                SubtitleText(text = "默认配置，大小会充满画布，并且居中", line = 3)
                Canvas(modifier = smallCanvasModifier) {
                    drawCircle(color = colors.tertiary)
                }
            }

            Spacer(modifier = Modifier.size(20.dp))
            Column(modifier = Modifier.weight(1f)) {
                SubtitleText(text = "修改 radius 参数，让圆形变小", line = 3)
                Canvas(modifier = smallCanvasModifier) {
                    drawCircle(
                        color = colors.tertiary,
                        radius = size.minDimension / 4.0f,
                    )
                }
            }

            Spacer(modifier = Modifier.size(20.dp))
            Column(modifier = Modifier.weight(1f)) {
                SubtitleText(text = "修改 center 参数让圆形位于左上角", line = 3)
                Canvas(modifier = smallCanvasModifier) {
                    drawCircle(
                        color = colors.tertiary,
                        radius = size.minDimension / 4.0f,
                        center = Offset(size.minDimension / 4.0f, size.minDimension / 4.0f)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.size(20.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.weight(1f)) {
                SubtitleText(text = "通过 brush 参数，可以使用渐变色", line = 3)
                Canvas(modifier = smallCanvasModifier) {
                    drawCircle(
                        brush = Brush.linearGradient(
                            listOf(colors.primary, colors.tertiary, colors.primary)
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.size(20.dp))
            Column(modifier = Modifier.weight(1f)) {
                SubtitleText(text = "通过 brush 参数，可以使用渐变色", line = 3)
                Canvas(modifier = smallCanvasModifier) {
                    drawCircle(
                        brush = Brush.radialGradient(
                            listOf(colors.primary, colors.tertiary, colors.primary)
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.size(20.dp))
            Column(modifier = Modifier.weight(1f)) {
                SubtitleText(text = "通过 brush 参数，可以使用渐变色", line = 3)
                Canvas(modifier = smallCanvasModifier) {
                    drawCircle(brush = rainbowColorsBrush)
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


@Composable
private fun DrawScopeDrawImageSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyThemeColors3.current
    ExpandableItem3(title = "DrawScope - drawImage", allExpandFlow, padding = 20.dp) {
        Row(modifier = Modifier.fillMaxWidth()) {
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
            Column(modifier = Modifier.weight(1f)) {
                SubtitleText(text = "默认配置", line = 3)
                Canvas(modifier = smallCanvasModifier) {
                    drawImage(imageHor)
                }
                Spacer(modifier = Modifier.size(10.dp))
                Canvas(modifier = smallCanvasModifier) {
                    drawImage(imageVer)
                }
            }

            Spacer(modifier = Modifier.size(20.dp))
            Column(modifier = Modifier.weight(1f)) {
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

            Spacer(modifier = Modifier.size(20.dp))
            Column(modifier = Modifier.weight(1f)) {
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


@Composable
private fun DrawScopeDrawRectSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyThemeColors3.current
    ExpandableItem3(title = "DrawScope - drawRect", allExpandFlow, padding = 20.dp) {
        val smallCanvasModifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .border(1.dp, colors.primaryTranslucency)

        Row(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.weight(1f)) {
                SubtitleText(text = "默认配置，大小会充满画布", line = 3)
                Canvas(modifier = smallCanvasModifier) {
                    drawRect(color = colors.tertiary)
                }
            }

            Spacer(modifier = Modifier.size(20.dp))
            Column(modifier = Modifier.weight(1f)) {
                SubtitleText(text = "修改 size 参数，让矩形变小", line = 3)
                Canvas(modifier = smallCanvasModifier) {
                    drawRect(color = colors.tertiary, size = size / 2f)
                }
            }

            Spacer(modifier = Modifier.size(20.dp))
            Column(modifier = Modifier.weight(1f)) {
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
        }

        Spacer(modifier = Modifier.size(20.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.weight(1f)) {
                SubtitleText(text = "通过 brush 参数，可以使用渐变色", line = 3)
                Canvas(modifier = smallCanvasModifier) {
                    drawRect(
                        brush = Brush.linearGradient(
                            listOf(colors.primary, colors.tertiary, colors.primary)
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.size(20.dp))
            Column(modifier = Modifier.weight(1f)) {
                SubtitleText(text = "通过 brush 参数，可以使用渐变色", line = 3)
                Canvas(modifier = smallCanvasModifier) {
                    drawRect(
                        brush = Brush.radialGradient(
                            listOf(colors.primary, colors.tertiary, colors.primary)
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.size(20.dp))
            Column(modifier = Modifier.weight(1f)) {
                SubtitleText(text = "通过 brush 参数，可以使用渐变色", line = 3)
                Canvas(modifier = smallCanvasModifier) {
                    drawRect(brush = rainbowColorsBrush)
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