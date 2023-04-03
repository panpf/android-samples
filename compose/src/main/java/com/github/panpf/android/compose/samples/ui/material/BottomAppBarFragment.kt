package com.github.panpf.android.compose.samples.ui.material

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.panpf.android.compose.samples.ui.base.MaterialComposeAppBarFragment
import com.github.panpf.android.compose.samples.ui.base.MyColor
import com.github.panpf.tools4a.toast.ktx.showShortToast

class BottomAppBarFragment : MaterialComposeAppBarFragment() {

    override fun getTitle(): String {
        return "BottomAppBar - Material"
    }

    @Composable
    override fun DrawContent() {
        BottomAppBarSample()
    }
}


@Composable
private fun BottomAppBarSample() {
    val items = remember {
        listOf(
            "首页" to Icons.Filled.Home,
            "通讯录" to Icons.Filled.Phone,
            "游戏" to Icons.Filled.PlayArrow,
            "设置" to Icons.Filled.Settings,
        )
    }
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
    ) {
        Text(text = "Default")
        Spacer(modifier = Modifier.size(10.dp))
        BottomAppBar {
            items.forEach {
                IconButton(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f),
                    onClick = { context.showShortToast(it.first) }
                ) {
                    Icon(imageVector = it.second, contentDescription = it.first)
                }
            }
        }

        Spacer(modifier = Modifier.size(20.dp))
        Text(text = "backgroundColor")
        Spacer(modifier = Modifier.size(10.dp))
        BottomAppBar(backgroundColor = MyColor.TranslucenceRed) {
            items.forEach {
                IconButton(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f),
                    onClick = { context.showShortToast(it.first) }
                ) {
                    Icon(imageVector = it.second, contentDescription = it.first)
                }
            }
        }

        Spacer(modifier = Modifier.size(20.dp))
        Text(text = "contentColor")
        Spacer(modifier = Modifier.size(10.dp))
        BottomAppBar(contentColor = Color.Red) {
            items.forEach {
                IconButton(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f),
                    onClick = { context.showShortToast(it.first) }
                ) {
                    Icon(imageVector = it.second, contentDescription = it.first)
                }
            }
        }

        // cutoutShape Valid only in Scaffold

        Spacer(modifier = Modifier.size(20.dp))
        Text(text = "elevation=15.dp")
        Spacer(modifier = Modifier.size(10.dp))
        BottomAppBar(elevation = 15.dp) {
            items.forEach {
                IconButton(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f),
                    onClick = { context.showShortToast(it.first) }
                ) {
                    Icon(imageVector = it.second, contentDescription = it.first)
                }
            }
        }

        Spacer(modifier = Modifier.size(20.dp))
        Text(text = "contentPadding=PaddingValues(horizontal=20.dp, vertical=12.dp)")
        Spacer(modifier = Modifier.size(10.dp))
        BottomAppBar(contentPadding = PaddingValues(horizontal = 20.dp, vertical = 10.dp)) {
            items.forEach {
                IconButton(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f),
                    onClick = { context.showShortToast(it.first) }
                ) {
                    Icon(imageVector = it.second, contentDescription = it.first)
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun BottomAppBarSamplePreview() {
    BottomAppBarSample()
}