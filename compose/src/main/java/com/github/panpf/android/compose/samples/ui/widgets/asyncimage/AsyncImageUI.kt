package com.github.panpf.android.compose.samples.ui.widgets.asyncimage

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
fun AsyncImageUI() {
    ExpandableLayout { allExpandFlow ->
        AsyncImageSample(allExpandFlow)
    }
}


@Composable
fun AsyncImageSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem(title = "AsyncImage", allExpandFlow, padding = 20.dp) {
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun AsyncImageSamplePreview() {
    AsyncImageSample(remember { MutableStateFlow(true) })
}