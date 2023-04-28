package com.github.panpf.android.compose.samples.ui.customization.grid

import android.graphics.Point
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.MeasureResult
import androidx.compose.ui.layout.MeasureScope
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.unit.Constraints
import kotlin.math.max
import kotlin.math.roundToInt

@Composable
@ExperimentalLayoutApi
internal fun Grid(
    cells: GridCells,
    layoutOrientation: LayoutOrientation,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues,
    reverseLayout: Boolean,
    horizontalArrangement: Arrangement.Horizontal,
    verticalArrangement: Arrangement.Vertical,
    content: @Composable () -> Unit
) {
    val measurePolicy = rememberGridMeasurePolicy(
        cells = cells,
        layoutOrientation = layoutOrientation,
        contentPadding = contentPadding,
        reverseLayout = reverseLayout,
        horizontalArrangement = horizontalArrangement,
        verticalArrangement = verticalArrangement
    )
    Layout(
        modifier = modifier,
        content = content,
        measurePolicy = measurePolicy
    )
}


@Composable
private fun rememberGridMeasurePolicy(
    cells: GridCells,
    layoutOrientation: LayoutOrientation,
    contentPadding: PaddingValues,
    reverseLayout: Boolean,
    horizontalArrangement: Arrangement.Horizontal,
    verticalArrangement: Arrangement.Vertical,
) = remember<MeasureScope.(List<Measurable>, Constraints) -> MeasureResult>(
    cells,
    layoutOrientation,
    contentPadding,
    reverseLayout,
    horizontalArrangement,
    verticalArrangement
) {
    { measurables, constraints ->
        val measurementHelper = GridMeasurementHelper(
            cells = cells,
            contentPadding = contentPadding,
            reverseLayout = reverseLayout,
            layoutOrientation = layoutOrientation,
            horizontalArrangement = horizontalArrangement,
            verticalArrangement = verticalArrangement,
        )
        val measureResult = measurementHelper.measure(this, measurables, constraints)
        layout(width = measureResult.width, height = measureResult.height) {
            measurementHelper.placing(
                placeableScope = this@layout,
                result = measureResult
            )
        }
    }
}

internal class GridMeasureHelperResult(
    val mainAxisSize: Int,
    val crossAxisSize: Int,
    val width: Int,
    val height: Int,
    val spanCount: Int,
    val itemList: List<Pair<Placeable, Point>>,
)

internal class GridMeasurementHelper(
    private val cells: GridCells,
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
        val (mainAxisSpacing, crossAxisSpacing) = with(measureScope) {
            val verticalSpacing = verticalArrangement.spacing.toPx().roundToInt()
            val horizontalSpacing = horizontalArrangement.spacing.toPx().roundToInt()
            if (isVertical) horizontalSpacing to verticalSpacing else verticalSpacing to horizontalSpacing
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
            with(cells) {
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
        var xPosition = leftPadding
        var yPosition = topPadding
        val itemList = measurableList.mapIndexed { index, measurable ->
            val childMaxMainAxisSize =
                resolvedSlotSizesSums[index % spanCount] // child max width or height
            val childConstraints = if (isVertical) {
                Constraints(maxWidth = childMaxMainAxisSize, minWidth = childMaxMainAxisSize)
            } else {
                Constraints(maxHeight = childMaxMainAxisSize, minHeight = childMaxMainAxisSize)
            }
            val placeable = measurable.measure(childConstraints)
            val spanIndex = index % spanCount
            crossAxisIndex = index / spanCount
            val itemSize = if (isVertical) placeable.height else placeable.width
            val position: Point
            if (spanIndex == firstSpanIndex) {
                if (isVertical) {
                    xPosition = leftPadding
                    yPosition += if (crossAxisIndex > 0) crossAxisSpacing else 0
                    position = Point(xPosition, yPosition)
                    xPosition += placeable.width
                } else {
                    xPosition += if (crossAxisIndex > 0) crossAxisSpacing else 0
                    yPosition = topPadding
                    position = Point(xPosition, yPosition)
                    yPosition += placeable.height
                }
                crossAxisItemSize = itemSize
                crossAxisSize += if (crossAxisIndex > 0) crossAxisSpacing else 0
            } else {
                if (isVertical) {
                    xPosition += mainAxisSpacing
                    position = Point(xPosition, yPosition)
                    xPosition += placeable.width
                } else {
                    yPosition += mainAxisSpacing
                    position = Point(xPosition, yPosition)
                    yPosition += placeable.height
                }
                crossAxisItemSize = max(crossAxisItemSize, itemSize)
            }

            if (spanIndex == lastSpanIndex) {
                if (isVertical) {
                    yPosition += crossAxisItemSize
                } else {
                    xPosition += crossAxisItemSize
                }
            }
            if (spanIndex == lastSpanIndex || index == childSize - 1) {
                crossAxisSize += crossAxisItemSize
            }
            placeable to position
        }
        val widthNeedSize = if (isVertical) mainAxisSize else crossAxisSize
        val heightNeedSize = if (isVertical) crossAxisSize else mainAxisSize
        val width = widthNeedSize.coerceIn(constraints.minWidth, constraints.maxWidth)
        val height = heightNeedSize.coerceIn(constraints.minHeight, constraints.maxHeight)
        return GridMeasureHelperResult(
            mainAxisSize = mainAxisSize,
            crossAxisSize = crossAxisSize,
            width = width,
            height = height,
            spanCount = spanCount,
            itemList = itemList,
        )
    }

    fun placing(placeableScope: Placeable.PlacementScope, result: GridMeasureHelperResult) {
        result.itemList.forEach { (placeable, position) ->
            val finalPosition = applyReverseLayout(
                layoutWidth = result.width,
                layoutHeight = result.height,
                placeable = placeable,
                position = position,
            )
            with(placeableScope) {
                placeable.placeRelative(x = finalPosition.x, y = finalPosition.y)
            }
        }
    }

//    private fun applyArrangement() {
//      // TODO apply arrangement
//    }

    private fun applyReverseLayout(
        layoutWidth: Int,
        layoutHeight: Int,
        placeable: Placeable,
        position: Point
    ): Point {
        if (!reverseLayout) return position
        val isVertical = layoutOrientation == LayoutOrientation.Vertical
        return if (isVertical) {
            val yPosition: Int = layoutHeight - position.y - placeable.height
            Point(position.x, yPosition)
        } else {
            val xPosition: Int = layoutWidth - position.x - placeable.width
            Point(xPosition, position.y)
        }
    }
}