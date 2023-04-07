package com.github.panpf.android.compose.samples.ui.base

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.MeasureScope
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.github.panpf.android.compose.samples.ui.base.LayoutOrientation.Horizontal
import com.github.panpf.android.compose.samples.ui.base.LayoutOrientation.Vertical
import kotlin.math.max
import kotlin.math.roundToInt

@Composable
fun HorizontalGrid(
    rows: GridCells,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    reverseLayout: Boolean = false,
    horizontalArrangement: Arrangement.Horizontal =
        if (!reverseLayout) Arrangement.Start else Arrangement.End,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    crossAxisSize: Dp = 0.dp,
    content: @Composable () -> Unit
) {
    Grid(
        rows = rows,
        layoutOrientation = Horizontal,
        modifier = modifier,
        contentPadding = contentPadding,
        reverseLayout = reverseLayout,
        horizontalArrangement = horizontalArrangement,
        verticalArrangement = verticalArrangement,
        crossAxisSize = crossAxisSize,
        content = content
    )
}

@Composable
fun VerticalGrid(
    rows: GridCells,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    reverseLayout: Boolean = false,
    horizontalArrangement: Arrangement.Horizontal =
        if (!reverseLayout) Arrangement.Start else Arrangement.End,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    crossAxisSize: Dp = 0.dp,
    content: @Composable () -> Unit
) {
    Grid(
        rows = rows,
        layoutOrientation = Vertical,
        modifier = modifier,
        contentPadding = contentPadding,
        reverseLayout = reverseLayout,
        horizontalArrangement = horizontalArrangement,
        verticalArrangement = verticalArrangement,
        crossAxisSize = crossAxisSize,
        content = content
    )
}

@Composable
internal fun Grid(
    rows: GridCells,
    layoutOrientation: LayoutOrientation,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues,
    reverseLayout: Boolean,
    horizontalArrangement: Arrangement.Horizontal,
    verticalArrangement: Arrangement.Vertical,
    crossAxisSize: Dp,
    content: @Composable () -> Unit
) {
    Layout(
        modifier = modifier,
        content = content
    ) { measurables, constraints ->
        val measurementHelper = GridMeasurementHelper(
            rows = rows,
            contentPadding = contentPadding,
            reverseLayout = reverseLayout,
            layoutDirection = layoutDirection,
            layoutOrientation = Vertical,
            horizontalArrangement = horizontalArrangement,
            verticalArrangement = verticalArrangement,
            crossAxisSize = crossAxisSize
        )
        val measureResult = measurementHelper.measure(this, measurables, constraints)
        layout(measureResult.mainAxisSize, measureResult.crossAxisSize) {
            measurementHelper.placing(
                measureScope = this@Layout,
                placeableScope = this@layout,
                result = measureResult
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun HorizontalGridPreview() {
    HorizontalGrid(
//        rows = GridCells.Fixed(2),
        rows = GridCells.Adaptive(100.dp),
        modifier = Modifier.background(Color.Blue.copy(alpha = 0.2f)),
//        crossAxisSize = 100.dp
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        repeat(7) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .width(80.dp)
                    .aspectRatio(1f)
//                    .height(((it + 1) * 10).dp)
                    .border(width = 1.dp, color = Color.Red)
            ) {
                Text("Item ${it + 1}")
            }
        }
    }
    // todo more samples
}

/**
 * [Row] will be [Horizontal], [Column] is [Vertical].
 */
internal enum class LayoutOrientation {
    Horizontal,
    Vertical
}

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
    private val crossAxisSize: Dp,
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
        val crossAxisSizePx = with(measureScope) { crossAxisSize.toPx().roundToInt() }
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
        var line = 0
        val firstSpanIndex = 0
        val lastSpanIndex = spanCount - 1
        val placeables = measurables.mapIndexed { index, measurable ->
            val childMaxWidth = resolvedSlotSizesSums[index % spanCount]
            val childConstraints = if (crossAxisSizePx > 0) {
                Constraints(
                    maxWidth = childMaxWidth,
                    minHeight = crossAxisSizePx,
                    maxHeight = crossAxisSizePx
                )
            } else {
                Constraints(maxWidth = childMaxWidth)
            }
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