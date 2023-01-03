package com.github.panpf.android.compose.samples.ui.gestures

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.panpf.android.compose.samples.R
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem3
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ScrollFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "Scroll"
    }

    @Composable
    override fun DrawContent() {
        ExpandableLayout { allExpandFlow ->
            ScrollVerticalScrollSample(allExpandFlow)
            ScrollHorizontalScrollSample(allExpandFlow)
            // todo ScrollScrollableSample(allExpandFlow)
            // todo ScrollNestedScrollSample(allExpandFlow)
        }
    }
}


private const val text =
    "躲过了暴风雪之后，我们再次起程赶路，在一处斜坡下发现了阿宁他们的马队，同时也发现了海底墓穴影画之中的那一座神秘雪山，赫然出现在了我们的视野尽头。就在我们询问向导如何才能到达那里的时候，顺子却摇头，说我们绝对无法过去。----摘自《盗墓笔记》 - 云顶天宫（下）第一章 五圣雪山，网址：http://www.daomubiji.com/yun-ding-tian-gong-15.html"


@Composable
private fun ScrollVerticalScrollSample(allExpandFlow: Flow<Boolean>) {
    val coroutineScope = rememberCoroutineScope()
    val scrollState = rememberScrollState()
    ExpandableItem3(
        title = "Scroll（verticalScroll）",
        allExpandFlow,
        padding = 20.dp,
    ) {
        Text(text = "reverseScrolling=false")
        Text(
            text = text,
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.5f))
                .verticalScroll(scrollState, reverseScrolling = false)
        )

        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "reverseScrolling=true")
        Text(
            text = text,
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.5f))
                .verticalScroll(scrollState, reverseScrolling = true)
        )

        Spacer(modifier = Modifier.height(20.dp))
        Column(modifier = Modifier.align(CenterHorizontally)) {
            IconButton(onClick = {
                coroutineScope.launch {
                    scrollState.animateScrollBy(-100f)
                }
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_up),
                    contentDescription = "up"
                )
            }
            Text(text = "${scrollState.value}", modifier = Modifier.align(CenterHorizontally))
            IconButton(onClick = {
                coroutineScope.launch {
                    scrollState.animateScrollBy(100f)
                }
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_down),
                    contentDescription = "down"
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun ScrollVerticalScrollSamplePreview() {
    ScrollVerticalScrollSample(remember { MutableStateFlow(true) })
}


@Composable
private fun ScrollHorizontalScrollSample(allExpandFlow: Flow<Boolean>) {
    val coroutineScope = rememberCoroutineScope()
    val scrollState = rememberScrollState()
    ExpandableItem3(
        title = "Scroll（horizontalScroll）",
        allExpandFlow,
        padding = 20.dp,
    ) {
        Text(text = "reverseScrolling=false")
        Text(
            text = text,
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.5f))
                .horizontalScroll(scrollState, reverseScrolling = false)
        )

        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "reverseScrolling=true")
        Text(
            text = text,
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.5f))
                .horizontalScroll(scrollState, reverseScrolling = true)
        )

        Spacer(modifier = Modifier.height(20.dp))
        Row(modifier = Modifier.align(CenterHorizontally)) {
            IconButton(onClick = {
                coroutineScope.launch {
                    scrollState.animateScrollBy(-100f)
                }
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_left),
                    contentDescription = "left"
                )
            }
            Text(text = "${scrollState.value}", modifier = Modifier.align(CenterVertically))
            IconButton(onClick = {
                coroutineScope.launch {
                    scrollState.animateScrollBy(100f)
                }
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_right),
                    contentDescription = "right"
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun ScrollHorizontalScrollSamplePreview() {
    ScrollHorizontalScrollSample(remember { MutableStateFlow(true) })
}