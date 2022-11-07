package com.github.panpf.android.compose.samples.ui.widgets

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

class SeniorWidgetsFragment : ToolbarFragment() {

    override fun createView(
        toolbar: Toolbar,
        inflater: LayoutInflater,
        parent: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        toolbar.title = "Senior Widgets"
        val links = listOf(
            Link("LazyRow", R.id.action_seniorWidgets_lazyRowFragment),
            Link("LazyColumn", R.id.action_seniorWidgets_lazyColumnFragment),
            Link("LazyHorizontalGrid", R.id.action_seniorWidgets_lazyHorizontalGridFragment),
            Link("LazyVerticalGrid", R.id.action_seniorWidgets_lazyVerticalGridFragment),
            Link("HorizontalPager", R.id.action_seniorWidgets_horizontalPagerFragment),
            Link("VerticalPager", R.id.action_seniorWidgets_verticalPagerFragment),
            Link("Drawer", R.id.action_seniorWidgets_drawerFragment),
            Link("NavigationDrawer", R.id.action_seniorWidgets_navigationDrawerFragment),
            Link("Card", R.id.action_seniorWidgets_cardFragment),
            Link("NavigationBar", R.id.action_seniorWidgets_navigationBarFragment),
            Link("NavigationRail", R.id.action_seniorWidgets_navigationRailFragment),
            Link("TopAppBar", R.id.action_seniorWidgets_topAppBarFragment),
            Link("BottomAppBar", R.id.action_seniorWidgets_bottomAppBarFragment),
            Link("BottomNavigation", R.id.action_seniorWidgets_bottomNavigationFragment),
            Link("ModalBottomSheetLayout", R.id.action_seniorWidgets_modalBottomSheetLayoutFragment),
            Link("Scaffold", R.id.action_seniorWidgets_scaffoldFragment),
            Link("DatePicker"),
            Link("TimePicker"),
            Link("Dialog"),
            Link("Menu"),
            Link("ListItem"),
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