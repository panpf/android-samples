package com.github.panpf.android.compose.samples.ui.lazy

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.foundation.lazy.staggeredgrid.itemsIndexed
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
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
import com.github.panpf.android.compose.samples.ui.base.SubtitleText
import com.google.accompanist.flowlayout.FlowRow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class LazyVerticalStaggeredGridFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "LazyVerticalStaggeredGrid"
    }

    @Composable
    override fun DrawContent() {
        ExpandableLayout { allExpandFlow ->
            LazyVerticalStaggeredGridSample(allExpandFlow)
            LazyVerticalStaggeredGridColumnsAdaptiveSample(allExpandFlow)
            LazyVerticalStaggeredGridContentPaddingSample(allExpandFlow)
            LazyVerticalStaggeredGridReverseLayoutSample(allExpandFlow)
            LazyVerticalStaggeredGridHorizontalArrangementSample(allExpandFlow)
            LazyVerticalStaggeredGridItemSpacedSample(allExpandFlow)
            LazyVerticalStaggeredGridUserScrollEnabledSample(allExpandFlow)
            LazyVerticalStaggeredGridUserVisibleItemIndexSample(allExpandFlow)
            LazyVerticalStaggeredGridScrollInProgressSample(allExpandFlow)
            LazyVerticalStaggeredGridAnimateScrollToItemSample(allExpandFlow)
            LazyVerticalStaggeredGridLayoutInfoSample(allExpandFlow)
            LazyVerticalStaggeredGridContentTypeSample(allExpandFlow)
            LazyVerticalStaggeredGridSpanSample(allExpandFlow)
        }
    }
}

