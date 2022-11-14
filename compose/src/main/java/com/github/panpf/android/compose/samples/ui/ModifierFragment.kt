package com.github.panpf.android.compose.samples.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.github.panpf.android.compose.samples.R
import com.github.panpf.android.compose.samples.model.Link
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
import com.github.panpf.android.compose.samples.ui.base.list.LinkList

class ModifierFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "Modifier"
    }

    @Composable
    override fun DrawContent() {
        val links = remember {
            listOf(
                Link("size", R.id.action_global_modifierSizeFragment),
                Link("padding", R.id.action_global_modifierPaddingFragment),
                Link("border"),
                Link("background"),
                Link("clip"),
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