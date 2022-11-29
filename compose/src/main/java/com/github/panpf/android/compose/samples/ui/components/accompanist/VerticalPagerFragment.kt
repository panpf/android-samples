package com.github.panpf.android.compose.samples.ui.components.accompanist

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
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
import com.google.accompanist.pager.VerticalPager
import com.google.accompanist.pager.VerticalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
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
            VerticalPagerItemSpacingSample(allExpandFlow)
            VerticalPagerContentPaddingSample(allExpandFlow)
            VerticalPagerHorizontalAlignmentHorSample(allExpandFlow)
            VerticalPagerScrollToPageSample(allExpandFlow)
            VerticalPagerAnimateScrollToPageSample(allExpandFlow)
            VerticalPagerScrollInProgressSample(allExpandFlow)
            VerticalPagerCurrentPageSample(allExpandFlow)
            VerticalPagerIndicatorSample(allExpandFlow)
        }
    }
}


@OptIn(ExperimentalPagerApi::class)
@Composable
private fun VerticalPagerSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyColor.halfRainbows
    val items = listOf("数码", "汽车", "摄影", "舞蹈", "二次元", "音乐", "科技", "健身")
        .mapIndexed { index, string -> "${index + 1}. $string" }
    ExpandableItem3(title = "VerticalPager", allExpandFlow, padding = 20.dp) {
        VerticalPager(
            count = items.size,
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


@OptIn(ExperimentalPagerApi::class)
@Composable
private fun VerticalPagerReverseLayoutSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyColor.halfRainbows
    val items = listOf("数码", "汽车", "摄影", "舞蹈", "二次元", "音乐", "科技", "健身")
        .mapIndexed { index, string -> "${index + 1}. $string" }
    ExpandableItem3(title = "VerticalPager（reverseLayout）", allExpandFlow, padding = 20.dp) {
        VerticalPager(
            count = items.size,
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


@OptIn(ExperimentalPagerApi::class)
@Composable
private fun VerticalPagerItemSpacingSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyColor.halfRainbows
    val items = listOf("数码", "汽车", "摄影", "舞蹈", "二次元", "音乐", "科技", "健身")
        .mapIndexed { index, string -> "${index + 1}. $string" }
    ExpandableItem3(title = "VerticalPager（itemSpacing）", allExpandFlow, padding = 20.dp) {
        VerticalPager(
            count = items.size,
            modifier = Modifier
                .width(200.dp)
                .height(300.dp)
                .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
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
private fun VerticalPagerItemSpacingSamplePreview() {
    VerticalPagerItemSpacingSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalPagerApi::class)
@Composable
private fun VerticalPagerContentPaddingSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyColor.halfRainbows
    val items = listOf("数码", "汽车", "摄影", "舞蹈", "二次元", "音乐", "科技", "健身")
        .mapIndexed { index, string -> "${index + 1}. $string" }
    ExpandableItem3(title = "VerticalPager（contentPadding）", allExpandFlow, padding = 20.dp) {
        VerticalPager(
            count = items.size,
            modifier = Modifier
                .width(200.dp)
                .height(300.dp)
                .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
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
private fun VerticalPagerContentPaddingSamplePreview() {
    VerticalPagerContentPaddingSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalPagerApi::class)
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
                        count = items.size,
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


@OptIn(ExperimentalPagerApi::class)
@Composable
private fun VerticalPagerScrollToPageSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyColor.halfRainbows
    val items = listOf("数码", "汽车", "摄影", "舞蹈", "二次元", "音乐", "科技", "健身")
        .mapIndexed { index, string -> "${index + 1}. $string" }
    val pagerState = rememberPagerState()
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
                    Image(
                        painter = painterResource(id = R.drawable.ic_arrow_up),
                        contentDescription = "before"
                    )
                }
                VerticalPager(
                    count = items.size,
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
                    Image(
                        painter = painterResource(id = R.drawable.ic_arrow_down),
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


@OptIn(ExperimentalPagerApi::class)
@Composable
private fun VerticalPagerAnimateScrollToPageSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyColor.halfRainbows
    val items = listOf("数码", "汽车", "摄影", "舞蹈", "二次元", "音乐", "科技", "健身")
        .mapIndexed { index, string -> "${index + 1}. $string" }
    val pagerState = rememberPagerState()
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
                    Image(
                        painter = painterResource(id = R.drawable.ic_arrow_up),
                        contentDescription = "before"
                    )
                }
                VerticalPager(
                    count = items.size,
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
                    Image(
                        painter = painterResource(id = R.drawable.ic_arrow_down),
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


@OptIn(ExperimentalPagerApi::class)
@Composable
private fun VerticalPagerScrollInProgressSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyColor.halfRainbows
    val items = listOf("数码", "汽车", "摄影", "舞蹈", "二次元", "音乐", "科技", "健身")
        .mapIndexed { index, string -> "${index + 1}. $string" }
    val pagerState = rememberPagerState()
    val scrollInProgressState = remember { derivedStateOf { pagerState.isScrollInProgress } }
    ExpandableItem3(title = "VerticalPager（isScrollInProgress）", allExpandFlow, padding = 20.dp) {
        Column {
            VerticalPager(
                count = items.size,
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


@OptIn(ExperimentalPagerApi::class)
@Composable
private fun VerticalPagerCurrentPageSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyColor.halfRainbows
    val items = listOf("数码", "汽车", "摄影", "舞蹈", "二次元", "音乐", "科技", "健身")
        .mapIndexed { index, string -> "${index + 1}. $string" }
    val pagerState = rememberPagerState()
    val currentPageState = remember { derivedStateOf { pagerState.currentPage } }
    val currentPageOffsetState = remember { derivedStateOf { pagerState.currentPageOffset } }
    ExpandableItem3(title = "VerticalPager（currentPage）", allExpandFlow, padding = 20.dp) {
        Column {
            VerticalPager(
                count = items.size,
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
            Text(text = "currentPageOffset: ${currentPageOffsetState.value}")
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun VerticalPagerCurrentPageSamplePreview() {
    VerticalPagerCurrentPageSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalPagerApi::class)
@Composable
private fun VerticalPagerIndicatorSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyColor.halfRainbows
    val items = listOf("数码", "汽车", "摄影", "舞蹈", "二次元", "音乐", "科技", "健身")
        .mapIndexed { index, string -> "${index + 1}. $string" }
    val pagerState = rememberPagerState()
    ExpandableItem3(title = "VerticalPager（Indicator）", allExpandFlow, padding = 20.dp) {
        Box {
            VerticalPager(
                count = items.size,
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
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                )

                Spacer(modifier = Modifier.size(10.dp))
                VerticalPagerIndicator(
                    pagerState = pagerState,
                    modifier = Modifier
                        .align(Alignment.CenterVertically),
                    activeColor = Color.Red,
                    inactiveColor = MyColor.TranslucenceRed,
                )

                Spacer(modifier = Modifier.size(10.dp))
                VerticalPagerIndicator(
                    pagerState = pagerState,
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