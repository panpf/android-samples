package com.github.panpf.android.compose.samples.ui.material3

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.FilledIconToggleButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.FilledTonalIconToggleButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.OutlinedIconToggleButton
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
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
import com.github.panpf.android.compose.samples.R.drawable
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
                            IconButtonSample(allExpandFlow)
                            FilledIconButtonSample(allExpandFlow)
                            FilledTonalIconButtonSample(allExpandFlow)
                            OutlinedIconButtonSample(allExpandFlow)
                            IconToggleButtonSample(allExpandFlow)
                            FilledIconToggleButtonSample(allExpandFlow)
                            FilledTonalIconToggleButtonSample(allExpandFlow)
                            OutlinedIconToggleButtonSample(allExpandFlow)
                            FloatingActionButtonSample(allExpandFlow)
                            FloatingActionButtonShapeSample(allExpandFlow)
                            FloatingActionButtonColorSample(allExpandFlow)
                            SmallFloatingActionButtonSample(allExpandFlow)
                            LargeFloatingActionButtonSample(allExpandFlow)
                            ExtendedFloatingActionButtonSample(allExpandFlow)
                            // todo Segmented button planned https://m3.material.io/components/segmented-buttons/overview
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
                painterResource(id = R.drawable.ic_arrow_down),
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
                painterResource(id = R.drawable.ic_arrow_down),
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
                painterResource(id = R.drawable.ic_arrow_down),
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
                    painterResource(id = R.drawable.ic_arrow_down),
                    contentDescription = "",
                )
            }
            Spacer(modifier = Modifier
                .width(16.dp)
                .height(10.dp))
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
                    painterResource(id = R.drawable.ic_arrow_down),
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
                painterResource(id = R.drawable.ic_arrow_down),
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
                painterResource(id = R.drawable.ic_arrow_down),
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
                painterResource(id = R.drawable.ic_arrow_down),
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
                painterResource(id = R.drawable.ic_arrow_down),
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
                painterResource(id = R.drawable.ic_arrow_down),
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
                painterResource(id = R.drawable.ic_arrow_down),
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


@Composable
fun IconButtonSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem("IconButton", allExpandFlow, padding = 20.dp) {
        IconButton(onClick = { context.showShortToast("你点了我！") }) {
            Image(
                painterResource(id = drawable.ic_arrow_down),
                contentDescription = "",
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun IconButtonSamplePreview() {
    IconButtonSample(remember { MutableStateFlow(true) })
}


@Composable
fun FilledIconButtonSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem("FilledIconButton", allExpandFlow, padding = 20.dp) {
        FilledIconButton(onClick = { context.showShortToast("你点了我！") }) {
            Image(
                painterResource(id = drawable.ic_arrow_down),
                contentDescription = "",
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun FilledIconButtonSamplePreview() {
    FilledIconButtonSample(remember { MutableStateFlow(true) })
}


@Composable
fun FilledTonalIconButtonSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem("FilledTonalIconButton", allExpandFlow, padding = 20.dp) {
        FilledTonalIconButton(onClick = { context.showShortToast("你点了我！") }) {
            Image(
                painterResource(id = drawable.ic_arrow_down),
                contentDescription = "",
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun FilledTonalIconButtonSamplePreview() {
    FilledTonalIconButtonSample(remember { MutableStateFlow(true) })
}


@Composable
fun OutlinedIconButtonSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem("OutlinedIconButton", allExpandFlow, padding = 20.dp) {
        OutlinedIconButton(onClick = { context.showShortToast("你点了我！") }) {
            Image(
                painterResource(id = drawable.ic_arrow_down),
                contentDescription = "",
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun OutlinedIconButtonSamplePreview() {
    OutlinedIconButtonSample(remember { MutableStateFlow(true) })
}


@Composable
fun IconToggleButtonSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem("IconToggleButton", allExpandFlow, padding = 20.dp) {
        val checked = remember { mutableStateOf(false) }
        IconToggleButton(
            checked = checked.value,
            onCheckedChange = { checked.value = it }
        ) {
            val iconResId =
                if (checked.value) drawable.ic_arrow_up else drawable.ic_arrow_down
            Image(
                painterResource(id = iconResId),
                contentDescription = "",
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun IconToggleButtonSamplePreview() {
    IconToggleButtonSample(remember { MutableStateFlow(true) })
}


@Composable
fun FilledIconToggleButtonSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem("FilledIconToggleButton", allExpandFlow, padding = 20.dp) {
        val checked = remember { mutableStateOf(false) }
        FilledIconToggleButton(
            checked = checked.value,
            onCheckedChange = { checked.value = it }
        ) {
            val iconResId =
                if (checked.value) drawable.ic_arrow_up else drawable.ic_arrow_down
            Image(
                painterResource(id = iconResId),
                contentDescription = "",
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun FilledIconToggleButtonSamplePreview() {
    FilledIconToggleButtonSample(remember { MutableStateFlow(true) })
}


@Composable
fun FilledTonalIconToggleButtonSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem("FilledTonalIconToggleButton", allExpandFlow, padding = 20.dp) {
        val checked = remember { mutableStateOf(false) }
        FilledTonalIconToggleButton(
            checked = checked.value,
            onCheckedChange = { checked.value = it }
        ) {
            val iconResId =
                if (checked.value) drawable.ic_arrow_up else drawable.ic_arrow_down
            Image(
                painterResource(id = iconResId),
                contentDescription = "",
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun FilledTonalIconToggleButtonSamplePreview() {
    FilledTonalIconToggleButtonSample(remember { MutableStateFlow(true) })
}


@Composable
fun OutlinedIconToggleButtonSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem("OutlinedIconToggleButton", allExpandFlow, padding = 20.dp) {
        val checked = remember { mutableStateOf(false) }
        OutlinedIconToggleButton(
            checked = checked.value,
            onCheckedChange = { checked.value = it }
        ) {
            val iconResId =
                if (checked.value) drawable.ic_arrow_up else drawable.ic_arrow_down
            Image(
                painterResource(id = iconResId),
                contentDescription = "",
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun OutlinedIconToggleButtonSamplePreview() {
    OutlinedIconToggleButtonSample(remember { MutableStateFlow(true) })
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
                    painter = painterResource(id = drawable.ic_arrow_down),
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