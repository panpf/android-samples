package com.github.panpf.android.compose.samples.ui.gestures

import android.view.LayoutInflater
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.rememberNestedScrollInteropConnection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.github.panpf.android.compose.samples.R
import com.github.panpf.android.compose.samples.ui.base.ExpandableText
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment

class ScrollNestedScrollInteropWithComposeFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "Scroll - NestedScroll - Interop - Compose"
    }

    @Composable
    override fun DrawContent() {
        ScrollNestedScrollInteropWithComposeSample()
    }
}

@Composable
@OptIn(ExperimentalComposeUiApi::class)
private fun ScrollNestedScrollInteropWithComposeSample() {
    val desc = """
        在 View 系统中嵌套 ComposeView 时，通过在 Compose 组件的 nestedScroll 修饰符上使用 rememberNestedScrollInteropConnection() 就可以实现事件在 compose 和 View 系统的互操作。
        
        以下示例是使用 CoordinatorLayout、CollapsingToolbarLayout 和子级可组合项展示滑动列表时折叠顶部的图片：
    """.trimIndent()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        ExpandableText(text = desc)
        Spacer(modifier = Modifier.height(20.dp))
        AndroidView(
            modifier = Modifier
                .fillMaxSize()
                .clipToBounds(),
            factory = { context ->
                LayoutInflater.from(context)
                    .inflate(R.layout.scroll_nestedscroll_interop_compose, null, false).apply {
                        findViewById<ComposeView>(R.id.compose_view).apply {
                            setContent {
                                val nestedScrollInterop = rememberNestedScrollInteropConnection()
                                // Add the nested scroll connection to your top level @Composable element
                                // using the nestedScroll modifier.
                                LazyColumn(modifier = Modifier.nestedScroll(nestedScrollInterop)) {
                                    items(20) { item ->
                                        Box(
                                            modifier = Modifier
                                                .padding(16.dp)
                                                .height(56.dp)
                                                .fillMaxWidth()
                                                .background(Color.Gray),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Text(item.toString())
                                        }
                                    }
                                }
                            }
                        }
                    }
            }
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun ScrollNestedScrollInteropWithComposeSamplePreview() {
    ScrollNestedScrollInteropWithComposeSample()
}
