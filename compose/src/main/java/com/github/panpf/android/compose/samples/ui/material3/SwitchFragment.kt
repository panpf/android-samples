package com.github.panpf.android.compose.samples.ui.material3

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
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem3
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class SwitchFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "Switch - Material3"
    }

    @Composable
    override fun DrawContent() {
        ExpandableLayout { allExpandFlow ->
            SwitchSample(allExpandFlow)
            SwitchEnabledFalseSample(allExpandFlow)
            SwitchColorsSample(allExpandFlow)
            SwitchGroupSingleSample(allExpandFlow)
            SwitchGroupMultiSample(allExpandFlow)
        }
    }
}


@Composable
private fun SwitchSample(allExpandFlow: Flow<Boolean>) {
    val checked = remember { mutableStateOf(false) }
    ExpandableItem3(title = "Switch", allExpandFlow, padding = 20.dp) {
        Switch(
            checked = checked.value,
            onCheckedChange = { checked.value = it }
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun SwitchSamplePreview() {
    SwitchSample(remember { MutableStateFlow(true) })
}


@Composable
private fun SwitchEnabledFalseSample(allExpandFlow: Flow<Boolean>) {
    val checked = remember { mutableStateOf(false) }
    ExpandableItem3(title = "Switch（enabled - false）", allExpandFlow, padding = 20.dp) {
        Switch(
            checked = checked.value,
            onCheckedChange = { checked.value = it },
            enabled = false,
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun SwitchEnabledFalseSamplePreview() {
    SwitchEnabledFalseSample(remember { MutableStateFlow(true) })
}


@Composable
private fun SwitchColorsSample(allExpandFlow: Flow<Boolean>) {
    val checked = remember { mutableStateOf(false) }
    ExpandableItem3(title = "Switch（colors）", allExpandFlow, padding = 20.dp) {
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
private fun SwitchColorsSamplePreview() {
    SwitchColorsSample(remember { MutableStateFlow(true) })
}


@Composable
private fun SwitchGroupSingleSample(allExpandFlow: Flow<Boolean>) {
    val platforms = listOf("Android", "iOS", "macOS", "Windows", "Linux")
    val selectedIndex = remember { mutableStateOf(null as Int?) }
    ExpandableItem3(title = "Switch（Group - Single）", allExpandFlow, padding = 20.dp) {
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
                    Spacer(
                        modifier = Modifier
                            .width(16.dp)
                            .height(10.dp)
                    )
                    Text(text = platform, modifier = Modifier.align(Alignment.CenterVertically))
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun SwitchGroupSingleSamplePreview() {
    SwitchGroupSingleSample(remember { MutableStateFlow(true) })
}


@Composable
private fun SwitchGroupMultiSample(allExpandFlow: Flow<Boolean>) {
    val platforms = listOf("Android", "iOS", "macOS", "Windows", "Linux")
    val checkedSet = remember { mutableStateOf(setOf<Int>()) }
    ExpandableItem3(title = "Switch（Group - Multi）", allExpandFlow, padding = 20.dp) {
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
                    Spacer(
                        modifier = Modifier
                            .width(16.dp)
                            .height(10.dp)
                    )
                    Text(text = platform, modifier = Modifier.align(Alignment.CenterVertically))
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun SwitchGroupMultiSamplePreview() {
    SwitchGroupMultiSample(remember { MutableStateFlow(true) })
}