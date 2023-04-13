package com.github.panpf.android.compose.samples.ui.customization.grid

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.unit.dp
import com.github.panpf.android.compose.samples.ui.customization.grid.LayoutOrientation.Horizontal
import com.github.panpf.android.compose.samples.ui.customization.grid.LayoutOrientation.Vertical

@Composable
fun VerticalGrid(
    rows: GridCells,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    reverseLayout: Boolean = false,
    verticalArrangement: Arrangement.Vertical = if (!reverseLayout) Arrangement.Top else Arrangement.Bottom,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
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
        content = content
    )
}

@Composable
fun HorizontalGrid(
    rows: GridCells,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    reverseLayout: Boolean = false,
    horizontalArrangement: Arrangement.Horizontal =
        if (!reverseLayout) Arrangement.Start else Arrangement.End,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
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

/**
 * [Row] will be [Horizontal], [Column] is [Vertical].
 */
internal enum class LayoutOrientation {
    Horizontal,
    Vertical
}