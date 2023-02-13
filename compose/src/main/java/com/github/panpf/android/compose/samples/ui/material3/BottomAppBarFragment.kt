package com.github.panpf.android.compose.samples.ui.material3

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem3
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
import com.github.panpf.tools4a.toast.ktx.showShortToast
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class BottomAppBarFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "BottomAppBar - Material3"
    }

    @Composable
    override fun DrawContent() {
        ExpandableLayout { allExpandFlow ->
            BottomAppBarSample(allExpandFlow)
            BottomAppBarColorsSample(allExpandFlow)
        }
    }
}


@Composable
private fun BottomAppBarSample(allExpandFlow: Flow<Boolean>) {
    val items = remember {
        listOf(
            "首页" to Icons.Filled.Home,
            "通讯录" to Icons.Filled.Phone,
            "游戏" to Icons.Filled.PlayArrow,
            "设置" to Icons.Filled.Settings,
        )
    }
    val context = LocalContext.current
    ExpandableItem3(title = "BottomAppBar", allExpandFlow, padding = 20.dp) {
        Column {
            BottomAppBar(
                actions = {
                    items.forEach {
                        IconButton(
                            modifier = Modifier.fillMaxHeight(),
                            onClick = { context.showShortToast(it.first) }
                        ) {
                            Icon(imageVector = it.second, contentDescription = it.first)
                        }
                    }
                },
                floatingActionButton = {
                    FloatingActionButton(onClick = { context.showShortToast("add") }) {
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = "add"
                        )
                    }
                }
            )
            Spacer(modifier = Modifier.size(10.dp))
            BottomAppBar {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    items.forEach {
                        IconButton(
                            modifier = Modifier
                                .fillMaxHeight()
                                .weight(1f),
                            onClick = { context.showShortToast(it.first) }
                        ) {
                            Icon(imageVector = it.second, contentDescription = it.first)
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun BottomAppBarSamplePreview() {
    BottomAppBarSample(remember { MutableStateFlow(true) })
}


@Composable
private fun BottomAppBarColorsSample(allExpandFlow: Flow<Boolean>) {
    val items = remember {
        listOf(
            "首页" to Icons.Filled.Home,
            "通讯录" to Icons.Filled.Phone,
            "游戏" to Icons.Filled.PlayArrow,
            "设置" to Icons.Filled.Settings,
        )
    }
    val context = LocalContext.current
    ExpandableItem3(title = "BottomAppBar（colors）", allExpandFlow, padding = 20.dp) {
        Column {
            BottomAppBar(
                actions = {
                    items.forEach {
                        IconButton(
                            modifier = Modifier.fillMaxHeight(),
                            onClick = { context.showShortToast(it.first) }
                        ) {
                            Icon(imageVector = it.second, contentDescription = it.first)
                        }
                    }
                },
                floatingActionButton = {
                    FloatingActionButton(onClick = { context.showShortToast("add") }) {
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = "add"
                        )
                    }
                },
                containerColor = Color.Blue.copy(alpha = 0.6f),
                contentColor = Color.White,
            )
            Spacer(modifier = Modifier.size(10.dp))
            BottomAppBar(
                containerColor = Color.Blue.copy(alpha = 0.6f),
                contentColor = Color.White,
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    items.forEach {
                        IconButton(
                            modifier = Modifier
                                .fillMaxHeight()
                                .weight(1f),
                            onClick = { context.showShortToast(it.first) }
                        ) {
                            Icon(imageVector = it.second, contentDescription = it.first)
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun BottomAppBarColorsSamplePreview() {
    BottomAppBarColorsSample(remember { MutableStateFlow(true) })
}