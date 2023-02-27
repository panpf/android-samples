package com.github.panpf.android.compose.samples.ui.animation

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateInt
import androidx.compose.animation.core.animateOffset
import androidx.compose.animation.core.animateSize
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
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

class UpdateTransitionFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "updateTransition"
    }

    @Composable
    override fun DrawContent() {
        val desc = """
            Transition 可管理一个或多个动画作为其子项，并在多个状态之间同时运行这些动画。
            您可以使用某个 animate* 扩展函数来定义此过渡效果中的子动画。
            对于涉及多个可组合函数的更复杂的过渡，可使用 createChildTransition 来创建子过渡
        """.trimIndent()
        ExpandableLayout { allExpandFlow ->
            ExpandableText(text = desc, modifier = Modifier.padding(20.dp))
            UpdateTransitionSample(allExpandFlow)
            // todo createChildTransition
        }
    }
}

@Composable
private fun UpdateTransitionSample(allExpandFlow: Flow<Boolean>) {
    var enabled by remember { mutableStateOf(true) }
    val transition = updateTransition(targetState = enabled, label = "UpdateTransitionSample")
    val background by transition.animateColor(label = "background") {
        if (it) MyColor.HalfBlue else MyColor.HalfMagenta
    }
    val size by transition.animateSize(label = "size") {
        if (it) Size(50f, 50f) else Size(70f, 40f)
    }
    val roundedCornerPercent by transition.animateInt(label = "roundedCornerPercent") {
        if (it) 50 else 20
    }
    val offset by transition.animateOffset(label = "offset") {
        if (it) Offset(30f, 10f) else Offset(10f, 30f)
    }
    ExpandableItem3(title = "updateTransition", allExpandFlow, padding = 20.dp) {
        Box(
            modifier = Modifier
                .border(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.5f))
                .size(100.dp)
        ) {
            Box(
                modifier = Modifier
                    .offset(offset.x.dp, offset.y.dp)
                    .clip(RoundedCornerShape(roundedCornerPercent))
                    .background(background)
                    .size(size.width.dp, size.height.dp)
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
        Text(text = "size: (width=${size.width.toInt()}.dp, height=${size.height.toInt()}.dp)")
        Text(text = "RoundedCornerShape percent: $roundedCornerPercent")
        Text(text = "offset: (x=${offset.x.toInt()}.dp, y=${offset.y.toInt()}.dp)")
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = { enabled = !enabled }) {
            Text(text = "Change All")
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun UpdateTransitionSamplePreview() {
    UpdateTransitionSample(remember { MutableStateFlow(true) })
}