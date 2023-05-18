package com.github.panpf.android.compose.samples.ui.customization.zoomimage.my

import androidx.compose.ui.Alignment
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.isUnspecified
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ScaleFactor
import androidx.compose.ui.layout.times
import kotlin.math.absoluteValue

fun computeContentInContainerRect(
    containerSize: Size,
    contentSize: Size,
    contentScale: ContentScale,
    contentAlignment: Alignment,
): Rect {
    if (containerSize.isUnspecified || contentSize.isUnspecified) return Rect.Zero
    val scaleFactor =
        contentScale.computeScaleFactor(srcSize = contentSize, dstSize = containerSize)
    val scaledContentSize = contentSize.times(scaleFactor)
    val left: Float
    val top: Float
    when (contentAlignment) {
        Alignment.TopStart -> {
            left = 0f
            top = 0f
        }

        Alignment.TopCenter -> {
            left = (containerSize.width - scaledContentSize.width) / 2
            top = 0f
        }

        Alignment.TopEnd -> {
            left = containerSize.width - scaledContentSize.width
            top = 0f
        }

        Alignment.CenterStart -> {
            left = 0f
            top = (containerSize.height - scaledContentSize.height) / 2
        }

        Alignment.Center -> {
            left = (containerSize.width - scaledContentSize.width) / 2
            top = (containerSize.height - scaledContentSize.height) / 2
        }

        Alignment.CenterEnd -> {
            left = containerSize.width - scaledContentSize.width
            top = (containerSize.height - scaledContentSize.height) / 2
        }

        Alignment.BottomStart -> {
            left = 0f
            top = containerSize.height - scaledContentSize.height
        }

        Alignment.BottomCenter -> {
            left = (containerSize.width - scaledContentSize.width) / 2
            top = containerSize.height - scaledContentSize.height
        }

        Alignment.BottomEnd -> {
            left = containerSize.width - scaledContentSize.width
            top = containerSize.height - scaledContentSize.height
        }

        else -> {
            left = 0f
            top = 0f
        }
    }
    return Rect(
        left = left.coerceAtLeast(0f),
        top = top.coerceAtLeast(0f),
        right = (left + scaledContentSize.width).coerceAtMost(containerSize.width),
        bottom = (top + scaledContentSize.height).coerceAtMost(containerSize.height),
    )
}

internal fun computeScaleTargetTranslation(
    containerSize: Size,
    scale: Float,
    containerCentroid: Centroid
): Offset {
    if (containerSize.isUnspecified || containerCentroid.isUnspecified) return Offset.Zero
    val newScaledContainerSize = containerSize.times(scale)
    return Offset(
        x = (((newScaledContainerSize.width * containerCentroid.x) - (containerSize.width / 2)) * -1)
            .coerceIn(-newScaledContainerSize.width, 0f),
        y = (((newScaledContainerSize.height * containerCentroid.y) - (containerSize.height / 2)) * -1)
            .coerceIn(-newScaledContainerSize.height, 0f)
    )
}

internal fun computeTranslationBounds(
    containerSize: Size,
    contentSize: Size,
    contentScale: ContentScale,
    contentAlignment: Alignment,    // todo support
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
    val scaleFactor =
        contentScale.computeScaleFactor(srcSize = contentSize, dstSize = containerSize)
    val scaledContentSize = contentSize.times(scaleFactor)
    val scaledScaledContentSize = scaledContentSize.times(scale)
    when (contentScale) {
        ContentScale.Crop, ContentScale.FillBounds -> {    // todo support
            return defaultBounds
        }

        ContentScale.Fit, ContentScale.FillWidth, ContentScale.FillHeight -> {    // todo support
//            return defaultBounds
            val spaceOfWidth = (scaledContainerSize.width - scaledScaledContentSize.width) / 2
            val spaceOfHeight = (scaledContainerSize.height - scaledScaledContentSize.height) / 2
            val scaledScaledContentTop = spaceOfHeight
            val scaledScaledContentBottom = scaledScaledContentTop + scaledScaledContentSize.height
            val scaledScaledContentLeft = spaceOfWidth
            val scaledScaledContentRight = scaledScaledContentLeft + scaledScaledContentSize.width
            val horizontalBounds = if (scaledScaledContentSize.width < containerSize.width) {
                0f..0f
                val space = (scaledContainerSize.width - containerSize.width) * -1 / 2
                space..space
            } else {
                ((scaledScaledContentRight - containerSize.width) * -1).coerceAtMost(0f)..(scaledScaledContentLeft * -1).coerceAtMost(
                    0f
                )
            }
            val verticalBounds = if (scaledScaledContentSize.height < containerSize.height) {
                0f..0f
                val space = (scaledContainerSize.height - containerSize.height) * -1 / 2
                space..space
            } else {
                ((scaledScaledContentBottom - containerSize.height) * -1).coerceAtMost(0f)..(scaledScaledContentTop * -1).coerceAtMost(
                    0f
                )
            }
            return Rect(
                left = horizontalBounds.start,
                top = verticalBounds.start,
                right = horizontalBounds.endInclusive,
                bottom = verticalBounds.endInclusive,
            )
        }

        ContentScale.Inside -> {    // todo support
            return defaultBounds
        }

        else -> return defaultBounds
    }
}


/* ******************************************* VisibleRect ***************************************** */

