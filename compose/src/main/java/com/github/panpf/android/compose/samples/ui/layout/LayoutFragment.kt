package com.github.panpf.android.compose.samples.ui.layout

import com.github.panpf.android.compose.samples.R
import com.github.panpf.android.compose.samples.ui.base.list.BaseLinkListFragment
import com.github.panpf.android.samples.model.Link

class LayoutFragment : BaseLinkListFragment() {

    override fun getTitle(): String {
        return "Layout"
    }

    override fun buildLinkList(): List<Link> = listOf(
        Link("Box", R.id.action_global_boxFragment),
        Link("Column", R.id.action_global_columnFragment),
        Link("ConstraintLayout", R.id.action_global_constraintLayoutFragment),
        Link("FlowColumn", R.id.action_global_flowColumnFragment),
        Link("FlowRow", R.id.action_global_flowRowFragment),
        Link("HorizontalGrid（Customization）", R.id.action_global_horizontalGridFragment),
        Link("Row", R.id.action_global_rowFragment),
        Link("Spacer", R.id.action_global_spacerFragment),
        Link("VerticalGrid（Customization）", R.id.action_global_verticalGridFragment),
    )
}