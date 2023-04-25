package com.github.panpf.android.compose.samples.ui.accompanist

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedAssistChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem3
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
import com.google.accompanist.flowlayout.FlowCrossAxisAlignment
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.SizeMode
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FlowRowFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "FlowRow - Accompanist"
    }

    @Composable
    override fun DrawContent() {
        ExpandableLayout { allExpandFlow ->
            FlowRowSample(allExpandFlow)
            FlowRowMainAxisSizeSample(allExpandFlow)
            FlowRowMainAxisAlignmentSample(allExpandFlow)
            FlowRowMainAxisSpacingSample(allExpandFlow)
            FlowRowCrossAxisAlignmentSample(allExpandFlow)
            FlowRowCrossAxisSpacingSample(allExpandFlow)
            FlowRowLastLineMainAxisAlignmentSample(allExpandFlow)
        }
    }
}


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
                ElevatedAssistChip(
                    onClick = { },
                    shape = RoundedCornerShape(50),
                    label = { Text(text = it) }
                )
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
private fun FlowRowSamplePreview() {
    FlowRowSample(remember { MutableStateFlow(true) })
}


@Composable
private fun FlowRowMainAxisSizeSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "FlowRow（mainAxisSize）", allExpandFlow, padding = 20.dp) {
        Column {
            listOf(
                SizeMode.Wrap to "Wrap",
                SizeMode.Expand to "Expand",
            ).forEachIndexed { index, (sizeMode, name) ->
                if (index > 0) {
                    Spacer(modifier = Modifier.size(10.dp))
                }
                Text(text = name)
                FlowRow(
                    modifier = Modifier
                        .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                        .padding(2.dp),
                    mainAxisSize = sizeMode
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
private fun FlowRowMainAxisSizeSamplePreview() {
    FlowRowMainAxisSizeSample(remember { MutableStateFlow(true) })
}


@Composable
private fun FlowRowMainAxisAlignmentSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "FlowRow（mainAxisAlignment）", allExpandFlow, padding = 20.dp) {
        Column {
            listOf(
                FlowMainAxisAlignment.Start to "Start",
                FlowMainAxisAlignment.Center to "Center",
                FlowMainAxisAlignment.End to "End",
                FlowMainAxisAlignment.SpaceBetween to "SpaceBetween",
                FlowMainAxisAlignment.SpaceAround to "SpaceAround",
                FlowMainAxisAlignment.SpaceEvenly to "SpaceEvenly",
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
                    mainAxisAlignment = alignment
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
private fun FlowRowMainAxisAlignmentSamplePreview() {
    FlowRowMainAxisAlignmentSample(remember { MutableStateFlow(true) })
}


@Composable
private fun FlowRowMainAxisSpacingSample(allExpandFlow: Flow<Boolean>) {
    val items = remember {
        listOf("数码", "汽车", "摄影", "舞蹈", "二次元", "音乐", "科技", "健身", "游戏", "文学")
    }
    ExpandableItem3(title = "FlowRow（mainAxisSpacing）", allExpandFlow, padding = 20.dp) {
        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                .padding(2.dp),
            mainAxisSpacing = 10.dp
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

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun FlowRowMainAxisSpacingSamplePreview() {
    FlowRowMainAxisSpacingSample(remember { MutableStateFlow(true) })
}


@Composable
private fun FlowRowCrossAxisAlignmentSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "FlowRow（crossAxisAlignment）", allExpandFlow, padding = 20.dp) {
        Column {
            listOf(
                FlowCrossAxisAlignment.Start to "Start",
                FlowCrossAxisAlignment.Center to "Center",
                FlowCrossAxisAlignment.End to "End",
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
                    crossAxisAlignment = alignment
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
private fun FlowRowCrossAxisAlignmentSamplePreview() {
    FlowRowCrossAxisAlignmentSample(remember { MutableStateFlow(true) })
}


@Composable
private fun FlowRowCrossAxisSpacingSample(allExpandFlow: Flow<Boolean>) {
    val items = remember {
        listOf("数码", "汽车", "摄影", "舞蹈", "二次元", "音乐", "科技", "健身", "游戏", "文学")
    }
    ExpandableItem3(title = "FlowRow（crossAxisSpacing）", allExpandFlow, padding = 20.dp) {
        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                .padding(2.dp),
            crossAxisSpacing = 16.dp
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

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun FlowRowCrossAxisSpacingSamplePreview() {
    FlowRowCrossAxisSpacingSample(remember { MutableStateFlow(true) })
}


@Composable
private fun FlowRowLastLineMainAxisAlignmentSample(allExpandFlow: Flow<Boolean>) {
    val items = remember {
        listOf("数码", "汽车", "摄影", "舞蹈", "二次元", "音乐", "科技", "健身", "游戏", "文学")
    }
    ExpandableItem3(title = "FlowRow（lastLineMainAxisAlignment）", allExpandFlow, padding = 20.dp) {
        Column {
            listOf(
                FlowMainAxisAlignment.Start to "Start",
                FlowMainAxisAlignment.Center to "Center",
                FlowMainAxisAlignment.End to "End",
                FlowMainAxisAlignment.SpaceBetween to "SpaceBetween",
                FlowMainAxisAlignment.SpaceAround to "SpaceAround",
                FlowMainAxisAlignment.SpaceEvenly to "SpaceEvenly",
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
                    lastLineMainAxisAlignment = alignment
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
private fun FlowRowLastLineMainAxisAlignmentSamplePreview() {
    FlowRowLastLineMainAxisAlignmentSample(remember { MutableStateFlow(true) })
}