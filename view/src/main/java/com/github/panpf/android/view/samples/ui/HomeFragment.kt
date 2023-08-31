package com.github.panpf.android.view.samples.ui

import com.github.panpf.android.samples.model.Link
import com.github.panpf.android.view.samples.R
import com.github.panpf.android.view.samples.ui.link.BaseLinkListFragment

class HomeFragment : BaseLinkListFragment() {

    override fun buildData(): List<Any> {
        return listOf(
            Link(
                title = "Images",
                nav = R.id.action_global_imagesFragment,
            ),
        )
    }
}