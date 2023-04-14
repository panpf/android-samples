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
    private val contentPadding: PaddingValues,
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
        val mainAxisSpacing = with(measureScope) {
            horizontalArrangement.spacing.toPx().roundToInt()
        }
        val crossAxisSpacing = with(measureScope) {
            verticalArrangement.spacing.toPx().roundToInt()
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
                val availableSize = constraints.maxWidth - leftPadding - rightPadding
                calculateCrossAxisCellSizes(
                    availableSize = availableSize,
                    spacing = mainAxisSpacing
                )
            }
        }
        val spanCount = resolvedSlotSizesSums.size
        val mainAxisSize = constraints.maxWidth
        var crossAxisSize = topPadding + bottomPadding
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
        val mainAxisSpacing = with(measureScope) {
            horizontalArrangement.spacing.toPx().roundToInt()
        }
        val crossAxisSpacing = with(measureScope) {
            verticalArrangement.spacing.toPx().roundToInt()
        }
        val leftPadding = with(measureScope) {
            contentPadding.calculateLeftPadding(layoutDirection).toPx().roundToInt()
        }
        val topPadding = with(measureScope) {
            contentPadding.calculateTopPadding().toPx().roundToInt()
        }
        with(placeableScope) {
            var yPosition = topPadding
            var xPosition = leftPadding
            val spanCount = result.spanCount
            var line: Int
            var lineHeight = 0
            val lastSpanIndex = spanCount - 1
            val firstSpanIndex = 0
            result.placeables.forEachIndexed { index, placeable ->
                line = index / spanCount
                val spanIndex = index % spanCount
                if (spanIndex == firstSpanIndex) {
                    xPosition = leftPadding
                    yPosition += if (line > 0) crossAxisSpacing else 0
                    placeable.placeRelative(x = xPosition, y = yPosition)
                    xPosition += placeable.width
                    lineHeight = placeable.height
                } else {
                    xPosition += mainAxisSpacing
                    placeable.placeRelative(x = xPosition, y = yPosition)
                    xPosition += placeable.width
                    lineHeight = max(lineHeight, placeable.height)
                }
                if (spanIndex == lastSpanIndex) {
                    yPosition += lineHeight
                }
            }
        }
    }
}