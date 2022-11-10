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

class HomeFragment : ToolbarFragment() {

    override fun createView(
        toolbar: Toolbar,
        inflater: LayoutInflater,
        parent: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        toolbar.title = requireContext().getString(R.string.app_name)

        val links = listOf(
            Link("Basic Components", R.id.action_global_basicWidgetsFragment),
            Link("Extension Components", R.id.action_global_extensionComponentsFragment),
            Link("Material Components", R.id.action_global_materialComponentsFragment),
            Link("Material3 Components", R.id.action_global_material3ComponentsFragment),
            Link("Animation（Implementation）"),   // todo Implementation
            Link("Theme（Implementation）"),   // todo Implementation
            Link("TouchEvent（Implementation）"),   // todo Implementation
            Link("Navigation（Implementation）"),   // todo Implementation
            Link("Custom（Implementation）"),   // todo Implementation
            Link("Cases（Implementation）"),  // todo Implementation banner
            Link("Other（Implementation）"),  // todo Implementation Placeholder, Drawable Painter
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