package com.github.panpf.android.compose.samples.ui.layout

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FlowColumnFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "FlowColumn - Foundation"
    }

    @Composable
    override fun DrawContent() {
        ExpandableLayout { allExpandFlow ->
            FlowColumnSample(allExpandFlow)
            FlowColumnVerticalArrangementSample(allExpandFlow)
            FlowColumnVerticalSpacedSample(allExpandFlow)
            FlowColumnHorizontalArrangementSample(allExpandFlow)
            FlowColumnHorizontalSpacedSample(allExpandFlow)
            FlowColumnMaxItemsInEachRowSample(allExpandFlow)
        }
    }
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun FlowColumnSample(allExpandFlow: Flow<Boolean>) {
    val items = remember { listOf("数码", "汽车", "摄影", "舞蹈", "音乐", "科技", "漫画", "功夫") }
    ExpandableItem3(title = "FlowColumn", allExpandFlow, padding = 20.dp) {
        Row {
            FlowColumn(
                modifier = Modifier
                    .height(200.dp)
                    .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                    .padding(2.dp)
            ) {
                items.take(3).forEach {
                    HorizontalTag(text = it)
                }
            }

            Spacer(modifier = Modifier.size(10.dp))
            FlowColumn(
                modifier = Modifier
                    .height(200.dp)
                    .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                    .padding(2.dp)
            ) {
                items.forEach {
                    HorizontalTag(text = it)
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun FlowColumnSamplePreview() {
    FlowColumnSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun FlowColumnVerticalArrangementSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "FlowColumn（verticalAlignment）", allExpandFlow, padding = 20.dp) {
        VerticalGrid(
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            listOf(
                Arrangement.Top to "Top",
                Arrangement.Center to "Center",
                Arrangement.Bottom to "Bottom",
                Arrangement.SpaceBetween to "Space\nBetween",
                Arrangement.SpaceAround to "Space\nAround",
                Arrangement.SpaceEvenly to "Space\nEvenly",
            ).forEach { (alignment, name) ->
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = name)
                    FlowColumn(
                        modifier = Modifier
                            .height(200.dp)
                            .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                            .padding(2.dp),
                        verticalArrangement = alignment,
                    ) {
                        listOf(
                            "数码",
                            "汽车",
                            "摄影",
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
private fun FlowColumnVerticalArrangementSamplePreview() {
    FlowColumnVerticalArrangementSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun FlowColumnVerticalSpacedSample(allExpandFlow: Flow<Boolean>) {
    val items = remember { listOf("数码", "汽车", "摄影", "舞蹈", "音乐", "科技", "漫画", "功夫") }
    ExpandableItem3(title = "FlowColumn（VerticalSpaced）", allExpandFlow, padding = 20.dp) {
        VerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            listOf(
                0 to "0.dp",
                10 to "10.dp",
            ).forEach { (spacing, name) ->
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = name)
                    FlowColumn(
                        modifier = Modifier
                            .height(200.dp)
                            .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                            .padding(2.dp),
                        verticalArrangement = Arrangement.spacedBy(spacing.dp),
                    ) {
                        items.forEach {
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
private fun FlowColumnVerticalSpacedSamplePreview() {
    FlowColumnVerticalSpacedSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun FlowColumnHorizontalArrangementSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "FlowColumn（horizontalArrangement）", allExpandFlow, padding = 20.dp) {
        VerticalGrid(
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            listOf(
                Arrangement.Start to "Start",
                Arrangement.Center to "Center",
                Arrangement.End to "End",
                Arrangement.SpaceBetween to "Space\nBetween",
                Arrangement.SpaceAround to "Space\nAround",
                Arrangement.SpaceEvenly to "Space\nEvenly",
            ).forEach { (alignment, name) ->
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = name,
                        modifier = Modifier.height(46.dp)
                    )
                    FlowColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                            .padding(2.dp),
                        horizontalArrangement = alignment
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
private fun FlowColumnHorizontalArrangementSamplePreview() {
    FlowColumnHorizontalArrangementSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun FlowColumnHorizontalSpacedSample(allExpandFlow: Flow<Boolean>) {
    val items = remember { listOf("数码", "汽车", "摄影", "舞蹈", "音乐", "科技", "漫画", "功夫") }
    ExpandableItem3(title = "FlowColumn（HorizontalSpaced）", allExpandFlow, padding = 20.dp) {
        VerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            listOf(
                0 to "0.dp",
                10 to "10.dp",
            ).forEach { (spacing, name) ->
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = name)
                    FlowColumn(
                        modifier = Modifier
                            .height(200.dp)
                            .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                            .padding(2.dp),
                        horizontalArrangement = Arrangement.spacedBy(spacing.dp),
                    ) {
                        items.forEach {
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
private fun FlowColumnHorizontalSpacedSamplePreview() {
    FlowColumnHorizontalSpacedSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun FlowColumnMaxItemsInEachRowSample(allExpandFlow: Flow<Boolean>) {
    val items = remember { listOf("数码", "汽车", "摄影", "舞蹈", "音乐", "科技", "教育") }
    ExpandableItem3(title = "FlowColumn（maxItemsInEachColumn）", allExpandFlow, padding = 20.dp) {
        VerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            listOf(
                "Int.MAX_VALUE" to Int.MAX_VALUE,
                "4" to 4
            ).forEach { (title, maxItemsInEachColumn) ->
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = title)
                    FlowColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                            .padding(2.dp),
                        maxItemsInEachColumn = maxItemsInEachColumn,
                    ) {
                        items.forEach {
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
private fun FlowColumnMaxItemsInEachRowSamplePreview() {
    FlowColumnMaxItemsInEachRowSample(remember { MutableStateFlow(true) })
}