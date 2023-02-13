package com.github.panpf.android.compose.samples.ui.gestures

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollDispatcher
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.rememberNestedScrollInteropConnection
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem3
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

class ScrollFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "Scroll"
    }

    @Composable
    override fun DrawContent() {
        ExpandableLayout { allExpandFlow ->
            ScrollVerticalScrollSample(allExpandFlow)
            ScrollHorizontalScrollSample(allExpandFlow)
            ScrollScrollableSample(allExpandFlow)
            ScrollNestedScrollAutoSample(allExpandFlow)
            ScrollNestedScrollParentNestedScrollConnectionSample(allExpandFlow)
            ScrollNestedScrollChildNestedScrollDispatcherDispatcherSample(allExpandFlow)
            // todo ScrollNestedScrollInteropWithViewSample(allExpandFlow)
        }
    }
}


private const val text =
    "躲过了暴风雪之后，我们再次起程赶路，在一处斜坡下发现了阿宁他们的马队，同时也发现了海底墓穴影画之中的那一座神秘雪山，赫然出现在了我们的视野尽头。就在我们询问向导如何才能到达那里的时候，顺子却摇头，说我们绝对无法过去。----摘自《盗墓笔记》 - 云顶天宫（下）第一章 五圣雪山，网址：http://www.daomubiji.com/yun-ding-tian-gong-15.html"


