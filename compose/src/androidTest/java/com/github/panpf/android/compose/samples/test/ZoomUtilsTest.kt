package com.github.panpf.android.compose.samples.test

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.panpf.android.compose.samples.ui.image.zoom.com.github.panpf.sketch.zoom.compose.computeTranslationBoundsWithTopLeftScale
import com.github.panpf.android.compose.samples.ui.image.zoom.com.github.panpf.sketch.zoom.compose.computeVisibleCenterOfScaledContent
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
    fun testComputeVisibleCenterOfContent() {
        val unspecifiedSize = Size.Unspecified
        val spaceSize = Size(1080f, 1920f)
        val contentSize = Size(1080f, 1920f)

        /* scale=1f */
        Assert.assertEquals(
            "spaceSize=$spaceSize, contentSize=$contentSize, scale=1f, translation=Offset(-2000f, -3000f)",
            Offset(1080f, 1920f),
            computeVisibleCenterOfScaledContent(spaceSize, contentSize, 1f, Offset(-2000f, -3000f))
        )
        Assert.assertEquals(
            "spaceSize=$spaceSize, contentSize=$contentSize, scale=1f, translation=Offset(-810f, -1440f)",
            Offset(1080f, 1920f),
            computeVisibleCenterOfScaledContent(spaceSize, contentSize, 1f, Offset(-810f, -1440f))
        )
        Assert.assertEquals(
            "spaceSize=$spaceSize, contentSize=$contentSize, scale=1f, translation=Offset(-540f, -960f)",
            Offset(1080f, 1920f),
            computeVisibleCenterOfScaledContent(spaceSize, contentSize, 1f, Offset(-540f, -960f))
        )
        Assert.assertEquals(
            "spaceSize=$spaceSize, contentSize=$contentSize, scale=1f, translation=Offset(-270f, -480f)",
            Offset(810f, 1440f),
            computeVisibleCenterOfScaledContent(spaceSize, contentSize, 1f, Offset(-270f, -480f))
        )
        Assert.assertEquals(
            "spaceSize=$spaceSize, contentSize=$contentSize, scale=1f, translation=Offset(0f, 0f)",
            Offset(540f, 960f),
            computeVisibleCenterOfScaledContent(spaceSize, contentSize, 1f, Offset(0f, 0f))
        )
        Assert.assertEquals(
            "spaceSize=$spaceSize, contentSize=$contentSize, scale=1f, translation=Offset(270f, 480f)",
            Offset(270f, 480f),
            computeVisibleCenterOfScaledContent(spaceSize, contentSize, 1f, Offset(270f, 480f))
        )
        Assert.assertEquals(
            "spaceSize=$spaceSize, contentSize=$contentSize, scale=1f, translation=Offset(540f, 960f)",
            Offset(0f, 0f),
            computeVisibleCenterOfScaledContent(spaceSize, contentSize, 1f, Offset(540f, 960f))
        )
        Assert.assertEquals(
            "spaceSize=$spaceSize, contentSize=$contentSize, scale=1f, translation=Offset(810f, 1440f)",
            Offset(0f, 0f),
            computeVisibleCenterOfScaledContent(spaceSize, contentSize, 1f, Offset(810f, 1440f))
        )
        Assert.assertEquals(
            "spaceSize=$spaceSize, contentSize=$contentSize, scale=1f, translation=Offset(2000f, 3000f)",
            Offset(0f, 0f),
            computeVisibleCenterOfScaledContent(spaceSize, contentSize, 1f, Offset(2000f, 3000f))
        )

        /* scale=0.5f */
        Assert.assertEquals(
            "spaceSize=$spaceSize, contentSize=$contentSize, scale=0.5f, translation=Offset(-2000f, -3000f)",
            Offset(540f, 960f),
            computeVisibleCenterOfScaledContent(spaceSize, contentSize, 0.5f, Offset(-2000f, -3000f))
        )
        Assert.assertEquals(
            "spaceSize=$spaceSize, contentSize=$contentSize, scale=0.5f, translation=Offset(-810f, -1440f)",
            Offset(540f, 960f),
            computeVisibleCenterOfScaledContent(spaceSize, contentSize, 0.5f, Offset(-810f, -1440f))
        )
        Assert.assertEquals(
            "spaceSize=$spaceSize, contentSize=$contentSize, scale=0.5f, translation=Offset(-540f, -960f)",
            Offset(540f, 960f),
            computeVisibleCenterOfScaledContent(spaceSize, contentSize, 0.5f, Offset(-540f, -960f))
        )
        Assert.assertEquals(
            "spaceSize=$spaceSize, contentSize=$contentSize, scale=0.5f, translation=Offset(-270f, -480f)",
            Offset(540f, 960f),
            computeVisibleCenterOfScaledContent(spaceSize, contentSize, 0.5f, Offset(-270f, -480f))
        )
        Assert.assertEquals(
            "spaceSize=$spaceSize, contentSize=$contentSize, scale=0.5f, translation=Offset(0f, 0f)",
            Offset(540f, 960f),
            computeVisibleCenterOfScaledContent(spaceSize, contentSize, 0.5f, Offset(0f, 0f))
        )
        Assert.assertEquals(
            "spaceSize=$spaceSize, contentSize=$contentSize, scale=0.5f, translation=Offset(270f, 480f)",
            Offset(270f, 480f),
            computeVisibleCenterOfScaledContent(spaceSize, contentSize, 0.5f, Offset(270f, 480f))
        )
        Assert.assertEquals(
            "spaceSize=$spaceSize, contentSize=$contentSize, scale=0.5f, translation=Offset(540f, 960f)",
            Offset(0f, 0f),
            computeVisibleCenterOfScaledContent(spaceSize, contentSize, 0.5f, Offset(540f, 960f))
        )
        Assert.assertEquals(
            "spaceSize=$spaceSize, contentSize=$contentSize, scale=0.5f, translation=Offset(810f, 1440f)",
            Offset(0f, 0f),
            computeVisibleCenterOfScaledContent(spaceSize, contentSize, 0.5f, Offset(810f, 1440f))
        )
        Assert.assertEquals(
            "spaceSize=$spaceSize, contentSize=$contentSize, scale=0.5f, translation=Offset(2000f, 3000f)",
            Offset(0f, 0f),
            computeVisibleCenterOfScaledContent(spaceSize, contentSize, 0.5f, Offset(2000f, 3000f))
        )

        /* scale=2f */
        Assert.assertEquals(
            "spaceSize=$spaceSize, contentSize=$contentSize, scale=2f, translation=Offset(-2000f, -3000f)",
            Offset(2160f, 3840f),
            computeVisibleCenterOfScaledContent(spaceSize, contentSize, 2f, Offset(-2000f, -3000f))
        )
        Assert.assertEquals(
            "spaceSize=$spaceSize, contentSize=$contentSize, scale=2f, translation=Offset(-810f, -1440f)",
            Offset(1350f, 2400f),
            computeVisibleCenterOfScaledContent(spaceSize, contentSize, 2f, Offset(-810f, -1440f))
        )
        Assert.assertEquals(
            "spaceSize=$spaceSize, contentSize=$contentSize, scale=2f, translation=Offset(-540f, -960f)",
            Offset(1080f, 1920f),
            computeVisibleCenterOfScaledContent(spaceSize, contentSize, 2f, Offset(-540f, -960f))
        )
        Assert.assertEquals(
            "spaceSize=$spaceSize, contentSize=$contentSize, scale=2f, translation=Offset(-270f, -480f)",
            Offset(810f, 1440f),
            computeVisibleCenterOfScaledContent(spaceSize, contentSize, 2f, Offset(-270f, -480f))
        )
        Assert.assertEquals(
            "spaceSize=$spaceSize, contentSize=$contentSize, scale=2f, translation=Offset(0f, 0f)",
            Offset(540f, 960f),
            computeVisibleCenterOfScaledContent(spaceSize, contentSize, 2f, Offset(0f, 0f))
        )
        Assert.assertEquals(
            "spaceSize=$spaceSize, contentSize=$contentSize, scale=2f, translation=Offset(270f, 480f)",
            Offset(270f, 480f),
            computeVisibleCenterOfScaledContent(spaceSize, contentSize, 2f, Offset(270f, 480f))
        )
        Assert.assertEquals(
            "spaceSize=$spaceSize, contentSize=$contentSize, scale=2f, translation=Offset(540f, 960f)",
            Offset(0f, 0f),
            computeVisibleCenterOfScaledContent(spaceSize, contentSize, 2f, Offset(540f, 960f))
        )
        Assert.assertEquals(
            "spaceSize=$spaceSize, contentSize=$contentSize, scale=2f, translation=Offset(810f, 1440f)",
            Offset(0f, 0f),
            computeVisibleCenterOfScaledContent(spaceSize, contentSize, 2f, Offset(810f, 1440f))
        )
        Assert.assertEquals(
            "spaceSize=$spaceSize, contentSize=$contentSize, scale=2f, translation=Offset(2000f, 3000f)",
            Offset(0f, 0f),
            computeVisibleCenterOfScaledContent(spaceSize, contentSize, 2f, Offset(2000f, 3000f))
        )

        // unspecifiedSize
        Assert.assertEquals(
            "spaceSize=$spaceSize, contentSize=$unspecifiedSize, scale=2f, translation=Offset(270f, 480f)",
            Offset(0f, 0f),
            computeVisibleCenterOfScaledContent(unspecifiedSize, contentSize, 2f, Offset(270f, 480f))
        )
        Assert.assertEquals(
            "spaceSize=$spaceSize, contentSize=$unspecifiedSize, scale=2f, translation=Offset(270f, 480f)",
            Offset(0f, 0f),
            computeVisibleCenterOfScaledContent(spaceSize, unspecifiedSize, 2f, Offset(270f, 480f))
        )
        Assert.assertEquals(
            "spaceSize=$unspecifiedSize, contentSize=$unspecifiedSize, scale=2f, translation=Offset(270f, 480f)",
            Offset(0f, 0f),
            computeVisibleCenterOfScaledContent(unspecifiedSize, unspecifiedSize, 2f, Offset(270f, 480f))
        )
    }
}