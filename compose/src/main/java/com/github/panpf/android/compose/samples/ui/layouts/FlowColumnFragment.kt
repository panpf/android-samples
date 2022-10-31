package com.github.panpf.android.compose.samples.ui.layouts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Chip
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.ToolbarFragment
import com.github.panpf.android.compose.samples.ui.base.theme.MyTheme
import com.google.accompanist.flowlayout.FlowColumn
import com.google.accompanist.flowlayout.FlowCrossAxisAlignment
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.SizeMode
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FlowColumnFragment : ToolbarFragment() {

    override fun createView(
        toolbar: Toolbar,
        inflater: LayoutInflater,
        parent: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        toolbar.title = "FlowColumn"
        return ComposeView(inflater.context).apply {
            setContent {
                MyTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        ExpandableLayout { allExpandFlow ->
                            FlowColumnSample(allExpandFlow)
                            FlowColumnFullSample(allExpandFlow)
                            FlowColumnMainAxisSizeSample(allExpandFlow)
                            FlowColumnMainAxisAlignmentSample(allExpandFlow)
                            FlowColumnMainAxisSpacingSample(allExpandFlow)
                            FlowColumnCrossAxisAlignmentSample(allExpandFlow)
                            FlowColumnCrossAxisSpacingSample(allExpandFlow)
                            FlowColumnLastLineMainAxisAlignmentSample(allExpandFlow)
                        }
                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FlowColumnSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem(title = "FlowColumn", allExpandFlow, padding = 20.dp) {
        FlowColumn(
            modifier = Modifier
                .height(200.dp)
                .background(Color.Red.copy(alpha = 0.5f))
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
fun FlowColumnSamplePreview() {
    FlowColumnSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FlowColumnFullSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem(title = "FlowColumn（Full）", allExpandFlow, padding = 20.dp) {
        FlowColumn(
            modifier = Modifier
                .height(200.dp)
                .background(Color.Red.copy(alpha = 0.5f))
        ) {
            listOf("数\n码", "汽\n车", "摄\n影", "舞\n蹈", "音\n乐", "科\n技").forEach {
                Chip(onClick = { }) {
                    Text(text = it)
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun FlowColumnFullSamplePreview() {
    FlowColumnFullSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FlowColumnMainAxisSizeSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem(title = "FlowColumn（mainAxisSize）", allExpandFlow, padding = 20.dp) {
        Row(Modifier.height(240.dp)) {
            listOf(
                SizeMode.Wrap to "Wrap",
                SizeMode.Expand to "Expand",
            ).forEachIndexed { index, (sizeMode, name) ->
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
                        modifier = Modifier.background(Color.Red.copy(alpha = 0.5f)),
                        mainAxisSize = sizeMode
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
fun FlowColumnMainAxisSizeSamplePreview() {
    FlowColumnMainAxisSizeSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FlowColumnMainAxisAlignmentSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem(title = "FlowColumn（mainAxisAlignment）", allExpandFlow, padding = 20.dp) {
        FlowRow(mainAxisSpacing = 10.dp, crossAxisSpacing = 10.dp) {
            listOf(
                FlowMainAxisAlignment.Start to "Start",
                FlowMainAxisAlignment.Center to "Center",
                FlowMainAxisAlignment.End to "End",
                FlowMainAxisAlignment.SpaceBetween to "Space\nBetween",
                FlowMainAxisAlignment.SpaceAround to "Space\nAround",
                FlowMainAxisAlignment.SpaceEvenly to "Space\nEvenly",
            ).forEach { (alignment, name) ->
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
                            .background(Color.Red.copy(alpha = 0.5f)),
                        mainAxisAlignment = alignment
                    ) {
                        listOf(
                            "数\n码",
                            "汽\n车",
                            "摄\n影",
                        ).forEach {
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
fun FlowColumnMainAxisAlignmentSamplePreview() {
    FlowColumnMainAxisAlignmentSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FlowColumnMainAxisSpacingSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem(title = "FlowColumn（mainAxisSpacing）", allExpandFlow, padding = 20.dp) {
        FlowColumn(
            modifier = Modifier
                .height(200.dp)
                .background(Color.Red.copy(alpha = 0.5f)),
            mainAxisSpacing = 10.dp
        ) {
            listOf("数\n码", "汽\n车", "摄\n影", "舞\n蹈", "音\n乐").forEach {
                Chip(onClick = { }) {
                    Text(text = it)
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun FlowColumnMainAxisSpacingSamplePreview() {
    FlowColumnMainAxisSpacingSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FlowColumnCrossAxisAlignmentSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem(title = "FlowColumn（crossAxisAlignment）（无效）", allExpandFlow, padding = 20.dp) {
        FlowRow(mainAxisSpacing = 10.dp, crossAxisSpacing = 10.dp) {
            listOf(
                FlowCrossAxisAlignment.Start to "Start",
                FlowCrossAxisAlignment.Center to "Center",
                FlowCrossAxisAlignment.End to "End",
            ).forEach { (alignment, name) ->
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
                            .background(Color.Red.copy(alpha = 0.5f)),
                        crossAxisAlignment = alignment  // todo 无效
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
fun FlowColumnCrossAxisAlignmentSamplePreview() {
    FlowColumnCrossAxisAlignmentSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FlowColumnCrossAxisSpacingSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem(title = "FlowColumn（crossAxisSpacing）", allExpandFlow, padding = 20.dp) {
        FlowColumn(
            modifier = Modifier
                .height(200.dp)
                .background(Color.Red.copy(alpha = 0.5f)),
            crossAxisSpacing = 16.dp
        ) {
            listOf("数\n码", "汽\n车", "摄\n影", "舞\n蹈", "音\n乐").forEach {
                Chip(onClick = { }) {
                    Text(text = it)
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun FlowColumnCrossAxisSpacingSamplePreview() {
    FlowColumnCrossAxisSpacingSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FlowColumnLastLineMainAxisAlignmentSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem(
        title = "FlowColumn（lastLineMainAxisAlignment）",
        allExpandFlow,
        padding = 20.dp
    ) {
        FlowRow(mainAxisSpacing = 10.dp, crossAxisSpacing = 10.dp) {
            listOf(
                FlowMainAxisAlignment.Start to "Start",
                FlowMainAxisAlignment.Center to "Center",
                FlowMainAxisAlignment.End to "End",
                FlowMainAxisAlignment.SpaceBetween to "Space\nBetween",
                FlowMainAxisAlignment.SpaceAround to "Space\nAround",
                FlowMainAxisAlignment.SpaceEvenly to "Space\nEvenly",
            ).forEach { (alignment, name) ->
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
                            .background(Color.Red.copy(alpha = 0.5f)),
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
                        ).forEach {
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
fun FlowColumnLastLineMainAxisAlignmentSamplePreview() {
    FlowColumnLastLineMainAxisAlignmentSample(remember { MutableStateFlow(true) })
}