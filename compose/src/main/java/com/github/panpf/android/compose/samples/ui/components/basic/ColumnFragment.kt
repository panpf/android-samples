package com.github.panpf.android.compose.samples.ui.components.basic

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Chip
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem3
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
import com.google.accompanist.flowlayout.FlowRow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class ColumnFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "Column"
    }

    @Composable
    override fun DrawContent() {
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


@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun ColumnSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "Column", allExpandFlow, padding = 20.dp) {
        Column(
            modifier = Modifier
                .height(200.dp)
                .border(2.dp, Color.Red)
                .padding(2.dp)
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
private fun ColumnSamplePreview() {
    ColumnSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun ColumnFullSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "Column（Full）", allExpandFlow, padding = 20.dp) {
        Column(
            modifier = Modifier
                .height(200.dp)
                .border(2.dp, Color.Red)
                .padding(2.dp)
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
private fun ColumnFullSamplePreview() {
    ColumnFullSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun ColumnItemSpacedSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "Column（ItemSpaced）", allExpandFlow, padding = 20.dp) {
        Column(
            modifier = Modifier
                .height(200.dp)
                .border(2.dp, Color.Red)
                .padding(2.dp),
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
private fun ColumnItemSpacedSamplePreview() {
    ColumnItemSpacedSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun ColumnVerticalArrangementSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "Column（verticalArrangement）", allExpandFlow, padding = 20.dp) {
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
                            .border(2.dp, Color.Red)
                            .padding(2.dp),
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
private fun ColumnVerticalArrangementSamplePreview() {
    ColumnVerticalArrangementSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun ColumnHorizontalAlignmentSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "Column（horizontalAlignment）", allExpandFlow, padding = 20.dp) {
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
                            .border(2.dp, Color.Red)
                            .padding(2.dp),
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
private fun ColumnHorizontalAlignmentSamplePreview() {
    ColumnHorizontalAlignmentSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun ColumnWeightSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "Column（weight）", allExpandFlow, padding = 20.dp) {
        FlowRow(mainAxisSpacing = 10.dp, crossAxisSpacing = 10.dp) {
            listOf(
                listOf("数\n码" to 1f, "汽\n车" to null, "摄\n影" to null),
                listOf("数\n码" to 1f, "汽\n车" to 1f, "摄\n影" to null),
                listOf("数\n码" to 1f, "汽\n车" to 1f, "摄\n影" to 1f),
            ).forEach { list ->
                Column(
                    modifier = Modifier
                        .height(200.dp)
                        .border(2.dp, Color.Red)
                        .padding(2.dp)
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
private fun ColumnWeightSamplePreview() {
    ColumnWeightSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun ColumnAlignSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "Column（align）", allExpandFlow, padding = 20.dp) {
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
                            .border(2.dp, Color.Red)
                            .padding(2.dp)
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
private fun ColumnAlignSamplePreview() {
    ColumnAlignSample(remember { MutableStateFlow(true) })
}