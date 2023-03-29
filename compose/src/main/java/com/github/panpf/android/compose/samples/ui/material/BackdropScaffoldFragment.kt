package com.github.panpf.android.compose.samples.ui.material

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.material.BackdropScaffold
import androidx.compose.material.BackdropValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.ListItem
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.rememberBackdropScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.github.panpf.android.compose.samples.ui.base.MaterialComposeAppBarFragment
import com.github.panpf.android.compose.samples.ui.base.MyColor
import kotlinx.coroutines.launch

class BackdropScaffoldFragment : MaterialComposeAppBarFragment() {

    override fun getTitle(): String? {
        return null
    }

    @Composable
    override fun DrawContent() {
        BackdropScaffoldSample()
    }
}


@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
private fun BackdropScaffoldSample() {
    val colors = MyColor.halfRainbows
    val menuItems = remember {
        listOf(
            "推荐" to Icons.Filled.Home,
            "消息" to Icons.Filled.Email,
            "通讯录" to Icons.Filled.Phone,
            "发现" to Icons.Filled.Place,
        )
    }
    val snackbarHostState = remember { SnackbarHostState() }
    val scaffoldState = rememberBackdropScaffoldState(initialValue = BackdropValue.Concealed)
    val coroutineScope = rememberCoroutineScope()

    BackdropScaffold(
        scaffoldState = scaffoldState,
        appBar = {
            TopAppBar(
                title = { Text("BackdropScaffold - Material") },
            )
        },
        frontLayerContent = {
            Column {
                menuItems.forEachIndexed { _, itemPair ->
                    ListItem(
                        icon = {
                            Icon(imageVector = itemPair.second, contentDescription = itemPair.first)
                        },
                        text = { Text(text = itemPair.first) },
                        modifier = Modifier.clickable {
                            coroutineScope.launch {
                                scaffoldState.reveal()
                            }
                        }
                    )
                }
            }
        },
        backLayerContent = {
            HorizontalPager(
                pageCount = colors.size,
                modifier = Modifier.fillMaxSize(),
            ) { index ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(colors[index % colors.size])
                )
            }
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
    )
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun BackdropScaffoldSamplePreview() {
    BackdropScaffoldSample()
}