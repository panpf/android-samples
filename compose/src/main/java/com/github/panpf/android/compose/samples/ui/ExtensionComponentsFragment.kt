package com.github.panpf.android.compose.samples.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.github.panpf.android.compose.samples.R
import com.github.panpf.android.compose.samples.model.Link
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
import com.github.panpf.android.compose.samples.ui.base.list.LinkList

class ExtensionComponentsFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "Extension Components"
    }

    @Composable
    override fun DrawContent() {
        val links = remember {
            listOf(
                Link("AsyncImage - Coil", R.id.action_global_asyncImageCoilFragment),
                Link("AsyncImage - Sketch", R.id.action_global_asyncImageSketchFragment),
                Link("FlowColumn", R.id.action_global_flowColumnFragment),
                Link("FlowRow", R.id.action_global_flowRowFragment),
                Link("HorizontalPager", R.id.action_global_horizontalPagerFragment),
                Link("SwipeRefreshPaging", R.id.action_global_swipeRefreshPagingFragment),
                Link("VerticalPager", R.id.action_global_verticalPagerFragment),
                Link("WebView", R.id.action_global_webViewFragment),
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