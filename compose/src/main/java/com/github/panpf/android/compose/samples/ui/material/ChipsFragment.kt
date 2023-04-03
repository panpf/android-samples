package com.github.panpf.android.compose.samples.ui.material

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Chip
import androidx.compose.material.ChipDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FilterChip
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Info
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

class ChipsFragment : MaterialComposeAppBarFragment() {

    override fun getTitle(): String {
        return "Chips - Material"
    }

    @Composable
    override fun DrawContent() {
        ExpandableLayout { allExpandFlow ->
            ChipSample(allExpandFlow)
            FilterChipSample(allExpandFlow)
        }
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun ChipSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem(title = "Chip", allExpandFlow, padding = 20.dp) {
        val tag = "射击"
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            mainAxisSpacing = 20.dp,
            crossAxisSpacing = 20.dp,
        ) {
            Column {
                Text(text = "Default")
                Spacer(modifier = Modifier.size(10.dp))
                Chip(onClick = { context.showShortToast(tag) }) {
                    Text(text = tag)
                }
            }

            Column {
                Text(text = "shape")
                Spacer(modifier = Modifier.size(10.dp))
                Chip(
                    onClick = { context.showShortToast(tag) },
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(text = tag)
                }
            }

            Column {
                Text(text = "border")
                Spacer(modifier = Modifier.size(10.dp))
                Chip(
                    onClick = { context.showShortToast(tag) },
                    border = BorderStroke(1.dp, Color.Red)
                ) {
                    Text(text = tag)
                }
            }

            Column {
                Text(text = "colors")
                Spacer(modifier = Modifier.size(10.dp))
                Chip(
                    onClick = { context.showShortToast(tag) },
                    colors = ChipDefaults.chipColors(
                        backgroundColor = Color.Red,
                        contentColor = Color.Cyan,
                    )
                ) {
                    Text(text = tag, color = Color.White)
                }
            }

            Column {
                Text(text = "leadingIcon")
                Spacer(modifier = Modifier.size(10.dp))
                Chip(
                    onClick = { context.showShortToast(tag) },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = null
                        )
                    }
                ) {
                    Text(text = tag)
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun ChipSamplePreview() {
    ChipSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun FilterChipSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem(title = "FilterChip", allExpandFlow, padding = 20.dp) {
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
                ) {
                    Text(text = tag)
                }
            }

            Column {
                Text(text = "shape")
                Spacer(modifier = Modifier.size(10.dp))
                val selectedState = remember { mutableStateOf(false) }
                FilterChip(
                    selected = selectedState.value,
                    onClick = { selectedState.value = !selectedState.value },
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(text = tag)
                }
            }

            Column {
                Text(text = "border")
                Spacer(modifier = Modifier.size(10.dp))
                val selectedState = remember { mutableStateOf(false) }
                FilterChip(
                    selected = selectedState.value,
                    onClick = { selectedState.value = !selectedState.value },
                    border = BorderStroke(1.dp, Color.Red)
                ) {
                    Text(text = tag)
                }
            }

            Column {
                Text(text = "colors")
                Spacer(modifier = Modifier.size(10.dp))
                val selectedState = remember { mutableStateOf(false) }
                FilterChip(
                    selected = selectedState.value,
                    onClick = { selectedState.value = !selectedState.value },
                    colors = ChipDefaults.filterChipColors(
                        backgroundColor = Color.Red,
                        contentColor = Color.Cyan,
                    )
                ) {
                    Text(text = tag, color = Color.White)
                }
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
                    }
                ) {
                    Text(text = tag)
                }
            }

            Column {
                Text(text = "selectedIcon")
                Spacer(modifier = Modifier.size(10.dp))
                val selectedState = remember { mutableStateOf(false) }
                FilterChip(
                    selected = selectedState.value,
                    onClick = { selectedState.value = !selectedState.value },
                    selectedIcon = {
                        Icon(
                            imageVector = Icons.Filled.Done,
                            contentDescription = null
                        )
                    }
                ) {
                    Text(text = tag)
                }
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
                    }
                ) {
                    Text(text = tag)
                }
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
                    selectedIcon = {
                        Icon(
                            imageVector = Icons.Filled.Done,
                            contentDescription = null
                        )
                    },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = null
                        )
                    }
                ) {
                    Text(text = tag)
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun FilterChipSamplePreview() {
    FilterChipSample(remember { MutableStateFlow(true) })
}