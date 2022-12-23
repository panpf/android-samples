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
                Link("Layout", R.id.action_global_layoutFragment),
                Link("List Grid", R.id.action_global_listFragment),
                Link("Images", R.id.action_global_imagesFragment),
                Link("Material", R.id.action_global_materialFragment),
                Link("Material3", R.id.action_global_material3Fragment),
                Link("Accompanist", R.id.action_global_accompanistFragment),
                Link("Window", R.id.action_global_windowFragment),
                Link("Modifier", R.id.action_global_modifierFragment),
                Link("Animation", R.id.action_global_animationFragment),
                Link("Theme（Implementation）"),   // todo Implementation
                Link("Gestures（Implementation）"),   // todo Implementation
                Link("Navigation（Implementation）"),   // todo Implementation
                Link("Custom（Implementation）"),   // todo Implementation
                Link("Draw（Implementation）"),   // todo Implementation
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