package com.github.panpf.android.compose.samples.ui.gestures

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem3
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class MultitouchFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "Multitouch"
    }

    @Composable
    override fun DrawContent() {
        ExpandableLayout { allExpandFlow ->
            MultitouchPanningSample(allExpandFlow)
        }
    }
}


@Composable
private fun MultitouchPanningSample(allExpandFlow: Flow<Boolean>) {
    val desc = """
        Modifier.transformable() 修饰符可以检测平移、缩放和旋转多点触控手势，
        transformable 同样仅报告变化增量，您需要保存状态并通过 graphicsLayer 修饰符来应用变化
    """.trimIndent()
    // set up all transformation states
    var scale by remember { mutableStateOf(1f) }
    var rotation by remember { mutableStateOf(0f) }
    var offset by remember { mutableStateOf(Offset.Zero) }
    val state = rememberTransformableState { zoomChange, offsetChange, rotationChange ->
        scale *= zoomChange
        rotation += rotationChange
        offset += offsetChange
    }
    var lockRotationOnZoomPan by remember { mutableStateOf(false) }
    ExpandableItem3(
        title = "Multitouch（transformable）",
        allExpandFlow,
        padding = 20.dp,
        desc = desc,
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
            Checkbox(
                checked = lockRotationOnZoomPan,
                onCheckedChange = {
                    lockRotationOnZoomPan = it
                }
            )
            Text(text = "缩放时禁用旋转", modifier = Modifier.align(Alignment.CenterVertically))
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .border(width = 1.dp, color = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f))
        ) {
            Box(
                Modifier
                    // apply other transformations like rotation and zoom
                    // on the pizza slice emoji
                    .graphicsLayer(
                        scaleX = scale,
                        scaleY = scale,
                        rotationZ = rotation,
                        translationX = offset.x,
                        translationY = offset.y
                    )
                    // add transformable to listen to multitouch transformation events
                    // after offset
                    .transformable(state = state, lockRotationOnZoomPan = lockRotationOnZoomPan)
                    .background(MaterialTheme.colorScheme.primary)
                    .size(160.dp)
                    .align(Alignment.Center)
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun MultitouchPanningSamplePreview() {
    MultitouchPanningSample(remember { MutableStateFlow(true) })
}