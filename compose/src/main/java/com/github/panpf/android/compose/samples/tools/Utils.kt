package com.github.panpf.android.compose.samples.tools

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import com.github.panpf.android.compose.samples.ui.customization.zoomimage.my.Centroid

fun Size.toShortString(): String = "(${width}x$height)"

fun Offset.toShortString(): String = "(${x.toInt()}x${y.toInt()})"

fun Rect.toShortString(): String =
    "(${left.toInt()},${top.toInt()},${right.toInt()},${bottom.toInt()})"

fun Centroid.toShortString(): String = "(${x.toInt()}x${y.toInt()})"