package com.github.panpf.android.compose.samples.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.github.panpf.android.compose.samples.R
import com.github.panpf.android.compose.samples.model.Link
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
import com.github.panpf.android.compose.samples.ui.base.list.LinkList

class GesturesFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "Gestures"
    }

    @Composable
    override fun DrawContent() {
        val links = remember {
            listOf(
                Link("Click", R.id.action_global_clickFragment),
                Link("Scroll", R.id.action_global_scrollFragment),
                // todo Link("Drag", R.id.action_global_scrollFragment),
                // todo Link("Swipe", R.id.action_global_scrollFragment),
                // todo Link("Transform", R.id.action_global_scrollFragment),
            )
        }
        LinkList(links) { _, link ->
            val nav = link.nav
            if (nav is Int) {
                findNavController().navigate(nav)
            } else if (nav is NavDirections) {
                findNavController().navigate(nav)
            }
        }
    }
}