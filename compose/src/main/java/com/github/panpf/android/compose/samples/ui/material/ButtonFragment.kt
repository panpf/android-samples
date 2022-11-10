package com.github.panpf.android.compose.samples.ui.material

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.IconToggleButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
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
                        color = MaterialTheme.colors.background
                    ) {
                        ExpandableLayout { allExpandFlow ->
                            ButtonContentTextSample(allExpandFlow)
                            ButtonContentImageSample(allExpandFlow)
                            ButtonShapeSample(allExpandFlow)
                            ButtonColorSample(allExpandFlow)
                            ButtonElevationSample(allExpandFlow)
                            ButtonBorderSample(allExpandFlow)
                            ButtonContentPaddingSample(allExpandFlow)
                            OutlinedButtonSample(allExpandFlow)
                            TextButtonSample(allExpandFlow)
                            IconButtonSample(allExpandFlow)
                            IconToggleButtonSample(allExpandFlow)
                            FloatingActionButtonSample(allExpandFlow)
                            FloatingActionButtonShapeSample(allExpandFlow)
                            FloatingActionButtonColorSample(allExpandFlow)
                            ExtendedFloatingActionButtonSample(allExpandFlow)
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
    ExpandableItem("Button（Text）", allExpandFlow, padding = 20.dp) {
        Button(onClick = {
            context.showShortToast("You tapped me!")
        }) {
            Text(text = "Send")
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
    ExpandableItem("Button（Icon）", allExpandFlow, padding = 20.dp) {
        Button(onClick = { context.showShortToast("You tapped me!") }) {
            Icon(
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
fun ButtonShapeSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem("Button（shape)", allExpandFlow, padding = 20.dp) {
        Button(
            shape = RoundedCornerShape(50),
            onClick = { context.showShortToast("You tapped me!") }
        ) {
            Icon(
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
    ExpandableItem("Button（colors)", allExpandFlow, padding = 20.dp) {
        Row {
            Button(
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Cyan,
                    contentColor = Color.Black,
                    disabledBackgroundColor = Color.Gray,
                    disabledContentColor = Color.White,
                ),
                onClick = { context.showShortToast("You tapped me!") }
            ) {
                Icon(
                    painterResource(id = R.drawable.ic_arrow_down),
                    contentDescription = "",
                )
            }
            Spacer(
                modifier = Modifier
                    .width(16.dp)
                    .height(10.dp)
            )
            Button(
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Cyan,
                    contentColor = Color.Black,
                    disabledBackgroundColor = Color.Gray,
                    disabledContentColor = Color.White,
                ),
                enabled = false,
                onClick = { context.showShortToast("You tapped me!") }
            ) {
                Icon(
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
    ExpandableItem("Button（elevation）", allExpandFlow, padding = 20.dp) {
        Button(
            elevation = ButtonDefaults.elevation(
                defaultElevation = 10.dp,
                pressedElevation = 0.dp
            ),
            onClick = { context.showShortToast("You tapped me!") }
        ) {
            Icon(
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
    ExpandableItem("Button（border）", allExpandFlow, padding = 20.dp) {
        Button(
            border = BorderStroke(1.dp, Color.Cyan),
            onClick = { context.showShortToast("You tapped me!") }
        ) {
            Icon(
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
    ExpandableItem("Button（contentPadding）", allExpandFlow, padding = 20.dp) {
        Button(
            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 24.dp),
            onClick = { context.showShortToast("You tapped me!") }
        ) {
            Icon(
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
fun OutlinedButtonSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem("OutlinedButton", allExpandFlow, padding = 20.dp) {
        OutlinedButton(onClick = { context.showShortToast("You tapped me!") }) {
            Icon(
                painterResource(id = R.drawable.ic_arrow_down),
                contentDescription = "",
                tint = Color.Red
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
        TextButton(onClick = { context.showShortToast("You tapped me!") }) {
            Text(text = "Send")
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
        IconButton(onClick = { context.showShortToast("You tapped me!") }) {
            Icon(
                painterResource(id = R.drawable.ic_arrow_down),
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
fun IconToggleButtonSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem("IconToggleButton", allExpandFlow, padding = 20.dp) {
        val checked = remember { mutableStateOf(false) }
        IconToggleButton(
            checked = checked.value,
            onCheckedChange = { checked.value = it }
        ) {
            val iconResId =
                if (checked.value) R.drawable.ic_arrow_up else R.drawable.ic_arrow_down
            Icon(
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
fun FloatingActionButtonSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem("FloatingActionButton", allExpandFlow, padding = 20.dp) {
        FloatingActionButton(onClick = { context.showShortToast("You tapped me!") }) {
            Text(text = "Send")
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
            onClick = { context.showShortToast("You tapped me!") },
        ) {
            Text(text = "Send")
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
            backgroundColor = Color.Cyan,
            contentColor = Color.Magenta,
            onClick = { context.showShortToast("You tapped me!") },
        ) {
            Text(text = "Send")
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun FloatingActionButtonColorSamplePreview() {
    FloatingActionButtonColorSample(remember { MutableStateFlow(true) })
}


@Composable
fun ExtendedFloatingActionButtonSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem("ExtendedFloatingActionButton", allExpandFlow, padding = 20.dp) {
        val expanded = remember { mutableStateOf(false) }
        ExtendedFloatingActionButton(
            onClick = { expanded.value = !expanded.value },
            text = { Text(text = "Expand") },
            icon = {
                Icon(
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