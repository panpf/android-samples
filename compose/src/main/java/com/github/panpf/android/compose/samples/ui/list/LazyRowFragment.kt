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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedAssistChip
import androidx.compose.material3.ExperimentalMaterial3Api
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
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class LazyRowFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "LazyRow"
    }

    @Composable
    override fun DrawContent() {
        ExpandableLayout { allExpandFlow ->
            LazyRowSample(allExpandFlow)
            LazyRowContentPaddingSample(allExpandFlow)
            LazyRowItemSpacedSample(allExpandFlow)
            LazyRowReverseLayoutSample(allExpandFlow)
            LazyRowHorizontalArrangementSample(allExpandFlow)
            LazyRowVerticalAlignmentSample(allExpandFlow)
            LazyRowUserScrollEnabledSample(allExpandFlow)
            LazyRowUserVisibleItemIndexSample(allExpandFlow)
            LazyRowScrollInProgressSample(allExpandFlow)
            LazyRowAnimateScrollToItemSample(allExpandFlow)
            LazyRowAnimateItemPlacementSample(allExpandFlow)
            LazyRowLayoutInfoSample(allExpandFlow)
            LazyRowStickerHeaderSample(allExpandFlow)
            LazyRowMultiTypeSample(allExpandFlow)
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LazyRowSample(allExpandFlow: Flow<Boolean>) {
    val list = remember {
        listOf(
            "??????", "??????", "??????", "??????", "?????????", "??????", "??????", "??????",
            "??????", "??????", "??????", "??????", "??????", "??????", "??????"
        )
    }
    ExpandableItem3(title = "LazyRow", allExpandFlow, padding = 20.dp) {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                .padding(2.dp)
        ) {
            itemsIndexed(list) { index, item ->
                ElevatedAssistChip(
                    onClick = { },
                    shape = RoundedCornerShape(50),
                    label = { Text(text = "$index:$item") }
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun LazyRowSamplePreview() {
    LazyRowSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LazyRowContentPaddingSample(allExpandFlow: Flow<Boolean>) {
    val list = remember {
        listOf(
            "??????", "??????", "??????", "??????", "?????????", "??????", "??????", "??????",
            "??????", "??????", "??????", "??????", "??????", "??????", "??????"
        )
    }
    ExpandableItem3(title = "LazyRow???contentPadding???", allExpandFlow, padding = 20.dp) {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                .padding(2.dp),
            contentPadding = PaddingValues(10.dp)
        ) {
            itemsIndexed(list) { index, item ->
                ElevatedAssistChip(
                    onClick = { },
                    shape = RoundedCornerShape(50),
                    label = { Text(text = "$index:$item") }
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun LazyRowContentPaddingSamplePreview() {
    LazyRowContentPaddingSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LazyRowItemSpacedSample(allExpandFlow: Flow<Boolean>) {
    val list = remember {
        listOf(
            "??????", "??????", "??????", "??????", "?????????", "??????", "??????", "??????",
            "??????", "??????", "??????", "??????", "??????", "??????", "??????"
        )
    }
    ExpandableItem3(title = "LazyRow???ItemSpaced???", allExpandFlow, padding = 20.dp) {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                .padding(2.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            itemsIndexed(list) { index, item ->
                ElevatedAssistChip(
                    onClick = { },
                    shape = RoundedCornerShape(50),
                    label = { Text(text = "$index:$item") }
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun LazyRowItemSpacedSamplePreview() {
    LazyRowItemSpacedSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LazyRowReverseLayoutSample(allExpandFlow: Flow<Boolean>) {
    val list = remember {
        listOf(
            "??????", "??????", "??????", "??????", "?????????", "??????", "??????", "??????",
            "??????", "??????", "??????", "??????", "??????", "??????", "??????"
        )
    }
    ExpandableItem3(title = "LazyRow???reverseLayout???", allExpandFlow, padding = 20.dp) {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                .padding(2.dp),
            reverseLayout = true
        ) {
            itemsIndexed(list) { index, item ->
                ElevatedAssistChip(
                    onClick = { },
                    shape = RoundedCornerShape(50),
                    label = { Text(text = "$index:$item") }
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun LazyRowReverseLayoutSamplePreview() {
    LazyRowReverseLayoutSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LazyRowHorizontalArrangementSample(allExpandFlow: Flow<Boolean>) {
    val list = remember {
        listOf("??????", "??????", "??????", "??????")
    }
    ExpandableItem3(title = "LazyRow???horizontalArrangement???", allExpandFlow, padding = 20.dp) {
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
                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                            .padding(2.dp),
                        horizontalArrangement = arrangement ?: Arrangement.spacedBy(10.dp)
                    ) {
                        itemsIndexed(list) { index, item ->
                            ElevatedAssistChip(
                                onClick = { },
                                shape = RoundedCornerShape(50),
                                label = { Text(text = "$index:$item") }
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
private fun LazyRowHorizontalArrangementSamplePreview() {
    LazyRowHorizontalArrangementSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LazyRowVerticalAlignmentSample(allExpandFlow: Flow<Boolean>) {
    val list = remember {
        listOf(
            "??????", "??????", "??????", "??????", "?????????", "??????", "??????", "??????",
            "??????", "??????", "??????", "??????", "??????", "??????", "??????"
        )
    }
    ExpandableItem3(title = "LazyRow???verticalAlignment???", allExpandFlow, padding = 20.dp) {
        Column {
            listOf(
                Alignment.Top to "Top",
                Alignment.CenterVertically to "CenterVertically",
                Alignment.Bottom to "Bottom",
            ).forEachIndexed { index, (arrangement, name) ->
                if (index > 0) {
                    Spacer(modifier = Modifier.size(10.dp))
                }
                Column {
                    Text(text = name)
                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp)
                            .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                            .padding(2.dp),
                        verticalAlignment = arrangement
                    ) {
                        itemsIndexed(list) { index, item ->
                            ElevatedAssistChip(
                                onClick = { },
                                shape = RoundedCornerShape(50),
                                label = { Text(text = "$index:$item") }
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
private fun LazyRowVerticalAlignmentSamplePreview() {
    LazyRowVerticalAlignmentSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LazyRowUserScrollEnabledSample(allExpandFlow: Flow<Boolean>) {
    val list = remember {
        listOf(
            "??????", "??????", "??????", "??????", "?????????", "??????", "??????", "??????",
            "??????", "??????", "??????", "??????", "??????", "??????", "??????"
        )
    }
    ExpandableItem3(title = "LazyRow???userScrollEnabled - false???", allExpandFlow, padding = 20.dp) {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                .padding(2.dp),
            userScrollEnabled = false,
            state = rememberLazyListState()
        ) {
            itemsIndexed(list) { index, item ->
                ElevatedAssistChip(
                    onClick = { },
                    shape = RoundedCornerShape(50),
                    label = { Text(text = "$index:$item") })
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun LazyRowUserScrollEnabledSamplePreview() {
    LazyRowUserScrollEnabledSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LazyRowUserVisibleItemIndexSample(allExpandFlow: Flow<Boolean>) {
    val list = remember {
        listOf(
            "??????", "??????", "??????", "??????", "?????????", "??????", "??????", "??????",
            "??????", "??????", "??????", "??????", "??????", "??????", "??????"
        )
    }
    val lazyListState = rememberLazyListState(3)
    val itemIndexState = remember { derivedStateOf { lazyListState.firstVisibleItemIndex } }
    val offsetState =
        remember { derivedStateOf { lazyListState.firstVisibleItemScrollOffset } }
    ExpandableItem3(title = "LazyRow???firstVisibleItemIndex???", allExpandFlow, padding = 20.dp) {
        Column {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                    .padding(2.dp),
                state = lazyListState
            ) {
                itemsIndexed(list) { index, item ->
                    ElevatedAssistChip(
                        onClick = { },
                        shape = RoundedCornerShape(50),
                        label = { Text(text = "$index:$item") })
                }
            }
            Text(text = "firstVisibleItemIndex: ${itemIndexState.value}, firstVisibleItemScrollOffset: ${offsetState.value}")
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun LazyRowUserVisibleItemIndexSamplePreview() {
    LazyRowUserVisibleItemIndexSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LazyRowScrollInProgressSample(allExpandFlow: Flow<Boolean>) {
    val list = remember {
        listOf(
            "??????", "??????", "??????", "??????", "?????????", "??????", "??????", "??????",
            "??????", "??????", "??????", "??????", "??????", "??????", "??????"
        )
    }
    val lazyListState = rememberLazyListState()
    val scrollInProgressState = remember { derivedStateOf { lazyListState.isScrollInProgress } }
    ExpandableItem3(title = "LazyRow???isScrollInProgress???", allExpandFlow, padding = 20.dp) {
        Column {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                    .padding(2.dp),
                state = lazyListState
            ) {
                itemsIndexed(list) { index, item ->
                    ElevatedAssistChip(
                        onClick = { },
                        shape = RoundedCornerShape(50),
                        label = { Text(text = "$index:$item") }
                    )
                }
            }
            Text(text = "isScrollInProgress: ${scrollInProgressState.value}")
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun LazyRowScrollInProgressSamplePreview() {
    LazyRowScrollInProgressSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LazyRowAnimateScrollToItemSample(allExpandFlow: Flow<Boolean>) {
    val list = remember {
        listOf(
            "??????", "??????", "??????", "??????", "?????????", "??????", "??????", "??????",
            "??????", "??????", "??????", "??????", "??????", "??????", "??????"
        )
    }
    val lazyListState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    ExpandableItem3(title = "LazyRow???animateScrollToItem???", allExpandFlow, padding = 20.dp) {
        Row {
            IconButton(
                onClick = {
                    val firstVisibleItemIndex = lazyListState.firstVisibleItemIndex
                    if (firstVisibleItemIndex > 0) {
                        coroutineScope.launch {
                            lazyListState.animateScrollToItem(firstVisibleItemIndex - 1)
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
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                    .padding(2.dp)
                    .weight(1f),
                state = lazyListState
            ) {
                itemsIndexed(list) { index, item ->
                    ElevatedAssistChip(
                        onClick = {
                            coroutineScope.launch {
                                lazyListState.animateScrollToItem(index)
                            }
                        },
                        shape = RoundedCornerShape(50),
                        label = {
                            Text(text = "$index:$item")
                        }
                    )
                }
            }
            IconButton(
                onClick = {
                    val firstVisibleItemIndex = lazyListState.firstVisibleItemIndex
                    if (firstVisibleItemIndex < list.size - 1) {
                        coroutineScope.launch {
                            lazyListState.animateScrollToItem(firstVisibleItemIndex + 1)
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
private fun LazyRowAnimateScrollToItemSamplePreview() {
    LazyRowAnimateScrollToItemSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
private fun LazyRowAnimateItemPlacementSample(allExpandFlow: Flow<Boolean>) {
    val list = remember {
        mutableStateOf(
            listOf(
                "??????", "??????", "??????", "??????", "?????????", "??????", "??????", "??????",
                "??????", "??????", "??????", "??????", "??????", "??????", "??????"
            ).mapIndexed { index, item -> "$index:$item" }
        )
    }
    ExpandableItem3(title = "LazyRow???animateItemPlacement???", allExpandFlow, padding = 20.dp) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(text = "?????? item ??????????????????????????????")
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                    .padding(2.dp),
                contentPadding = PaddingValues(10.dp)
            ) {
                itemsIndexed(
                    items = list.value,
                    key = { _, item -> item }
                ) { _, item ->
                    ElevatedAssistChip(
                        onClick = {
                            list.value = list.value.toMutableList().apply {
                                remove(item)
                            }.toList()
                        },
                        shape = RoundedCornerShape(50),
                        modifier = Modifier.animateItemPlacement(),
                        label = {
                            Text(text = item)
                        }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun LazyRowAnimateItemPlacementSamplePreview() {
    LazyRowAnimateItemPlacementSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LazyRowLayoutInfoSample(allExpandFlow: Flow<Boolean>) {
    val list = remember {
        listOf(
            "??????", "??????", "??????", "??????", "?????????", "??????", "??????", "??????",
            "??????", "??????", "??????", "??????", "??????", "??????", "??????"
        )
    }
    val lazyListState = rememberLazyListState()
    val layoutInfoState = remember { derivedStateOf { lazyListState.layoutInfo } }
    ExpandableItem3(title = "LazyRow???layoutInfo???", allExpandFlow, padding = 20.dp) {
        Column {
            LazyRow(
                state = lazyListState,
                modifier = Modifier
                    .fillMaxWidth()
                    .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                    .padding(2.dp)
            ) {
                itemsIndexed(list) { index, item ->
                    ElevatedAssistChip(
                        onClick = { },
                        shape = RoundedCornerShape(50),
                        label = { Text(text = "$index:$item") }
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
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun LazyRowLayoutInfoSamplePreview() {
    LazyRowLayoutInfoSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
private fun LazyRowStickerHeaderSample(allExpandFlow: Flow<Boolean>) {
    val groupList = remember {
        listOf(
            "??????", "??????", "??????", "??????", "?????????", "??????", "??????", "??????",
            "??????", "??????", "??????", "??????", "??????", "??????", "??????"
        ).chunked(5).mapIndexed { index, strings ->
            val start = (index * 5) + 1
            val groupName = "$start - ${start + strings.size - 1}"
            groupName to strings
        }
    }
    ExpandableItem3(title = "LazyRow???stickyHeader???", allExpandFlow, padding = 20.dp) {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                .padding(2.dp)
        ) {
            groupList.forEachIndexed { groupIndex, group ->
                stickyHeader {
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(70.dp)
                            .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.7f))
                    ) {
                        Text(
                            text = group.first,
                            modifier = Modifier.align(Alignment.Center),
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }
                itemsIndexed(group.second) { itemIndex, item ->
                    ElevatedAssistChip(
                        onClick = { },
                        shape = RoundedCornerShape(50),
                        label = {
                            Text(text = "${(groupIndex * 5) + itemIndex + 1}:$item")
                        }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun LazyRowStickerHeaderSamplePreview() {
    LazyRowStickerHeaderSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LazyRowMultiTypeSample(allExpandFlow: Flow<Boolean>) {
    val list = remember {
        listOf<Any>(
            "??????", R.drawable.ic_arrow_left, "??????", "??????", R.drawable.ic_arrow_right,
            R.drawable.ic_arrow_down, "??????", R.drawable.ic_check, "?????????", "??????", "??????", "??????",
            "??????", R.drawable.ic_clear, "??????", R.drawable.ic_close, "??????", "??????", "??????", "??????",
            R.drawable.ic_games, "??????"
        )
    }
    ExpandableItem3(title = "LazyRow???MultiType???", allExpandFlow, padding = 20.dp) {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                .padding(2.dp),
        ) {
            itemsIndexed(
                items = list,
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
                        ElevatedAssistChip(
                            onClick = { },
                            shape = RoundedCornerShape(50),
                            label = { Text(text = "$index:$item") }
                        )
                    }
                    is Int -> {
                        FilledTonalIconButton(onClick = { }) {
                            Image(painter = painterResource(id = item), contentDescription = "icon")
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun LazyRowMultiTypeSamplePreview() {
    LazyRowMultiTypeSample(remember { MutableStateFlow(true) })
}