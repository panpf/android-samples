package com.github.panpf.android.compose.samples.ui.graphics

import com.github.panpf.android.compose.samples.R
import com.github.panpf.android.compose.samples.ui.base.list.BaseLinkListFragment
import com.github.panpf.android.samples.model.Link

class GraphicsFragment : BaseLinkListFragment() {

    override fun getTitle(): String {
        return "Graphics"
    }

    override fun buildLinkList(): List<Link> = listOf(
        Link("DrawScope - draw*", R.id.action_global_drawScopeDrawFragment),
        Link("DrawScope - transform", R.id.action_global_drawScopeTransformFragment),
        Link("Paint", R.id.action_global_paintFragment),
    )
}