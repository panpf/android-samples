package com.github.panpf.android.compose.samples.ui.animation

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
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

class AnimationSpecFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "AnimationSpec"
    }

    @Composable
    override fun DrawContent() {
        ExpandableLayout { allExpandFlow ->
            AnimationSpecSpringSample(allExpandFlow)
            AnimationSpecTweenSample(allExpandFlow)
            // todo keyframes
            // todo repeatable
            // todo infiniteRepeatable
            // todo snap
        }
    }
}

@Composable
private fun AnimationSpecSpringSample(allExpandFlow: Flow<Boolean>) {
    val title = """
        |像弹簧一样的弹性动画，无法自定义动画持续时间，参数如下：
        |   * dampingRatio：阻尼比。值越大阻尼越大，弹性越小。默认值为 1f（Spring.DampingRatioNoBouncy），无阻尼也无弹性
        |   * stiffness：刚度。值越小，动画持续时间越长。默认值为 1500f（Spring.StiffnessMedium）
        |   * visibilityThreshold：可见性阈值。默认值为 null
    """.trimMargin()
    var enabled by remember { mutableStateOf(false) }
    val attrList = listOf(
        "dampingRatio" to listOf(
            "0.2f (HighBouncy)" to Spring.DampingRatioHighBouncy,
            "0.5f (MediumBouncy)" to Spring.DampingRatioMediumBouncy,
            "0.75f (LowBouncy)" to Spring.DampingRatioLowBouncy,
            "1f (NoBouncy)" to Spring.DampingRatioNoBouncy,
            "2f" to 2f,
        ).map {
            it.first to animateFloatAsState(
                targetValue = if (enabled) 1f else 0f,
                animationSpec = spring(dampingRatio = it.second)
            )
        },
        "stiffness" to listOf(
            "10000f (High)" to Spring.StiffnessHigh,
            "1500f (Medium)" to Spring.StiffnessMedium,
            "400f (MediumLow)" to Spring.StiffnessMediumLow,
            "200f (Low)" to Spring.StiffnessLow,
            "50f (VeryLow)" to Spring.StiffnessVeryLow,
        ).map {
            it.first to animateFloatAsState(
                targetValue = if (enabled) 1f else 0f,
                animationSpec = spring(stiffness = it.second)
            )
        }
    )
    ExpandableItem3(
        title = "AnimationSpec（spring）",
        allExpandFlow,
        padding = 20.dp
    ) {
        ExpandableText(text = title)

        Spacer(modifier = Modifier.height(20.dp))
        Row {
            attrList.forEachIndexed { index, attrPair ->
                if (index > 0) {
                    Spacer(modifier = Modifier.width(20.dp))
                }
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = attrPair.first, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(10.dp))
                    attrPair.second.forEachIndexed { index, pair ->
                        if (index > 0) {
                            Spacer(modifier = Modifier.height(16.dp))
                        }
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
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = { enabled = !enabled }) {
            Text(text = "Play")
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun AnimationSpecSpringSamplePreview() {
    AnimationSpecSpringSample(remember { MutableStateFlow(true) })
}

@Composable
private fun AnimationSpecTweenSample(allExpandFlow: Flow<Boolean>) {
    val title = """
        |在指定的时间内使用缓和曲线在起始值和结束值之间添加动画效果，可自定义动画持续时间，参数如下：
        |   * durationMillis：动画持续时间，单位毫秒。值越大动画时间越长。默认值为 300（AnimationConstants.DefaultDurationMillis）
        |   * delayMillis：延迟一段时间后启动动画，单位毫秒。默认值为 0
        |   * easing：动画的曲线变化。默认值为 FastOutSlowInEasing
    """.trimMargin()
    var enabled by remember { mutableStateOf(false) }
    val attrList = listOf(
        "durationMillis" to listOf(
            "100\n" to 100,
            "300\n (Default)" to 300,
            "1000\n" to 1000,
            "3000\n" to 3000,
        ).map {
            it.first to animateFloatAsState(
                targetValue = if (enabled) 1f else 0f,
                animationSpec = tween(durationMillis = it.second)
            )
        },
        "delayMillis" to listOf(
            "0\n (Default)" to 0,
            "300\n" to 300,
            "1000\n" to 1000,
            "3000\n" to 3000,
        ).map {
            it.first to animateFloatAsState(
                targetValue = if (enabled) 1f else 0f,
                animationSpec = tween(delayMillis = it.second)
            )
        },
        "easing" to listOf(
            "FastOutSlowIn\n (Default)" to FastOutSlowInEasing,
            "LinearOutSlowIn\n" to LinearOutSlowInEasing,
            "FastOutLinearIn\n" to FastOutLinearInEasing,
            "Linear\n" to LinearEasing,
        ).map {
            it.first to animateFloatAsState(
                targetValue = if (enabled) 1f else 0f,
                animationSpec = tween(easing = it.second, durationMillis = 2000)
            )
        }
    )
    ExpandableItem3(
        title = "AnimationSpec（tween）",
        allExpandFlow,
        padding = 20.dp
    ) {
        ExpandableText(text = title)

        Spacer(modifier = Modifier.height(20.dp))
        Row {
            attrList.forEachIndexed { index, attrPair ->
                if (index > 0) {
                    Spacer(modifier = Modifier.width(20.dp))
                }
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = attrPair.first, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(10.dp))
                    attrPair.second.forEachIndexed { index, pair ->
                        if (index > 0) {
                            Spacer(modifier = Modifier.height(16.dp))
                        }
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
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = { enabled = !enabled }) {
            Text(text = "Play")
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun AnimationSpecTweenSamplePreview() {
    AnimationSpecTweenSample(remember { MutableStateFlow(true) })
}