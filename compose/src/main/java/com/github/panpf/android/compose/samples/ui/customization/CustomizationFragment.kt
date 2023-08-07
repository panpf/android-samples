package com.github.panpf.android.compose.samples.ui.customization

import com.github.panpf.android.compose.samples.R
import com.github.panpf.android.compose.samples.ui.base.list.BaseLinkListFragment
import com.github.panpf.android.samples.model.Link

class CustomizationFragment : BaseLinkListFragment() {

    override fun getTitle(): String {
        return "Customization"
    }

    override fun buildLinkList(): List<Link> = listOf(
        Link("HorizontalGrid（Customization）", R.id.action_global_horizontalGridFragment),
        Link("VerticalGrid（Customization）", R.id.action_global_verticalGridFragment),
        Link("ZoomImage", R.id.action_global_ZoomImageFragment),
    )
}