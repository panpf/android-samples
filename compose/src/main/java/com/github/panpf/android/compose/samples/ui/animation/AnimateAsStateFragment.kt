package com.github.panpf.android.compose.samples.ui.animation

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.AnimationVector3D
import androidx.compose.animation.core.TwoWayConverter
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.animateOffsetAsState
import androidx.compose.animation.core.animateRectAsState
import androidx.compose.animation.core.animateSizeAsState
import androidx.compose.animation.core.animateValueAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem3
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
import com.github.panpf.android.compose.samples.ui.base.MyColor
import com.github.panpf.tools4j.math.ktx.format
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlin.math.roundToInt

class AnimateAsStateFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "animate*AsState"
    }

    @Composable
    override fun DrawContent() {
        ExpandableLayout { allExpandFlow ->
            AnimateFloatAsStateSample(allExpandFlow)
            AnimateIntAsStateSample(allExpandFlow)
            AnimateDpAsStateSample(allExpandFlow)
            AnimateColorAsStateSample(allExpandFlow)
            AnimateSizeAsStateSample(allExpandFlow)
            AnimateOffsetAsStateSample(allExpandFlow)
            AnimateRectAsStateSample(allExpandFlow)
            AnimateValueAsStateSample(allExpandFlow)
        }
    }
}


