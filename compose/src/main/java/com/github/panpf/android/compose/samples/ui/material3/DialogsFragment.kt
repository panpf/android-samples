package com.github.panpf.android.compose.samples.ui.material3

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
import com.google.accompanist.flowlayout.FlowRow

class DialogsFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "Dialogs - Material3"
    }

    @Composable
    override fun DrawContent() {
        AlertDialogSample()
    }
}


@Composable
private fun AlertDialogSample() {
    val defaultShowState = remember { mutableStateOf(false) }
    val limitDismissShowState = remember { mutableStateOf(false) }
    val shapeShowState = remember { mutableStateOf(false) }
    val colorsShowState = remember { mutableStateOf(false) }
    val tonalElevationShowState = remember { mutableStateOf(false) }
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
                    Text(text = "Show AlertDialog")
                }
                Button(onClick = { limitDismissShowState.value = true }) {
                    Text(text = "Show Limit Dismiss AlertDialog")
                }
                Button(onClick = { shapeShowState.value = true }) {
                    Text(text = "Show Rectangle AlertDialog")
                }
                Button(onClick = { colorsShowState.value = true }) {
                    Text(text = "Show Blue AlertDialog")
                }
                Button(onClick = { colorsShowState.value = true }) {
                    Text(text = "Show tonalElevation AlertDialog")
                }
            }
            if (defaultShowState.value) {
                AlertDialogDefaultSample(openDialog = defaultShowState)
            }
            if (limitDismissShowState.value) {
                AlertDialogLimitDismissSample(openDialog = limitDismissShowState)
            }
            if (shapeShowState.value) {
                AlertDialogShapeSample(openDialog = shapeShowState)
            }
            if (colorsShowState.value) {
                AlertDialogColorsSample(openDialog = colorsShowState)
            }
            if (tonalElevationShowState.value) {
                AlertDialogTonalElevationSample(openDialog = tonalElevationShowState)
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun AlertDialogSamplePreview() {
    AlertDialogSample()
}


@Composable
private fun AlertDialogDefaultSample(openDialog: MutableState<Boolean>) {
    AlertDialog(
        icon = {
            Icon(
                imageVector = Icons.Filled.Info,
                contentDescription = "icon"
            )
        },
        title = {
            Text(text = "提示")
        },
        text = {
            Text(text = "确定要退出 App 吗？")
        },
        confirmButton = {
            TextButton(
                onClick = {
                    openDialog.value = false
                }
            ) {
                Text(text = "确定")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    openDialog.value = false
                }
            ) {
                Text(text = "取消")
            }
        },
        onDismissRequest = {
            openDialog.value = false
        }
    )
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun AlertDialogDefaultSamplePreview() {
    AlertDialogDefaultSample(remember { mutableStateOf(true) })
}


@Composable
private fun AlertDialogLimitDismissSample(openDialog: MutableState<Boolean>) {
    AlertDialog(
        icon = {
            Icon(
                imageVector = Icons.Filled.Info,
                contentDescription = "icon"
            )
        },
        title = {
            Text(text = "提示")
        },
        text = {
            Text(text = "确定要退出 App 吗？")
        },
        confirmButton = {
            TextButton(
                onClick = {
                    openDialog.value = false
                }
            ) {
                Text(text = "确定")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    openDialog.value = false
                }
            ) {
                Text(text = "取消")
            }
        },
        onDismissRequest = {
            openDialog.value = false
        },
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false
        )
    )
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun AlertDialogLimitDismissSamplePreview() {
    AlertDialogLimitDismissSample(remember { mutableStateOf(true) })
}


@Composable
private fun AlertDialogShapeSample(openDialog: MutableState<Boolean>) {
    AlertDialog(
        icon = {
            Icon(
                imageVector = Icons.Filled.Info,
                contentDescription = "icon"
            )
        },
        title = {
            Text(text = "提示")
        },
        text = {
            Text(text = "确定要退出 App 吗？")
        },
        confirmButton = {
            TextButton(
                onClick = {
                    openDialog.value = false
                }
            ) {
                Text(text = "确定")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    openDialog.value = false
                }
            ) {
                Text(text = "取消")
            }
        },
        onDismissRequest = {
            openDialog.value = false
        },
        shape = androidx.compose.ui.graphics.RectangleShape
    )
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun AlertDialogShapeSamplePreview() {
    AlertDialogShapeSample(remember { mutableStateOf(true) })
}


@Composable
private fun AlertDialogColorsSample(openDialog: MutableState<Boolean>) {
    AlertDialog(
        icon = {
            Icon(
                imageVector = Icons.Filled.Info,
                contentDescription = "icon",
            )
        },
        title = {
            Text(text = "提示")
        },
        text = {
            Text(text = "确定要退出 App 吗？")
        },
        confirmButton = {
            TextButton(
                onClick = {
                    openDialog.value = false
                }
            ) {
                Text(text = "确定", color = Color.White)
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    openDialog.value = false
                }
            ) {
                Text(text = "取消", color = Color.White)
            }
        },
        onDismissRequest = {
            openDialog.value = false
        },
        containerColor = Color(0xFF7F7FFF),
        iconContentColor = Color.White,
        titleContentColor = Color.White,
        textContentColor = Color.White,
    )
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun AlertDialogColorsSamplePreview() {
    AlertDialogColorsSample(remember { mutableStateOf(true) })
}


@Composable
private fun AlertDialogTonalElevationSample(openDialog: MutableState<Boolean>) {
    AlertDialog(
        icon = {
            Icon(
                imageVector = Icons.Filled.Info,
                contentDescription = "icon",
            )
        },
        title = {
            Text(text = "提示")
        },
        text = {
            Text(text = "确定要退出 App 吗？")
        },
        confirmButton = {
            TextButton(
                onClick = {
                    openDialog.value = false
                }
            ) {
                Text(text = "确定")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    openDialog.value = false
                }
            ) {
                Text(text = "取消")
            }
        },
        onDismissRequest = {
            openDialog.value = false
        },
        tonalElevation = 100.dp,
    )
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun AlertDialogTonalElevationSamplePreview() {
    AlertDialogTonalElevationSample(remember { mutableStateOf(true) })
}