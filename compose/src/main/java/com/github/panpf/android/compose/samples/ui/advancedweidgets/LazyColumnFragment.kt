package com.github.panpf.android.compose.samples.ui.advancedweidgets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Chip
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
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

class LazyColumnFragment : ToolbarFragment() {

    override fun createView(
        toolbar: Toolbar,
        inflater: LayoutInflater,
        parent: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        toolbar.title = "LazyColumn"
        return ComposeView(inflater.context).apply {
            setContent {
                MyTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        ExpandableLayout { allExpandFlow ->
                            LazyColumnSample(allExpandFlow)
                            LazyColumnContentPaddingSample(allExpandFlow)
                            LazyColumnReverseLayoutSample(allExpandFlow)
                            LazyColumnVerticalArrangementSample(allExpandFlow)
                            LazyColumnHorizontalAlignmentSample(allExpandFlow)
                            LazyColumnUserScrollEnabledSample(allExpandFlow)
                            LazyColumnUserVisibleItemIndexSample(allExpandFlow)
                            LazyColumnScrollInProgressSample(allExpandFlow)
                            LazyColumnAnimateScrollToItemSample(allExpandFlow)
                            // todo contentType
                            // todo key
                            // todo paging
                        }
                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LazyColumnSample(allExpandFlow: Flow<Boolean>) {
    val list = remember {
        listOf(
            "数码", "汽车", "摄影", "舞蹈", "二次元", "音乐", "科技", "健身",
            "游戏", "文学", "运动", "生活", "美食", "动物", "时尚"
        )
    }
    ExpandableItem(title = "LazyColumn", allExpandFlow, padding = 20.dp) {
        LazyColumn(
            modifier = Modifier
                .height(240.dp)
                .width(100.dp)
                .background(Color.Red.copy(alpha = 0.5f))
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
fun LazyColumnSamplePreview() {
    LazyColumnSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LazyColumnContentPaddingSample(allExpandFlow: Flow<Boolean>) {
    val list = remember {
        listOf(
            "数码", "汽车", "摄影", "舞蹈", "二次元", "音乐", "科技", "健身",
            "游戏", "文学", "运动", "生活", "美食", "动物", "时尚"
        )
    }
    ExpandableItem(title = "LazyColumn（contentPadding）", allExpandFlow, padding = 20.dp) {
        LazyColumn(
            modifier = Modifier
                .height(240.dp)
                .width(100.dp)
                .background(Color.Red.copy(alpha = 0.5f)),
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

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun LazyColumnContentPaddingSamplePreview() {
    LazyColumnContentPaddingSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LazyColumnReverseLayoutSample(allExpandFlow: Flow<Boolean>) {
    val list = remember {
        listOf(
            "数码", "汽车", "摄影", "舞蹈", "二次元", "音乐", "科技", "健身",
            "游戏", "文学", "运动", "生活", "美食", "动物", "时尚"
        )
    }
    ExpandableItem(title = "LazyColumn（reverseLayout）", allExpandFlow, padding = 20.dp) {
        LazyColumn(
            modifier = Modifier
                .height(240.dp)
                .width(100.dp)
                .background(Color.Red.copy(alpha = 0.5f)),
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
fun LazyColumnReverseLayoutSamplePreview() {
    LazyColumnReverseLayoutSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LazyColumnVerticalArrangementSample(allExpandFlow: Flow<Boolean>) {
    val list = remember {
        listOf("数码", "汽车", "摄影", "舞蹈")
    }
    ExpandableItem(title = "LazyColumn（verticalArrangement）", allExpandFlow, padding = 20.dp) {
        FlowRow(mainAxisSpacing = 10.dp, crossAxisSpacing = 10.dp) {
            listOf(
                Arrangement.Top to "Top",
                Arrangement.Center to "Center",
                Arrangement.Bottom to "Bottom",
                Arrangement.SpaceBetween to "Space\nBetween",
                Arrangement.SpaceAround to "Space\nAround",
                Arrangement.SpaceEvenly to "Space\nEvenly",
            ).forEach { (arrangement, name) ->
                Column {
                    Text(text = name, modifier = Modifier.height(46.dp))
                    LazyColumn(
                        modifier = Modifier
                            .height(240.dp)
                            .background(Color.Red.copy(alpha = 0.5f)),
                        verticalArrangement = arrangement
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
fun LazyColumnVerticalArrangementSamplePreview() {
    LazyColumnVerticalArrangementSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LazyColumnHorizontalAlignmentSample(allExpandFlow: Flow<Boolean>) {
    val list = remember {
        listOf("数码", "汽车", "摄影", "舞蹈")
    }
    ExpandableItem(title = "LazyColumn（horizontalAlignment）", allExpandFlow, padding = 20.dp) {
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
                            .background(Color.Red.copy(alpha = 0.5f)),
                        horizontalAlignment = arrangement
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
fun LazyColumnHorizontalAlignmentSamplePreview() {
    LazyColumnHorizontalAlignmentSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LazyColumnUserScrollEnabledSample(allExpandFlow: Flow<Boolean>) {
    val list = remember {
        listOf(
            "数码", "汽车", "摄影", "舞蹈", "二次元", "音乐", "科技", "健身",
            "游戏", "文学", "运动", "生活", "美食", "动物", "时尚"
        )
    }
    ExpandableItem(
        title = "LazyColumn（userScrollEnabled - false）",
        allExpandFlow,
        padding = 20.dp
    ) {
        LazyColumn(
            modifier = Modifier
                .height(240.dp)
                .width(100.dp)
                .background(Color.Red.copy(alpha = 0.5f)),
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
fun LazyColumnUserScrollEnabledSamplePreview() {
    LazyColumnUserScrollEnabledSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LazyColumnUserVisibleItemIndexSample(allExpandFlow: Flow<Boolean>) {
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
    ExpandableItem(title = "LazyColumn（firstVisibleItemIndex）", allExpandFlow, padding = 20.dp) {
        Column {
            LazyColumn(
                modifier = Modifier
                    .height(240.dp)
                    .width(100.dp)
                    .background(Color.Red.copy(alpha = 0.5f)),
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
fun LazyColumnUserVisibleItemIndexSamplePreview() {
    LazyColumnUserVisibleItemIndexSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LazyColumnScrollInProgressSample(allExpandFlow: Flow<Boolean>) {
    val list = remember {
        listOf(
            "数码", "汽车", "摄影", "舞蹈", "二次元", "音乐", "科技", "健身",
            "游戏", "文学", "运动", "生活", "美食", "动物", "时尚"
        )
    }
    val lazyListState = rememberLazyListState()
    val scrollInProgressState = remember { derivedStateOf { lazyListState.isScrollInProgress } }
    ExpandableItem(title = "LazyColumn（isScrollInProgress）", allExpandFlow, padding = 20.dp) {
        Column {
            LazyColumn(
                modifier = Modifier
                    .height(240.dp)
                    .width(100.dp)
                    .background(Color.Red.copy(alpha = 0.5f)),
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
fun LazyColumnScrollInProgressSamplePreview() {
    LazyColumnScrollInProgressSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LazyColumnAnimateScrollToItemSample(allExpandFlow: Flow<Boolean>) {
    val list = remember {
        listOf(
            "数码", "汽车", "摄影", "舞蹈", "二次元", "音乐", "科技", "健身",
            "游戏", "文学", "运动", "生活", "美食", "动物", "时尚"
        )
    }
    val lazyListState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    ExpandableItem(title = "LazyColumn（animateScrollToItem）", allExpandFlow, padding = 20.dp) {
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
                    painter = painterResource(id = R.drawable.ic_expand_less),
                    contentDescription = "back"
                )
            }
            LazyColumn(
                modifier = Modifier
                    .height(240.dp)
                    .width(100.dp)
                    .background(Color.Red.copy(alpha = 0.5f)),
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
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_expand_more),
                    contentDescription = "forward",
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun LazyColumnAnimateScrollToItemSamplePreview() {
    LazyColumnAnimateScrollToItemSample(remember { MutableStateFlow(true) })
}