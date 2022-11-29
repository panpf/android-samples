package com.github.panpf.android.compose.samples.ui.material3

import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem3
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class DividerFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "Divider - Material3"
    }

    @Composable
    override fun DrawContent() {
        ExpandableLayout { allExpandFlow ->
            DividerSample(allExpandFlow)
            DividerColorSample(allExpandFlow)
            DividerThicknessSample(allExpandFlow)
//        DividerStartIndentSample(allExpandFlow)
        }
    }
}


@Composable
private fun DividerSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "Divider", allExpandFlow, padding = 20.dp) {
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
    ExpandableItem3(title = "Divider（color）", allExpandFlow, padding = 20.dp) {
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
    ExpandableItem3(title = "Divider（thickness）", allExpandFlow, padding = 20.dp) {
        Divider(thickness = 5.dp)
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun DividerThicknessSamplePreview() {
    DividerThicknessSample(remember { MutableStateFlow(true) })
}


//@Composable
//fun DividerStartIndentSample(allExpandFlow: Flow<Boolean>) {
//    ExpandableItem3(title = "Divider（startIndent）", allExpandFlow, padding = 20.dp) {
//        Divider(startIndent = 20.dp)
//    }
//}
//
//@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
//@Composable
//fun DividerStartIndentSamplePreview() {
//    DividerStartIndentSample(remember { MutableStateFlow(true) })
//}