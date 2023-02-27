package com.github.panpf.android.compose.samples.ui.animation

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.panpf.android.compose.samples.R
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem3
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.ExpandableText
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class AnimatedContentSizeFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "animatedContentSize"
    }

    @Composable
    override fun DrawContent() {
        val desc = """
            animateContentSize 修饰符可为大小变化添加动画效果。
            animateContentSize 在修饰符链中的位置顺序很重要。为了确保流畅的动画，请务必将其放置在任何大小修饰符（如 size 或 defaultMinSize）前面。
            EnterTransition 定义了目标内容应如何显示，ExitTransition 则定义了初始内容应如何消失。
            SizeTransform 定义了大小应如何在初始内容与目标内容之间添加动画效果。
            就像 AnimatedVisibility 一样，animateEnterExit 修饰符可以在 AnimatedContent 的内容 lambda 内使用。使用此修饰符可将 EnterAnimation 和 ExitAnimation 分别应用于每个直接或间接子项。
        """.trimIndent()
        ExpandableLayout { allExpandFlow ->
            ExpandableText(text = desc, modifier = Modifier.padding(20.dp))
            AnimatedContentSizeSample(allExpandFlow)
        }
    }
}


@Composable
private fun AnimatedContentSizeSample(allExpandFlow: Flow<Boolean>) {
    var size by remember { mutableStateOf(60) }
    ExpandableItem3(title = "animatedContentSize", allExpandFlow, padding = 20.dp) {
        Box(
            modifier = Modifier
                .border(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.5f))
                .size(120.dp)
        ) {
            Box(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.primary)
                    .align(Alignment.Center)
                    .animateContentSize()
                    .size(size.dp)
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row {
            FilledIconButton(onClick = {
                size = (size + 20).coerceAtMost(120)
            }) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "add"
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            FilledIconButton(onClick = {
                size = (size - 20).coerceAtLeast(20)
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_subtract),
                    contentDescription = "subtract"
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun AnimatedContentSizeSamplePreview() {
    AnimatedContentSizeSample(remember { MutableStateFlow(true) })
}