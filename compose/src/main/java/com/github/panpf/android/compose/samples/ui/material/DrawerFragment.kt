package com.github.panpf.android.compose.samples.ui.material

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomDrawer
import androidx.compose.material.BottomDrawerValue
import androidx.compose.material.DrawerValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.IconButton
import androidx.compose.material.ModalDrawer
import androidx.compose.material.Surface
import androidx.compose.material.rememberBottomDrawerState
import androidx.compose.material.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
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
import kotlinx.coroutines.launch

class DrawerFragment : ToolbarFragment() {

    override fun createView(
        toolbar: Toolbar,
        inflater: LayoutInflater,
        parent: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        toolbar.title = "Drawer"
        return ComposeView(inflater.context).apply {
            setContent {
                MyTheme {
                    Surface(modifier = Modifier.fillMaxSize()) {
                        ExpandableLayout { allExpandFlow ->
                            ModalDrawerSample(allExpandFlow)
                            ModalDrawerDrawerShapeSample(allExpandFlow)
                            BottomDrawerSample(allExpandFlow)
                            BottomDrawerDrawerShapeSample(allExpandFlow)
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun ModalDrawerSample(allExpandFlow: Flow<Boolean>) {
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
                            .background(Color.Blue.copy(alpha = 0.5f))
                    )
                }
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .background(Color.Yellow.copy(alpha = 0.5f))
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
                val icon =
                    if (drawerState.isOpen) R.drawable.ic_arrow_left else R.drawable.ic_arrow_right
                Image(
                    painter = painterResource(id = icon),
                    contentDescription = "menu"
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun ModalDrawerSamplePreview() {
    ModalDrawerSample(remember { MutableStateFlow(true) })
}


@Composable
fun ModalDrawerDrawerShapeSample(allExpandFlow: Flow<Boolean>) {
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
                            .background(Color.Blue.copy(alpha = 0.5f))
                    )
                },
                drawerShape = RoundedCornerShape(20.dp),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .background(Color.Yellow.copy(alpha = 0.5f))
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
                val icon =
                    if (drawerState.isOpen) R.drawable.ic_arrow_left else R.drawable.ic_arrow_right
                Image(
                    painter = painterResource(id = icon),
                    contentDescription = "menu"
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun ModalDrawerDrawerShapeSamplePreview() {
    ModalDrawerDrawerShapeSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomDrawerSample(allExpandFlow: Flow<Boolean>) {
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
                            .background(Color.Blue.copy(alpha = 0.5f))
                    )
                }
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .background(Color.Yellow.copy(alpha = 0.5f))
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
                    BottomDrawerValue.Closed -> R.drawable.ic_arrow_up
                    BottomDrawerValue.Open -> R.drawable.ic_arrow_up
                    BottomDrawerValue.Expanded -> R.drawable.ic_arrow_down
                }
                Image(
                    painter = painterResource(id = icon),
                    contentDescription = "menu"
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun BottomDrawerSamplePreview() {
    BottomDrawerSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomDrawerDrawerShapeSample(allExpandFlow: Flow<Boolean>) {
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
                            .background(Color.Blue.copy(alpha = 0.5f))
                    )
                },
                drawerShape = RoundedCornerShape(20.dp),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .background(Color.Yellow.copy(alpha = 0.5f))
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
                    BottomDrawerValue.Closed -> R.drawable.ic_arrow_up
                    BottomDrawerValue.Open -> R.drawable.ic_arrow_up
                    BottomDrawerValue.Expanded -> R.drawable.ic_arrow_down
                }
                Image(
                    painter = painterResource(id = icon),
                    contentDescription = "menu"
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun BottomDrawerDrawerShapeSamplePreview() {
    BottomDrawerDrawerShapeSample(remember { MutableStateFlow(true) })
}