package com.github.panpf.android.compose.samples.ui.animation

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.StartOffset
import androidx.compose.animation.core.StartOffsetType
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.repeatable
import androidx.compose.animation.core.snap
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.panpf.android.compose.samples.R
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem3
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.ExpandableText
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
import com.github.panpf.tools4j.math.ktx.format
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class AnimationSpecFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "AnimationSpec"
    }

    @Composable
    override fun DrawContent() {
        val desc = remember {
            """
                |AnimationSpec ?????????????????????????????????????????????:
                |   * FiniteAnimationSpec???????????????
                |       - DurationBasedAnimationSpec????????????????????????
                |           ~ TweenSpec??????????????????????????????????????????????????????????????????????????????
                |           ~ KeyframesSpec????????????????????????????????????????????????????????????????????????????????????
                |           ~ SnapSpec??????????????????????????????????????????????????????????????????
                |       - RepeatableSpec??????????????? DurationBasedAnimationSpec ??????
                |       - SpringSpec?????????????????????????????????????????????
                |   * InfiniteAnimationSpec???????????????
            """.trimMargin()
        }
        ExpandableLayout { allExpandFlow ->
            ExpandableText(text = desc, modifier = Modifier.padding(20.dp))
            AnimationSpecSpringSample(allExpandFlow)
            AnimationSpecTweenSample(allExpandFlow)
            AnimationSpecKeyframesSample(allExpandFlow)
            AnimationSpecRepeatableSample(allExpandFlow)
            AnimationSpecInfiniteRepeatableSample(allExpandFlow)
            AnimationSpecSnapSample(allExpandFlow)
        }
    }
}


