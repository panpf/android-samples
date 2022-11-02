package com.github.panpf.android.compose.samples.ui.advancedweidgets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Chip
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.IconButton
import androidx.compose.material3.FilledTonalIconButton
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

class LazyRowFragment : ToolbarFragment() {

    override fun createView(
        toolbar: Toolbar,
        inflater: LayoutInflater,
        parent: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        toolbar.title = "LazyRow"
        return ComposeView(inflater.context).apply {
            setContent {
                MyTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
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
                            LazyRowMultiTypeSample(allExpandFlow)
                            LazyRowAnimateItemPlacementSample(allExpandFlow)
                            LazyRowLayoutInfoSample(allExpandFlow)
                            LazyRowStickerHeaderSample(allExpandFlow)
                        }
                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LazyRowSample(allExpandFlow: Flow<Boolean>) {
    val list = remember {
        listOf(
            "数码", "汽车", "摄影", "舞蹈", "二次元", "音乐", "科技", "健身",
            "游戏", "文学", "运动", "生活", "美食", "动物", "时尚"
        )
    }
    ExpandableItem(title = "LazyRow", allExpandFlow, padding = 20.dp) {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .border(2.dp, Color.Red)
                .padding(2.dp)
        ) {
            itemsIndexed(list) { index, item ->
                Chip(onClick = { }) {
                    Text(text = "$index:$item")
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun LazyRowSamplePreview() {
    LazyRowSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LazyRowContentPaddingSample(allExpandFlow: Flow<Boolean>) {
    val list = remember {
        listOf(
            "数码", "汽车", "摄影", "舞蹈", "二次元", "音乐", "科技", "健身",
            "游戏", "文学", "运动", "生活", "美食", "动物", "时尚"
        )
    }
    ExpandableItem(title = "LazyRow（contentPadding）", allExpandFlow, padding = 20.dp) {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .border(2.dp, Color.Red)
                .padding(2.dp),
            contentPadding = PaddingValues(10.dp)
        ) {
            itemsIndexed(list) { index, item ->
                Chip(onClick = { }) {
                    Text(text = "$index:$item")
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LazyRowItemSpacedSample(allExpandFlow: Flow<Boolean>) {
    val list = remember {
        listOf(
            "数码", "汽车", "摄影", "舞蹈", "二次元", "音乐", "科技", "健身",
            "游戏", "文学", "运动", "生活", "美食", "动物", "时尚"
        )
    }
    ExpandableItem(title = "LazyRow（ItemSpaced）", allExpandFlow, padding = 20.dp) {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .border(2.dp, Color.Red)
                .padding(2.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            itemsIndexed(list) { index, item ->
                Chip(onClick = { }) {
                    Text(text = "$index:$item")
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun LazyRowItemSpacedSamplePreview() {
    LazyRowItemSpacedSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LazyRowReverseLayoutSample(allExpandFlow: Flow<Boolean>) {
    val list = remember {
        listOf(
            "数码", "汽车", "摄影", "舞蹈", "二次元", "音乐", "科技", "健身",
            "游戏", "文学", "运动", "生活", "美食", "动物", "时尚"
        )
    }
    ExpandableItem(title = "LazyRow（reverseLayout）", allExpandFlow, padding = 20.dp) {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .border(2.dp, Color.Red)
                .padding(2.dp),
            reverseLayout = true
        ) {
            itemsIndexed(list) { index, item ->
                Chip(onClick = { }) {
                    Text(text = "$index:$item")
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun LazyRowReverseLayoutSamplePreview() {
    LazyRowReverseLayoutSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LazyRowHorizontalArrangementSample(allExpandFlow: Flow<Boolean>) {
    val list = remember {
        listOf("数码", "汽车", "摄影", "舞蹈")
    }
    ExpandableItem(title = "LazyRow（horizontalArrangement）", allExpandFlow, padding = 20.dp) {
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
                            .border(2.dp, Color.Red)
                            .padding(2.dp),
                        horizontalArrangement = arrangement ?: Arrangement.spacedBy(10.dp)
                    ) {
                        itemsIndexed(list) { index, item ->
                            Chip(onClick = { }) {
                                Text(text = "$index:$item")
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
fun LazyRowHorizontalArrangementSamplePreview() {
    LazyRowHorizontalArrangementSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LazyRowVerticalAlignmentSample(allExpandFlow: Flow<Boolean>) {
    val list = remember {
        listOf(
            "数码", "汽车", "摄影", "舞蹈", "二次元", "音乐", "科技", "健身",
            "游戏", "文学", "运动", "生活", "美食", "动物", "时尚"
        )
    }
    ExpandableItem(title = "LazyRow（verticalAlignment）", allExpandFlow, padding = 20.dp) {
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
                            .border(2.dp, Color.Red)
                            .padding(2.dp),
                        verticalAlignment = arrangement
                    ) {
                        itemsIndexed(list) { index, item ->
                            Chip(onClick = { }) {
                                Text(text = "$index:$item")
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
fun LazyRowVerticalAlignmentSamplePreview() {
    LazyRowVerticalAlignmentSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LazyRowUserScrollEnabledSample(allExpandFlow: Flow<Boolean>) {
    val list = remember {
        listOf(
            "数码", "汽车", "摄影", "舞蹈", "二次元", "音乐", "科技", "健身",
            "游戏", "文学", "运动", "生活", "美食", "动物", "时尚"
        )
    }
    ExpandableItem(title = "LazyRow（userScrollEnabled - false）", allExpandFlow, padding = 20.dp) {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .border(2.dp, Color.Red)
                .padding(2.dp),
            userScrollEnabled = false,
            state = rememberLazyListState()
        ) {
            itemsIndexed(list) { index, item ->
                Chip(onClick = { }) {
                    Text(text = "$index:$item")
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun LazyRowUserScrollEnabledSamplePreview() {
    LazyRowUserScrollEnabledSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LazyRowUserVisibleItemIndexSample(allExpandFlow: Flow<Boolean>) {
    val list = remember {
        listOf(
            "数码", "汽车", "摄影", "舞蹈", "二次元", "音乐", "科技", "健身",
            "游戏", "文学", "运动", "生活", "美食", "动物", "时尚"
        )
    }
    val lazyListState = rememberLazyListState(3)
    val itemIndexState = remember { derivedStateOf { lazyListState.firstVisibleItemIndex } }
    val offsetState =
        remember { derivedStateOf { lazyListState.firstVisibleItemScrollOffset } }
    ExpandableItem(title = "LazyRow（firstVisibleItemIndex）", allExpandFlow, padding = 20.dp) {
        Column {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(2.dp, Color.Red)
                    .padding(2.dp),
                state = lazyListState
            ) {
                itemsIndexed(list) { index, item ->
                    Chip(onClick = {}) {
                        Text(text = "$index:$item")
                    }
                }
            }
            Text(text = "firstVisibleItemIndex: ${itemIndexState.value}, firstVisibleItemScrollOffset: ${offsetState.value}")
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun LazyRowUserVisibleItemIndexSamplePreview() {
    LazyRowUserVisibleItemIndexSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LazyRowScrollInProgressSample(allExpandFlow: Flow<Boolean>) {
    val list = remember {
        listOf(
            "数码", "汽车", "摄影", "舞蹈", "二次元", "音乐", "科技", "健身",
            "游戏", "文学", "运动", "生活", "美食", "动物", "时尚"
        )
    }
    val lazyListState = rememberLazyListState()
    val scrollInProgressState = remember { derivedStateOf { lazyListState.isScrollInProgress } }
    ExpandableItem(title = "LazyRow（isScrollInProgress）", allExpandFlow, padding = 20.dp) {
        Column {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(2.dp, Color.Red)
                    .padding(2.dp),
                state = lazyListState
            ) {
                itemsIndexed(list) { index, item ->
                    Chip(onClick = {}) {
                        Text(text = "$index:$item")
                    }
                }
            }
            Text(text = "isScrollInProgress: ${scrollInProgressState.value}")
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun LazyRowScrollInProgressSamplePreview() {
    LazyRowScrollInProgressSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LazyRowAnimateScrollToItemSample(allExpandFlow: Flow<Boolean>) {
    val list = remember {
        listOf(
            "数码", "汽车", "摄影", "舞蹈", "二次元", "音乐", "科技", "健身",
            "游戏", "文学", "运动", "生活", "美食", "动物", "时尚"
        )
    }
    val lazyListState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    ExpandableItem(title = "LazyRow（animateScrollToItem）", allExpandFlow, padding = 20.dp) {
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
                    painter = painterResource(id = R.drawable.ic_navigate_before),
                    contentDescription = "before"
                )
            }
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(2.dp, Color.Red)
                    .padding(2.dp)
                    .weight(1f),
                state = lazyListState
            ) {
                itemsIndexed(list) { index, item ->
                    Chip(onClick = {
                        coroutineScope.launch {
                            lazyListState.animateScrollToItem(index)
                        }
                    }) {
                        Text(text = "$index:$item")
                    }
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
                    painter = painterResource(id = R.drawable.ic_navigate_next),
                    contentDescription = "next",
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun LazyRowAnimateScrollToItemSamplePreview() {
    LazyRowAnimateScrollToItemSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LazyRowMultiTypeSample(allExpandFlow: Flow<Boolean>) {
    val list = remember {
        listOf<Any>(
            "数码", R.drawable.ic_navigate_before, "汽车", "摄影", R.drawable.ic_navigate_next,
            R.drawable.ic_expand_more, "舞蹈", R.drawable.ic_check, "二次元", "音乐", "科技", "健身",
            "游戏", R.drawable.ic_clear, "文学", R.drawable.ic_close, "运动", "生活", "美食", "动物",
            R.drawable.ic_games, "时尚"
        )
    }
    ExpandableItem(title = "LazyRow（MultiType）", allExpandFlow, padding = 20.dp) {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .border(2.dp, Color.Red)
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
                        Chip(onClick = { }) {
                            Text(text = "$index:$item")
                        }
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
fun LazyRowMultiTypeSamplePreview() {
    LazyRowMultiTypeSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
fun LazyRowAnimateItemPlacementSample(allExpandFlow: Flow<Boolean>) {
    val list = remember {
        mutableStateOf(
            listOf(
                "数码", "汽车", "摄影", "舞蹈", "二次元", "音乐", "科技", "健身",
                "游戏", "文学", "运动", "生活", "美食", "动物", "时尚"
            ).mapIndexed { index, item -> "$index:$item" }
        )
    }
    ExpandableItem(title = "LazyRow（animateItemPlacement）", allExpandFlow, padding = 20.dp) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(text = "点击 item 删除它，然后触发动画")
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(2.dp, Color.Red)
                    .padding(2.dp),
                contentPadding = PaddingValues(10.dp)
            ) {
                itemsIndexed(
                    items = list.value,
                    key = { _, item -> item }
                ) { _, item ->
                    Chip(
                        onClick = {
                            list.value = list.value.toMutableList().apply {
                                remove(item)
                            }.toList()
                        },
                        modifier = Modifier.animateItemPlacement()
                    ) {
                        Text(text = item)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun LazyRowAnimateItemPlacementSamplePreview() {
    LazyRowAnimateItemPlacementSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LazyRowLayoutInfoSample(allExpandFlow: Flow<Boolean>) {
    val list = remember {
        listOf(
            "数码", "汽车", "摄影", "舞蹈", "二次元", "音乐", "科技", "健身",
            "游戏", "文学", "运动", "生活", "美食", "动物", "时尚"
        )
    }
    val lazyListState = rememberLazyListState()
    val layoutInfoState = remember { derivedStateOf { lazyListState.layoutInfo } }
    ExpandableItem(title = "LazyRow（layoutInfo）", allExpandFlow, padding = 20.dp) {
        Column {
            LazyRow(
                state = lazyListState,
                modifier = Modifier
                    .fillMaxWidth()
                    .border(2.dp, Color.Red)
                    .padding(2.dp)
            ) {
                itemsIndexed(list) { index, item ->
                    Chip(onClick = { }) {
                        Text(text = "$index:$item")
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
fun LazyRowLayoutInfoSamplePreview() {
    LazyRowLayoutInfoSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
fun LazyRowStickerHeaderSample(allExpandFlow: Flow<Boolean>) {
    val groupList = remember {
        listOf(
            "数码", "汽车", "摄影", "舞蹈", "二次元", "音乐", "科技", "健身",
            "游戏", "文学", "运动", "生活", "美食", "动物", "时尚"
        ).chunked(5).mapIndexed { index, strings ->
            val start = (index * 5) + 1
            val groupName = "$start - ${start + strings.size - 1}"
            groupName to strings
        }
    }
    ExpandableItem(title = "LazyRow（stickyHeader）", allExpandFlow, padding = 20.dp) {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .border(2.dp, Color.Red)
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
                        Text(text = group.first, modifier = Modifier.align(Alignment.Center))
                    }
                }
                itemsIndexed(group.second) { itemIndex, item ->
                    Chip(onClick = { }) {
                        Text(text = "${(groupIndex * 5) + itemIndex + 1}:$item")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun LazyRowStickerHeaderSamplePreview() {
    LazyRowStickerHeaderSample(remember { MutableStateFlow(true) })
}