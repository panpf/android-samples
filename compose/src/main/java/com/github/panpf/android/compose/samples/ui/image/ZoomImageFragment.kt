package com.github.panpf.android.compose.samples.ui.image

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.fragment.navArgs
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
import com.github.panpf.android.samples.SampleImages

class ZoomImageFragment : Material3ComposeAppBarFragment() {

    private val args by navArgs<ZoomImageFragmentArgs>()
    private val zoomImageType by lazy { ZoomImageType.valueOf(args.zoomImageType) }

    override fun getTitle(): String {
        return zoomImageType.title
    }

    @Composable
    override fun DrawContent() {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
        ) {
            zoomImageType.drawContent(SampleImages.Asset.CHINA.uri)
        }
    }
}