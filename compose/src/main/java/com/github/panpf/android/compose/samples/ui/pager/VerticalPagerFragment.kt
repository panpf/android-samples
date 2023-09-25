package com.github.panpf.android.compose.samples.ui.pager

import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.panpf.android.compose.samples.R
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem3
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
import com.github.panpf.android.compose.samples.ui.base.MyColor
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.VerticalPagerIndicator
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class VerticalPagerFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "VerticalPager"
    }

    @Composable
    override fun DrawContent() {
        ExpandableLayout { allExpandFlow ->
            VerticalPagerSample(allExpandFlow)
            VerticalPagerReverseLayoutSample(allExpandFlow)
            VerticalPagerPageSpacingSample(allExpandFlow)
            VerticalPagerContentPaddingSample(allExpandFlow)
            VerticalPagerPageSizeSample(allExpandFlow)
            VerticalPagerBeyondBoundsPageCountSample(allExpandFlow)
            VerticalPagerHorizontalAlignmentHorSample(allExpandFlow)
            VerticalPagerScrollToPageSample(allExpandFlow)
            VerticalPagerAnimateScrollToPageSample(allExpandFlow)
            VerticalPagerScrollInProgressSample(allExpandFlow)
            VerticalPagerCurrentPageSample(allExpandFlow)
            VerticalPagerIndicatorSample(allExpandFlow)
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun VerticalPagerSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyColor.halfRainbows
    val items = listOf("数码", "汽车", "摄影", "舞蹈", "二次元", "音乐", "科技", "健身")
        .mapIndexed { index, string -> "${index + 1}. $string" }
    ExpandableItem3(title = "VerticalPager", allExpandFlow, padding = 20.dp) {
        VerticalPager(
            state = rememberPagerState {
                items.size
            },
            modifier = Modifier
                .width(200.dp)
                .height(300.dp)
                .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
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
private fun VerticalPagerSamplePreview() {
    VerticalPagerSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun VerticalPagerReverseLayoutSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyColor.halfRainbows
    val items = listOf("数码", "汽车", "摄影", "舞蹈", "二次元", "音乐", "科技", "健身")
        .mapIndexed { index, string -> "${index + 1}. $string" }
    ExpandableItem3(title = "VerticalPager（reverseLayout）", allExpandFlow, padding = 20.dp) {
        VerticalPager(
            state = rememberPagerState {
                items.size
            },
            modifier = Modifier
                .width(200.dp)
                .height(300.dp)
                .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
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
private fun VerticalPagerReverseLayoutSamplePreview() {
    VerticalPagerReverseLayoutSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun VerticalPagerPageSpacingSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyColor.halfRainbows
    val items = listOf("数码", "汽车", "摄影", "舞蹈", "二次元", "音乐", "科技", "健身")
        .mapIndexed { index, string -> "${index + 1}. $string" }
    val pageSpacingState = remember { mutableStateOf(20.dp) }
    val pageSpacingRange = 5.dp..35.dp
    val pageSpacingStep = 5.dp
    ExpandableItem3(title = "VerticalPager（pageSpacing）", allExpandFlow, padding = 20.dp) {
        VerticalPager(
            state = rememberPagerState {
                items.size
            },
            modifier = Modifier
                .width(200.dp)
                .height(300.dp)
                .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                .padding(2.dp),
            pageSpacing = pageSpacingState.value
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
        Row(modifier = Modifier.fillMaxWidth()) {
            IconButton(
                onClick = {
                    pageSpacingState.value =
                        (pageSpacingState.value - pageSpacingStep).coerceIn(pageSpacingRange)
                }, modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
            ) {
                Icon(
                    painterResource(id = R.drawable.ic_subtract),
                    contentDescription = "subtract"
                )
            }
            Text(
                text = "${pageSpacingState.value}",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
            )
            IconButton(
                onClick = {
                    pageSpacingState.value =
                        (pageSpacingState.value + pageSpacingStep).coerceIn(pageSpacingRange)
                }, modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "add"
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun VerticalPagerPageSpacingSamplePreview() {
    VerticalPagerPageSpacingSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun VerticalPagerContentPaddingSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyColor.halfRainbows
    val items = listOf("数码", "汽车", "摄影", "舞蹈", "二次元", "音乐", "科技", "健身")
        .mapIndexed { index, string -> "${index + 1}. $string" }
    val contentPaddingState = remember { mutableStateOf(20.dp) }
    val contentPaddingRange = 5.dp..35.dp
    val contentPaddingStep = 5.dp
    ExpandableItem3(title = "VerticalPager（contentPadding）", allExpandFlow, padding = 20.dp) {
        VerticalPager(
            state = rememberPagerState {
                items.size
            },
            modifier = Modifier
                .width(200.dp)
                .height(300.dp)
                .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                .padding(2.dp),
            contentPadding = PaddingValues(contentPaddingState.value),
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
        Row(modifier = Modifier.fillMaxWidth()) {
            IconButton(
                onClick = {
                    contentPaddingState.value =
                        (contentPaddingState.value - contentPaddingStep).coerceIn(
                            contentPaddingRange
                        )
                }, modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
            ) {
                Icon(
                    painterResource(id = R.drawable.ic_subtract),
                    contentDescription = "subtract"
                )
            }
            Text(
                text = "${contentPaddingState.value}",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
            )
            IconButton(
                onClick = {
                    contentPaddingState.value =
                        (contentPaddingState.value + contentPaddingStep).coerceIn(
                            contentPaddingRange
                        )
                }, modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "add"
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun VerticalPagerContentPaddingSamplePreview() {
    VerticalPagerContentPaddingSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun VerticalPagerPageSizeSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyColor.halfRainbows
    val items = listOf("数码", "汽车", "摄影", "舞蹈", "二次元", "音乐", "科技", "健身")
        .mapIndexed { index, string -> "${index + 1}. $string" }
    ExpandableItem3(title = "VerticalPager（pageSize）", allExpandFlow, padding = 20.dp) {
        Row(Modifier.fillMaxWidth()) {
            listOf(50.dp, 100.dp, 200.dp).forEachIndexed { pageSizeIndex, pageSize ->
                if (pageSizeIndex > 0) {
                    Spacer(modifier = Modifier.size(10.dp))
                }
                Column {
                    Text(text = "$pageSize")
                    VerticalPager(
                        state = rememberPagerState {
                            items.size
                        },
                        modifier = Modifier
                            .width(100.dp)
                            .height(300.dp)
                            .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                            .padding(2.dp),
                        pageSize = PageSize.Fixed(pageSize),
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
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun VerticalPagerPageSizeSamplePreview() {
    VerticalPagerPageSizeSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun VerticalPagerBeyondBoundsPageCountSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyColor.halfRainbows
    val items = listOf("数码", "汽车", "摄影", "舞蹈", "二次元", "音乐", "科技", "健身")
        .mapIndexed { index, string -> "${index + 1}. $string" }
    val desc = """
        beyondBoundsPageCount 属性表示除当前页面外前后各预加载多少个页面。
        
        每个页面都会延迟 3 秒钟显示内容，因此你可以在等待 3 秒钟后滑动页面时是否立即展示了下个页面来理解 beyondBoundsPageCount 属性的效果。
    """.trimIndent()
    ExpandableItem3(
        title = "VerticalPager（beyondBoundsPageCount）",
        desc = desc,
        allExpand = allExpandFlow,
        padding = 20.dp
    ) {
        Row(Modifier.fillMaxWidth()) {
            listOf(0, 1, 2).forEachIndexed { beyondBoundsPageCountIndex, beyondBoundsPageCount ->
                if (beyondBoundsPageCountIndex > 0) {
                    Spacer(modifier = Modifier.size(10.dp))
                }
                Column {
                    Text(text = "$beyondBoundsPageCount")
                    VerticalPager(
                        state = rememberPagerState {
                            items.size
                        },
                        modifier = Modifier
                            .width(100.dp)
                            .height(200.dp)
                            .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                            .padding(2.dp),
                        beyondBoundsPageCount = beyondBoundsPageCount,
                    ) { index ->
                        val loadingState = remember { mutableStateOf(true) }
                        if (loadingState.value) {
                            Box(modifier = Modifier.fillMaxSize()) {
                                CircularProgressIndicator(
                                    modifier = Modifier
                                        .size(30.dp)
                                        .align(Alignment.Center)
                                )
                                LaunchedEffect(key1 = Unit) {
                                    delay(3000)
                                    loadingState.value = false
                                }
                            }
                        } else {
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
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun VerticalPagerBeyondBoundsPageCountSamplePreview() {
    VerticalPagerBeyondBoundsPageCountSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun VerticalPagerHorizontalAlignmentHorSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyColor.halfRainbows
    val items = listOf("数码", "汽车", "摄影", "舞蹈", "二次元", "音乐", "科技", "健身")
        .mapIndexed { index, string -> "${index + 1}. $string" }
    ExpandableItem3(title = "VerticalPager（horizontalAlignment）", allExpandFlow, padding = 20.dp) {
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            mainAxisSpacing = 10.dp,
            crossAxisSpacing = 10.dp
        ) {
            listOf(
                Alignment.Start to "Start",
                Alignment.CenterHorizontally to "Center\nHorizontally",
                Alignment.End to "End",
            ).forEach {
                Column {
                    Spacer(modifier = Modifier.size(10.dp))
                    Text(text = it.second, modifier = Modifier.height(46.dp))
                    VerticalPager(
                        state = rememberPagerState {
                            items.size
                        },
                        modifier = Modifier
                            .width(100.dp)
                            .height(200.dp)
                            .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                            .padding(2.dp),
                        horizontalAlignment = it.first
                    ) { index ->
                        Box(
                            modifier = Modifier
                                .fillMaxHeight()
                                .width(60.dp)
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
private fun VerticalPagerVerticalAlignmentSamplePreview() {
    VerticalPagerHorizontalAlignmentHorSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun VerticalPagerScrollToPageSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyColor.halfRainbows
    val items = listOf("数码", "汽车", "摄影", "舞蹈", "二次元", "音乐", "科技", "健身")
        .mapIndexed { index, string -> "${index + 1}. $string" }
    val pagerState = rememberPagerState {
        items.size
    }
    val coroutineScope = rememberCoroutineScope()
    ExpandableItem3(title = "VerticalPager（scrollToPage）", allExpandFlow, padding = 20.dp) {
        Row {
            Column(
                modifier = Modifier
                    .height(400.dp)
                    .width(200.dp)
            ) {
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
                        .height(46.dp)
                        .fillMaxWidth()
                ) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowUp,
                        contentDescription = "before"
                    )
                }
                VerticalPager(
                    state = pagerState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
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
                        .height(46.dp)
                        .fillMaxWidth()
                ) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowDown,
                        contentDescription = "next",
                    )
                }
            }
            Column(
                modifier = Modifier
                    .height(400.dp)
                    .padding(top = 46.dp, bottom = 46.dp)
            ) {
                repeat(items.size) { index ->
                    Box(modifier = Modifier
                        .width(40.dp)
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
private fun VerticalPagerScrollToPageSamplePreview() {
    VerticalPagerScrollToPageSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun VerticalPagerAnimateScrollToPageSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyColor.halfRainbows
    val items = listOf("数码", "汽车", "摄影", "舞蹈", "二次元", "音乐", "科技", "健身")
        .mapIndexed { index, string -> "${index + 1}. $string" }
    val pagerState = rememberPagerState {
        items.size
    }
    val coroutineScope = rememberCoroutineScope()
    ExpandableItem3(title = "VerticalPager（animateScrollToPage）", allExpandFlow, padding = 20.dp) {
        Row {
            Column(
                modifier = Modifier
                    .height(400.dp)
                    .width(200.dp)
            ) {
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
                        .height(46.dp)
                        .fillMaxWidth()
                ) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowUp,
                        contentDescription = "before"
                    )
                }
                VerticalPager(
                    state = pagerState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
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
                        .height(46.dp)
                        .fillMaxWidth()
                ) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowDown,
                        contentDescription = "next",
                    )
                }
            }
            Column(
                modifier = Modifier
                    .height(400.dp)
                    .padding(top = 46.dp, bottom = 46.dp)
            ) {
                repeat(items.size) { index ->
                    Box(modifier = Modifier
                        .width(40.dp)
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
private fun VerticalPagerAnimateScrollToPageSamplePreview() {
    VerticalPagerAnimateScrollToPageSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun VerticalPagerScrollInProgressSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyColor.halfRainbows
    val items = listOf("数码", "汽车", "摄影", "舞蹈", "二次元", "音乐", "科技", "健身")
        .mapIndexed { index, string -> "${index + 1}. $string" }
    val pagerState = rememberPagerState {
        items.size
    }
    val scrollInProgressState = remember { derivedStateOf { pagerState.isScrollInProgress } }
    ExpandableItem3(title = "VerticalPager（isScrollInProgress）", allExpandFlow, padding = 20.dp) {
        Column {
            VerticalPager(
                state = pagerState,
                modifier = Modifier
                    .width(160.dp)
                    .height(260.dp)
                    .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
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
private fun VerticalPagerScrollInProgressSamplePreview() {
    VerticalPagerScrollInProgressSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun VerticalPagerCurrentPageSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyColor.halfRainbows
    val items = listOf("数码", "汽车", "摄影", "舞蹈", "二次元", "音乐", "科技", "健身")
        .mapIndexed { index, string -> "${index + 1}. $string" }
    val pagerState = rememberPagerState {
        items.size
    }
    val currentPageState = remember { derivedStateOf { pagerState.currentPage } }
    val currentPageOffsetFractionState =
        remember { derivedStateOf { pagerState.currentPageOffsetFraction } }
    ExpandableItem3(title = "VerticalPager（currentPage）", allExpandFlow, padding = 20.dp) {
        Column {
            VerticalPager(
                state = pagerState,
                modifier = Modifier
                    .width(160.dp)
                    .height(260.dp)
                    .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
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
            Text(text = "currentPageOffsetFraction: ${currentPageOffsetFractionState.value}")
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun VerticalPagerCurrentPageSamplePreview() {
    VerticalPagerCurrentPageSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalPagerApi::class, ExperimentalFoundationApi::class)
@Composable
private fun VerticalPagerIndicatorSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyColor.halfRainbows
    val items = listOf("数码", "汽车", "摄影", "舞蹈", "二次元", "音乐", "科技", "健身")
        .mapIndexed { index, string -> "${index + 1}. $string" }
    val pagerState = rememberPagerState {
        items.size
    }
    ExpandableItem3(title = "VerticalPager（Indicator）", allExpandFlow, padding = 20.dp) {
        Box {
            VerticalPager(
                state = pagerState,
                modifier = Modifier
                    .width(160.dp)
                    .height(260.dp)
                    .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
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
            Row(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(16.dp)
            ) {
                VerticalPagerIndicator(
                    pagerState = pagerState,
                    pageCount = items.size,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                )

                Spacer(modifier = Modifier.size(10.dp))
                VerticalPagerIndicator(
                    pagerState = pagerState,
                    pageCount = items.size,
                    modifier = Modifier
                        .align(Alignment.CenterVertically),
                    activeColor = Color.Red,
                    inactiveColor = MyColor.TranslucenceRed,
                )

                Spacer(modifier = Modifier.size(10.dp))
                VerticalPagerIndicator(
                    pagerState = pagerState,
                    pageCount = items.size,
                    modifier = Modifier
                        .align(Alignment.CenterVertically),
                    indicatorWidth = 4.dp,
                    indicatorHeight = 10.dp,
                    indicatorShape = RoundedCornerShape(50)
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun VerticalPagerIndicatorSamplePreview() {
    VerticalPagerIndicatorSample(remember { MutableStateFlow(true) })
}