package com.github.panpf.android.compose.samples.ui.graphics

import android.graphics.Bitmap
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.ImageShader
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PaintingStyle
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StampedPathEffectStyle
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.graphics.withSaveLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.toBitmap
import com.github.panpf.android.compose.samples.R
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem3
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
import com.github.panpf.android.compose.samples.ui.base.SubtitleText
import com.github.panpf.android.compose.samples.ui.base.blackWhiteColorFilter
import com.github.panpf.android.compose.samples.ui.base.computeTrianglePath
import com.github.panpf.android.compose.samples.ui.base.inversionOfNegativeColorFilter
import com.github.panpf.android.compose.samples.ui.base.theme.MyThemeColors3
import com.github.panpf.android.compose.samples.ui.base.toPx
import com.github.panpf.android.compose.samples.ui.customization.grid.VerticalGrid
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlin.math.min
import kotlin.math.roundToInt

class PaintFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "Paint"
    }

    @Composable
    override fun DrawContent() {
        ExpandableLayout { allExpandFlow ->
            PaintAlphaSample(allExpandFlow)
            PaintBlendModeShapeSample(allExpandFlow)
//            PaintBlendModeBitmapSample(allExpandFlow)
            PaintColorSample(allExpandFlow)
            PaintColorFilterSample(allExpandFlow)
            PaintIsAntiAliasSample(allExpandFlow)
            PaintShaderLinearGradientSample(allExpandFlow)
            PaintShaderRadialGradientSample(allExpandFlow)
            PaintShaderSweepGradientSample(allExpandFlow)
            PaintShaderImageSample(allExpandFlow)
            PaintStrokeSample(allExpandFlow)
            PaintStrokePathEffectSample(allExpandFlow)
            PaintStyleSample(allExpandFlow)
        }
    }
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun PaintAlphaSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyThemeColors3.current
    ExpandableItem3(title = "Paint - alpha", allExpandFlow, padding = 20.dp) {
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
                SubtitleText(text = "Default", line = 1)
                Canvas(modifier = smallCanvasModifier) {
                    drawRect(color = colors.tertiary)
                }
            }

            Column {
                SubtitleText(text = "alpha = 0.7f", line = 1)
                Canvas(modifier = smallCanvasModifier) {
                    drawRect(color = colors.tertiary, alpha = 0.7f)
                }
            }

            Column {
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


val blendModes = listOf(
    null,
    BlendMode.Clear,
    BlendMode.Color,
    BlendMode.ColorBurn,
    BlendMode.ColorDodge,
    BlendMode.Darken,
    BlendMode.Difference,
    BlendMode.Dst,
    BlendMode.DstAtop,
    BlendMode.DstIn,
    BlendMode.DstOver,
    BlendMode.DstOut,
    BlendMode.Exclusion,
    BlendMode.Hardlight,
    BlendMode.Hue,
    BlendMode.Lighten,
    BlendMode.Luminosity,
    BlendMode.Modulate,
    BlendMode.Multiply,
    BlendMode.Overlay,
    BlendMode.Plus,
    BlendMode.Saturation,
    BlendMode.Screen,
    BlendMode.Softlight,
    BlendMode.Src,
    BlendMode.SrcAtop,
    BlendMode.SrcIn,
    BlendMode.SrcOut,
    BlendMode.SrcOver,
    BlendMode.Xor,
)

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun PaintBlendModeShapeSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyThemeColors3.current
    ExpandableItem3(title = "Paint - blendMode", allExpandFlow, padding = 20.dp) {
        VerticalGrid(
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            val smallCanvasModifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .border(1.dp, colors.primaryTranslucency)
            blendModes.forEach { blendMode ->
                Column {
                    SubtitleText(text = blendMode?.toString() ?: "Default", line = 1)
                    Canvas(modifier = smallCanvasModifier) {
                        drawIntoCanvas {
                            it.withSaveLayer(
                                bounds = Rect(0f, 0f, size.width, size.height),
                                paint = Paint()
                            ) {
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
                                    blendMode = blendMode ?: DrawScope.DefaultBlendMode
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun PaintBlendModeShapeSSamplePreview() {
    PaintBlendModeShapeSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun PaintBlendModeBitmapSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyThemeColors3.current
    val smallCanvasModifier = Modifier
        .fillMaxWidth()
        .aspectRatio(1f)
        .border(1.dp, colors.primaryTranslucency)
    var dstBitmap: ImageBitmap? by remember {
        mutableStateOf(null)
    }
    var srcBitmap: ImageBitmap? by remember {
        mutableStateOf(null)
    }
    ExpandableItem3(title = "Paint - blendMode - Bitmap", allExpandFlow, padding = 20.dp) {
        VerticalGrid(
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            blendModes.forEach { blendMode ->
                Column {
                    SubtitleText(text = blendMode?.toString() ?: "Default", line = 1)
                    Canvas(modifier = smallCanvasModifier) {
                        val dst = dstBitmap ?: Bitmap.createBitmap(
                            (size.minDimension / 2.0f).roundToInt(),
                            (size.minDimension / 2.0f).roundToInt(), Bitmap.Config.ARGB_8888
                        ).apply {
                            val canvas = android.graphics.Canvas(this)
                            val scrPaint =
                                android.graphics.Paint(android.graphics.Paint.ANTI_ALIAS_FLAG)
                            scrPaint.color = colors.primary.toArgb()
                            canvas.drawCircle(width / 2f, height / 2f, width / 2f, scrPaint)
                        }.asImageBitmap().apply {
                            dstBitmap = this
                        }
                        val src = srcBitmap ?: Bitmap.createBitmap(
                            (size.minDimension / 2.0f).roundToInt(),
                            (size.minDimension / 2.0f).roundToInt(), Bitmap.Config.ARGB_8888
                        ).apply {
                            val canvas = android.graphics.Canvas(this)
                            val dstPaint =
                                android.graphics.Paint(android.graphics.Paint.ANTI_ALIAS_FLAG)
                            dstPaint.color = colors.tertiary.toArgb()
                            canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), dstPaint)
                        }.asImageBitmap().apply {
                            srcBitmap = this
                        }
                        drawImage(image = dst)
                        drawImage(
                            image = src,
                            topLeft = Offset(
                                x = (size.width - src.width) / 2f,
                                y = (size.height - src.height) / 2f
                            ),
                            blendMode = blendMode ?: DrawScope.DefaultBlendMode
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun PaintBlendModeBitmapSamplePreview() {
    PaintBlendModeBitmapSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun PaintColorSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyThemeColors3.current
    ExpandableItem3(title = "Paint - color", allExpandFlow, padding = 20.dp) {
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
                SubtitleText(text = "primary", line = 1)
                Canvas(modifier = smallCanvasModifier) {
                    drawRect(color = colors.primary)
                }
            }

            Column {
                SubtitleText(text = "tertiary", line = 1)
                Canvas(modifier = smallCanvasModifier) {
                    drawRect(color = colors.tertiary)
                }
            }

            Column {
                SubtitleText(text = "inversePrimary", line = 1)
                Canvas(modifier = smallCanvasModifier) {
                    drawRect(color = colors.inversePrimary)
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


@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun PaintColorFilterSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyThemeColors3.current
    ExpandableItem3(
        title = "Paint - colorFilter&filterQuality",
        allExpandFlow,
        padding = 20.dp
    ) {
        VerticalGrid(
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            val smallCanvasModifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .border(1.dp, colors.primaryTranslucency)
            val dogHorImage = ImageBitmap.imageResource(R.drawable.dog_hor)

            Column {
                SubtitleText(text = "None", line = 1)
                Canvas(modifier = smallCanvasModifier) {
                    val scale = min(
                        size.width / dogHorImage.width.toFloat(),
                        size.height / dogHorImage.height.toFloat()
                    )
                    val dstSize = IntSize(
                        width = (dogHorImage.width * scale).roundToInt(),
                        height = (dogHorImage.height * scale).roundToInt()
                    )
                    drawImage(
                        image = dogHorImage,
                        srcOffset = IntOffset.Zero,
                        srcSize = IntSize(dogHorImage.width, dogHorImage.height),
                        dstOffset = IntOffset(
                            x = ((size.width - dstSize.width) / 2f).roundToInt(),
                            y = ((size.height - dstSize.height) / 2f).roundToInt()
                        ),
                        dstSize = dstSize
                    )
                }
            }

            Column {
                SubtitleText(text = "黑白", line = 1)
                Canvas(modifier = smallCanvasModifier) {
                    val scale = min(
                        size.width / dogHorImage.width.toFloat(),
                        size.height / dogHorImage.height.toFloat()
                    )
                    val dstSize = IntSize(
                        width = (dogHorImage.width * scale).roundToInt(),
                        height = (dogHorImage.height * scale).roundToInt()
                    )
                    drawImage(
                        image = dogHorImage,
                        srcOffset = IntOffset.Zero,
                        srcSize = IntSize(dogHorImage.width, dogHorImage.height),
                        dstOffset = IntOffset(
                            x = ((size.width - dstSize.width) / 2f).roundToInt(),
                            y = ((size.height - dstSize.height) / 2f).roundToInt()
                        ),
                        dstSize = dstSize,
                        colorFilter = blackWhiteColorFilter
                    )
                }
            }

            Column {
                SubtitleText(text = "反转负片", line = 1)
                Canvas(modifier = smallCanvasModifier) {
                    val scale = min(
                        size.width / dogHorImage.width.toFloat(),
                        size.height / dogHorImage.height.toFloat()
                    )
                    val dstSize = IntSize(
                        width = (dogHorImage.width * scale).roundToInt(),
                        height = (dogHorImage.height * scale).roundToInt()
                    )
                    drawImage(
                        image = dogHorImage,
                        srcOffset = IntOffset.Zero,
                        srcSize = IntSize(dogHorImage.width, dogHorImage.height),
                        dstOffset = IntOffset(
                            x = ((size.width - dstSize.width) / 2f).roundToInt(),
                            y = ((size.height - dstSize.height) / 2f).roundToInt()
                        ),
                        dstSize = dstSize,
                        colorFilter = inversionOfNegativeColorFilter
                    )
                }
            }

            Column {
                SubtitleText(text = "filterQuality：None", line = 2)
                Canvas(modifier = smallCanvasModifier) {
                    val scale = min(
                        size.width / dogHorImage.width.toFloat(),
                        size.height / dogHorImage.height.toFloat()
                    )
                    val dstSize = IntSize(
                        width = (dogHorImage.width * scale).roundToInt(),
                        height = (dogHorImage.height * scale).roundToInt()
                    )
                    drawImage(
                        image = dogHorImage,
                        srcOffset = IntOffset.Zero,
                        srcSize = IntSize(dogHorImage.width, dogHorImage.height),
                        dstOffset = IntOffset(
                            x = ((size.width - dstSize.width) / 2f).roundToInt(),
                            y = ((size.height - dstSize.height) / 2f).roundToInt()
                        ),
                        dstSize = dstSize,
                        colorFilter = inversionOfNegativeColorFilter,
                        filterQuality = FilterQuality.None
                    )
                }
            }

            Column {
                SubtitleText(text = "filterQuality：Medium", line = 2)
                Canvas(modifier = smallCanvasModifier) {
                    val scale = min(
                        size.width / dogHorImage.width.toFloat(),
                        size.height / dogHorImage.height.toFloat()
                    )
                    val dstSize = IntSize(
                        width = (dogHorImage.width * scale).roundToInt(),
                        height = (dogHorImage.height * scale).roundToInt()
                    )
                    drawImage(
                        image = dogHorImage,
                        srcOffset = IntOffset.Zero,
                        srcSize = IntSize(dogHorImage.width, dogHorImage.height),
                        dstOffset = IntOffset(
                            x = ((size.width - dstSize.width) / 2f).roundToInt(),
                            y = ((size.height - dstSize.height) / 2f).roundToInt()
                        ),
                        dstSize = dstSize,
                        colorFilter = inversionOfNegativeColorFilter,
                        filterQuality = FilterQuality.Medium
                    )
                }
            }

            Column {
                SubtitleText(text = "filterQuality：High", line = 2)
                Canvas(modifier = smallCanvasModifier) {
                    val scale = min(
                        size.width / dogHorImage.width.toFloat(),
                        size.height / dogHorImage.height.toFloat()
                    )
                    val dstSize = IntSize(
                        width = (dogHorImage.width * scale).roundToInt(),
                        height = (dogHorImage.height * scale).roundToInt()
                    )
                    drawImage(
                        image = dogHorImage,
                        srcOffset = IntOffset.Zero,
                        srcSize = IntSize(dogHorImage.width, dogHorImage.height),
                        dstOffset = IntOffset(
                            x = ((size.width - dstSize.width) / 2f).roundToInt(),
                            y = ((size.height - dstSize.height) / 2f).roundToInt()
                        ),
                        dstSize = dstSize,
                        colorFilter = inversionOfNegativeColorFilter,
                        filterQuality = FilterQuality.High
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun PaintColorFilterSamplePreview() {
    PaintColorFilterSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun PaintIsAntiAliasSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyThemeColors3.current
    ExpandableItem3(title = "Paint - isAntiAlias", allExpandFlow, padding = 20.dp) {
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

            Column {
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


@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun PaintShaderLinearGradientSample(allExpandFlow: Flow<Boolean>) {
    val desc = remember {
        """
        stops: List<Float>。用于定义每个颜色的结束位置，取值大于 0f 小于等于 1f
        tileMode：定义当无法完整填充形状时（可以定义填充的起始和结束位置），剩下未填充部分如何处理
            1. Clamp：用最后的颜色填充剩下部分
            2. Repeated：重复第一个颜色到最后一个颜色填充剩下部分
            3. Mirror：反向重复第一个颜色到最后一个颜色填充剩下部分
            4. Decal：用透明颜色填充剩下部分。API 31 以上才支持 
    """.trimIndent()
    }
    val colors = MyThemeColors3.current
    ExpandableItem3(
        title = "Paint - shader | brush：linearGradient",
        allExpandFlow,
        padding = 20.dp,
        desc = desc
    ) {
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
                SubtitleText(text = "linearGradient", line = 2)
                Canvas(modifier = smallCanvasModifier) {
                    drawCircle(
                        brush = Brush.linearGradient(
                            colors = listOf(colors.primary, colors.tertiary)
                        )
                    )
                }
            }

            Column {
                SubtitleText(text = "linearGradient - stops", line = 2)
                Canvas(modifier = smallCanvasModifier) {
                    drawCircle(
                        brush = Brush.linearGradient(
                            colorStops = arrayOf(
                                0.3f to colors.primary,
                                1f to colors.tertiary
                            )
                        )
                    )
                }
            }

            Column {
                SubtitleText(text = "linearGradient - stops", line = 2)
                Canvas(modifier = smallCanvasModifier) {
                    drawCircle(
                        brush = Brush.linearGradient(
                            colorStops = arrayOf(
                                0.6f to colors.primary,
                                1f to colors.tertiary
                            )
                        )
                    )
                }
            }

            Column {
                SubtitleText(text = "linearGradient - tileMode: Clamp", line = 3)
                Canvas(modifier = smallCanvasModifier) {
                    drawCircle(
                        brush = Brush.linearGradient(
                            colors = listOf(colors.primary, colors.tertiary),
                            end = Offset(size.width / 2, size.height / 2),
                            tileMode = TileMode.Clamp
                        )
                    )
                }
            }

            Column {
                SubtitleText(text = "linearGradient - tileMode: Repeated", line = 3)
                Canvas(modifier = smallCanvasModifier) {
                    drawCircle(
                        brush = Brush.linearGradient(
                            colors = listOf(colors.primary, colors.tertiary),
                            end = Offset(size.width / 2, size.height / 2),
                            tileMode = TileMode.Repeated
                        )
                    )
                }
            }

            Column {
                SubtitleText(text = "linearGradient - tileMode: Mirror", line = 3)
                Canvas(modifier = smallCanvasModifier) {
                    drawCircle(
                        brush = Brush.linearGradient(
                            colors = listOf(colors.primary, colors.tertiary),
                            end = Offset(size.width / 2, size.height / 2),
                            tileMode = TileMode.Mirror
                        )
                    )
                }
            }

            Column {
                SubtitleText(text = "linearGradient - tileMode: Decal", line = 3)
                Canvas(modifier = smallCanvasModifier) {
                    drawCircle(
                        brush = Brush.linearGradient(
                            colors = listOf(colors.primary, colors.tertiary),
                            end = Offset(size.width / 2, size.height / 2),
                            tileMode = TileMode.Decal
                        )
                    )
                }
            }

            Column {
                SubtitleText(text = "horizontalGradient", line = 3)
                Canvas(modifier = smallCanvasModifier) {
                    drawCircle(
                        brush = Brush.horizontalGradient(
                            colors = listOf(colors.primary, colors.tertiary)
                        )
                    )
                }
            }

            Column {
                SubtitleText(text = "verticalGradient", line = 3)
                Canvas(modifier = smallCanvasModifier) {
                    drawCircle(
                        brush = Brush.verticalGradient(
                            colors = listOf(colors.primary, colors.tertiary)
                        )
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun PaintShaderLinearGradientSamplePreview() {
    PaintShaderLinearGradientSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun PaintShaderRadialGradientSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyThemeColors3.current
    ExpandableItem3(
        title = "Paint - shader | brush：radialGradient",
        allExpandFlow,
        padding = 20.dp,
    ) {
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
                SubtitleText(text = "radialGradient", line = 2)
                Canvas(modifier = smallCanvasModifier) {
                    drawCircle(
                        brush = Brush.radialGradient(
                            colors = listOf(colors.primary, colors.tertiary),
                        )
                    )
                }
            }

            Column {
                SubtitleText(text = "radialGradient - stops", line = 2)
                Canvas(modifier = smallCanvasModifier) {
                    drawCircle(
                        brush = Brush.radialGradient(
                            colorStops = arrayOf(
                                0.3f to colors.primary,
                                1f to colors.tertiary
                            ),
                        )
                    )
                }
            }

            Column {
                SubtitleText(text = "radialGradient - tileMode: Clamp", line = 2)
                Canvas(modifier = smallCanvasModifier) {
                    drawCircle(
                        brush = Brush.radialGradient(
                            colors = listOf(colors.primary, colors.tertiary),
                            radius = size.width / 4,
                            tileMode = TileMode.Clamp
                        )
                    )
                }
            }

            Column {
                SubtitleText(text = "radialGradient - tileMode: Repeated", line = 2)
                Canvas(modifier = smallCanvasModifier) {
                    drawCircle(
                        brush = Brush.radialGradient(
                            colors = listOf(colors.primary, colors.tertiary),
                            radius = size.width / 4,
                            tileMode = TileMode.Repeated
                        )
                    )
                }
            }

            Column {
                SubtitleText(text = "radialGradient - tileMode: Mirror", line = 2)
                Canvas(modifier = smallCanvasModifier) {
                    drawCircle(
                        brush = Brush.radialGradient(
                            colors = listOf(colors.primary, colors.tertiary),
                            radius = size.width / 4,
                            tileMode = TileMode.Mirror
                        )
                    )
                }
            }

            Column {
                SubtitleText(text = "radialGradient - tileMode: Decal", line = 2)
                Canvas(modifier = smallCanvasModifier) {
                    drawCircle(
                        brush = Brush.radialGradient(
                            colors = listOf(colors.primary, colors.tertiary),
                            radius = size.width / 4,
                            tileMode = TileMode.Decal
                        )
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun PaintShaderRadialGradientSamplePreview() {
    PaintShaderRadialGradientSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun PaintShaderSweepGradientSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyThemeColors3.current
    ExpandableItem3(
        title = "Paint - shader | brush：sweepGradient",
        allExpandFlow,
        padding = 20.dp,
    ) {
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
                SubtitleText(text = "sweepGradient", line = 2)
                Canvas(modifier = smallCanvasModifier) {
                    drawCircle(
                        brush = Brush.sweepGradient(
                            colors = listOf(
                                colors.primary,
                                colors.tertiary,
                                colors.primary
                            ),
                        )
                    )
                }
            }

            Column {
                SubtitleText(text = "sweepGradient - stops", line = 2)
                Canvas(modifier = smallCanvasModifier) {
                    drawCircle(
                        brush = Brush.sweepGradient(
                            colorStops = arrayOf(
                                0.1f to colors.primary,
                                0.3f to colors.tertiary,
                                1f to colors.primary
                            ),
                        )
                    )
                }
            }

            Column {
                SubtitleText(text = "sweepGradient - center", line = 2)
                Canvas(modifier = smallCanvasModifier) {
                    drawCircle(
                        brush = Brush.sweepGradient(
                            colors = listOf(
                                colors.primary,
                                colors.tertiary,
                                colors.primary
                            ),
                            center = Offset(size.width / 3, size.height / 3)
                        )
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun PaintShaderSweepGradientSamplePreview() {
    PaintShaderSweepGradientSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun PaintShaderImageSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyThemeColors3.current
    ExpandableItem3(
        title = "Paint - shader | brush：image",
        allExpandFlow,
        padding = 20.dp,
    ) {
        VerticalGrid(
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            val smallCanvasModifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .border(1.dp, colors.primaryTranslucency)
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
            Column {
                SubtitleText(text = "ImageShader - tileMode: Clamp", line = 3)
                Canvas(modifier = smallCanvasModifier) {
                    drawIntoCanvas {
                        it.drawRect(0f, 0f, size.width, size.height, Paint().apply {
                            shader = ImageShader(
                                imageHor,
                                tileModeX = TileMode.Clamp,
                                tileModeY = TileMode.Clamp
                            )
                        })
                    }
                }
            }

            Column {
                SubtitleText(text = "ImageShader - tileMode: Repeated", line = 3)
                Canvas(modifier = smallCanvasModifier) {
                    drawIntoCanvas {
                        it.drawRect(0f, 0f, size.width, size.height, Paint().apply {
                            shader = ImageShader(
                                imageHor,
                                tileModeX = TileMode.Repeated,
                                tileModeY = TileMode.Repeated
                            )
                        })
                    }
                }
            }

            Column {
                SubtitleText(text = "ImageShader - tileMode: Mirror", line = 3)
                Canvas(modifier = smallCanvasModifier) {
                    drawIntoCanvas {
                        it.drawRect(0f, 0f, size.width, size.height, Paint().apply {
                            shader = ImageShader(
                                imageHor,
                                tileModeX = TileMode.Mirror,
                                tileModeY = TileMode.Mirror
                            )
                        })
                    }
                }
            }

            Column {
                SubtitleText(text = "ImageShader - tileMode: Decal", line = 3)
                Canvas(modifier = smallCanvasModifier) {
                    drawIntoCanvas {
                        it.drawRect(0f, 0f, size.width, size.height, Paint().apply {
                            shader = ImageShader(
                                imageHor,
                                tileModeX = TileMode.Decal,
                                tileModeY = TileMode.Decal
                            )
                        })
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun PaintShaderImageSamplePreview() {
    PaintShaderImageSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalTextApi::class, ExperimentalLayoutApi::class)
@Composable
private fun PaintStrokeSample(allExpandFlow: Flow<Boolean>) {
    val desc = remember {
        """
            cap：设置线条首尾的样式：
                StrokeCap.BUTT：无
                StrokeCap.SQUARE：方形
                StrokeCap.ROUND： 半圆形
            注意： StrokeCap.ROUND、StrokeCap.SQUARE 会在线长度的基础上首尾添加一个通过 setStrokeWidth 设置的宽度。因此 ROUND 和 SQUARE 样式的线条明显长一点
        """.trimIndent()
    }
    val colors = MyThemeColors3.current
    ExpandableItem3(title = "Paint - stroke", allExpandFlow, padding = 20.dp, desc = desc) {
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
                SubtitleText(text = "strokeWidth - 10.dp", line = 2)
                Canvas(modifier = smallCanvasModifier) {
                    drawRect(
                        color = colors.tertiary,
                        topLeft = Offset(10.dp.toPx(), 10.dp.toPx()),
                        size = Size(
                            size.width - 10.dp.toPx() * 2,
                            size.height - 10.dp.toPx() * 2
                        ),
                        style = Stroke(width = 9.dp.toPx())
                    )
                }
            }

            Column {
                SubtitleText(text = "strokeWidth - 3.dp", line = 2)
                Canvas(modifier = smallCanvasModifier) {
                    drawRect(
                        color = colors.tertiary,
                        topLeft = Offset(10.dp.toPx(), 10.dp.toPx()),
                        size = Size(
                            size.width - 10.dp.toPx() * 2,
                            size.height - 10.dp.toPx() * 2
                        ),
                        style = Stroke(width = 3.dp.toPx())
                    )
                }
            }

            val textMeasurer = rememberTextMeasurer()
            Column {
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

            Column {
                SubtitleText(text = "strokeJoin - Miter", line = 2)
                Canvas(modifier = smallCanvasModifier) {
                    drawPath(
                        color = colors.tertiary,
                        path = getPath(size),
                        style = Stroke(width = 10.dp.toPx(), join = StrokeJoin.Miter)
                    )
                }
            }

            Column {
                SubtitleText(text = "strokeJoin - Round", line = 2)
                Canvas(modifier = smallCanvasModifier) {
                    drawPath(
                        color = colors.tertiary,
                        path = getPath(size),
                        style = Stroke(width = 10.dp.toPx(), join = StrokeJoin.Round)
                    )
                }
            }

            Column {
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


@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun PaintStrokePathEffectSample(allExpandFlow: Flow<Boolean>) {
    val desc = remember {
        """
            pathEffect 用于在绘制路径时给路径添加特效，有以下几种实现
            1. cornerPathEffect：拐角处绘制成圆角
            2. dashPathEffect：虚线效果
            3. stampedPathEffect：用一个个小印章形状代替虚线效果中的实线部分绘制路径
            4. chainPathEffect：将两个 PathEffect 先后应用于路径上 
        """.trimIndent()
    }
    val colors = MyThemeColors3.current
    ExpandableItem3(
        title = "Paint - stroke - pathEffect",
        allExpandFlow,
        padding = 20.dp,
        desc = desc
    ) {
        VerticalGrid(
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
        ) {
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
            Column {
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

            Column {
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

            Column {
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

            Column {
                SubtitleText(text = "stamped - style: Morph", line = 3)
                Canvas(modifier = smallCanvasModifier) {
                    val strokeSize = 10.dp.toPx()
                    drawPath(
                        color = colors.tertiary,
                        path = getPath(size),
                        style = Stroke(
                            width = strokeSize,
                            pathEffect = PathEffect.stampedPathEffect(
                                shape = computeTrianglePath(strokeSize),
                                advance = 60f,
                                phase = 0f,
                                style = StampedPathEffectStyle.Morph
                            )
                        )
                    )
                }
            }

            Column {
                SubtitleText(text = "stamped - style: Rotate", line = 3)
                Canvas(modifier = smallCanvasModifier) {
                    val strokeSize = 10.dp.toPx()
                    drawPath(
                        color = colors.tertiary,
                        path = getPath(size),
                        style = Stroke(
                            width = strokeSize,
                            pathEffect = PathEffect.stampedPathEffect(
                                shape = computeTrianglePath(strokeSize),
                                advance = 60f,
                                phase = 0f,
                                style = StampedPathEffectStyle.Rotate
                            )
                        )
                    )
                }
            }

            Column {
                SubtitleText(text = "stamped - style: Translate", line = 3)
                Canvas(modifier = smallCanvasModifier) {
                    val strokeSize = 10.dp.toPx()
                    drawPath(
                        color = colors.tertiary,
                        path = getPath(size),
                        style = Stroke(
                            width = strokeSize,
                            pathEffect = PathEffect.stampedPathEffect(
                                shape = computeTrianglePath(strokeSize),
                                advance = 60f,
                                phase = 0f,
                                style = StampedPathEffectStyle.Translate
                            )
                        )
                    )
                }
            }

            Column {
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
                                shape = computeTrianglePath(strokeSize),
                                advance = 60f,
                                phase = phaseState,
                                style = StampedPathEffectStyle.Translate
                            )
                        )
                    )
                }
            }

            Column {
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
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun PaintStrokePathEffectSamplePreview() {
    PaintStrokePathEffectSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun PaintStyleSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyThemeColors3.current
    ExpandableItem3(title = "Paint - style", allExpandFlow, padding = 20.dp) {
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
                SubtitleText(text = "Fill", line = 1)
                Canvas(modifier = smallCanvasModifier) {
                    drawRect(color = colors.tertiary, style = Fill)
                }
            }

            Column {
                SubtitleText(text = "Stroke", line = 1)
                Canvas(modifier = smallCanvasModifier) {
                    drawRect(color = colors.tertiary, style = Stroke(3.dp.toPx()))
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun PaintStyleSamplePreview() {
    PaintStyleSample(remember { MutableStateFlow(true) })
}