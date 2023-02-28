package com.github.panpf.android.compose.samples.ui.animation

import androidx.compose.animation.rememberSplineBasedDecay
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.util.VelocityTracker
import androidx.compose.ui.input.pointer.util.addPointerInputChange
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.center
import androidx.compose.ui.unit.dp
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem3
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.ExpandableText
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
import com.github.panpf.android.compose.samples.ui.base.theme.MyThemeColors3
import com.github.panpf.android.compose.samples.ui.base.toPx
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

class AnimatableFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "Animatable"
    }

    @Composable
    override fun DrawContent() {
        val desc = """
            |    Animatable 是低级别的 API，它为 animate*AsState 等高级别的 API 提供实现。
            |    Animatable 是一个值容器，它可以在通过 animateTo 更改值时为值添加动画效果。
            |    Animatable 的许多功能（包括 animateTo）以挂起函数的形式提供。这意味着，它们需要封装在适当的协程作用域内。
            |    Animatable 有以下几个主要的方法：
            |        animateTo：启动从当前值到目标值的动画
            |        animateDecay：启动一个衰减动画，通常用于 fling 手势
            |        snapTo：立即结束动画并将当前值设为目标值
            |        updateBounds：限制最小和最大值，animateDecay 示例就通过 updateBounds 限制了小球不会跑到框外
        """.trimMargin()
        ExpandableLayout { allExpandFlow ->
            ExpandableText(text = desc, modifier = Modifier.padding(20.dp))
            AnimatableAnimateToSample(allExpandFlow)
            AnimatableAnimateDecaySample(allExpandFlow)
            AnimatableSnapToSample(allExpandFlow)
        }
    }
}


@Composable
private fun AnimatableAnimateToSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyThemeColors3.current
    val coroutineScope = rememberCoroutineScope()
    val translateXAnimatable = remember { androidx.compose.animation.core.Animatable(0f) }
    val translateYAnimatable = remember { androidx.compose.animation.core.Animatable(0f) }
    val ballSizeDp = 60.dp
    val ballSize = IntSize(ballSizeDp.toPx().roundToInt(), ballSizeDp.toPx().roundToInt())
    ExpandableItem3(title = "Animatable - animateTo", allExpandFlow, padding = 20.dp) {
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .border(2.dp, color = colors.primaryTranslucency)
                .pointerInput(Unit) {
                    detectTapGestures {
                        val targetCoordinates = it
                        coroutineScope.launch {
                            translateXAnimatable.animateTo(targetCoordinates.x - (ballSize.width / 2))
                        }
                        coroutineScope.launch {
                            translateYAnimatable.animateTo(targetCoordinates.y - (ballSize.height / 2))
                        }
                    }
                }
        ) {
            val siteSize = IntSize(maxWidth.toPx().roundToInt(), maxHeight.toPx().roundToInt())
            translateXAnimatable.updateBounds(
                lowerBound = 0f,
                upperBound = (siteSize.width - ballSize.width).toFloat(),
            )
            translateYAnimatable.updateBounds(
                lowerBound = 0f,
                upperBound = (siteSize.height - ballSize.height).toFloat(),
            )
            LaunchedEffect(key1 = Unit) {
                val siteCenter = siteSize.center
                translateXAnimatable.snapTo(siteCenter.x.toFloat() - (ballSize.width / 2))
                translateYAnimatable.snapTo(siteCenter.y.toFloat() - (ballSize.height / 2))
            }
            Box(
                modifier = Modifier
                    .offset {
                        IntOffset(
                            translateXAnimatable.value.roundToInt(),
                            translateYAnimatable.value.roundToInt()
                        )
                    }
                    .size(ballSizeDp)
                    .background(color = colors.tertiary, shape = CircleShape)
            )
        }
        Text(text = "你可以点击空白处试试")
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun AnimatableAnimateToSamplePreview() {
    AnimatableAnimateToSample(remember { MutableStateFlow(true) })
}


