package com.github.panpf.android.compose.samples.ui.modifier

import androidx.compose.runtime.Composable
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
import com.github.panpf.android.compose.samples.ui.gestures.ScrollHorizontalScrollSample

class ModifierHorizontalScrollFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "Modifier - horizontalScroll"
    }

    @Composable
    override fun DrawContent() {
        ExpandableLayout { allExpandFlow ->
            ScrollHorizontalScrollSample(allExpandFlow)
        }
    }
}