package com.github.panpf.android.compose.samples.ui.components.basic

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyHorizontalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.itemsIndexed
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.material3.FilledTonalIconButton
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.panpf.android.compose.samples.R
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem3
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
import com.github.panpf.android.compose.samples.ui.base.MyColor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class LazyHorizontalStaggeredGridFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "LazyHorizontalStaggeredGrid"
    }

    @Composable
    override fun DrawContent() {
        ExpandableLayout { allExpandFlow ->
            LazyHorizontalStaggeredGridSample(allExpandFlow)
            LazyHorizontalStaggeredGridColumnsDynamicCellsSample(allExpandFlow)
            LazyHorizontalStaggeredGridContentPaddingSample(allExpandFlow)
            LazyHorizontalStaggeredGridItemSpacedSample(allExpandFlow)
            LazyHorizontalStaggeredGridHorizontalArrangementSample(allExpandFlow)
            LazyHorizontalStaggeredGridVerticalArrangementSample(allExpandFlow)
            LazyHorizontalStaggeredGridUserScrollEnabledSample(allExpandFlow)
            LazyHorizontalStaggeredGridUserVisibleItemIndexSample(allExpandFlow)
            LazyHorizontalStaggeredGridScrollInProgressSample(allExpandFlow)
            LazyHorizontalStaggeredGridAnimateScrollToItemSample(allExpandFlow)
            LazyHorizontalStaggeredGridLayoutInfoSample(allExpandFlow)
            LazyHorizontalStaggeredGridMultiTypeSample(allExpandFlow)
        }
    }
}

