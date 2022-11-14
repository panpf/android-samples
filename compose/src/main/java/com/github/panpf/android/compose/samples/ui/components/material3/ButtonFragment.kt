package com.github.panpf.android.compose.samples.ui.components.material3

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.OutlinedIconToggleButton
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem3
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
import com.github.panpf.tools4a.toast.ktx.showShortToast
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class ButtonFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "Button - Material3"
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
            // todo SegmentedButton Planned https://m3.material.io/components/segmented-buttons/overview
        }
    }
}

@Composable
private fun ButtonContentTextSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem3("Button（Text）", allExpandFlow, padding = 20.dp) {
        Button(onClick = {
            context.showShortToast("You tapped me!")
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
    ExpandableItem3("Button（Icon）", allExpandFlow, padding = 20.dp) {
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
private fun ButtonContentImageSamplePreview() {
    ButtonContentImageSample(remember { MutableStateFlow(true) })
}


@Composable
private fun ButtonShapeSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem3("Button（shape)", allExpandFlow, padding = 20.dp) {
        Button(
            shape = RoundedCornerShape(10.dp),
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
private fun ButtonShapeSamplePreview() {
    ButtonShapeSample(remember { MutableStateFlow(true) })
}


@Composable
private fun ButtonColorSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem3("Button（colors)", allExpandFlow, padding = 20.dp) {
        Row {
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Cyan,
                    contentColor = Color.Black,
                    disabledContainerColor = Color.Gray,
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
                    containerColor = Color.Cyan,
                    contentColor = Color.Black,
                    disabledContainerColor = Color.Gray,
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
private fun ButtonColorSamplePreview() {
    ButtonColorSample(remember { MutableStateFlow(true) })
}


@Composable
private fun ButtonElevationSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem3("Button（elevation）", allExpandFlow, padding = 20.dp) {
        Button(
            elevation = ButtonDefaults.buttonElevation(
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
private fun ButtonElevationSamplePreview() {
    ButtonElevationSample(remember { MutableStateFlow(true) })
}


@Composable
private fun ButtonBorderSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem3("Button（border）", allExpandFlow, padding = 20.dp) {
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
private fun ButtonBorderSamplePreview() {
    ButtonBorderSample(remember { MutableStateFlow(true) })
}


@Composable
private fun ButtonContentPaddingSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem3("Button（contentPadding）", allExpandFlow, padding = 20.dp) {
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
private fun ButtonContentPaddingSamplePreview() {
    ButtonContentPaddingSample(remember { MutableStateFlow(true) })
}


@Composable
private fun ElevatedButtonSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem3("ElevatedButton", allExpandFlow, padding = 20.dp) {
        ElevatedButton(onClick = { context.showShortToast("You tapped me!") }) {
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
private fun ElevatedButtonSamplePreview() {
    ElevatedButtonSample(remember { MutableStateFlow(true) })
}


@Composable
private fun FilledTonalButtonSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem3("FilledTonalButton", allExpandFlow, padding = 20.dp) {
        FilledTonalButton(onClick = { context.showShortToast("You tapped me!") }) {
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
private fun FilledTonalButtonSamplePreview() {
    FilledTonalButtonSample(remember { MutableStateFlow(true) })
}


@Composable
private fun OutlinedButtonSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem3("OutlinedButton", allExpandFlow, padding = 20.dp) {
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
private fun OutlinedButtonSamplePreview() {
    OutlinedButtonSample(remember { MutableStateFlow(true) })
}


@Composable
private fun TextButtonSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem3("TextButton", allExpandFlow, padding = 20.dp) {
        TextButton(onClick = { context.showShortToast("You tapped me!") }) {
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
    ExpandableItem3("IconButton", allExpandFlow, padding = 20.dp) {
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
private fun IconButtonSamplePreview() {
    IconButtonSample(remember { MutableStateFlow(true) })
}


@Composable
private fun FilledIconButtonSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem3("FilledIconButton", allExpandFlow, padding = 20.dp) {
        FilledIconButton(onClick = { context.showShortToast("You tapped me!") }) {
            Icon(
                painterResource(id = R.drawable.ic_arrow_down),
                contentDescription = "",
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun FilledIconButtonSamplePreview() {
    FilledIconButtonSample(remember { MutableStateFlow(true) })
}


@Composable
private fun FilledTonalIconButtonSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem3("FilledTonalIconButton", allExpandFlow, padding = 20.dp) {
        FilledTonalIconButton(onClick = { context.showShortToast("You tapped me!") }) {
            Icon(
                painterResource(id = R.drawable.ic_arrow_down),
                contentDescription = "",
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun FilledTonalIconButtonSamplePreview() {
    FilledTonalIconButtonSample(remember { MutableStateFlow(true) })
}


@Composable
private fun OutlinedIconButtonSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem3("OutlinedIconButton", allExpandFlow, padding = 20.dp) {
        OutlinedIconButton(onClick = { context.showShortToast("You tapped me!") }) {
            Icon(
                painterResource(id = R.drawable.ic_arrow_down),
                contentDescription = "",
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun OutlinedIconButtonSamplePreview() {
    OutlinedIconButtonSample(remember { MutableStateFlow(true) })
}


@Composable
private fun IconToggleButtonSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3("IconToggleButton", allExpandFlow, padding = 20.dp) {
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
private fun FilledIconToggleButtonSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3("FilledIconToggleButton", allExpandFlow, padding = 20.dp) {
        val checked = remember { mutableStateOf(false) }
        FilledIconToggleButton(
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
private fun FilledIconToggleButtonSamplePreview() {
    FilledIconToggleButtonSample(remember { MutableStateFlow(true) })
}


@Composable
private fun FilledTonalIconToggleButtonSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3("FilledTonalIconToggleButton", allExpandFlow, padding = 20.dp) {
        val checked = remember { mutableStateOf(false) }
        FilledTonalIconToggleButton(
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
private fun FilledTonalIconToggleButtonSamplePreview() {
    FilledTonalIconToggleButtonSample(remember { MutableStateFlow(true) })
}


@Composable
private fun OutlinedIconToggleButtonSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3("OutlinedIconToggleButton", allExpandFlow, padding = 20.dp) {
        val checked = remember { mutableStateOf(false) }
        OutlinedIconToggleButton(
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
private fun OutlinedIconToggleButtonSamplePreview() {
    OutlinedIconToggleButtonSample(remember { MutableStateFlow(true) })
}


@Composable
private fun FloatingActionButtonSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem3("FloatingActionButton", allExpandFlow, padding = 20.dp) {
        FloatingActionButton(onClick = { context.showShortToast("You tapped me!") }) {
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
    ExpandableItem3("FloatingActionButton（Shape）", allExpandFlow, padding = 20.dp) {
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
private fun FloatingActionButtonShapeSamplePreview() {
    FloatingActionButtonShapeSample(remember { MutableStateFlow(true) })
}


@Composable
private fun FloatingActionButtonColorSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem3("FloatingActionButton（Color）", allExpandFlow, padding = 20.dp) {
        FloatingActionButton(
            containerColor = Color.Cyan,
            contentColor = Color.Magenta,
            onClick = { context.showShortToast("You tapped me!") },
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
private fun SmallFloatingActionButtonSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem3("SmallFloatingActionButton", allExpandFlow, padding = 20.dp) {
        SmallFloatingActionButton(onClick = { context.showShortToast("You tapped me!") }) {
            Text(text = "Send")
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun SmallFloatingActionButtonSamplePreview() {
    SmallFloatingActionButtonSample(remember { MutableStateFlow(true) })
}


@Composable
private fun LargeFloatingActionButtonSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem3("LargeFloatingActionButton", allExpandFlow, padding = 20.dp) {
        LargeFloatingActionButton(onClick = { context.showShortToast("You tapped me!") }) {
            Text(text = "Send")
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun LargeFloatingActionButtonSamplePreview() {
    LargeFloatingActionButtonSample(remember { MutableStateFlow(true) })
}


@Composable
private fun ExtendedFloatingActionButtonSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3("ExtendedFloatingActionButton", allExpandFlow, padding = 20.dp) {
        val expanded = remember { mutableStateOf(false) }
        ExtendedFloatingActionButton(
            expanded = expanded.value,
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