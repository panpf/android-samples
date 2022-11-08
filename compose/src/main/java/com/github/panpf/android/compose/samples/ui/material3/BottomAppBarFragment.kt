package com.github.panpf.android.compose.samples.ui.material3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.panpf.android.compose.samples.R
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.ToolbarFragment
import com.github.panpf.android.compose.samples.ui.base.theme.MyTheme
import com.github.panpf.tools4a.toast.ktx.showShortToast
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class BottomAppBarFragment : ToolbarFragment() {

    override fun createView(
        toolbar: Toolbar,
        inflater: LayoutInflater,
        parent: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        toolbar.title = "BottomAppBar"
        return ComposeView(inflater.context).apply {
            setContent {
                MyTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        ExpandableLayout { allExpandFlow ->
                            BottomAppBarSample(allExpandFlow)
                            BottomAppBarColorsSample(allExpandFlow)
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun BottomAppBarSample(allExpandFlow: Flow<Boolean>) {
    val items = remember {
        listOf(
            "首页" to R.drawable.ic_home,
            "通讯录" to R.drawable.ic_phone,
            "游戏" to R.drawable.ic_games,
            "设置" to R.drawable.ic_settings,
        )
    }
    val context = LocalContext.current
    ExpandableItem(title = "BottomAppBar", allExpandFlow, padding = 20.dp) {
        Column {
            BottomAppBar(
                actions = {
                    items.forEach {
                        IconButton(
                            modifier = Modifier.fillMaxHeight(),
                            onClick = { context.showShortToast(it.first) }
                        ) {
                            Icon(
                                painter = painterResource(id = it.second),
                                contentDescription = it.first
                            )
                        }
                    }
                },
                floatingActionButton = {
                    FloatingActionButton(onClick = { context.showShortToast("add") }) {
                        Icon(painter = painterResource(id = R.drawable.ic_add), contentDescription = "add")
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
                            Icon(
                                painter = painterResource(id = it.second),
                                contentDescription = it.first
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun BottomAppBarSamplePreview() {
    BottomAppBarSample(remember { MutableStateFlow(true) })
}


@Composable
fun BottomAppBarColorsSample(allExpandFlow: Flow<Boolean>) {
    val items = remember {
        listOf(
            "首页" to R.drawable.ic_home,
            "通讯录" to R.drawable.ic_phone,
            "游戏" to R.drawable.ic_games,
            "设置" to R.drawable.ic_settings,
        )
    }
    val context = LocalContext.current
    ExpandableItem(title = "BottomAppBar（colors）", allExpandFlow, padding = 20.dp) {
        Column {
            BottomAppBar(
                actions = {
                    items.forEach {
                        IconButton(
                            modifier = Modifier.fillMaxHeight(),
                            onClick = { context.showShortToast(it.first) }
                        ) {
                            Icon(
                                painter = painterResource(id = it.second),
                                contentDescription = "back"
                            )
                        }
                    }
                },
                floatingActionButton = {
                    FloatingActionButton(onClick = { context.showShortToast("add") }) {
                        Icon(painter = painterResource(id = R.drawable.ic_add), contentDescription = "add")
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
                            Icon(
                                painter = painterResource(id = it.second),
                                contentDescription = "back"
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun BottomAppBarColorsSamplePreview() {
    BottomAppBarColorsSample(remember { MutableStateFlow(true) })
}