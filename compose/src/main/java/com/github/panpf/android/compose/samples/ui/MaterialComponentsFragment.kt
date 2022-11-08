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

class MaterialComponentsFragment : ToolbarFragment() {

    override fun createView(
        toolbar: Toolbar,
        inflater: LayoutInflater,
        parent: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        toolbar.title = "Material Components"
        val links = listOf(
            Link("BottomAppBar", R.id.action_global_materialBottomAppBarFragment),
            Link("BackdropScaffold（Implementation）"),
            Link("Badge（Implementation）"),
            Link("BottomNavigation", R.id.action_global_bottomNavigationFragment),
            Link("Button（Implementation）"), //  FloatingActionButton, IconButton, IconToggleButton
            Link("Card（Implementation）"),
            Link("Checkbox（Implementation）"),
            Link("Chip（Implementation）"),
            Link("Dialog（Implementation）"), // AlertDialog
            Link("Divider（Implementation）"),
            Link("Drawer", R.id.action_global_drawerFragment),
            Link("Icon（Implementation）"),
            Link("ListItem（Implementation）"),
            Link("Menu（Implementation）"),   // DropdownMenu
            Link("NavigationRail（Implementation）"),
            Link("ProgressIndicator（Implementation）"),
            Link("RadioButton（Implementation）"),
            Link("Scaffold（Implementation）"),
            Link(
                "Sheet",
                R.id.action_global_modalBottomSheetLayoutFragment
            ),    // ModalBottomSheetLayout, BottomSheetScaffold
            Link("Slider（Implementation）"),
            Link("Snackbar（Implementation）"),
            Link("Surface（Implementation）"),
            Link("Switch（Implementation）"),
            Link("Tab（Implementation）"),
            Link("TextField（Implementation）"),
            Link("Text（Implementation）"),
            Link("TopAppBar（Implementation）"),
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