package com.github.panpf.android.compose.samples.ui.list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
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
import com.google.accompanist.flowlayout.FlowRow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class LazyColumnFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "LazyColumn"
    }

    @Composable
    override fun DrawContent() {
        ExpandableLayout { allExpandFlow ->
            LazyColumnSample(allExpandFlow)
            LazyColumnContentPaddingSample(allExpandFlow)
            LazyColumnItemSpacedSample(allExpandFlow)
            LazyColumnReverseLayoutSample(allExpandFlow)
            LazyColumnVerticalArrangementSample(allExpandFlow)
            LazyColumnHorizontalAlignmentSample(allExpandFlow)
            LazyColumnUserScrollEnabledSample(allExpandFlow)
            LazyColumnUserVisibleItemIndexSample(allExpandFlow)
            LazyColumnScrollInProgressSample(allExpandFlow)
            LazyColumnAnimateScrollToItemSample(allExpandFlow)
            LazyColumnAnimateItemPlacementSample(allExpandFlow)
            LazyColumnLayoutInfoSample(allExpandFlow)
            LazyColumnStickerHeaderSample(allExpandFlow)
            LazyColumnMultiTypeSample(allExpandFlow)
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LazyColumnSample(allExpandFlow: Flow<Boolean>) {
    val list = remember {
        listOf(
            "??????", "??????", "??????", "??????", "?????????", "??????", "??????", "??????",
            "??????", "??????", "??????", "??????", "??????", "??????", "??????"
        )
    }
    ExpandableItem3(title = "LazyColumn", allExpandFlow, padding = 20.dp) {
        LazyColumn(
            modifier = Modifier
                .height(240.dp)
                .width(100.dp)
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
private fun LazyColumnSamplePreview() {
    LazyColumnSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LazyColumnContentPaddingSample(allExpandFlow: Flow<Boolean>) {
    val list = remember {
        listOf(
            "??????", "??????", "??????", "??????", "?????????", "??????", "??????", "??????",
            "??????", "??????", "??????", "??????", "??????", "??????", "??????"
        )
    }
    ExpandableItem3(title = "LazyColumn???contentPadding???", allExpandFlow, padding = 20.dp) {
        LazyColumn(
            modifier = Modifier
                .height(240.dp)
                .width(100.dp)
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
private fun LazyColumnContentPaddingSamplePreview() {
    LazyColumnContentPaddingSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LazyColumnItemSpacedSample(allExpandFlow: Flow<Boolean>) {
    val list = remember {
        listOf(
            "??????", "??????", "??????", "??????", "?????????", "??????", "??????", "??????",
            "??????", "??????", "??????", "??????", "??????", "??????", "??????"
        )
    }
    ExpandableItem3(title = "LazyColumn???ItemSpaced???", allExpandFlow, padding = 20.dp) {
        LazyColumn(
            modifier = Modifier
                .height(240.dp)
                .width(100.dp)
                .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                .padding(2.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
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
private fun LazyColumnItemSpacedSamplePreview() {
    LazyColumnItemSpacedSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LazyColumnReverseLayoutSample(allExpandFlow: Flow<Boolean>) {
    val list = remember {
        listOf(
            "??????", "??????", "??????", "??????", "?????????", "??????", "??????", "??????",
            "??????", "??????", "??????", "??????", "??????", "??????", "??????"
        )
    }
    ExpandableItem3(title = "LazyColumn???reverseLayout???", allExpandFlow, padding = 20.dp) {
        LazyColumn(
            modifier = Modifier
                .height(240.dp)
                .width(100.dp)
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
private fun LazyColumnReverseLayoutSamplePreview() {
    LazyColumnReverseLayoutSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LazyColumnVerticalArrangementSample(allExpandFlow: Flow<Boolean>) {
    val list = remember {
        listOf("??????", "??????", "??????", "??????")
    }
    ExpandableItem3(title = "LazyColumn???verticalArrangement???", allExpandFlow, padding = 20.dp) {
        FlowRow(mainAxisSpacing = 10.dp, crossAxisSpacing = 10.dp) {
            listOf(
                Arrangement.Top to "Top",
                Arrangement.Center to "Center",
                Arrangement.Bottom to "Bottom",
                null to "Space",
                Arrangement.SpaceBetween to "Space\nBetween",
                Arrangement.SpaceAround to "Space\nAround",
                Arrangement.SpaceEvenly to "Space\nEvenly",
            ).forEach { (arrangement, name) ->
                Column {
                    Text(text = name, modifier = Modifier.height(46.dp))
                    LazyColumn(
                        modifier = Modifier
                            .height(240.dp)
                            .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                            .padding(2.dp),
                        verticalArrangement = arrangement ?: Arrangement.spacedBy(10.dp)
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
private fun LazyColumnVerticalArrangementSamplePreview() {
    LazyColumnVerticalArrangementSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LazyColumnHorizontalAlignmentSample(allExpandFlow: Flow<Boolean>) {
    val list = remember {
        listOf("??????", "??????", "??????", "??????")
    }
    ExpandableItem3(title = "LazyColumn???horizontalAlignment???", allExpandFlow, padding = 20.dp) {
        FlowRow(mainAxisSpacing = 10.dp, crossAxisSpacing = 10.dp) {
            listOf(
                Alignment.Start to "Start",
                Alignment.CenterHorizontally to "Center\nHorizontally",
                Alignment.End to "End",
            ).forEach { (arrangement, name) ->
                Column {
                    Text(text = name, modifier = Modifier.height(46.dp))
                    LazyColumn(
                        modifier = Modifier
                            .height(240.dp)
                            .width(100.dp)
                            .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                            .padding(2.dp),
                        horizontalAlignment = arrangement
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
private fun LazyColumnHorizontalAlignmentSamplePreview() {
    LazyColumnHorizontalAlignmentSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LazyColumnUserScrollEnabledSample(allExpandFlow: Flow<Boolean>) {
    val list = remember {
        listOf(
            "??????", "??????", "??????", "??????", "?????????", "??????", "??????", "??????",
            "??????", "??????", "??????", "??????", "??????", "??????", "??????"
        )
    }
    ExpandableItem3(
        title = "LazyColumn???userScrollEnabled - false???",
        allExpandFlow,
        padding = 20.dp
    ) {
        LazyColumn(
            modifier = Modifier
                .height(240.dp)
                .width(100.dp)
                .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                .padding(2.dp),
            userScrollEnabled = false,
            state = rememberLazyListState()
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
private fun LazyColumnUserScrollEnabledSamplePreview() {
    LazyColumnUserScrollEnabledSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LazyColumnUserVisibleItemIndexSample(allExpandFlow: Flow<Boolean>) {
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
    ExpandableItem3(title = "LazyColumn???firstVisibleItemIndex???", allExpandFlow, padding = 20.dp) {
        Column {
            LazyColumn(
                modifier = Modifier
                    .height(240.dp)
                    .width(100.dp)
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
            Text(text = "firstVisibleItemIndex: ${itemIndexState.value}, firstVisibleItemScrollOffset: ${offsetState.value}")
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun LazyColumnUserVisibleItemIndexSamplePreview() {
    LazyColumnUserVisibleItemIndexSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LazyColumnScrollInProgressSample(allExpandFlow: Flow<Boolean>) {
    val list = remember {
        listOf(
            "??????", "??????", "??????", "??????", "?????????", "??????", "??????", "??????",
            "??????", "??????", "??????", "??????", "??????", "??????", "??????"
        )
    }
    val lazyListState = rememberLazyListState()
    val scrollInProgressState = remember { derivedStateOf { lazyListState.isScrollInProgress } }
    ExpandableItem3(title = "LazyColumn???isScrollInProgress???", allExpandFlow, padding = 20.dp) {
        Column {
            LazyColumn(
                modifier = Modifier
                    .height(240.dp)
                    .width(100.dp)
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
private fun LazyColumnScrollInProgressSamplePreview() {
    LazyColumnScrollInProgressSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LazyColumnAnimateScrollToItemSample(allExpandFlow: Flow<Boolean>) {
    val list = remember {
        listOf(
            "??????", "??????", "??????", "??????", "?????????", "??????", "??????", "??????",
            "??????", "??????", "??????", "??????", "??????", "??????", "??????"
        )
    }
    val lazyListState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    ExpandableItem3(title = "LazyColumn???animateScrollToItem???", allExpandFlow, padding = 20.dp) {
        Column {
            IconButton(
                onClick = {
                    val firstVisibleItemIndex = lazyListState.firstVisibleItemIndex
                    if (firstVisibleItemIndex > 0) {
                        coroutineScope.launch {
                            lazyListState.animateScrollToItem(firstVisibleItemIndex - 1)
                        }
                    }
                },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_arrow_up),
                    contentDescription = "back"
                )
            }
            LazyColumn(
                modifier = Modifier
                    .height(240.dp)
                    .width(100.dp)
                    .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                    .padding(2.dp),
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
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_arrow_down),
                    contentDescription = "forward",
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun LazyColumnAnimateScrollToItemSamplePreview() {
    LazyColumnAnimateScrollToItemSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
private fun LazyColumnAnimateItemPlacementSample(allExpandFlow: Flow<Boolean>) {
    val list = remember {
        mutableStateOf(
            listOf(
                "??????", "??????", "??????", "??????", "?????????", "??????", "??????", "??????",
                "??????", "??????", "??????", "??????", "??????", "??????", "??????"
            ).mapIndexed { index, item -> "$index:$item" }
        )
    }
    ExpandableItem3(title = "LazyColumn???animateItemPlacement???", allExpandFlow, padding = 20.dp) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(text = "?????? item ??????????????????????????????")
            LazyColumn(
                modifier = Modifier
                    .height(240.dp)
                    .width(100.dp)
                    .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                    .padding(2.dp)
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
private fun LazyColumnAnimateItemPlacementSamplePreview() {
    LazyColumnAnimateItemPlacementSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LazyColumnLayoutInfoSample(allExpandFlow: Flow<Boolean>) {
    val list = remember {
        listOf(
            "??????", "??????", "??????", "??????", "?????????", "??????", "??????", "??????",
            "??????", "??????", "??????", "??????", "??????", "??????", "??????"
        )
    }
    val lazyListState = rememberLazyListState()
    val layoutInfoState = remember { derivedStateOf { lazyListState.layoutInfo } }
    ExpandableItem3(title = "LazyColumn???layoutInfo???", allExpandFlow, padding = 20.dp) {
        Column {
            LazyColumn(
                state = lazyListState,
                modifier = Modifier
                    .height(240.dp)
                    .width(100.dp)
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
private fun LazyColumnLayoutInfoSamplePreview() {
    LazyColumnLayoutInfoSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
private fun LazyColumnStickerHeaderSample(allExpandFlow: Flow<Boolean>) {
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
    ExpandableItem3(title = "LazyColumn???stickyHeader???", allExpandFlow, padding = 20.dp) {
        LazyColumn(
            modifier = Modifier
                .height(240.dp)
                .width(100.dp)
                .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                .padding(2.dp)
        ) {
            groupList.forEachIndexed { groupIndex, group ->
                stickyHeader {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.7f))
                    ) {
                        Text(
                            text = group.first,
                            modifier = Modifier.padding(10.dp),
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }
                itemsIndexed(group.second) { itemIndex, item ->
                    ElevatedAssistChip(
                        onClick = { },
                        shape = RoundedCornerShape(50),
                        label = { Text(text = "${(groupIndex * 5) + itemIndex + 1}:$item") }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun LazyColumnStickerHeaderSamplePreview() {
    LazyColumnStickerHeaderSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LazyColumnMultiTypeSample(allExpandFlow: Flow<Boolean>) {
    val list = remember {
        listOf<Any>(
            "??????", R.drawable.ic_arrow_left, "??????", "??????", R.drawable.ic_arrow_right,
            R.drawable.ic_arrow_down, "??????", R.drawable.ic_check, "?????????", "??????", "??????", "??????",
            "??????", R.drawable.ic_clear, "??????", R.drawable.ic_close, "??????", "??????", "??????", "??????",
            R.drawable.ic_games, "??????"
        )
    }
    ExpandableItem3(title = "LazyColumn???MultiType???", allExpandFlow, padding = 20.dp) {
        LazyColumn(
            modifier = Modifier
                .height(240.dp)
                .width(100.dp)
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
private fun LazyColumnMultiTypeSamplePreview() {
    LazyColumnMultiTypeSample(remember { MutableStateFlow(true) })
}