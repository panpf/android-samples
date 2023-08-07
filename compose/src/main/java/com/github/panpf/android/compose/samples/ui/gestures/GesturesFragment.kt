package com.github.panpf.android.compose.samples.ui.gestures

import com.github.panpf.android.compose.samples.R
import com.github.panpf.android.compose.samples.ui.base.list.BaseLinkListFragment
import com.github.panpf.android.samples.model.Link

class GesturesFragment : BaseLinkListFragment() {

    override fun getTitle(): String {
        return "Gestures"
    }

    override fun buildLinkList(): List<Link> = listOf(
        Link("Click", R.id.action_global_clickFragment),
        Link("Drag", R.id.action_global_dragFragment),
        Link("Multitouch", R.id.action_global_multitouchFragment),
        Link("Scroll", R.id.action_global_scrollFragment),
        Link(
            "Scroll - NestedScroll - Interop - View",
            R.id.action_global_scrollNestedScrollInteropWithViewFragment
        ),
        Link(
            "Scroll - NestedScroll - Interop - Compose",
            R.id.action_global_scrollNestedScrollInteropWithComposeFragment
        ),
        Link("Swip", R.id.action_global_swipFragment),
    )
}