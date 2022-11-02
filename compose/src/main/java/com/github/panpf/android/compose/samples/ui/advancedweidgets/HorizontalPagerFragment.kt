package com.github.panpf.android.compose.samples.ui.advancedweidgets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.panpf.android.compose.samples.R
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.ToolbarFragment
import com.github.panpf.android.compose.samples.ui.base.theme.MyTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class HorizontalPagerFragment : ToolbarFragment() {

    override fun createView(
        toolbar: Toolbar,
        inflater: LayoutInflater,
        parent: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        toolbar.title = "HorizontalPager"
        return ComposeView(inflater.context).apply {
            setContent {
                MyTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        ExpandableLayout { allExpandFlow ->
                            HorizontalPagerSample(allExpandFlow)
                            HorizontalPagerReverseLayoutSample(allExpandFlow)
                            HorizontalPagerItemSpacingSample(allExpandFlow)
                            HorizontalPagerContentPaddingSample(allExpandFlow)
                            HorizontalPagerVerticalAlignmentSample(allExpandFlow)
                            HorizontalPagerScrollToPageSample(allExpandFlow)
                            HorizontalPagerAnimateScrollToPageSample(allExpandFlow)
                            HorizontalPagerScrollInProgressSample(allExpandFlow)
                            HorizontalPagerCurrentPageSample(allExpandFlow)
                        }
                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun HorizontalPagerSample(allExpandFlow: Flow<Boolean>) {
    val colors = remember {
        listOf(Color.Blue, Color.Magenta, Color.Cyan, Color.Red, Color.Yellow, Color.Green)
            .map { it.copy(alpha = 0.5f) }
    }
    val items = listOf("数码", "汽车", "摄影", "舞蹈", "二次元", "音乐", "科技", "健身")
        .mapIndexed { index, string -> "${index + 1}. $string" }
    ExpandableItem(title = "HorizontalPager", allExpandFlow, padding = 20.dp) {
        HorizontalPager(
            count = items.size,
            modifier = Modifier
                .height(200.dp)
                .border(2.dp, Color.Red)
                .padding(2.dp),
        ) { index ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(colors[index % colors.size])
            ) {
                Text(
                    text = items[index],
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun HorizontalPagerSamplePreview() {
    HorizontalPagerSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun HorizontalPagerReverseLayoutSample(allExpandFlow: Flow<Boolean>) {
    val colors = remember {
        listOf(Color.Blue, Color.Magenta, Color.Cyan, Color.Red, Color.Yellow, Color.Green)
            .map { it.copy(alpha = 0.5f) }
    }
    val items = listOf("数码", "汽车", "摄影", "舞蹈", "二次元", "音乐", "科技", "健身")
        .mapIndexed { index, string -> "${index + 1}. $string" }
    ExpandableItem(title = "HorizontalPager（reverseLayout）", allExpandFlow, padding = 20.dp) {
        HorizontalPager(
            count = items.size,
            modifier = Modifier
                .height(200.dp)
                .border(2.dp, Color.Red)
                .padding(2.dp),
            reverseLayout = true
        ) { index ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(colors[index % colors.size])
            ) {
                Text(
                    text = items[index],
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun HorizontalPagerReverseLayoutSamplePreview() {
    HorizontalPagerReverseLayoutSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun HorizontalPagerItemSpacingSample(allExpandFlow: Flow<Boolean>) {
    val colors = remember {
        listOf(Color.Blue, Color.Magenta, Color.Cyan, Color.Red, Color.Yellow, Color.Green)
            .map { it.copy(alpha = 0.5f) }
    }
    val items = listOf("数码", "汽车", "摄影", "舞蹈", "二次元", "音乐", "科技", "健身")
        .mapIndexed { index, string -> "${index + 1}. $string" }
    ExpandableItem(title = "HorizontalPager（itemSpacing）", allExpandFlow, padding = 20.dp) {
        HorizontalPager(
            count = items.size,
            modifier = Modifier
                .height(200.dp)
                .border(2.dp, Color.Red)
                .padding(2.dp),
            itemSpacing = 20.dp
        ) { index ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(colors[index % colors.size])
            ) {
                Text(
                    text = items[index],
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun HorizontalPagerItemSpacingSamplePreview() {
    HorizontalPagerItemSpacingSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun HorizontalPagerContentPaddingSample(allExpandFlow: Flow<Boolean>) {
    val colors = remember {
        listOf(Color.Blue, Color.Magenta, Color.Cyan, Color.Red, Color.Yellow, Color.Green)
            .map { it.copy(alpha = 0.5f) }
    }
    val items = listOf("数码", "汽车", "摄影", "舞蹈", "二次元", "音乐", "科技", "健身")
        .mapIndexed { index, string -> "${index + 1}. $string" }
    ExpandableItem(title = "HorizontalPager（contentPadding）", allExpandFlow, padding = 20.dp) {
        HorizontalPager(
            count = items.size,
            modifier = Modifier
                .height(200.dp)
                .border(2.dp, Color.Red)
                .padding(2.dp),
            contentPadding = PaddingValues(20.dp),
        ) { index ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(colors[index % colors.size])
            ) {
                Text(
                    text = items[index],
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun HorizontalPagerContentPaddingSamplePreview() {
    HorizontalPagerContentPaddingSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun HorizontalPagerVerticalAlignmentSample(allExpandFlow: Flow<Boolean>) {
    val colors = remember {
        listOf(Color.Blue, Color.Magenta, Color.Cyan, Color.Red, Color.Yellow, Color.Green)
            .map { it.copy(alpha = 0.5f) }
    }
    val items = listOf("数码", "汽车", "摄影", "舞蹈", "二次元", "音乐", "科技", "健身")
        .mapIndexed { index, string -> "${index + 1}. $string" }
    ExpandableItem(title = "HorizontalPager（verticalAlignment）", allExpandFlow, padding = 20.dp) {
        Column(
            modifier = Modifier.fillMaxWidth(),
        ) {
            listOf(
                Alignment.Top to "Top",
                Alignment.CenterVertically to "CenterVertically",
                Alignment.Bottom to "Bottom",
            ).forEach {
                Column {
                    Spacer(modifier = Modifier.size(10.dp))
                    Text(text = it.second)
                    HorizontalPager(
                        count = items.size,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp)
                            .border(2.dp, Color.Red)
                            .padding(2.dp),
                        verticalAlignment = it.first
                    ) { index ->
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(60.dp)
                                .background(colors[index % colors.size])
                        ) {
                            Text(
                                text = items[index],
                                modifier = Modifier
                                    .align(Alignment.Center)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun HorizontalPagerVerticalAlignmentSamplePreview() {
    HorizontalPagerVerticalAlignmentSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun HorizontalPagerScrollToPageSample(allExpandFlow: Flow<Boolean>) {
    val colors = remember {
        listOf(Color.Blue, Color.Magenta, Color.Cyan, Color.Red, Color.Yellow, Color.Green)
            .map { it.copy(alpha = 0.5f) }
    }
    val items = listOf("数码", "汽车", "摄影", "舞蹈", "二次元", "音乐", "科技", "健身")
        .mapIndexed { index, string -> "${index + 1}. $string" }
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()
    ExpandableItem(title = "HorizontalPager（scrollToPage）", allExpandFlow, padding = 20.dp) {
        Column {
            Row(modifier = Modifier.height(200.dp)) {
                IconButton(
                    onClick = {
                        val currentPage = pagerState.currentPage
                        if (currentPage > 0) {
                            coroutineScope.launch {
                                pagerState.scrollToPage(currentPage - 1)
                            }
                        }
                    },
                    modifier = Modifier
                        .width(46.dp)
                        .fillMaxHeight()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_navigate_before),
                        contentDescription = "before"
                    )
                }
                HorizontalPager(
                    count = items.size,
                    state = pagerState,
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f)
                        .border(2.dp, Color.Red)
                        .padding(2.dp),
                ) { index ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(colors[index % colors.size])
                    ) {
                        Text(
                            text = items[index],
                            modifier = Modifier
                                .align(Alignment.Center)
                        )
                    }
                }
                IconButton(
                    onClick = {
                        val currentPage = pagerState.currentPage
                        if (currentPage < items.size - 1) {
                            coroutineScope.launch {
                                pagerState.scrollToPage(currentPage + 1)
                            }
                        }
                    },
                    modifier = Modifier
                        .width(46.dp)
                        .fillMaxHeight()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_navigate_next),
                        contentDescription = "next",
                    )
                }
            }
            Row(modifier = Modifier.padding(start = 46.dp, end = 46.dp)) {
                repeat(items.size) { index ->
                    Box(modifier = Modifier
                        .height(40.dp)
                        .weight(1f)
                        .clickable {
                            coroutineScope.launch {
                                pagerState.scrollToPage(index)
                            }
                        }
                    ) {
                        Text(
                            text = (index + 1).toString(),
                            modifier = Modifier.align(Alignment.Center),
                            color = Color.Blue,
                            textDecoration = TextDecoration.Underline
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun HorizontalPagerScrollToPageSamplePreview() {
    HorizontalPagerScrollToPageSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun HorizontalPagerAnimateScrollToPageSample(allExpandFlow: Flow<Boolean>) {
    val colors = remember {
        listOf(Color.Blue, Color.Magenta, Color.Cyan, Color.Red, Color.Yellow, Color.Green)
            .map { it.copy(alpha = 0.5f) }
    }
    val items = listOf("数码", "汽车", "摄影", "舞蹈", "二次元", "音乐", "科技", "健身")
        .mapIndexed { index, string -> "${index + 1}. $string" }
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()
    ExpandableItem(title = "HorizontalPager（animateScrollToPage）", allExpandFlow, padding = 20.dp) {
        Column {
            Row(modifier = Modifier.height(200.dp)) {
                IconButton(
                    onClick = {
                        val currentPage = pagerState.currentPage
                        if (currentPage > 0) {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(currentPage - 1)
                            }
                        }
                    },
                    modifier = Modifier
                        .width(46.dp)
                        .fillMaxHeight()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_navigate_before),
                        contentDescription = "before"
                    )
                }
                HorizontalPager(
                    count = items.size,
                    state = pagerState,
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f)
                        .border(2.dp, Color.Red)
                        .padding(2.dp),
                ) { index ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(colors[index % colors.size])
                    ) {
                        Text(
                            text = items[index],
                            modifier = Modifier
                                .align(Alignment.Center)
                        )
                    }
                }
                IconButton(
                    onClick = {
                        val currentPage = pagerState.currentPage
                        if (currentPage < items.size - 1) {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(currentPage + 1)
                            }
                        }
                    },
                    modifier = Modifier
                        .width(46.dp)
                        .fillMaxHeight()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_navigate_next),
                        contentDescription = "next",
                    )
                }
            }
            Row(modifier = Modifier.padding(start = 46.dp, end = 46.dp)) {
                repeat(items.size) { index ->
                    Box(modifier = Modifier
                        .height(40.dp)
                        .weight(1f)
                        .clickable {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        }
                    ) {
                        Text(
                            text = (index + 1).toString(),
                            modifier = Modifier.align(Alignment.Center),
                            color = Color.Blue,
                            textDecoration = TextDecoration.Underline
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun HorizontalPagerAnimateScrollToPageSamplePreview() {
    HorizontalPagerAnimateScrollToPageSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun HorizontalPagerScrollInProgressSample(allExpandFlow: Flow<Boolean>) {
    val colors = remember {
        listOf(Color.Blue, Color.Magenta, Color.Cyan, Color.Red, Color.Yellow, Color.Green)
            .map { it.copy(alpha = 0.5f) }
    }
    val items = listOf("数码", "汽车", "摄影", "舞蹈", "二次元", "音乐", "科技", "健身")
        .mapIndexed { index, string -> "${index + 1}. $string" }
    val pagerState = rememberPagerState()
    val scrollInProgressState = remember { derivedStateOf { pagerState.isScrollInProgress } }
    ExpandableItem(title = "HorizontalPager（isScrollInProgress）", allExpandFlow, padding = 20.dp) {
        Column {
            HorizontalPager(
                count = items.size,
                state = pagerState,
                modifier = Modifier
                    .height(200.dp)
                    .border(2.dp, Color.Red)
                    .padding(2.dp),
            ) { index ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(colors[index % colors.size])
                ) {
                    Text(
                        text = items[index],
                        modifier = Modifier
                            .align(Alignment.Center)
                    )
                }
            }
            Text(text = "isScrollInProgress: ${scrollInProgressState.value}")
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun HorizontalPagerScrollInProgressSamplePreview() {
    HorizontalPagerScrollInProgressSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun HorizontalPagerCurrentPageSample(allExpandFlow: Flow<Boolean>) {
    val colors = remember {
        listOf(Color.Blue, Color.Magenta, Color.Cyan, Color.Red, Color.Yellow, Color.Green)
            .map { it.copy(alpha = 0.5f) }
    }
    val items = listOf("数码", "汽车", "摄影", "舞蹈", "二次元", "音乐", "科技", "健身")
        .mapIndexed { index, string -> "${index + 1}. $string" }
    val pagerState = rememberPagerState()
    val currentPageState = remember { derivedStateOf { pagerState.currentPage } }
    val currentPageOffsetState = remember { derivedStateOf { pagerState.currentPageOffset } }
    ExpandableItem(title = "HorizontalPager（currentPage）", allExpandFlow, padding = 20.dp) {
        Column {
            HorizontalPager(
                count = items.size,
                state = pagerState,
                modifier = Modifier
                    .height(200.dp)
                    .border(2.dp, Color.Red)
                    .padding(2.dp),
            ) { index ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(colors[index % colors.size])
                ) {
                    Text(
                        text = items[index],
                        modifier = Modifier
                            .align(Alignment.Center)
                    )
                }
            }
            Text(text = "currentPage: ${currentPageState.value}")
            Text(text = "currentPageOffset: ${currentPageOffsetState.value}")
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun HorizontalPagerCurrentPageSamplePreview() {
    HorizontalPagerCurrentPageSample(remember { MutableStateFlow(true) })
}