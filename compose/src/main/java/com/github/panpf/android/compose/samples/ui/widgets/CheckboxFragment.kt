package com.github.panpf.android.compose.samples.ui.widgets

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.ToolbarFragment
import com.github.panpf.android.compose.samples.ui.base.theme.MyTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class CheckboxFragment : ToolbarFragment() {

    override fun createView(
        toolbar: Toolbar,
        inflater: LayoutInflater,
        parent: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        toolbar.title = "Checkbox"
        return ComposeView(inflater.context).apply {
            setContent {
                MyTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        CheckboxUI()
                    }
                }
            }
        }
    }
}

@Composable
fun CheckboxUI() {
    ExpandableLayout { allExpandFlow ->
        CheckboxSample(allExpandFlow)
        CheckboxEnabledFalseSample(allExpandFlow)
        CheckboxColorsSample(allExpandFlow)
        CheckboxGroupSample(allExpandFlow)
    }
}


@Composable
fun CheckboxSample(allExpandFlow: Flow<Boolean>) {
    val checked = remember { mutableStateOf(false) }
    ExpandableItem(title = "Checkbox", allExpandFlow, padding = 20.dp) {
        Checkbox(
            checked = checked.value,
            onCheckedChange = { checked.value = it }
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun CheckboxSamplePreview() {
    CheckboxSample(remember { MutableStateFlow(true) })
}


@Composable
fun CheckboxEnabledFalseSample(allExpandFlow: Flow<Boolean>) {
    val checked = remember { mutableStateOf(false) }
    ExpandableItem(title = "Checkbox（enabled - false）", allExpandFlow, padding = 20.dp) {
        Checkbox(
            checked = checked.value,
            onCheckedChange = { checked.value = it },
            enabled = false,
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun CheckboxEnabledFalseSamplePreview() {
    CheckboxEnabledFalseSample(remember { MutableStateFlow(true) })
}


@Composable
fun CheckboxColorsSample(allExpandFlow: Flow<Boolean>) {
    val checked = remember { mutableStateOf(false) }
    ExpandableItem(title = "Checkbox（colors）", allExpandFlow, padding = 20.dp) {
        Checkbox(
            checked = checked.value,
            onCheckedChange = { checked.value = it },
            colors = CheckboxDefaults.colors(
                checkedColor = Color.Blue,
                uncheckedColor = Color.Red
            ),
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun CheckboxColorsSamplePreview() {
    CheckboxColorsSample(remember { MutableStateFlow(true) })
}


@Composable
fun CheckboxGroupSample(allExpandFlow: Flow<Boolean>) {
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
fun CheckboxGroupSamplePreview() {
    CheckboxGroupSample(remember { MutableStateFlow(true) })
}