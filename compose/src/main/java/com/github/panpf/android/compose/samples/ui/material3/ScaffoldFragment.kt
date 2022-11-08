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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
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
import com.github.panpf.android.compose.samples.ui.base.ToolbarFragment
import com.github.panpf.android.compose.samples.ui.base.theme.MyTheme3
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

class ScaffoldFragment : ToolbarFragment() {

    override fun createView(
        toolbar: Toolbar,
        inflater: LayoutInflater,
        parent: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        toolbar.title = "Scaffold"
        return ComposeView(inflater.context).apply {
            setContent {
                MyTheme3 {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        ScaffoldSample()
                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class, ExperimentalPagerApi::class)
@Composable
fun ScaffoldSample() {
    val colors = remember {
        listOf(Color.Blue, Color.Magenta, Color.Cyan, Color.Red, Color.Yellow, Color.Green)
            .map { it.copy(alpha = 0.5f) }
    }
    val pagerItems = remember {
        listOf(
            "消息" to R.drawable.ic_message,
            "通讯录" to R.drawable.ic_phone,
            "发现" to R.drawable.ic_games,
            "运动" to R.drawable.ic_sports_baseball,
        )
    }
    val menuItems = listOf(
        "首页" to R.drawable.ic_home,
        "分享" to R.drawable.ic_share,
        "关于" to R.drawable.ic_info,
        "设置" to R.drawable.ic_settings,
    )
    val snackbarHostState = remember { SnackbarHostState() }
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()
    val menuSelectedIndex = remember { mutableStateOf(0) }
    val navigationSelectedIndex = remember { mutableStateOf(0) }
    val pagerState = rememberPagerState(navigationSelectedIndex.value)
    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect {
            navigationSelectedIndex.value = it
        }
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        modifier = Modifier.fillMaxSize(),
        drawerContent = {
            ModalDrawerSheet(modifier = Modifier.fillMaxWidth(0.7f)) {
                Column(modifier = Modifier.fillMaxSize().padding(20.dp)) {
                    menuItems.forEachIndexed { index, pair ->
                        NavigationDrawerItem(
                            icon = {
                                Icon(
                                    painter = painterResource(id = pair.second),
                                    contentDescription = "icon"
                                )
                            },
                            label = { Text(text = pair.first) },
                            selected = menuSelectedIndex.value == index,
                            onClick = {
                                menuSelectedIndex.value = index
                                coroutineScope.launch {
                                    drawerState.close()
                                }
                            }
                        )
                    }
                }
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Simple Scaffold Screen") },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                coroutineScope.launch {
                                    drawerState.open()
                                }
                            }
                        ) {
                            Icon(Icons.Filled.Menu, contentDescription = "Localized description")
                        }
                    }
                )
            },
            content = { innerPadding ->
                HorizontalPager(
                    state = pagerState,
                    count = pagerItems.size,
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = innerPadding
                ) { index ->
                    Box(
                        modifier = Modifier
                            .background(colors[index % colors.size])
                            .fillMaxSize()
                            .padding(20.dp)
                    ) {
                        Text(
                            text = pagerItems[index].first,
                            modifier = Modifier
                                .align(Alignment.Center),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            },
            bottomBar = {
                NavigationBar {
                    pagerItems.forEachIndexed { index, itemPair ->
                        NavigationBarItem(
                            icon = {
                                Icon(
                                    painter = painterResource(id = itemPair.second),
                                    contentDescription = "icon"
                                )
                            },
                            label = { Text(text = itemPair.first) },
                            selected = index == navigationSelectedIndex.value,
                            onClick = {
                                navigationSelectedIndex.value = index
                                coroutineScope.launch {
                                    pagerState.animateScrollToPage(index)
                                }
                            },
                        )
                    }
                }
            },
            floatingActionButtonPosition = FabPosition.End,
            floatingActionButton = {
                val clickCount = remember { mutableStateOf(0) }
                ExtendedFloatingActionButton(
                    onClick = {
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar(
                                "Snackbar # ${++clickCount.value}"
                            )
                        }
                    }
                ) {
                    Text("Show snackbar")
                }
            },
            snackbarHost = { SnackbarHost(snackbarHostState) },
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun ScaffoldSamplePreview() {
    ScaffoldSample()
}