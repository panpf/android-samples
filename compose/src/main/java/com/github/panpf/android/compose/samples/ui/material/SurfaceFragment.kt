package com.github.panpf.android.compose.samples.ui.material

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.MaterialComposeAppBarFragment
import com.github.panpf.android.compose.samples.ui.base.MyColor
import com.github.panpf.android.compose.samples.ui.base.SubtitleText
import com.github.panpf.android.compose.samples.ui.customization.grid.VerticalGrid
import com.github.panpf.tools4a.toast.ktx.showLongToast
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class SurfaceFragment : MaterialComposeAppBarFragment() {

    override fun getTitle(): String {
        return "Surface - Material"
    }

    @Composable
    override fun DrawContent() {
        ExpandableLayout { allExpandFlow ->
            SurfaceSample(allExpandFlow)
            SurfaceWithBoxSample(allExpandFlow)
        }
    }
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun SurfaceSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem(title = "Surface", allExpandFlow, padding = 20.dp) {
        VerticalGrid(
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            Column {
                Text(text = "Default")
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f),
                ) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(text = "小强")
                    }
                }
            }

            Column {
                Text(text = "color")
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f),
                    color = MyColor.TranslucenceRed
                ) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(text = "小强")
                    }
                }
            }

            Column {
                Text(text = "contentColor")
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f),
                    contentColor = MyColor.TranslucenceRed
                ) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(text = "小强")
                    }
                }
            }

            Column {
                Text(text = "shape")
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f),
                    color = MyColor.TranslucenceRed,
                    shape = RoundedCornerShape(20.dp),
                ) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(text = "小强")
                    }
                }
            }

            Column {
                Text(text = "border")
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f),
                    border = BorderStroke(2.dp, Color.Red)
                ) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(text = "小强")
                    }
                }
            }

            Column {
                Text(text = "elevation")
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f),
                    elevation = 10.dp
                ) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(text = "小强")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun SurfaceSamplePreview() {
    SurfaceSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun SurfaceWithBoxSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem(title = "Surface（WithBox）", allExpandFlow, padding = 20.dp) {
        Text(
            text = """与 Box 相比 Surface常用来作为一个屏幕的根节点，原因如下：
            1. Surface 默认有背景
            2. Surface 会拦截触摸事件导致它下面的所有节点都无法点击
            """.trimIndent(),
        )
        Spacer(modifier = Modifier.size(10.dp))
        VerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            Column {
                SubtitleText(text = "按钮 2 上面是 Surface，所以按钮 2 不可点击", line = 2)
                Text(text = "* 绿色层是 Surface", fontSize = 12.sp, color = Color.Gray)
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .background(MyColor.TranslucenceRed)
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
                        color = MyColor.TranslucenceGreen
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

            Column {
                SubtitleText(text = "按钮 2 上面是 Box，所以按钮 2 依然可以点击", line = 2)
                Text(text = "* 绿色层是 Box", fontSize = 12.sp, color = Color.Gray)
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .background(MyColor.TranslucenceRed)
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
                            .background(MyColor.TranslucenceGreen),
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

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun SurfaceWithBoxSamplePreview() {
    SurfaceWithBoxSample(remember { MutableStateFlow(true) })
}