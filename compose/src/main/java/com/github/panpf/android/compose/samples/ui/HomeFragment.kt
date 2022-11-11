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

class HomeFragment : ComposeFragment() {

    override fun onCreateComposeView(context: Context): ComposeView {
        val links = listOf(
            Link("Basic Components", R.id.action_global_basicWidgetsFragment),
            Link("Extension Components", R.id.action_global_extensionComponentsFragment),
            Link("Material Components", R.id.action_global_materialComponentsFragment),
            Link("Material3 Components", R.id.action_global_material3ComponentsFragment),
            Link("Animation（Implementation）"),   // todo Implementation
            Link("Theme（Implementation）"),   // todo Implementation
            Link("TouchEvent（Implementation）"),   // todo Implementation
            Link("Navigation（Implementation）"),   // todo Implementation
            Link("Custom（Implementation）"),   // todo Implementation
            Link("Cases（Implementation）"),  // todo Implementation banner
            Link("Other（Implementation）"),  // todo Implementation Placeholder, Drawable Painter
        )
        return ComposeView(context).apply {
            setContent {
                MyTopAppBarScaffold3 {
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