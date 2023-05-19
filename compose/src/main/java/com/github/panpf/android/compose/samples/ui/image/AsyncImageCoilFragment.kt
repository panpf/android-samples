package com.github.panpf.android.compose.samples.ui.image

import android.content.Context
import android.net.Uri
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
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
import com.github.panpf.android.compose.samples.tools.name
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem3
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
import com.github.panpf.android.compose.samples.ui.base.PhotoItem
import com.github.panpf.android.compose.samples.ui.base.SquashedOval
import com.github.panpf.android.compose.samples.ui.base.SubtitleText
import com.github.panpf.android.compose.samples.ui.base.TitleRadioButton
import com.github.panpf.android.compose.samples.ui.base.blackWhiteColorFilter
import com.github.panpf.android.compose.samples.ui.base.horPhoto
import com.github.panpf.android.compose.samples.ui.base.httpPhotoUrl
import com.github.panpf.android.compose.samples.ui.base.inversionOfNegativeColorFilter
import com.github.panpf.android.compose.samples.ui.base.newColorFilterByContrastAndBrightness
import com.github.panpf.android.compose.samples.ui.base.rainbowColorsBrush
import com.github.panpf.android.compose.samples.ui.base.verPhoto
import com.github.panpf.android.compose.samples.ui.customization.grid.VerticalGrid
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class AsyncImageCoilFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "AsyncImage - Coil"
    }

    @Composable
    override fun DrawContent() {
        ExpandableLayout { allExpandFlow ->
            CoilAsyncImageSample(allExpandFlow)
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


@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun CoilAsyncImageSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem3(title = "CoilAsyncImage", allExpandFlow, padding = 20.dp) {
        VerticalGrid(
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Resource")
                AsyncImage(
                    model = ImageRequest.Builder(context)
                        .data(context.newCoilResourceUri(R.drawable.dog_hor))
                        .placeholder(R.drawable.im_placeholder)
                        .build(),
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .background(MaterialTheme.colorScheme.primaryContainer),
                )
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Asset")
                AsyncImage(
                    model = ImageRequest.Builder(context)
                        .data(newCoilAssetUri("dog.jpg"))
                        .placeholder(R.drawable.im_placeholder)
                        .build(),
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .background(MaterialTheme.colorScheme.primaryContainer),
                )
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Http")
                AsyncImage(
                    model = ImageRequest.Builder(context)
                        .data(httpPhotoUrl)
                        .placeholder(R.drawable.im_placeholder)
                        .build(),
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
private fun CoilAsyncImageSamplePreview() {
    CoilAsyncImageSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun CoilAsyncImageAlignmentSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    val horSmall = remember { PhotoItem(horPhoto, "横向图片 - 小", false) }
    val verSmall = remember { PhotoItem(verPhoto, "竖向图片 - 小", false) }
    val horBig = remember { PhotoItem(horPhoto, "横向图片 - 大", true) }
    val verBig = remember { PhotoItem(verPhoto, "竖向图片 - 大", true) }
    val horImageSelected = remember { mutableStateOf(true) }
    val smallImageSelected = remember { mutableStateOf(true) }
    val image by remember {
        derivedStateOf {
            if (horImageSelected.value) {
                if (smallImageSelected.value) horSmall else horBig
            } else {
                if (smallImageSelected.value) verSmall else verBig
            }
        }
    }
    val contentScaleMenuExpanded = remember { mutableStateOf(false) }
    val contentScaleState = remember { mutableStateOf(ContentScale.Inside) }
    val contentScales = remember {
        listOf(
            ContentScale.Fit,
            ContentScale.Crop,
            ContentScale.Inside,
            ContentScale.FillWidth,
            ContentScale.FillHeight,
            ContentScale.FillBounds,
            ContentScale.None,
        )
    }
    ExpandableItem3(title = "CoilAsyncImage（alignment）", allExpandFlow, padding = 20.dp) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "图片比例", modifier = Modifier.weight(1f))
            TitleRadioButton(
                selected = horImageSelected.value,
                title = "横图",
                onClick = {
                    horImageSelected.value = true
                },
            )
            Spacer(modifier = Modifier.size(20.dp))
            TitleRadioButton(
                selected = !horImageSelected.value,
                title = "竖图",
                onClick = {
                    horImageSelected.value = false
                },
            )
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "图片大小", modifier = Modifier.weight(1f))
            TitleRadioButton(
                selected = smallImageSelected.value,
                title = "小图",
                onClick = {
                    smallImageSelected.value = true
                },
            )
            Spacer(modifier = Modifier.size(20.dp))
            TitleRadioButton(
                selected = !smallImageSelected.value,
                title = "大图",
                onClick = {
                    smallImageSelected.value = false
                },
            )
        }
        Row {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .clickable {
                        contentScaleMenuExpanded.value = !contentScaleMenuExpanded.value
                    },
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(text = "ContentScale", Modifier.weight(1f))
                Spacer(modifier = Modifier.size(20.dp))
                Text(text = contentScaleState.value.name)
                Icon(
                    imageVector = if (contentScaleMenuExpanded.value) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                    contentDescription = ""
                )
            }
            DropdownMenu(
                expanded = contentScaleMenuExpanded.value,
                onDismissRequest = {
                    contentScaleMenuExpanded.value = !contentScaleMenuExpanded.value
                },
            ) {
                contentScales.forEachIndexed { index, contentScale ->
                    if (index > 0) {
                        Divider(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 14.dp)
                        )
                    }
                    DropdownMenuItem(onClick = {
                        contentScaleState.value = contentScale
                        contentScaleMenuExpanded.value = !contentScaleMenuExpanded.value
                    }) {
                        Text(text = contentScale.name)
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
                    val targetSize = image.calculateTargetSize(viewSizePx.toInt())
                    Column {
                        Text(
                            text = alignment.second,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                        AsyncImage(
                            model = ImageRequest.Builder(context)
                                .data(context.newCoilResourceUri(image.photo.resId))
                                .placeholder(R.drawable.im_placeholder)
                                .size(targetSize.width.toInt(), targetSize.height.toInt())
                                .precision(Precision.EXACT)
                                .build(),
                            contentDescription = "",
                            modifier = Modifier
                                .size(110.dp)
                                .background(MaterialTheme.colorScheme.primaryContainer)
                                .padding(2.dp),
                            contentScale = contentScaleState.value,
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
private fun CoilAsyncImageAlignmentSamplePreview() {
    CoilAsyncImageAlignmentSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun CoilAsyncImageContentScaleSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    val horSmall = remember { PhotoItem(horPhoto, "横向图片 - 小", false) }
    val verSmall = remember { PhotoItem(verPhoto, "竖向图片 - 小", false) }
    val horBig = remember { PhotoItem(horPhoto, "横向图片 - 大", true) }
    val verBig = remember { PhotoItem(verPhoto, "竖向图片 - 大", true) }
    val horImageSelected = remember { mutableStateOf(true) }
    val smallImageSelected = remember { mutableStateOf(true) }
    val image by remember {
        derivedStateOf {
            if (horImageSelected.value) {
                if (smallImageSelected.value) horSmall else horBig
            } else {
                if (smallImageSelected.value) verSmall else verBig
            }
        }
    }
    val alignmentMenuExpanded = remember { mutableStateOf(false) }
    val alignmentState = remember { mutableStateOf(Alignment.Center) }
    val alignmentList = remember {
        listOf(
            Alignment.TopStart,
            Alignment.TopCenter,
            Alignment.TopEnd,
            Alignment.CenterStart,
            Alignment.Center,
            Alignment.CenterEnd,
            Alignment.BottomStart,
            Alignment.BottomCenter,
            Alignment.BottomEnd,
        )
    }
    ExpandableItem3(title = "Image（contentScale）", allExpandFlow, padding = 20.dp) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "图片比例", modifier = Modifier.weight(1f))
            TitleRadioButton(
                selected = horImageSelected.value,
                title = "横图",
                onClick = {
                    horImageSelected.value = true
                },
            )
            Spacer(modifier = Modifier.size(20.dp))
            TitleRadioButton(
                selected = !horImageSelected.value,
                title = "竖图",
                onClick = {
                    horImageSelected.value = false
                },
            )
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "图片大小", modifier = Modifier.weight(1f))
            TitleRadioButton(
                selected = smallImageSelected.value,
                title = "小图",
                onClick = {
                    smallImageSelected.value = true
                },
            )
            Spacer(modifier = Modifier.size(20.dp))
            TitleRadioButton(
                selected = !smallImageSelected.value,
                title = "大图",
                onClick = {
                    smallImageSelected.value = false
                },
            )
        }
        Row {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .clickable {
                        alignmentMenuExpanded.value = !alignmentMenuExpanded.value
                    },
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(text = "Alignment", Modifier.weight(1f))
                Spacer(modifier = Modifier.size(20.dp))
                Text(text = alignmentState.value.name)
                Icon(
                    imageVector = if (alignmentMenuExpanded.value) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                    contentDescription = ""
                )
            }
            DropdownMenu(
                expanded = alignmentMenuExpanded.value,
                onDismissRequest = {
                    alignmentMenuExpanded.value = !alignmentMenuExpanded.value
                },
            ) {
                alignmentList.forEachIndexed { index, alignment ->
                    if (index > 0) {
                        Divider(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 14.dp)
                        )
                    }
                    DropdownMenuItem(onClick = {
                        alignmentState.value = alignment
                        alignmentMenuExpanded.value = !alignmentMenuExpanded.value
                    }) {
                        Text(text = alignment.name)
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
                ContentScale.None,
                ContentScale.Inside,
                ContentScale.Fit,
                ContentScale.FillWidth,
                ContentScale.FillHeight,
                ContentScale.FillBounds,
                ContentScale.Crop,
            ).forEach { contentScale ->
                BoxWithConstraints {
                    val viewSize = maxWidth
                    val viewSizePx = with(LocalDensity.current) { viewSize.toPx() }
                    val targetSize = image.calculateTargetSize(viewSizePx.toInt())
                    Column {
                        Text(
                            text = contentScale.name,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                        AsyncImage(
                            model = ImageRequest.Builder(context)
                                .data(context.newCoilResourceUri(image.photo.resId))
                                .placeholder(R.drawable.im_placeholder)
                                .size(targetSize.width.toInt(), targetSize.height.toInt())
                                .precision(Precision.EXACT)
                                .build(),
                            contentDescription = "",
                            modifier = Modifier
                                .size(viewSize)
                                .background(MaterialTheme.colorScheme.primaryContainer)
                                .padding(2.dp),
                            contentScale = contentScale,
                            alignment = alignmentState.value,
                        )
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


@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun CoilAsyncImageAlphaSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem3(title = "CoilAsyncImage（alpha）", allExpandFlow, padding = 20.dp) {
        VerticalGrid(
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "1f")
                AsyncImage(
                    model = ImageRequest.Builder(context)
                        .data(context.newCoilResourceUri(R.drawable.dog_hor))
                        .placeholder(R.drawable.im_placeholder)
                        .build(),
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f),
                    alpha = 1f
                )
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "0.7f")
                AsyncImage(
                    model = ImageRequest.Builder(context)
                        .data(context.newCoilResourceUri(R.drawable.dog_hor))
                        .placeholder(R.drawable.im_placeholder)
                        .build(),
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f),
                    alpha = 0.7f
                )
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "0.3f")
                AsyncImage(
                    model = ImageRequest.Builder(context)
                        .data(context.newCoilResourceUri(R.drawable.dog_hor))
                        .placeholder(R.drawable.im_placeholder)
                        .build(),
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
private fun CoilAsyncImageAlphaSamplePreview() {
    CoilAsyncImageAlphaSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun CoilAsyncImageClipSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem3(title = "CoilAsyncImage（clip）", allExpandFlow, padding = 20.dp) {
        VerticalGrid(
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                SubtitleText(text = "RoundedCorner", line = 2)
                AsyncImage(
                    model = ImageRequest.Builder(context)
                        .data(context.newCoilResourceUri(R.drawable.dog_hor))
                        .placeholder(R.drawable.im_placeholder)
                        .build(),
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
                AsyncImage(
                    model = ImageRequest.Builder(context)
                        .data(context.newCoilResourceUri(R.drawable.dog_hor))
                        .placeholder(R.drawable.im_placeholder)
                        .build(),
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
                AsyncImage(
                    model = ImageRequest.Builder(context)
                        .data(context.newCoilResourceUri(R.drawable.dog_hor))
                        .placeholder(R.drawable.im_placeholder)
                        .build(),
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
private fun CoilAsyncImageClipSamplePreview() {
    CoilAsyncImageClipSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun CoilAsyncImageBorderSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem3(title = "CoilAsyncImage（border）", allExpandFlow, padding = 20.dp) {
        VerticalGrid(
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                SubtitleText(text = "Default", line = 2)
                AsyncImage(
                    model = ImageRequest.Builder(context)
                        .data(context.newCoilResourceUri(R.drawable.dog_hor))
                        .placeholder(R.drawable.im_placeholder)
                        .build(),
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
                AsyncImage(
                    model = ImageRequest.Builder(context)
                        .data(context.newCoilResourceUri(R.drawable.dog_hor))
                        .placeholder(R.drawable.im_placeholder)
                        .build(),
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
                AsyncImage(
                    model = ImageRequest.Builder(context)
                        .data(context.newCoilResourceUri(R.drawable.dog_hor))
                        .placeholder(R.drawable.im_placeholder)
                        .build(),
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
private fun CoilAsyncImageBorderSamplePreview() {
    CoilAsyncImageBorderSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun CoilAsyncImageColorFilterSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem3(title = "CoilAsyncImage（colorFilter）", allExpandFlow, padding = 20.dp) {
        VerticalGrid(
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "黑白")
                AsyncImage(
                    model = ImageRequest.Builder(context)
                        .data(context.newCoilResourceUri(R.drawable.dog_hor))
                        .placeholder(R.drawable.im_placeholder)
                        .build(),
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f),
                    colorFilter = blackWhiteColorFilter
                )
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "反转负片")
                AsyncImage(
                    model = ImageRequest.Builder(context)
                        .data(context.newCoilResourceUri(R.drawable.dog_hor))
                        .placeholder(R.drawable.im_placeholder)
                        .build(),
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f),
                    colorFilter = inversionOfNegativeColorFilter
                )
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "亮度对比度")
                AsyncImage(
                    model = ImageRequest.Builder(context)
                        .data(context.newCoilResourceUri(R.drawable.dog_hor))
                        .placeholder(R.drawable.im_placeholder)
                        .build(),
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
private fun CoilAsyncImageColorFilterSamplePreview() {
    CoilAsyncImageColorFilterSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun CoilAsyncImageBlurSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem3(title = "CoilAsyncImage（blur）", allExpandFlow, padding = 20.dp) {
        Text(text = "仅支持 Android 12 以上版本")
        Spacer(modifier = Modifier.size(10.dp))
        VerticalGrid(
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
        ) {
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