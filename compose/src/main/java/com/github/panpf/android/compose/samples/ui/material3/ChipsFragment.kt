package com.github.panpf.android.compose.samples.ui.material3

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.ElevatedAssistChip
import androidx.compose.material3.ElevatedFilterChip
import androidx.compose.material3.ElevatedSuggestionChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.InputChip
import androidx.compose.material3.InputChipDefaults
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem3
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
import com.github.panpf.tools4a.toast.ktx.showShortToast
import com.google.accompanist.flowlayout.FlowRow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class ChipsFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "Chips - Material3"
    }

    @Composable
    override fun DrawContent() {
        ExpandableLayout { allExpandFlow ->
            AssistChipSample(allExpandFlow)
            ElevatedAssistChipSample(allExpandFlow)
            FilterChipSample(allExpandFlow)
            ElevatedFilterChipSample(allExpandFlow)
            InputChipSample(allExpandFlow)
            SuggestionChipSample(allExpandFlow)
            ElevatedSuggestionChipSample(allExpandFlow)
        }
    }
}


@Composable
private fun AssistChipSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem3(title = "AssistChip", allExpandFlow, padding = 20.dp) {
        val tag = "射击"
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            mainAxisSpacing = 20.dp,
            crossAxisSpacing = 20.dp,
        ) {
            Column {
                Text(text = "Default")
                Spacer(modifier = Modifier.size(10.dp))
                AssistChip(
                    onClick = { context.showShortToast(tag) },
                    label = { Text(text = tag) }
                )
            }

            Column {
                Text(text = "shape")
                Spacer(modifier = Modifier.size(10.dp))
                AssistChip(
                    onClick = { context.showShortToast(tag) },
                    label = { Text(text = tag) },
                    shape = RoundedCornerShape(50)
                )
            }

            Column {
                Text(text = "border")
                Spacer(modifier = Modifier.size(10.dp))
                AssistChip(
                    onClick = { context.showShortToast(tag) },
                    label = { Text(text = tag) },
                    border = AssistChipDefaults.assistChipBorder(borderColor = Color.Red)
                )
            }

            Column {
                Text(text = "colors")
                Spacer(modifier = Modifier.size(10.dp))
                AssistChip(
                    onClick = { context.showShortToast(tag) },
                    label = { Text(text = tag) },
                    colors = AssistChipDefaults.assistChipColors(
                        labelColor = Color.Red,
                        containerColor = Color.Cyan,
                        leadingIconContentColor = Color.Blue,
                        trailingIconContentColor = Color.Yellow,
                    )
                )
            }

            Column {
                Text(text = "elevation")
                Spacer(modifier = Modifier.size(10.dp))
                AssistChip(
                    onClick = { context.showShortToast(tag) },
                    label = { Text(text = tag) },
                    elevation = AssistChipDefaults.assistChipElevation(10.dp)
                )
            }

            Column {
                Text(text = "leadingIcon")
                Spacer(modifier = Modifier.size(10.dp))
                AssistChip(
                    onClick = { context.showShortToast(tag) },
                    label = { Text(text = tag) },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Info,
                            contentDescription = null
                        )
                    }
                )
            }

            Column {
                Text(text = "trailingIcon")
                Spacer(modifier = Modifier.size(10.dp))
                AssistChip(
                    onClick = { context.showShortToast(tag) },
                    label = { Text(text = tag) },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = null
                        )
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun AssistChipSamplePreview() {
    AssistChipSample(remember { MutableStateFlow(true) })
}


@Composable
private fun ElevatedAssistChipSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem3(title = "ElevatedAssistChip", allExpandFlow, padding = 20.dp) {
        val tag = "射击"
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            mainAxisSpacing = 20.dp,
            crossAxisSpacing = 20.dp,
        ) {
            Column {
                Text(text = "Default")
                Spacer(modifier = Modifier.size(10.dp))
                ElevatedAssistChip(
                    onClick = { context.showShortToast(tag) },
                    label = { Text(text = tag) }
                )
            }

            Column {
                Text(text = "shape")
                Spacer(modifier = Modifier.size(10.dp))
                ElevatedAssistChip(
                    onClick = { context.showShortToast(tag) },
                    label = { Text(text = tag) },
                    shape = RoundedCornerShape(50)
                )
            }

            Column {
                Text(text = "border")
                Spacer(modifier = Modifier.size(10.dp))
                ElevatedAssistChip(
                    onClick = { context.showShortToast(tag) },
                    label = { Text(text = tag) },
                    border = AssistChipDefaults.assistChipBorder(borderColor = Color.Red)
                )
            }

            Column {
                Text(text = "colors")
                Spacer(modifier = Modifier.size(10.dp))
                ElevatedAssistChip(
                    onClick = { context.showShortToast(tag) },
                    label = { Text(text = tag) },
                    colors = AssistChipDefaults.assistChipColors(
                        labelColor = Color.Red,
                        containerColor = Color.Cyan,
                        leadingIconContentColor = Color.Blue,
                        trailingIconContentColor = Color.Yellow,
                    )
                )
            }

            Column {
                Text(text = "elevation")
                Spacer(modifier = Modifier.size(10.dp))
                ElevatedAssistChip(
                    onClick = { context.showShortToast(tag) },
                    label = { Text(text = tag) },
                    elevation = AssistChipDefaults.elevatedAssistChipElevation(10.dp)
                )
            }

            Column {
                Text(text = "leadingIcon")
                Spacer(modifier = Modifier.size(10.dp))
                ElevatedAssistChip(
                    onClick = { context.showShortToast(tag) },
                    label = { Text(text = tag) },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Info,
                            contentDescription = null
                        )
                    }
                )
            }

            Column {
                Text(text = "trailingIcon")
                Spacer(modifier = Modifier.size(10.dp))
                ElevatedAssistChip(
                    onClick = { context.showShortToast(tag) },
                    label = { Text(text = tag) },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = null
                        )
                    }
                )
            }

            Column {
                Text(text = "icons")
                Spacer(modifier = Modifier.size(10.dp))
                ElevatedAssistChip(
                    onClick = { context.showShortToast(tag) },
                    label = { Text(text = tag) },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Info,
                            contentDescription = null
                        )
                    },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = null
                        )
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun ElevatedAssistChipSamplePreview() {
    ElevatedAssistChipSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FilterChipSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "FilterChip", allExpandFlow, padding = 20.dp) {
        val tag = "射击"
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            mainAxisSpacing = 20.dp,
            crossAxisSpacing = 20.dp,
        ) {
            Column {
                Text(text = "Default")
                Spacer(modifier = Modifier.size(10.dp))
                val selectedState = remember { mutableStateOf(false) }
                FilterChip(
                    selected = selectedState.value,
                    onClick = { selectedState.value = !selectedState.value },
                    label = { Text(text = tag) },
                )
            }

            Column {
                Text(text = "shape")
                Spacer(modifier = Modifier.size(10.dp))
                val selectedState = remember { mutableStateOf(false) }
                FilterChip(
                    selected = selectedState.value,
                    onClick = { selectedState.value = !selectedState.value },
                    shape = RoundedCornerShape(50),
                    label = { Text(text = tag) },
                )
            }

            Column {
                Text(text = "border")
                Spacer(modifier = Modifier.size(10.dp))
                val selectedState = remember { mutableStateOf(false) }
                FilterChip(
                    selected = selectedState.value,
                    onClick = { selectedState.value = !selectedState.value },
                    border = FilterChipDefaults.filterChipBorder(borderWidth = 1.dp, borderColor = Color.Red),
                    label = { Text(text = tag) },
                )
            }

            Column {
                Text(text = "colors")
                Spacer(modifier = Modifier.size(10.dp))
                val selectedState = remember { mutableStateOf(false) }
                FilterChip(
                    selected = selectedState.value,
                    onClick = { selectedState.value = !selectedState.value },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Info,
                            contentDescription = null
                        )
                    },
                    colors = FilterChipDefaults.filterChipColors(
                        containerColor = Color.Red,
                        labelColor = Color.Cyan,
                        iconColor = Color.Cyan,
                    ),
                    label = { Text(text = tag) },
                )
            }

            Column {
                Text(text = "elevation")
                Spacer(modifier = Modifier.size(10.dp))
                val selectedState = remember { mutableStateOf(false) }
                FilterChip(
                    selected = selectedState.value,
                    onClick = { selectedState.value = !selectedState.value },
                    elevation = FilterChipDefaults.filterChipElevation(10.dp),
                    label = { Text(text = tag) },
                )
            }

            Column {
                Text(text = "leadingIcon")
                Spacer(modifier = Modifier.size(10.dp))
                val selectedState = remember { mutableStateOf(false) }
                FilterChip(
                    selected = selectedState.value,
                    onClick = { selectedState.value = !selectedState.value },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Info,
                            contentDescription = null
                        )
                    },
                    label = { Text(text = tag) },
                )
            }

            Column {
                Text(text = "trailingIcon")
                Spacer(modifier = Modifier.size(10.dp))
                val selectedState = remember { mutableStateOf(false) }
                FilterChip(
                    selected = selectedState.value,
                    onClick = { selectedState.value = !selectedState.value },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = null
                        )
                    },
                    label = { Text(text = tag) },
                )
            }

            Column {
                Text(text = "icons")
                Spacer(modifier = Modifier.size(10.dp))
                val selectedState = remember { mutableStateOf(false) }
                FilterChip(
                    selected = selectedState.value,
                    onClick = { selectedState.value = !selectedState.value },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Info,
                            contentDescription = null
                        )
                    },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = null
                        )
                    },
                    label = { Text(text = tag) },
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun FilterChipSamplePreview() {
    FilterChipSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ElevatedFilterChipSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "ElevatedFilterChip", allExpandFlow, padding = 20.dp) {
        val tag = "射击"
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            mainAxisSpacing = 20.dp,
            crossAxisSpacing = 20.dp,
        ) {
            Column {
                Text(text = "Default")
                Spacer(modifier = Modifier.size(10.dp))
                val selectedState = remember { mutableStateOf(false) }
                ElevatedFilterChip(
                    selected = selectedState.value,
                    onClick = { selectedState.value = !selectedState.value },
                    label = { Text(text = tag) },
                )
            }

            Column {
                Text(text = "shape")
                Spacer(modifier = Modifier.size(10.dp))
                val selectedState = remember { mutableStateOf(false) }
                ElevatedFilterChip(
                    selected = selectedState.value,
                    onClick = { selectedState.value = !selectedState.value },
                    shape = RoundedCornerShape(50),
                    label = { Text(text = tag) },
                )
            }

            Column {
                Text(text = "border")
                Spacer(modifier = Modifier.size(10.dp))
                val selectedState = remember { mutableStateOf(false) }
                ElevatedFilterChip(
                    selected = selectedState.value,
                    onClick = { selectedState.value = !selectedState.value },
                    border = FilterChipDefaults.filterChipBorder(borderWidth = 1.dp, borderColor = Color.Red),
                    label = { Text(text = tag) },
                )
            }

            Column {
                Text(text = "colors")
                Spacer(modifier = Modifier.size(10.dp))
                val selectedState = remember { mutableStateOf(false) }
                ElevatedFilterChip(
                    selected = selectedState.value,
                    onClick = { selectedState.value = !selectedState.value },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Info,
                            contentDescription = null
                        )
                    },
                    colors = FilterChipDefaults.filterChipColors(
                        containerColor = Color.Red,
                        labelColor = Color.Cyan,
                        iconColor = Color.Cyan,
                    ),
                    label = { Text(text = tag) },
                )
            }

            Column {
                Text(text = "elevation")
                Spacer(modifier = Modifier.size(10.dp))
                val selectedState = remember { mutableStateOf(false) }
                ElevatedFilterChip(
                    selected = selectedState.value,
                    onClick = { selectedState.value = !selectedState.value },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Info,
                            contentDescription = null
                        )
                    },
                    elevation = FilterChipDefaults.elevatedFilterChipElevation(10.dp),
                    label = { Text(text = tag) },
                )
            }

            Column {
                Text(text = "leadingIcon")
                Spacer(modifier = Modifier.size(10.dp))
                val selectedState = remember { mutableStateOf(false) }
                ElevatedFilterChip(
                    selected = selectedState.value,
                    onClick = { selectedState.value = !selectedState.value },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Info,
                            contentDescription = null
                        )
                    },
                    label = { Text(text = tag) },
                )
            }

            Column {
                Text(text = "trailingIcon")
                Spacer(modifier = Modifier.size(10.dp))
                val selectedState = remember { mutableStateOf(false) }
                ElevatedFilterChip(
                    selected = selectedState.value,
                    onClick = { selectedState.value = !selectedState.value },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = null
                        )
                    },
                    label = { Text(text = tag) },
                )
            }

            Column {
                Text(text = "icons")
                Spacer(modifier = Modifier.size(10.dp))
                val selectedState = remember { mutableStateOf(false) }
                ElevatedFilterChip(
                    selected = selectedState.value,
                    onClick = { selectedState.value = !selectedState.value },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Info,
                            contentDescription = null
                        )
                    },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = null
                        )
                    },
                    label = { Text(text = tag) },
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun ElevatedFilterChipSamplePreview() {
    ElevatedFilterChipSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun InputChipSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "InputChip", allExpandFlow, padding = 20.dp) {
        val tag = "射击"
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            mainAxisSpacing = 20.dp,
            crossAxisSpacing = 20.dp,
        ) {
            Column {
                Text(text = "Default")
                Spacer(modifier = Modifier.size(10.dp))
                val selectedState = remember { mutableStateOf(false) }
                InputChip(
                    selected = selectedState.value,
                    onClick = { selectedState.value = !selectedState.value },
                    label = { Text(text = tag) },
                )
            }

            Column {
                Text(text = "shape")
                Spacer(modifier = Modifier.size(10.dp))
                val selectedState = remember { mutableStateOf(false) }
                InputChip(
                    selected = selectedState.value,
                    onClick = { selectedState.value = !selectedState.value },
                    shape = RoundedCornerShape(50),
                    label = { Text(text = tag) },
                )
            }

            Column {
                Text(text = "border")
                Spacer(modifier = Modifier.size(10.dp))
                val selectedState = remember { mutableStateOf(false) }
                InputChip(
                    selected = selectedState.value,
                    onClick = { selectedState.value = !selectedState.value },
                    border = FilterChipDefaults.filterChipBorder(borderWidth = 1.dp, borderColor = Color.Red),
                    label = { Text(text = tag) },
                )
            }

            Column {
                Text(text = "colors")
                Spacer(modifier = Modifier.size(10.dp))
                val selectedState = remember { mutableStateOf(false) }
                InputChip(
                    selected = selectedState.value,
                    onClick = { selectedState.value = !selectedState.value },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Info,
                            contentDescription = null
                        )
                    },
                    colors = FilterChipDefaults.filterChipColors(
                        containerColor = Color.Red,
                        labelColor = Color.Cyan,
                        iconColor = Color.Cyan,
                    ),
                    label = { Text(text = tag) },
                )
            }

            Column {
                Text(text = "elevation")
                Spacer(modifier = Modifier.size(10.dp))
                val selectedState = remember { mutableStateOf(false) }
                InputChip(
                    selected = selectedState.value,
                    onClick = { selectedState.value = !selectedState.value },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Info,
                            contentDescription = null
                        )
                    },
                    elevation = InputChipDefaults.inputChipElevation(10.dp),
                    label = { Text(text = tag) },
                )
            }

            Column {
                Text(text = "leadingIcon")
                Spacer(modifier = Modifier.size(10.dp))
                val selectedState = remember { mutableStateOf(false) }
                InputChip(
                    selected = selectedState.value,
                    onClick = { selectedState.value = !selectedState.value },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Info,
                            contentDescription = null
                        )
                    },
                    label = { Text(text = tag) },
                )
            }

            Column {
                Text(text = "avatar")
                Spacer(modifier = Modifier.size(10.dp))
                val selectedState = remember { mutableStateOf(false) }
                InputChip(
                    selected = selectedState.value,
                    onClick = { selectedState.value = !selectedState.value },
                    avatar = {
                        Icon(
                            imageVector = Icons.Filled.Edit,
                            contentDescription = null
                        )
                    },
                    label = { Text(text = tag) },
                )
            }

            Column {
                Text(text = "trailingIcon")
                Spacer(modifier = Modifier.size(10.dp))
                val selectedState = remember { mutableStateOf(false) }
                InputChip(
                    selected = selectedState.value,
                    onClick = { selectedState.value = !selectedState.value },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = null
                        )
                    },
                    label = { Text(text = tag) },
                )
            }

            Column {
                Text(text = "icons")
                Spacer(modifier = Modifier.size(10.dp))
                val selectedState = remember { mutableStateOf(false) }
                InputChip(
                    selected = selectedState.value,
                    onClick = { selectedState.value = !selectedState.value },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Info,
                            contentDescription = null
                        )
                    },
                    avatar = {
                        Icon(
                            imageVector = Icons.Filled.Edit,
                            contentDescription = null
                        )
                    },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = null
                        )
                    },
                    label = { Text(text = tag) },
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun InputChipSamplePreview() {
    InputChipSample(remember { MutableStateFlow(true) })
}


