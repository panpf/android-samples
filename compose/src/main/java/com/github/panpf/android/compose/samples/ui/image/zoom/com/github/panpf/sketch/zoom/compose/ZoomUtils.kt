package com.github.panpf.android.compose.samples.ui.image.zoom.com.github.panpf.sketch.zoom.compose

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.isUnspecified
import kotlin.math.absoluteValue

/**
 * 计算以左上角为缩放中心时位移的边界
 */
internal fun computeTranslationBoundsWithTopLeftScale(
    spaceSize: Size,
    contentSize: Size,
    scale: Float
): Rect {
    if (spaceSize.isUnspecified || contentSize.isUnspecified) {
        return Rect(left = 0f, top = 0f, right = 0f, bottom = 0f)
    }

    val contentScaledSize = contentSize.times(scale)
    return Rect(
        left = if (contentScaledSize.width > spaceSize.width)
            -(contentScaledSize.width - spaceSize.width) else 0f,
        top = if (contentScaledSize.height > spaceSize.height)
            -(contentScaledSize.height - spaceSize.height) else 0f,
        right = 0f,
        bottom = 0f
    )
}

/**
 * 计算 content 当前可见的中心坐标，不会超过 content 的范围
 */
internal fun computeVisibleCenterOfContent(
    spaceSize: Size,
    contentSize: Size,
    scale: Float,
    translation: Offset
): Offset {
    if (spaceSize.isUnspecified || contentSize.isUnspecified) {
        return Offset.Zero
    }
    val contentScaledSize = contentSize.times(scale)

    val x = if (translation.x > 0) {
        (spaceSize.width / 2) - translation.x
    } else {
        translation.x.absoluteValue + (spaceSize.width / 2)
    }.coerceAtLeast(0f).coerceAtMost(contentScaledSize.width)
    val y = if (translation.y > 0) {
        (spaceSize.height / 2) - translation.y
    } else {
        translation.y.absoluteValue + (spaceSize.height / 2)
    }.coerceAtLeast(0f).coerceAtMost(contentScaledSize.height)
    return Offset(x = x, y = y)
}

internal fun computeContentScaleTranslation(
    spaceSize: Size,
    contentSize: Size,
    translation: Offset,
    scale: Float,
    newScale: Float,
    contentScaleCenterPercentage: Offset
): Offset {
    if (spaceSize.isUnspecified || contentSize.isUnspecified) {
        return Offset.Zero
    }
    val contentNewScaledSize = contentSize.times(newScale)
    val contentNewScaleCenter = Offset(
        x = contentNewScaledSize.width * contentScaleCenterPercentage.x,
        y = contentNewScaledSize.height * contentScaleCenterPercentage.y
    )
    val currentCenter = computeVisibleCenterOfContent(spaceSize, contentSize, scale, translation)
//    if (newScale > scale) {
        return currentCenter - contentNewScaleCenter
//    } else {
//        return currentCenter * (newScale / scale) - contentNewScaleCenter
//    }
}