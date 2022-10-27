package com.github.panpf.android.compose.samples.ui.widgets.asyncimage

import coil.compose.AsyncImage as CoilAsyncImage
import coil.request.ImageRequest as CoilImageRequest
import android.content.Context
import android.net.Uri
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.panpf.android.compose.samples.R
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
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
        CoilAsyncImageContentScaleSmallSample(allExpandFlow)
        CoilAsyncImageContentScaleLargeSample(allExpandFlow)
        CoilAsyncImageAlphaSample(allExpandFlow)
        CoilAsyncImageColorFilterSample(allExpandFlow)
        CoilAsyncImageClipSample(allExpandFlow)
    }
}


@Composable
fun CoilAsyncImageResourceSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem(title = "CoilAsyncImage（Resource）", allExpandFlow, padding = 20.dp) {
        CoilAsyncImage(
            model = CoilImageRequest.Builder(context)
                .data(context.newCoilResourceUri(R.drawable.image))
                .placeholder(R.drawable.im_placeholder)
                .build(),
            contentDescription = "",
            modifier = Modifier
                .size(200.dp)
                .background(Color.Red.copy(alpha = 0.5f))
                .padding(2.dp),
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
            modifier = Modifier
                .size(200.dp)
                .background(Color.Red.copy(alpha = 0.5f))
                .padding(2.dp),
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
            modifier = Modifier
                .size(200.dp)
                .background(Color.Red.copy(alpha = 0.5f))
                .padding(2.dp),
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
                    CoilAsyncImage(
                        model = CoilImageRequest.Builder(context)
                            .data(context.newCoilResourceUri(R.drawable.image2))
                            .placeholder(R.drawable.im_placeholder)
                            // CoilAsyncImage 默认会根据 SketchAsyncImage 的大小调整图片尺寸，主动将 resize 设置为很大的值可以避免缩小图片
                            .size(10000, 10000)
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
fun CoilAsyncImageContentScaleSmallSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem(title = "CoilAsyncImage（contentScale - Small）", allExpandFlow, padding = 20.dp) {
        FlowRow(mainAxisSpacing = 10.dp, crossAxisSpacing = 10.dp) {
            listOf(
                ContentScale.None to "None",
                ContentScale.Inside to "Inside",
                ContentScale.Crop to "Crop",
                ContentScale.FillBounds to "FillBounds",
                ContentScale.FillWidth to "FillWidth",
                ContentScale.FillHeight to "FillHeight",
                ContentScale.Fit to "Fit",
            ).forEach { contentScale ->
                Column {
                    Text(
                        text = contentScale.second,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                    CoilAsyncImage(
                        model = CoilImageRequest.Builder(context)
                            .data(context.newCoilResourceUri(R.drawable.image2))
                            .placeholder(R.drawable.im_placeholder)
                            // CoilAsyncImage 默认会根据 SketchAsyncImage 的大小调整图片尺寸，主动将 resize 设置为很大的值可以避免缩小图片
                            .size(10000, 10000)
                            .build(),
                        contentDescription = "",
                        modifier = Modifier
                            .size(110.dp)
                            .background(Color.Red.copy(alpha = 0.5f))
                            .padding(2.dp),
                        contentScale = contentScale.first,
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun CoilAsyncImageContentScaleSmallSamplePreview() {
    CoilAsyncImageContentScaleSmallSample(remember { MutableStateFlow(true) })
}


@Composable
fun CoilAsyncImageContentScaleLargeSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem(title = "CoilAsyncImage（contentScale - Large）", allExpandFlow, padding = 20.dp) {
        FlowRow(mainAxisSpacing = 10.dp, crossAxisSpacing = 10.dp) {
            listOf(
                ContentScale.None to "None",
                ContentScale.Inside to "Inside",
                ContentScale.Crop to "Crop",
                ContentScale.FillBounds to "FillBounds",
                ContentScale.FillWidth to "FillWidth",
                ContentScale.FillHeight to "FillHeight",
                ContentScale.Fit to "Fit",
            ).forEach { contentScale ->
                Column {
                    Text(
                        text = contentScale.second,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                    CoilAsyncImage(
                        model = CoilImageRequest.Builder(context)
                            .data(context.newCoilResourceUri(R.drawable.image))
                            .placeholder(R.drawable.im_placeholder)
                            // CoilAsyncImage 默认会根据 SketchAsyncImage 的大小调整图片尺寸，主动将 resize 设置为很大的值可以避免缩小图片
                            .size(10000, 10000)
                            .build(),
                        contentDescription = "",
                        modifier = Modifier
                            .size(110.dp)
                            .background(Color.Red.copy(alpha = 0.5f))
                            .padding(2.dp),
                        contentScale = contentScale.first,
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun CoilAsyncImageContentScaleLargeSamplePreview() {
    CoilAsyncImageContentScaleLargeSample(remember { MutableStateFlow(true) })
}


@Composable
fun CoilAsyncImageAlphaSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem(title = "CoilAsyncImage（alpha）", allExpandFlow, padding = 20.dp) {
        CoilAsyncImage(
            model = CoilImageRequest.Builder(context)
                .data(context.newCoilResourceUri(R.drawable.image))
                .placeholder(R.drawable.im_placeholder)
                .build(),
            contentDescription = "",
            modifier = Modifier
                .size(200.dp)
                .background(Color.Red.copy(alpha = 0.5f))
                .padding(2.dp),
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
fun CoilAsyncImageColorFilterSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem(title = "CoilAsyncImage（colorFilter）", allExpandFlow, padding = 20.dp) {
        CoilAsyncImage(
            model = CoilImageRequest.Builder(context)
                .data(context.newCoilResourceUri(R.drawable.image))
                .placeholder(R.drawable.im_placeholder)
                .build(),
            contentDescription = "",
            modifier = Modifier
                .size(200.dp)
                .background(Color.Red.copy(alpha = 0.5f))
                .padding(2.dp),
            colorFilter = ColorFilter.colorMatrix(
                ColorMatrix(
                    floatArrayOf(
                        0.33F, 0.59F, 0.11F, 0f, 0f,
                        0.33F, 0.59F, 0.11F, 0f, 0f,
                        0.33F, 0.59F, 0.11F, 0f, 0f,
                        0f, 0f, 0f, 1f, 0f
                    )
                )
            )
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun CoilAsyncImageColorFilterSamplePreview() {
    CoilAsyncImageColorFilterSample(remember { MutableStateFlow(true) })
}


@Composable
fun CoilAsyncImageClipSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem(title = "CoilAsyncImage（shape）", allExpandFlow, padding = 20.dp) {
        Row {
            CoilAsyncImage(
                model = CoilImageRequest.Builder(context)
                    .data(context.newCoilResourceUri(R.drawable.image))
                    .placeholder(R.drawable.im_placeholder)
                    .build(),
                contentDescription = "",
                modifier = Modifier
                    .size(160.dp)
                    .clip(RoundedCornerShape(20.dp)),
                contentScale = ContentScale.Crop,
            )

            Spacer(modifier = Modifier.size(10.dp))

            CoilAsyncImage(
                model = CoilImageRequest.Builder(context)
                    .data(context.newCoilResourceUri(R.drawable.image))
                    .placeholder(R.drawable.im_placeholder)
                    .build(),
                contentDescription = "",
                modifier = Modifier
                    .size(160.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop,
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun AsyncCoilAsyncImageClipSamplePreview() {
    CoilAsyncImageClipSample(remember { MutableStateFlow(true) })
}

private fun Context.newCoilResourceUri(@DrawableRes id: Int): Uri {
    return Uri.parse("android.resource://${packageName}/${id}")
}

private fun newCoilAssetUri(path: String): Uri {
    return Uri.parse("file://filled/android_asset/$path")
}