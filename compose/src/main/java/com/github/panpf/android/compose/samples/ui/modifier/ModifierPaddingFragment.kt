package com.github.panpf.android.compose.samples.ui.modifier

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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

class ModifierPaddingFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "Modifier - padding"
    }

    @Composable
    override fun DrawContent() {
        ExpandableLayout { allExpandFlow ->
            ModifierPaddingSample(allExpandFlow)
            ModifierPaddingWithBackgroundSample(allExpandFlow)
            // todo absolutePadding 因为还没有找到修改 LayoutDirection 的方法
        }
    }
}


@Composable
private fun ModifierPaddingSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "Modifier（padding）", allExpandFlow, padding = 20.dp) {
        val colorScheme = MaterialTheme.colorScheme
        DescItem("no padding") {
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .background(colorScheme.primaryContainer)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(colorScheme.primary)
                )
            }
        }

        Spacer(modifier = Modifier.size(10.dp))
        DescItem("padding(all = 4.dp)") {
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .background(colorScheme.primaryContainer)
                    .padding(all = 4.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(colorScheme.primary)
                )
            }
        }

        Spacer(modifier = Modifier.size(10.dp))
        DescItem("padding(horizontal = 4.dp, vertical = 8.dp)") {
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .background(colorScheme.primaryContainer)
                    .padding(horizontal = 4.dp, vertical = 8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(colorScheme.primary)
                )
            }
        }

        Spacer(modifier = Modifier.size(10.dp))
        DescItem("padding(start = 2.dp, top = 4.dp, end = 6.dp, bottom = 8.dp)") {
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .background(colorScheme.primaryContainer)
                    .padding(start = 2.dp, top = 4.dp, end = 6.dp, bottom = 8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(colorScheme.primary)
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun ModifierPaddingSamplePreview() {
    ModifierPaddingSample(remember { MutableStateFlow(true) })
}


@Composable
private fun ModifierPaddingWithBackgroundSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "Modifier（withBackground）", allExpandFlow, padding = 20.dp) {
        Text(text = "padding 和 background 的顺序很重要")

        Spacer(modifier = Modifier.size(10.dp))
        val colorScheme = MaterialTheme.colorScheme
        FlowRow(
            mainAxisSpacing = 20.dp,
            crossAxisSpacing = 20.dp,
            mainAxisAlignment = MainAxisAlignment.SpaceBetween
        ) {
            DescItem("background -> padding") {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .border(1.dp, Color.Red)
                        .background(colorScheme.primaryContainer)
                        .padding(all = 8.dp)
                ) {
                    Text(text = "Compose")
                }
            }

            DescItem("padding -> background") {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .border(1.dp, Color.Red)
                        .padding(all = 8.dp)
                        .background(colorScheme.primaryContainer)
                ) {
                    Text(text = "Compose")
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun ModifierPaddingWithBackgroundSamplePreview() {
    ModifierPaddingWithBackgroundSample(remember { MutableStateFlow(true) })
}