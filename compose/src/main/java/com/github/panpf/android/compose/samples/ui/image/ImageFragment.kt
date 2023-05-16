package com.github.panpf.android.compose.samples.ui.image

import android.graphics.drawable.BitmapDrawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.res.ResourcesCompat
import com.github.panpf.android.compose.samples.R
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem3
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
import com.github.panpf.android.compose.samples.ui.base.PhotoItem
import com.github.panpf.android.compose.samples.ui.base.SquashedOval
import com.github.panpf.android.compose.samples.ui.base.SubtitleText
import com.github.panpf.android.compose.samples.ui.base.blackWhiteColorFilter
import com.github.panpf.android.compose.samples.ui.base.horPhoto
import com.github.panpf.android.compose.samples.ui.base.inversionOfNegativeColorFilter
import com.github.panpf.android.compose.samples.ui.base.newColorFilterByContrastAndBrightness
import com.github.panpf.android.compose.samples.ui.base.rainbowColorsBrush
import com.github.panpf.android.compose.samples.ui.base.verPhoto
import com.github.panpf.android.compose.samples.ui.customization.grid.VerticalGrid
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class ImageFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "Image"
    }

    @Composable
    override fun DrawContent() {
        ExpandableLayout { allExpandFlow ->
            ImageSample(allExpandFlow)
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


@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ImageSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "Image", allExpandFlow, padding = 20.dp) {
        VerticalGrid(
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Resource")
                Image(
                    painter = painterResource(id = R.drawable.dog_hor),
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .background(MaterialTheme.colorScheme.primaryContainer),
                )
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Vector")
                Image(
                    imageVector = Icons.Filled.Call,
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .background(MaterialTheme.colorScheme.primaryContainer),
                )
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Bitmap")
                val context = LocalContext.current
                val imageBitmap = remember {
                    ResourcesCompat.getDrawable(context.resources, R.drawable.dog_ver, null)
                        .let { it as BitmapDrawable }.bitmap.asImageBitmap()
                }
                Image(
                    bitmap = imageBitmap,
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .background(MaterialTheme.colorScheme.primaryContainer),
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun ImageSamplePreview() {
    ImageSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ImageAlignmentSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    val horSmall = remember {
        PhotoItem(horPhoto, "横向图片 - 小", false)
    }
    val verSmall = remember {
        PhotoItem(verPhoto, "竖向图片 - 小", false)
    }
    val horImageSelected = remember { mutableStateOf(true) }
    val image = if (horImageSelected.value) horSmall else verSmall
    val contentScaleMenuExpanded = remember { mutableStateOf(false) }
    val selectedContentScaleIndex = remember { mutableStateOf(2) }
    val contentScales = remember {
        listOf(
            ContentScale.Fit to "Fit",
            ContentScale.Crop to "Crop",
            ContentScale.Inside to "Inside",
            ContentScale.FillWidth to "FillWidth",
            ContentScale.FillHeight to "FillHeight",
            ContentScale.FillBounds to "FillBounds",
            ContentScale.None to "None",
        )
    }
    val selectedContentScale = contentScales[selectedContentScaleIndex.value]
    ExpandableItem3(title = "Image（alignment）", allExpandFlow, padding = 20.dp) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Row(
                modifier = Modifier.clickable { horImageSelected.value = !horImageSelected.value },
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = horImageSelected.value,
                    onClick = { horImageSelected.value = !horImageSelected.value })
                Text(text = "横图")
            }
            Spacer(modifier = Modifier.size(20.dp))
            Row(
                modifier = Modifier.clickable { horImageSelected.value = !horImageSelected.value },
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = !horImageSelected.value,
                    onClick = { horImageSelected.value = !horImageSelected.value })
                Text(text = "竖图")
            }
            Spacer(modifier = Modifier.size(20.dp))
            Box(modifier = Modifier.weight(1f)) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            contentScaleMenuExpanded.value = !contentScaleMenuExpanded.value
                        },
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = "ContentScale\n${selectedContentScale.second}",
                        textAlign = TextAlign.End
                    )
                    val icon = if (contentScaleMenuExpanded.value) {
                        Icons.Filled.KeyboardArrowUp
                    } else {
                        Icons.Filled.KeyboardArrowDown
                    }
                    Icon(imageVector = icon, contentDescription = "")
                }
                DropdownMenu(
                    expanded = contentScaleMenuExpanded.value,
                    onDismissRequest = {
                        contentScaleMenuExpanded.value = !contentScaleMenuExpanded.value
                    },
                ) {
                    contentScales.forEachIndexed { index, pair ->
                        if (index > 0) {
                            Divider(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 14.dp)
                            )
                        }
                        DropdownMenuItem(onClick = {
                            selectedContentScaleIndex.value = index
                            contentScaleMenuExpanded.value = !contentScaleMenuExpanded.value
                        }) {
                            Text(text = pair.second)
                        }
                    }
                }
            }
        }
        Spacer(modifier = Modifier.size(10.dp))
        VerticalGrid(
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            listOf(
                Alignment.TopStart to "TopStart",
                Alignment.TopCenter to "TopCenter",
                Alignment.TopEnd to "TopEnd",
                Alignment.CenterStart to "CenterStart",
                Alignment.Center to "Center",
                Alignment.CenterEnd to "CenterEnd",
                Alignment.BottomStart to "Bottom\nStart",
                Alignment.BottomCenter to "Bottom\nCenter",
                Alignment.BottomEnd to "Bottom\nEnd",
            ).forEach { alignment ->
                BoxWithConstraints {
                    val viewSize = maxWidth
                    val viewSizePx = with(LocalDensity.current) { viewSize.toPx() }
                    val bitmap = image.getBitmap(context, viewSizePx.toInt())
                    Column {
                        Text(
                            text = alignment.second,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                        Image(
                            bitmap = bitmap.asImageBitmap(),
                            contentDescription = "",
                            modifier = Modifier
                                .size(viewSize)
                                .background(MaterialTheme.colorScheme.primaryContainer)
                                .padding(2.dp),
                            contentScale = selectedContentScale.first,
                            alignment = alignment.first,
                        )
                    }
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


@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ImageContentScaleSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    val scales = remember {
        listOf(
            ContentScale.Fit to "Fit",
            ContentScale.Crop to "Crop",
            ContentScale.Inside to "Inside",
            ContentScale.FillWidth to "FillWidth",
            ContentScale.FillHeight to "FillHeight",
            ContentScale.FillBounds to "FillBounds",
            ContentScale.None to "None",
        )
    }
    val images = remember {
        val horBig = PhotoItem(horPhoto, "横向图片 - 大", true)
        val horSmall = PhotoItem(horPhoto, "横向图片 - 小", false)
        val verBig = PhotoItem(verPhoto, "纵向图片 - 大", true)
        val verSmall = PhotoItem(verPhoto, "纵向图片 - 小", false)
        listOf(horBig, verBig, horSmall, verSmall)
    }
    ExpandableItem3(title = "Image（contentScale）", allExpandFlow, padding = 20.dp) {
        scales.forEachIndexed { index, scale ->
            if (index != 0) {
                Spacer(modifier = Modifier.size(20.dp))
            }
            Row(Modifier.fillMaxWidth()) {
                Text(
                    text = scale.second,
                    modifier = Modifier
                        .size(80.dp)
                        .padding(top = 18.dp),
                )
                Spacer(modifier = Modifier.size(10.dp))
                VerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    images.forEach { photoItem ->
                        BoxWithConstraints {
                            val viewSize = maxWidth
                            val viewSizePx = with(LocalDensity.current) { viewSize.toPx() }
                            val bitmap = photoItem.getBitmap(context, viewSizePx.toInt())
                            Column {
                                Text(
                                    text = photoItem.name,
                                    modifier = Modifier.align(Alignment.CenterHorizontally),
                                )
                                Image(
                                    bitmap = bitmap.asImageBitmap(),
                                    contentDescription = "",
                                    modifier = Modifier
                                        .size(viewSize)
                                        .background(MaterialTheme.colorScheme.primaryContainer)
                                        .padding(2.dp),
                                    contentScale = scale.first,
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


@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ImageAlphaSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "Image（alpha）", allExpandFlow, padding = 20.dp) {
        VerticalGrid(
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "1f")
                Image(
                    painter = painterResource(id = R.drawable.dog_hor),
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f),
                    alpha = 1f
                )
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "0.7f")
                Image(
                    painter = painterResource(id = R.drawable.dog_hor),
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f),
                    alpha = 0.7f
                )
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "0.3f")
                Image(
                    painter = painterResource(id = R.drawable.dog_hor),
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f),
                    alpha = 0.3f
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun ImageAlphaSamplePreview() {
    ImageAlphaSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ImageClipSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "Image（shape）", allExpandFlow, padding = 20.dp) {
        VerticalGrid(
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                SubtitleText(text = "RoundedCorner", line = 2)
                Image(
                    painter = painterResource(id = R.drawable.dog_hor),
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .clip(RoundedCornerShape(20.dp)),
                    contentScale = ContentScale.Crop,
                )
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                SubtitleText(text = "Circle", line = 2)
                Image(
                    painter = painterResource(id = R.drawable.dog_hor),
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop,
                )
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                SubtitleText(text = "SquashedOval", line = 2)
                Image(
                    painter = painterResource(id = R.drawable.dog_hor),
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .clip(SquashedOval()),
                    contentScale = ContentScale.Crop,
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun ImageClipSamplePreview() {
    ImageClipSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ImageBorderSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "Image（border）", allExpandFlow, padding = 20.dp) {
        VerticalGrid(
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                SubtitleText(text = "Default", line = 2)
                Image(
                    painter = painterResource(id = R.drawable.dog_hor),
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .border(2.dp, MaterialTheme.colorScheme.primary),
                    contentScale = ContentScale.Crop,
                )
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                SubtitleText(text = "RoundedCorner", line = 2)
                Image(
                    painter = painterResource(id = R.drawable.dog_hor),
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .border(2.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(20.dp))
                        .clip(RoundedCornerShape(20.dp)),
                    contentScale = ContentScale.Crop,
                )
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                SubtitleText(text = "Circle + brush", line = 2)
                Image(
                    painter = painterResource(id = R.drawable.dog_hor),
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .border(2.dp, rainbowColorsBrush, CircleShape)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop,
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun ImageBorderSamplePreview() {
    ImageBorderSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ImageColorFilterSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "Image（colorFilter）", allExpandFlow, padding = 20.dp) {
        VerticalGrid(
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "黑白")
                Image(
                    painter = painterResource(id = R.drawable.dog_hor),
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f),
                    colorFilter = blackWhiteColorFilter
                )
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "反转负片")
                Image(
                    painter = painterResource(id = R.drawable.dog_hor),
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f),
                    colorFilter = inversionOfNegativeColorFilter
                )
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "亮度对比度")
                Image(
                    painter = painterResource(id = R.drawable.dog_hor),
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f),
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


@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ImageBlurSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "Image（blur）", allExpandFlow, padding = 20.dp) {
        Text(text = "仅支持 Android 12 以上版本")
        Spacer(modifier = Modifier.size(10.dp))
        VerticalGrid(
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            Image(
                painter = painterResource(id = R.drawable.dog_hor),
                contentScale = ContentScale.Crop,
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .blur(
                        radius = 5.dp,
                        edgeTreatment = BlurredEdgeTreatment.Rectangle
                    ),
            )

            Image(
                painter = painterResource(id = R.drawable.dog_hor),
                contentScale = ContentScale.Crop,
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .blur(
                        radius = 5.dp,
                        edgeTreatment = BlurredEdgeTreatment(RoundedCornerShape(8.dp))
                    ),
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun ImageBlurSamplePreview() {
    ImageBlurSample(remember { MutableStateFlow(true) })
}