@Composable
private fun AnimationSpecSpringSample(allExpandFlow: Flow<Boolean>) {
    val desc = """
        |????????????????????????????????????????????????????????????????????????????????????
        |   * dampingRatio?????????????????????????????????????????????????????????????????? 1f???Spring.DampingRatioNoBouncy???????????????????????????
        |   * stiffness??????????????????????????????????????????????????????????????? 1500f???Spring.StiffnessMedium???
        |   * visibilityThreshold????????????????????????????????? null
    """.trimMargin()
    var enabled by remember { mutableStateOf(false) }
    val attrList = listOf(
        "dampingRatio" to listOf(
            "0.2f (HighBouncy)" to Spring.DampingRatioHighBouncy,
            "0.5f (MediumBouncy)" to Spring.DampingRatioMediumBouncy,
            "0.75f (LowBouncy)" to Spring.DampingRatioLowBouncy,
            "1f (NoBouncy)" to Spring.DampingRatioNoBouncy,
            "2f" to 2f,
        ).map {
            it.first to animateFloatAsState(
                targetValue = if (enabled) 1f else 0f,
                animationSpec = spring(dampingRatio = it.second)
            )
        },
        "stiffness" to listOf(
            "10000f (High)" to Spring.StiffnessHigh,
            "1500f (Medium)" to Spring.StiffnessMedium,
            "400f (MediumLow)" to Spring.StiffnessMediumLow,
            "200f (Low)" to Spring.StiffnessLow,
            "50f (VeryLow)" to Spring.StiffnessVeryLow,
        ).map {
            it.first to animateFloatAsState(
                targetValue = if (enabled) 1f else 0f,
                animationSpec = spring(stiffness = it.second)
            )
        }
    )
    ExpandableItem3(
        title = "AnimationSpec???spring???",
        allExpandFlow,
        padding = 20.dp,
        desc = desc,
    ) {
        Row {
            attrList.forEachIndexed { index, attrPair ->
                if (index > 0) {
                    Spacer(modifier = Modifier.width(20.dp))
                }
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = attrPair.first, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(10.dp))
                    attrPair.second.forEachIndexed { index, pair ->
                        if (index > 0) {
                            Spacer(modifier = Modifier.height(16.dp))
                        }
                        Text(
                            text = "${pair.first}: ${pair.second.value.format("", 1)}",
                            fontSize = 12.sp
                        )
                        BoxWithConstraints(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(20.dp)
                                .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.5f))
                        ) {
                            val blockSize = maxHeight
                            Box(
                                modifier = Modifier
                                    .offset((maxWidth - blockSize) * pair.second.value, 0.dp)
                                    .size(blockSize)
                                    .background(MaterialTheme.colorScheme.tertiary)
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = { enabled = !enabled }) {
            Text(text = "Play")
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun AnimationSpecSpringSamplePreview() {
    AnimationSpecSpringSample(remember { MutableStateFlow(true) })
}


@Composable
private fun AnimationSpecTweenSample(allExpandFlow: Flow<Boolean>) {
    val desc = """
        |??????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
        |   * durationMillis????????????????????????????????????????????????????????????????????????????????? 300???AnimationConstants.DefaultDurationMillis???
        |   * delayMillis?????????????????????????????????????????????????????????????????? 0
        |   * easing??????????????????????????????????????? FastOutSlowInEasing
    """.trimMargin()
    var enabled by remember { mutableStateOf(false) }
    val attrList = listOf(
        "durationMillis" to listOf(
            "100\n" to 100,
            "300\n (Default)" to 300,
            "1000\n" to 1000,
            "3000\n" to 3000,
        ).map {
            it.first to animateFloatAsState(
                targetValue = if (enabled) 1f else 0f,
                animationSpec = tween(durationMillis = it.second)
            )
        },
        "delayMillis" to listOf(
            "0\n (Default)" to 0,
            "300\n" to 300,
            "1000\n" to 1000,
            "3000\n" to 3000,
        ).map {
            it.first to animateFloatAsState(
                targetValue = if (enabled) 1f else 0f,
                animationSpec = tween(delayMillis = it.second)
            )
        },
        "easing" to listOf(
            "FastOutSlowIn\n (Default)" to FastOutSlowInEasing,
            "LinearOutSlowIn\n" to LinearOutSlowInEasing,
            "FastOutLinearIn\n" to FastOutLinearInEasing,
            "Linear\n" to LinearEasing,
        ).map {
            it.first to animateFloatAsState(
                targetValue = if (enabled) 1f else 0f,
                animationSpec = tween(easing = it.second, durationMillis = 2000)
            )
        }
    )
    ExpandableItem3(
        title = "AnimationSpec???tween???",
        allExpandFlow,
        padding = 20.dp,
        desc = desc,
    ) {
        Row {
            attrList.forEachIndexed { index, attrPair ->
                if (index > 0) {
                    Spacer(modifier = Modifier.width(20.dp))
                }
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = attrPair.first, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(10.dp))
                    attrPair.second.forEachIndexed { index, pair ->
                        if (index > 0) {
                            Spacer(modifier = Modifier.height(16.dp))
                        }
                        Text(
                            text = "${pair.first}: ${pair.second.value.format("", 1)}",
                            fontSize = 12.sp
                        )
                        BoxWithConstraints(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(20.dp)
                                .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.5f))
                        ) {
                            val blockSize = maxHeight
                            Box(
                                modifier = Modifier
                                    .offset((maxWidth - blockSize) * pair.second.value, 0.dp)
                                    .size(blockSize)
                                    .background(MaterialTheme.colorScheme.tertiary)
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = { enabled = !enabled }) {
            Text(text = "Play")
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun AnimationSpecTweenSamplePreview() {
    AnimationSpecTweenSample(remember { MutableStateFlow(true) })
}


@Composable
private fun AnimationSpecKeyframesSample(allExpandFlow: Flow<Boolean>) {
    val desc = """
        |keyframes ?????????????????????????????????????????????????????????keyframes ??????????????????????????????????????????????????????????????????????????? Easing
        |
        |?????????????????? 0 ??????????????????????????????????????????????????????????????????????????????????????????????????????????????????
        |
        |?????????????????????
        |   0.2f at 200 with LinearEasing
        |   0.4f at 400 with LinearOutSlowInEasing
        |   0.6f at 500 with FastOutLinearInEasing
        |   0.4f at 600 with FastOutLinearInEasing
        |   0.8f at 800 with FastOutSlowInEasing
    """.trimMargin()
    val offsetAnimatable = remember { Animatable(0f) }
    val coroutineScope = rememberCoroutineScope()
    ExpandableItem3(
        title = "AnimationSpec???keyframes???",
        allExpandFlow,
        padding = 20.dp,
        desc = desc,
    ) {
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxWidth()
                .height(20.dp)
                .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.5f))
        ) {
            val blockSize = maxHeight
            Box(
                modifier = Modifier
                    .offset((maxWidth - blockSize) * offsetAnimatable.value, 0.dp)
                    .size(blockSize)
                    .background(MaterialTheme.colorScheme.tertiary)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = {
            coroutineScope.launch {
                offsetAnimatable.snapTo(0f)
                offsetAnimatable.animateTo(
                    targetValue = 1f,
                    animationSpec = keyframes {
                        durationMillis = 1000
                        delayMillis = 100
                        0.2f at 200 with LinearEasing
                        0.4f at 400 with LinearOutSlowInEasing
                        0.6f at 500 with FastOutLinearInEasing
                        0.4f at 600 with FastOutLinearInEasing
                        0.8f at 800 with FastOutSlowInEasing
                    }
                )
            }
        }) {
            Text(text = "Play")
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun AnimationSpecKeyframesSamplePreview() {
    AnimationSpecKeyframesSample(remember { MutableStateFlow(true) })
}


@Composable
private fun AnimationSpecRepeatableSample(allExpandFlow: Flow<Boolean>) {
    val desc = """
        |repeatable ??????????????????????????????????????????tween ??? keyFrames??????????????????
        |   * iterations?????????????????????
        |   * animation????????????????????????????????? tween ??? keyFrames  
        |   * repeatMode??????????????????????????????????????????????????????
        |       - RepeatMode.Restart??? ????????????
        |       - RepeatMode.Reverse??? ????????????
        |   * initialStartOffset???????????????????????????
        |       - offsetMillis??? ???????????????????????????
        |       - offsetType??? ???????????????
        |           ~ StartOffsetType.Delay??? ??????????????????????????????????????????
        |           ~ StartOffsetType.FastForward??? ??????????????????????????????????????????????????????
    """.trimMargin()
    val coroutineScope = rememberCoroutineScope()
    val list = listOf(
        "iterations=2" to (remember { Animatable(0f) } to repeatable<Float>(
            iterations = 3,
            animation = tween(durationMillis = 1000),
            repeatMode = RepeatMode.Restart
        )),
        "iterations=4" to (remember { Animatable(0f) } to repeatable(
            iterations = 4,
            animation = tween(durationMillis = 1000),
            repeatMode = RepeatMode.Restart
        )),
        "repeatMode=Restart" to (remember { Animatable(0f) } to repeatable(
            iterations = 3,
            animation = tween(durationMillis = 1000),
            repeatMode = RepeatMode.Restart
        )),
        "repeatMode=Reverse" to (remember { Animatable(0f) } to repeatable(
            iterations = 3,
            animation = tween(durationMillis = 1000),
            repeatMode = RepeatMode.Reverse
        )),
        "durationMillis=1000" to (remember { Animatable(0f) } to repeatable(
            iterations = 3,
            animation = tween(durationMillis = 1000),
            repeatMode = RepeatMode.Restart
        )),
        "durationMillis=2000" to (remember { Animatable(0f) } to repeatable(
            iterations = 3,
            animation = tween(durationMillis = 2000),
            repeatMode = RepeatMode.Restart
        )),
        "easing=FastOutSlowIn" to (remember { Animatable(0f) } to repeatable(
            iterations = 3,
            animation = tween(durationMillis = 2000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Restart
        )),
        "easing=Linear" to (remember { Animatable(0f) } to repeatable(
            iterations = 3,
            animation = tween(durationMillis = 2000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )),
        "offsetType=Delay" to (remember { Animatable(0f) } to repeatable(
            iterations = 3,
            animation = tween(durationMillis = 2000),
            repeatMode = RepeatMode.Restart,
            initialStartOffset = StartOffset(
                offsetMillis = 1000,
                offsetType = StartOffsetType.Delay
            )
        )),
        "offsetType=FastForward" to (remember { Animatable(0f) } to repeatable(
            iterations = 3,
            animation = tween(durationMillis = 2000),
            repeatMode = RepeatMode.Restart,
            initialStartOffset = StartOffset(
                offsetMillis = 1000,
                offsetType = StartOffsetType.FastForward
            )
        )),
    )
    ExpandableItem3(
        title = "AnimationSpec???repeatable???",
        allExpandFlow,
        padding = 20.dp,
        desc = desc,
    ) {
        list.chunked(2).forEachIndexed { index, pairs ->
            if (index > 0) {
                Spacer(modifier = Modifier.height(10.dp))
            }
            Row {
                pairs.forEachIndexed { index1, pair ->
                    if (index1 > 0) {
                        Spacer(modifier = Modifier.width(20.dp))
                    }
                    Column(modifier = Modifier.weight(1f)) {
                        Text(text = pair.first, fontSize = 12.sp)
                        Image(
                            painter = painterResource(id = R.drawable.dog_hor),
                            contentDescription = "dog",
                            modifier = Modifier
                                .size(40.dp)
                                .clip(RoundedCornerShape(50))
                                .rotate(360 * pair.second.first.value),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = {
            list.forEach { pair ->
                coroutineScope.launch {
                    pair.second.first.snapTo(0f)
                    pair.second.first.animateTo(
                        targetValue = 1f,
                        animationSpec = pair.second.second
                    )
                }
            }
        }) {
            Text(text = "Play")
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun AnimationSpecRepeatableSamplePreview() {
    AnimationSpecRepeatableSample(remember { MutableStateFlow(true) })
}


@Composable
private fun AnimationSpecInfiniteRepeatableSample(allExpandFlow: Flow<Boolean>) {
    val desc = """
        |infiniteRepeatable ??? Repeatable ?????????????????????????????????????????????
        |   * animation????????????????????????????????? tween ??? keyFrames  
        |   * repeatMode??????????????????????????????????????????????????????
        |       - RepeatMode.Restart??? ????????????
        |       - RepeatMode.Reverse??? ????????????
        |   * initialStartOffset???????????????????????????
        |       - offsetMillis??? ???????????????????????????
        |       - offsetType??? ???????????????
        |           ~ StartOffsetType.Delay??? ??????????????????????????????????????????
        |           ~ StartOffsetType.FastForward??? ??????????????????????????????????????????????????????
    """.trimMargin()
    val list = listOf(
        "repeatMode=Restart" to (remember { Animatable(0f) } to infiniteRepeatable<Float>(
            animation = tween(durationMillis = 1000),
            repeatMode = RepeatMode.Restart
        )),
        "repeatMode=Reverse" to (remember { Animatable(0f) } to infiniteRepeatable(
            animation = tween(durationMillis = 1000),
            repeatMode = RepeatMode.Reverse
        )),
        "durationMillis=1000" to (remember { Animatable(0f) } to infiniteRepeatable(
            animation = tween(durationMillis = 1000),
            repeatMode = RepeatMode.Restart
        )),
        "durationMillis=2000" to (remember { Animatable(0f) } to infiniteRepeatable(
            animation = tween(durationMillis = 2000),
            repeatMode = RepeatMode.Restart
        )),
        "easing=FastOutSlowIn" to (remember { Animatable(0f) } to infiniteRepeatable(
            animation = tween(durationMillis = 2000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Restart
        )),
        "easing=Linear" to (remember { Animatable(0f) } to infiniteRepeatable(
            animation = tween(durationMillis = 2000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )),
        "offsetType=Delay" to (remember { Animatable(0f) } to infiniteRepeatable(
            animation = tween(durationMillis = 2000),
            repeatMode = RepeatMode.Restart,
            initialStartOffset = StartOffset(
                offsetMillis = 1000,
                offsetType = StartOffsetType.Delay
            )
        )),
        "offsetType=FastForward" to (remember { Animatable(0f) } to infiniteRepeatable(
            animation = tween(durationMillis = 2000),
            repeatMode = RepeatMode.Restart,
            initialStartOffset = StartOffset(
                offsetMillis = 1000,
                offsetType = StartOffsetType.FastForward
            )
        )),
    )

    list.forEach { pair ->
        LaunchedEffect(list) {
            pair.second.first.animateTo(
                targetValue = 1f,
                animationSpec = pair.second.second
            )
        }
    }

    ExpandableItem3(
        title = "AnimationSpec???infiniteRepeatable???",
        allExpandFlow,
        padding = 20.dp,
        desc = desc,
    ) {
        list.chunked(2).forEachIndexed { index, pairs ->
            if (index > 0) {
                Spacer(modifier = Modifier.height(10.dp))
            }
            Row {
                pairs.forEachIndexed { index1, pair ->
                    if (index1 > 0) {
                        Spacer(modifier = Modifier.width(20.dp))
                    }
                    Column(modifier = Modifier.weight(1f)) {
                        Text(text = pair.first, fontSize = 12.sp)
                        Image(
                            painter = painterResource(id = R.drawable.dog_hor),
                            contentDescription = "dog",
                            modifier = Modifier
                                .size(40.dp)
                                .clip(RoundedCornerShape(50))
                                .rotate(360 * pair.second.first.value),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun AnimationSpecInfiniteRepeatableSamplePreview() {
    AnimationSpecInfiniteRepeatableSample(remember { MutableStateFlow(true) })
}


@Composable
private fun AnimationSpecSnapSample(allExpandFlow: Flow<Boolean>) {
    val desc = """
        |snap ???????????? AnimationSpec??????????????????????????????????????????????????????????????????????????????
        |   * delayMillis?????????????????????????????????????????????????????????????????? 0
    """.trimMargin()
    val list = listOf(
        "delayMillis=0" to (remember { Animatable(0f) } to snap<Float>()),
        "delayMillis=1000" to (remember { Animatable(0f) } to snap(delayMillis = 1000)),
    )

    val coroutineScope = rememberCoroutineScope()
    ExpandableItem3(
        title = "AnimationSpec???snap???",
        allExpandFlow,
        padding = 20.dp,
        desc = desc,
    ) {
        list.chunked(2).forEachIndexed { index, pairs ->
            if (index > 0) {
                Spacer(modifier = Modifier.height(10.dp))
            }
            Row {
                pairs.forEachIndexed { index1, pair ->
                    if (index1 > 0) {
                        Spacer(modifier = Modifier.width(20.dp))
                    }
                    Column(modifier = Modifier.weight(1f)) {
                        Text(text = pair.first, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(10.dp))
                        BoxWithConstraints(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(20.dp)
                                .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.5f))
                        ) {
                            val blockSize = maxHeight
                            Box(
                                modifier = Modifier
                                    .offset((maxWidth - blockSize) * pair.second.first.value, 0.dp)
                                    .size(blockSize)
                                    .background(MaterialTheme.colorScheme.tertiary)
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = {
            list.forEach { pair ->
                coroutineScope.launch {
                    pair.second.first.snapTo(0f)
                    pair.second.first.animateTo(
                        targetValue = 1f,
                        animationSpec = pair.second.second
                    )
                }
            }
        }) {
            Text(text = "Play")
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun AnimationSpecSnapSamplePreview() {
    AnimationSpecSnapSample(remember { MutableStateFlow(true) })
}