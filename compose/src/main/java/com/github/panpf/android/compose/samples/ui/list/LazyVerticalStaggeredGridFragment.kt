package com.github.panpf.android.compose.samples.ui.list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
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
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem3
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
import com.github.panpf.android.compose.samples.ui.base.MyColor
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
            LazyVerticalStaggeredGridColumnsDynamicCellsSample(allExpandFlow)
            LazyVerticalStaggeredGridContentPaddingSample(allExpandFlow)
            LazyVerticalStaggeredGridReverseLayoutSample(allExpandFlow)
            LazyVerticalStaggeredGridItemSpacedSample(allExpandFlow)
            LazyVerticalStaggeredGridVerticalItemSpacingSample(allExpandFlow)
            LazyVerticalStaggeredGridHorizontalArrangementSample(allExpandFlow)
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
    val colors = MyColor.rainbows
    ExpandableItem3(title = "LazyVerticalStaggeredGrid", allExpandFlow, padding = 20.dp) {
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(4),
            modifier = Modifier
                .fillMaxWidth()
                .height(360.dp)
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
private fun LazyVerticalStaggeredGridSamplePreview() {
    LazyVerticalStaggeredGridSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun LazyVerticalStaggeredGridColumnsDynamicCellsSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyColor.rainbows
    val items = remember {
        buildList {
            repeat(49) {
                add((it + 1).toString())
            }
        }
    }
    val gridHeight = remember { mutableStateOf(200.dp) }
    ExpandableItem3(
        title = "LazyVerticalStaggeredGrid（columns - AdaptiveCells）",
        allExpandFlow,
        padding = 20.dp
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Adaptive(80.dp),
                modifier = Modifier
                    .width(gridHeight.value)
                    .height(360.dp)
                    .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                    .padding(2.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                itemsIndexed(items) { index, item ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(getAspectRation(index))
                            .background(colors[index % colors.size].copy(alpha = 0.5f))
                            .border(1.dp, colors[index % colors.size])
                    ) {
                        Text(
                            text = item,
                            modifier = Modifier
                                .align(Alignment.Center)
                        )
                    }
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
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun LazyVerticalStaggeredGridColumnsDynamicCellsSamplePreview() {
    LazyVerticalStaggeredGridColumnsDynamicCellsSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun LazyVerticalStaggeredGridContentPaddingSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyColor.rainbows
    val items = remember {
        buildList {
            repeat(49) {
                add((it + 1).toString())
            }
        }
    }
    ExpandableItem3(
        title = "LazyVerticalStaggeredGrid（contentPadding）",
        allExpandFlow,
        padding = 20.dp
    ) {
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(4),
            modifier = Modifier
                .fillMaxWidth()
                .height(360.dp)
                .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                .padding(2.dp),
            contentPadding = PaddingValues(20.dp)
        ) {
            itemsIndexed(items) { index, item ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(getAspectRation(index))
                        .background(colors[index % colors.size].copy(alpha = 0.5f))
                        .border(1.dp, colors[index % colors.size])
                ) {
                    Text(
                        text = item,
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
private fun LazyVerticalStaggeredGridContentPaddingSamplePreview() {
    LazyVerticalStaggeredGridContentPaddingSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun LazyVerticalStaggeredGridReverseLayoutSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyColor.rainbows
    ExpandableItem3(title = "LazyVerticalStaggeredGrid（reverseLayout）", allExpandFlow, padding = 20.dp) {
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(4),
            modifier = Modifier
                .fillMaxWidth()
                .height(360.dp)
                .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                .padding(2.dp),
            reverseLayout = true
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
private fun LazyVerticalStaggeredGridReverseLayoutSamplePreview() {
    LazyVerticalStaggeredGridReverseLayoutSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun LazyVerticalStaggeredGridItemSpacedSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyColor.rainbows
    val items = remember {
        buildList {
            repeat(49) {
                add((it + 1).toString())
            }
        }
    }
    ExpandableItem3(
        title = "LazyVerticalStaggeredGrid（ItemSpaced）",
        allExpandFlow,
        padding = 20.dp
    ) {
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(4),
            modifier = Modifier
                .fillMaxWidth()
                .height(360.dp)
                .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                .padding(2.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalItemSpacing = 10.dp,
        ) {
            itemsIndexed(items) { index, item ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(getAspectRation(index))
                        .background(colors[index % colors.size].copy(alpha = 0.5f))
                        .border(1.dp, colors[index % colors.size])
                ) {
                    Text(
                        text = item,
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
private fun LazyVerticalStaggeredGridItemSpacedSamplePreview() {
    LazyVerticalStaggeredGridItemSpacedSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun LazyVerticalStaggeredGridVerticalItemSpacingSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyColor.rainbows
    val items = remember {
        buildList {
            repeat(49) {
                add((it + 1).toString())
            }
        }
    }
    ExpandableItem3(
        title = "LazyVerticalStaggeredGrid（verticalItemSpacing）",
        allExpandFlow,
        padding = 20.dp
    ) {
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(4),
            modifier = Modifier
                .fillMaxWidth()
                .height(360.dp)
                .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                .padding(2.dp),
            verticalItemSpacing = 20.dp,
        ) {
            itemsIndexed(items) { index, item ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(getAspectRation(index))
                        .background(colors[index % colors.size].copy(alpha = 0.5f))
                        .border(1.dp, colors[index % colors.size])
                ) {
                    Text(
                        text = item,
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
private fun LazyVerticalStaggeredGridVerticalItemSpacingSamplePreview() {
    LazyVerticalStaggeredGridVerticalItemSpacingSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun LazyVerticalStaggeredGridHorizontalArrangementSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyColor.rainbows
    val items = buildList {
        repeat(9) {
            add((it + 1).toString())
        }
    }
    ExpandableItem3(
        title = "LazyVerticalStaggeredGrid（horizontalArrangement）（无效）",
        allExpandFlow,
        padding = 20.dp
    ) {
        FlowRow(mainAxisSpacing = 10.dp, crossAxisSpacing = 10.dp) {
            listOf(
                Arrangement.Start to "Start",
                Arrangement.Center to "Center",
                Arrangement.End to "End",
                null to "Space=10.dp",
                Arrangement.SpaceBetween to "SpaceBetween",
                Arrangement.SpaceAround to "SpaceAround",
                Arrangement.SpaceEvenly to "SpaceEvenly",
            ).forEach { (arrangement, name) ->
                Column {
                    Text(text = name)
                    LazyVerticalStaggeredGrid(
                        columns = StaggeredGridCells.Fixed(3),
                        modifier = Modifier
                            .width(110.dp)
                            .height(130.dp)
                            .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                            .padding(2.dp),
                        horizontalArrangement = arrangement ?: Arrangement.spacedBy(10.dp)  // todo Invalid
                    ) {
                        itemsIndexed(items) { index, item ->
                            Box(
                                modifier = Modifier
                                    .requiredSize(25.dp)
                                    .background(colors[index % colors.size].copy(alpha = 0.5f))
                                    .border(1.dp, colors[index % colors.size])
                            ) {
                                Text(
                                    text = item,
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
private fun LazyVerticalStaggeredGridHorizontalArrangementSamplePreview() {
    LazyVerticalStaggeredGridHorizontalArrangementSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun LazyVerticalStaggeredGridUserScrollEnabledSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyColor.rainbows
    val items = remember {
        buildList {
            repeat(49) {
                add((it + 1).toString())
            }
        }
    }
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
                .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                .padding(2.dp),
            userScrollEnabled = false
        ) {
            itemsIndexed(items) { index, item ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(getAspectRation(index))
                        .background(colors[index % colors.size].copy(alpha = 0.5f))
                        .border(1.dp, colors[index % colors.size])
                ) {
                    Text(
                        text = item,
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
private fun LazyVerticalStaggeredGridUserScrollEnabledSamplePreview() {
    LazyVerticalStaggeredGridUserScrollEnabledSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun LazyVerticalStaggeredGridUserVisibleItemIndexSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyColor.rainbows
    val items = remember {
        buildList {
            repeat(49) {
                add((it + 1).toString())
            }
        }
    }
    val lazyListState = rememberLazyStaggeredGridState(4)
    val itemIndexState = remember { derivedStateOf { lazyListState.firstVisibleItemIndex } }
    val offsetState =
        remember { derivedStateOf { lazyListState.firstVisibleItemScrollOffset } }
    ExpandableItem3(
        title = "LazyVerticalStaggeredGrid（firstVisibleItemIndex）",
        allExpandFlow,
        padding = 20.dp
    ) {
        Column {
            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Fixed(4),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(360.dp)
                    .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                    .padding(2.dp),
                state = lazyListState
            ) {
                itemsIndexed(items) { index, item ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(getAspectRation(index))
                            .background(colors[index % colors.size].copy(alpha = 0.5f))
                            .border(1.dp, colors[index % colors.size])
                    ) {
                        Text(
                            text = item,
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
private fun LazyVerticalStaggeredGridUserVisibleItemIndexSamplePreview() {
    LazyVerticalStaggeredGridUserVisibleItemIndexSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun LazyVerticalStaggeredGridScrollInProgressSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyColor.rainbows
    val items = remember {
        buildList {
            repeat(49) {
                add((it + 1).toString())
            }
        }
    }
    val lazyListState = rememberLazyStaggeredGridState(3)
    val scrollInProgressState = remember { derivedStateOf { lazyListState.isScrollInProgress } }
    ExpandableItem3(
        title = "LazyVerticalStaggeredGrid（isScrollInProgress）",
        allExpandFlow,
        padding = 20.dp
    ) {
        Column {
            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Fixed(4),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(360.dp)
                    .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                    .padding(2.dp),
                state = lazyListState
            ) {
                itemsIndexed(items) { index, item ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(getAspectRation(index))
                            .background(colors[index % colors.size].copy(alpha = 0.5f))
                            .border(1.dp, colors[index % colors.size])
                    ) {
                        Text(
                            text = item,
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
private fun LazyVerticalStaggeredGridScrollInProgressSamplePreview() {
    LazyVerticalStaggeredGridScrollInProgressSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun LazyVerticalStaggeredGridAnimateScrollToItemSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyColor.rainbows
    val items = remember {
        buildList {
            repeat(49) {
                add((it + 1).toString())
            }
        }
    }
    val lazyListState = rememberLazyStaggeredGridState(3)
    val coroutineScope = rememberCoroutineScope()
    ExpandableItem3(
        title = "LazyVerticalStaggeredGrid（animateScrollToItem）",
        allExpandFlow,
        padding = 20.dp
    ) {
        Column {
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
                    .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                    .padding(2.dp),
                state = lazyListState
            ) {
                itemsIndexed(items) { index, item ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(getAspectRation(index))
                            .background(colors[index % colors.size].copy(alpha = 0.5f))
                            .border(1.dp, colors[index % colors.size])
                    ) {
                        Text(
                            text = item,
                            modifier = Modifier
                                .align(Alignment.Center)
                        )
                    }
                }
            }
            IconButton(
                onClick = {
                    val firstVisibleItemIndex = lazyListState.firstVisibleItemIndex
                    if (firstVisibleItemIndex < items.size - 4) {
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
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun LazyVerticalStaggeredGridAnimateScrollToItemSamplePreview() {
    LazyVerticalStaggeredGridAnimateScrollToItemSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun LazyVerticalStaggeredGridLayoutInfoSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyColor.rainbows
    val items = remember {
        buildList {
            repeat(49) {
                add((it + 1).toString())
            }
        }
    }
    val lazyGridState = rememberLazyStaggeredGridState()
    val layoutInfoState = remember { derivedStateOf { lazyGridState.layoutInfo } }
    ExpandableItem3(
        title = "LazyVerticalStaggeredGrid（layoutInfo）",
        allExpandFlow,
        padding = 20.dp
    ) {
        Column {
            LazyVerticalStaggeredGrid(
                state = lazyGridState,
                columns = StaggeredGridCells.Fixed(4),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(360.dp)
                    .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                    .padding(2.dp)
            ) {
                itemsIndexed(items) { index, item ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(getAspectRation(index))
                            .background(colors[index % colors.size].copy(alpha = 0.5f))
                            .border(1.dp, colors[index % colors.size])
                    ) {
                        Text(
                            text = item,
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
private fun LazyVerticalStaggeredGridLayoutInfoSamplePreview() {
    LazyVerticalStaggeredGridLayoutInfoSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun LazyVerticalStaggeredGridContentTypeSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyColor.rainbows
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
                .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                .padding(2.dp)
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
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(getAspectRation(index))
                                .background(colors[index % colors.size].copy(alpha = 0.5f))
                                .border(1.dp, colors[index % colors.size])
                        ) {
                            Text(
                                text = item,
                                modifier = Modifier
                                    .align(Alignment.Center)
                            )
                        }
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
    val colors = MyColor.rainbows
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
                .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                .padding(2.dp)
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
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(getAspectRation(index))
                                .background(colors[index % colors.size].copy(alpha = 0.5f))
                                .border(1.dp, colors[index % colors.size])
                        ) {
                            Text(
                                text = item,
                                modifier = Modifier
                                    .align(Alignment.Center)
                            )
                        }
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