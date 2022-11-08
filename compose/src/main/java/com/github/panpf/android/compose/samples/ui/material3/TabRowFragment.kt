package com.github.panpf.android.compose.samples.ui.material3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Surface
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
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.ToolbarFragment
import com.github.panpf.android.compose.samples.ui.base.pagerTabIndicatorOffset3
import com.github.panpf.android.compose.samples.ui.base.theme.MyTheme3
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class TabRowFragment : ToolbarFragment() {

    override fun createView(
        toolbar: Toolbar,
        inflater: LayoutInflater,
        parent: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        toolbar.title = "TabRow"
        return ComposeView(inflater.context).apply {
            setContent {
                MyTheme3 {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
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
            }
        }
    }
}


@Composable
fun TabRowSample(allExpandFlow: Flow<Boolean>) {
    val selectedTabIndex = remember { mutableStateOf(0) }
    val items = listOf("数码", "汽车", "摄影", "舞蹈", "二次元", "音乐", "科技", "健身")
    ExpandableItem(title = "TabRow", allExpandFlow, padding = 20.dp) {
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
fun TabRowSamplePreview() {
    TabRowSample(remember { MutableStateFlow(true) })
}


@Composable
fun TabRowColorsSample(allExpandFlow: Flow<Boolean>) {
    val selectedTabIndex = remember { mutableStateOf(0) }
    val items = listOf("数码", "汽车", "摄影", "舞蹈", "二次元", "音乐", "科技", "健身")
    ExpandableItem(title = "TabRow（colors）", allExpandFlow, padding = 20.dp) {
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
                Divider(thickness = 2.dp, color = Color.Cyan.copy(alpha = 0.5f))
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
fun TabRowColorsSamplePreview() {
    TabRowColorsSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabRowPagerSample(allExpandFlow: Flow<Boolean>) {
    val colors = remember {
        listOf(Color.Blue, Color.Magenta, Color.Cyan, Color.Red, Color.Yellow, Color.Green)
            .map { it.copy(alpha = 0.5f) }
    }
    val items = listOf("数码", "汽车", "摄影", "舞蹈", "二次元", "音乐", "科技", "健身")
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()
    ExpandableItem(title = "TabRow（Pager）", allExpandFlow, padding = 20.dp) {
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
fun TabRowPagerSamplePreview() {
    TabRowPagerSample(remember { MutableStateFlow(true) })
}


@Composable
fun ScrollableTabRowSample(allExpandFlow: Flow<Boolean>) {
    val selectedTabIndex = remember { mutableStateOf(0) }
    val items = listOf("数码", "汽车", "摄影", "舞蹈", "二次元", "音乐", "科技", "健身")
    ExpandableItem(title = "ScrollableTabRow", allExpandFlow, padding = 20.dp) {
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
fun ScrollableTabRowSamplePreview() {
    ScrollableTabRowSample(remember { MutableStateFlow(true) })
}


@Composable
fun ScrollableTabRowColorsSample(allExpandFlow: Flow<Boolean>) {
    val selectedTabIndex = remember { mutableStateOf(0) }
    val items = listOf("数码", "汽车", "摄影", "舞蹈", "二次元", "音乐", "科技", "健身")
    ExpandableItem(title = "ScrollableTabRow（colors）", allExpandFlow, padding = 20.dp) {
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
                Divider(thickness = 2.dp, color = Color.Cyan.copy(alpha = 0.5f))
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
fun ScrollableTabRowColorsSamplePreview() {
    ScrollableTabRowColorsSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun ScrollableTabRowPagerSample(allExpandFlow: Flow<Boolean>) {
    val colors = remember {
        listOf(Color.Blue, Color.Magenta, Color.Cyan, Color.Red, Color.Yellow, Color.Green)
            .map { it.copy(alpha = 0.5f) }
    }
    val items = listOf("数码", "汽车", "摄影", "舞蹈", "二次元", "音乐", "科技", "健身")
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()
    ExpandableItem(title = "ScrollableTabRow（Pager）", allExpandFlow, padding = 20.dp) {
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
fun ScrollableTabRowPagerSamplePreview() {
    ScrollableTabRowPagerSample(remember { MutableStateFlow(true) })
}