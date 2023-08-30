package com.github.panpf.android.compose.samples.ui.image

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import coil.request.ImageRequest
import com.github.panpf.android.compose.samples.tools.sketchUri2CoilModel
import me.saket.telephoto.zoomable.coil.ZoomableAsyncImage

@Composable
fun TelephotoZoomableAsyncImageSample(sketchImageUri: String) {
    val context = LocalContext.current
    val coilData = remember(key1 = sketchImageUri) { sketchUri2CoilModel(context, sketchImageUri) }
    ZoomableAsyncImage(
        model = ImageRequest.Builder(LocalContext.current).apply {
            data(coilData)
            crossfade(true)
        }.build(),
        contentDescription = "",
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
    )
}