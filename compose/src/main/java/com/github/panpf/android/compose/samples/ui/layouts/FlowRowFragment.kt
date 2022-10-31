package com.github.panpf.android.compose.samples.ui.layouts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.Chip
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.ToolbarFragment
import com.github.panpf.android.compose.samples.ui.base.theme.MyTheme
import com.google.accompanist.flowlayout.FlowCrossAxisAlignment
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.SizeMode
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FlowRowFragment : ToolbarFragment() {

    override fun createView(
        toolbar: Toolbar,
        inflater: LayoutInflater,
        parent: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        toolbar.title = "FlowRow"
        return ComposeView(inflater.context).apply {
            setContent {
                MyTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        ExpandableLayout { allExpandFlow ->
                            FlowRowSample(allExpandFlow)
                            FlowRowFullSample(allExpandFlow)
                            FlowRowMainAxisSizeSample(allExpandFlow)
                            FlowRowMainAxisAlignmentSample(allExpandFlow)
                            FlowRowMainAxisSpacingSample(allExpandFlow)
                            FlowRowCrossAxisAlignmentSample(allExpandFlow)
                            FlowRowCrossAxisSpacingSample(allExpandFlow)
                            FlowRowLastLineMainAxisAlignmentSample(allExpandFlow)
                        }
                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FlowRowSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem(title = "FlowRow", allExpandFlow, padding = 20.dp) {
        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Red.copy(alpha = 0.5f))
        ) {
            listOf("数码", "汽车", "摄影", "舞蹈").forEach {
                Chip(onClick = { }) {
                    Text(text = it)
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun FlowRowSamplePreview() {
    FlowRowSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FlowRowFullSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem(title = "FlowRow（Full）", allExpandFlow, padding = 20.dp) {
        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Red.copy(alpha = 0.5f))
        ) {
            listOf("数码", "汽车", "摄影", "舞蹈", "二次元", "音乐", "科技", "健身", "游戏", "文学").forEach {
                Chip(onClick = { }) {
                    Text(text = it)
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun FlowRowFullSamplePreview() {
    FlowRowFullSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FlowRowMainAxisSizeSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem(title = "FlowRow（mainAxisSize）", allExpandFlow, padding = 20.dp) {
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
                    modifier = Modifier.background(Color.Red.copy(alpha = 0.5f)),
                    mainAxisSize = sizeMode
                ) {
                    listOf("数码", "汽车", "摄影", "舞蹈").forEach {
                        Chip(onClick = { }) {
                            Text(text = it)
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun FlowRowMainAxisSizeSamplePreview() {
    FlowRowMainAxisSizeSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FlowRowMainAxisAlignmentSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem(title = "FlowRow（mainAxisAlignment）", allExpandFlow, padding = 20.dp) {
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
                        .background(Color.Red.copy(alpha = 0.5f)),
                    mainAxisAlignment = alignment
                ) {
                    listOf("数码", "汽车", "摄影", "舞蹈", "二次元", "音乐", "科技", "健身", "游戏", "文学").forEach {
                        Chip(onClick = { }) {
                            Text(text = it)
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun FlowRowMainAxisAlignmentSamplePreview() {
    FlowRowMainAxisAlignmentSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FlowRowMainAxisSpacingSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem(title = "FlowRow（mainAxisSpacing）", allExpandFlow, padding = 20.dp) {
        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Red.copy(alpha = 0.5f)),
            mainAxisSpacing = 10.dp
        ) {
            listOf("数码", "汽车", "摄影", "舞蹈", "二次元", "音乐", "科技", "健身", "游戏", "文学").forEach {
                Chip(onClick = { }) {
                    Text(text = it)
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun FlowRowMainAxisSpacingSamplePreview() {
    FlowRowMainAxisSpacingSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FlowRowCrossAxisAlignmentSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem(title = "FlowRow（crossAxisAlignment）（无效）", allExpandFlow, padding = 20.dp) {
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
                        .background(Color.Red.copy(alpha = 0.5f)),
                    crossAxisAlignment = alignment  // todo 无效
                ) {
                    listOf("数码", "汽车", "摄影", "舞蹈").forEach {
                        Chip(onClick = { }) {
                            Text(text = it)
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun FlowRowCrossAxisAlignmentSamplePreview() {
    FlowRowCrossAxisAlignmentSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FlowRowCrossAxisSpacingSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem(title = "FlowRow（crossAxisSpacing）", allExpandFlow, padding = 20.dp) {
        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Red.copy(alpha = 0.5f)),
            crossAxisSpacing = 16.dp
        ) {
            listOf("数码", "汽车", "摄影", "舞蹈", "二次元", "音乐", "科技", "健身", "游戏", "文学").forEach {
                Chip(onClick = { }) {
                    Text(text = it)
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun FlowRowCrossAxisSpacingSamplePreview() {
    FlowRowCrossAxisSpacingSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FlowRowLastLineMainAxisAlignmentSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem(title = "FlowRow（lastLineMainAxisAlignment）", allExpandFlow, padding = 20.dp) {
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
                        .background(Color.Red.copy(alpha = 0.5f)),
                    lastLineMainAxisAlignment = alignment
                ) {
                    listOf("数码", "汽车", "摄影", "舞蹈", "二次元", "音乐", "科技", "健身", "游戏", "文学").forEach {
                        Chip(onClick = { }) {
                            Text(text = it)
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun FlowRowLastLineMainAxisAlignmentSamplePreview() {
    FlowRowLastLineMainAxisAlignmentSample(remember { MutableStateFlow(true) })
}