package com.github.panpf.android.compose.samples.ui.base

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.scale
import com.github.panpf.android.compose.samples.R.drawable
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.sin

object MyColor {
    val HalfBlue = Color(127, 127, 255)
    val HalfMagenta = Color(255, 127, 255)
    val HalfGreen = Color(127, 255, 127)
    val HalfRed = Color(255, 127, 127)
    val HalfCyan = Color(127, 255, 255)
    val HalfYellow = Color(255, 255, 127)
    val HalfGray = Color(195, 195, 195)
    val HalfBlack = Color(127, 127, 127)

    val TranslucenceBlue = Color.Blue.copy(alpha = 0.5f)
    val TranslucenceMagenta = Color.Magenta.copy(alpha = 0.5f)
    val TranslucenceGreen = Color.Green.copy(alpha = 0.5f)
    val TranslucenceRed = Color.Red.copy(alpha = 0.5f)
    val TranslucenceCyan = Color.Cyan.copy(alpha = 0.5f)
    val TranslucenceYellow = Color.Yellow.copy(alpha = 0.5f)
    val TranslucenceGray = Color.Gray.copy(alpha = 0.5f)
    val TranslucenceBlack = Color.Black.copy(alpha = 0.5f)

    val rainbows = listOf(
        Color.Blue,
        Color.Magenta,
        Color.Green,
        Color.Red,
        Color.Cyan,
        Color.Yellow,
    )

    val halfRainbows = listOf(
        HalfBlue,
        HalfMagenta,
        HalfGreen,
        HalfRed,
        HalfCyan,
        HalfYellow,
    )

    val translucenceRainbows = listOf(
        TranslucenceBlue,
        TranslucenceMagenta,
        TranslucenceGreen,
        TranslucenceRed,
        TranslucenceCyan,
        TranslucenceYellow,
    )
}

val rainbowColorsBrush = Brush.sweepGradient(
    colors = MyColor.halfRainbows + MyColor.halfRainbows.first()
)

val blackWhiteColorFilter: ColorFilter =
    ColorFilter.colorMatrix(ColorMatrix().apply { setToSaturation(0f) })

val inversionOfNegativeColorFilter: ColorFilter = ColorFilter.colorMatrix(
    ColorMatrix(
        floatArrayOf(
            -1f, 0f, 0f, 0f, 255f,
            0f, -1f, 0f, 0f, 255f,
            0f, 0f, -1f, 0f, 255f,
            0f, 0f, 0f, 1f, 0f
        )
    )
)

/**
 * @param contrast 0f..10f (1 should be default)
 * @param brightness -255f..255f (0 should be default)
 */
fun newColorFilterByContrastAndBrightness(
    contrast: Float = 2f,
    brightness: Float = -180f
): ColorFilter {
    val colorMatrix = floatArrayOf(
        contrast, 0f, 0f, 0f, brightness,
        0f, contrast, 0f, 0f, brightness,
        0f, 0f, contrast, 0f, brightness,
        0f, 0f, 0f, 1f, 0f
    )
    return ColorFilter.colorMatrix(ColorMatrix(colorMatrix))
}

class SquashedOval : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val path = Path().apply {
            // We create an Oval that starts at ¼ of the width, and ends at ¾ of the width of the container.
            addOval(
                Rect(
                    left = size.width / 4f,
                    top = 0f,
                    right = size.width * 3 / 4f,
                    bottom = size.height
                )
            )
        }
        return Outline.Generic(path = path)
    }
}

data class Photo(@DrawableRes val resId: Int, val aspectRatio: Float) {

    fun calculateTargetSize(viewSize: Int, toBig: Boolean): Size {
        return if (aspectRatio >= 1f) {
            val targetWidth = if (toBig) viewSize * 3f else viewSize / 2f
            val targetHeight = (targetWidth / aspectRatio)
            Size(targetWidth, targetHeight)
        } else {
            val targetHeight = if (toBig) viewSize * 3f else viewSize / 2f
            val targetWidth = (targetHeight * aspectRatio)
            Size(targetWidth, targetHeight)
        }
    }
}

val horPhoto = Photo(drawable.dog_hor, 640.toFloat() / 427)
val verPhoto = Photo(drawable.dog_ver, 150.toFloat() / 266)
val httpPhotoUrl =
    "https://images.unsplash.com/photo-1552053831-71594a27632d?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=662&q=80"

data class PhotoItem(val photo: Photo, val name: String, val big: Boolean) {

    private var cacheBitmap: Bitmap? = null
    private var cacheViewSize: Int? = null

    fun calculateTargetSize(viewSize: Int): Size {
        return photo.calculateTargetSize(viewSize, big)
    }

    fun getBitmap(context: Context, viewSize: Int): Bitmap {
        if (viewSize != cacheViewSize) {
            cacheBitmap = null
        }

        val cacheBitmap = cacheBitmap
        if (cacheBitmap != null) return cacheBitmap

        val targetSize = photo.calculateTargetSize(viewSize, big)
        val bitmap = ResourcesCompat.getDrawable(context.resources, photo.resId, null)
            .let { it as BitmapDrawable }.bitmap
        return bitmap.scale(targetSize.width.toInt(), targetSize.height.toInt()).apply {
            this@PhotoItem.cacheBitmap = this
            this@PhotoItem.cacheViewSize = viewSize
        }
    }
}

@Composable
fun Dp.toPx(): Float {
    return with(LocalDensity.current) { this@toPx.toPx() }
}

@Composable
fun Float.toDp(): Dp {
    return with(LocalDensity.current) { this@toDp.toDp() }
}

fun computePentagramPath(size: Size): Path {
    val centerPoint = Offset(size.width / 2, size.height / 2)
    val radius = min(centerPoint.x, centerPoint.y)
    val getPoint: (angle: Float) -> Offset = { angle ->
        Offset(
            x = (centerPoint.x + (radius * cos(angle * Math.PI / 180f))).toFloat(),
            y = (centerPoint.y + (radius * sin(angle * Math.PI / 180f))).toFloat()
        )
    }
    val point1 = getPoint(270f + (0 * 72f))
    val point2 = getPoint(270f + (1 * 72f))
    val point3 = getPoint(270f + (2 * 72f))
    val point4 = getPoint(270f + (3 * 72f))
    val point5 = getPoint(270f + (4 * 72f))
    return Path().apply {
        moveTo(point5.x, point5.y)
        lineTo(point2.x, point2.y)
        lineTo(point4.x, point4.y)
        lineTo(point1.x, point1.y)
        lineTo(point3.x, point3.y)
        close()
    }
}

fun computeTrianglePath(size: Float): Path {
    return Path().apply {
        moveTo(size / 2, 0f)
        lineTo(size, size)
        lineTo(0f, size)
    }
}

fun computeTrianglePath(size: Size): Path {
    return computeTrianglePath(min(size.width, size.height))
}
