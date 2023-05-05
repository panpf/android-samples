package com.github.panpf.android.compose.samples.ui.layout

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem3
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.HorizontalTag
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
import com.github.panpf.android.compose.samples.ui.customization.grid.VerticalGrid
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
            ColumnVerticalArrangementSample(allExpandFlow)
            ColumnVerticalSpacedSample(allExpandFlow)
            ColumnHorizontalAlignmentSample(allExpandFlow)
            ColumnWeightSample(allExpandFlow)
            ColumnAlignSample(allExpandFlow)
        }
    }
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ColumnSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "Column", allExpandFlow, padding = 20.dp) {
        VerticalGrid(
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Few items")
                Column(
                    modifier = Modifier
                        .height(200.dp)
                        .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                        .padding(2.dp)
                ) {
                    listOf("数码", "汽车", "摄影").forEach {
                        HorizontalTag(text = it)
                    }
                }
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Lots items")
                Column(
                    modifier = Modifier
                        .height(200.dp)
                        .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                        .padding(2.dp)
                ) {
                    listOf(
                        "数码",
                        "汽车",
                        "摄影",
                        "舞蹈",
                        "音乐",
                        "科技",
                        "健身",
                        "游戏",
                        "文学"
                    ).forEach {
                        HorizontalTag(text = it)
                    }
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


@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ColumnVerticalArrangementSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "Column（verticalArrangement）", allExpandFlow, padding = 20.dp) {
        VerticalGrid(
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            listOf(
                Arrangement.Top to "Top",
                Arrangement.Center to "Center",
                Arrangement.Bottom to "Bottom",
                Arrangement.SpaceBetween to "SpaceBetween",
                Arrangement.SpaceAround to "SpaceAround",
                Arrangement.SpaceEvenly to "SpaceEvenly",
            ).forEach { (arrangement, name) ->
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = name)
                    Column(
                        modifier = Modifier
                            .height(200.dp)
                            .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                            .padding(2.dp),
                        verticalArrangement = arrangement
                    ) {
                        listOf("数码", "汽车", "摄影").forEach {
                            HorizontalTag(text = it)
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


@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ColumnVerticalSpacedSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "Column（VerticalSpaced）", allExpandFlow, padding = 20.dp) {
        VerticalGrid(
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            listOf(
                0 to "0.dp",
                10 to "10.dp",
                20 to "20.dp",
            ).forEach { (spacing, name) ->
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = name)
                    Column(
                        modifier = Modifier
                            .height(200.dp)
                            .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                            .padding(2.dp),
                        verticalArrangement = Arrangement.spacedBy(spacing.dp)
                    ) {
                        listOf(
                            "数码",
                            "汽车",
                            "摄影",
                            "舞蹈",
                            "音乐",
                            "科技",
                            "健身",
                            "游戏",
                            "文学"
                        ).forEach {
                            HorizontalTag(text = it)
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun ColumnVerticalSpacedSamplePreview() {
    ColumnVerticalSpacedSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ColumnHorizontalAlignmentSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "Column（horizontalAlignment）", allExpandFlow, padding = 20.dp) {
        VerticalGrid(
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            listOf(
                Alignment.Start to "Start",
                Alignment.CenterHorizontally to "Center\nHorizontally",
                Alignment.End to "End",
            ).forEach { (alignment, name) ->
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = name,
                        modifier = Modifier.height(46.dp)
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                            .padding(2.dp),
                        horizontalAlignment = alignment
                    ) {
                        listOf("数码", "汽车", "摄影").forEach {
                            HorizontalTag(text = it)
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


@Composable
private fun ColumnWeightSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "Column（weight）", allExpandFlow, padding = 20.dp) {
        FlowRow(mainAxisSpacing = 10.dp, crossAxisSpacing = 10.dp) {
            listOf(
                listOf("数码" to 1f, "汽车" to null, "摄影" to null),
                listOf("数码" to 1f, "汽车" to 1f, "摄影" to null),
                listOf("数码" to 1f, "汽车" to 1f, "摄影" to 1f),
            ).forEach { list ->
                Column(
                    modifier = Modifier
                        .height(200.dp)
                        .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                        .padding(2.dp)
                ) {
                    list.forEach { (tag, weight) ->
                        HorizontalTag(
                            text = tag,
                            modifier = Modifier.let {
                                if (weight != null) it.weight(weight) else it
                            },
                        )
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


@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ColumnAlignSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "Column（align）", allExpandFlow, padding = 20.dp) {
        VerticalGrid(
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            listOf(
                Alignment.Start to "Start",
                Alignment.CenterHorizontally to "Center\nHorizontally",
                Alignment.End to "End",
            ).forEach { (alignment, name) ->
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
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
                            .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                            .padding(2.dp)
                    ) {
                        HorizontalTag(text = "数码")
                        HorizontalTag(
                            text = "舞蹈",
                            modifier = Modifier.align(alignment),
                        )
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