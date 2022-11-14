package com.github.panpf.android.compose.samples.ui.components.basic

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem3
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class DialogFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "Dialog"
    }

    @Composable
    override fun DrawContent() {
        ExpandableLayout { allExpandFlow ->
            DialogSample(allExpandFlow)
            DialogLimitDismissSample(allExpandFlow)
        }
    }
}


@Composable
private fun DialogSample(allExpandFlow: Flow<Boolean>) {
    val openDialog = remember { mutableStateOf(false) }
    ExpandableItem3(title = "Dialog", allExpandFlow, padding = 20.dp) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Button(
                onClick = {
                    openDialog.value = true
                }
            ) {
                Text(text = "Show Dialog")
            }
            if (openDialog.value) {
                Dialog(
                    onDismissRequest = {
                        openDialog.value = false
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
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun DialogSamplePreview() {
    DialogSample(remember { MutableStateFlow(true) })
}


@Composable
private fun DialogLimitDismissSample(allExpandFlow: Flow<Boolean>) {
    val openDialog = remember { mutableStateOf(false) }
    ExpandableItem3(title = "Dialog（LimitDismiss）", allExpandFlow, padding = 20.dp) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Button(
                onClick = {
                    openDialog.value = true
                }
            ) {
                Text(text = "Show Limit Dismiss Dialog")
            }
            if (openDialog.value) {
                Dialog(
                    onDismissRequest = {
                        openDialog.value = false
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
                                openDialog.value = false
                            },
                            modifier = Modifier.align(Alignment.End)
                        ) {
                            Text(text = "关闭")
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun DialogLimitDismissSamplePreview() {
    DialogLimitDismissSample(remember { MutableStateFlow(true) })
}