@Composable
fun ScrollVerticalScrollSample(allExpandFlow: Flow<Boolean>) {
    val desc = """
        verticalScroll 修饰符提供一种最简单的垂直滚动方法，可让用户在元素内容边界大于最大尺寸约束时滚动元素。您无需转换或偏移内容。
    """.trimIndent()
    val coroutineScope = rememberCoroutineScope()
    val scrollState = rememberScrollState()
    ExpandableItem3(
        title = "Scroll（verticalScroll）",
        allExpandFlow,
        padding = 20.dp,
        desc = desc,
    ) {
        Text(text = "reverseScrolling=false")
        Text(
            text = text,
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.5f))
                .verticalScroll(scrollState, reverseScrolling = false)
        )

        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "reverseScrolling=true")
        Text(
            text = text,
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.5f))
                .verticalScroll(scrollState, reverseScrolling = true)
        )

        Spacer(modifier = Modifier.height(20.dp))
        Column(modifier = Modifier.align(Alignment.CenterHorizontally)) {
            IconButton(onClick = {
                coroutineScope.launch {
                    scrollState.animateScrollBy(-100f)
                }
            }) {
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowUp,
                    contentDescription = "up"
                )
            }
            Text(
                text = "${scrollState.value}",
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            IconButton(onClick = {
                coroutineScope.launch {
                    scrollState.animateScrollBy(100f)
                }
            }) {
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowDown,
                    contentDescription = "down"
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun ScrollVerticalScrollSamplePreview() {
    ScrollVerticalScrollSample(remember { MutableStateFlow(true) })
}


@Composable
fun ScrollHorizontalScrollSample(allExpandFlow: Flow<Boolean>) {
    val desc = """
        horizontalScroll 修饰符提供一种最简单的横向滚动方法，可让用户在元素内容边界大于最大尺寸约束时滚动元素。您无需转换或偏移内容。
    """.trimIndent()
    val coroutineScope = rememberCoroutineScope()
    val scrollState = rememberScrollState()
    ExpandableItem3(
        title = "Scroll（horizontalScroll）",
        allExpandFlow,
        padding = 20.dp,
        desc = desc,
    ) {
        Text(text = "reverseScrolling=false")
        Text(
            text = text,
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.5f))
                .horizontalScroll(scrollState, reverseScrolling = false)
        )

        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "reverseScrolling=true")
        Text(
            text = text,
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.5f))
                .horizontalScroll(scrollState, reverseScrolling = true)
        )

        Spacer(modifier = Modifier.height(20.dp))
        Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
            IconButton(onClick = {
                coroutineScope.launch {
                    scrollState.animateScrollBy(-100f)
                }
            }) {
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowLeft,
                    contentDescription = "left"
                )
            }
            Text(
                text = "${scrollState.value}",
                modifier = Modifier
                    .width(30.dp)
                    .align(Alignment.CenterVertically),
                textAlign = TextAlign.Center
            )
            IconButton(onClick = {
                coroutineScope.launch {
                    scrollState.animateScrollBy(100f)
                }
            }) {
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowRight,
                    contentDescription = "right"
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun ScrollHorizontalScrollSamplePreview() {
    ScrollHorizontalScrollSample(remember { MutableStateFlow(true) })
}


@Composable
private fun ScrollScrollableSample(allExpandFlow: Flow<Boolean>) {
    val desc = """
        |       scrollable 可检测滚动手势，但不会偏移其内容。必须配合 ScrollableState 才能正常工作。
        |       构造 ScrollableState 时，您必须提供一个 consumeScrollDelta 函数，该函数将在每个滚动步骤调用（通过手势输入、流畅滚动或快速滑动），并且增量以像素为单位。
        |       该函数必须返回所消耗的滚动距离，以确保在存在具有 scrollable 修饰符的嵌套元素时，可以正确传播相应事件。
    """.trimMargin()
    var xDeltaTotal by remember { mutableStateOf(0f) }
    var yDeltaTotal by remember { mutableStateOf(0f) }
    ExpandableItem3(
        title = "Scroll（scrollable）",
        allExpandFlow,
        padding = 20.dp,
        desc = desc,
    ) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Column(modifier = Modifier.weight(1f)) {
                BoxWithConstraints(
                    Modifier
                        .size(150.dp, 50.dp)
                        .scrollable(
                            orientation = Orientation.Horizontal,
                            // Scrollable state: describes how to consume
                            // scrolling delta and update offset
                            state = rememberScrollableState { delta ->
                                xDeltaTotal += delta
                                delta
                            }
                        )
                        .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.5f))
                        .clipToBounds(),
                ) {
                    val maxWidthInt = with(LocalDensity.current) { maxWidth.toPx() }.toInt()
                    val maxHeightInt = with(LocalDensity.current) { maxHeight.toPx() }.toInt()
                    val xOffset by remember {
                        derivedStateOf {
                            xDeltaTotal
                                .toInt()
                                .coerceIn(0, maxWidthInt - maxHeightInt)
                        }
                    }
                    Row(
                        modifier = Modifier
                            .offset(x = with(LocalDensity.current) { xOffset.toDp() }, y = 0.dp)
                            .size(maxHeight)
                            .background(MaterialTheme.colorScheme.primary),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(
                            imageVector = Icons.Filled.KeyboardArrowLeft,
                            contentDescription = "left",
                            modifier = Modifier.size(20.dp),
                            tint = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f)
                        )
                        Icon(
                            imageVector = Icons.Filled.KeyboardArrowRight,
                            contentDescription = "right",
                            modifier = Modifier.size(20.dp),
                            tint = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f)
                        )
                    }
                }
                Text("offset: $xDeltaTotal")
            }

            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                BoxWithConstraints(
                    Modifier
                        .size(50.dp, 150.dp)
                        .scrollable(
                            orientation = Orientation.Vertical,
                            // Scrollable state: describes how to consume
                            // scrolling delta and update offset
                            state = rememberScrollableState { delta ->
                                yDeltaTotal += delta
                                delta
                            }
                        )
                        .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.5f))
                        .clipToBounds(),
                ) {
                    val maxWidthInt = with(LocalDensity.current) { maxWidth.toPx() }.toInt()
                    val maxHeightInt = with(LocalDensity.current) { maxHeight.toPx() }.toInt()
                    val yOffset by remember {
                        derivedStateOf {
                            yDeltaTotal
                                .toInt()
                                .coerceIn(0, maxHeightInt - maxWidthInt)
                        }
                    }
                    Column(
                        modifier = Modifier
                            .offset(x = 0.dp, y = with(LocalDensity.current) { yOffset.toDp() })
                            .size(maxWidth)
                            .background(MaterialTheme.colorScheme.primary),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Icon(
                            imageVector = Icons.Filled.KeyboardArrowUp,
                            contentDescription = "up",
                            modifier = Modifier.size(20.dp),
                            tint = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f)
                        )
                        Icon(
                            imageVector = Icons.Filled.KeyboardArrowDown,
                            contentDescription = "down",
                            modifier = Modifier.size(20.dp),
                            tint = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f)
                        )
                    }
                }
                Text("offset: $yDeltaTotal")
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun ScrollScrollableSamplePreview() {
    ScrollScrollableSample(remember { MutableStateFlow(true) })
}


