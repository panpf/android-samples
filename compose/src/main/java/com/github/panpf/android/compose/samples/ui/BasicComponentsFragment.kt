package com.github.panpf.android.compose.samples.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.github.panpf.android.compose.samples.R
import com.github.panpf.android.compose.samples.model.Link
import com.github.panpf.android.compose.samples.ui.base.ToolbarFragment
import com.github.panpf.android.compose.samples.ui.base.list.LinkList
import com.github.panpf.android.compose.samples.ui.base.theme.MyTheme3

class BasicComponentsFragment : ToolbarFragment() {

    override fun createView(
        toolbar: Toolbar,
        inflater: LayoutInflater,
        parent: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        toolbar.title = "Basic Components"
        val links = listOf(
            Link("Box", R.id.action_global_boxFragment),
            Link("Column", R.id.action_global_columnFragment),
            Link("Row", R.id.action_global_rowFragment),
            Link("ConstraintLayout", R.id.action_global_constraintLayoutFragment),
            Link("Image", R.id.action_global_imageFragment),
            Link("LazyColumn", R.id.action_global_lazyColumnFragment),
            Link("LazyRow", R.id.action_global_lazyRowFragment),
            Link("LazyHorizontalGrid", R.id.action_global_lazyHorizontalGridFragment),
            Link("LazyVerticalGrid", R.id.action_global_lazyVerticalGridFragment),
            Link("Dialog", R.id.action_global_dialogFragment),
            Link("Popup", R.id.action_global_popupFragment),
        )
        return ComposeView(inflater.context).apply {
            setContent {
                MyTheme3 {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
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
}