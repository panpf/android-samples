package com.github.panpf.android.compose.samples.ui.material3

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.NavigationRailItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.panpf.android.compose.samples.R
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem3
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
import com.github.panpf.android.compose.samples.ui.base.MyColor
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.VerticalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class NavigationRailFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "NavigationRail - Material3"
    }

    @Composable
    override fun DrawContent() {
        ExpandableLayout { allExpandFlow ->
            NavigationRailSample(allExpandFlow)
            NavigationRailColorsSample(allExpandFlow)
            NavigationRailPagerSample(allExpandFlow)
        }
    }
}


@Composable
private fun NavigationRailSample(allExpandFlow: Flow<Boolean>) {
    val selectedIndex = remember { mutableStateOf(0) }
    val items = remember {
        listOf(
            "??????" to R.drawable.ic_home,
            "?????????" to R.drawable.ic_phone,
            "??????" to R.drawable.ic_games,
            "??????" to R.drawable.ic_settings,
        )
    }
    ExpandableItem3(title = "NavigationRail", allExpandFlow, padding = 20.dp) {
        NavigationRail(
            modifier = Modifier
                .height(300.dp),
        ) {
            items.forEachIndexed { index, itemPair ->
                if (index > 0) {
                    Spacer(modifier = Modifier.size(10.dp))
                }
                NavigationRailItem(
                    selected = selectedIndex.value == index,
                    onClick = {
                        selectedIndex.value = index
                    },
                    label = { Text(text = itemPair.first) },
                    icon = {
                        Icon(
                            painter = painterResource(id = itemPair.second),
                            contentDescription = "icon"
                        )
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun NavigationRailSamplePreview() {
    NavigationRailSample(remember { MutableStateFlow(true) })
}


@Composable
private fun NavigationRailColorsSample(allExpandFlow: Flow<Boolean>) {
    val selectedIndex = remember { mutableStateOf(0) }
    val items = remember {
        listOf(
            "??????" to R.drawable.ic_home,
            "?????????" to R.drawable.ic_phone,
            "??????" to R.drawable.ic_games,
            "??????" to R.drawable.ic_settings,
        )
    }
    ExpandableItem3(title = "NavigationRail???colors???", allExpandFlow, padding = 20.dp) {
        NavigationRail(
            modifier = Modifier.height(300.dp),
            containerColor = MyColor.TranslucenceYellow
        ) {
            items.forEachIndexed { index, itemPair ->
                if (index > 0) {
                    Spacer(modifier = Modifier.size(10.dp))
                }
                NavigationRailItem(
                    selected = selectedIndex.value == index,
                    onClick = {
                        selectedIndex.value = index
                    },
                    label = { Text(text = itemPair.first) },
                    icon = {
                        Icon(
                            painter = painterResource(id = itemPair.second),
                            contentDescription = "icon"
                        )
                    },
                    colors = NavigationRailItemDefaults.colors(
                        selectedIconColor = Color.Blue,
                        selectedTextColor = Color.Magenta,
                        indicatorColor = MyColor.TranslucenceGreen,
                        unselectedIconColor = MyColor.TranslucenceBlue,
                        unselectedTextColor = MyColor.TranslucenceMagenta,
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun NavigationRailColorsSamplePreview() {
    NavigationRailColorsSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalPagerApi::class)
@Composable
private fun NavigationRailPagerSample(allExpandFlow: Flow<Boolean>) {
    val colors = MyColor.halfRainbows
    val selectedIndex = remember { mutableStateOf(0) }
    val items = remember {
        listOf(
            "??????" to R.drawable.ic_home,
            "?????????" to R.drawable.ic_phone,
            "??????" to R.drawable.ic_games,
            "??????" to R.drawable.ic_settings,
        )
    }
    val pagerState = rememberPagerState(selectedIndex.value)
    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect {
            selectedIndex.value = it
        }
    }
    val coroutineScope = rememberCoroutineScope()
    ExpandableItem3(title = " NavigationRail???Pager???", allExpandFlow, padding = 20.dp) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        ) {
            NavigationRail {
                items.forEachIndexed { index, itemPair ->
                    NavigationRailItem(
                        icon = {
                            Image(
                                painter = painterResource(id = itemPair.second),
                                contentDescription = "icon"
                            )
                        },
                        label = { Text(text = itemPair.first) },
                        selected = index == selectedIndex.value,
                        onClick = {
                            selectedIndex.value = index
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        },
                    )
                }
            }
            VerticalPager(
                state = pagerState,
                count = items.size,
                modifier = Modifier
                    .fillMaxSize()
            ) { index ->
                Box(
                    modifier = Modifier
                        .background(colors[index % colors.size])
                        .fillMaxSize()
                        .padding(20.dp)
                ) {
                    Text(
                        text = items[index].first,
                        modifier = Modifier
                            .align(Alignment.Center),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun NavigationRailPagerSamplePreview() {
    NavigationRailPagerSample(remember { MutableStateFlow(true) })
}