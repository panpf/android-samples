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
                Link("BackdropScaffold", R.id.action_global_materialBackdropScaffoldFragment),
                Link("Badge", R.id.action_global_materialBadgeFragment),
                Link("BottomAppBar", R.id.action_global_materialBottomAppBarFragment),
                Link("BottomNavigation", R.id.action_global_materialBottomNavigationFragment),
                Link("BottomSheets", R.id.action_global_materialBottomSheetsFragment),
                Link("Buttons", R.id.action_global_materialButtonsFragment),
                Link("Card", R.id.action_global_materialCardFragment),
                Link("Checkboxes", R.id.action_global_materialCheckboxesFragment),
                Link("Chips", R.id.action_global_materialChipsFragment),
                Link("Dialogs", R.id.action_global_materialAlertDialogFragment),
                Link("Divider", R.id.action_global_materialDividerFragment),
                Link("Icon", R.id.action_global_materialIconFragment),
                Link("Lists", R.id.action_global_materialListsFragment),
                Link("Menus", R.id.action_global_materialMenusFragment),
                Link("NavigationDrawer", R.id.action_global_materialNavigationDrawerFragment),
                Link("NavigationRail", R.id.action_global_materialNavigationRailFragment),
                Link("ProgressIndicators", R.id.action_global_materialProgressIndicatorsFragment),
                Link("PullRefresh", R.id.action_global_materialPullRefreshFragment),
                Link("RadioButton", R.id.action_global_materialRadioButtonFragment),
                Link("Scaffold", R.id.action_global_materialScaffoldFragment),
                Link("Sliders", R.id.action_global_materialSlidersFragment),
                Link("Snackbar", R.id.action_global_materialSnackbarFragment),
                Link("Surface", R.id.action_global_materialSurfaceFragment),
                Link("Switch", R.id.action_global_materialSwitchFragment),
                Link("Tabs", R.id.action_global_materialTabsFragment),
                Link("Text", R.id.action_global_materialTextFragment),
                Link("TextFields", R.id.action_global_materialTextFieldsFragment),
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