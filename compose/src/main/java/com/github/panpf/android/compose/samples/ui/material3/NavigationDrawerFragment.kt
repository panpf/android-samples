package com.github.panpf.android.compose.samples.ui.material3

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.DismissibleDrawerSheet
import androidx.compose.material3.DismissibleNavigationDrawer
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.PermanentDrawerSheet
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem3
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
import com.github.panpf.android.compose.samples.ui.base.MyColor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class NavigationDrawerFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "NavigationDrawer - Material3"
    }

    @Composable
    override fun DrawContent() {
        ExpandableLayout { allExpandFlow ->
            ModalNavigationDrawerSample(allExpandFlow)
            DismissibleNavigationDrawerSample(allExpandFlow)
            PermanentNavigationDrawerSample(allExpandFlow)
        }
    }
}


@Composable
private fun ModalNavigationDrawerSample(allExpandFlow: Flow<Boolean>) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()
    val selectedIndex = remember { mutableStateOf(0) }
    ExpandableItem3(title = "ModalNavigationDrawer", allExpandFlow, padding = 20.dp) {
        Box(modifier = Modifier.clip(RectangleShape)) {
            ModalNavigationDrawer(
                drawerState = drawerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                drawerContent = {
                    ModalDrawerSheet(modifier = Modifier.fillMaxWidth(0.5f)) {
                        listOf("首页", "推荐", "发现", "设置", "关于").forEachIndexed { index, title ->
                            NavigationDrawerItem(
                                label = { Text(text = title) },
                                selected = selectedIndex.value == index,
                                onClick = {
                                    selectedIndex.value = index
                                    coroutineScope.launch {
                                        drawerState.close()
                                    }
                                }
                            )
                        }
                    }
                }
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .background(MyColor.TranslucenceYellow)
                )
            }
            IconButton(
                onClick = {
                    coroutineScope.launch {
                        if (drawerState.isOpen) {
                            drawerState.close()
                        } else {
                            drawerState.open()
                        }
                    }
                },
                modifier = Modifier.align(Alignment.CenterStart)
            ) {
                Icon(
                    imageVector = if (drawerState.isOpen)
                        Icons.Filled.KeyboardArrowLeft else Icons.Filled.KeyboardArrowRight,
                    contentDescription = "expand"
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun ModalNavigationDrawerSamplePreview() {
    ModalNavigationDrawerSample(remember { MutableStateFlow(true) })
}


@Composable
private fun DismissibleNavigationDrawerSample(allExpandFlow: Flow<Boolean>) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()
    val selectedIndex = remember { mutableStateOf(0) }
    ExpandableItem3(title = "DismissibleNavigationDrawer", allExpandFlow, padding = 20.dp) {
        Box(modifier = Modifier.clip(RectangleShape)) {
            DismissibleNavigationDrawer(
                drawerState = drawerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                drawerContent = {
                    DismissibleDrawerSheet(modifier = Modifier.fillMaxWidth(0.5f)) {
                        listOf("首页", "推荐", "发现", "设置", "关于").forEachIndexed { index, title ->
                            NavigationDrawerItem(
                                label = { Text(text = title) },
                                selected = selectedIndex.value == index,
                                onClick = {
                                    selectedIndex.value = index
                                    coroutineScope.launch {
                                        drawerState.close()
                                    }
                                }
                            )
                        }
                    }
                }
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .background(MyColor.TranslucenceYellow)
                )
            }
            IconButton(
                onClick = {
                    coroutineScope.launch {
                        if (drawerState.isOpen) {
                            drawerState.close()
                        } else {
                            drawerState.open()
                        }
                    }
                },
                modifier = Modifier.align(Alignment.CenterStart)
            ) {
                Icon(
                    imageVector = if (drawerState.isOpen)
                        Icons.Filled.KeyboardArrowLeft else Icons.Filled.KeyboardArrowRight,
                    contentDescription = "expand"
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun DismissibleNavigationDrawerSamplePreview() {
    DismissibleNavigationDrawerSample(remember { MutableStateFlow(true) })
}


@Composable
private fun PermanentNavigationDrawerSample(allExpandFlow: Flow<Boolean>) {
    val selectedIndex = remember { mutableStateOf(0) }
    ExpandableItem3(title = "PermanentNavigationDrawer", allExpandFlow, padding = 20.dp) {
        PermanentNavigationDrawer(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            drawerContent = {
                PermanentDrawerSheet(modifier = Modifier.fillMaxWidth(0.5f)) {
                    listOf("首页", "推荐", "发现", "设置", "关于").forEachIndexed { index, title ->
                        NavigationDrawerItem(
                            label = { Text(text = title) },
                            selected = selectedIndex.value == index,
                            onClick = {
                                selectedIndex.value = index
                            }
                        )
                    }
                }
            }
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(MyColor.TranslucenceYellow)
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun PermanentNavigationDrawerSamplePreview() {
    PermanentNavigationDrawerSample(remember { MutableStateFlow(true) })
}