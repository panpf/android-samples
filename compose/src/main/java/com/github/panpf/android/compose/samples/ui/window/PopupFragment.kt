package com.github.panpf.android.compose.samples.ui.window

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
import com.google.accompanist.flowlayout.FlowColumn

class PopupFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "Popup"
    }

    @Composable
    override fun DrawContent() {
        PopupSample()
    }
}


@Composable
private fun PopupSample() {
    val defaultShowState = remember { mutableStateOf(false) }
    val limitDismissShowState = remember { mutableStateOf(false) }
    val offsetShowState = remember { mutableStateOf(false) }
    val alignmentTopCenterShowState = remember { mutableStateOf(false) }
    val alignmentTopEndShowState = remember { mutableStateOf(false) }
    val alignmentCenterStartShowState = remember { mutableStateOf(false) }
    val alignmentCenterShowState = remember { mutableStateOf(false) }
    val alignmentCenterEndShowState = remember { mutableStateOf(false) }
    val alignmentBottomStartShowState = remember { mutableStateOf(false) }
    val alignmentBottomCenterShowState = remember { mutableStateOf(false) }
    val alignmentBottomEndShowState = remember { mutableStateOf(false) }
    FlowColumn(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        mainAxisSpacing = 20.dp,
        crossAxisSpacing = 20.dp,
    ) {
        Button(onClick = { defaultShowState.value = true }) {
            Text(text = "Show Popup")
        }
        Button(onClick = { limitDismissShowState.value = true }) {
            Text(text = "Show LimitDismiss Popup")
        }
        Button(onClick = { offsetShowState.value = true }) {
            Text(text = "Show Offset Popup")
        }
        Button(onClick = { alignmentTopCenterShowState.value = true }) {
            Text(text = "Show TopCenter Popup")
        }
        Button(onClick = { alignmentTopEndShowState.value = true }) {
            Text(text = "Show TopEnd Popup")
        }
        Button(onClick = { alignmentCenterStartShowState.value = true }) {
            Text(text = "Show CenterStart Popup")
        }
        Button(onClick = { alignmentCenterShowState.value = true }) {
            Text(text = "Show Center Popup")
        }
        Button(onClick = { alignmentCenterEndShowState.value = true }) {
            Text(text = "Show CenterEnd Popup")
        }
        Button(onClick = { alignmentBottomStartShowState.value = true }) {
            Text(text = "Show BottomStart Popup")
        }
        Button(onClick = { alignmentBottomCenterShowState.value = true }) {
            Text(text = "Show BottomCenter Popup")
        }
        Button(onClick = { alignmentBottomEndShowState.value = true }) {
            Text(text = "Show BottomEnd Popup")
        }
    }
    if (defaultShowState.value) {
        PopupDefaultSample(showPopupState = defaultShowState)
    }
    if (limitDismissShowState.value) {
        PopupLimitDismissSample(showPopupState = limitDismissShowState)
    }
    if (offsetShowState.value) {
        PopupOffsetSample(showPopupState = offsetShowState)
    }
    if (alignmentTopCenterShowState.value) {
        PopupAlignmentTopCenterSample(showPopupState = alignmentTopCenterShowState)
    }
    if (alignmentTopEndShowState.value) {
        PopupAlignmentTopEndSample(showPopupState = alignmentTopEndShowState)
    }
    if (alignmentCenterStartShowState.value) {
        PopupAlignmentCenterStartSample(showPopupState = alignmentCenterStartShowState)
    }
    if (alignmentCenterShowState.value) {
        PopupAlignmentCenterSample(showPopupState = alignmentCenterShowState)
    }
    if (alignmentCenterEndShowState.value) {
        PopupAlignmentCenterEndSample(showPopupState = alignmentCenterEndShowState)
    }
    if (alignmentBottomStartShowState.value) {
        PopupAlignmentBottomStartSample(showPopupState = alignmentBottomStartShowState)
    }
    if (alignmentBottomCenterShowState.value) {
        PopupAlignmentBottomCenterSample(showPopupState = alignmentBottomCenterShowState)
    }
    if (alignmentBottomEndShowState.value) {
        PopupAlignmentBottomEndSample(showPopupState = alignmentBottomEndShowState)
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun PopupSamplePreview() {
    PopupSample()
}


@Composable
private fun PopupDefaultSample(showPopupState: MutableState<Boolean>) {
    Popup(
        onDismissRequest = {
            showPopupState.value = false
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
                        showPopupState.value = false
                    },
            ) {
                Text(text = "下一步", color = Color.White)
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun PopupDefaultSamplePreview() {
    PopupDefaultSample(remember { mutableStateOf(true) })
}


@Composable
private fun PopupLimitDismissSample(showPopupState: MutableState<Boolean>) {
    Popup(
        onDismissRequest = {
            showPopupState.value = false
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
                        showPopupState.value = false
                    },
            ) {
                Text(text = "下一步", color = Color.White)
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun PopupLimitDismissSamplePreview() {
    PopupLimitDismissSample(remember { mutableStateOf(true) })
}


@Composable
private fun PopupOffsetSample(showPopupState: MutableState<Boolean>) {
    Popup(
        onDismissRequest = {
            showPopupState.value = false
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
                        showPopupState.value = false
                    },
            ) {
                Text(text = "下一步", color = Color.White)
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun PopupOffsetSamplePreview() {
    PopupOffsetSample(remember { mutableStateOf(true) })
}


@Composable
private fun PopupAlignmentTopCenterSample(showPopupState: MutableState<Boolean>) {
    Popup(
        onDismissRequest = {
            showPopupState.value = false
        },
        alignment = Alignment.TopCenter
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
                        showPopupState.value = false
                    },
            ) {
                Text(text = "下一步", color = Color.White)
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun PopupAlignmentTopCenterSamplePreview() {
    PopupAlignmentTopCenterSample(remember { mutableStateOf(true) })
}


@Composable
private fun PopupAlignmentTopEndSample(showPopupState: MutableState<Boolean>) {
    Popup(
        onDismissRequest = {
            showPopupState.value = false
        },
        alignment = Alignment.TopEnd
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
                        showPopupState.value = false
                    },
            ) {
                Text(text = "下一步", color = Color.White)
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun PopupAlignmentTopEndSamplePreview() {
    PopupAlignmentTopEndSample(remember { mutableStateOf(true) })
}


@Composable
private fun PopupAlignmentCenterStartSample(showPopupState: MutableState<Boolean>) {
    Popup(
        onDismissRequest = {
            showPopupState.value = false
        },
        alignment = Alignment.CenterStart
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
                        showPopupState.value = false
                    },
            ) {
                Text(text = "下一步", color = Color.White)
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun PopupAlignmentCenterStartSamplePreview() {
    PopupAlignmentCenterStartSample(remember { mutableStateOf(true) })
}


@Composable
private fun PopupAlignmentCenterSample(showPopupState: MutableState<Boolean>) {
    Popup(
        onDismissRequest = {
            showPopupState.value = false
        },
        alignment = Alignment.Center
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
                        showPopupState.value = false
                    },
            ) {
                Text(text = "下一步", color = Color.White)
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun PopupAlignmentCenterSamplePreview() {
    PopupAlignmentCenterSample(remember { mutableStateOf(true) })
}


@Composable
private fun PopupAlignmentCenterEndSample(showPopupState: MutableState<Boolean>) {
    Popup(
        onDismissRequest = {
            showPopupState.value = false
        },
        alignment = Alignment.CenterEnd
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
                        showPopupState.value = false
                    },
            ) {
                Text(text = "下一步", color = Color.White)
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun PopupAlignmentCenterEndSamplePreview() {
    PopupAlignmentCenterEndSample(remember { mutableStateOf(true) })
}


@Composable
private fun PopupAlignmentBottomStartSample(showPopupState: MutableState<Boolean>) {
    Popup(
        onDismissRequest = {
            showPopupState.value = false
        },
        alignment = Alignment.BottomStart
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
                        showPopupState.value = false
                    },
            ) {
                Text(text = "下一步", color = Color.White)
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun PopupAlignmentBottomStartSamplePreview() {
    PopupAlignmentBottomStartSample(remember { mutableStateOf(true) })
}


@Composable
private fun PopupAlignmentBottomCenterSample(showPopupState: MutableState<Boolean>) {
    Popup(
        onDismissRequest = {
            showPopupState.value = false
        },
        alignment = Alignment.BottomCenter
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
                        showPopupState.value = false
                    },
            ) {
                Text(text = "下一步", color = Color.White)
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun PopupAlignmentBottomCenterSamplePreview() {
    PopupAlignmentBottomCenterSample(remember { mutableStateOf(true) })
}


@Composable
private fun PopupAlignmentBottomEndSample(showPopupState: MutableState<Boolean>) {
    Popup(
        onDismissRequest = {
            showPopupState.value = false
        },
        alignment = Alignment.BottomEnd
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
                        showPopupState.value = false
                    },
            ) {
                Text(text = "下一步", color = Color.White)
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun PopupAlignmentBottomEndSamplePreview() {
    PopupAlignmentBottomEndSample(remember { mutableStateOf(true) })
}