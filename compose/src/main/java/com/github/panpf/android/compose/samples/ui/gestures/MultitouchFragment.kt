package com.github.panpf.android.compose.samples.ui.gestures

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.panpf.android.compose.samples.R
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem3
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

class MultitouchFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "Multitouch"
    }

    @Composable
    override fun DrawContent() {
        ExpandableLayout { allExpandFlow ->
            MultitouchPanningSample(allExpandFlow)
            DetectTransformGesturesSample(allExpandFlow)
            OfficialDetectTransformGestures(allExpandFlow)
        }
    }
}


@Composable
private fun MultitouchPanningSample(allExpandFlow: Flow<Boolean>) {
    val desc = """
        Modifier.transformable() 修饰符可以检测平移、缩放和旋转多点触控手势，
        transformable 同样仅报告变化增量，您需要保存状态并通过 graphicsLayer 修饰符来应用变化
        
        transformable 没有返回 centroid 参数，所以双指缩放时只能以中心点为质心缩放，和当前手指所在位置无关。如果想要以当前手指所在位置为质心缩放，可以使用 detectTransformGestures 函数通过 centroid 参数来实现
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
                .clipToBounds()
        ) {
            Image(
                painter = painterResource(id = R.drawable.dog_hor),
                contentDescription = null,
                Modifier
                    .fillMaxSize()
                    // add transformable to listen to multitouch transformation events
                    // after offset
                    .transformable(state = state, lockRotationOnZoomPan = lockRotationOnZoomPan)
                    // apply other transformations like rotation and zoom
                    // on the pizza slice emoji
                    .graphicsLayer(
                        scaleX = scale,
                        scaleY = scale,
                        rotationZ = rotation,
                        translationX = offset.x,
                        translationY = offset.y
                    )
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun MultitouchPanningSamplePreview() {
    MultitouchPanningSample(remember { MutableStateFlow(true) })
}


@Composable
private fun DetectTransformGesturesSample(allExpandFlow: Flow<Boolean>) {
    val desc = """
        detectTransformGestures 函数可以检测平移、缩放和旋转多点触控手势，同样仅报告变化增量，您需要保存状态并通过 graphicsLayer 修饰符来应用变化
        
        detectTransformGestures 还会返回 centroid 通过此参数可以实现双指缩放时以当前手指所在位置为质心缩放
    """.trimIndent()

    var offset by remember { mutableStateOf(Offset.Zero) }
    var zoom by remember { mutableStateOf(1f) }
    var angle by remember { mutableStateOf(0f) }
    var lockRotationOnZoomPan by remember { mutableStateOf(false) }

    ExpandableItem3(
        title = "Multitouch（detectTransformGestures）",
        allExpandFlow,
        padding = 20.dp,
        desc = desc,
    ) {
        /**
         * Rotates the given offset around the origin by the given angle in degrees.
         *
         * A positive angle indicates a counterclockwise rotation around the right-handed 2D Cartesian
         * coordinate system.
         *
         * See: [Rotation matrix](https://en.wikipedia.org/wiki/Rotation_matrix)
         */
        fun Offset.rotateBy(angle: Float): Offset {
            val angleInRadians = angle * PI / 180
            return Offset(
                (x * cos(angleInRadians) - y * sin(angleInRadians)).toFloat(),
                (x * sin(angleInRadians) + y * cos(angleInRadians)).toFloat()
            )
        }

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
                .clipToBounds()
        ) {
            Image(
                painter = painterResource(id = R.drawable.dog_hor),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    /* 通过 centroid 计算便宜两，保持以手指位置为质心缩放 */
                    .pointerInput(lockRotationOnZoomPan) {
                        detectTransformGestures(panZoomLock = lockRotationOnZoomPan) { centroid, pan, gestureZoom, gestureRotate ->
                            val oldScale = zoom
                            val newScale = zoom * gestureZoom

                            // For natural zooming and rotating, the centroid of the gesture should
                            // be the fixed point where zooming and rotating occurs.
                            // We compute where the centroid was (in the pre-transformed coordinate
                            // space), and then compute where it will be after this delta.
                            // We then compute what the new offset should be to keep the centroid
                            // visually stationary for rotating and zooming, and also apply the pan.
                            offset = (offset + centroid / oldScale).rotateBy(gestureRotate) -
                                    (centroid / newScale + pan / oldScale)
                            zoom = newScale
                            angle += gestureRotate
                        }
                    }
                    .graphicsLayer {
                        translationX = -offset.x * zoom
                        translationY = -offset.y * zoom
                        scaleX = zoom
                        scaleY = zoom
                        rotationZ = angle
                        transformOrigin = TransformOrigin(0f, 0f)
                    }
//                    /* 和 transformable 一样始终以中心为质心缩放 */
//                    .pointerInput(lockRotationOnZoomPan) {
//                        detectTransformGestures(panZoomLock = lockRotationOnZoomPan) { centroid, pan, gestureZoom, gestureRotate ->
//                            zoom *= gestureZoom
//                            angle += gestureRotate
//                            offset += pan
//                        }
//                    }
//                    .graphicsLayer {
//                        translationX = offset.x
//                        translationY = offset.y
//                        scaleX = zoom
//                        scaleY = zoom
//                        rotationZ = angle
//                        transformOrigin = TransformOrigin.Center
//                    }
            )
        }
    }
}

/**
 * from https://github.com/androidx/androidx/blob/643b1cfdd7dfbc5ccce1ad951b6999df049678b3/compose/foundation/foundation/samples/src/main/java/androidx/compose/foundation/samples/TransformGestureSamples.kt
 *
 * Rotates the given offset around the origin by the given angle in degrees.
 *
 * A positive angle indicates a counterclockwise rotation around the right-handed 2D Cartesian
 * coordinate system.
 *
 * See: [Rotation matrix](https://en.wikipedia.org/wiki/Rotation_matrix)
 */
@Composable
private fun OfficialDetectTransformGestures(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(
        title = "DetectTransformGestures（Official）",
        allExpandFlow,
        padding = 20.dp,
    ) {
        fun Offset.rotateBy(angle: Float): Offset {
            val angleInRadians = angle * PI / 180
            return Offset(
                (x * cos(angleInRadians) - y * sin(angleInRadians)).toFloat(),
                (x * sin(angleInRadians) + y * cos(angleInRadians)).toFloat()
            )
        }

        var offset by remember { mutableStateOf(Offset.Zero) }
        var zoom by remember { mutableStateOf(1f) }
        var angle by remember { mutableStateOf(0f) }

        Box(
            Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .border(width = 1.dp, color = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f))
                .pointerInput(Unit) {
                    detectTransformGestures(
                        onGesture = { centroid, pan, gestureZoom, gestureRotate ->
                            val oldScale = zoom
                            val newScale = zoom * gestureZoom

                            // For natural zooming and rotating, the centroid of the gesture should
                            // be the fixed point where zooming and rotating occurs.
                            // We compute where the centroid was (in the pre-transformed coordinate
                            // space), and then compute where it will be after this delta.
                            // We then compute what the new offset should be to keep the centroid
                            // visually stationary for rotating and zooming, and also apply the pan.
                            offset = (offset + centroid / oldScale).rotateBy(gestureRotate) -
                                    (centroid / newScale + pan / oldScale)
                            zoom = newScale
                            angle += gestureRotate
                        }
                    )
                }
        ) {
            Box(
                Modifier
                    .graphicsLayer {
                        translationX = -offset.x * zoom
                        translationY = -offset.y * zoom
                        scaleX = zoom
                        scaleY = zoom
                        rotationZ = angle
                        transformOrigin = TransformOrigin(0f, 0f)
                    }
                    .size(100.dp)
                    .background(Color.Blue)
            )
        }
    }
}
