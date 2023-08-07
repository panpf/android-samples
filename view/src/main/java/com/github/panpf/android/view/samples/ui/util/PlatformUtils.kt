package com.github.panpf.android.view.samples.ui.util

import android.graphics.Matrix
import com.github.panpf.android.view.samples.util.OffsetCompat
import com.github.panpf.android.view.samples.util.ScaleFactorCompat
import kotlin.math.abs
import kotlin.math.atan2
import kotlin.math.pow
import kotlin.math.roundToInt
import kotlin.math.sqrt

private val matrixValuesLocal = ThreadLocal<FloatArray>()
private val Matrix.localValues: FloatArray
    get() {
        val values = matrixValuesLocal.get()
            ?: FloatArray(9).apply { matrixValuesLocal.set(this) }
        getValues(values)
        return values
    }

internal fun Matrix.getScale(): ScaleFactorCompat {
    val values = localValues

    val scaleX: Float = values[Matrix.MSCALE_X]
    val skewY: Float = values[Matrix.MSKEW_Y]
    val scaleX1 = sqrt(scaleX.toDouble().pow(2.0) + skewY.toDouble().pow(2.0)).toFloat()
    val scaleY: Float = values[Matrix.MSCALE_Y]
    val skewX: Float = values[Matrix.MSKEW_X]
    val scaleY1 = sqrt(scaleY.toDouble().pow(2.0) + skewX.toDouble().pow(2.0)).toFloat()
    @Suppress("UnnecessaryVariable") val scaleFactorCompat =
        ScaleFactorCompat(scaleX = scaleX1, scaleY = scaleY1)
    return scaleFactorCompat
}

internal fun Matrix.getTranslation(): OffsetCompat {
    val values = localValues
    @Suppress("UnnecessaryVariable") val offsetCompat = OffsetCompat(
        x = values[Matrix.MTRANS_X],
        y = values[Matrix.MTRANS_Y]
    )
    return offsetCompat
}

internal fun Matrix.getRotation(): Int {
    val values = localValues
    val skewX: Float = values[Matrix.MSKEW_X]
    val scaleX: Float = values[Matrix.MSCALE_X]
    val degrees = (atan2(skewX.toDouble(), scaleX.toDouble()) * (180 / Math.PI)).roundToInt()
    val rotation = when {
        degrees < 0 -> abs(degrees)
        degrees > 0 -> 360 - degrees
        else -> 0
    }
    return rotation
}