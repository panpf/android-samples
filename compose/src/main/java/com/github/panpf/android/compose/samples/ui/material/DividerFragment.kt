package com.github.panpf.android.compose.samples.ui.material

import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.MaterialComposeAppBarFragment
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class DividerFragment : MaterialComposeAppBarFragment() {

    override fun getTitle(): String {
        return "Divider - Material"
    }

    @Composable
    override fun DrawContent() {
        ExpandableLayout { allExpandFlow ->
            DividerSample(allExpandFlow)
            DividerColorSample(allExpandFlow)
            DividerThicknessSample(allExpandFlow)
            DividerStartIndentSample(allExpandFlow)
        }
    }
}


@Composable
private fun DividerSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem(title = "Divider", allExpandFlow, padding = 20.dp) {
        Divider()
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun DividerSamplePreview() {
    DividerSample(remember { MutableStateFlow(true) })
}


@Composable
private fun DividerColorSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem(title = "Divider（color）", allExpandFlow, padding = 20.dp) {
        Divider(color = Color.Red)
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun DividerColorSamplePreview() {
    DividerColorSample(remember { MutableStateFlow(true) })
}


@Composable
private fun DividerThicknessSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem(title = "Divider（thickness）", allExpandFlow, padding = 20.dp) {
        Divider(thickness = 5.dp)
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun DividerThicknessSamplePreview() {
    DividerThicknessSample(remember { MutableStateFlow(true) })
}


@Composable
private fun DividerStartIndentSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem(title = "Divider（startIndent）", allExpandFlow, padding = 20.dp) {
        Divider(startIndent = 20.dp)
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun DividerStartIndentSamplePreview() {
    DividerStartIndentSample(remember { MutableStateFlow(true) })
}