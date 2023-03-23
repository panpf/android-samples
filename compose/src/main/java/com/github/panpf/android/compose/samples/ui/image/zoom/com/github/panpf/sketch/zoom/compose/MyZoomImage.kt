package com.github.panpf.sketch.zoom.compose

import android.util.Log
import androidx.annotation.FloatRange
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.toSize
import com.github.panpf.android.compose.samples.ui.image.zoom.com.github.panpf.sketch.zoom.compose.centroid
import kotlinx.coroutines.launch

@Composable
fun MyZoomImage(
    painter: Painter,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    state: MyZoomState = rememberMyZoomState(),
    animateDoubleTapScale: Boolean = true,
    alignment: Alignment = Alignment.Center,
    contentScale: ContentScale = ContentScale.Fit,
    alpha: Float = DefaultAlpha,
    colorFilter: ColorFilter? = null,
) {
    Image(
        painter = painter,
        contentDescription = contentDescription,
        modifier = modifier.then(Modifier.createZoomModifier(
            state = state,
            painter = painter,
            contentScale = contentScale,
            animateDoubleTapScale = animateDoubleTapScale
        )),
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

private fun Modifier.createZoomModifier(
    state: MyZoomState,
    painter: Painter,
    contentScale: ContentScale,
    animateDoubleTapScale: Boolean = true
): Modifier = composed {
    Log.i(
        "MyZoomState",
        "createZoomModifier. animateDoubleTapScale=$animateDoubleTapScale"
    )
    // todo compat viewpager
    val coroutineScope = rememberCoroutineScope()
    val centroid = remember { mutableStateOf(Offset.Zero) }
    Modifier
        .onSizeChanged {
            state.spaceSize = it.toSize()
            state.contentSize = it.toSize()
            state.coreSize = painter.intrinsicSize
            state.coreScale = contentScale
        }
        .pointerInput(animateDoubleTapScale) {
            detectTapGestures(onDoubleTap = { offset ->
                coroutineScope.launch {
                    if (animateDoubleTapScale) {
                        state.animateDoubleTapScale(
                            percentageCentroidOfContent = state.touchPointToPercentageCentroidOfContent(
                                offset
                            ),
                            offset
                        )
                    } else {
                        state.snapDoubleTapScale(percentageCentroidOfContent = state.touchPointToPercentageCentroidOfContent(
                            offset
                        ))
                    }
                }
            })
        }
        .pointerInput(Unit) {
            detectDragGestures(
                onDragStart = {
                    state.dragStart()
                },
                onDrag = { change, dragAmount ->
//                    change.consume() todo
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
        .clipToBounds()
        .graphicsLayer {
            scaleX = state.scale
            scaleY = state.scale
            translationX = state.translationX
            translationY = state.translationY
            transformOrigin = state.transformOrigin
        }
        .centroid {
            centroid.value = it
        }
        .transformable(
            state = rememberTransformableState { zoomChange: Float, panChange: Offset, rotationChange: Float ->
                coroutineScope.launch {
                    state.transform(
                        zoomChange = zoomChange,
                        panChange = panChange,
                        rotationChange = rotationChange,
                        percentageCentroidOfContent = state.touchPointToPercentageCentroidOfContent(
                            centroid.value
                        )
                    )
                }
            },
            lockRotationOnZoomPan = true
        )
//        .rotate(0f)// todo rotation
}