package com.github.panpf.android.compose.samples.ui.material

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.FabPosition
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.panpf.android.compose.samples.R
import com.github.panpf.android.compose.samples.ui.base.MaterialComposeAppBarFragment
import com.github.panpf.android.compose.samples.ui.base.MyColor
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

class BottomSheetScaffoldFragment : MaterialComposeAppBarFragment() {

    override fun getTitle(): String? {
        return null
    }

    @Composable
    override fun DrawContent() {
        BottomSheetScaffoldSample()
    }
}


@OptIn(ExperimentalPagerApi::class, ExperimentalMaterialApi::class)
@Composable
private fun BottomSheetScaffoldSample() {
    val colors = MyColor.halfRainbows
    val pagerItems = remember {
        listOf(
            "??????" to R.drawable.ic_message,
            "?????????" to R.drawable.ic_phone,
            "??????" to R.drawable.ic_games,
            "??????" to R.drawable.ic_sports_baseball,
        )
    }
    val menuItems = listOf(
        "??????" to R.drawable.ic_home,
        "??????" to R.drawable.ic_share,
        "??????" to R.drawable.ic_info,
        "??????" to R.drawable.ic_settings,
    )
    val snackbarHostState = remember { SnackbarHostState() }
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    val menuSelectedIndex = remember { mutableStateOf(0) }
    val navigationSelectedIndex = remember { mutableStateOf(0) }
    val pagerState = rememberPagerState(navigationSelectedIndex.value)
    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect {
            navigationSelectedIndex.value = it
        }
    }

    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        topBar = {
            TopAppBar(
                title = { Text("BottomSheetScaffold - Material") },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            coroutineScope.launch {
                                bottomSheetScaffoldState.drawerState.open()
                            }
                        }
                    ) {
                        Icon(Icons.Filled.Menu, contentDescription = "Localized description")
                    }
                }
            )
        },
        drawerContent = {
            Column(
                modifier = Modifier
                    .padding(20.dp)
            ) {
                menuItems.forEachIndexed { index, pair ->
                    if (index > 0) {
                        Divider(modifier = Modifier.fillMaxWidth())
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                menuSelectedIndex.value = index
                                coroutineScope.launch {
                                    bottomSheetScaffoldState.drawerState.close()
                                }
                            }
                            .padding(20.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = pair.second),
                            contentDescription = "icon"
                        )
                        Spacer(modifier = Modifier.size(16.dp))
                        Text(text = pair.first)
                    }
                }
            }
        },
        sheetContent = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .height(300.dp)
            ) {
                val icon = if (bottomSheetScaffoldState.bottomSheetState.isCollapsed)
                    R.drawable.ic_arrow_up else R.drawable.ic_arrow_down
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = "up",
                    modifier = Modifier.align(Alignment.TopCenter),
                )
                Text(
                    text = "Sheet content",
                    modifier = Modifier.align(Alignment.Center),
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )
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
    )
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun BottomSheetScaffoldSamplePreview() {
    BottomSheetScaffoldSample()
}