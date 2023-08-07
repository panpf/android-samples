package com.github.panpf.android.compose.samples.ui

import com.github.panpf.android.compose.samples.R
import com.github.panpf.android.compose.samples.ui.base.list.BaseLinkListFragment
import com.github.panpf.android.samples.model.Link

class HomeFragment : BaseLinkListFragment() {

    override fun buildLinkList(): List<Link> = listOf(
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