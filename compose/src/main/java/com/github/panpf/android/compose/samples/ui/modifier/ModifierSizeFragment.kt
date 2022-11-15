package com.github.panpf.android.compose.samples.ui.modifier

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredHeightIn
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredSizeIn
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.requiredWidthIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.panpf.android.compose.samples.ui.base.DescItem
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem3
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.MainAxisAlignment
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class ModifierSizeFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "Modifier - size"
    }

    @Composable
    override fun DrawContent() {
        ExpandableLayout { allExpandFlow ->
            ModifierSizeSample(allExpandFlow)
            ModifierSizeWrapSample(allExpandFlow)
            ModifierSizeFillSample(allExpandFlow)
            ModifierSizeDefaultMinSizeSample(allExpandFlow)
            ModifierSizeInSample(allExpandFlow)
            ModifierSizeRequireSizeSample(allExpandFlow)
            ModifierSizeRequireSizeInSample(allExpandFlow)
        }
    }
}


@Composable
private fun ModifierSizeSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "Modifier（size）", allExpandFlow, padding = 20.dp) {
        val colorScheme = MaterialTheme.colorScheme
        FlowRow(
            mainAxisSpacing = 20.dp,
            crossAxisSpacing = 20.dp,
            mainAxisAlignment = MainAxisAlignment.SpaceBetween
        ) {
            DescItem("width(100.dp)") {
                Text(
                    modifier = Modifier
                        .width(100.dp)
                        .background(colorScheme.primaryContainer),
                    text = "Compose"
                )
            }

            DescItem("height(100.dp)") {
                Text(
                    modifier = Modifier
                        .height(100.dp)
                        .background(colorScheme.primaryContainer),
                    text = "Compose"
                )
            }

            DescItem("size(100.dp)") {
                Text(
                    modifier = Modifier
                        .size(100.dp)
                        .background(colorScheme.primaryContainer),
                    text = "Compose"
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun ModifierSizeSamplePreview() {
    ModifierSizeSample(remember { MutableStateFlow(true) })
}


@Composable
private fun ModifierSizeWrapSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "Modifier（wrap）", allExpandFlow, padding = 20.dp) {
        val colorScheme = MaterialTheme.colorScheme
        DescItem("wrapContentWidth, height(30.dp)") {
            Text(
                modifier = Modifier
                    .wrapContentWidth()
                    .height(40.dp)
                    .background(colorScheme.primaryContainer),
                text = "ComposeCompose"
            )
            Spacer(modifier = Modifier.size(4.dp))
            Text(
                modifier = Modifier
                    .wrapContentWidth()
                    .height(40.dp)
                    .background(colorScheme.primaryContainer),
                text = "ComposeComposeComposeComposeComposeComposeComposeComposeComposeCompose"
            )
        }

        Spacer(modifier = Modifier.size(10.dp))
        DescItem("width(200.dp), wrapContentHeight") {
            Text(
                modifier = Modifier
                    .width(200.dp)
                    .wrapContentHeight()
                    .background(colorScheme.primaryContainer),
                text = "ComposeCompose"
            )
            Spacer(modifier = Modifier.size(4.dp))
            Text(
                modifier = Modifier
                    .width(200.dp)
                    .wrapContentHeight()
                    .background(colorScheme.primaryContainer),
                text = "ComposeComposeComposeComposeComposeComposeComposeComposeComposeCompose"
            )
        }

        Spacer(modifier = Modifier.size(10.dp))
        DescItem("wrapContentHeight") {
            Text(
                modifier = Modifier
                    .wrapContentSize()
                    .background(colorScheme.primaryContainer),
                text = "ComposeCompose"
            )
            Spacer(modifier = Modifier.size(4.dp))
            Text(
                modifier = Modifier
                    .wrapContentSize()
                    .background(colorScheme.primaryContainer),
                text = "ComposeComposeComposeComposeComposeComposeComposeComposeComposeCompose"
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun ModifierSizeWrapSamplePreview() {
    ModifierSizeWrapSample(remember { MutableStateFlow(true) })
}


@Composable
private fun ModifierSizeFillSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "Modifier（fill）", allExpandFlow, padding = 20.dp) {
        val colorScheme = MaterialTheme.colorScheme
        FlowRow(
            mainAxisSpacing = 20.dp,
            crossAxisSpacing = 20.dp,
            mainAxisAlignment = MainAxisAlignment.SpaceBetween
        ) {
            DescItem("wrap") {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .background(colorScheme.primaryContainer)
                        .padding(2.dp)
                ) {
                    Text(
                        modifier = Modifier
                            .wrapContentSize()
                            .background(colorScheme.primary),
                        text = "COM",
                        color = Color.White
                    )
                }
            }

            DescItem("fillMaxWidth") {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .background(colorScheme.primaryContainer)
                        .padding(2.dp)
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(colorScheme.primary),
                        text = "COM",
                        color = Color.White
                    )
                }
            }

            DescItem("fillMaxHeight") {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .background(colorScheme.primaryContainer)
                        .padding(2.dp)
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxHeight()
                            .background(colorScheme.primary),
                        text = "COM",
                        color = Color.White
                    )
                }
            }

            DescItem("fillMaxSize") {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .background(colorScheme.primaryContainer)
                        .padding(2.dp)
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(colorScheme.primary),
                        text = "COM",
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun ModifierSizeFillSamplePreview() {
    ModifierSizeFillSample(remember { MutableStateFlow(true) })
}


@Composable
private fun ModifierSizeDefaultMinSizeSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "Modifier（defaultMinSize）", allExpandFlow, padding = 20.dp) {
        val colorScheme = MaterialTheme.colorScheme
        DescItem("default") {
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .background(colorScheme.primaryContainer)
                    .padding(2.dp)
            ) {
                Text(
                    modifier = Modifier
                        .background(colorScheme.primary),
                    text = "COM",
                    color = Color.White
                )
            }
        }

        Spacer(modifier = Modifier.size(10.dp))
        DescItem("defaultMinSize(minWidth = 50.dp)") {
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .background(colorScheme.primaryContainer)
                    .padding(2.dp)
            ) {
                Text(
                    modifier = Modifier
                        .defaultMinSize(minWidth = 50.dp)
                        .background(colorScheme.primary),
                    text = "COM",
                    color = Color.White
                )
            }
        }

        Spacer(modifier = Modifier.size(10.dp))
        DescItem("defaultMinSize(minHeight = 50.dp)") {
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .background(colorScheme.primaryContainer)
                    .padding(2.dp)
            ) {
                Text(
                    modifier = Modifier
                        .defaultMinSize(minHeight = 50.dp)
                        .background(colorScheme.primary),
                    text = "COM",
                    color = Color.White
                )
            }
        }

        Spacer(modifier = Modifier.size(10.dp))
        DescItem("defaultMinSize(minWidth = 50.dp, minHeight = 50.dp)") {
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .background(colorScheme.primaryContainer)
                    .padding(2.dp)
            ) {
                Text(
                    modifier = Modifier
                        .defaultMinSize(minWidth = 50.dp, minHeight = 50.dp)
                        .background(colorScheme.primary),
                    text = "COM",
                    color = Color.White
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun ModifierSizeDefaultMinSizeSamplePreview() {
    ModifierSizeDefaultMinSizeSample(remember { MutableStateFlow(true) })
}


@Composable
private fun ModifierSizeInSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "Modifier（sizeIn）", allExpandFlow, padding = 20.dp) {
        Text(text = "sizeIn 限制节点的最小最大尺寸")

        val colorScheme = MaterialTheme.colorScheme
        Spacer(modifier = Modifier.size(10.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            DescItem("wrap", modifier = Modifier.weight(1f)) {
                Box(
                    modifier = Modifier
                        .background(colorScheme.primaryContainer)
                        .padding(2.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(10.dp, 20.dp)
                            .background(colorScheme.primary)
                    )
                }
                Spacer(modifier = Modifier.size(10.dp))
                Box(
                    modifier = Modifier
                        .background(colorScheme.primaryContainer)
                        .padding(2.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(80.dp, 20.dp)
                            .background(colorScheme.primary)
                    )
                }
            }
            DescItem("widthIn(20.dp, 60.dp)", modifier = Modifier.weight(1f)) {
                Box(
                    modifier = Modifier
                        .widthIn(20.dp, 60.dp)
                        .background(colorScheme.primaryContainer)
                        .padding(2.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(10.dp, 20.dp)
                            .background(colorScheme.primary)
                    )
                }
                Spacer(modifier = Modifier.size(10.dp))
                Box(
                    modifier = Modifier
                        .widthIn(20.dp, 60.dp)
                        .background(colorScheme.primaryContainer)
                        .padding(2.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(80.dp, 20.dp)
                            .background(colorScheme.primary)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.size(20.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            DescItem("wrap", modifier = Modifier.weight(1f)) {
                Row {
                    Box(
                        modifier = Modifier
                            .background(colorScheme.primaryContainer)
                            .padding(2.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(20.dp, 10.dp)
                                .background(colorScheme.primary)
                        )
                    }
                    Spacer(modifier = Modifier.size(10.dp))
                    Box(
                        modifier = Modifier
                            .background(colorScheme.primaryContainer)
                            .padding(2.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(20.dp, 80.dp)
                                .background(colorScheme.primary)
                        )
                    }
                }
            }
            DescItem("heightIn(20.dp, 60.dp)", modifier = Modifier.weight(1f)) {
                Row {
                    Box(
                        modifier = Modifier
                            .heightIn(20.dp, 60.dp)
                            .background(colorScheme.primaryContainer)
                            .padding(2.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(20.dp, 10.dp)
                                .background(colorScheme.primary)
                        )
                    }
                    Spacer(modifier = Modifier.size(10.dp))
                    Box(
                        modifier = Modifier
                            .heightIn(20.dp, 60.dp)
                            .background(colorScheme.primaryContainer)
                            .padding(2.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(20.dp, 80.dp)
                                .background(colorScheme.primary)
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.size(20.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            DescItem("wrap\n", modifier = Modifier.weight(1f)) {
                Box(
                    modifier = Modifier
                        .background(colorScheme.primaryContainer)
                        .padding(2.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(10.dp, 10.dp)
                            .background(colorScheme.primary)
                    )
                }
                Spacer(modifier = Modifier.size(10.dp))
                Box(
                    modifier = Modifier
                        .background(colorScheme.primaryContainer)
                        .padding(2.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(80.dp, 80.dp)
                            .background(colorScheme.primary)
                    )
                }
            }
            DescItem("sizeIn(20.dp, 20.dp, 60.dp, 60.dp)", modifier = Modifier.weight(1f)) {
                Box(
                    modifier = Modifier
                        .sizeIn(20.dp, 20.dp, 60.dp, 60.dp)
                        .background(colorScheme.primaryContainer)
                        .padding(2.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(10.dp, 10.dp)
                            .background(colorScheme.primary)
                    )
                }
                Spacer(modifier = Modifier.size(10.dp))
                Box(
                    modifier = Modifier
                        .sizeIn(20.dp, 20.dp, 60.dp, 60.dp)
                        .background(colorScheme.primaryContainer)
                        .padding(2.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(80.dp, 80.dp)
                            .background(colorScheme.primary)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun ModifierSizeInSamplePreview() {
    ModifierSizeInSample(remember { MutableStateFlow(true) })
}


@Composable
private fun ModifierSizeRequireSizeSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "Modifier（requireSize）", allExpandFlow, padding = 20.dp) {
        Text(text = "requireSize 允许子节点尺寸超过父节点并依然显示超出的部分")
        Text(text = "如下所示父节点尺寸 60.dp，子节点尺寸 80.dp 使用不同函数时的效果")
        Spacer(modifier = Modifier.size(10.dp))
        val colorScheme = MaterialTheme.colorScheme
        FlowRow(
            mainAxisSpacing = 20.dp,
            crossAxisSpacing = 20.dp,
            mainAxisAlignment = MainAxisAlignment.SpaceBetween
        ) {
            DescItem("size(80.dp)") {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .background(colorScheme.primaryContainer)
                        .padding(2.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(80.dp)
                            .background(colorScheme.primary.copy(alpha = 0.7f))
                    )
                }
            }

            DescItem("requiredSize(80.dp)") {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .background(colorScheme.primaryContainer)
                        .padding(2.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .requiredSize(80.dp)
                            .background(colorScheme.primary.copy(alpha = 0.7f))
                    )
                }
            }

            DescItem("requiredWidth(80.dp)") {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .background(colorScheme.primaryContainer)
                        .padding(2.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(80.dp)
                            .requiredWidth(80.dp)
                            .background(colorScheme.primary.copy(alpha = 0.7f))
                    )
                }
            }

            DescItem("requiredHeight(80.dp)") {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .background(colorScheme.primaryContainer)
                        .padding(2.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(80.dp)
                            .requiredHeight(80.dp)
                            .background(colorScheme.primary.copy(alpha = 0.7f))
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun ModifierSizeRequireSizeSamplePreview() {
    ModifierSizeRequireSizeSample(remember { MutableStateFlow(true) })
}


@Composable
private fun ModifierSizeRequireSizeInSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "Modifier（requireSizeIn）", allExpandFlow, padding = 20.dp) {
        Text(text = "如下所示父节点尺寸 60.dp，子节点设置 requiredSizeIn(40.dp, 40.dp, 80.dp, 80.dp) 时不同 size 的效果")
        Spacer(modifier = Modifier.size(10.dp))
        val colorScheme = MaterialTheme.colorScheme
        FlowRow(
            mainAxisSpacing = 20.dp,
            crossAxisSpacing = 20.dp,
            mainAxisAlignment = MainAxisAlignment.SpaceBetween
        ) {
            DescItem("size(20.dp)") {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .background(colorScheme.primaryContainer)
                        .padding(2.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(20.dp)
                            .requiredSizeIn(40.dp, 40.dp, 80.dp, 80.dp)
                            .background(colorScheme.primary.copy(alpha = 0.7f))
                    )
                }
            }

            DescItem("size(40.dp)") {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .background(colorScheme.primaryContainer)
                        .padding(2.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .requiredSizeIn(40.dp, 40.dp, 80.dp, 80.dp)
                            .background(colorScheme.primary.copy(alpha = 0.7f))
                    )
                }
            }

            DescItem("size(60.dp)") {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .background(colorScheme.primaryContainer)
                        .padding(2.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(60.dp)
                            .requiredSizeIn(40.dp, 40.dp, 80.dp, 80.dp)
                            .background(colorScheme.primary.copy(alpha = 0.7f))
                    )
                }
            }

            DescItem("size(80.dp)") {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .background(colorScheme.primaryContainer)
                        .padding(2.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(80.dp)
                            .requiredSizeIn(40.dp, 40.dp, 80.dp, 80.dp)
                            .background(colorScheme.primary.copy(alpha = 0.7f))
                    )
                }
            }

            DescItem("size(100.dp)") {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .background(colorScheme.primaryContainer)
                        .padding(2.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(100.dp)
                            .requiredSizeIn(40.dp, 40.dp, 80.dp, 80.dp)
                            .background(colorScheme.primary.copy(alpha = 0.7f))
                    )
                }
            }
        }

        Spacer(modifier = Modifier.size(40.dp))
        Text(text = "如下所示父节点尺寸 60.dp，子节点设置 requiredWidthIn(40.dp, 80.dp) 时不同 size 的效果")
        Spacer(modifier = Modifier.size(10.dp))
        FlowRow(
            mainAxisSpacing = 20.dp,
            crossAxisSpacing = 20.dp,
            mainAxisAlignment = MainAxisAlignment.SpaceBetween
        ) {

            DescItem("size(20.dp)") {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .background(colorScheme.primaryContainer)
                        .padding(2.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(20.dp)
                            .requiredWidthIn(40.dp, 80.dp)
                            .background(colorScheme.primary.copy(alpha = 0.7f))
                    )
                }
            }

            DescItem("size(40.dp)") {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .background(colorScheme.primaryContainer)
                        .padding(2.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .requiredWidthIn(40.dp, 80.dp)
                            .background(colorScheme.primary.copy(alpha = 0.7f))
                    )
                }
            }

            DescItem("size(60.dp)") {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .background(colorScheme.primaryContainer)
                        .padding(2.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(60.dp)
                            .requiredWidthIn(40.dp, 80.dp)
                            .background(colorScheme.primary.copy(alpha = 0.7f))
                    )
                }
            }

            DescItem("size(80.dp)") {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .background(colorScheme.primaryContainer)
                        .padding(2.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(80.dp)
                            .requiredWidthIn(40.dp, 80.dp)
                            .background(colorScheme.primary.copy(alpha = 0.7f))
                    )
                }
            }

            DescItem("size(100.dp)") {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .background(colorScheme.primaryContainer)
                        .padding(2.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(100.dp)
                            .requiredWidthIn(40.dp, 80.dp)
                            .background(colorScheme.primary.copy(alpha = 0.7f))
                    )
                }
            }
        }

        Spacer(modifier = Modifier.size(40.dp))
        Text(text = "如下所示父节点尺寸 60.dp，子节点设置 requiredHeightIn(40.dp, 80.dp) 时不同 size 的效果")
        Spacer(modifier = Modifier.size(10.dp))
        FlowRow(
            mainAxisSpacing = 20.dp,
            crossAxisSpacing = 20.dp,
            mainAxisAlignment = MainAxisAlignment.SpaceBetween
        ) {
            DescItem("size(20.dp)") {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .background(colorScheme.primaryContainer)
                        .padding(2.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(20.dp)
                            .requiredHeightIn(40.dp, 80.dp)
                            .background(colorScheme.primary.copy(alpha = 0.7f))
                    )
                }
            }

            DescItem("size(40.dp)") {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .background(colorScheme.primaryContainer)
                        .padding(2.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .requiredHeightIn(40.dp, 80.dp)
                            .background(colorScheme.primary.copy(alpha = 0.7f))
                    )
                }
            }

            DescItem("size(60.dp)") {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .background(colorScheme.primaryContainer)
                        .padding(2.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(60.dp)
                            .requiredHeightIn(40.dp, 80.dp)
                            .background(colorScheme.primary.copy(alpha = 0.7f))
                    )
                }
            }

            DescItem("size(80.dp)") {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .background(colorScheme.primaryContainer)
                        .padding(2.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(80.dp)
                            .requiredHeightIn(40.dp, 80.dp)
                            .background(colorScheme.primary.copy(alpha = 0.7f))
                    )
                }
            }

            DescItem("size(100.dp)") {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .background(colorScheme.primaryContainer)
                        .padding(2.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(100.dp)
                            .requiredHeightIn(40.dp, 80.dp)
                            .background(colorScheme.primary.copy(alpha = 0.7f))
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun ModifierSizeRequireSizeInSamplePreview() {
    ModifierSizeRequireSizeInSample(remember { MutableStateFlow(true) })
}