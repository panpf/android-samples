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
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
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
import com.github.panpf.android.compose.samples.ui.base.pagerTabIndicatorOffset3
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPagerIndicator
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class HorizontalPagerFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "HorizontalPager"
    }

    @Composable
    override fun DrawContent() {
        ExpandableLayout { allExpandFlow ->
            HorizontalPagerSample(allExpandFlow)
            HorizontalPagerReverseLayoutSample(allExpandFlow)
            HorizontalPagerPageSpacingSample(allExpandFlow)
            HorizontalPagerContentPaddingSample(allExpandFlow)
            HorizontalPagerPageSizeSample(allExpandFlow)
            HorizontalPagerBeyondBoundsPageCountSample(allExpandFlow)
            HorizontalPagerVerticalAlignmentSample(allExpandFlow)
            HorizontalPagerScrollToPageSample(allExpandFlow)
            HorizontalPagerAnimateScrollToPageSample(allExpandFlow)
            HorizontalPagerScrollInProgressSample(allExpandFlow)
            HorizontalPagerCurrentPageSample(allExpandFlow)
            HorizontalPagerIndicatorSample(allExpandFlow)
            HorizontalPagerTabRowSample(allExpandFlow)
            HorizontalPagerScrollableTabRowSample(allExpandFlow)
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun HorizontalPagerSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyColor.halfRainbows
    val items = listOf("数码", "汽车", "摄影", "舞蹈", "二次元", "音乐", "科技", "健身")
        .mapIndexed { index, string -> "${index + 1}. $string" }
    ExpandableItem3(title = "HorizontalPager", allExpandFlow, padding = 20.dp) {
        HorizontalPager(
            state = rememberPagerState {
                items.size
            },
            modifier = Modifier
                .height(200.dp)
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
private fun HorizontalPagerSamplePreview() {
    HorizontalPagerSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun HorizontalPagerReverseLayoutSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyColor.halfRainbows
    val items = listOf("数码", "汽车", "摄影", "舞蹈", "二次元", "音乐", "科技", "健身")
        .mapIndexed { index, string -> "${index + 1}. $string" }
    ExpandableItem3(title = "HorizontalPager（reverseLayout）", allExpandFlow, padding = 20.dp) {
        HorizontalPager(
            state = rememberPagerState {
                items.size
            },
            modifier = Modifier
                .height(200.dp)
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
private fun HorizontalPagerReverseLayoutSamplePreview() {
    HorizontalPagerReverseLayoutSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun HorizontalPagerPageSpacingSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyColor.halfRainbows
    val items = listOf("数码", "汽车", "摄影", "舞蹈", "二次元", "音乐", "科技", "健身")
        .mapIndexed { index, string -> "${index + 1}. $string" }
    val pageSpacingState = remember { mutableStateOf(20.dp) }
    val pageSpacingRange = 5.dp..35.dp
    val pageSpacingStep = 5.dp
    ExpandableItem3(title = "HorizontalPager（pageSpacing）", allExpandFlow, padding = 20.dp) {
        HorizontalPager(
            state = rememberPagerState {
                items.size
            },
            modifier = Modifier
                .height(200.dp)
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
private fun HorizontalPagerPageSpacingSamplePreview() {
    HorizontalPagerPageSpacingSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun HorizontalPagerContentPaddingSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyColor.halfRainbows
    val items = listOf("数码", "汽车", "摄影", "舞蹈", "二次元", "音乐", "科技", "健身")
        .mapIndexed { index, string -> "${index + 1}. $string" }
    val contentPaddingState = remember { mutableStateOf(20.dp) }
    val contentPaddingRange = 5.dp..35.dp
    val contentPaddingStep = 5.dp
    ExpandableItem3(title = "HorizontalPager（contentPadding）", allExpandFlow, padding = 20.dp) {
        HorizontalPager(
            state = rememberPagerState {
                items.size
            },
            modifier = Modifier
                .height(200.dp)
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
private fun HorizontalPagerContentPaddingSamplePreview() {
    HorizontalPagerContentPaddingSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun HorizontalPagerPageSizeSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyColor.halfRainbows
    val items = listOf("数码", "汽车", "摄影", "舞蹈", "二次元", "音乐", "科技", "健身")
        .mapIndexed { index, string -> "${index + 1}. $string" }
    ExpandableItem3(title = "HorizontalPager（pageSize）", allExpandFlow, padding = 20.dp) {
        listOf(100.dp, 200.dp, 300.dp).forEachIndexed { pageSizeIndex, pageSize ->
            if (pageSizeIndex > 0) {
                Spacer(modifier = Modifier.size(10.dp))
            }
            Text(text = "$pageSize")
            HorizontalPager(
                state = rememberPagerState {
                    items.size
                },
                modifier = Modifier
                    .height(100.dp)
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

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun HorizontalPagerPageSizeSamplePreview() {
    HorizontalPagerPageSizeSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun HorizontalPagerBeyondBoundsPageCountSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyColor.halfRainbows
    val items = listOf("数码", "汽车", "摄影", "舞蹈", "二次元", "音乐", "科技", "健身")
        .mapIndexed { index, string -> "${index + 1}. $string" }
    val desc = """
        beyondBoundsPageCount 属性表示除当前页面外前后各预加载多少个页面。
        
        每个页面都会延迟 3 秒钟显示内容，因此你可以在等待 3 秒钟后滑动页面时是否立即展示了下个页面来理解 beyondBoundsPageCount 属性的效果。
    """.trimIndent()
    ExpandableItem3(
        title = "HorizontalPager（beyondBoundsPageCount）",
        desc = desc,
        allExpand = allExpandFlow,
        padding = 20.dp
    ) {
        listOf(0, 1, 2).forEachIndexed { beyondBoundsPageCountIndex, beyondBoundsPageCount ->
            if (beyondBoundsPageCountIndex > 0) {
                Spacer(modifier = Modifier.size(10.dp))
            }
            Text(text = "$beyondBoundsPageCount")
            HorizontalPager(
                state = rememberPagerState {
                    items.size
                },
                modifier = Modifier
                    .height(100.dp)
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

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun HorizontalPagerBeyondBoundsPageCountSamplePreview() {
    HorizontalPagerBeyondBoundsPageCountSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun HorizontalPagerVerticalAlignmentSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyColor.halfRainbows
    val items = listOf("数码", "汽车", "摄影", "舞蹈", "二次元", "音乐", "科技", "健身")
        .mapIndexed { index, string -> "${index + 1}. $string" }
    ExpandableItem3(title = "HorizontalPager（verticalAlignment）", allExpandFlow, padding = 20.dp) {
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
                        state = rememberPagerState {
                            items.size
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp)
                            .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
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
private fun HorizontalPagerVerticalAlignmentSamplePreview() {
    HorizontalPagerVerticalAlignmentSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun HorizontalPagerScrollToPageSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyColor.halfRainbows
    val items = listOf("数码", "汽车", "摄影", "舞蹈", "二次元", "音乐", "科技", "健身")
        .mapIndexed { index, string -> "${index + 1}. $string" }
    val pagerState = rememberPagerState() {
        items.size
    }
    val coroutineScope = rememberCoroutineScope()
    ExpandableItem3(title = "HorizontalPager（scrollToPage）", allExpandFlow, padding = 20.dp) {
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
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowLeft,
                        contentDescription = "before"
                    )
                }
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier
                        .fillMaxHeight()
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
                        .width(46.dp)
                        .fillMaxHeight()
                ) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowRight,
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
private fun HorizontalPagerScrollToPageSamplePreview() {
    HorizontalPagerScrollToPageSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun HorizontalPagerAnimateScrollToPageSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyColor.halfRainbows
    val items = listOf("数码", "汽车", "摄影", "舞蹈", "二次元", "音乐", "科技", "健身")
        .mapIndexed { index, string -> "${index + 1}. $string" }
    val pagerState = rememberPagerState(){
        items.size
    }
    val coroutineScope = rememberCoroutineScope()
    ExpandableItem3(
        title = "HorizontalPager（animateScrollToPage）",
        allExpandFlow,
        padding = 20.dp
    ) {
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
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowLeft,
                        contentDescription = "before"
                    )
                }
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier
                        .fillMaxHeight()
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
                        .width(46.dp)
                        .fillMaxHeight()
                ) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowRight,
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
private fun HorizontalPagerAnimateScrollToPageSamplePreview() {
    HorizontalPagerAnimateScrollToPageSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun HorizontalPagerScrollInProgressSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyColor.halfRainbows
    val items = listOf("数码", "汽车", "摄影", "舞蹈", "二次元", "音乐", "科技", "健身")
        .mapIndexed { index, string -> "${index + 1}. $string" }
    val pagerState = rememberPagerState {
        items.size
    }
    val scrollInProgressState = remember { derivedStateOf { pagerState.isScrollInProgress } }
    ExpandableItem3(title = "HorizontalPager（isScrollInProgress）", allExpandFlow, padding = 20.dp) {
        Column {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .height(200.dp)
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
private fun HorizontalPagerScrollInProgressSamplePreview() {
    HorizontalPagerScrollInProgressSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun HorizontalPagerCurrentPageSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyColor.halfRainbows
    val items = listOf("数码", "汽车", "摄影", "舞蹈", "二次元", "音乐", "科技", "健身")
        .mapIndexed { index, string -> "${index + 1}. $string" }
    val pagerState = rememberPagerState {
        items.size
    }
    val currentPageState = remember { derivedStateOf { pagerState.currentPage } }
    val currentPageOffsetFractionState =
        remember { derivedStateOf { pagerState.currentPageOffsetFraction } }
    ExpandableItem3(title = "HorizontalPager（currentPage）", allExpandFlow, padding = 20.dp) {
        Column {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .height(200.dp)
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
private fun HorizontalPagerCurrentPageSamplePreview() {
    HorizontalPagerCurrentPageSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalFoundationApi::class, ExperimentalPagerApi::class)
@Composable
private fun HorizontalPagerIndicatorSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyColor.halfRainbows
    val items = listOf("数码", "汽车", "摄影", "舞蹈", "二次元", "音乐", "科技", "健身")
        .mapIndexed { index, string -> "${index + 1}. $string" }
    val pagerState = rememberPagerState{
        items.size
    }
    ExpandableItem3(title = "HorizontalPager（Indicator）", allExpandFlow, padding = 20.dp) {
        Box {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .height(200.dp)
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
            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(16.dp)
            ) {
                HorizontalPagerIndicator(
                    pagerState = pagerState,
                    pageCount = items.size,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.size(10.dp))
                HorizontalPagerIndicator(
                    pagerState = pagerState,
                    pageCount = items.size,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally),
                    activeColor = Color.Red,
                    inactiveColor = MyColor.TranslucenceRed,
                )

                Spacer(modifier = Modifier.size(10.dp))
                HorizontalPagerIndicator(
                    pagerState = pagerState,
                    pageCount = items.size,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally),
                    indicatorWidth = 10.dp,
                    indicatorHeight = 4.dp,
                    indicatorShape = RoundedCornerShape(50)
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun HorizontalPagerIndicatorSamplePreview() {
    HorizontalPagerIndicatorSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun HorizontalPagerTabRowSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyColor.halfRainbows
    val items = listOf("数码", "汽车", "摄影", "舞蹈", "二次元", "音乐", "科技", "健身")
        .mapIndexed { index, string -> "${index + 1}. $string" }
    val pagerState = rememberPagerState{
        items.size
    }
    val coroutineScope = rememberCoroutineScope()
    ExpandableItem3(title = "HorizontalPager（TabRow）", allExpandFlow, padding = 20.dp) {
        Column {
            TabRow(
                selectedTabIndex = pagerState.currentPage,
                indicator = { tabPositions ->
                    TabRowDefaults.Indicator(
                        modifier = Modifier.pagerTabIndicatorOffset3(pagerState, tabPositions),
                    )
                }
            ) {
                items.forEachIndexed { index, item ->
                    Tab(
                        selected = index == pagerState.currentPage,
                        onClick = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        }
                    ) {
                        Text(text = item)
                    }
                }
            }
            Spacer(modifier = Modifier.size(2.dp))
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.height(200.dp)
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

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun HorizontalPagerTabRowSamplePreview() {
    HorizontalPagerTabRowSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun HorizontalPagerScrollableTabRowSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyColor.halfRainbows
    val items = listOf("数码", "汽车", "摄影", "舞蹈", "二次元", "音乐", "科技", "健身")
        .mapIndexed { index, string -> "${index + 1}. $string" }
    val pagerState = rememberPagerState {
        items.size
    }
    val coroutineScope = rememberCoroutineScope()
    ExpandableItem3(title = "HorizontalPager（ScrollableTabRow）", allExpandFlow, padding = 20.dp) {
        Column {
            ScrollableTabRow(
                selectedTabIndex = pagerState.currentPage,
                indicator = { tabPositions ->
                    TabRowDefaults.Indicator(
                        modifier = Modifier.pagerTabIndicatorOffset3(pagerState, tabPositions),
                    )
                },
            ) {
                items.forEachIndexed { index, item ->
                    Tab(
                        selected = index == pagerState.currentPage,
                        onClick = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        }
                    ) {
                        Text(text = item, modifier = Modifier.padding(vertical = 10.dp))
                    }
                }
            }
            Spacer(modifier = Modifier.size(2.dp))
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.height(200.dp)
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

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun HorizontalPagerScrollableTabRowSamplePreview() {
    HorizontalPagerScrollableTabRowSample(remember { MutableStateFlow(true) })
}