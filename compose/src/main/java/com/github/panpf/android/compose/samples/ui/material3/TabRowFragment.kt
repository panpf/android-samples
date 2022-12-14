package com.github.panpf.android.compose.samples.ui.material3

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Divider
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem3
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
import com.github.panpf.android.compose.samples.ui.base.MyColor
import com.github.panpf.android.compose.samples.ui.base.pagerTabIndicatorOffset3
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class TabRowFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "TabRow - Material3"
    }

    @Composable
    override fun DrawContent() {
        ExpandableLayout { allExpandFlow ->
            TabRowSample(allExpandFlow)
            TabRowColorsSample(allExpandFlow)
            TabRowPagerSample(allExpandFlow)
            ScrollableTabRowSample(allExpandFlow)
            ScrollableTabRowColorsSample(allExpandFlow)
            ScrollableTabRowPagerSample(allExpandFlow)
        }
    }
}


@Composable
private fun TabRowSample(allExpandFlow: Flow<Boolean>) {
    val selectedTabIndex = remember { mutableStateOf(0) }
    val items = listOf("??????", "??????", "??????", "??????", "?????????", "??????", "??????", "??????")
    ExpandableItem3(title = "TabRow", allExpandFlow, padding = 20.dp) {
        TabRow(
            selectedTabIndex = selectedTabIndex.value,
        ) {
            items.forEachIndexed { index, item ->
                Tab(
                    selected = selectedTabIndex.value == index,
                    onClick = {
                        selectedTabIndex.value = index
                    }
                ) {
                    Text(text = item)
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun TabRowSamplePreview() {
    TabRowSample(remember { MutableStateFlow(true) })
}


@Composable
private fun TabRowColorsSample(allExpandFlow: Flow<Boolean>) {
    val selectedTabIndex = remember { mutableStateOf(0) }
    val items = listOf("??????", "??????", "??????", "??????", "?????????", "??????", "??????", "??????")
    ExpandableItem3(title = "TabRow???colors???", allExpandFlow, padding = 20.dp) {
        TabRow(
            selectedTabIndex = selectedTabIndex.value,
            containerColor = Color.Transparent,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex.value]),
                    height = 4.dp,
                    color = Color.Cyan
                )
            },
            divider = {
                Divider(thickness = 2.dp, color = MyColor.TranslucenceCyan)
            }
        ) {
            items.forEachIndexed { index, item ->
                Tab(
                    selected = selectedTabIndex.value == index,
                    selectedContentColor = Color.Red,
                    unselectedContentColor = Color.Red.copy(alpha = 0.3f),
                    onClick = {
                        selectedTabIndex.value = index
                    }
                ) {
                    Text(text = item)
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun TabRowColorsSamplePreview() {
    TabRowColorsSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalPagerApi::class)
@Composable
private fun TabRowPagerSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyColor.halfRainbows
    val items = listOf("??????", "??????", "??????", "??????", "?????????", "??????", "??????", "??????")
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()
    ExpandableItem3(title = "TabRow???Pager???", allExpandFlow, padding = 20.dp) {
        Column {
            TabRow(
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
                        Text(text = item)
                    }
                }
            }
            HorizontalPager(
                count = items.size,
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
private fun TabRowPagerSamplePreview() {
    TabRowPagerSample(remember { MutableStateFlow(true) })
}


@Composable
private fun ScrollableTabRowSample(allExpandFlow: Flow<Boolean>) {
    val selectedTabIndex = remember { mutableStateOf(0) }
    val items = listOf("??????", "??????", "??????", "??????", "?????????", "??????", "??????", "??????")
    ExpandableItem3(title = "ScrollableTabRow", allExpandFlow, padding = 20.dp) {
        ScrollableTabRow(
            selectedTabIndex = selectedTabIndex.value,
        ) {
            items.forEachIndexed { index, item ->
                Tab(
                    selected = selectedTabIndex.value == index,
                    onClick = {
                        selectedTabIndex.value = index
                    }
                ) {
                    Text(text = item)
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun ScrollableTabRowSamplePreview() {
    ScrollableTabRowSample(remember { MutableStateFlow(true) })
}


@Composable
private fun ScrollableTabRowColorsSample(allExpandFlow: Flow<Boolean>) {
    val selectedTabIndex = remember { mutableStateOf(0) }
    val items = listOf("??????", "??????", "??????", "??????", "?????????", "??????", "??????", "??????")
    ExpandableItem3(title = "ScrollableTabRow???colors???", allExpandFlow, padding = 20.dp) {
        ScrollableTabRow(
            selectedTabIndex = selectedTabIndex.value,
            containerColor = Color.Transparent,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex.value]),
                    height = 4.dp,
                    color = Color.Cyan
                )
            },
            divider = {
                Divider(thickness = 2.dp, color = MyColor.TranslucenceCyan)
            }
        ) {
            items.forEachIndexed { index, item ->
                Tab(
                    selected = selectedTabIndex.value == index,
                    selectedContentColor = Color.Red,
                    unselectedContentColor = Color.Red.copy(alpha = 0.3f),
                    onClick = {
                        selectedTabIndex.value = index
                    }
                ) {
                    Text(text = item)
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun ScrollableTabRowColorsSamplePreview() {
    ScrollableTabRowColorsSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalPagerApi::class)
@Composable
private fun ScrollableTabRowPagerSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyColor.halfRainbows
    val items = listOf("??????", "??????", "??????", "??????", "?????????", "??????", "??????", "??????")
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()
    ExpandableItem3(title = "ScrollableTabRow???Pager???", allExpandFlow, padding = 20.dp) {
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
                        Text(text = item)
                    }
                }
            }
            HorizontalPager(
                count = items.size,
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
private fun ScrollableTabRowPagerSamplePreview() {
    ScrollableTabRowPagerSample(remember { MutableStateFlow(true) })
}