@Composable
private fun SuggestionChipSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem3(title = "SuggestionChip", allExpandFlow, padding = 20.dp) {
        val tag = "射击"
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            mainAxisSpacing = 20.dp,
            crossAxisSpacing = 20.dp,
        ) {
            Column {
                Text(text = "Default")
                Spacer(modifier = Modifier.size(10.dp))
                SuggestionChip(
                    onClick = { context.showShortToast(tag) },
                    label = { Text(text = tag) }
                )
            }

            Column {
                Text(text = "shape")
                Spacer(modifier = Modifier.size(10.dp))
                SuggestionChip(
                    onClick = { context.showShortToast(tag) },
                    label = { Text(text = tag) },
                    shape = RoundedCornerShape(50)
                )
            }

            Column {
                Text(text = "border")
                Spacer(modifier = Modifier.size(10.dp))
                SuggestionChip(
                    onClick = { context.showShortToast(tag) },
                    label = { Text(text = tag) },
                    border = AssistChipDefaults.assistChipBorder(borderColor = Color.Red)
                )
            }

            Column {
                Text(text = "colors")
                Spacer(modifier = Modifier.size(10.dp))
                SuggestionChip(
                    onClick = { context.showShortToast(tag) },
                    label = { Text(text = tag) },
                    colors = AssistChipDefaults.assistChipColors(
                        labelColor = Color.Red,
                        containerColor = Color.Cyan,
                        leadingIconContentColor = Color.Blue,
                        trailingIconContentColor = Color.Yellow,
                    )
                )
            }

            Column {
                Text(text = "elevation")
                Spacer(modifier = Modifier.size(10.dp))
                SuggestionChip(
                    onClick = { context.showShortToast(tag) },
                    label = { Text(text = tag) },
                    elevation = SuggestionChipDefaults.suggestionChipElevation(10.dp)
                )
            }

            Column {
                Text(text = "icon")
                Spacer(modifier = Modifier.size(10.dp))
                SuggestionChip(
                    onClick = { context.showShortToast(tag) },
                    label = { Text(text = tag) },
                    icon = {
                        Icon(
                            imageVector = Icons.Filled.Info,
                            contentDescription = null
                        )
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun SuggestionChipSamplePreview() {
    SuggestionChipSample(remember { MutableStateFlow(true) })
}


@Composable
private fun ElevatedSuggestionChipSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem3(title = "ElevatedSuggestionChip", allExpandFlow, padding = 20.dp) {
        val tag = "射击"
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            mainAxisSpacing = 20.dp,
            crossAxisSpacing = 20.dp,
        ) {
            Column {
                Text(text = "Default")
                Spacer(modifier = Modifier.size(10.dp))
                ElevatedSuggestionChip(
                    onClick = { context.showShortToast(tag) },
                    label = { Text(text = tag) }
                )
            }

            Column {
                Text(text = "shape")
                Spacer(modifier = Modifier.size(10.dp))
                ElevatedSuggestionChip(
                    onClick = { context.showShortToast(tag) },
                    label = { Text(text = tag) },
                    shape = RoundedCornerShape(50)
                )
            }

            Column {
                Text(text = "border")
                Spacer(modifier = Modifier.size(10.dp))
                ElevatedSuggestionChip(
                    onClick = { context.showShortToast(tag) },
                    label = { Text(text = tag) },
                    border = AssistChipDefaults.assistChipBorder(borderColor = Color.Red)
                )
            }

            Column {
                Text(text = "colors")
                Spacer(modifier = Modifier.size(10.dp))
                ElevatedSuggestionChip(
                    onClick = { context.showShortToast(tag) },
                    label = { Text(text = tag) },
                    colors = AssistChipDefaults.assistChipColors(
                        labelColor = Color.Red,
                        containerColor = Color.Cyan,
                        leadingIconContentColor = Color.Blue,
                        trailingIconContentColor = Color.Yellow,
                    )
                )
            }

            Column {
                Text(text = "elevation")
                Spacer(modifier = Modifier.size(10.dp))
                ElevatedSuggestionChip(
                    onClick = { context.showShortToast(tag) },
                    label = { Text(text = tag) },
                    elevation = SuggestionChipDefaults.elevatedSuggestionChipElevation(10.dp)
                )
            }

            Column {
                Text(text = "icon")
                Spacer(modifier = Modifier.size(10.dp))
                ElevatedSuggestionChip(
                    onClick = { context.showShortToast(tag) },
                    label = { Text(text = tag) },
                    icon = {
                        Icon(
                            imageVector = Icons.Filled.Info,
                            contentDescription = null
                        )
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun ElevatedSuggestionChipSamplePreview() {
    ElevatedSuggestionChipSample(remember { MutableStateFlow(true) })
}