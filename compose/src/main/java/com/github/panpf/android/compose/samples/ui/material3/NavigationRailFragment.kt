package com.github.panpf.android.compose.samples.ui.material3

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.NavigationRailItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.panpf.android.compose.samples.R
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem3
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
import com.github.panpf.android.compose.samples.ui.base.MyColor
import com.github.panpf.android.compose.samples.ui.customization.grid.VerticalGrid
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class NavigationRailFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "NavigationRail - Material3"
    }

    @Composable
    override fun DrawContent() {
        ExpandableLayout { allExpandFlow ->
            NavigationRailSample(allExpandFlow)
            NavigationRailPagerSample(allExpandFlow)
        }
    }
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun NavigationRailSample(allExpandFlow: Flow<Boolean>) {
    val selectedIndex = remember { mutableStateOf(0) }
    val items = remember {
        listOf(
            "首页" to Icons.Filled.Home,
            "通讯录" to Icons.Filled.Phone,
            "游戏" to Icons.Filled.PlayArrow,
            "设置" to Icons.Filled.Settings,
        )
    }
    ExpandableItem3(title = "NavigationRail", allExpandFlow, padding = 20.dp) {
        VerticalGrid(
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            Column {
                Text(text = "Default")
                Spacer(modifier = Modifier.size(10.dp))
                NavigationRail(modifier = Modifier.height(400.dp)) {
                    items.forEachIndexed { index, itemPair ->
                        if (index > 0) {
                            Spacer(modifier = Modifier.size(10.dp))
                        }
                        NavigationRailItem(
                            selected = selectedIndex.value == index,
                            onClick = {
                                selectedIndex.value = index
                            },
                            label = { Text(text = itemPair.first) },
                            icon = {
                                Icon(
                                    imageVector = itemPair.second,
                                    contentDescription = itemPair.first
                                )
                            }
                        )
                    }
                }
            }

            Column {
                Text(text = "colors")
                Spacer(modifier = Modifier.size(10.dp))
                NavigationRail(
                    modifier = Modifier.height(400.dp),
                    containerColor = MyColor.TranslucenceYellow
                ) {
                    items.forEachIndexed { index, itemPair ->
                        if (index > 0) {
                            Spacer(modifier = Modifier.size(10.dp))
                        }
                        NavigationRailItem(
                            selected = selectedIndex.value == index,
                            onClick = {
                                selectedIndex.value = index
                            },
                            label = { Text(text = itemPair.first) },
                            icon = {
                                Icon(
                                    imageVector = itemPair.second,
                                    contentDescription = itemPair.first
                                )
                            },
                            colors = NavigationRailItemDefaults.colors(
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

            Column {
                Text(text = "header")
                Spacer(modifier = Modifier.size(10.dp))
                NavigationRail(
                    modifier = Modifier
                        .height(400.dp),
                    header = {
                        Image(
                            painter = painterResource(id = R.drawable.ic_avatar),
                            contentDescription = "avatar"
                        )
                    },
                ) {
                    items.forEachIndexed { index, itemPair ->
                        if (index > 0) {
                            Spacer(modifier = Modifier.size(10.dp))
                        }
                        NavigationRailItem(
                            selected = selectedIndex.value == index,
                            onClick = {
                                selectedIndex.value = index
                            },
                            label = { Text(text = itemPair.first) },
                            icon = {
                                Icon(
                                    imageVector = itemPair.second,
                                    contentDescription = itemPair.first
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun NavigationRailSamplePreview() {
    NavigationRailSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun NavigationRailPagerSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyColor.halfRainbows
    val selectedIndex = remember { mutableStateOf(0) }
    val items = remember {
        listOf(
            "首页" to Icons.Filled.Home,
            "通讯录" to Icons.Filled.Phone,
            "游戏" to Icons.Filled.PlayArrow,
            "设置" to Icons.Filled.Settings,
        )
    }
    val pagerState = rememberPagerState(selectedIndex.value)
    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect {
            selectedIndex.value = it
        }
    }
    val coroutineScope = rememberCoroutineScope()
    ExpandableItem3(title = " NavigationRail（Pager）", allExpandFlow, padding = 20.dp) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        ) {
            NavigationRail {
                items.forEachIndexed { index, itemPair ->
                    NavigationRailItem(
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
            VerticalPager(
                state = pagerState,
                pageCount = items.size,
                modifier = Modifier
                    .fillMaxSize()
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
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun NavigationRailPagerSamplePreview() {
    NavigationRailPagerSample(remember { MutableStateFlow(true) })
}