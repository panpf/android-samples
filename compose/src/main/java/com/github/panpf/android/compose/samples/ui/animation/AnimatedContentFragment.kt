package com.github.panpf.android.compose.samples.ui.animation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.github.panpf.android.compose.samples.R
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem3
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class AnimatedContentFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "AnimatedContent"
    }

    @Composable
    override fun DrawContent() {
        ExpandableLayout { allExpandFlow ->
            AnimatedContentSample(allExpandFlow)
            AnimatedContentTransitionSpecSample(allExpandFlow)
            AnimatedContentTransitionSpecSizeSample(allExpandFlow)
            AnimatedContentTransitionSpecContentAlignmentSample(allExpandFlow)
        }
    }
}


@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun AnimatedContentSample(allExpandFlow: Flow<Boolean>) {
    var count by remember { mutableStateOf(0) }
    ExpandableItem3(title = "AnimatedContent", allExpandFlow, padding = 20.dp) {
        AnimatedContent(targetState = count) { targetCount ->
            // Make sure to use `targetCount`, not `count`.
            Text(text = "Count: $targetCount")
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row {
            FilledIconButton(onClick = { count++ }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_add),
                    contentDescription = "add"
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            FilledIconButton(onClick = { count-- }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_subtract),
                    contentDescription = "subtract"
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun AnimatedContentSamplePreview() {
    AnimatedContentSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun AnimatedContentTransitionSpecSample(allExpandFlow: Flow<Boolean>) {
    var count by remember { mutableStateOf(0) }
    ExpandableItem3(title = "AnimatedContent（transitionSpec）", allExpandFlow, padding = 20.dp) {
        AnimatedContent(
            targetState = count,
            transitionSpec = {
                // Compare the incoming number with the previous number.
                if (targetState > initialState) {
                    // If the target number is larger, it slides up and fades in
                    // while the initial (smaller) number slides up and fades out.
                    ContentTransform(
                        targetContentEnter = slideInVertically { height -> height } + fadeIn(),
                        initialContentExit = slideOutVertically { height -> -height } + fadeOut()
                    )
                } else {
                    // If the target number is smaller, it slides down and fades in
                    // while the initial number slides down and fades out.
                    ContentTransform(
                        targetContentEnter = slideInVertically { height -> -height } + fadeIn(),
                        initialContentExit = slideOutVertically { height -> height } + fadeOut()
                    )
                }.using(
                    // Disable clipping since the faded slide-in/out should
                    // be displayed out of bounds.
                    SizeTransform(clip = false)
                )
            }
        ) { targetCount ->
            // Make sure to use `targetCount`, not `count`.
            Text(text = "$targetCount")
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row {
            FilledIconButton(onClick = { count++ }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_add),
                    contentDescription = "add"
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            FilledIconButton(onClick = { count-- }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_subtract),
                    contentDescription = "subtract"
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun AnimatedContentTransitionSpecSamplePreview() {
    AnimatedContentTransitionSpecSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun AnimatedContentTransitionSpecSizeSample(allExpandFlow: Flow<Boolean>) {
    var expanded by remember { mutableStateOf(false) }
    val text =
        "躲过了暴风雪之后，我们再次起程赶路，在一处斜坡下发现了阿宁他们的马队，同时也发现了海底墓穴影画之中的那一座神秘雪山，赫然出现在了我们的视野尽头。就在我们询问向导如何才能到达那里的时候，顺子却摇头，说我们绝对无法过去。\n          ----摘自《盗墓笔记》 - 云顶天宫（下）第一章 五圣雪山，网址：http://www.daomubiji.com/yun-ding-tian-gong-15.html"
    ExpandableItem3(
        title = "AnimatedContent（transitionSpec - size）",
        allExpandFlow,
        padding = 20.dp
    ) {
        Box(modifier = Modifier
            .background(MaterialTheme.colorScheme.primary)
            .clickable { expanded = !expanded }
        ) {
            AnimatedContent(
                targetState = expanded,
                contentAlignment = Alignment.TopEnd,
                transitionSpec = {
                    ContentTransform(
                        targetContentEnter = fadeIn(animationSpec = tween(150, 150)),
                        initialContentExit = fadeOut(animationSpec = tween(150))
                    ).using(
                        SizeTransform { initialSize, targetSize ->
                            if (targetState) {
                                keyframes {
                                    // Expand horizontally first.
                                    IntSize(targetSize.width, initialSize.height) at 150
                                    durationMillis = 300
                                }
                            } else {
                                keyframes {
                                    // Shrink vertically first.
                                    IntSize(initialSize.width, targetSize.height) at 150
                                    durationMillis = 300
                                }
                            }
                        }
                    )
                }
            ) { targetExpanded ->
                if (targetExpanded) {
                    Text(
                        text = text,
                        color = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    )
                } else {
                    Icon(
                        modifier = Modifier
                            .size(40.dp)
                            .padding(10.dp),
                        painter = painterResource(id = R.drawable.ic_arrow_down),
                        contentDescription = "expand",
                        tint = MaterialTheme.colorScheme.onPrimary,
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun AnimatedContentTransitionSpecSizeSamplePreview() {
    AnimatedContentTransitionSpecSizeSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun AnimatedContentTransitionSpecContentAlignmentSample(allExpandFlow: Flow<Boolean>) {
    var expanded by remember { mutableStateOf(false) }
    val text =
        "躲过了暴风雪之后，我们再次起程赶路，在一处斜坡下发现了阿宁他们的马队，同时也发现了海底墓穴影画之中的那一座神秘雪山，赫然出现在了我们的视野尽头。就在我们询问向导如何才能到达那里的时候，顺子却摇头，说我们绝对无法过去。\n          ----摘自《盗墓笔记》 - 云顶天宫（下）第一章 五圣雪山，网址：http://www.daomubiji.com/yun-ding-tian-gong-15.html"
    ExpandableItem3(
        title = "AnimatedContent（transitionSpec - contentAlignment）",
        allExpandFlow,
        padding = 20.dp
    ) {
        listOf(
            "TopStart" to Alignment.TopStart,
            "TopCenter" to Alignment.TopCenter,
            "TopEnd" to Alignment.TopEnd,
        ).forEachIndexed { index, pair ->
            if (index > 0) {
                Spacer(modifier = Modifier.height(20.dp))
            }
            Text(text = pair.first)
            Box(modifier = Modifier
                .background(MaterialTheme.colorScheme.primary)
                .clickable { expanded = !expanded }
            ) {
                AnimatedContent(
                    targetState = expanded,
                    contentAlignment = pair.second,
                    transitionSpec = {
                        ContentTransform(
                            targetContentEnter = fadeIn(animationSpec = tween(1500, 1500)),
                            initialContentExit = fadeOut(animationSpec = tween(1500))
                        ).using(
                            SizeTransform { initialSize, targetSize ->
                                if (targetState) {
                                    keyframes {
                                        // Expand horizontally first.
                                        IntSize(targetSize.width, initialSize.height) at 1500
                                        durationMillis = 3000
                                    }
                                } else {
                                    keyframes {
                                        // Shrink vertically first.
                                        IntSize(initialSize.width, targetSize.height) at 1500
                                        durationMillis = 3000
                                    }
                                }
                            }
                        )
                    }
                ) { targetExpanded ->
                    if (targetExpanded) {
                        Text(
                            text = text,
                            color = MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp)
                        )
                    } else {
                        Icon(
                            modifier = Modifier
                                .size(40.dp)
                                .padding(10.dp),
                            painter = painterResource(id = R.drawable.ic_arrow_down),
                            contentDescription = "expand",
                            tint = MaterialTheme.colorScheme.onPrimary,
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun AnimatedContentTransitionSpecContentAlignmentSamplePreview() {
    AnimatedContentTransitionSpecContentAlignmentSample(remember { MutableStateFlow(true) })
}