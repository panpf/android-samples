package com.github.panpf.android.compose.samples.test

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.panpf.android.compose.samples.ui.image.zoom.com.github.panpf.sketch.zoom.compose.computePercentageCentroidOfContentByTouchPoint
import com.github.panpf.android.compose.samples.ui.image.zoom.com.github.panpf.sketch.zoom.compose.computeScaledContentVisibleCenter
import com.github.panpf.android.compose.samples.ui.image.zoom.com.github.panpf.sketch.zoom.compose.computeTranslationBoundsWithTopLeftScale
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ZoomUtilsTest {

    @Test
    fun testComputeTranslationBoundsWithTopLeftScale() {
        val unspecifiedSize = Size.Unspecified
        val spaceSize = Size(1080f, 1920f)
        val contentSize = Size(1080f, 1920f)
        val realContentSize = Size(1080f, 720f)

        Assert.assertEquals(
            "spaceSize=$spaceSize, contentSize=$contentSize, scale=0.5f",
            Rect(0f, 0f, 0f, 0f),
            computeTranslationBoundsWithTopLeftScale(spaceSize, contentSize, scale = 0.5f)
        )
        Assert.assertEquals(
            "spaceSize=$spaceSize, contentSize=$contentSize, scale=1f",
            Rect(0f, 0f, 0f, 0f),
            computeTranslationBoundsWithTopLeftScale(spaceSize, contentSize, scale = 1f)
        )
        Assert.assertEquals(
            "spaceSize=$spaceSize, contentSize=$contentSize, scale=2f",
            Rect(-1080f, -1920f, 0f, 0f),
            computeTranslationBoundsWithTopLeftScale(spaceSize, contentSize, scale = 2f)
        )
        Assert.assertEquals(
            "spaceSize=$spaceSize, contentSize=$contentSize, scale=3f",
            Rect(-2160f, -3840f, 0f, 0f),
            computeTranslationBoundsWithTopLeftScale(spaceSize, contentSize, scale = 3f)
        )
        Assert.assertEquals(
            "spaceSize=$spaceSize, contentSize=$contentSize, scale=4f",
            Rect(-3240f, -5760f, 0f, 0f),
            computeTranslationBoundsWithTopLeftScale(spaceSize, contentSize, scale = 4f)
        )

        /* realContentSize */
        Assert.assertEquals(
            "spaceSize=$spaceSize, contentSize=$realContentSize, scale=0.5f",
            Rect(0f, 0f, 0f, 0f),
            computeTranslationBoundsWithTopLeftScale(spaceSize, realContentSize, scale = 0.5f)
        )
        Assert.assertEquals(
            "spaceSize=$spaceSize, contentSize=$realContentSize, scale=1f",
            Rect(0f, 0f, 0f, 0f),
            computeTranslationBoundsWithTopLeftScale(spaceSize, realContentSize, scale = 1f)
        )
        Assert.assertEquals(
            "spaceSize=$spaceSize, contentSize=$realContentSize, scale=2f",
            Rect(-1080f, 0f, 0f, 0f),
            computeTranslationBoundsWithTopLeftScale(spaceSize, realContentSize, scale = 2f)
        )
        Assert.assertEquals(
            "spaceSize=$spaceSize, contentSize=$realContentSize, scale=3f",
            Rect(-2160f, -240f, 0f, 0f),
            computeTranslationBoundsWithTopLeftScale(spaceSize, realContentSize, scale = 3f)
        )
        Assert.assertEquals(
            "spaceSize=$spaceSize, contentSize=$realContentSize, scale=4f",
            Rect(-3240f, -960f, 0f, 0f),
            computeTranslationBoundsWithTopLeftScale(spaceSize, realContentSize, scale = 4f)
        )

        /* unspecifiedSize */
        Assert.assertEquals(
            "spaceSize=$unspecifiedSize, contentSize=$contentSize, scale=2f",
            Rect(0f, 0f, 0f, 0f),
            computeTranslationBoundsWithTopLeftScale(unspecifiedSize, contentSize, scale = 2f)
        )
        Assert.assertEquals(
            "spaceSize=$spaceSize, contentSize=$unspecifiedSize, scale=2f",
            Rect(0f, 0f, 0f, 0f),
            computeTranslationBoundsWithTopLeftScale(spaceSize, unspecifiedSize, scale = 2f)
        )
        Assert.assertEquals(
            "spaceSize=$unspecifiedSize, contentSize=$unspecifiedSize, scale=2f",
            Rect(0f, 0f, 0f, 0f),
            computeTranslationBoundsWithTopLeftScale(unspecifiedSize, unspecifiedSize, scale = 2f)
        )
    }

    @Test
    fun testComputeScaledContentVisibleCenter() {
        val unspecifiedSize = Size.Unspecified
        val spaceSize = Size(1080f, 1920f)
        val contentSize = Size(1080f, 1920f)

        /* scale=1f */
        Assert.assertEquals(
            "spaceSize=$spaceSize, contentSize=$contentSize, scale=1f, translation=Offset(-2000f, -3000f)",
            Offset(1080f, 1920f),
            computeScaledContentVisibleCenter(spaceSize, contentSize, 1f, Offset(-2000f, -3000f))
        )
        Assert.assertEquals(
            "spaceSize=$spaceSize, contentSize=$contentSize, scale=1f, translation=Offset(-810f, -1440f)",
            Offset(1080f, 1920f),
            computeScaledContentVisibleCenter(spaceSize, contentSize, 1f, Offset(-810f, -1440f))
        )
        Assert.assertEquals(
            "spaceSize=$spaceSize, contentSize=$contentSize, scale=1f, translation=Offset(-540f, -960f)",
            Offset(1080f, 1920f),
            computeScaledContentVisibleCenter(spaceSize, contentSize, 1f, Offset(-540f, -960f))
        )
        Assert.assertEquals(
            "spaceSize=$spaceSize, contentSize=$contentSize, scale=1f, translation=Offset(-270f, -480f)",
            Offset(810f, 1440f),
            computeScaledContentVisibleCenter(spaceSize, contentSize, 1f, Offset(-270f, -480f))
        )
        Assert.assertEquals(
            "spaceSize=$spaceSize, contentSize=$contentSize, scale=1f, translation=Offset(0f, 0f)",
            Offset(540f, 960f),
            computeScaledContentVisibleCenter(spaceSize, contentSize, 1f, Offset(0f, 0f))
        )
        Assert.assertEquals(
            "spaceSize=$spaceSize, contentSize=$contentSize, scale=1f, translation=Offset(270f, 480f)",
            Offset(270f, 480f),
            computeScaledContentVisibleCenter(spaceSize, contentSize, 1f, Offset(270f, 480f))
        )
        Assert.assertEquals(
            "spaceSize=$spaceSize, contentSize=$contentSize, scale=1f, translation=Offset(540f, 960f)",
            Offset(0f, 0f),
            computeScaledContentVisibleCenter(spaceSize, contentSize, 1f, Offset(540f, 960f))
        )
        Assert.assertEquals(
            "spaceSize=$spaceSize, contentSize=$contentSize, scale=1f, translation=Offset(810f, 1440f)",
            Offset(0f, 0f),
            computeScaledContentVisibleCenter(spaceSize, contentSize, 1f, Offset(810f, 1440f))
        )
        Assert.assertEquals(
            "spaceSize=$spaceSize, contentSize=$contentSize, scale=1f, translation=Offset(2000f, 3000f)",
            Offset(0f, 0f),
            computeScaledContentVisibleCenter(spaceSize, contentSize, 1f, Offset(2000f, 3000f))
        )

        /* scale=0.5f */
        Assert.assertEquals(
            "spaceSize=$spaceSize, contentSize=$contentSize, scale=0.5f, translation=Offset(-2000f, -3000f)",
            Offset(540f, 960f),
            computeScaledContentVisibleCenter(spaceSize, contentSize, 0.5f, Offset(-2000f, -3000f))
        )
        Assert.assertEquals(
            "spaceSize=$spaceSize, contentSize=$contentSize, scale=0.5f, translation=Offset(-810f, -1440f)",
            Offset(540f, 960f),
            computeScaledContentVisibleCenter(spaceSize, contentSize, 0.5f, Offset(-810f, -1440f))
        )
        Assert.assertEquals(
            "spaceSize=$spaceSize, contentSize=$contentSize, scale=0.5f, translation=Offset(-540f, -960f)",
            Offset(540f, 960f),
            computeScaledContentVisibleCenter(spaceSize, contentSize, 0.5f, Offset(-540f, -960f))
        )
        Assert.assertEquals(
            "spaceSize=$spaceSize, contentSize=$contentSize, scale=0.5f, translation=Offset(-270f, -480f)",
            Offset(540f, 960f),
            computeScaledContentVisibleCenter(spaceSize, contentSize, 0.5f, Offset(-270f, -480f))
        )
        Assert.assertEquals(
            "spaceSize=$spaceSize, contentSize=$contentSize, scale=0.5f, translation=Offset(0f, 0f)",
            Offset(540f, 960f),
            computeScaledContentVisibleCenter(spaceSize, contentSize, 0.5f, Offset(0f, 0f))
        )
        Assert.assertEquals(
            "spaceSize=$spaceSize, contentSize=$contentSize, scale=0.5f, translation=Offset(270f, 480f)",
            Offset(270f, 480f),
            computeScaledContentVisibleCenter(spaceSize, contentSize, 0.5f, Offset(270f, 480f))
        )
        Assert.assertEquals(
            "spaceSize=$spaceSize, contentSize=$contentSize, scale=0.5f, translation=Offset(540f, 960f)",
            Offset(0f, 0f),
            computeScaledContentVisibleCenter(spaceSize, contentSize, 0.5f, Offset(540f, 960f))
        )
        Assert.assertEquals(
            "spaceSize=$spaceSize, contentSize=$contentSize, scale=0.5f, translation=Offset(810f, 1440f)",
            Offset(0f, 0f),
            computeScaledContentVisibleCenter(spaceSize, contentSize, 0.5f, Offset(810f, 1440f))
        )
        Assert.assertEquals(
            "spaceSize=$spaceSize, contentSize=$contentSize, scale=0.5f, translation=Offset(2000f, 3000f)",
            Offset(0f, 0f),
            computeScaledContentVisibleCenter(spaceSize, contentSize, 0.5f, Offset(2000f, 3000f))
        )

        /* scale=2f */
        Assert.assertEquals(
            "spaceSize=$spaceSize, contentSize=$contentSize, scale=2f, translation=Offset(-2000f, -3000f)",
            Offset(2160f, 3840f),
            computeScaledContentVisibleCenter(spaceSize, contentSize, 2f, Offset(-2000f, -3000f))
        )
        Assert.assertEquals(
            "spaceSize=$spaceSize, contentSize=$contentSize, scale=2f, translation=Offset(-810f, -1440f)",
            Offset(1350f, 2400f),
            computeScaledContentVisibleCenter(spaceSize, contentSize, 2f, Offset(-810f, -1440f))
        )
        Assert.assertEquals(
            "spaceSize=$spaceSize, contentSize=$contentSize, scale=2f, translation=Offset(-540f, -960f)",
            Offset(1080f, 1920f),
            computeScaledContentVisibleCenter(spaceSize, contentSize, 2f, Offset(-540f, -960f))
        )
        Assert.assertEquals(
            "spaceSize=$spaceSize, contentSize=$contentSize, scale=2f, translation=Offset(-270f, -480f)",
            Offset(810f, 1440f),
            computeScaledContentVisibleCenter(spaceSize, contentSize, 2f, Offset(-270f, -480f))
        )
        Assert.assertEquals(
            "spaceSize=$spaceSize, contentSize=$contentSize, scale=2f, translation=Offset(0f, 0f)",
            Offset(540f, 960f),
            computeScaledContentVisibleCenter(spaceSize, contentSize, 2f, Offset(0f, 0f))
        )
        Assert.assertEquals(
            "spaceSize=$spaceSize, contentSize=$contentSize, scale=2f, translation=Offset(270f, 480f)",
            Offset(270f, 480f),
            computeScaledContentVisibleCenter(spaceSize, contentSize, 2f, Offset(270f, 480f))
        )
        Assert.assertEquals(
            "spaceSize=$spaceSize, contentSize=$contentSize, scale=2f, translation=Offset(540f, 960f)",
            Offset(0f, 0f),
            computeScaledContentVisibleCenter(spaceSize, contentSize, 2f, Offset(540f, 960f))
        )
        Assert.assertEquals(
            "spaceSize=$spaceSize, contentSize=$contentSize, scale=2f, translation=Offset(810f, 1440f)",
            Offset(0f, 0f),
            computeScaledContentVisibleCenter(spaceSize, contentSize, 2f, Offset(810f, 1440f))
        )
        Assert.assertEquals(
            "spaceSize=$spaceSize, contentSize=$contentSize, scale=2f, translation=Offset(2000f, 3000f)",
            Offset(0f, 0f),
            computeScaledContentVisibleCenter(spaceSize, contentSize, 2f, Offset(2000f, 3000f))
        )

        // unspecifiedSize
        Assert.assertEquals(
            "spaceSize=$spaceSize, contentSize=$unspecifiedSize, scale=2f, translation=Offset(270f, 480f)",
            Offset(0f, 0f),
            computeScaledContentVisibleCenter(unspecifiedSize, contentSize, 2f, Offset(270f, 480f))
        )
        Assert.assertEquals(
            "spaceSize=$spaceSize, contentSize=$unspecifiedSize, scale=2f, translation=Offset(270f, 480f)",
            Offset(0f, 0f),
            computeScaledContentVisibleCenter(spaceSize, unspecifiedSize, 2f, Offset(270f, 480f))
        )
        Assert.assertEquals(
            "spaceSize=$unspecifiedSize, contentSize=$unspecifiedSize, scale=2f, translation=Offset(270f, 480f)",
            Offset(0f, 0f),
            computeScaledContentVisibleCenter(
                unspecifiedSize,
                unspecifiedSize,
                2f,
                Offset(270f, 480f)
            )
        )
    }

    @Test
    fun testComputePercentageCentroidOfContentByTouchPoint() {
        var spaceSize = Size(1080f, 1920f)
        var contentSize = Size(1080f, 1920f)

        var scale = 1f
        var translation = Offset(0f, 0f)
        listOf(
            Offset(216f, 960f) to Offset(0.2f, 0.5f),
            Offset(540f, 384f) to Offset(0.5f, 0.2f),
            Offset(864f, 960f) to Offset(0.8f, 0.5f),
            Offset(540f, 1536f) to Offset(0.5f, 0.8f)
        ).forEach { (touchPoint, targetPercentageCentroidOfContent) ->
            Assert.assertEquals(
                "spaceSize=$spaceSize, contentSize=$contentSize, scale=$scale, translation=$translation, touchPoint=$touchPoint",
                targetPercentageCentroidOfContent,
                computePercentageCentroidOfContentByTouchPoint(
                    spaceSize,
                    contentSize,
                    scale,
                    translation,
                    touchPoint
                )
            )
        }

        scale = 1f
        translation = Offset(540f, 960f)
        listOf(
            Offset(216f, 960f) to Offset(0f, 0f),
            Offset(540f, 384f) to Offset(0f, 0f),
            Offset(864f, 960f) to Offset(0.3f, 0f),
            Offset(540f, 1536f) to Offset(0f, 0.3f)
        ).forEach { (touchPoint, targetPercentageCentroidOfContent) ->
            Assert.assertEquals(
                "spaceSize=$spaceSize, contentSize=$contentSize, scale=$scale, translation=$translation, touchPoint=$touchPoint",
                targetPercentageCentroidOfContent,
                computePercentageCentroidOfContentByTouchPoint(
                    spaceSize,
                    contentSize,
                    scale,
                    translation,
                    touchPoint
                )
            )
        }

        scale = 1f
        translation = Offset(-540f, -960f)
        listOf(
            Offset(216f, 960f) to Offset(0.7f, 1f),
            Offset(540f, 384f) to Offset(1f, 0.7f),
            Offset(864f, 960f) to Offset(1f, 1f),
            Offset(540f, 1536f) to Offset(1f, 1f)
        ).forEach { (touchPoint, targetPercentageCentroidOfContent) ->
            Assert.assertEquals(
                "spaceSize=$spaceSize, contentSize=$contentSize, scale=$scale, translation=$translation, touchPoint=$touchPoint",
                targetPercentageCentroidOfContent,
                computePercentageCentroidOfContentByTouchPoint(
                    spaceSize,
                    contentSize,
                    scale,
                    translation,
                    touchPoint
                )
            )
        }

        scale = 2f
        translation = Offset(0f, 0f)
        listOf(
            Offset(216f, 960f) to Offset(0.1f, 0.25f),
            Offset(540f, 384f) to Offset(0.25f, 0.1f),
            Offset(864f, 960f) to Offset(0.4f, 0.25f),
            Offset(540f, 1536f) to Offset(0.25f, 0.4f)
        ).forEach { (touchPoint, targetPercentageCentroidOfContent) ->
            Assert.assertEquals(
                "spaceSize=$spaceSize, contentSize=$contentSize, scale=$scale, translation=$translation, touchPoint=$touchPoint",
                targetPercentageCentroidOfContent,
                computePercentageCentroidOfContentByTouchPoint(
                    spaceSize,
                    contentSize,
                    scale,
                    translation,
                    touchPoint
                )
            )
        }

        scale = 2f
        translation = Offset(540f, 960f)
        listOf(
            Offset(216f, 960f) to Offset(0f, 0f),
            Offset(540f, 384f) to Offset(0f, 0f),
            Offset(864f, 960f) to Offset(0.15f, 0f),
            Offset(540f, 1536f) to Offset(0f, 0.15f)
        ).forEach { (touchPoint, targetPercentageCentroidOfContent) ->
            Assert.assertEquals(
                "spaceSize=$spaceSize, contentSize=$contentSize, scale=$scale, translation=$translation, touchPoint=$touchPoint",
                targetPercentageCentroidOfContent,
                computePercentageCentroidOfContentByTouchPoint(
                    spaceSize,
                    contentSize,
                    scale,
                    translation,
                    touchPoint
                )
            )
        }

        scale = 2f
        translation = Offset(-540f, -960f)
        listOf(
            Offset(216f, 960f) to Offset(0.35f, 0.5f),
            Offset(540f, 384f) to Offset(0.5f, 0.35f),
            Offset(864f, 960f) to Offset(0.65f, 0.5f),
            Offset(540f, 1536f) to Offset(0.5f, 0.65f)
        ).forEach { (touchPoint, targetPercentageCentroidOfContent) ->
            Assert.assertEquals(
                "spaceSize=$spaceSize, contentSize=$contentSize, scale=$scale, translation=$translation, touchPoint=$touchPoint",
                targetPercentageCentroidOfContent,
                computePercentageCentroidOfContentByTouchPoint(
                    spaceSize,
                    contentSize,
                    scale,
                    translation,
                    touchPoint
                )
            )
        }

        spaceSize = Size.Unspecified
        contentSize = Size(1080f, 1920f)
        scale = 1f
        translation = Offset(0f, 0f)
        listOf(
            Offset(216f, 960f) to Offset(0f, 0f),
            Offset(540f, 384f) to Offset(0f, 0f),
            Offset(864f, 960f) to Offset(0f, 0f),
            Offset(540f, 1536f) to Offset(0f, 0f)
        ).forEach { (touchPoint, targetPercentageCentroidOfContent) ->
            Assert.assertEquals(
                "spaceSize=$spaceSize, contentSize=$contentSize, scale=$scale, translation=$translation, touchPoint=$touchPoint",
                targetPercentageCentroidOfContent,
                computePercentageCentroidOfContentByTouchPoint(
                    spaceSize,
                    contentSize,
                    scale,
                    translation,
                    touchPoint
                )
            )
        }

        spaceSize = Size(1080f, 1920f)
        contentSize = Size.Unspecified
        scale = 1f
        translation = Offset(0f, 0f)
        listOf(
            Offset(216f, 960f) to Offset(0f, 0f),
            Offset(540f, 384f) to Offset(0f, 0f),
            Offset(864f, 960f) to Offset(0f, 0f),
            Offset(540f, 1536f) to Offset(0f, 0f)
        ).forEach { (touchPoint, targetPercentageCentroidOfContent) ->
            Assert.assertEquals(
                "spaceSize=$spaceSize, contentSize=$contentSize, scale=$scale, translation=$translation, touchPoint=$touchPoint",
                targetPercentageCentroidOfContent,
                computePercentageCentroidOfContentByTouchPoint(
                    spaceSize,
                    contentSize,
                    scale,
                    translation,
                    touchPoint
                )
            )
        }
    }
}