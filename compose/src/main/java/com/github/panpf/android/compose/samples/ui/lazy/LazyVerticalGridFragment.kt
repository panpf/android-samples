package com.github.panpf.android.compose.samples.ui.lazy

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.panpf.android.compose.samples.R
import com.github.panpf.android.compose.samples.ui.base.CenteredText
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem3
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
import com.google.accompanist.flowlayout.FlowRow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlin.math.ceil

class LazyVerticalGridFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "LazyVerticalGrid"
    }

    @Composable
    override fun DrawContent() {
        ExpandableLayout { allExpandFlow ->
            LazyVerticalGridSample(allExpandFlow)
            LazyVerticalGridColumnsFixedSample(allExpandFlow)
            LazyVerticalGridColumnsAdaptiveSample(allExpandFlow)
            LazyVerticalGridContentPaddingSample(allExpandFlow)
            LazyVerticalGridReverseLayoutSample(allExpandFlow)
            LazyVerticalGridVerticalArrangementSample(allExpandFlow)
            LazyVerticalGridHorizontalArrangementSample(allExpandFlow)
            LazyVerticalGridItemSpacedSample(allExpandFlow)
            LazyVerticalGridUserScrollEnabledSample(allExpandFlow)
            LazyVerticalGridUserVisibleItemIndexSample(allExpandFlow)
            LazyVerticalGridScrollInProgressSample(allExpandFlow)
            LazyVerticalGridAnimateScrollToItemSample(allExpandFlow)
            LazyVerticalGridAnimateItemPlacementSample(allExpandFlow)
            LazyVerticalGridLayoutInfoSample(allExpandFlow)
            LazyVerticalGridContentTypeSample(allExpandFlow)
            LazyVerticalGridSpanSample(allExpandFlow)
            LazyVerticalGridItemSizeSample(allExpandFlow)
        }
    }
}


