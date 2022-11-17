package com.github.panpf.android.compose.samples.ui.components.basic

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedAssistChip
import androidx.compose.material3.ExperimentalMaterial3Api
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

class RowFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "Row"
    }

    @Composable
    override fun DrawContent() {
        ExpandableLayout { allExpandFlow ->
            RowSample(allExpandFlow)
            RowFullSample(allExpandFlow)
            RowItemSpacedSample(allExpandFlow)
            RowHorizontalArrangementSample(allExpandFlow)
            RowVerticalAlignmentSample(allExpandFlow)
            RowWeightSample(allExpandFlow)
            RowAlignSample(allExpandFlow)
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun RowSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "Row", allExpandFlow, padding = 20.dp) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                .padding(2.dp)
        ) {
            listOf("数码", "汽车", "摄影", "舞蹈").forEach {
                ElevatedAssistChip(
                    onClick = { },
                    shape = RoundedCornerShape(50),
                    label = { Text(text = it) }
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun RowSamplePreview() {
    RowSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun RowFullSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "Row（Full）", allExpandFlow, padding = 20.dp) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                .padding(2.dp)
        ) {
            listOf("数码", "汽车", "摄影", "舞蹈", "二次元", "音乐", "科技", "健身", "游戏", "文学").forEach {
                ElevatedAssistChip(
                    onClick = { },
                    shape = RoundedCornerShape(50),
                    label = { Text(text = it) }
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun RowFullSamplePreview() {
    RowFullSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun RowItemSpacedSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "Row（ItemSpaced）", allExpandFlow, padding = 20.dp) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                .padding(2.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            listOf("数码", "汽车", "摄影", "舞蹈", "二次元", "音乐", "科技", "健身", "游戏", "文学").forEach {
                ElevatedAssistChip(
                    onClick = { },
                    shape = RoundedCornerShape(50),
                    label = { Text(text = it) }
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun RowItemSpacedSamplePreview() {
    RowItemSpacedSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun RowHorizontalArrangementSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "Row（horizontalArrangement）", allExpandFlow, padding = 20.dp) {
        Column {
            listOf(
                Arrangement.Start to "Start",
                Arrangement.Center to "Center",
                Arrangement.End to "End",
                null to "Space",
                Arrangement.SpaceBetween to "SpaceBetween",
                Arrangement.SpaceAround to "SpaceAround",
                Arrangement.SpaceEvenly to "SpaceEvenly",
            ).forEachIndexed { index, (arrangement, name) ->
                if (index > 0) {
                    Spacer(modifier = Modifier.size(10.dp))
                }
                Text(text = name)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                        .padding(2.dp),
                    horizontalArrangement = arrangement ?: Arrangement.spacedBy(10.dp)
                ) {
                    listOf("数码", "汽车", "摄影", "舞蹈").forEach {
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
private fun RowHorizontalArrangementSamplePreview() {
    RowHorizontalArrangementSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun RowVerticalAlignmentSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "Row（verticalAlignment）", allExpandFlow, padding = 20.dp) {
        Column {
            listOf(
                Alignment.Top to "Top",
                Alignment.CenterVertically to "CenterVertically",
                Alignment.Bottom to "Bottom",
            ).forEachIndexed { index, (alignment, name) ->
                if (index > 0) {
                    Spacer(modifier = Modifier.size(10.dp))
                }
                Text(text = name)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                        .padding(2.dp),
                    verticalAlignment = alignment
                ) {
                    listOf("数码", "汽车", "摄影", "舞蹈").forEach {
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
private fun RowVerticalAlignmentSamplePreview() {
    RowVerticalAlignmentSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun RowWeightSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "Row（weight）", allExpandFlow, padding = 20.dp) {
        Column {
            listOf(
                listOf("数码" to 1f, "汽车" to null, "摄影" to null, "舞蹈" to null),
                listOf("数码" to 1f, "汽车" to 1f, "摄影" to null, "舞蹈" to null),
                listOf("数码" to 1f, "汽车" to 1f, "摄影" to 1f, "舞蹈" to null),
                listOf("数码" to 1f, "汽车" to 1f, "摄影" to 1f, "舞蹈" to 1f),
            ).forEachIndexed { index, list ->
                if (index > 0) {
                    Spacer(modifier = Modifier.size(10.dp))
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                        .padding(2.dp)
                ) {
                    list.forEach { (tag, weight) ->
                        ElevatedAssistChip(
                            onClick = { },
                            modifier = Modifier.let {
                                if (weight != null) it.weight(weight) else it
                            },
                            shape = RoundedCornerShape(50),
                            label = { Text(text = tag) }
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun RowWeightSamplePreview() {
    RowWeightSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun RowAlignSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "Row（align）", allExpandFlow, padding = 20.dp) {
        Column {
            listOf(
                Alignment.Top to "Top",
                Alignment.CenterVertically to "CenterVertically",
                Alignment.Bottom to "Bottom",
            ).forEachIndexed { index, (alignment, name) ->
                if (index > 0) {
                    Spacer(modifier = Modifier.size(10.dp))
                }
                Text(text = name)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(70.dp)
                        .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                        .padding(2.dp)
                ) {
                    ElevatedAssistChip(
                        onClick = { },
                        shape = RoundedCornerShape(50),
                        label = { Text(text = "数码") }
                    )
                    ElevatedAssistChip(
                        onClick = { },
                        shape = RoundedCornerShape(50),
                        modifier = Modifier.align(alignment),
                        label = { Text(text = "舞蹈") }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun RowAlignSamplePreview() {
    RowAlignSample(remember { MutableStateFlow(true) })
}