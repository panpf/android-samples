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

internal fun computeTranslationBounds(
    containerSize: Size,
    contentSize: Size,
    contentScale: ContentScale,
    scale: Float
): Rect {
    // based on the top left zoom
    if (scale <= 1.0f || containerSize.isUnspecified || contentSize.isUnspecified) {
        return Rect.Zero
    }
    val scaledContainerSize = containerSize.times(scale)
    val defaultBounds = Rect(
        left = if (scaledContainerSize.width > containerSize.width)
            -(scaledContainerSize.width - containerSize.width) else 0f,
        top = if (scaledContainerSize.height > containerSize.height)
            -(scaledContainerSize.height - containerSize.height) else 0f,
        right = 0f,
        bottom = 0f
    )
//    val scaleFactor =
//        contentScale.computeScaleFactor(srcSize = contentSize, dstSize = containerSize)
//    val scaledContentSize = contentSize.times(scaleFactor)
//    val scaledScaledContentSize = scaledContentSize.times(scale)
//    val finalContentWidth = scaledScaledContentSize.width.coerceAtMost(scaledContainerSize.width)
    when (contentScale) {
        ContentScale.Crop -> {    // todo support
            return defaultBounds
        }

        ContentScale.Fit -> {    // todo support
            return defaultBounds
//            return Rect(
//                left = if (scaledContainerSize.width > containerSize.width)
//                    -(scaledContainerSize.width - (containerSize.width - scaledContentSize.width) / 2) else 0f,
//                top = if (scaledContainerSize.height > containerSize.height)
//                    -(scaledContainerSize.height - (containerSize.height - scaledContentSize.height) / 2) else 0f,
//                right = 0f,
//                bottom = 0f
//            )
        }

        ContentScale.FillHeight -> {    // todo support
            return defaultBounds
        }

        ContentScale.FillWidth -> {    // todo support
            return defaultBounds
        }

        ContentScale.Inside -> {    // todo support
            return defaultBounds
        }

        ContentScale.FillBounds -> {    // todo support
            return defaultBounds
        }

        else -> return defaultBounds
    }
}

internal fun computeScaleTranslation(
    containerSize: Size,
    translation: Offset,
    currentScale: Float,
    newScale: Float,
    relativelyCentroid: RelativelyCentroid
): Offset {
    if (containerSize.isUnspecified) {
        return Offset.Zero
    }
    val newScaledContainerSize = containerSize.times(newScale)
    val newScaledContentScaleCenter = Offset(
        x = newScaledContainerSize.width * relativelyCentroid.x,
        y = newScaledContainerSize.height * relativelyCentroid.y
    )
    val scaledContentVisibleCenter =
        computeScaledVisibleCenter(containerSize, currentScale, translation)
    return scaledContentVisibleCenter - newScaledContentScaleCenter
}

/**
 * 计算当前可见的中心坐标
 */
internal fun computeScaledVisibleCenter(
    containerSize: Size,
    scale: Float,
    translation: Offset
): Offset {
    if (containerSize.isUnspecified) {
        return Offset.Zero
    }
    val scaledContainerSize = containerSize.times(scale)

    val translationX = translation.x
    val x = if (translationX > 0) {
        (containerSize.width / 2) - translationX
    } else {
        translationX.absoluteValue + (containerSize.width / 2)
    }.coerceIn(0f..scaledContainerSize.width)

    val translationY = translation.y
    val y = if (translationY > 0) {
        (containerSize.height / 2) - translationY
    } else {
        translationY.absoluteValue + (containerSize.height / 2)
    }.coerceIn(0f..scaledContainerSize.height)

    return Offset(x = x, y = y)
}

internal fun computeScaledVisibleRect(
    containerSize: Size,
    scale: Float,
    translation: Offset,
): Rect {
    // based on the top left zoom
    if (containerSize.isUnspecified) {
        return Rect.Zero
    }
    val scaledContainerSize = containerSize.times(scale)
    val translationX = translation.x
    val translationY = translation.y
    val left: Float
    val right: Float
    if (translationX > 0) {
        left = 0f
        right = containerSize.width - translationX
    } else {
        left = translationX.absoluteValue
        right = translationX.absoluteValue + containerSize.width
    }
    val top: Float
    val bottom: Float
    if (translationY > 0) {
        top = 0f
        bottom = containerSize.height - translationY
    } else {
        top = translationY.absoluteValue
        bottom = translationY.absoluteValue + containerSize.height
    }
    return Rect(
        left = left.coerceAtLeast(0f),
        top = top.coerceAtLeast(0f),
        right = right.coerceAtMost(scaledContainerSize.width),
        bottom = bottom.coerceAtMost(scaledContainerSize.height)
    )
}

