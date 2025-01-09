package com.github.panpf.android.compose.samples.ui.image.photoslideshow

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.github.panpf.android.compose.samples.R
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
import com.github.panpf.android.compose.samples.ui.image.ZoomImageType

class PhotoSlideshowComposeFragment : Material3ComposeAppBarFragment() {

    private val args by navArgs<PhotoSlideshowComposeFragmentArgs>()
    private val zoomImageType by lazy { ZoomImageType.valueOf(args.zoomImageType) }
    private val viewModel by viewModels<PhotoSlideshowComposeViewMode>()

    override fun getTitle(): String {
        return "Photo Slideshow"
    }

    override fun getSubtitle(): String? {
        return zoomImageType.subtitle
    }

    @Composable
    override fun RowScope.DrawActions() {
        val horizontalLayout by viewModel.horizontalLayout.collectAsState(initial = true)
        IconButton(onClick = {
            viewModel.horizontalLayout.value = !horizontalLayout
        }) {
            val meuIcon =
                if (horizontalLayout) R.drawable.ic_swap_vert else R.drawable.ic_swap_horiz
            Icon(painter = painterResource(id = meuIcon), contentDescription = "Icon")
         }
    }

    @Composable
    override fun DrawContent() {
        val horizontalLayout by viewModel.horizontalLayout.collectAsState(initial = true)
        val imageUrlList = remember { args.imageUris.split(",") }
        val pagerState = rememberPagerState(initialPage = args.position - args.startPosition) {
            imageUrlList.size
        }
        Box(modifier = Modifier.fillMaxSize().background(Color.Black)) {
            if (horizontalLayout) {
                HorizontalPager(
                    state = pagerState,
                    beyondViewportPageCount = 0,
                    modifier = Modifier.fillMaxSize()
                ) { index ->
                    zoomImageType.drawContent(imageUrlList[index])
                }
            } else {
                VerticalPager(
                    state = pagerState,
                    beyondViewportPageCount = 0,
                    modifier = Modifier.fillMaxSize()
                ) { index ->
                    zoomImageType.drawContent(imageUrlList[index])
                }
            }
            PageNumber(
                number = pagerState.currentPage + 1,
                total = args.totalCount,
                modifier = Modifier.align(Alignment.TopEnd)
            )
        }
    }
}

@Composable
private fun PageNumber(modifier: Modifier = Modifier, number: Int, total: Int) {
    val colors = MaterialTheme.colorScheme
    Text(
        text = "${number}\n·\n${total}",
        textAlign = TextAlign.Center,
        color = colors.onTertiary,
        style = TextStyle(lineHeight = 12.sp),
        modifier = Modifier
            .padding(20.dp) // margin
            .background(
                color = colors.tertiary.copy(alpha = 0.7f),
                shape = RoundedCornerShape(50)
            )
            .padding(horizontal = 8.dp, vertical = 12.dp)
            .then(modifier)
    )
}

@Preview
@Composable
private fun PageNumberPreview() {
    PageNumber(number = 9, total = 22)
}