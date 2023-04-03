package com.github.panpf.android.compose.samples.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.github.panpf.android.compose.samples.R
import com.github.panpf.android.compose.samples.model.Link
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
import com.github.panpf.android.compose.samples.ui.base.list.LinkList

class Material3Fragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "Material3"
    }

    @Composable
    override fun DrawContent() {
        val links = remember {
            listOf(
                Link("Badges", R.id.action_global_material3BadgesFragment),
                Link("BottomAppBar", R.id.action_global_material3BottomAppBarFragment),
                Link("BottomSheets（Planned）"),  // todo Planned https://m3.material.io/components/bottom-sheets/overview
                Link("Buttons", R.id.action_global_material3ButtonsFragment),
                Link("Cards", R.id.action_global_material3CardsFragment),
                Link("Checkbox", R.id.action_global_material3CheckboxFragment),
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
                Link("Search"),  // todo Planned https://m3.material.io/components/date-pickers/overview
                Link("SideSheets"),  // todo Planned https://m3.material.io/components/date-pickers/overview
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
        LinkList(links) { _, link ->
            when (val nav = link.nav) {
                is Int -> findNavController().navigate(nav)
                is NavDirections -> findNavController().navigate(nav)
            }
        }
    }
}