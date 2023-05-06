package com.github.panpf.android.compose.samples.ui.material3

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.material3.TriStateCheckbox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
import com.github.panpf.android.compose.samples.ui.customization.grid.VerticalGrid
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class CheckboxesFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "Checkboxes - Material3"
    }

    @Composable
    override fun DrawContent() {
        ExpandableLayout { allExpandFlow ->
            CheckboxSample(allExpandFlow)
            CheckboxGroupSample(allExpandFlow)
            TriStateCheckboxSample(allExpandFlow)
            TriStateCheckboxGroupSample(allExpandFlow)
        }
    }
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun CheckboxSample(allExpandFlow: Flow<Boolean>) {
    val checked = remember { mutableStateOf(false) }
    ExpandableItem(title = "Checkbox", allExpandFlow, padding = 20.dp) {
        VerticalGrid(
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            Column {
                Text(text = "Default")
                Spacer(modifier = Modifier.size(10.dp))
                Checkbox(
                    checked = checked.value,
                    onCheckedChange = { checked.value = it }
                )
            }

            Column {
                Text(text = "colors")
                Spacer(modifier = Modifier.size(10.dp))
                Checkbox(
                    checked = checked.value,
                    onCheckedChange = { checked.value = it },
                    colors = CheckboxDefaults.colors(
                        checkedColor = Color.Blue,
                        uncheckedColor = Color.Red
                    ),
                )
            }

            Column {
                Text(text = "enabled=false")
                Spacer(modifier = Modifier.size(10.dp))
                Checkbox(
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
private fun CheckboxSamplePreview() {
    CheckboxSample(remember { MutableStateFlow(true) })
}


@Composable
private fun CheckboxGroupSample(allExpandFlow: Flow<Boolean>) {
    val platforms = listOf("Android", "iOS", "macOS", "Windows", "Linux")
    val checkedSet = remember { mutableStateOf(setOf<Int>()) }
    ExpandableItem(title = "Checkbox（Group）", allExpandFlow, padding = 20.dp) {
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
                    Checkbox(
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
                    Text(text = platform, modifier = Modifier.align(Alignment.CenterVertically))
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun CheckboxGroupSamplePreview() {
    CheckboxGroupSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun TriStateCheckboxSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem(title = "TriStateCheckbox", allExpandFlow, padding = 20.dp) {
        VerticalGrid(
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            Column {
                Text(text = "Default")
                Spacer(modifier = Modifier.size(10.dp))
                val toggleableState = remember { mutableStateOf(ToggleableState(false)) }
                TriStateCheckbox(
                    state = toggleableState.value,
                    onClick = {
                        toggleableState.value = when (toggleableState.value) {
                            ToggleableState.Off -> ToggleableState.Indeterminate
                            ToggleableState.Indeterminate -> ToggleableState.On
                            ToggleableState.On -> ToggleableState.Off
                        }
                    }
                )
            }

            Column {
                Text(text = "colors")
                Spacer(modifier = Modifier.size(10.dp))
                val toggleableState = remember { mutableStateOf(ToggleableState(false)) }
                TriStateCheckbox(
                    state = toggleableState.value,
                    onClick = {
                        toggleableState.value = when (toggleableState.value) {
                            ToggleableState.Off -> ToggleableState.Indeterminate
                            ToggleableState.Indeterminate -> ToggleableState.On
                            ToggleableState.On -> ToggleableState.Off
                        }
                    },
                    colors = CheckboxDefaults.colors(
                        checkedColor = Color.Blue,
                        uncheckedColor = Color.Red
                    ),
                )
            }

            Column {
                Text(text = "enabled=false")
                Spacer(modifier = Modifier.size(10.dp))
                val toggleableState = remember { mutableStateOf(ToggleableState(false)) }
                TriStateCheckbox(
                    state = toggleableState.value,
                    enabled = false,
                    onClick = {
                        toggleableState.value = when (toggleableState.value) {
                            ToggleableState.Off -> ToggleableState.Indeterminate
                            ToggleableState.Indeterminate -> ToggleableState.On
                            ToggleableState.On -> ToggleableState.Off
                        }
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun TriStateCheckboxSamplePreview() {
    TriStateCheckboxSample(remember { MutableStateFlow(true) })
}


@Composable
private fun TriStateCheckboxGroupSample(allExpandFlow: Flow<Boolean>) {
    val platforms = listOf("Android", "iOS", "macOS", "Windows", "Linux")
    val checkedSet = remember { mutableStateOf(setOf(2)) }
    ExpandableItem(title = "TriStateCheckbox（Group）", allExpandFlow, padding = 20.dp) {
        val allToggleableState = when {
            checkedSet.value.isEmpty() -> ToggleableState.Off
            checkedSet.value.size == platforms.size -> ToggleableState.On
            else -> ToggleableState.Indeterminate
        }
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        checkedSet.value = when (allToggleableState) {
                            ToggleableState.Off,
                            ToggleableState.Indeterminate -> checkedSet.value
                                .toMutableSet()
                                .apply {
                                    platforms.indices.forEach {
                                        add(it)
                                    }
                                }
                                .toSet()

                            ToggleableState.On -> emptySet()
                        }
                    }
            ) {
                TriStateCheckbox(
                    state = allToggleableState,
                    onClick = {
                        checkedSet.value = when (allToggleableState) {
                            ToggleableState.Off,
                            ToggleableState.Indeterminate -> checkedSet.value
                                .toMutableSet()
                                .apply {
                                    platforms.indices.forEach {
                                        add(it)
                                    }
                                }
                                .toSet()

                            ToggleableState.On -> emptySet()
                        }
                    },
                )
                Text(text = "All Selected", modifier = Modifier.align(Alignment.CenterVertically))
            }
            Divider()
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
                    Checkbox(
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
                    Text(text = platform, modifier = Modifier.align(Alignment.CenterVertically))
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun TriStateCheckboxGroupSamplePreview() {
    TriStateCheckboxGroupSample(remember { MutableStateFlow(true) })
}