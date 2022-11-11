package com.github.panpf.android.compose.samples.ui.material3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.ToolbarFragment
import com.github.panpf.android.compose.samples.ui.base.theme.MyTheme3
import com.github.panpf.tools4a.toast.ktx.showLongToast
import com.google.accompanist.flowlayout.FlowRow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class SurfaceFragment : ToolbarFragment() {

    override fun createView(
        toolbar: Toolbar,
        inflater: LayoutInflater,
        parent: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        toolbar.title = "Surface - Material3"
        return ComposeView(inflater.context).apply {
            setContent {
                MyTheme3 {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        ExpandableLayout { allExpandFlow ->
                            SurfaceSample(allExpandFlow)
                            SurfaceColorSample(allExpandFlow)
                            SurfaceShapeSample(allExpandFlow)
                            SurfaceBorderSample(allExpandFlow)
                            SurfaceWithBoxSample(allExpandFlow)
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun SurfaceSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem(title = "Surface", allExpandFlow, padding = 20.dp) {
        Surface(
            modifier = Modifier.size(150.dp),
        ) {
            Box {
                Button(
                    onClick = {
                        context.showLongToast("我是按钮 1")
                    },
                    modifier = Modifier.align(Alignment.Center)
                ) {
                    Text(text = "按钮 1")
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun SurfaceSamplePreview() {
    SurfaceSample(remember { MutableStateFlow(true) })
}


@Composable
fun SurfaceColorSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem(title = "Surface（color）", allExpandFlow, padding = 20.dp) {
        Surface(
            modifier = Modifier.size(150.dp),
            color = Color.Red.copy(alpha = 0.5f)
        ) {
            Box {
                Button(
                    onClick = {
                        context.showLongToast("我是按钮 1")
                    },
                    modifier = Modifier.align(Alignment.Center)
                ) {
                    Text(text = "按钮 1")
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun SurfaceColorSamplePreview() {
    SurfaceColorSample(remember { MutableStateFlow(true) })
}


@Composable
fun SurfaceShapeSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem(title = "Surface（shape）", allExpandFlow, padding = 20.dp) {
        Surface(
            modifier = Modifier.size(150.dp),
            color = Color.Red.copy(alpha = 0.5f),
            shape = RoundedCornerShape(20.dp),
        ) {
            Box {
                Button(
                    onClick = {
                        context.showLongToast("我是按钮 1")
                    },
                    modifier = Modifier.align(Alignment.Center)
                ) {
                    Text(text = "按钮 1")
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun SurfaceShapeSamplePreview() {
    SurfaceShapeSample(remember { MutableStateFlow(true) })
}


@Composable
fun SurfaceBorderSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem(title = "Surface（border）", allExpandFlow, padding = 20.dp) {
        Surface(
            modifier = Modifier.size(150.dp),
            border = BorderStroke(2.dp, Color.Red)
        ) {
            Box {
                Button(
                    onClick = {
                        context.showLongToast("我是按钮 1")
                    },
                    modifier = Modifier.align(Alignment.Center)
                ) {
                    Text(text = "按钮 1")
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun SurfaceBorderSamplePreview() {
    SurfaceBorderSample(remember { MutableStateFlow(true) })
}


@Composable
fun SurfaceWithBoxSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem(title = "Surface（WithBox）", allExpandFlow, padding = 20.dp) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "与 Box 相比 Surface 会拦截触摸事件导致它下面的所有节点都无法点击")
            Spacer(modifier = Modifier.size(10.dp))
            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                mainAxisSpacing = 10.dp,
                crossAxisSpacing = 10.dp
            ) {
                Column(
                    modifier = Modifier
                        .width(150.dp)
                        .height(200.dp)
                ) {
                    Text(text = "按钮 2 上面是 Surface，所以按钮 2 不可点击")
                    Text(text = "* 绿色层是 Surface", fontSize = 12.sp, color = Color.Gray)
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Red.copy(alpha = 0.5f))
                            .padding(4.dp)
                    ) {
                        Button(
                            onClick = {
                                context.showLongToast("我是按钮 2")
                            },
                            modifier = Modifier.align(Alignment.TopCenter)
                        ) {
                            Text(text = "按钮 2")
                        }
                        Surface(
                            modifier = Modifier.fillMaxSize(),
                            color = Color.Green.copy(alpha = 0.5f)
                        ) {
                            Box {
                                Button(
                                    onClick = {
                                        context.showLongToast("我是按钮 1")
                                    },
                                    modifier = Modifier.align(Alignment.BottomCenter)
                                ) {
                                    Text(text = "按钮 1")
                                }
                            }
                        }
                    }
                }

                Column(
                    modifier = Modifier
                        .width(150.dp)
                        .height(200.dp)
                ) {
                    Text(text = "按钮 2 上面是 Box，所以按钮 2 依然可以点击")
                    Text(text = "* 绿色层是 Box", fontSize = 12.sp, color = Color.Gray)
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Red.copy(alpha = 0.5f))
                            .padding(4.dp)
                    ) {
                        Button(
                            onClick = {
                                context.showLongToast("我是按钮 2")
                            },
                            modifier = Modifier.align(Alignment.TopCenter)
                        ) {
                            Text(text = "按钮 2")
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.Green.copy(alpha = 0.5f)),
                        ) {
                            Button(
                                onClick = {
                                    context.showLongToast("我是按钮 1")
                                },
                                modifier = Modifier.align(Alignment.BottomCenter)
                            ) {
                                Text(text = "按钮 1")
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun SurfaceWithBoxSamplePreview() {
    SurfaceWithBoxSample(remember { MutableStateFlow(true) })
}