@Composable
private fun AnimateFloatAsStateSample(allExpandFlow: Flow<Boolean>) {
    var show by remember { mutableStateOf(true) }
    val alpha by animateFloatAsState(targetValue = if (show) 1.0f else 0.2f)
    ExpandableItem3(title = "animateFloatAsState", allExpandFlow, padding = 20.dp) {
        Box(
            modifier = Modifier
                .alpha(alpha)
                .background(MaterialTheme.colorScheme.primary)
                .size(100.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "alpha: ${alpha.format("", 1)}")
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = { show = !show }) {
            Text(text = "Change Float")
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun AnimateFloatAsStateSamplePreview() {
    AnimateFloatAsStateSample(remember { MutableStateFlow(true) })
}


@Composable
private fun AnimateIntAsStateSample(allExpandFlow: Flow<Boolean>) {
    var enabled by remember { mutableStateOf(true) }
    val percent by animateIntAsState(targetValue = if (enabled) 50 else 20)
    ExpandableItem3(title = "animateIntAsState", allExpandFlow, padding = 20.dp) {
        Box(
            modifier = Modifier
                .border(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.5f))
                .size(100.dp)
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(percent))
                    .background(MaterialTheme.colorScheme.primary)
                    .size(100.dp)
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "RoundedCornerShape percent: $percent")
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = { enabled = !enabled }) {
            Text(text = "Change Int")
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun AnimateIntAsStateSamplePreview() {
    AnimateIntAsStateSample(remember { MutableStateFlow(true) })
}


@Composable
private fun AnimateDpAsStateSample(allExpandFlow: Flow<Boolean>) {
    var enabled by remember { mutableStateOf(true) }
    val padding by animateDpAsState(targetValue = if (enabled) 10.dp else 20.dp)
    ExpandableItem3(title = "animateDpAsState", allExpandFlow, padding = 20.dp) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .border(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.5f))
                .padding(padding)
                .background(MaterialTheme.colorScheme.primary)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "padding: ${padding.value.toInt()}.dp")
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = { enabled = !enabled }) {
            Text(text = "Change Dp")
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun AnimateDpAsStateSamplePreview() {
    AnimateDpAsStateSample(remember { MutableStateFlow(true) })
}


@Composable
private fun AnimateColorAsStateSample(allExpandFlow: Flow<Boolean>) {
    var enabled by remember { mutableStateOf(true) }
    val bgColor by animateColorAsState(targetValue = if (enabled) MyColor.HalfBlue else MyColor.HalfMagenta)
    ExpandableItem3(title = "animateColorAsState", allExpandFlow, padding = 20.dp) {
        Box(
            modifier = Modifier
                .background(bgColor)
                .size(100.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "background: (" +
                    "red=${bgColor.red.format("", 1)}, " +
                    "green=${bgColor.green.format("", 1)}, " +
                    "blue=${bgColor.blue.format("", 1)}" +
                    ")"
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = { enabled = !enabled }) {
            Text(text = "Change Color")
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun AnimateColorAsStateSamplePreview() {
    AnimateColorAsStateSample(remember { MutableStateFlow(true) })
}


@Composable
private fun AnimateSizeAsStateSample(allExpandFlow: Flow<Boolean>) {
    var enabled by remember { mutableStateOf(true) }
    val size by animateSizeAsState(targetValue = if (enabled) Size(40f, 60f) else Size(100f, 100f))
    ExpandableItem3(
        title = "animateSizeAsState\nanimateIntSizeAsState",
        allExpandFlow,
        padding = 20.dp
    ) {
        Box(
            modifier = Modifier
                .border(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.5f))
                .size(100.dp)
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .background(MaterialTheme.colorScheme.primary)
                    .size(size.width.dp, size.height.dp)
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "size: (width=${size.width.toInt()}.dp, height=${size.height.toInt()}.dp)")
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = { enabled = !enabled }) {
            Text(text = "Change Size")
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun AnimateSizeAsStateSamplePreview() {
    AnimateSizeAsStateSample(remember { MutableStateFlow(true) })
}


@Composable
private fun AnimateOffsetAsStateSample(allExpandFlow: Flow<Boolean>) {
    var enabled by remember { mutableStateOf(true) }
    val offset by animateOffsetAsState(
        targetValue = if (enabled)
            Offset(20f, 10f) else Offset(30f, 50f)
    )
    ExpandableItem3(
        title = "animateOffsetAsState\nanimateIntOffsetAsState",
        allExpandFlow,
        padding = 20.dp
    ) {
        Box(
            modifier = Modifier
                .border(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.5f))
                .size(100.dp)
        ) {
            Box(
                modifier = Modifier
                    .offset(offset.x.dp, offset.y.dp)
                    .background(MaterialTheme.colorScheme.primary)
                    .size(40.dp)
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "offset: (x=${offset.x.toInt()}.dp, y=${offset.y.toInt()}.dp)")
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = { enabled = !enabled }) {
            Text(text = "Change Offset")
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun AnimateOffsetAsStateSamplePreview() {
    AnimateOffsetAsStateSample(remember { MutableStateFlow(true) })
}


@Composable
private fun AnimateRectAsStateSample(allExpandFlow: Flow<Boolean>) {
    var enabled by remember { mutableStateOf(true) }
    val rect by animateRectAsState(
        targetValue = if (enabled)
            Rect(20f, 10f, 80f, 50f) else Rect(10f, 20f, 50f, 80f)
    )
    ExpandableItem3(title = "animateRectAsState", allExpandFlow, padding = 20.dp) {
        Box(
            modifier = Modifier
                .border(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.5f))
                .size(100.dp)
        ) {
            Box(
                modifier = Modifier
                    .offset(rect.left.dp, rect.top.dp)
                    .background(MaterialTheme.colorScheme.primary)
                    .size(rect.width.dp, rect.height.dp)
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "Rect: (left=${rect.left.toInt()}.dp, top=${rect.top.toInt()}.dp, right=${rect.right.toInt()}.dp, bottom=${rect.bottom.toInt()}.dp)")
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = { enabled = !enabled }) {
            Text(text = "Change Rect")
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun AnimateRectAsStateSamplePreview() {
    AnimateRectAsStateSample(remember { MutableStateFlow(true) })
}


@Composable
private fun AnimateValueAsStateSample(allExpandFlow: Flow<Boolean>) {
    var enabled by remember { mutableStateOf(true) }
    val block by animateValueAsState(
        targetValue = if (enabled) Block(50f, 50f, 50) else Block(70f, 50f, 20),
        typeConverter = Block.VectorConverter
    )
    ExpandableItem3(title = "animateValueAsState", allExpandFlow, padding = 20.dp) {
        Box(
            modifier = Modifier
                .border(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.5f))
                .size(100.dp)
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(block.roundedCornerPercent))
                    .background(MaterialTheme.colorScheme.primary)
                    .size(block.width.dp, block.height.dp)
                    .align(Alignment.Center)
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "Block(width=${block.width.toInt()}.dp, height=${block.height.toInt()}.dp, roundedCornerPercent=${block.roundedCornerPercent})")
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = { enabled = !enabled }) {
            Text(text = "Change Block")
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun AnimateValueAsStateSamplePreview() {
    AnimateValueAsStateSample(remember { MutableStateFlow(true) })
}

data class Block(val width: Float, val height: Float, val roundedCornerPercent: Int) {

    override fun toString(): String {
        return "Block(width=$width, height=$height, roundedCornerPercent=$roundedCornerPercent)"
    }

    companion object {
        val VectorConverter: TwoWayConverter<Block, AnimationVector3D>
            get() = BlockToVector

        private val BlockToVector: TwoWayConverter<Block, AnimationVector3D> =
            TwoWayConverter(
                { AnimationVector3D(it.width, it.height, it.roundedCornerPercent.toFloat()) },
                { Block(it.v1, it.v2, it.v3.roundToInt()) }
            )
    }
}