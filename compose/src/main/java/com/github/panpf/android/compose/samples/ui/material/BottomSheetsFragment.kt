package com.github.panpf.android.compose.samples.ui.material

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.FabPosition
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.MaterialComposeAppBarFragment
import com.github.panpf.android.compose.samples.ui.base.MyColor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class BottomSheetsFragment : MaterialComposeAppBarFragment() {

    override fun getTitle(): String {
        return "BottomSheets - Material"
    }

    @Composable
    override fun DrawContent() {
        ExpandableLayout { allExpandFlow ->
            ModalBottomSheetLayoutSample(allExpandFlow)
            ModalBottomSheetLayoutSheetShapeSample(allExpandFlow)
            BottomSheetScaffoldSample(allExpandFlow)
        }
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun ModalBottomSheetLayoutSample(allExpandFlow: Flow<Boolean>) {
    val skipHalfExpanded = remember { mutableStateOf(false) }
    val state = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = skipHalfExpanded.value
    )
    val coroutineScope = rememberCoroutineScope()
    ExpandableItem(title = "ModalBottomSheetLayout", allExpandFlow, padding = 20.dp) {
        ModalBottomSheetLayout(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .clip(RectangleShape),
            sheetState = state,
            sheetContent = {
                Column(modifier = Modifier.fillMaxWidth()) {
                    listOf(
                        "分享" to Icons.Filled.Share,
                        "通讯录" to Icons.Filled.Phone,
                        "关于" to Icons.Filled.Info,
                        "设置" to Icons.Filled.Settings,
                    ).forEach { item ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    coroutineScope.launch {
                                        state.hide()
                                    }
                                }
                                .padding(20.dp)
                        ) {
                            Icon(imageVector = item.second, contentDescription = item.first)
                            Spacer(modifier = Modifier.size(10.dp))
                            Text(
                                text = item.first,
                                modifier = Modifier.align(Alignment.CenterVertically)
                            )
                        }
                    }
                }
            }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MyColor.TranslucenceBlue),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    Modifier.toggleable(
                        value = skipHalfExpanded.value,
                        role = Role.Checkbox,
                        onValueChange = { checked -> skipHalfExpanded.value = checked }
                    )
                ) {
                    Checkbox(checked = skipHalfExpanded.value, onCheckedChange = null)
                    Spacer(Modifier.size(16.dp))
                    Text("Skip Half Expanded State")
                }
                Spacer(Modifier.size(16.dp))
                Button(
                    onClick = {
                        coroutineScope.launch {
                            state.show()
                        }
                    }
                ) {
                    Text(text = "展开")
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun ModalBottomSheetLayoutSamplePreview() {
    ModalBottomSheetLayoutSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun ModalBottomSheetLayoutSheetShapeSample(allExpandFlow: Flow<Boolean>) {
    val skipHalfExpanded = remember { mutableStateOf(false) }
    val state = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = skipHalfExpanded.value
    )
    val coroutineScope = rememberCoroutineScope()
    ExpandableItem(title = "ModalBottomSheetLayout（sheetShape）", allExpandFlow, padding = 20.dp) {
        ModalBottomSheetLayout(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .clip(RectangleShape),
            sheetState = state,
            sheetShape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
            sheetContent = {
                Column(modifier = Modifier.fillMaxWidth()) {
                    listOf(
                        "分享" to Icons.Filled.Share,
                        "通讯录" to Icons.Filled.Phone,
                        "关于" to Icons.Filled.Info,
                        "设置" to Icons.Filled.Settings,
                    ).forEach { item ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    coroutineScope.launch {
                                        state.hide()
                                    }
                                }
                                .padding(20.dp)
                        ) {
                            Icon(imageVector = item.second, contentDescription = item.first)
                            Spacer(modifier = Modifier.size(10.dp))
                            Text(
                                text = item.first,
                                modifier = Modifier.align(Alignment.CenterVertically)
                            )
                        }
                    }
                }
            }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MyColor.TranslucenceBlue),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    Modifier.toggleable(
                        value = skipHalfExpanded.value,
                        role = Role.Checkbox,
                        onValueChange = { checked -> skipHalfExpanded.value = checked }
                    )
                ) {
                    Checkbox(checked = skipHalfExpanded.value, onCheckedChange = null)
                    Spacer(Modifier.size(16.dp))
                    Text("Skip Half Expanded State")
                }
                Spacer(Modifier.size(16.dp))
                Button(
                    onClick = {
                        coroutineScope.launch {
                            state.show()
                        }
                    }
                ) {
                    Text(text = "展开")
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun ModalBottomSheetLayoutSheetShapeSampleSheetPreview() {
    ModalBottomSheetLayoutSheetShapeSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
private fun BottomSheetScaffoldSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyColor.halfRainbows
    val pagerItems = remember {
        listOf(
            "消息" to Icons.Filled.Email,
            "通讯录" to Icons.Filled.Phone,
            "发现" to Icons.Filled.Place,
            "收藏" to Icons.Filled.Star,
        )
    }
    val menuItems = listOf(
        "首页" to Icons.Filled.Home,
        "分享" to Icons.Filled.Share,
        "关于" to Icons.Filled.Info,
        "设置" to Icons.Filled.Settings,
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
    ExpandableItem(title = "BottomSheetScaffold", allExpandFlow, padding = 20.dp) {
        BottomSheetScaffold(
            modifier = Modifier.fillMaxWidth().height(500.dp),
            scaffoldState = bottomSheetScaffoldState,
            topBar = {
                TopAppBar(
                    title = { Text("Title") },
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
                            Icon(imageVector = pair.second, contentDescription = pair.first)
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
                    Icon(
                        imageVector = if (bottomSheetScaffoldState.bottomSheetState.isCollapsed)
                            Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
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
                    pageCount = pagerItems.size,
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
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun BottomSheetScaffoldSamplePreview() {
    BottomSheetScaffoldSample(remember { MutableStateFlow(true) })
}