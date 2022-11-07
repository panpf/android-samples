package com.github.panpf.android.compose.samples.ui.widgets.basic

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TriStateCheckbox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.ToolbarFragment
import com.github.panpf.android.compose.samples.ui.base.theme.MyTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class TriStateCheckboxFragment : ToolbarFragment() {

    override fun createView(
        toolbar: Toolbar,
        inflater: LayoutInflater,
        parent: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        toolbar.title = "TriStateCheckbox"
        return ComposeView(inflater.context).apply {
            setContent {
                MyTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        ExpandableLayout { allExpandFlow ->
                            TriStateCheckboxSample(allExpandFlow)
                            TriStateCheckboxColorsSample(allExpandFlow)
                            TriStateCheckboxGroupSample(allExpandFlow)
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun TriStateCheckboxSample(allExpandFlow: Flow<Boolean>) {
    val toggleableState = remember {
        mutableStateOf(ToggleableState(false))
    }
    ExpandableItem(title = "TriStateCheckbox", allExpandFlow, padding = 20.dp) {
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
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun TriStateCheckboxSamplePreview() {
    TriStateCheckboxSample(remember { MutableStateFlow(true) })
}


@Composable
fun TriStateCheckboxColorsSample(allExpandFlow: Flow<Boolean>) {
    val toggleableState = remember {
        mutableStateOf(ToggleableState(false))
    }
    ExpandableItem(title = "TriStateCheckbox（colors）", allExpandFlow, padding = 20.dp) {
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
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun TriStateCheckboxColorsSamplePreview() {
    TriStateCheckboxColorsSample(remember { MutableStateFlow(true) })
}


@Composable
fun TriStateCheckboxGroupSample(allExpandFlow: Flow<Boolean>) {
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
                Text(text = "全选", modifier = Modifier.align(Alignment.CenterVertically))
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
fun TriStateCheckboxGroupSamplePreview() {
    TriStateCheckboxGroupSample(remember { MutableStateFlow(true) })
}