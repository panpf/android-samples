package com.github.panpf.android.compose.samples.ui.material3

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.ElevatedAssistChip
import androidx.compose.material3.ElevatedFilterChip
import androidx.compose.material3.ElevatedSuggestionChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.InputChip
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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

class ChipFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "Chip - Material3"
    }

    @Composable
    override fun DrawContent() {
        ExpandableLayout { allExpandFlow ->
            AssistChipSample(allExpandFlow)
            AssistChipShapeSample(allExpandFlow)
            AssistChipColorsSample(allExpandFlow)
            AssistChipBorderSample(allExpandFlow)
            ElevatedAssistChipSample(allExpandFlow)
            FilterChipSample(allExpandFlow)
            ElevatedFilterChipSample(allExpandFlow)
            InputChipSample(allExpandFlow)
            //        InputChipTextFieldSample(allExpandFlow)
            SuggestionChipSample(allExpandFlow)
            ElevatedSuggestionChipSample(allExpandFlow)
        }
    }
}


@Composable
private fun AssistChipSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem3(title = "AssistChip", allExpandFlow, padding = 20.dp) {
        FlowRow(mainAxisSpacing = 10.dp) {
            listOf("射击", "跑酷", "解谜", "推塔", "塔防", "赛车", "横版", "舞蹈").forEach {
                AssistChip(
                    onClick = { context.showShortToast(it) },
                    label = {
                        Text(text = it)
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = null
                        )
                    },
                    trailingIcon = {
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
private fun AssistChipSamplePreview() {
    AssistChipSample(remember { MutableStateFlow(true) })
}


@Composable
private fun AssistChipShapeSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem3(title = "AssistChip（shape）", allExpandFlow, padding = 20.dp) {
        FlowRow(mainAxisSpacing = 10.dp) {
            listOf("射击", "跑酷", "解谜", "推塔", "塔防", "赛车", "横版", "舞蹈").forEach {
                AssistChip(
                    onClick = { context.showShortToast(it) },
                    label = {
                        Text(text = it)
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = null
                        )
                    },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Info,
                            contentDescription = null
                        )
                    },
                    shape = RoundedCornerShape(50)
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun AssistChipShapeSamplePreview() {
    AssistChipShapeSample(remember { MutableStateFlow(true) })
}


@Composable
private fun AssistChipColorsSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem3(title = "AssistChip（colors）", allExpandFlow, padding = 20.dp) {
        FlowRow(mainAxisSpacing = 10.dp) {
            listOf("射击", "跑酷", "解谜", "推塔", "塔防", "赛车", "横版", "舞蹈").forEach {
                AssistChip(
                    onClick = { context.showShortToast(it) },
                    label = {
                        Text(text = it)
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = null,
                        )
                    },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Info,
                            contentDescription = null,
                        )
                    },
                    colors = AssistChipDefaults.assistChipColors(
                        labelColor = Color.Red,
                        containerColor = Color.Cyan,
                        leadingIconContentColor = Color.Blue,
                        trailingIconContentColor = Color.Yellow,
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun AssistChipColorsSamplePreview() {
    AssistChipColorsSample(remember { MutableStateFlow(true) })
}


@Composable
private fun AssistChipBorderSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem3(title = "AssistChip（border）", allExpandFlow, padding = 20.dp) {
        FlowRow(mainAxisSpacing = 10.dp) {
            listOf("射击", "跑酷", "解谜", "推塔", "塔防", "赛车", "横版", "舞蹈").forEach {
                AssistChip(
                    onClick = { context.showShortToast(it) },
                    label = {
                        Text(text = it)
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = null
                        )
                    },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Info,
                            contentDescription = null
                        )
                    },
                    border = AssistChipDefaults.assistChipBorder(borderColor = Color.Red)
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun AssistChipBorderSamplePreview() {
    AssistChipBorderSample(remember { MutableStateFlow(true) })
}


@Composable
private fun ElevatedAssistChipSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem3(title = "ElevatedAssistChip", allExpandFlow, padding = 20.dp) {
        FlowRow(mainAxisSpacing = 10.dp) {
            listOf("射击", "跑酷", "解谜", "推塔", "塔防", "赛车", "横版", "舞蹈").forEach {
                ElevatedAssistChip(
                    onClick = { context.showShortToast(it) },
                    label = {
                        Text(text = it)
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = null
                        )
                    },
                    trailingIcon = {
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
private fun ElevatedAssistChipSamplePreview() {
    ElevatedAssistChipSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FilterChipSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    val items = remember {
        listOf(
            "射击" to mutableStateOf(false),
            "跑酷" to mutableStateOf(false),
            "解谜" to mutableStateOf(false),
            "推塔" to mutableStateOf(false),
            "塔防" to mutableStateOf(false),
            "赛车" to mutableStateOf(false),
            "横版" to mutableStateOf(false),
            "舞蹈" to mutableStateOf(false)
        )
    }
    ExpandableItem3(title = "FilterChip", allExpandFlow, padding = 20.dp) {
        FlowRow(mainAxisSpacing = 10.dp) {
            items.forEach {
                FilterChip(
                    selected = it.second.value,
                    onClick = {
                        val newSelected = !it.second.value
                        it.second.value = newSelected
                        context.showShortToast("${it.first}：${newSelected}")
                    },
                    label = {
                        Text(text = it.first)
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = null
                        )
                    },
                    trailingIcon = {
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
private fun FilterChipSamplePreview() {
    FilterChipSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ElevatedFilterChipSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    val items = remember {
        listOf(
            "射击" to mutableStateOf(false),
            "跑酷" to mutableStateOf(false),
            "解谜" to mutableStateOf(false),
            "推塔" to mutableStateOf(false),
            "塔防" to mutableStateOf(false),
            "赛车" to mutableStateOf(false),
            "横版" to mutableStateOf(false),
            "舞蹈" to mutableStateOf(false)
        )
    }
    ExpandableItem3(title = "ElevatedFilterChip", allExpandFlow, padding = 20.dp) {
        FlowRow(mainAxisSpacing = 10.dp) {
            items.forEach {
                ElevatedFilterChip(
                    selected = it.second.value,
                    onClick = {
                        val newSelected = !it.second.value
                        it.second.value = newSelected
                        context.showShortToast("${it.first}：${newSelected}")
                    },
                    label = {
                        Text(text = it.first)
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = null
                        )
                    },
                    trailingIcon = {
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
private fun ElevatedFilterChipSamplePreview() {
    ElevatedFilterChipSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun InputChipSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    val items = remember {
        listOf(
            "射击" to mutableStateOf(false),
            "跑酷" to mutableStateOf(false),
            "解谜" to mutableStateOf(false),
            "推塔" to mutableStateOf(false),
            "塔防" to mutableStateOf(false),
            "赛车" to mutableStateOf(false),
            "横版" to mutableStateOf(false),
            "舞蹈" to mutableStateOf(false)
        )
    }
    ExpandableItem3(title = "InputChip", allExpandFlow, padding = 20.dp) {
        FlowRow(mainAxisSpacing = 10.dp) {
            items.forEach {
                InputChip(
                    selected = it.second.value,
                    onClick = {
                        val newSelected = !it.second.value
                        it.second.value = newSelected
                        context.showShortToast("${it.first}：${newSelected}")
                    },
                    label = {
                        Text(text = it.first)
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = null
                        )
                    },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Clear,
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
private fun InputChipSamplePreview() {
    InputChipSample(remember { MutableStateFlow(true) })
}


@Composable
private fun SuggestionChipSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem3(title = "SuggestionChip", allExpandFlow, padding = 20.dp) {
        FlowRow(mainAxisSpacing = 10.dp) {
            listOf("射击", "跑酷", "解谜", "推塔", "塔防", "赛车", "横版", "舞蹈").forEach {
                SuggestionChip(
                    onClick = { context.showShortToast(it) },
                    label = {
                        Text(text = it)
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = null
                        )
                    },
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
        FlowRow(mainAxisSpacing = 10.dp) {
            listOf("射击", "跑酷", "解谜", "推塔", "塔防", "赛车", "横版", "舞蹈").forEach {
                ElevatedSuggestionChip(
                    onClick = { context.showShortToast(it) },
                    label = {
                        Text(text = it)
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = null,
                        )
                    },
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