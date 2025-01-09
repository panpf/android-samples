package com.github.panpf.android.compose.samples.ui.image.photoalbum

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import com.github.panpf.android.compose.samples.R
import com.github.panpf.sketch.AsyncImage
import com.github.panpf.sketch.request.ImageRequest
import com.github.panpf.sketch.resize.LongImagePrecisionDecider
import com.github.panpf.sketch.resize.LongImageScaleDecider
import com.github.panpf.sketch.resize.Precision.SAME_ASPECT_RATIO
import com.github.panpf.sketch.state.IconDrawableStateImage

@Composable
fun SketchListImage(sketchImageUri: String, modifier: Modifier) {
    AsyncImage(
        request = ImageRequest(LocalContext.current, sketchImageUri) {
            placeholder(
                IconDrawableStateImage(
                    icon = R.drawable.ic_image_outline,
                    background = R.color.placeholder_bg
                )
            )
            error(
                IconDrawableStateImage(
                    icon = R.drawable.ic_error,
                    background = R.color.placeholder_bg
                )
            )
            crossfade()
            resizeOnDraw()
            precision(LongImagePrecisionDecider(SAME_ASPECT_RATIO))
            scale(LongImageScaleDecider())
        },
        modifier = modifier,
        contentScale = ContentScale.Crop,
        contentDescription = "photo",
    )
}