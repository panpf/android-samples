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
            Link("Card", R.id.action_seniorLayouts_cardFragment),
            Link("NavigationBar", R.id.action_seniorLayouts_navigationBarFragment),
            Link("NavigationDrawer", R.id.action_seniorLayouts_navigationDrawerFragment),
            Link("NavigationRail", R.id.action_seniorLayouts_navigationRailFragment),
            Link("Scaffold", R.id.action_seniorLayouts_scaffoldFragment),
            Link("Backdrop", R.id.action_seniorLayouts_backdropFragment),
            Link("AppBar", R.id.action_seniorLayouts_appBarFragment),
            Link("BottomAppBar", R.id.action_seniorLayouts_bottomAppBarFragment),
            Link("BottomNavigation", R.id.action_seniorLayouts_bottomNavigationFragment),
            Link("Surface", R.id.action_seniorLayouts_surfaceFragment),
            Link("BottomSheet", R.id.action_seniorLayouts_bottomSheetFragment),
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