package com.github.panpf.android.compose.samples.ui.image

import android.graphics.drawable.BitmapDrawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.res.ResourcesCompat
import com.github.panpf.android.compose.samples.R
import com.github.panpf.android.compose.samples.ui.base.ContentScaleItem
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem3
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
import com.github.panpf.android.compose.samples.ui.base.PhotoItem
import com.github.panpf.android.compose.samples.ui.base.SquashedOval
import com.github.panpf.android.compose.samples.ui.base.blackWhiteColorFilter
import com.github.panpf.android.compose.samples.ui.base.horPhoto
import com.github.panpf.android.compose.samples.ui.base.inversionOfNegativeColorFilter
import com.github.panpf.android.compose.samples.ui.base.newColorFilterByContrastAndBrightness
import com.github.panpf.android.compose.samples.ui.base.rainbowColorsBrush
import com.github.panpf.android.compose.samples.ui.base.verPhoto
import com.google.accompanist.flowlayout.FlowRow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class ImageFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "Image"
    }

    @Composable
    override fun DrawContent() {
        ExpandableLayout { allExpandFlow ->
            ImageResourceSample(allExpandFlow)
            ImageVectorSample(allExpandFlow)
            ImageBitmapSample(allExpandFlow)
            ImageAlignmentSample(allExpandFlow)
            ImageContentScaleSample(allExpandFlow)
            ImageAlphaSample(allExpandFlow)
            ImageClipSample(allExpandFlow)
            ImageBorderSample(allExpandFlow)
            ImageColorFilterSample(allExpandFlow)
            ImageBlurSample(allExpandFlow)
        }
    }
}


