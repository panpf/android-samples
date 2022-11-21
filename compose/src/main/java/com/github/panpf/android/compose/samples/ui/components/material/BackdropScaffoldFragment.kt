package com.github.panpf.android.compose.samples.ui.components.material

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.BackdropScaffold
import androidx.compose.material.BackdropValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.ListItem
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.rememberBackdropScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.github.panpf.android.compose.samples.R
import com.github.panpf.android.compose.samples.ui.base.MaterialComposeAppBarFragment
import com.github.panpf.android.compose.samples.ui.base.MyColor
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import kotlinx.coroutines.launch

class BackdropScaffoldFragment : MaterialComposeAppBarFragment() {

    override fun getTitle(): String {
        return "BackdropScaffold"
    }

    @Composable
    override fun DrawContent() {
        BackdropScaffoldSample()
    }
}


@OptIn(ExperimentalPagerApi::class, ExperimentalMaterialApi::class)
@Composable
private fun BackdropScaffoldSample() {
    val colors = MyColor.halfRainbows
    val menuItems = remember {
        listOf(
            "消息" to R.drawable.ic_message,
            "通讯录" to R.drawable.ic_phone,
            "发现" to R.drawable.ic_games,
            "运动" to R.drawable.ic_sports_baseball,
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
                            Icon(
                                painter = painterResource(id = itemPair.second),
                                contentDescription = "icon"
                            )
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
                count = colors.size,
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