package com.github.panpf.android.compose.samples.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.github.panpf.android.compose.samples.R
import com.github.panpf.android.compose.samples.model.Link
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
import com.github.panpf.android.compose.samples.ui.base.list.LinkList

class HomeFragment : Material3ComposeAppBarFragment() {

    @Composable
    override fun DrawContent() {
        val links = remember {
            listOf(
                Link("Components - Basic", R.id.action_global_basicComponentsFragment),
                Link("Components - Extension", R.id.action_global_extensionComponentsFragment),
                Link("Components - Material", R.id.action_global_materialComponentsFragment),
                Link("Components - Material3", R.id.action_global_material3ComponentsFragment),
                Link("Modifier", R.id.action_global_modifierFragment),
                Link("Animation（Implementation）"),   // todo Implementation
                Link("Theme（Implementation）"),   // todo Implementation
                Link("TouchEvent（Implementation）"),   // todo Implementation
                Link("Navigation（Implementation）"),   // todo Implementation
                Link("Custom（Implementation）"),   // todo Implementation
                Link("Cases（Implementation）"),  // todo Implementation banner
                Link("Other（Implementation）"),  // todo Implementation Placeholder, Drawable Painter
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