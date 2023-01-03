package com.github.panpf.android.compose.samples.ui.base

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.panpf.android.compose.samples.R
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun ExpandableLayout(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.(allExpandFlow: MutableStateFlow<Boolean>) -> Unit
) {
    val allExpandFlow = remember { MutableStateFlow(true) }
    Column(
        modifier.then(
            Modifier
                .verticalScroll(rememberScrollState())
                .padding(bottom = 100.dp)
        )
    ) {
        content(allExpandFlow)
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .height(DividerDefaults.Thickness)
                .padding(start = 20.dp, end = 20.dp)
        )
    }
    Box(modifier = Modifier.fillMaxSize()) {
        FloatingActionButton(
            onClick = { allExpandFlow.value = !allExpandFlow.value },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(20.dp)
        ) {
            val allExpand by allExpandFlow.collectAsState()
            val iconId =
                if (allExpand) R.drawable.ic_arrow_up else R.drawable.ic_arrow_down
            Image(painterResource(id = iconId), contentDescription = "")
        }
    }
}

@Composable
fun BaseExpandableItem(
    title: String,
    allExpand: Flow<Boolean>,
    color: Color,
    padding: Dp = 0.dp,
    desc: String? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    val expandedBgColor = color.copy(0.3f)
    Column(modifier = Modifier.fillMaxWidth()) {
        val selfExpand = remember { mutableStateOf(true) }
        LaunchedEffect(allExpand) {
            allExpand.collect {
                selfExpand.value = it
            }
        }
        val expand = selfExpand.value

        if (!expand) {
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(DividerDefaults.Thickness)
                    .padding(start = 20.dp, end = 20.dp)
            )
        }

        Row(modifier = Modifier
            .fillMaxWidth()
            .clickable {
                selfExpand.value = !selfExpand.value
            }
            .background(if (expand) expandedBgColor else Color.Transparent)
            .padding(start = 20.dp, end = 20.dp, top = 16.dp, bottom = 20.dp)
        ) {
            Text(
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f)
            )

            val iconId = if (expand) R.drawable.ic_arrow_up else R.drawable.ic_arrow_down
            Image(painterResource(id = iconId), contentDescription = "")
        }

        AnimatedVisibility(visible = expand) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(padding)
            ) {
                if (desc != null) {
                    ExpandableText(text = desc)
                    Spacer(modifier = Modifier.height(20.dp))
                }
                content()
            }
        }
    }
}

@Composable
fun ExpandableItem(
    title: String,
    allExpand: Flow<Boolean>,
    padding: Dp = 0.dp,
    desc: String? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    val primaryColor = androidx.compose.material.MaterialTheme.colors.primary
    BaseExpandableItem(title, allExpand, primaryColor, padding, desc, content)
}

@Composable
fun ExpandableItem3(
    title: String,
    allExpand: Flow<Boolean>,
    padding: Dp = 0.dp,
    desc: String? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    val primaryColor = androidx.compose.material3.MaterialTheme.colorScheme.primary
    BaseExpandableItem(title, allExpand, primaryColor, padding, desc, content)
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun ExpandableLayoutPreview() {
    ExpandableLayout { allExpandFlow ->
        ExpandableItem(title = "标题1", allExpandFlow) {
            Text(text = "躲过了暴风雪之后，我们再次起程赶路，在一处斜坡下发现了阿宁他们的马队，同时也发现了海底墓穴影画之中的那一座神秘雪山，赫然出现在了我们的视野尽头。就在我们询问向导如何才能到达那里的时候，顺子却摇头，说我们绝对无法过去。\n          ----摘自《盗墓笔记》 - 云顶天宫（下）第一章 五圣雪山，网址：http://www.daomubiji.com/yun-ding-tian-gong-15.html")
        }
        ExpandableItem(title = "标题2", allExpandFlow) {
            Text(text = "躲过了暴风雪之后，我们再次起程赶路，在一处斜坡下发现了阿宁他们的马队，同时也发现了海底墓穴影画之中的那一座神秘雪山，赫然出现在了我们的视野尽头。就在我们询问向导如何才能到达那里的时候，顺子却摇头，说我们绝对无法过去。\n          ----摘自《盗墓笔记》 - 云顶天宫（下）第一章 五圣雪山，网址：http://www.daomubiji.com/yun-ding-tian-gong-15.html")
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun ExpandableItemPreview() {
    val allExpandFlow = remember { MutableStateFlow(true) }
    ExpandableItem(title = "标题", allExpandFlow) {
        Text(text = "躲过了暴风雪之后，我们再次起程赶路，在一处斜坡下发现了阿宁他们的马队，同时也发现了海底墓穴影画之中的那一座神秘雪山，赫然出现在了我们的视野尽头。就在我们询问向导如何才能到达那里的时候，顺子却摇头，说我们绝对无法过去。\n          ----摘自《盗墓笔记》 - 云顶天宫（下）第一章 五圣雪山，网址：http://www.daomubiji.com/yun-ding-tian-gong-15.html")
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun ExpandableItem3Preview() {
    val allExpandFlow = remember { MutableStateFlow(true) }
    ExpandableItem3(title = "标题", allExpandFlow) {
        Text(text = "躲过了暴风雪之后，我们再次起程赶路，在一处斜坡下发现了阿宁他们的马队，同时也发现了海底墓穴影画之中的那一座神秘雪山，赫然出现在了我们的视野尽头。就在我们询问向导如何才能到达那里的时候，顺子却摇头，说我们绝对无法过去。\n          ----摘自《盗墓笔记》 - 云顶天宫（下）第一章 五圣雪山，网址：http://www.daomubiji.com/yun-ding-tian-gong-15.html")
    }
}