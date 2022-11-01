package com.github.panpf.android.compose.samples.ui.advancedweidgets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.panpf.android.compose.samples.R
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.ToolbarFragment
import com.github.panpf.android.compose.samples.ui.base.theme.MyTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class LazyHorizontalGridFragment : ToolbarFragment() {

    override fun createView(
        toolbar: Toolbar,
        inflater: LayoutInflater,
        parent: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        toolbar.title = "LazyHorizontalGrid"
        return ComposeView(inflater.context).apply {
            setContent {
                MyTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        ExpandableLayout { allExpandFlow ->
                            LazyHorizontalGridSample(allExpandFlow)
                            LazyHorizontalGridHorizontalGridsDynamicCellsSample(allExpandFlow)
                            LazyHorizontalGridContentPaddingSample(allExpandFlow)
                            LazyHorizontalGridReverseLayoutSample(allExpandFlow)
                            LazyHorizontalGridHorizontalArrangementSample(allExpandFlow)
                            LazyHorizontalGridVerticalArrangementSample(allExpandFlow)
                            LazyHorizontalGridUserScrollEnabledSample(allExpandFlow)
                            LazyHorizontalGridUserVisibleItemIndexSample(allExpandFlow)
                            LazyHorizontalGridScrollInProgressSample(allExpandFlow)
                            LazyHorizontalGridAnimateScrollToItemSample(allExpandFlow)
                            // todo contentType
                            // todo key
                            // todo span
                            // todo paging
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun LazyHorizontalGridSample(allExpandFlow: Flow<Boolean>) {
    val colors = listOf(
        Color.Red, Color.Black, Color.White, Color.Magenta, Color.Cyan,
        Color.Yellow, Color.Blue, Color.Green, Color.Gray
    ).map { it.copy(alpha = 0.5f) }
    val items = buildList {
        repeat(50) {
            add(it.toString())
        }
    }
    ExpandableItem(title = "LazyHorizontalGrid", allExpandFlow, padding = 20.dp) {
        LazyHorizontalGrid(
            rows = GridCells.Fixed(3),
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(Color.Red.copy(alpha = 0.5f))
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
fun LazyHorizontalGridSamplePreview() {
    LazyHorizontalGridSample(remember { MutableStateFlow(true) })
}


@Composable
fun LazyHorizontalGridHorizontalGridsDynamicCellsSample(allExpandFlow: Flow<Boolean>) {
    val colors = listOf(
        Color.Red, Color.Black, Color.White, Color.Magenta, Color.Cyan,
        Color.Yellow, Color.Blue, Color.Green, Color.Gray
    ).map { it.copy(alpha = 0.5f) }
    val items = buildList {
        repeat(50) {
            add(it.toString())
        }
    }
    val gridHeight = remember { mutableStateOf(200.dp) }
    ExpandableItem(
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
                Image(painterResource(id = R.drawable.ic_remove), contentDescription = "remove")
            }
            LazyHorizontalGrid(
                rows = GridCells.Adaptive(80.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(gridHeight.value)
                    .background(Color.Red.copy(alpha = 0.5f))
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
fun LazyHorizontalGridHorizontalGridsDynamicCellsSamplePreview() {
    LazyHorizontalGridHorizontalGridsDynamicCellsSample(remember { MutableStateFlow(true) })
}


@Composable
fun LazyHorizontalGridContentPaddingSample(allExpandFlow: Flow<Boolean>) {
    val colors = listOf(
        Color.Red, Color.Black, Color.White, Color.Magenta, Color.Cyan,
        Color.Yellow, Color.Blue, Color.Green, Color.Gray
    ).map { it.copy(alpha = 0.5f) }
    val items = buildList {
        repeat(50) {
            add(it.toString())
        }
    }
    ExpandableItem(
        title = "LazyHorizontalGrid（contentPadding）",
        allExpandFlow,
        padding = 20.dp
    ) {
        LazyHorizontalGrid(
            rows = GridCells.Fixed(3),
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(Color.Red.copy(alpha = 0.5f))
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
fun LazyHorizontalGridContentPaddingSamplePreview() {
    LazyHorizontalGridContentPaddingSample(remember { MutableStateFlow(true) })
}


@Composable
fun LazyHorizontalGridReverseLayoutSample(allExpandFlow: Flow<Boolean>) {
    val colors = listOf(
        Color.Red, Color.Black, Color.White, Color.Magenta, Color.Cyan,
        Color.Yellow, Color.Blue, Color.Green, Color.Gray
    ).map { it.copy(alpha = 0.5f) }
    val items = buildList {
        repeat(50) {
            add(it.toString())
        }
    }
    ExpandableItem(
        title = "LazyHorizontalGrid（reverseLayout）",
        allExpandFlow,
        padding = 20.dp
    ) {
        LazyHorizontalGrid(
            rows = GridCells.Fixed(3),
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(Color.Red.copy(alpha = 0.5f))
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
fun LazyHorizontalGridReverseLayoutSamplePreview() {
    LazyHorizontalGridReverseLayoutSample(remember { MutableStateFlow(true) })
}


@Composable
fun LazyHorizontalGridHorizontalArrangementSample(allExpandFlow: Flow<Boolean>) {
    val colors = listOf(
        Color.Red, Color.Black, Color.White, Color.Magenta, Color.Cyan,
        Color.Yellow, Color.Blue, Color.Green, Color.Gray
    ).map { it.copy(alpha = 0.5f) }
    val items = buildList {
        repeat(9) {
            add(it.toString())
        }
    }
    ExpandableItem(
        title = "LazyHorizontalGrid（horizontalArrangement）",
        allExpandFlow,
        padding = 20.dp
    ) {
        Column {
            listOf(
                Arrangement.Start to "Start",
                Arrangement.Center to "Center",
                Arrangement.End to "End",
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
                            .background(Color.Red.copy(alpha = 0.5f))
                            .padding(2.dp),
                        horizontalArrangement = arrangement
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
fun LazyHorizontalGridHorizontalArrangementSamplePreview() {
    LazyHorizontalGridHorizontalArrangementSample(remember { MutableStateFlow(true) })
}


@Composable
fun LazyHorizontalGridVerticalArrangementSample(allExpandFlow: Flow<Boolean>) {
    val colors = listOf(
        Color.Red, Color.Black, Color.White, Color.Magenta, Color.Cyan,
        Color.Yellow, Color.Blue, Color.Green, Color.Gray
    ).map { it.copy(alpha = 0.5f) }
    val items = buildList {
        repeat(9) {
            add(it.toString())
        }
    }
    ExpandableItem(
        title = "LazyHorizontalGrid（verticalArrangement）",
        allExpandFlow,
        padding = 20.dp
    ) {
        Column {
            listOf(
                Arrangement.Top to "Top",
                Arrangement.Center to "Center",
                Arrangement.Bottom to "Bottom",
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
                            .background(Color.Red.copy(alpha = 0.5f))
                            .padding(2.dp),
                        verticalArrangement = arrangement
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
fun LazyHorizontalGridVerticalArrangementSamplePreview() {
    LazyHorizontalGridVerticalArrangementSample(remember { MutableStateFlow(true) })
}


@Composable
fun LazyHorizontalGridUserScrollEnabledSample(allExpandFlow: Flow<Boolean>) {
    val colors = listOf(
        Color.Red, Color.Black, Color.White, Color.Magenta, Color.Cyan,
        Color.Yellow, Color.Blue, Color.Green, Color.Gray
    ).map { it.copy(alpha = 0.5f) }
    val items = buildList {
        repeat(50) {
            add(it.toString())
        }
    }
    ExpandableItem(
        title = "LazyHorizontalGrid（userScrollEnabled = false）",
        allExpandFlow,
        padding = 20.dp
    ) {
        LazyHorizontalGrid(
            rows = GridCells.Fixed(3),
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(Color.Red.copy(alpha = 0.5f))
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
fun LazyHorizontalGridUserScrollEnabledSamplePreview() {
    LazyHorizontalGridUserScrollEnabledSample(remember { MutableStateFlow(true) })
}


@Composable
fun LazyHorizontalGridUserVisibleItemIndexSample(allExpandFlow: Flow<Boolean>) {
    val colors = listOf(
        Color.Red, Color.Black, Color.White, Color.Magenta, Color.Cyan,
        Color.Yellow, Color.Blue, Color.Green, Color.Gray
    ).map { it.copy(alpha = 0.5f) }
    val items = buildList {
        repeat(50) {
            add(it.toString())
        }
    }
    val lazyListState = rememberLazyGridState(3)
    val itemIndexState = remember { derivedStateOf { lazyListState.firstVisibleItemIndex } }
    val offsetState =
        remember { derivedStateOf { lazyListState.firstVisibleItemScrollOffset } }
    ExpandableItem(
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
                    .background(Color.Red.copy(alpha = 0.5f))
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
fun LazyHorizontalGridUserVisibleItemIndexSamplePreview() {
    LazyHorizontalGridUserVisibleItemIndexSample(remember { MutableStateFlow(true) })
}


@Composable
fun LazyHorizontalGridScrollInProgressSample(allExpandFlow: Flow<Boolean>) {
    val colors = listOf(
        Color.Red, Color.Black, Color.White, Color.Magenta, Color.Cyan,
        Color.Yellow, Color.Blue, Color.Green, Color.Gray
    ).map { it.copy(alpha = 0.5f) }
    val items = buildList {
        repeat(50) {
            add(it.toString())
        }
    }
    val lazyListState = rememberLazyGridState(3)
    val scrollInProgressState = remember { derivedStateOf { lazyListState.isScrollInProgress } }
    ExpandableItem(
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
                    .background(Color.Red.copy(alpha = 0.5f))
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
fun LazyHorizontalGridScrollInProgressSamplePreview() {
    LazyHorizontalGridScrollInProgressSample(remember { MutableStateFlow(true) })
}


@Composable
fun LazyHorizontalGridAnimateScrollToItemSample(allExpandFlow: Flow<Boolean>) {
    val colors = listOf(
        Color.Red, Color.Black, Color.White, Color.Magenta, Color.Cyan,
        Color.Yellow, Color.Blue, Color.Green, Color.Gray
    ).map { it.copy(alpha = 0.5f) }
    val items = buildList {
        repeat(50) {
            add(it.toString())
        }
    }
    val lazyListState = rememberLazyGridState(3)
    val coroutineScope = rememberCoroutineScope()
    ExpandableItem(
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
                    painter = painterResource(id = R.drawable.ic_navigate_before),
                    contentDescription = "before"
                )
            }
            LazyHorizontalGrid(
                rows = GridCells.Fixed(3),
                modifier = Modifier
                    .weight(1f)
                    .height(200.dp)
                    .background(Color.Red.copy(alpha = 0.5f))
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
                    painter = painterResource(id = R.drawable.ic_navigate_next),
                    contentDescription = "next",
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun LazyHorizontalGridAnimateScrollToItemSamplePreview() {
    LazyHorizontalGridAnimateScrollToItemSample(remember { MutableStateFlow(true) })
}