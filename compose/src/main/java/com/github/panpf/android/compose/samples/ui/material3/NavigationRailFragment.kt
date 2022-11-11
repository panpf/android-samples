package com.github.panpf.android.compose.samples.ui.material3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.NavigationRailItemDefaults
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
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem3
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.ToolbarFragment
import com.github.panpf.android.compose.samples.ui.base.theme.MyTheme3
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.VerticalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class NavigationRailFragment : ToolbarFragment() {

    override fun createView(
        toolbar: Toolbar,
        inflater: LayoutInflater,
        parent: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        toolbar.title = "NavigationRail - Material3"
        return ComposeView(inflater.context).apply {
            setContent {
                MyTheme3 {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        ExpandableLayout { allExpandFlow ->
                            NavigationRailSample(allExpandFlow)
                            NavigationRailColorsSample(allExpandFlow)
                            NavigationRailPagerSample(allExpandFlow)
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun NavigationRailSample(allExpandFlow: Flow<Boolean>) {
    val selectedIndex = remember { mutableStateOf(0) }
    val items = remember {
        listOf(
            "首页" to R.drawable.ic_home,
            "通讯录" to R.drawable.ic_phone,
            "游戏" to R.drawable.ic_games,
            "设置" to R.drawable.ic_settings,
        )
    }
    ExpandableItem3(title = "NavigationRail", allExpandFlow, padding = 20.dp) {
        NavigationRail(
            modifier = Modifier
                .height(300.dp),
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
                            painter = painterResource(id = itemPair.second),
                            contentDescription = "icon"
                        )
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun NavigationRailSamplePreview() {
    NavigationRailSample(remember { MutableStateFlow(true) })
}


@Composable
fun NavigationRailColorsSample(allExpandFlow: Flow<Boolean>) {
    val selectedIndex = remember { mutableStateOf(0) }
    val items = remember {
        listOf(
            "首页" to R.drawable.ic_home,
            "通讯录" to R.drawable.ic_phone,
            "游戏" to R.drawable.ic_games,
            "设置" to R.drawable.ic_settings,
        )
    }
    ExpandableItem3(title = "NavigationRail（colors）", allExpandFlow, padding = 20.dp) {
        NavigationRail(
            modifier = Modifier.height(300.dp),
            containerColor = Color.Yellow.copy(alpha = 0.5f)
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
                            painter = painterResource(id = itemPair.second),
                            contentDescription = "icon"
                        )
                    },
                    colors = NavigationRailItemDefaults.colors(
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
fun NavigationRailColorsSamplePreview() {
    NavigationRailColorsSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun NavigationRailPagerSample(allExpandFlow: Flow<Boolean>) {
    val colors = remember {
        listOf(Color.Blue, Color.Magenta, Color.Cyan, Color.Red, Color.Yellow, Color.Green)
            .map { it.copy(alpha = 0.5f) }
    }
    val selectedIndex = remember { mutableStateOf(0) }
    val items = remember {
        listOf(
            "首页" to R.drawable.ic_home,
            "通讯录" to R.drawable.ic_phone,
            "游戏" to R.drawable.ic_games,
            "设置" to R.drawable.ic_settings,
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
                            Image(
                                painter = painterResource(id = itemPair.second),
                                contentDescription = "icon"
                            )
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
                count = items.size,
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
fun NavigationRailPagerSamplePreview() {
    NavigationRailPagerSample(remember { MutableStateFlow(true) })
}