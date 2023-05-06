package com.github.panpf.android.compose.samples.ui.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem3
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
import com.github.panpf.android.compose.samples.ui.base.MyColor
import com.github.panpf.android.compose.samples.ui.customization.grid.VerticalGrid
import com.google.accompanist.flowlayout.FlowRow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class BoxFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "Box"
    }

    @Composable
    override fun DrawContent() {
        ExpandableLayout { allExpandFlow ->
            BoxSample(allExpandFlow)
            BoxContentAlignmentSample(allExpandFlow)
            BoxAlignSample(allExpandFlow)
            BoxMatchParentSizeSample(allExpandFlow)
        }
    }
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun BoxSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "Box", allExpandFlow, padding = 20.dp) {
        VerticalGrid(
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                    .padding(2.dp)
            ) {
                Text(
                    text = "",
                    modifier = Modifier
                        .size(maxWidth * 0.6f)
                        .background(MyColor.HalfBlue)
                )
            }

            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                    .padding(2.dp)
            ) {
                Text(
                    text = "",
                    modifier = Modifier
                        .size(maxWidth * 0.6f)
                        .background(MyColor.HalfBlue)
                )
                Text(
                    text = "",
                    modifier = Modifier
                        .size(maxWidth * 0.4f)
                        .background(MyColor.HalfGreen)
                )
            }

            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                    .padding(2.dp)
            ) {
                Text(
                    text = "",
                    modifier = Modifier
                        .size(maxWidth * 0.6f)
                        .background(MyColor.HalfBlue)
                )
                Text(
                    text = "",
                    modifier = Modifier
                        .size(maxWidth * 0.4f)
                        .background(MyColor.HalfGreen)
                )
                Text(
                    text = "",
                    modifier = Modifier
                        .size(maxWidth * 0.2f)
                        .background(MyColor.HalfRed)
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun BoxSamplePreview() {
    BoxSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun BoxContentAlignmentSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "Box（contentAlignment）", allExpandFlow, padding = 20.dp) {
        VerticalGrid(
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
        ) {
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
                    BoxWithConstraints(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1f)
                            .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                            .padding(2.dp),
                        contentAlignment = alignment
                    ) {
                        Text(
                            text = "",
                            modifier = Modifier
                                .size(maxWidth * 0.6f)
                                .background(MyColor.HalfBlue)
                        )
                        Text(
                            text = "",
                            modifier = Modifier
                                .size(maxWidth * 0.4f)
                                .background(MyColor.HalfGreen)
                        )
                        Text(
                            text = "",
                            modifier = Modifier
                                .size(maxWidth * 0.2f)
                                .background(MyColor.HalfRed)
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun BoxContentAlignmentSamplePreview() {
    BoxContentAlignmentSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun BoxAlignSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "Box（align）", allExpandFlow, padding = 20.dp) {
        Text(text = "蓝色和绿色块受 Box 的 contentAlignment 属性控制始终居中，红色块受自身 align 属性控制")
        Spacer(modifier = Modifier.size(10.dp))
        VerticalGrid(
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
        ) {
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
                    BoxWithConstraints(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1f)
                            .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                            .padding(2.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "",
                            modifier = Modifier
                                .size(maxWidth * 0.6f)
                                .background(MyColor.HalfBlue)
                        )
                        Text(
                            text = "",
                            modifier = Modifier
                                .size(maxWidth * 0.4f)
                                .background(MyColor.HalfGreen)
                        )
                        Text(
                            text = "",
                            modifier = Modifier
                                .size(maxWidth * 0.2f)
                                .background(MyColor.HalfRed)
                                .align(alignment)
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun BoxAlignSamplePreview() {
    BoxAlignSample(remember { MutableStateFlow(true) })
}


@Composable
private fun BoxMatchParentSizeSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "Box（matchParentSize）", allExpandFlow, padding = 20.dp) {
        Text(text = "Box 中一个 Text，Text 设置 matchParentSize。\nBox 设置宽高时 Text 充满 Box；\nBox 不设置宽高时 Box 不可见。\n因此 matchParentSize 不会影响 Box 的宽高")
        Spacer(modifier = Modifier.size(10.dp))
        FlowRow(mainAxisSpacing = 10.dp, crossAxisSpacing = 10.dp) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                    .padding(2.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "舞蹈",
                    color = Color.White,
                    modifier = Modifier
                        .matchParentSize()
                        .background(MyColor.HalfBlue)
                )
            }

            Box(
                modifier = Modifier
                    .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                    .padding(2.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "舞蹈",
                    color = Color.White,
                    modifier = Modifier
                        .matchParentSize()
                        .background(MyColor.HalfBlue)
                )
            }
        }

        Spacer(modifier = Modifier.size(40.dp))
        Text(text = "Box 中一个 Text，Text 设置 fillMaxSize。\nBox 设置宽高时 Text 充满 Box；\nBox 仅设置高时 Box 宽充满父布局。\n因此 fillMaxSize 会影响 Box 的宽高")
        FlowRow(mainAxisSpacing = 10.dp, crossAxisSpacing = 10.dp) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                    .padding(2.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "舞蹈",
                    color = Color.White,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MyColor.HalfBlue)
                )
            }
            Box(
                modifier = Modifier
                    .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                    .height(100.dp)
                    .padding(2.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "舞蹈",
                    color = Color.White,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MyColor.HalfBlue)
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun BoxMatchParentSizeSamplePreview() {
    BoxMatchParentSizeSample(remember { MutableStateFlow(true) })
}