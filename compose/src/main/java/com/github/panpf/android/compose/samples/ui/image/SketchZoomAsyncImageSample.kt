package com.github.panpf.android.compose.samples.ui.image

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.github.panpf.android.compose.samples.R
import com.github.panpf.sketch.fetch.newResourceUri
import com.github.panpf.sketch.request.DisplayRequest
import com.github.panpf.zoomimage.SketchZoomAsyncImage

@Composable
fun SketchZoomAsyncImageSample(sketchImageUri: String) {
    SketchZoomAsyncImage(
        request = DisplayRequest(LocalContext.current, sketchImageUri) {
            crossfade()
        },
        contentDescription = "view image",
        modifier = Modifier.fillMaxSize(),
    )
}

@Preview
@Composable
private fun SketchZoomAsyncImageSamplePreview() {
    SketchZoomAsyncImageSample(newResourceUri(R.drawable.im_placeholder))
}