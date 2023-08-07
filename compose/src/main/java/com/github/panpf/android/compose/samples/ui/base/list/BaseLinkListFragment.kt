package com.github.panpf.android.compose.samples.ui.base.list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
import com.github.panpf.android.samples.model.Link

abstract class BaseLinkListFragment : Material3ComposeAppBarFragment() {

    @Composable
    override fun DrawContent() {
        val links = remember { buildLinkList() }
        LinkList(links) { _, link ->
            when (val nav = link.nav) {
                is Int -> findNavController().navigate(nav)
                is NavDirections -> findNavController().navigate(nav)
            }
        }
    }

    abstract fun buildLinkList(): List<Link>
}