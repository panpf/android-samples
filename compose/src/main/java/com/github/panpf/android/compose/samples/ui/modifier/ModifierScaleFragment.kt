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
import androidx.compose.ui.draw.scale
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

class ModifierScaleFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "Modifier - scale"
    }

    @Composable
    override fun DrawContent() {
        ExpandableLayout { allExpandFlow ->
            ModifierScaleSample(allExpandFlow)
        }
    }
}


@Composable
private fun ModifierScaleSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "Modifier（scale）", allExpandFlow, padding = 20.dp) {
        val colorScheme = MaterialTheme.colorScheme
        FlowRow(mainAxisSpacing = 10.dp, crossAxisSpacing = 10.dp) {
            DescItem("no scale") {
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
            DescItem("scale=1.5f") {
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
                            .scale(1.5f)
                            .align(Alignment.Center)
                    )
                }
            }
            DescItem("scale=0.5f") {
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
                            .scale(0.5f)
                            .align(Alignment.Center)
                    )
                }
            }
            DescItem("scaleX=1.5f\nscaleY=1f") {
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
                            .scale(scaleX = 1.5f, scaleY = 1f)
                            .align(Alignment.Center)
                    )
                }
            }
            DescItem("scaleX=1f\nscaleY=1.5f") {
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
                            .scale(scaleX = 1f, scaleY = 1.5f)
                            .align(Alignment.Center)
                    )
                }
            }
            DescItem("scaleX=0.5f\nscaleY=1.5f") {
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
                            .scale(scaleX = 0.5f, scaleY = 1.5f)
                            .align(Alignment.Center)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun ModifierScaleSamplePreview() {
    ModifierScaleSample(remember { MutableStateFlow(true) })
}