package com.github.panpf.android.compose.samples.ui.animation

import androidx.compose.animation.core.Ease
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseInBack
import androidx.compose.animation.core.EaseInBounce
import androidx.compose.animation.core.EaseInCirc
import androidx.compose.animation.core.EaseInCubic
import androidx.compose.animation.core.EaseInElastic
import androidx.compose.animation.core.EaseInExpo
import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.EaseInOutBack
import androidx.compose.animation.core.EaseInOutBounce
import androidx.compose.animation.core.EaseInOutCirc
import androidx.compose.animation.core.EaseInOutCubic
import androidx.compose.animation.core.EaseInOutElastic
import androidx.compose.animation.core.EaseInOutExpo
import androidx.compose.animation.core.EaseInOutQuad
import androidx.compose.animation.core.EaseInOutQuart
import androidx.compose.animation.core.EaseInOutQuint
import androidx.compose.animation.core.EaseInOutSine
import androidx.compose.animation.core.EaseInQuad
import androidx.compose.animation.core.EaseInQuart
import androidx.compose.animation.core.EaseInQuint
import androidx.compose.animation.core.EaseInSine
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.EaseOutBack
import androidx.compose.animation.core.EaseOutBounce
import androidx.compose.animation.core.EaseOutCirc
import androidx.compose.animation.core.EaseOutCubic
import androidx.compose.animation.core.EaseOutElastic
import androidx.compose.animation.core.EaseOutExpo
import androidx.compose.animation.core.EaseOutQuad
import androidx.compose.animation.core.EaseOutQuart
import androidx.compose.animation.core.EaseOutQuint
import androidx.compose.animation.core.EaseOutSine
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem3
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.ExpandableText
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
import com.github.panpf.tools4j.math.ktx.format
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class EasingFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "Easing"
    }

    @Composable
    override fun DrawContent() {
        val desc = """
            Easing 用于为基于时长的 AnimationSpec 操作（如 tween 或 keyframes）调整动画的小数值。这样可让动画值加速和减速，而不是以恒定的速率移动。小数是介于 0（起始值）和 1.0（结束值）之间的值，表示动画中的当前点。
            Easing 实际上是一个函数，它取一个介于 0 和 1.0 之间的小数值并返回一个浮点数。返回的值可能位于边界之外，表示过冲或下冲。
            Compose 提供多种内置 Easing 函数，可以满足绝大多数的需求。
        """.trimIndent()
        ExpandableLayout { allExpandFlow ->
            ExpandableText(text = desc, modifier = Modifier.padding(20.dp))
            EasingLinearSample(allExpandFlow)
            EasingEaseSample(allExpandFlow)
            EasingSineSample(allExpandFlow)
            EasingCubicSample(allExpandFlow)
            EasingQuintSample(allExpandFlow)
            EasingCircSample(allExpandFlow)
            EasingQuadSample(allExpandFlow)
            EasingQuartSample(allExpandFlow)
            EasingExpoSample(allExpandFlow)
            EasingBackSample(allExpandFlow)
            EasingElasticSample(allExpandFlow)
            EasingBounceSample(allExpandFlow)
        }
    }
}


@Composable
private fun EasingItems(
    type: String,
    list: List<Pair<String, Easing>>,
    allExpandFlow: Flow<Boolean>
) {
    var enabled by remember { mutableStateOf(false) }
    val stateList = list.map {
        it.first to animateFloatAsState(
            targetValue = if (enabled) 1f else 0f,
            animationSpec = tween(easing = it.second, durationMillis = 2000)
        )
    }
    ExpandableItem3(title = "Easing（$type）", allExpandFlow, padding = 20.dp) {
        stateList.forEachIndexed { index1, pair ->
            if (index1 > 0) {
                Spacer(modifier = Modifier.height(10.dp))
            }
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "${pair.first}: ${pair.second.value.format("", 1)}",
                    fontSize = 12.sp
                )
                BoxWithConstraints(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(20.dp)
                        .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.5f))
                ) {
                    val blockSize = maxHeight
                    Box(
                        modifier = Modifier
                            .offset((maxWidth - blockSize) * pair.second.value, 0.dp)
                            .size(blockSize)
                            .background(MaterialTheme.colorScheme.tertiary)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = { enabled = !enabled }) {
            Text(text = "Play")
        }
    }
}


