package com.github.panpf.android.compose.samples.ui.widgets.asyncimage

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
import com.github.panpf.sketch.compose.AsyncImage
import com.github.panpf.sketch.fetch.newAssetUri
import com.github.panpf.sketch.fetch.newResourceUri
import com.google.accompanist.flowlayout.FlowRow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun AsyncImageUI() {
    ExpandableLayout { allExpandFlow ->
        AsyncImageResourceSample(allExpandFlow)
        AsyncImageAssetSample(allExpandFlow)
        AsyncImageHttpSample(allExpandFlow)
        AsyncImageAlignmentSample(allExpandFlow)
        AsyncImageContentScaleSmallSample(allExpandFlow)
        AsyncImageContentScaleLargeSample(allExpandFlow)
        AsyncImageAlphaSample(allExpandFlow)
        AsyncImageColorFilterSample(allExpandFlow)
        AsyncImageClipSample(allExpandFlow)
    }
}


@Composable
fun AsyncImageResourceSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem(title = "AsyncImage（Resource）", allExpandFlow, padding = 20.dp) {
        AsyncImage(
            imageUri = newResourceUri(R.drawable.image),
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
fun AsyncImageResourceSamplePreview() {
    AsyncImageResourceSample(remember { MutableStateFlow(true) })
}


@Composable
fun AsyncImageAssetSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem(title = "AsyncImage（Asset）", allExpandFlow, padding = 20.dp) {
        AsyncImage(
            imageUri = newAssetUri("image.jpg"),
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
fun AsyncImageAssetSamplePreview() {
    AsyncImageAssetSample(remember { MutableStateFlow(true) })
}


@Composable
fun AsyncImageHttpSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem(title = "AsyncImage（Http）", allExpandFlow, padding = 20.dp) {
        AsyncImage(
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
fun AsyncImageHttpSamplePreview() {
    AsyncImageHttpSample(remember { MutableStateFlow(true) })
}


@Composable
fun AsyncImageAlignmentSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem(title = "AsyncImage（alignment）", allExpandFlow, padding = 20.dp) {
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
                    AsyncImage(
                        imageUri = newResourceUri(R.drawable.image2),
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
fun AsyncImageAlignmentSamplePreview() {
    AsyncImageAlignmentSample(remember { MutableStateFlow(true) })
}


@Composable
fun AsyncImageContentScaleSmallSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem(title = "AsyncImage（contentScale - Small）", allExpandFlow, padding = 20.dp) {
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
                    AsyncImage(
                        imageUri = newResourceUri(R.drawable.image2),
                        contentDescription = "",
                        modifier = Modifier
                            .fillMaxWidth(0.315f)
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
fun AsyncImageContentScaleSmallSamplePreview() {
    AsyncImageContentScaleSmallSample(remember { MutableStateFlow(true) })
}


@Composable
fun AsyncImageContentScaleLargeSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem(title = "AsyncImage（contentScale - Large）", allExpandFlow, padding = 20.dp) {
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
                    AsyncImage(
                        imageUri = newResourceUri(R.drawable.image),
                        contentDescription = "",
                        modifier = Modifier
                            .fillMaxWidth(0.315f)
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
fun AsyncImageContentScaleLargeSamplePreview() {
    AsyncImageContentScaleLargeSample(remember { MutableStateFlow(true) })
}


@Composable
fun AsyncImageAlphaSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem(title = "AsyncImage（alpha）", allExpandFlow, padding = 20.dp) {
        AsyncImage(
            imageUri = newResourceUri(R.drawable.image),
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
fun AsyncImageAlphaSamplePreview() {
    AsyncImageAlphaSample(remember { MutableStateFlow(true) })
}


@Composable
fun AsyncImageColorFilterSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem(title = "AsyncImage（colorFilter）", allExpandFlow, padding = 20.dp) {
        AsyncImage(
            imageUri = newResourceUri(R.drawable.image),
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
fun AsyncImageColorFilterSamplePreview() {
    AsyncImageColorFilterSample(remember { MutableStateFlow(true) })
}


@Composable
fun AsyncImageClipSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem(title = "AsyncImage（shape）", allExpandFlow, padding = 20.dp) {
        Row {
            AsyncImage(
                imageUri = newResourceUri(R.drawable.image),
                contentDescription = "",
                modifier = Modifier
                    .size(160.dp)
                    .clip(RoundedCornerShape(20.dp)),
                contentScale = ContentScale.Crop,
            )

            Spacer(modifier = Modifier.size(10.dp))

            AsyncImage(
                imageUri = newResourceUri(R.drawable.image),
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
fun AsyncAsyncImageClipSamplePreview() {
    AsyncImageClipSample(remember { MutableStateFlow(true) })
}