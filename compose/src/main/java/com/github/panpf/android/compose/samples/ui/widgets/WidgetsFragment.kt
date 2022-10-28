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

class WidgetsFragment : ToolbarFragment() {

    override fun createView(
        toolbar: Toolbar,
        inflater: LayoutInflater,
        parent: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        toolbar.title = "Widgets"
        val links = listOf(
            Link("Text", R.id.action_widgets_textFragment),
            Link("TextField", R.id.action_widgets_textFieldFragment),
            Link("Button", R.id.action_widgets_buttonFragment),
            Link("IconButton", R.id.action_widgets_iconButtonFragment),
            Link("IconToggleButton", R.id.action_widgets_iconToggleButtonFragment),
            Link("FloatingActionButton", R.id.action_widgets_floatingActionButtonFragment),
            Link("RadioButton", R.id.action_widgets_radioButtonFragment),
            Link("Checkbox", R.id.action_widgets_checkboxFragment),
            Link("Switch", R.id.action_widgets_switchFragment),
            Link("Icon", R.id.action_widgets_iconFragment),
            Link("Image", R.id.action_widgets_imageFragment),
            Link("AsyncImage - Coil", R.id.action_widgets_asyncImageCoilFragment),
            Link("AsyncImage - Sketch", R.id.action_widgets_asyncImageSketchFragment),
            Link("Slider", R.id.action_widgets_sliderFragment),
            Link("ProgressIndicator", R.id.action_widgets_progressIndicatorFragment),
            Link("Badge", R.id.action_widgets_badgeFragment),
            Link("Divider", R.id.action_widgets_dividerFragment),
            Link("Snackbar", R.id.action_widgets_snackbarFragment),
            Link("Chip", R.id.action_widgets_chipFragment),
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