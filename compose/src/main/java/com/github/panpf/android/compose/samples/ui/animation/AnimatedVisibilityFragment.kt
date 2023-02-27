package com.github.panpf.android.compose.samples.ui.animation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.expandIn
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.shrinkOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOut
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.panpf.android.compose.samples.R
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem3
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.ExpandableText
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
import com.google.accompanist.flowlayout.FlowRow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class AnimatedVisibilityFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "AnimatedVisibility"
    }

    @Composable
    override fun DrawContent() {
        val desc = """
            AnimatedVisibility 可组合项可以为内容的出现和消失添加动画效果。
            默认情况下，内容以淡入和扩大的方式出现，以淡出和缩小的方式消失。您可以通过指定 EnterTransition 和 ExitTransition 来自定义这种过渡效果。
            AnimatedVisibility 中的内容（直接或间接子项）可以使用 animateEnterExit 修饰符为每个子项指定不同的动画行为
        """.trimIndent()
        ExpandableLayout { allExpandFlow ->
            ExpandableText(text = desc, modifier = Modifier.padding(20.dp))
            AnimatedVisibilitySample(allExpandFlow)
            AnimatedVisibilityEnterExitFadeSample(allExpandFlow)
            AnimatedVisibilityEnterExitScaleSample(allExpandFlow)
            AnimatedVisibilityEnterExitSlideSample(allExpandFlow)
            AnimatedVisibilityEnterExitExpandSample(allExpandFlow)
            // todo 为子项添加进入和退出动画效果 https://developer.android.com/jetpack/compose/animation?hl=zh-cn#animatedvisibility-enter-exit
            // todo 添加自定义动画 https://developer.android.com/jetpack/compose/animation?hl=zh-cn#animatedvisibility-add-custom-animation
        }
    }
}


