package com.github.panpf.android.compose.samples.ui.modifier

import androidx.compose.runtime.Composable
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
import com.github.panpf.android.compose.samples.ui.gestures.ScrollVerticalScrollSample

class ModifierVerticalScrollFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "Modifier - verticalScroll"
    }

    @Composable
    override fun DrawContent() {
        ExpandableLayout { allExpandFlow ->
            ScrollVerticalScrollSample(allExpandFlow)
        }
    }
}