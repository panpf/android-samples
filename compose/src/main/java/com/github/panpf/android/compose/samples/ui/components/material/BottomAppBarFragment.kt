package com.github.panpf.android.compose.samples.ui.components.material

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.panpf.android.compose.samples.R
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.MaterialComposeAppBarFragment
import com.github.panpf.android.compose.samples.ui.base.MyColor
import com.github.panpf.tools4a.toast.ktx.showShortToast
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class BottomAppBarFragment : MaterialComposeAppBarFragment() {

    override fun getTitle(): String {
        return "BottomAppBar - Material"
    }

    @Composable
    override fun DrawContent() {
        ExpandableLayout { allExpandFlow ->
            BottomAppBarSample(allExpandFlow)
            BottomAppBarColorsSample(allExpandFlow)
        }
    }
}


@Composable
private fun BottomAppBarSample(allExpandFlow: Flow<Boolean>) {
    val items = remember {
        listOf(
            "首页" to R.drawable.ic_home,
            "通讯录" to R.drawable.ic_phone,
            "游戏" to R.drawable.ic_games,
            "设置" to R.drawable.ic_settings,
        )
    }
    val context = LocalContext.current
    ExpandableItem(title = "BottomAppBar", allExpandFlow, padding = 20.dp) {
        Column {
            BottomAppBar {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    items.forEach {
                        IconButton(
                            modifier = Modifier
                                .fillMaxHeight()
                                .weight(1f),
                            onClick = { context.showShortToast(it.first) }
                        ) {
                            Icon(
                                painter = painterResource(id = it.second),
                                contentDescription = it.first
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun BottomAppBarSamplePreview() {
    BottomAppBarSample(remember { MutableStateFlow(true) })
}


@Composable
private fun BottomAppBarColorsSample(allExpandFlow: Flow<Boolean>) {
    val items = remember {
        listOf(
            "首页" to R.drawable.ic_home,
            "通讯录" to R.drawable.ic_phone,
            "游戏" to R.drawable.ic_games,
            "设置" to R.drawable.ic_settings,
        )
    }
    val context = LocalContext.current
    ExpandableItem(title = "BottomAppBar（colors）", allExpandFlow, padding = 20.dp) {
        Column {
            BottomAppBar(
                backgroundColor = MyColor.TranslucenceRed,
                contentColor = Color.Red,
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    items.forEach {
                        IconButton(
                            modifier = Modifier
                                .fillMaxHeight()
                                .weight(1f),
                            onClick = { context.showShortToast(it.first) }
                        ) {
                            Icon(
                                painter = painterResource(id = it.second),
                                contentDescription = "back"
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun BottomAppBarColorsSamplePreview() {
    BottomAppBarColorsSample(remember { MutableStateFlow(true) })
}