package com.github.panpf.android.compose.samples.ui.customization.zoomimage.my

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.isSpecified
import androidx.compose.ui.geometry.isUnspecified
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ScaleFactor
import androidx.compose.ui.layout.times
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
internal fun computeScaledContentVisibleCenter(
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
    currentScale: Float,
    newScale: Float,
    relativelyCentroid: RelativelyCentroid
): Offset {
    if (spaceSize.isUnspecified || contentSize.isUnspecified) {
        return Offset.Zero
    }
    val newScaledContentSize = contentSize.times(newScale)
    val newScaledContentScaleCenter = Offset(
        x = newScaledContentSize.width * relativelyCentroid.x,
        y = newScaledContentSize.height * relativelyCentroid.y
    )
    val scaledContentVisibleCenter =
        computeScaledContentVisibleCenter(spaceSize, contentSize, currentScale, translation)
    return scaledContentVisibleCenter - newScaledContentScaleCenter
}

internal fun computeScaledContentVisibleRectWithTopLeftScale(
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

fun Rect.restoreScale(scaleFactor: ScaleFactor): Rect {
    return Rect(
        left = (left / scaleFactor.scaleX),
        top = (top / scaleFactor.scaleY),
        right = (right / scaleFactor.scaleX),
        bottom = (bottom / scaleFactor.scaleY)
    )
}

fun Offset.toShortString(): String =
    toString().replace(if (isSpecified) "Offset" else "Offset.", "")

fun Rect.toShortString() = toString().replace("Rect.fromLTRB", "")

fun computeScaleFactor(
    contentSize: Size,
    coreSize: Size,
    coreScale: ContentScale = ContentScale.Fit
): ScaleFactor {
    if (contentSize.isUnspecified || coreSize.isUnspecified) {
        return ScaleFactor(scaleX = 1f, scaleY = 1f)
    }
    require(coreScale == ContentScale.Fit)   // todo 暂时只支持 ContentScale.Fit
    return coreScale.computeScaleFactor(srcSize = coreSize, dstSize = contentSize)
}

fun computeScaledCoreSize(
    contentSize: Size,
    coreSize: Size,
    coreScale: ContentScale = ContentScale.Fit
): Size {
    if (contentSize.isUnspecified || coreSize.isUnspecified) {
        return Size.Zero
    }
    require(coreScale == ContentScale.Fit)   // todo 暂时只支持 ContentScale.Fit
    val scaleFactor = coreScale.computeScaleFactor(srcSize = coreSize, dstSize = contentSize)
    return coreSize.times(scaleFactor)
}

fun computeScaledCoreRectOfContent(
    contentSize: Size,
    coreSize: Size,
    coreScale: ContentScale = ContentScale.Fit
): Rect {
    if (contentSize.isUnspecified || coreSize.isUnspecified) {
        return Rect.Zero
    }
    require(coreScale == ContentScale.Fit)   // todo 暂时只支持 ContentScale.Fit
    val scaleFactor = coreScale.computeScaleFactor(srcSize = coreSize, dstSize = contentSize)
    val scaledCoreSize = coreSize.times(scaleFactor)
    val left = (contentSize.width - scaledCoreSize.width) / 2
    val top = (contentSize.height - scaledCoreSize.height) / 2
    return Rect(
        left = left,
        top = top,
        right = left + scaledCoreSize.width,
        bottom = top + scaledCoreSize.height
    )
}

fun computeScaledCoreVisibleRect(
    contentSize: Size,
    visibleRectOfContent: Rect,
    coreSize: Size,
    coreScale: ContentScale = ContentScale.Fit,
): Rect {
    if (contentSize.isUnspecified || coreSize.isUnspecified) {
        return Rect.Zero
    }
    val coreRectOfContent = computeScaledCoreRectOfContent(
        contentSize = contentSize,
        coreSize = coreSize,
        coreScale = coreScale
    )
    return if (
        visibleRectOfContent.left >= coreRectOfContent.right ||
        visibleRectOfContent.top >= coreRectOfContent.bottom ||
        visibleRectOfContent.bottom <= coreRectOfContent.top ||
        visibleRectOfContent.right <= coreRectOfContent.left
    ) {
        Rect(0f, 0f, 0f, 0f)
    } else {
        val left = (visibleRectOfContent.left - coreRectOfContent.left).coerceAtLeast(0f)
        val top = (visibleRectOfContent.top - coreRectOfContent.top).coerceAtLeast(0f)
        val right = (visibleRectOfContent.right - coreRectOfContent.left).coerceAtLeast(0f)
            .coerceAtMost(coreRectOfContent.width)
        val bottom = (visibleRectOfContent.bottom - coreRectOfContent.top).coerceAtLeast(0f)
            .coerceAtMost(coreRectOfContent.height)
        Rect(left, top, right, bottom)
    }
}

/**
 * 将用户的触摸点转换为 content 上的百分比重心
 */
fun computeRelativelyCentroidOfContentByTouchPosition(
    spaceSize: Size,
    contentSize: Size,
    scale: Float,
    translation: Offset,
    touchPosition: Offset
): RelativelyCentroid {
    if (spaceSize.isUnspecified || contentSize.isUnspecified) {
        return RelativelyCentroid.Zero
    }
    val touchPositionOfContent = Offset(
        x = touchPosition.x - translation.x,
        y = touchPosition.y - translation.y,
    )
    return RelativelyCentroid(
        x = ((touchPositionOfContent.x / scale) / contentSize.width).coerceIn(0f, 1f),
        y = ((touchPositionOfContent.y / scale) / contentSize.height).coerceIn(0f, 1f),
    )
}