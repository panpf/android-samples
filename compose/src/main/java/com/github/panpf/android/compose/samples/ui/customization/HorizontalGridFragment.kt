package com.github.panpf.android.compose.samples.ui.customization

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.panpf.android.compose.samples.R
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem3
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
import com.github.panpf.android.compose.samples.ui.customization.grid.HorizontalGrid
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class HorizontalGridFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "HorizontalGrid"
    }

    @Composable
    override fun DrawContent() {
        ExpandableLayout { allExpandFlow ->
            HorizontalGridSample(allExpandFlow)
            HorizontalGridRowsFixedSample(allExpandFlow)
            HorizontalGridRowsAdaptiveSample(allExpandFlow)
            HorizontalGridContentPaddingSample(allExpandFlow)
            HorizontalGridContentPaddingWithArrangementSample(allExpandFlow)
            HorizontalGridArrangementSample(allExpandFlow)
            HorizontalGridArrangementWithRowsSample(allExpandFlow)
            HorizontalGridReverseLayoutSample(allExpandFlow)
            // todo 不同宽度的 item
            // todo 宽度不充满网格的 item
            // todo scrollable
            // todo 固定宽高
        }
    }
}


@Composable
fun HorizontalGridSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "HorizontalGrid", allExpandFlow, padding = 20.dp) {
        val colorScheme = MaterialTheme.colorScheme
        HorizontalGrid(
            rows = GridCells.Fixed(3),
            modifier = Modifier
                .height(120.dp)
                .background(colorScheme.primaryContainer)
        ) {
            repeat(7) {
                Text(
                    text = "Item ${it + 1}",
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(100.dp)
                        .border(width = 1.dp, color = colorScheme.tertiary)
                        .padding(10.dp)
                )
            }
        }

        Spacer(modifier = Modifier.size(20.dp))
        HorizontalGrid(
            rows = GridCells.Fixed(3),
            modifier = Modifier
                .height(120.dp)
                .background(colorScheme.primaryContainer)
        ) {
            repeat(17) {
                Text(
                    text = "Item ${it + 1}",
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(100.dp)
                        .border(width = 1.dp, color = colorScheme.tertiary)
                        .padding(10.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun HorizontalGridSamplePreview() {
    HorizontalGridSample(remember { MutableStateFlow(true) })
}


@Composable
fun HorizontalGridRowsFixedSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "HorizontalGrid（rows - Fixed）", allExpandFlow, padding = 20.dp) {
        val colorScheme = MaterialTheme.colorScheme
        Column(Modifier.fillMaxWidth()) {
            listOf(3, 2, 1).forEachIndexed { index, rows ->
                if (index > 0) {
                    Spacer(modifier = Modifier.size(20.dp))
                }
                Text(text = "Rows=Fixed($rows)")
                Spacer(modifier = Modifier.size(10.dp))
                HorizontalGrid(
                    rows = GridCells.Fixed(rows),
                    modifier = Modifier
                        .height(120.dp)
                        .background(colorScheme.primaryContainer)
                ) {
                    repeat(7) {
                        Text(
                            text = "Item ${it + 1}",
                            modifier = Modifier
                                .fillMaxHeight()
                                .width(100.dp)
                                .border(width = 1.dp, color = colorScheme.tertiary)
                                .padding(10.dp)
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun HorizontalGridRowsFixedSamplePreview() {
    HorizontalGridRowsFixedSample(remember { MutableStateFlow(true) })
}


@Composable
fun HorizontalGridRowsAdaptiveSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "HorizontalGrid（rows - Adaptive）", allExpandFlow, padding = 20.dp) {
        val colorScheme = MaterialTheme.colorScheme
        Column(Modifier.fillMaxWidth()) {
            Text(text = "Rows=Adaptive(100.dp)")
            Spacer(modifier = Modifier.size(10.dp))
            var fillMaxHeightFraction by remember { mutableStateOf(0.6f) }
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .border(width = 1.dp, color = colorScheme.tertiary)
            ) {
                HorizontalGrid(
                    rows = GridCells.Adaptive(40.dp),
                    modifier = Modifier
                        .fillMaxHeight(fraction = fillMaxHeightFraction)
                        .background(colorScheme.primaryContainer)
                ) {
                    repeat(17) {
                        Text(
                            text = "Item ${it + 1}",
                            modifier = Modifier
                                .fillMaxHeight()
                                .width(100.dp)
                                .border(width = 1.dp, color = colorScheme.tertiary)
                                .padding(10.dp)
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row {
                FilledIconButton(
                    modifier = Modifier.weight(1f),
                    onClick = {
                        fillMaxHeightFraction = (fillMaxHeightFraction - 0.1f).coerceAtLeast(0.2f)
                    }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_subtract),
                        contentDescription = "subtract"
                    )
                }
                Spacer(modifier = Modifier.size(20.dp))
                Text(
                    text = "fraction=$fillMaxHeightFraction",
                    modifier = Modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.size(20.dp))
                FilledIconButton(
                    modifier = Modifier.weight(1f),
                    onClick = {
                        fillMaxHeightFraction = (fillMaxHeightFraction + 0.1f).coerceAtMost(1f)
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = "add"
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun HorizontalGridRowsAdaptiveSamplePreview() {
    HorizontalGridRowsAdaptiveSample(remember { MutableStateFlow(true) })
}


@Composable
fun HorizontalGridContentPaddingSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "HorizontalGrid（PaddingValues）", allExpandFlow, padding = 20.dp) {
        val colorScheme = MaterialTheme.colorScheme
        Column(Modifier.fillMaxWidth()) {
            Text(text = "PaddingValues(20.dp)")
            Spacer(modifier = Modifier.size(10.dp))
            HorizontalGrid(
                rows = GridCells.Fixed(3),
                modifier = Modifier
                    .height(160.dp)
                    .background(colorScheme.primaryContainer),
                contentPadding = PaddingValues(20.dp)
            ) {
                repeat(7) {
                    Text(
                        text = "Item ${it + 1}",
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(100.dp)
                            .border(width = 1.dp, color = colorScheme.tertiary)
                            .padding(10.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.size(20.dp))
            Text(text = "PaddingValues(horizontal=20.dp, vertical=10.dp)")
            Spacer(modifier = Modifier.size(10.dp))
            HorizontalGrid(
                rows = GridCells.Fixed(3),
                modifier = Modifier
                    .height(160.dp)
                    .background(colorScheme.primaryContainer),
                contentPadding = PaddingValues(horizontal = 20.dp, vertical = 10.dp)
            ) {
                repeat(7) {
                    Text(
                        text = "Item ${it + 1}",
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(100.dp)
                            .border(width = 1.dp, color = colorScheme.tertiary)
                            .padding(10.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.size(20.dp))
            Text(text = "PaddingValues(start=10.dp, top=20.dp, end=40.dp, bottom=60.dp)")
            Spacer(modifier = Modifier.size(10.dp))
            HorizontalGrid(
                rows = GridCells.Fixed(3),
                modifier = Modifier
                    .height(200.dp)
                    .background(colorScheme.primaryContainer),
                contentPadding = PaddingValues(
                    start = 10.dp,
                    top = 20.dp,
                    end = 40.dp,
                    bottom = 60.dp
                )
            ) {
                repeat(7) {
                    Text(
                        text = "Item ${it + 1}",
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(100.dp)
                            .border(width = 1.dp, color = colorScheme.tertiary)
                            .padding(10.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun HorizontalGridContentPaddingSamplePreview() {
    HorizontalGridContentPaddingSample(remember { MutableStateFlow(true) })
}


@Composable
fun HorizontalGridContentPaddingWithArrangementSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(
        title = "HorizontalGrid（PaddingValues + Arrangement）",
        allExpandFlow,
        padding = 20.dp
    ) {
        val colorScheme = MaterialTheme.colorScheme
        Column(Modifier.fillMaxWidth()) {
            Text(text = "PaddingValues + verticalArrangement")
            Spacer(modifier = Modifier.size(10.dp))
            HorizontalGrid(
                rows = GridCells.Fixed(3),
                modifier = Modifier
                    .height(140.dp)
                    .background(colorScheme.primaryContainer),
                contentPadding = PaddingValues(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                repeat(7) {
                    Text(
                        text = "Item ${it + 1}",
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(100.dp)
                            .border(width = 1.dp, color = colorScheme.tertiary)
                            .padding(10.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.size(20.dp))
            Text(text = "PaddingValues + verticalArrangement")
            Spacer(modifier = Modifier.size(10.dp))
            HorizontalGrid(
                rows = GridCells.Fixed(3),
                modifier = Modifier
                    .height(160.dp)
                    .background(colorScheme.primaryContainer),
                contentPadding = PaddingValues(10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                repeat(7) {
                    Text(
                        text = "Item ${it + 1}",
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(100.dp)
                            .border(width = 1.dp, color = colorScheme.tertiary)
                            .padding(10.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.size(20.dp))
            Text(text = "PaddingValues + verticalArrangement + horizontalArrangement")
            Spacer(modifier = Modifier.size(10.dp))
            HorizontalGrid(
                rows = GridCells.Fixed(3),
                modifier = Modifier
                    .height(160.dp)
                    .background(colorScheme.primaryContainer),
                contentPadding = PaddingValues(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                repeat(7) {
                    Text(
                        text = "Item ${it + 1}",
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(100.dp)
                            .border(width = 1.dp, color = colorScheme.tertiary)
                            .padding(10.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun HorizontalGridContentPaddingWithArrangementSamplePreview() {
    HorizontalGridContentPaddingWithArrangementSample(remember { MutableStateFlow(true) })
}


@Composable
fun HorizontalGridArrangementSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(
        title = "HorizontalGrid（Arrangement）",
        allExpandFlow,
        padding = 20.dp
    ) {
        val colorScheme = MaterialTheme.colorScheme
        Column(Modifier.fillMaxWidth()) {
            Text(text = "verticalArrangement=spacedBy(10.dp)")
            Spacer(modifier = Modifier.size(10.dp))
            HorizontalGrid(
                rows = GridCells.Fixed(3),
                modifier = Modifier
                    .height(120.dp)
                    .background(colorScheme.primaryContainer),
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                repeat(7) {
                    Text(
                        text = "Item ${it + 1}",
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(100.dp)
                            .border(width = 1.dp, color = colorScheme.tertiary)
                            .padding(10.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.size(20.dp))
            Text(text = "horizontalArrangement=spacedBy(10.dp)")
            Spacer(modifier = Modifier.size(10.dp))
            HorizontalGrid(
                rows = GridCells.Fixed(3),
                modifier = Modifier
                    .height(140.dp)
                    .background(colorScheme.primaryContainer),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                repeat(7) {
                    Text(
                        text = "Item ${it + 1}",
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(100.dp)
                            .border(width = 1.dp, color = colorScheme.tertiary)
                            .padding(10.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.size(20.dp))
            Text(text = "verticalArrangement=spacedBy(10.dp)\nhorizontalArrangement=spacedBy(10.dp)")
            Spacer(modifier = Modifier.size(10.dp))
            HorizontalGrid(
                rows = GridCells.Fixed(3),
                modifier = Modifier
                    .height(140.dp)
                    .background(colorScheme.primaryContainer),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                repeat(7) {
                    Text(
                        text = "Item ${it + 1}",
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(100.dp)
                            .border(width = 1.dp, color = colorScheme.tertiary)
                            .padding(10.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun HorizontalGridArrangementSamplePreview() {
    HorizontalGridArrangementSample(remember { MutableStateFlow(true) })
}


@Composable
fun HorizontalGridArrangementWithRowsSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(
        title = "HorizontalGrid（Arrangement + Rows）",
        allExpandFlow,
        padding = 20.dp
    ) {
        val colorScheme = MaterialTheme.colorScheme
        Column(Modifier.fillMaxWidth()) {
            listOf(3, 2, 1).forEachIndexed { index, rows ->
                if (index > 0) {
                    Spacer(modifier = Modifier.size(20.dp))
                }
                Text(text = "Arrangement + Fixed($rows)")
                Spacer(modifier = Modifier.size(10.dp))
                HorizontalGrid(
                    rows = GridCells.Fixed(rows),
                    modifier = Modifier
                        .height(140.dp)
                        .background(colorScheme.primaryContainer),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    repeat(7) {
                        Text(
                            text = "Item ${it + 1}",
                            modifier = Modifier
                                .fillMaxHeight()
                                .width(100.dp)
                                .border(width = 1.dp, color = colorScheme.tertiary)
                                .padding(10.dp)
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun HorizontalGridArrangementWithRowsSamplePreview() {
    HorizontalGridArrangementWithRowsSample(remember { MutableStateFlow(true) })
}


@Composable
fun HorizontalGridReverseLayoutSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "HorizontalGrid（reverseLayout）", allExpandFlow, padding = 20.dp) {
        val colorScheme = MaterialTheme.colorScheme
        Column(Modifier.fillMaxWidth()) {
            listOf(3, 2, 1).forEachIndexed { index, rows ->
                if (index > 0) {
                    Spacer(modifier = Modifier.size(20.dp))
                }
                Text(text = "Arrangement + Fixed($rows)")
                Spacer(modifier = Modifier.size(10.dp))
                HorizontalGrid(
                    rows = GridCells.Fixed(rows),
                    modifier = Modifier
                        .height(120.dp)
                        .background(colorScheme.primaryContainer),
                    reverseLayout = true,
                ) {
                    repeat(7) {
                        Text(
                            text = "Item ${it + 1}",
                            modifier = Modifier
                                .fillMaxHeight()
                                .width(100.dp)
                                .border(width = 1.dp, color = colorScheme.tertiary)
                                .padding(10.dp)
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun HorizontalGridReverseLayoutSamplePreview() {
    HorizontalGridReverseLayoutSample(remember { MutableStateFlow(true) })
}