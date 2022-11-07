package com.github.panpf.android.compose.samples.ui.basic

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import com.google.accompanist.flowlayout.FlowRow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class BoxFragment : ToolbarFragment() {

    override fun createView(
        toolbar: Toolbar,
        inflater: LayoutInflater,
        parent: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        toolbar.title = "Box"
        return ComposeView(inflater.context).apply {
            setContent {
                MyTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        ExpandableLayout { allExpandFlow ->
                            BoxSample(allExpandFlow)
                            BoxContentAlignmentSample(allExpandFlow)
                            BoxAlignSample(allExpandFlow)
                            BoxMatchParentSizeSample(allExpandFlow)
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun BoxSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem(title = "Box", allExpandFlow, padding = 20.dp) {
        FlowRow(mainAxisSpacing = 10.dp, crossAxisSpacing = 10.dp) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .border(2.dp, Color.Red)
                    .padding(2.dp)
            ) {
                Text(
                    text = "",
                    modifier = Modifier
                        .size(60.dp)
                        .background(Color.Blue.copy(alpha = 0.5f))
                )
            }
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .border(2.dp, Color.Red)
                    .padding(2.dp)
            ) {
                Text(
                    text = "",
                    modifier = Modifier
                        .size(60.dp)
                        .background(Color.Blue.copy(alpha = 0.5f))
                )
                Text(
                    text = "",
                    modifier = Modifier
                        .size(40.dp)
                        .background(Color.Green.copy(alpha = 0.5f))
                )
            }
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .border(2.dp, Color.Red)
                    .padding(2.dp)
            ) {
                Text(
                    text = "",
                    modifier = Modifier
                        .size(60.dp)
                        .background(Color.Blue.copy(alpha = 0.5f))
                )
                Text(
                    text = "",
                    modifier = Modifier
                        .size(40.dp)
                        .background(Color.Green.copy(alpha = 0.5f))
                )
                Text(
                    text = "",
                    modifier = Modifier
                        .size(20.dp)
                        .background(Color.Magenta.copy(alpha = 0.5f))
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun BoxSamplePreview() {
    BoxSample(remember { MutableStateFlow(true) })
}


@Composable
fun BoxContentAlignmentSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem(title = "Box（contentAlignment）", allExpandFlow, padding = 20.dp) {
        FlowRow(mainAxisSpacing = 10.dp, crossAxisSpacing = 10.dp) {
            listOf(
                Alignment.TopStart to "TopStart",
                Alignment.TopCenter to "TopCenter",
                Alignment.TopEnd to "TopEnd",
                Alignment.CenterStart to "CenterStart",
                Alignment.Center to "Center",
                Alignment.CenterEnd to "CenterEnd",
                Alignment.BottomStart to "BottomStart",
                Alignment.BottomCenter to "BottomCenter",
                Alignment.BottomEnd to "BottomEnd",
            ).forEach { (alignment, name) ->
                Column {
                    Text(text = name, modifier = Modifier.align(Alignment.CenterHorizontally))
                    Box(
                        modifier = Modifier
                            .size(100.dp)
                            .border(2.dp, Color.Red)
                            .padding(2.dp),
                        contentAlignment = alignment
                    ) {
                        Text(
                            text = "",
                            modifier = Modifier
                                .size(60.dp)
                                .background(Color.Blue.copy(alpha = 0.5f))
                        )
                        Text(
                            text = "",
                            modifier = Modifier
                                .size(40.dp)
                                .background(Color.Green.copy(alpha = 0.5f))
                        )
                        Text(
                            text = "",
                            modifier = Modifier
                                .size(20.dp)
                                .background(Color.Magenta.copy(alpha = 0.5f))
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun BoxContentAlignmentSamplePreview() {
    BoxContentAlignmentSample(remember { MutableStateFlow(true) })
}


@Composable
fun BoxAlignSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem(title = "Box（align）", allExpandFlow, padding = 20.dp) {
        Column {
            Text(text = "蓝色和绿色块受 Box 的 contentAlignment 属性控制始终居中，红色块受自身 align 属性控制")
            Spacer(modifier = Modifier.size(10.dp))
            FlowRow(mainAxisSpacing = 10.dp, crossAxisSpacing = 10.dp) {
                listOf(
                    Alignment.TopStart to "TopStart",
                    Alignment.TopCenter to "TopCenter",
                    Alignment.TopEnd to "TopEnd",
                    Alignment.CenterStart to "CenterStart",
                    Alignment.Center to "Center",
                    Alignment.CenterEnd to "CenterEnd",
                    Alignment.BottomStart to "BottomStart",
                    Alignment.BottomCenter to "BottomCenter",
                    Alignment.BottomEnd to "BottomEnd",
                ).forEach { (alignment, name) ->
                    Column {
                        Text(text = name, modifier = Modifier.align(Alignment.CenterHorizontally))
                        Box(
                            modifier = Modifier
                                .size(100.dp)
                                .border(2.dp, Color.Red)
                                .padding(2.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "",
                                modifier = Modifier
                                    .size(60.dp)
                                    .background(Color.Blue.copy(alpha = 0.5f))
                            )
                            Text(
                                text = "",
                                modifier = Modifier
                                    .size(40.dp)
                                    .background(Color.Green.copy(alpha = 0.5f))
                            )
                            Text(
                                text = "",
                                modifier = Modifier
                                    .size(20.dp)
                                    .background(Color.Magenta.copy(alpha = 0.5f))
                                    .align(alignment)
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
fun BoxAlignSamplePreview() {
    BoxAlignSample(remember { MutableStateFlow(true) })
}


@Composable
fun BoxMatchParentSizeSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem(title = "Box（matchParentSize）", allExpandFlow, padding = 20.dp) {
        Column {
            Text(text = "Box 中一个 Text，Text 设置 matchParentSize。Box 设置宽高时 Text 充满 Box；Box 不设置宽高时 Box 不可见，因此 matchParentSize 不会影响 Box 的宽高")
            Spacer(modifier = Modifier.size(10.dp))
            FlowRow(mainAxisSpacing = 10.dp, crossAxisSpacing = 10.dp) {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .border(2.dp, Color.Red)
                        .padding(2.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "舞蹈",
                        modifier = Modifier
                            .matchParentSize()
                            .background(Color.Blue.copy(alpha = 0.5f))
                    )
                }

                Box(
                    modifier = Modifier
                        .border(2.dp, Color.Red)
                        .padding(2.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "舞蹈",
                        modifier = Modifier
                            .matchParentSize()
                            .background(Color.Blue.copy(alpha = 0.5f))
                    )
                }
            }

            Spacer(modifier = Modifier.size(40.dp))
            Text(text = "Box 中一个 Text，Text 设置 fillMaxSize。Box 设置宽高时 Text 充满 Box；Box 仅设置高时 Box 宽充满父布局，因此 fillMaxSize 会影响 Box 的宽高")
            FlowRow(mainAxisSpacing = 10.dp, crossAxisSpacing = 10.dp) {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .border(2.dp, Color.Red)
                        .padding(2.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "舞蹈",
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Blue.copy(alpha = 0.5f))
                    )
                }
                Box(
                    modifier = Modifier
                        .border(2.dp, Color.Red)
                        .height(100.dp)
                        .padding(2.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "舞蹈",
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Blue.copy(alpha = 0.5f))
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun BoxMatchParentSizeSamplePreview() {
    BoxMatchParentSizeSample(remember { MutableStateFlow(true) })
}