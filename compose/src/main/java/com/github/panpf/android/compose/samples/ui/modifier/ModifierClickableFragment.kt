package com.github.panpf.android.compose.samples.ui.modifier

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.panpf.android.compose.samples.ui.base.DescItem
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem3
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
import com.github.panpf.tools4a.toast.ktx.showShortToast
import com.google.accompanist.flowlayout.FlowRow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class ModifierClickableFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "Modifier - clickable"
    }

    @Composable
    override fun DrawContent() {
        ExpandableLayout { allExpandFlow ->
            ModifierClickableSample(allExpandFlow)
            ModifierClickableCombinedSample(allExpandFlow)
        }
    }
}


@Composable
private fun ModifierClickableSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem3(title = "Modifier（clickable）", allExpandFlow, padding = 20.dp) {
        val colorScheme = MaterialTheme.colorScheme
        FlowRow(mainAxisSpacing = 10.dp, crossAxisSpacing = 10.dp) {
            DescItem("unclickable") {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .background(colorScheme.primaryContainer)
                )
            }

            DescItem("clickable") {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .background(colorScheme.primaryContainer)
                        .clickable {
                            context.showShortToast("You tapped me!")
                        }
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun ModifierClickableSamplePreview() {
    ModifierClickableSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ModifierClickableCombinedSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem3(title = "Modifier（combinedClickable）", allExpandFlow, padding = 20.dp) {
        val colorScheme = MaterialTheme.colorScheme
        FlowRow(mainAxisSpacing = 10.dp, crossAxisSpacing = 10.dp) {
            DescItem("single,double,long") {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .background(colorScheme.primaryContainer)
                        .combinedClickable(
                            onLongClick = {
                                context.showShortToast("You long tapped me!")
                            },
                            onDoubleClick = {
                                context.showShortToast("You double tapped me!")
                            },
                            onClick = {
                                context.showShortToast("You tapped me!")
                            },
                        )
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun ModifierClickableCombinedSamplePreview() {
    ModifierClickableCombinedSample(remember { MutableStateFlow(true) })
}