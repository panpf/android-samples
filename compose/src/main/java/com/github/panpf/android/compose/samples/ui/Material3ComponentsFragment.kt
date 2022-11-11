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

class Material3ComponentsFragment : ToolbarFragment() {

    override fun createView(
        toolbar: Toolbar,
        inflater: LayoutInflater,
        parent: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        toolbar.title = "Material3 Components"
        val links = listOf(
            Link("AlertDialog", R.id.action_global_material3AlertDialogFragment),
            Link("Badge", R.id.action_global_material3BadgeFragment),
            Link("BottomAppBar", R.id.action_global_material3BottomAppBarFragment),
            Link("BottomSheets（Planned）"),  // todo Planned https://m3.material.io/components/bottom-sheets/overview
            Link("Button", R.id.action_global_material3ButtonFragment),
            Link("Card", R.id.action_global_material3CardFragment),
            Link("Checkbox", R.id.action_global_material3CheckboxFragment),
            Link("Chip", R.id.action_global_material3ChipFragment),
            Link("DatePicker（Planned）"),  // todo Planned https://m3.material.io/components/date-pickers/overview
            Link("Divider", R.id.action_global_material3DividerFragment),
            Link("DropdownMenu", R.id.action_global_material3DropdownMenuFragment),
            Link("Icon", R.id.action_global_material3IconFragment),
            Link("ListItem", R.id.action_global_material3ListItemFragment),
            Link("NavigationBar", R.id.action_global_material3NavigationBarFragment),
            Link("NavigationDrawer", R.id.action_global_material3NavigationDrawerFragment),
            Link("NavigationRail", R.id.action_global_material3NavigationRailFragment),
            Link("ProgressIndicator", R.id.action_global_material3ProgressIndicatorFragment),
            Link("RadioButton", R.id.action_global_material3RadioButtonFragment),
            Link("Scaffold", R.id.action_global_material3ScaffoldFragment),
            Link("Slider", R.id.action_global_material3SliderFragment),
            Link("Snackbar", R.id.action_global_material3SnackbarFragment),
            Link("Surface", R.id.action_global_material3SurfaceFragment),
            Link("Switch", R.id.action_global_material3SwitchFragment),
            Link("TabRow", R.id.action_global_material3TabRowFragment),
            Link("Text", R.id.action_global_material3TextFragment),
            Link("TextField", R.id.action_global_material3TextFieldFragment),
            Link("TimePicker（Planned）"),  // todo Planned https://m3.material.io/components/time-pickers/overview
            Link("TopAppBar", R.id.action_global_material3TopAppBarFragment),
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