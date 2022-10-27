package com.github.panpf.android.compose.samples.ui.widgets.asyncimage

import coil.compose.AsyncImage as CoilAsyncImage
import coil.request.ImageRequest as CoilImageRequest
import android.content.Context
import android.net.Uri
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.size.Precision.EXACT
import com.github.panpf.android.compose.samples.R
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.widgets.image.ContentScaleItem
import com.github.panpf.android.compose.samples.ui.widgets.image.PhotoItem
import com.github.panpf.android.compose.samples.ui.widgets.image.SquashedOval
import com.github.panpf.android.compose.samples.ui.widgets.image.blackWhiteColorFilter
import com.github.panpf.android.compose.samples.ui.widgets.image.horPhoto
import com.github.panpf.android.compose.samples.ui.widgets.image.inversionOfNegativeColorFilter
import com.github.panpf.android.compose.samples.ui.widgets.image.newColorFilterByContrastAndBrightness
import com.github.panpf.android.compose.samples.ui.widgets.image.rainbowColorsBrush
import com.github.panpf.android.compose.samples.ui.widgets.image.verPhoto
import com.google.accompanist.flowlayout.FlowRow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun CoilAsyncImageUI() {
    ExpandableLayout { allExpandFlow ->
        CoilAsyncImageResourceSample(allExpandFlow)
        CoilAsyncImageAssetSample(allExpandFlow)
        CoilAsyncImageHttpSample(allExpandFlow)
        CoilAsyncImageAlignmentSample(allExpandFlow)
        CoilAsyncImageContentScaleSample(allExpandFlow)
        CoilAsyncImageAlphaSample(allExpandFlow)
        CoilAsyncImageClipSample(allExpandFlow)
        CoilAsyncImageBorderSample(allExpandFlow)
        CoilAsyncImageColorFilterSample(allExpandFlow)
        CoilAsyncImageBlurSample(allExpandFlow)
    }
}


