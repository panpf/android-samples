package com.github.panpf.android.compose.samples.ui.customization

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
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
import com.google.accompanist.flowlayout.FlowRow
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
            VerticalGridColumnsFixedSample(allExpandFlow)
            VerticalGridColumnsAdaptiveSample(allExpandFlow)
            VerticalGridContentPaddingSample(allExpandFlow)
            VerticalGridArrangementSample(allExpandFlow)
            VerticalGridArrangementWithColumnsSample(allExpandFlow)
            VerticalGridItemSpacedSample(allExpandFlow)
            VerticalGridReverseLayoutSample(allExpandFlow)
            VerticalGridNotNeatItemSample(allExpandFlow)
            VerticalGridSizeSample(allExpandFlow)
            VerticalGridItemSizeSample(allExpandFlow)
        }
    }
}


@Composable
@OptIn(ExperimentalLayoutApi::class)
fun VerticalGridSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "VerticalGrid", allExpandFlow, padding = 20.dp) {
        val colorScheme = MaterialTheme.colorScheme
        VerticalGrid(
            columns = GridCells.Fixed(3),
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
            columns = GridCells.Fixed(3),
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
@OptIn(ExperimentalLayoutApi::class)
fun VerticalGridColumnsFixedSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "VerticalGrid（columns - Fixed）", allExpandFlow, padding = 20.dp) {
        val colorScheme = MaterialTheme.colorScheme
        Column(Modifier.fillMaxWidth()) {
            listOf(3, 2, 1).forEachIndexed { index, columns ->
                if (index > 0) {
                    Spacer(modifier = Modifier.size(20.dp))
                }
                Text(text = "columns=Fixed($columns)")
                Spacer(modifier = Modifier.size(10.dp))
                VerticalGrid(
                    columns = GridCells.Fixed(columns),
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
private fun VerticalGridColumnsFixedSamplePreview() {
    VerticalGridColumnsFixedSample(remember { MutableStateFlow(true) })
}


@Composable
@OptIn(ExperimentalLayoutApi::class)
fun VerticalGridColumnsAdaptiveSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "VerticalGrid（columns - Adaptive）", allExpandFlow, padding = 20.dp) {
        val colorScheme = MaterialTheme.colorScheme
        Column(Modifier.fillMaxWidth()) {
            Text(text = "columns=Adaptive(100.dp)")
            Spacer(modifier = Modifier.size(10.dp))
            var fillMaxWidthFraction by remember { mutableStateOf(0.6f) }
            VerticalGrid(
                columns = GridCells.Adaptive(100.dp),
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
private fun VerticalGridColumnsAdaptiveSamplePreview() {
    VerticalGridColumnsAdaptiveSample(remember { MutableStateFlow(true) })
}


@Composable
@OptIn(ExperimentalLayoutApi::class)
fun VerticalGridContentPaddingSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "VerticalGrid（PaddingValues）", allExpandFlow, padding = 20.dp) {
        val colorScheme = MaterialTheme.colorScheme
        Column(Modifier.fillMaxWidth()) {
            Text(text = "PaddingValues(20.dp)")
            Spacer(modifier = Modifier.size(10.dp))
            VerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = Modifier.background(colorScheme.primaryContainer),
                contentPadding = PaddingValues(20.dp)
            ) {
                repeat(6) {
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
                columns = GridCells.Fixed(3),
                modifier = Modifier.background(colorScheme.primaryContainer),
                contentPadding = PaddingValues(horizontal = 20.dp, vertical = 10.dp)
            ) {
                repeat(6) {
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
                columns = GridCells.Fixed(3),
                modifier = Modifier.background(colorScheme.primaryContainer),
                contentPadding = PaddingValues(
                    start = 10.dp,
                    top = 20.dp,
                    end = 40.dp,
                    bottom = 60.dp
                )
            ) {
                repeat(6) {
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
            Text(text = "PaddingValues + ItemSpaced")
            Spacer(modifier = Modifier.size(10.dp))
            VerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = Modifier.background(colorScheme.primaryContainer),
                contentPadding = PaddingValues(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                repeat(6) {
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
@OptIn(ExperimentalLayoutApi::class)
fun VerticalGridArrangementSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(
        title = "VerticalGrid（Arrangement）",
        allExpandFlow,
        padding = 20.dp
    ) {
        // todo 未完成
//        listOf(
//            Arrangement.Top to "Top",
//            Arrangement.Center to "Center",
//            Arrangement.Bottom to "Bottom",
//            null to "Space=10.dp",
//            Arrangement.SpaceBetween to "SpaceBetween",
//            Arrangement.SpaceAround to "SpaceAround",
//            Arrangement.SpaceEvenly to "SpaceEvenly",
//        ).forEach { (arrangement, name) ->
//            Column {
//                Text(text = name)
//            }
//        }
        val colorScheme = MaterialTheme.colorScheme
        Column(Modifier.fillMaxWidth()) {
            Text(text = "verticalArrangement=spacedBy(10.dp)")
            Spacer(modifier = Modifier.size(10.dp))
            VerticalGrid(
                columns = GridCells.Fixed(3),
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
                columns = GridCells.Fixed(3),
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
                columns = GridCells.Fixed(3),
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
@OptIn(ExperimentalLayoutApi::class)
fun VerticalGridArrangementWithColumnsSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(
        title = "VerticalGrid（Arrangement + Columns）",
        allExpandFlow,
        padding = 20.dp
    ) {
        val colorScheme = MaterialTheme.colorScheme
        Column(Modifier.fillMaxWidth()) {
            listOf(3, 2, 1).forEachIndexed { index, columns ->
                if (index > 0) {
                    Spacer(modifier = Modifier.size(20.dp))
                }
                Text(text = "Arrangement + Fixed($columns)")
                Spacer(modifier = Modifier.size(10.dp))
                VerticalGrid(
                    columns = GridCells.Fixed(columns),
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
private fun VerticalGridArrangementWithColumnsSamplePreview() {
    VerticalGridArrangementWithColumnsSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun VerticalGridItemSpacedSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(
        title = "VerticalGrid（ItemSpaced）",
        allExpandFlow,
        padding = 20.dp
    ) {
        val colorScheme = MaterialTheme.colorScheme
        FlowRow(mainAxisSpacing = 10.dp, crossAxisSpacing = 10.dp) {
            Column(Modifier.fillMaxWidth(0.45f)) {
                Text(text = "vertical=10.dp\nhorizontal=20.dp")
                VerticalGrid(
                    columns = GridCells.Fixed(3),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .background(colorScheme.primaryContainer),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    horizontalArrangement = Arrangement.spacedBy(20.dp),
                ) {
                    repeat(7) { index ->
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(1f)
                                .border(width = 1.dp, color = colorScheme.tertiary)
                        ) {
                            Text(
                                text = index.plus(1).toString(),
                                modifier = Modifier
                                    .align(Alignment.Center)
                            )
                        }
                    }
                }
            }

            Column(Modifier.fillMaxWidth(0.45f)) {
                Text(text = "vertical=20.dp\nhorizontal=10.dp")
                VerticalGrid(
                    columns = GridCells.Fixed(3),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .background(colorScheme.primaryContainer),
                    verticalArrangement = Arrangement.spacedBy(20.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    repeat(7) { index ->
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(1f)
                                .border(width = 1.dp, color = colorScheme.tertiary)
                        ) {
                            Text(
                                text = index.plus(1).toString(),
                                modifier = Modifier
                                    .align(Alignment.Center)
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
private fun VerticalGridItemSpacedSamplePreview() {
    VerticalGridItemSpacedSample(remember { MutableStateFlow(true) })
}


@Composable
@OptIn(ExperimentalLayoutApi::class)
fun VerticalGridReverseLayoutSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "VerticalGrid（reverseLayout）", allExpandFlow, padding = 20.dp) {
        val colorScheme = MaterialTheme.colorScheme
        Column(Modifier.fillMaxWidth()) {
            listOf(3, 2, 1).forEachIndexed { index, columns ->
                if (index > 0) {
                    Spacer(modifier = Modifier.size(20.dp))
                }
                Text(text = "Arrangement + Fixed($columns)")
                Spacer(modifier = Modifier.size(10.dp))
                VerticalGrid(
                    columns = GridCells.Fixed(columns),
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
@OptIn(ExperimentalLayoutApi::class)
fun VerticalGridNotNeatItemSample(allExpandFlow: Flow<Boolean>) {
    val itemHeights = remember { listOf(40.dp, 50.dp, 60.dp) }
    ExpandableItem3(title = "VerticalGrid（NotNeatItem）", allExpandFlow, padding = 20.dp) {
        val colorScheme = MaterialTheme.colorScheme
        Column(Modifier.fillMaxWidth()) {
            listOf(3, 2, 1).forEachIndexed { index, columns ->
                if (index > 0) {
                    Spacer(modifier = Modifier.size(20.dp))
                }
                Text(text = "Arrangement + Fixed($columns)")
                Spacer(modifier = Modifier.size(10.dp))
                VerticalGrid(
                    columns = GridCells.Fixed(columns),
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
@OptIn(ExperimentalLayoutApi::class)
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
                columns = GridCells.Fixed(3),
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
                columns = GridCells.Fixed(3),
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
                columns = GridCells.Fixed(3),
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
                columns = GridCells.Fixed(3),
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
                columns = GridCells.Fixed(3),
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
                columns = GridCells.Fixed(3),
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


@Composable
@OptIn(ExperimentalLayoutApi::class)
fun VerticalGridItemSizeSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "VerticalGrid（ItemSize）", allExpandFlow, padding = 20.dp) {
        val colorScheme = MaterialTheme.colorScheme

        Text(text = "size(60.dp)")
        Spacer(modifier = Modifier.size(10.dp))
        VerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier
                .size(240.dp)
                .background(colorScheme.primaryContainer)
        ) {
            repeat(7) {
                Text(
                    text = "Item ${it + 1}",
                    modifier = Modifier
                        .size(60.dp)
                        .border(width = 1.dp, color = colorScheme.tertiary)
                        .padding(10.dp)
                )
            }
        }

        Spacer(modifier = Modifier.size(20.dp))
        Text(text = "requiredSize(60.dp)")
        Spacer(modifier = Modifier.size(10.dp))
        VerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier
                .size(240.dp)
                .background(colorScheme.primaryContainer)
        ) {
            repeat(7) {
                Text(
                    text = "Item ${it + 1}",
                    modifier = Modifier
                        .requiredSize(60.dp)
                        .border(width = 1.dp, color = colorScheme.tertiary)
                        .padding(10.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun VerticalGridItemSizeSamplePreview() {
    VerticalGridItemSizeSample(remember { MutableStateFlow(true) })
}