fun computeScaleFactor(
    containerSize: Size,
    contentSize: Size,
    contentScale: ContentScale = ContentScale.Fit
): ScaleFactor {
    if (containerSize.isUnspecified || contentSize.isUnspecified) {
        return ScaleFactor(scaleX = 1f, scaleY = 1f)
    }
    require(contentScale == ContentScale.Fit)   // todo 暂时只支持 ContentScale.Fit
    return contentScale.computeScaleFactor(srcSize = contentSize, dstSize = containerSize)
}

fun computeScaledContentSize(
    containerSize: Size,
    contentSize: Size,
    contentScale: ContentScale = ContentScale.Fit
): Size {
    if (containerSize.isUnspecified || contentSize.isUnspecified) {
        return Size.Zero
    }
    require(contentScale == ContentScale.Fit)   // todo 暂时只支持 ContentScale.Fit
    val scaleFactor =
        contentScale.computeScaleFactor(srcSize = contentSize, dstSize = containerSize)
    return contentSize.times(scaleFactor)
}

fun computeContentOfContainerRect(
    containerSize: Size,
    contentSize: Size,
    contentScale: ContentScale = ContentScale.Fit
): Rect {
    if (containerSize.isUnspecified || contentSize.isUnspecified) {
        return Rect.Zero
    }
    require(contentScale == ContentScale.Fit)   // todo 暂时只支持 ContentScale.Fit
    val scaleFactor =
        contentScale.computeScaleFactor(srcSize = contentSize, dstSize = containerSize)
    val scaledContentSize = contentSize.times(scaleFactor)
    val left = (containerSize.width - scaledContentSize.width) / 2
    val top = (containerSize.height - scaledContentSize.height) / 2
    return Rect(
        left = left,
        top = top,
        right = left + scaledContentSize.width,
        bottom = top + scaledContentSize.height
    )
}

fun computeScaledContentVisibleRect(
    containerSize: Size,
    visibleRectOfContent: Rect,
    contentSize: Size,
    contentScale: ContentScale = ContentScale.Fit,
): Rect {
    if (containerSize.isUnspecified || contentSize.isUnspecified) {
        return Rect.Zero
    }
    val contentRectOfContainer = computeContentOfContainerRect(
        containerSize = containerSize,
        contentSize = contentSize,
        contentScale = contentScale
    )
    return if (
        visibleRectOfContent.left >= contentRectOfContainer.right ||
        visibleRectOfContent.top >= contentRectOfContainer.bottom ||
        visibleRectOfContent.bottom <= contentRectOfContainer.top ||
        visibleRectOfContent.right <= contentRectOfContainer.left
    ) {
        Rect(0f, 0f, 0f, 0f)
    } else {
        val left = (visibleRectOfContent.left - contentRectOfContainer.left).coerceAtLeast(0f)
        val top = (visibleRectOfContent.top - contentRectOfContainer.top).coerceAtLeast(0f)
        val right = (visibleRectOfContent.right - contentRectOfContainer.left).coerceAtLeast(0f)
            .coerceAtMost(contentRectOfContainer.width)
        val bottom = (visibleRectOfContent.bottom - contentRectOfContainer.top).coerceAtLeast(0f)
            .coerceAtMost(contentRectOfContainer.height)
        Rect(left, top, right, bottom)
    }
}

/**
 * 将用户的触摸点转换为 container 上的百分比重心
 */
fun computeRelativelyCentroidOfContainerByTouchPosition(
    containerSize: Size,
    scale: Float,
    translation: Offset,
    touchPosition: Offset
): RelativelyCentroid {
    if (containerSize.isUnspecified) {
        return RelativelyCentroid.Zero
    }
    val touchPositionOfContent = Offset(
        x = touchPosition.x - translation.x,
        y = touchPosition.y - translation.y,
    )
    return RelativelyCentroid(
        x = ((touchPositionOfContent.x / scale) / containerSize.width).coerceIn(0f, 1f),
        y = ((touchPositionOfContent.y / scale) / containerSize.height).coerceIn(0f, 1f),
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