package com.github.panpf.android.compose.samples.ui.modifier

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.panpf.android.compose.samples.ui.base.DescItem
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem3
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
import com.google.accompanist.flowlayout.FlowRow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class ModifierAlphaFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "Modifier - alpha"
    }

    @Composable
    override fun DrawContent() {
        ExpandableLayout { allExpandFlow ->
            ModifierAlphaSample(allExpandFlow)
        }
    }
}


@Composable
private fun ModifierAlphaSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "Modifier（alpha）", allExpandFlow, padding = 20.dp) {
        val colorScheme = MaterialTheme.colorScheme
        FlowRow(mainAxisSpacing = 10.dp, crossAxisSpacing = 10.dp) {
            DescItem("no alpha") {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .background(colorScheme.primary)
                )
            }

            DescItem("alpha - 0.7f") {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .alpha(0.5f)
                        .background(colorScheme.primary)
                )
            }

            DescItem("alpha - 0.3f") {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .alpha(0.3f)
                        .background(colorScheme.primary)
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun ModifierAlphaSamplePreview() {
    ModifierAlphaSample(remember { MutableStateFlow(true) })
}