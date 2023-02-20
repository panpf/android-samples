package com.github.panpf.android.compose.samples.ui.graphics

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PaintingStyle
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StampedPathEffectStyle
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem3
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
import com.github.panpf.android.compose.samples.ui.base.SubtitleText
import com.github.panpf.android.compose.samples.ui.base.theme.MyThemeColors3
import com.github.panpf.android.compose.samples.ui.base.toPx
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class PaintFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "Paint"
    }

    @Composable
    override fun DrawContent() {
        ExpandableLayout { allExpandFlow ->
            PaintAlphaSample(allExpandFlow)
            PaintBlendModeSample(allExpandFlow)
            PaintColorSample(allExpandFlow)
            // todo PaintColorFilterSample(allExpandFlow)
            // todo PaintFilterQualitySample(allExpandFlow)
            PaintIsAntiAliasSample(allExpandFlow)
            // todo PaintShaderSample(allExpandFlow)
            PaintStrokeSample(allExpandFlow)
            PaintStrokePathEffectSample(allExpandFlow)
            // todo PaintStyleSample(allExpandFlow)
        }
    }
}


@Composable
private fun PaintAlphaSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyThemeColors3.current
    ExpandableItem3(title = "Paint - alpha", allExpandFlow, padding = 20.dp) {
        val smallCanvasModifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .border(1.dp, colors.primaryTranslucency)
        Row(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.weight(1f)) {
                SubtitleText(text = "Default", line = 1)
                Canvas(modifier = smallCanvasModifier) {
                    drawRect(color = colors.tertiary)
                }
            }

            Spacer(modifier = Modifier.size(20.dp))
            Column(modifier = Modifier.weight(1f)) {
                SubtitleText(text = "alpha = 0.7f", line = 1)
                Canvas(modifier = smallCanvasModifier) {
                    drawRect(color = colors.tertiary, alpha = 0.7f)
                }
            }

            Spacer(modifier = Modifier.size(20.dp))
            Column(modifier = Modifier.weight(1f)) {
                SubtitleText(text = "alpha = 0.3f", line = 1)
                Canvas(modifier = smallCanvasModifier) {
                    drawRect(color = colors.tertiary, alpha = 0.3f)
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun PaintAlphaSamplePreview() {
    PaintAlphaSample(remember { MutableStateFlow(true) })
}


@Composable
private fun PaintBlendModeSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyThemeColors3.current
    val smallCanvasModifier = Modifier
        .fillMaxWidth()
        .aspectRatio(1f)
        .border(1.dp, colors.primaryTranslucency)
    ExpandableItem3(title = "Paint - blendMode", allExpandFlow, padding = 20.dp) {
        val chunked = 4
        listOf(
            BlendMode.Clear,
            BlendMode.Src,
            BlendMode.Dst,
            BlendMode.SrcOver,
            BlendMode.DstOver,
            BlendMode.SrcIn,
            BlendMode.DstIn,
            BlendMode.SrcOut,
            BlendMode.DstOut,
            BlendMode.SrcAtop,
            BlendMode.DstAtop,
            BlendMode.Xor,
            BlendMode.Plus,
            BlendMode.Modulate,
            BlendMode.Screen,
            BlendMode.Overlay,
            BlendMode.Darken,
            BlendMode.Lighten,
            BlendMode.ColorDodge,
            BlendMode.ColorBurn,
            BlendMode.Hardlight,
            BlendMode.Softlight,
            BlendMode.Difference,
            BlendMode.Exclusion,
            BlendMode.Multiply,
            BlendMode.Hue,
            BlendMode.Saturation,
            BlendMode.Color,
            BlendMode.Luminosity,
        ).chunked(chunked).forEachIndexed { index, blendModes: List<BlendMode> ->
            if (index > 0) {
                Spacer(modifier = Modifier.size(20.dp))
            }
            Row(modifier = Modifier.fillMaxWidth()) {
                blendModes.forEachIndexed { index, blendMode ->
                    if (index > 0) {
                        Spacer(modifier = Modifier.size(20.dp))
                    }
                    Column(modifier = Modifier.weight(1f)) {
                        SubtitleText(text = blendMode.toString(), line = 1)
                        Canvas(modifier = smallCanvasModifier) {
                            drawCircle(
                                color = colors.primary,
                                radius = size.minDimension / 4.0f,
                                center = Offset(size.minDimension / 4.0f, size.minDimension / 4.0f)
                            )
                            val rectSize = size / 2f
                            drawRect(
                                color = colors.tertiary,
                                topLeft = Offset(
                                    x = (size.width - rectSize.width) / 2f,
                                    y = (size.height - rectSize.height) / 2f
                                ),
                                size = rectSize,
                                blendMode = blendMode
                            )
                        }
                    }
                }
                if (blendModes.size < chunked) {
                    repeat(chunked - blendModes.size) {
                        Spacer(modifier = Modifier.size(20.dp))
                        Column(modifier = Modifier.weight(1f)) {
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun PaintBlendModeSamplePreview() {
    PaintBlendModeSample(remember { MutableStateFlow(true) })
}


@Composable
private fun PaintColorSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyThemeColors3.current
    ExpandableItem3(title = "Paint - color", allExpandFlow, padding = 20.dp) {
        val smallCanvasModifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .border(1.dp, colors.primaryTranslucency)
        Row(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.weight(1f)) {
                SubtitleText(text = "Red", line = 1)
                Canvas(modifier = smallCanvasModifier) {
                    drawRect(color = Color.Red)
                }
            }

            Spacer(modifier = Modifier.size(20.dp))
            Column(modifier = Modifier.weight(1f)) {
                SubtitleText(text = "Yellow", line = 1)
                Canvas(modifier = smallCanvasModifier) {
                    drawRect(color = Color.Yellow)
                }
            }

            Spacer(modifier = Modifier.size(20.dp))
            Column(modifier = Modifier.weight(1f)) {
                SubtitleText(text = "Green", line = 1)
                Canvas(modifier = smallCanvasModifier) {
                    drawRect(color = Color.Green)
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun PaintColorSamplePreview() {
    PaintColorSample(remember { MutableStateFlow(true) })
}


@Composable
private fun PaintIsAntiAliasSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyThemeColors3.current
    ExpandableItem3(title = "Paint - isAntiAlias", allExpandFlow, padding = 20.dp) {
        val smallCanvasModifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .border(1.dp, colors.primaryTranslucency)
        Row(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.weight(1f)) {
                SubtitleText(text = "Default：true", line = 1)
                Canvas(modifier = smallCanvasModifier) {
                    drawIntoCanvas {
                        it.drawCircle(
                            center = Offset(x = size.width / 2, y = size.height / 2),
                            radius = size.width * 0.4f,
                            paint = Paint().apply {
                                color = colors.tertiary
                                style = PaintingStyle.Stroke
                                strokeWidth = 20.dp.toPx()
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.size(20.dp))
            Column(modifier = Modifier.weight(1f)) {
                SubtitleText(text = "false", line = 1)
                Canvas(modifier = smallCanvasModifier) {
                    drawIntoCanvas {
                        it.drawCircle(
                            center = Offset(x = size.width / 2, y = size.height / 2),
                            radius = size.width * 0.4f,
                            paint = Paint().apply {
                                color = colors.tertiary
                                style = PaintingStyle.Stroke
                                strokeWidth = 20.dp.toPx()
                                isAntiAlias = false
                            }
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun PaintIsAntiAliasSamplePreview() {
    PaintIsAntiAliasSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalTextApi::class)
@Composable
private fun PaintStrokeSample(allExpandFlow: Flow<Boolean>) {
    val desc = """
        cap：设置线条首尾的样式：
            StrokeCap.BUTT：无
            StrokeCap.SQUARE：方形
            StrokeCap.ROUND： 半圆形
        注意： StrokeCap.ROUND、StrokeCap.SQUARE 会在线长度的基础上首尾添加一个通过 setStrokeWidth 设置的宽度。因此 ROUND 和 SQUARE 样式的线条明显长一点
    """.trimIndent()
    val colors = MyThemeColors3.current
    ExpandableItem3(title = "Paint - stroke", allExpandFlow, padding = 20.dp, desc = desc) {
        val smallCanvasModifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .border(1.dp, colors.primaryTranslucency)
        Row(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.weight(1f)) {
                SubtitleText(text = "strokeWidth - 10.dp", line = 2)
                Canvas(modifier = smallCanvasModifier) {
                    drawRect(
                        color = colors.tertiary,
                        topLeft = Offset(10.dp.toPx(), 10.dp.toPx()),
                        size = Size(size.width - 10.dp.toPx() * 2, size.height - 10.dp.toPx() * 2),
                        style = Stroke(width = 9.dp.toPx())
                    )
                }
            }

            Spacer(modifier = Modifier.size(20.dp))
            Column(modifier = Modifier.weight(1f)) {
                SubtitleText(text = "strokeWidth - 3.dp", line = 2)
                Canvas(modifier = smallCanvasModifier) {
                    drawRect(
                        color = colors.tertiary,
                        topLeft = Offset(10.dp.toPx(), 10.dp.toPx()),
                        size = Size(size.width - 10.dp.toPx() * 2, size.height - 10.dp.toPx() * 2),
                        style = Stroke(width = 3.dp.toPx())
                    )
                }
            }

            Spacer(modifier = Modifier.size(20.dp))
            val textMeasurer = rememberTextMeasurer()
            Column(modifier = Modifier.weight(1f)) {
                SubtitleText(text = "cap", line = 2)
                Canvas(modifier = smallCanvasModifier) {
                    val padding = 10.dp.toPx()
                    val lineSpace = 4.dp.toPx()
                    val lineSize = 5.dp.toPx()
                    val textFontSize = 12.sp
                    var top = padding
                    listOf(
                        "Butt" to StrokeCap.Butt,
                        "Square" to StrokeCap.Square,
                        "Round" to StrokeCap.Round
                    ).forEachIndexed { index, pair ->
                        val (name, cap) = pair
                        if (index > 0) {
                            top += lineSpace
                        }
                        val textLayoutResult = textMeasurer.measure(
                            AnnotatedString(name),
                            style = TextStyle(fontSize = textFontSize)
                        )
                        drawText(
                            textLayoutResult,
                            topLeft = Offset(padding, top)
                        )
                        top += textLayoutResult.size.height + 2.dp.toPx()
                        drawLine(
                            colors.tertiary,
                            start = Offset(padding, top),
                            end = Offset(size.width - padding, top),
                            strokeWidth = lineSize,
                            cap = cap
                        )
                        top += lineSize
                    }
                }
            }
        }

        val padding = 20.dp.toPx()
        val getPath: (size: Size) -> Path = { size ->
            Path().apply {
                moveTo(padding, padding)
                lineTo(size.width - padding, padding)
                lineTo(size.width - padding, size.height / 2)
                lineTo(padding, size.height / 2)
                lineTo(padding, size.height - padding)
                lineTo(size.width - padding, size.height - padding)
            }
        }

        Spacer(modifier = Modifier.size(20.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.weight(1f)) {
                SubtitleText(text = "strokeJoin - Miter", line = 2)
                Canvas(modifier = smallCanvasModifier) {
                    drawPath(
                        color = colors.tertiary,
                        path = getPath(size),
                        style = Stroke(width = 10.dp.toPx(), join = StrokeJoin.Miter)
                    )
                }
            }

            Spacer(modifier = Modifier.size(20.dp))
            Column(modifier = Modifier.weight(1f)) {
                SubtitleText(text = "strokeJoin - Round", line = 2)
                Canvas(modifier = smallCanvasModifier) {
                    drawPath(
                        color = colors.tertiary,
                        path = getPath(size),
                        style = Stroke(width = 10.dp.toPx(), join = StrokeJoin.Round)
                    )
                }
            }

            Spacer(modifier = Modifier.size(20.dp))
            Column(modifier = Modifier.weight(1f)) {
                SubtitleText(text = "strokeJoin - Bevel", line = 2)
                Canvas(modifier = smallCanvasModifier) {
                    drawPath(
                        color = colors.tertiary,
                        path = getPath(size),
                        style = Stroke(width = 10.dp.toPx(), join = StrokeJoin.Bevel)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun PaintStrokeSamplePreview() {
    PaintStrokeSample(remember { MutableStateFlow(true) })
}


@Composable
private fun PaintStrokePathEffectSample(allExpandFlow: Flow<Boolean>) {
    val desc = """
        pathEffect 用于在绘制路径时给路径添加特效，有以下几种实现
        1. cornerPathEffect：拐角处绘制成圆角
        2. dashPathEffect：虚线效果
        3. stampedPathEffect：用一个个小印章形状代替虚线效果中的实线部分绘制路径
        4. chainPathEffect：将两个 PathEffect 先后应用于路径上 
    """.trimIndent()
    val colors = MyThemeColors3.current
    ExpandableItem3(title = "Paint - stroke - pathEffect", allExpandFlow, padding = 20.dp, desc = desc) {
        val smallCanvasModifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .border(1.dp, colors.primaryTranslucency)

        val padding = 20.dp.toPx()
        val getPath: (size: Size) -> Path = { size ->
            Path().apply {
                moveTo(padding, padding)
                lineTo(size.width - padding, padding)
                lineTo(size.width - padding, size.height / 2)
                lineTo(padding, size.height / 2)
                lineTo(padding, size.height - padding)
                lineTo(size.width - padding, size.height - padding)
            }
        }

        Row(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.weight(1f)) {
                SubtitleText(text = "corner", line = 1)
                Canvas(modifier = smallCanvasModifier) {
                    drawPath(
                        color = colors.tertiary,
                        path = getPath(size),
                        style = Stroke(
                            width = 10.dp.toPx(),
                            pathEffect = PathEffect.cornerPathEffect(10.dp.toPx())
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.size(20.dp))
            Column(modifier = Modifier.weight(1f)) {
                SubtitleText(text = "dash", line = 1)
                Canvas(modifier = smallCanvasModifier) {
                    drawPath(
                        color = colors.tertiary,
                        path = getPath(size),
                        style = Stroke(
                            width = 2.dp.toPx(),
                            pathEffect = PathEffect.dashPathEffect(
                                intervals = floatArrayOf(10.dp.toPx(), 2.dp.toPx()),
                            )
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.size(20.dp))
            Column(modifier = Modifier.weight(1f)) {
                SubtitleText(text = "dash - phase", line = 1)
                val infiniteTransition = rememberInfiniteTransition()
                val initialValue = infiniteTransition.animateFloat(
                    initialValue = 0f,
                    targetValue = 1f,
                    animationSpec = infiniteRepeatable(
                        animation = tween(durationMillis = 1000, easing = LinearEasing),
                    )
                )
                var phaseState by remember { mutableStateOf(0f) }
                Canvas(modifier = smallCanvasModifier) {
                    if (phaseState < -100000) {
                        phaseState = 0f
                    }
                    phaseState += -3f + (initialValue.value * 0f)
                    drawPath(
                        color = colors.tertiary,
                        path = getPath(size),
                        style = Stroke(
                            width = 2.dp.toPx(),
                            pathEffect = PathEffect.dashPathEffect(
                                intervals = floatArrayOf(10.dp.toPx(), 2.dp.toPx()),
                                phase = phaseState
                            )
                        )
                    )
                }
            }
        }

        Spacer(modifier = Modifier.size(20.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.weight(1f)) {
                SubtitleText(text = "stamped - style: Morph", line = 3)
                Canvas(modifier = smallCanvasModifier) {
                    val strokeSize = 10.dp.toPx()
                    drawPath(
                        color = colors.tertiary,
                        path = getPath(size),
                        style = Stroke(
                            width = strokeSize,
                            pathEffect = PathEffect.stampedPathEffect(
                                shape = Path().apply {
                                    moveTo(strokeSize / 2, 0f)
                                    lineTo(strokeSize, strokeSize)
                                    lineTo(0f, strokeSize)
                                },
                                advance = 60f,
                                phase = 0f,
                                style = StampedPathEffectStyle.Morph
                            )
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.size(20.dp))
            Column(modifier = Modifier.weight(1f)) {
                SubtitleText(text = "stamped - style: Rotate", line = 3)
                Canvas(modifier = smallCanvasModifier) {
                    val strokeSize = 10.dp.toPx()
                    drawPath(
                        color = colors.tertiary,
                        path = getPath(size),
                        style = Stroke(
                            width = strokeSize,
                            pathEffect = PathEffect.stampedPathEffect(
                                shape = Path().apply {
                                    moveTo(strokeSize / 2, 0f)
                                    lineTo(strokeSize, strokeSize)
                                    lineTo(0f, strokeSize)
                                },
                                advance = 60f,
                                phase = 0f,
                                style = StampedPathEffectStyle.Rotate
                            )
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.size(20.dp))
            Column(modifier = Modifier.weight(1f)) {
                SubtitleText(text = "stamped - style: Translate", line = 3)
                Canvas(modifier = smallCanvasModifier) {
                    val strokeSize = 10.dp.toPx()
                    drawPath(
                        color = colors.tertiary,
                        path = getPath(size),
                        style = Stroke(
                            width = strokeSize,
                            pathEffect = PathEffect.stampedPathEffect(
                                shape = Path().apply {
                                    moveTo(strokeSize / 2, 0f)
                                    lineTo(strokeSize, strokeSize)
                                    lineTo(0f, strokeSize)
                                },
                                advance = 60f,
                                phase = 0f,
                                style = StampedPathEffectStyle.Translate
                            )
                        )
                    )
                }
            }
        }

        Spacer(modifier = Modifier.size(20.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.weight(1f)) {
                SubtitleText(text = "stamped - phase", line = 2)
                val infiniteTransition = rememberInfiniteTransition()
                val initialValue = infiniteTransition.animateFloat(
                    initialValue = 0f,
                    targetValue = 1f,
                    animationSpec = infiniteRepeatable(
                        animation = tween(durationMillis = 1000, easing = LinearEasing),
                    )
                )
                var phaseState by remember { mutableStateOf(0f) }
                Canvas(modifier = smallCanvasModifier) {
                    if (phaseState < -100000) {
                        phaseState = 0f
                    }
                    phaseState += -3f + (initialValue.value * 0f)
                    val strokeSize = 10.dp.toPx()
                    drawPath(
                        color = colors.tertiary,
                        path = getPath(size),
                        style = Stroke(
                            width = strokeSize,
                            pathEffect = PathEffect.stampedPathEffect(
                                shape = Path().apply {
                                    moveTo(strokeSize / 2, 0f)
                                    lineTo(strokeSize, strokeSize)
                                    lineTo(0f, strokeSize)
                                },
                                advance = 60f,
                                phase = phaseState,
                                style = StampedPathEffectStyle.Translate
                            )
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.size(20.dp))
            Column(modifier = Modifier.weight(1f)) {
                SubtitleText(text = "chain", line = 2)
                Canvas(modifier = smallCanvasModifier) {
                    drawPath(
                        color = colors.tertiary,
                        path = getPath(size),
                        style = Stroke(
                            width = 2.dp.toPx(),
                            pathEffect = PathEffect.chainPathEffect(
                                PathEffect.cornerPathEffect(10.dp.toPx()),
                                PathEffect.dashPathEffect(
                                    intervals = floatArrayOf(10.dp.toPx(), 2.dp.toPx()),
                                )
                            )
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.size(20.dp))
            Column(modifier = Modifier.weight(1f)) {

            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun PaintStrokePathEffectSamplePreview() {
    PaintStrokePathEffectSample(remember { MutableStateFlow(true) })
}