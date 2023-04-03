package com.github.panpf.android.compose.samples.ui.material3

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem3
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
import com.github.panpf.tools4a.toast.ktx.showShortToast
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class MenusFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "Menus - Material3"
    }

    @Composable
    override fun DrawContent() {
        ExpandableLayout { allExpandFlow ->
            DropdownMenuSample(allExpandFlow)
            DropdownMenuOffsetSample(allExpandFlow)
            DropdownMenuLimitDismissSample(allExpandFlow)
        }
    }
}


@Composable
private fun DropdownMenuSample(allExpandFlow: Flow<Boolean>) {
    val expanded = remember { mutableStateOf(false) }
    val items = remember {
        listOf(
            "首页" to Icons.Filled.Home,
            "通讯录" to Icons.Filled.Phone,
            "游戏" to Icons.Filled.PlayArrow,
            "设置" to Icons.Filled.Settings,
        )
    }
    val context = LocalContext.current
    ExpandableItem3(title = "DropdownMenu", allExpandFlow, padding = 20.dp) {
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
                        Icon(imageVector = pair.second, contentDescription = pair.first)
                    },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Filled.KeyboardArrowRight,
                            contentDescription = "open"
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
private fun DropdownMenuSamplePreview() {
    DropdownMenuSample(remember { MutableStateFlow(true) })
}


@Composable
private fun DropdownMenuOffsetSample(allExpandFlow: Flow<Boolean>) {
    val expanded = remember { mutableStateOf(false) }
    val items = remember {
        listOf(
            "首页" to Icons.Filled.Home,
            "通讯录" to Icons.Filled.Phone,
            "游戏" to Icons.Filled.PlayArrow,
            "设置" to Icons.Filled.Settings,
        )
    }
    val context = LocalContext.current
    ExpandableItem3(title = "DropdownMenu（offset）", allExpandFlow, padding = 20.dp) {
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
                        Icon(imageVector = pair.second, contentDescription = pair.first)
                    },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Filled.KeyboardArrowRight,
                            contentDescription = "open"
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
private fun DropdownMenuOffsetSamplePreview() {
    DropdownMenuOffsetSample(remember { MutableStateFlow(true) })
}


@Composable
private fun DropdownMenuLimitDismissSample(allExpandFlow: Flow<Boolean>) {
    val expanded = remember { mutableStateOf(false) }
    val items = remember {
        listOf(
            "首页" to Icons.Filled.Home,
            "通讯录" to Icons.Filled.Phone,
            "游戏" to Icons.Filled.PlayArrow,
            "设置" to Icons.Filled.Settings,
        )
    }
    val context = LocalContext.current
    ExpandableItem3(title = "DropdownMenu（LimitDismiss）", allExpandFlow, padding = 20.dp) {
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
                        Icon(imageVector = pair.second, contentDescription = pair.first)
                    },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Filled.KeyboardArrowRight,
                            contentDescription = "open"
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
private fun DropdownMenuLimitDismissSamplePreview() {
    DropdownMenuLimitDismissSample(remember { MutableStateFlow(true) })
}