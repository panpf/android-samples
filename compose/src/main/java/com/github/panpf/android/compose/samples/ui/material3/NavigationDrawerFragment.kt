package com.github.panpf.android.compose.samples.ui.material3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.DismissibleDrawerSheet
import androidx.compose.material3.DismissibleNavigationDrawer
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.PermanentDrawerSheet
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.panpf.android.compose.samples.R
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.ToolbarFragment
import com.github.panpf.android.compose.samples.ui.base.theme.MyTheme3
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class NavigationDrawerFragment : ToolbarFragment() {

    override fun createView(
        toolbar: Toolbar,
        inflater: LayoutInflater,
        parent: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        toolbar.title = "NavigationDrawer"
        return ComposeView(inflater.context).apply {
            setContent {
                MyTheme3 {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        ExpandableLayout { allExpandFlow ->
                            ModalNavigationDrawerSample(allExpandFlow)
                            DismissibleNavigationDrawerSample(allExpandFlow)
                            PermanentNavigationDrawerSample(allExpandFlow)
                        }
                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModalNavigationDrawerSample(allExpandFlow: Flow<Boolean>) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()
    val selectedIndex = remember { mutableStateOf(0) }
    ExpandableItem(title = "ModalNavigationDrawer", allExpandFlow, padding = 20.dp) {
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
                        .background(Color.Yellow.copy(alpha = 0.5f))
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
                val icon =
                    if (drawerState.isOpen) R.drawable.ic_arrow_left else R.drawable.ic_arrow_right
                Image(
                    painter = painterResource(id = icon),
                    contentDescription = "menu"
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun ModalNavigationDrawerSamplePreview() {
    ModalNavigationDrawerSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DismissibleNavigationDrawerSample(allExpandFlow: Flow<Boolean>) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()
    val selectedIndex = remember { mutableStateOf(0) }
    ExpandableItem(title = "DismissibleNavigationDrawer", allExpandFlow, padding = 20.dp) {
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
                        .background(Color.Yellow.copy(alpha = 0.5f))
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
                val icon =
                    if (drawerState.isOpen) R.drawable.ic_arrow_left else R.drawable.ic_arrow_right
                Image(
                    painter = painterResource(id = icon),
                    contentDescription = "menu"
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun DismissibleNavigationDrawerSamplePreview() {
    DismissibleNavigationDrawerSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PermanentNavigationDrawerSample(allExpandFlow: Flow<Boolean>) {
    val selectedIndex = remember { mutableStateOf(0) }
    ExpandableItem(title = "PermanentNavigationDrawer", allExpandFlow, padding = 20.dp) {
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
                    .background(Color.Yellow.copy(alpha = 0.5f))
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun PermanentNavigationDrawerSamplePreview() {
    PermanentNavigationDrawerSample(remember { MutableStateFlow(true) })
}