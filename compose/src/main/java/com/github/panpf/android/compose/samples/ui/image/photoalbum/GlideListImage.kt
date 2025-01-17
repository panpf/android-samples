package com.github.panpf.android.compose.samples.ui.image.photoalbum

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.github.panpf.android.compose.samples.R
import com.github.panpf.android.samples.utils.sketchUri2GlideModel
import com.github.panpf.zoomimage.compose.glide.ExperimentalGlideComposeApi
import com.github.panpf.zoomimage.compose.glide.GlideImage

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun GlideListImage(sketchImageUri: String, modifier: Modifier) {
    val glideModel = remember(sketchImageUri) {
        sketchUri2GlideModel(sketchImageUri)
    }
    GlideImage(
        model = glideModel,
        modifier = modifier,
        contentScale = ContentScale.Crop,
        contentDescription = "photo",
    ) {
        it.placeholder(R.drawable.im_placeholder)
            .error(R.drawable.im_placeholder)
    }
}