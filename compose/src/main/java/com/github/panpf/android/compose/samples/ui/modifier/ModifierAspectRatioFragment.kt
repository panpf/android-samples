package com.github.panpf.android.compose.samples.ui.modifier

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.coerceAtMost
import androidx.compose.ui.unit.dp
import com.github.panpf.android.compose.samples.ui.base.DescItem
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem3
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
import com.google.accompanist.flowlayout.FlowRow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class ModifierAspectRatioFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "Modifier - aspectRatio"
    }

    @Composable
    override fun DrawContent() {
        ExpandableLayout { allExpandFlow ->
            ModifierAspectRatioSample(allExpandFlow)
        }
    }
}


@Composable
private fun ModifierAspectRatioSample(allExpandFlow: Flow<Boolean>) {
    val state = remember { mutableStateOf(60.dp) }
    ExpandableItem3(title = "Modifier（aspectRatio）", allExpandFlow, padding = 20.dp) {
        val colorScheme = MaterialTheme.colorScheme
        Text(text = "宽固定，高按比例生成")
        FlowRow(mainAxisSpacing = 10.dp, crossAxisSpacing = 10.dp) {
            DescItem("aspectRatio - 1f") {
                Box(
                    modifier = Modifier
                        .width(state.value)
                        .aspectRatio(1f)
                        .background(colorScheme.primaryContainer)
                )
            }

            DescItem("aspectRatio - 1.5f") {
                Box(
                    modifier = Modifier
                        .width(state.value)
                        .aspectRatio(1.5f)
                        .background(colorScheme.primaryContainer)
                )
            }

            DescItem("aspectRatio - 0.5f") {
                Box(
                    modifier = Modifier
                        .width(state.value)
                        .aspectRatio(0.5f)
                        .background(colorScheme.primaryContainer)
                )
            }
        }

        Spacer(modifier = Modifier.size(20.dp))
        Text(text = "高固定，宽按比例生成")
        FlowRow(mainAxisSpacing = 10.dp, crossAxisSpacing = 10.dp) {
            DescItem("aspectRatio - 1f") {
                Box(
                    modifier = Modifier
                        .height(state.value)
                        .aspectRatio(1f)
                        .background(colorScheme.primaryContainer)
                )
            }

            DescItem("aspectRatio - 1.5f") {
                Box(
                    modifier = Modifier
                        .height(state.value)
                        .aspectRatio(1.5f)
                        .background(colorScheme.primaryContainer)
                )
            }

            DescItem("aspectRatio - 0.5f") {
                Box(
                    modifier = Modifier
                        .height(state.value)
                        .aspectRatio(0.5f)
                        .background(colorScheme.primaryContainer)
                )
            }
        }


        Spacer(modifier = Modifier.size(20.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            Button(
                onClick = {
                    state.value = (state.value - 10.dp).coerceAtLeast(20.dp)
                },
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "-10.dp")
            }

            Spacer(modifier = Modifier.size(20.dp))

            Button(
                onClick = {
                    state.value = (state.value + 10.dp).coerceAtMost(100.dp)
                },
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "+10.dp")
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun ModifierAspectRatioSamplePreview() {
    ModifierAspectRatioSample(remember { MutableStateFlow(true) })
}