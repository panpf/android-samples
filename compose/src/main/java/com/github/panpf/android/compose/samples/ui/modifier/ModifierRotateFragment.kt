package com.github.panpf.android.compose.samples.ui.modifier

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.panpf.android.compose.samples.R
import com.github.panpf.android.compose.samples.ui.base.DescItem
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem3
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
import com.google.accompanist.flowlayout.FlowRow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class ModifierRotateFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "Modifier - rotate"
    }

    @Composable
    override fun DrawContent() {
        ExpandableLayout { allExpandFlow ->
            ModifierRotateSample(allExpandFlow)
        }
    }
}


@Composable
private fun ModifierRotateSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "Modifier（rotate）", allExpandFlow, padding = 20.dp) {
        val colorScheme = MaterialTheme.colorScheme
        FlowRow(mainAxisSpacing = 10.dp, crossAxisSpacing = 10.dp) {
            DescItem("no rotate") {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .border(2.dp, colorScheme.primary)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.dog_hor),
                        contentDescription = "image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(60.dp)
                            .align(Alignment.Center)
                    )
                }
            }
            DescItem("45°") {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .border(2.dp, colorScheme.primary)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.dog_hor),
                        contentDescription = "image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(60.dp)
                            .rotate(45f)
                            .align(Alignment.Center)
                    )
                }
            }
            DescItem("90°") {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .border(2.dp, colorScheme.primary)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.dog_hor),
                        contentDescription = "image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(60.dp)
                            .rotate(90f)
                            .align(Alignment.Center)
                    )
                }
            }
            DescItem("135°") {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .border(2.dp, colorScheme.primary)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.dog_hor),
                        contentDescription = "image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(60.dp)
                            .rotate(135f)
                            .align(Alignment.Center)
                    )
                }
            }
            DescItem("180°") {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .border(2.dp, colorScheme.primary)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.dog_hor),
                        contentDescription = "image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(60.dp)
                            .rotate(180f)
                            .align(Alignment.Center)
                    )
                }
            }
            DescItem("225°") {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .border(2.dp, colorScheme.primary)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.dog_hor),
                        contentDescription = "image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(60.dp)
                            .rotate(225f)
                            .align(Alignment.Center)
                    )
                }
            }
            DescItem("270°") {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .border(2.dp, colorScheme.primary)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.dog_hor),
                        contentDescription = "image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(60.dp)
                            .rotate(270f)
                            .align(Alignment.Center)
                    )
                }
            }
            DescItem("315°") {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .border(2.dp, colorScheme.primary)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.dog_hor),
                        contentDescription = "image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(60.dp)
                            .rotate(315f)
                            .align(Alignment.Center)
                    )
                }
            }
            DescItem("360°") {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .border(2.dp, colorScheme.primary)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.dog_hor),
                        contentDescription = "image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(60.dp)
                            .rotate(360f)
                            .align(Alignment.Center)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun ModifierRotateSamplePreview() {
    ModifierRotateSample(remember { MutableStateFlow(true) })
}