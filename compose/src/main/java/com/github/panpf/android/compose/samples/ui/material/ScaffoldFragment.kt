package com.github.panpf.android.compose.samples.ui.material

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.FabPosition
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
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
import androidx.core.view.isVisible
import com.github.panpf.android.compose.samples.R
import com.github.panpf.android.compose.samples.ui.base.ToolbarFragment
import com.github.panpf.android.compose.samples.ui.base.theme.MyTheme
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
        toolbar.isVisible = false
        return ComposeView(inflater.context).apply {
            setContent {
                MyTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colors.background
                    ) {
                        ScaffoldSample()
                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalPagerApi::class)
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
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val navigationSelectedIndex = remember { mutableStateOf(0) }
    val pagerState = rememberPagerState(navigationSelectedIndex.value)
    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect {
            navigationSelectedIndex.value = it
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Scaffold - Material") },
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
            BottomNavigation {
                pagerItems.forEachIndexed { index, itemPair ->
                    BottomNavigationItem(
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
                },
                text = {
                    Text("Show snackbar")
                }
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
    )
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun ScaffoldSamplePreview() {
    ScaffoldSample()
}