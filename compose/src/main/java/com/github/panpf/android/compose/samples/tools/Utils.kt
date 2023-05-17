package com.github.panpf.android.compose.samples.tools

import androidx.compose.ui.Alignment
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.ContentScale
import com.github.panpf.android.compose.samples.ui.customization.zoomimage.my.Centroid

fun Size.toShortString(): String = "(${width}x$height)"

fun Offset.toShortString(): String = "(${x.toInt()}x${y.toInt()})"

fun Rect.toShortString(): String =
    "(${left.toInt()},${top.toInt()},${right.toInt()},${bottom.toInt()})"

fun Centroid.toShortString(): String = "(${x.toInt()}x${y.toInt()})"

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