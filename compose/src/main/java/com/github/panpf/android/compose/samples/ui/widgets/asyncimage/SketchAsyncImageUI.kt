package com.github.panpf.android.compose.samples.ui.widgets.asyncimage

import com.github.panpf.sketch.compose.AsyncImage as SketchAsyncImage
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.panpf.android.compose.samples.R
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.widgets.image.ContentScaleItem
import com.github.panpf.sketch.fetch.newAssetUri
import com.github.panpf.sketch.fetch.newResourceUri
import com.google.accompanist.flowlayout.FlowRow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun SketchAsyncImageUI() {
    ExpandableLayout { allExpandFlow ->
        SketchAsyncImageResourceSample(allExpandFlow)
        SketchAsyncImageAssetSample(allExpandFlow)
        SketchAsyncImageHttpSample(allExpandFlow)
        SketchAsyncImageAlignmentSample(allExpandFlow)
        SketchAsyncImageContentScaleSample(allExpandFlow)
        SketchAsyncImageAlphaSample(allExpandFlow)
        SketchAsyncImageColorFilterSample(allExpandFlow)
        SketchAsyncImageClipSample(allExpandFlow)
    }
}


@Composable
fun SketchAsyncImageResourceSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem(title = "SketchAsyncImage（Resource）", allExpandFlow, padding = 20.dp) {
        SketchAsyncImage(
            imageUri = newResourceUri(R.drawable.image_hor),
            contentDescription = "",
            modifier = Modifier
                .size(200.dp)
                .background(Color.Red.copy(alpha = 0.5f))
                .padding(2.dp),
        ) {
            placeholder(R.drawable.im_placeholder)
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun SketchAsyncImageResourceSamplePreview() {
    SketchAsyncImageResourceSample(remember { MutableStateFlow(true) })
}


@Composable
fun SketchAsyncImageAssetSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem(title = "SketchAsyncImage（Asset）", allExpandFlow, padding = 20.dp) {
        SketchAsyncImage(
            imageUri = newAssetUri("image3.jpg"),
            contentDescription = "",
            modifier = Modifier
                .size(200.dp)
                .background(Color.Red.copy(alpha = 0.5f))
                .padding(2.dp),
        ) {
            placeholder(R.drawable.im_placeholder)
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun SketchAsyncImageAssetSamplePreview() {
    SketchAsyncImageAssetSample(remember { MutableStateFlow(true) })
}


@Composable
fun SketchAsyncImageHttpSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem(title = "SketchAsyncImage（Http）", allExpandFlow, padding = 20.dp) {
        SketchAsyncImage(
            imageUri = "https://images.unsplash.com/photo-1431440869543-efaf3388c585?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max&s=8b00971a3e4a84fb43403797126d1991%22",
            contentDescription = "",
            modifier = Modifier
                .size(200.dp)
                .background(Color.Red.copy(alpha = 0.5f))
                .padding(2.dp),
        ) {
            placeholder(R.drawable.im_placeholder)
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun SketchAsyncImageHttpSamplePreview() {
    SketchAsyncImageHttpSample(remember { MutableStateFlow(true) })
}


@Composable
fun SketchAsyncImageAlignmentSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem(title = "SketchAsyncImage（alignment）", allExpandFlow, padding = 20.dp) {
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
                    SketchAsyncImage(
                        imageUri = newResourceUri(R.drawable.image_hor_small),
                        contentDescription = "",
                        modifier = Modifier
                            .size(110.dp)
                            .background(Color.Red.copy(alpha = 0.5f))
                            .padding(2.dp),
                        contentScale = ContentScale.None,
                        alignment = alignment.first,
                    ) {
                        placeholder(R.drawable.im_placeholder)
                        // SketchAsyncImage 默认会根据 SketchAsyncImage 的大小调整图片尺寸，主动将 resize 设置为很大的值可以避免缩小图片
                        resizeSize(10000, 10000)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun SketchAsyncImageAlignmentSamplePreview() {
    SketchAsyncImageAlignmentSample(remember { MutableStateFlow(true) })
}


@Composable
fun SketchAsyncImageContentScaleSample(allExpandFlow: Flow<Boolean>) {
    val hor = R.drawable.image_hor to "横向图片"
    val horSmall = R.drawable.image_hor_small to "横向图片 - 小"
    val ver = R.drawable.image_ver to "纵向图片"
    val verSmall = R.drawable.image_ver_small to "纵向图片 - 小"
    val items = listOf(
        ContentScaleItem(ContentScale.Fit, "Fit", listOf(hor, ver)),
        ContentScaleItem(ContentScale.FillBounds, "FillBounds", listOf(hor, ver)),
        ContentScaleItem(ContentScale.FillWidth, "FillWidth", listOf(hor, ver)),
        ContentScaleItem(ContentScale.FillHeight, "FillHeight", listOf(hor, ver)),
        ContentScaleItem(ContentScale.Crop, "Crop", listOf(hor, ver)),
        ContentScaleItem(ContentScale.Inside, "Inside", listOf(hor, ver, horSmall, verSmall)),
        ContentScaleItem(ContentScale.None, "None", listOf(hor, ver, horSmall, verSmall)),
    )
    ExpandableItem(title = "SketchAsyncImage（contentScale）", allExpandFlow, padding = 20.dp) {
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
                        items.sampleResList.forEach { res ->
                            Column {
                                Text(
                                    text = res.second,
                                    modifier = Modifier.align(Alignment.CenterHorizontally),
                                )
                                SketchAsyncImage(
                                    imageUri = newResourceUri(res.first),
                                    contentDescription = "",
                                    modifier = Modifier
                                        .size(110.dp)
                                        .background(Color.Red.copy(alpha = 0.5f))
                                        .padding(2.dp),
                                    contentScale = items.contentScale,
                                ) {
                                    placeholder(R.drawable.im_placeholder)
                                    // SketchAsyncImage 默认会根据 SketchAsyncImage 的大小调整图片尺寸，主动将 resize 设置为很大的值可以避免缩小图片
                                    resizeSize(10000, 10000)
                                }
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
fun SketchAsyncImageContentScaleSamplePreview() {
    SketchAsyncImageContentScaleSample(remember { MutableStateFlow(true) })
}


@Composable
fun SketchAsyncImageAlphaSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem(title = "SketchAsyncImage（alpha）", allExpandFlow, padding = 20.dp) {
        SketchAsyncImage(
            imageUri = newResourceUri(R.drawable.image_hor),
            contentDescription = "",
            modifier = Modifier
                .size(200.dp)
                .background(Color.Red.copy(alpha = 0.5f))
                .padding(2.dp),
            alpha = 0.5f
        ) {
            placeholder(R.drawable.im_placeholder)
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun SketchAsyncImageAlphaSamplePreview() {
    SketchAsyncImageAlphaSample(remember { MutableStateFlow(true) })
}


@Composable
fun SketchAsyncImageColorFilterSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem(title = "SketchAsyncImage（colorFilter）", allExpandFlow, padding = 20.dp) {
        SketchAsyncImage(
            imageUri = newResourceUri(R.drawable.image_hor),
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
        ) {
            placeholder(R.drawable.im_placeholder)
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun SketchAsyncImageColorFilterSamplePreview() {
    SketchAsyncImageColorFilterSample(remember { MutableStateFlow(true) })
}


@Composable
fun SketchAsyncImageClipSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem(title = "SketchAsyncImage（shape）", allExpandFlow, padding = 20.dp) {
        Row {
            SketchAsyncImage(
                imageUri = newResourceUri(R.drawable.image_hor),
                contentDescription = "",
                modifier = Modifier
                    .size(160.dp)
                    .clip(RoundedCornerShape(20.dp)),
                contentScale = ContentScale.Crop,
            ) {
                placeholder(R.drawable.im_placeholder)
            }

            Spacer(modifier = Modifier.size(10.dp))

            SketchAsyncImage(
                imageUri = newResourceUri(R.drawable.image_hor),
                contentDescription = "",
                modifier = Modifier
                    .size(160.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop,
            ) {
                placeholder(R.drawable.im_placeholder)
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun AsyncSketchAsyncImageClipSamplePreview() {
    SketchAsyncImageClipSample(remember { MutableStateFlow(true) })
}