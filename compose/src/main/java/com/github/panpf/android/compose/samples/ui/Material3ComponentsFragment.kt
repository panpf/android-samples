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
            Link("AlertDialog", R.id.action_global_alertDialogFragment),
            Link("Badge", R.id.action_global_badgeFragment),
            Link("BottomAppBar", R.id.action_global_bottomAppBarFragment),
            Link("BottomSheets（Planned）"),
            Link("Button", R.id.action_global_buttonFragment),
            Link("Card", R.id.action_global_cardFragment),
            Link("Checkbox", R.id.action_global_checkboxFragment),
            Link("Chip", R.id.action_global_chipFragment),
            Link("DatePicker（Planned）"),
            Link("Divider", R.id.action_global_dividerFragment),
            Link("DropdownMenu", R.id.action_global_dropdownMenuFragment),
            Link("Icon", R.id.action_global_iconFragment),
            Link("ListItem", R.id.action_global_listItemFragment),
            Link("NavigationBar", R.id.action_global_navigationBarFragment),
            Link("NavigationDrawer", R.id.action_global_navigationDrawerFragment),
            Link("NavigationRail", R.id.action_global_navigationRailFragment),
            Link("ProgressIndicator", R.id.action_global_progressIndicatorFragment),
            Link("RadioButton", R.id.action_global_radioButtonFragment),
            Link("Scaffold", R.id.action_global_scaffoldFragment),
            Link("Slider", R.id.action_global_sliderFragment),
            Link("Snackbar", R.id.action_global_snackbarFragment),
            Link("Surface", R.id.action_global_surfaceFragment),
            Link("Switch", R.id.action_global_switchFragment),
            Link("TabRow", R.id.action_global_tabRowFragment),
            Link("TextField", R.id.action_global_textFieldFragment),
            Link("Text", R.id.action_global_textFragment),
            Link("TimePicker（Planned）"),
            Link("TopAppBar", R.id.action_global_topAppBarFragment),
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