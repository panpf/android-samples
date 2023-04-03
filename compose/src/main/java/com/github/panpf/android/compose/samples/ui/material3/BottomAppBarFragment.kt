package com.github.panpf.android.compose.samples.ui.material3

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
import com.github.panpf.android.compose.samples.ui.base.theme.MyThemeColors3
import com.github.panpf.tools4a.toast.ktx.showShortToast

class BottomAppBarFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "BottomAppBar - Material3"
    }

    @Composable
    override fun DrawContent() {
        BottomAppBarSample()
    }
}


@Composable
private fun BottomAppBarSample() {
    val items = remember {
        listOf(
            "首页" to Icons.Filled.Home,
            "通讯录" to Icons.Filled.Phone,
            "游戏" to Icons.Filled.PlayArrow,
            "设置" to Icons.Filled.Settings,
        )
    }
    val context = LocalContext.current
    val colors = MyThemeColors3.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
    ) {
        Text(text = "Default")
        Spacer(modifier = Modifier.size(10.dp))
        BottomAppBar {
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

        Spacer(modifier = Modifier.size(20.dp))
        Text(text = "floatingActionButton")
        Spacer(modifier = Modifier.size(10.dp))
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

        Spacer(modifier = Modifier.size(20.dp))
        Text(text = "containerColor")
        Spacer(modifier = Modifier.size(10.dp))
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
            containerColor = colors.tertiaryContainer,
        )

        Spacer(modifier = Modifier.size(20.dp))
        Text(text = "contentColor")
        Spacer(modifier = Modifier.size(10.dp))
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
            contentColor = colors.tertiary,
        )
        
        // todo tonalElevation invalid

        Spacer(modifier = Modifier.size(20.dp))
        Text(text = "contentPadding=PaddingValues(horizontal=20.dp, vertical=12.dp)")
        Spacer(modifier = Modifier.size(10.dp))
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
            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 10.dp),
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun BottomAppBarSamplePreview() {
    BottomAppBarSample()
}