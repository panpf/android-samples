package com.github.panpf.android.compose.samples.ui.advancedweidgets

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

class AdvancedLayoutsFragment : ToolbarFragment() {

    override fun createView(
        toolbar: Toolbar,
        inflater: LayoutInflater,
        parent: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        toolbar.title = "Layouts"
        val links = listOf(
            Link("LazyRow", R.id.action_advancedLayouts_lazyRowFragment),
            Link("LazyRow - Paging", R.id.action_advancedLayouts_lazyRowPagingFragment),
            Link("LazyColumn", R.id.action_advancedLayouts_lazyColumnFragment),
            Link("LazyColumn - Paging", R.id.action_advancedLayouts_lazyColumnPagingFragment),
            Link("LazyHorizontalGrid", R.id.action_advancedLayouts_lazyHorizontalGridFragment),
            Link("LazyHorizontalGrid - Paging", R.id.action_advancedLayouts_lazyHorizontalGridPagingFragment),
            Link("LazyVerticalGrid", R.id.action_advancedLayouts_lazyVerticalGridFragment),
            Link("LazyVerticalGrid - Paging", R.id.action_advancedLayouts_lazyVerticalGridPagingFragment),
            Link("Card", R.id.action_advancedLayouts_cardFragment),
            Link("HorizontalPager", R.id.action_advancedLayouts_horizontalPagerFragment),
            Link("VerticalPager", R.id.action_advancedLayouts_verticalPagerFragment),
            Link("Drawer", R.id.action_advancedLayouts_drawerFragment),
            Link("NavigationBar", R.id.action_advancedLayouts_navigationBarFragment),
            Link("NavigationDrawer", R.id.action_advancedLayouts_navigationDrawerFragment),
            Link("NavigationRail", R.id.action_advancedLayouts_navigationRailFragment),
            Link("Scaffold", R.id.action_advancedLayouts_scaffoldFragment),
            Link("Backdrop", R.id.action_advancedLayouts_backdropFragment),
            Link("AppBar", R.id.action_advancedLayouts_appBarFragment),
            Link("BottomAppBar", R.id.action_advancedLayouts_bottomAppBarFragment),
            Link("BottomNavigation", R.id.action_advancedLayouts_bottomNavigationFragment),
            Link("Surface", R.id.action_advancedLayouts_surfaceFragment),
            Link("BottomSheet", R.id.action_advancedLayouts_bottomSheetFragment),
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