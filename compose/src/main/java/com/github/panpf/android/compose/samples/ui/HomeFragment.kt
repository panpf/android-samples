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
                Link("Accompanist", R.id.action_global_accompanistFragment),
                Link("Animations", R.id.action_global_animationsFragment),
                Link("Customization", R.id.action_global_customizationFragment),
                Link("Gestures", R.id.action_global_gesturesFragment),
                Link("Graphics", R.id.action_global_graphicsFragment),
                Link("Images", R.id.action_global_imagesFragment),
                Link("Layout", R.id.action_global_layoutFragment),
                Link("Lazy", R.id.action_global_lazyFragment),
                Link("Material", R.id.action_global_materialFragment),
                Link("Material3", R.id.action_global_material3Fragment),
                Link("Modifiers", R.id.action_global_modifierFragment),
                Link("Pager", R.id.action_global_pagerFragment),
                Link("Navigation（Implementation）"),   // todo Implementation
                Link("Theme（Implementation）"),   // todo Implementation
//                Link("Cases（Implementation）"),  // todo Implementation banner
//                Link("Other（Implementation）"),  // todo Implementation Placeholder, Drawable Painter
                Link("Window", R.id.action_global_windowFragment),
            )
        }
        LinkList(links) { _, link ->
            when (val nav = link.nav) {
                is Int -> findNavController().navigate(nav)
                is NavDirections -> findNavController().navigate(nav)
            }
        }
    }
}