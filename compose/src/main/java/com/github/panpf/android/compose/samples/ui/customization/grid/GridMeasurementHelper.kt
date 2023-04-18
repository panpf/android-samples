package com.github.panpf.android.compose.samples.ui.customization.grid

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.MeasureScope
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.unit.Constraints
import kotlin.math.max
import kotlin.math.roundToInt

internal class GridMeasureHelperResult(
    val mainAxisSize: Int,
    val crossAxisSize: Int,
    val spanCount: Int,
    val placeableList: List<Placeable>,
)

internal class GridMeasurementHelper(
    private val rows: GridCells,
    private val contentPadding: PaddingValues,
    private val reverseLayout: Boolean,
    private val layoutOrientation: LayoutOrientation,
    private val horizontalArrangement: Arrangement.Horizontal,
    private val verticalArrangement: Arrangement.Vertical,
) {

    fun measure(
        measureScope: MeasureScope,
        measurableList: List<Measurable>,
        constraints: Constraints
    ): GridMeasureHelperResult {
        val isVertical = layoutOrientation == LayoutOrientation.Vertical
        if (isVertical) {
            require(constraints.maxWidth != Constraints.Infinity) {
                "VerticalGrid's width should be bound by parent."
            }
        } else {
            require(constraints.maxHeight != Constraints.Infinity) {
                "HorizontalGrid's height should be bound by parent."
            }
        }
        val mainAxisSpacing = with(measureScope) {
            if (isVertical) {
                verticalArrangement.spacing.toPx().roundToInt()
            } else {
                horizontalArrangement.spacing.toPx().roundToInt()
            }
        }
        val crossAxisSpacing = with(measureScope) {
            if (isVertical) {
                horizontalArrangement.spacing.toPx().roundToInt()
            } else {
                verticalArrangement.spacing.toPx().roundToInt()
            }
        }
        val leftPadding = with(measureScope) {
            contentPadding.calculateLeftPadding(layoutDirection).toPx().roundToInt()
        }
        val rightPadding = with(measureScope) {
            contentPadding.calculateRightPadding(layoutDirection).toPx().roundToInt()
        }
        val topPadding = with(measureScope) {
            contentPadding.calculateTopPadding().toPx().roundToInt()
        }
        val bottomPadding = with(measureScope) {
            contentPadding.calculateBottomPadding().toPx().roundToInt()
        }
        val resolvedSlotSizesSums = with(measureScope) {
            with(rows) {
                val availableSize = if (isVertical) {
                    constraints.maxWidth - leftPadding - rightPadding
                } else {
                    constraints.maxHeight - topPadding - bottomPadding
                }
                calculateCrossAxisCellSizes(
                    availableSize = availableSize,
                    spacing = mainAxisSpacing
                )
            }
        }
        val mainAxisSize =
            if (isVertical) constraints.maxWidth else constraints.maxHeight
        var crossAxisSize =
            if (isVertical) topPadding + bottomPadding else leftPadding + rightPadding
        var crossAxisItemSize = 0    // row height or column width
        var crossAxisIndex: Int
        val spanCount = resolvedSlotSizesSums.size
        val firstSpanIndex = 0
        val lastSpanIndex = spanCount - 1
        val childSize = measurableList.size
        val placeableList = measurableList.mapIndexed { index, measurable ->
            val childMaxSize = resolvedSlotSizesSums[index % spanCount] // child max width or height
            val childConstraints = if (isVertical)
                Constraints(maxWidth = childMaxSize) else Constraints(maxHeight = childMaxSize)
            val placeable = measurable.measure(childConstraints)
            val spanIndex = index % spanCount
            crossAxisIndex = index / spanCount
            val itemSize = if (isVertical) placeable.height else placeable.width
            if (spanIndex == firstSpanIndex) {
                crossAxisItemSize = itemSize
                crossAxisSize += if (crossAxisIndex > 0) crossAxisSpacing else 0
            } else {
                crossAxisItemSize = max(crossAxisItemSize, itemSize)
            }
            if (spanIndex == lastSpanIndex || index == childSize - 1) {
                crossAxisSize += crossAxisItemSize
            }
            placeable
        }
        val crossAxisMaxSize = (if (isVertical) constraints.maxHeight else constraints.maxWidth)
        val crossAxisMinSize = (if (isVertical) constraints.minHeight else constraints.minWidth)
        return GridMeasureHelperResult(
            mainAxisSize = mainAxisSize,
            crossAxisSize = crossAxisSize.coerceIn(crossAxisMinSize, crossAxisMaxSize),
            spanCount = spanCount,
            placeableList = placeableList
        )
    }

    fun placing(
        measureScope: MeasureScope,
        placeableScope: Placeable.PlacementScope,
        result: GridMeasureHelperResult
    ) {
        val isVertical = layoutOrientation == LayoutOrientation.Vertical
        val mainAxisSpacing = with(measureScope) {
            if (isVertical) {
                verticalArrangement.spacing.toPx().roundToInt()
            } else {
                horizontalArrangement.spacing.toPx().roundToInt()
            }
        }
        val crossAxisSpacing = with(measureScope) {
            if (isVertical) {
                horizontalArrangement.spacing.toPx().roundToInt()
            } else {
                verticalArrangement.spacing.toPx().roundToInt()
            }
        }
        val leftPadding = with(measureScope) {
            contentPadding.calculateLeftPadding(layoutDirection).toPx().roundToInt()
        }
        val topPadding = with(measureScope) {
            contentPadding.calculateTopPadding().toPx().roundToInt()
        }
        var xPosition = leftPadding
        var yPosition = topPadding
        var crossAxisIndex: Int
        var crossAxisItemSize = 0
        val spanCount = result.spanCount
        val firstSpanIndex = 0
        val lastSpanIndex = spanCount - 1
        result.placeableList.forEachIndexed { index, placeable ->
            crossAxisIndex = index / spanCount
            val spanIndex = index % spanCount
            if (spanIndex == firstSpanIndex) {
                if (isVertical) {
                    xPosition = leftPadding
                    yPosition += if (crossAxisIndex > 0) crossAxisSpacing else 0
                } else {
                    xPosition += if (crossAxisIndex > 0) crossAxisSpacing else 0
                    yPosition = topPadding
                }
                placeableWithReverseLayout(
                    isVertical = isVertical,
                    result = result,
                    placeableScope = placeableScope,
                    placeable = placeable,
                    x = xPosition,
                    y = yPosition
                )
                if (isVertical) {
                    xPosition += placeable.width
                    crossAxisItemSize = placeable.height
                } else {
                    yPosition += placeable.height
                    crossAxisItemSize = placeable.width
                }
            } else {
                if (isVertical) {
                    xPosition += mainAxisSpacing
                } else {
                    yPosition += mainAxisSpacing
                }
                placeableWithReverseLayout(
                    isVertical = isVertical,
                    result = result,
                    placeableScope = placeableScope,
                    placeable = placeable,
                    x = xPosition,
                    y = yPosition
                )
                if (isVertical) {
                    xPosition += placeable.width
                    crossAxisItemSize = max(crossAxisItemSize, placeable.height)
                } else {
                    yPosition += placeable.height
                    crossAxisItemSize = max(crossAxisItemSize, placeable.width)
                }
            }
            if (spanIndex == lastSpanIndex) {
                if (isVertical) {
                    yPosition += crossAxisItemSize
                } else {
                    xPosition += crossAxisItemSize
                }
            }
        }
    }

    private fun placeableWithReverseLayout(
        isVertical: Boolean,
        result: GridMeasureHelperResult,
        placeableScope: Placeable.PlacementScope,
        placeable: Placeable,
        x: Int,
        y: Int,
    ) {
        if (isVertical) {
            val yPosition: Int = if (reverseLayout) {
                result.crossAxisSize - y - placeable.height
            } else {
                y
            }
            with(placeableScope) {
                placeable.placeRelative(x = x, y = yPosition)
            }
        } else {
            val xPosition: Int = if (reverseLayout) {
                result.crossAxisSize - x - placeable.width
            } else {
                x
            }
            with(placeableScope) {
                placeable.placeRelative(x = xPosition, y = y)
            }
        }
    }
}