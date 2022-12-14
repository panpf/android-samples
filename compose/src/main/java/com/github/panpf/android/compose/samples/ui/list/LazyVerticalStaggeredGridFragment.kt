package com.github.panpf.android.compose.samples.ui.list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
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
            LazyVerticalStaggeredGridItemSpacedSample(allExpandFlow)
            LazyVerticalStaggeredGridVerticalArrangementSample(allExpandFlow)
            LazyVerticalStaggeredGridHorizontalArrangementSample(allExpandFlow)
            LazyVerticalStaggeredGridUserScrollEnabledSample(allExpandFlow)
            LazyVerticalStaggeredGridUserVisibleItemIndexSample(allExpandFlow)
            LazyVerticalStaggeredGridScrollInProgressSample(allExpandFlow)
            LazyVerticalStaggeredGridAnimateScrollToItemSample(allExpandFlow)
            LazyVerticalStaggeredGridLayoutInfoSample(allExpandFlow)
            LazyVerticalStaggeredGridMultiTypeSample(allExpandFlow)
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
        title = "LazyVerticalStaggeredGrid???columns - AdaptiveCells???",
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
        title = "LazyVerticalStaggeredGrid???contentPadding???",
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
        title = "LazyVerticalStaggeredGrid???ItemSpaced???",
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
            verticalArrangement = Arrangement.spacedBy(10.dp),
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
private fun LazyVerticalStaggeredGridVerticalArrangementSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyColor.rainbows
    val items = buildList {
        repeat(5) {
            add((it + 1).toString())
        }
    }
    ExpandableItem3(
        title = "LazyVerticalStaggeredGrid???verticalArrangement???",
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
                    LazyVerticalStaggeredGrid(
                        columns = StaggeredGridCells.Fixed(2),
                        modifier = Modifier
                            .width(110.dp)
                            .height(200.dp)
                            .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                            .padding(2.dp),
                        verticalArrangement = arrangement ?: Arrangement.spacedBy(10.dp),
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
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun LazyVerticalStaggeredGridVerticalArrangementSamplePreview() {
    LazyVerticalStaggeredGridVerticalArrangementSample(remember { MutableStateFlow(true) })
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
        title = "LazyVerticalStaggeredGrid???horizontalArrangement???",
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
                    LazyVerticalStaggeredGrid(
                        columns = StaggeredGridCells.Fixed(1),
                        modifier = Modifier
                            .width(110.dp)
                            .height(200.dp)
                            .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                            .padding(2.dp),
                        horizontalArrangement = arrangement ?: Arrangement.spacedBy(10.dp)
                    ) {
                        itemsIndexed(items) { index, item ->
                            Box(
                                modifier = Modifier
                                    .size(40.dp)
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
        title = "LazyVerticalStaggeredGrid???userScrollEnabled = false???",
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
        title = "LazyVerticalStaggeredGrid???firstVisibleItemIndex???",
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
        title = "LazyVerticalStaggeredGrid???isScrollInProgress???",
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
        title = "LazyVerticalStaggeredGrid???animateScrollToItem???",
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
        title = "LazyVerticalStaggeredGrid???layoutInfo???",
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
private fun LazyVerticalStaggeredGridMultiTypeSample(allExpandFlow: Flow<Boolean>) {
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
        title = "LazyVerticalStaggeredGrid???MultiType???",
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
                                text = item,
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
private fun LazyVerticalStaggeredGridMultiTypeSamplePreview() {
    LazyVerticalStaggeredGridMultiTypeSample(remember { MutableStateFlow(true) })
}