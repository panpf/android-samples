package com.github.panpf.android.compose.samples.ui.material

import com.github.panpf.android.compose.samples.R
import com.github.panpf.android.compose.samples.ui.base.list.BaseLinkListFragment
import com.github.panpf.android.samples.model.Link

class MaterialFragment : BaseLinkListFragment() {

    override fun getTitle(): String {
        return "Material"
    }

    override fun buildLinkList(): List<Link> = listOf(
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