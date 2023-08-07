package com.github.panpf.android.compose.samples.ui.window

import com.github.panpf.android.compose.samples.R
import com.github.panpf.android.compose.samples.ui.base.list.BaseLinkListFragment
import com.github.panpf.android.samples.model.Link

class WindowFragment : BaseLinkListFragment() {

    override fun getTitle(): String {
        return "Window"
    }

    override fun buildLinkList(): List<Link> = listOf(
        Link("Dialog", R.id.action_global_dialogFragment),
        Link("Popup", R.id.action_global_popupFragment),
    )
}