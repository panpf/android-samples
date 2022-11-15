package com.github.panpf.android.compose.samples.ui.modifier

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
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

class ModifierClipFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "Modifier - clip"
    }

    @Composable
    override fun DrawContent() {
        ExpandableLayout { allExpandFlow ->
            ModifierClipSample(allExpandFlow)
            ModifierClipToBoundsSample(allExpandFlow)
        }
    }
}


@Composable
private fun ModifierClipSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "Modifier（clip）", allExpandFlow, padding = 20.dp) {
        val colorScheme = MaterialTheme.colorScheme
        FlowRow(mainAxisSpacing = 10.dp, crossAxisSpacing = 10.dp) {
            DescItem("no clip\n") {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .background(colorScheme.primaryContainer)
                )
            }

            DescItem("clip - \nRoundedCorner") {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(colorScheme.primaryContainer)
                )
            }

            DescItem("clip - \nCircle") {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .clip(CircleShape)
                        .background(colorScheme.primaryContainer)
                )
            }

            DescItem("clip - \nOval") {
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
private fun ModifierClipSamplePreview() {
    ModifierClipSample(remember { MutableStateFlow(true) })
}


@Composable
private fun ModifierClipToBoundsSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "Modifier（clipToBounds）", allExpandFlow, padding = 20.dp) {
        val colorScheme = MaterialTheme.colorScheme
        FlowRow(mainAxisSpacing = 30.dp, crossAxisSpacing = 30.dp) {
            DescItem("parent - 60.dp") {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .background(colorScheme.primaryContainer)
                )
            }

            DescItem("child - 100.dp") {
                Box(
                    modifier = Modifier
                        .requiredSize(80.dp)
                        .alpha(0.5f)
                        .background(Color.Red)
                )
            }

            DescItem("no clip\n") {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .background(colorScheme.primaryContainer)
                ) {
                    Box(
                        modifier = Modifier
                            .requiredSize(80.dp)
                            .alpha(0.5f)
                            .background(Color.Red)
                    )
                }
            }

            DescItem("clipToBounds") {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .clipToBounds()
                        .background(colorScheme.primaryContainer)
                ) {
                    Box(
                        modifier = Modifier
                            .requiredSize(80.dp)
                            .alpha(0.5f)
                            .background(Color.Red)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun ModifierClipToBoundsSamplePreview() {
    ModifierClipToBoundsSample(remember { MutableStateFlow(true) })
}