package com.github.panpf.android.compose.samples.ui.material

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomDrawer
import androidx.compose.material.BottomDrawerValue
import androidx.compose.material.DrawerValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.ModalDrawer
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
import com.github.panpf.android.compose.samples.ui.base.MyColor
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
            ModalDrawerDrawerShapeSample(allExpandFlow)
            BottomDrawerSample(allExpandFlow)
            BottomDrawerDrawerShapeSample(allExpandFlow)
        }
    }
}


@Composable
private fun ModalDrawerSample(allExpandFlow: Flow<Boolean>) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()
    ExpandableItem(title = "ModalDrawer", allExpandFlow, padding = 20.dp) {
        Box(modifier = Modifier.clip(RectangleShape)) {
            ModalDrawer(
                drawerState = drawerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                drawerContent = {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .background(MyColor.TranslucenceBlue)
                    )
                }
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .background(MyColor.TranslucenceYellow)
                )
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


@Composable
private fun ModalDrawerDrawerShapeSample(allExpandFlow: Flow<Boolean>) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()
    ExpandableItem(title = "ModalDrawer（drawerShape）", allExpandFlow, padding = 20.dp) {
        Box(modifier = Modifier.clip(RectangleShape)) {
            ModalDrawer(
                drawerState = drawerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                drawerContent = {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .background(MyColor.TranslucenceBlue)
                    )
                },
                drawerShape = RoundedCornerShape(20.dp),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .background(MyColor.TranslucenceYellow)
                )
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
private fun ModalDrawerDrawerShapeSamplePreview() {
    ModalDrawerDrawerShapeSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun BottomDrawerSample(allExpandFlow: Flow<Boolean>) {
    val drawerState = rememberBottomDrawerState(initialValue = BottomDrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()
    ExpandableItem(title = "BottomDrawer", allExpandFlow, padding = 20.dp) {
        Box(modifier = Modifier.clip(RectangleShape)) {
            BottomDrawer(
                drawerState = drawerState,
                modifier = Modifier
                    .width(200.dp)
                    .height(300.dp),
                drawerContent = {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .background(MyColor.TranslucenceBlue)
                    )
                }
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .background(MyColor.TranslucenceYellow)
                )
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

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun BottomDrawerSamplePreview() {
    BottomDrawerSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun BottomDrawerDrawerShapeSample(allExpandFlow: Flow<Boolean>) {
    val drawerState = rememberBottomDrawerState(initialValue = BottomDrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()
    ExpandableItem(title = "BottomDrawer（drawerShape）", allExpandFlow, padding = 20.dp) {
        Box(modifier = Modifier.clip(RectangleShape)) {
            BottomDrawer(
                drawerState = drawerState,
                modifier = Modifier
                    .width(200.dp)
                    .height(300.dp),
                drawerContent = {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .background(MyColor.TranslucenceBlue)
                    )
                },
                drawerShape = RoundedCornerShape(20.dp),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .background(MyColor.TranslucenceYellow)
                )
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

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun BottomDrawerDrawerShapeSamplePreview() {
    BottomDrawerDrawerShapeSample(remember { MutableStateFlow(true) })
}