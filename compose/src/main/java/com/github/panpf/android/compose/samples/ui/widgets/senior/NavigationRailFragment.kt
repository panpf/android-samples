package com.github.panpf.android.compose.samples.ui.widgets.senior

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.panpf.android.compose.samples.R
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.ToolbarFragment
import com.github.panpf.android.compose.samples.ui.base.theme.MyTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class NavigationRailFragment : ToolbarFragment() {

    override fun createView(
        toolbar: Toolbar,
        inflater: LayoutInflater,
        parent: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        toolbar.title = "NavigationRail"
        return ComposeView(inflater.context).apply {
            setContent {
                MyTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        ExpandableLayout { allExpandFlow ->
                            NavigationRailSample(allExpandFlow)
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun NavigationRailSample(allExpandFlow: Flow<Boolean>) {
    val selectedIndex = remember { mutableStateOf(0) }
    val items = listOf(
        "首页" to R.drawable.ic_home,
        "游戏" to R.drawable.ic_games,
        "朋友" to R.drawable.ic_phone,
//        "设置" to R.drawable.ic_settings,
//        "关于" to R.drawable.ic_info
    )
    ExpandableItem(title = "NavigationRail", allExpandFlow, padding = 20.dp) {
        NavigationRail(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            header = {
                Row {
                    items.forEachIndexed { index, pair ->
                        if (index > 0) {
                            Spacer(modifier = Modifier.size(10.dp))
                        }
                        NavigationRailItem(
                            selected = selectedIndex.value == index,
                            onClick = {
                                selectedIndex.value = index
                            },
                            label = { Text(text = pair.first) },
                            icon = {
                                Icon(
                                    painter = painterResource(id = pair.second),
                                    contentDescription = "icon"
                                )
                            }
                        )
                    }
                }
            }
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Blue.copy(alpha = 0.5f))
            ) {
                Text(
                    text = items[selectedIndex.value].first,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun NavigationRailSamplePreview() {
    NavigationRailSample(remember { MutableStateFlow(true) })
}