package com.github.panpf.android.compose.samples.ui.material3

import com.github.panpf.android.compose.samples.R
import com.github.panpf.android.compose.samples.ui.base.list.BaseLinkListFragment
import com.github.panpf.android.samples.model.Link

class Material3Fragment : BaseLinkListFragment() {

    override fun getTitle(): String {
        return "Material3"
    }

    override fun buildLinkList(): List<Link> = listOf(
        Link("Badge", R.id.action_global_material3BadgeFragment),
        Link("BottomAppBar", R.id.action_global_material3BottomAppBarFragment),
        Link("BottomSheets（Planned）"),  // todo Planned https://m3.material.io/components/bottom-sheets/overview
        Link("Buttons", R.id.action_global_material3ButtonsFragment),
        Link("Cards", R.id.action_global_material3CardsFragment),
        Link("Checkboxes", R.id.action_global_material3CheckboxesFragment),
        Link("Chips", R.id.action_global_material3ChipsFragment),
        Link("DatePickers（Planned）"),  // todo Planned https://m3.material.io/components/date-pickers/overview
        Link("Dialogs", R.id.action_global_material3DialogsFragment),
        Link("Divider", R.id.action_global_material3DividerFragment),
        Link("Lists", R.id.action_global_material3ListsFragment),
        Link("Menus", R.id.action_global_material3MenusFragment),
        Link("Icon", R.id.action_global_material3IconFragment),
        Link("NavigationBar", R.id.action_global_material3NavigationBarFragment),
        Link("NavigationDrawer", R.id.action_global_material3NavigationDrawerFragment),
        Link("NavigationRail", R.id.action_global_material3NavigationRailFragment),
        Link("ProgressIndicators", R.id.action_global_material3ProgressIndicatorsFragment),
        Link("RadioButton", R.id.action_global_material3RadioButtonFragment),
        Link("Search（Planned）"),  // todo Planned https://m3.material.io/components/date-pickers/overview
        Link("SideSheets（Planned）"),  // todo Planned https://m3.material.io/components/date-pickers/overview
        Link("Scaffold", R.id.action_global_material3ScaffoldFragment),
        Link("Sliders", R.id.action_global_material3SlidersFragment),
        Link("Snackbar", R.id.action_global_material3SnackbarFragment),
        Link("Surface", R.id.action_global_material3SurfaceFragment),
        Link("Switch", R.id.action_global_material3SwitchFragment),
        Link("Tabs", R.id.action_global_material3TabsFragment),
        Link("Text", R.id.action_global_material3TextFragment),
        Link("TextFields", R.id.action_global_material3TextFieldsFragment),
        Link("TimePickers（Planned）"),  // todo Planned https://m3.material.io/components/time-pickers/overview
        Link("TopAppBar", R.id.action_global_material3TopAppBarFragment),
    )
}