package com.github.panpf.android.compose.samples.ui.material3

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
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
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem3
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
import com.github.panpf.tools4a.toast.ktx.showShortToast
import com.google.accompanist.flowlayout.FlowRow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class ButtonFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "Button - Material3"
    }

    @Composable
    override fun DrawContent() {
        ExpandableLayout { allExpandFlow ->
            ButtonSample(allExpandFlow)
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
            SmallFloatingActionButtonSample(allExpandFlow)
            LargeFloatingActionButtonSample(allExpandFlow)
            ExtendedFloatingActionButtonSample(allExpandFlow)
            // todo SegmentedButton Planned https://m3.material.io/components/segmented-buttons/overview
        }
    }
}


@Composable
private fun ButtonSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem("Button", allExpandFlow, padding = 20.dp) {
        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp), mainAxisSpacing = 20.dp, crossAxisSpacing = 20.dp
        ) {
            Column {
                Text(text = "Text")
                Spacer(modifier = Modifier.size(10.dp))
                Button(onClick = {
                    context.showShortToast("You click me!")
                }) {
                    Text(text = "Send")
                }
            }

            Column {
                Text(text = "Icon")
                Spacer(modifier = Modifier.size(10.dp))
                Button(onClick = { context.showShortToast("You click me!") }) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowDown,
                        contentDescription = "",
                    )
                }
            }

            Column {
                Text(text = "shape")
                Spacer(modifier = Modifier.size(10.dp))
                Button(
                    shape = RoundedCornerShape(10.dp),
                    onClick = { context.showShortToast("You click me!") }
                ) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowDown,
                        contentDescription = "",
                    )
                }
            }

            Column {
                Text(text = "colors")
                Spacer(modifier = Modifier.size(10.dp))
                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Cyan,
                        contentColor = Color.Black,
                        disabledContainerColor = Color.Gray,
                        disabledContentColor = Color.White,
                    ),
                    onClick = { context.showShortToast("You click me!") }
                ) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowDown,
                        contentDescription = "",
                    )
                }
            }

            Column {
                Text(text = "elevation")
                Spacer(modifier = Modifier.size(10.dp))
                Button(
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 10.dp,
                        pressedElevation = 0.dp
                    ),
                    onClick = { context.showShortToast("You click me!") }
                ) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowDown,
                        contentDescription = "",
                    )
                }
            }

            Column {
                Text(text = "border")
                Spacer(modifier = Modifier.size(10.dp))
                Button(
                    border = BorderStroke(1.dp, Color.Cyan),
                    onClick = { context.showShortToast("You click me!") }
                ) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowDown,
                        contentDescription = "",
                    )
                }
            }

            Column {
                Text(text = "contentPadding")
                Spacer(modifier = Modifier.size(10.dp))
                Button(
                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 24.dp),
                    onClick = { context.showShortToast("You click me!") }
                ) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowDown,
                        contentDescription = "",
                    )
                }
            }

            Column {
                Text(text = "enabled=false")
                Spacer(modifier = Modifier.size(10.dp))
                Button(
                    enabled = false,
                    onClick = { context.showShortToast("You click me!") }
                ) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowDown,
                        contentDescription = "",
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun ButtonSamplePreview() {
    ButtonSample(remember { MutableStateFlow(true) })
}


@Composable
private fun ElevatedButtonSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem3("ElevatedButton", allExpandFlow, padding = 20.dp) {
        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp), mainAxisSpacing = 20.dp, crossAxisSpacing = 20.dp
        ) {
            Column {
                Text(text = "Default")
                Spacer(modifier = Modifier.size(10.dp))
                ElevatedButton(onClick = { context.showShortToast("You click me!") }) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowDown,
                        contentDescription = "",
                    )
                }
            }

            Column {
                Text(text = "shape")
                Spacer(modifier = Modifier.size(10.dp))
                ElevatedButton(
                    shape = RoundedCornerShape(10.dp),
                    onClick = { context.showShortToast("You click me!") }
                ) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowDown,
                        contentDescription = "",
                    )
                }
            }

            Column {
                Text(text = "colors")
                Spacer(modifier = Modifier.size(10.dp))
                ElevatedButton(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Cyan,
                        contentColor = Color.Black,
                        disabledContainerColor = Color.Gray,
                        disabledContentColor = Color.White,
                    ),
                    onClick = { context.showShortToast("You click me!") }
                ) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowDown,
                        contentDescription = "",
                    )
                }
            }

            Column {
                Text(text = "elevation")
                Spacer(modifier = Modifier.size(10.dp))
                ElevatedButton(
                    elevation = ButtonDefaults.elevatedButtonElevation(
                        defaultElevation = 10.dp,
                        pressedElevation = 0.dp
                    ),
                    onClick = { context.showShortToast("You click me!") }
                ) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowDown,
                        contentDescription = "",
                    )
                }
            }

            Column {
                Text(text = "border")
                Spacer(modifier = Modifier.size(10.dp))
                ElevatedButton(
                    border = BorderStroke(1.dp, Color.Cyan),
                    onClick = { context.showShortToast("You click me!") }
                ) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowDown,
                        contentDescription = "",
                    )
                }
            }

            Column {
                Text(text = "contentPadding")
                Spacer(modifier = Modifier.size(10.dp))
                ElevatedButton(
                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 24.dp),
                    onClick = { context.showShortToast("You click me!") }
                ) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowDown,
                        contentDescription = "",
                    )
                }
            }

            Column {
                Text(text = "enabled=false")
                Spacer(modifier = Modifier.size(10.dp))
                ElevatedButton(
                    enabled = false,
                    onClick = { context.showShortToast("You click me!") }
                ) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowDown,
                        contentDescription = "",
                    )
                }
            }
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
        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp), mainAxisSpacing = 20.dp, crossAxisSpacing = 20.dp
        ) {
            Column {
                Text(text = "Default")
                Spacer(modifier = Modifier.size(10.dp))
                FilledTonalButton(onClick = { context.showShortToast("You click me!") }) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowDown,
                        contentDescription = "",
                    )
                }
            }

            Column {
                Text(text = "shape")
                Spacer(modifier = Modifier.size(10.dp))
                FilledTonalButton(
                    shape = RoundedCornerShape(10.dp),
                    onClick = { context.showShortToast("You click me!") }
                ) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowDown,
                        contentDescription = "",
                    )
                }
            }

            Column {
                Text(text = "colors")
                Spacer(modifier = Modifier.size(10.dp))
                FilledTonalButton(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Cyan,
                        contentColor = Color.Black,
                        disabledContainerColor = Color.Gray,
                        disabledContentColor = Color.White,
                    ),
                    onClick = { context.showShortToast("You click me!") }
                ) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowDown,
                        contentDescription = "",
                    )
                }
            }

            Column {
                Text(text = "elevation")
                Spacer(modifier = Modifier.size(10.dp))
                FilledTonalButton(
                    elevation = ButtonDefaults.filledTonalButtonElevation(
                        defaultElevation = 10.dp,
                        pressedElevation = 0.dp
                    ),
                    onClick = { context.showShortToast("You click me!") }
                ) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowDown,
                        contentDescription = "",
                    )
                }
            }

            Column {
                Text(text = "border")
                Spacer(modifier = Modifier.size(10.dp))
                FilledTonalButton(
                    border = BorderStroke(1.dp, Color.Cyan),
                    onClick = { context.showShortToast("You click me!") }
                ) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowDown,
                        contentDescription = "",
                    )
                }
            }

            Column {
                Text(text = "contentPadding")
                Spacer(modifier = Modifier.size(10.dp))
                FilledTonalButton(
                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 24.dp),
                    onClick = { context.showShortToast("You click me!") }
                ) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowDown,
                        contentDescription = "",
                    )
                }
            }

            Column {
                Text(text = "enabled=false")
                Spacer(modifier = Modifier.size(10.dp))
                FilledTonalButton(
                    enabled = false,
                    onClick = { context.showShortToast("You click me!") }
                ) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowDown,
                        contentDescription = "",
                    )
                }
            }
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
        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp), mainAxisSpacing = 20.dp, crossAxisSpacing = 20.dp
        ) {
            Column {
                Text(text = "Text")
                Spacer(modifier = Modifier.size(10.dp))
                OutlinedButton(onClick = {
                    context.showShortToast("You click me!")
                }) {
                    Text(text = "Send")
                }
            }

            Column {
                Text(text = "Icon")
                Spacer(modifier = Modifier.size(10.dp))
                OutlinedButton(onClick = { context.showShortToast("You click me!") }) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowDown,
                        contentDescription = "",
                    )
                }
            }

            Column {
                Text(text = "shape")
                Spacer(modifier = Modifier.size(10.dp))
                OutlinedButton(
                    shape = RoundedCornerShape(10.dp),
                    onClick = { context.showShortToast("You click me!") }
                ) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowDown,
                        contentDescription = "",
                    )
                }
            }

            Column {
                Text(text = "colors")
                Spacer(modifier = Modifier.size(10.dp))
                OutlinedButton(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Cyan,
                        contentColor = Color.Black,
                        disabledContainerColor = Color.Gray,
                        disabledContentColor = Color.White,
                    ),
                    onClick = { context.showShortToast("You click me!") }
                ) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowDown,
                        contentDescription = "",
                    )
                }
            }

            Column {
                Text(text = "elevation")
                Spacer(modifier = Modifier.size(10.dp))
                OutlinedButton(
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 10.dp,
                        pressedElevation = 0.dp
                    ),
                    onClick = { context.showShortToast("You click me!") }
                ) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowDown,
                        contentDescription = "",
                    )
                }
            }

            Column {
                Text(text = "border")
                Spacer(modifier = Modifier.size(10.dp))
                OutlinedButton(
                    border = BorderStroke(1.dp, Color.Cyan),
                    onClick = { context.showShortToast("You click me!") }
                ) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowDown,
                        contentDescription = "",
                    )
                }
            }

            Column {
                Text(text = "contentPadding")
                Spacer(modifier = Modifier.size(10.dp))
                OutlinedButton(
                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 24.dp),
                    onClick = { context.showShortToast("You click me!") }
                ) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowDown,
                        contentDescription = "",
                    )
                }
            }

            Column {
                Text(text = "enabled=false")
                Spacer(modifier = Modifier.size(10.dp))
                OutlinedButton(
                    enabled = false,
                    onClick = { context.showShortToast("You click me!") }
                ) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowDown,
                        contentDescription = "",
                    )
                }
            }
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
        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp), mainAxisSpacing = 20.dp, crossAxisSpacing = 20.dp
        ) {
            Column {
                Text(text = "Default")
                Spacer(modifier = Modifier.size(10.dp))
                TextButton(onClick = {
                    context.showShortToast("You click me!")
                }) {
                    Text(text = "Send")
                }
            }

            Column {
                Text(text = "shape")
                Spacer(modifier = Modifier.size(10.dp))
                TextButton(
                    shape = RoundedCornerShape(10.dp),
                    onClick = { context.showShortToast("You click me!") }
                ) {
                    Text(text = "Send")
                }
            }

            Column {
                Text(text = "colors")
                Spacer(modifier = Modifier.size(10.dp))
                TextButton(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Cyan,
                        contentColor = Color.Black,
                        disabledContainerColor = Color.Gray,
                        disabledContentColor = Color.White,
                    ),
                    onClick = { context.showShortToast("You click me!") }
                ) {
                    Text(text = "Send")
                }
            }

            Column {
                Text(text = "elevation")
                Spacer(modifier = Modifier.size(10.dp))
                TextButton(
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 10.dp,
                        pressedElevation = 0.dp
                    ),
                    onClick = { context.showShortToast("You click me!") }
                ) {
                    Text(text = "Send")
                }
            }

            Column {
                Text(text = "border")
                Spacer(modifier = Modifier.size(10.dp))
                TextButton(
                    border = BorderStroke(1.dp, Color.Cyan),
                    onClick = { context.showShortToast("You click me!") }
                ) {
                    Text(text = "Send")
                }
            }

            Column {
                Text(text = "contentPadding")
                Spacer(modifier = Modifier.size(10.dp))
                TextButton(
                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 24.dp),
                    onClick = { context.showShortToast("You click me!") }
                ) {
                    Text(text = "Send")
                }
            }

            Column {
                Text(text = "enabled=false")
                Spacer(modifier = Modifier.size(10.dp))
                TextButton(
                    enabled = false,
                    onClick = { context.showShortToast("You click me!") }
                ) {
                    Text(text = "Send")
                }
            }
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
        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp), mainAxisSpacing = 20.dp, crossAxisSpacing = 20.dp
        ) {
            Column {
                Text(text = "Default")
                Spacer(modifier = Modifier.size(10.dp))
                IconButton(onClick = { context.showShortToast("You click me!") }) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowDown,
                        contentDescription = "",
                    )
                }
            }

            Column {
                Text(text = "colors")
                Spacer(modifier = Modifier.size(10.dp))
                IconButton(
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = Color.Cyan,
                        contentColor = Color.Red,
                        disabledContainerColor = Color.Gray,
                        disabledContentColor = Color.White,
                    ),
                    onClick = { context.showShortToast("You click me!") }
                ) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowDown,
                        contentDescription = "",
                    )
                }
            }

            Column {
                Text(text = "enabled=false")
                Spacer(modifier = Modifier.size(10.dp))
                IconButton(
                    enabled = false,
                    onClick = { context.showShortToast("You click me!") }
                ) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowDown,
                        contentDescription = "",
                    )
                }
            }
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
        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp), mainAxisSpacing = 20.dp, crossAxisSpacing = 20.dp
        ) {
            Column {
                Text(text = "Default")
                Spacer(modifier = Modifier.size(10.dp))
                FilledIconButton(onClick = { context.showShortToast("You click me!") }) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowDown,
                        contentDescription = "",
                    )
                }
            }

            Column {
                Text(text = "colors")
                Spacer(modifier = Modifier.size(10.dp))
                FilledIconButton(
                    colors = IconButtonDefaults.filledIconButtonColors(
                        containerColor = Color.Cyan,
                        contentColor = Color.Red,
                        disabledContainerColor = Color.Gray,
                        disabledContentColor = Color.White,
                    ),
                    onClick = { context.showShortToast("You click me!") }
                ) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowDown,
                        contentDescription = "",
                    )
                }
            }

            Column {
                Text(text = "enabled=false")
                Spacer(modifier = Modifier.size(10.dp))
                FilledIconButton(
                    enabled = false,
                    onClick = { context.showShortToast("You click me!") }
                ) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowDown,
                        contentDescription = "",
                    )
                }
            }
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
        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp), mainAxisSpacing = 20.dp, crossAxisSpacing = 20.dp
        ) {
            Column {
                Text(text = "Default")
                Spacer(modifier = Modifier.size(10.dp))
                FilledTonalIconButton(onClick = { context.showShortToast("You click me!") }) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowDown,
                        contentDescription = "",
                    )
                }
            }

            Column {
                Text(text = "colors")
                Spacer(modifier = Modifier.size(10.dp))
                FilledTonalIconButton(
                    colors = IconButtonDefaults.filledTonalIconButtonColors(
                        containerColor = Color.Cyan,
                        contentColor = Color.Red,
                        disabledContainerColor = Color.Gray,
                        disabledContentColor = Color.White,
                    ),
                    onClick = { context.showShortToast("You click me!") }
                ) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowDown,
                        contentDescription = "",
                    )
                }
            }

            Column {
                Text(text = "enabled=false")
                Spacer(modifier = Modifier.size(10.dp))
                FilledTonalIconButton(
                    enabled = false,
                    onClick = { context.showShortToast("You click me!") }
                ) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowDown,
                        contentDescription = "",
                    )
                }
            }
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
        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp), mainAxisSpacing = 20.dp, crossAxisSpacing = 20.dp
        ) {
            Column {
                Text(text = "Default")
                Spacer(modifier = Modifier.size(10.dp))
                OutlinedIconButton(onClick = { context.showShortToast("You click me!") }) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowDown,
                        contentDescription = "",
                    )
                }
            }

            Column {
                Text(text = "shape")
                Spacer(modifier = Modifier.size(10.dp))
                OutlinedIconButton(
                    shape = RoundedCornerShape(10.dp),
                    onClick = { context.showShortToast("You click me!") }
                ) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowDown,
                        contentDescription = "",
                    )
                }
            }

            Column {
                Text(text = "colors")
                Spacer(modifier = Modifier.size(10.dp))
                OutlinedIconButton(
                    colors = IconButtonDefaults.outlinedIconButtonColors(
                        containerColor = Color.Cyan,
                        contentColor = Color.Red,
                        disabledContainerColor = Color.Gray,
                        disabledContentColor = Color.White,
                    ),
                    onClick = { context.showShortToast("You click me!") }
                ) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowDown,
                        contentDescription = "",
                    )
                }
            }

            Column {
                Text(text = "border")
                Spacer(modifier = Modifier.size(10.dp))
                OutlinedIconButton(
                    border = BorderStroke(1.dp, Color.Cyan),
                    onClick = { context.showShortToast("You click me!") }
                ) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowDown,
                        contentDescription = "",
                    )
                }
            }

            Column {
                Text(text = "enabled=false")
                Spacer(modifier = Modifier.size(10.dp))
                OutlinedIconButton(
                    enabled = false,
                    onClick = { context.showShortToast("You click me!") }
                ) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowDown,
                        contentDescription = "",
                    )
                }
            }
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
        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp), mainAxisSpacing = 20.dp, crossAxisSpacing = 20.dp
        ) {
            Column {
                Text(text = "Default")
                Spacer(modifier = Modifier.size(10.dp))
                val checked = remember { mutableStateOf(false) }
                IconToggleButton(
                    checked = checked.value,
                    onCheckedChange = { checked.value = it }
                ) {
                    Icon(
                        imageVector = if (checked.value)
                            Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                        contentDescription = "",
                    )
                }
            }

            Column {
                Text(text = "colors")
                Spacer(modifier = Modifier.size(10.dp))
                val checked = remember { mutableStateOf(false) }
                IconToggleButton(
                    checked = checked.value,
                    colors = IconButtonDefaults.iconToggleButtonColors(
                        containerColor = Color.Cyan,
                        contentColor = Color.Red,
                        disabledContainerColor = Color.Gray,
                        disabledContentColor = Color.White,
                    ),
                    onCheckedChange = { checked.value = it }
                ) {
                    Icon(
                        imageVector = if (checked.value)
                            Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                        contentDescription = "",
                    )
                }
            }

            Column {
                Text(text = "enabled=false")
                Spacer(modifier = Modifier.size(10.dp))
                val checked = remember { mutableStateOf(false) }
                IconToggleButton(
                    checked = checked.value,
                    enabled = false,
                    onCheckedChange = { checked.value = it }
                ) {
                    Icon(
                        imageVector = if (checked.value)
                            Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                        contentDescription = "",
                    )
                }
            }
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
        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp), mainAxisSpacing = 20.dp, crossAxisSpacing = 20.dp
        ) {
            Column {
                Text(text = "Default")
                Spacer(modifier = Modifier.size(10.dp))
                val checked = remember { mutableStateOf(false) }
                FilledIconToggleButton(
                    checked = checked.value,
                    onCheckedChange = { checked.value = it }
                ) {
                    Icon(
                        imageVector = if (checked.value)
                            Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                        contentDescription = "",
                    )
                }
            }

            Column {
                Text(text = "shape")
                Spacer(modifier = Modifier.size(10.dp))
                val checked = remember { mutableStateOf(false) }
                FilledIconToggleButton(
                    checked = checked.value,
                    shape = RoundedCornerShape(10.dp),
                    onCheckedChange = { checked.value = it }
                ) {
                    Icon(
                        imageVector = if (checked.value)
                            Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                        contentDescription = "",
                    )
                }
            }

            Column {
                Text(text = "colors")
                Spacer(modifier = Modifier.size(10.dp))
                val checked = remember { mutableStateOf(false) }
                FilledIconToggleButton(
                    checked = checked.value,
                    colors = IconButtonDefaults.filledIconToggleButtonColors(
                        containerColor = Color.Cyan,
                        contentColor = Color.Red,
                        disabledContainerColor = Color.Gray,
                        disabledContentColor = Color.White,
                    ),
                    onCheckedChange = { checked.value = it }
                ) {
                    Icon(
                        imageVector = if (checked.value)
                            Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                        contentDescription = "",
                    )
                }
            }

            Column {
                Text(text = "enabled=false")
                Spacer(modifier = Modifier.size(10.dp))
                val checked = remember { mutableStateOf(false) }
                FilledIconToggleButton(
                    checked = checked.value,
                    enabled = false,
                    onCheckedChange = { checked.value = it }
                ) {
                    Icon(
                        imageVector = if (checked.value)
                            Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                        contentDescription = "",
                    )
                }
            }
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
        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp), mainAxisSpacing = 20.dp, crossAxisSpacing = 20.dp
        ) {
            Column {
                Text(text = "Default")
                Spacer(modifier = Modifier.size(10.dp))
                val checked = remember { mutableStateOf(false) }
                FilledTonalIconToggleButton(
                    checked = checked.value,
                    onCheckedChange = { checked.value = it }
                ) {
                    Icon(
                        imageVector = if (checked.value)
                            Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                        contentDescription = "",
                    )
                }
            }

            Column {
                Text(text = "shape")
                Spacer(modifier = Modifier.size(10.dp))
                val checked = remember { mutableStateOf(false) }
                FilledTonalIconToggleButton(
                    checked = checked.value,
                    shape = RoundedCornerShape(10.dp),
                    onCheckedChange = { checked.value = it }
                ) {
                    Icon(
                        imageVector = if (checked.value)
                            Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                        contentDescription = "",
                    )
                }
            }

            Column {
                Text(text = "colors")
                Spacer(modifier = Modifier.size(10.dp))
                val checked = remember { mutableStateOf(false) }
                FilledTonalIconToggleButton(
                    checked = checked.value,
                    colors = IconButtonDefaults.filledTonalIconToggleButtonColors(
                        containerColor = Color.Cyan,
                        contentColor = Color.Red,
                        disabledContainerColor = Color.Gray,
                        disabledContentColor = Color.White,
                    ),
                    onCheckedChange = { checked.value = it }
                ) {
                    Icon(
                        imageVector = if (checked.value)
                            Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                        contentDescription = "",
                    )
                }
            }

            Column {
                Text(text = "enabled=false")
                Spacer(modifier = Modifier.size(10.dp))
                val checked = remember { mutableStateOf(false) }
                FilledTonalIconToggleButton(
                    checked = checked.value,
                    enabled = false,
                    onCheckedChange = { checked.value = it }
                ) {
                    Icon(
                        imageVector = if (checked.value)
                            Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                        contentDescription = "",
                    )
                }
            }
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
        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp), mainAxisSpacing = 20.dp, crossAxisSpacing = 20.dp
        ) {
            Column {
                Text(text = "Default")
                Spacer(modifier = Modifier.size(10.dp))
                val checked = remember { mutableStateOf(false) }
                OutlinedIconToggleButton(
                    checked = checked.value,
                    onCheckedChange = { checked.value = it }
                ) {
                    Icon(
                        imageVector = if (checked.value)
                            Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                        contentDescription = "",
                    )
                }
            }

            Column {
                Text(text = "shape")
                Spacer(modifier = Modifier.size(10.dp))
                val checked = remember { mutableStateOf(false) }
                OutlinedIconToggleButton(
                    checked = checked.value,
                    shape = RoundedCornerShape(10.dp),
                    onCheckedChange = { checked.value = it }
                ) {
                    Icon(
                        imageVector = if (checked.value)
                            Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                        contentDescription = "",
                    )
                }
            }

            Column {
                Text(text = "colors")
                Spacer(modifier = Modifier.size(10.dp))
                val checked = remember { mutableStateOf(false) }
                OutlinedIconToggleButton(
                    checked = checked.value,
                    colors = IconButtonDefaults.outlinedIconToggleButtonColors(
                        containerColor = Color.Cyan,
                        contentColor = Color.Red,
                        disabledContainerColor = Color.Gray,
                        disabledContentColor = Color.White,
                    ),
                    onCheckedChange = { checked.value = it }
                ) {
                    Icon(
                        imageVector = if (checked.value)
                            Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                        contentDescription = "",
                    )
                }
            }

            Column {
                Text(text = "border")
                Spacer(modifier = Modifier.size(10.dp))
                val checked = remember { mutableStateOf(false) }
                OutlinedIconToggleButton(
                    checked = checked.value,
                    border = BorderStroke(1.dp, Color.Cyan),
                    onCheckedChange = { checked.value = it }
                ) {
                    Icon(
                        imageVector = if (checked.value)
                            Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                        contentDescription = "",
                    )
                }
            }

            Column {
                Text(text = "enabled=false")
                Spacer(modifier = Modifier.size(10.dp))
                val checked = remember { mutableStateOf(false) }
                OutlinedIconToggleButton(
                    checked = checked.value,
                    enabled = false,
                    onCheckedChange = { checked.value = it }
                ) {
                    Icon(
                        imageVector = if (checked.value)
                            Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                        contentDescription = "",
                    )
                }
            }
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
        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp), mainAxisSpacing = 20.dp, crossAxisSpacing = 20.dp
        ) {
            Column {
                Text(text = "Default")
                Spacer(modifier = Modifier.size(10.dp))
                FloatingActionButton(onClick = { context.showShortToast("You click me!") }) {
                    Text(text = "Send")
                }
            }

            Column {
                Text(text = "shape")
                Spacer(modifier = Modifier.size(10.dp))
                FloatingActionButton(
                    shape = RoundedCornerShape(50),
                    onClick = { context.showShortToast("You click me!") },
                ) {
                    Text(text = "Send")
                }
            }

            Column {
                Text(text = "containerColor")
                Spacer(modifier = Modifier.size(10.dp))
                FloatingActionButton(
                    containerColor = Color.Cyan,
                    onClick = { context.showShortToast("You click me!") },
                ) {
                    Text(text = "Send")
                }
            }

            Column {
                Text(text = "contentColor")
                Spacer(modifier = Modifier.size(10.dp))
                FloatingActionButton(
                    contentColor = Color.Magenta,
                    onClick = { context.showShortToast("You click me!") },
                ) {
                    Text(text = "Send")
                }
            }

            Column {
                Text(text = "elevation")
                Spacer(modifier = Modifier.size(10.dp))
                FloatingActionButton(
                    elevation = FloatingActionButtonDefaults.elevation(
                        defaultElevation = 10.dp,
                        pressedElevation = 0.dp
                    ),
                    onClick = { context.showShortToast("You click me!") },
                ) {
                    Text(text = "Send")
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun FloatingActionButtonSamplePreview() {
    FloatingActionButtonSample(remember { MutableStateFlow(true) })
}


@Composable
private fun SmallFloatingActionButtonSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem3("SmallFloatingActionButton", allExpandFlow, padding = 20.dp) {
        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp), mainAxisSpacing = 20.dp, crossAxisSpacing = 20.dp
        ) {
            Column {
                Text(text = "Default")
                Spacer(modifier = Modifier.size(10.dp))
                SmallFloatingActionButton(onClick = { context.showShortToast("You click me!") }) {
                    Text(text = "Send")
                }
            }

            Column {
                Text(text = "shape")
                Spacer(modifier = Modifier.size(10.dp))
                SmallFloatingActionButton(
                    shape = RoundedCornerShape(50),
                    onClick = { context.showShortToast("You click me!") },
                ) {
                    Text(text = "Send")
                }
            }

            Column {
                Text(text = "containerColor")
                Spacer(modifier = Modifier.size(10.dp))
                SmallFloatingActionButton(
                    containerColor = Color.Cyan,
                    onClick = { context.showShortToast("You click me!") },
                ) {
                    Text(text = "Send")
                }
            }

            Column {
                Text(text = "contentColor")
                Spacer(modifier = Modifier.size(10.dp))
                SmallFloatingActionButton(
                    contentColor = Color.Magenta,
                    onClick = { context.showShortToast("You click me!") },
                ) {
                    Text(text = "Send")
                }
            }

            Column {
                Text(text = "elevation")
                Spacer(modifier = Modifier.size(10.dp))
                SmallFloatingActionButton(
                    elevation = FloatingActionButtonDefaults.elevation(
                        defaultElevation = 10.dp,
                        pressedElevation = 0.dp
                    ),
                    onClick = { context.showShortToast("You click me!") },
                ) {
                    Text(text = "Send")
                }
            }
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
        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp), mainAxisSpacing = 20.dp, crossAxisSpacing = 20.dp
        ) {
            Column {
                Text(text = "Default")
                Spacer(modifier = Modifier.size(10.dp))
                LargeFloatingActionButton(onClick = { context.showShortToast("You click me!") }) {
                    Text(text = "Send")
                }
            }

            Column {
                Text(text = "shape")
                Spacer(modifier = Modifier.size(10.dp))
                LargeFloatingActionButton(
                    shape = RoundedCornerShape(50),
                    onClick = { context.showShortToast("You click me!") },
                ) {
                    Text(text = "Send")
                }
            }

            Column {
                Text(text = "containerColor")
                Spacer(modifier = Modifier.size(10.dp))
                LargeFloatingActionButton(
                    containerColor = Color.Cyan,
                    onClick = { context.showShortToast("You click me!") },
                ) {
                    Text(text = "Send")
                }
            }

            Column {
                Text(text = "contentColor")
                Spacer(modifier = Modifier.size(10.dp))
                LargeFloatingActionButton(
                    contentColor = Color.Magenta,
                    onClick = { context.showShortToast("You click me!") },
                ) {
                    Text(text = "Send")
                }
            }

            Column {
                Text(text = "elevation")
                Spacer(modifier = Modifier.size(10.dp))
                LargeFloatingActionButton(
                    elevation = FloatingActionButtonDefaults.elevation(
                        defaultElevation = 10.dp,
                        pressedElevation = 0.dp
                    ),
                    onClick = { context.showShortToast("You click me!") },
                ) {
                    Text(text = "Send")
                }
            }
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
        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp), mainAxisSpacing = 20.dp, crossAxisSpacing = 20.dp
        ) {
            Column {
                Text(text = "Default")
                Spacer(modifier = Modifier.size(10.dp))
                val expanded = remember { mutableStateOf(false) }
                ExtendedFloatingActionButton(
                    onClick = { expanded.value = !expanded.value },
                    text = { Text(text = "Expand") },
                    icon = {
                        Icon(
                            imageVector = if (expanded.value) Icons.Filled.KeyboardArrowDown else Icons.Filled.KeyboardArrowUp,
                            contentDescription = ""
                        )
                    }
                )
            }

            Column {
                Text(text = "shape")
                Spacer(modifier = Modifier.size(10.dp))
                val expanded = remember { mutableStateOf(false) }
                ExtendedFloatingActionButton(
                    onClick = { expanded.value = !expanded.value },
                    shape = RoundedCornerShape(50),
                    text = { Text(text = "Expand") },
                    icon = {
                        Icon(
                            imageVector = if (expanded.value) Icons.Filled.KeyboardArrowDown else Icons.Filled.KeyboardArrowUp,
                            contentDescription = ""
                        )
                    }
                )
            }

            Column {
                Text(text = "containerColor")
                Spacer(modifier = Modifier.size(10.dp))
                val expanded = remember { mutableStateOf(false) }
                ExtendedFloatingActionButton(
                    onClick = { expanded.value = !expanded.value },
                    containerColor = Color.Cyan,
                    text = { Text(text = "Expand") },
                    icon = {
                        Icon(
                            imageVector = if (expanded.value) Icons.Filled.KeyboardArrowDown else Icons.Filled.KeyboardArrowUp,
                            contentDescription = ""
                        )
                    }
                )
            }

            Column {
                Text(text = "contentColor")
                Spacer(modifier = Modifier.size(10.dp))
                val expanded = remember { mutableStateOf(false) }
                ExtendedFloatingActionButton(
                    onClick = { expanded.value = !expanded.value },
                    contentColor = Color.Magenta,
                    text = { Text(text = "Expand") },
                    icon = {
                        Icon(
                            imageVector = if (expanded.value) Icons.Filled.KeyboardArrowDown else Icons.Filled.KeyboardArrowUp,
                            contentDescription = ""
                        )
                    }
                )
            }

            Column {
                Text(text = "elevation")
                Spacer(modifier = Modifier.size(10.dp))
                val expanded = remember { mutableStateOf(false) }
                ExtendedFloatingActionButton(
                    onClick = { expanded.value = !expanded.value },
                    elevation = FloatingActionButtonDefaults.elevation(
                        defaultElevation = 10.dp,
                        pressedElevation = 0.dp
                    ),
                    text = { Text(text = "Expand") },
                    icon = {
                        Icon(
                            imageVector = if (expanded.value) Icons.Filled.KeyboardArrowDown else Icons.Filled.KeyboardArrowUp,
                            contentDescription = ""
                        )
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun ExtendedFloatingActionButtonSamplePreview() {
    ExtendedFloatingActionButtonSample(remember { MutableStateFlow(true) })
}