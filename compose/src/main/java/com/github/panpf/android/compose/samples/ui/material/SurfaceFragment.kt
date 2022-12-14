package com.github.panpf.android.compose.samples.ui.material

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
import com.github.panpf.tools4a.toast.ktx.showLongToast
import com.google.accompanist.flowlayout.FlowRow
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
            SurfaceColorSample(allExpandFlow)
            SurfaceShapeSample(allExpandFlow)
            SurfaceBorderSample(allExpandFlow)
            SurfaceWithBoxSample(allExpandFlow)
        }
    }
}


@Composable
private fun SurfaceSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem(title = "Surface", allExpandFlow, padding = 20.dp) {
        Surface(
            modifier = Modifier.size(150.dp),
        ) {
            Box {
                Button(
                    onClick = {
                        context.showLongToast("???????????? 1")
                    },
                    modifier = Modifier.align(Alignment.Center)
                ) {
                    Text(text = "?????? 1")
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


@Composable
private fun SurfaceColorSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem(title = "Surface???color???", allExpandFlow, padding = 20.dp) {
        Surface(
            modifier = Modifier.size(150.dp),
            color = MyColor.TranslucenceRed
        ) {
            Box {
                Button(
                    onClick = {
                        context.showLongToast("???????????? 1")
                    },
                    modifier = Modifier.align(Alignment.Center)
                ) {
                    Text(text = "?????? 1")
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun SurfaceColorSamplePreview() {
    SurfaceColorSample(remember { MutableStateFlow(true) })
}


@Composable
private fun SurfaceShapeSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem(title = "Surface???shape???", allExpandFlow, padding = 20.dp) {
        Surface(
            modifier = Modifier.size(150.dp),
            color = MyColor.TranslucenceRed,
            shape = RoundedCornerShape(20.dp),
        ) {
            Box {
                Button(
                    onClick = {
                        context.showLongToast("???????????? 1")
                    },
                    modifier = Modifier.align(Alignment.Center)
                ) {
                    Text(text = "?????? 1")
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun SurfaceShapeSamplePreview() {
    SurfaceShapeSample(remember { MutableStateFlow(true) })
}


@Composable
private fun SurfaceBorderSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem(title = "Surface???border???", allExpandFlow, padding = 20.dp) {
        Surface(
            modifier = Modifier.size(150.dp),
            border = BorderStroke(2.dp, Color.Red)
        ) {
            Box {
                Button(
                    onClick = {
                        context.showLongToast("???????????? 1")
                    },
                    modifier = Modifier.align(Alignment.Center)
                ) {
                    Text(text = "?????? 1")
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun SurfaceBorderSamplePreview() {
    SurfaceBorderSample(remember { MutableStateFlow(true) })
}


@Composable
private fun SurfaceWithBoxSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem(title = "Surface???WithBox???", allExpandFlow, padding = 20.dp) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "??? Box ?????? Surface????????????????????????????????????????????????????????????\n" +
                        "1. Surface ???????????????" +
                        "2. Surface ??????????????????????????????????????????????????????????????????\n"
            )
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
                    Text(text = "?????? 2 ????????? Surface??????????????? 2 ????????????")
                    Text(text = "* ???????????? Surface", fontSize = 12.sp, color = Color.Gray)
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MyColor.TranslucenceRed)
                            .padding(4.dp)
                    ) {
                        Button(
                            onClick = {
                                context.showLongToast("???????????? 2")
                            },
                            modifier = Modifier.align(Alignment.TopCenter)
                        ) {
                            Text(text = "?????? 2")
                        }
                        Surface(
                            modifier = Modifier.fillMaxSize(),
                            color = MyColor.TranslucenceGreen
                        ) {
                            Box {
                                Button(
                                    onClick = {
                                        context.showLongToast("???????????? 1")
                                    },
                                    modifier = Modifier.align(Alignment.BottomCenter)
                                ) {
                                    Text(text = "?????? 1")
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
                    Text(text = "?????? 2 ????????? Box??????????????? 2 ??????????????????")
                    Text(text = "* ???????????? Box", fontSize = 12.sp, color = Color.Gray)
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MyColor.TranslucenceRed)
                            .padding(4.dp)
                    ) {
                        Button(
                            onClick = {
                                context.showLongToast("???????????? 2")
                            },
                            modifier = Modifier.align(Alignment.TopCenter)
                        ) {
                            Text(text = "?????? 2")
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(MyColor.TranslucenceGreen),
                        ) {
                            Button(
                                onClick = {
                                    context.showLongToast("???????????? 1")
                                },
                                modifier = Modifier.align(Alignment.BottomCenter)
                            ) {
                                Text(text = "?????? 1")
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
private fun SurfaceWithBoxSamplePreview() {
    SurfaceWithBoxSample(remember { MutableStateFlow(true) })
}