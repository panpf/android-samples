package com.github.panpf.android.compose.samples.ui.image

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import com.github.panpf.android.compose.samples.R
import com.github.panpf.sketch.compose.AsyncImage
import com.github.panpf.sketch.request.DisplayRequest
import com.github.panpf.sketch.resize.LongImageClipPrecisionDecider
import com.github.panpf.sketch.resize.LongImageScaleDecider
import com.github.panpf.sketch.resize.Precision.SAME_ASPECT_RATIO
import com.github.panpf.sketch.stateimage.IconStateImage
import com.github.panpf.sketch.stateimage.ResColor

@Composable
fun SketchListImage(sketchImageUri: String, modifier: Modifier) {
    AsyncImage(
        request = DisplayRequest(LocalContext.current, sketchImageUri) {
            placeholder(
                IconStateImage(R.drawable.ic_image_outline, ResColor(R.color.placeholder_bg))
            )
            error(
                IconStateImage(R.drawable.ic_error, ResColor(R.color.placeholder_bg))
            )
            crossfade()
            resizeApplyToDrawable()
            resizePrecision(LongImageClipPrecisionDecider(SAME_ASPECT_RATIO))
            resizeScale(LongImageScaleDecider())
        },
        modifier = modifier,
        contentScale = ContentScale.Crop,
        contentDescription = "photo",
    )
}