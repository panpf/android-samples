package com.github.panpf.android.compose.samples.ui.material

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.IconToggleButton
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.MaterialComposeAppBarFragment
import com.github.panpf.tools4a.toast.ktx.showShortToast
import com.google.accompanist.flowlayout.FlowRow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class ButtonFragment : MaterialComposeAppBarFragment() {

    override fun getTitle(): String {
        return "Button - Material"
    }

    @Composable
    override fun DrawContent() {
        ExpandableLayout { allExpandFlow ->
            ButtonSample(allExpandFlow)
            OutlinedButtonSample(allExpandFlow)
            TextButtonSample(allExpandFlow)
            IconButtonSample(allExpandFlow)
            IconToggleButtonSample(allExpandFlow)
            FloatingActionButtonSample(allExpandFlow)
            ExtendedFloatingActionButtonSample(allExpandFlow)
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
                    shape = RoundedCornerShape(50),
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
                        backgroundColor = Color.Cyan,
                        contentColor = Color.Black,
                        disabledBackgroundColor = Color.Gray,
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
                    elevation = ButtonDefaults.elevation(
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
private fun OutlinedButtonSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem("OutlinedButton", allExpandFlow, padding = 20.dp) {
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
                    shape = RoundedCornerShape(50),
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
                        backgroundColor = Color.Cyan,
                        contentColor = Color.Black,
                        disabledBackgroundColor = Color.Gray,
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
                    elevation = ButtonDefaults.elevation(
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
    ExpandableItem("TextButton", allExpandFlow, padding = 20.dp) {
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
                    shape = RoundedCornerShape(50),
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
                        backgroundColor = Color.Cyan,
                        contentColor = Color.Black,
                        disabledBackgroundColor = Color.Gray,
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
                    elevation = ButtonDefaults.elevation(
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
    ExpandableItem("IconButton", allExpandFlow, padding = 20.dp) {
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
private fun IconToggleButtonSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem("IconToggleButton", allExpandFlow, padding = 20.dp) {
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
private fun FloatingActionButtonSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem("FloatingActionButton", allExpandFlow, padding = 20.dp) {
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
                    shape = RoundedCornerShape(10.dp),
                    onClick = { context.showShortToast("You click me!") },
                ) {
                    Text(text = "Send")
                }
            }

            Column {
                Text(text = "backgroundColor")
                Spacer(modifier = Modifier.size(10.dp))
                FloatingActionButton(
                    backgroundColor = Color.Cyan,
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
private fun ExtendedFloatingActionButtonSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem("ExtendedFloatingActionButton", allExpandFlow, padding = 20.dp) {
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
                    shape = RoundedCornerShape(10.dp),
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
                Text(text = "backgroundColor")
                Spacer(modifier = Modifier.size(10.dp))
                val expanded = remember { mutableStateOf(false) }
                ExtendedFloatingActionButton(
                    onClick = { expanded.value = !expanded.value },
                    backgroundColor = Color.Cyan,
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