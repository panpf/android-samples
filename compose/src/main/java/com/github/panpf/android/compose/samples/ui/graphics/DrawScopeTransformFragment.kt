package com.github.panpf.android.compose.samples.ui.graphics

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.ClipOp
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.graphics.drawscope.inset
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.rotateRad
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem3
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
import com.github.panpf.android.compose.samples.ui.base.SubtitleText
import com.github.panpf.android.compose.samples.ui.base.computePentagramPath
import com.github.panpf.android.compose.samples.ui.base.computeTrianglePath
import com.github.panpf.android.compose.samples.ui.base.theme.MyThemeColors3
import com.github.panpf.android.compose.samples.ui.customization.grid.VerticalGrid
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class DrawScopeTransformFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "DrawScope - transform"
    }

    @Composable
    override fun DrawContent() {
        ExpandableLayout { allExpandFlow ->
            Text(
                text = "transform 包括 scale、rotate、translate、clip 等一系列对画布进行变换操作的函数",
                modifier = Modifier.padding(20.dp)
            )
            DrawScopeClipRectSample(allExpandFlow)
            DrawScopeClipPathSample(allExpandFlow)
            DrawScopeInsetSample(allExpandFlow)
            DrawScopeRotateSample(allExpandFlow)
            DrawScopeRotateRadSample(allExpandFlow)
            DrawScopeScaleSample(allExpandFlow)
            DrawScopeTranslateSample(allExpandFlow)
            DrawScopeWithTransformSample(allExpandFlow)
        }
    }
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun DrawScopeClipRectSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyThemeColors3.current
    ExpandableItem3(
        title = "DrawScope - clipRect",
        allExpandFlow,
        padding = 20.dp,
        desc = "clipRect 用矩形裁剪画布"
    ) {
        VerticalGrid(
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
        ){
            val smallCanvasModifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .border(1.dp, colors.primaryTranslucency)
            val subtitleTextLine = 4
            Column {
                SubtitleText(text = "None", line = subtitleTextLine)
                Canvas(modifier = smallCanvasModifier) {
                    drawCircle(
                        color = colors.tertiary
                    )
                }
            }

            Column {
                SubtitleText(
                    text = "clipRect(10.dp, 10.dp, width-10.dp, height-10.dp)".trimIndent(),
                    line = subtitleTextLine
                )
                Canvas(modifier = smallCanvasModifier) {
                    clipRect(
                        left = 10.dp.toPx(),
                        top = 10.dp.toPx(),
                        right = size.width - 10.dp.toPx(),
                        bottom = size.height - 10.dp.toPx(),
                    ) {
                        drawCircle(
                            color = colors.tertiary
                        )
                    }
                }
            }

            Column {
                SubtitleText(text = "clipOp: Difference", line = subtitleTextLine)
                Canvas(modifier = smallCanvasModifier) {
                    clipRect(
                        left = 10.dp.toPx(),
                        top = 10.dp.toPx(),
                        right = size.width - 10.dp.toPx(),
                        bottom = size.height - 10.dp.toPx(),
                        clipOp = ClipOp.Difference,
                    ) {
                        drawCircle(
                            color = colors.tertiary
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun DrawScopeClipRectSamplePreview() {
    DrawScopeClipRectSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun DrawScopeClipPathSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyThemeColors3.current
    ExpandableItem3(
        title = "DrawScope - clipPath",
        allExpandFlow,
        padding = 20.dp,
        desc = "clipPath 用 Path 裁剪画布"
    ) {
        VerticalGrid(
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
        ){
            val smallCanvasModifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .border(1.dp, colors.primaryTranslucency)
            val subtitleTextLine = 2
            Column {
                SubtitleText(text = "None", line = subtitleTextLine)
                Canvas(modifier = smallCanvasModifier) {
                    drawCircle(
                        color = colors.tertiary
                    )
                }
            }

            Column {
                SubtitleText(text = "clipPath".trimIndent(), line = subtitleTextLine)
                Canvas(modifier = smallCanvasModifier) {
                    clipPath(
                        path = computePentagramPath(size)
                    ) {
                        drawCircle(
                            color = colors.tertiary
                        )
                    }
                }
            }

            Column {
                SubtitleText(text = "clipOp: Difference", line = subtitleTextLine)
                Canvas(modifier = smallCanvasModifier) {
                    clipPath(
                        path = computePentagramPath(size),
                        clipOp = ClipOp.Difference,
                    ) {
                        drawCircle(
                            color = colors.tertiary
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun DrawScopeClipPathSamplePreview() {
    DrawScopeClipPathSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun DrawScopeInsetSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyThemeColors3.current
    ExpandableItem3(
        title = "DrawScope - inset",
        allExpandFlow,
        padding = 20.dp,
        desc = "inset 理解成 padding 即可"
    ) {
        VerticalGrid(
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
        ){
            val smallCanvasModifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .border(1.dp, colors.primaryTranslucency)
            val subtitleTextLine = 3
            Column {
                SubtitleText(text = "None", line = subtitleTextLine)
                Canvas(modifier = smallCanvasModifier) {
                    drawRect(
                        color = colors.tertiary
                    )
                }
            }

            Column {
                SubtitleText(text = "inset(20.dp)", line = subtitleTextLine)
                Canvas(modifier = smallCanvasModifier) {
                    inset(20.dp.toPx()) {
                        drawRect(
                            color = colors.tertiary
                        )
                    }
                }
            }

            Column {
                SubtitleText(text = "inset(10.dp, 20.dp, 30.dp, 40.dp)", line = subtitleTextLine)
                Canvas(modifier = smallCanvasModifier) {
                    inset(10.dp.toPx(), 20.dp.toPx(), 30.dp.toPx(), 40.dp.toPx()) {
                        drawRect(
                            color = colors.tertiary
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun DrawScopeInsetSamplePreview() {
    DrawScopeInsetSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun DrawScopeRotateSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyThemeColors3.current
    ExpandableItem3(
        title = "DrawScope - rotate",
        allExpandFlow,
        padding = 20.dp,
        desc = "rotate 根据角度旋转画布"
    ) {
        VerticalGrid(
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
        ){
            val smallCanvasModifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .border(1.dp, colors.primaryTranslucency)
            val subtitleTextLine = 4
            Column {
                SubtitleText(text = "None", line = subtitleTextLine)
                Canvas(modifier = smallCanvasModifier) {
                    inset(20.dp.toPx()) {
                        drawPath(
                            path = computeTrianglePath(size),
                            color = colors.tertiary,
                        )
                    }
                }
            }

            val infiniteTransition = rememberInfiniteTransition()
            val degrees by infiniteTransition.animateFloat(
                initialValue = 0f,
                targetValue = 360f,
                animationSpec = infiniteRepeatable(
                    animation = tween(durationMillis = 5000, easing = LinearEasing),
                    repeatMode = RepeatMode.Restart
                )
            )
            Column {
                SubtitleText(text = "degrees", line = subtitleTextLine)
                Canvas(modifier = smallCanvasModifier) {
                    rotate(degrees = degrees) {
                        inset(20.dp.toPx()) {
                            drawPath(
                                path = computeTrianglePath(size),
                                color = colors.tertiary,
                            )
                        }
                    }
                }
            }

            Column {
                SubtitleText(
                    text = "pivotX=width*0.25f, pivotY=height*0.5f",
                    line = subtitleTextLine
                )
                Canvas(modifier = smallCanvasModifier) {
                    rotate(
                        degrees = degrees,
                        pivot = Offset(size.width * 0.25f, size.height * 0.5f)
                    ) {
                        inset(20.dp.toPx()) {
                            drawPath(
                                path = computeTrianglePath(size),
                                color = colors.tertiary,
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun DrawScopeRotateSamplePreview() {
    DrawScopeRotateSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun DrawScopeRotateRadSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyThemeColors3.current
    ExpandableItem3(
        title = "DrawScope - rotateRad",
        allExpandFlow,
        padding = 20.dp,
        desc = "rotateRad 根据弧度旋转画布"
    ) {
        VerticalGrid(
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
        ){
            val smallCanvasModifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .border(1.dp, colors.primaryTranslucency)
            val subtitleTextLine = 4
            Column {
                SubtitleText(text = "None", line = subtitleTextLine)
                Canvas(modifier = smallCanvasModifier) {
                    inset(20.dp.toPx()) {
                        drawPath(
                            path = computeTrianglePath(size),
                            color = colors.tertiary,
                        )
                    }
                }
            }

            val infiniteTransition = rememberInfiniteTransition()
            val degrees by infiniteTransition.animateFloat(
                initialValue = 0f,
                targetValue = 360f,
                animationSpec = infiniteRepeatable(
                    animation = tween(durationMillis = 5000, easing = LinearEasing),
                    repeatMode = RepeatMode.Restart
                )
            )
            Column {
                SubtitleText(text = "radians", line = subtitleTextLine)
                Canvas(modifier = smallCanvasModifier) {
                    rotateRad(radians = ((degrees * Math.PI / 180).toFloat())) {
                        inset(20.dp.toPx()) {
                            drawPath(
                                path = computeTrianglePath(size),
                                color = colors.tertiary,
                            )
                        }
                    }
                }
            }

            Column {
                SubtitleText(
                    text = "pivotX=width*0.25f, pivotY=height*0.5f",
                    line = subtitleTextLine
                )
                Canvas(modifier = smallCanvasModifier) {
                    rotateRad(
                        radians = ((degrees * Math.PI / 180).toFloat()),
                        pivot = Offset(size.width * 0.25f, size.height * 0.5f)
                    ) {
                        inset(20.dp.toPx()) {
                            drawPath(
                                path = computeTrianglePath(size),
                                color = colors.tertiary,
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun DrawScopeRotateRadSamplePreview() {
    DrawScopeRotateRadSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun DrawScopeScaleSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyThemeColors3.current
    ExpandableItem3(
        title = "DrawScope - scale",
        allExpandFlow,
        padding = 20.dp,
        desc = "scale 缩放画布"
    ) {
        VerticalGrid(
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
        ){
            val smallCanvasModifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .border(1.dp, colors.primaryTranslucency)
            var subtitleTextLine = 3
            Column {
                SubtitleText(text = "None", line = subtitleTextLine)
                Canvas(modifier = smallCanvasModifier) {
                    drawPath(
                        path = computeTrianglePath(size),
                        color = colors.tertiary,
                    )
                }
            }

            Column {
                SubtitleText(text = "scale=0.5f", line = subtitleTextLine)
                Canvas(modifier = smallCanvasModifier) {
                    scale(scale = 0.5f) {
                        drawPath(
                            path = computeTrianglePath(size),
                            color = colors.tertiary,
                        )
                    }
                }
            }

            Column {
                SubtitleText(text = "scale=1.2f", line = subtitleTextLine)
                Canvas(modifier = smallCanvasModifier) {
                    scale(scale = 1.2f) {
                        drawPath(
                            path = computeTrianglePath(size),
                            color = colors.tertiary,
                        )
                    }
                }
            }

            subtitleTextLine = 4
            Column {
                SubtitleText(text = "scaleX=0.5f, scaleY=0.8f", line = subtitleTextLine)
                Canvas(modifier = smallCanvasModifier) {
                    scale(scaleX = 0.5f, scaleY = 0.8f) {
                        drawPath(
                            path = computeTrianglePath(size),
                            color = colors.tertiary,
                        )
                    }
                }
            }

            Column {
                SubtitleText(text = "scaleX=0.8f, scaleY=0.5f", line = subtitleTextLine)
                Canvas(modifier = smallCanvasModifier) {
                    scale(scaleX = 0.8f, scaleY = 0.5f) {
                        drawPath(
                            path = computeTrianglePath(size),
                            color = colors.tertiary,
                        )
                    }
                }
            }

            Column {
                SubtitleText(
                    text = "pivotX=width*0.25f, pivotY=height*0.5f",
                    line = subtitleTextLine
                )
                Canvas(modifier = smallCanvasModifier) {
                    scale(
                        scale = 0.5f,
                        pivot = Offset(size.width * 0.25f, size.height * 0.5f)
                    ) {
                        drawPath(
                            path = computeTrianglePath(size),
                            color = colors.tertiary,
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun DrawScopeScaleSamplePreview() {
    DrawScopeScaleSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun DrawScopeTranslateSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyThemeColors3.current
    ExpandableItem3(
        title = "DrawScope - translate",
        allExpandFlow,
        padding = 20.dp,
        desc = "translate 偏移画布"
    ) {
        VerticalGrid(
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
        ){
            val smallCanvasModifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .border(1.dp, colors.primaryTranslucency)
            val subtitleTextLine = 2
            Column {
                SubtitleText(text = "None", line = subtitleTextLine)
                Canvas(modifier = smallCanvasModifier) {
                    drawPath(
                        path = computeTrianglePath(size),
                        color = colors.tertiary,
                    )
                }
            }

            Column {
                SubtitleText(text = "left=5.dp, top=10.dp", line = subtitleTextLine)
                Canvas(modifier = smallCanvasModifier) {
                    translate(left = 5.dp.toPx(), top = 10.dp.toPx()) {
                        drawPath(
                            path = computeTrianglePath(size),
                            color = colors.tertiary,
                        )
                    }
                }
            }

            Column {
                SubtitleText(text = "left=10.dp, top=5.dp", line = subtitleTextLine)
                Canvas(modifier = smallCanvasModifier) {
                    translate(left = 10.dp.toPx(), top = 5.dp.toPx()) {
                        drawPath(
                            path = computeTrianglePath(size),
                            color = colors.tertiary,
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun DrawScopeTranslateSamplePreview() {
    DrawScopeTranslateSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun DrawScopeWithTransformSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyThemeColors3.current
    ExpandableItem3(
        title = "DrawScope - withTransform",
        allExpandFlow,
        padding = 20.dp,
        desc = "withTransform 可以一次执行多种变换操作"
    ) {
        VerticalGrid(
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
        ){
            val smallCanvasModifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .border(1.dp, colors.primaryTranslucency)
            val subtitleTextLine = 2
            Column {
                SubtitleText(text = "None", line = subtitleTextLine)
                Canvas(modifier = smallCanvasModifier) {
                    drawPath(
                        path = computeTrianglePath(size),
                        color = colors.tertiary,
                    )
                }
            }

            Column {
                SubtitleText(text = "scale, rotate", line = subtitleTextLine)
                Canvas(modifier = smallCanvasModifier) {
                    withTransform({
                        scale(0.5f)
                        rotate(45f)
                    }) {
                        drawPath(
                            path = computeTrianglePath(size),
                            color = colors.tertiary,
                        )
                    }
                }
            }

            Column {
                SubtitleText(text = "scale, translate", line = subtitleTextLine)
                Canvas(modifier = smallCanvasModifier) {
                    withTransform({
                        scale(0.5f)
                        translate(-40f, -40f)
                    }) {
                        drawPath(
                            path = computeTrianglePath(size),
                            color = colors.tertiary,
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun DrawScopeWithTransformSamplePreview() {
    DrawScopeWithTransformSample(remember { MutableStateFlow(true) })
}