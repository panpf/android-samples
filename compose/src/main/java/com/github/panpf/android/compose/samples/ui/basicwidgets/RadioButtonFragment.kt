package com.github.panpf.android.compose.samples.ui.basicwidgets

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
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

class RadioButtonFragment : ToolbarFragment() {

    override fun createView(
        toolbar: Toolbar,
        inflater: LayoutInflater,
        parent: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        toolbar.title = "RadioButton"
        return ComposeView(inflater.context).apply {
            setContent {
                MyTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        ExpandableLayout { allExpandFlow ->
                            RadioButtonSample(allExpandFlow)
                            RadioButtonEnabledFalseSample(allExpandFlow)
                            RadioButtonColorsSample(allExpandFlow)
                            RadioButtonGroupSample(allExpandFlow)
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun RadioButtonSample(allExpandFlow: Flow<Boolean>) {
    val selected = remember { mutableStateOf(false) }
    ExpandableItem(title = "RadioButton", allExpandFlow, padding = 20.dp) {
        RadioButton(
            selected = selected.value,
            onClick = { selected.value = !selected.value }
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun RadioButtonSamplePreview() {
    RadioButtonSample(remember { MutableStateFlow(true) })
}


@Composable
fun RadioButtonEnabledFalseSample(allExpandFlow: Flow<Boolean>) {
    val selected = remember { mutableStateOf(false) }
    ExpandableItem(title = "RadioButton（enabled - false）", allExpandFlow, padding = 20.dp) {
        RadioButton(
            selected = selected.value,
            onClick = { selected.value = !selected.value },
            enabled = false,
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun RadioButtonEnabledFalseSamplePreview() {
    RadioButtonEnabledFalseSample(remember { MutableStateFlow(true) })
}


@Composable
fun RadioButtonColorsSample(allExpandFlow: Flow<Boolean>) {
    val selected = remember { mutableStateOf(false) }
    ExpandableItem(title = "RadioButton（colors）", allExpandFlow, padding = 20.dp) {
        RadioButton(
            selected = selected.value,
            onClick = { selected.value = !selected.value },
            colors = RadioButtonDefaults.colors(
                selectedColor = Color.Blue,
                unselectedColor = Color.Red
            ),
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun RadioButtonColorsSamplePreview() {
    RadioButtonColorsSample(remember { MutableStateFlow(true) })
}


@Composable
fun RadioButtonGroupSample(allExpandFlow: Flow<Boolean>) {
    val platforms = listOf("Android", "iOS", "macOS", "Windows", "Linux")
    val selectedIndex = remember { mutableStateOf(null as Int?) }
    ExpandableItem(title = "RadioButton（Group）", allExpandFlow, padding = 20.dp) {
        Column {
            platforms.forEachIndexed { index, platform ->
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        selectedIndex.value = if (selectedIndex.value == index) null else index
                    }
                ) {
                    RadioButton(
                        selected = index == selectedIndex.value,
                        onClick = {
                            selectedIndex.value = if (selectedIndex.value == index) null else index
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
fun RadioButtonGroupSamplePreview() {
    RadioButtonGroupSample(remember { MutableStateFlow(true) })
}