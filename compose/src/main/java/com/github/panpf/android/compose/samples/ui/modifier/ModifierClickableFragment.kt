package com.github.panpf.android.compose.samples.ui.modifier

import androidx.compose.runtime.Composable
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
import com.github.panpf.android.compose.samples.ui.gestures.ClickClickableSample
import com.github.panpf.android.compose.samples.ui.gestures.ClickCombinedClickableSample

class ModifierClickableFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "Modifier - clickable"
    }

    @Composable
    override fun DrawContent() {
        ExpandableLayout { allExpandFlow ->
            ClickClickableSample(allExpandFlow)
            ClickCombinedClickableSample(allExpandFlow)
        }
    }
}