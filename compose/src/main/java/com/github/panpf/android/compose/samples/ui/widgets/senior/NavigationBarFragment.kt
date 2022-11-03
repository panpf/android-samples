package com.github.panpf.android.compose.samples.ui.widgets.senior

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.Toolbar
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Surface
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
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
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

class NavigationBarFragment : ToolbarFragment() {

    override fun createView(
        toolbar: Toolbar,
        inflater: LayoutInflater,
        parent: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        toolbar.title = "NavigationBar"
        return ComposeView(inflater.context).apply {
            setContent {
                MyTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        ExpandableLayout { allExpandFlow ->
                            NavigationBarSample(allExpandFlow)
                            NavigationBarColorsSample(allExpandFlow)
                            NavigationBarPagerSample(allExpandFlow)
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun NavigationBarSample(allExpandFlow: Flow<Boolean>) {
    val selectedIndex = remember { mutableStateOf(0) }
    val items = remember {
        mutableStateOf(
            listOf(
                NavigationBarItemInfo("首页", R.drawable.ic_home),
                NavigationBarItemInfo("通讯录", R.drawable.ic_phone),
                NavigationBarItemInfo("游戏", R.drawable.ic_games),
                NavigationBarItemInfo("设置", R.drawable.ic_settings),
            )
        )
    }
    ExpandableItem(title = "NavigationBar", allExpandFlow, padding = 20.dp) {
        NavigationBar {
            items.value.forEachIndexed { index, info ->
                NavigationBarItem(
                    icon = {
                        Image(
                            painter = painterResource(id = info.iconId),
                            contentDescription = "icon"
                        )
                    },
                    label = { Text(text = info.title) },
                    selected = index == selectedIndex.value,
                    onClick = {
                        selectedIndex.value = index
                    },
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun NavigationBarSamplePreview() {
    NavigationBarSample(remember { MutableStateFlow(true) })
}


@Composable
fun NavigationBarColorsSample(allExpandFlow: Flow<Boolean>) {
    val selectedIndex = remember { mutableStateOf(0) }
    val items = remember {
        mutableStateOf(
            listOf(
                NavigationBarItemInfo("首页", R.drawable.ic_home),
                NavigationBarItemInfo("通讯录", R.drawable.ic_phone),
                NavigationBarItemInfo("游戏", R.drawable.ic_games),
                NavigationBarItemInfo("设置", R.drawable.ic_settings),
            )
        )
    }
    ExpandableItem(title = "NavigationBar（colors）", allExpandFlow, padding = 20.dp) {
        NavigationBar {
            items.value.forEachIndexed { index, info ->
                NavigationBarItem(
                    icon = {
                        Icon(
                            painter = painterResource(id = info.iconId),
                            contentDescription = "icon"
                        )
                    },
                    label = { Text(text = info.title) },
                    selected = index == selectedIndex.value,
                    onClick = {
                        selectedIndex.value = index
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.Blue,
                        selectedTextColor = Color.Magenta,
                        indicatorColor = Color.Green.copy(alpha = 0.5f),
                        unselectedIconColor = Color.Blue.copy(alpha = 0.5f),
                        unselectedTextColor = Color.Magenta.copy(alpha = 0.5f),
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun NavigationBarColorsSamplePreview() {
    NavigationBarColorsSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun NavigationBarPagerSample(allExpandFlow: Flow<Boolean>) {
    val colors = remember {
        listOf(Color.Blue, Color.Magenta, Color.Cyan, Color.Red, Color.Yellow, Color.Green)
            .map { it.copy(alpha = 0.5f) }
    }
    val selectedIndex = remember { mutableStateOf(0) }
    val items = remember {
        mutableStateOf(
            listOf(
                NavigationBarItemInfo("首页", R.drawable.ic_home),
                NavigationBarItemInfo("通讯录", R.drawable.ic_phone),
                NavigationBarItemInfo("游戏", R.drawable.ic_games),
                NavigationBarItemInfo("设置", R.drawable.ic_settings),
            )
        )
    }
    val pagerState = rememberPagerState(selectedIndex.value)
    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect {
            selectedIndex.value = it
        }
    }
    val coroutineScope = rememberCoroutineScope()
    ExpandableItem(title = "NavigationBar（Pager）", allExpandFlow, padding = 20.dp) {
        Column(modifier = Modifier.fillMaxWidth()) {
            HorizontalPager(
                state = pagerState,
                count = items.value.size,
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
                        text = items.value[index].title,
                        modifier = Modifier
                            .align(Alignment.Center),
                        textAlign = TextAlign.Center
                    )
                }
            }
            NavigationBar {
                items.value.forEachIndexed { index, info ->
                    NavigationBarItem(
                        icon = {
                            Image(
                                painter = painterResource(id = info.iconId),
                                contentDescription = "icon"
                            )
                        },
                        label = { Text(text = info.title) },
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
fun NavigationBarPagerSamplePreview() {
    NavigationBarPagerSample(remember { MutableStateFlow(true) })
}

data class NavigationBarItemInfo(val title: String, @DrawableRes val iconId: Int)