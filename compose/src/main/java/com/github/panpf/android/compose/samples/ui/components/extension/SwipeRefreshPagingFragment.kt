package com.github.panpf.android.compose.samples.ui.components.extension

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.paging.compose.collectAsLazyPagingItems
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
import com.github.panpf.android.compose.samples.ui.base.list.HorizontalAppendStateUI
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Date

class SwipeRefreshPagingFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "SwipeRefreshPaging"
    }

    @Composable
    override fun DrawContent() {
        SwipeRefreshPagingSample()
    }
}

@Composable
private fun SwipeRefreshPagingSample() {
    val pagingFlow = remember {
        Pager(
            config = PagingConfig(
                pageSize = 40,
                initialLoadSize = 40,
                enablePlaceholders = false,
            ),
            initialKey = 0,
            pagingSourceFactory = {
                MyPagingSource()
            }
        ).flow
    }
    val lazyPagingItems = pagingFlow.collectAsLazyPagingItems()
    val swipeRefreshState =
        rememberSwipeRefreshState(lazyPagingItems.loadState.refresh is LoadState.Loading).apply {
            isRefreshing = lazyPagingItems.loadState.refresh is LoadState.Loading
        }
    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = { lazyPagingItems.refresh() },
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(
                count = lazyPagingItems.itemCount,
                key = { lazyPagingItems.peek(it) ?: "" },
                contentType = { 0 },
            ) { index ->
                Box(modifier = Modifier.fillMaxWidth()) {
                    if (index > 0) {
                        Divider()
                    }
                    SwipeRefreshPagingItem(lazyPagingItems[index] ?: "")
                }
            }

            if (lazyPagingItems.itemCount > 0) {
                item(
                    key = "AppendState",
                    contentType = 1
                ) {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        Divider()
                        HorizontalAppendStateUI(lazyPagingItems.loadState.append) {
                            lazyPagingItems.retry()
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun SwipeRefreshPagingSamplePreview() {
    SwipeRefreshPagingSample()
}


@Composable
private fun SwipeRefreshPagingItem(item: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
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
private fun SwipeRefreshPagingItemPreview() {
    SwipeRefreshPagingItem("15. 18:23:45")
}

private class MyPagingSource : PagingSource<Int, String>() {

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