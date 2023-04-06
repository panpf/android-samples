package com.github.panpf.android.compose.samples.ui.window

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
import com.google.accompanist.flowlayout.FlowColumn

class DialogFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "Dialog"
    }

    @Composable
    override fun DrawContent() {
        DialogSample()
    }
}


@Composable
private fun DialogSample() {
    val defaultShowState = remember { mutableStateOf(false) }
    val limitDismissShowState = remember { mutableStateOf(false) }
    FlowColumn(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        mainAxisSpacing = 20.dp,
        crossAxisSpacing = 20.dp,
    ) {
        Button(
            onClick = {
                defaultShowState.value = true
            }
        ) {
            Text(text = "Show Dialog")
        }
        Button(
            onClick = {
                limitDismissShowState.value = true
            }
        ) {
            Text(text = "Show LimitDismiss Dialog")
        }
    }
    if (defaultShowState.value) {
        DialogDefaultSample(defaultShowState)
    }
    if (limitDismissShowState.value) {
        DialogLimitDismissSample(limitDismissShowState)
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun DialogSamplePreview() {
    DialogSample()
}


@Composable
private fun DialogDefaultSample(showDialogState: MutableState<Boolean>) {
    Dialog(
        onDismissRequest = {
            showDialogState.value = false
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(20.dp)
        ) {
            Text(text = "这是一个内容全靠自定义的普通的不能再不普通的 Dialog")
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun DialogDefaultSamplePreview() {
    DialogDefaultSample(remember { mutableStateOf(true) })
}


@Composable
private fun DialogLimitDismissSample(showDialogState: MutableState<Boolean>) {
    Dialog(
        onDismissRequest = {
            showDialogState.value = false
        },
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(20.dp)
        ) {
            Text(text = "这是一个内容全靠自定义的且无法通过点击返回键和其它区域关闭的 Dialog")
            Spacer(modifier = Modifier.size(20.dp))
            TextButton(
                onClick = {
                    showDialogState.value = false
                },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text(text = "关闭")
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun DialogLimitDismissSamplePreview() {
    DialogLimitDismissSample(remember { mutableStateOf(true) })
}