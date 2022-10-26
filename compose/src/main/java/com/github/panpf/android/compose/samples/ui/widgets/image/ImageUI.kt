package com.github.panpf.android.compose.samples.ui.widgets.image

import android.graphics.drawable.BitmapDrawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
        ImageContentScaleSmallSample(allExpandFlow)
        ImageContentScaleLargeSample(allExpandFlow)
        ImageAlphaSample(allExpandFlow)
        ImageTintSample(allExpandFlow)
    }
}


@Composable
fun ImageResourceSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem(title = "Image（Resource）", allExpandFlow, padding = 20.dp) {
        Image(
            painter = painterResource(id = R.drawable.image2),
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
            R.drawable.image,
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
                        painter = painterResource(id = R.drawable.image),
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


@Composable
fun ImageContentScaleSmallSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem(title = "Image（contentScale - Small）", allExpandFlow, padding = 20.dp) {
        FlowRow(mainAxisSpacing = 10.dp, crossAxisSpacing = 10.dp) {
            listOf(
                ContentScale.None to "None",
                ContentScale.Inside to "Inside",
                ContentScale.Crop to "Crop",
                ContentScale.Fit to "Fit",
                ContentScale.FillBounds to "FillBounds",
                ContentScale.FillHeight to "FillHeight",
                ContentScale.FillWidth to "FillWidth",
            ).forEach { contentScale ->
                Column {
                    Text(
                        text = contentScale.second,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.image),
                        contentDescription = "",
                        modifier = Modifier
                            .fillMaxWidth(0.3f)
                            .aspectRatio(1f)
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
fun ImageContentScaleSmallSamplePreview() {
    ImageContentScaleSmallSample(remember { MutableStateFlow(true) })
}


@Composable
fun ImageContentScaleLargeSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem(title = "Image（contentScale - Large）", allExpandFlow, padding = 20.dp) {
        FlowRow(mainAxisSpacing = 10.dp, crossAxisSpacing = 10.dp) {
            listOf(
                ContentScale.None to "None",
                ContentScale.Inside to "Inside",
                ContentScale.Crop to "Crop",
                ContentScale.Fit to "Fit",
                ContentScale.FillBounds to "FillBounds",
                ContentScale.FillHeight to "FillHeight",
                ContentScale.FillWidth to "FillWidth",
            ).forEach { contentScale ->
                Column {
                    Text(
                        text = contentScale.second,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.image2),
                        contentDescription = "",
                        modifier = Modifier
                            .fillMaxWidth(0.3f)
                            .aspectRatio(1f)
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
fun ImageContentScaleLargeSamplePreview() {
    ImageContentScaleLargeSample(remember { MutableStateFlow(true) })
}


@Composable
fun ImageAlphaSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem(title = "Image（alpha）", allExpandFlow, padding = 20.dp) {
        Image(
            painter = painterResource(id = R.drawable.image2),
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
fun ImageTintSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem(title = "Image（tint）", allExpandFlow, padding = 20.dp) {
        Image(
            painter = painterResource(id = R.drawable.image2),
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
fun ImageTintSamplePreview() {
    ImageTintSample(remember { MutableStateFlow(true) })
}