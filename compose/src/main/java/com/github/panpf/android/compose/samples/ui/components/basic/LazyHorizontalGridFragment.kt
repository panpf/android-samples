package com.github.panpf.android.compose.samples.ui.components.basic

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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.panpf.android.compose.samples.R
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem3
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlin.math.ceil

class LazyHorizontalGridFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "LazyHorizontalGrid"
    }

    @Composable
    override fun DrawContent() {
        ExpandableLayout { allExpandFlow ->
            LazyHorizontalGridSample(allExpandFlow)
            LazyHorizontalGridHorizontalGridsDynamicCellsSample(allExpandFlow)
            LazyHorizontalGridContentPaddingSample(allExpandFlow)
            LazyHorizontalGridItemSpacedSample(allExpandFlow)
            LazyHorizontalGridReverseLayoutSample(allExpandFlow)
            LazyHorizontalGridHorizontalArrangementSample(allExpandFlow)
            LazyHorizontalGridVerticalArrangementSample(allExpandFlow)
            LazyHorizontalGridUserScrollEnabledSample(allExpandFlow)
            LazyHorizontalGridUserVisibleItemIndexSample(allExpandFlow)
            LazyHorizontalGridScrollInProgressSample(allExpandFlow)
            LazyHorizontalGridAnimateScrollToItemSample(allExpandFlow)
            LazyHorizontalGridSpanSample(allExpandFlow)
            LazyHorizontalGridAnimateItemPlacementSample(allExpandFlow)
            LazyHorizontalGridLayoutInfoSample(allExpandFlow)
            LazyHorizontalGridMultiTypeSample(allExpandFlow)
        }
    }
}


