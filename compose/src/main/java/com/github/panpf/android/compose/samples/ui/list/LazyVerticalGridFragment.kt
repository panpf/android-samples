package com.github.panpf.android.compose.samples.ui.list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
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
            LazyVerticalGridColumnsDynamicCellsSample(allExpandFlow)
            LazyVerticalGridContentPaddingSample(allExpandFlow)
            LazyVerticalGridItemSpacedSample(allExpandFlow)
            LazyVerticalGridReverseLayoutSample(allExpandFlow)
            LazyVerticalGridVerticalArrangementSample(allExpandFlow)
            LazyVerticalGridHorizontalArrangementSample(allExpandFlow)
            LazyVerticalGridUserScrollEnabledSample(allExpandFlow)
            LazyVerticalGridUserVisibleItemIndexSample(allExpandFlow)
            LazyVerticalGridScrollInProgressSample(allExpandFlow)
            LazyVerticalGridAnimateScrollToItemSample(allExpandFlow)
            LazyVerticalGridSpanSample(allExpandFlow)
            LazyVerticalGridAnimateItemPlacementSample(allExpandFlow)
            LazyVerticalGridLayoutInfoSample(allExpandFlow)
            LazyVerticalGridMultiTypeSample(allExpandFlow)
        }
    }
}


@Composable
private fun LazyVerticalGridSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyColor.halfRainbows
    ExpandableItem3(title = "LazyVerticalGrid", allExpandFlow, padding = 20.dp) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
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
                        .aspectRatio(1f)
                        .background(colors[index % colors.size])
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
private fun LazyVerticalGridSamplePreview() {
    LazyVerticalGridSample(remember { MutableStateFlow(true) })
}


