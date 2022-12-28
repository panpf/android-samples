package com.github.panpf.android.compose.samples.ui.animation

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem3
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.ExpandableText
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
import com.github.panpf.tools4a.display.ktx.getDisplayMetrics
import com.github.panpf.tools4j.math.ktx.format
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class AnimationSpecFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "AnimationSpec"
    }

    @Composable
    override fun DrawContent() {
        ExpandableLayout { allExpandFlow ->
            AnimationSpecSample(allExpandFlow)
            // todo tween
            // todo keyframes
            // todo repeatable
            // todo infiniteRepeatable
            // todo snap
        }
    }
}

@Composable
private fun AnimationSpecSample(allExpandFlow: Flow<Boolean>) {
    var enabled by remember { mutableStateOf(false) }
    val padding = 20.dp
    LocalContext.current.getDisplayMetrics().widthPixels / 2
    val dampingRatioList = listOf(
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
    }
    val stiffnessList = listOf(
        "10000f (High)" to Spring.StiffnessHigh,
        "1500f (Medium)" to Spring.StiffnessMedium,
        "400f (MediumLow)" to Spring.StiffnessMediumLow,
        "200f (Low)" to Spring.StiffnessLow,
        "50f (VeryLow)" to Spring.StiffnessVeryLow,
        "10f" to 10f,
    ).map {
        it.first to animateFloatAsState(
            targetValue = if (enabled) 1f else 0f,
            animationSpec = spring(stiffness = it.second)
        )
    }
    ExpandableItem3(
        title = "AnimationSpec（spring）",
        allExpandFlow,
        padding = padding
    ) {
        ExpandableText(
            text = """
                |像弹簧一样的弹性动画，无法自定义动画持续时间，参数如下：
                |dampingRatio：阻尼比。值越大阻尼越大，弹性越小。默认值为 1f（Spring.DampingRatioNoBouncy），无阻尼也无弹性
                |stiffness：刚度。值越小，动画持续时间越长。默认值为 1500f（Spring.StiffnessMedium）
                |visibilityThreshold：可见性阈值。默认值为 null
            """.trimMargin()
        )
        Spacer(modifier = Modifier.height(20.dp))
        Row {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = "dampingRatio", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(10.dp))
                dampingRatioList.forEachIndexed { index, pair ->
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
            Spacer(modifier = Modifier.width(20.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(text = "stiffness", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(10.dp))
                stiffnessList.forEachIndexed { index, pair ->
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

        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = { enabled = !enabled }) {
            Text(text = "Play")
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun AnimationSpecSamplePreview() {
    AnimationSpecSample(remember { MutableStateFlow(true) })
}