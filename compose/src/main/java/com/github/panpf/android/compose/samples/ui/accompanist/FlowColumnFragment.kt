package com.github.panpf.android.compose.samples.ui.accompanist

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
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
import com.github.panpf.android.compose.samples.ui.base.VerticalTag
import com.github.panpf.android.compose.samples.ui.customization.grid.VerticalGrid
import com.google.accompanist.flowlayout.FlowColumn
import com.google.accompanist.flowlayout.FlowCrossAxisAlignment
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.SizeMode
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FlowColumnFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "FlowColumn - Accompanist"
    }

    @Composable
    override fun DrawContent() {
        ExpandableLayout { allExpandFlow ->
            FlowColumnSample(allExpandFlow)
            FlowColumnMainAxisSizeSample(allExpandFlow)
            FlowColumnMainAxisAlignmentSample(allExpandFlow)
            FlowColumnMainAxisSpacingSample(allExpandFlow)
            FlowColumnCrossAxisAlignmentSample(allExpandFlow)
            FlowColumnCrossAxisSpacingSample(allExpandFlow)
            FlowColumnLastLineMainAxisAlignmentSample(allExpandFlow)
        }
    }
}


@Composable
private fun FlowColumnSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "FlowColumn", allExpandFlow, padding = 20.dp) {
        Row {
            FlowColumn(
                modifier = Modifier
                    .height(200.dp)
                    .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                    .padding(2.dp)
            ) {
                listOf("数码", "汽车", "摄影").forEach {
                    HorizontalTag(text = it)
                }
            }

            Spacer(modifier = Modifier.size(20.dp))

            FlowColumn(
                modifier = Modifier
                    .height(200.dp)
                    .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                    .padding(2.dp)
            ) {
                listOf("数码", "汽车", "摄影", "舞蹈", "音乐", "科技", "漫画", "功夫").forEach {
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


@Composable
private fun FlowColumnMainAxisSizeSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "FlowColumn（mainAxisSize）", allExpandFlow, padding = 20.dp) {
        Row(Modifier.height(240.dp)) {
            listOf(
                SizeMode.Wrap to "Wrap",
                SizeMode.Expand to "Expand",
            ).forEachIndexed { index, (sizeMode, name) ->
                if (index > 0) {
                    Spacer(modifier = Modifier.size(20.dp))
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
                            .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                            .padding(2.dp),
                        mainAxisSize = sizeMode
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
private fun FlowColumnMainAxisSizeSamplePreview() {
    FlowColumnMainAxisSizeSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun FlowColumnMainAxisAlignmentSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "FlowColumn（mainAxisAlignment）", allExpandFlow, padding = 20.dp) {
        VerticalGrid(
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            listOf(
                FlowMainAxisAlignment.Start to "Start",
                FlowMainAxisAlignment.Center to "Center",
                FlowMainAxisAlignment.End to "End",
                FlowMainAxisAlignment.SpaceBetween to "SpaceBetween",
                FlowMainAxisAlignment.SpaceAround to "SpaceAround",
                FlowMainAxisAlignment.SpaceEvenly to "SpaceEvenly",
            ).forEach { (alignment, name) ->
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = name)
                    FlowColumn(
                        modifier = Modifier
                            .height(200.dp)
                            .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                            .padding(2.dp),
                        mainAxisAlignment = alignment
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
private fun FlowColumnMainAxisAlignmentSamplePreview() {
    FlowColumnMainAxisAlignmentSample(remember { MutableStateFlow(true) })
}


@Composable
private fun FlowColumnMainAxisSpacingSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "FlowColumn（mainAxisSpacing）", allExpandFlow, padding = 20.dp) {
        Row {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "0.dp")
                FlowColumn(
                    modifier = Modifier
                        .height(200.dp)
                        .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                        .padding(2.dp),
                    mainAxisSpacing = 0.dp
                ) {
                    listOf("数码", "汽车", "摄影", "舞蹈", "音乐", "科技", "漫画", "功夫").forEach {
                        HorizontalTag(text = it)
                    }
                }
            }

            Spacer(modifier = Modifier.size(20.dp))

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "20.dp")
                FlowColumn(
                    modifier = Modifier
                        .height(200.dp)
                        .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                        .padding(2.dp),
                    mainAxisSpacing = 20.dp
                ) {
                    listOf("数码", "汽车", "摄影", "舞蹈", "音乐", "科技", "漫画", "功夫").forEach {
                        HorizontalTag(text = it)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun FlowColumnMainAxisSpacingSamplePreview() {
    FlowColumnMainAxisSpacingSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun FlowColumnCrossAxisAlignmentSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "FlowColumn（crossAxisAlignment）", allExpandFlow, padding = 20.dp) {
        VerticalGrid(
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            listOf(
                FlowCrossAxisAlignment.Start to "Start",
                FlowCrossAxisAlignment.Center to "Center",
                FlowCrossAxisAlignment.End to "End",
            ).forEach { (alignment, name) ->
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = name)
                    FlowColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                            .padding(2.dp),
                        crossAxisAlignment = alignment
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
private fun FlowColumnCrossAxisAlignmentSamplePreview() {
    FlowColumnCrossAxisAlignmentSample(remember { MutableStateFlow(true) })
}


@Composable
private fun FlowColumnCrossAxisSpacingSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "FlowColumn（crossAxisSpacing）", allExpandFlow, padding = 20.dp) {
        Row {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "0.dp")
                FlowColumn(
                    modifier = Modifier
                        .height(200.dp)
                        .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                        .padding(2.dp),
                    crossAxisSpacing = 0.dp
                ) {
                    listOf("数码", "汽车", "摄影", "舞蹈", "音乐", "科技", "漫画", "功夫").forEach {
                        HorizontalTag(text = it)
                    }
                }
            }

            Spacer(modifier = Modifier.size(20.dp))

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "20.dp")
                FlowColumn(
                    modifier = Modifier
                        .height(200.dp)
                        .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                        .padding(2.dp),
                    crossAxisSpacing = 20.dp
                ) {
                    listOf("数码", "汽车", "摄影", "舞蹈", "音乐", "科技", "漫画", "功夫").forEach {
                        HorizontalTag(text = it)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun FlowColumnCrossAxisSpacingSamplePreview() {
    FlowColumnCrossAxisSpacingSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun FlowColumnLastLineMainAxisAlignmentSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(
        title = "FlowColumn（lastLineMainAxisAlignment）",
        allExpandFlow,
        padding = 20.dp
    ) {
        VerticalGrid(
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            listOf(
                FlowMainAxisAlignment.Start to "Start",
                FlowMainAxisAlignment.Center to "Center",
                FlowMainAxisAlignment.End to "End",
                FlowMainAxisAlignment.SpaceBetween to "SpaceBetween",
                FlowMainAxisAlignment.SpaceAround to "SpaceAround",
                FlowMainAxisAlignment.SpaceEvenly to "SpaceEvenly",
            ).forEach { (alignment, name) ->
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = name)
                    FlowColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(260.dp)
                            .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                            .padding(2.dp),
                        lastLineMainAxisAlignment = alignment
                    ) {
                        listOf(
                            "数\n码",
                            "汽\n车",
                            "摄\n影",
                            "舞\n蹈",
                            "音\n乐",
                            "科\n技",
                            "健\n身",
                            "漫\n画",
                            "功\n夫"
                        ).forEach {
                            VerticalTag(text = it)
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun FlowColumnLastLineMainAxisAlignmentSamplePreview() {
    FlowColumnLastLineMainAxisAlignmentSample(remember { MutableStateFlow(true) })
}