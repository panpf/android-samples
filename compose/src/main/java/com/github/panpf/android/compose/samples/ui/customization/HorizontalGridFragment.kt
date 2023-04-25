package com.github.panpf.android.compose.samples.ui.customization

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.rememberScrollState
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
import com.github.panpf.android.compose.samples.ui.base.CenteredText
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem3
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
import com.github.panpf.android.compose.samples.ui.customization.grid.HorizontalGrid
import com.google.accompanist.flowlayout.FlowRow
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
            HorizontalGridHorizontalArrangementSample(allExpandFlow)
            HorizontalGridVerticalArrangementSample(allExpandFlow)
            HorizontalGridItemSpacedSample(allExpandFlow)
            HorizontalGridReverseLayoutSample(allExpandFlow)
            HorizontalGridNotNeatItemSample(allExpandFlow)
            HorizontalGridSizeWrapSample(allExpandFlow)
            HorizontalGridSizeFixedSample(allExpandFlow)
            HorizontalGridItemSizeSample(allExpandFlow)
        }
    }
}


@Composable
@OptIn(ExperimentalLayoutApi::class)
fun HorizontalGridSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "HorizontalGrid", allExpandFlow, padding = 20.dp) {
        val colorScheme = MaterialTheme.colorScheme
        HorizontalGrid(
            rows = GridCells.Fixed(3),
            modifier = Modifier
                .height(200.dp)
                .background(colorScheme.primaryContainer)
        ) {
            repeat(17) { index ->
                CenteredText(
                    text = index.plus(1).toString(),
                    modifier = Modifier
                        .width(40.dp)
                        .border(width = 1.dp, color = colorScheme.tertiary)
                        .padding(10.dp)
                )
            }
        }

        Spacer(modifier = Modifier.size(20.dp))
        HorizontalGrid(
            rows = GridCells.Fixed(3),
            modifier = Modifier
                .height(200.dp)
                .background(colorScheme.primaryContainer)
        ) {
            repeat(37) { index ->
                CenteredText(
                    text = index.plus(1).toString(),
                    modifier = Modifier
                        .width(40.dp)
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
@OptIn(ExperimentalLayoutApi::class)
fun HorizontalGridRowsFixedSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "HorizontalGrid（rows - Fixed）", allExpandFlow, padding = 20.dp) {
        val colorScheme = MaterialTheme.colorScheme
        Column(Modifier.fillMaxWidth()) {
            listOf(3, 2, 1).forEachIndexed { index, rows ->
                if (index > 0) {
                    Spacer(modifier = Modifier.size(20.dp))
                }
                Text(text = "rows=Fixed($rows)")
                Spacer(modifier = Modifier.size(10.dp))
                HorizontalGrid(
                    rows = GridCells.Fixed(rows),
                    modifier = Modifier
                        .height(160.dp)
                        .background(colorScheme.primaryContainer)
                ) {
                    repeat(17) { index ->
                        CenteredText(
                            text = index.plus(1).toString(),
                            modifier = Modifier
                                .width(40.dp)
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
@OptIn(ExperimentalLayoutApi::class)
fun HorizontalGridRowsAdaptiveSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "HorizontalGrid（rows - Adaptive）", allExpandFlow, padding = 20.dp) {
        val colorScheme = MaterialTheme.colorScheme
        Column(Modifier.fillMaxWidth()) {
            Text(text = "rows=Adaptive(100.dp)")
            Spacer(modifier = Modifier.size(10.dp))
            var fillMaxHeightFraction by remember { mutableStateOf(0.6f) }
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .border(width = 1.dp, color = colorScheme.tertiary)
            ) {
                HorizontalGrid(
                    rows = GridCells.Adaptive(60.dp),
                    modifier = Modifier
                        .fillMaxHeight(fraction = fillMaxHeightFraction)
                        .background(colorScheme.primaryContainer)
                ) {
                    repeat(23) { index ->
                        CenteredText(
                            text = index.plus(1).toString(),
                            modifier = Modifier
                                .width(40.dp)
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
@OptIn(ExperimentalLayoutApi::class)
fun HorizontalGridContentPaddingSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "HorizontalGrid（PaddingValues）", allExpandFlow, padding = 20.dp) {
        val colorScheme = MaterialTheme.colorScheme
        Column(Modifier.fillMaxWidth()) {
            Text(text = "PaddingValues(20.dp)")
            Spacer(modifier = Modifier.size(10.dp))
            HorizontalGrid(
                rows = GridCells.Fixed(2),
                modifier = Modifier
                    .height(120.dp)
                    .background(colorScheme.primaryContainer),
                contentPadding = PaddingValues(20.dp)
            ) {
                repeat(13) { index ->
                    CenteredText(
                        text = index.plus(1).toString(),
                        modifier = Modifier
                            .width(40.dp)
                            .border(width = 1.dp, color = colorScheme.tertiary)
                            .padding(10.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.size(20.dp))
            Text(text = "PaddingValues(horizontal=20.dp, vertical=10.dp)")
            Spacer(modifier = Modifier.size(10.dp))
            HorizontalGrid(
                rows = GridCells.Fixed(2),
                modifier = Modifier
                    .height(100.dp)
                    .background(colorScheme.primaryContainer),
                contentPadding = PaddingValues(horizontal = 20.dp, vertical = 10.dp)
            ) {
                repeat(13) { index ->
                    CenteredText(
                        text = index.plus(1).toString(),
                        modifier = Modifier
                            .width(40.dp)
                            .border(width = 1.dp, color = colorScheme.tertiary)
                            .padding(10.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.size(20.dp))
            Text(text = "PaddingValues(start=10.dp, top=20.dp, end=40.dp, bottom=60.dp)")
            Spacer(modifier = Modifier.size(10.dp))
            HorizontalGrid(
                rows = GridCells.Fixed(2),
                modifier = Modifier
                    .height(160.dp)
                    .background(colorScheme.primaryContainer),
                contentPadding = PaddingValues(
                    start = 10.dp,
                    top = 20.dp,
                    end = 40.dp,
                    bottom = 60.dp
                )
            ) {
                repeat(13) { index ->
                    CenteredText(
                        text = index.plus(1).toString(),
                        modifier = Modifier
                            .width(40.dp)
                            .border(width = 1.dp, color = colorScheme.tertiary)
                            .padding(10.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.size(20.dp))
            Text(text = "PaddingValues + ItemSpaced")
            Spacer(modifier = Modifier.size(10.dp))
            HorizontalGrid(
                rows = GridCells.Fixed(2),
                modifier = Modifier
                    .height(110.dp)
                    .background(colorScheme.primaryContainer),
                contentPadding = PaddingValues(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
//                horizontalArrangement = Arrangement.spacedBy(10.dp),      // todo crash
            ) {
                repeat(13) { index ->
                    CenteredText(
                        text = index.plus(1).toString(),
                        modifier = Modifier
                            .width(40.dp)
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
@OptIn(ExperimentalLayoutApi::class)
fun HorizontalGridHorizontalArrangementSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(
        title = "HorizontalGrid（horizontalArrangement）",
        allExpandFlow,
        padding = 20.dp
    ) {
        val colorScheme = MaterialTheme.colorScheme
        FlowRow(mainAxisSpacing = 10.dp, crossAxisSpacing = 10.dp) {
            listOf(
                Arrangement.Start to "Start",
                Arrangement.Center to "Center",
                Arrangement.End to "End",
                Arrangement.SpaceBetween to "SpaceBetween",
                Arrangement.SpaceAround to "SpaceAround",
                Arrangement.SpaceEvenly to "SpaceEvenly",
            ).forEach { (arrangement, name) ->
                Column(Modifier.fillMaxWidth(0.48f)) {
                    Text(text = name)
                    HorizontalGrid(
                        rows = GridCells.Fixed(3),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(160.dp)
                            .background(colorScheme.primaryContainer),
                        horizontalArrangement = arrangement,  // todo invalid
                    ) {
                        repeat(7) { index ->
                            CenteredText(
                                text = index.plus(1).toString(),
                                modifier = Modifier
                                    .requiredSize(30.dp)
                                    .border(width = 1.dp, color = colorScheme.tertiary)
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
private fun HorizontalGridHorizontalArrangementSamplePreview() {
    HorizontalGridHorizontalArrangementSample(remember { MutableStateFlow(true) })
}


@Composable
@OptIn(ExperimentalLayoutApi::class)
fun HorizontalGridVerticalArrangementSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(
        title = "HorizontalGrid（verticalArrangement）",
        allExpandFlow,
        padding = 20.dp
    ) {
        val colorScheme = MaterialTheme.colorScheme
        FlowRow(mainAxisSpacing = 10.dp, crossAxisSpacing = 10.dp) {
            listOf(
                Arrangement.Top to "Start",
                Arrangement.Center to "Center",
                Arrangement.Bottom to "End",
                Arrangement.SpaceBetween to "SpaceBetween",
                Arrangement.SpaceAround to "SpaceAround",
                Arrangement.SpaceEvenly to "SpaceEvenly",
            ).forEach { (arrangement, name) ->
                Column(Modifier.fillMaxWidth(0.48f)) {
                    Text(text = name)
                    HorizontalGrid(
                        rows = GridCells.Fixed(3),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp)
                            .background(colorScheme.primaryContainer),
                        verticalArrangement = arrangement,
                    ) {
                        repeat(7) { index ->
                            CenteredText(
                                text = index.plus(1).toString(),
                                modifier = Modifier
                                    .requiredSize(30.dp)
                                    .border(width = 1.dp, color = colorScheme.tertiary)
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
private fun HorizontalGridVerticalArrangementSamplePreview() {
    HorizontalGridVerticalArrangementSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun HorizontalGridItemSpacedSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(
        title = "HorizontalGrid（ItemSpaced）",
        allExpandFlow,
        padding = 20.dp
    ) {
        val colorScheme = MaterialTheme.colorScheme
        listOf(
            "Fixed(3), horizontal=10.dp, vertical=20.dp" to ((10.dp to 20.dp) to 3),
            "Fixed(3), horizontal=20.dp, vertical=10.dp" to ((20.dp to 10.dp) to 3),
            "Fixed(2), horizontal=10.dp, vertical=10.dp" to ((10.dp to 10.dp) to 2),
            "Fixed(1), horizontal=10.dp, vertical=10.dp" to ((10.dp to 10.dp) to 1),
        ).forEachIndexed { index, pair ->
            if (index > 0) {
                Spacer(modifier = Modifier.height(20.dp))
            }
            val title = pair.first
            val horizontalSpacing = pair.second.first.first
            val verticalSpacing = pair.second.first.second
            val rows = pair.second.second
            Text(text = title)
            HorizontalGrid(
                rows = GridCells.Fixed(rows),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .background(colorScheme.primaryContainer),
                horizontalArrangement = Arrangement.spacedBy(horizontalSpacing),
                verticalArrangement = Arrangement.spacedBy(verticalSpacing),
            ) {
                repeat(13) { index ->
                    CenteredText(
                        text = index.plus(1).toString(),
                        modifier = Modifier
                            .size(40.dp)
                            .border(width = 1.dp, color = colorScheme.tertiary)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun HorizontalGridItemSpacedSamplePreview() {
    HorizontalGridItemSpacedSample(remember { MutableStateFlow(true) })
}


@Composable
@OptIn(ExperimentalLayoutApi::class)
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
                    repeat(13) { index ->
                        CenteredText(
                            text = index.plus(1).toString(),
                            modifier = Modifier
                                .width(40.dp)
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


@Composable
@OptIn(ExperimentalLayoutApi::class)
fun HorizontalGridNotNeatItemSample(allExpandFlow: Flow<Boolean>) {
    val itemWidths = remember { listOf(90.dp, 100.dp, 110.dp) }
    ExpandableItem3(title = "HorizontalGrid（NotNeatItem）", allExpandFlow, padding = 20.dp) {
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
                ) {
                    repeat(7) { index ->
                        CenteredText(
                            text = index.plus(1).toString(),
                            modifier = Modifier
                                .width(itemWidths[index % itemWidths.size])
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
private fun HorizontalGridNotNeatItemSamplePreview() {
    HorizontalGridNotNeatItemSample(remember { MutableStateFlow(true) })
}


@Composable
@OptIn(ExperimentalLayoutApi::class)
fun HorizontalGridSizeWrapSample(allExpandFlow: Flow<Boolean>) {
    val colorScheme = MaterialTheme.colorScheme
    ExpandableItem3(title = "HorizontalGrid（Size - Wrap）", allExpandFlow, padding = 20.dp) {
        Text(
            text = "width=Wrap, height=Wrap",
            fontWeight = FontWeight.Bold, fontSize = 20.sp
        )

        Spacer(modifier = Modifier.size(10.dp))
        Text(text = "Low number of items")
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .border(width = 1.dp, color = colorScheme.inversePrimary)
                .padding(1.dp)
        ) {
            HorizontalGrid(
                rows = GridCells.Fixed(3),
                modifier = Modifier
                    .background(colorScheme.primaryContainer),
            ) {
                repeat(17) { index ->
                    CenteredText(
                        text = index.plus(1).toString(),
                        modifier = Modifier
                            .width(40.dp)
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
                .height(120.dp)
                .border(width = 1.dp, color = colorScheme.inversePrimary)
                .padding(1.dp)
        ) {
            HorizontalGrid(
                rows = GridCells.Fixed(3),
                modifier = Modifier
                    .background(colorScheme.primaryContainer),
            ) {
                repeat(37) { index ->
                    CenteredText(
                        text = index.plus(1).toString(),
                        modifier = Modifier
                            .width(40.dp)
                            .border(width = 1.dp, color = colorScheme.tertiary)
                            .padding(10.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.size(10.dp))
        Text(text = "Large number of items and scrollable horizontally")
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .border(width = 1.dp, color = colorScheme.inversePrimary)
                .padding(1.dp)
        ) {
            HorizontalGrid(
                rows = GridCells.Fixed(3),
                modifier = Modifier
                    .background(colorScheme.primaryContainer)
                    .horizontalScroll(rememberScrollState()),
            ) {
                repeat(37) { index ->
                    CenteredText(
                        text = index.plus(1).toString(),
                        modifier = Modifier
                            .width(40.dp)
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
private fun HorizontalGridSizeWrapSamplePreview() {
    HorizontalGridSizeWrapSample(remember { MutableStateFlow(true) })
}


@Composable
@OptIn(ExperimentalLayoutApi::class)
fun HorizontalGridSizeFixedSample(allExpandFlow: Flow<Boolean>) {
    val colorScheme = MaterialTheme.colorScheme
    ExpandableItem3(title = "HorizontalGrid（Size - Fixed）", allExpandFlow, padding = 20.dp) {
        Text(
            text = "width=350.dp, height=120.dp",
            fontWeight = FontWeight.Bold, fontSize = 20.sp
        )

        Spacer(modifier = Modifier.size(10.dp))
        Text(text = "Low number of items")
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .border(width = 1.dp, color = colorScheme.inversePrimary)
                .padding(1.dp)
        ) {
            HorizontalGrid(
                rows = GridCells.Fixed(3),
                modifier = Modifier
                    .width(300.dp)
                    .height(120.dp)
                    .background(colorScheme.primaryContainer),
            ) {
                repeat(17) { index ->
                    CenteredText(
                        text = index.plus(1).toString(),
                        modifier = Modifier
                            .width(40.dp)
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
                .height(120.dp)
                .border(width = 1.dp, color = colorScheme.inversePrimary)
                .padding(1.dp)
        ) {
            HorizontalGrid(
                rows = GridCells.Fixed(3),
                modifier = Modifier
                    .width(300.dp)
                    .height(120.dp)
                    .background(colorScheme.primaryContainer),
            ) {
                repeat(37) { index ->
                    CenteredText(
                        text = index.plus(1).toString(),
                        modifier = Modifier
                            .width(40.dp)
                            .border(width = 1.dp, color = colorScheme.tertiary)
                            .padding(10.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.size(10.dp))
        Text(text = "Large number of items and scrollable horizontally")
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .border(width = 1.dp, color = colorScheme.inversePrimary)
                .padding(1.dp)
        ) {
            HorizontalGrid(
                rows = GridCells.Fixed(3),
                modifier = Modifier
                    .width(300.dp)
                    .height(120.dp)
                    .background(colorScheme.primaryContainer)
                    .horizontalScroll(rememberScrollState()),
            ) {
                repeat(37) { index ->
                    CenteredText(
                        text = index.plus(1).toString(),
                        modifier = Modifier
                            .width(40.dp)
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
private fun HorizontalGridSizeFixedSamplePreview() {
    HorizontalGridSizeFixedSample(remember { MutableStateFlow(true) })
}


@Composable
@OptIn(ExperimentalLayoutApi::class)
fun HorizontalGridItemSizeSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "HorizontalGrid（ItemSize）", allExpandFlow, padding = 20.dp) {
        val colorScheme = MaterialTheme.colorScheme

        Text(text = "size(40.dp)")
        Spacer(modifier = Modifier.size(10.dp))
        HorizontalGrid(
            rows = GridCells.Fixed(3),
            modifier = Modifier
                .size(240.dp)
                .background(colorScheme.primaryContainer),
        ) {
            repeat(17) { index ->
                CenteredText(
                    text = index.plus(1).toString(),
                    modifier = Modifier
                        .size(40.dp)
                        .border(width = 1.dp, color = colorScheme.tertiary)
                        .padding(10.dp)
                )
            }
        }

        Spacer(modifier = Modifier.size(20.dp))
        Text(text = "requiredSize(40.dp)")
        Spacer(modifier = Modifier.size(10.dp))
        HorizontalGrid(
            rows = GridCells.Fixed(3),
            modifier = Modifier
                .size(240.dp)
                .background(colorScheme.primaryContainer),
        ) {
            repeat(17) { index ->
                CenteredText(
                    text = index.plus(1).toString(),
                    modifier = Modifier
                        .requiredSize(40.dp)
                        .border(width = 1.dp, color = colorScheme.tertiary)
                        .padding(10.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun HorizontalGridItemSizeSamplePreview() {
    HorizontalGridItemSizeSample(remember { MutableStateFlow(true) })
}