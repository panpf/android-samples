package com.github.panpf.android.compose.samples.tools

import androidx.compose.ui.Alignment
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.ContentScale
import com.github.panpf.android.compose.samples.ui.customization.zoomimage.my.Centroid
import com.github.panpf.tools4j.math.ktx.format

fun Size.toShortString(): String = "(${width}x$height)"

fun Offset.toShortString(): String = "(${x.format("", 1)}x${y.format("", 1)})"

fun Rect.toShortString(): String =
    "(${left.format("", 1)},${top.format("", 1)},${right.format("", 1)},${bottom.format("", 1)})"

fun Centroid.toShortString(): String = "(${x.format("", 1)}x${y.format("", 1)})"

val ContentScale.name: String
    get() = when (this) {
        ContentScale.FillWidth -> "FillWidth"
        ContentScale.FillHeight -> "FillHeight"
        ContentScale.FillBounds -> "FillBounds"
        ContentScale.Fit -> "Fit"
        ContentScale.Crop -> "Crop"
        ContentScale.Inside -> "Inside"
        ContentScale.None -> "None"
        else -> "Unknown"
    }

val Alignment.name: String
    get() = when (this) {
        Alignment.TopStart -> "TopStart"
        Alignment.TopCenter -> "TopCenter"
        Alignment.TopEnd -> "TopEnd"
        Alignment.CenterStart -> "CenterStart"
        Alignment.Center -> "Center"
        Alignment.CenterEnd -> "CenterEnd"
        Alignment.BottomStart -> "BottomStart"
        Alignment.BottomCenter -> "BottomCenter"
        Alignment.BottomEnd -> "BottomEnd"
        else -> "Unknown"
    }

val Alignment.isStart: Boolean
    get() = this == Alignment.TopStart || this == Alignment.CenterStart || this == Alignment.BottomStart
val Alignment.isHorizontalCenter: Boolean
    get() = this == Alignment.TopCenter || this == Alignment.Center || this == Alignment.BottomCenter
val Alignment.isCenter: Boolean
    get() = this == Alignment.Center
val Alignment.isEnd: Boolean
    get() = this == Alignment.TopEnd || this == Alignment.CenterEnd || this == Alignment.BottomEnd
val Alignment.isTop: Boolean
    get() = this == Alignment.TopStart || this == Alignment.TopCenter || this == Alignment.TopEnd
val Alignment.isVerticalCenter: Boolean
    get() = this == Alignment.CenterStart || this == Alignment.Center || this == Alignment.CenterEnd
val Alignment.isBottom: Boolean
    get() = this == Alignment.BottomStart || this == Alignment.BottomCenter || this == Alignment.BottomEnd
