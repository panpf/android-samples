package com.github.panpf.android.compose.samples.ui.components.material

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.RangeSlider
import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.material.Text
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
import com.github.panpf.android.compose.samples.ui.base.MaterialComposeAppBarFragment
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlin.math.roundToInt

class SliderFragment : MaterialComposeAppBarFragment() {

    override fun getTitle(): String {
        return "Slider - Material"
    }

    @Composable
    override fun DrawContent() {
        ExpandableLayout { allExpandFlow ->
            SliderSample(allExpandFlow)
            SliderValueSample(allExpandFlow)
            SliderEnabledFalseSample(allExpandFlow)
            SliderValueRangeSample(allExpandFlow)
            SliderStepsSample(allExpandFlow)
            SliderColorsSample(allExpandFlow)
            RangeSliderSample(allExpandFlow)
        }
    }
}


@Composable
private fun SliderSample(allExpandFlow: Flow<Boolean>) {
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
private fun SliderSamplePreview() {
    SliderSample(remember { MutableStateFlow(true) })
}


@Composable
private fun SliderValueSample(allExpandFlow: Flow<Boolean>) {
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
private fun SliderValueSamplePreview() {
    SliderValueSample(remember { MutableStateFlow(true) })
}


@Composable
private fun SliderEnabledFalseSample(allExpandFlow: Flow<Boolean>) {
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
private fun SliderEnabledFalseSamplePreview() {
    SliderEnabledFalseSample(remember { MutableStateFlow(true) })
}


@Composable
private fun SliderValueRangeSample(allExpandFlow: Flow<Boolean>) {
    val value = remember { mutableStateOf(0f) }
    val valueRange = 0.2f..0.8f
    ExpandableItem(title = "Slider（valueRange）", allExpandFlow, padding = 20.dp) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Box(modifier = Modifier.weight(1f)) {
                Slider(
                    value = value.value,
                    onValueChange = { value.value = it },
                    valueRange = valueRange,
                )
            }
            Spacer(modifier = Modifier.size(10.dp))
            val finalValue = if (value.value == 0f) valueRange.start else value.value
            Text(
                text = "${(finalValue * 100).roundToInt()}%",
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
private fun SliderValueRangeSamplePreview() {
    SliderValueRangeSample(remember { MutableStateFlow(true) })
}


@Composable
private fun SliderStepsSample(allExpandFlow: Flow<Boolean>) {
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
private fun SliderStepsSamplePreview() {
    SliderStepsSample(remember { MutableStateFlow(true) })
}


@Composable
private fun SliderColorsSample(allExpandFlow: Flow<Boolean>) {
    val value = remember { mutableStateOf(0.4f) }
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
private fun SliderColorsSamplePreview() {
    SliderColorsSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun RangeSliderSample(allExpandFlow: Flow<Boolean>) {
    val value = remember { mutableStateOf(0.4f..0.8f) }
    ExpandableItem(title = "RangeSlider", allExpandFlow, padding = 20.dp) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Box(modifier = Modifier.weight(1f)) {
                RangeSlider(
                    value = value.value,
                    onValueChange = { value.value = it },
                )
            }
            Spacer(modifier = Modifier.size(10.dp))
            Text(
                text = "${(value.value.start * 100).roundToInt()}% - ${(value.value.endInclusive * 100).roundToInt()}%",
                modifier = Modifier
                    .width(100.dp)
                    .align(Alignment.CenterVertically),
                textAlign = TextAlign.End,
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun RangeSliderSamplePreview() {
    RangeSliderSample(remember { MutableStateFlow(true) })
}