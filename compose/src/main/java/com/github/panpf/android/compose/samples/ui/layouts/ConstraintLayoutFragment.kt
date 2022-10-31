package com.github.panpf.android.compose.samples.ui.layouts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.ToolbarFragment
import com.github.panpf.android.compose.samples.ui.base.theme.MyTheme
import com.google.accompanist.flowlayout.FlowRow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class ConstraintLayoutFragment : ToolbarFragment() {

    override fun createView(
        toolbar: Toolbar,
        inflater: LayoutInflater,
        parent: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        toolbar.title = "ConstraintLayout"
        return ComposeView(inflater.context).apply {
            setContent {
                MyTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        ExpandableLayout { allExpandFlow ->
                            ConstraintLayoutConstrainAsSample(allExpandFlow)
                            // todo 约束
                            // 引导线
                            // 屏蔽线
                            // 链
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun ConstraintLayoutConstrainAsSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem(title = "ConstraintLayout（constrainAs）", allExpandFlow, padding = 20.dp) {
        FlowRow(mainAxisSpacing = 10.dp, crossAxisSpacing = 10.dp) {
            Column {
                Text(text = "linkTo", modifier = Modifier.align(Alignment.CenterHorizontally))
                ConstraintLayout(
                    modifier = Modifier
                        .size(160.dp)
                        .background(Color.Red.copy(alpha = 0.5f))
                ) {
                    val (menu, action1, action2, action3, action4) = createRefs()
                    Text(
                        text = "",
                        modifier = Modifier
                            .size(40.dp)
                            .background(Color.Red.copy(alpha = 0.5f))
                            .constrainAs(menu) {
                                centerTo(parent)
                            },
                    )
                    Text(
                        text = "",
                        modifier = Modifier
                            .size(26.dp)
                            .background(Color.Green.copy(alpha = 0.5f))
                            .constrainAs(action1) {
                                end.linkTo(menu.start, margin = 10.dp)
                                top.linkTo(menu.top)
                                bottom.linkTo(menu.bottom)
                            },
                    )
                    Text(
                        text = "",
                        modifier = Modifier
                            .size(26.dp)
                            .background(Color.Blue.copy(alpha = 0.5f))
                            .constrainAs(action2) {
                                start.linkTo(menu.start)
                                end.linkTo(menu.end)
                                bottom.linkTo(menu.top, margin = 10.dp)
                            },
                    )
                    Text(
                        text = "",
                        modifier = Modifier
                            .size(26.dp)
                            .background(Color.Yellow.copy(alpha = 0.5f))
                            .constrainAs(action3) {
                                start.linkTo(menu.end, margin = 10.dp)
                                top.linkTo(menu.top)
                                bottom.linkTo(menu.bottom)
                            },
                    )
                    Text(
                        text = "",
                        modifier = Modifier
                            .size(26.dp)
                            .background(Color.Cyan.copy(alpha = 0.5f))
                            .constrainAs(action4) {
                                start.linkTo(menu.start)
                                end.linkTo(menu.end)
                                top.linkTo(menu.bottom, margin = 10.dp)
                            },
                    )
                }
            }

            Column {
                Text(text = "circular", modifier = Modifier.align(Alignment.CenterHorizontally))
                ConstraintLayout(
                    modifier = Modifier
                        .size(160.dp)
                        .background(Color.Red.copy(alpha = 0.5f))
                ) {
                    val (menu, action1, action2, action3, action4) = createRefs()
                    Text(
                        text = "",
                        modifier = Modifier
                            .size(40.dp)
                            .background(Color.Red.copy(alpha = 0.5f))
                            .constrainAs(menu) {
                                end.linkTo(parent.end, margin = 20.dp)
                                bottom.linkTo(parent.bottom, margin = 20.dp)
                            },
                    )
                    Text(
                        text = "",
                        modifier = Modifier
                            .size(26.dp)
                            .background(Color.Green.copy(alpha = 0.5f))
                            .constrainAs(action1) {
                                circular(menu, 254f, 60.dp)
                            },
                    )
                    Text(
                        text = "",
                        modifier = Modifier
                            .size(26.dp)
                            .background(Color.Blue.copy(alpha = 0.5f))
                            .constrainAs(action2) {
                                circular(menu, 295f, 60.dp)
                            },
                    )
                    Text(
                        text = "",
                        modifier = Modifier
                            .size(26.dp)
                            .background(Color.Yellow.copy(alpha = 0.5f))
                            .constrainAs(action3) {
                                circular(menu, 336f, 60.dp)
                            },
                    )
                    Text(
                        text = "",
                        modifier = Modifier
                            .size(26.dp)
                            .background(Color.Cyan.copy(alpha = 0.5f))
                            .constrainAs(action4) {
                                circular(menu, 16f, 60.dp)
                            },
                    )
                }
            }

            Column {
                Text(text = "centerTo", modifier = Modifier.align(Alignment.CenterHorizontally))
                ConstraintLayout(
                    modifier = Modifier
                        .size(160.dp)
                        .background(Color.Red.copy(alpha = 0.5f))
                ) {
                    val (menu, action1) = createRefs()
                    Text(
                        text = "",
                        modifier = Modifier
                            .size(40.dp)
                            .background(Color.Red.copy(alpha = 0.5f))
                            .constrainAs(menu) {
                                centerTo(parent)
                            },
                    )
                    Text(
                        text = "",
                        modifier = Modifier
                            .size(26.dp)
                            .background(Color.Green.copy(alpha = 0.5f))
                            .constrainAs(action1) {
                                centerTo(menu)
                            },
                    )
                }
            }

            Column {
                Text(
                    text = "centerHorizontallyTo",
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                ConstraintLayout(
                    modifier = Modifier
                        .size(160.dp)
                        .background(Color.Red.copy(alpha = 0.5f))
                ) {
                    val (menu, action1) = createRefs()
                    Text(
                        text = "",
                        modifier = Modifier
                            .size(40.dp)
                            .background(Color.Red.copy(alpha = 0.5f))
                            .constrainAs(menu) {
                                centerTo(parent)
                            },
                    )
                    Text(
                        text = "",
                        modifier = Modifier
                            .size(26.dp)
                            .background(Color.Green.copy(alpha = 0.5f))
                            .constrainAs(action1) {
                                centerHorizontallyTo(menu)
                            },
                    )
                }
            }

            Column {
                Text(
                    text = "centerVerticallyTo",
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                ConstraintLayout(
                    modifier = Modifier
                        .size(160.dp)
                        .background(Color.Red.copy(alpha = 0.5f))
                ) {
                    val (menu, action1) = createRefs()
                    Text(
                        text = "",
                        modifier = Modifier
                            .size(40.dp)
                            .background(Color.Red.copy(alpha = 0.5f))
                            .constrainAs(menu) {
                                centerTo(parent)
                            },
                    )
                    Text(
                        text = "",
                        modifier = Modifier
                            .size(26.dp)
                            .background(Color.Green.copy(alpha = 0.5f))
                            .constrainAs(action1) {
                                centerVerticallyTo(menu)
                            },
                    )
                }
            }

            Column {
                Text(
                    text = "centerAround",
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                ConstraintLayout(
                    modifier = Modifier
                        .size(160.dp)
                        .background(Color.Red.copy(alpha = 0.5f))
                ) {
                    val (menu, action1, action2, action3, action4) = createRefs()
                    Text(
                        text = "",
                        modifier = Modifier
                            .size(40.dp)
                            .background(Color.Red.copy(alpha = 0.5f))
                            .constrainAs(menu) {
                                centerTo(parent)
                            },
                    )
                    Text(
                        text = "",
                        modifier = Modifier
                            .size(26.dp)
                            .background(Color.Green.copy(alpha = 0.5f))
                            .constrainAs(action1) {
                                centerAround(menu.top)
                                centerAround(menu.start)
                            },
                    )
                    Text(
                        text = "",
                        modifier = Modifier
                            .size(26.dp)
                            .background(Color.Blue.copy(alpha = 0.5f))
                            .constrainAs(action2) {
                                centerAround(menu.top)
                                centerAround(menu.end)
                            },
                    )
                    Text(
                        text = "",
                        modifier = Modifier
                            .size(26.dp)
                            .background(Color.Yellow.copy(alpha = 0.5f))
                            .constrainAs(action3) {
                                centerAround(menu.bottom)
                                centerAround(menu.start)
                            },
                    )
                    Text(
                        text = "",
                        modifier = Modifier
                            .size(26.dp)
                            .background(Color.Cyan.copy(alpha = 0.5f))
                            .constrainAs(action4) {
                                centerAround(menu.bottom)
                                centerAround(menu.end)
                            },
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun ConstraintLayoutConstrainAsSamplePreview() {
    ConstraintLayoutConstrainAsSample(remember { MutableStateFlow(true) })
}


@Composable
fun ConstraintLayoutBarrierSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem(title = "ConstraintLayout（Barrier）", allExpandFlow, padding = 20.dp) {
        FlowRow(mainAxisSpacing = 10.dp, crossAxisSpacing = 10.dp) {
            Column {
                Text(text = "linkTo", modifier = Modifier.align(Alignment.CenterHorizontally))
                ConstraintLayout(
                    modifier = Modifier
                        .size(160.dp)
                        .background(Color.Red.copy(alpha = 0.5f))
                ) {
                    val (text1, text2, text3, text4) = createRefs()
                    Text(
                        text = "",
                        modifier = Modifier
                            .height(26.dp)
                            .width(60.dp)
                            .background(Color.Red.copy(alpha = 0.5f))
                            .constrainAs(text1) {
                                start.linkTo(parent.start)
                                top.linkTo(parent.top)
                            })
                    Text(
                        text = "",
                        modifier = Modifier
                            .height(26.dp)
                            .width(20.dp)
                            .background(Color.Magenta.copy(alpha = 0.5f))
                            .constrainAs(text2) {
                                start.linkTo(parent.start)
                                top.linkTo(text1.bottom)
                            })
                    Text(
                        text = "",
                        modifier = Modifier
                            .height(26.dp)
                            .width(80.dp)
                            .background(Color.White.copy(alpha = 0.5f))
                            .constrainAs(text3) {
                                start.linkTo(parent.start)
                                top.linkTo(text2.bottom)
                            })
                    Text(
                        text = "",
                        modifier = Modifier
                            .height(26.dp)
                            .width(40.dp)
                            .background(Color.Black.copy(alpha = 0.5f))
                            .constrainAs(text4) {
                                start.linkTo(parent.start)
                                top.linkTo(text3.bottom)
                            })
                    val endBarrier = createEndBarrier(text1, text2, text3, text4, margin = 10.dp)

                    val (action1, action2, action3, action4) = createRefs()
                    Text(
                        text = "",
                        modifier = Modifier
                            .size(26.dp)
                            .background(Color.Green.copy(alpha = 0.5f))
                            .constrainAs(action1) {
                                start.linkTo(endBarrier)
                                top.linkTo(parent.top)
                            },
                    )
                    Text(
                        text = "",
                        modifier = Modifier
                            .size(26.dp)
                            .background(Color.Blue.copy(alpha = 0.5f))
                            .constrainAs(action2) {
                                start.linkTo(endBarrier)
                                top.linkTo(action1.bottom)
                            },
                    )
                    Text(
                        text = "",
                        modifier = Modifier
                            .size(26.dp)
                            .background(Color.Yellow.copy(alpha = 0.5f))
                            .constrainAs(action3) {
                                start.linkTo(endBarrier)
                                top.linkTo(action2.bottom)
                            },
                    )
                    Text(
                        text = "",
                        modifier = Modifier
                            .size(26.dp)
                            .background(Color.Cyan.copy(alpha = 0.5f))
                            .constrainAs(action4) {
                                start.linkTo(endBarrier)
                                top.linkTo(action3.bottom)
                            },
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun ConstraintLayoutBarrierSamplePreview() {
    ConstraintLayoutBarrierSample(remember { MutableStateFlow(true) })
}


@Composable
fun ConstraintLayoutGuideLineSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem(title = "ConstraintLayout（GuideLine）", allExpandFlow, padding = 20.dp) {
        FlowRow(mainAxisSpacing = 10.dp, crossAxisSpacing = 10.dp) {
            listOf("start", "end").forEach {
                Column {
                    Text(text = it, modifier = Modifier.align(Alignment.CenterHorizontally))
                    ConstraintLayout(
                        modifier = Modifier
                            .size(160.dp)
                            .background(Color.Red.copy(alpha = 0.5f))
                    ) {
                        val guideLine = if ("start" == it) {
                            createGuidelineFromStart(0.3f)
                        } else {
                            createGuidelineFromEnd(0.3f)
                        }

                        val (action1, action2, action3, action4) = createRefs()
                        Text(
                            text = "",
                            modifier = Modifier
                                .size(26.dp)
                                .background(Color.Green.copy(alpha = 0.5f))
                                .constrainAs(action1) {
                                    start.linkTo(guideLine)
                                    top.linkTo(parent.top)
                                },
                        )
                        Text(
                            text = "",
                            modifier = Modifier
                                .size(26.dp)
                                .background(Color.Blue.copy(alpha = 0.5f))
                                .constrainAs(action2) {
                                    start.linkTo(guideLine)
                                    top.linkTo(action1.bottom)
                                },
                        )
                        Text(
                            text = "",
                            modifier = Modifier
                                .size(26.dp)
                                .background(Color.Yellow.copy(alpha = 0.5f))
                                .constrainAs(action3) {
                                    start.linkTo(guideLine)
                                    top.linkTo(action2.bottom)
                                },
                        )
                        Text(
                            text = "",
                            modifier = Modifier
                                .size(26.dp)
                                .background(Color.Cyan.copy(alpha = 0.5f))
                                .constrainAs(action4) {
                                    start.linkTo(guideLine)
                                    top.linkTo(action3.bottom)
                                },
                        )
                    }
                }
            }

            listOf("top", "bottom").forEach {
                Column {
                    Text(text = it, modifier = Modifier.align(Alignment.CenterHorizontally))
                    ConstraintLayout(
                        modifier = Modifier
                            .size(160.dp)
                            .background(Color.Red.copy(alpha = 0.5f))
                    ) {
                        val guideLine = if ("top" == it) {
                            createGuidelineFromTop(0.3f)
                        } else {
                            createGuidelineFromBottom(0.3f)
                        }

                        val (action1, action2, action3, action4) = createRefs()
                        Text(
                            text = "",
                            modifier = Modifier
                                .size(26.dp)
                                .background(Color.Green.copy(alpha = 0.5f))
                                .constrainAs(action1) {
                                    top.linkTo(guideLine)
                                    start.linkTo(parent.start)
                                },
                        )
                        Text(
                            text = "",
                            modifier = Modifier
                                .size(26.dp)
                                .background(Color.Blue.copy(alpha = 0.5f))
                                .constrainAs(action2) {
                                    top.linkTo(guideLine)
                                    start.linkTo(action1.end)
                                },
                        )
                        Text(
                            text = "",
                            modifier = Modifier
                                .size(26.dp)
                                .background(Color.Yellow.copy(alpha = 0.5f))
                                .constrainAs(action3) {
                                    top.linkTo(guideLine)
                                    start.linkTo(action2.end)
                                },
                        )
                        Text(
                            text = "",
                            modifier = Modifier
                                .size(26.dp)
                                .background(Color.Cyan.copy(alpha = 0.5f))
                                .constrainAs(action4) {
                                    top.linkTo(guideLine)
                                    start.linkTo(action3.end)
                                },
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun ConstraintLayoutGuideLineSamplePreview() {
    ConstraintLayoutGuideLineSample(remember { MutableStateFlow(true) })
}


@Composable
fun ConstraintLayoutChainSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem(title = "ConstraintLayout（Chain）", allExpandFlow, padding = 20.dp) {
        FlowRow(mainAxisSpacing = 10.dp, crossAxisSpacing = 10.dp) {
            listOf("Vertical", "Horizontal").forEach { direction ->
                listOf(
                    ChainStyle.Spread to "Spread",
                    ChainStyle.Packed to "Packed",
                    ChainStyle.SpreadInside to "SpreadInside"
                ).forEach { (chainStyle, name) ->
                    Column {
                        Text(
                            text = "${direction}\n${name}",
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                        ConstraintLayout(
                            modifier = Modifier
                                .size(160.dp)
                                .background(Color.Red.copy(alpha = 0.5f))
                        ) {
                            val (action1, action2, action3, action4) = createRefs()
                            if (direction == "Vertical") {
                                createVerticalChain(
                                    action1, action2, action3, action4, chainStyle = chainStyle
                                )
                            } else {
                                createHorizontalChain(
                                    action1, action2, action3, action4, chainStyle = chainStyle
                                )
                            }
                            Text(
                                text = "",
                                modifier = Modifier
                                    .size(26.dp)
                                    .background(Color.Green.copy(alpha = 0.5f))
                                    .constrainAs(action1) {},
                            )
                            Text(
                                text = "",
                                modifier = Modifier
                                    .size(26.dp)
                                    .background(Color.Blue.copy(alpha = 0.5f))
                                    .constrainAs(action2) {},
                            )
                            Text(
                                text = "",
                                modifier = Modifier
                                    .size(26.dp)
                                    .background(Color.Yellow.copy(alpha = 0.5f))
                                    .constrainAs(action3) {},
                            )
                            Text(
                                text = "",
                                modifier = Modifier
                                    .size(26.dp)
                                    .background(Color.Cyan.copy(alpha = 0.5f))
                                    .constrainAs(action4) {},
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun ConstraintLayoutChainSamplePreview() {
    ConstraintLayoutChainSample(remember { MutableStateFlow(true) })
}