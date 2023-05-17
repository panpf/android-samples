package com.github.panpf.android.compose.samples.test

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.ContentScale
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.panpf.android.compose.samples.ui.customization.zoomimage.my.Centroid
import com.github.panpf.android.compose.samples.ui.customization.zoomimage.my.computeScaleCentroidByTouchPosition
import com.github.panpf.android.compose.samples.ui.customization.zoomimage.my.computeScaledVisibleCenter
import com.github.panpf.android.compose.samples.ui.customization.zoomimage.my.computeTranslationBounds
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ZoomUtilsTest {

    @Test
    fun testComputeTranslationBounds() {
        var containerSize = Size(1080f, 1920f)
        var contentSize = Size(1080f, 1920f)
        var contentScale = ContentScale.Fit
        Assert.assertEquals(
            "containerSize=$containerSize, contentSize=$contentSize, contentScale=$contentScale, scale=0.5f",
            Rect(0f, 0f, 0f, 0f),
            computeTranslationBounds(containerSize, contentSize, contentScale, scale = 0.5f)
        )
        Assert.assertEquals(
            "containerSize=$containerSize, contentSize=$contentSize, contentScale=$contentScale, scale=1f",
            Rect(0f, 0f, 0f, 0f),
            computeTranslationBounds(containerSize, contentSize, contentScale, scale = 1f)
        )
        Assert.assertEquals(
            "containerSize=$containerSize, contentSize=$contentSize, contentScale=$contentScale, scale=2f",
            Rect(-1080f, -1920f, 0f, 0f),
            computeTranslationBounds(containerSize, contentSize, contentScale, scale = 2f)
        )
        Assert.assertEquals(
            "containerSize=$containerSize, contentSize=$contentSize, contentScale=$contentScale, scale=3f",
            Rect(-2160f, -3840f, 0f, 0f),
            computeTranslationBounds(containerSize, contentSize, contentScale, scale = 3f)
        )
        Assert.assertEquals(
            "containerSize=$containerSize, contentSize=$contentSize, contentScale=$contentScale, scale=4f",
            Rect(-3240f, -5760f, 0f, 0f),
            computeTranslationBounds(containerSize, contentSize, contentScale, scale = 4f)
        )

        /* contentSize small */ // todo contentSize
        containerSize = Size(1080f, 1920f)
        contentSize = Size(1080f, 720f)
        contentScale = ContentScale.Fit
        Assert.assertEquals(
            "containerSize=$containerSize, contentSize=$contentSize, contentScale=$contentScale, scale=0.5f",
            Rect(0f, 0f, 0f, 0f),
            computeTranslationBounds(containerSize, contentSize, contentScale, scale = 0.5f)
        )
        Assert.assertEquals(
            "containerSize=$containerSize, contentSize=$contentSize, contentScale=$contentScale, scale=1f",
            Rect(0f, 0f, 0f, 0f),
            computeTranslationBounds(containerSize, contentSize, contentScale, scale = 1f)
        )
        Assert.assertEquals(
            "containerSize=$containerSize, contentSize=$contentSize, contentScale=$contentScale, scale=2f",
            Rect(-1080f, -1920f, 0f, 0f),
            computeTranslationBounds(containerSize, contentSize, contentScale, scale = 2f)
        )
        Assert.assertEquals(
            "containerSize=$containerSize, contentSize=$contentSize, contentScale=$contentScale, scale=3f",
            Rect(-2160f, -3840f, 0f, 0f),
            computeTranslationBounds(containerSize, contentSize, contentScale, scale = 3f)
        )
        Assert.assertEquals(
            "containerSize=$containerSize, contentSize=$contentSize, contentScale=$contentScale, scale=4f",
            Rect(-3240f, -5760f, 0f, 0f),
            computeTranslationBounds(containerSize, contentSize, contentScale, scale = 4f)
        )

        // todo scale

        /* unspecifiedSize */
        containerSize = Size.Unspecified
        contentSize = Size(1080f, 1920f)
        contentScale = ContentScale.Fit
        Assert.assertEquals(
            "containerSize=$containerSize, contentSize=$contentSize, contentScale=$contentScale, scale=2f",
            Rect(0f, 0f, 0f, 0f),
            computeTranslationBounds(containerSize, contentSize, contentScale, scale = 2f)
        )
        containerSize = Size(1080f, 1920f)
        contentSize = Size.Unspecified
        contentScale = ContentScale.Fit
        Assert.assertEquals(
            "containerSize=$containerSize, contentSize=$contentSize, contentScale=$contentScale, scale=2f",
            Rect(0f, 0f, 0f, 0f),
            computeTranslationBounds(containerSize, contentSize, contentScale, scale = 2f)
        )
        containerSize = Size.Unspecified
        contentSize = Size.Unspecified
        contentScale = ContentScale.Fit
        Assert.assertEquals(
            "containerSize=$containerSize, contentSize=$contentSize, contentScale=$contentScale, scale=2f",
            Rect(0f, 0f, 0f, 0f),
            computeTranslationBounds(containerSize, contentSize, contentScale, scale = 2f)
        )
    }

    @Test
    fun testComputeScaledVisibleCenter() {
        val unspecifiedSize = Size.Unspecified
        val containerSize = Size(1080f, 1920f)

        /* scale=1f */
        Assert.assertEquals(
            "containerSize=$containerSize, scale=1f, translation=Offset(-2000f, -3000f)",
            Offset(1080f, 1920f),
            computeScaledVisibleCenter(containerSize, 1f, Offset(-2000f, -3000f))
        )
        Assert.assertEquals(
            "containerSize=$containerSize, scale=1f, translation=Offset(-810f, -1440f)",
            Offset(1080f, 1920f),
            computeScaledVisibleCenter(containerSize, 1f, Offset(-810f, -1440f))
        )
        Assert.assertEquals(
            "containerSize=$containerSize, scale=1f, translation=Offset(-540f, -960f)",
            Offset(1080f, 1920f),
            computeScaledVisibleCenter(containerSize, 1f, Offset(-540f, -960f))
        )
        Assert.assertEquals(
            "containerSize=$containerSize, scale=1f, translation=Offset(-270f, -480f)",
            Offset(810f, 1440f),
            computeScaledVisibleCenter(containerSize, 1f, Offset(-270f, -480f))
        )
        Assert.assertEquals(
            "containerSize=$containerSize, scale=1f, translation=Offset(0f, 0f)",
            Offset(540f, 960f),
            computeScaledVisibleCenter(containerSize, 1f, Offset(0f, 0f))
        )
        Assert.assertEquals(
            "containerSize=$containerSize, scale=1f, translation=Offset(270f, 480f)",
            Offset(270f, 480f),
            computeScaledVisibleCenter(containerSize, 1f, Offset(270f, 480f))
        )
        Assert.assertEquals(
            "containerSize=$containerSize, scale=1f, translation=Offset(540f, 960f)",
            Offset(0f, 0f),
            computeScaledVisibleCenter(containerSize, 1f, Offset(540f, 960f))
        )
        Assert.assertEquals(
            "containerSize=$containerSize, scale=1f, translation=Offset(810f, 1440f)",
            Offset(0f, 0f),
            computeScaledVisibleCenter(containerSize, 1f, Offset(810f, 1440f))
        )
        Assert.assertEquals(
            "containerSize=$containerSize, scale=1f, translation=Offset(2000f, 3000f)",
            Offset(0f, 0f),
            computeScaledVisibleCenter(containerSize, 1f, Offset(2000f, 3000f))
        )

        /* scale=0.5f */
        Assert.assertEquals(
            "containerSize=$containerSize, scale=0.5f, translation=Offset(-2000f, -3000f)",
            Offset(540f, 960f),
            computeScaledVisibleCenter(containerSize, 0.5f, Offset(-2000f, -3000f))
        )
        Assert.assertEquals(
            "containerSize=$containerSize, scale=0.5f, translation=Offset(-810f, -1440f)",
            Offset(540f, 960f),
            computeScaledVisibleCenter(containerSize, 0.5f, Offset(-810f, -1440f))
        )
        Assert.assertEquals(
            "containerSize=$containerSize, scale=0.5f, translation=Offset(-540f, -960f)",
            Offset(540f, 960f),
            computeScaledVisibleCenter(containerSize, 0.5f, Offset(-540f, -960f))
        )
        Assert.assertEquals(
            "containerSize=$containerSize, scale=0.5f, translation=Offset(-270f, -480f)",
            Offset(540f, 960f),
            computeScaledVisibleCenter(containerSize, 0.5f, Offset(-270f, -480f))
        )
        Assert.assertEquals(
            "containerSize=$containerSize, scale=0.5f, translation=Offset(0f, 0f)",
            Offset(540f, 960f),
            computeScaledVisibleCenter(containerSize, 0.5f, Offset(0f, 0f))
        )
        Assert.assertEquals(
            "containerSize=$containerSize, scale=0.5f, translation=Offset(270f, 480f)",
            Offset(270f, 480f),
            computeScaledVisibleCenter(containerSize, 0.5f, Offset(270f, 480f))
        )
        Assert.assertEquals(
            "containerSize=$containerSize, scale=0.5f, translation=Offset(540f, 960f)",
            Offset(0f, 0f),
            computeScaledVisibleCenter(containerSize, 0.5f, Offset(540f, 960f))
        )
        Assert.assertEquals(
            "containerSize=$containerSize, scale=0.5f, translation=Offset(810f, 1440f)",
            Offset(0f, 0f),
            computeScaledVisibleCenter(containerSize, 0.5f, Offset(810f, 1440f))
        )
        Assert.assertEquals(
            "containerSize=$containerSize, scale=0.5f, translation=Offset(2000f, 3000f)",
            Offset(0f, 0f),
            computeScaledVisibleCenter(containerSize, 0.5f, Offset(2000f, 3000f))
        )

        /* scale=2f */
        Assert.assertEquals(
            "containerSize=$containerSize, scale=2f, translation=Offset(-2000f, -3000f)",
            Offset(2160f, 3840f),
            computeScaledVisibleCenter(containerSize, 2f, Offset(-2000f, -3000f))
        )
        Assert.assertEquals(
            "containerSize=$containerSize, scale=2f, translation=Offset(-810f, -1440f)",
            Offset(1350f, 2400f),
            computeScaledVisibleCenter(containerSize, 2f, Offset(-810f, -1440f))
        )
        Assert.assertEquals(
            "containerSize=$containerSize, scale=2f, translation=Offset(-540f, -960f)",
            Offset(1080f, 1920f),
            computeScaledVisibleCenter(containerSize, 2f, Offset(-540f, -960f))
        )
        Assert.assertEquals(
            "containerSize=$containerSize, scale=2f, translation=Offset(-270f, -480f)",
            Offset(810f, 1440f),
            computeScaledVisibleCenter(containerSize, 2f, Offset(-270f, -480f))
        )
        Assert.assertEquals(
            "containerSize=$containerSize, scale=2f, translation=Offset(0f, 0f)",
            Offset(540f, 960f),
            computeScaledVisibleCenter(containerSize, 2f, Offset(0f, 0f))
        )
        Assert.assertEquals(
            "containerSize=$containerSize, scale=2f, translation=Offset(270f, 480f)",
            Offset(270f, 480f),
            computeScaledVisibleCenter(containerSize, 2f, Offset(270f, 480f))
        )
        Assert.assertEquals(
            "containerSize=$containerSize, scale=2f, translation=Offset(540f, 960f)",
            Offset(0f, 0f),
            computeScaledVisibleCenter(containerSize, 2f, Offset(540f, 960f))
        )
        Assert.assertEquals(
            "containerSize=$containerSize, scale=2f, translation=Offset(810f, 1440f)",
            Offset(0f, 0f),
            computeScaledVisibleCenter(containerSize, 2f, Offset(810f, 1440f))
        )
        Assert.assertEquals(
            "containerSize=$containerSize, scale=2f, translation=Offset(2000f, 3000f)",
            Offset(0f, 0f),
            computeScaledVisibleCenter(containerSize, 2f, Offset(2000f, 3000f))
        )

        // unspecifiedSize
        Assert.assertEquals(
            "containerSize=$containerSize, scale=2f, translation=Offset(270f, 480f)",
            Offset(270f, 480f),
            computeScaledVisibleCenter(containerSize, 2f, Offset(270f, 480f))
        )
        Assert.assertEquals(
            "containerSize=$unspecifiedSize, scale=2f, translation=Offset(270f, 480f)",
            Offset(0f, 0f),
            computeScaledVisibleCenter(unspecifiedSize, 2f, Offset(270f, 480f))
        )
    }

    @Test
    fun testComputeRelativelyCentroidOfContainerByTouchPosition() {
        var containerSize = Size(1080f, 1920f)

        var scale = 1f
        var translation = Offset(0f, 0f)
        listOf(
            Offset(216f, 960f) to Centroid(0.2f, 0.5f),
            Offset(540f, 384f) to Centroid(0.5f, 0.2f),
            Offset(864f, 960f) to Centroid(0.8f, 0.5f),
            Offset(540f, 1536f) to Centroid(0.5f, 0.8f)
        ).forEach { (touchPosition, targetPercentageCentroidOfContent) ->
            Assert.assertEquals(
                "containerSize=$containerSize, scale=$scale, translation=$translation, touchPosition=$touchPosition",
                targetPercentageCentroidOfContent,
                computeScaleCentroidByTouchPosition(
                    containerSize, scale, translation, touchPosition
                )
            )
        }

        scale = 1f
        translation = Offset(540f, 960f)
        listOf(
            Offset(216f, 960f) to Centroid(0f, 0f),
            Offset(540f, 384f) to Centroid(0f, 0f),
            Offset(864f, 960f) to Centroid(0.3f, 0f),
            Offset(540f, 1536f) to Centroid(0f, 0.3f)
        ).forEach { (touchPosition, targetPercentageCentroidOfContent) ->
            Assert.assertEquals(
                "containerSize=$containerSize, scale=$scale, translation=$translation, touchPosition=$touchPosition",
                targetPercentageCentroidOfContent,
                computeScaleCentroidByTouchPosition(
                    containerSize, scale, translation, touchPosition
                )
            )
        }

        scale = 1f
        translation = Offset(-540f, -960f)
        listOf(
            Offset(216f, 960f) to Centroid(0.7f, 1f),
            Offset(540f, 384f) to Centroid(1f, 0.7f),
            Offset(864f, 960f) to Centroid(1f, 1f),
            Offset(540f, 1536f) to Centroid(1f, 1f)
        ).forEach { (touchPosition, targetPercentageCentroidOfContent) ->
            Assert.assertEquals(
                "containerSize=$containerSize, scale=$scale, translation=$translation, touchPosition=$touchPosition",
                targetPercentageCentroidOfContent,
                computeScaleCentroidByTouchPosition(
                    containerSize, scale, translation, touchPosition
                )
            )
        }

        scale = 2f
        translation = Offset(0f, 0f)
        listOf(
            Offset(216f, 960f) to Centroid(0.1f, 0.25f),
            Offset(540f, 384f) to Centroid(0.25f, 0.1f),
            Offset(864f, 960f) to Centroid(0.4f, 0.25f),
            Offset(540f, 1536f) to Centroid(0.25f, 0.4f)
        ).forEach { (touchPosition, targetPercentageCentroidOfContent) ->
            Assert.assertEquals(
                "containerSize=$containerSize, scale=$scale, translation=$translation, touchPosition=$touchPosition",
                targetPercentageCentroidOfContent,
                computeScaleCentroidByTouchPosition(
                    containerSize, scale, translation, touchPosition
                )
            )
        }

        scale = 2f
        translation = Offset(540f, 960f)
        listOf(
            Offset(216f, 960f) to Centroid(0f, 0f),
            Offset(540f, 384f) to Centroid(0f, 0f),
            Offset(864f, 960f) to Centroid(0.15f, 0f),
            Offset(540f, 1536f) to Centroid(0f, 0.15f)
        ).forEach { (touchPosition, targetPercentageCentroidOfContent) ->
            Assert.assertEquals(
                "containerSize=$containerSize, scale=$scale, translation=$translation, touchPosition=$touchPosition",
                targetPercentageCentroidOfContent,
                computeScaleCentroidByTouchPosition(
                    containerSize, scale, translation, touchPosition
                )
            )
        }

        scale = 2f
        translation = Offset(-540f, -960f)
        listOf(
            Offset(216f, 960f) to Centroid(0.35f, 0.5f),
            Offset(540f, 384f) to Centroid(0.5f, 0.35f),
            Offset(864f, 960f) to Centroid(0.65f, 0.5f),
            Offset(540f, 1536f) to Centroid(0.5f, 0.65f)
        ).forEach { (touchPosition, targetPercentageCentroidOfContent) ->
            Assert.assertEquals(
                "containerSize=$containerSize, scale=$scale, translation=$translation, touchPosition=$touchPosition",
                targetPercentageCentroidOfContent,
                computeScaleCentroidByTouchPosition(
                    containerSize, scale, translation, touchPosition
                )
            )
        }

        containerSize = Size.Unspecified
        scale = 1f
        translation = Offset(0f, 0f)
        listOf(
            Offset(216f, 960f) to Centroid(0f, 0f),
            Offset(540f, 384f) to Centroid(0f, 0f),
            Offset(864f, 960f) to Centroid(0f, 0f),
            Offset(540f, 1536f) to Centroid(0f, 0f)
        ).forEach { (touchPosition, targetPercentageCentroidOfContent) ->
            Assert.assertEquals(
                "containerSize=$containerSize, scale=$scale, translation=$translation, touchPosition=$touchPosition",
                targetPercentageCentroidOfContent,
                computeScaleCentroidByTouchPosition(
                    containerSize, scale, translation, touchPosition
                )
            )
        }
    }
}