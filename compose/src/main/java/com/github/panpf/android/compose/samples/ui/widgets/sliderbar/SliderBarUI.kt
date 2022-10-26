package com.github.panpf.android.compose.samples.ui.widgets.sliderbar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun SliderBarUI() {
    ExpandableLayout { allExpandFlow ->
        SliderBarSample(allExpandFlow)
    }
}


@Composable
fun SliderBarSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem(title = "SliderBar", allExpandFlow, padding = 20.dp) {
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun SliderBarSamplePreview() {
    SliderBarSample(remember { MutableStateFlow(true) })
}