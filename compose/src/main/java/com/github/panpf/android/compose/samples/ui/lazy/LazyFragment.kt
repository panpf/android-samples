package com.github.panpf.android.compose.samples.ui.lazy

import com.github.panpf.android.compose.samples.R
import com.github.panpf.android.compose.samples.ui.base.list.BaseLinkListFragment
import com.github.panpf.android.samples.model.Link

class LazyFragment : BaseLinkListFragment() {

    override fun getTitle(): String {
        return "Lazy"
    }

    override fun buildLinkList(): List<Link> = listOf(
        Link("LazyColumn", R.id.action_global_lazyColumnFragment),
        Link("LazyHorizontalGrid", R.id.action_global_lazyHorizontalGridFragment),
        Link(
            "LazyHorizontalStaggeredGrid",
            R.id.action_global_lazyHorizontalStaggeredGridFragment
        ),
        Link("LazyRow", R.id.action_global_lazyRowFragment),
        Link("LazyVerticalGrid", R.id.action_global_lazyVerticalGridFragment),
        Link(
            "LazyVerticalStaggeredGrid",
            R.id.action_global_lazyVerticalStaggeredGridFragment
        ),
    )
}