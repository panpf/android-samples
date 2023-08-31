package com.github.panpf.android.compose.samples.ui.image.photoalbum

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.github.panpf.android.compose.samples.R
import com.github.panpf.android.samples.utils.sketchUri2CoilModel

@Composable
fun CoilListImage(sketchImageUri: String, modifier: Modifier) {
    val context = LocalContext.current
    val coilModel = remember(sketchImageUri) {
        sketchUri2CoilModel(context, sketchImageUri)
    }
    AsyncImage(
        model = ImageRequest.Builder(context).apply {
            data(coilModel)
            placeholder(R.drawable.im_placeholder)
            error(R.drawable.im_placeholder)
            crossfade(true)
        }.build(),
        modifier = modifier,
        contentScale = ContentScale.Crop,
        contentDescription = "photo",
    )
}