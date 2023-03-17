package com.github.panpf.android.compose.samples.ui.image

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.panpf.android.compose.samples.R
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
import com.github.panpf.android.compose.samples.ui.base.pagerTabIndicatorOffset3
import com.github.panpf.android.compose.samples.ui.base.theme.MyThemeColors3
import com.github.panpf.android.compose.samples.ui.image.zoom.com.github.panpf.sketch.zoom.compose.MyZoomVisibleRectImage
import com.github.panpf.android.compose.samples.ui.image.zoom.com.github.panpf.sketch.zoom.compose.toShortString
import com.github.panpf.sketch.zoom.compose.MyZoomImage
import com.github.panpf.sketch.zoom.compose.rememberMyZoomState
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch
import moe.tlaster.zoomable.TlasterZoomable
import moe.tlaster.zoomable.rememberTlasterZoomableState
import nl.birdly.zoombox.birdlyZoomable
import soup.compose.photo.ExperimentalPhotoApi
import soup.compose.photo.PhotoBox

class ZoomImageFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "ZoomImage"
    }

    @OptIn(ExperimentalPagerApi::class, ExperimentalPhotoApi::class)
    @Composable
    override fun DrawContent() {
        val pagerState = rememberPagerState()
        val coroutineScope = rememberCoroutineScope()
        val items = listOf("My", "Tlaster", "Birdly", "Photo")
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
                        MyZoomImageSample()
                    }

                    1 -> {
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

                    2 -> {
                        Image(
                            painter = painterResource(id = R.drawable.dog_hor),
                            contentDescription = "",
                            modifier = Modifier
                                .fillMaxSize()
                                .birdlyZoomable(),
                        )
                    }

                    3 -> {
                        PhotoBox(
                            modifier = Modifier.fillMaxSize(),
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.dog_hor),
                                contentDescription = "",
                                modifier = Modifier
                                    .fillMaxSize()
                            )
                        }
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

@Composable
private fun MyZoomImageSample() {
    val coroutineScope = rememberCoroutineScope()
    val colors = MyThemeColors3.current
    Box(modifier = Modifier.fillMaxSize()) {
        val myZoomState = rememberMyZoomState()
        val zoomIn = remember {
            derivedStateOf {
                val nextScale = myZoomState.nextDoubleTapScale()
                nextScale > myZoomState.minScale
            }
        }
        MyZoomImage(
            painter = painterResource(id = R.drawable.dog_hor),
            contentDescription = "",
            modifier = Modifier.fillMaxSize(),
            state = myZoomState,
        )

        MyZoomVisibleRectImage(
            painter = painterResource(id = R.drawable.dog_hor),
            modifier = Modifier.align(Alignment.BottomStart),
            state = myZoomState,
        )

        Column(Modifier.padding(10.dp)) {
            Text(
                text = """
                    scale: ${myZoomState.scale}
                    translation: ${myZoomState.translation.toShortString()}
                    visibleRect: ${myZoomState.contentVisibleRect.toShortString()}
                    visibleCenter: ${myZoomState.contentVisibleCenter.toShortString()}
                    coreVisibleRect: ${myZoomState.coreVisibleRect.toShortString()}
                """.trimIndent(),
                color = Color.White,
                fontSize = 13.sp,
                lineHeight = 16.sp,
                style = LocalTextStyle.current.copy(
                    shadow = Shadow(offset = Offset(4f, 4f), blurRadius = 2f),
                )
            )
        }

        Row(
            Modifier
                .align(Alignment.BottomEnd)
                .padding(20.dp), horizontalArrangement = Arrangement.End
        ) {
            IconButton(
                modifier = Modifier
                    .background(colors.tertiary, CircleShape),
                onClick = {
                    coroutineScope.launch {
                        myZoomState.snapDoubleTapScale()
                    }
                }
            ) {
                val icon =
                    if (zoomIn.value) R.drawable.ic_add to "snap zoom in" else R.drawable.ic_subtract to "snap zoom out"
                Icon(
                    painter = painterResource(id = icon.first),
                    contentDescription = icon.second,
                    tint = colors.onTertiary
                )
            }
            Spacer(modifier = Modifier.size(10.dp))
            IconButton(
                modifier = Modifier
                    .background(colors.tertiary, CircleShape),
                onClick = {
                    coroutineScope.launch {
                        myZoomState.animateDoubleTapScale()
                    }
                }
            ) {
                val icon =
                    if (zoomIn.value) R.drawable.ic_zoom_in to "zoom in" else R.drawable.ic_zoom_out to "zoom out"
                Icon(
                    painter = painterResource(id = icon.first),
                    contentDescription = icon.second,
                    tint = colors.onTertiary
                )
            }
        }
    }
}

@Preview
@Composable
private fun MyZoomImageSamplePreview() {
    MyZoomImageSample()
}