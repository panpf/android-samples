package com.github.panpf.android.compose.samples.ui

import android.content.Context
import androidx.compose.ui.platform.ComposeView
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.github.panpf.android.compose.samples.R
import com.github.panpf.android.compose.samples.model.Link
import com.github.panpf.android.compose.samples.ui.base.ComposeFragment
import com.github.panpf.android.compose.samples.ui.base.MyTopAppBarScaffold3
import com.github.panpf.android.compose.samples.ui.base.list.LinkList

class ExtensionComponentsFragment : ComposeFragment() {

    override fun onCreateComposeView(context: Context): ComposeView {
        val links = listOf(
            Link("AsyncImage - Coil", R.id.action_global_asyncImageCoilFragment),
            Link("AsyncImage - Sketch", R.id.action_global_asyncImageSketchFragment),
            Link("FlowColumn", R.id.action_global_flowColumnFragment),
            Link("FlowRow", R.id.action_global_flowRowFragment),
            Link("HorizontalPager", R.id.action_global_horizontalPagerFragment),
            Link("SwipeRefreshPaging", R.id.action_global_swipeRefreshPagingFragment),
            Link("VerticalPager", R.id.action_global_verticalPagerFragment),
            Link("WebView", R.id.action_global_webViewFragment),
        )
        return ComposeView(context).apply {
            setContent {
                MyTopAppBarScaffold3("Extension Components") {
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
        }
    }
}