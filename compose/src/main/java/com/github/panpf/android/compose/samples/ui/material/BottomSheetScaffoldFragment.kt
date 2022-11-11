package com.github.panpf.android.compose.samples.ui.material

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
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
import androidx.compose.material.MaterialTheme
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Surface
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.isVisible
import com.github.panpf.android.compose.samples.R
import com.github.panpf.android.compose.samples.ui.base.ToolbarFragment
import com.github.panpf.android.compose.samples.ui.base.theme.MyTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

class BottomSheetScaffoldFragment : ToolbarFragment() {

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
                        BottomSheetScaffoldSample()
                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalPagerApi::class, ExperimentalMaterialApi::class)
@Composable
fun BottomSheetScaffoldSample() {
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
fun BottomSheetScaffoldSamplePreview() {
    BottomSheetScaffoldSample()
}