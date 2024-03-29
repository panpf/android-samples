package com.github.panpf.android.compose.samples.ui.material

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
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.MaterialComposeAppBarFragment
import com.github.panpf.android.compose.samples.ui.base.MyColor
import com.github.panpf.tools4a.toast.ktx.showShortToast
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class BottomNavigationFragment : MaterialComposeAppBarFragment() {

    override fun getTitle(): String {
        return "BottomNavigation - Material"
    }

    @Composable
    override fun DrawContent() {
        ExpandableLayout { allExpandFlow ->
            BottomNavigationSample(allExpandFlow)
            BottomNavigationPagerSample(allExpandFlow)
        }
    }
}


@Composable
private fun BottomNavigationSample(allExpandFlow: Flow<Boolean>) {
    val items = remember {
        listOf(
            "首页" to Icons.Filled.Home,
            "通讯录" to Icons.Filled.Phone,
            "游戏" to Icons.Filled.PlayArrow,
            "设置" to Icons.Filled.Settings,
        )
    }
    val selectedIndex = remember { mutableStateOf(0) }
    val context = LocalContext.current
    ExpandableItem(title = "BottomNavigation", allExpandFlow, padding = 20.dp) {
        Text(text = "Default")
        Spacer(modifier = Modifier.size(10.dp))
        BottomNavigation {
            items.forEachIndexed { index, item ->
                BottomNavigationItem(
                    selected = selectedIndex.value == index,
                    onClick = {
                        context.showShortToast(item.first)
                        selectedIndex.value = index
                    },
                    icon = {
                        Icon(imageVector = item.second, contentDescription = item.first)
                    },
                    label = {
                        Text(text = item.first)
                    }
                )
            }
        }

        Spacer(modifier = Modifier.size(20.dp))
        Text(text = "colors")
        Spacer(modifier = Modifier.size(10.dp))
        BottomNavigation {
            items.forEachIndexed { index, item ->
                BottomNavigationItem(
                    selected = selectedIndex.value == index,
                    onClick = {
                        context.showShortToast(item.first)
                        selectedIndex.value = index
                    },
                    icon = {
                        Icon(imageVector = item.second, contentDescription = item.first)
                    },
                    label = {
                        Text(text = item.first)
                    },
                    selectedContentColor = Color.Yellow,
                    unselectedContentColor = Color.Red,
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun BottomNavigationSamplePreview() {
    BottomNavigationSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun BottomNavigationPagerSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyColor.halfRainbows.toMutableList().apply {
        val value1 = this[1]
        this[1] = this[0]
        this[0] = value1
    }.toList()
    val selectedIndex = remember { mutableStateOf(0) }
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
    ExpandableItem(title = "BottomNavigation（Pager）", allExpandFlow, padding = 20.dp) {
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
            BottomNavigation {
                items.forEachIndexed { index, itemPair ->
                    BottomNavigationItem(
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
private fun BottomNavigationPagerSamplePreview() {
    BottomNavigationPagerSample(remember { MutableStateFlow(true) })
}