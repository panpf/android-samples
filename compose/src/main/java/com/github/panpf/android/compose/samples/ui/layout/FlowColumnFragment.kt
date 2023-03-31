package com.github.panpf.android.compose.samples.ui.layout

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedAssistChip
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
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
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
            FlowColumnHorizontalAlignmentSample(allExpandFlow)
            FlowColumnMaxItemsInEachRowSample(allExpandFlow)
        }
    }
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun FlowColumnSample(allExpandFlow: Flow<Boolean>) {
    val items = remember { listOf("数码", "汽车", "摄影", "舞蹈", "音乐", "科技") }
    ExpandableItem3(title = "FlowColumn", allExpandFlow, padding = 20.dp) {
        Row {
            FlowColumn(
                modifier = Modifier
                    .height(200.dp)
                    .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                    .padding(2.dp)
            ) {
                items.take(3).forEach {
                    ElevatedAssistChip(
                        onClick = { },
                        shape = RoundedCornerShape(50),
                        label = { Text(text = it) }
                    )
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
                    ElevatedAssistChip(
                        onClick = { },
                        shape = RoundedCornerShape(50),
                        label = { Text(text = it) }
                    )
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
        FlowRow {
            listOf(
                Arrangement.Top to "Top",
                Arrangement.Center to "Center",
                Arrangement.Bottom to "Bottom",
                null to "Space=10.dp",
                Arrangement.SpaceBetween to "Space\nBetween",
                Arrangement.SpaceAround to "Space\nAround",
                Arrangement.SpaceEvenly to "Space\nEvenly",
            ).forEachIndexed { index, (alignment, name) ->
                if (index > 0) {
                    Spacer(modifier = Modifier.size(10.dp))
                }
                Column {
                    Text(
                        text = name,
                        modifier = Modifier
                            .height(46.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                    FlowColumn(
                        modifier = Modifier
                            .height(200.dp)
                            .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                            .padding(2.dp),
                        verticalArrangement = alignment ?: Arrangement.spacedBy(10.dp),
                    ) {
                        listOf(
                            "数码",
                            "汽车",
                            "摄影",
                        ).forEach {
                            ElevatedAssistChip(
                                onClick = { },
                                shape = RoundedCornerShape(50),
                                label = { Text(text = it) }
                            )
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
private fun FlowColumnHorizontalAlignmentSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "FlowColumn（Alignment）（无效）", allExpandFlow, padding = 20.dp) {
        FlowRow {
            listOf(
                Alignment.Start to "Start",
                Alignment.CenterHorizontally to "Center",
                Alignment.End to "End",
            ).forEachIndexed { index, (alignment, name) ->
                if (index > 0) {
                    Spacer(modifier = Modifier.size(10.dp))
                }
                Column {
                    Text(
                        text = name,
                        modifier = Modifier
                            .height(46.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                    FlowColumn(
                        modifier = Modifier
                            .height(200.dp)
                            .width(100.dp)
                            .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                            .padding(2.dp),
                        horizontalAlignment = alignment  // todo Invalid
                    ) {
                        listOf("数码", "汽车", "摄影").forEach {
                            ElevatedAssistChip(
                                onClick = { },
                                shape = RoundedCornerShape(50),
                                label = { Text(text = it) }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun FlowColumnHorizontalAlignmentSamplePreview() {
    FlowColumnHorizontalAlignmentSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun FlowColumnMaxItemsInEachRowSample(allExpandFlow: Flow<Boolean>) {
    val items = remember { listOf("数码", "汽车", "摄影", "舞蹈", "音乐", "科技", "教育") }
    ExpandableItem3(title = "FlowColumn（maxItemsInEachColumn）", allExpandFlow, padding = 20.dp) {
        Row {
            Column {
                Text(text = "Int.MAX_VALUE")
                FlowColumn(
                    modifier = Modifier
                        .height(200.dp)
                        .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                        .padding(2.dp)
                ) {
                    items.forEach {
                        ElevatedAssistChip(
                            onClick = { },
                            shape = RoundedCornerShape(50),
                            label = { Text(text = it) }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.size(10.dp))
            Column {
                Text(text = "3")
                FlowColumn(
                    modifier = Modifier
                        .height(200.dp)
                        .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                        .padding(2.dp),
                    maxItemsInEachColumn = 3
                ) {
                    items.forEach {
                        ElevatedAssistChip(
                            onClick = { },
                            shape = RoundedCornerShape(50),
                            label = { Text(text = it) }
                        )
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