@Composable
private fun LazyVerticalGridSample(allExpandFlow: Flow<Boolean>) {
    val colorScheme = MaterialTheme.colorScheme
    ExpandableItem3(title = "LazyVerticalGrid", allExpandFlow, padding = 20.dp) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp)
                .background(colorScheme.primaryContainer)
        ) {
            items(count = 50) { index ->
                CenteredText(
                    text = index.plus(1).toString(),
                    modifier = Modifier
                        .height(40.dp)
                        .border(width = 1.dp, color = colorScheme.tertiary)
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun LazyVerticalGridSamplePreview() {
    LazyVerticalGridSample(remember { MutableStateFlow(true) })
}


@Composable
private fun LazyVerticalGridColumnsFixedSample(allExpandFlow: Flow<Boolean>) {
    val colorScheme = MaterialTheme.colorScheme
    ExpandableItem3(
        title = "LazyVerticalGrid（columns - Fixed）",
        allExpandFlow,
        padding = 20.dp
    ) {
        listOf(3, 2, 1).forEachIndexed { index, columns ->
            if (index > 0) {
                Spacer(modifier = Modifier.size(20.dp))
            }
            Text(text = "columns=Fixed($columns)")
            Spacer(modifier = Modifier.size(10.dp))
            LazyVerticalGrid(
                columns = GridCells.Fixed(columns),
                modifier = Modifier
                    .height(200.dp)
                    .background(colorScheme.primaryContainer)
                    .align(Alignment.CenterHorizontally)
            ) {
                items(count = 50) { index ->
                    CenteredText(
                        text = index.plus(1).toString(),
                        modifier = Modifier
                            .height(40.dp)
                            .border(width = 1.dp, color = colorScheme.tertiary)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun LazyVerticalGridColumnsFixedSamplePreview() {
    LazyVerticalGridColumnsFixedSample(remember { MutableStateFlow(true) })
}


@Composable
private fun LazyVerticalGridColumnsAdaptiveSample(allExpandFlow: Flow<Boolean>) {
    val colorScheme = MaterialTheme.colorScheme
    val gridHeight = remember { mutableStateOf(200.dp) }
    ExpandableItem3(
        title = "LazyVerticalGrid（columns - Adaptive）",
        allExpandFlow,
        padding = 20.dp
    ) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(80.dp),
            modifier = Modifier
                .width(gridHeight.value)
                .height(360.dp)
                .background(colorScheme.primaryContainer)
                .align(Alignment.CenterHorizontally)
        ) {
            items(count = 50) { index ->
                CenteredText(
                    text = index.plus(1).toString(),
                    modifier = Modifier
                        .height(40.dp)
                        .border(width = 1.dp, color = colorScheme.tertiary)
                )
            }
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            IconButton(
                onClick = {
                    if (gridHeight.value > 80.dp) {
                        gridHeight.value -= 60.dp
                    }
                }, modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
            ) {
                Icon(
                    painterResource(id = R.drawable.ic_subtract),
                    contentDescription = "subtract"
                )
            }
            IconButton(
                onClick = {
                    gridHeight.value += 60.dp
                }, modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
            ) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "add")
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun LazyVerticalGridColumnsAdaptiveSamplePreview() {
    LazyVerticalGridColumnsAdaptiveSample(remember { MutableStateFlow(true) })
}


@Composable
private fun LazyVerticalGridContentPaddingSample(allExpandFlow: Flow<Boolean>) {
    val colorScheme = MaterialTheme.colorScheme
    ExpandableItem3(
        title = "LazyVerticalGrid（contentPadding）",
        allExpandFlow,
        padding = 20.dp
    ) {
        Text(text = "PaddingValues(20.dp)")
        Spacer(modifier = Modifier.size(10.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp)
                .background(colorScheme.primaryContainer),
            contentPadding = PaddingValues(20.dp)
        ) {
            items(count = 50) { index ->
                CenteredText(
                    text = index.plus(1).toString(),
                    modifier = Modifier
                        .height(40.dp)
                        .border(width = 1.dp, color = colorScheme.tertiary)
                )
            }
        }

        Spacer(modifier = Modifier.size(20.dp))
        Text(text = "PaddingValues(horizontal=20.dp, vertical=10.dp)")
        Spacer(modifier = Modifier.size(10.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp)
                .background(colorScheme.primaryContainer),
            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 10.dp)
        ) {
            items(count = 50) { index ->
                CenteredText(
                    text = index.plus(1).toString(),
                    modifier = Modifier
                        .height(40.dp)
                        .border(width = 1.dp, color = colorScheme.tertiary)
                )
            }
        }

        Spacer(modifier = Modifier.size(20.dp))
        Text(text = "PaddingValues(start=10.dp, top=20.dp, end=40.dp, bottom=60.dp)")
        Spacer(modifier = Modifier.size(10.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp)
                .background(colorScheme.primaryContainer),
            contentPadding = PaddingValues(
                start = 10.dp,
                top = 20.dp,
                end = 40.dp,
                bottom = 60.dp
            )
        ) {
            items(count = 50) { index ->
                CenteredText(
                    text = index.plus(1).toString(),
                    modifier = Modifier
                        .height(40.dp)
                        .border(width = 1.dp, color = colorScheme.tertiary)
                )
            }
        }

        Spacer(modifier = Modifier.size(20.dp))
        Text(text = "PaddingValues + ItemSpaced")
        Spacer(modifier = Modifier.size(10.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp)
                .background(colorScheme.primaryContainer),
            contentPadding = PaddingValues(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            items(count = 50) { index ->
                CenteredText(
                    text = index.plus(1).toString(),
                    modifier = Modifier
                        .height(40.dp)
                        .border(width = 1.dp, color = colorScheme.tertiary)
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun LazyVerticalGridContentPaddingSamplePreview() {
    LazyVerticalGridContentPaddingSample(remember { MutableStateFlow(true) })
}


@Composable
private fun LazyVerticalGridReverseLayoutSample(allExpandFlow: Flow<Boolean>) {
    val colorScheme = MaterialTheme.colorScheme
    ExpandableItem3(
        title = "LazyVerticalGrid（reverseLayout）",
        allExpandFlow,
        padding = 20.dp
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp)
                .background(colorScheme.primaryContainer),
            reverseLayout = true
        ) {
            items(count = 50) { index ->
                CenteredText(
                    text = index.plus(1).toString(),
                    modifier = Modifier
                        .height(40.dp)
                        .border(width = 1.dp, color = colorScheme.tertiary)
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun LazyVerticalGridReverseLayoutSamplePreview() {
    LazyVerticalGridReverseLayoutSample(remember { MutableStateFlow(true) })
}


@Composable
private fun LazyVerticalGridVerticalArrangementSample(allExpandFlow: Flow<Boolean>) {
    val colorScheme = MaterialTheme.colorScheme
    ExpandableItem3(
        title = "LazyVerticalGrid（verticalArrangement）",
        allExpandFlow,
        padding = 20.dp
    ) {
        FlowRow(mainAxisSpacing = 10.dp, crossAxisSpacing = 10.dp) {
            listOf(
                Arrangement.Top to "Top",
                Arrangement.Center to "Center",
                Arrangement.Bottom to "Bottom",
                Arrangement.SpaceBetween to "SpaceBetween",
                Arrangement.SpaceAround to "SpaceAround",
                Arrangement.SpaceEvenly to "SpaceEvenly",
            ).forEach { (arrangement, name) ->
                Column(Modifier.fillMaxWidth(0.48f)) {
                    Text(text = name)
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(3),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp)
                            .background(colorScheme.primaryContainer),
                        verticalArrangement = arrangement,
                    ) {
                        items(count = 7) { index ->
                            CenteredText(
                                text = index.plus(1).toString(),
                                modifier = Modifier
                                    .requiredSize(40.dp)
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
private fun LazyVerticalGridVerticalArrangementSamplePreview() {
    LazyVerticalGridVerticalArrangementSample(remember { MutableStateFlow(true) })
}


@Composable
private fun LazyVerticalGridHorizontalArrangementSample(allExpandFlow: Flow<Boolean>) {
    val colorScheme = MaterialTheme.colorScheme
    ExpandableItem3(
        title = "LazyVerticalGrid（horizontalArrangement）",
        allExpandFlow,
        padding = 20.dp
    ) {
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
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(3),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp)
                            .background(colorScheme.primaryContainer),
                        horizontalArrangement = arrangement
                    ) {
                        items(count = 7) { index ->
                            CenteredText(
                                text = index.plus(1).toString(),
                                modifier = Modifier
                                    .requiredSize(40.dp)
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
private fun LazyVerticalGridHorizontalArrangementSamplePreview() {
    LazyVerticalGridHorizontalArrangementSample(remember { MutableStateFlow(true) })
}


@Composable
private fun LazyVerticalGridItemSpacedSample(allExpandFlow: Flow<Boolean>) {
    val colorScheme = MaterialTheme.colorScheme
    ExpandableItem3(
        title = "LazyVerticalGrid（ItemSpaced）",
        allExpandFlow,
        padding = 20.dp
    ) {
        FlowRow(mainAxisSpacing = 10.dp, crossAxisSpacing = 10.dp) {
            listOf(
                "Fixed(3)\nvertical=10.dp\nhorizontal=20.dp" to ((10.dp to 20.dp) to 3),
                "Fixed(3)\nvertical=20.dp\nhorizontal=10.dp" to ((20.dp to 10.dp) to 3),
                "Fixed(2)\nvertical=10.dp\nhorizontal=10.dp" to ((10.dp to 10.dp) to 2),
                "Fixed(1)\nvertical=10.dp\nhorizontal=10.dp" to ((10.dp to 10.dp) to 1),
            ).forEach { pair ->
                Column(Modifier.fillMaxWidth(0.48f)) {
                    val title = pair.first
                    val verticalSpacing = pair.second.first.first
                    val horizontalSpacing = pair.second.first.second
                    val rows = pair.second.second
                    Text(text = title)
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(rows),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .background(colorScheme.primaryContainer),
                        verticalArrangement = Arrangement.spacedBy(verticalSpacing),
                        horizontalArrangement = Arrangement.spacedBy(horizontalSpacing),
                    ) {
                        items(count = 50) { index ->
                            CenteredText(
                                text = index.plus(1).toString(),
                                modifier = Modifier
                                    .height(40.dp)
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
private fun LazyVerticalGridItemSpacedSamplePreview() {
    LazyVerticalGridItemSpacedSample(remember { MutableStateFlow(true) })
}


@Composable
private fun LazyVerticalGridUserScrollEnabledSample(allExpandFlow: Flow<Boolean>) {
    val colorScheme = MaterialTheme.colorScheme
    ExpandableItem3(
        title = "LazyVerticalGrid（userScrollEnabled = false）",
        allExpandFlow,
        padding = 20.dp
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            modifier = Modifier
                .fillMaxWidth()
                .height(360.dp)
                .background(colorScheme.primaryContainer),
            userScrollEnabled = false
        ) {
            items(count = 50) { index ->
                CenteredText(
                    text = index.plus(1).toString(),
                    modifier = Modifier
                        .height(40.dp)
                        .border(width = 1.dp, color = colorScheme.tertiary)
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun LazyVerticalGridUserScrollEnabledSamplePreview() {
    LazyVerticalGridUserScrollEnabledSample(remember { MutableStateFlow(true) })
}


@Composable
private fun LazyVerticalGridUserVisibleItemIndexSample(allExpandFlow: Flow<Boolean>) {
    val colorScheme = MaterialTheme.colorScheme
    val lazyListState = rememberLazyGridState(4)
    val itemIndexState = remember { derivedStateOf { lazyListState.firstVisibleItemIndex } }
    val offsetState =
        remember { derivedStateOf { lazyListState.firstVisibleItemScrollOffset } }
    ExpandableItem3(
        title = "LazyVerticalGrid（firstVisibleItemIndex）",
        allExpandFlow,
        padding = 20.dp
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp)
                .background(colorScheme.primaryContainer),
            state = lazyListState
        ) {
            items(count = 50) { index ->
                CenteredText(
                    text = index.plus(1).toString(),
                    modifier = Modifier
                        .height(40.dp)
                        .border(width = 1.dp, color = colorScheme.tertiary)
                )
            }
        }
        Text(text = "firstVisibleItemIndex: ${itemIndexState.value}, firstVisibleItemScrollOffset: ${offsetState.value}")
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun LazyVerticalGridUserVisibleItemIndexSamplePreview() {
    LazyVerticalGridUserVisibleItemIndexSample(remember { MutableStateFlow(true) })
}


@Composable
private fun LazyVerticalGridScrollInProgressSample(allExpandFlow: Flow<Boolean>) {
    val colorScheme = MaterialTheme.colorScheme
    val lazyListState = rememberLazyGridState(3)
    val scrollInProgressState = remember { derivedStateOf { lazyListState.isScrollInProgress } }
    ExpandableItem3(
        title = "LazyVerticalGrid（isScrollInProgress）",
        allExpandFlow,
        padding = 20.dp
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp)
                .background(colorScheme.primaryContainer),
            state = lazyListState
        ) {
            items(count = 50) { index ->
                CenteredText(
                    text = index.plus(1).toString(),
                    modifier = Modifier
                        .height(40.dp)
                        .border(width = 1.dp, color = colorScheme.tertiary)
                )
            }
        }
        Text(text = "isScrollInProgress: ${scrollInProgressState.value}")
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun LazyVerticalGridScrollInProgressSamplePreview() {
    LazyVerticalGridScrollInProgressSample(remember { MutableStateFlow(true) })
}


@Composable
private fun LazyVerticalGridAnimateScrollToItemSample(allExpandFlow: Flow<Boolean>) {
    val colorScheme = MaterialTheme.colorScheme
    val lazyListState = rememberLazyGridState(3)
    val coroutineScope = rememberCoroutineScope()
    ExpandableItem3(
        title = "LazyVerticalGrid（animateScrollToItem）",
        allExpandFlow,
        padding = 20.dp
    ) {
        IconButton(
            onClick = {
                val firstVisibleItemIndex = lazyListState.firstVisibleItemIndex
                if (firstVisibleItemIndex >= 4) {
                    coroutineScope.launch {
                        lazyListState.animateScrollToItem(firstVisibleItemIndex - 4)
                    }
                }
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Icon(
                imageVector = Icons.Filled.KeyboardArrowUp,
                contentDescription = "before"
            )
        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp)
                .background(colorScheme.primaryContainer),
            state = lazyListState
        ) {
            items(count = 50) { index ->
                CenteredText(
                    text = index.plus(1).toString(),
                    modifier = Modifier
                        .height(40.dp)
                        .border(width = 1.dp, color = colorScheme.tertiary)
                )
            }
        }
        IconButton(
            onClick = {
                val firstVisibleItemIndex = lazyListState.firstVisibleItemIndex
                if (firstVisibleItemIndex < 50 - 4) {
                    coroutineScope.launch {
                        lazyListState.animateScrollToItem(firstVisibleItemIndex + 4)
                    }
                }
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Icon(
                imageVector = Icons.Filled.KeyboardArrowDown,
                contentDescription = "next",
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun LazyVerticalGridAnimateScrollToItemSamplePreview() {
    LazyVerticalGridAnimateScrollToItemSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun LazyVerticalGridAnimateItemPlacementSample(allExpandFlow: Flow<Boolean>) {
    val colorScheme = MaterialTheme.colorScheme
    val items = remember { mutableStateOf((1..50).map { it.toString() }) }
    ExpandableItem3(
        title = "LazyVerticalGrid（animateItemPlacement）",
        allExpandFlow,
        padding = 20.dp
    ) {
        Text(text = "点击 item 删除它，然后触发动画")
        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp)
                .background(colorScheme.primaryContainer)
        ) {
            itemsIndexed(
                items = items.value,
                key = { _, item -> item }
            ) { _, item ->
                CenteredText(
                    text = item,
                    modifier = Modifier
                        .height(40.dp)
                        .border(width = 1.dp, color = colorScheme.tertiary)
                        .animateItemPlacement()
                        .clickable {
                            items.value = items.value
                                .toMutableList()
                                .apply {
                                    remove(item)
                                }
                                .toList()
                        },
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun LazyVerticalGridAnimateItemPlacementSamplePreview() {
    LazyVerticalGridAnimateItemPlacementSample(remember { MutableStateFlow(true) })
}


@Composable
private fun LazyVerticalGridLayoutInfoSample(allExpandFlow: Flow<Boolean>) {
    val colorScheme = MaterialTheme.colorScheme
    val lazyGridState = rememberLazyGridState()
    val layoutInfoState = remember { derivedStateOf { lazyGridState.layoutInfo } }
    ExpandableItem3(title = "LazyVerticalGrid（layoutInfo）", allExpandFlow, padding = 20.dp) {
        LazyVerticalGrid(
            state = lazyGridState,
            columns = GridCells.Fixed(4),
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp)
                .background(colorScheme.primaryContainer)
        ) {
            items(count = 50) { index ->
                CenteredText(
                    text = index.plus(1).toString(),
                    modifier = Modifier
                        .height(40.dp)
                        .border(width = 1.dp, color = colorScheme.tertiary)
                )
            }
        }
        Text(text = layoutInfoState.let { listLayoutInfoState ->
            buildString {
                append("visibleItemsInfo: ")
                listLayoutInfoState.value.visibleItemsInfo.forEach { itemInfo ->
                    appendLine()
                    append("        ")
                    append("index=${itemInfo.index}, offset=${itemInfo.offset}, size=${itemInfo.size}")
                }

                appendLine()
                append("viewportStartOffset: ${listLayoutInfoState.value.viewportStartOffset}")

                appendLine()
                append("viewportEndOffset: ${listLayoutInfoState.value.viewportEndOffset}")

                appendLine()
                append("totalItemsCount: ${listLayoutInfoState.value.totalItemsCount}")

                appendLine()
                append("viewportSize: ${listLayoutInfoState.value.viewportSize}")

                appendLine()
                append("reverseLayout: ${listLayoutInfoState.value.reverseLayout}")

                appendLine()
                append("beforeContentPadding: ${listLayoutInfoState.value.beforeContentPadding}")

                appendLine()
                append("afterContentPadding: ${listLayoutInfoState.value.afterContentPadding}")
            }
        })
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun LazyVerticalGridLayoutInfoSamplePreview() {
    LazyVerticalGridLayoutInfoSample(remember { MutableStateFlow(true) })
}


@Composable
private fun LazyVerticalGridContentTypeSample(allExpandFlow: Flow<Boolean>) {
    val colorScheme = MaterialTheme.colorScheme
    val items = buildList<Any> {
        repeat(49) {
            add((it + 1).toString())
        }
    }.toMutableList().apply {
        set(1, Icons.Filled.KeyboardArrowLeft)
        set(3, Icons.Filled.Add)
        set(10, Icons.Filled.Menu)
        set(17, Icons.Filled.KeyboardArrowDown)
        set(18, Icons.Filled.Check)
        set(40, Icons.Filled.Info)
    }.toList()
    ExpandableItem3(title = "LazyVerticalGrid（contentType）", allExpandFlow, padding = 20.dp) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp)
                .background(colorScheme.primaryContainer)
        ) {
            itemsIndexed(
                items = items,
                contentType = { _, item ->
                    when (item) {
                        is String -> 0
                        is Int -> 1
                        else -> 2
                    }
                }
            ) { _, item ->
                when (item) {
                    is String -> {
                        CenteredText(
                            text = item,
                            modifier = Modifier
                                .aspectRatio(1f)
                                .border(width = 1.dp, color = colorScheme.tertiary)
                        )
                    }

                    is ImageVector -> {
                        Box(modifier = Modifier.aspectRatio(1f)) {
                            FilledTonalIconButton(
                                onClick = { },
                                modifier = Modifier
                                    .align(Alignment.Center)
                            ) {
                                Icon(imageVector = item, contentDescription = "icon")
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
private fun LazyVerticalGridContentTypeSamplePreview() {
    LazyVerticalGridContentTypeSample(remember { MutableStateFlow(true) })
}


@Composable
private fun LazyVerticalGridSpanSample(allExpandFlow: Flow<Boolean>) {
    val colorScheme = MaterialTheme.colorScheme
    ExpandableItem3(title = "LazyVerticalGrid（span）", allExpandFlow, padding = 20.dp) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp)
                .background(colorScheme.primaryContainer)
        ) {
            items(
                count = 50,
                span = { index ->
                    when (index) {
                        3 -> GridItemSpan(ceil(maxLineSpan.div(2f)).toInt())
                        7 -> GridItemSpan(maxLineSpan)
                        15 -> GridItemSpan(maxLineSpan)
                        24 -> GridItemSpan(ceil(maxLineSpan.div(2f)).toInt())
                        35 -> GridItemSpan(maxLineSpan)
                        36 -> GridItemSpan(maxLineSpan)
                        43 -> GridItemSpan(ceil(maxLineSpan.div(2f)).toInt())
                        44 -> GridItemSpan(ceil(maxLineSpan.div(2f)).toInt())
                        else -> GridItemSpan(1)
                    }
                }
            ) { index ->
                CenteredText(
                    text = index.plus(1).toString(),
                    modifier = Modifier
                        .height(40.dp)
                        .border(width = 1.dp, color = colorScheme.tertiary)
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun LazyVerticalGridSpanSamplePreview() {
    LazyVerticalGridSpanSample(remember { MutableStateFlow(true) })
}


@Composable
private fun LazyVerticalGridItemSizeSample(allExpandFlow: Flow<Boolean>) {
    val colorScheme = MaterialTheme.colorScheme
    ExpandableItem3(title = "LazyVerticalGrid（ItemSize）", allExpandFlow, padding = 20.dp) {
        Text(text = "size(40.dp)")
        Spacer(modifier = Modifier.size(10.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier
                .size(240.dp)
                .background(colorScheme.primaryContainer),
        ) {
            items(count = 50) { index ->
                CenteredText(
                    text = index.plus(1).toString(),
                    modifier = Modifier
                        .size(40.dp)
                        .border(width = 1.dp, color = colorScheme.tertiary)
                )
            }
        }

        Spacer(modifier = Modifier.size(20.dp))
        Text(text = "requiredSize(40.dp)")
        Spacer(modifier = Modifier.size(10.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier
                .size(240.dp)
                .background(colorScheme.primaryContainer),
        ) {
            items(count = 50) { index ->
                CenteredText(
                    text = index.plus(1).toString(),
                    modifier = Modifier
                        .requiredSize(40.dp)
                        .border(width = 1.dp, color = colorScheme.tertiary)
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun LazyVerticalGridItemSizeSamplePreview() {
    LazyVerticalGridItemSizeSample(remember { MutableStateFlow(true) })
}