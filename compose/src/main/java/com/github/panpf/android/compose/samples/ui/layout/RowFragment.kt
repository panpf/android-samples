package com.github.panpf.android.compose.samples.ui.layout

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
            RowHorizontalArrangementSample(allExpandFlow)
            RowHorizontalSpacedSample(allExpandFlow)
            RowVerticalAlignmentSample(allExpandFlow)
            RowWeightSample(allExpandFlow)
            RowAlignSample(allExpandFlow)
        }
    }
}


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
                HorizontalTag(text = it)
            }
        }

        Spacer(modifier = Modifier.size(20.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                .padding(2.dp)
        ) {
            listOf(
                "数码",
                "汽车",
                "摄影",
                "舞蹈",
                "二次元",
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

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun RowSamplePreview() {
    RowSample(remember { MutableStateFlow(true) })
}


@Composable
private fun RowHorizontalArrangementSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "Row（horizontalArrangement）", allExpandFlow, padding = 20.dp) {
        Column {
            listOf(
                Arrangement.Start to "Start",
                Arrangement.Center to "Center",
                Arrangement.End to "End",
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
                    horizontalArrangement = arrangement
                ) {
                    listOf("数码", "汽车", "摄影", "舞蹈").forEach {
                        HorizontalTag(text = it)
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


@Composable
private fun RowHorizontalSpacedSample(allExpandFlow: Flow<Boolean>) {
    val items = remember {
        listOf("数码", "汽车", "摄影", "舞蹈", "二次元", "音乐", "科技", "健身", "游戏", "文学")
    }
    ExpandableItem3(title = "Row（HorizontalSpaced）", allExpandFlow, padding = 20.dp) {
        listOf(
            0 to "0.dp",
            10 to "10.dp",
            20 to "20.dp",
        ).forEachIndexed { index, (spacing, name) ->
            if (index > 0) {
                Spacer(modifier = Modifier.size(10.dp))
            }
            Text(text = name)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                    .padding(2.dp),
                horizontalArrangement = Arrangement.spacedBy(spacing.dp)
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
private fun RowHorizontalSpacedSamplePreview() {
    RowHorizontalSpacedSample(remember { MutableStateFlow(true) })
}


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
                        HorizontalTag(text = it)
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
private fun RowWeightSamplePreview() {
    RowWeightSample(remember { MutableStateFlow(true) })
}


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

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun RowAlignSamplePreview() {
    RowAlignSample(remember { MutableStateFlow(true) })
}