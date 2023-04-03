package com.github.panpf.android.compose.samples.ui.material

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text
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
import com.github.panpf.android.compose.samples.ui.base.MaterialComposeAppBarFragment
import com.github.panpf.android.compose.samples.ui.base.MyColor
import com.google.accompanist.flowlayout.FlowRow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class SwitchsFragment : MaterialComposeAppBarFragment() {

    override fun getTitle(): String {
        return "Switchs - Material"
    }

    @Composable
    override fun DrawContent() {
        ExpandableLayout { allExpandFlow ->
            SwitchSample(allExpandFlow)
            SwitchGroupSample(allExpandFlow)
        }
    }
}


@Composable
private fun SwitchSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem(title = "Switch", allExpandFlow, padding = 20.dp) {
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            mainAxisSpacing = 20.dp,
            crossAxisSpacing = 20.dp,
        ) {
            Column {
                Text(text = "Default")
                Spacer(modifier = Modifier.size(10.dp))
                val checked = remember { mutableStateOf(false) }
                Switch(
                    checked = checked.value,
                    onCheckedChange = { checked.value = it }
                )
            }

            Column {
                Text(text = "colors")
                Spacer(modifier = Modifier.size(10.dp))
                val checked = remember { mutableStateOf(false) }
                Switch(
                    checked = checked.value,
                    onCheckedChange = { checked.value = it },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = Color.Blue,
                        checkedTrackColor = MyColor.TranslucenceBlue,
                        uncheckedThumbColor = Color.Red,
                        uncheckedTrackColor = MyColor.TranslucenceRed,
                    ),
                )
            }

            Column {
                Text(text = "enabled=false")
                Spacer(modifier = Modifier.size(10.dp))
                val checked = remember { mutableStateOf(false) }
                Switch(
                    checked = checked.value,
                    onCheckedChange = { checked.value = it },
                    enabled = false,
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun SwitchSamplePreview() {
    SwitchSample(remember { MutableStateFlow(true) })
}


@Composable
private fun SwitchGroupSample(allExpandFlow: Flow<Boolean>) {
    val platforms = remember { listOf("Android", "iOS", "macOS", "Windows", "Linux") }
    ExpandableItem(title = "Switch（Group）", allExpandFlow, padding = 20.dp) {
        Row(Modifier.fillMaxWidth()) {
            Column(Modifier.weight(1f)) {
                Text(text = "单选")
                Spacer(modifier = Modifier.size(10.dp))
                val selectedIndex = remember { mutableStateOf(null as Int?) }
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
                                selectedIndex.value =
                                    if (selectedIndex.value == index) null else index
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

            Spacer(modifier = Modifier.size(20.dp))
            Column(Modifier.weight(1f)) {
                Text(text = "多选")
                Spacer(modifier = Modifier.size(10.dp))
                val checkedSet = remember { mutableStateOf(setOf<Int>()) }
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
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun SwitchGroupSamplePreview() {
    SwitchGroupSample(remember { MutableStateFlow(true) })
}