package com.github.panpf.android.compose.samples.ui.material

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties
import com.github.panpf.android.compose.samples.ui.base.MaterialComposeAppBarFragment
import com.github.panpf.tools4a.toast.ktx.showShortToast
import com.google.accompanist.flowlayout.FlowRow

class MenusFragment : MaterialComposeAppBarFragment() {

    override fun getTitle(): String {
        return "Menus - Material"
    }

    @Composable
    override fun DrawContent() {
        DropdownMenuSample()
    }
}


@Composable
private fun DropdownMenuSample() {
    val defaultShowState = remember { mutableStateOf(false) }
    val offsetShowState = remember { mutableStateOf(false) }
    val limitDismissShowState = remember { mutableStateOf(false) }
    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        mainAxisSpacing = 20.dp,
        crossAxisSpacing = 20.dp,
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            FlowRow(mainAxisSpacing = 10.dp, crossAxisSpacing = 10.dp) {
                Button(onClick = { defaultShowState.value = true }) {
                    Text(text = "Show DropdownMenu")
                }
                Button(onClick = { offsetShowState.value = true }) {
                    Text(text = "Show Offset DropdownMenu")
                }
                Button(onClick = { limitDismissShowState.value = true }) {
                    Text(text = "Show Limit Dismiss DropdownMenu")
                }
            }
            if (defaultShowState.value) {
                DropdownMenuDefaultSample(expandedState = defaultShowState)
            }
            if (offsetShowState.value) {
                DropdownMenuOffsetSample(expandedState = offsetShowState)
            }
            if (limitDismissShowState.value) {
                DropdownMenuLimitDismissSample(expandedState = limitDismissShowState)
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun DropdownMenuSamplePreview() {
    DropdownMenuSample()
}


@Composable
private fun DropdownMenuDefaultSample(expandedState: MutableState<Boolean>) {
    val items = remember {
        listOf(
            "首页" to Icons.Filled.Home,
            "通讯录" to Icons.Filled.Phone,
            "游戏" to Icons.Filled.PlayArrow,
            "设置" to Icons.Filled.Settings,
        )
    }
    val context = LocalContext.current
    DropdownMenu(
        expanded = expandedState.value,
        onDismissRequest = { expandedState.value = false }
    ) {
        items.forEach { pair ->
            DropdownMenuItem(
                onClick = {
                    context.showShortToast(pair.first)
                    expandedState.value = false
                }
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(imageVector = pair.second, contentDescription = pair.first)
                    Spacer(modifier = Modifier.size(16.dp))
                    Text(text = pair.first, modifier = Modifier.weight(1f))
                    Spacer(modifier = Modifier.size(16.dp))
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowRight,
                        contentDescription = "open"
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun DropdownMenuDefaultSamplePreview() {
    DropdownMenuDefaultSample(remember { mutableStateOf(true) })
}


@Composable
private fun DropdownMenuOffsetSample(expandedState: MutableState<Boolean>) {
    val items = remember {
        listOf(
            "首页" to Icons.Filled.Home,
            "通讯录" to Icons.Filled.Phone,
            "游戏" to Icons.Filled.PlayArrow,
            "设置" to Icons.Filled.Settings,
        )
    }
    val context = LocalContext.current
    DropdownMenu(
        expanded = expandedState.value,
        onDismissRequest = { expandedState.value = false },
        offset = DpOffset(x = 50.dp, y = 0.dp)
    ) {
        items.forEach { pair ->
            DropdownMenuItem(
                onClick = {
                    context.showShortToast(pair.first)
                    expandedState.value = false
                }
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(imageVector = pair.second, contentDescription = pair.first)
                    Spacer(modifier = Modifier.size(16.dp))
                    Text(text = pair.first, modifier = Modifier.weight(1f))
                    Spacer(modifier = Modifier.size(16.dp))
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowRight,
                        contentDescription = "open"
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun DropdownMenuOffsetSamplePreview() {
    DropdownMenuOffsetSample(remember { mutableStateOf(true) })
}


@Composable
private fun DropdownMenuLimitDismissSample(expandedState: MutableState<Boolean>) {
    val items = remember {
        listOf(
            "首页" to Icons.Filled.Home,
            "通讯录" to Icons.Filled.Phone,
            "游戏" to Icons.Filled.PlayArrow,
            "设置" to Icons.Filled.Settings,
        )
    }
    val context = LocalContext.current
    DropdownMenu(
        expanded = expandedState.value,
        onDismissRequest = { expandedState.value = false },
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
                    expandedState.value = false
                }
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(imageVector = pair.second, contentDescription = pair.first)
                    Spacer(modifier = Modifier.size(16.dp))
                    Text(text = pair.first, modifier = Modifier.weight(1f))
                    Spacer(modifier = Modifier.size(16.dp))
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowRight,
                        contentDescription = "open"
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun DropdownMenuLimitDismissSamplePreview() {
    DropdownMenuLimitDismissSample(remember { mutableStateOf(true) })
}