@Composable
private fun ScrollNestedScrollAutoSample(allExpandFlow: Flow<Boolean>) {
    val desc = """
        |       Compose 支持嵌套滚动，可让多个组件对一个滚动手势做出回应。部分 Compose 组件和修饰符原生支持自动嵌套滚动，包括：verticalScroll、horizontalScroll、scrollable、Lazy API 和 TextField，下面仅演示 verticalScroll
    """.trimMargin()
    ExpandableItem3(
        title = "NestedScroll（Auto）",
        allExpandFlow,
        padding = 20.dp,
        desc = desc,
    ) {
        Column(
            modifier = Modifier
                .height(300.dp)
                .width(200.dp)
                .background(MaterialTheme.colorScheme.primary.copy(0.5f))
                .verticalScroll(rememberScrollState())
                .padding(32.dp)
        ) {
            repeat(3) {
                if (it > 0) {
                    Spacer(modifier = Modifier.height(16.dp))
                }
                Box(
                    modifier = Modifier
                        .height(160.dp)
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.primary)
                        .verticalScroll(rememberScrollState())
                ) {
                    Text(
                        "1\n\n2\n\n3\n\n4\n\n5\n\n6\n\n7\n\n8",
                        color = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun ScrollNestedScrollAutoSamplePreview() {
    ScrollNestedScrollAutoSample(remember { MutableStateFlow(true) })
}


@Composable
private fun ScrollNestedScrollParentNestedScrollConnectionSample(allExpandFlow: Flow<Boolean>) {
    val desc = """
        通过 Modifier.nestedScroll() 函数可以实现自定义嵌套滚动，它有两个参数
            NestedScrollConnection：当前组件作为父组件时通过 NestedScrollConnection 接收子组件传递的滚动事件并优先消费事件
            NestedScrollDispatcher：当前组件作为子组件时通过 NestedScrollDispatcher 将滚动事件传递给父组件，让其先消费，然后自己再消费剩余的事件
        
        本实例演示仅作为父组件时，通过 NestedScrollConnection 优先消费事件来隐藏或显示 title bar
    """.trimIndent()
    val topAppBaeHeightSize = 64.dp
    val topAppBaeHeightSizePx = with(LocalDensity.current) { topAppBaeHeightSize.toPx() }
    var topAppBarOffsetY by remember { mutableStateOf(0f) }
    ExpandableItem3(
        title = "NestedScroll（Parent: NestedScrollConnection）",
        allExpandFlow,
        padding = 20.dp,
        desc = desc,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .clipToBounds()
                .nestedScroll(remember {
                    object : NestedScrollConnection {
                        override fun onPreScroll(
                            available: Offset,
                            source: NestedScrollSource
                        ): Offset {
                            val delta = available.y
//                            val oldTopAppBarOffsetY = topAppBarOffsetY
                            val newTopAppBarOffsetY =
                                (topAppBarOffsetY + delta).coerceIn(-topAppBaeHeightSizePx, 0f)
                            topAppBarOffsetY = newTopAppBarOffsetY
//                            return Offset(x = 0f, y = newTopAppBarOffsetY - oldTopAppBarOffsetY)
                            return Offset.Zero
                        }
                    }
                })
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(top = topAppBaeHeightSize),
                content = {
                    items(20) { index ->
                        Text(
                            text = "Item ${index + 1}",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(20.dp)
                        )
                    }
                })

            Row(
                modifier = Modifier
                    .offset { IntOffset(x = 0, y = topAppBarOffsetY.roundToInt()) }
                    .fillMaxWidth()
                    .height(topAppBaeHeightSize)
                    .background(MaterialTheme.colorScheme.primary),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowLeft,
                    contentDescription = "back",
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier
                        .size(topAppBaeHeightSize)
                        .padding(14.dp),
                )
                Text(
                    text = "Title", modifier = Modifier.weight(1f),
                    color = MaterialTheme.colorScheme.onPrimary,
                )
                Icon(
                    imageVector = Icons.Filled.MoreVert,
                    contentDescription = "more",
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier
                        .size(topAppBaeHeightSize)
                        .padding(14.dp),
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun ScrollNestedScrollParentNestedScrollConnectionSamplePreview() {
    ScrollNestedScrollParentNestedScrollConnectionSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun ScrollNestedScrollChildNestedScrollDispatcherDispatcherSample(allExpandFlow: Flow<Boolean>) {
    val desc = """
        通过 Modifier.nestedScroll() 函数可以实现自定义嵌套滚动，它有两个参数
            NestedScrollConnection：当前组件作为父组件时通过 NestedScrollConnection 接收子组件传递的滚动事件并优先消费事件
            NestedScrollDispatcher：当前组件作为子组件时通过 NestedScrollDispatcher 将滚动事件传递给父组件，让其先消费，然后自己再消费剩余的事件
        
        本实例演示仅作为子组件时，通过 NestedScrollDispatcher 将滚动事件传递给父组件，让其先消费，然后自己再消费剩余的事件。
        
        大色块可以接收触摸事件并左右移动，但必须要先将小色快移动到边缘位置大色块自身才会开始移动
    """.trimIndent()
    val bottomBlockSize = 120.dp
    val topBlockSize = 60.dp
    val bottomBlockSizePx = with(LocalDensity.current) { bottomBlockSize.toPx() }
    val topBlockSizePx = with(LocalDensity.current) { topBlockSize.toPx() }
    var bottomBlockOffsetX by remember { mutableStateOf(0f) }
    var topBlockOffsetX by remember { mutableStateOf(0f) }
    var boxWidthPx = 0f
    val nestedScrollDispatcher = remember { NestedScrollDispatcher() }
    ExpandableItem3(
        title = "NestedScroll（Child: NestedScrollDispatcher）",
        allExpandFlow,
        padding = 20.dp,
        desc = desc,
    ) {
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxWidth()
                .height(bottomBlockSize)
                .border(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.5f))
                .clipToBounds()
                .nestedScroll(remember {
                    object : NestedScrollConnection {
                        override fun onPreScroll(
                            available: Offset,
                            source: NestedScrollSource
                        ): Offset {
                            val delta = available.x
                            val oldTopBlockOffsetX = topBlockOffsetX
                            val newTopBlockOffsetX = (topBlockOffsetX + delta)
                                .coerceIn(
                                    -(boxWidthPx - topBlockSizePx) / 2,
                                    (boxWidthPx - topBlockSizePx) / 2
                                )
                            topBlockOffsetX = newTopBlockOffsetX
                            return Offset(x = newTopBlockOffsetX - oldTopBlockOffsetX, y = 0f)
                        }
                    }
                })
        ) {
            boxWidthPx = with(LocalDensity.current) { maxWidth.toPx() }
            Box(
                modifier = Modifier
                    .offset { IntOffset(x = bottomBlockOffsetX.roundToInt(), y = 0) }
                    .align(Alignment.Center)
                    .size(bottomBlockSize)
                    .background(MaterialTheme.colorScheme.tertiary.copy(alpha = 0.8f))
                    .nestedScroll(rememberNestedScrollInteropConnection(), nestedScrollDispatcher)
                    .draggable(
                        orientation = Orientation.Horizontal,
                        state = rememberDraggableState { delta ->
                            // 先让父组件消费
                            val parentsConsumed = nestedScrollDispatcher.dispatchPreScroll(
                                available = Offset(x = delta, y = 0f),
                                source = NestedScrollSource.Drag
                            )

                            // 自己再消费父组件剩下的
                            val adjustedAvailableX = delta - parentsConsumed.x
                            val oldBottomBlockOffsetX = bottomBlockOffsetX
                            val newBottomBlockOffsetX = (bottomBlockOffsetX + adjustedAvailableX)
                                .coerceIn(
                                    -(boxWidthPx - bottomBlockSizePx) / 2,
                                    (boxWidthPx - bottomBlockSizePx) / 2
                                )
                            bottomBlockOffsetX = newBottomBlockOffsetX

                            // 再让父组件消费自己剩下的
                            val weConsumed = newBottomBlockOffsetX - oldBottomBlockOffsetX
                            val totalConsumed = Offset(x = weConsumed, y = 0f) + parentsConsumed
                            val left = adjustedAvailableX - weConsumed
                            nestedScrollDispatcher.dispatchPostScroll(
                                consumed = totalConsumed,
                                available = Offset(x = left, y = 0f),
                                source = NestedScrollSource.Drag
                            )
                        }
                    )
            ) {
                Row(modifier = Modifier.align(Alignment.Center)) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowLeft,
                        contentDescription = "left",
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowRight,
                        contentDescription = "right",
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
            Box(
                modifier = Modifier
                    .offset { IntOffset(x = topBlockOffsetX.roundToInt(), y = 0) }
                    .align(Alignment.Center)
                    .size(topBlockSize)
                    .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.8f))
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun ScrollNestedScrollChildNestedScrollDispatcherDispatcherSamplePreview() {
    ScrollNestedScrollChildNestedScrollDispatcherDispatcherSample(remember { MutableStateFlow(true) })
}