@Composable
private fun LazyVerticalGridColumnsDynamicCellsSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyColor.halfRainbows
    val gridHeight = remember { mutableStateOf(200.dp) }
    ExpandableItem3(
        title = "LazyVerticalGrid???columns - AdaptiveCells???",
        allExpandFlow,
        padding = 20.dp
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(80.dp),
                modifier = Modifier
                    .width(gridHeight.value)
                    .height(360.dp)
                    .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                    .padding(2.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                items(count = 50) { index ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1f)
                            .background(colors[index % colors.size])
                    ) {
                        Text(
                            text = index.plus(1).toString(),
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
                    Image(
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
                    Image(painterResource(id = R.drawable.ic_add), contentDescription = "add")
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun LazyVerticalGridColumnsDynamicCellsSamplePreview() {
    LazyVerticalGridColumnsDynamicCellsSample(remember { MutableStateFlow(true) })
}


@Composable
private fun LazyVerticalGridContentPaddingSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyColor.halfRainbows
    ExpandableItem3(
        title = "LazyVerticalGrid???contentPadding???",
        allExpandFlow,
        padding = 20.dp
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            modifier = Modifier
                .fillMaxWidth()
                .height(360.dp)
                .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                .padding(2.dp),
            contentPadding = PaddingValues(20.dp)
        ) {
            items(count = 50) { index ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .background(colors[index % colors.size])
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
private fun LazyVerticalGridContentPaddingSamplePreview() {
    LazyVerticalGridContentPaddingSample(remember { MutableStateFlow(true) })
}


@Composable
private fun LazyVerticalGridItemSpacedSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyColor.halfRainbows
    ExpandableItem3(
        title = "LazyVerticalGrid???ItemSpaced???",
        allExpandFlow,
        padding = 20.dp
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            modifier = Modifier
                .fillMaxWidth()
                .height(360.dp)
                .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                .padding(2.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            items(count = 50) { index ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .background(colors[index % colors.size])
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
private fun LazyVerticalGridItemSpacedSamplePreview() {
    LazyVerticalGridItemSpacedSample(remember { MutableStateFlow(true) })
}


@Composable
private fun LazyVerticalGridReverseLayoutSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyColor.halfRainbows
    ExpandableItem3(
        title = "LazyVerticalGrid???reverseLayout???",
        allExpandFlow,
        padding = 20.dp
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
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
                        .aspectRatio(1f)
                        .background(colors[index % colors.size])
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
private fun LazyVerticalGridReverseLayoutSamplePreview() {
    LazyVerticalGridReverseLayoutSample(remember { MutableStateFlow(true) })
}


@Composable
private fun LazyVerticalGridVerticalArrangementSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyColor.halfRainbows
    ExpandableItem3(
        title = "LazyVerticalGrid???verticalArrangement???",
        allExpandFlow,
        padding = 20.dp
    ) {
        FlowRow(mainAxisSpacing = 10.dp, crossAxisSpacing = 10.dp) {
            listOf(
                Arrangement.Top to "Top",
                Arrangement.Center to "Center",
                Arrangement.Bottom to "Bottom",
                null to "Space",
                Arrangement.SpaceBetween to "SpaceBetween",
                Arrangement.SpaceAround to "SpaceAround",
                Arrangement.SpaceEvenly to "SpaceEvenly",
            ).forEach { (arrangement, name) ->
                Column {
                    Text(text = name)
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        modifier = Modifier
                            .width(110.dp)
                            .height(200.dp)
                            .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                            .padding(2.dp),
                        verticalArrangement = arrangement ?: Arrangement.spacedBy(10.dp),
                    ) {
                        items(count = 5) { index ->
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .aspectRatio(1f)
                                    .background(colors[index % colors.size])
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
private fun LazyVerticalGridVerticalArrangementSamplePreview() {
    LazyVerticalGridVerticalArrangementSample(remember { MutableStateFlow(true) })
}


@Composable
private fun LazyVerticalGridHorizontalArrangementSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyColor.halfRainbows
    ExpandableItem3(
        title = "LazyVerticalGrid???horizontalArrangement???",
        allExpandFlow,
        padding = 20.dp
    ) {
        FlowRow(mainAxisSpacing = 10.dp, crossAxisSpacing = 10.dp) {
            listOf(
                Arrangement.Start to "Start",
                Arrangement.Center to "Center",
                Arrangement.End to "End",
                null to "Space",
                Arrangement.SpaceBetween to "SpaceBetween",
                Arrangement.SpaceAround to "SpaceAround",
                Arrangement.SpaceEvenly to "SpaceEvenly",
            ).forEach { (arrangement, name) ->
                Column {
                    Text(text = name)
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(1),
                        modifier = Modifier
                            .width(110.dp)
                            .height(200.dp)
                            .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                            .padding(2.dp),
                        horizontalArrangement = arrangement ?: Arrangement.spacedBy(10.dp)
                    ) {
                        items(count = 9) { index ->
                            Box(
                                modifier = Modifier
                                    .size(40.dp)
                                    .background(colors[index % colors.size])
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
private fun LazyVerticalGridHorizontalArrangementSamplePreview() {
    LazyVerticalGridHorizontalArrangementSample(remember { MutableStateFlow(true) })
}


@Composable
private fun LazyVerticalGridUserScrollEnabledSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyColor.halfRainbows
    ExpandableItem3(
        title = "LazyVerticalGrid???userScrollEnabled = false???",
        allExpandFlow,
        padding = 20.dp
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            modifier = Modifier
                .fillMaxWidth()
                .height(360.dp)
                .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                .padding(2.dp),
            userScrollEnabled = false
        ) {
            items(count = 50) { index ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .background(colors[index % colors.size])
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
private fun LazyVerticalGridUserScrollEnabledSamplePreview() {
    LazyVerticalGridUserScrollEnabledSample(remember { MutableStateFlow(true) })
}


@Composable
private fun LazyVerticalGridUserVisibleItemIndexSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyColor.halfRainbows
    val lazyListState = rememberLazyGridState(4)
    val itemIndexState = remember { derivedStateOf { lazyListState.firstVisibleItemIndex } }
    val offsetState =
        remember { derivedStateOf { lazyListState.firstVisibleItemScrollOffset } }
    ExpandableItem3(
        title = "LazyVerticalGrid???firstVisibleItemIndex???",
        allExpandFlow,
        padding = 20.dp
    ) {
        Column {
            LazyVerticalGrid(
                columns = GridCells.Fixed(4),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(360.dp)
                    .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                    .padding(2.dp),
                state = lazyListState
            ) {
                items(count = 50) { index ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1f)
                            .background(colors[index % colors.size])
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
private fun LazyVerticalGridUserVisibleItemIndexSamplePreview() {
    LazyVerticalGridUserVisibleItemIndexSample(remember { MutableStateFlow(true) })
}


@Composable
private fun LazyVerticalGridScrollInProgressSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyColor.halfRainbows
    val lazyListState = rememberLazyGridState(3)
    val scrollInProgressState = remember { derivedStateOf { lazyListState.isScrollInProgress } }
    ExpandableItem3(
        title = "LazyVerticalGrid???isScrollInProgress???",
        allExpandFlow,
        padding = 20.dp
    ) {
        Column {
            LazyVerticalGrid(
                columns = GridCells.Fixed(4),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(360.dp)
                    .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                    .padding(2.dp),
                state = lazyListState
            ) {
                items(count = 50) { index ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1f)
                            .background(colors[index % colors.size])
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
private fun LazyVerticalGridScrollInProgressSamplePreview() {
    LazyVerticalGridScrollInProgressSample(remember { MutableStateFlow(true) })
}


@Composable
private fun LazyVerticalGridAnimateScrollToItemSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyColor.halfRainbows
    val lazyListState = rememberLazyGridState(3)
    val coroutineScope = rememberCoroutineScope()
    ExpandableItem3(
        title = "LazyVerticalGrid???animateScrollToItem???",
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
                Image(
                    painter = painterResource(id = R.drawable.ic_arrow_up),
                    contentDescription = "before"
                )
            }
            LazyVerticalGrid(
                columns = GridCells.Fixed(4),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(360.dp)
                    .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                    .padding(2.dp),
                state = lazyListState
            ) {
                items(count = 50) { index ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1f)
                            .background(colors[index % colors.size])
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
                    if (firstVisibleItemIndex < 50 - 4) {
                        coroutineScope.launch {
                            lazyListState.animateScrollToItem(firstVisibleItemIndex + 4)
                        }
                    }
                },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_arrow_down),
                    contentDescription = "next",
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun LazyVerticalGridAnimateScrollToItemSamplePreview() {
    LazyVerticalGridAnimateScrollToItemSample(remember { MutableStateFlow(true) })
}


@Composable
private fun LazyVerticalGridSpanSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyColor.halfRainbows
    ExpandableItem3(title = "LazyVerticalGrid???span???", allExpandFlow, padding = 20.dp) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            modifier = Modifier
                .fillMaxWidth()
                .height(360.dp)
                .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                .padding(2.dp)
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
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                        .background(colors[index % colors.size])
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
private fun LazyVerticalGridSpanSamplePreview() {
    LazyVerticalGridSpanSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun LazyVerticalGridAnimateItemPlacementSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyColor.halfRainbows
    val items = remember {
        mutableStateOf(
            buildList {
                repeat(49) {
                    add((it + 1).toString())
                }
            }
        )
    }
    ExpandableItem3(
        title = "LazyVerticalGrid???animateItemPlacement???",
        allExpandFlow,
        padding = 20.dp
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(text = "?????? item ??????????????????????????????")
            LazyVerticalGrid(
                columns = GridCells.Fixed(4),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(360.dp)
                    .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                    .padding(2.dp)
                    .padding(2.dp)
            ) {
                itemsIndexed(
                    items = items.value,
                    key = { _, item -> item }
                ) { index, item ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1f)
                            .background(colors[index % colors.size])
                            .animateItemPlacement()
                            .clickable {
                                items.value = items.value
                                    .toMutableList()
                                    .apply {
                                        remove(item)
                                    }
                                    .toList()
                            },
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

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun LazyVerticalGridAnimateItemPlacementSamplePreview() {
    LazyVerticalGridAnimateItemPlacementSample(remember { MutableStateFlow(true) })
}


@Composable
private fun LazyVerticalGridLayoutInfoSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyColor.halfRainbows
    val lazyGridState = rememberLazyGridState()
    val layoutInfoState = remember { derivedStateOf { lazyGridState.layoutInfo } }
    ExpandableItem3(title = "LazyVerticalGrid???layoutInfo???", allExpandFlow, padding = 20.dp) {
        Column {
            LazyVerticalGrid(
                state = lazyGridState,
                columns = GridCells.Fixed(4),
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
                            .aspectRatio(1f)
                            .background(colors[index % colors.size])
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
                    append("reverseLayout: ${listLayoutInfoState.value.reverseLayout}")

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
private fun LazyVerticalGridLayoutInfoSamplePreview() {
    LazyVerticalGridLayoutInfoSample(remember { MutableStateFlow(true) })
}


@Composable
private fun LazyVerticalGridMultiTypeSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyColor.halfRainbows
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
    ExpandableItem3(title = "LazyVerticalGrid???MultiType???", allExpandFlow, padding = 20.dp) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
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
                                .aspectRatio(1f)
                                .background(colors[index % colors.size])
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
                                .aspectRatio(1f)
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
private fun LazyVerticalGridMultiTypeSamplePreview() {
    LazyVerticalGridMultiTypeSample(remember { MutableStateFlow(true) })
}