@Composable
private fun AnimatableAnimateDecaySample(allExpandFlow: Flow<Boolean>) {
    val colors = MyThemeColors3.current
    val velocityTracker = remember { VelocityTracker() }
    val coroutineScope = rememberCoroutineScope()
    val translateXAnimatable = remember { androidx.compose.animation.core.Animatable(0f) }
    val translateYAnimatable = remember { androidx.compose.animation.core.Animatable(0f) }
    val decayAnimationSpec = rememberSplineBasedDecay<Float>()
    ExpandableItem3(title = "Animatable - animateDecay", allExpandFlow, padding = 20.dp) {
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .border(2.dp, color = colors.primaryTranslucency)
                .align(Alignment.CenterHorizontally)
        ) {
            val siteSize = IntSize(maxWidth.toPx().roundToInt(), maxHeight.toPx().roundToInt())
            val ballSize = IntSize(60.dp.toPx().roundToInt(), 60.dp.toPx().roundToInt())
            translateXAnimatable.updateBounds(
                lowerBound = 0f,
                upperBound = (siteSize.width - ballSize.width).toFloat(),
            )
            translateYAnimatable.updateBounds(
                lowerBound = 0f,
                upperBound = (siteSize.height - ballSize.height).toFloat(),
            )
            LaunchedEffect(key1 = Unit) {
                val siteCenter = siteSize.center
                translateXAnimatable.snapTo(siteCenter.x.toFloat() - (ballSize.width / 2))
                translateYAnimatable.snapTo(siteCenter.y.toFloat() - (ballSize.height / 2))
            }
            Box(
                modifier = Modifier
                    .offset {
                        IntOffset(
                            translateXAnimatable.value.roundToInt(),
                            translateYAnimatable.value.roundToInt()
                        )
                    }
                    .size(60.dp)
                    .background(color = colors.tertiary, shape = CircleShape)
                    .pointerInput(Unit) {
                        detectDragGestures(
                            onDragStart = {
                                velocityTracker.resetTracking()
                            },
                            onDragEnd = {
                                val velocity = velocityTracker.calculateVelocity()
                                coroutineScope.launch {
                                    translateXAnimatable
                                        .animateDecay(velocity.x, decayAnimationSpec)
                                }
                                coroutineScope.launch {
                                    translateYAnimatable
                                        .animateDecay(velocity.y, decayAnimationSpec)
                                }
                            },
                            onDragCancel = { }
                        ) { change, dragAmount ->
                            change.consume()
                            coroutineScope.launch {
                                translateXAnimatable.snapTo(translateXAnimatable.value + dragAmount.x)
                            }
                            coroutineScope.launch {
                                translateYAnimatable.snapTo(translateYAnimatable.value + dragAmount.y)
                            }
                            velocityTracker.addPointerInputChange(change)
                        }
                    }
            )
        }

        Text(text = "你可以拖动小球试试")
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun AnimatableAnimateDecaySamplePreview() {
    AnimatableAnimateDecaySample(remember { MutableStateFlow(true) })
}


@Composable
private fun AnimatableSnapToSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyThemeColors3.current
    val coroutineScope = rememberCoroutineScope()
    val translateXAnimatable = remember { androidx.compose.animation.core.Animatable(0f) }
    val translateYAnimatable = remember { androidx.compose.animation.core.Animatable(0f) }
    val ballSizeDp = 60.dp
    val ballSize = IntSize(ballSizeDp.toPx().roundToInt(), ballSizeDp.toPx().roundToInt())
    ExpandableItem3(title = "Animatable - snapTo", allExpandFlow, padding = 20.dp) {
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .border(2.dp, color = colors.primaryTranslucency)
                .pointerInput(Unit) {
                    detectTapGestures {
                        val targetCoordinates = it
                        coroutineScope.launch {
                            translateXAnimatable.snapTo(targetCoordinates.x - (ballSize.width / 2))
                        }
                        coroutineScope.launch {
                            translateYAnimatable.snapTo(targetCoordinates.y - (ballSize.height / 2))
                        }
                    }
                }
        ) {
            val siteSize = IntSize(maxWidth.toPx().roundToInt(), maxHeight.toPx().roundToInt())
            translateXAnimatable.updateBounds(
                lowerBound = 0f,
                upperBound = (siteSize.width - ballSize.width).toFloat(),
            )
            translateYAnimatable.updateBounds(
                lowerBound = 0f,
                upperBound = (siteSize.height - ballSize.height).toFloat(),
            )
            LaunchedEffect(key1 = Unit) {
                val siteCenter = siteSize.center
                translateXAnimatable.snapTo(siteCenter.x.toFloat() - (ballSize.width / 2))
                translateYAnimatable.snapTo(siteCenter.y.toFloat() - (ballSize.height / 2))
            }
            Box(
                modifier = Modifier
                    .offset {
                        IntOffset(
                            translateXAnimatable.value.roundToInt(),
                            translateYAnimatable.value.roundToInt()
                        )
                    }
                    .size(ballSizeDp)
                    .background(color = colors.tertiary, shape = CircleShape)
            )
        }
        Text(text = "你可以点击空白处试试")
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun AnimatableSnapToSamplePreview() {
    AnimatableSnapToSample(remember { MutableStateFlow(true) })
}