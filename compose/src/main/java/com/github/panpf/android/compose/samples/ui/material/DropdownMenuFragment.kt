package com.github.panpf.android.compose.samples.ui.material

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties
import com.github.panpf.android.compose.samples.R
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.ToolbarFragment
import com.github.panpf.android.compose.samples.ui.base.theme.MyTheme
import com.github.panpf.tools4a.toast.ktx.showShortToast
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class DropdownMenuFragment : ToolbarFragment() {

    override fun createView(
        toolbar: Toolbar,
        inflater: LayoutInflater,
        parent: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        toolbar.title = "DropdownMenu"
        return ComposeView(inflater.context).apply {
            setContent {
                MyTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colors.background
                    ) {
                        ExpandableLayout { allExpandFlow ->
                            DropdownMenuSample(allExpandFlow)
                            DropdownMenuOffsetSample(allExpandFlow)
                            DropdownMenuLimitDismissSample(allExpandFlow)
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun DropdownMenuSample(allExpandFlow: Flow<Boolean>) {
    val expanded = remember { mutableStateOf(false) }
    val items = remember {
        listOf(
            "首页" to R.drawable.ic_home,
            "通讯录" to R.drawable.ic_phone,
            "游戏" to R.drawable.ic_games,
            "设置" to R.drawable.ic_settings,
        )
    }
    val context = LocalContext.current
    ExpandableItem(title = "DropdownMenu", allExpandFlow, padding = 20.dp) {
        Button(onClick = { expanded.value = true }) {
            Text(text = "Show DropdownMenu")
        }
        DropdownMenu(
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false }
        ) {
            items.forEach { pair ->
                DropdownMenuItem(
                    onClick = {
                        context.showShortToast(pair.first)
                        expanded.value = false
                    }
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = pair.second),
                            contentDescription = pair.first
                        )
                        Spacer(modifier = Modifier.size(16.dp))
                        Text(text = pair.first, modifier = Modifier.weight(1f))
                        Spacer(modifier = Modifier.size(16.dp))
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow_right),
                            contentDescription = pair.first
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun DropdownMenuSamplePreview() {
    DropdownMenuSample(remember { MutableStateFlow(true) })
}


@Composable
fun DropdownMenuOffsetSample(allExpandFlow: Flow<Boolean>) {
    val expanded = remember { mutableStateOf(false) }
    val items = remember {
        listOf(
            "首页" to R.drawable.ic_home,
            "通讯录" to R.drawable.ic_phone,
            "游戏" to R.drawable.ic_games,
            "设置" to R.drawable.ic_settings,
        )
    }
    val context = LocalContext.current
    ExpandableItem(title = "DropdownMenu（offset）", allExpandFlow, padding = 20.dp) {
        Button(onClick = { expanded.value = true }) {
            Text(text = "Show Offset DropdownMenu")
        }
        DropdownMenu(
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false },
            offset = DpOffset(x = 50.dp, y = 0.dp)
        ) {
            items.forEach { pair ->
                DropdownMenuItem(
                    onClick = {
                        context.showShortToast(pair.first)
                        expanded.value = false
                    }
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = pair.second),
                            contentDescription = pair.first
                        )
                        Spacer(modifier = Modifier.size(16.dp))
                        Text(text = pair.first, modifier = Modifier.weight(1f))
                        Spacer(modifier = Modifier.size(16.dp))
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow_right),
                            contentDescription = pair.first
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun DropdownMenuOffsetSamplePreview() {
    DropdownMenuOffsetSample(remember { MutableStateFlow(true) })
}


@Composable
fun DropdownMenuLimitDismissSample(allExpandFlow: Flow<Boolean>) {
    val expanded = remember { mutableStateOf(false) }
    val items = remember {
        listOf(
            "首页" to R.drawable.ic_home,
            "通讯录" to R.drawable.ic_phone,
            "游戏" to R.drawable.ic_games,
            "设置" to R.drawable.ic_settings,
        )
    }
    val context = LocalContext.current
    ExpandableItem(title = "DropdownMenu（LimitDismiss）", allExpandFlow, padding = 20.dp) {
        Button(onClick = { expanded.value = true }) {
            Text(text = "Show Limit Dismiss DropdownMenu")
        }
        DropdownMenu(
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false },
            properties = PopupProperties(
                focusable = true,
                dismissOnBackPress = false,
                dismissOnClickOutside = false
            )
        ) {
            items.forEach { pair ->
                DropdownMenuItem(
                    onClick = {
                        context.showShortToast(pair.first)
                        expanded.value = false
                    }
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = pair.second),
                            contentDescription = pair.first
                        )
                        Spacer(modifier = Modifier.size(16.dp))
                        Text(text = pair.first, modifier = Modifier.weight(1f))
                        Spacer(modifier = Modifier.size(16.dp))
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow_right),
                            contentDescription = pair.first
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun DropdownMenuLimitDismissSamplePreview() {
    DropdownMenuLimitDismissSample(remember { MutableStateFlow(true) })
}