@Composable
private fun ImageResourceSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "Image（Resource）", allExpandFlow, padding = 20.dp) {
        Image(
            painter = painterResource(id = R.drawable.dog_hor),
            contentDescription = "",
            modifier = Modifier.size(200.dp),
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun ImageResourceSamplePreview() {
    ImageResourceSample(remember { MutableStateFlow(true) })
}


@Composable
private fun ImageVectorSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "Image（Vector）", allExpandFlow, padding = 20.dp) {
        Image(
            imageVector = Icons.Filled.Call,
            contentDescription = "",
            modifier = Modifier.size(200.dp),
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun ImageVectorSamplePreview() {
    ImageVectorSample(remember { MutableStateFlow(true) })
}


@Composable
private fun ImageBitmapSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    val imageBitmap = remember {
        ResourcesCompat.getDrawable(context.resources, R.drawable.dog_hor, null)
            .let { it as BitmapDrawable }.bitmap.asImageBitmap()
    }
    ExpandableItem3(title = "Image（Bitmap）", allExpandFlow, padding = 20.dp) {
        Image(
            bitmap = imageBitmap,
            contentDescription = "",
            modifier = Modifier.size(200.dp),
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun ImageBitmapSamplePreview() {
    ImageBitmapSample(remember { MutableStateFlow(true) })
}


@Composable
private fun ImageAlignmentSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    val horSmall = remember {
        PhotoItem(horPhoto, "横向图片 - 小", false)
    }
    ExpandableItem3(title = "Image（alignment）", allExpandFlow, padding = 20.dp) {
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
                    val bitmap = horSmall.getBitmap(context, viewSizePx.toInt())
                    Image(
                        bitmap = bitmap.asImageBitmap(),
                        contentDescription = "",
                        modifier = Modifier
                            .size(viewSize)
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
private fun ImageAlignmentSamplePreview() {
    ImageAlignmentSample(remember { MutableStateFlow(true) })
}


@Composable
private fun ImageContentScaleSample(allExpandFlow: Flow<Boolean>) {
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
    ExpandableItem3(title = "Image（contentScale）", allExpandFlow, padding = 20.dp) {
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
                                val bitmap = photoItem.getBitmap(context, viewSizePx.toInt())
                                Image(
                                    bitmap = bitmap.asImageBitmap(),
                                    contentDescription = "",
                                    modifier = Modifier
                                        .size(viewSize)
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
private fun ImageContentScaleSamplePreview() {
    ImageContentScaleSample(remember { MutableStateFlow(true) })
}


@Composable
private fun ImageAlphaSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "Image（alpha）", allExpandFlow, padding = 20.dp) {
        Image(
            painter = painterResource(id = R.drawable.dog_hor),
            contentDescription = "",
            modifier = Modifier.size(200.dp),
            alpha = 0.5f
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun ImageAlphaSamplePreview() {
    ImageAlphaSample(remember { MutableStateFlow(true) })
}


@Composable
private fun ImageClipSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "Image（shape）", allExpandFlow, padding = 20.dp) {
        Row {
            Image(
                painter = painterResource(id = R.drawable.dog_hor),
                contentDescription = "",
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(20.dp)),
                contentScale = ContentScale.Crop,
            )

            Spacer(modifier = Modifier.size(10.dp))

            Image(
                painter = painterResource(id = R.drawable.dog_hor),
                contentDescription = "",
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop,
            )

            Spacer(modifier = Modifier.size(10.dp))

            Image(
                painter = painterResource(id = R.drawable.dog_hor),
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
private fun ImageClipSamplePreview() {
    ImageClipSample(remember { MutableStateFlow(true) })
}


@Composable
private fun ImageBorderSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "Image（border）", allExpandFlow, padding = 20.dp) {
        Row {
            Image(
                painter = painterResource(id = R.drawable.dog_hor),
                contentDescription = "",
                modifier = Modifier
                    .size(100.dp)
                    .border(2.dp, MaterialTheme.colorScheme.primary),
                contentScale = ContentScale.Crop,
            )

            Spacer(modifier = Modifier.size(10.dp))

            Image(
                painter = painterResource(id = R.drawable.dog_hor),
                contentDescription = "",
                modifier = Modifier
                    .size(100.dp)
                    .border(2.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(20.dp))
                    .clip(RoundedCornerShape(20.dp)),
                contentScale = ContentScale.Crop,
            )

            Spacer(modifier = Modifier.size(10.dp))

            Image(
                painter = painterResource(id = R.drawable.dog_hor),
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
private fun ImageBorderSamplePreview() {
    ImageBorderSample(remember { MutableStateFlow(true) })
}


@Composable
private fun ImageColorFilterSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "Image（colorFilter）", allExpandFlow, padding = 20.dp) {
        Row {
            Column {
                Text(text = "黑白", Modifier.align(Alignment.CenterHorizontally))
                Image(
                    painter = painterResource(id = R.drawable.dog_hor),
                    contentDescription = "",
                    modifier = Modifier.size(100.dp),
                    colorFilter = blackWhiteColorFilter
                )
            }
            Spacer(modifier = Modifier.size(10.dp))
            Column {
                Text(text = "反转负片", Modifier.align(Alignment.CenterHorizontally))
                Image(
                    painter = painterResource(id = R.drawable.dog_hor),
                    contentDescription = "",
                    modifier = Modifier.size(100.dp),
                    colorFilter = inversionOfNegativeColorFilter
                )
            }
            Spacer(modifier = Modifier.size(10.dp))
            Column {
                Text(text = "亮度对比度", Modifier.align(Alignment.CenterHorizontally))
                Image(
                    painter = painterResource(id = R.drawable.dog_hor),
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
private fun ImageColorFilterSamplePreview() {
    ImageColorFilterSample(remember { MutableStateFlow(true) })
}


@Composable
private fun ImageBlurSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "Image（blur）", allExpandFlow, padding = 20.dp) {
        Column {
            Text(text = "仅支持 Android 12 以上版本")
            Spacer(modifier = Modifier.size(10.dp))
            Row {
                Image(
                    painter = painterResource(id = R.drawable.dog_hor),
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
                Image(
                    painter = painterResource(id = R.drawable.dog_hor),
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
private fun ImageBlurSamplePreview() {
    ImageBlurSample(remember { MutableStateFlow(true) })
}