package com.github.panpf.android.compose.samples.ui.image.zoom.com.github.panpf.sketch.zoom.compose

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.isUnspecified
import kotlin.math.absoluteValue
import kotlin.math.pow

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
    val scaledContentSize = contentSize.times(scale)
    return Rect(
        left = if (scaledContentSize.width > spaceSize.width)
            -(scaledContentSize.width - spaceSize.width) else 0f,
        top = if (scaledContentSize.height > spaceSize.height)
            -(scaledContentSize.height - spaceSize.height) else 0f,
        right = 0f,
        bottom = 0f
    )
}

/**
 * 计算 content 当前可见的中心坐标，不会超过 content 的范围
 */
internal fun computeVisibleCenterOfScaledContent(
    spaceSize: Size,
    contentSize: Size,
    scale: Float,
    translation: Offset
): Offset {
    if (spaceSize.isUnspecified || contentSize.isUnspecified) {
        return Offset.Zero
    }
    val scaledContentSize = contentSize.times(scale)

    val translationX = translation.x
    val x = if (translationX > 0) {
        (spaceSize.width / 2) - translationX
    } else {
        translationX.absoluteValue + (spaceSize.width / 2)
    }.coerceIn(0f..scaledContentSize.width)

    val translationY = translation.y
    val y = if (translationY > 0) {
        (spaceSize.height / 2) - translationY
    } else {
        translationY.absoluteValue + (spaceSize.height / 2)
    }.coerceIn(0f..scaledContentSize.height)

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
    val newScaledContentSize = contentSize.times(newScale)
    val newScaledContentScaleCenter = Offset(
        x = newScaledContentSize.width * contentScaleCenterPercentage.x,
        y = newScaledContentSize.height * contentScaleCenterPercentage.y
    )
    val currentCenter =
        computeVisibleCenterOfScaledContent(spaceSize, contentSize, scale, translation)
//    if (newScale > scale) {
    return currentCenter - newScaledContentScaleCenter
//    } else {
//        return currentCenter * (newScale / scale) - contentNewScaleCenter
//    }
}

internal fun computeVisibleRectOfScaledContentWithTopLeftScale(
    spaceSize: Size,
    contentSize: Size,
    scale: Float,
    translation: Offset,
): Rect {
    if (spaceSize.isUnspecified || contentSize.isUnspecified) {
        return Rect.Zero
    }
    val scaledContentSize = contentSize.times(scale)
    val translationX = translation.x
    val translationY = translation.y
    val left: Float
    val right: Float
    if (translationX > 0) {
        left = 0f
        right = spaceSize.width - translationX
    } else {
        left = translationX.absoluteValue
        right = translationX.absoluteValue + spaceSize.width
    }
    val top: Float
    val bottom: Float
    if (translationY > 0) {
        top = 0f
        bottom = spaceSize.height - translationY
    } else {
        top = translationY.absoluteValue
        bottom = translationY.absoluteValue + spaceSize.height
    }
    return Rect(
        left = left.coerceAtLeast(0f),
        top = top.coerceAtLeast(0f),
        right = right.coerceAtMost(scaledContentSize.width),
        bottom = bottom.coerceAtMost(scaledContentSize.height)
    )
}

fun Rect.scale(scale: Float): Rect {
    return Rect(
        left = (left * scale),
        top = (top * scale),
        right = (right * scale),
        bottom = (bottom * scale)
    )
}

fun Rect.restoreScale(scale: Float): Rect {
    return Rect(
        left = (left / scale),
        top = (top / scale),
        right = (right / scale),
        bottom = (bottom / scale)
    )
}

fun Offset.toShortString(): String = "(${x.toStringAsFixed(1)}, ${y.toStringAsFixed(1)})"

fun Rect.toShortString() = "(" +
        "${left.toStringAsFixed(1)}, " +
        "${top.toStringAsFixed(1)}, " +
        "${right.toStringAsFixed(1)}, " +
        "${bottom.toStringAsFixed(1)})"

// File of internal utility methods used for the geometry library
internal fun Float.toStringAsFixed(digits: Int): String {
    val clampedDigits: Int = kotlin.math.max(digits, 0) // Accept positive numbers and 0 only
    val pow = 10f.pow(clampedDigits)
    val shifted = this * pow // shift the given value by the corresponding power of 10
    val decimal = shifted - shifted.toInt() // obtain the decimal of the shifted value
    // Manually round up if the decimal value is greater than or equal to 0.5f.
    // because kotlin.math.round(0.5f) rounds down
    val roundedShifted = if (decimal >= 0.5f) {
        shifted.toInt() + 1
    } else {
        shifted.toInt()
    }

    val rounded = roundedShifted / pow // divide off the corresponding power of 10 to shift back
    return if (clampedDigits > 0) {
        // If we have any decimal points, convert the float to a string
        rounded.toString()
    } else {
        // If we do not have any decimal points, return the int
        // based string representation
        rounded.toInt().toString()
    }
}