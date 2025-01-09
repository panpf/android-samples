package com.github.panpf.android.compose.samples.ui.image.photoslideshow

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.github.panpf.android.compose.samples.R
import com.github.panpf.android.samples.utils.sketchUri2CoilModel
import com.github.panpf.sketch.fetch.newResourceUri
import com.github.panpf.zoomimage.CoilZoomAsyncImage
import com.github.panpf.zoomimage.rememberCoilZoomState
import com.github.panpf.zoomimage.util.Logger

@Composable
fun CoilZoomAsyncImageSample(sketchImageUri: String) {
    val context = LocalContext.current
    val coilData =
        remember(key1 = sketchImageUri) { sketchUri2CoilModel(context, sketchImageUri) }
    CoilZoomAsyncImage(
        model = ImageRequest.Builder(LocalContext.current).apply {
            data(coilData)
            crossfade(true)
        }.build(),
        contentDescription = "view image",
        modifier = Modifier.fillMaxSize(),
        zoomState = rememberCoilZoomState(logLevel = Logger.Level.Debug)
    )
}

@Preview
@Composable
private fun CoilZoomAsyncImageSamplePreview() {
    CoilZoomAsyncImageSample(newResourceUri(R.drawable.im_placeholder))
}