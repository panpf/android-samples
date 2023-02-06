package com.github.panpf.android.compose.samples.ui.gestures

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem3
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class DragFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "Drag"
    }

    @Composable
    override fun DrawContent() {
        ExpandableLayout { allExpandFlow ->
            DragDraggableSample(allExpandFlow)
            DragDetectDragGesturesSample(allExpandFlow)
        }
    }
}


@Composable
private fun DragDraggableSample(allExpandFlow: Flow<Boolean>) {
    val desc = """
        Modifier.draggable() 修饰符可以检测拖动手势。
        draggable 仅报告拖动距离（以像素为单位），您需要保存状态并通过 offset 修饰符来移动元素。
        draggable 只能检测横向或竖向拖动，不能同时检测两个方向。
    """.trimIndent()
    val group1 =
        listOf(false to remember { mutableStateOf(0f) }, true to remember { mutableStateOf(0f) })
    val group2 =
        listOf(false to remember { mutableStateOf(0f) }, true to remember { mutableStateOf(0f) })
    ExpandableItem3(
        title = "Drag（draggable）",
        allExpandFlow,
        padding = 20.dp,
        desc = desc,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(280.dp)
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                group1.forEachIndexed { index, pair ->
                    if (index > 0) {
                        Spacer(modifier = Modifier.height(20.dp))
                    }
                    val (move, offset) = pair
                    Text(text = if (move) "移动元素" else "不移动元素")
                    BoxWithConstraints(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.5f))
                    ) {
                        Box(
                            modifier = Modifier
                                .let {
                                    if (move) {
                                        it.offset { IntOffset(x = offset.value.toInt(), y = 0) }
                                    } else {
                                        it
                                    }
                                }
                                .size(maxHeight * 0.5f)
                                .align(Alignment.Center)
                                .draggable(
                                    orientation = Orientation.Horizontal,
                                    state = rememberDraggableState { delta ->
                                        offset.value += delta
                                    }
                                )
                                .background(MaterialTheme.colorScheme.primary),
                        ) {
                            Text(
                                modifier = Modifier.align(Alignment.Center),
                                text = "${offset.value.toInt()}",
                                color = MaterialTheme.colorScheme.onPrimary,
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.width(20.dp))
            Row(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
            ) {
                group2.forEachIndexed { index, pair ->
                    if (index > 0) {
                        Spacer(modifier = Modifier.width(20.dp))
                    }
                    val (move, offset) = pair
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                    ) {
                        Text(text = if (move) "移动元素" else "不移动元素")
                        BoxWithConstraints(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.5f))
                        ) {
                            Box(
                                modifier = Modifier
                                    .let {
                                        if (move) {
                                            it.offset { IntOffset(x = 0, y = offset.value.toInt()) }
                                        } else {
                                            it
                                        }
                                    }
                                    .size(maxWidth * 0.7f)
                                    .align(Alignment.Center)
                                    .draggable(
                                        orientation = Orientation.Vertical,
                                        state = rememberDraggableState { delta ->
                                            offset.value += delta
                                        }
                                    )
                                    .background(MaterialTheme.colorScheme.primary),
                            ) {
                                Text(
                                    modifier = Modifier.align(Alignment.Center),
                                    text = "${offset.value.toInt()}",
                                    color = MaterialTheme.colorScheme.onPrimary,
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
private fun DragDraggableSamplePreview() {
    DragDraggableSample(remember { MutableStateFlow(true) })
}


@Composable
private fun DragDetectDragGesturesSample(allExpandFlow: Flow<Boolean>) {
    val desc = """
        detectDragGestures 可以同时检测两个方向的拖动手势，同样是仅报告拖动距离（以像素为单位），您需要保存状态并通过 offset 修饰符来移动元素。
    """.trimIndent()
    val group1 = listOf(
        false to remember { mutableStateOf(Offset(0f, 0f)) },
        true to remember { mutableStateOf(Offset(0f, 0f)) }
    )
    ExpandableItem3(
        title = "Drag（detectDragGestures）",
        allExpandFlow,
        padding = 20.dp,
        desc = desc,
    ) {
        Row(Modifier.fillMaxWidth()) {
            group1.forEachIndexed { index, pair ->
                if (index > 0) {
                    Spacer(modifier = Modifier.width(20.dp))
                }
                val (move, offset) = pair
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = if (move) "移动元素" else "不移动元素")
                    BoxWithConstraints(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1f)
                            .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.5f))
                    ) {
                        Box(
                            modifier = Modifier
                                .let {
                                    if (move) {
                                        it.offset {
                                            IntOffset(
                                                x = offset.value.x.toInt(),
                                                y = offset.value.y.toInt()
                                            )
                                        }
                                    } else {
                                        it
                                    }
                                }
                                .size(maxHeight * 0.5f)
                                .align(Alignment.Center)
                                .pointerInput(Unit) {
                                    detectDragGestures { change, dragAmount ->
                                        change.consume()
                                        offset.value += Offset(dragAmount.x, dragAmount.y)
                                    }
                                }
                                .background(MaterialTheme.colorScheme.primary),
                        ) {
                            Text(
                                modifier = Modifier.align(Alignment.Center),
                                text = "${offset.value.x.toInt()}, ${offset.value.y.toInt()}",
                                color = MaterialTheme.colorScheme.onPrimary,
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
private fun DragDetectDragGesturesSamplePreview() {
    DragDetectDragGesturesSample(remember { MutableStateFlow(true) })
}