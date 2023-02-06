package com.github.panpf.android.compose.samples.ui.gestures

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FixedThreshold
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.rememberSwipeableState
import androidx.compose.material.swipeable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem3
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
import com.github.panpf.tools4j.math.ktx.format
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlin.math.roundToInt

class SwipFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "Swip"
    }

    @Composable
    override fun DrawContent() {
        ExpandableLayout { allExpandFlow ->
            SwipSwipeableSample(allExpandFlow)
            SwipSwipeableAnchorsSample(allExpandFlow)
            SwipSwipeableThresholdsSample(allExpandFlow)
        }
    }
}


@Composable
@OptIn(ExperimentalMaterialApi::class)
private fun SwipSwipeableSample(allExpandFlow: Flow<Boolean>) {
    val desc = """
        Modifier.swipeable() 可以检测滑动手势，并在结束滑动时移动元素到目标位置或初始位置，效果类似滑动开关或滑动关闭窗口。
        swipeable 同样仅报告滑动距离（以像素为单位），您需要保存状态并通过 offset 修饰符来移动元素。
        swipeable 只能检测横向或竖向滑动，不能同时检测两个方向。
    """.trimIndent()
    val squareSize = 48.dp
    val bgSize = squareSize * 2.5f
    val swipeableState1 = rememberSwipeableState(0)
    val swipeableState2 = rememberSwipeableState(0)
    val squareSizePx = with(LocalDensity.current) { squareSize.toPx() }
    val bgSizePx = with(LocalDensity.current) { bgSize.toPx() }
    val anchors =
        mapOf(0f to 0, bgSizePx - squareSizePx to 1) // Maps anchor points (in px) to states
    ExpandableItem3(
        title = "Swip（swipeable）",
        allExpandFlow,
        padding = 20.dp,
        desc = desc,
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Box(modifier = Modifier.weight(1f)) {
                Box(
                    modifier = Modifier
                        .width(bgSize)
                        .swipeable(
                            state = swipeableState1,
                            anchors = anchors,
                            thresholds = { _, _ -> FractionalThreshold(0.3f) },
                            orientation = Orientation.Horizontal
                        )
                        .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.5f))
                        .align(Alignment.Center)
                ) {
                    Box(
                        Modifier
                            .offset { IntOffset(swipeableState1.offset.value.roundToInt(), 0) }
                            .size(squareSize)
                            .background(MaterialTheme.colorScheme.primary)
                    )
                }
            }

            Box(modifier = Modifier.weight(1f)) {
                Box(
                    modifier = Modifier
                        .height(bgSize)
                        .swipeable(
                            state = swipeableState2,
                            anchors = anchors,
                            thresholds = { _, _ -> FractionalThreshold(0.3f) },
                            orientation = Orientation.Vertical
                        )
                        .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.5f))
                        .align(Alignment.Center)
                ) {
                    Box(
                        Modifier
                            .offset { IntOffset(0, swipeableState2.offset.value.roundToInt()) }
                            .size(squareSize)
                            .background(MaterialTheme.colorScheme.primary)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun SwipSwipeableSamplePreview() {
    SwipSwipeableSample(remember { MutableStateFlow(true) })
}


@Composable
@OptIn(ExperimentalMaterialApi::class)
private fun SwipSwipeableAnchorsSample(allExpandFlow: Flow<Boolean>) {
    val desc = """
        Modifier.swipeable() 的 anchors 属性用来设置锚点数，如下示例可轻松实现一个拥有 5 个锚点的滑动条。
    """.trimIndent()
    val trackSize = 16.dp
    val thumbSize = 40.dp
    val thumbSizePx = with(LocalDensity.current) { thumbSize.toPx() }
    val stepSize = 10.dp
    val stepSizePx = with(LocalDensity.current) { stepSize.toPx() }
    val anchorsCount = 5
    val swipeableState = rememberSwipeableState(0f)
    ExpandableItem3(
        title = "Swip（swipeable - anchors）",
        allExpandFlow,
        padding = 20.dp,
        desc = desc,
    ) {
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxWidth()
                .height(thumbSize)
        ) {
            val trackStartPadding = (thumbSize / 2) - (stepSize / 2)
            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(start = trackStartPadding, end = trackStartPadding)
                    .fillMaxWidth()
                    .height(trackSize)
                    .clip(RoundedCornerShape(50))
                    .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.5f))
            )

            val maxWidthPx = with(LocalDensity.current) { maxWidth.toPx() }
            repeat(anchorsCount) { index ->
                Box(
                    modifier = Modifier
                        .offset {
                            val partLength = 1 / (anchorsCount - 1f)
                            val progress = index * partLength
                            val offset =
                                (progress * (maxWidthPx - (thumbSizePx))) + (thumbSizePx / 2) - (stepSizePx / 2)
                            IntOffset(x = offset.roundToInt(), y = 0)
                        }
                        .align(Alignment.CenterStart)
                        .size(stepSize)
                        .clip(RoundedCornerShape(50))
                        .background(MaterialTheme.colorScheme.primary)
                )
            }

            val anchors = buildMap {
                val partLength = 1 / (anchorsCount - 1f)
                repeat(anchorsCount) { index ->
                    val progress = index * partLength
                    put(progress * (maxWidthPx - (thumbSizePx)), progress)
                }
            }
            Box(
                Modifier
                    .offset {
                        val offsetX = swipeableState.offset.value
                            .coerceAtLeast(0f)
                            .coerceAtMost(maxWidthPx - (thumbSizePx))
                            .roundToInt()
                        IntOffset(offsetX, 0)
                    }
                    .size(thumbSize)
                    .clip(RoundedCornerShape(50))
                    .background(MaterialTheme.colorScheme.tertiary.copy(alpha = 0.8f))
                    .swipeable(
                        state = swipeableState,
                        anchors = anchors,
                        thresholds = { _, _ -> FractionalThreshold(0.3f) },
                        orientation = Orientation.Horizontal
                    )
            )
        }
        Text(
            text = "offset: ${swipeableState.offset.value.toInt()}, " +
                    "current: ${swipeableState.currentValue.format("", 1)}, " +
                    "target: ${swipeableState.targetValue.format("", 1)}"
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun SwipSwipeableAnchorsSamplePreview() {
    SwipSwipeableAnchorsSample(remember { MutableStateFlow(true) })
}


@Composable
@OptIn(ExperimentalMaterialApi::class)
private fun SwipSwipeableThresholdsSample(allExpandFlow: Flow<Boolean>) {
    val desc = """
        Modifier.swipeable() 的 thresholds 属性用来设置松手后该滑向哪里的临界阈值，分为以下两种。
        FixedThreshold：固定阈值，距离上一个锚点的偏移量大于等于此固定阈值就滑向下一个锚点
        FractionalThreshold：百分比阈值，超过两个锚点之间此百分比阈值就滑向下一个锚点
    """.trimIndent()
    val trackSize = 16.dp
    val thumbSize = 40.dp
    val thumbSizePx = with(LocalDensity.current) { thumbSize.toPx() }
    val stepSize = 2.dp
    val stepSizePx = with(LocalDensity.current) { stepSize.toPx() }
    ExpandableItem3(
        title = "Swip（swipeable - thresholds）",
        allExpandFlow,
        padding = 20.dp,
        desc = desc,
    ) {
        listOf(
            "FixedThreshold(100.dp)" to FixedThreshold(100.dp),
            "FixedThreshold(200.dp)" to FixedThreshold(200.dp),
            "FractionalThreshold(0.3f)" to FractionalThreshold(0.3f),
            "FractionalThreshold(0.7f)" to FractionalThreshold(0.7f),
        ).forEachIndexed { index, pair ->
            if (index > 0) {
                Spacer(modifier = Modifier.height(20.dp))
            }
            Text(text = pair.first)
            val swipeableState = rememberSwipeableState(0f)
            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(thumbSize)
            ) {
                val trackStartPadding = (thumbSize / 2) - (stepSize / 2)
                Box(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(start = trackStartPadding, end = trackStartPadding)
                        .fillMaxWidth()
                        .height(trackSize)
//                    .clip(RoundedCornerShape(50))
                        .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.5f))
                )

                val maxWidthPx = with(LocalDensity.current) { maxWidth.toPx() }
                val stepCount = 11
                repeat(stepCount) { index ->
                    Box(
                        modifier = Modifier
                            .offset {
                                val partLength = 1 / (stepCount - 1f)
                                val progress = index * partLength
                                val offset =
                                    (progress * (maxWidthPx - (thumbSizePx))) + (thumbSizePx / 2) - (stepSizePx / 2)
                                IntOffset(x = offset.roundToInt(), y = 0)
                            }
                            .align(Alignment.CenterStart)
                            .width(stepSize)
                            .height(trackSize)
//                        .clip(RoundedCornerShape(50))
                            .background(MaterialTheme.colorScheme.primary)
                    )
                }

                val anchors = mapOf(0f to 0f, (maxWidthPx - (thumbSizePx)) to 1f)
                Box(
                    Modifier
                        .offset {
                            val offsetX = swipeableState.offset.value
                                .coerceAtLeast(0f)
                                .coerceAtMost(maxWidthPx - (thumbSizePx))
                                .roundToInt()
                            IntOffset(offsetX, 0)
                        }
                        .size(thumbSize)
                        .clip(RoundedCornerShape(50))
                        .background(MaterialTheme.colorScheme.tertiary.copy(alpha = 0.8f))
                        .swipeable(
                            state = swipeableState,
                            anchors = anchors,
                            thresholds = { _, _ -> pair.second },
                            orientation = Orientation.Horizontal
                        )
                )
            }
            Text(
                text = "offset: ${swipeableState.offset.value.toInt()}, " +
                        "current: ${swipeableState.currentValue.format("", 1)}, " +
                        "target: ${swipeableState.targetValue.format("", 1)}"
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun SwipSwipeableThresholdsSamplePreview() {
    SwipSwipeableThresholdsSample(remember { MutableStateFlow(true) })
}