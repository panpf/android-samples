package com.github.panpf.android.compose.samples.ui.material3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.github.panpf.android.compose.samples.R
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.ToolbarFragment
import com.github.panpf.android.compose.samples.ui.base.theme.MyTheme
import com.google.accompanist.flowlayout.FlowRow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class AlertDialogFragment : ToolbarFragment() {

    override fun createView(
        toolbar: Toolbar,
        inflater: LayoutInflater,
        parent: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        toolbar.title = "AlertDialog"
        return ComposeView(inflater.context).apply {
            setContent {
                MyTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        ExpandableLayout { allExpandFlow ->
                            AlertDialogSample(allExpandFlow)
                            AlertDialogLimitDismissSample(allExpandFlow)
                            AlertDialogShapeSample(allExpandFlow)
                            AlertDialogColorsSample(allExpandFlow)
                            AlertDialogTonalElevationSample(allExpandFlow)
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun AlertDialogSample(allExpandFlow: Flow<Boolean>) {
    val openDialog = remember { mutableStateOf(false) }
    ExpandableItem(title = "AlertDialog", allExpandFlow, padding = 20.dp) {
        Box(modifier = Modifier.fillMaxWidth()) {
            FlowRow(mainAxisSpacing = 10.dp, crossAxisSpacing = 10.dp) {
                Button(
                    onClick = {
                        openDialog.value = true
                    }
                ) {
                    Text(text = "Show AlertDialog")
                }
            }
            if (openDialog.value) {
                AlertDialog(
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_info),
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
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun AlertDialogSamplePreview() {
    AlertDialogSample(remember { MutableStateFlow(false) })
}


@Composable
fun AlertDialogLimitDismissSample(allExpandFlow: Flow<Boolean>) {
    val openDialog = remember { mutableStateOf(false) }
    ExpandableItem(title = "AlertDialog（LimitDismiss）", allExpandFlow, padding = 20.dp) {
        Box(modifier = Modifier.fillMaxWidth()) {
            FlowRow(mainAxisSpacing = 10.dp, crossAxisSpacing = 10.dp) {
                Button(
                    onClick = {
                        openDialog.value = true
                    }
                ) {
                    Text(text = "Show Limit Dismiss AlertDialog")
                }
            }
            if (openDialog.value) {
                AlertDialog(
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_info),
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
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun AlertDialogLimitDismissSamplePreview() {
    AlertDialogLimitDismissSample(remember { MutableStateFlow(true) })
}


@Composable
fun AlertDialogShapeSample(allExpandFlow: Flow<Boolean>) {
    val openDialog = remember { mutableStateOf(false) }
    ExpandableItem(title = "AlertDialog（shape）", allExpandFlow, padding = 20.dp) {
        Box(modifier = Modifier.fillMaxWidth()) {
            FlowRow(mainAxisSpacing = 10.dp, crossAxisSpacing = 10.dp) {
                Button(
                    onClick = {
                        openDialog.value = true
                    }
                ) {
                    Text(text = "Show Rectangle AlertDialog")
                }
            }
            if (openDialog.value) {
                AlertDialog(
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_info),
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
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun AlertDialogShapeSamplePreview() {
    AlertDialogShapeSample(remember { MutableStateFlow(true) })
}


@Composable
fun AlertDialogColorsSample(allExpandFlow: Flow<Boolean>) {
    val openDialog = remember { mutableStateOf(false) }
    ExpandableItem(title = "AlertDialog（colors）", allExpandFlow, padding = 20.dp) {
        Box(modifier = Modifier.fillMaxWidth()) {
            FlowRow(mainAxisSpacing = 10.dp, crossAxisSpacing = 10.dp) {
                Button(
                    onClick = {
                        openDialog.value = true
                    }
                ) {
                    Text(text = "Show Rectangle AlertDialog")
                }
            }
            if (openDialog.value) {
                AlertDialog(
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_info),
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
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun AlertDialogColorsSamplePreview() {
    AlertDialogColorsSample(remember { MutableStateFlow(true) })
}


@Composable
fun AlertDialogTonalElevationSample(allExpandFlow: Flow<Boolean>) {
    val openDialog = remember { mutableStateOf(false) }
    ExpandableItem(title = "AlertDialog（tonalElevation）", allExpandFlow, padding = 20.dp) {
        Box(modifier = Modifier.fillMaxWidth()) {
            FlowRow(mainAxisSpacing = 10.dp, crossAxisSpacing = 10.dp) {
                Button(
                    onClick = {
                        openDialog.value = true
                    }
                ) {
                    Text(text = "Show tonalElevation AlertDialog")
                }
            }
            if (openDialog.value) {
                AlertDialog(
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_info),
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
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun AlertDialogTonalElevationSamplePreview() {
    AlertDialogTonalElevationSample(remember { MutableStateFlow(true) })
}