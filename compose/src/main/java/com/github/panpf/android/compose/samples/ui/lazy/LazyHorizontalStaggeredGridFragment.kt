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
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyHorizontalStaggeredGrid
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
import androidx.compose.material.icons.filled.KeyboardArrowRight
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

class LazyHorizontalStaggeredGridFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "LazyHorizontalStaggeredGrid"
    }

    @Composable
    override fun DrawContent() {
        ExpandableLayout { allExpandFlow ->
            LazyHorizontalStaggeredGridSample(allExpandFlow)
            LazyHorizontalStaggeredGridRowsFixedSample(allExpandFlow)
            LazyHorizontalStaggeredGridRowsAdaptiveSample(allExpandFlow)
            LazyHorizontalStaggeredGridContentPaddingSample(allExpandFlow)
            LazyHorizontalStaggeredGridReverseLayoutSample(allExpandFlow)
            LazyHorizontalStaggeredGridVerticalArrangementSample(allExpandFlow)
            LazyHorizontalStaggeredGridItemSpacedSample(allExpandFlow)
            LazyHorizontalStaggeredGridUserScrollEnabledSample(allExpandFlow)
            LazyHorizontalStaggeredGridUserVisibleItemIndexSample(allExpandFlow)
            LazyHorizontalStaggeredGridScrollInProgressSample(allExpandFlow)
            LazyHorizontalStaggeredGridAnimateScrollToItemSample(allExpandFlow)
            LazyHorizontalStaggeredGridLayoutInfoSample(allExpandFlow)
            LazyHorizontalStaggeredGridContentTypeSample(allExpandFlow)
            LazyHorizontalStaggeredGridSpanSample(allExpandFlow)
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
    val colorScheme = MaterialTheme.colorScheme
    ExpandableItem3(title = "LazyHorizontalStaggeredGrid", allExpandFlow, padding = 20.dp) {
        LazyHorizontalStaggeredGrid(
            rows = StaggeredGridCells.Fixed(3),
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
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
private fun LazyHorizontalStaggeredGridSamplePreview() {
    LazyHorizontalStaggeredGridSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun LazyHorizontalStaggeredGridRowsFixedSample(allExpandFlow: Flow<Boolean>) {
    val colorScheme = MaterialTheme.colorScheme
    ExpandableItem3(
        title = "LazyHorizontalStaggeredGrid（rows - Fixed）",
        allExpandFlow,
        padding = 20.dp
    ) {
        listOf(3, 2, 1).forEachIndexed { index, rows ->
            if (index > 0) {
                Spacer(modifier = Modifier.size(20.dp))
            }
            Text(text = "rows=Fixed($rows)")
            Spacer(modifier = Modifier.size(10.dp))
            LazyHorizontalStaggeredGrid(
                rows = StaggeredGridCells.Fixed(rows),
                modifier = Modifier
                    .height(120.dp)
                    .background(colorScheme.primaryContainer)
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

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun LazyHorizontalStaggeredGridRowsFixedSamplePreview() {
    LazyHorizontalStaggeredGridRowsFixedSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun LazyHorizontalStaggeredGridRowsAdaptiveSample(allExpandFlow: Flow<Boolean>) {
    val colorScheme = MaterialTheme.colorScheme
    val gridHeight = remember { mutableStateOf(200.dp) }
    ExpandableItem3(
        title = "LazyHorizontalStaggeredGrid（rows - Adaptive）",
        allExpandFlow,
        padding = 20.dp
    ) {
        IconButton(
            onClick = {
                if (gridHeight.value > 80.dp) {
                    gridHeight.value -= 60.dp
                }
            }, modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
        ) {
            Icon(painterResource(id = R.drawable.ic_subtract), contentDescription = "subtract")
        }
        LazyHorizontalStaggeredGrid(
            rows = StaggeredGridCells.Adaptive(80.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(gridHeight.value)
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
        IconButton(
            onClick = {
                gridHeight.value += 60.dp
            }, modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
        ) {
            Icon(imageVector = Icons.Filled.Add, contentDescription = "add")
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun LazyHorizontalStaggeredGridRowsAdaptiveSamplePreview() {
    LazyHorizontalStaggeredGridRowsAdaptiveSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun LazyHorizontalStaggeredGridContentPaddingSample(allExpandFlow: Flow<Boolean>) {
    val colorScheme = MaterialTheme.colorScheme
    ExpandableItem3(
        title = "LazyHorizontalStaggeredGrid（contentPadding）",
        allExpandFlow,
        padding = 20.dp
    ) {
        Text(text = "PaddingValues(20.dp)")
        Spacer(modifier = Modifier.size(10.dp))
        LazyHorizontalStaggeredGrid(
            rows = StaggeredGridCells.Fixed(2),
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
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

        Spacer(modifier = Modifier.size(20.dp))
        Text(text = "PaddingValues(horizontal=20.dp, vertical=10.dp)")
        Spacer(modifier = Modifier.size(10.dp))
        LazyHorizontalStaggeredGrid(
            rows = StaggeredGridCells.Fixed(2),
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
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

        Spacer(modifier = Modifier.size(20.dp))
        Text(text = "PaddingValues(start=10.dp, top=20.dp, end=40.dp, bottom=60.dp)")
        Spacer(modifier = Modifier.size(10.dp))
        LazyHorizontalStaggeredGrid(
            rows = StaggeredGridCells.Fixed(2),
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
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

        Spacer(modifier = Modifier.size(20.dp))
        Text(text = "PaddingValues + ItemSpaced")
        Spacer(modifier = Modifier.size(10.dp))
        LazyHorizontalStaggeredGrid(
            rows = StaggeredGridCells.Fixed(2),
            modifier = Modifier
                .fillMaxWidth()
                .height(110.dp)
                .background(colorScheme.primaryContainer),
            contentPadding = PaddingValues(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalItemSpacing = 10.dp,
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

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun LazyHorizontalStaggeredGridContentPaddingSamplePreview() {
    LazyHorizontalStaggeredGridContentPaddingSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun LazyHorizontalStaggeredGridReverseLayoutSample(allExpandFlow: Flow<Boolean>) {
    val colorScheme = MaterialTheme.colorScheme
    ExpandableItem3(
        title = "LazyHorizontalStaggeredGrid（reverseLayout）",
        allExpandFlow,
        padding = 20.dp
    ) {
        LazyHorizontalStaggeredGrid(
            rows = StaggeredGridCells.Fixed(3),
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
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
private fun LazyHorizontalStaggeredGridReverseLayoutSamplePreview() {
    LazyHorizontalStaggeredGridReverseLayoutSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun LazyHorizontalStaggeredGridVerticalArrangementSample(allExpandFlow: Flow<Boolean>) {
    val colorScheme = MaterialTheme.colorScheme
    ExpandableItem3(
        title = "LazyHorizontalStaggeredGrid（verticalArrangement）",
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
                    LazyHorizontalStaggeredGrid(
                        rows = StaggeredGridCells.Fixed(3),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(140.dp)
                            .background(colorScheme.primaryContainer),
                        verticalArrangement = arrangement
                    ) {
                        items(count = 9) { index ->
                            CenteredText(
                                text = index.plus(1).toString(),
                                modifier = Modifier
                                    .requiredSize(25.dp)
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
private fun LazyHorizontalStaggeredGridVerticalArrangementSamplePreview() {
    LazyHorizontalStaggeredGridVerticalArrangementSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun LazyHorizontalStaggeredGridItemSpacedSample(allExpandFlow: Flow<Boolean>) {
    val colorScheme = MaterialTheme.colorScheme
    ExpandableItem3(
        title = "LazyHorizontalStaggeredGrid（ItemSpaced）",
        allExpandFlow,
        padding = 20.dp
    ) {
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
            LazyHorizontalStaggeredGrid(
                rows = StaggeredGridCells.Fixed(rows),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .background(colorScheme.primaryContainer),
                horizontalItemSpacing = horizontalSpacing,
                verticalArrangement = Arrangement.spacedBy(verticalSpacing),
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

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun LazyHorizontalStaggeredGridItemSpacedSamplePreview() {
    LazyHorizontalStaggeredGridItemSpacedSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun LazyHorizontalStaggeredGridUserScrollEnabledSample(allExpandFlow: Flow<Boolean>) {
    val colorScheme = MaterialTheme.colorScheme
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
private fun LazyHorizontalStaggeredGridUserScrollEnabledSamplePreview() {
    LazyHorizontalStaggeredGridUserScrollEnabledSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun LazyHorizontalStaggeredGridUserVisibleItemIndexSample(allExpandFlow: Flow<Boolean>) {
    val colorScheme = MaterialTheme.colorScheme
    val lazyListState = rememberLazyStaggeredGridState(3)
    val itemIndexState = remember { derivedStateOf { lazyListState.firstVisibleItemIndex } }
    val offsetState =
        remember { derivedStateOf { lazyListState.firstVisibleItemScrollOffset } }
    ExpandableItem3(
        title = "LazyHorizontalStaggeredGrid（firstVisibleItemIndex）",
        allExpandFlow,
        padding = 20.dp
    ) {
        LazyHorizontalStaggeredGrid(
            rows = StaggeredGridCells.Fixed(3),
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
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
private fun LazyHorizontalStaggeredGridUserVisibleItemIndexSamplePreview() {
    LazyHorizontalStaggeredGridUserVisibleItemIndexSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun LazyHorizontalStaggeredGridScrollInProgressSample(allExpandFlow: Flow<Boolean>) {
    val colorScheme = MaterialTheme.colorScheme
    val lazyListState = rememberLazyStaggeredGridState(3)
    val scrollInProgressState = remember { derivedStateOf { lazyListState.isScrollInProgress } }
    ExpandableItem3(
        title = "LazyHorizontalStaggeredGrid（isScrollInProgress）",
        allExpandFlow,
        padding = 20.dp
    ) {
        LazyHorizontalStaggeredGrid(
            rows = StaggeredGridCells.Fixed(3),
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
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
private fun LazyHorizontalStaggeredGridScrollInProgressSamplePreview() {
    LazyHorizontalStaggeredGridScrollInProgressSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun LazyHorizontalStaggeredGridAnimateScrollToItemSample(allExpandFlow: Flow<Boolean>) {
    val colorScheme = MaterialTheme.colorScheme
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
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowLeft,
                    contentDescription = "before"
                )
            }
            LazyHorizontalStaggeredGrid(
                rows = StaggeredGridCells.Fixed(3),
                modifier = Modifier
                    .weight(1f)
                    .height(200.dp)
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
                    if (firstVisibleItemIndex < 50 - 3) {
                        coroutineScope.launch {
                            lazyListState.animateScrollToItem(firstVisibleItemIndex + 3)
                        }
                    }
                },
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowRight,
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
    val colorScheme = MaterialTheme.colorScheme
    val lazyGridState = rememberLazyStaggeredGridState()
    val layoutInfoState = remember { derivedStateOf { lazyGridState.layoutInfo } }
    ExpandableItem3(
        title = "LazyHorizontalStaggeredGrid（layoutInfo）",
        allExpandFlow,
        padding = 20.dp
    ) {
        LazyHorizontalStaggeredGrid(
            state = lazyGridState,
            rows = StaggeredGridCells.Fixed(3),
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
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
private fun LazyHorizontalStaggeredGridLayoutInfoSamplePreview() {
    LazyHorizontalStaggeredGridLayoutInfoSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun LazyHorizontalStaggeredGridContentTypeSample(allExpandFlow: Flow<Boolean>) {
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
        title = "LazyHorizontalStaggeredGrid（contentType）",
        allExpandFlow,
        padding = 20.dp
    ) {
        LazyHorizontalStaggeredGrid(
            rows = StaggeredGridCells.Fixed(3),
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
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
                            text = index.plus(1).toString(),
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
private fun LazyHorizontalStaggeredGridContentTypeSamplePreview() {
    LazyHorizontalStaggeredGridContentTypeSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun LazyHorizontalStaggeredGridSpanSample(allExpandFlow: Flow<Boolean>) {
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
        title = "LazyHorizontalStaggeredGrid（span）",
        allExpandFlow,
        padding = 20.dp
    ) {
        LazyHorizontalStaggeredGrid(
            rows = StaggeredGridCells.Fixed(3),
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
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
                            text = index.plus(1).toString(),
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
                            FilledTonalIconButton(
                                onClick = { },
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
private fun LazyHorizontalStaggeredGridSpanSamplePreview() {
    LazyHorizontalStaggeredGridSpanSample(remember { MutableStateFlow(true) })
}