package com.github.panpf.sketch.zoom.compose

import android.util.Log
import androidx.annotation.FloatRange
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import kotlinx.coroutines.launch

@Composable
fun MyZoomImage(
    painter: Painter,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    state: MyZoomState = rememberMyZoomState(),
    alignment: Alignment = Alignment.Center,
    contentScale: ContentScale = ContentScale.Fit,
    alpha: Float = DefaultAlpha,
    colorFilter: ColorFilter? = null
) {
    Image(
        painter = painter,
        contentDescription = contentDescription,
        modifier = modifier.then(Modifier.createZoomModifier(state)),
        alignment = alignment,
        contentScale = contentScale,
        alpha = alpha,
        colorFilter = colorFilter
    )
}

@Composable
fun rememberMyZoomState(
    @FloatRange(from = 0.0) minScale: Float = 1f,
    @FloatRange(from = 0.0) maxScale: Float = 4f,
): MyZoomState {
    return rememberSaveable(saver = MyZoomState.Saver) {
        MyZoomState(minScale = minScale, maxScale = maxScale)
    }
}

private fun Modifier.createZoomModifier(state: MyZoomState): Modifier = composed {
    Log.d("ZoomImage", "state: $state")
    val coroutineScope = rememberCoroutineScope()
    Modifier
        .graphicsLayer {
            scaleX = state.scale
            scaleY = state.scale
            translationX = state.translationX * state.scale
            translationY = state.translationY * state.scale
//            translationX = state.translationX,
//            translationY = state.translationY
        }
        .pointerInput(Unit) {
            detectTapGestures(onDoubleTap = { offset ->
                coroutineScope.launch {
                    state.animateDoubleTapScale(offset)
                }
            })
        }
        .pointerInput(Unit) {
            detectDragGestures(
                onDragStart = {
                    state.dragStart()
                },
                onDrag = { change, dragAmount ->
//                    change.consume()
                    coroutineScope.launch {
                        state.drag(change, dragAmount)
                    }
                },
                onDragEnd = {
                    coroutineScope.launch {
                        state.dragEnd()
                    }
                },
                onDragCancel = {
                    coroutineScope.launch {
                        state.dragCancel()
                    }
                }
            )
        }
        .transformable(
            state = rememberTransformableState { zoomChange: Float, panChange: Offset, rotationChange: Float ->
                coroutineScope.launch {
                    state.transform(zoomChange, panChange, rotationChange)
                }
            },
            lockRotationOnZoomPan = true
        )
}