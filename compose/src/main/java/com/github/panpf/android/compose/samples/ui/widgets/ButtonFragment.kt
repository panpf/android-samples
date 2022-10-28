package com.github.panpf.android.compose.samples.ui.widgets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
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

class ButtonFragment : ToolbarFragment() {

    override fun createView(
        toolbar: Toolbar,
        inflater: LayoutInflater,
        parent: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        toolbar.title = "Button"
        return ComposeView(inflater.context).apply {
            setContent {
                MyTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        ExpandableLayout { allExpandFlow ->
                            ButtonContentTextSample(allExpandFlow)
                            ButtonContentImageSample(allExpandFlow)
                            ButtonDisabledSample(allExpandFlow)
                            ButtonShapeSample(allExpandFlow)
                            ButtonColorSample(allExpandFlow)
                            ButtonElevationSample(allExpandFlow)
                            ButtonBorderSample(allExpandFlow)
                            ButtonContentPaddingSample(allExpandFlow)
                            ElevatedButtonSample(allExpandFlow)
                            FilledTonalButtonSample(allExpandFlow)
                            OutlinedButtonSample(allExpandFlow)
                            TextButtonSample(allExpandFlow)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ButtonContentTextSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem("Button（文字）", allExpandFlow, padding = 20.dp) {
        Button(onClick = {
            context.showShortToast("你点了我！")
        }) {
            Text(text = "按钮2")
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun ButtonContentTextSamplePreview() {
    ButtonContentTextSample(remember { MutableStateFlow(true) })
}


@Composable
fun ButtonContentImageSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem("Button（图片）", allExpandFlow, padding = 20.dp) {
        Button(onClick = { context.showShortToast("你点了我！") }) {
            Image(
                painterResource(id = R.drawable.ic_expand_more),
                contentDescription = "",
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun ButtonContentImageSamplePreview() {
    ButtonContentImageSample(remember { MutableStateFlow(true) })
}


@Composable
fun ButtonDisabledSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem("Button（不可用）", allExpandFlow, padding = 20.dp) {
        Button(
            enabled = false,
            onClick = { context.showShortToast("你点了我！") }
        ) {
            Image(
                painterResource(id = R.drawable.ic_expand_more),
                contentDescription = "",
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun ButtonDisabledSamplePreview() {
    ButtonDisabledSample(remember { MutableStateFlow(true) })
}


@Composable
fun ButtonShapeSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem("Button（形状 - 圆角)", allExpandFlow, padding = 20.dp) {
        Button(
            shape = RoundedCornerShape(10.dp),
            onClick = { context.showShortToast("你点了我！") }
        ) {
            Image(
                painterResource(id = R.drawable.ic_expand_more),
                contentDescription = "",
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun ButtonShapeSamplePreview() {
    ButtonShapeSample(remember { MutableStateFlow(true) })
}


@Composable
fun ButtonColorSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem("Button（颜色 - 可用/不可用)", allExpandFlow, padding = 20.dp) {
        Row {
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Cyan,
                    contentColor = Color.Black,
                    disabledContainerColor = Color.Gray,
                    disabledContentColor = Color.White,
                ),
                onClick = { context.showShortToast("你点了我！") }
            ) {
                Image(
                    painterResource(id = R.drawable.ic_expand_more),
                    contentDescription = "",
                )
            }
            Spacer(modifier = Modifier.width(16.dp).height(10.dp))
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Cyan,
                    contentColor = Color.Black,
                    disabledContainerColor = Color.Gray,
                    disabledContentColor = Color.White,
                ),
                enabled = false,
                onClick = { context.showShortToast("你点了我！") }
            ) {
                Image(
                    painterResource(id = R.drawable.ic_expand_more),
                    contentDescription = "",
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun ButtonColorSamplePreview() {
    ButtonColorSample(remember { MutableStateFlow(true) })
}


@Composable
fun ButtonElevationSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem("Button（高度）", allExpandFlow, padding = 20.dp) {
        Button(
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 10.dp,
                pressedElevation = 0.dp
            ),
            onClick = { context.showShortToast("你点了我！") }
        ) {
            Image(
                painterResource(id = R.drawable.ic_expand_more),
                contentDescription = "",
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun ButtonElevationSamplePreview() {
    ButtonElevationSample(remember { MutableStateFlow(true) })
}


@Composable
fun ButtonBorderSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem("Button（描边）", allExpandFlow, padding = 20.dp) {
        Button(
            border = BorderStroke(1.dp, Color.Cyan),
            onClick = { context.showShortToast("你点了我！") }
        ) {
            Image(
                painterResource(id = R.drawable.ic_expand_more),
                contentDescription = "",
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun ButtonBorderSamplePreview() {
    ButtonBorderSample(remember { MutableStateFlow(true) })
}


@Composable
fun ButtonContentPaddingSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem("Button（内边距）", allExpandFlow, padding = 20.dp) {
        Button(
            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 24.dp),
            onClick = { context.showShortToast("你点了我！") }
        ) {
            Image(
                painterResource(id = R.drawable.ic_expand_more),
                contentDescription = "",
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun ButtonContentPaddingSamplePreview() {
    ButtonContentPaddingSample(remember { MutableStateFlow(true) })
}


@Composable
fun ElevatedButtonSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem("ElevatedButton", allExpandFlow, padding = 20.dp) {
        ElevatedButton(onClick = { context.showShortToast("你点了我！") }) {
            Image(
                painterResource(id = R.drawable.ic_expand_more),
                contentDescription = "",
                colorFilter = ColorFilter.tint(Color.Red)
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun ElevatedButtonSamplePreview() {
    ElevatedButtonSample(remember { MutableStateFlow(true) })
}


@Composable
fun FilledTonalButtonSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem("FilledTonalButton", allExpandFlow, padding = 20.dp) {
        FilledTonalButton(onClick = { context.showShortToast("你点了我！") }) {
            Image(
                painterResource(id = R.drawable.ic_expand_more),
                contentDescription = "",
                colorFilter = ColorFilter.tint(Color.Red)
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun FilledTonalButtonSamplePreview() {
    FilledTonalButtonSample(remember { MutableStateFlow(true) })
}


@Composable
fun OutlinedButtonSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem("OutlinedButton", allExpandFlow, padding = 20.dp) {
        OutlinedButton(onClick = { context.showShortToast("你点了我！") }) {
            Image(
                painterResource(id = R.drawable.ic_expand_more),
                contentDescription = "",
                colorFilter = ColorFilter.tint(Color.Red)
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun OutlinedButtonSamplePreview() {
    OutlinedButtonSample(remember { MutableStateFlow(true) })
}


@Composable
fun TextButtonSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem("TextButton", allExpandFlow, padding = 20.dp) {
        TextButton(onClick = { context.showShortToast("你点了我！") }) {
            Text(text = "发送")
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun TextButtonSamplePreview() {
    TextButtonSample(remember { MutableStateFlow(true) })
}