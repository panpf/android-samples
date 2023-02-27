package com.github.panpf.android.compose.samples.ui.animation

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem3
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.ExpandableText
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

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
        """.trimMargin()
        ExpandableLayout { allExpandFlow ->
            ExpandableText(text = desc, modifier = Modifier.padding(20.dp))
            AnimatableAnimateToSample(allExpandFlow)
            // todo animateDecay
            // todo snapTo
        }
    }
}

@Composable
private fun AnimatableAnimateToSample(allExpandFlow: Flow<Boolean>) {
    val animatable = remember { Animatable(initialValue = Color.Red) }
    val coroutineScope = rememberCoroutineScope()
    ExpandableItem3(title = "Animatable - animateTo", allExpandFlow, padding = 20.dp) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .background(color = animatable.value)
                .align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.size(20.dp))
        Row(
            Modifier
                .width(300.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            val colorList = remember {
                listOf(
                    Color.Black,
                    Color.Blue,
                    Color.DarkGray,
                    Color.Cyan,
                    Color.Gray,
                    Color.Green,
                    Color.Magenta,
                    Color.LightGray,
                    Color.Yellow
                )
            }
            colorList.forEachIndexed { index, color ->
                if (index > 0) {
                    Spacer(modifier = Modifier.size(4.dp))
                }
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .aspectRatio(1f)
                        .background(color = color)
                        .clickable {
                            coroutineScope.launch {
                                animatable.animateTo(
                                    targetValue = color,
                                    animationSpec = tween(durationMillis = 2000)
                                )
                            }
                        }
                )
            }
        }
        Spacer(modifier = Modifier.size(20.dp))
        Text(text = "点击切换颜色")
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun AnimatableAnimateToSamplePreview() {
    AnimatableAnimateToSample(remember { MutableStateFlow(true) })
}