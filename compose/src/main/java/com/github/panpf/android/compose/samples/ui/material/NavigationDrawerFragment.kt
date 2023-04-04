package com.github.panpf.android.compose.samples.ui.material

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomDrawer
import androidx.compose.material.BottomDrawerValue
import androidx.compose.material.DrawerValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.ModalDrawer
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.rememberBottomDrawerState
import androidx.compose.material.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.MaterialComposeAppBarFragment
import com.github.panpf.android.compose.samples.ui.base.theme.MyThemeColors3
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class NavigationDrawerFragment : MaterialComposeAppBarFragment() {

    override fun getTitle(): String {
        return "NavigationDrawer - Material"
    }

    @Composable
    override fun DrawContent() {
        ExpandableLayout { allExpandFlow ->
            ModalDrawerSample(allExpandFlow)
            BottomDrawerSample(allExpandFlow)
        }
    }
}


@Composable
private fun ModalDrawerSample(allExpandFlow: Flow<Boolean>) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()
    val colors = MyThemeColors3.current
    ExpandableItem(title = "ModalDrawer", allExpandFlow, padding = 20.dp) {
        Text(text = "Default")
        Spacer(modifier = Modifier.size(10.dp))
        Box(modifier = Modifier.clip(RectangleShape)) {
            ModalDrawer(
                drawerState = drawerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .border(width = 1.dp, color = colors.tertiaryTranslucency),
                drawerContent = {
                    Box(modifier = Modifier.fillMaxSize()) {
                        Text(text = "DrawerContent", modifier = Modifier.align(Alignment.Center))
                    }
                }
            ) {
                Box(modifier = Modifier.fillMaxSize()) {
                    Text(text = "Content", modifier = Modifier.align(Alignment.Center))
                }
            }
            IconButton(
                onClick = {
                    coroutineScope.launch {
                        if (drawerState.isOpen) {
                            drawerState.close()
                        } else {
                            drawerState.open()
                        }
                    }
                },
                modifier = Modifier.align(Alignment.CenterStart)
            ) {
                Icon(
                    imageVector = if (drawerState.isOpen)
                        Icons.Filled.KeyboardArrowLeft else Icons.Filled.KeyboardArrowRight,
                    contentDescription = "expand"
                )
            }
        }

        Spacer(modifier = Modifier.size(20.dp))
        Text(text = "drawerShape")
        Spacer(modifier = Modifier.size(10.dp))
        Box(modifier = Modifier.clip(RectangleShape)) {
            ModalDrawer(
                drawerState = drawerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .border(width = 1.dp, color = colors.tertiaryTranslucency),
                drawerContent = {
                    Box(modifier = Modifier.fillMaxSize()) {
                        Text(text = "DrawerContent", modifier = Modifier.align(Alignment.Center))
                    }
                },
                drawerShape = RoundedCornerShape(20.dp),
            ) {
                Box(modifier = Modifier.fillMaxSize()) {
                    Text(text = "Content", modifier = Modifier.align(Alignment.Center))
                }
            }
            IconButton(
                onClick = {
                    coroutineScope.launch {
                        if (drawerState.isOpen) {
                            drawerState.close()
                        } else {
                            drawerState.open()
                        }
                    }
                },
                modifier = Modifier.align(Alignment.CenterStart)
            ) {
                Icon(
                    imageVector = if (drawerState.isOpen)
                        Icons.Filled.KeyboardArrowLeft else Icons.Filled.KeyboardArrowRight,
                    contentDescription = "expand"
                )
            }
        }

        Spacer(modifier = Modifier.size(20.dp))
        Text(text = "colors")
        Spacer(modifier = Modifier.size(10.dp))
        Box(modifier = Modifier.clip(RectangleShape)) {
            ModalDrawer(
                drawerState = drawerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .border(width = 1.dp, color = colors.tertiaryTranslucency),
                drawerContent = {
                    Box(modifier = Modifier.fillMaxSize()) {
                        Text(text = "DrawerContent", modifier = Modifier.align(Alignment.Center))
                    }
                },
                drawerBackgroundColor = colors.tertiaryContainer,
                drawerContentColor = colors.onTertiary,
                scrimColor = colors.tertiary,
            ) {
                Box(modifier = Modifier.fillMaxSize()) {
                    Text(text = "Content", modifier = Modifier.align(Alignment.Center))
                }
            }
            IconButton(
                onClick = {
                    coroutineScope.launch {
                        if (drawerState.isOpen) {
                            drawerState.close()
                        } else {
                            drawerState.open()
                        }
                    }
                },
                modifier = Modifier.align(Alignment.CenterStart)
            ) {
                Icon(
                    imageVector = if (drawerState.isOpen)
                        Icons.Filled.KeyboardArrowLeft else Icons.Filled.KeyboardArrowRight,
                    contentDescription = "expand"
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun ModalDrawerSamplePreview() {
    ModalDrawerSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun BottomDrawerSample(allExpandFlow: Flow<Boolean>) {
    val drawerState = rememberBottomDrawerState(initialValue = BottomDrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()
    val colors = MyThemeColors3.current
    ExpandableItem(title = "BottomDrawer", allExpandFlow, padding = 20.dp) {
        Row(Modifier.fillMaxWidth()) {
            Column(Modifier.weight(1f)) {
                Text(text = "Default")
                Spacer(modifier = Modifier.size(10.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RectangleShape)
                ) {
                    BottomDrawer(
                        drawerState = drawerState,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                            .border(width = 1.dp, color = colors.tertiaryTranslucency),
                        drawerContent = {
                            Box(modifier = Modifier.fillMaxSize()) {
                                Text(
                                    text = "Drawer\nContent",
                                    modifier = Modifier.align(Alignment.Center)
                                )
                            }
                        }
                    ) {
                        Box(modifier = Modifier.fillMaxSize()) {
                            Text(text = "Content", modifier = Modifier.align(Alignment.Center))
                        }
                    }
                    IconButton(
                        onClick = {
                            coroutineScope.launch {
                                when (drawerState.currentValue) {
                                    BottomDrawerValue.Closed -> drawerState.open()
                                    BottomDrawerValue.Open -> drawerState.expand()
                                    BottomDrawerValue.Expanded -> drawerState.close()
                                }
                            }
                        },
                        modifier = Modifier.align(Alignment.BottomCenter)
                    ) {
                        val icon = when (drawerState.currentValue) {
                            BottomDrawerValue.Closed -> Icons.Filled.KeyboardArrowUp
                            BottomDrawerValue.Open -> Icons.Filled.KeyboardArrowUp
                            BottomDrawerValue.Expanded -> Icons.Filled.KeyboardArrowDown
                        }
                        Icon(imageVector = icon, contentDescription = "menu")
                    }
                }
            }

            Spacer(modifier = Modifier.size(20.dp))
            Column(Modifier.weight(1f)) {
                Text(text = "drawerShape")
                Spacer(modifier = Modifier.size(10.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RectangleShape)
                ) {
                    BottomDrawer(
                        drawerState = drawerState,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                            .border(width = 1.dp, color = colors.tertiaryTranslucency),
                        drawerContent = {
                            Box(modifier = Modifier.fillMaxSize()) {
                                Text(
                                    text = "Drawer\nContent",
                                    modifier = Modifier.align(Alignment.Center)
                                )
                            }
                        },
                        drawerShape = RoundedCornerShape(20.dp),
                    ) {
                        Box(modifier = Modifier.fillMaxSize()) {
                            Text(text = "Content", modifier = Modifier.align(Alignment.Center))
                        }
                    }
                    IconButton(
                        onClick = {
                            coroutineScope.launch {
                                when (drawerState.currentValue) {
                                    BottomDrawerValue.Closed -> drawerState.open()
                                    BottomDrawerValue.Open -> drawerState.expand()
                                    BottomDrawerValue.Expanded -> drawerState.close()
                                }
                            }
                        },
                        modifier = Modifier.align(Alignment.BottomCenter)
                    ) {
                        val icon = when (drawerState.currentValue) {
                            BottomDrawerValue.Closed -> Icons.Filled.KeyboardArrowUp
                            BottomDrawerValue.Open -> Icons.Filled.KeyboardArrowUp
                            BottomDrawerValue.Expanded -> Icons.Filled.KeyboardArrowDown
                        }
                        Icon(imageVector = icon, contentDescription = "menu")
                    }
                }
            }

            Spacer(modifier = Modifier.size(20.dp))
            Column(Modifier.weight(1f)) {
                Text(text = "colors")
                Spacer(modifier = Modifier.size(10.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RectangleShape)
                ) {
                    BottomDrawer(
                        drawerState = drawerState,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                            .border(width = 1.dp, color = colors.tertiaryTranslucency),
                        drawerContent = {
                            Box(modifier = Modifier.fillMaxSize()) {
                                Text(
                                    text = "Drawer\nContent",
                                    modifier = Modifier.align(Alignment.Center)
                                )
                            }
                        },
                        drawerBackgroundColor = colors.tertiaryContainer,
                        drawerContentColor = colors.onTertiary,
                        scrimColor = colors.tertiary,
                    ) {
                        Box(modifier = Modifier.fillMaxSize()) {
                            Text(text = "Content", modifier = Modifier.align(Alignment.Center))
                        }
                    }
                    IconButton(
                        onClick = {
                            coroutineScope.launch {
                                when (drawerState.currentValue) {
                                    BottomDrawerValue.Closed -> drawerState.open()
                                    BottomDrawerValue.Open -> drawerState.expand()
                                    BottomDrawerValue.Expanded -> drawerState.close()
                                }
                            }
                        },
                        modifier = Modifier.align(Alignment.BottomCenter)
                    ) {
                        val icon = when (drawerState.currentValue) {
                            BottomDrawerValue.Closed -> Icons.Filled.KeyboardArrowUp
                            BottomDrawerValue.Open -> Icons.Filled.KeyboardArrowUp
                            BottomDrawerValue.Expanded -> Icons.Filled.KeyboardArrowDown
                        }
                        Icon(imageVector = icon, contentDescription = "menu")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun BottomDrawerSamplePreview() {
    BottomDrawerSample(remember { MutableStateFlow(true) })
}