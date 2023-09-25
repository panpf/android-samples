package com.github.panpf.android.compose.samples.ui.material3

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem3
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
import com.github.panpf.android.compose.samples.ui.base.MyColor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class NavigationBarFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "NavigationBar - Material3"
    }

    @Composable
    override fun DrawContent() {
        ExpandableLayout { allExpandFlow ->
            NavigationBarSample(allExpandFlow)
            NavigationBarPagerSample(allExpandFlow)
        }
    }
}


@Composable
private fun NavigationBarSample(allExpandFlow: Flow<Boolean>) {
    val selectedIndex = remember { mutableStateOf(0) }
    val items = remember {
        listOf(
            "首页" to Icons.Filled.Home,
            "通讯录" to Icons.Filled.Phone,
            "游戏" to Icons.Filled.PlayArrow,
            "设置" to Icons.Filled.Settings,
        )
    }
    ExpandableItem3(title = "NavigationBar", allExpandFlow, padding = 20.dp) {
        Text(text = "Default")
        Spacer(modifier = Modifier.size(10.dp))
        NavigationBar {
            items.forEachIndexed { index, itemPair ->
                NavigationBarItem(
                    icon = {
                        Icon(imageVector = itemPair.second, contentDescription = itemPair.first)
                    },
                    label = { Text(text = itemPair.first) },
                    selected = index == selectedIndex.value,
                    onClick = {
                        selectedIndex.value = index
                    },
                )
            }
        }

        Spacer(modifier = Modifier.size(20.dp))
        Text(text = "colors")
        Spacer(modifier = Modifier.size(10.dp))
        NavigationBar(
            containerColor = MyColor.TranslucenceYellow
        ) {
            items.forEachIndexed { index, itemPair ->
                NavigationBarItem(
                    icon = {
                        Icon(imageVector = itemPair.second, contentDescription = itemPair.first)
                    },
                    label = { Text(text = itemPair.first) },
                    selected = index == selectedIndex.value,
                    onClick = {
                        selectedIndex.value = index
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.Blue,
                        selectedTextColor = Color.Magenta,
                        indicatorColor = MyColor.TranslucenceGreen,
                        unselectedIconColor = MyColor.TranslucenceBlue,
                        unselectedTextColor = MyColor.TranslucenceMagenta,
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun NavigationBarSamplePreview() {
    NavigationBarSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun NavigationBarPagerSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyColor.halfRainbows
    val selectedIndex = remember { mutableIntStateOf(0) }
    val items = remember {
        listOf(
            "首页" to Icons.Filled.Home,
            "通讯录" to Icons.Filled.Phone,
            "游戏" to Icons.Filled.PlayArrow,
            "设置" to Icons.Filled.Settings,
        )
    }
    val pagerState = rememberPagerState(selectedIndex.value) {
        items.size
    }
    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect {
            selectedIndex.value = it
        }
    }
    val coroutineScope = rememberCoroutineScope()
    ExpandableItem3(title = "NavigationBar（Pager）", allExpandFlow, padding = 20.dp) {
        Column(modifier = Modifier.fillMaxWidth()) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
            ) { index ->
                Box(
                    modifier = Modifier
                        .background(colors[index % colors.size])
                        .fillMaxSize()
                        .padding(20.dp)
                ) {
                    Text(
                        text = items[index].first,
                        modifier = Modifier
                            .align(Alignment.Center),
                        textAlign = TextAlign.Center
                    )
                }
            }
            NavigationBar {
                items.forEachIndexed { index, itemPair ->
                    NavigationBarItem(
                        icon = {
                            Icon(imageVector = itemPair.second, contentDescription = itemPair.first)
                        },
                        label = { Text(text = itemPair.first) },
                        selected = index == selectedIndex.value,
                        onClick = {
                            selectedIndex.value = index
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        },
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun NavigationBarPagerSamplePreview() {
    NavigationBarPagerSample(remember { MutableStateFlow(true) })
}