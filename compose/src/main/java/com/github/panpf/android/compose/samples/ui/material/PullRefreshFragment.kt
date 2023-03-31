package com.github.panpf.android.compose.samples.ui.material

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
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
import com.github.panpf.android.compose.samples.ui.base.MaterialComposeAppBarFragment
import com.github.panpf.android.compose.samples.ui.base.list.HorizontalAppendStateUI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Date

class PullRefreshFragment : MaterialComposeAppBarFragment() {

    override fun getTitle(): String {
        return "PullRefresh - Material"
    }

    @Composable
    override fun DrawContent() {
        PullRefreshSample()
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun PullRefreshSample() {
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
    val pullRefreshState = rememberPullRefreshState(
        refreshing = lazyPagingItems.loadState.refresh is LoadState.Loading,
        onRefresh = {
            lazyPagingItems.refresh()
        })
    Box(
        modifier = Modifier
            .fillMaxSize()
            .pullRefresh(pullRefreshState)
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
                    PullRefreshItem(lazyPagingItems[index] ?: "")
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
        PullRefreshIndicator(
            refreshing = lazyPagingItems.loadState.refresh is LoadState.Loading,
            state = pullRefreshState,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun PullRefreshSamplePreview() {
    PullRefreshSample()
}


@Composable
private fun PullRefreshItem(item: String) {
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
private fun PullRefreshPreview() {
    PullRefreshItem("15. 18:23:45")
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