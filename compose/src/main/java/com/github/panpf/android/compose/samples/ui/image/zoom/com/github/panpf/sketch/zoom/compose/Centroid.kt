package com.github.panpf.android.compose.samples.ui.image.zoom.com.github.panpf.sketch.zoom.compose

import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.calculateCentroid
import androidx.compose.foundation.gestures.forEachGesture
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.PointerEvent
import androidx.compose.ui.input.pointer.PointerInputScope
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.debugInspectorInfo

fun Modifier.centroid(
    onGesture: (centroid: Offset) -> Unit,
) = composed(
    factory = {
        val block: suspend PointerInputScope.() -> Unit = remember {
            {
                forEachGesture {
                    awaitPointerEventScope {
                        awaitFirstDown(requireUnconsumed = false)
                        do {
                            val event: PointerEvent = awaitPointerEvent()
                            val canceled = event.changes.any { it.isConsumed }
                            val centroid = event.calculateCentroid(useCurrent = false)
                            onGesture(centroid)
                        } while (!canceled)
                    }
                }
            }
        }
        Modifier.pointerInput(Unit, block)
    },
    inspectorInfo = debugInspectorInfo {
        name = "centroid"
    }
)