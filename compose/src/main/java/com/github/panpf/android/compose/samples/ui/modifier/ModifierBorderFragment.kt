package com.github.panpf.android.compose.samples.ui.modifier

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
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
import com.google.accompanist.flowlayout.FlowColumn
import com.google.accompanist.flowlayout.FlowRow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class ModifierBorderFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "Modifier - border"
    }

    @Composable
    override fun DrawContent() {
        ExpandableLayout { allExpandFlow ->
            ModifierBorderWidthSample(allExpandFlow)
            ModifierBorderColorSample(allExpandFlow)
            ModifierBorderBrushSample(allExpandFlow)
            ModifierBorderShapeSample(allExpandFlow)
        }
    }
}


@Composable
private fun ModifierBorderWidthSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "Modifier（border - width）", allExpandFlow, padding = 20.dp) {
        val colorScheme = MaterialTheme.colorScheme
        FlowRow(mainAxisSpacing = 10.dp, crossAxisSpacing = 10.dp) {
            DescItem("no border") {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .background(colorScheme.primaryContainer)
                )
            }

            DescItem("width - 1.dp") {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .background(colorScheme.primaryContainer)
                        .border(1.dp, colorScheme.primary)
                )
            }

            DescItem("width - 2.dp") {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .background(colorScheme.primaryContainer)
                        .border(2.dp, colorScheme.primary)
                )
            }

            DescItem("width - 4.dp") {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .background(colorScheme.primaryContainer)
                        .border(4.dp, colorScheme.primary)
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun ModifierBorderWidthSamplePreview() {
    ModifierBorderWidthSample(remember { MutableStateFlow(true) })
}


@Composable
private fun ModifierBorderColorSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "Modifier（border - color）", allExpandFlow, padding = 20.dp) {
        val colorScheme = MaterialTheme.colorScheme
        FlowRow(mainAxisSpacing = 10.dp, crossAxisSpacing = 10.dp) {
            DescItem("no border") {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .background(colorScheme.primaryContainer)
                )
            }

            DescItem("color - Primary") {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .background(colorScheme.primaryContainer)
                        .border(2.dp, colorScheme.primary)
                )
            }

            DescItem("color - Red") {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .background(colorScheme.primaryContainer)
                        .border(4.dp, Color.Red)
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun ModifierBorderColorSamplePreview() {
    ModifierBorderColorSample(remember { MutableStateFlow(true) })
}


@Composable
private fun ModifierBorderBrushSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "Modifier（border - brush）", allExpandFlow, padding = 20.dp) {
        val colorScheme = MaterialTheme.colorScheme
        FlowRow(mainAxisSpacing = 10.dp, crossAxisSpacing = 10.dp) {
            DescItem("no border") {
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
                        .background(colorScheme.primaryContainer)
                        .border(BorderStroke(2.dp, rainbowColorsBrush))
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun ModifierBorderBrushSamplePreview() {
    ModifierBorderBrushSample(remember { MutableStateFlow(true) })
}


@Composable
private fun ModifierBorderShapeSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "Modifier（border - shape）", allExpandFlow, padding = 20.dp) {
        val colorScheme = MaterialTheme.colorScheme
        FlowColumn(mainAxisSpacing = 10.dp, crossAxisSpacing = 10.dp) {
            DescItem("no border") {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .background(colorScheme.primaryContainer)
                )
            }

            DescItem("shape - Only border shape") {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .background(colorScheme.primaryContainer)
                        .border(BorderStroke(2.dp, rainbowColorsBrush), CircleShape)
                )
            }

            DescItem("shape - Border shape and clip background") {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .clip(CircleShape)
                        .background(colorScheme.primaryContainer)
                        .border(BorderStroke(2.dp, rainbowColorsBrush), CircleShape)
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun ModifierBorderShapeSamplePreview() {
    ModifierBorderShapeSample(remember { MutableStateFlow(true) })
}