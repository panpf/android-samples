package com.github.panpf.android.compose.samples.ui.components.extension

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Precision
import com.github.panpf.android.compose.samples.R
import com.github.panpf.android.compose.samples.ui.base.ContentScaleItem
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem3
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
import com.github.panpf.android.compose.samples.ui.base.PhotoItem
import com.github.panpf.android.compose.samples.ui.base.SquashedOval
import com.github.panpf.android.compose.samples.ui.base.blackWhiteColorFilter
import com.github.panpf.android.compose.samples.ui.base.horPhoto
import com.github.panpf.android.compose.samples.ui.base.httpPhotoUrl
import com.github.panpf.android.compose.samples.ui.base.inversionOfNegativeColorFilter
import com.github.panpf.android.compose.samples.ui.base.newColorFilterByContrastAndBrightness
import com.github.panpf.android.compose.samples.ui.base.rainbowColorsBrush
import com.github.panpf.android.compose.samples.ui.base.verPhoto
import com.google.accompanist.flowlayout.FlowRow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class AsyncImageCoilFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "AsyncImage - Coil"
    }

    @Composable
    override fun DrawContent() {
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
}


@Composable
private fun CoilAsyncImageResourceSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem3(title = "CoilAsyncImage（Resource）", allExpandFlow, padding = 20.dp) {
        AsyncImage(
            model = ImageRequest.Builder(context)
                .data(context.newCoilResourceUri(R.drawable.dog_hor))
                .placeholder(R.drawable.im_placeholder)
                .build(),
            contentDescription = "",
            modifier = Modifier.size(200.dp),
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun CoilAsyncImageResourceSamplePreview() {
    CoilAsyncImageResourceSample(remember { MutableStateFlow(true) })
}


@Composable
private fun CoilAsyncImageAssetSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem3(title = "CoilAsyncImage（Asset）", allExpandFlow, padding = 20.dp) {
        AsyncImage(
            model = ImageRequest.Builder(context)
                .data(newCoilAssetUri("dog.jpg"))
                .placeholder(R.drawable.im_placeholder)
                .build(),
            contentDescription = "",
            modifier = Modifier.size(200.dp),
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun CoilAsyncImageAssetSamplePreview() {
    CoilAsyncImageAssetSample(remember { MutableStateFlow(true) })
}


@Composable
private fun CoilAsyncImageHttpSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem3(title = "CoilAsyncImage（Http）", allExpandFlow, padding = 20.dp) {
        AsyncImage(
            model = ImageRequest.Builder(context)
                .data(httpPhotoUrl)
                .placeholder(R.drawable.im_placeholder)
                .build(),
            contentDescription = "",
            modifier = Modifier.size(200.dp),
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun CoilAsyncImageHttpSamplePreview() {
    CoilAsyncImageHttpSample(remember { MutableStateFlow(true) })
}


@Composable
private fun CoilAsyncImageAlignmentSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    val photo = horPhoto
    ExpandableItem3(title = "CoilAsyncImage（alignment）", allExpandFlow, padding = 20.dp) {
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
                    AsyncImage(
                        model = ImageRequest.Builder(context)
                            .data(context.newCoilResourceUri(photo.resId))
                            .placeholder(R.drawable.im_placeholder)
                            .size(targetSize.width.toInt(), targetSize.height.toInt())
                            .precision(Precision.EXACT)
                            .build(),
                        contentDescription = "",
                        modifier = Modifier
                            .size(110.dp)
                            .background(MaterialTheme.colorScheme.primaryContainer)
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
private fun CoilAsyncImageAlignmentSamplePreview() {
    CoilAsyncImageAlignmentSample(remember { MutableStateFlow(true) })
}


@Composable
private fun CoilAsyncImageContentScaleSample(allExpandFlow: Flow<Boolean>) {
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
    ExpandableItem3(title = "CoilAsyncImage（contentScale）", allExpandFlow, padding = 20.dp) {
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
                                AsyncImage(
                                    model = ImageRequest.Builder(context)
                                        .data(context.newCoilResourceUri(photoItem.photo.resId))
                                        .placeholder(R.drawable.im_placeholder)
                                        .size(targetSize.width.toInt(), targetSize.height.toInt())
                                        .precision(Precision.EXACT)
                                        .build(),
                                    contentDescription = "",
                                    modifier = Modifier
                                        .size(110.dp)
                                        .background(MaterialTheme.colorScheme.primaryContainer)
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
private fun CoilAsyncImageContentScaleSamplePreview() {
    CoilAsyncImageContentScaleSample(remember { MutableStateFlow(true) })
}


@Composable
private fun CoilAsyncImageAlphaSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem3(title = "CoilAsyncImage（alpha）", allExpandFlow, padding = 20.dp) {
        AsyncImage(
            model = ImageRequest.Builder(context)
                .data(context.newCoilResourceUri(R.drawable.dog_hor))
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
private fun CoilAsyncImageAlphaSamplePreview() {
    CoilAsyncImageAlphaSample(remember { MutableStateFlow(true) })
}


@Composable
private fun CoilAsyncImageClipSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem3(title = "CoilAsyncImage（shape）", allExpandFlow, padding = 20.dp) {
        Row {
            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(context.newCoilResourceUri(R.drawable.dog_hor))
                    .placeholder(R.drawable.im_placeholder)
                    .build(),
                contentDescription = "",
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(20.dp)),
                contentScale = ContentScale.Crop,
            )

            Spacer(modifier = Modifier.size(10.dp))

            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(context.newCoilResourceUri(R.drawable.dog_hor))
                    .placeholder(R.drawable.im_placeholder)
                    .build(),
                contentDescription = "",
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop,
            )

            Spacer(modifier = Modifier.size(10.dp))

            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(context.newCoilResourceUri(R.drawable.dog_hor))
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
private fun CoilAsyncImageClipSamplePreview() {
    CoilAsyncImageClipSample(remember { MutableStateFlow(true) })
}


@Composable
private fun CoilAsyncImageBorderSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem3(title = "CoilAsyncImage（border）", allExpandFlow, padding = 20.dp) {
        Row {
            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(context.newCoilResourceUri(R.drawable.dog_hor))
                    .placeholder(R.drawable.im_placeholder)
                    .build(),
                contentDescription = "",
                modifier = Modifier
                    .size(100.dp)
                    .border(2.dp, MaterialTheme.colorScheme.primary),
                contentScale = ContentScale.Crop,
            )

            Spacer(modifier = Modifier.size(10.dp))

            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(context.newCoilResourceUri(R.drawable.dog_hor))
                    .placeholder(R.drawable.im_placeholder)
                    .build(),
                contentDescription = "",
                modifier = Modifier
                    .size(100.dp)
                    .border(2.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(20.dp))
                    .clip(RoundedCornerShape(20.dp)),
                contentScale = ContentScale.Crop,
            )

            Spacer(modifier = Modifier.size(10.dp))

            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(context.newCoilResourceUri(R.drawable.dog_hor))
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
private fun CoilAsyncImageBorderSamplePreview() {
    CoilAsyncImageBorderSample(remember { MutableStateFlow(true) })
}


@Composable
private fun CoilAsyncImageColorFilterSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem3(title = "CoilAsyncImage（colorFilter）", allExpandFlow, padding = 20.dp) {
        Row {
            Column {
                Text(text = "黑白", Modifier.align(Alignment.CenterHorizontally))
                AsyncImage(
                    model = ImageRequest.Builder(context)
                        .data(context.newCoilResourceUri(R.drawable.dog_hor))
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
                AsyncImage(
                    model = ImageRequest.Builder(context)
                        .data(context.newCoilResourceUri(R.drawable.dog_hor))
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
                AsyncImage(
                    model = ImageRequest.Builder(context)
                        .data(context.newCoilResourceUri(R.drawable.dog_hor))
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
private fun CoilAsyncImageColorFilterSamplePreview() {
    CoilAsyncImageColorFilterSample(remember { MutableStateFlow(true) })
}


@Composable
private fun CoilAsyncImageBlurSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem3(title = "CoilAsyncImage（blur）", allExpandFlow, padding = 20.dp) {
        Column {
            Text(text = "仅支持 Android 12 以上版本")
            Spacer(modifier = Modifier.size(10.dp))
            Row {
                AsyncImage(
                    model = ImageRequest.Builder(context)
                        .data(context.newCoilResourceUri(R.drawable.dog_hor))
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
                AsyncImage(
                    model = ImageRequest.Builder(context)
                        .data(context.newCoilResourceUri(R.drawable.dog_hor))
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
private fun CoilAsyncImageBlurSamplePreview() {
    CoilAsyncImageBlurSample(remember { MutableStateFlow(true) })
}

private fun Context.newCoilResourceUri(@DrawableRes id: Int): Uri {
    return Uri.parse("android.resource://${packageName}/${id}")
}

private fun newCoilAssetUri(@Suppress("SameParameterValue") path: String): Uri {
    return Uri.parse("file://filled/android_asset/$path")
}