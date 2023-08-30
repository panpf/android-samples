package com.github.panpf.android.compose.samples.ui.image

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells.Fixed
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.paging.LoadState.Loading
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.github.panpf.android.compose.samples.NavMainDirections
import com.github.panpf.android.compose.samples.R
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
import com.github.panpf.android.samples.photos.Photo
import com.github.panpf.android.samples.photos.PhotoAlbumViewModel

class PhotoAlbumComposeFragment : Material3ComposeAppBarFragment() {

    private val args by navArgs<PhotoAlbumComposeFragmentArgs>()
    private val photoAlbumViewModel by viewModels<PhotoAlbumViewModel>()
    private val zoomImageType by lazy { ZoomImageType.valueOf(args.zoomImageType) }

    override fun getTitle(): String {
        return "Photo Album"
    }

    override fun getSubtitle(): String? {
        return zoomImageType.subtitle
    }

    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    override fun DrawContent() {
        val pagingItems = photoAlbumViewModel.pagingFlow.collectAsLazyPagingItems()
        val pullRefreshState = rememberPullRefreshState(
            refreshing = pagingItems.loadState.refresh is Loading,
            onRefresh = { pagingItems.refresh() }
        )
        Box(modifier = Modifier.fillMaxSize()) {
            val divider = Arrangement.spacedBy(dimensionResource(id = R.dimen.grid_divider))
            LazyVerticalGrid(
                columns = Fixed(3),
                horizontalArrangement = divider,
                verticalArrangement = divider,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black)
                    .pullRefresh(pullRefreshState)
            ) {
                items(
                    count = pagingItems.itemCount,
                    key = { pagingItems.peek(it)?.uri ?: "" },
                ) { index ->
                    val photo = pagingItems.peek(index)
                    zoomImageType.drawListContent(
                        photo?.uri.orEmpty(),
                        Modifier
                            .fillMaxWidth()
                            .aspectRatio(1f)
                            .clickable {
                                startImageDetail(pagingItems, index)
                            }
                    )
                }
            }

            PullRefreshIndicator(
                refreshing = pagingItems.loadState.refresh is Loading,
                state = pullRefreshState,
                modifier = Modifier.align(Alignment.TopCenter)
            )
        }
    }

    private fun startImageDetail(pagingItems: LazyPagingItems<Photo>, position: Int) {
        val startPosition = (position - 50).coerceAtLeast(0)
        val totalCount = pagingItems.itemCount
        val endPosition = (position + 50).coerceAtMost(totalCount - 1)
        val imageList = (startPosition..endPosition).map {
            pagingItems.peek(it)?.uri
        }
        findNavController().navigate(
            NavMainDirections.actionGlobalPhotoSlideshowComposeFragment(
                zoomImageType = args.zoomImageType,
                imageUris = imageList.joinToString(separator = ","),
                position = position,
                startPosition = startPosition,
                totalCount = totalCount
            ),
        )
    }
}