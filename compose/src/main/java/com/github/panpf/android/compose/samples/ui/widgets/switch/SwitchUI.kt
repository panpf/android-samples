package com.github.panpf.android.compose.samples.ui.widgets.switch

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun SwitchUI() {
    ExpandableLayout { allExpandFlow ->
        SwitchSample(allExpandFlow)
        SwitchEnabledFalseSample(allExpandFlow)
        SwitchColorsSample(allExpandFlow)
        SwitchGroupSingleSample(allExpandFlow)
        SwitchGroupMultiSample(allExpandFlow)
    }
}


@Composable
fun SwitchSample(allExpandFlow: Flow<Boolean>) {
    val checked = remember { mutableStateOf(false) }
    ExpandableItem(title = "Switch", allExpandFlow, padding = 20.dp) {
        Switch(
            checked = checked.value,
            onCheckedChange = { checked.value = it }
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun SwitchSamplePreview() {
    SwitchSample(remember { MutableStateFlow(true) })
}


@Composable
fun SwitchEnabledFalseSample(allExpandFlow: Flow<Boolean>) {
    val checked = remember { mutableStateOf(false) }
    ExpandableItem(title = "Switch（enabled - false）", allExpandFlow, padding = 20.dp) {
        Switch(
            checked = checked.value,
            onCheckedChange = { checked.value = it },
            enabled = false,
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun SwitchEnabledFalseSamplePreview() {
    SwitchEnabledFalseSample(remember { MutableStateFlow(true) })
}


@Composable
fun SwitchColorsSample(allExpandFlow: Flow<Boolean>) {
    val checked = remember { mutableStateOf(false) }
    ExpandableItem(title = "Switch（colors）", allExpandFlow, padding = 20.dp) {
        Switch(
            checked = checked.value,
            onCheckedChange = { checked.value = it },
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.Blue,
                checkedTrackColor = Color.Blue.copy(alpha = 0.5f),
                checkedBorderColor = Color.Blue,
                uncheckedThumbColor = Color.Red,
                uncheckedTrackColor = Color.Red.copy(alpha = 0.5f),
                uncheckedBorderColor = Color.Red,
            ),
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun SwitchColorsSamplePreview() {
    SwitchColorsSample(remember { MutableStateFlow(true) })
}


@Composable
fun SwitchGroupSingleSample(allExpandFlow: Flow<Boolean>) {
    val platforms = listOf("Android", "iOS", "macOS", "Windows", "Linux")
    val selectedIndex = remember { mutableStateOf(null as Int?) }
    ExpandableItem(title = "Switch（Group - Single）", allExpandFlow, padding = 20.dp) {
        Column {
            platforms.forEachIndexed { index, platform ->
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        selectedIndex.value = if (selectedIndex.value == index) null else index
                    }
                ) {
                    Switch(
                        checked = index == selectedIndex.value,
                        onCheckedChange = {
                            selectedIndex.value = if (selectedIndex.value == index) null else index
                        }
                    )
                    Spacer(modifier = Modifier
                        .width(16.dp)
                        .height(10.dp))
                    Text(text = platform, modifier = Modifier.align(Alignment.CenterVertically))
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun SwitchGroupSingleSamplePreview() {
    SwitchGroupSingleSample(remember { MutableStateFlow(true) })
}


@Composable
fun SwitchGroupMultiSample(allExpandFlow: Flow<Boolean>) {
    val platforms = listOf("Android", "iOS", "macOS", "Windows", "Linux")
    val checkedSet = remember { mutableStateOf(setOf<Int>()) }
    ExpandableItem(title = "Switch（Group - Multi）", allExpandFlow, padding = 20.dp) {
        Column {
            platforms.forEachIndexed { index, platform ->
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        checkedSet.value = checkedSet.value
                            .toMutableSet()
                            .apply {
                                if (!contains(index)) {
                                    add(index)
                                } else {
                                    remove(index)
                                }
                            }
                            .toSet()
                    }
                ) {
                    Switch(
                        checked = checkedSet.value.contains(index),
                        onCheckedChange = {
                            checkedSet.value = checkedSet.value.toMutableSet().apply {
                                if (it) {
                                    add(index)
                                } else {
                                    remove(index)
                                }
                            }.toSet()
                        }
                    )
                    Spacer(modifier = Modifier
                        .width(16.dp)
                        .height(10.dp))
                    Text(text = platform, modifier = Modifier.align(Alignment.CenterVertically))
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun SwitchGroupMultiSamplePreview() {
    SwitchGroupMultiSample(remember { MutableStateFlow(true) })
}