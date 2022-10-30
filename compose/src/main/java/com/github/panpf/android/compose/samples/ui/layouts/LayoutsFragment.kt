package com.github.panpf.android.compose.samples.ui.layouts

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
import com.github.panpf.android.compose.samples.ui.base.theme.MyTheme

class LayoutsFragment : ToolbarFragment() {

    override fun createView(
        toolbar: Toolbar,
        inflater: LayoutInflater,
        parent: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        toolbar.title = "Layouts"
        val links = listOf(
            Link("Row", R.id.action_layouts_rowFragment),
            Link("FlowRow", R.id.action_layouts_flowRowFragment),
            Link("Column", R.id.action_layouts_columnFragment),
            Link("FlowColumn", R.id.action_layouts_flowColumnFragment),
            Link("Box", R.id.action_layouts_boxFragment),
            Link("Pager", R.id.action_layouts_pagerFragment),
            Link("LazyRaw", R.id.action_layouts_lazyRawFragment),
            Link("LazyColumn", R.id.action_layouts_lazyColumnFragment),
            Link("LazyList", R.id.action_layouts_lazyListFragment),
            Link("LazyGrid", R.id.action_layouts_lazyGridFragment),
            Link("ConstraintLayout", R.id.action_layouts_constraintLayoutFragment),
            Link("Card", R.id.action_layouts_cardFragment),
            Link("Drawer", R.id.action_layouts_drawerFragment),
            Link("NavigationBar", R.id.action_layouts_navigationBarFragment),
            Link("NavigationDrawer", R.id.action_layouts_navigationDrawerFragment),
            Link("NavigationRail", R.id.action_layouts_navigationRailFragment),
            Link("Scaffold", R.id.action_layouts_scaffoldFragment),
            Link("Backdrop", R.id.action_layouts_backdropFragment),
            Link("AppBar", R.id.action_layouts_appBarFragment),
            Link("BottomAppBar", R.id.action_layouts_bottomAppBarFragment),
            Link("BottomNavigation", R.id.action_layouts_bottomNavigationFragment),
            Link("Surface", R.id.action_layouts_surfaceFragment),
            Link("TabRow", R.id.action_layouts_tabRowFragment),
            Link("BottomSheet", R.id.action_layouts_bottomSheetFragment),
        )
        return ComposeView(inflater.context).apply {
            setContent {
                MyTheme {
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