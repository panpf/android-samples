package com.github.panpf.android.compose.samples.ui.pager

import com.github.panpf.android.compose.samples.R
import com.github.panpf.android.compose.samples.ui.base.list.BaseLinkListFragment
import com.github.panpf.android.samples.model.Link

class PagerFragment : BaseLinkListFragment() {

    override fun getTitle(): String {
        return "Pager"
    }

    override fun buildLinkList(): List<Link> = listOf(
        Link("HorizontalPager", R.id.action_global_horizontalPagerFragment),
        Link("VerticalPager", R.id.action_global_verticalPagerFragment),
    )
}