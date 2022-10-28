package com.github.panpf.android.compose.samples.ui.widgets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
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

class DividerFragment : ToolbarFragment() {

    override fun createView(
        toolbar: Toolbar,
        inflater: LayoutInflater,
        parent: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        toolbar.title = "Divider"
        return ComposeView(inflater.context).apply {
            setContent {
                MyTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        ExpandableLayout { allExpandFlow ->
                            DividerSample(allExpandFlow)
                            DividerColorSample(allExpandFlow)
                            DividerThicknessSample(allExpandFlow)
//        DividerStartIndentSample(allExpandFlow)
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun DividerSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem(title = "Divider", allExpandFlow, padding = 20.dp) {
        Divider()
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun DividerSamplePreview() {
    DividerSample(remember { MutableStateFlow(true) })
}


@Composable
fun DividerColorSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem(title = "Divider（color）", allExpandFlow, padding = 20.dp) {
        Divider(color = Color.Red)
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun DividerColorSamplePreview() {
    DividerColorSample(remember { MutableStateFlow(true) })
}


@Composable
fun DividerThicknessSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem(title = "Divider（thickness）", allExpandFlow, padding = 20.dp) {
        Divider(thickness = 5.dp)
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun DividerThicknessSamplePreview() {
    DividerThicknessSample(remember { MutableStateFlow(true) })
}


//@Composable
//fun DividerStartIndentSample(allExpandFlow: Flow<Boolean>) {
//    ExpandableItem(title = "Divider（startIndent）", allExpandFlow, padding = 20.dp) {
//        Divider(startIndent = 20.dp)
//    }
//}
//
//@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
//@Composable
//fun DividerStartIndentSamplePreview() {
//    DividerStartIndentSample(remember { MutableStateFlow(true) })
//}