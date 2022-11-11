package com.github.panpf.android.compose.samples.ui

import android.content.Context
import androidx.compose.ui.platform.ComposeView
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import com.github.panpf.android.compose.samples.R
import com.github.panpf.android.compose.samples.model.Link
import com.github.panpf.android.compose.samples.ui.base.ComposeFragment
import com.github.panpf.android.compose.samples.ui.base.MyTopAppBarScaffold3
import com.github.panpf.android.compose.samples.ui.base.list.LinkList

class BasicComponentsFragment : ComposeFragment() {

    override fun onCreateComposeView(context: Context): ComposeView {
        val links = listOf(
            Link("Box", R.id.action_global_boxFragment),
            Link("Column", R.id.action_global_columnFragment),
            Link("Dialog", R.id.action_global_dialogFragment),
            Link("ConstraintLayout", R.id.action_global_constraintLayoutFragment),
            Link("Image", R.id.action_global_imageFragment),
            Link("LazyColumn", R.id.action_global_lazyColumnFragment),
            Link("LazyHorizontalGrid", R.id.action_global_lazyHorizontalGridFragment),
            Link("LazyRow", R.id.action_global_lazyRowFragment),
            Link("LazyVerticalGrid", R.id.action_global_lazyVerticalGridFragment),
            Link("Popup", R.id.action_global_popupFragment),
            Link("Row", R.id.action_global_rowFragment),
        )
        return ComposeView(context).apply {
            setContent {
                MyTopAppBarScaffold3("Basic Components") {
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
        }
    }
}