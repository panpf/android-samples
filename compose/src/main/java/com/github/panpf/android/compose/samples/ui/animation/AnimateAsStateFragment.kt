package com.github.panpf.android.compose.samples.ui.animation

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.animateOffsetAsState
import androidx.compose.animation.core.animateSizeAsState
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
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem3
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
import com.github.panpf.android.compose.samples.ui.base.MyColor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class AnimateAsStateFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "animate*AsState"
    }

    @Composable
    override fun DrawContent() {
        ExpandableLayout { allExpandFlow ->
            AnimateFloatAsStateSample(allExpandFlow)
            AnimateIntAsStateSample(allExpandFlow)
            AnimateOffsetAsStateSample(allExpandFlow)
//            AnimateIntOffsetAsStateSample(allExpandFlow)
            AnimateDpAsStateSample(allExpandFlow)
            AnimateSizeAsStateSample(allExpandFlow)
//            AnimateIntSizeAsStateSample(allExpandFlow)
//            AnimateRectAsStateSample(allExpandFlow)
            AnimateColorAsStateSample(allExpandFlow)
//            AnimateAsStateSample(allExpandFlow)
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
        Text(text = "alpha: $alpha")
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
private fun AnimateColorAsStateSample(allExpandFlow: Flow<Boolean>) {
    var enabled by remember { mutableStateOf(true) }
    val bgColor by animateColorAsState(targetValue = if (enabled) MyColor.HalfBlue else MyColor.HalfMagenta)
    ExpandableItem3(title = "animateColorAsState", allExpandFlow, padding = 20.dp) {
        Box(
            modifier = Modifier
                .background(bgColor)
                .size(100.dp)
        )
        Text(text = "background: $bgColor")
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
    ExpandableItem3(title = "animateSizeAsState", allExpandFlow, padding = 20.dp) {
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
        Text(text = "size: (${size.width}.dp, ${size.height}.dp)")
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
    ExpandableItem3(title = "animateOffsetAsState", allExpandFlow, padding = 20.dp) {
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
        Text(text = "offset: (${offset.x}.dp, ${offset.y}.dp)")
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
        Text(text = "padding: $padding")
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
private fun AnimateIntAsStateSample(allExpandFlow: Flow<Boolean>) {
    var enabled by remember { mutableStateOf(true) }
    val percent by animateIntAsState(targetValue = if (enabled) 50 else 20)
    ExpandableItem3(title = "animateIntAsState", allExpandFlow, padding = 20.dp) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(percent))
                .background(MaterialTheme.colorScheme.primary)
                .size(100.dp)
        )
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