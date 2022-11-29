package com.github.panpf.android.compose.samples.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.github.panpf.android.compose.samples.R
import com.github.panpf.android.compose.samples.model.Link
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
import com.github.panpf.android.compose.samples.ui.base.list.LinkList

class LayoutFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "Layout"
    }

    @Composable
    override fun DrawContent() {
        val links = remember {
            listOf(
                Link("Box", R.id.action_global_boxFragment),
                Link("Column", R.id.action_global_columnFragment),
                Link("ConstraintLayout", R.id.action_global_constraintLayoutFragment),
                Link("FlowColumn", R.id.action_global_flowColumnFragment),
                Link("FlowRow", R.id.action_global_flowRowFragment),
                Link("Row", R.id.action_global_rowFragment),
                Link("Spacer", R.id.action_global_spacerFragment),
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