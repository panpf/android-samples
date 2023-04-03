package com.github.panpf.android.compose.samples.ui.material

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
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem3
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.MaterialComposeAppBarFragment
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlin.math.roundToInt

class SlidersFragment : MaterialComposeAppBarFragment() {

    override fun getTitle(): String {
        return "Sliders - Material"
    }

    @Composable
    override fun DrawContent() {
        ExpandableLayout { allExpandFlow ->
            SliderSample(allExpandFlow)
            RangeSliderSample(allExpandFlow)
        }
    }
}


@Composable
private fun SliderSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "Slider", allExpandFlow, padding = 20.dp) {
        Text(text = "Default")
        Row(modifier = Modifier.fillMaxWidth()) {
            val value = remember { mutableStateOf(0f) }
            Slider(
                value = value.value,
                onValueChange = { value.value = it },
                modifier = Modifier.weight(1f),
            )
            Spacer(modifier = Modifier.size(10.dp))
            Text(
                text = "${(value.value * 100).roundToInt()}%",
                modifier = Modifier
                    .width(45.dp)
                    .align(Alignment.CenterVertically),
                textAlign = TextAlign.End,
            )
        }

        Spacer(modifier = Modifier.size(20.dp))
        Text(text = "InitialValue")
        Row(modifier = Modifier.fillMaxWidth()) {
            val value = remember { mutableStateOf(0.4f) }
            Slider(
                value = value.value,
                onValueChange = { value.value = it },
                modifier = Modifier.weight(1f),
            )
            Spacer(modifier = Modifier.size(10.dp))
            Text(
                text = "${(value.value * 100).roundToInt()}%",
                modifier = Modifier
                    .width(45.dp)
                    .align(Alignment.CenterVertically),
                textAlign = TextAlign.End,
            )
        }

        Spacer(modifier = Modifier.size(20.dp))
        Text(text = "valueRange")
        Row(modifier = Modifier.fillMaxWidth()) {
            val value = remember { mutableStateOf(0f) }
            val valueRange = 0.2f..0.8f
            Slider(
                value = value.value,
                onValueChange = { value.value = it },
                modifier = Modifier.weight(1f),
                valueRange = valueRange,
            )
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

        Spacer(modifier = Modifier.size(20.dp))
        Text(text = "steps")
        Row(modifier = Modifier.fillMaxWidth()) {
            val value = remember { mutableStateOf(0f) }
            Slider(
                value = value.value,
                onValueChange = { value.value = it },
                modifier = Modifier.weight(1f),
                steps = 9,
            )
            Spacer(modifier = Modifier.size(10.dp))
            Text(
                text = "${(value.value * 100).roundToInt()}%",
                modifier = Modifier
                    .width(45.dp)
                    .align(Alignment.CenterVertically),
                textAlign = TextAlign.End,
            )
        }

        Spacer(modifier = Modifier.size(20.dp))
        Text(text = "colors")
        Row(modifier = Modifier.fillMaxWidth()) {
            val value = remember { mutableStateOf(0f) }
            Slider(
                value = value.value,
                onValueChange = { value.value = it },
                modifier = Modifier.weight(1f),
                steps = 9,
                colors = SliderDefaults.colors(
                    thumbColor = Color.Red,
                    activeTrackColor = Color.Yellow,
                    activeTickColor = Color.Cyan,
                    inactiveTrackColor = Color.Blue,
                    inactiveTickColor = Color.White,
                ),
            )
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


@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun RangeSliderSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "RangeSlider", allExpandFlow, padding = 20.dp) {
        Text(text = "Default")
        Row(modifier = Modifier.fillMaxWidth()) {
            val value = remember { mutableStateOf(0f..1f) }
            RangeSlider(
                value = value.value,
                onValueChange = { value.value = it },
                modifier = Modifier.weight(1f),
            )
            Spacer(modifier = Modifier.size(10.dp))
            Text(
                text = "${(value.value.start * 100).roundToInt()}% - ${(value.value.endInclusive * 100).roundToInt()}%",
                modifier = Modifier
                    .width(100.dp)
                    .align(Alignment.CenterVertically),
                textAlign = TextAlign.End,
            )
        }

        Spacer(modifier = Modifier.size(20.dp))
        Text(text = "InitialValue")
        Row(modifier = Modifier.fillMaxWidth()) {
            val value = remember { mutableStateOf(0.2f..0.8f) }
            RangeSlider(
                value = value.value,
                onValueChange = { value.value = it },
                modifier = Modifier.weight(1f),
            )
            Spacer(modifier = Modifier.size(10.dp))
            Text(
                text = "${(value.value.start * 100).roundToInt()}% - ${(value.value.endInclusive * 100).roundToInt()}%",
                modifier = Modifier
                    .width(100.dp)
                    .align(Alignment.CenterVertically),
                textAlign = TextAlign.End,
            )
        }

        Spacer(modifier = Modifier.size(20.dp))
        Text(text = "valueRange")
        Row(modifier = Modifier.fillMaxWidth()) {
            val value = remember { mutableStateOf(0.2f..0.8f) }
            RangeSlider(
                value = value.value,
                onValueChange = { value.value = it },
                modifier = Modifier.weight(1f),
                valueRange = 0.2f..0.8f,
            )
            Spacer(modifier = Modifier.size(10.dp))
            Text(
                text = "${(value.value.start * 100).roundToInt()}% - ${(value.value.endInclusive * 100).roundToInt()}%",
                modifier = Modifier
                    .width(100.dp)
                    .align(Alignment.CenterVertically),
                textAlign = TextAlign.End,
            )
        }

        Spacer(modifier = Modifier.size(20.dp))
        Text(text = "steps")
        Row(modifier = Modifier.fillMaxWidth()) {
            val value = remember { mutableStateOf(0f..1f) }
            RangeSlider(
                value = value.value,
                onValueChange = { value.value = it },
                modifier = Modifier.weight(1f),
                steps = 9,
            )
            Spacer(modifier = Modifier.size(10.dp))
            Text(
                text = "${(value.value.start * 100).roundToInt()}% - ${(value.value.endInclusive * 100).roundToInt()}%",
                modifier = Modifier
                    .width(100.dp)
                    .align(Alignment.CenterVertically),
                textAlign = TextAlign.End,
            )
        }

        Spacer(modifier = Modifier.size(20.dp))
        Text(text = "colors")
        Row(modifier = Modifier.fillMaxWidth()) {
            val value = remember { mutableStateOf(0f..1f) }
            RangeSlider(
                value = value.value,
                onValueChange = { value.value = it },
                modifier = Modifier.weight(1f),
                steps = 9,
                colors = SliderDefaults.colors(
                    thumbColor = Color.Red,
                    activeTrackColor = Color.Yellow,
                    activeTickColor = Color.Cyan,
                    inactiveTrackColor = Color.Blue,
                    inactiveTickColor = Color.White,
                ),
            )
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