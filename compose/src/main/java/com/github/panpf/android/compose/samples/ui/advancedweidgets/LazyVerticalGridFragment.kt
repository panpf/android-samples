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
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
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
import com.google.accompanist.flowlayout.FlowRow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlin.math.ceil

class LazyVerticalGridFragment : ToolbarFragment() {

    override fun createView(
        toolbar: Toolbar,
        inflater: LayoutInflater,
        parent: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        toolbar.title = "LazyVerticalGrid"
        return ComposeView(inflater.context).apply {
            setContent {
                MyTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        ExpandableLayout { allExpandFlow ->
                            LazyVerticalGridSample(allExpandFlow)
                            LazyVerticalGridHorizontalGridsDynamicCellsSample(allExpandFlow)
                            LazyVerticalGridContentPaddingSample(allExpandFlow)
                            LazyVerticalGridReverseLayoutSample(allExpandFlow)
                            LazyVerticalGridVerticalArrangementSample(allExpandFlow)
                            LazyVerticalGridHorizontalArrangementSample(allExpandFlow)
                            LazyVerticalGridUserScrollEnabledSample(allExpandFlow)
                            LazyVerticalGridUserVisibleItemIndexSample(allExpandFlow)
                            LazyVerticalGridScrollInProgressSample(allExpandFlow)
                            LazyVerticalGridAnimateScrollToItemSample(allExpandFlow)
                            LazyVerticalGridMultiTypeSample(allExpandFlow)
                            LazyVerticalGridSpanSample(allExpandFlow)
                            // todo paging
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun LazyVerticalGridSample(allExpandFlow: Flow<Boolean>) {
    val colors = remember {
        listOf(
            Color.Red, Color.Black, Color.White, Color.Magenta, Color.Cyan,
            Color.Yellow, Color.Blue, Color.Green, Color.Gray
        ).map { it.copy(alpha = 0.5f) }
    }
    val items = remember {
        buildList {
            repeat(49) {
                add((it + 1).toString())
            }
        }
    }
    ExpandableItem(title = "LazyVerticalGrid", allExpandFlow, padding = 20.dp) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            modifier = Modifier
                .fillMaxWidth()
                .height(360.dp)
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
fun LazyVerticalGridSamplePreview() {
    LazyVerticalGridSample(remember { MutableStateFlow(true) })
}


@Composable
fun LazyVerticalGridHorizontalGridsDynamicCellsSample(allExpandFlow: Flow<Boolean>) {
    val colors = remember {
        listOf(
            Color.Red, Color.Black, Color.White, Color.Magenta, Color.Cyan,
            Color.Yellow, Color.Blue, Color.Green, Color.Gray
        ).map { it.copy(alpha = 0.5f) }
    }
    val items = remember {
        buildList {
            repeat(49) {
                add((it + 1).toString())
            }
        }
    }
    val gridHeight = remember { mutableStateOf(200.dp) }
    ExpandableItem(
        title = "LazyVerticalGrid（columns - AdaptiveCells）",
        allExpandFlow,
        padding = 20.dp
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(80.dp),
                modifier = Modifier
                    .width(gridHeight.value)
                    .height(360.dp)
                    .background(Color.Red.copy(alpha = 0.5f))
                    .padding(2.dp)
                    .align(Alignment.CenterHorizontally)
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
                    Image(painterResource(id = R.drawable.ic_remove), contentDescription = "remove")
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
fun LazyVerticalGridHorizontalGridsDynamicCellsSamplePreview() {
    LazyVerticalGridHorizontalGridsDynamicCellsSample(remember { MutableStateFlow(true) })
}


@Composable
fun LazyVerticalGridContentPaddingSample(allExpandFlow: Flow<Boolean>) {
    val colors = remember {
        listOf(
            Color.Red, Color.Black, Color.White, Color.Magenta, Color.Cyan,
            Color.Yellow, Color.Blue, Color.Green, Color.Gray
        ).map { it.copy(alpha = 0.5f) }
    }
    val items = remember {
        buildList {
            repeat(49) {
                add((it + 1).toString())
            }
        }
    }
    ExpandableItem(
        title = "LazyVerticalGrid（contentPadding）",
        allExpandFlow,
        padding = 20.dp
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            modifier = Modifier
                .fillMaxWidth()
                .height(360.dp)
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
fun LazyVerticalGridContentPaddingSamplePreview() {
    LazyVerticalGridContentPaddingSample(remember { MutableStateFlow(true) })
}


@Composable
fun LazyVerticalGridReverseLayoutSample(allExpandFlow: Flow<Boolean>) {
    val colors = remember {
        listOf(
            Color.Red, Color.Black, Color.White, Color.Magenta, Color.Cyan,
            Color.Yellow, Color.Blue, Color.Green, Color.Gray
        ).map { it.copy(alpha = 0.5f) }
    }
    val items = remember {
        buildList {
            repeat(49) {
                add((it + 1).toString())
            }
        }
    }
    ExpandableItem(
        title = "LazyVerticalGrid（reverseLayout）",
        allExpandFlow,
        padding = 20.dp
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            modifier = Modifier
                .fillMaxWidth()
                .height(360.dp)
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
fun LazyVerticalGridReverseLayoutSamplePreview() {
    LazyVerticalGridReverseLayoutSample(remember { MutableStateFlow(true) })
}


@Composable
fun LazyVerticalGridVerticalArrangementSample(allExpandFlow: Flow<Boolean>) {
    val colors = remember {
        listOf(
            Color.Red, Color.Black, Color.White, Color.Magenta, Color.Cyan,
            Color.Yellow, Color.Blue, Color.Green, Color.Gray
        ).map { it.copy(alpha = 0.5f) }
    }
    val items = buildList {
        repeat(5) {
            add((it + 1).toString())
        }
    }
    ExpandableItem(
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
                Column {
                    Text(text = name)
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        modifier = Modifier
                            .width(110.dp)
                            .height(200.dp)
                            .background(Color.Red.copy(alpha = 0.5f))
                            .padding(2.dp),
                        verticalArrangement = arrangement
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
fun LazyVerticalGridVerticalArrangementSamplePreview() {
    LazyVerticalGridVerticalArrangementSample(remember { MutableStateFlow(true) })
}


@Composable
fun LazyVerticalGridHorizontalArrangementSample(allExpandFlow: Flow<Boolean>) {
    val colors = remember {
        listOf(
            Color.Red, Color.Black, Color.White, Color.Magenta, Color.Cyan,
            Color.Yellow, Color.Blue, Color.Green, Color.Gray
        ).map { it.copy(alpha = 0.5f) }
    }
    val items = buildList {
        repeat(9) {
            add((it + 1).toString())
        }
    }
    ExpandableItem(
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
                Column {
                    Text(text = name)
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(1),
                        modifier = Modifier
                            .width(110.dp)
                            .height(200.dp)
                            .background(Color.Red.copy(alpha = 0.5f))
                            .padding(2.dp),
                        horizontalArrangement = arrangement
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
fun LazyVerticalGridHorizontalArrangementSamplePreview() {
    LazyVerticalGridHorizontalArrangementSample(remember { MutableStateFlow(true) })
}


@Composable
fun LazyVerticalGridUserScrollEnabledSample(allExpandFlow: Flow<Boolean>) {
    val colors = remember {
        listOf(
            Color.Red, Color.Black, Color.White, Color.Magenta, Color.Cyan,
            Color.Yellow, Color.Blue, Color.Green, Color.Gray
        ).map { it.copy(alpha = 0.5f) }
    }
    val items = remember {
        buildList {
            repeat(49) {
                add((it + 1).toString())
            }
        }
    }
    ExpandableItem(
        title = "LazyVerticalGrid（userScrollEnabled = false）",
        allExpandFlow,
        padding = 20.dp
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            modifier = Modifier
                .fillMaxWidth()
                .height(360.dp)
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
fun LazyVerticalGridUserScrollEnabledSamplePreview() {
    LazyVerticalGridUserScrollEnabledSample(remember { MutableStateFlow(true) })
}


@Composable
fun LazyVerticalGridUserVisibleItemIndexSample(allExpandFlow: Flow<Boolean>) {
    val colors = remember {
        listOf(
            Color.Red, Color.Black, Color.White, Color.Magenta, Color.Cyan,
            Color.Yellow, Color.Blue, Color.Green, Color.Gray
        ).map { it.copy(alpha = 0.5f) }
    }
    val items = remember {
        buildList {
            repeat(49) {
                add((it + 1).toString())
            }
        }
    }
    val lazyListState = rememberLazyGridState(4)
    val itemIndexState = remember { derivedStateOf { lazyListState.firstVisibleItemIndex } }
    val offsetState =
        remember { derivedStateOf { lazyListState.firstVisibleItemScrollOffset } }
    ExpandableItem(
        title = "LazyVerticalGrid（firstVisibleItemIndex）",
        allExpandFlow,
        padding = 20.dp
    ) {
        Column {
            LazyVerticalGrid(
                columns = GridCells.Fixed(4),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(360.dp)
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
fun LazyVerticalGridUserVisibleItemIndexSamplePreview() {
    LazyVerticalGridUserVisibleItemIndexSample(remember { MutableStateFlow(true) })
}


@Composable
fun LazyVerticalGridScrollInProgressSample(allExpandFlow: Flow<Boolean>) {
    val colors = remember {
        listOf(
            Color.Red, Color.Black, Color.White, Color.Magenta, Color.Cyan,
            Color.Yellow, Color.Blue, Color.Green, Color.Gray
        ).map { it.copy(alpha = 0.5f) }
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
    ExpandableItem(
        title = "LazyVerticalGrid（isScrollInProgress）",
        allExpandFlow,
        padding = 20.dp
    ) {
        Column {
            LazyVerticalGrid(
                columns = GridCells.Fixed(4),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(360.dp)
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
fun LazyVerticalGridScrollInProgressSamplePreview() {
    LazyVerticalGridScrollInProgressSample(remember { MutableStateFlow(true) })
}


@Composable
fun LazyVerticalGridAnimateScrollToItemSample(allExpandFlow: Flow<Boolean>) {
    val colors = remember {
        listOf(
            Color.Red, Color.Black, Color.White, Color.Magenta, Color.Cyan,
            Color.Yellow, Color.Blue, Color.Green, Color.Gray
        ).map { it.copy(alpha = 0.5f) }
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
    ExpandableItem(
        title = "LazyVerticalGrid（animateScrollToItem）",
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
                    painter = painterResource(id = R.drawable.ic_expand_less),
                    contentDescription = "before"
                )
            }
            LazyVerticalGrid(
                columns = GridCells.Fixed(4),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(360.dp)
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
                    if (firstVisibleItemIndex < items.size - 4) {
                        coroutineScope.launch {
                            lazyListState.animateScrollToItem(firstVisibleItemIndex + 4)
                        }
                    }
                },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_expand_more),
                    contentDescription = "next",
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun LazyVerticalGridAnimateScrollToItemSamplePreview() {
    LazyVerticalGridAnimateScrollToItemSample(remember { MutableStateFlow(true) })
}


@Composable
fun LazyVerticalGridMultiTypeSample(allExpandFlow: Flow<Boolean>) {
    val colors = remember {
        listOf(
            Color.Red, Color.Black, Color.White, Color.Magenta, Color.Cyan,
            Color.Yellow, Color.Blue, Color.Green, Color.Gray
        ).map { it.copy(alpha = 0.5f) }
    }
    val items = buildList<Any> {
        repeat(49) {
            add((it + 1).toString())
        }
    }.toMutableList().apply {
        set(1, R.drawable.ic_navigate_before)
        set(3, R.drawable.ic_add)
        set(10, R.drawable.ic_games)
        set(17, R.drawable.ic_expand_more)
        set(18, R.drawable.ic_check)
        set(40, R.drawable.ic_info)
    }.toList()
    ExpandableItem(title = "LazyVerticalGrid（MultiType）", allExpandFlow, padding = 20.dp) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            modifier = Modifier
                .fillMaxWidth()
                .height(360.dp)
                .background(Color.Red.copy(alpha = 0.5f))
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
fun LazyVerticalGridMultiTypeSamplePreview() {
    LazyVerticalGridMultiTypeSample(remember { MutableStateFlow(true) })
}


@Composable
fun LazyVerticalGridSpanSample(allExpandFlow: Flow<Boolean>) {
    val colors = remember {
        listOf(
            Color.Red, Color.Black, Color.White, Color.Magenta, Color.Cyan,
            Color.Yellow, Color.Blue, Color.Green, Color.Gray
        ).map { it.copy(alpha = 0.5f) }
    }
    val items = remember {
        buildList {
            repeat(49) {
                add((it + 1).toString())
            }
        }
    }
    ExpandableItem(title = "LazyHorizontalGrid（span）", allExpandFlow, padding = 20.dp) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            modifier = Modifier
                .fillMaxWidth()
                .height(360.dp)
                .background(Color.Red.copy(alpha = 0.5f))
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
                        .fillMaxWidth()
                        .height(80.dp)
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
fun LazyVerticalGridSpanSamplePreview() {
    LazyVerticalGridSpanSample(remember { MutableStateFlow(true) })
}