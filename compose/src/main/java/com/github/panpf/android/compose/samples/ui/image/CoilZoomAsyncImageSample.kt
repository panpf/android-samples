package com.github.panpf.android.compose.samples.ui.image

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import coil.request.ImageRequest
import com.github.panpf.android.compose.samples.R
import com.github.panpf.android.compose.samples.tools.sketchUri2CoilModel
import com.github.panpf.sketch.fetch.newResourceUri
import com.github.panpf.zoomimage.CoilZoomAsyncImage

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
    )
}

@Preview
@Composable
private fun CoilZoomAsyncImageSamplePreview() {
    CoilZoomAsyncImageSample(newResourceUri(R.drawable.im_placeholder))
}