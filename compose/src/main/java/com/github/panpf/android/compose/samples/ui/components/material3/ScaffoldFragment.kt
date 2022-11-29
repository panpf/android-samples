package com.github.panpf.android.compose.samples.ui.components.material3

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.panpf.android.compose.samples.R
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
import com.github.panpf.android.compose.samples.ui.base.MyColor
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

class ScaffoldFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String? {
        return null
    }

    @Composable
    override fun DrawContent() {
        ScaffoldSample()
    }
}


@OptIn(ExperimentalMaterial3Api::class, ExperimentalPagerApi::class)
@Composable
private fun ScaffoldSample() {
    val colors = MyColor.halfRainbows
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
                title = { Text("Scaffold - Material3") },
                windowInsets = WindowInsets(0.dp)
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

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun ScaffoldSamplePreview() {
    ScaffoldSample()
}