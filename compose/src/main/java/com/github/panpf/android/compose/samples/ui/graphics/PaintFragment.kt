package com.github.panpf.android.compose.samples.ui.graphics

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem3
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
import com.github.panpf.android.compose.samples.ui.base.inversionOfNegativeColorFilter
import com.github.panpf.android.compose.samples.ui.base.theme.MyThemeColors3
import com.google.accompanist.flowlayout.FlowRow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class PaintFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "Paint"
    }

    @Composable
    override fun DrawContent() {
        ExpandableLayout { allExpandFlow ->
            PaintSample(allExpandFlow)
            // todo alpha
            // todo isAntiAlias
            // todo color
            // todo stroke
            // todo blendMode
            // todo pathEffect
            // todo shader
            // todo colorFilter
            // todo style
            // todo filterQuality
        }
    }
}


@Composable
private fun PaintSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyThemeColors3.current
    val smallCanvasModifier = Modifier
        .size(100.dp)
        .border(1.dp, colors.primaryTranslucency)
    ExpandableItem3(title = "DrawScope - drawRect", allExpandFlow, padding = 20.dp) {
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            mainAxisSpacing = 20.dp,
            crossAxisSpacing = 20.dp
        ) {
            Column(modifier = Modifier.fillMaxWidth(0.4f)) {
                Text(text = "默认配置，大小会充满画布", modifier = Modifier.height(50.dp))
                Canvas(modifier = smallCanvasModifier) {
                    drawRect(color = colors.tertiary)
                }
            }

            Column(modifier = Modifier.fillMaxWidth(0.4f)) {
                Text(text = "修改 size 参数，让矩形宽高只有画布的一半", modifier = Modifier.height(50.dp))
                Canvas(modifier = smallCanvasModifier) {
                    drawRect(color = colors.tertiary, size = size / 2f)
                }
            }

            Column(modifier = Modifier.fillMaxWidth(0.4f)) {
                Text(text = "修改 topLeft 参数让矩形居中", modifier = Modifier.height(50.dp))
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

            Column(modifier = Modifier.fillMaxWidth(0.4f)) {
                Text(text = "修改 alpha 参数让矩形更加透明", modifier = Modifier.height(50.dp))
                Canvas(modifier = smallCanvasModifier) {
                    val rectSize = size / 2f
                    drawRect(
                        color = colors.tertiary,
                        topLeft = Offset(
                            x = (size.width - rectSize.width) / 2f,
                            y = (size.height - rectSize.height) / 2f
                        ),
                        size = rectSize,
                        alpha = 0.5f,
                    )
                }
            }

            Column(modifier = Modifier.fillMaxWidth(0.4f)) {
                Text(text = "修改 style 参数让矩形变为描边样式", modifier = Modifier.height(50.dp))
                val strokeWidth = with(LocalDensity.current) { 2.dp.toPx() }
                Canvas(modifier = smallCanvasModifier) {
                    val rectSize = size / 2f
                    drawRect(
                        color = colors.tertiary,
                        topLeft = Offset(
                            x = (size.width - rectSize.width) / 2f,
                            y = (size.height - rectSize.height) / 2f
                        ),
                        size = rectSize,
                        style = Stroke(width = strokeWidth)
                    )
                }
            }

            Column(modifier = Modifier.fillMaxWidth(0.4f)) {
                Text(text = "修改 colorFilter 参数翻转矩形的颜色", modifier = Modifier.height(50.dp))
                Canvas(modifier = smallCanvasModifier) {
                    val rectSize = size / 2f
                    drawRect(
                        color = colors.tertiary,
                        topLeft = Offset(
                            x = (size.width - rectSize.width) / 2f,
                            y = (size.height - rectSize.height) / 2f
                        ),
                        size = rectSize,
                        colorFilter = inversionOfNegativeColorFilter
                    )
                }
            }

            Column(modifier = Modifier.fillMaxWidth(0.4f)) {
                Text(text = "绘制一个圆形和一个矩形，它们两个相交", modifier = Modifier.height(50.dp))
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
                    )
                }
            }

            Column(modifier = Modifier.fillMaxWidth(0.4f)) {
                Text(text = "修改 blendMode 参数改变它们的混合模式", modifier = Modifier.height(50.dp))
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
                        blendMode = BlendMode.SrcOut
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun PaintSamplePreview() {
    PaintSample(remember { MutableStateFlow(true) })
}