@Composable
fun CoilAsyncImageResourceSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem(title = "CoilAsyncImage（Resource）", allExpandFlow, padding = 20.dp) {
        CoilAsyncImage(
            model = CoilImageRequest.Builder(context)
                .data(context.newCoilResourceUri(R.drawable.image_hor))
                .placeholder(R.drawable.im_placeholder)
                .build(),
            contentDescription = "",
            modifier = Modifier.size(200.dp),
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun CoilAsyncImageResourceSamplePreview() {
    CoilAsyncImageResourceSample(remember { MutableStateFlow(true) })
}


@Composable
fun CoilAsyncImageAssetSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem(title = "CoilAsyncImage（Asset）", allExpandFlow, padding = 20.dp) {
        CoilAsyncImage(
            model = CoilImageRequest.Builder(context)
                .data(newCoilAssetUri("image3.jpg"))
                .placeholder(R.drawable.im_placeholder)
                .build(),
            contentDescription = "",
            modifier = Modifier.size(200.dp),
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun CoilAsyncImageAssetSamplePreview() {
    CoilAsyncImageAssetSample(remember { MutableStateFlow(true) })
}


@Composable
fun CoilAsyncImageHttpSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem(title = "CoilAsyncImage（Http）", allExpandFlow, padding = 20.dp) {
        CoilAsyncImage(
            model = CoilImageRequest.Builder(context)
                .data("https://images.unsplash.com/photo-1431440869543-efaf3388c585?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max&s=8b00971a3e4a84fb43403797126d1991%22")
                .placeholder(R.drawable.im_placeholder)
                .build(),
            contentDescription = "",
            modifier = Modifier.size(200.dp),
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun CoilAsyncImageHttpSamplePreview() {
    CoilAsyncImageHttpSample(remember { MutableStateFlow(true) })
}


@Composable
fun CoilAsyncImageAlignmentSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    val photo = horPhoto
    ExpandableItem(title = "CoilAsyncImage（alignment）", allExpandFlow, padding = 20.dp) {
        FlowRow(mainAxisSpacing = 10.dp, crossAxisSpacing = 10.dp) {
            listOf(
                Alignment.TopStart to "TopStart",
                Alignment.TopCenter to "TopCenter",
                Alignment.TopEnd to "TopEnd",
                Alignment.CenterStart to "CenterStart",
                Alignment.Center to "Center",
                Alignment.CenterEnd to "CenterEnd",
                Alignment.BottomStart to "BottomStart",
                Alignment.BottomCenter to "BottomCenter",
                Alignment.BottomEnd to "BottomEnd",
            ).forEach { alignment ->
                Column {
                    Text(
                        text = alignment.second,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                    val viewSize = 110.dp
                    val viewSizePx = with(LocalDensity.current) { viewSize.toPx() }
                    val targetSize = photo.calculateTargetSize(viewSizePx.toInt(), false)
                    CoilAsyncImage(
                        model = CoilImageRequest.Builder(context)
                            .data(context.newCoilResourceUri(photo.resId))
                            .placeholder(R.drawable.im_placeholder)
                            .size(targetSize.width.toInt(), targetSize.height.toInt())
                            .precision(EXACT)
                            .build(),
                        contentDescription = "",
                        modifier = Modifier
                            .size(110.dp)
                            .background(Color.Red.copy(alpha = 0.5f))
                            .padding(2.dp),
                        contentScale = ContentScale.None,
                        alignment = alignment.first,
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun CoilAsyncImageAlignmentSamplePreview() {
    CoilAsyncImageAlignmentSample(remember { MutableStateFlow(true) })
}


@Composable
fun CoilAsyncImageContentScaleSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    val items = remember {
        val horBig = PhotoItem(horPhoto, "横向图片 - 大", true)
        val horSmall = PhotoItem(horPhoto, "横向图片 - 小", false)
        val verBig = PhotoItem(verPhoto, "纵向图片 - 大", true)
        val verSmall = PhotoItem(verPhoto, "纵向图片 - 小", false)
        listOf(
            ContentScaleItem(ContentScale.Fit, "Fit", listOf(horBig, verBig)),
            ContentScaleItem(ContentScale.FillBounds, "FillBounds", listOf(horBig, verBig)),
            ContentScaleItem(ContentScale.FillWidth, "FillWidth", listOf(horBig, verBig)),
            ContentScaleItem(ContentScale.FillHeight, "FillHeight", listOf(horBig, verBig)),
            ContentScaleItem(ContentScale.Crop, "Crop", listOf(horBig, verBig)),
            ContentScaleItem(
                ContentScale.Inside,
                "Inside",
                listOf(horBig, verBig, horSmall, verSmall)
            ),
            ContentScaleItem(ContentScale.None, "None", listOf(horBig, verBig, horSmall, verSmall)),
        )
    }
    ExpandableItem(title = "CoilAsyncImage（contentScale）", allExpandFlow, padding = 20.dp) {
        Column {
            items.forEachIndexed { index, items ->
                if (index != 0) {
                    Spacer(modifier = Modifier.size(10.dp))
                }
                Row {
                    Text(
                        text = items.name,
                        modifier = Modifier
                            .size(80.dp)
                            .padding(top = 18.dp),
                    )
                    Spacer(modifier = Modifier.size(10.dp))
                    FlowRow(mainAxisSpacing = 10.dp, crossAxisSpacing = 10.dp) {
                        items.sampleResList.forEach { photoItem ->
                            Column {
                                Text(
                                    text = photoItem.name,
                                    modifier = Modifier.align(Alignment.CenterHorizontally),
                                )
                                val viewSize = 110.dp
                                val viewSizePx = with(LocalDensity.current) { viewSize.toPx() }
                                val targetSize = photoItem.photo
                                    .calculateTargetSize(viewSizePx.toInt(), photoItem.big)
                                CoilAsyncImage(
                                    model = CoilImageRequest.Builder(context)
                                        .data(context.newCoilResourceUri(photoItem.photo.resId))
                                        .placeholder(R.drawable.im_placeholder)
                                        .size(targetSize.width.toInt(), targetSize.height.toInt())
                                        .precision(EXACT)
                                        .build(),
                                    contentDescription = "",
                                    modifier = Modifier
                                        .size(110.dp)
                                        .background(Color.Red.copy(alpha = 0.5f))
                                        .padding(2.dp),
                                    contentScale = items.contentScale,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun CoilAsyncImageContentScaleSamplePreview() {
    CoilAsyncImageContentScaleSample(remember { MutableStateFlow(true) })
}


@Composable
fun CoilAsyncImageAlphaSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem(title = "CoilAsyncImage（alpha）", allExpandFlow, padding = 20.dp) {
        CoilAsyncImage(
            model = CoilImageRequest.Builder(context)
                .data(context.newCoilResourceUri(R.drawable.image_hor))
                .placeholder(R.drawable.im_placeholder)
                .build(),
            contentDescription = "",
            modifier = Modifier.size(200.dp),
            alpha = 0.5f
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun CoilAsyncImageAlphaSamplePreview() {
    CoilAsyncImageAlphaSample(remember { MutableStateFlow(true) })
}


@Composable
fun CoilAsyncImageClipSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem(title = "CoilAsyncImage（shape）", allExpandFlow, padding = 20.dp) {
        Row {
            CoilAsyncImage(
                model = CoilImageRequest.Builder(context)
                    .data(context.newCoilResourceUri(R.drawable.image_hor))
                    .placeholder(R.drawable.im_placeholder)
                    .build(),
                contentDescription = "",
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(20.dp)),
                contentScale = ContentScale.Crop,
            )

            Spacer(modifier = Modifier.size(10.dp))

            CoilAsyncImage(
                model = CoilImageRequest.Builder(context)
                    .data(context.newCoilResourceUri(R.drawable.image_hor))
                    .placeholder(R.drawable.im_placeholder)
                    .build(),
                contentDescription = "",
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop,
            )

            Spacer(modifier = Modifier.size(10.dp))

            CoilAsyncImage(
                model = CoilImageRequest.Builder(context)
                    .data(context.newCoilResourceUri(R.drawable.image_hor))
                    .placeholder(R.drawable.im_placeholder)
                    .build(),
                contentDescription = "",
                modifier = Modifier
                    .size(100.dp)
                    .clip(SquashedOval()),
                contentScale = ContentScale.Crop,
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun CoilAsyncImageClipSamplePreview() {
    CoilAsyncImageClipSample(remember { MutableStateFlow(true) })
}


@Composable
fun CoilAsyncImageBorderSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem(title = "CoilAsyncImage（border）", allExpandFlow, padding = 20.dp) {
        Row {
            CoilAsyncImage(
                model = CoilImageRequest.Builder(context)
                    .data(context.newCoilResourceUri(R.drawable.image_hor))
                    .placeholder(R.drawable.im_placeholder)
                    .build(),
                contentDescription = "",
                modifier = Modifier
                    .size(100.dp)
                    .border(2.dp, Color.Magenta),
                contentScale = ContentScale.Crop,
            )

            Spacer(modifier = Modifier.size(10.dp))

            CoilAsyncImage(
                model = CoilImageRequest.Builder(context)
                    .data(context.newCoilResourceUri(R.drawable.image_hor))
                    .placeholder(R.drawable.im_placeholder)
                    .build(),
                contentDescription = "",
                modifier = Modifier
                    .size(100.dp)
                    .border(2.dp, Color.Magenta, RoundedCornerShape(20.dp))
                    .clip(RoundedCornerShape(20.dp)),
                contentScale = ContentScale.Crop,
            )

            Spacer(modifier = Modifier.size(10.dp))

            CoilAsyncImage(
                model = CoilImageRequest.Builder(context)
                    .data(context.newCoilResourceUri(R.drawable.image_hor))
                    .placeholder(R.drawable.im_placeholder)
                    .build(),
                contentDescription = "",
                modifier = Modifier
                    .size(100.dp)
                    .border(2.dp, rainbowColorsBrush, CircleShape)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop,
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun CoilAsyncImageBorderSamplePreview() {
    CoilAsyncImageBorderSample(remember { MutableStateFlow(true) })
}


@Composable
fun CoilAsyncImageColorFilterSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem(title = "CoilAsyncImage（colorFilter）", allExpandFlow, padding = 20.dp) {
        Row {
            Column {
                Text(text = "黑白", Modifier.align(Alignment.CenterHorizontally))
                CoilAsyncImage(
                    model = CoilImageRequest.Builder(context)
                        .data(context.newCoilResourceUri(R.drawable.image_hor))
                        .placeholder(R.drawable.im_placeholder)
                        .build(),
                    contentDescription = "",
                    modifier = Modifier.size(100.dp),
                    colorFilter = blackWhiteColorFilter
                )
            }
            Spacer(modifier = Modifier.size(10.dp))
            Column {
                Text(text = "反转负片", Modifier.align(Alignment.CenterHorizontally))
                CoilAsyncImage(
                    model = CoilImageRequest.Builder(context)
                        .data(context.newCoilResourceUri(R.drawable.image_hor))
                        .placeholder(R.drawable.im_placeholder)
                        .build(),
                    contentDescription = "",
                    modifier = Modifier.size(100.dp),
                    colorFilter = inversionOfNegativeColorFilter
                )
            }
            Spacer(modifier = Modifier.size(10.dp))
            Column {
                Text(text = "亮度对比度", Modifier.align(Alignment.CenterHorizontally))
                CoilAsyncImage(
                    model = CoilImageRequest.Builder(context)
                        .data(context.newCoilResourceUri(R.drawable.image_hor))
                        .placeholder(R.drawable.im_placeholder)
                        .build(),
                    contentDescription = "",
                    modifier = Modifier.size(100.dp),
                    colorFilter = newColorFilterByContrastAndBrightness()
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun CoilAsyncImageColorFilterSamplePreview() {
    CoilAsyncImageColorFilterSample(remember { MutableStateFlow(true) })
}


@Composable
fun CoilAsyncImageBlurSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem(title = "CoilAsyncImage（blur）", allExpandFlow, padding = 20.dp) {
        Column {
            Text(text = "仅支持 Android 12 以上版本")
            Spacer(modifier = Modifier.size(10.dp))
            Row {
                CoilAsyncImage(
                    model = CoilImageRequest.Builder(context)
                        .data(context.newCoilResourceUri(R.drawable.image_hor))
                        .placeholder(R.drawable.im_placeholder)
                        .build(),
                    contentScale = ContentScale.Crop,
                    contentDescription = "",
                    modifier = Modifier
                        .size(100.dp)
                        .blur(
                            radius = 5.dp,
                            edgeTreatment = BlurredEdgeTreatment.Rectangle
                        ),
                )
                Spacer(modifier = Modifier.size(10.dp))
                CoilAsyncImage(
                    model = CoilImageRequest.Builder(context)
                        .data(context.newCoilResourceUri(R.drawable.image_hor))
                        .placeholder(R.drawable.im_placeholder)
                        .build(),
                    contentScale = ContentScale.Crop,
                    contentDescription = "",
                    modifier = Modifier
                        .size(100.dp)
                        .blur(
                            radius = 5.dp,
                            edgeTreatment = BlurredEdgeTreatment(RoundedCornerShape(8.dp))
                        ),
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun CoilAsyncImageBlurSamplePreview() {
    CoilAsyncImageBlurSample(remember { MutableStateFlow(true) })
}

private fun Context.newCoilResourceUri(@DrawableRes id: Int): Uri {
    return Uri.parse("android.resource://${packageName}/${id}")
}

private fun newCoilAssetUri(@Suppress("SameParameterValue") path: String): Uri {
    return Uri.parse("file://filled/android_asset/$path")
}