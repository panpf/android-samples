package com.github.panpf.android.compose.samples.ui.layouts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.ToolbarFragment
import com.github.panpf.android.compose.samples.ui.base.theme.MyTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class RowFragment : ToolbarFragment() {

    override fun createView(
        toolbar: Toolbar,
        inflater: LayoutInflater,
        parent: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        toolbar.title = "Row"
        return ComposeView(inflater.context).apply {
            setContent {
                MyTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        ExpandableLayout { allExpandFlow ->
                            RowSample(allExpandFlow)
                            RowFullSample(allExpandFlow)
                            RowHorizontalArrangementSample(allExpandFlow)
                            RowVerticalAlignmentSample(allExpandFlow)
                            RowWeightSample(allExpandFlow)
                            RowAlignSample(allExpandFlow)
                            // todo alignBy
                        }
                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RowSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem(title = "Row", allExpandFlow, padding = 20.dp) {
        Row(
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
fun RowSamplePreview() {
    RowSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RowFullSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem(title = "Row（Full）", allExpandFlow, padding = 20.dp) {
        Row(
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
fun RowFullSamplePreview() {
    RowFullSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RowHorizontalArrangementSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem(title = "Row（horizontalArrangement）", allExpandFlow, padding = 20.dp) {
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
                        .background(Color.Red.copy(alpha = 0.5f)),
                    horizontalArrangement = arrangement
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
fun RowHorizontalArrangementSamplePreview() {
    RowHorizontalArrangementSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RowVerticalAlignmentSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem(title = "Row（verticalAlignment）", allExpandFlow, padding = 20.dp) {
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
                        .background(Color.Red.copy(alpha = 0.5f)),
                    verticalAlignment = alignment
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
fun RowVerticalAlignmentSamplePreview() {
    RowVerticalAlignmentSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RowWeightSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem(title = "Row（weight）", allExpandFlow, padding = 20.dp) {
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
                        .background(Color.Red.copy(alpha = 0.5f))
                ) {
                    list.forEach { (tag, weight) ->
                        Chip(
                            onClick = { },
                            modifier = Modifier.let {
                                if (weight != null) it.weight(weight) else it
                            }
                        ) {
                            Text(text = tag)
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun RowWeightSamplePreview() {
    RowWeightSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RowAlignSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem(title = "Row（align）", allExpandFlow, padding = 20.dp) {
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
                        .background(Color.Red.copy(alpha = 0.5f))
                ) {
                    Chip(
                        onClick = { },
                    ) {
                        Text(text = "数码")
                    }
                    Chip(
                        onClick = { },
                        modifier = Modifier.align(alignment),
                    ) {
                        Text(text = "舞蹈")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun RowAlignSamplePreview() {
    RowAlignSample(remember { MutableStateFlow(true) })
}