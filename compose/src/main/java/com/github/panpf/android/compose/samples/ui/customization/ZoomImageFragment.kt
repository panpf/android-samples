package com.github.panpf.android.compose.samples.ui.customization

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.github.panpf.android.compose.samples.BuildConfig
import com.github.panpf.android.compose.samples.R
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
import com.github.panpf.android.compose.samples.ui.base.pagerTabIndicatorOffset3
import com.github.panpf.android.compose.samples.ui.base.theme.MyThemeColors3
import com.github.panpf.android.compose.samples.ui.customization.zoomimage.birdly.birdlyZoomable
import com.github.panpf.android.compose.samples.ui.customization.zoomimage.my.MyZoomImage
import com.github.panpf.android.compose.samples.ui.customization.zoomimage.my.MyZoomVisibleRectImage
import com.github.panpf.android.compose.samples.ui.customization.zoomimage.my.ScaleAnimationConfig
import com.github.panpf.android.compose.samples.ui.customization.zoomimage.my.rememberMyZoomState
import com.github.panpf.android.compose.samples.ui.customization.zoomimage.my.toShortString
import com.github.panpf.android.compose.samples.ui.customization.zoomimage.photo.ExperimentalPhotoApi
import com.github.panpf.android.compose.samples.ui.customization.zoomimage.photo.PhotoBox
import com.github.panpf.android.compose.samples.ui.customization.zoomimage.tlaster.TlasterZoomable
import com.github.panpf.android.compose.samples.ui.customization.zoomimage.tlaster.rememberTlasterZoomableState
import kotlinx.coroutines.launch

class ZoomImageFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "ZoomImage"
    }

    @OptIn(ExperimentalPhotoApi::class, ExperimentalFoundationApi::class)
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
                pageCount = items.size,
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
    val animateDoubleTapScaleState = remember { mutableStateOf(true) }
    val slowerScaleAnimationState = remember { mutableStateOf(false) }
    val settingsDialogState = remember { mutableStateOf(false) }
    val animationDurationMillisState = remember(slowerScaleAnimationState.value) {
        mutableStateOf(if (slowerScaleAnimationState.value) 3000 else ScaleAnimationConfig.DefaultDurationMillis)
    }
    Box(modifier = Modifier.fillMaxSize()) {
        val myZoomState = rememberMyZoomState(debugMode = BuildConfig.DEBUG)
        val zoomIn = remember {
            derivedStateOf {
                val nextScale = myZoomState.nextScale()
                nextScale > myZoomState.minScale
            }
        }
        MyZoomImage(
            painter = painterResource(id = R.drawable.dog_hor),
            contentDescription = "",
            modifier = Modifier.fillMaxSize(),
            state = myZoomState,
            scaleAnimationConfig = ScaleAnimationConfig(
                animateDoubleTapScale = animateDoubleTapScaleState.value,
                animationDurationMillis = animationDurationMillisState.value,
            ),
        )

        MyZoomVisibleRectImage(
            painter = painterResource(id = R.drawable.dog_hor),
            modifier = Modifier.align(Alignment.BottomStart),
            state = myZoomState,
            animateScaleState = animateDoubleTapScaleState,
            animationDurationMillisState = animationDurationMillisState,
        )

        Column {
            val expandedState = remember { mutableStateOf(false) }
            Text(
                text = """
                    scale: ${myZoomState.scale}, ${if (myZoomState.zooming) "zooming" else ""}
                    translation: ${myZoomState.translation.toShortString()}
                    translationBounds: ${myZoomState.translationBounds.toShortString()}
                    visibleRect: ${myZoomState.visibleRect.toShortString()}
                    contentVisibleRect: ${myZoomState.contentVisibleRect.toShortString()}
                    containerSize: ${myZoomState.containerSize}
                    contentSize: ${myZoomState.contentSize}
                    contentOfContainerRect: ${myZoomState.contentOfContainerRect.toShortString()}
                """.trimIndent(),
                color = Color.White,
                fontSize = 13.sp,
                lineHeight = 16.sp,
                style = LocalTextStyle.current.copy(
                    shadow = Shadow(offset = Offset(1f, 1f), blurRadius = 4f),
                ),
                maxLines = if (expandedState.value) Int.MAX_VALUE else 5,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .clickable { expandedState.value = !expandedState.value }
                    .padding(10.dp)
            )
        }

        Row(
            Modifier
                .align(Alignment.BottomEnd)
                .padding(20.dp)
                .background(colors.tertiary.copy(alpha = 0.8f), RoundedCornerShape(50)),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(
                onClick = {
                    coroutineScope.launch {
                        val newScale = myZoomState.nextScale()
                        if (animateDoubleTapScaleState.value) {
                            myZoomState.animateScaleTo(newScale = newScale)
                        } else {
                            myZoomState.snapScaleTo(newScale = newScale)
                        }
                    }
                }
            ) {
                val icon = if (zoomIn.value)
                    R.drawable.ic_zoom_in to "zoom in" else R.drawable.ic_zoom_out to "zoom out"
                Icon(
                    painter = painterResource(id = icon.first),
                    contentDescription = icon.second,
                    tint = colors.onTertiary
                )
            }
            IconButton(
                onClick = {
                    settingsDialogState.value = true
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_settings),
                    contentDescription = "Settings",
                    tint = colors.onTertiary
                )
            }
        }

        if (settingsDialogState.value) {
            SettingsDialog(animateDoubleTapScaleState, slowerScaleAnimationState) {
                settingsDialogState.value = false
            }
        }
    }
}

@Preview
@Composable
private fun MyZoomImageSamplePreview() {
    MyZoomImageSample()
}

@Composable
private fun SettingsDialog(
    animateDoubleTapScaleState: MutableState<Boolean>,
    slowerScaleAnimationState: MutableState<Boolean>,
    onDismissRequest: () -> Unit
) {
    Dialog(onDismissRequest) {
        Column(
            Modifier
                .fillMaxWidth()
                .background(Color.White, shape = RoundedCornerShape(20.dp))
                .padding(vertical = 20.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        animateDoubleTapScaleState.value = !animateDoubleTapScaleState.value
                    }
                    .padding(horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "开启缩放动画", modifier = Modifier.weight(1f))
                Checkbox(
                    checked = animateDoubleTapScaleState.value,
                    onCheckedChange = {
                        animateDoubleTapScaleState.value = !animateDoubleTapScaleState.value
                    }
                )
            }
            Spacer(modifier = Modifier.size(10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        slowerScaleAnimationState.value = !slowerScaleAnimationState.value
                    }
                    .padding(horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "更慢的缩放动画", modifier = Modifier.weight(1f))
                Checkbox(
                    checked = slowerScaleAnimationState.value,
                    onCheckedChange = {
                        slowerScaleAnimationState.value = !slowerScaleAnimationState.value
                    }
                )
            }
        }
    }
}