package com.github.panpf.android.compose.samples.ui.components.material

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.BottomAppBar
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.FabPosition
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.panpf.android.compose.samples.R
import com.github.panpf.android.compose.samples.ui.base.MaterialComposeAppBarFragment
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.launch

class ScaffoldFragment : MaterialComposeAppBarFragment() {

    override fun getTitle(): String? {
        return null
    }

    @Composable
    override fun DrawContent() {
        ScaffoldSample()
    }
}


@OptIn(ExperimentalPagerApi::class)
@Composable
private fun ScaffoldSample() {
    val pagerItems = remember {
        listOf(
            "消息" to R.drawable.ic_message,
            "发现" to R.drawable.ic_games,
            "运动" to R.drawable.ic_sports_baseball,
        )
    }
    val scaffoldState = rememberScaffoldState()
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                navigationIcon = {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .clickable {
                                coroutineScope.launch {
                                    scaffoldState.drawerState.open()
                                }
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_menu),
                            contentDescription = "menu",
                        )
                    }
                },
                title = { Text("Scaffold - Material") },
            )
        },
        content = { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                Text(
                    text = "Content",
                    modifier = Modifier
                        .align(Alignment.Center),
                    textAlign = TextAlign.Center
                )
            }
        },
        isFloatingActionButtonDocked = true,
        bottomBar = {
            BottomAppBar(
                cutoutShape = MaterialTheme.shapes.small.copy(
                    CornerSize(percent = 50)
                ),
                contentPadding = PaddingValues(end = 170.dp)
            ) {
                pagerItems.forEach { itemPair ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            painter = painterResource(id = itemPair.second),
                            contentDescription = "icon"
                        )
                        Text(text = itemPair.first)
                    }
                }
            }
        },
        drawerContent = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(MaterialTheme.colors.primary.copy(alpha = 0.5f))
            )
        },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            val clickCount = remember { mutableStateOf(0) }
            ExtendedFloatingActionButton(
                onClick = {
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(
                            "Snackbar # ${++clickCount.value}"
                        )
                    }
                },
                text = {
                    Text("Show snackbar")
                }
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
    )
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun ScaffoldSamplePreview() {
    ScaffoldSample()
}