package com.github.panpf.android.compose.samples.ui.window

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem3
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class PopupFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "Popup"
    }

    @Composable
    override fun DrawContent() {
        ExpandableLayout { allExpandFlow ->
            PopupSample(allExpandFlow)  // todo merge
            PopupLimitDismissSample(allExpandFlow)
            PopupOffsetSample(allExpandFlow)
        }
    }
}


@Composable
private fun PopupSample(allExpandFlow: Flow<Boolean>) {
    val openPopup = remember { mutableStateOf(false) }
    ExpandableItem3(title = "Popup", allExpandFlow, padding = 20.dp) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Button(
                onClick = {
                    openPopup.value = true
                }
            ) {
                Text(text = "Show Popup")
            }
            if (openPopup.value) {
                Popup(
                    onDismissRequest = {
                        openPopup.value = false
                    },
                ) {
                    Surface(
                        shape = RoundedCornerShape(50),
                        color = Color.Black.copy(alpha = 0.8f),
                        tonalElevation = 10.dp,
                        shadowElevation = 10.dp,
                    ) {
                        Box(
                            modifier = Modifier
                                .padding(horizontal = 16.dp, vertical = 8.dp)
                                .clickable {
                                    openPopup.value = false
                                },
                        ) {
                            Text(text = "下一步", color = Color.White)
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun PopupSamplePreview() {
    PopupSample(remember { MutableStateFlow(true) })
}


@Composable
private fun PopupLimitDismissSample(allExpandFlow: Flow<Boolean>) {
    val openPopup = remember { mutableStateOf(false) }
    ExpandableItem3(title = "Popup（LimitDismiss）", allExpandFlow, padding = 20.dp) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Button(
                onClick = {
                    openPopup.value = true
                }
            ) {
                Text(text = "Show Limit Dismiss Popup")
            }
            if (openPopup.value) {
                Popup(
                    onDismissRequest = {
                        openPopup.value = false
                    },
                    properties = PopupProperties(
                        dismissOnBackPress = false,
                        dismissOnClickOutside = false
                    )
                ) {
                    Surface(
                        shape = RoundedCornerShape(50),
                        color = Color.Black.copy(alpha = 0.8f),
                        tonalElevation = 10.dp,
                        shadowElevation = 10.dp,
                    ) {
                        Box(
                            modifier = Modifier
                                .padding(horizontal = 16.dp, vertical = 8.dp)
                                .clickable {
                                    openPopup.value = false
                                },
                        ) {
                            Text(text = "下一步", color = Color.White)
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun PopupLimitDismissSamplePreview() {
    PopupLimitDismissSample(remember { MutableStateFlow(true) })
}


@Composable
private fun PopupOffsetSample(allExpandFlow: Flow<Boolean>) {
    val openPopup = remember { mutableStateOf(false) }
    ExpandableItem3(title = "Popup（offset）", allExpandFlow, padding = 20.dp) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Button(
                onClick = {
                    openPopup.value = true
                }
            ) {
                Text(text = "Show Offset Popup")
            }
            if (openPopup.value) {
                Popup(
                    onDismissRequest = {
                        openPopup.value = false
                    },
                    offset = IntOffset(100, 80)
                ) {
                    Surface(
                        shape = RoundedCornerShape(50),
                        color = Color.Black.copy(alpha = 0.8f),
                        tonalElevation = 10.dp,
                        shadowElevation = 10.dp,
                    ) {
                        Box(
                            modifier = Modifier
                                .padding(horizontal = 16.dp, vertical = 8.dp)
                                .clickable {
                                    openPopup.value = false
                                },
                        ) {
                            Text(text = "下一步", color = Color.White)
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun PopupOffsetSamplePreview() {
    PopupOffsetSample(remember { MutableStateFlow(true) })
}