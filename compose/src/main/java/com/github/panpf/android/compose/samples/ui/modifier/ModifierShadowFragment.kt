package com.github.panpf.android.compose.samples.ui.modifier

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.panpf.android.compose.samples.ui.base.DescItem
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem3
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
import com.google.accompanist.flowlayout.FlowRow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class ModifierShadowFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "Modifier - shadow"
    }

    @Composable
    override fun DrawContent() {
        ExpandableLayout { allExpandFlow ->
            ModifierShadowElevationSample(allExpandFlow)
            ModifierShadowShapeSample(allExpandFlow)
            ModifierShadowColorSample(allExpandFlow)
        }
    }
}


@Composable
private fun ModifierShadowElevationSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "Modifier（shadow - elevation）", allExpandFlow, padding = 20.dp) {
        val colorScheme = MaterialTheme.colorScheme
        FlowRow(mainAxisSpacing = 10.dp, crossAxisSpacing = 10.dp) {
            DescItem("no shadow") {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .background(colorScheme.primaryContainer)
                )
            }
            DescItem("6.dp") {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .shadow(elevation = 6.dp)
                        .background(colorScheme.primaryContainer)
                )
            }
            DescItem("12.dp") {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .shadow(elevation = 12.dp)
                        .background(colorScheme.primaryContainer)
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun ModifierShadowElevationSamplePreview() {
    ModifierShadowElevationSample(remember { MutableStateFlow(true) })
}


@Composable
private fun ModifierShadowShapeSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "Modifier（shadow - shape）", allExpandFlow, padding = 20.dp) {
        val colorScheme = MaterialTheme.colorScheme
        FlowRow(mainAxisSpacing = 10.dp, crossAxisSpacing = 10.dp) {
            DescItem("no shape") {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .shadow(elevation = 6.dp)
                        .background(colorScheme.primaryContainer)
                )
            }
            DescItem("RoundedCorne") {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .shadow(elevation = 6.dp, shape = RoundedCornerShape(10.dp))
                        .background(colorScheme.primaryContainer)
                )
            }
            DescItem("Circle") {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .shadow(elevation = 6.dp, shape = CircleShape)
                        .background(colorScheme.primaryContainer)
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun ModifierShadowShapeSamplePreview() {
    ModifierShadowShapeSample(remember { MutableStateFlow(true) })
}


@Composable
private fun ModifierShadowColorSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "Modifier（shadow - colors）", allExpandFlow, padding = 20.dp) {
        val colorScheme = MaterialTheme.colorScheme
        FlowRow(mainAxisSpacing = 10.dp, crossAxisSpacing = 10.dp) {
            DescItem("Default colors") {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .shadow(elevation = 6.dp)
                        .background(colorScheme.primaryContainer)
                )
            }
            DescItem("Custom colors") {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .shadow(elevation = 6.dp, ambientColor = Color.Green, spotColor = Color.Red)
                        .background(colorScheme.primaryContainer)
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun ModifierShadowColorSamplePreview() {
    ModifierShadowColorSample(remember { MutableStateFlow(true) })
}