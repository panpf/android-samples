package com.github.panpf.android.compose.samples.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.github.panpf.android.compose.samples.R
import com.github.panpf.android.compose.samples.model.Link
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
import com.github.panpf.android.compose.samples.ui.base.list.LinkList

class ListFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "List&Grid"
    }

    @Composable
    override fun DrawContent() {
        val links = remember {
            listOf(
                Link("LazyColumn", R.id.action_global_lazyColumnFragment),
                Link("LazyHorizontalGrid", R.id.action_global_lazyHorizontalGridFragment),
                Link("LazyHorizontalStaggeredGrid", R.id.action_global_lazyHorizontalStaggeredGridFragment),
                Link("LazyRow", R.id.action_global_lazyRowFragment),
                Link("LazyVerticalGrid", R.id.action_global_lazyVerticalGridFragment),
                Link("LazyVerticalStaggeredGrid", R.id.action_global_lazyVerticalStaggeredGridFragment),
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