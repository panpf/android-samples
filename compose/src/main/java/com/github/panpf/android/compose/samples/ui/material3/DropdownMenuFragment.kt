package com.github.panpf.android.compose.samples.ui.material3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.github.panpf.android.compose.samples.ui.base.theme.MyTheme3
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
        toolbar.title = "DropdownMenu - Material3"
        return ComposeView(inflater.context).apply {
            setContent {
                MyTheme3 {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
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
                    text = { Text(text = pair.first) },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = pair.second),
                            contentDescription = pair.first
                        )
                    },
                    trailingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow_right),
                            contentDescription = pair.first
                        )
                    },
                    onClick = {
                        context.showShortToast(pair.first)
                        expanded.value = false
                    }
                )
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
                    text = { Text(text = pair.first) },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = pair.second),
                            contentDescription = pair.first
                        )
                    },
                    trailingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow_right),
                            contentDescription = pair.first
                        )
                    },
                    onClick = {
                        context.showShortToast(pair.first)
                        expanded.value = false
                    }
                )
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
                    text = { Text(text = pair.first) },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = pair.second),
                            contentDescription = pair.first
                        )
                    },
                    trailingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow_right),
                            contentDescription = pair.first
                        )
                    },
                    onClick = {
                        context.showShortToast(pair.first)
                        expanded.value = false
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun DropdownMenuLimitDismissSamplePreview() {
    DropdownMenuLimitDismissSample(remember { MutableStateFlow(true) })
}