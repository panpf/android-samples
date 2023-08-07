package com.github.panpf.android.compose.samples.ui.accompanist

import com.github.panpf.android.compose.samples.R
import com.github.panpf.android.compose.samples.ui.base.list.BaseLinkListFragment
import com.github.panpf.android.samples.model.Link

class AccompanistFragment : BaseLinkListFragment() {

    override fun getTitle(): String {
        return "Accompanist"
    }

    override fun buildLinkList(): List<Link> = listOf(
        Link("FlowColumn", R.id.action_global_accompanistFlowColumnFragment),
        Link("FlowRow", R.id.action_global_accompanistFlowRowFragment),
        Link("Placeholder", R.id.action_global_placeholderFragment),
        Link("WebView", R.id.action_global_webViewFragment),
    )
}