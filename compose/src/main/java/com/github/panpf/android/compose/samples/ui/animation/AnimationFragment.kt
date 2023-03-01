package com.github.panpf.android.compose.samples.ui.animation

import androidx.compose.animation.core.DecayAnimation
import androidx.compose.animation.core.TargetBasedAnimation
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.tween
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.withFrameNanos
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

class AnimationFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "Animation"
    }

    @Composable
    override fun DrawContent() {
        val desc = """
            |    Animation 是可用的最低级别的 Animation API，它是无状态的，它没有任何生命周期概念。
            |    Animation 充当更高级别 API 使用的动画计算引擎，到目前为止，我们看到的许多动画都是基于 Animation 构建的。
            |    只有在需要手动控制动画时间的场景下才需要直接使用 Animation。
            |    Animation 子类型有两种：
            |       TargetBasedAnimation：有目标值并且可以控制播放时间的动画
            |       DecayAnimation：没有目标值，不能控制播放时间，只能根据起始速度衰减的动画
        """.trimMargin()
        ExpandableLayout { allExpandFlow ->
            ExpandableText(text = desc, modifier = Modifier.padding(20.dp))
            TargetBasedAnimationSample(allExpandFlow)
            DecayAnimationSample(allExpandFlow)
        }
    }
}


@Composable
private fun TargetBasedAnimationSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyThemeColors3.current
    val coroutineScope = rememberCoroutineScope()
    val translateXState = remember { mutableStateOf(0f) }
    val translateYState = remember { mutableStateOf(0f) }
    val ballSizeDp = 60.dp
    val ballSize = IntSize(ballSizeDp.toPx().roundToInt(), ballSizeDp.toPx().roundToInt())
    ExpandableItem3(title = "TargetBasedAnimation", allExpandFlow, padding = 20.dp) {
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .border(2.dp, color = colors.primaryTranslucency)
                .pointerInput(Unit) {
                    detectTapGestures { offset ->
                        coroutineScope.launch {
                            val animation = TargetBasedAnimation(
                                animationSpec = tween(1000),
                                Float.VectorConverter,
                                initialValue = translateXState.value,
                                offset.x - (ballSize.width / 2)
                            )
                            val startTime = withFrameNanos { it }
                            do {
                                val playTime = withFrameNanos { it } - startTime
                                translateXState.value = animation.getValueFromNanos(playTime)
                            } while (!animation.isFinishedFromNanos(playTime))
                        }

                        coroutineScope.launch {
                            val animation = TargetBasedAnimation(
                                animationSpec = tween(1000),
                                Float.VectorConverter,
                                initialValue = translateYState.value,
                                offset.y - (ballSize.height / 2)
                            )
                            val startTime = withFrameNanos { it }
                            do {
                                val playTime = withFrameNanos { it } - startTime
                                translateYState.value = animation.getValueFromNanos(playTime)
                            } while (!animation.isFinishedFromNanos(playTime))
                        }
                    }
                }
        ) {
            val siteSize = IntSize(maxWidth.toPx().roundToInt(), maxHeight.toPx().roundToInt())
            LaunchedEffect(key1 = Unit) {
                val siteCenter = siteSize.center
                translateXState.value = siteCenter.x.toFloat() - (ballSize.width / 2)
                translateYState.value = siteCenter.y.toFloat() - (ballSize.height / 2)
            }
            Box(
                modifier = Modifier
                    .offset {
                        IntOffset(
                            translateXState.value.roundToInt(),
                            translateYState.value.roundToInt()
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
private fun TargetBasedAnimationSamplePreview() {
    TargetBasedAnimationSample(remember { MutableStateFlow(true) })
}


@Composable
private fun DecayAnimationSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyThemeColors3.current
    val velocityTracker = remember { VelocityTracker() }
    val coroutineScope = rememberCoroutineScope()
    val translateXState = remember { mutableStateOf(0f) }
    val translateYState = remember { mutableStateOf(0f) }
    val decayAnimationSpec = rememberSplineBasedDecay<Float>()
    ExpandableItem3(title = "DecayAnimation", allExpandFlow, padding = 20.dp) {
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .border(2.dp, color = colors.primaryTranslucency)
                .align(Alignment.CenterHorizontally)
        ) {
            val siteSize = IntSize(maxWidth.toPx().roundToInt(), maxHeight.toPx().roundToInt())
            val ballSize = IntSize(60.dp.toPx().roundToInt(), 60.dp.toPx().roundToInt())
            val xUpperBound = (siteSize.width - ballSize.width).toFloat()
            val yUpperBound = (siteSize.height - ballSize.height).toFloat()
            LaunchedEffect(key1 = Unit) {
                val siteCenter = siteSize.center
                translateXState.value = siteCenter.x.toFloat() - (ballSize.width / 2)
                translateYState.value = siteCenter.y.toFloat() - (ballSize.height / 2)
            }
            Box(
                modifier = Modifier
                    .offset {
                        IntOffset(
                            translateXState.value.roundToInt(),
                            translateYState.value.roundToInt()
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
                                    val animation = DecayAnimation(
                                        animationSpec = decayAnimationSpec,
                                        initialValue = translateXState.value,
                                        initialVelocityVector = Float.VectorConverter
                                            .convertToVector(velocity.x),
                                        typeConverter = Float.VectorConverter
                                    )
                                    val startTime = withFrameNanos { it }
                                    do {
                                        val playTime = withFrameNanos { it } - startTime
                                        translateXState.value =
                                            animation
                                                .getValueFromNanos(playTime)
                                                .coerceIn(0f, xUpperBound)
                                    } while (!animation.isFinishedFromNanos(playTime))
                                }
                                coroutineScope.launch {
                                    val animation = DecayAnimation(
                                        animationSpec = decayAnimationSpec,
                                        initialValue = translateYState.value,
                                        initialVelocityVector = Float.VectorConverter
                                            .convertToVector(velocity.y),
                                        typeConverter = Float.VectorConverter
                                    )
                                    val startTime = withFrameNanos { it }
                                    do {
                                        val playTime = withFrameNanos { it } - startTime
                                        translateYState.value =
                                            animation
                                                .getValueFromNanos(playTime)
                                                .coerceIn(0f, yUpperBound)
                                    } while (!animation.isFinishedFromNanos(playTime))
                                }
                            },
                            onDragCancel = { }
                        ) { change, dragAmount ->
                            change.consume()
                            translateXState.value =
                                (translateXState.value + dragAmount.x).coerceIn(0f, xUpperBound)
                            translateYState.value =
                                (translateYState.value + dragAmount.y).coerceIn(0f, yUpperBound)
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
private fun DecayAnimationSamplePreview() {
    DecayAnimationSample(remember { MutableStateFlow(true) })
}