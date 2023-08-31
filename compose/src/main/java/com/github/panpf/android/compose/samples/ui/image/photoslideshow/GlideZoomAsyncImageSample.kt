package com.github.panpf.android.compose.samples.ui.image.photoslideshow

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.github.panpf.android.compose.samples.R
import com.github.panpf.android.samples.utils.sketchUri2GlideModel
import com.github.panpf.sketch.fetch.newResourceUri
import com.github.panpf.zoomimage.GlideZoomAsyncImage
import com.github.panpf.zoomimage.compose.glide.internal.ExperimentalGlideComposeApi

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun GlideZoomAsyncImageSample(sketchImageUri: String) {
    val glideData =
        remember(key1 = sketchImageUri) { sketchUri2GlideModel(sketchImageUri) }
    GlideZoomAsyncImage(
        model = glideData,
        contentDescription = "view image",
        modifier = Modifier.fillMaxSize(),
    )
}

@Preview
@Composable
private fun CoilZoomAsyncImageSamplePreview() {
    CoilZoomAsyncImageSample(newResourceUri(R.drawable.im_placeholder))
}