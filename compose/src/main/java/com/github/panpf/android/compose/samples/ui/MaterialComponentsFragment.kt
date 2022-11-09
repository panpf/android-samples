package com.github.panpf.android.compose.samples.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.github.panpf.android.compose.samples.R
import com.github.panpf.android.compose.samples.model.Link
import com.github.panpf.android.compose.samples.ui.base.ToolbarFragment
import com.github.panpf.android.compose.samples.ui.base.list.LinkList
import com.github.panpf.android.compose.samples.ui.base.theme.MyTheme

class MaterialComponentsFragment : ToolbarFragment() {

    override fun createView(
        toolbar: Toolbar,
        inflater: LayoutInflater,
        parent: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        toolbar.title = "Material Components"
        val links = listOf(
            Link("AlertDialog", R.id.action_global_materialAlertDialogFragment),
            Link("Badge", R.id.action_global_materialBadgeFragment),
            Link("BackdropScaffold（Implementation）"),
            Link("BottomAppBar", R.id.action_global_materialBottomAppBarFragment),
            Link("BottomNavigation", R.id.action_global_materialBottomNavigationFragment),
            Link("BottomSheetLayout", R.id.action_global_materialBottomSheetLayoutFragment),
            Link("BottomSheetScaffold", R.id.action_global_materialBottomSheetScaffoldFragment),
            Link("Button", R.id.action_global_materialButtonFragment),
            Link("Card", R.id.action_global_materialCardFragment),
            Link("Checkbox", R.id.action_global_materialCheckboxFragment),
            Link("Chip", R.id.action_global_materialChipFragment),
            Link("Divider", R.id.action_global_materialDividerFragment),
            Link("DropdownMenu", R.id.action_global_materialDropdownMenuFragment),
            Link("Drawer", R.id.action_global_materialDrawerFragment),
            Link("Icon", R.id.action_global_materialIconFragment),
            Link("ListItem", R.id.action_global_materialListItemFragment),
            Link("NavigationRail", R.id.action_global_materialNavigationRailFragment),
            Link("ProgressIndicator", R.id.action_global_materialProgressIndicatorFragment),
            Link("RadioButton", R.id.action_global_materialRadioButtonFragment),
            Link("Scaffold", R.id.action_global_materialScaffoldFragment),
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
                MyTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colors.background
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