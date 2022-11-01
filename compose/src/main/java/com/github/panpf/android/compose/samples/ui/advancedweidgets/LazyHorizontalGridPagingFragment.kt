package com.github.panpf.android.compose.samples.ui.advancedweidgets

import android.annotation.SuppressLint
import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.viewModels
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.PagingSource.LoadResult.Page
import androidx.paging.PagingState
import androidx.paging.cachedIn
import androidx.paging.compose.collectAsLazyPagingItems
import com.github.panpf.android.compose.samples.ui.base.ToolbarFragment
import com.github.panpf.android.compose.samples.ui.base.list.VerticalAppendStateUI
import com.github.panpf.android.compose.samples.ui.base.theme.MyTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Date

class LazyHorizontalGridPagingFragment : ToolbarFragment() {

    private val viewModel by viewModels<LazyHorizontalGridPagingViewModel>()

    override fun createView(
        toolbar: Toolbar,
        inflater: LayoutInflater,
        parent: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        toolbar.title = "LazyHorizontalGrid - Paging"
        return ComposeView(inflater.context).apply {
            setContent {
                MyTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        LazyHorizontalGridPagingSample(viewModel.pagingFlow)
                    }
                }
            }
        }
    }
}


@Composable
fun LazyHorizontalGridPagingSample(pagingFlow: Flow<PagingData<String>>) {
    val colors = remember {
        listOf(
            Color.Red, Color.Black, Color.Magenta, Color.Cyan,
            Color.Yellow, Color.Blue, Color.Green, Color.Gray
        ).map { it.copy(alpha = 0.5f) }
    }
    val lazyPagingItems = pagingFlow.collectAsLazyPagingItems()
//    val swipeRefreshState =
//        rememberSwipeRefreshState(lazyPagingItems.loadState.refresh is LoadState.Loading).apply {
//            isRefreshing = lazyPagingItems.loadState.refresh is LoadState.Loading
//        }
//    SwipeRefresh(state = swipeRefreshState, onRefresh = { lazyPagingItems.refresh() }) {
    LazyHorizontalGrid(rows = GridCells.Adaptive(128.dp), modifier = Modifier.fillMaxSize()) {
        items(
            count = lazyPagingItems.itemCount,
            key = { lazyPagingItems.peek(it) ?: "" },
            contentType = { 0 },
        ) { index ->
            LazyHorizontalGridPagingItem(lazyPagingItems[index] ?: "", colors[index % colors.size])
        }

        if (lazyPagingItems.itemCount > 0) {
            item(
                key = "AppendState",
                contentType = 1,
                span = { GridItemSpan(maxLineSpan) }
            ) {
                VerticalAppendStateUI(lazyPagingItems.loadState.append) {
                    lazyPagingItems.retry()
                }
            }
        }
    }
//    }
}

@Composable
fun LazyHorizontalGridPagingItem(item: String, bgColor: Color) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .background(bgColor)
    ) {
        Text(
            text = item,
            modifier = Modifier.align(Alignment.Center),
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun LazyHorizontalGridPagingItemPreview() {
    LazyHorizontalGridPagingItem("15. 18:23:45", Color.Red.copy(alpha = 0.5f))
}

class LazyHorizontalGridPagingViewModel(application: Application) :
    AndroidViewModel(application) {
    val pagingFlow = Pager(
        config = PagingConfig(
            pageSize = 40,
            initialLoadSize = 80,
            enablePlaceholders = false,
        ),
        initialKey = 0,
        pagingSourceFactory = {
            LazyHorizontalGridPagingPagingSource()
        }
    ).flow.cachedIn(viewModelScope)

    private class LazyHorizontalGridPagingPagingSource : PagingSource<Int, String>() {
        override fun getRefreshKey(state: PagingState<Int, String>): Int {
            return 0
        }

        @SuppressLint("SimpleDateFormat")
        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, String> {
            return withContext(Dispatchers.IO) {
                delay(2000)
                val start = params.key ?: 0
                val loadSize = params.loadSize
                val time = SimpleDateFormat("HH:mm:ss").format(Date())
                val list = buildList {
                    repeat(loadSize) {
                        val index = start + it
                        if (index < 199) {
                            add("${(index + 1)}. $time")
                        }
                    }
                }
                val nextKey = (start + loadSize).let {
                    if (it < 199) it else null
                }
                Page(data = list, prevKey = null, nextKey = nextKey)
            }
        }
    }
}