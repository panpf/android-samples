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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.viewModels
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LoadState
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.paging.cachedIn
import androidx.paging.compose.collectAsLazyPagingItems
import com.github.panpf.android.compose.samples.ui.base.ToolbarFragment
import com.github.panpf.android.compose.samples.ui.base.list.HorizontalAppendStateUI
import com.github.panpf.android.compose.samples.ui.base.theme.MyTheme
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Date

class LazyColumnPagingFragment : ToolbarFragment() {

    private val viewModel by viewModels<LazyColumnPagingViewModel>()

    override fun createView(
        toolbar: Toolbar,
        inflater: LayoutInflater,
        parent: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        toolbar.title = "LazyColumn - Paging"
        return ComposeView(inflater.context).apply {
            setContent {
                MyTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        LazyColumnPagingSample(viewModel.pagingFlow)
                    }
                }
            }
        }
    }
}

@Composable
fun LazyColumnPagingSample(pagingFlow: Flow<PagingData<String>>) {
    val colors = remember {
        listOf(
            Color.Red, Color.Black, Color.Magenta, Color.Cyan,
            Color.Yellow, Color.Blue, Color.Green, Color.Gray
        ).map { it.copy(alpha = 0.5f) }
    }
    val lazyPagingItems = pagingFlow.collectAsLazyPagingItems()
    val swipeRefreshState =
        rememberSwipeRefreshState(lazyPagingItems.loadState.refresh is LoadState.Loading).apply {
            isRefreshing = lazyPagingItems.loadState.refresh is LoadState.Loading
        }
    SwipeRefresh(state = swipeRefreshState, onRefresh = { lazyPagingItems.refresh() }) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(
                count = lazyPagingItems.itemCount,
                key = { lazyPagingItems.peek(it) ?: "" },
                contentType = { 0 },
            ) { index ->
                LazyColumnPagingItem(lazyPagingItems[index] ?: "", colors[index % colors.size])
            }

            if (lazyPagingItems.itemCount > 0) {
                item(
                    key = "AppendState",
                    contentType = 1
                ) {
                    HorizontalAppendStateUI(lazyPagingItems.loadState.append) {
                        lazyPagingItems.retry()
                    }
                }
            }
        }
    }
}

@Composable
fun LazyColumnPagingItem(item: String, bgColor: Color) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(bgColor)
    ) {
        Text(
            text = item,
            modifier = Modifier
                .align(Alignment.Center)
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun LazyColumnPagingItemPreview() {
    LazyColumnPagingItem("15. 18:23:45", Color.Red.copy(alpha = 0.5f))
}

class LazyColumnPagingViewModel(application: Application) : AndroidViewModel(application) {
    val pagingFlow = Pager(
        config = PagingConfig(
            pageSize = 40,
            initialLoadSize = 80,
            enablePlaceholders = false,
        ),
        initialKey = 0,
        pagingSourceFactory = {
            LazyColumnPagingPagingSource()
        }
    ).flow.cachedIn(viewModelScope)

    private class LazyColumnPagingPagingSource : PagingSource<Int, String>() {
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
                LoadResult.Page(data = list, prevKey = null, nextKey = nextKey)
            }
        }
    }
}