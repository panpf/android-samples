package com.github.panpf.android.compose.samples.ui.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem3
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.HorizontalDashedDivider
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
import com.github.panpf.android.compose.samples.ui.base.MyColor
import com.github.panpf.android.compose.samples.ui.base.VerticalDashedDivider
import com.google.accompanist.flowlayout.FlowRow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class ConstraintLayoutFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "ConstraintLayout"
    }

    @Composable
    override fun DrawContent() {
        ExpandableLayout { allExpandFlow ->
            ConstraintLayoutConstrainAsSample(allExpandFlow)
            ConstraintLayoutBarrierSample(allExpandFlow)
            ConstraintLayoutGuideLineSample(allExpandFlow)
            ConstraintLayoutChainSample(allExpandFlow)
        }
    }
}


@Composable
private fun ConstraintLayoutConstrainAsSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "ConstraintLayout（constrainAs）", allExpandFlow, padding = 20.dp) {
        FlowRow(mainAxisSpacing = 10.dp, crossAxisSpacing = 10.dp) {
            Column {
                Text(text = "linkTo", modifier = Modifier.align(Alignment.CenterHorizontally))
                ConstraintLayout(
                    modifier = Modifier
                        .size(160.dp)
                        .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                        .padding(2.dp)
                ) {
                    val (menu, action1, action2, action3, action4) = createRefs()
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .background(MyColor.HalfRed)
                            .constrainAs(menu) {
                                centerTo(parent)
                            },
                    )
                    Box(
                        modifier = Modifier
                            .size(26.dp)
                            .background(MyColor.HalfGreen)
                            .constrainAs(action1) {
                                end.linkTo(menu.start, margin = 10.dp)
                                top.linkTo(menu.top)
                                bottom.linkTo(menu.bottom)
                            },
                    )
                    Box(
                        modifier = Modifier
                            .size(26.dp)
                            .background(MyColor.HalfBlue)
                            .constrainAs(action2) {
                                start.linkTo(menu.start)
                                end.linkTo(menu.end)
                                bottom.linkTo(menu.top, margin = 10.dp)
                            },
                    )
                    Box(
                        modifier = Modifier
                            .size(26.dp)
                            .background(MyColor.HalfYellow)
                            .constrainAs(action3) {
                                start.linkTo(menu.end, margin = 10.dp)
                                top.linkTo(menu.top)
                                bottom.linkTo(menu.bottom)
                            },
                    )
                    Box(
                        modifier = Modifier
                            .size(26.dp)
                            .background(MyColor.HalfMagenta)
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
                        .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                        .padding(2.dp)
                ) {
                    val (menu, action1, action2, action3, action4) = createRefs()
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .background(MyColor.HalfRed)
                            .constrainAs(menu) {
                                end.linkTo(parent.end, margin = 20.dp)
                                bottom.linkTo(parent.bottom, margin = 20.dp)
                            },
                    )
                    Box(
                        modifier = Modifier
                            .size(26.dp)
                            .background(MyColor.HalfGreen)
                            .constrainAs(action1) {
                                circular(menu, 254f, 60.dp)
                            },
                    )
                    Box(
                        modifier = Modifier
                            .size(26.dp)
                            .background(MyColor.HalfBlue)
                            .constrainAs(action2) {
                                circular(menu, 295f, 60.dp)
                            },
                    )
                    Box(
                        modifier = Modifier
                            .size(26.dp)
                            .background(MyColor.HalfYellow)
                            .constrainAs(action3) {
                                circular(menu, 336f, 60.dp)
                            },
                    )
                    Box(
                        modifier = Modifier
                            .size(26.dp)
                            .background(MyColor.HalfMagenta)
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
                        .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                        .padding(2.dp)
                ) {
                    val (menu, action1) = createRefs()
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .background(MyColor.HalfRed)
                            .constrainAs(menu) {
                                centerTo(parent)
                            },
                    )
                    Box(
                        modifier = Modifier
                            .size(26.dp)
                            .background(MyColor.HalfGreen)
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
                        .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                        .padding(2.dp)
                ) {
                    val (menu, action1) = createRefs()
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .background(MyColor.HalfRed)
                            .constrainAs(menu) {
                                centerTo(parent)
                            },
                    )
                    Box(
                        modifier = Modifier
                            .size(26.dp)
                            .background(MyColor.HalfGreen)
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
                        .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                        .padding(2.dp)
                ) {
                    val (menu, action1) = createRefs()
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .background(MyColor.HalfRed)
                            .constrainAs(menu) {
                                centerTo(parent)
                            },
                    )
                    Box(
                        modifier = Modifier
                            .size(26.dp)
                            .background(MyColor.HalfGreen)
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
                        .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                        .padding(2.dp)
                ) {
                    val (menu, action1, action2, action3, action4) = createRefs()
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .background(MyColor.TranslucenceRed)
                            .constrainAs(menu) {
                                centerTo(parent)
                            },
                    )
                    Box(
                        modifier = Modifier
                            .size(26.dp)
                            .background(MyColor.TranslucenceGreen)
                            .constrainAs(action1) {
                                centerAround(menu.top)
                                centerAround(menu.start)
                            },
                    )
                    Box(
                        modifier = Modifier
                            .size(26.dp)
                            .background(MyColor.TranslucenceBlue)
                            .constrainAs(action2) {
                                centerAround(menu.top)
                                centerAround(menu.end)
                            },
                    )
                    Box(
                        modifier = Modifier
                            .size(26.dp)
                            .background(MyColor.TranslucenceYellow)
                            .constrainAs(action3) {
                                centerAround(menu.bottom)
                                centerAround(menu.start)
                            },
                    )
                    Box(
                        modifier = Modifier
                            .size(26.dp)
                            .background(MyColor.TranslucenceMagenta)
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
private fun ConstraintLayoutConstrainAsSamplePreview() {
    ConstraintLayoutConstrainAsSample(remember { MutableStateFlow(true) })
}


@Composable
private fun ConstraintLayoutBarrierSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "ConstraintLayout（Barrier）", allExpandFlow, padding = 20.dp) {
        Text(text = "以 N 个元素的其中一个边的最大值为基准创建一条线，其它元素可以以这条线为约束，这样的线就是 '屏障线'")
        Spacer(modifier = Modifier.size(10.dp))
        ConstraintLayout(
            modifier = Modifier
                .size(160.dp)
                .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                .padding(2.dp)
        ) {
            val (text1, text2, text3, text4, barrierLineUI) = createRefs()
            Box(
                modifier = Modifier
                    .height(26.dp)
                    .width(60.dp)
                    .background(MyColor.HalfRed)
                    .constrainAs(text1) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                    })
            Box(
                modifier = Modifier
                    .height(26.dp)
                    .width(20.dp)
                    .background(MyColor.HalfYellow)
                    .constrainAs(text2) {
                        start.linkTo(parent.start)
                        top.linkTo(text1.bottom)
                    })
            Box(
                modifier = Modifier
                    .height(26.dp)
                    .width(80.dp)
                    .background(MyColor.HalfGreen)
                    .constrainAs(text3) {
                        start.linkTo(parent.start)
                        top.linkTo(text2.bottom)
                    })
            Box(
                modifier = Modifier
                    .height(26.dp)
                    .width(40.dp)
                    .background(MyColor.HalfBlue)
                    .constrainAs(text4) {
                        start.linkTo(parent.start)
                        top.linkTo(text3.bottom)
                    })
            val barrierLine = createEndBarrier(text1, text2, text3, text4)

            val (action1, action2, action3, action4) = createRefs()
            Box(
                modifier = Modifier
                    .size(26.dp)
                    .background(MyColor.HalfCyan)
                    .constrainAs(action1) {
                        start.linkTo(barrierLine)
                        top.linkTo(parent.top)
                    },
            )
            Box(
                modifier = Modifier
                    .size(26.dp)
                    .background(MyColor.HalfGray)
                    .constrainAs(action2) {
                        start.linkTo(barrierLine)
                        top.linkTo(action1.bottom)
                    },
            )
            Box(
                modifier = Modifier
                    .size(26.dp)
                    .background(MyColor.HalfMagenta)
                    .constrainAs(action3) {
                        start.linkTo(barrierLine)
                        top.linkTo(action2.bottom)
                    },
            )
            Box(
                modifier = Modifier
                    .size(26.dp)
                    .background(MyColor.HalfBlack)
                    .constrainAs(action4) {
                        start.linkTo(barrierLine)
                        top.linkTo(action3.bottom)
                    },
            )

            VerticalDashedDivider(
                color = MyColor.HalfGray,
                modifier = Modifier
                    .constrainAs(barrierLineUI) {
                        start.linkTo(barrierLine)
                    }
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun ConstraintLayoutBarrierSamplePreview() {
    ConstraintLayoutBarrierSample(remember { MutableStateFlow(true) })
}


@Composable
private fun ConstraintLayoutGuideLineSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "ConstraintLayout（GuideLine）", allExpandFlow, padding = 20.dp) {
        Text(text = "从 Layout 的其中一个边为起点创建一条线，其它元素可以以这条线为约束，这样的线就是引导线")
        Spacer(modifier = Modifier.size(10.dp))
        FlowRow(mainAxisSpacing = 10.dp, crossAxisSpacing = 10.dp) {
            listOf("fromStart", "fromEnd").forEach {
                Column {
                    Text(text = it, modifier = Modifier.align(Alignment.CenterHorizontally))
                    ConstraintLayout(
                        modifier = Modifier
                            .size(160.dp)
                            .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                            .padding(2.dp)
                    ) {
                        val guideLine = if ("fromStart" == it) {
                            createGuidelineFromStart(0.3f)
                        } else {
                            createGuidelineFromEnd(0.3f)
                        }
                        val (action1, action2, action3, action4, guideLineUI) = createRefs()

                        Box(
                            modifier = Modifier
                                .size(26.dp)
                                .background(MyColor.HalfRed)
                                .constrainAs(action1) {
                                    start.linkTo(guideLine)
                                    top.linkTo(parent.top)
                                },
                        )
                        Box(
                            modifier = Modifier
                                .size(26.dp)
                                .background(MyColor.HalfYellow)
                                .constrainAs(action2) {
                                    start.linkTo(guideLine)
                                    top.linkTo(action1.bottom)
                                },
                        )
                        Box(
                            modifier = Modifier
                                .size(26.dp)
                                .background(MyColor.HalfGreen)
                                .constrainAs(action3) {
                                    start.linkTo(guideLine)
                                    top.linkTo(action2.bottom)
                                },
                        )
                        Box(
                            modifier = Modifier
                                .size(26.dp)
                                .background(MyColor.HalfBlue)
                                .constrainAs(action4) {
                                    start.linkTo(guideLine)
                                    top.linkTo(action3.bottom)
                                },
                        )

                        VerticalDashedDivider(
                            color = MyColor.HalfGray,
                            modifier = Modifier
                                .constrainAs(guideLineUI) {
                                    end.linkTo(guideLine)
                                }
                        )
                    }
                }
            }

            listOf("fromTop", "fromBottom").forEach {
                Column {
                    Text(text = it, modifier = Modifier.align(Alignment.CenterHorizontally))
                    ConstraintLayout(
                        modifier = Modifier
                            .size(160.dp)
                            .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                            .padding(2.dp)
                    ) {
                        val guideLine = if ("fromTop" == it) {
                            createGuidelineFromTop(0.3f)
                        } else {
                            createGuidelineFromBottom(0.3f)
                        }
                        val (action1, action2, action3, action4, guideLineUI) = createRefs()

                        Box(
                            modifier = Modifier
                                .size(26.dp)
                                .background(MyColor.HalfRed)
                                .constrainAs(action1) {
                                    top.linkTo(guideLine)
                                    start.linkTo(parent.start)
                                },
                        )
                        Box(
                            modifier = Modifier
                                .size(26.dp)
                                .background(MyColor.HalfYellow)
                                .constrainAs(action2) {
                                    top.linkTo(guideLine)
                                    start.linkTo(action1.end)
                                },
                        )
                        Box(
                            modifier = Modifier
                                .size(26.dp)
                                .background(MyColor.HalfGreen)
                                .constrainAs(action3) {
                                    top.linkTo(guideLine)
                                    start.linkTo(action2.end)
                                },
                        )
                        Box(
                            modifier = Modifier
                                .size(26.dp)
                                .background(MyColor.HalfBlue)
                                .constrainAs(action4) {
                                    top.linkTo(guideLine)
                                    start.linkTo(action3.end)
                                },
                        )

                        HorizontalDashedDivider(
                            color = MyColor.HalfGray,
                            modifier = Modifier
                                .constrainAs(guideLineUI) {
                                    bottom.linkTo(guideLine)
                                }
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun ConstraintLayoutGuideLineSamplePreview() {
    ConstraintLayoutGuideLineSample(remember { MutableStateFlow(true) })
}


@Composable
private fun ConstraintLayoutChainSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "ConstraintLayout（Chain）", allExpandFlow, padding = 20.dp) {
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
                                .border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                                .padding(2.dp)
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
                            Box(
                                modifier = Modifier
                                    .size(26.dp)
                                    .background(MyColor.HalfRed)
                                    .constrainAs(action1) {},
                            )
                            Box(
                                modifier = Modifier
                                    .size(26.dp)
                                    .background(MyColor.HalfYellow)
                                    .constrainAs(action2) {},
                            )
                            Box(
                                modifier = Modifier
                                    .size(26.dp)
                                    .background(MyColor.HalfGreen)
                                    .constrainAs(action3) {},
                            )
                            Box(
                                modifier = Modifier
                                    .size(26.dp)
                                    .background(MyColor.HalfBlue)
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
private fun ConstraintLayoutChainSamplePreview() {
    ConstraintLayoutChainSample(remember { MutableStateFlow(true) })
}