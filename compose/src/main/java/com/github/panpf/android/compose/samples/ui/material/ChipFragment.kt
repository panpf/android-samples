package com.github.panpf.android.compose.samples.ui.material

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Chip
import androidx.compose.material.ChipDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FilterChip
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
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
import com.google.accompanist.flowlayout.FlowRow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class ChipFragment : ToolbarFragment() {

    override fun createView(
        toolbar: Toolbar,
        inflater: LayoutInflater,
        parent: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        toolbar.title = "Chip - Material"
        return ComposeView(inflater.context).apply {
            setContent {
                MyTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colors.background
                    ) {
                        ExpandableLayout { allExpandFlow ->
                            ChipSample(allExpandFlow)
                            ChipShapeSample(allExpandFlow)
                            ChipBorderSample(allExpandFlow)
                            ChipColorsSample(allExpandFlow)
                            ChipLeadingIconSample(allExpandFlow)
                            FilterChipSample(allExpandFlow)
                        }
                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ChipSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem(title = "Chip", allExpandFlow, padding = 20.dp) {
        FlowRow(mainAxisSpacing = 10.dp) {
            listOf("射击", "跑酷", "解谜", "推塔", "塔防", "赛车", "横版", "舞蹈").forEach {
                Chip(onClick = { context.showShortToast(it) }) {
                    Text(text = it)
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun ChipSamplePreview() {
    ChipSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ChipShapeSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem(title = "Chip（shape）", allExpandFlow, padding = 20.dp) {
        FlowRow(mainAxisSpacing = 10.dp) {
            listOf("射击", "跑酷", "解谜", "推塔", "塔防", "赛车", "横版", "舞蹈").forEach {
                Chip(
                    onClick = { context.showShortToast(it) },
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(text = it)
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun ChipShapeSamplePreview() {
    ChipShapeSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ChipBorderSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem(title = "Chip（border）", allExpandFlow, padding = 20.dp) {
        FlowRow(mainAxisSpacing = 10.dp) {
            listOf("射击", "跑酷", "解谜", "推塔", "塔防", "赛车", "横版", "舞蹈").forEach {
                Chip(
                    onClick = { context.showShortToast(it) },
                    border = BorderStroke(1.dp, Color.Red)
                ) {
                    Text(text = it)
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun ChipBorderSamplePreview() {
    ChipBorderSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ChipColorsSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem(title = "Chip（colors）", allExpandFlow, padding = 20.dp) {
        FlowRow(mainAxisSpacing = 10.dp) {
            listOf("射击", "跑酷", "解谜", "推塔", "塔防", "赛车", "横版", "舞蹈").forEach {
                Chip(
                    onClick = { context.showShortToast(it) },
                    colors = ChipDefaults.chipColors(
                        backgroundColor = Color.Red,
                        contentColor = Color.Cyan,
                    )
                ) {
                    Text(text = it, color = Color.White)
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun ChipColorsSamplePreview() {
    ChipColorsSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ChipLeadingIconSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem(title = "Chip（leadingIcon）", allExpandFlow, padding = 20.dp) {
        FlowRow(mainAxisSpacing = 10.dp) {
            listOf("射击", "跑酷", "解谜", "推塔", "塔防", "赛车", "横版", "舞蹈").forEach {
                Chip(
                    onClick = { context.showShortToast(it) },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_games),
                            contentDescription = null
                        )
                    }
                ) {
                    Text(text = it)
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun ChipLeadingIconSamplePreview() {
    ChipLeadingIconSample(remember { MutableStateFlow(true) })
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FilterChipSample(allExpandFlow: Flow<Boolean>) {
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
    ExpandableItem(title = "FilterChip", allExpandFlow, padding = 20.dp) {
        FlowRow(mainAxisSpacing = 10.dp) {
            items.forEach {
                FilterChip(
                    selected = it.second.value,
                    onClick = {
                        val newSelected = !it.second.value
                        it.second.value = newSelected
                        context.showShortToast("${it.first}：${newSelected}")
                    },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_games),
                            contentDescription = null
                        )
                    },
                    trailingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_info),
                            contentDescription = null
                        )
                    }
                ) {
                    Text(text = it.first)
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun FilterChipSamplePreview() {
    FilterChipSample(remember { MutableStateFlow(true) })
}