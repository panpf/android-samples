package com.github.panpf.android.compose.samples.ui.widgets.slider

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlin.math.roundToInt

@Composable
fun SliderUI() {
    ExpandableLayout { allExpandFlow ->
        SliderSample(allExpandFlow)
        SliderValueSample(allExpandFlow)
        SliderEnabledFalseSample(allExpandFlow)
        SliderStepsSample(allExpandFlow)
        SliderColorsSample(allExpandFlow)
    }
}


@Composable
fun SliderSample(allExpandFlow: Flow<Boolean>) {
    val value = remember { mutableStateOf(0f) }
    ExpandableItem(title = "Slider", allExpandFlow, padding = 20.dp) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Box(modifier = Modifier.weight(1f)) {
                Slider(
                    value = value.value,
                    onValueChange = { value.value = it },
                )
            }
            Spacer(modifier = Modifier.size(10.dp))
            Text(
                text = "${(value.value * 100).roundToInt()}%",
                modifier = Modifier
                    .width(45.dp)
                    .align(Alignment.CenterVertically),
                textAlign = TextAlign.End,
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun SliderSamplePreview() {
    SliderSample(remember { MutableStateFlow(true) })
}


@Composable
fun SliderValueSample(allExpandFlow: Flow<Boolean>) {
    val value = remember { mutableStateOf(0.4f) }
    ExpandableItem(title = "Slider（value）", allExpandFlow, padding = 20.dp) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Box(modifier = Modifier.weight(1f)) {
                Slider(
                    value = value.value,
                    onValueChange = { value.value = it },
                )
            }
            Spacer(modifier = Modifier.size(10.dp))
            Text(
                text = "${(value.value * 100).roundToInt()}%",
                modifier = Modifier
                    .width(45.dp)
                    .align(Alignment.CenterVertically),
                textAlign = TextAlign.End,
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun SliderValueSamplePreview() {
    SliderValueSample(remember { MutableStateFlow(true) })
}


@Composable
fun SliderEnabledFalseSample(allExpandFlow: Flow<Boolean>) {
    val value = remember { mutableStateOf(0f) }
    ExpandableItem(title = "Slider（enabled - false）", allExpandFlow, padding = 20.dp) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Box(modifier = Modifier.weight(1f)) {
                Slider(
                    value = value.value,
                    onValueChange = { value.value = it },
                    enabled = false,
                )
            }
            Spacer(modifier = Modifier.size(10.dp))
            Text(
                text = "${(value.value * 100).roundToInt()}%",
                modifier = Modifier
                    .width(45.dp)
                    .align(Alignment.CenterVertically),
                textAlign = TextAlign.End,
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun SliderEnabledFalseSamplePreview() {
    SliderEnabledFalseSample(remember { MutableStateFlow(true) })
}


@Composable
fun SliderStepsSample(allExpandFlow: Flow<Boolean>) {
    val value = remember { mutableStateOf(0f) }
    ExpandableItem(title = "Slider（steps）", allExpandFlow, padding = 20.dp) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Box(modifier = Modifier.weight(1f)) {
                Slider(
                    value = value.value,
                    onValueChange = { value.value = it },
                    steps = 9,
                )
            }
            Spacer(modifier = Modifier.size(10.dp))
            Text(
                text = "${(value.value * 100).roundToInt()}%",
                modifier = Modifier
                    .width(45.dp)
                    .align(Alignment.CenterVertically),
                textAlign = TextAlign.End,
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun SliderStepsSamplePreview() {
    SliderStepsSample(remember { MutableStateFlow(true) })
}


@Composable
fun SliderColorsSample(allExpandFlow: Flow<Boolean>) {
    val value = remember { mutableStateOf(0f) }
    ExpandableItem(title = "Slider（colors）", allExpandFlow, padding = 20.dp) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Box(modifier = Modifier.weight(1f)) {
                Slider(
                    value = value.value,
                    onValueChange = { value.value = it },
                    steps = 9,
                    colors = SliderDefaults.colors(
                        thumbColor = Color.Red,
                        activeTrackColor = Color.Yellow,
                        activeTickColor = Color.Cyan,
                        inactiveTrackColor = Color.Blue,
                        inactiveTickColor = Color.White,
                    ),
                )
            }
            Spacer(modifier = Modifier.size(10.dp))
            Text(
                text = "${(value.value * 100).roundToInt()}%",
                modifier = Modifier
                    .width(45.dp)
                    .align(Alignment.CenterVertically),
                textAlign = TextAlign.End,
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun SliderColorsSamplePreview() {
    SliderColorsSample(remember { MutableStateFlow(true) })
}