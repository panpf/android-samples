package com.github.panpf.android.compose.samples.ui.modifier

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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
            // todo absoluteOffset 因为还没有找到修改 LayoutDirection 的方法
        }
    }
}


@Composable
private fun ModifierOffsetSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "Modifier（offset）", allExpandFlow, padding = 20.dp) {
        val colorScheme = MaterialTheme.colorScheme
        FlowRow(mainAxisSpacing = 10.dp, crossAxisSpacing = 10.dp) {
            DescItem("no offset") {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .border(2.dp, colorScheme.primary)
                ) {
                    Box(
                        modifier = Modifier
                            .size(60.dp)
                            .background(colorScheme.primaryContainer)
                    )
                }
            }

            DescItem("x=20.dp, y=30.dp") {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .border(2.dp, colorScheme.primary)
                ) {
                    Box(
                        modifier = Modifier
                            .size(60.dp)
                            .offset(x = 20.dp, y = 30.dp)
                            .background(colorScheme.primaryContainer)
                    )
                }
            }

            DescItem("x=-4.dp, y=-4.dp") {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .border(2.dp, colorScheme.primary)
                ) {
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

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun ModifierOffsetSamplePreview() {
    ModifierOffsetSample(remember { MutableStateFlow(true) })
}