package com.github.panpf.android.compose.samples.ui.image

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.github.panpf.android.compose.samples.R
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
import com.github.panpf.android.compose.samples.ui.base.pagerTabIndicatorOffset3
import com.github.panpf.sketch.zoom.compose.MyZoomImage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch
import me.rerere.zoomableimage.RerereZoomableImage
import moe.tlaster.zoomable.TlasterZoomable
import moe.tlaster.zoomable.rememberTlasterZoomableState
import nl.birdly.zoombox.birdlyZoomable

class ZoomImageFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "ZoomImage"
    }

    @OptIn(ExperimentalPagerApi::class)
    @Composable
    override fun DrawContent() {
        val pagerState = rememberPagerState()
        val coroutineScope = rememberCoroutineScope()
        val items = listOf("My", "Rerere", "Tlaster", "Birdly")
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
        ) {
            HorizontalPager(
                count = items.size,
                state = pagerState,
                userScrollEnabled = false,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) { index ->
                when (index) {
                    0 -> {
                        MyZoomImage(
                            painter = painterResource(id = R.drawable.dog_hor),
                            contentDescription = "",
                            modifier = Modifier.fillMaxSize(),
                        )
                    }

                    1 -> {
                        RerereZoomableImage(
                            painter = painterResource(id = R.drawable.dog_hor),
                            contentDescription = "",
                            modifier = Modifier.fillMaxSize(),
                        )
                    }

                    2 -> {
                        TlasterZoomable(
                            state = rememberTlasterZoomableState(),
                            modifier = Modifier.fillMaxSize(),
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.dog_hor),
                                contentDescription = "",
                                modifier = Modifier.fillMaxSize(),
                            )
                        }
                    }

                    3 -> {
                        Image(
                            painter = painterResource(id = R.drawable.dog_hor),
                            contentDescription = "",
                            modifier = Modifier
                                .fillMaxSize()
                                .birdlyZoomable(),
                        )
                    }
                }
            }
            TabRow(
                selectedTabIndex = pagerState.currentPage,
                indicator = { tabPositions ->
                    TabRowDefaults.Indicator(
                        modifier = Modifier.pagerTabIndicatorOffset3(pagerState, tabPositions),
                    )
                }
            ) {
                items.forEachIndexed { index, item ->
                    Tab(
                        selected = index == pagerState.currentPage,
                        onClick = {
                            coroutineScope.launch {
                                pagerState.scrollToPage(index)
                            }
                        }
                    ) {
                        Text(text = item, modifier = Modifier.padding(vertical = 10.dp))
                    }
                }
            }
        }
    }
}