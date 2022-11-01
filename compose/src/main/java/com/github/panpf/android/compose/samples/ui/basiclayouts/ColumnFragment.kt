package com.github.panpf.android.compose.samples.ui.basiclayouts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Chip
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.ToolbarFragment
import com.github.panpf.android.compose.samples.ui.base.theme.MyTheme
import com.google.accompanist.flowlayout.FlowRow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class ColumnFragment : ToolbarFragment() {

    override fun createView(
        toolbar: Toolbar,
        inflater: LayoutInflater,
        parent: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        toolbar.title = "Column"
        return ComposeView(inflater.context).apply {
            setContent {
                MyTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        ExpandableLayout { allExpandFlow ->
                            ColumnSample(allExpandFlow)
                            ColumnFullSample(allExpandFlow)
                            ColumnItemSpacedSample(allExpandFlow)
                            ColumnVerticalArrangementSample(allExpandFlow)
                            ColumnHorizontalAlignmentSample(allExpandFlow)
                            ColumnWeightSample(allExpandFlow)
                            ColumnAlignSample(allExpandFlow)
                        }
                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ColumnSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem(title = "Column", allExpandFlow, padding = 20.dp) {
        Column(
            modifier = Modifier
                .height(200.dp)
                .background(Color.Red.copy(alpha = 0.5f))
        ) {
            listOf("数\n码", "汽\n车", "摄\n影").forEach {
                Chip(onClick = { }) {
                    Text(text = it)
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun ColumnSamplePreview() {
    ColumnSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ColumnFullSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem(title = "Column（Full）", allExpandFlow, padding = 20.dp) {
        Column(
            modifier = Modifier
                .height(200.dp)
                .background(Color.Red.copy(alpha = 0.5f))
        ) {
            listOf("数\n码", "汽\n车", "摄\n影", "舞\n蹈", "音\n乐", "科\n技", "健\n身", "游\n戏", "文\n学").forEach {
                Chip(onClick = { }) {
                    Text(text = it)
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun ColumnFullSamplePreview() {
    ColumnFullSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ColumnItemSpacedSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem(title = "Column（ItemSpaced）", allExpandFlow, padding = 20.dp) {
        Column(
            modifier = Modifier
                .height(200.dp)
                .background(Color.Red.copy(alpha = 0.5f)),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            listOf("数\n码", "汽\n车", "摄\n影", "舞\n蹈", "音\n乐", "科\n技", "健\n身", "游\n戏", "文\n学").forEach {
                Chip(onClick = { }) {
                    Text(text = it)
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun ColumnItemSpacedSamplePreview() {
    ColumnItemSpacedSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ColumnVerticalArrangementSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem(title = "Column（verticalArrangement）", allExpandFlow, padding = 20.dp) {
        FlowRow(mainAxisSpacing = 10.dp, crossAxisSpacing = 10.dp) {
            listOf(
                Arrangement.Top to "Top",
                Arrangement.Center to "Center",
                Arrangement.Bottom to "Bottom",
                null to "Space",
                Arrangement.SpaceBetween to "Space\nBetween",
                Arrangement.SpaceAround to "Space\nAround",
                Arrangement.SpaceEvenly to "Space\nEvenly",
            ).forEach { (arrangement, name) ->
                Column {
                    Text(
                        text = name,
                        modifier = Modifier
                            .height(46.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                    Column(
                        modifier = Modifier
                            .height(200.dp)
                            .background(Color.Red.copy(alpha = 0.5f)),
                        verticalArrangement = arrangement ?: Arrangement.spacedBy(10.dp)
                    ) {
                        listOf("数\n码", "汽\n车", "摄\n影").forEach {
                            Chip(onClick = { }) {
                                Text(text = it)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun ColumnVerticalArrangementSamplePreview() {
    ColumnVerticalArrangementSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ColumnHorizontalAlignmentSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem(title = "Column（horizontalAlignment）", allExpandFlow, padding = 20.dp) {
        FlowRow(mainAxisSpacing = 10.dp, crossAxisSpacing = 10.dp) {
            listOf(
                Alignment.Start to "Start",
                Alignment.CenterHorizontally to "Center\nHorizontally",
                Alignment.End to "End",
            ).forEach { (alignment, name) ->
                Column {
                    Text(
                        text = name,
                        modifier = Modifier
                            .height(46.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                    Column(
                        modifier = Modifier
                            .height(200.dp)
                            .width(100.dp)
                            .background(Color.Red.copy(alpha = 0.5f)),
                        horizontalAlignment = alignment
                    ) {
                        listOf("数\n码", "汽\n车", "摄\n影").forEach {
                            Chip(onClick = { }) {
                                Text(text = it)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun ColumnHorizontalAlignmentSamplePreview() {
    ColumnHorizontalAlignmentSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ColumnWeightSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem(title = "Column（weight）", allExpandFlow, padding = 20.dp) {
        FlowRow(mainAxisSpacing = 10.dp, crossAxisSpacing = 10.dp) {
            listOf(
                listOf("数\n码" to 1f, "汽\n车" to null, "摄\n影" to null),
                listOf("数\n码" to 1f, "汽\n车" to 1f, "摄\n影" to null),
                listOf("数\n码" to 1f, "汽\n车" to 1f, "摄\n影" to 1f),
            ).forEach { list ->
                Column(
                    modifier = Modifier
                        .height(200.dp)
                        .background(Color.Red.copy(alpha = 0.5f))
                ) {
                    list.forEach { (tag, weight) ->
                        Chip(
                            onClick = { },
                            modifier = Modifier.let {
                                if (weight != null) it.weight(weight) else it
                            }
                        ) {
                            Text(text = tag)
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun ColumnWeightSamplePreview() {
    ColumnWeightSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ColumnAlignSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem(title = "Column（align）", allExpandFlow, padding = 20.dp) {
        FlowRow(mainAxisSpacing = 10.dp, crossAxisSpacing = 10.dp) {
            listOf(
                Alignment.Start to "Start",
                Alignment.CenterHorizontally to "Center\nHorizontally",
                Alignment.End to "End",
            ).forEach { (alignment, name) ->
                Column {
                    Text(
                        text = name,
                        modifier = Modifier
                            .height(46.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                    Column(
                        modifier = Modifier
                            .height(200.dp)
                            .width(100.dp)
                            .background(Color.Red.copy(alpha = 0.5f))
                    ) {
                        Chip(
                            onClick = { },
                        ) {
                            Text(text = "数\n码")
                        }
                        Chip(
                            onClick = { },
                            modifier = Modifier.align(alignment),
                        ) {
                            Text(text = "舞\n蹈")
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun ColumnAlignSamplePreview() {
    ColumnAlignSample(remember { MutableStateFlow(true) })
}