private fun getAspectRation(index: Int): Float =
    when {
        index.plus(1) % 3 == 0 -> 0.8f
        index.plus(1) % 7 == 0 -> 0.5f
        else -> 1f
    }


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun LazyVerticalStaggeredGridSample(allExpandFlow: Flow<Boolean>) {
    val colorScheme = MaterialTheme.colorScheme
    ExpandableItem3(title = "LazyVerticalStaggeredGrid", allExpandFlow, padding = 20.dp) {
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(4),
            modifier = Modifier
                .fillMaxWidth()
                .height(360.dp)
                .background(colorScheme.primaryContainer)
        ) {
            items(count = 50) { index ->
                CenteredText(
                    text = index.plus(1).toString(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(getAspectRation(index))
                        .border(width = 1.dp, color = colorScheme.tertiary)
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun LazyVerticalStaggeredGridSamplePreview() {
    LazyVerticalStaggeredGridSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun LazyVerticalStaggeredGridColumnsFixedSample(allExpandFlow: Flow<Boolean>) {
    val colorScheme = MaterialTheme.colorScheme
    ExpandableItem3(
        title = "LazyVerticalStaggeredGrid（columns - Fixed）",
        allExpandFlow,
        padding = 20.dp
    ) {
        FlowRow(mainAxisSpacing = 10.dp, crossAxisSpacing = 10.dp) {
            listOf(3, 2, 1).forEachIndexed { _, columns ->
                Column(Modifier.fillMaxWidth(0.48f)) {
                    Text(text = "columns=Fixed($columns)")
                    Spacer(modifier = Modifier.size(10.dp))
                    LazyVerticalStaggeredGrid(
                        columns = StaggeredGridCells.Fixed(columns),
                        modifier = Modifier
                            .height(200.dp)
                            .background(colorScheme.primaryContainer)
                            .align(Alignment.CenterHorizontally)
                    ) {
                        items(count = 50) { index ->
                            CenteredText(
                                text = index.plus(1).toString(),
                                modifier = Modifier
                                    .aspectRatio(getAspectRation(index))
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
private fun LazyVerticalStaggeredGridColumnsFixedSamplePreview() {
    LazyVerticalStaggeredGridColumnsFixedSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun LazyVerticalStaggeredGridColumnsAdaptiveSample(allExpandFlow: Flow<Boolean>) {
    val colorScheme = MaterialTheme.colorScheme
    val gridHeight = remember { mutableStateOf(200.dp) }
    ExpandableItem3(
        title = "LazyVerticalStaggeredGrid（columns - Adaptive）",
        allExpandFlow,
        padding = 20.dp
    ) {
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Adaptive(80.dp),
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
                        .fillMaxWidth()
                        .aspectRatio(getAspectRation(index))
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
private fun LazyVerticalStaggeredGridColumnsAdaptiveSamplePreview() {
    LazyVerticalStaggeredGridColumnsAdaptiveSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun LazyVerticalStaggeredGridContentPaddingSample(allExpandFlow: Flow<Boolean>) {
    val colorScheme = MaterialTheme.colorScheme
    ExpandableItem3(
        title = "LazyVerticalStaggeredGrid（contentPadding）",
        allExpandFlow,
        padding = 20.dp
    ) {
        FlowRow(mainAxisSpacing = 10.dp, crossAxisSpacing = 10.dp) {
            Column(Modifier.fillMaxWidth(0.48f)) {
                SubtitleText(text = "PaddingValues(20.dp)", line = 3)
                Spacer(modifier = Modifier.size(10.dp))
                LazyVerticalStaggeredGrid(
                    columns = StaggeredGridCells.Fixed(3),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .background(colorScheme.primaryContainer),
                    contentPadding = PaddingValues(20.dp)
                ) {
                    items(count = 50) { index ->
                        CenteredText(
                            text = index.plus(1).toString(),
                            modifier = Modifier
                                .aspectRatio(getAspectRation(index))
                                .border(width = 1.dp, color = colorScheme.tertiary)
                        )
                    }
                }
            }

            Column(Modifier.fillMaxWidth(0.48f)) {
                SubtitleText(text = "PaddingValues(horizontal=20.dp, vertical=10.dp)", line = 3)
                Spacer(modifier = Modifier.size(10.dp))
                LazyVerticalStaggeredGrid(
                    columns = StaggeredGridCells.Fixed(3),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .background(colorScheme.primaryContainer),
                    contentPadding = PaddingValues(horizontal = 20.dp, vertical = 10.dp)
                ) {
                    items(count = 50) { index ->
                        CenteredText(
                            text = index.plus(1).toString(),
                            modifier = Modifier
                                .aspectRatio(getAspectRation(index))
                                .border(width = 1.dp, color = colorScheme.tertiary)
                        )
                    }
                }
            }

            Column(Modifier.fillMaxWidth(0.48f)) {
                SubtitleText(
                    text = "PaddingValues(start=10.dp, top=20.dp, end=40.dp, bottom=60.dp)",
                    line = 3
                )
                Spacer(modifier = Modifier.size(10.dp))
                LazyVerticalStaggeredGrid(
                    columns = StaggeredGridCells.Fixed(3),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
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
                                .aspectRatio(getAspectRation(index))
                                .border(width = 1.dp, color = colorScheme.tertiary)
                        )
                    }
                }
            }

            Column(Modifier.fillMaxWidth(0.48f)) {
                SubtitleText(text = "PaddingValues + ItemSpaced", line = 3)
                Spacer(modifier = Modifier.size(10.dp))
                LazyVerticalStaggeredGrid(
                    columns = StaggeredGridCells.Fixed(3),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .background(colorScheme.primaryContainer),
                    contentPadding = PaddingValues(10.dp),
                    verticalItemSpacing = 10.dp,
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    items(count = 50) { index ->
                        CenteredText(
                            text = index.plus(1).toString(),
                            modifier = Modifier
                                .aspectRatio(getAspectRation(index))
                                .border(width = 1.dp, color = colorScheme.tertiary)
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun LazyVerticalStaggeredGridContentPaddingSamplePreview() {
    LazyVerticalStaggeredGridContentPaddingSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun LazyVerticalStaggeredGridReverseLayoutSample(allExpandFlow: Flow<Boolean>) {
    val colorScheme = MaterialTheme.colorScheme
    ExpandableItem3(
        title = "LazyVerticalStaggeredGrid（reverseLayout）",
        allExpandFlow,
        padding = 20.dp
    ) {
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(4),
            modifier = Modifier
                .fillMaxWidth()
                .height(360.dp)
                .background(colorScheme.primaryContainer),
            reverseLayout = true
        ) {
            items(count = 50) { index ->
                CenteredText(
                    text = index.plus(1).toString(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(getAspectRation(index))
                        .border(width = 1.dp, color = colorScheme.tertiary)
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun LazyVerticalStaggeredGridReverseLayoutSamplePreview() {
    LazyVerticalStaggeredGridReverseLayoutSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun LazyVerticalStaggeredGridHorizontalArrangementSample(allExpandFlow: Flow<Boolean>) {
    val colorScheme = MaterialTheme.colorScheme
    ExpandableItem3(
        title = "LazyVerticalStaggeredGrid（horizontalArrangement）",
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
                    LazyVerticalStaggeredGrid(
                        columns = StaggeredGridCells.Fixed(3),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(160.dp)
                            .background(colorScheme.primaryContainer),
                        horizontalArrangement = arrangement
                    ) {
                        items(count = 9) { index ->
                            CenteredText(
                                text = index.plus(1).toString(),
                                modifier = Modifier
                                    .requiredWidth(25.dp)
                                    .aspectRatio(getAspectRation(index))
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
private fun LazyVerticalStaggeredGridHorizontalArrangementSamplePreview() {
    LazyVerticalStaggeredGridHorizontalArrangementSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun LazyVerticalStaggeredGridItemSpacedSample(allExpandFlow: Flow<Boolean>) {
    val colorScheme = MaterialTheme.colorScheme
    ExpandableItem3(
        title = "LazyVerticalStaggeredGrid（ItemSpaced）",
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
                    LazyVerticalStaggeredGrid(
                        columns = StaggeredGridCells.Fixed(rows),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .background(colorScheme.primaryContainer),
                        verticalItemSpacing = verticalSpacing,
                        horizontalArrangement = Arrangement.spacedBy(horizontalSpacing),
                    ) {
                        items(count = 50) { index ->
                            CenteredText(
                                text = index.plus(1).toString(),
                                modifier = Modifier
                                    .aspectRatio(getAspectRation(index))
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
private fun LazyVerticalStaggeredGridItemSpacedSamplePreview() {
    LazyVerticalStaggeredGridItemSpacedSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun LazyVerticalStaggeredGridUserScrollEnabledSample(allExpandFlow: Flow<Boolean>) {
    val colorScheme = MaterialTheme.colorScheme
    ExpandableItem3(
        title = "LazyVerticalStaggeredGrid（userScrollEnabled = false）",
        allExpandFlow,
        padding = 20.dp
    ) {
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(4),
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
                        .fillMaxWidth()
                        .aspectRatio(getAspectRation(index))
                        .border(width = 1.dp, color = colorScheme.tertiary)
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun LazyVerticalStaggeredGridUserScrollEnabledSamplePreview() {
    LazyVerticalStaggeredGridUserScrollEnabledSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun LazyVerticalStaggeredGridUserVisibleItemIndexSample(allExpandFlow: Flow<Boolean>) {
    val colorScheme = MaterialTheme.colorScheme
    val lazyListState = rememberLazyStaggeredGridState(4)
    val itemIndexState = remember { derivedStateOf { lazyListState.firstVisibleItemIndex } }
    val offsetState =
        remember { derivedStateOf { lazyListState.firstVisibleItemScrollOffset } }
    ExpandableItem3(
        title = "LazyVerticalStaggeredGrid（firstVisibleItemIndex）",
        allExpandFlow,
        padding = 20.dp
    ) {
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(4),
            modifier = Modifier
                .fillMaxWidth()
                .height(360.dp)
                .background(colorScheme.primaryContainer),
            state = lazyListState
        ) {
            items(count = 50) { index ->
                CenteredText(
                    text = index.plus(1).toString(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(getAspectRation(index))
                        .border(width = 1.dp, color = colorScheme.tertiary)
                )
            }
        }
        Text(text = "firstVisibleItemIndex: ${itemIndexState.value}, firstVisibleItemScrollOffset: ${offsetState.value}")
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun LazyVerticalStaggeredGridUserVisibleItemIndexSamplePreview() {
    LazyVerticalStaggeredGridUserVisibleItemIndexSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun LazyVerticalStaggeredGridScrollInProgressSample(allExpandFlow: Flow<Boolean>) {
    val colorScheme = MaterialTheme.colorScheme
    val lazyListState = rememberLazyStaggeredGridState(3)
    val scrollInProgressState = remember { derivedStateOf { lazyListState.isScrollInProgress } }
    ExpandableItem3(
        title = "LazyVerticalStaggeredGrid（isScrollInProgress）",
        allExpandFlow,
        padding = 20.dp
    ) {
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(4),
            modifier = Modifier
                .fillMaxWidth()
                .height(360.dp)
                .background(colorScheme.primaryContainer),
            state = lazyListState
        ) {
            items(count = 50) { index ->
                CenteredText(
                    text = index.plus(1).toString(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(getAspectRation(index))
                        .border(width = 1.dp, color = colorScheme.tertiary)
                )
            }
        }
        Text(text = "isScrollInProgress: ${scrollInProgressState.value}")
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun LazyVerticalStaggeredGridScrollInProgressSamplePreview() {
    LazyVerticalStaggeredGridScrollInProgressSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun LazyVerticalStaggeredGridAnimateScrollToItemSample(allExpandFlow: Flow<Boolean>) {
    val colorScheme = MaterialTheme.colorScheme
    val lazyListState = rememberLazyStaggeredGridState(3)
    val coroutineScope = rememberCoroutineScope()
    ExpandableItem3(
        title = "LazyVerticalStaggeredGrid（animateScrollToItem）",
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
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(4),
            modifier = Modifier
                .fillMaxWidth()
                .height(360.dp)
                .background(colorScheme.primaryContainer),
            state = lazyListState
        ) {
            items(count = 50) { index ->
                CenteredText(
                    text = index.plus(1).toString(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(getAspectRation(index))
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
private fun LazyVerticalStaggeredGridAnimateScrollToItemSamplePreview() {
    LazyVerticalStaggeredGridAnimateScrollToItemSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun LazyVerticalStaggeredGridLayoutInfoSample(allExpandFlow: Flow<Boolean>) {
    val colorScheme = MaterialTheme.colorScheme
    val lazyGridState = rememberLazyStaggeredGridState()
    val layoutInfoState = remember { derivedStateOf { lazyGridState.layoutInfo } }
    ExpandableItem3(
        title = "LazyVerticalStaggeredGrid（layoutInfo）",
        allExpandFlow,
        padding = 20.dp
    ) {
        LazyVerticalStaggeredGrid(
            state = lazyGridState,
            columns = StaggeredGridCells.Fixed(4),
            modifier = Modifier
                .fillMaxWidth()
                .height(360.dp)
                .background(colorScheme.primaryContainer)
        ) {
            items(count = 50) { index ->
                CenteredText(
                    text = index.plus(1).toString(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(getAspectRation(index))
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
                append("beforeContentPadding: ${listLayoutInfoState.value.beforeContentPadding}")

                appendLine()
                append("afterContentPadding: ${listLayoutInfoState.value.afterContentPadding}")
            }
        })
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun LazyVerticalStaggeredGridLayoutInfoSamplePreview() {
    LazyVerticalStaggeredGridLayoutInfoSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun LazyVerticalStaggeredGridContentTypeSample(allExpandFlow: Flow<Boolean>) {
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
    ExpandableItem3(
        title = "LazyVerticalStaggeredGrid（contentType）",
        allExpandFlow,
        padding = 20.dp
    ) {
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(4),
            modifier = Modifier
                .fillMaxWidth()
                .height(360.dp)
                .background(colorScheme.primaryContainer)
        ) {
            itemsIndexed(
                items = items,
                contentType = { _, item ->
                    when (item) {
                        is String -> 0
                        else -> 1
                    }
                }
            ) { index, item ->
                when (item) {
                    is String -> {
                        CenteredText(
                            text = item,
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(getAspectRation(index))
                                .border(width = 1.dp, color = colorScheme.tertiary)
                        )
                    }

                    is ImageVector -> {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(getAspectRation(index))
                        ) {
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
private fun LazyVerticalStaggeredGridContentTypeSamplePreview() {
    LazyVerticalStaggeredGridContentTypeSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun LazyVerticalStaggeredGridSpanSample(allExpandFlow: Flow<Boolean>) {
    val colorScheme = MaterialTheme.colorScheme
    val items = buildList<Any> {
        repeat(49) {
            add((it + 1).toString())
        }
    }.toMutableList().apply {
        set(0, Icons.Filled.KeyboardArrowLeft)
        set(10, Icons.Filled.Add)
        set(17, Icons.Filled.Menu)
        set(25, Icons.Filled.KeyboardArrowDown)
        set(34, Icons.Filled.Check)
    }.toList()
    ExpandableItem3(
        title = "LazyVerticalStaggeredGrid（span）",
        allExpandFlow,
        padding = 20.dp
    ) {
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(4),
            modifier = Modifier
                .fillMaxWidth()
                .height(360.dp)
                .background(colorScheme.primaryContainer)
        ) {
            itemsIndexed(
                items = items,
                contentType = { _, item ->
                    when (item) {
                        is String -> 0
                        else -> 1
                    }
                },
                span = { _, item ->
                    when (item) {
                        is String -> StaggeredGridItemSpan.SingleLane
                        else -> StaggeredGridItemSpan.FullLine
                    }
                }
            ) { index, item ->
                when (item) {
                    is String -> {
                        CenteredText(
                            text = item,
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(getAspectRation(index))
                                .border(width = 1.dp, color = colorScheme.tertiary)
                        )
                    }

                    is ImageVector -> {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                        ) {
                            FilledTonalIconButton(onClick = { }) {
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
private fun LazyVerticalStaggeredGridSpanSamplePreview() {
    LazyVerticalStaggeredGridSpanSample(remember { MutableStateFlow(true) })
}