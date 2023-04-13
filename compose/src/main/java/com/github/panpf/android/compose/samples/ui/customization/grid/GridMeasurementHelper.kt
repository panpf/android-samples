package com.github.panpf.android.compose.samples.ui.customization.grid

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.MeasureScope
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.LayoutDirection
import kotlin.math.max
import kotlin.math.roundToInt

internal class GridMeasureHelperResult(
    val mainAxisSize: Int,
    val crossAxisSize: Int,
    val spanCount: Int,
    val placeables: List<Placeable>,
)

internal class GridMeasurementHelper(
    private val rows: GridCells,
    private val contentPadding: PaddingValues,  // todo support padding
    private val reverseLayout: Boolean, // todo support reverse layout
    private val layoutDirection: LayoutDirection,   // left to right or right to left todo support rtl
    private val layoutOrientation: LayoutOrientation,   // horizontal or vertical todo support vertical
    private val horizontalArrangement: Arrangement.Horizontal,
    private val verticalArrangement: Arrangement.Vertical,
) {

    fun measure(
        measureScope: MeasureScope,
        measurables: List<Measurable>,
        constraints: Constraints
    ): GridMeasureHelperResult {
        require(constraints.maxWidth != Constraints.Infinity) {
            "Grid can not be infinite width"
        }
        val mainAxisSpacing =
            with(measureScope) { horizontalArrangement.spacing.toPx().roundToInt() }
        val crossAxisSpacing =
            with(measureScope) { verticalArrangement.spacing.toPx().roundToInt() }
        val resolvedSlotSizesSums = with(measureScope) {
            with(rows) {
                val leftPadding =
                    contentPadding.calculateLeftPadding(layoutDirection).toPx().roundToInt()
                val rightPadding =
                    contentPadding.calculateRightPadding(layoutDirection).toPx().roundToInt()
                val availableSize = constraints.maxWidth - leftPadding - rightPadding
                calculateCrossAxisCellSizes(
                    availableSize = availableSize,
                    spacing = mainAxisSpacing
                )
            }
        }
        val spanCount = resolvedSlotSizesSums.size
        val mainAxisSize = constraints.maxWidth
        var crossAxisSize = 0
        var lineHeight = 0
        val childSize = measurables.size
        var line: Int
        val firstSpanIndex = 0
        val lastSpanIndex = spanCount - 1
        val placeables = measurables.mapIndexed { index, measurable ->
            val childMaxWidth = resolvedSlotSizesSums[index % spanCount]
            val childConstraints = Constraints(maxWidth = childMaxWidth)
            val placeable = measurable.measure(childConstraints)
            val spanIndex = index % spanCount
            line = index / spanCount
            val itemHeight = placeable.height
            if (spanIndex == firstSpanIndex) {
                lineHeight = itemHeight
                crossAxisSize += if (line > 0) crossAxisSpacing else 0
            } else {
                lineHeight = max(lineHeight, itemHeight)
            }
            if (spanIndex == lastSpanIndex || index == childSize - 1) {
                crossAxisSize += lineHeight
            }
            placeable
        }
        return GridMeasureHelperResult(
            mainAxisSize = mainAxisSize,
            crossAxisSize = crossAxisSize,
            spanCount = spanCount,
            placeables = placeables
        )
    }

    fun placing(
        measureScope: MeasureScope,
        placeableScope: Placeable.PlacementScope,
        result: GridMeasureHelperResult
    ) {
        with(measureScope) {
            with(placeableScope) {
                var yPosition = 0
                var xPosition = 0
                val spanCount = result.spanCount
//                val leftPadding =
//                    contentPadding.calculateLeftPadding(layoutDirection).toPx().roundToInt()
//                val rightPadding =
//                    contentPadding.calculateRightPadding(layoutDirection).toPx().roundToInt()
                var line: Int
                var lineHeight = 0
                val mainAxisSpacing = horizontalArrangement.spacing.toPx().roundToInt()
                val crossAxisSpacing = verticalArrangement.spacing.toPx().roundToInt()
                val lastSpanIndex = spanCount - 1
                val firstSpanIndex = 0
                result.placeables.forEachIndexed { index, placeable ->
                    line = index / spanCount
                    when (index % spanCount) {
                        firstSpanIndex -> {
                            xPosition = 0
                            yPosition += if (line > 0) crossAxisSpacing else 0
                            placeable.placeRelative(x = xPosition, y = yPosition)
                            xPosition += placeable.width
                            lineHeight = placeable.height
                        }

                        lastSpanIndex -> {
                            xPosition += mainAxisSpacing
                            placeable.placeRelative(x = xPosition, y = yPosition)
                            lineHeight = max(lineHeight, placeable.height)
                            yPosition += lineHeight
                        }

                        else -> {
                            xPosition += mainAxisSpacing
                            placeable.placeRelative(x = xPosition, y = yPosition)
                            xPosition += placeable.width
                            lineHeight = max(lineHeight, placeable.height)
                        }
                    }
                }
            }
        }
    }
}