@Composable
private fun EasingLinearSample(allExpandFlow: Flow<Boolean>) {
    EasingItems(
        "Linear",
        listOf(
            "FastOutSlowIn" to FastOutSlowInEasing,
            "LinearOutSlowIn" to LinearOutSlowInEasing,
            "FastOutLinearIn" to FastOutLinearInEasing,
            "Linear" to LinearEasing,
        ),
        allExpandFlow
    )
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun EasingLinearSamplePreview() {
    EasingLinearSample(remember { MutableStateFlow(true) })
}


@Composable
private fun EasingEaseSample(allExpandFlow: Flow<Boolean>) {
    EasingItems(
        "Ease",
        listOf(
            "Ease" to Ease,
            "EaseIn" to EaseIn,
            "EaseOut" to EaseOut,
            "EaseInOut" to EaseInOut,
        ),
        allExpandFlow
    )
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun EasingEaseSamplePreview() {
    EasingEaseSample(remember { MutableStateFlow(true) })
}


@Composable
private fun EasingSineSample(allExpandFlow: Flow<Boolean>) {
    EasingItems(
        "Sine",
        listOf(
            "EaseInSine" to EaseInSine,
            "EaseOutSine" to EaseOutSine,
            "EaseInOutSine" to EaseInOutSine,
        ),
        allExpandFlow
    )
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun EasingSineSamplePreview() {
    EasingSineSample(remember { MutableStateFlow(true) })
}


@Composable
private fun EasingCubicSample(allExpandFlow: Flow<Boolean>) {
    EasingItems(
        "Cubic",
        listOf(
            "EaseInCubic" to EaseInCubic,
            "EaseOutCubic" to EaseOutCubic,
            "EaseInOutCubic" to EaseInOutCubic,
        ),
        allExpandFlow
    )
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun EasingCubicSamplePreview() {
    EasingCubicSample(remember { MutableStateFlow(true) })
}


@Composable
private fun EasingQuintSample(allExpandFlow: Flow<Boolean>) {
    EasingItems(
        "Quint",
        listOf(
            "EaseInQuint" to EaseInQuint,
            "EaseOutQuint" to EaseOutQuint,
            "EaseInOutQuint" to EaseInOutQuint,
        ),
        allExpandFlow
    )
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun EasingQuintSamplePreview() {
    EasingQuintSample(remember { MutableStateFlow(true) })
}


@Composable
private fun EasingCircSample(allExpandFlow: Flow<Boolean>) {
    EasingItems(
        "Circ",
        listOf(
            "EaseInCirc" to EaseInCirc,
            "EaseOutCirc" to EaseOutCirc,
            "EaseInOutCirc" to EaseInOutCirc,
        ),
        allExpandFlow
    )
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun EasingCircSamplePreview() {
    EasingCircSample(remember { MutableStateFlow(true) })
}


@Composable
private fun EasingQuadSample(allExpandFlow: Flow<Boolean>) {
    EasingItems(
        "Quad",
        listOf(
            "EaseInQuad" to EaseInQuad,
            "EaseOutQuad" to EaseOutQuad,
            "EaseInOutQuad" to EaseInOutQuad,
        ),
        allExpandFlow
    )
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun EasingQuadSamplePreview() {
    EasingQuadSample(remember { MutableStateFlow(true) })
}


@Composable
private fun EasingQuartSample(allExpandFlow: Flow<Boolean>) {
    EasingItems(
        "Quart",
        listOf(
            "EaseInQuart" to EaseInQuart,
            "EaseOutQuart" to EaseOutQuart,
            "EaseInOutQuart" to EaseInOutQuart,
        ),
        allExpandFlow
    )
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun EasingQuartSamplePreview() {
    EasingQuartSample(remember { MutableStateFlow(true) })
}


@Composable
private fun EasingExpoSample(allExpandFlow: Flow<Boolean>) {
    EasingItems(
        "Expo",
        listOf(
            "EaseInExpo" to EaseInExpo,
            "EaseOutExpo" to EaseOutExpo,
            "EaseInOutExpo" to EaseInOutExpo,
        ),
        allExpandFlow
    )
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun EasingExpoSamplePreview() {
    EasingExpoSample(remember { MutableStateFlow(true) })
}


@Composable
private fun EasingBackSample(allExpandFlow: Flow<Boolean>) {
    EasingItems(
        "Back",
        listOf(
            "EaseInBack" to EaseInBack,
            "EaseOutBack" to EaseOutBack,
            "EaseInOutBack" to EaseInOutBack,
        ),
        allExpandFlow
    )
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun EasingBackSamplePreview() {
    EasingBackSample(remember { MutableStateFlow(true) })
}


@Composable
private fun EasingElasticSample(allExpandFlow: Flow<Boolean>) {
    EasingItems(
        "Elastic",
        listOf(
            "EaseInElastic" to EaseInElastic,
            "EaseOutElastic" to EaseOutElastic,
            "EaseInOutElastic" to EaseInOutElastic,
        ),
        allExpandFlow
    )
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun EasingElasticSamplePreview() {
    EasingElasticSample(remember { MutableStateFlow(true) })
}


@Composable
private fun EasingBounceSample(allExpandFlow: Flow<Boolean>) {
    EasingItems(
        "Bounce",
        listOf(
            "EaseInBounce" to EaseInBounce,
            "EaseOutBounce" to EaseOutBounce,
            "EaseInOutBounce" to EaseInOutBounce,
        ),
        allExpandFlow
    )
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun EasingBounceSamplePreview() {
    EasingBounceSample(remember { MutableStateFlow(true) })
}