private fun getAspectRation(index: Int): Float =
    when {
        index.plus(1) % 3 == 0 -> 1.2f
        index.plus(1) % 7 == 0 -> 1.5f
        else -> 1f
    }


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun LazyHorizontalStaggeredGridSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyColor.rainbows
    ExpandableItem3(title = "LazyHorizontalStaggeredGrid", allExpandFlow, padding = 20.dp) {
        LazyHorizontalStaggeredGrid(
            rows = StaggeredGridCells.Fixed(3),
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                .padding(2.dp)
        ) {
            items(count = 50) { index ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(getAspectRation(index))
                        .background(colors[index % colors.size].copy(alpha = 0.5f))
                        .border(1.dp, colors[index % colors.size])
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

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun LazyHorizontalStaggeredGridSamplePreview() {
    LazyHorizontalStaggeredGridSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun LazyHorizontalStaggeredGridColumnsDynamicCellsSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyColor.rainbows
    val gridHeight = remember { mutableStateOf(200.dp) }
    ExpandableItem3(
        title = "LazyHorizontalStaggeredGrid（rows - AdaptiveCells）",
        allExpandFlow,
        padding = 20.dp
    ) {
        Column {
            IconButton(
                onClick = {
                    if (gridHeight.value > 80.dp) {
                        gridHeight.value -= 60.dp
                    }
                }, modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
            ) {
                Image(painterResource(id = R.drawable.ic_subtract), contentDescription = "subtract")
            }
            LazyHorizontalStaggeredGrid(
                rows = StaggeredGridCells.Adaptive(80.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(gridHeight.value)
                    .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                    .padding(2.dp)
            ) {
                items(count = 50) { index ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(getAspectRation(index))
                            .background(colors[index % colors.size].copy(alpha = 0.5f))
                            .border(1.dp, colors[index % colors.size])
                    ) {
                        Text(
                            text = index.plus(1).toString(),
                            modifier = Modifier
                                .align(Alignment.Center)
                        )
                    }
                }
            }
            IconButton(
                onClick = {
                    gridHeight.value += 60.dp
                }, modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
            ) {
                Image(painterResource(id = R.drawable.ic_add), contentDescription = "add")
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun LazyHorizontalStaggeredGridColumnsDynamicCellsSamplePreview() {
    LazyHorizontalStaggeredGridColumnsDynamicCellsSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun LazyHorizontalStaggeredGridContentPaddingSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyColor.rainbows
    ExpandableItem3(
        title = "LazyHorizontalStaggeredGrid（contentPadding）",
        allExpandFlow,
        padding = 20.dp
    ) {
        LazyHorizontalStaggeredGrid(
            rows = StaggeredGridCells.Fixed(3),
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                .padding(2.dp),
            contentPadding = PaddingValues(20.dp)
        ) {
            items(count = 50) { index ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(getAspectRation(index))
                        .background(colors[index % colors.size].copy(alpha = 0.5f))
                        .border(1.dp, colors[index % colors.size])
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

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun LazyHorizontalStaggeredGridContentPaddingSamplePreview() {
    LazyHorizontalStaggeredGridContentPaddingSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun LazyHorizontalStaggeredGridItemSpacedSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyColor.rainbows
    ExpandableItem3(
        title = "LazyHorizontalStaggeredGrid（ItemSpaced）",
        allExpandFlow,
        padding = 20.dp
    ) {
        LazyHorizontalStaggeredGrid(
            rows = StaggeredGridCells.Fixed(3),
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                .padding(2.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            items(count = 50) { index ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(getAspectRation(index))
                        .background(colors[index % colors.size].copy(alpha = 0.5f))
                        .border(1.dp, colors[index % colors.size])
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

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun LazyHorizontalStaggeredGridItemSpacedSamplePreview() {
    LazyHorizontalStaggeredGridItemSpacedSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun LazyHorizontalStaggeredGridHorizontalArrangementSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyColor.rainbows
    ExpandableItem3(
        title = "LazyHorizontalStaggeredGrid（horizontalArrangement）",
        allExpandFlow,
        padding = 20.dp
    ) {
        Column {
            listOf(
                Arrangement.Start to "Start",
                Arrangement.Center to "Center",
                Arrangement.End to "End",
                null to "Space",
                Arrangement.SpaceBetween to "SpaceBetween",
                Arrangement.SpaceAround to "SpaceAround",
                Arrangement.SpaceEvenly to "SpaceEvenly",
            ).forEachIndexed { index, (arrangement, name) ->
                if (index > 0) {
                    Spacer(modifier = Modifier.size(10.dp))
                }
                Column {
                    Text(text = name)
                    LazyHorizontalStaggeredGrid(
                        rows = StaggeredGridCells.Fixed(2),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(110.dp)
                            .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                            .padding(2.dp),
                        horizontalArrangement = arrangement ?: Arrangement.spacedBy(10.dp),
                    ) {
                        items(count = 9) { index ->
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .aspectRatio(getAspectRation(index))
                                    .background(colors[index % colors.size].copy(alpha = 0.5f))
                                    .border(1.dp, colors[index % colors.size])
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
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun LazyHorizontalStaggeredGridHorizontalArrangementSamplePreview() {
    LazyHorizontalStaggeredGridHorizontalArrangementSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun LazyHorizontalStaggeredGridVerticalArrangementSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyColor.rainbows
    ExpandableItem3(
        title = "LazyHorizontalStaggeredGrid（verticalArrangement）",
        allExpandFlow,
        padding = 20.dp
    ) {
        Column {
            listOf(
                Arrangement.Top to "Top",
                Arrangement.Center to "Center",
                Arrangement.Bottom to "Bottom",
                null to "Space",
                Arrangement.SpaceBetween to "SpaceBetween",
                Arrangement.SpaceAround to "SpaceAround",
                Arrangement.SpaceEvenly to "SpaceEvenly",
            ).forEachIndexed { index, (arrangement, name) ->
                if (index > 0) {
                    Spacer(modifier = Modifier.size(10.dp))
                }
                Column {
                    Text(text = name)
                    LazyHorizontalStaggeredGrid(
                        rows = StaggeredGridCells.Fixed(1),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(110.dp)
                            .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                            .padding(2.dp),
                        verticalArrangement = arrangement ?: Arrangement.spacedBy(10.dp)
                    ) {
                        items(count = 9) { index ->
                            Box(
                                modifier = Modifier
                                    .size(40.dp)
                                    .background(colors[index % colors.size].copy(alpha = 0.5f))
                                    .border(1.dp, colors[index % colors.size])
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
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun LazyHorizontalStaggeredGridVerticalArrangementSamplePreview() {
    LazyHorizontalStaggeredGridVerticalArrangementSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun LazyHorizontalStaggeredGridUserScrollEnabledSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyColor.rainbows
    ExpandableItem3(
        title = "LazyHorizontalStaggeredGrid（userScrollEnabled = false）",
        allExpandFlow,
        padding = 20.dp
    ) {
        LazyHorizontalStaggeredGrid(
            rows = StaggeredGridCells.Fixed(3),
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                .padding(2.dp),
            userScrollEnabled = false
        ) {
            items(count = 50) { index ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(getAspectRation(index))
                        .background(colors[index % colors.size].copy(alpha = 0.5f))
                        .border(1.dp, colors[index % colors.size])
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

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun LazyHorizontalStaggeredGridUserScrollEnabledSamplePreview() {
    LazyHorizontalStaggeredGridUserScrollEnabledSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun LazyHorizontalStaggeredGridUserVisibleItemIndexSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyColor.rainbows
    val lazyListState = rememberLazyStaggeredGridState(3)
    val itemIndexState = remember { derivedStateOf { lazyListState.firstVisibleItemIndex } }
    val offsetState =
        remember { derivedStateOf { lazyListState.firstVisibleItemScrollOffset } }
    ExpandableItem3(
        title = "LazyHorizontalStaggeredGrid（firstVisibleItemIndex）",
        allExpandFlow,
        padding = 20.dp
    ) {
        Column {
            LazyHorizontalStaggeredGrid(
                rows = StaggeredGridCells.Fixed(3),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                    .padding(2.dp),
                state = lazyListState
            ) {
                items(count = 50) { index ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(getAspectRation(index))
                            .background(colors[index % colors.size].copy(alpha = 0.5f))
                            .border(1.dp, colors[index % colors.size])
                    ) {
                        Text(
                            text = index.plus(1).toString(),
                            modifier = Modifier
                                .align(Alignment.Center)
                        )
                    }
                }
            }
            Text(text = "firstVisibleItemIndex: ${itemIndexState.value}, firstVisibleItemScrollOffset: ${offsetState.value}")
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun LazyHorizontalStaggeredGridUserVisibleItemIndexSamplePreview() {
    LazyHorizontalStaggeredGridUserVisibleItemIndexSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun LazyHorizontalStaggeredGridScrollInProgressSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyColor.rainbows
    val lazyListState = rememberLazyStaggeredGridState(3)
    val scrollInProgressState = remember { derivedStateOf { lazyListState.isScrollInProgress } }
    ExpandableItem3(
        title = "LazyHorizontalStaggeredGrid（isScrollInProgress）",
        allExpandFlow,
        padding = 20.dp
    ) {
        Column {
            LazyHorizontalStaggeredGrid(
                rows = StaggeredGridCells.Fixed(3),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                    .padding(2.dp),
                state = lazyListState
            ) {
                items(count = 50) { index ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(getAspectRation(index))
                            .background(colors[index % colors.size].copy(alpha = 0.5f))
                            .border(1.dp, colors[index % colors.size])
                    ) {
                        Text(
                            text = index.plus(1).toString(),
                            modifier = Modifier
                                .align(Alignment.Center)
                        )
                    }
                }
            }
            Text(text = "isScrollInProgress: ${scrollInProgressState.value}")
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun LazyHorizontalStaggeredGridScrollInProgressSamplePreview() {
    LazyHorizontalStaggeredGridScrollInProgressSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun LazyHorizontalStaggeredGridAnimateScrollToItemSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyColor.rainbows
    val lazyListState = rememberLazyStaggeredGridState(3)
    val coroutineScope = rememberCoroutineScope()
    ExpandableItem3(
        title = "LazyHorizontalStaggeredGrid（animateScrollToItem）",
        allExpandFlow,
        padding = 20.dp
    ) {
        Row {
            IconButton(
                onClick = {
                    val firstVisibleItemIndex = lazyListState.firstVisibleItemIndex
                    if (firstVisibleItemIndex >= 3) {
                        coroutineScope.launch {
                            lazyListState.animateScrollToItem(firstVisibleItemIndex - 3)
                        }
                    }
                },
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_arrow_left),
                    contentDescription = "before"
                )
            }
            LazyHorizontalStaggeredGrid(
                rows = StaggeredGridCells.Fixed(3),
                modifier = Modifier
                    .weight(1f)
                    .height(200.dp)
                    .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                    .padding(2.dp),
                state = lazyListState
            ) {
                items(count = 50) { index ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(getAspectRation(index))
                            .background(colors[index % colors.size].copy(alpha = 0.5f))
                            .border(1.dp, colors[index % colors.size])
                    ) {
                        Text(
                            text = index.plus(1).toString(),
                            modifier = Modifier
                                .align(Alignment.Center)
                        )
                    }
                }
            }
            IconButton(
                onClick = {
                    val firstVisibleItemIndex = lazyListState.firstVisibleItemIndex
                    if (firstVisibleItemIndex < 50 - 3) {
                        coroutineScope.launch {
                            lazyListState.animateScrollToItem(firstVisibleItemIndex + 3)
                        }
                    }
                },
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_arrow_right),
                    contentDescription = "next",
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun LazyHorizontalStaggeredGridAnimateScrollToItemSamplePreview() {
    LazyHorizontalStaggeredGridAnimateScrollToItemSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun LazyHorizontalStaggeredGridLayoutInfoSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyColor.rainbows
    val lazyGridState = rememberLazyStaggeredGridState()
    val layoutInfoState = remember { derivedStateOf { lazyGridState.layoutInfo } }
    ExpandableItem3(
        title = "LazyHorizontalStaggeredGrid（layoutInfo）",
        allExpandFlow,
        padding = 20.dp
    ) {
        Column {
            LazyHorizontalStaggeredGrid(
                state = lazyGridState,
                rows = StaggeredGridCells.Fixed(3),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                    .padding(2.dp)
            ) {
                items(count = 50) { index ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(getAspectRation(index))
                            .background(colors[index % colors.size].copy(alpha = 0.5f))
                            .border(1.dp, colors[index % colors.size])
                    ) {
                        Text(
                            text = index.plus(1).toString(),
                            modifier = Modifier
                                .align(Alignment.Center)
                        )
                    }
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
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun LazyHorizontalStaggeredGridLayoutInfoSamplePreview() {
    LazyHorizontalStaggeredGridLayoutInfoSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun LazyHorizontalStaggeredGridMultiTypeSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyColor.rainbows
    val items = buildList<Any> {
        repeat(49) {
            add((it + 1).toString())
        }
    }.toMutableList().apply {
        set(1, R.drawable.ic_arrow_left)
        set(3, R.drawable.ic_add)
        set(10, R.drawable.ic_games)
        set(17, R.drawable.ic_arrow_down)
        set(18, R.drawable.ic_check)
        set(40, R.drawable.ic_info)
    }.toList()
    ExpandableItem3(
        title = "LazyHorizontalStaggeredGrid（MultiType）",
        allExpandFlow,
        padding = 20.dp
    ) {
        LazyHorizontalStaggeredGrid(
            rows = StaggeredGridCells.Fixed(3),
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                .padding(2.dp)
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
            ) { index, item ->
                when (item) {
                    is String -> {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(getAspectRation(index))
                                .background(colors[index % colors.size].copy(alpha = 0.5f))
                                .border(1.dp, colors[index % colors.size])
                        ) {
                            Text(
                                text = index.plus(1).toString(),
                                modifier = Modifier
                                    .align(Alignment.Center)
                            )
                        }
                    }
                    is Int -> {
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
                                Image(
                                    painter = painterResource(id = item),
                                    contentDescription = "icon"
                                )
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
private fun LazyHorizontalStaggeredGridMultiTypeSamplePreview() {
    LazyHorizontalStaggeredGridMultiTypeSample(remember { MutableStateFlow(true) })
}