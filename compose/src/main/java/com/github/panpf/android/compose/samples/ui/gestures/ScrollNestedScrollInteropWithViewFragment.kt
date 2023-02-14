package com.github.panpf.android.compose.samples.ui.gestures

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.view.ViewCompat
import androidx.core.view.updatePadding
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.panpf.android.compose.samples.ui.base.ExpandableText
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
import kotlin.math.roundToInt

class ScrollNestedScrollInteropWithViewFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "Scroll - NestedScroll - Interop - View"
    }

    @Composable
    override fun DrawContent() {
        ScrollNestedScrollInteropWithViewSample()
    }
}


@Composable
private fun ScrollNestedScrollInteropWithViewSample() {
    val desc = """
        Compose 的嵌套滚动支持与 View 系统的嵌套滚动互操作。AndroidView 会实现 NestedScrollDispatcher，因为它充当 Compose 滚动父项的子项；还会实现 NestedScrollingParent3，因为它充当 View 滚动子项的父项。然后，Compose 父项将能够从嵌套的可滚动子级 View 接收嵌套滚动增量。
        
        以下示例展示了在 AndroidView 中嵌套一个 RecyclerView 依然能够和 Compose 中的组件联动滚动达到收起工具栏的效果：
    """.trimIndent()
    val topAppBaeHeightSize = 64.dp
    val topAppBaeHeightSizePx = with(LocalDensity.current) { topAppBaeHeightSize.toPx() }
    var topAppBarOffsetY by remember { mutableStateOf(0f) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        ExpandableText(text = desc)
        Spacer(modifier = Modifier.height(20.dp))
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clipToBounds()
                .nestedScroll(remember {
                    object : NestedScrollConnection {
                        override fun onPreScroll(
                            available: Offset,
                            source: NestedScrollSource
                        ): Offset {
                            val delta = available.y
                            val newTopAppBarOffsetY =
                                (topAppBarOffsetY + delta).coerceIn(-topAppBaeHeightSizePx, 0f)
                            topAppBarOffsetY = newTopAppBarOffsetY
                            return Offset.Zero
                        }
                    }
                })
        ) {
            AndroidView(
                modifier = Modifier
                    .fillMaxSize(),
                factory = { context ->
                    RecyclerView(context).apply {
                        updatePadding(top = topAppBaeHeightSizePx.roundToInt())
                        clipToPadding = false
                        layoutParams = ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
                        )
                        layoutManager = LinearLayoutManager(context)
                        adapter = NestedScrollInteropAdapter()
                        ViewCompat.setNestedScrollingEnabled(this, true)
                    }
                }
            )

            Row(
                modifier = Modifier
                    .offset { IntOffset(x = 0, y = topAppBarOffsetY.roundToInt()) }
                    .fillMaxWidth()
                    .height(topAppBaeHeightSize)
                    .background(color = MaterialTheme.colorScheme.tertiary),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier
                        .size(topAppBaeHeightSize)
                        .padding(18.dp),
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "back",
                    tint = MaterialTheme.colorScheme.onTertiary
                )
                Text(text = "Title", color = MaterialTheme.colorScheme.onTertiary, fontSize = 20.sp)
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun ScrollNestedScrollInteropWithViewSamplePreview() {
    ScrollNestedScrollInteropWithViewSample()
}

private class NestedScrollInteropAdapter :
    RecyclerView.Adapter<NestedScrollInteropAdapter.NestedScrollInteropViewHolder>() {

    private val items = (1..50).map { it.toString() }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NestedScrollInteropViewHolder {
        return NestedScrollInteropViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(android.R.layout.simple_list_item_1, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: NestedScrollInteropViewHolder, position: Int) {
        holder.bind(items[position])
    }

    class NestedScrollInteropViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(item: String) {
            (itemView as TextView).text = item
        }
    }
}