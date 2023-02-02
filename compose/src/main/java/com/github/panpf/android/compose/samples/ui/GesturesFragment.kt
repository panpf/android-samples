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
                Link("Drag", R.id.action_global_dragFragment),
                Link("Multitouch", R.id.action_global_multitouchFragment),
                Link("Scroll", R.id.action_global_scrollFragment),
                Link("Swip", R.id.action_global_swipFragment),
            )
        }
        LinkList(links) { _, link ->
            when (val nav = link.nav) {
                is Int -> findNavController().navigate(nav)
                is NavDirections -> findNavController().navigate(nav)
            }
        }
    }
}