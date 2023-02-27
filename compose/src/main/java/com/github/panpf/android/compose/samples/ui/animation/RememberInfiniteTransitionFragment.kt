package com.github.panpf.android.compose.samples.ui.animation

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateValue
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem3
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.ExpandableText
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
import com.github.panpf.android.compose.samples.ui.base.MyColor
import com.github.panpf.tools4j.math.ktx.format
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class RememberInfiniteTransitionFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "rememberInfiniteTransition"
    }

    @Composable
    override fun DrawContent() {
        val desc = """
            InfiniteTransition 可以像 Transition 一样保存一个或多个子动画，但是，这些动画一进入组合阶段就开始运行，除非被移除，否则不会停止。
            您可以使用 rememberInfiniteTransition 创建 InfiniteTransition 实例。
        """.trimIndent()
        ExpandableLayout { allExpandFlow ->
            ExpandableText(text = desc, modifier = Modifier.padding(20.dp))
            RememberInfiniteTransitionSample(allExpandFlow)
        }
    }
}

@Composable
private fun RememberInfiniteTransitionSample(allExpandFlow: Flow<Boolean>) {
    val transition = rememberInfiniteTransition()
    val background by transition.animateColor(
        initialValue = MyColor.HalfBlue,
        targetValue = MyColor.HalfMagenta,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )
    val size by transition.animateFloat(
        initialValue = 50f,
        targetValue = 80f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )
    val roundedCornerPercent by transition.animateValue(
        initialValue = 50,
        targetValue = 20,
        typeConverter = Int.VectorConverter,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )
    ExpandableItem3(title = "rememberInfiniteTransition", allExpandFlow, padding = 20.dp) {
        Box(
            modifier = Modifier
                .border(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.5f))
                .size(100.dp)
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(roundedCornerPercent))
                    .background(background)
                    .size(size.dp, size.dp)
                    .align(Alignment.Center)
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "background: (" +
                    "red=${background.red.format("", 1)}, " +
                    "green=${background.green.format("", 1)}, " +
                    "blue=${background.blue.format("", 1)}" +
                    ")"
        )
        Text(text = "size: (width=${size.toInt()}.dp, height=${size.toInt()}.dp)")
        Text(text = "RoundedCornerShape percent: $roundedCornerPercent")
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun RememberInfiniteTransitionSamplePreview() {
    RememberInfiniteTransitionSample(remember { MutableStateFlow(true) })
}