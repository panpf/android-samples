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

class BasicWidgetsFragment : ToolbarFragment() {

    override fun createView(
        toolbar: Toolbar,
        inflater: LayoutInflater,
        parent: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        toolbar.title = "Basic Widgets"
        val links = listOf(
            Link("Text", R.id.action_basicWidgets_textFragment),
            Link("TextField", R.id.action_basicWidgets_textFieldFragment),
            Link("Button", R.id.action_basicWidgets_buttonFragment),
            Link("IconButton", R.id.action_basicWidgets_iconButtonFragment),
            Link("IconToggleButton", R.id.action_basicWidgets_iconToggleButtonFragment),
            Link("FloatingActionButton", R.id.action_basicWidgets_floatingActionButtonFragment),
            // todo Segmented button planned https://m3.material.io/components/segmented-buttons/overview
            Link("RadioButton", R.id.action_basicWidgets_radioButtonFragment),
            Link("Checkbox", R.id.action_basicWidgets_checkboxFragment),
            // todo TriStateCheckbox
            Link("Switch", R.id.action_basicWidgets_switchFragment),
            Link("Icon", R.id.action_basicWidgets_iconFragment),
            Link("Image", R.id.action_basicWidgets_imageFragment),
            Link("AsyncImage - Coil", R.id.action_basicWidgets_asyncImageCoilFragment),
            Link("AsyncImage - Sketch", R.id.action_basicWidgets_asyncImageSketchFragment),
            Link("Slider", R.id.action_basicWidgets_sliderFragment),
            Link("ProgressIndicator", R.id.action_basicWidgets_progressIndicatorFragment),
            Link("Badge", R.id.action_basicWidgets_badgeFragment),
            Link("Divider", R.id.action_basicWidgets_dividerFragment),
            Link("Snackbar", R.id.action_basicWidgets_snackbarFragment),
            Link("Chip", R.id.action_basicWidgets_chipFragment),
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