@Composable
private fun AnimatedVisibilitySample(allExpandFlow: Flow<Boolean>) {
    var show by remember { mutableStateOf(true) }
    ExpandableItem3(title = "AnimatedVisibility", allExpandFlow, padding = 20.dp) {
        AnimatedVisibility(visible = show) {
            Image(
                painter = painterResource(id = R.drawable.dog_hor),
                contentDescription = "dog",
                modifier = Modifier
                    .width(100.dp)
                    .aspectRatio(640 / 427f)
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = { show = !show }) {
            Text(text = if (show) "隐藏" else "显示")
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun AnimatedVisibilitySamplePreview() {
    AnimatedVisibilitySample(remember { MutableStateFlow(true) })
}


@Composable
private fun AnimatedVisibilityEnterExitFadeSample(allExpandFlow: Flow<Boolean>) {
    var show by remember { mutableStateOf(true) }
    ExpandableItem3(
        title = "AnimatedVisibility（EnterExit - fade）",
        allExpandFlow,
        padding = 20.dp
    ) {
        FlowRow(crossAxisSpacing = 20.dp, mainAxisSpacing = 20.dp) {
            listOf(
                "fade" to (fadeIn() to fadeOut()),
                "fade\ninitialAlpha" to (fadeIn(initialAlpha = 0.5f)
                        to fadeOut(targetAlpha = 0.5f)),
            ).forEach { item ->
                Column {
                    Text(text = item.first, modifier = Modifier.height(40.dp), fontSize = 12.sp)
                    Box(
                        modifier = Modifier
                            .size(70.dp)
                            .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)),
                        contentAlignment = Alignment.Center
                    ) {
                        androidx.compose.animation.AnimatedVisibility(
                            visible = show,
                            enter = item.second.first,
                            exit = item.second.second
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.dog_hor),
                                contentDescription = "dog",
                                modifier = Modifier
                                    .size(50.dp)
                                    .clip(RoundedCornerShape(50)),
                                contentScale = ContentScale.Crop,
                            )
                        }
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = { show = !show }) {
            Text(text = if (show) "隐藏" else "显示")
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun AnimatedVisibilityEnterExitFadeSamplePreview() {
    AnimatedVisibilityEnterExitFadeSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun AnimatedVisibilityEnterExitScaleSample(allExpandFlow: Flow<Boolean>) {
    var show by remember { mutableStateOf(true) }
    ExpandableItem3(
        title = "AnimatedVisibility（EnterExit - scale）",
        allExpandFlow,
        padding = 20.dp
    ) {
        FlowRow(crossAxisSpacing = 20.dp, mainAxisSpacing = 20.dp) {
            listOf(
                "scale" to (scaleIn() to scaleOut()),
                "scale\ninitialScale" to (scaleIn(initialScale = 0.5f)
                        to scaleOut(targetScale = 0.5f)),
                "scale\nTopStart" to (scaleIn(transformOrigin = TransformOrigin(0f, 0f))
                        to scaleOut(transformOrigin = TransformOrigin(0f, 0f))),
                "scale\nTopEnd" to (scaleIn(transformOrigin = TransformOrigin(1f, 0f))
                        to scaleOut(transformOrigin = TransformOrigin(1f, 0f))),
                "scale\nBottomStart" to (scaleIn(transformOrigin = TransformOrigin(0f, 1f))
                        to scaleOut(transformOrigin = TransformOrigin(0f, 1f))),
                "scale\nBottomEnd" to (scaleIn(transformOrigin = TransformOrigin(1f, 1f))
                        to scaleOut(transformOrigin = TransformOrigin(1f, 1f))),
            ).forEach { item ->
                Column {
                    Text(text = item.first, modifier = Modifier.height(40.dp), fontSize = 12.sp)
                    Box(
                        modifier = Modifier
                            .size(70.dp)
                            .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.5f))
                            .padding(10.dp)
                            .border(1.dp, MaterialTheme.colorScheme.primary),
                        contentAlignment = Alignment.Center
                    ) {
                        androidx.compose.animation.AnimatedVisibility(
                            visible = show,
                            enter = item.second.first,
                            exit = item.second.second
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.dog_hor),
                                contentDescription = "dog",
                                modifier = Modifier
                                    .size(50.dp)
                                    .clip(RoundedCornerShape(50)),
                                contentScale = ContentScale.Crop,
                            )
                        }
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = { show = !show }) {
            Text(text = if (show) "隐藏" else "显示")
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun AnimatedVisibilityEnterExitScaleSamplePreview() {
    AnimatedVisibilityEnterExitScaleSample(remember { MutableStateFlow(true) })
}


@Composable
private fun AnimatedVisibilityEnterExitSlideSample(allExpandFlow: Flow<Boolean>) {
    var show by remember { mutableStateOf(true) }
    ExpandableItem3(
        title = "AnimatedVisibility（EnterExit - slide）",
        allExpandFlow,
        padding = 20.dp
    ) {
        FlowRow(crossAxisSpacing = 20.dp, mainAxisSpacing = 20.dp) {
            listOf(
                "slide\nTopStart" to (slideIn { IntOffset(-it.width, -it.height) }
                        to slideOut { IntOffset(-it.width, -it.height) }),
                "slide\nTopEnd" to (slideIn { IntOffset(it.width, -it.height) }
                        to slideOut { IntOffset(it.width, -it.height) }),
                "slide\nBottomStart" to (slideIn { IntOffset(-it.width, it.height) }
                        to slideOut { IntOffset(-it.width, it.height) }),
                "slide\nBottomEnd" to (slideIn { IntOffset(it.width, it.height) }
                        to slideOut { IntOffset(it.width, it.height) }),
                "slide\nStart" to (slideInHorizontally { -it } to slideOutHorizontally { -it }),
                "slide\nEnd" to (slideInHorizontally { it } to slideOutHorizontally { it }),
                "slide\nTop" to (slideInVertically { -it } to slideOutVertically { -it }),
                "slide\nBottom" to (slideInVertically { it } to slideOutVertically { it }),
            ).forEach { item ->
                Column {
                    Text(text = item.first, modifier = Modifier.height(40.dp), fontSize = 12.sp)
                    Box(
                        modifier = Modifier
                            .size(70.dp)
                            .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.5f))
                            .padding(10.dp)
                            .border(1.dp, MaterialTheme.colorScheme.primary)
                            .clipToBounds(),
                        contentAlignment = Alignment.Center
                    ) {
                        androidx.compose.animation.AnimatedVisibility(
                            visible = show,
                            enter = item.second.first,
                            exit = item.second.second
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.dog_hor),
                                contentDescription = "dog",
                                modifier = Modifier
                                    .size(50.dp)
                                    .clip(RoundedCornerShape(50)),
                                contentScale = ContentScale.Crop,
                            )
                        }
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = { show = !show }) {
            Text(text = if (show) "隐藏" else "显示")
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun AnimatedVisibilityEnterExitSlideSamplePreview() {
    AnimatedVisibilityEnterExitSlideSample(remember { MutableStateFlow(true) })
}


@Composable
private fun AnimatedVisibilityEnterExitExpandSample(allExpandFlow: Flow<Boolean>) {
    var show by remember { mutableStateOf(true) }
    ExpandableItem3(
        title = "AnimatedVisibility（EnterExit - expand）",
        allExpandFlow,
        padding = 20.dp
    ) {
        FlowRow(crossAxisSpacing = 20.dp, mainAxisSpacing = 20.dp) {
            listOf(
                "expand\nTopStart" to (expandIn(expandFrom = Alignment.TopStart)
                        to shrinkOut(shrinkTowards = Alignment.TopStart)),
                "expand\nTopEnd" to (expandIn(expandFrom = Alignment.TopEnd)
                        to shrinkOut(shrinkTowards = Alignment.TopEnd)),
                "expand\nBottomStart" to (expandIn(expandFrom = Alignment.BottomStart)
                        to shrinkOut(shrinkTowards = Alignment.BottomStart)),
                "expand\nBottomEnd" to (expandIn(expandFrom = Alignment.BottomEnd)
                        to shrinkOut(shrinkTowards = Alignment.BottomEnd)),
                "expand\nStart" to (expandHorizontally(expandFrom = Alignment.Start)
                        to shrinkHorizontally(shrinkTowards = Alignment.Start)),
                "expand\nEnd" to (expandHorizontally(expandFrom = Alignment.End)
                        to shrinkHorizontally(shrinkTowards = Alignment.End)),
                "expand\nTop" to (expandVertically(expandFrom = Alignment.Top)
                        to shrinkVertically(shrinkTowards = Alignment.Top)),
                "expand\nBottom" to (expandVertically(expandFrom = Alignment.Bottom)
                        to shrinkVertically(shrinkTowards = Alignment.Bottom)),
            ).forEach { item ->
                Column {
                    Text(text = item.first, modifier = Modifier.height(40.dp), fontSize = 12.sp)
                    Box(
                        modifier = Modifier
                            .size(70.dp)
                            .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.5f))
                            .padding(10.dp)
                            .border(1.dp, MaterialTheme.colorScheme.primary),
                        contentAlignment = Alignment.Center
                    ) {
                        androidx.compose.animation.AnimatedVisibility(
                            visible = show,
                            enter = item.second.first,
                            exit = item.second.second
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.dog_hor),
                                contentDescription = "dog",
                                modifier = Modifier
                                    .size(50.dp)
                                    .clip(RoundedCornerShape(50)),
                                contentScale = ContentScale.Crop,
                            )
                        }
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = { show = !show }) {
            Text(text = if (show) "隐藏" else "显示")
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun AnimatedVisibilityEnterExitExpandSamplePreview() {
    AnimatedVisibilityEnterExitExpandSample(remember { MutableStateFlow(true) })
}