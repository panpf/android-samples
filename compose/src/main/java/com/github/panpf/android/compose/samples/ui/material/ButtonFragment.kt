package com.github.panpf.android.compose.samples.ui.material

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.panpf.android.compose.samples.R
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.MaterialComposeAppBarFragment
import com.github.panpf.tools4a.toast.ktx.showShortToast
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class ButtonFragment : MaterialComposeAppBarFragment() {

    override fun getTitle(): String {
        return "Button - Material"
    }

    @Composable
    override fun DrawContent() {
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

@Composable
private fun ButtonContentTextSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem("Button（Text）", allExpandFlow, padding = 20.dp) {
        Button(onClick = {
            context.showShortToast("You click me!")
        }) {
            Text(text = "Send")
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun ButtonContentTextSamplePreview() {
    ButtonContentTextSample(remember { MutableStateFlow(true) })
}


@Composable
private fun ButtonContentImageSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem("Button（Icon）", allExpandFlow, padding = 20.dp) {
        Button(onClick = { context.showShortToast("You click me!") }) {
            Icon(
                painterResource(id = R.drawable.ic_arrow_down),
                contentDescription = "",
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun ButtonContentImageSamplePreview() {
    ButtonContentImageSample(remember { MutableStateFlow(true) })
}


@Composable
private fun ButtonShapeSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem("Button（shape)", allExpandFlow, padding = 20.dp) {
        Button(
            shape = RoundedCornerShape(50),
            onClick = { context.showShortToast("You click me!") }
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
private fun ButtonShapeSamplePreview() {
    ButtonShapeSample(remember { MutableStateFlow(true) })
}


@Composable
private fun ButtonColorSample(allExpandFlow: Flow<Boolean>) {
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
                onClick = { context.showShortToast("You click me!") }
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
                onClick = { context.showShortToast("You click me!") }
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
private fun ButtonColorSamplePreview() {
    ButtonColorSample(remember { MutableStateFlow(true) })
}


@Composable
private fun ButtonElevationSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem("Button（elevation）", allExpandFlow, padding = 20.dp) {
        Button(
            elevation = ButtonDefaults.elevation(
                defaultElevation = 10.dp,
                pressedElevation = 0.dp
            ),
            onClick = { context.showShortToast("You click me!") }
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
private fun ButtonElevationSamplePreview() {
    ButtonElevationSample(remember { MutableStateFlow(true) })
}


@Composable
private fun ButtonBorderSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem("Button（border）", allExpandFlow, padding = 20.dp) {
        Button(
            border = BorderStroke(1.dp, Color.Cyan),
            onClick = { context.showShortToast("You click me!") }
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
private fun ButtonBorderSamplePreview() {
    ButtonBorderSample(remember { MutableStateFlow(true) })
}


@Composable
private fun ButtonContentPaddingSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem("Button（contentPadding）", allExpandFlow, padding = 20.dp) {
        Button(
            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 24.dp),
            onClick = { context.showShortToast("You click me!") }
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
private fun ButtonContentPaddingSamplePreview() {
    ButtonContentPaddingSample(remember { MutableStateFlow(true) })
}


@Composable
private fun OutlinedButtonSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem("OutlinedButton", allExpandFlow, padding = 20.dp) {
        OutlinedButton(onClick = { context.showShortToast("You click me!") }) {
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
private fun OutlinedButtonSamplePreview() {
    OutlinedButtonSample(remember { MutableStateFlow(true) })
}


@Composable
private fun TextButtonSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem("TextButton", allExpandFlow, padding = 20.dp) {
        TextButton(onClick = { context.showShortToast("You click me!") }) {
            Text(text = "Send")
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun TextButtonSamplePreview() {
    TextButtonSample(remember { MutableStateFlow(true) })
}


@Composable
private fun IconButtonSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem("IconButton", allExpandFlow, padding = 20.dp) {
        IconButton(onClick = { context.showShortToast("You click me!") }) {
            Icon(
                painterResource(id = R.drawable.ic_arrow_down),
                contentDescription = "",
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun IconButtonSamplePreview() {
    IconButtonSample(remember { MutableStateFlow(true) })
}


@Composable
private fun IconToggleButtonSample(allExpandFlow: Flow<Boolean>) {
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
private fun IconToggleButtonSamplePreview() {
    IconToggleButtonSample(remember { MutableStateFlow(true) })
}


@Composable
private fun FloatingActionButtonSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem("FloatingActionButton", allExpandFlow, padding = 20.dp) {
        FloatingActionButton(onClick = { context.showShortToast("You click me!") }) {
            Text(text = "Send")
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun FloatingActionButtonSamplePreview() {
    FloatingActionButtonSample(remember { MutableStateFlow(true) })
}


@Composable
private fun FloatingActionButtonShapeSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem("FloatingActionButton（Shape）", allExpandFlow, padding = 20.dp) {
        FloatingActionButton(
            shape = CircleShape,
            onClick = { context.showShortToast("You click me!") },
        ) {
            Text(text = "Send")
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun FloatingActionButtonShapeSamplePreview() {
    FloatingActionButtonShapeSample(remember { MutableStateFlow(true) })
}


@Composable
private fun FloatingActionButtonColorSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem("FloatingActionButton（Color）", allExpandFlow, padding = 20.dp) {
        FloatingActionButton(
            backgroundColor = Color.Cyan,
            contentColor = Color.Magenta,
            onClick = { context.showShortToast("You click me!") },
        ) {
            Text(text = "Send")
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun FloatingActionButtonColorSamplePreview() {
    FloatingActionButtonColorSample(remember { MutableStateFlow(true) })
}


@Composable
private fun ExtendedFloatingActionButtonSample(allExpandFlow: Flow<Boolean>) {
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
private fun ExtendedFloatingActionButtonSamplePreview() {
    ExtendedFloatingActionButtonSample(remember { MutableStateFlow(true) })
}