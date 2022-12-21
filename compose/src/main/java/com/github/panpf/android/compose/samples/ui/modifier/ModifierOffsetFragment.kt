package com.github.panpf.android.compose.samples.ui.modifier

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.github.panpf.android.compose.samples.ui.base.DescItem
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem3
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
import com.google.accompanist.flowlayout.FlowRow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class ModifierOffsetFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "Modifier - offset"
    }

    @Composable
    override fun DrawContent() {
        ExpandableLayout { allExpandFlow ->
            ModifierOffsetSample(allExpandFlow)
            ModifierAbsoluteOffsetSample(allExpandFlow)
        }
    }
}


@Composable
private fun ModifierOffsetSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "Modifier（offset）", allExpandFlow, padding = 20.dp) {
        val colorScheme = MaterialTheme.colorScheme
        FlowRow(mainAxisSpacing = 30.dp, crossAxisSpacing = 30.dp) {
            DescItem("LTR\nno offset") {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .border(2.dp, colorScheme.primary)
                ) {
                    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
                        Box(
                            modifier = Modifier
                                .size(60.dp)
                                .background(colorScheme.primaryContainer)
                        )
                    }
                }
            }

            DescItem("RTL\nno offset") {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .border(2.dp, colorScheme.primary)
                ) {
                    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                        Box(
                            modifier = Modifier
                                .size(60.dp)
                                .background(colorScheme.primaryContainer)
                        )
                    }
                }
            }

            DescItem("LTR\nx=10.dp, y=10.dp") {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .border(2.dp, colorScheme.primary)
                ) {
                    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
                        Box(
                            modifier = Modifier
                                .size(60.dp)
                                .offset(x = 10.dp, y = 10.dp)
                                .background(colorScheme.primaryContainer)
                        )
                    }
                }
            }

            DescItem("RTL\nx=10.dp, y=10.dp") {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .border(2.dp, colorScheme.primary)
                ) {
                    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                        Box(
                            modifier = Modifier
                                .size(60.dp)
                                .offset(x = 10.dp, y = 10.dp)
                                .background(colorScheme.primaryContainer)
                        )
                    }
                }
            }

            DescItem("LTR\nx=-4.dp, y=-4.dp") {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .border(2.dp, colorScheme.primary)
                ) {
                    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
                        Box(
                            modifier = Modifier
                                .size(60.dp)
                                .offset(x = (-4).dp, y = (-4).dp)
                                .background(colorScheme.primaryContainer)
                        )
                    }
                }
            }

            DescItem("RTL\nx=-4.dp, y=-4.dp") {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .border(2.dp, colorScheme.primary)
                ) {
                    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                        Box(
                            modifier = Modifier
                                .size(60.dp)
                                .offset(x = (-4).dp, y = (-4).dp)
                                .background(colorScheme.primaryContainer)
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun ModifierOffsetSamplePreview() {
    ModifierOffsetSample(remember { MutableStateFlow(true) })
}


@Composable
private fun ModifierAbsoluteOffsetSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "Modifier（absoluteOffset）", allExpandFlow, padding = 20.dp) {
        val colorScheme = MaterialTheme.colorScheme
        FlowRow(mainAxisSpacing = 30.dp, crossAxisSpacing = 30.dp) {
            DescItem("LTR\nno absoluteOffset") {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .border(2.dp, colorScheme.primary)
                ) {
                    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
                        Box(
                            modifier = Modifier
                                .size(60.dp)
                                .background(colorScheme.primaryContainer)
                        )
                    }
                }
            }

            DescItem("RTL\nno absoluteOffset") {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .border(2.dp, colorScheme.primary)
                ) {
                    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                        Box(
                            modifier = Modifier
                                .size(60.dp)
                                .background(colorScheme.primaryContainer)
                        )
                    }
                }
            }

            DescItem("LTR\nx=10.dp, y=10.dp") {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .border(2.dp, colorScheme.primary)
                ) {
                    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
                        Box(
                            modifier = Modifier
                                .size(60.dp)
                                .absoluteOffset(x = 10.dp, y = 10.dp)
                                .background(colorScheme.primaryContainer)
                        )
                    }
                }
            }

            DescItem("RTL\nx=10.dp, y=10.dp") {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .border(2.dp, colorScheme.primary)
                ) {
                    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                        Box(
                            modifier = Modifier
                                .size(60.dp)
                                .absoluteOffset(x = 10.dp, y = 10.dp)
                                .background(colorScheme.primaryContainer)
                        )
                    }
                }
            }

            DescItem("LTR\nx=-4.dp, y=-4.dp") {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .border(2.dp, colorScheme.primary)
                ) {
                    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
                        Box(
                            modifier = Modifier
                                .size(60.dp)
                                .absoluteOffset(x = (-4).dp, y = (-4).dp)
                                .background(colorScheme.primaryContainer)
                        )
                    }
                }
            }

            DescItem("RTL\nx=-4.dp, y=-4.dp") {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .border(2.dp, colorScheme.primary)
                ) {
                    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                        Box(
                            modifier = Modifier
                                .size(60.dp)
                                .absoluteOffset(x = (-4).dp, y = (-4).dp)
                                .background(colorScheme.primaryContainer)
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun ModifierAbsoluteOffsetSamplePreview() {
    ModifierAbsoluteOffsetSample(remember { MutableStateFlow(true) })
}