@Composable
private fun LazyHorizontalGridSample(allExpandFlow: Flow<Boolean>) {
    val colors = remember {
        listOf(Color.Blue, Color.Magenta, Color.Cyan, Color.Red, Color.Yellow, Color.Green)
            .map { it.copy(alpha = 0.5f) }
    }
    val items = remember {
        buildList {
            repeat(49) {
                add((it + 1).toString())
            }
        }
    }
    ExpandableItem3(title = "LazyHorizontalGrid", allExpandFlow, padding = 20.dp) {
        LazyHorizontalGrid(
            rows = GridCells.Fixed(3),
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .border(2.dp, Color.Red)
                .padding(2.dp)
        ) {
            itemsIndexed(items) { index, item ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .background(colors[index % colors.size])
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
private fun LazyHorizontalGridSamplePreview() {
    LazyHorizontalGridSample(remember { MutableStateFlow(true) })
}


@Composable
private fun LazyHorizontalGridHorizontalGridsDynamicCellsSample(allExpandFlow: Flow<Boolean>) {
    val colors = remember {
        listOf(Color.Blue, Color.Magenta, Color.Cyan, Color.Red, Color.Yellow, Color.Green)
            .map { it.copy(alpha = 0.5f) }
    }
    val items = remember {
        buildList {
            repeat(49) {
                add((it + 1).toString())
            }
        }
    }
    val gridHeight = remember { mutableStateOf(200.dp) }
    ExpandableItem3(
        title = "LazyHorizontalGrid（rows - AdaptiveCells）",
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
            LazyHorizontalGrid(
                rows = GridCells.Adaptive(80.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(gridHeight.value)
                    .border(2.dp, Color.Red)
                    .padding(2.dp)
            ) {
                itemsIndexed(items) { index, item ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1f)
                            .background(colors[index % colors.size])
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
private fun LazyHorizontalGridHorizontalGridsDynamicCellsSamplePreview() {
    LazyHorizontalGridHorizontalGridsDynamicCellsSample(remember { MutableStateFlow(true) })
}


@Composable
private fun LazyHorizontalGridContentPaddingSample(allExpandFlow: Flow<Boolean>) {
    val colors = remember {
        listOf(Color.Blue, Color.Magenta, Color.Cyan, Color.Red, Color.Yellow, Color.Green)
            .map { it.copy(alpha = 0.5f) }
    }
    val items = remember {
        buildList {
            repeat(49) {
                add((it + 1).toString())
            }
        }
    }
    ExpandableItem3(
        title = "LazyHorizontalGrid（contentPadding）",
        allExpandFlow,
        padding = 20.dp
    ) {
        LazyHorizontalGrid(
            rows = GridCells.Fixed(3),
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .border(2.dp, Color.Red)
                .padding(2.dp),
            contentPadding = PaddingValues(20.dp)
        ) {
            itemsIndexed(items) { index, item ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .background(colors[index % colors.size])
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
private fun LazyHorizontalGridContentPaddingSamplePreview() {
    LazyHorizontalGridContentPaddingSample(remember { MutableStateFlow(true) })
}


@Composable
private fun LazyHorizontalGridItemSpacedSample(allExpandFlow: Flow<Boolean>) {
    val colors = remember {
        listOf(Color.Blue, Color.Magenta, Color.Cyan, Color.Red, Color.Yellow, Color.Green)
            .map { it.copy(alpha = 0.5f) }
    }
    val items = remember {
        buildList {
            repeat(49) {
                add((it + 1).toString())
            }
        }
    }
    ExpandableItem3(
        title = "LazyHorizontalGrid（ItemSpaced）",
        allExpandFlow,
        padding = 20.dp
    ) {
        LazyHorizontalGrid(
            rows = GridCells.Fixed(3),
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .border(2.dp, Color.Red)
                .padding(2.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            itemsIndexed(items) { index, item ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .background(colors[index % colors.size])
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
private fun LazyHorizontalGridItemSpacedSamplePreview() {
    LazyHorizontalGridItemSpacedSample(remember { MutableStateFlow(true) })
}


@Composable
private fun LazyHorizontalGridReverseLayoutSample(allExpandFlow: Flow<Boolean>) {
    val colors = remember {
        listOf(Color.Blue, Color.Magenta, Color.Cyan, Color.Red, Color.Yellow, Color.Green)
            .map { it.copy(alpha = 0.5f) }
    }
    val items = remember {
        buildList {
            repeat(49) {
                add((it + 1).toString())
            }
        }
    }
    ExpandableItem3(
        title = "LazyHorizontalGrid（reverseLayout）",
        allExpandFlow,
        padding = 20.dp
    ) {
        LazyHorizontalGrid(
            rows = GridCells.Fixed(3),
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .border(2.dp, Color.Red)
                .padding(2.dp),
            reverseLayout = true
        ) {
            itemsIndexed(items) { index, item ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .background(colors[index % colors.size])
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
private fun LazyHorizontalGridReverseLayoutSamplePreview() {
    LazyHorizontalGridReverseLayoutSample(remember { MutableStateFlow(true) })
}


@Composable
private fun LazyHorizontalGridHorizontalArrangementSample(allExpandFlow: Flow<Boolean>) {
    val colors = remember {
        listOf(Color.Blue, Color.Magenta, Color.Cyan, Color.Red, Color.Yellow, Color.Green)
            .map { it.copy(alpha = 0.5f) }
    }
    val items = buildList {
        repeat(9) {
            add((it + 1).toString())
        }
    }
    ExpandableItem3(
        title = "LazyHorizontalGrid（horizontalArrangement）",
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
                    LazyHorizontalGrid(
                        rows = GridCells.Fixed(2),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(110.dp)
                            .border(2.dp, Color.Red)
                            .padding(2.dp),
                        horizontalArrangement = arrangement ?: Arrangement.spacedBy(10.dp),
                    ) {
                        itemsIndexed(items) { index, item ->
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .aspectRatio(1f)
                                    .background(colors[index % colors.size])
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
private fun LazyHorizontalGridHorizontalArrangementSamplePreview() {
    LazyHorizontalGridHorizontalArrangementSample(remember { MutableStateFlow(true) })
}


@Composable
private fun LazyHorizontalGridVerticalArrangementSample(allExpandFlow: Flow<Boolean>) {
    val colors = remember {
        listOf(Color.Blue, Color.Magenta, Color.Cyan, Color.Red, Color.Yellow, Color.Green)
            .map { it.copy(alpha = 0.5f) }
    }
    val items = buildList {
        repeat(9) {
            add((it + 1).toString())
        }
    }
    ExpandableItem3(
        title = "LazyHorizontalGrid（verticalArrangement）",
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
                    LazyHorizontalGrid(
                        rows = GridCells.Fixed(1),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(110.dp)
                            .border(2.dp, Color.Red)
                            .padding(2.dp),
                        verticalArrangement = arrangement ?: Arrangement.spacedBy(10.dp)
                    ) {
                        itemsIndexed(items) { index, item ->
                            Box(
                                modifier = Modifier
                                    .size(40.dp)
                                    .background(colors[index % colors.size])
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
private fun LazyHorizontalGridVerticalArrangementSamplePreview() {
    LazyHorizontalGridVerticalArrangementSample(remember { MutableStateFlow(true) })
}


@Composable
private fun LazyHorizontalGridUserScrollEnabledSample(allExpandFlow: Flow<Boolean>) {
    val colors = remember {
        listOf(Color.Blue, Color.Magenta, Color.Cyan, Color.Red, Color.Yellow, Color.Green)
            .map { it.copy(alpha = 0.5f) }
    }
    val items = remember {
        buildList {
            repeat(49) {
                add((it + 1).toString())
            }
        }
    }
    ExpandableItem3(
        title = "LazyHorizontalGrid（userScrollEnabled = false）",
        allExpandFlow,
        padding = 20.dp
    ) {
        LazyHorizontalGrid(
            rows = GridCells.Fixed(3),
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .border(2.dp, Color.Red)
                .padding(2.dp),
            userScrollEnabled = false
        ) {
            itemsIndexed(items) { index, item ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .background(colors[index % colors.size])
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
private fun LazyHorizontalGridUserScrollEnabledSamplePreview() {
    LazyHorizontalGridUserScrollEnabledSample(remember { MutableStateFlow(true) })
}


@Composable
private fun LazyHorizontalGridUserVisibleItemIndexSample(allExpandFlow: Flow<Boolean>) {
    val colors = remember {
        listOf(Color.Blue, Color.Magenta, Color.Cyan, Color.Red, Color.Yellow, Color.Green)
            .map { it.copy(alpha = 0.5f) }
    }
    val items = remember {
        buildList {
            repeat(49) {
                add((it + 1).toString())
            }
        }
    }
    val lazyListState = rememberLazyGridState(3)
    val itemIndexState = remember { derivedStateOf { lazyListState.firstVisibleItemIndex } }
    val offsetState =
        remember { derivedStateOf { lazyListState.firstVisibleItemScrollOffset } }
    ExpandableItem3(
        title = "LazyHorizontalGrid（firstVisibleItemIndex）",
        allExpandFlow,
        padding = 20.dp
    ) {
        Column {
            LazyHorizontalGrid(
                rows = GridCells.Fixed(3),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .border(2.dp, Color.Red)
                    .padding(2.dp),
                state = lazyListState
            ) {
                itemsIndexed(items) { index, item ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1f)
                            .background(colors[index % colors.size])
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
private fun LazyHorizontalGridUserVisibleItemIndexSamplePreview() {
    LazyHorizontalGridUserVisibleItemIndexSample(remember { MutableStateFlow(true) })
}


@Composable
private fun LazyHorizontalGridScrollInProgressSample(allExpandFlow: Flow<Boolean>) {
    val colors = remember {
        listOf(Color.Blue, Color.Magenta, Color.Cyan, Color.Red, Color.Yellow, Color.Green)
            .map { it.copy(alpha = 0.5f) }
    }
    val items = remember {
        buildList {
            repeat(49) {
                add((it + 1).toString())
            }
        }
    }
    val lazyListState = rememberLazyGridState(3)
    val scrollInProgressState = remember { derivedStateOf { lazyListState.isScrollInProgress } }
    ExpandableItem3(
        title = "LazyHorizontalGrid（isScrollInProgress）",
        allExpandFlow,
        padding = 20.dp
    ) {
        Column {
            LazyHorizontalGrid(
                rows = GridCells.Fixed(3),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .border(2.dp, Color.Red)
                    .padding(2.dp),
                state = lazyListState
            ) {
                itemsIndexed(items) { index, item ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1f)
                            .background(colors[index % colors.size])
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
private fun LazyHorizontalGridScrollInProgressSamplePreview() {
    LazyHorizontalGridScrollInProgressSample(remember { MutableStateFlow(true) })
}


@Composable
private fun LazyHorizontalGridAnimateScrollToItemSample(allExpandFlow: Flow<Boolean>) {
    val colors = remember {
        listOf(Color.Blue, Color.Magenta, Color.Cyan, Color.Red, Color.Yellow, Color.Green)
            .map { it.copy(alpha = 0.5f) }
    }
    val items = remember {
        buildList {
            repeat(49) {
                add((it + 1).toString())
            }
        }
    }
    val lazyListState = rememberLazyGridState(3)
    val coroutineScope = rememberCoroutineScope()
    ExpandableItem3(
        title = "LazyHorizontalGrid（animateScrollToItem）",
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
            LazyHorizontalGrid(
                rows = GridCells.Fixed(3),
                modifier = Modifier
                    .weight(1f)
                    .height(200.dp)
                    .border(2.dp, Color.Red)
                    .padding(2.dp),
                state = lazyListState
            ) {
                itemsIndexed(items) { index, item ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1f)
                            .background(colors[index % colors.size])
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
                    if (firstVisibleItemIndex < items.size - 3) {
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
private fun LazyHorizontalGridAnimateScrollToItemSamplePreview() {
    LazyHorizontalGridAnimateScrollToItemSample(remember { MutableStateFlow(true) })
}


@Composable
private fun LazyHorizontalGridSpanSample(allExpandFlow: Flow<Boolean>) {
    val colors = remember {
        listOf(Color.Blue, Color.Magenta, Color.Cyan, Color.Red, Color.Yellow, Color.Green)
            .map { it.copy(alpha = 0.5f) }
    }
    val items = remember {
        buildList {
            repeat(49) {
                add((it + 1).toString())
            }
        }
    }
    ExpandableItem3(title = "LazyHorizontalGrid（span）", allExpandFlow, padding = 20.dp) {
        LazyHorizontalGrid(
            rows = GridCells.Fixed(3),
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .border(2.dp, Color.Red)
                .padding(2.dp)
        ) {
            itemsIndexed(
                items,
                span = { index, _ ->
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
            ) { index, item ->
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(80.dp)
                        .background(colors[index % colors.size])
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
private fun LazyHorizontalGridSpanSamplePreview() {
    LazyHorizontalGridSpanSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun LazyHorizontalGridAnimateItemPlacementSample(allExpandFlow: Flow<Boolean>) {
    val colors = remember {
        listOf(Color.Blue, Color.Magenta, Color.Cyan, Color.Red, Color.Yellow, Color.Green)
            .map { it.copy(alpha = 0.5f) }
    }
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
        title = "LazyHorizontalGrid（animateItemPlacement）",
        allExpandFlow,
        padding = 20.dp
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(text = "点击 item 删除它，然后触发动画")
            LazyHorizontalGrid(
                rows = GridCells.Fixed(3),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .border(2.dp, Color.Red)
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

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun LazyHorizontalGridAnimateItemPlacementSamplePreview() {
    LazyHorizontalGridAnimateItemPlacementSample(remember { MutableStateFlow(true) })
}


@Composable
private fun LazyHorizontalGridLayoutInfoSample(allExpandFlow: Flow<Boolean>) {
    val colors = remember {
        listOf(Color.Blue, Color.Magenta, Color.Cyan, Color.Red, Color.Yellow, Color.Green)
            .map { it.copy(alpha = 0.5f) }
    }
    val items = remember {
        buildList {
            repeat(49) {
                add((it + 1).toString())
            }
        }
    }
    val lazyGridState = rememberLazyGridState()
    val layoutInfoState = remember { derivedStateOf { lazyGridState.layoutInfo } }
    ExpandableItem3(title = "LazyHorizontalGrid（layoutInfo）", allExpandFlow, padding = 20.dp) {
        Column {
            LazyHorizontalGrid(
                state = lazyGridState,
                rows = GridCells.Fixed(3),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .border(2.dp, Color.Red)
                    .padding(2.dp)
            ) {
                itemsIndexed(items) { index, item ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1f)
                            .background(colors[index % colors.size])
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
private fun LazyHorizontalGridLayoutInfoSamplePreview() {
    LazyHorizontalGridLayoutInfoSample(remember { MutableStateFlow(true) })
}


@Composable
private fun LazyHorizontalGridMultiTypeSample(allExpandFlow: Flow<Boolean>) {
    val colors = remember {
        listOf(Color.Blue, Color.Magenta, Color.Cyan, Color.Red, Color.Yellow, Color.Green)
            .map { it.copy(alpha = 0.5f) }
    }
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
    ExpandableItem3(title = "LazyHorizontalGrid（MultiType）", allExpandFlow, padding = 20.dp) {
        LazyHorizontalGrid(
            rows = GridCells.Fixed(3),
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .border(2.dp, Color.Red)
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
private fun LazyHorizontalGridMultiTypeSamplePreview() {
    LazyHorizontalGridMultiTypeSample(remember { MutableStateFlow(true) })
}