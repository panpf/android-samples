package com.github.panpf.android.compose.samples.ui.material

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.panpf.android.compose.samples.R
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.ToolbarFragment
import com.github.panpf.android.compose.samples.ui.base.theme.MyTheme
import com.github.panpf.tools4a.toast.ktx.showShortToast
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class BottomNavigationFragment : ToolbarFragment() {

    override fun createView(
        toolbar: Toolbar,
        inflater: LayoutInflater,
        parent: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        toolbar.title = "BottomNavigation"
        return ComposeView(inflater.context).apply {
            setContent {
                MyTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colors.background
                    ) {
                        ExpandableLayout { allExpandFlow ->
                            BottomNavigationSample(allExpandFlow)
                            BottomNavigationColorsSample(allExpandFlow)
                            BottomNavigationPagerSample(allExpandFlow)
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun BottomNavigationSample(allExpandFlow: Flow<Boolean>) {
    val items = remember {
        listOf(
            "首页" to R.drawable.ic_home,
            "通讯录" to R.drawable.ic_phone,
            "游戏" to R.drawable.ic_games,
            "设置" to R.drawable.ic_settings,
        )
    }
    val selectedIndex = remember { mutableStateOf(0) }
    val context = LocalContext.current
    ExpandableItem(title = "BottomNavigation", allExpandFlow, padding = 20.dp) {
        Column {
            BottomNavigation {
                items.forEachIndexed { index, item ->
                    BottomNavigationItem(
                        selected = selectedIndex.value == index,
                        onClick = {
                            context.showShortToast(item.first)
                            selectedIndex.value = index
                        },
                        icon = {
                            Icon(
                                painter = painterResource(id = item.second),
                                contentDescription = item.first
                            )
                        },
                        label = {
                            Text(text = item.first)
                        }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun BottomNavigationSamplePreview() {
    BottomNavigationSample(remember { MutableStateFlow(true) })
}


@Composable
fun BottomNavigationColorsSample(allExpandFlow: Flow<Boolean>) {
    val items = remember {
        listOf(
            "首页" to R.drawable.ic_home,
            "通讯录" to R.drawable.ic_phone,
            "游戏" to R.drawable.ic_games,
            "设置" to R.drawable.ic_settings,
        )
    }
    val selectedIndex = remember { mutableStateOf(1) }
    val context = LocalContext.current
    ExpandableItem(title = "BottomNavigation（Colors）", allExpandFlow, padding = 20.dp) {
        Column {
            BottomNavigation {
                items.forEachIndexed { index, item ->
                    BottomNavigationItem(
                        selected = selectedIndex.value == index,
                        onClick = {
                            context.showShortToast(item.first)
                            selectedIndex.value = index
                        },
                        icon = {
                            Icon(
                                painter = painterResource(id = item.second),
                                contentDescription = item.first
                            )
                        },
                        label = {
                            Text(text = item.first)
                        },
                        selectedContentColor = Color.Yellow.copy(alpha = 0.8f),
                        unselectedContentColor = Color.Red.copy(alpha = 0.8f),
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun BottomNavigationColorsSamplePreview() {
    BottomNavigationColorsSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun BottomNavigationPagerSample(allExpandFlow: Flow<Boolean>) {
    val colors = remember {
        listOf(Color.Yellow, Color.Blue, Color.Magenta, Color.Cyan, Color.Red, Color.Green)
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
    ExpandableItem(title = "BottomNavigation（Pager）", allExpandFlow, padding = 20.dp) {
        Column(modifier = Modifier.fillMaxWidth()) {
            HorizontalPager(
                state = pagerState,
                count = items.size,
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
                            Icon(
                                painter = painterResource(id = itemPair.second),
                                contentDescription = itemPair.first
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
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun BottomNavigationPagerSamplePreview() {
    BottomNavigationPagerSample(remember { MutableStateFlow(true) })
}