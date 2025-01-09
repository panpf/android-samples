package com.github.panpf.android.samples.model.util

import androidx.core.graphics.scale
import com.github.panpf.sketch.BitmapImage
import com.github.panpf.sketch.Image
import com.github.panpf.sketch.asImage
import com.github.panpf.sketch.request.RequestContext
import com.github.panpf.sketch.transform.TransformResult
import com.github.panpf.sketch.transform.Transformation
import com.github.panpf.tools4k.lang.asOrNull
import kotlin.math.min
import kotlin.math.roundToInt

class BitmapScaleTransformation(val maxSize: Int) : Transformation {

    override val key: String = "BitmapScaleTransformation($maxSize)"

    override fun transform(
        requestContext: RequestContext,
        input: Image
    ): TransformResult? {
        val inputBitmap = input.asOrNull<BitmapImage>()?.bitmap ?: return null
        val scale = min(maxSize / input.width.toFloat(), maxSize / input.height.toFloat())
        val scaledBitmap = inputBitmap.scale(
            width = (input.width * scale).roundToInt(),
            height = (input.height * scale).roundToInt(),
            filter = true
        )
        return TransformResult(scaledBitmap.asImage(), key)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as BitmapScaleTransformation
        return maxSize == other.maxSize
    }

    override fun hashCode(): Int {
        return maxSize
    }

    override fun toString(): String {
        return "BitmapScaleTransformation(maxSize=$maxSize)"
    }
}