package com.github.panpf.android.compose.samples.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.github.panpf.android.compose.samples.R
import com.github.panpf.android.compose.samples.model.Link
import com.github.panpf.android.compose.samples.ui.base.MaterialComposeAppBarFragment
import com.github.panpf.android.compose.samples.ui.base.list.LinkList

class MaterialFragment : MaterialComposeAppBarFragment() {

    override fun getTitle(): String {
        return "Material"
    }

    @Composable
    override fun DrawContent() {
        val links = remember {
            listOf(
                Link("AlertDialog", R.id.action_global_materialAlertDialogFragment),
                Link("BackdropScaffold", R.id.action_global_materialBackdropScaffoldFragment),
                Link("Badge", R.id.action_global_materialBadgeFragment),
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
                Link("PullRefresh", R.id.action_global_materialPullRefreshFragment),
                Link("RadioButton", R.id.action_global_materialRadioButtonFragment),
                Link("Scaffold", R.id.action_global_materialScaffoldFragment),
                Link("Slider", R.id.action_global_materialSliderFragment),
                Link("Snackbar", R.id.action_global_materialSnackbarFragment),
                Link("Surface", R.id.action_global_materialSurfaceFragment),
                Link("Switch", R.id.action_global_materialSwitchFragment),
                Link("TabRow", R.id.action_global_materialTabRowFragment),
                Link("Text", R.id.action_global_materialTextFragment),
                Link("TextField", R.id.action_global_materialTextFieldFragment),
                Link("TopAppBar", R.id.action_global_materialTopAppBarFragment),
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