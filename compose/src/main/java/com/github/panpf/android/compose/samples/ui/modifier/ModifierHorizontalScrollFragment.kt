package com.github.panpf.android.compose.samples.ui.modifier

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.panpf.android.compose.samples.ui.base.DescItem
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem3
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
import com.google.accompanist.flowlayout.FlowRow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class ModifierHorizontalScrollFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "Modifier - horizontalScroll"
    }

    @Composable
    override fun DrawContent() {
        ExpandableLayout { allExpandFlow ->
            ModifierHorizontalScrollSample(allExpandFlow)
            ModifierHorizontalScrollReverseScrollingSample(allExpandFlow)
        }
    }
}

private const val text =
    "躲过了暴风雪之后，我们再次起程赶路，在一处斜坡下发现了阿宁他们的马队，同时也发现了海底墓穴影画之中的那一座神秘雪山，赫然出现在了我们的视野尽头。就在我们询问向导如何才能到达那里的时候，顺子却摇头，说我们绝对无法过去。\n          ----摘自《盗墓笔记》 - 云顶天宫（下）第一章 五圣雪山，网址：http://www.daomubiji.com/yun-ding-tian-gong-15.html"


@Composable
private fun ModifierHorizontalScrollSample(allExpandFlow: Flow<Boolean>) {
    val scrollState = rememberScrollState()
    ExpandableItem3(title = "Modifier（horizontalScroll）", allExpandFlow, padding = 20.dp) {
        val colorScheme = MaterialTheme.colorScheme
        FlowRow(mainAxisSpacing = 10.dp, crossAxisSpacing = 10.dp) {
            DescItem("non rollable") {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .background(colorScheme.primaryContainer)
                ) {
                    Text(text = text)
                }
            }

            DescItem("horizontalScroll") {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .background(colorScheme.primaryContainer)
                        .horizontalScroll(scrollState)
                ) {
                    Text(text = text)
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun ModifierHorizontalScrollSamplePreview() {
    ModifierHorizontalScrollSample(remember { MutableStateFlow(true) })
}


@Composable
private fun ModifierHorizontalScrollReverseScrollingSample(allExpandFlow: Flow<Boolean>) {
    val scrollState = rememberScrollState()
    val reverseScrollState = rememberScrollState()
    ExpandableItem3(
        title = "Modifier（horizontalScroll - reverseScrolling）",
        allExpandFlow,
        padding = 20.dp
    ) {
        val colorScheme = MaterialTheme.colorScheme
        FlowRow(mainAxisSpacing = 10.dp, crossAxisSpacing = 10.dp) {
            DescItem("horizontalScroll") {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .background(colorScheme.primaryContainer)
                        .horizontalScroll(scrollState)
                ) {
                    Text(text = text)
                }
            }

            DescItem("horizontalScroll - reverseScrolling") {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .background(colorScheme.primaryContainer)
                        .horizontalScroll(reverseScrollState, reverseScrolling = true)
                ) {
                    Text(text = text)
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun ModifierHorizontalScrollReverseScrollingSamplePreview() {
    ModifierHorizontalScrollReverseScrollingSample(remember { MutableStateFlow(true) })
}