package com.github.panpf.android.compose.samples.ui.gestures

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem3
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class ClickableFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "Clickable"
    }

    @Composable
    override fun DrawContent() {
        ExpandableLayout { allExpandFlow ->
            ClickableSample(allExpandFlow)
            CombinedClickableSample(allExpandFlow)
            DetectTapGesturesSample(allExpandFlow)
        }
    }
}


@Composable
private fun ClickableSample(allExpandFlow: Flow<Boolean>) {
    var clickCount by remember { mutableStateOf(0) }
    ExpandableItem3(
        title = "clickable",
        allExpandFlow,
        padding = 20.dp,
        desc = "Modifier.clickable() 用来接收单击事件，并为组件添加点击时的涟漪效果",
    ) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .background(MaterialTheme.colorScheme.primary)
                .clickable {
                    clickCount++
                }
        ) {
            Text(
                text = clickCount.toString(),
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "点击计数 +1", fontSize = 12.sp)
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun ClickableSamplePreview() {
    ClickableSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun CombinedClickableSample(allExpandFlow: Flow<Boolean>) {
    var clickCount by remember { mutableStateOf(0) }
    ExpandableItem3(
        title = "combinedClickable",
        allExpandFlow,
        padding = 20.dp,
        desc = "Modifier.combinedClickable 可一次性接收单击、双击、长按三种事件，并为组件添加点击时的涟漪效果",
    ) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .background(MaterialTheme.colorScheme.primary)
                .combinedClickable(
                    onClick = {
                        clickCount++
                    },
                    onDoubleClick = {
                        clickCount += 2
                    },
                    onLongClick = {
                        clickCount--
                    }
                )
        ) {
            Text(
                text = clickCount.toString(),
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "点击计数 +1", fontSize = 12.sp)
        Text(text = "双击计数 +2", fontSize = 12.sp)
        Text(text = "长按计数 -1", fontSize = 12.sp)
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun CombinedClickableSamplePreview() {
    CombinedClickableSample(remember { MutableStateFlow(true) })
}


@Composable
private fun DetectTapGesturesSample(allExpandFlow: Flow<Boolean>) {
    var clickCount by remember { mutableStateOf(0) }
    val colorScheme = MaterialTheme.colorScheme
    var background by remember { mutableStateOf(colorScheme.primary) }
    ExpandableItem3(
        title = "detectTapGestures",
        allExpandFlow,
        padding = 20.dp,
        desc = "detectTapGestures 可一次性接收单击、双击、长按、按下四种事件，但不会为组件添加点击时的涟漪效果",
    ) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .background(background)
                .pointerInput(Unit) {
                    // todo 检测到取消时恢复 background
                    detectTapGestures(
                        onPress = {
                            background = colorScheme.tertiary
                        },
                        onDoubleTap = {
                            background = colorScheme.primary
                            clickCount += 2
                        },
                        onLongPress = {
                            background = colorScheme.primary
                            clickCount--
                        },
                        onTap = {
                            background = colorScheme.primary
                            clickCount++
                        }
                    )
                }
        ) {
            Text(
                text = clickCount.toString(),
                color = colorScheme.onPrimary,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "点击计数 +1", fontSize = 12.sp)
        Text(text = "双击计数 +2", fontSize = 12.sp)
        Text(text = "长按计数 -1", fontSize = 12.sp)
        Text(text = "按下时改变背景", fontSize = 12.sp)
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun DetectTapGesturesSamplePreview() {
    DetectTapGesturesSample(remember { MutableStateFlow(true) })
}