package com.github.panpf.android.compose.samples.ui.widgets.image

import android.graphics.drawable.BitmapDrawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.res.ResourcesCompat
import com.github.panpf.android.compose.samples.R
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.google.accompanist.flowlayout.FlowRow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun ImageUI() {
    ExpandableLayout { allExpandFlow ->
        ImageResourceSample(allExpandFlow)
        ImageVectorSample(allExpandFlow)
        ImageBitmapSample(allExpandFlow)
        ImageAlignmentSample(allExpandFlow)
        ImageContentScaleSample(allExpandFlow)
        ImageAlphaSample(allExpandFlow)
        ImageColorFilterSample(allExpandFlow)
        ImageClipSample(allExpandFlow)
    }
}


@Composable
fun ImageResourceSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem(title = "Image（Resource）", allExpandFlow, padding = 20.dp) {
        Image(
            painter = painterResource(id = R.drawable.image_hor),
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
fun ImageResourceSamplePreview() {
    ImageResourceSample(remember { MutableStateFlow(true) })
}


@Composable
fun ImageVectorSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem(title = "Image（Vector）", allExpandFlow, padding = 20.dp) {
        Image(
            imageVector = Icons.Filled.Call,
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
fun ImageVectorSamplePreview() {
    ImageVectorSample(remember { MutableStateFlow(true) })
}


@Composable
fun ImageBitmapSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    val imageBitmap = remember {
        (ResourcesCompat.getDrawable(
            context.resources,
            R.drawable.image_hor_small,
            null
        ) as BitmapDrawable).bitmap.asImageBitmap()
    }
    ExpandableItem(title = "Image（Bitmap）", allExpandFlow, padding = 20.dp) {
        Image(
            bitmap = imageBitmap,
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
fun ImageBitmapSamplePreview() {
    ImageBitmapSample(remember { MutableStateFlow(true) })
}


@Composable
fun ImageAlignmentSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem(title = "Image（alignment）", allExpandFlow, padding = 20.dp) {
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
                    Image(
                        painter = painterResource(id = R.drawable.image_hor_small),
                        contentDescription = "",
                        modifier = Modifier
                            .fillMaxWidth(0.3f)
                            .aspectRatio(1f)
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
fun ImageAlignmentSamplePreview() {
    ImageAlignmentSample(remember { MutableStateFlow(true) })
}

data class ContentScaleItem(
    val contentScale: ContentScale,
    val name: String,
    val sampleResList: List<Pair<Int, String>>
)

@Composable
fun ImageContentScaleSample(allExpandFlow: Flow<Boolean>) {
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
    ExpandableItem(title = "Image（contentScale）", allExpandFlow, padding = 20.dp) {
        Column {
            items.forEachIndexed { index, items ->
                if (index != 0) {
                    Spacer(modifier = Modifier.size(10.dp))
                }
                Row {
                    Text(
                        text = items.name,
                        modifier = Modifier.size(80.dp),
                    )
                    Spacer(modifier = Modifier.size(10.dp))
                    FlowRow(mainAxisSpacing = 10.dp, crossAxisSpacing = 10.dp) {
                        items.sampleResList.forEach { res ->
                            Column {
                                Text(
                                    text = res.second,
                                    modifier = Modifier.align(Alignment.CenterHorizontally),
                                )
                                Image(
                                    painter = painterResource(id = res.first),
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
fun ImageContentScaleSamplePreview() {
    ImageContentScaleSample(remember { MutableStateFlow(true) })
}


@Composable
fun ImageAlphaSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem(title = "Image（alpha）", allExpandFlow, padding = 20.dp) {
        Image(
            painter = painterResource(id = R.drawable.image_hor),
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
fun ImageAlphaSamplePreview() {
    ImageAlphaSample(remember { MutableStateFlow(true) })
}


@Composable
fun ImageColorFilterSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem(title = "Image（colorFilter）", allExpandFlow, padding = 20.dp) {
        Image(
            painter = painterResource(id = R.drawable.image_hor),
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
fun ImageColorFilterSamplePreview() {
    ImageColorFilterSample(remember { MutableStateFlow(true) })
}


@Composable
fun ImageClipSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem(title = "Image（shape）", allExpandFlow, padding = 20.dp) {
        Row {
            Image(
                painter = painterResource(id = R.drawable.image_hor),
                contentDescription = "",
                modifier = Modifier
                    .size(160.dp)
                    .clip(RoundedCornerShape(20.dp)),
                contentScale = ContentScale.Crop,
            )

            Spacer(modifier = Modifier.size(10.dp))

            Image(
                painter = painterResource(id = R.drawable.image_hor),
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
fun ImageClipSamplePreview() {
    ImageClipSample(remember { MutableStateFlow(true) })
}