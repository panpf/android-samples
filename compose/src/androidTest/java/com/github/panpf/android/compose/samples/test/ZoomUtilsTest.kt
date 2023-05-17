package com.github.panpf.android.compose.samples.test

import androidx.compose.ui.Alignment
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.ContentScale
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.panpf.android.compose.samples.tools.name
import com.github.panpf.android.compose.samples.tools.toShortString
import com.github.panpf.android.compose.samples.ui.customization.zoomimage.my.Centroid
import com.github.panpf.android.compose.samples.ui.customization.zoomimage.my.computeContentOfContainerRect
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
        var contentAlignment = Alignment.Center
        Assert.assertEquals(
            "containerSize=$containerSize, contentSize=$contentSize, contentScale=$contentScale, scale=0.5f",
            Rect(0f, 0f, 0f, 0f),
            computeTranslationBounds(
                containerSize,
                contentSize,
                contentScale,
                contentAlignment,
                scale = 0.5f
            )
        )
        Assert.assertEquals(
            "containerSize=$containerSize, contentSize=$contentSize, contentScale=$contentScale, scale=1f",
            Rect(0f, 0f, 0f, 0f),
            computeTranslationBounds(
                containerSize,
                contentSize,
                contentScale,
                contentAlignment,
                scale = 1f
            )
        )
        Assert.assertEquals(
            "containerSize=$containerSize, contentSize=$contentSize, contentScale=$contentScale, scale=2f",
            Rect(-1080f, -1920f, 0f, 0f),
            computeTranslationBounds(
                containerSize,
                contentSize,
                contentScale,
                contentAlignment,
                scale = 2f
            )
        )
        Assert.assertEquals(
            "containerSize=$containerSize, contentSize=$contentSize, contentScale=$contentScale, scale=3f",
            Rect(-2160f, -3840f, 0f, 0f),
            computeTranslationBounds(
                containerSize,
                contentSize,
                contentScale,
                contentAlignment,
                scale = 3f
            )
        )
        Assert.assertEquals(
            "containerSize=$containerSize, contentSize=$contentSize, contentScale=$contentScale, scale=4f",
            Rect(-3240f, -5760f, 0f, 0f),
            computeTranslationBounds(
                containerSize,
                contentSize,
                contentScale,
                contentAlignment,
                scale = 4f
            )
        )

        /* contentSize small */ // todo contentSize
        containerSize = Size(1080f, 1920f)
        contentSize = Size(1080f, 720f)
        contentScale = ContentScale.Fit
        Assert.assertEquals(
            "containerSize=$containerSize, contentSize=$contentSize, contentScale=$contentScale, scale=0.5f",
            Rect(0f, 0f, 0f, 0f),
            computeTranslationBounds(
                containerSize,
                contentSize,
                contentScale,
                contentAlignment,
                scale = 0.5f
            )
        )
        Assert.assertEquals(
            "containerSize=$containerSize, contentSize=$contentSize, contentScale=$contentScale, scale=1f",
            Rect(0f, 0f, 0f, 0f),
            computeTranslationBounds(
                containerSize,
                contentSize,
                contentScale,
                contentAlignment,
                scale = 1f
            )
        )
        Assert.assertEquals(
            "containerSize=$containerSize, contentSize=$contentSize, contentScale=$contentScale, scale=2f",
            Rect(-1080f, -1920f, 0f, 0f),
            computeTranslationBounds(
                containerSize,
                contentSize,
                contentScale,
                contentAlignment,
                scale = 2f
            )
        )
        Assert.assertEquals(
            "containerSize=$containerSize, contentSize=$contentSize, contentScale=$contentScale, scale=3f",
            Rect(-2160f, -3840f, 0f, 0f),
            computeTranslationBounds(
                containerSize,
                contentSize,
                contentScale,
                contentAlignment,
                scale = 3f
            )
        )
        Assert.assertEquals(
            "containerSize=$containerSize, contentSize=$contentSize, contentScale=$contentScale, scale=4f",
            Rect(-3240f, -5760f, 0f, 0f),
            computeTranslationBounds(
                containerSize,
                contentSize,
                contentScale,
                contentAlignment,
                scale = 4f
            )
        )

        // todo scale

        /* unspecifiedSize */
        containerSize = Size.Unspecified
        contentSize = Size(1080f, 1920f)
        contentScale = ContentScale.Fit
        Assert.assertEquals(
            "containerSize=$containerSize, contentSize=$contentSize, contentScale=$contentScale, scale=2f",
            Rect(0f, 0f, 0f, 0f),
            computeTranslationBounds(
                containerSize,
                contentSize,
                contentScale,
                contentAlignment,
                scale = 2f
            )
        )
        containerSize = Size(1080f, 1920f)
        contentSize = Size.Unspecified
        contentScale = ContentScale.Fit
        Assert.assertEquals(
            "containerSize=$containerSize, contentSize=$contentSize, contentScale=$contentScale, scale=2f",
            Rect(0f, 0f, 0f, 0f),
            computeTranslationBounds(
                containerSize,
                contentSize,
                contentScale,
                contentAlignment,
                scale = 2f
            )
        )
        containerSize = Size.Unspecified
        contentSize = Size.Unspecified
        contentScale = ContentScale.Fit
        Assert.assertEquals(
            "containerSize=$containerSize, contentSize=$contentSize, contentScale=$contentScale, scale=2f",
            Rect(0f, 0f, 0f, 0f),
            computeTranslationBounds(
                containerSize,
                contentSize,
                contentScale,
                contentAlignment,
                scale = 2f
            )
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
    fun testComputeCentroidByTouchPosition() {
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

    @Test
    fun testComputeContentOfContainerRect() {
        val containerSize = Size(1000f, 2000f)

        var contentScale:ContentScale = ContentScale.None
        var contentSize = Size(200f, 800f)
        listOf(
            Alignment.TopStart to Rect(0f, 0f, 200f, 800f),
            Alignment.TopCenter to Rect(400f, 0f, 600f, 800f),
            Alignment.TopEnd to Rect(800f, 0f, 1000f, 800f),
            Alignment.CenterStart to Rect(0f, 600f, 200f, 1400f),
            Alignment.Center to Rect(400f, 600f, 600f, 1400f),
            Alignment.CenterEnd to Rect(800f, 600f, 1000f, 1400f),
            Alignment.BottomStart to Rect(0f, 1200f, 200f, 2000f),
            Alignment.BottomCenter to Rect(400f, 1200f, 600f, 2000f),
            Alignment.BottomEnd to Rect(800f, 1200f, 1000f, 2000f)
        ).forEach { (alignment, expected) ->
            Assert.assertEquals(
                "containerSize=${containerSize.toShortString()}, contentSize=${contentSize.toShortString()}, contentScale=${contentScale.name}, alignment=${alignment.name}",
                expected,
                computeContentOfContainerRect(containerSize, contentSize, contentScale, alignment)
            )
        }
        contentSize = Size(800f, 200f)
        listOf(
            Alignment.TopStart to Rect(0f, 0f, 800f, 200f),
            Alignment.TopCenter to Rect(100f, 0f, 900f, 200f),
            Alignment.TopEnd to Rect(200f, 0f, 1000f, 200f),
            Alignment.CenterStart to Rect(0f, 900f, 800f, 1100f),
            Alignment.Center to Rect(100f, 900f, 900f, 1100f),
            Alignment.CenterEnd to Rect(200f, 900f, 1000f, 1100f),
            Alignment.BottomStart to Rect(0f, 1800f, 800f, 2000f),
            Alignment.BottomCenter to Rect(100f, 1800f, 900f, 2000f),
            Alignment.BottomEnd to Rect(200f, 1800f, 1000f, 2000f)
        ).forEach { (alignment, expected) ->
            Assert.assertEquals(
                "containerSize=${containerSize.toShortString()}, contentSize=${contentSize.toShortString()}, contentScale=${contentScale.name}, alignment=${alignment.name}",
                expected,
                computeContentOfContainerRect(containerSize, contentSize, contentScale, alignment)
            )
        }

        contentScale = ContentScale.Inside
        contentSize = Size(200f, 800f)
        listOf(
            Alignment.TopStart to Rect(0f, 0f, 200f, 800f),
            Alignment.TopCenter to Rect(400f, 0f, 600f, 800f),
            Alignment.TopEnd to Rect(800f, 0f, 1000f, 800f),
            Alignment.CenterStart to Rect(0f, 600f, 200f, 1400f),
            Alignment.Center to Rect(400f, 600f, 600f, 1400f),
            Alignment.CenterEnd to Rect(800f, 600f, 1000f, 1400f),
            Alignment.BottomStart to Rect(0f, 1200f, 200f, 2000f),
            Alignment.BottomCenter to Rect(400f, 1200f, 600f, 2000f),
            Alignment.BottomEnd to Rect(800f, 1200f, 1000f, 2000f)
        ).forEach { (alignment, expected) ->
            Assert.assertEquals(
                "containerSize=${containerSize.toShortString()}, contentSize=${contentSize.toShortString()}, contentScale=${contentScale.name}, alignment=${alignment.name}",
                expected,
                computeContentOfContainerRect(containerSize, contentSize, contentScale, alignment)
            )
        }
        contentSize = Size(800f, 200f)
        listOf(
            Alignment.TopStart to Rect(0f, 0f, 800f, 200f),
            Alignment.TopCenter to Rect(100f, 0f, 900f, 200f),
            Alignment.TopEnd to Rect(200f, 0f, 1000f, 200f),
            Alignment.CenterStart to Rect(0f, 900f, 800f, 1100f),
            Alignment.Center to Rect(100f, 900f, 900f, 1100f),
            Alignment.CenterEnd to Rect(200f, 900f, 1000f, 1100f),
            Alignment.BottomStart to Rect(0f, 1800f, 800f, 2000f),
            Alignment.BottomCenter to Rect(100f, 1800f, 900f, 2000f),
            Alignment.BottomEnd to Rect(200f, 1800f, 1000f, 2000f)
        ).forEach { (alignment, expected) ->
            Assert.assertEquals(
                "containerSize=${containerSize.toShortString()}, contentSize=${contentSize.toShortString()}, contentScale=${contentScale.name}, alignment=${alignment.name}",
                expected,
                computeContentOfContainerRect(containerSize, contentSize, contentScale, alignment)
            )
        }

        contentScale = ContentScale.Crop
        contentSize = Size(200f, 800f)
        listOf(
            Alignment.TopStart to Rect(0f, 0f, 1000f, 2000f),
            Alignment.TopCenter to Rect(0f, 0f, 1000f, 2000f),
            Alignment.TopEnd to Rect(0f, 0f, 1000f, 2000f),
            Alignment.CenterStart to Rect(0f, 0f, 1000f, 2000f),
            Alignment.Center to Rect(0f, 0f, 1000f, 2000f),
            Alignment.CenterEnd to Rect(0f, 0f, 1000f, 2000f),
            Alignment.BottomStart to Rect(0f, 0f, 1000f, 2000f),
            Alignment.BottomCenter to Rect(0f, 0f, 1000f, 2000f),
            Alignment.BottomEnd to Rect(0f, 0f, 1000f, 2000f)
        ).forEach { (alignment, expected) ->
            Assert.assertEquals(
                "containerSize=${containerSize.toShortString()}, contentSize=${contentSize.toShortString()}, contentScale=${contentScale.name}, alignment=${alignment.name}",
                expected,
                computeContentOfContainerRect(containerSize, contentSize, contentScale, alignment)
            )
        }
        contentSize = Size(800f, 200f)
        listOf(
            Alignment.TopStart to Rect(0f, 0f, 1000f, 2000f),
            Alignment.TopCenter to Rect(0f, 0f, 1000f, 2000f),
            Alignment.TopEnd to Rect(0f, 0f, 1000f, 2000f),
            Alignment.CenterStart to Rect(0f, 0f, 1000f, 2000f),
            Alignment.Center to Rect(0f, 0f, 1000f, 2000f),
            Alignment.CenterEnd to Rect(0f, 0f, 1000f, 2000f),
            Alignment.BottomStart to Rect(0f, 0f, 1000f, 2000f),
            Alignment.BottomCenter to Rect(0f, 0f, 1000f, 2000f),
            Alignment.BottomEnd to Rect(0f, 0f, 1000f, 2000f)
        ).forEach { (alignment, expected) ->
            Assert.assertEquals(
                "containerSize=${containerSize.toShortString()}, contentSize=${contentSize.toShortString()}, contentScale=${contentScale.name}, alignment=${alignment.name}",
                expected,
                computeContentOfContainerRect(containerSize, contentSize, contentScale, alignment)
            )
        }

        contentScale = ContentScale.Fit
        contentSize = Size(200f, 800f)
        listOf(
            Alignment.TopStart to Rect(0f, 0f, 500f, 2000f),
            Alignment.TopCenter to Rect(250f, 0f, 750f, 2000f),
            Alignment.TopEnd to Rect(500f, 0f, 1000f, 2000f),
            Alignment.CenterStart to Rect(0f, 0f, 500f, 2000f),
            Alignment.Center to Rect(250f, 0f, 750f, 2000f),
            Alignment.CenterEnd to Rect(500f, 0f, 1000f, 2000f),
            Alignment.BottomStart to Rect(0f, 0f, 500f, 2000f),
            Alignment.BottomCenter to Rect(250f, 0f, 750f, 2000f),
            Alignment.BottomEnd to Rect(500f, 0f, 1000f, 2000f)
        ).forEach { (alignment, expected) ->
                Assert.assertEquals(
                "containerSize=${containerSize.toShortString()}, contentSize=${contentSize.toShortString()}, contentScale=${contentScale.name}, alignment=${alignment.name}",
                expected,
                computeContentOfContainerRect(containerSize, contentSize, contentScale, alignment)
            )
        }
        contentSize = Size(800f, 200f)
        listOf(
            Alignment.TopStart to Rect(0f, 0f, 1000f, 250f),
            Alignment.TopCenter to Rect(0f, 0f, 1000f, 250f),
            Alignment.TopEnd to Rect(0f, 0f, 1000f, 250f),
            Alignment.CenterStart to Rect(0f, 875f, 1000f, 1125f),
            Alignment.Center to Rect(0f, 875f, 1000f, 1125f),
            Alignment.CenterEnd to Rect(0f, 875f, 1000f, 1125f),
            Alignment.BottomStart to Rect(0f, 1750f, 1000f, 2000f),
            Alignment.BottomCenter to Rect(0f, 1750f, 1000f, 2000f),
            Alignment.BottomEnd to Rect(0f, 1750f, 1000f, 2000f)
        ).forEach { (alignment, expected) ->
                Assert.assertEquals(
                "containerSize=${containerSize.toShortString()}, contentSize=${contentSize.toShortString()}, contentScale=${contentScale.name}, alignment=${alignment.name}",
                expected,
                computeContentOfContainerRect(containerSize, contentSize, contentScale, alignment)
            )
        }

        contentScale = ContentScale.FillWidth
        contentSize = Size(200f, 800f)
        listOf(
            Alignment.TopStart to Rect(0f, 0f, 1000f, 2000f),
            Alignment.TopCenter to Rect(0f, 0f, 1000f, 2000f),
            Alignment.TopEnd to Rect(0f, 0f, 1000f, 2000f),
            Alignment.CenterStart to Rect(0f, 0f, 1000f, 2000f),
            Alignment.Center to Rect(0f, 0f, 1000f, 2000f),
            Alignment.CenterEnd to Rect(0f, 0f, 1000f, 2000f),
            Alignment.BottomStart to Rect(0f, 0f, 1000f, 2000f),
            Alignment.BottomCenter to Rect(0f, 0f, 1000f, 2000f),
            Alignment.BottomEnd to Rect(0f, 0f, 1000f, 2000f)
        ).forEach { (alignment, expected) ->
            Assert.assertEquals(
                "containerSize=${containerSize.toShortString()}, contentSize=${contentSize.toShortString()}, contentScale=${contentScale.name}, alignment=${alignment.name}",
                expected,
                computeContentOfContainerRect(containerSize, contentSize, contentScale, alignment)
            )
        }
        contentSize = Size(800f, 200f)
        listOf(
            Alignment.TopStart to Rect(0f, 0f, 1000f, 250f),
            Alignment.TopCenter to Rect(0f, 0f, 1000f, 250f),
            Alignment.TopEnd to Rect(0f, 0f, 1000f, 250f),
            Alignment.CenterStart to Rect(0f, 875f, 1000f, 1125f),
            Alignment.Center to Rect(0f, 875f, 1000f, 1125f),
            Alignment.CenterEnd to Rect(0f, 875f, 1000f, 1125f),
            Alignment.BottomStart to Rect(0f, 1750f, 1000f, 2000f),
            Alignment.BottomCenter to Rect(0f, 1750f, 1000f, 2000f),
            Alignment.BottomEnd to Rect(0f, 1750f, 1000f, 2000f)
        ).forEach { (alignment, expected) ->
            Assert.assertEquals(
                "containerSize=${containerSize.toShortString()}, contentSize=${contentSize.toShortString()}, contentScale=${contentScale.name}, alignment=${alignment.name}",
                expected,
                computeContentOfContainerRect(containerSize, contentSize, contentScale, alignment)
            )
        }

        contentScale = ContentScale.FillHeight
        contentSize = Size(200f, 800f)
        listOf(
            Alignment.TopStart to Rect(0f, 0f, 500f, 2000f),
            Alignment.TopCenter to Rect(250f, 0f, 750f, 2000f),
            Alignment.TopEnd to Rect(500f, 0f, 1000f, 2000f),
            Alignment.CenterStart to Rect(0f, 0f, 500f, 2000f),
            Alignment.Center to Rect(250f, 0f, 750f, 2000f),
            Alignment.CenterEnd to Rect(500f, 0f, 1000f, 2000f),
            Alignment.BottomStart to Rect(0f, 0f, 500f, 2000f),
            Alignment.BottomCenter to Rect(250f, 0f, 750f, 2000f),
            Alignment.BottomEnd to Rect(500f, 0f, 1000f, 2000f)
        ).forEach { (alignment, expected) ->
            Assert.assertEquals(
                "containerSize=${containerSize.toShortString()}, contentSize=${contentSize.toShortString()}, contentScale=${contentScale.name}, alignment=${alignment.name}",
                expected,
                computeContentOfContainerRect(containerSize, contentSize, contentScale, alignment)
            )
        }
        contentSize = Size(800f, 200f)
        listOf(
            Alignment.TopStart to Rect(0f, 0f, 1000f, 2000f),
            Alignment.TopCenter to Rect(0f, 0f, 1000f, 2000f),
            Alignment.TopEnd to Rect(0f, 0f, 1000f, 2000f),
            Alignment.CenterStart to Rect(0f, 0f, 1000f, 2000f),
            Alignment.Center to Rect(0f, 0f, 1000f, 2000f),
            Alignment.CenterEnd to Rect(0f, 0f, 1000f, 2000f),
            Alignment.BottomStart to Rect(0f, 0f, 1000f, 2000f),
            Alignment.BottomCenter to Rect(0f, 0f, 1000f, 2000f),
            Alignment.BottomEnd to Rect(0f, 0f, 1000f, 2000f)
        ).forEach { (alignment, expected) ->
            Assert.assertEquals(
                "containerSize=${containerSize.toShortString()}, contentSize=${contentSize.toShortString()}, contentScale=${contentScale.name}, alignment=${alignment.name}",
                expected,
                computeContentOfContainerRect(containerSize, contentSize, contentScale, alignment)
            )
        }

        contentScale = ContentScale.FillBounds
        contentSize = Size(200f, 800f)
        listOf(
            Alignment.TopStart to Rect(0f, 0f, 1000f, 2000f),
            Alignment.TopCenter to Rect(0f, 0f, 1000f, 2000f),
            Alignment.TopEnd to Rect(0f, 0f, 1000f, 2000f),
            Alignment.CenterStart to Rect(0f, 0f, 1000f, 2000f),
            Alignment.Center to Rect(0f, 0f, 1000f, 2000f),
            Alignment.CenterEnd to Rect(0f, 0f, 1000f, 2000f),
            Alignment.BottomStart to Rect(0f, 0f, 1000f, 2000f),
            Alignment.BottomCenter to Rect(0f, 0f, 1000f, 2000f),
            Alignment.BottomEnd to Rect(0f, 0f, 1000f, 2000f)
        ).forEach { (alignment, expected) ->
            Assert.assertEquals(
                "containerSize=${containerSize.toShortString()}, contentSize=${contentSize.toShortString()}, contentScale=${contentScale.name}, alignment=${alignment.name}",
                expected,
                computeContentOfContainerRect(containerSize, contentSize, contentScale, alignment)
            )
        }
        contentSize = Size(800f, 200f)
        listOf(
            Alignment.TopStart to Rect(0f, 0f, 1000f, 2000f),
            Alignment.TopCenter to Rect(0f, 0f, 1000f, 2000f),
            Alignment.TopEnd to Rect(0f, 0f, 1000f, 2000f),
            Alignment.CenterStart to Rect(0f, 0f, 1000f, 2000f),
            Alignment.Center to Rect(0f, 0f, 1000f, 2000f),
            Alignment.CenterEnd to Rect(0f, 0f, 1000f, 2000f),
            Alignment.BottomStart to Rect(0f, 0f, 1000f, 2000f),
            Alignment.BottomCenter to Rect(0f, 0f, 1000f, 2000f),
            Alignment.BottomEnd to Rect(0f, 0f, 1000f, 2000f)
        ).forEach { (alignment, expected) ->
            Assert.assertEquals(
                "containerSize=${containerSize.toShortString()}, contentSize=${contentSize.toShortString()}, contentScale=${contentScale.name}, alignment=${alignment.name}",
                expected,
                computeContentOfContainerRect(containerSize, contentSize, contentScale, alignment)
            )
        }
    }
}