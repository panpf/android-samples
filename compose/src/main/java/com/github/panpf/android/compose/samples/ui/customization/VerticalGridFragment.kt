package com.github.panpf.android.compose.samples.ui.customization

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.panpf.android.compose.samples.R
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem3
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
import com.github.panpf.android.compose.samples.ui.customization.grid.VerticalGrid
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class VerticalGridFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "VerticalGrid"
    }

    @Composable
    override fun DrawContent() {
        ExpandableLayout { allExpandFlow ->
            VerticalGridSample(allExpandFlow)
            VerticalGridRowsFixedSample(allExpandFlow)
            VerticalGridRowsAdaptiveSample(allExpandFlow)
            VerticalGridContentPaddingSample(allExpandFlow)
            VerticalGridContentPaddingWithArrangementSample(allExpandFlow)
            VerticalGridArrangementSample(allExpandFlow)
            VerticalGridArrangementWithRowsSample(allExpandFlow)
            VerticalGridReverseLayoutSample(allExpandFlow)
            VerticalGridNotNeatItemSample(allExpandFlow)
            VerticalGridSizeSample(allExpandFlow)
            // todo 宽度不充满网格的 item
        }
    }
}


@Composable
fun VerticalGridSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "VerticalGrid", allExpandFlow, padding = 20.dp) {
        val colorScheme = MaterialTheme.colorScheme
        VerticalGrid(
            rows = GridCells.Fixed(3),
            modifier = Modifier.background(colorScheme.primaryContainer)
        ) {
            repeat(7) {
                Text(
                    text = "Item ${it + 1}",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                        .border(width = 1.dp, color = colorScheme.tertiary)
                        .padding(10.dp)
                )
            }
        }

        Spacer(modifier = Modifier.size(20.dp))
        VerticalGrid(
            rows = GridCells.Fixed(3),
            modifier = Modifier.background(colorScheme.primaryContainer)
        ) {
            repeat(17) {
                Text(
                    text = "Item ${it + 1}",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                        .border(width = 1.dp, color = colorScheme.tertiary)
                        .padding(10.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun VerticalGridSamplePreview() {
    VerticalGridSample(remember { MutableStateFlow(true) })
}


@Composable
fun VerticalGridRowsFixedSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "VerticalGrid（rows - Fixed）", allExpandFlow, padding = 20.dp) {
        val colorScheme = MaterialTheme.colorScheme
        Column(Modifier.fillMaxWidth()) {
            listOf(3, 2, 1).forEachIndexed { index, rows ->
                if (index > 0) {
                    Spacer(modifier = Modifier.size(20.dp))
                }
                Text(text = "Rows=Fixed($rows)")
                Spacer(modifier = Modifier.size(10.dp))
                VerticalGrid(
                    rows = GridCells.Fixed(rows),
                    modifier = Modifier.background(colorScheme.primaryContainer)
                ) {
                    repeat(7) {
                        Text(
                            text = "Item ${it + 1}",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(40.dp)
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
private fun VerticalGridRowsFixedSamplePreview() {
    VerticalGridRowsFixedSample(remember { MutableStateFlow(true) })
}


@Composable
fun VerticalGridRowsAdaptiveSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "VerticalGrid（rows - Adaptive）", allExpandFlow, padding = 20.dp) {
        val colorScheme = MaterialTheme.colorScheme
        Column(Modifier.fillMaxWidth()) {
            Text(text = "Rows=Adaptive(100.dp)")
            Spacer(modifier = Modifier.size(10.dp))
            var fillMaxWidthFraction by remember { mutableStateOf(0.6f) }
            VerticalGrid(
                rows = GridCells.Adaptive(100.dp),
                modifier = Modifier
                    .fillMaxWidth(fraction = fillMaxWidthFraction)
                    .background(colorScheme.primaryContainer)
            ) {
                repeat(7) {
                    Text(
                        text = "Item ${it + 1}",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(40.dp)
                            .border(width = 1.dp, color = colorScheme.tertiary)
                            .padding(10.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row {
                FilledIconButton(
                    modifier = Modifier.weight(1f),
                    onClick = {
                        fillMaxWidthFraction = (fillMaxWidthFraction - 0.1f).coerceAtLeast(0.2f)
                    }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_subtract),
                        contentDescription = "subtract"
                    )
                }
                Spacer(modifier = Modifier.size(20.dp))
                Text(
                    text = "fraction=$fillMaxWidthFraction",
                    modifier = Modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.size(20.dp))
                FilledIconButton(
                    modifier = Modifier.weight(1f),
                    onClick = {
                        fillMaxWidthFraction = (fillMaxWidthFraction + 0.1f).coerceAtMost(1f)
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
private fun VerticalGridRowsAdaptiveSamplePreview() {
    VerticalGridRowsAdaptiveSample(remember { MutableStateFlow(true) })
}


@Composable
fun VerticalGridContentPaddingSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "VerticalGrid（PaddingValues）", allExpandFlow, padding = 20.dp) {
        val colorScheme = MaterialTheme.colorScheme
        Column(Modifier.fillMaxWidth()) {
            Text(text = "PaddingValues(20.dp)")
            Spacer(modifier = Modifier.size(10.dp))
            VerticalGrid(
                rows = GridCells.Fixed(3),
                modifier = Modifier.background(colorScheme.primaryContainer),
                contentPadding = PaddingValues(20.dp)
            ) {
                repeat(7) {
                    Text(
                        text = "Item ${it + 1}",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(40.dp)
                            .border(width = 1.dp, color = colorScheme.tertiary)
                            .padding(10.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.size(20.dp))
            Text(text = "PaddingValues(horizontal=20.dp, vertical=10.dp)")
            Spacer(modifier = Modifier.size(10.dp))
            VerticalGrid(
                rows = GridCells.Fixed(3),
                modifier = Modifier.background(colorScheme.primaryContainer),
                contentPadding = PaddingValues(horizontal = 20.dp, vertical = 10.dp)
            ) {
                repeat(7) {
                    Text(
                        text = "Item ${it + 1}",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(40.dp)
                            .border(width = 1.dp, color = colorScheme.tertiary)
                            .padding(10.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.size(20.dp))
            Text(text = "PaddingValues(start=10.dp, top=20.dp, end=40.dp, bottom=60.dp)")
            Spacer(modifier = Modifier.size(10.dp))
            VerticalGrid(
                rows = GridCells.Fixed(3),
                modifier = Modifier.background(colorScheme.primaryContainer),
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
                            .fillMaxWidth()
                            .height(40.dp)
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
private fun VerticalGridContentPaddingSamplePreview() {
    VerticalGridContentPaddingSample(remember { MutableStateFlow(true) })
}


@Composable
fun VerticalGridContentPaddingWithArrangementSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(
        title = "VerticalGrid（PaddingValues + Arrangement）",
        allExpandFlow,
        padding = 20.dp
    ) {
        val colorScheme = MaterialTheme.colorScheme
        Column(Modifier.fillMaxWidth()) {
            Text(text = "PaddingValues + verticalArrangement")
            Spacer(modifier = Modifier.size(10.dp))
            VerticalGrid(
                rows = GridCells.Fixed(3),
                modifier = Modifier.background(colorScheme.primaryContainer),
                contentPadding = PaddingValues(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                repeat(7) {
                    Text(
                        text = "Item ${it + 1}",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(40.dp)
                            .border(width = 1.dp, color = colorScheme.tertiary)
                            .padding(10.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.size(20.dp))
            Text(text = "PaddingValues + verticalArrangement")
            Spacer(modifier = Modifier.size(10.dp))
            VerticalGrid(
                rows = GridCells.Fixed(3),
                modifier = Modifier.background(colorScheme.primaryContainer),
                contentPadding = PaddingValues(10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                repeat(7) {
                    Text(
                        text = "Item ${it + 1}",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(40.dp)
                            .border(width = 1.dp, color = colorScheme.tertiary)
                            .padding(10.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.size(20.dp))
            Text(text = "PaddingValues + verticalArrangement + horizontalArrangement")
            Spacer(modifier = Modifier.size(10.dp))
            VerticalGrid(
                rows = GridCells.Fixed(3),
                modifier = Modifier.background(colorScheme.primaryContainer),
                contentPadding = PaddingValues(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                repeat(7) {
                    Text(
                        text = "Item ${it + 1}",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(40.dp)
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
private fun VerticalGridContentPaddingWithArrangementSamplePreview() {
    VerticalGridContentPaddingWithArrangementSample(remember { MutableStateFlow(true) })
}


@Composable
fun VerticalGridArrangementSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(
        title = "VerticalGrid（Arrangement）",
        allExpandFlow,
        padding = 20.dp
    ) {
        val colorScheme = MaterialTheme.colorScheme
        Column(Modifier.fillMaxWidth()) {
            Text(text = "verticalArrangement=spacedBy(10.dp)")
            Spacer(modifier = Modifier.size(10.dp))
            VerticalGrid(
                rows = GridCells.Fixed(3),
                modifier = Modifier.background(colorScheme.primaryContainer),
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                repeat(7) {
                    Text(
                        text = "Item ${it + 1}",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(40.dp)
                            .border(width = 1.dp, color = colorScheme.tertiary)
                            .padding(10.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.size(20.dp))
            Text(text = "horizontalArrangement=spacedBy(10.dp)")
            Spacer(modifier = Modifier.size(10.dp))
            VerticalGrid(
                rows = GridCells.Fixed(3),
                modifier = Modifier.background(colorScheme.primaryContainer),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                repeat(7) {
                    Text(
                        text = "Item ${it + 1}",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(40.dp)
                            .border(width = 1.dp, color = colorScheme.tertiary)
                            .padding(10.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.size(20.dp))
            Text(text = "verticalArrangement=spacedBy(10.dp)\nhorizontalArrangement=spacedBy(10.dp)")
            Spacer(modifier = Modifier.size(10.dp))
            VerticalGrid(
                rows = GridCells.Fixed(3),
                modifier = Modifier.background(colorScheme.primaryContainer),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                repeat(7) {
                    Text(
                        text = "Item ${it + 1}",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(40.dp)
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
private fun VerticalGridArrangementSamplePreview() {
    VerticalGridArrangementSample(remember { MutableStateFlow(true) })
}


@Composable
fun VerticalGridArrangementWithRowsSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(
        title = "VerticalGrid（Arrangement + Rows）",
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
                VerticalGrid(
                    rows = GridCells.Fixed(rows),
                    modifier = Modifier.background(colorScheme.primaryContainer),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    repeat(7) {
                        Text(
                            text = "Item ${it + 1}",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(40.dp)
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
private fun VerticalGridArrangementWithRowsSamplePreview() {
    VerticalGridArrangementWithRowsSample(remember { MutableStateFlow(true) })
}


@Composable
fun VerticalGridReverseLayoutSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "VerticalGrid（reverseLayout）", allExpandFlow, padding = 20.dp) {
        val colorScheme = MaterialTheme.colorScheme
        Column(Modifier.fillMaxWidth()) {
            listOf(3, 2, 1).forEachIndexed { index, rows ->
                if (index > 0) {
                    Spacer(modifier = Modifier.size(20.dp))
                }
                Text(text = "Arrangement + Fixed($rows)")
                Spacer(modifier = Modifier.size(10.dp))
                VerticalGrid(
                    rows = GridCells.Fixed(rows),
                    modifier = Modifier.background(colorScheme.primaryContainer),
                    reverseLayout = true,
                ) {
                    repeat(7) {
                        Text(
                            text = "Item ${it + 1}",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(40.dp)
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
private fun VerticalGridReverseLayoutSamplePreview() {
    VerticalGridReverseLayoutSample(remember { MutableStateFlow(true) })
}


@Composable
fun VerticalGridNotNeatItemSample(allExpandFlow: Flow<Boolean>) {
    val itemHeights = remember { listOf(40.dp, 50.dp, 60.dp) }
    ExpandableItem3(title = "VerticalGrid（NotNeatItem）", allExpandFlow, padding = 20.dp) {
        val colorScheme = MaterialTheme.colorScheme
        Column(Modifier.fillMaxWidth()) {
            listOf(3, 2, 1).forEachIndexed { index, rows ->
                if (index > 0) {
                    Spacer(modifier = Modifier.size(20.dp))
                }
                Text(text = "Arrangement + Fixed($rows)")
                Spacer(modifier = Modifier.size(10.dp))
                VerticalGrid(
                    rows = GridCells.Fixed(rows),
                    modifier = Modifier.background(colorScheme.primaryContainer),
                ) {
                    repeat(7) {
                        Text(
                            text = "Item ${it + 1}",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(itemHeights[it % itemHeights.size])
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
private fun VerticalGridNotNeatItemSamplePreview() {
    VerticalGridNotNeatItemSample(remember { MutableStateFlow(true) })
}


@Composable
fun VerticalGridSizeSample(allExpandFlow: Flow<Boolean>) {
    val colorScheme = MaterialTheme.colorScheme
    ExpandableItem3(title = "VerticalGrid（Size）", allExpandFlow, padding = 20.dp) {
        Text(
            text = "width=Wrap, height=Wrap",
            fontWeight = FontWeight.Bold, fontSize = 20.sp
        )

        Spacer(modifier = Modifier.size(10.dp))
        Text(text = "Low number of items")
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
                .border(width = 1.dp, color = colorScheme.inversePrimary)
                .padding(1.dp)
        ) {
            VerticalGrid(
                rows = GridCells.Fixed(3),
                modifier = Modifier.background(colorScheme.primaryContainer),
            ) {
                repeat(7) {
                    Text(
                        text = "Item ${it + 1}",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(40.dp)
                            .border(width = 1.dp, color = colorScheme.tertiary)
                            .padding(10.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.size(10.dp))
        Text(text = "Large number of items")
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
                .border(width = 1.dp, color = colorScheme.inversePrimary)
                .padding(1.dp)
        ) {
            VerticalGrid(
                rows = GridCells.Fixed(3),
                modifier = Modifier.background(colorScheme.primaryContainer),
            ) {
                repeat(13) {
                    Text(
                        text = "Item ${it + 1}",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(40.dp)
                            .border(width = 1.dp, color = colorScheme.tertiary)
                            .padding(10.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.size(60.dp))
        Text(text = "Large number of items and scrollable vertical")
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
                .border(width = 1.dp, color = colorScheme.inversePrimary)
                .padding(1.dp)
        ) {
            VerticalGrid(
                rows = GridCells.Fixed(3),
                modifier = Modifier
                    .background(colorScheme.primaryContainer)
                    .verticalScroll(rememberScrollState()),
            ) {
                repeat(13) {
                    Text(
                        text = "Item ${it + 1}",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(40.dp)
                            .border(width = 1.dp, color = colorScheme.tertiary)
                            .padding(10.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.size(20.dp))
        Text(
            text = "width=200.dp, height=120.dp",
            fontWeight = FontWeight.Bold, fontSize = 20.sp
        )

        Spacer(modifier = Modifier.size(10.dp))
        Text(text = "Low number of items")
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
                .border(width = 1.dp, color = colorScheme.inversePrimary)
                .padding(1.dp)
        ) {
            VerticalGrid(
                rows = GridCells.Fixed(3),
                modifier = Modifier
                    .width(300.dp)
                    .height(120.dp)
                    .background(colorScheme.primaryContainer),
            ) {
                repeat(7) {
                    Text(
                        text = "Item ${it + 1}",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(40.dp)
                            .border(width = 1.dp, color = colorScheme.tertiary)
                            .padding(10.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.size(10.dp))
        Text(text = "Large number of items")
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
                .border(width = 1.dp, color = colorScheme.inversePrimary)
                .padding(1.dp)
        ) {
            VerticalGrid(
                rows = GridCells.Fixed(3),
                modifier = Modifier
                    .width(300.dp)
                    .height(120.dp)
                    .background(colorScheme.primaryContainer),
            ) {
                repeat(13) {
                    Text(
                        text = "Item ${it + 1}",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(40.dp)
                            .border(width = 1.dp, color = colorScheme.tertiary)
                            .padding(10.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.size(60.dp))
        Text(text = "Large number of items and scrollable vertical")
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
                .border(width = 1.dp, color = colorScheme.inversePrimary)
                .padding(1.dp)
        ) {
            VerticalGrid(
                rows = GridCells.Fixed(3),
                modifier = Modifier
                    .width(300.dp)
                    .height(120.dp)
                    .background(colorScheme.primaryContainer)
                    .verticalScroll(rememberScrollState()),
            ) {
                repeat(13) {
                    Text(
                        text = "Item ${it + 1}",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(40.dp)
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
private fun VerticalGridSizeSamplePreview() {
    VerticalGridSizeSample(remember { MutableStateFlow(true) })
}