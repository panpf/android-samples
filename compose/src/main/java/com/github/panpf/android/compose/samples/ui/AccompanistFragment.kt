package com.github.panpf.android.compose.samples.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.github.panpf.android.compose.samples.R
import com.github.panpf.android.compose.samples.model.Link
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
import com.github.panpf.android.compose.samples.ui.base.list.LinkList

class AccompanistFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "Accompanist"
    }

    @Composable
    override fun DrawContent() {
        val links = remember {
            listOf(
                Link("FlowColumn", R.id.action_global_accompanistFlowColumnFragment),
                Link("FlowRow", R.id.action_global_accompanistFlowRowFragment),
                Link("WebView", R.id.action_global_webViewFragment),
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