package com.github.panpf.android.compose.samples.ui.modifier

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
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

class ModifierBlurFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "Modifier - blur"
    }

    @Composable
    override fun DrawContent() {
        ExpandableLayout { allExpandFlow ->
            ModifierBlurRadiusSample(allExpandFlow)
            ModifierBlurEdgeTreatmentSample(allExpandFlow)
        }
    }
}


@Composable
private fun ModifierBlurRadiusSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "Modifier（blur - radius）", allExpandFlow, padding = 20.dp) {
        Text(text = "仅支持 Android 12 以上版本")
        Spacer(modifier = Modifier.size(10.dp))
        val colorScheme = MaterialTheme.colorScheme
        FlowRow(mainAxisSpacing = 10.dp, crossAxisSpacing = 10.dp) {
            DescItem("no blur") {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .background(colorScheme.primaryContainer)
                ) {
                    Text(
                        text = "Compose",
                        modifier = Modifier
                            .background(colorScheme.primary)
                            .align(Alignment.Center),
                        color = colorScheme.onPrimary
                    )
                }
            }

            DescItem("radius=10.dp") {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .background(colorScheme.primaryContainer)
                        .blur(radius = 10.dp)
                ) {
                    Text(
                        text = "Compose",
                        modifier = Modifier
                            .background(colorScheme.primary)
                            .align(Alignment.Center),
                        color = colorScheme.onPrimary
                    )
                }
            }

            DescItem("radius=20.dp") {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .background(colorScheme.primaryContainer)
                        .blur(radius = 20.dp)
                ) {
                    Text(
                        text = "Compose",
                        modifier = Modifier
                            .background(colorScheme.primary)
                            .align(Alignment.Center),
                        color = colorScheme.onPrimary
                    )
                }
            }

            DescItem("radiusX=10.dp, radiusY=20.dp") {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .background(colorScheme.primaryContainer)
                        .blur(radiusX = 10.dp, radiusY = 20.dp)
                ) {
                    Text(
                        text = "Compose",
                        modifier = Modifier
                            .background(colorScheme.primary)
                            .align(Alignment.Center),
                        color = colorScheme.onPrimary
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun ModifierBlurRadiusSamplePreview() {
    ModifierBlurRadiusSample(remember { MutableStateFlow(true) })
}


@Composable
private fun ModifierBlurEdgeTreatmentSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "Modifier（blur - edgeTreatment）", allExpandFlow, padding = 20.dp) {
        Text(text = "仅支持 Android 12 以上版本")
        Spacer(modifier = Modifier.size(10.dp))
        Text(text = "模糊的时候边缘部分会超出节点本来的边界，edgeTreatment 属性用来指定如何处理超出边界的部分，默认是 Rectangle")
        Spacer(modifier = Modifier.size(10.dp))
        FlowRow(mainAxisSpacing = 10.dp, crossAxisSpacing = 10.dp) {
            DescItem("no blur") {
                Image(
                    painter = painterResource(id = R.drawable.dog_hor),
                    contentDescription = "image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(100.dp)
                )
            }

            DescItem("Unbounded") {
                Image(
                    painter = painterResource(id = R.drawable.dog_hor),
                    contentDescription = "image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(100.dp)
                        .blur(
                            radius = 10.dp,
                            edgeTreatment = BlurredEdgeTreatment.Unbounded
                        )
                )
            }

            DescItem("Rectangle") {
                Image(
                    painter = painterResource(id = R.drawable.dog_hor),
                    contentDescription = "image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(100.dp)
                        .blur(
                            radius = 10.dp,
                            edgeTreatment = BlurredEdgeTreatment.Rectangle
                        )
                )
            }

            DescItem("RoundedCorner") {
                Image(
                    painter = painterResource(id = R.drawable.dog_hor),
                    contentDescription = "image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(100.dp)
                        .blur(
                            radius = 10.dp,
                            edgeTreatment = BlurredEdgeTreatment(RoundedCornerShape(10.dp))
                        )
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun ModifierBlurEdgeTreatmentSamplePreview() {
    ModifierBlurEdgeTreatmentSample(remember { MutableStateFlow(true) })
}