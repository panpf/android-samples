package com.github.panpf.android.compose.samples.ui.layouts

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

class SeniorLayoutsFragment : ToolbarFragment() {

    override fun createView(
        toolbar: Toolbar,
        inflater: LayoutInflater,
        parent: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        toolbar.title = "Senior Layouts"
        val links = listOf(
            Link("LazyRow", R.id.action_seniorLayouts_lazyRowFragment),
            Link("LazyColumn", R.id.action_seniorLayouts_lazyColumnFragment),
            Link("LazyHorizontalGrid", R.id.action_seniorLayouts_lazyHorizontalGridFragment),
            Link("LazyVerticalGrid", R.id.action_seniorLayouts_lazyVerticalGridFragment),
            Link("HorizontalPager", R.id.action_seniorLayouts_horizontalPagerFragment),
            Link("VerticalPager", R.id.action_seniorLayouts_verticalPagerFragment),
            Link("Drawer", R.id.action_seniorLayouts_drawerFragment),
            Link("NavigationDrawer", R.id.action_seniorLayouts_navigationDrawerFragment),
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