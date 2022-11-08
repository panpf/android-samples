package com.github.panpf.android.compose.samples.ui.material

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
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

class ModalBottomSheetLayoutFragment : ToolbarFragment() {

    override fun createView(
        toolbar: Toolbar,
        inflater: LayoutInflater,
        parent: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        toolbar.title = "ModalBottomSheetLayout"
        return ComposeView(inflater.context).apply {
            setContent {
                MyTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colors.background
                    ) {
                        ExpandableLayout { allExpandFlow ->
                            ModalBottomSheetLayoutSample(allExpandFlow)
                            ModalBottomSheetLayoutSheetShapeSample(allExpandFlow)
                        }
                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ModalBottomSheetLayoutSample(allExpandFlow: Flow<Boolean>) {
    val skipHalfExpanded = remember { mutableStateOf(false) }
    val state = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = skipHalfExpanded.value
    )
    val coroutineScope = rememberCoroutineScope()
    ExpandableItem(title = "ModalBottomSheetLayout", allExpandFlow, padding = 20.dp) {
        ModalBottomSheetLayout(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .clip(RectangleShape),
            sheetState = state,
            sheetContent = {
                Column(modifier = Modifier.fillMaxWidth()) {
                    listOf(
                        "分享" to R.drawable.ic_share,
                        "通讯录" to R.drawable.ic_phone,
                        "关于" to R.drawable.ic_info,
                        "设置" to R.drawable.ic_settings,
                    ).forEach { item ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    coroutineScope.launch {
                                        state.hide()
                                    }
                                }
                                .padding(20.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = item.second),
                                contentDescription = item.first
                            )
                            Spacer(modifier = Modifier.size(10.dp))
                            Text(
                                text = item.first,
                                modifier = Modifier.align(Alignment.CenterVertically)
                            )
                        }
                    }
                }
            }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Blue.copy(alpha = 0.5f)),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    Modifier.toggleable(
                        value = skipHalfExpanded.value,
                        role = Role.Checkbox,
                        onValueChange = { checked -> skipHalfExpanded.value = checked }
                    )
                ) {
                    Checkbox(checked = skipHalfExpanded.value, onCheckedChange = null)
                    Spacer(Modifier.size(16.dp))
                    Text("Skip Half Expanded State")
                }
                Spacer(Modifier.size(16.dp))
                Button(
                    onClick = {
                        coroutineScope.launch {
                            state.show()
                        }
                    }
                ) {
                    Text(text = "展开")
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun ModalBottomSheetLayoutSamplePreview() {
    ModalBottomSheetLayoutSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ModalBottomSheetLayoutSheetShapeSample(allExpandFlow: Flow<Boolean>) {
    val skipHalfExpanded = remember { mutableStateOf(false) }
    val state = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = skipHalfExpanded.value
    )
    val coroutineScope = rememberCoroutineScope()
    ExpandableItem(title = "ModalBottomSheetLayout（sheetShape）", allExpandFlow, padding = 20.dp) {
        ModalBottomSheetLayout(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .clip(RectangleShape),
            sheetState = state,
            sheetShape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
            sheetContent = {
                Column(modifier = Modifier.fillMaxWidth()) {
                    listOf(
                        "分享" to R.drawable.ic_share,
                        "通讯录" to R.drawable.ic_phone,
                        "关于" to R.drawable.ic_info,
                        "设置" to R.drawable.ic_settings,
                    ).forEach { item ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    coroutineScope.launch {
                                        state.hide()
                                    }
                                }
                                .padding(20.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = item.second),
                                contentDescription = item.first
                            )
                            Spacer(modifier = Modifier.size(10.dp))
                            Text(
                                text = item.first,
                                modifier = Modifier.align(Alignment.CenterVertically)
                            )
                        }
                    }
                }
            }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Blue.copy(alpha = 0.5f)),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    Modifier.toggleable(
                        value = skipHalfExpanded.value,
                        role = Role.Checkbox,
                        onValueChange = { checked -> skipHalfExpanded.value = checked }
                    )
                ) {
                    Checkbox(checked = skipHalfExpanded.value, onCheckedChange = null)
                    Spacer(Modifier.size(16.dp))
                    Text("Skip Half Expanded State")
                }
                Spacer(Modifier.size(16.dp))
                Button(
                    onClick = {
                        coroutineScope.launch {
                            state.show()
                        }
                    }
                ) {
                    Text(text = "展开")
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun ModalBottomSheetLayoutSheetShapeSampleSheetPreview() {
    ModalBottomSheetLayoutSheetShapeSample(remember { MutableStateFlow(true) })
}