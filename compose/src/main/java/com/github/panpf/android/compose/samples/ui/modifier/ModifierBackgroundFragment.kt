package com.github.panpf.android.compose.samples.ui.modifier

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.panpf.android.compose.samples.ui.base.DescItem
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem3
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
import com.github.panpf.android.compose.samples.ui.base.rainbowColorsBrush
import com.google.accompanist.flowlayout.FlowRow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class ModifierBackgroundFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "Modifier - background"
    }

    @Composable
    override fun DrawContent() {
        ExpandableLayout { allExpandFlow ->
            ModifierBackgroundColorSample(allExpandFlow)
            ModifierBackgroundBrushSample(allExpandFlow)
            ModifierBackgroundShapeSample(allExpandFlow)
        }
    }
}


@Composable
private fun ModifierBackgroundColorSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "Modifier（background - color）", allExpandFlow, padding = 20.dp) {
        val colorScheme = MaterialTheme.colorScheme
        FlowRow(mainAxisSpacing = 10.dp, crossAxisSpacing = 10.dp) {
            DescItem("no background") {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .border(1.dp, colorScheme.primary)
                )
            }

            DescItem("color - Primary") {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .background(colorScheme.primaryContainer)
                )
            }

            DescItem("color - Red") {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .background(Color.Red)
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun ModifierBackgroundColorSamplePreview() {
    ModifierBackgroundColorSample(remember { MutableStateFlow(true) })
}


@Composable
private fun ModifierBackgroundBrushSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "Modifier（background - brush）", allExpandFlow, padding = 20.dp) {
        val colorScheme = MaterialTheme.colorScheme
        FlowRow(mainAxisSpacing = 10.dp, crossAxisSpacing = 10.dp) {
            DescItem("color") {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .background(colorScheme.primaryContainer)
                )
            }

            DescItem("brush - Rainbow") {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .background(rainbowColorsBrush)
                )
            }

            DescItem("brush - Rainbow - alpha") {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .background(brush = rainbowColorsBrush, alpha = 0.5f)
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun ModifierBackgroundBrushSamplePreview() {
    ModifierBackgroundBrushSample(remember { MutableStateFlow(true) })
}


@Composable
private fun ModifierBackgroundShapeSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "Modifier（background - shape）", allExpandFlow, padding = 20.dp) {
        val colorScheme = MaterialTheme.colorScheme
        FlowRow(mainAxisSpacing = 10.dp, crossAxisSpacing = 10.dp) {
            DescItem("no shape\n") {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .background(colorScheme.primaryContainer)
                )
            }

            DescItem("shape - \nRoundedCorner") {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(colorScheme.primaryContainer)
                )
            }

            DescItem("shape - \nCircle") {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .clip(CircleShape)
                        .background(colorScheme.primaryContainer)
                )
            }

            DescItem("shape - \nOval") {
                Box(
                    modifier = Modifier
                        .size(60.dp, 30.dp)
                        .clip(RoundedCornerShape(50))
                        .background(colorScheme.primaryContainer)
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun ModifierBackgroundShapeSamplePreview() {
    ModifierBackgroundShapeSample(remember { MutableStateFlow(true) })
}