package com.github.panpf.android.compose.samples.ui.layout

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem3
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.HorizontalTag
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FlowRowFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "FlowRow - Foundation"
    }

    @Composable
    override fun DrawContent() {
        ExpandableLayout { allExpandFlow ->
            FlowRowSample(allExpandFlow)
            FlowRowHorizontalArrangementSample(allExpandFlow)
            FlowRowHorizontalSpacedSample(allExpandFlow)
            FlowRowVerticalArrangementSample(allExpandFlow)
            FlowRowVerticalSpacedSample(allExpandFlow)
            FlowRowMaxItemsInEachRowSample(allExpandFlow)
        }
    }
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun FlowRowSample(allExpandFlow: Flow<Boolean>) {
    val items = remember {
        listOf("数码", "汽车", "摄影", "舞蹈", "二次元", "音乐", "科技", "健身", "游戏", "文学")
    }
    ExpandableItem3(title = "FlowRow", allExpandFlow, padding = 20.dp) {
        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                .padding(2.dp)
        ) {
            items.take(4).forEach {
                HorizontalTag(text = it)
            }
        }

        Spacer(modifier = Modifier.size(10.dp))
        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                .padding(2.dp)
        ) {
            items.forEach {
                HorizontalTag(text = it)
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun FlowRowSamplePreview() {
    FlowRowSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun FlowRowHorizontalArrangementSample(allExpandFlow: Flow<Boolean>) {
    val items = remember {
        listOf("数码", "汽车", "摄影", "舞蹈", "二次元", "赛博朋克", "音乐", "科技")
    }
    ExpandableItem3(title = "FlowRow（horizontalArrangement）", allExpandFlow, padding = 20.dp) {
        Column {
            listOf(
                Arrangement.Start to "Start",
                Arrangement.Center to "Center",
                Arrangement.End to "End",
                Arrangement.SpaceBetween to "Space\nBetween",
                Arrangement.SpaceAround to "Space\nAround",
                Arrangement.SpaceEvenly to "Space\nEvenly",
            ).forEachIndexed { index, (alignment, name) ->
                if (index > 0) {
                    Spacer(modifier = Modifier.size(10.dp))
                }
                Text(text = name)
                FlowRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                        .padding(2.dp),
                    horizontalArrangement = alignment
                ) {
                    items.forEach {
                        HorizontalTag(text = it)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun FlowRowHorizontalArrangementSamplePreview() {
    FlowRowHorizontalArrangementSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun FlowRowHorizontalSpacedSample(allExpandFlow: Flow<Boolean>) {
    val items = remember {
        listOf("数码", "汽车", "摄影", "舞蹈", "二次元", "音乐", "科技", "健身", "游戏", "文学")
    }
    ExpandableItem3(title = "FlowRow（HorizontalSpaced）", allExpandFlow, padding = 20.dp) {
        Column {
            listOf(
                0 to "0.dp",
                10 to "10.dp",
            ).forEachIndexed { index, (spacing, name) ->
                if (index > 0) {
                    Spacer(modifier = Modifier.size(10.dp))
                }
                Text(text = name)
                FlowRow(
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
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun FlowRowHorizontalSpacedSamplePreview() {
    FlowRowHorizontalSpacedSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun FlowRowVerticalArrangementSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "FlowRow（verticalArrangement）", allExpandFlow, padding = 20.dp) {
        Column {
            listOf(
                Arrangement.Top to "Start",
                Arrangement.Center to "Center",
                Arrangement.Bottom to "End",
                Arrangement.SpaceBetween to "Space\nBetween",
                Arrangement.SpaceAround to "Space\nAround",
                Arrangement.SpaceEvenly to "Space\nEvenly",
            ).forEachIndexed { index, (alignment, name) ->
                if (index > 0) {
                    Spacer(modifier = Modifier.size(10.dp))
                }
                Text(text = name)
                FlowRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                        .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                        .padding(2.dp),
                    verticalArrangement = alignment
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
private fun FlowRowVerticalArrangementSamplePreview() {
    FlowRowVerticalArrangementSample(remember { MutableStateFlow(true) })
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun FlowRowVerticalSpacedSample(allExpandFlow: Flow<Boolean>) {
    val items = remember {
        listOf("数码", "汽车", "摄影", "舞蹈", "二次元", "音乐", "科技", "健身", "游戏", "文学")
    }
    ExpandableItem3(title = "FlowRow（VerticalSpaced）", allExpandFlow, padding = 20.dp) {
        Column {
            listOf(
                0 to "0.dp",
                10 to "10.dp",
            ).forEachIndexed { index, (spacing, name) ->
                if (index > 0) {
                    Spacer(modifier = Modifier.size(10.dp))
                }
                Text(text = name)
                FlowRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                        .padding(2.dp),
                    verticalArrangement = Arrangement.spacedBy(spacing.dp)
                ) {
                    items.forEach {
                        HorizontalTag(text = it)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun FlowRowVerticalSpacedSamplePreview() {
    FlowRowVerticalSpacedSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun FlowRowMaxItemsInEachRowSample(allExpandFlow: Flow<Boolean>) {
    val items = remember {
        listOf("数码", "汽车", "摄影", "舞蹈", "二次元", "音乐", "科技", "健身", "游戏", "文学")
    }
    ExpandableItem3(title = "FlowRow（maxItemsInEachRow）", allExpandFlow, padding = 20.dp) {
        Text(text = "Int.MAX_VALUE")
        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                .padding(2.dp)
        ) {
            items.forEach {
                HorizontalTag(text = it)
            }
        }

        Spacer(modifier = Modifier.size(10.dp))
        Text(text = "3")
        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                .padding(2.dp),
            maxItemsInEachRow = 3
        ) {
            items.forEach {
                HorizontalTag(text = it)
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun FlowRowMaxItemsInEachRowSamplePreview() {
    FlowRowMaxItemsInEachRowSample(remember { MutableStateFlow(true) })
}