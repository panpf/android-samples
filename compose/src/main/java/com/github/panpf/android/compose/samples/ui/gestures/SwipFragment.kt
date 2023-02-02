package com.github.panpf.android.compose.samples.ui.gestures

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem3
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class SwipFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "Swip"
    }

    @Composable
    override fun DrawContent() {
        ExpandableLayout { allExpandFlow ->
            SwipSwipeableSample(allExpandFlow)
        }
    }
}


@Composable
private fun SwipSwipeableSample(allExpandFlow: Flow<Boolean>) {
    var clickCount by remember { mutableStateOf(0) }
    ExpandableItem3(
        title = "Swip（swipeable）",
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
private fun SwipSwipeableSamplePreview() {
    SwipSwipeableSample(remember { MutableStateFlow(true) })
}