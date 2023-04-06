package com.github.panpf.android.compose.samples.ui.material

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.github.panpf.android.compose.samples.ui.base.MaterialComposeAppBarFragment
import com.google.accompanist.flowlayout.FlowColumn

class DialogsFragment : MaterialComposeAppBarFragment() {

    override fun getTitle(): String {
        return "Dialogs - Material"
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
    FlowColumn(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        mainAxisSpacing = 20.dp,
        crossAxisSpacing = 20.dp,
    ) {
        Button(onClick = { defaultShowState.value = true }) {
            Text(text = "Show AlertDialog")
        }
        Button(onClick = { limitDismissShowState.value = true }) {
            Text(text = "Show Limit Dismiss AlertDialog")
        }
        Button(onClick = { shapeShowState.value = true }) {
            Text(text = "Show Rounded Corner AlertDialog")
        }
        Button(onClick = { colorsShowState.value = true }) {
            Text(text = "Show Blue AlertDialog")
        }
    }
    if (defaultShowState.value) {
        AlertDialogDefaultSample(showDialogState = defaultShowState)
    }
    if (limitDismissShowState.value) {
        AlertDialogLimitDismissSample(showDialogState = limitDismissShowState)
    }
    if (shapeShowState.value) {
        AlertDialogShapeSample(showDialogState = shapeShowState)
    }
    if (colorsShowState.value) {
        AlertDialogColorsSample(showDialogState = colorsShowState)
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun AlertDialogSamplePreview() {
    AlertDialogSample()
}


@Composable
private fun AlertDialogDefaultSample(showDialogState: MutableState<Boolean>) {
    AlertDialog(
        title = {
            Text(text = "提示")
        },
        text = {
            Text(text = "确定要退出 App 吗？")
        },
        confirmButton = {
            TextButton(onClick = { showDialogState.value = false }) {
                Text(text = "确定")
            }
        },
        dismissButton = {
            TextButton(onClick = { showDialogState.value = false }) {
                Text(text = "取消")
            }
        },
        onDismissRequest = {
            showDialogState.value = false
        },
    )
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun AlertDialogDefaultSamplePreview() {
    AlertDialogDefaultSample(remember { mutableStateOf(true) })
}


@Composable
private fun AlertDialogLimitDismissSample(showDialogState: MutableState<Boolean>) {
    AlertDialog(
        title = {
            Text(text = "提示")
        },
        text = {
            Text(text = "确定要退出 App 吗？")
        },
        confirmButton = {
            TextButton(onClick = { showDialogState.value = false }) {
                Text(text = "确定")
            }
        },
        dismissButton = {
            TextButton(onClick = { showDialogState.value = false }) {
                Text(text = "取消")
            }
        },
        onDismissRequest = {
            showDialogState.value = false
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
private fun AlertDialogShapeSample(showDialogState: MutableState<Boolean>) {
    AlertDialog(
        title = {
            Text(text = "提示")
        },
        text = {
            Text(text = "确定要退出 App 吗？")
        },
        confirmButton = {
            TextButton(onClick = { showDialogState.value = false }) {
                Text(text = "确定")
            }
        },
        dismissButton = {
            TextButton(onClick = { showDialogState.value = false }) {
                Text(text = "取消")
            }
        },
        onDismissRequest = {
            showDialogState.value = false
        },
        shape = RoundedCornerShape(20.dp)
    )
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun AlertDialogShapeSamplePreview() {
    AlertDialogShapeSample(remember { mutableStateOf(true) })
}


@Composable
private fun AlertDialogColorsSample(showDialogState: MutableState<Boolean>) {
    AlertDialog(
        title = {
            Text(text = "提示")
        },
        text = {
            Text(text = "确定要退出 App 吗？")
        },
        confirmButton = {
            TextButton(onClick = { showDialogState.value = false }) {
                Text(text = "确定", color = Color.White)
            }
        },
        dismissButton = {
            TextButton(onClick = { showDialogState.value = false }) {
                Text(text = "取消", color = Color.White)
            }
        },
        onDismissRequest = {
            showDialogState.value = false
        },
        backgroundColor = Color(0xFF7F7FFF),
        contentColor = Color.White,
    )
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun AlertDialogColorsSamplePreview() {
    AlertDialogColorsSample(remember { mutableStateOf(true) })
}