internal fun computeScaledVisibleRect(
    containerSize: Size,
    scale: Float,
    translation: Offset,
): Rect {
    // based on the top left zoom
    if (containerSize.isUnspecified) return Rect.Zero
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

internal fun computeVisibleRect(containerSize: Size, scale: Float, translation: Offset): Rect {
    if (containerSize.isUnspecified) return Rect.Zero
    val scaledVisibleRect = computeScaledVisibleRect(containerSize, scale, translation)
    return scaledVisibleRect.restoreScale(scale)
}

fun computeScaledContentVisibleRect(
    containerSize: Size,
    contentSize: Size,
    contentScale: ContentScale,
    contentAlignment: Alignment,
    scale: Float,
    translation: Offset
): Rect {
    if (containerSize.isUnspecified || contentSize.isUnspecified) return Rect.Zero
    val contentInContainerRect = computeContentInContainerRect(
        containerSize = containerSize,
        contentSize = contentSize,
        contentScale = contentScale,
        contentAlignment = contentAlignment,
    )
    val visibleRect = computeVisibleRect(containerSize, scale, translation)
    return if (
        visibleRect.left >= contentInContainerRect.right ||
        visibleRect.top >= contentInContainerRect.bottom ||
        visibleRect.bottom <= contentInContainerRect.top ||
        visibleRect.right <= contentInContainerRect.left
    ) {
        Rect(0f, 0f, 0f, 0f)
    } else {
        val left = (visibleRect.left - contentInContainerRect.left).coerceAtLeast(0f)
        val top = (visibleRect.top - contentInContainerRect.top).coerceAtLeast(0f)
        val right = (visibleRect.right - contentInContainerRect.left).coerceAtLeast(0f)
            .coerceAtMost(contentInContainerRect.width)
        val bottom = (visibleRect.bottom - contentInContainerRect.top).coerceAtLeast(0f)
            .coerceAtMost(contentInContainerRect.height)
        Rect(left, top, right, bottom)
    }
}

fun computeContentVisibleRect(
    containerSize: Size,
    contentSize: Size,
    contentScale: ContentScale,
    contentAlignment: Alignment,
    scale: Float,
    translation: Offset,
): Rect {
    if (containerSize.isUnspecified || contentSize.isUnspecified) return Rect.Zero
    val scaledContentVisibleRect = computeScaledContentVisibleRect(
        containerSize = containerSize,
        contentSize = contentSize,
        contentScale = contentScale,
        contentAlignment = contentAlignment,
        scale = scale,
        translation = translation,
    )
    val contentScaleFactor =
        contentScale.computeScaleFactor(srcSize = contentSize, dstSize = containerSize)
    return scaledContentVisibleRect.restoreScale(contentScaleFactor)
}


/* ******************************************* Centroid ***************************************** */

fun computeContainerCentroidByTouchPosition(
    containerSize: Size,
    scale: Float,
    translation: Offset,
    touchPosition: Offset
): Centroid {
    if (containerSize.isUnspecified) return Centroid.Zero
    val touchPositionOfContainerX = touchPosition.x - translation.x
    val touchPositionOfContainerY = touchPosition.y - translation.y
    return Centroid(
        x = ((touchPositionOfContainerX / scale) / containerSize.width).coerceIn(0f, 1f),
        y = ((touchPositionOfContainerY / scale) / containerSize.height).coerceIn(0f, 1f),
    )
}

fun containerCentroidToContentCentroid(
    containerSize: Size,
    contentSize: Size,
    contentScale: ContentScale,
    contentAlignment: Alignment,
    containerCentroid: Centroid
): Centroid {
    if (containerSize.isUnspecified || contentSize.isUnspecified) return Centroid.Zero
    val contentInContainerRect = computeContentInContainerRect(
        containerSize = containerSize,
        contentSize = contentSize,
        contentScale = contentScale,
        contentAlignment = contentAlignment
    )
    val contentSizeScaleFactor =
        contentScale.computeScaleFactor(srcSize = contentSize, dstSize = containerSize)
    val scaledContentSize = contentSize.times(contentSizeScaleFactor)
    return Centroid(
        x = ((containerSize.width * containerCentroid.x) - contentInContainerRect.left)
            .coerceIn(0f, scaledContentSize.width) / scaledContentSize.width,
        y = ((containerSize.height * containerCentroid.y) - contentInContainerRect.top)
            .coerceIn(0f, scaledContentSize.height) / scaledContentSize.height
    )
}

internal fun contentCentroidToContainerCentroid(
    containerSize: Size,
    contentSize: Size,
    contentScale: ContentScale,
    contentAlignment: Alignment,
    contentCentroid: Centroid
): Centroid {
    if (containerSize.isUnspecified || contentSize.isUnspecified) return Centroid.Zero
    val contentInContainerRect = computeContentInContainerRect(
        containerSize = containerSize,
        contentSize = contentSize,
        contentScale = contentScale,
        contentAlignment = contentAlignment
    )
    val contentSizeScaleFactor =
        contentScale.computeScaleFactor(srcSize = contentSize, dstSize = containerSize)
    val scaledContentSize = contentSize.times(contentSizeScaleFactor)
    return Centroid(
        x = (contentInContainerRect.left + (scaledContentSize.width * contentCentroid.x))
            .coerceIn(0f, containerSize.width) / containerSize.width,
        y = (contentInContainerRect.top + (scaledContentSize.height * contentCentroid.y))
            .coerceIn(0f, containerSize.height) / containerSize.height
    )
}

/* ******************************************* Tool ***************************************** */

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