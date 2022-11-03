package com.github.panpf.android.compose.samples.ui.widgets.basic

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.panpf.android.compose.samples.R
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.ToolbarFragment
import com.github.panpf.android.compose.samples.ui.base.theme.MyTheme
import com.github.panpf.tools4a.toast.ktx.showShortToast
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FloatingActionButtonFragment : ToolbarFragment() {

    override fun createView(
        toolbar: Toolbar,
        inflater: LayoutInflater,
        parent: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        toolbar.title = "FloatingActionButton"
        return ComposeView(inflater.context).apply {
            setContent {
                MyTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        ExpandableLayout { allExpandFlow ->
                            FloatingActionButtonSample(allExpandFlow)
                            FloatingActionButtonShapeSample(allExpandFlow)
                            FloatingActionButtonColorSample(allExpandFlow)
                            SmallFloatingActionButtonSample(allExpandFlow)
                            LargeFloatingActionButtonSample(allExpandFlow)
                            ExtendedFloatingActionButtonSample(allExpandFlow)
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun FloatingActionButtonSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem("FloatingActionButton", allExpandFlow, padding = 20.dp) {
        FloatingActionButton(onClick = { context.showShortToast("你点了我！") }) {
            Text(text = "发送")
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun FloatingActionButtonSamplePreview() {
    FloatingActionButtonSample(remember { MutableStateFlow(true) })
}


@Composable
fun FloatingActionButtonShapeSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem("FloatingActionButton（Shape）", allExpandFlow, padding = 20.dp) {
        FloatingActionButton(
            shape = CircleShape,
            onClick = { context.showShortToast("你点了我！") },
        ) {
            Text(text = "发送")
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun FloatingActionButtonShapeSamplePreview() {
    FloatingActionButtonShapeSample(remember { MutableStateFlow(true) })
}


@Composable
fun FloatingActionButtonColorSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem("FloatingActionButton（Color）", allExpandFlow, padding = 20.dp) {
        FloatingActionButton(
            containerColor = Color.Cyan,
            contentColor = Color.Magenta,
            onClick = { context.showShortToast("你点了我！") },
        ) {
            Text(text = "发送")
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun FloatingActionButtonColorSamplePreview() {
    FloatingActionButtonColorSample(remember { MutableStateFlow(true) })
}


@Composable
fun SmallFloatingActionButtonSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem("SmallFloatingActionButton", allExpandFlow, padding = 20.dp) {
        SmallFloatingActionButton(onClick = { context.showShortToast("你点了我！") }) {
            Text(text = "发送")
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun SmallFloatingActionButtonSamplePreview() {
    SmallFloatingActionButtonSample(remember { MutableStateFlow(true) })
}


@Composable
fun LargeFloatingActionButtonSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem("LargeFloatingActionButton", allExpandFlow, padding = 20.dp) {
        LargeFloatingActionButton(onClick = { context.showShortToast("你点了我！") }) {
            Text(text = "发送")
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun LargeFloatingActionButtonSamplePreview() {
    LargeFloatingActionButtonSample(remember { MutableStateFlow(true) })
}


@Composable
fun ExtendedFloatingActionButtonSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem("ExtendedFloatingActionButton", allExpandFlow, padding = 20.dp) {
        val expanded = remember { mutableStateOf(false) }
        ExtendedFloatingActionButton(
            expanded = expanded.value,
            onClick = { expanded.value = !expanded.value },
            text = { Text(text = "展开") },
            icon = {
                Image(
                    painter = painterResource(id = R.drawable.ic_arrow_down),
                    contentDescription = ""
                )
            }
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun ExtendedFloatingActionButtonSamplePreview() {
    ExtendedFloatingActionButtonSample(remember { MutableStateFlow(true) })
}