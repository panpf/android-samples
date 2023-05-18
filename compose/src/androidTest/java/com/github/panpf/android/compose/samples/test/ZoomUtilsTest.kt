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
import com.github.panpf.android.compose.samples.ui.customization.zoomimage.my.computeContainerCentroidByTouchPosition
import com.github.panpf.android.compose.samples.ui.customization.zoomimage.my.computeContainerVisibleRect
import com.github.panpf.android.compose.samples.ui.customization.zoomimage.my.computeContentInContainerRect
import com.github.panpf.android.compose.samples.ui.customization.zoomimage.my.computeContentVisibleRect
import com.github.panpf.android.compose.samples.ui.customization.zoomimage.my.computeScaleTargetTranslation
import com.github.panpf.android.compose.samples.ui.customization.zoomimage.my.computeTranslationBounds
import com.github.panpf.android.compose.samples.ui.customization.zoomimage.my.containerCentroidToContentCentroid
import com.github.panpf.android.compose.samples.ui.customization.zoomimage.my.contentCentroidToContainerCentroid
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ZoomUtilsTest {

    @Test
    fun testComputeContentInContainerRect() {
        val containerSize = Size(1000f, 2000f)

        var contentScale: ContentScale = ContentScale.None
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
                computeContentInContainerRect(containerSize, contentSize, contentScale, alignment)
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
                computeContentInContainerRect(containerSize, contentSize, contentScale, alignment)
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
                computeContentInContainerRect(containerSize, contentSize, contentScale, alignment)
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
                computeContentInContainerRect(containerSize, contentSize, contentScale, alignment)
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
                computeContentInContainerRect(containerSize, contentSize, contentScale, alignment)
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
                computeContentInContainerRect(containerSize, contentSize, contentScale, alignment)
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
                computeContentInContainerRect(containerSize, contentSize, contentScale, alignment)
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
                computeContentInContainerRect(containerSize, contentSize, contentScale, alignment)
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
                computeContentInContainerRect(containerSize, contentSize, contentScale, alignment)
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
                computeContentInContainerRect(containerSize, contentSize, contentScale, alignment)
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
                computeContentInContainerRect(containerSize, contentSize, contentScale, alignment)
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
                computeContentInContainerRect(containerSize, contentSize, contentScale, alignment)
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
                computeContentInContainerRect(containerSize, contentSize, contentScale, alignment)
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
                computeContentInContainerRect(containerSize, contentSize, contentScale, alignment)
            )
        }
    }

    @Test
    fun testComputeScaleTargetTranslation() {
        val containerSize = Size(1000f, 2000f)

        var scale = 1f
        listOf(
            Centroid(0.25f, 0.25f) to Offset(0f, 0f),
            Centroid(0.75f, 0.25f) to Offset(-250f, 0f),
            Centroid(0.5f, 0.5f) to Offset(-0f, -0f),
            Centroid(0.25f, 0.75f) to Offset(0f, -500f),
            Centroid(0.75f, 0.75f) to Offset(-250f, -500f),
        ).forEach { (containerCentroid, expected) ->
            Assert.assertEquals(
                "containerSize=$containerSize, scale=$scale, containerCentroid=$containerCentroid",
                expected,
                computeScaleTargetTranslation(containerSize, scale, containerCentroid)
            )
        }

        scale = 2f
        listOf(
            Centroid(0.25f, 0.25f) to Offset(-0f, -0f),
            Centroid(0.75f, 0.25f) to Offset(-1000f, -0f),
            Centroid(0.5f, 0.5f) to Offset(-500f, -1000f),
            Centroid(0.25f, 0.75f) to Offset(-0f, -2000f),
            Centroid(0.75f, 0.75f) to Offset(-1000f, -2000f),
        ).forEach { (containerCentroid, expected) ->
            Assert.assertEquals(
                "containerSize=$containerSize, scale=$scale, containerCentroid=$containerCentroid",
                expected,
                computeScaleTargetTranslation(containerSize, scale, containerCentroid)
            )
        }
    }

    @Test
    fun testComputeTranslationBounds() {
        var containerSize = Size(1080f, 1920f)
        var contentSize = Size(1080f, 1920f)
        var contentScale = ContentScale.Fit
        var contentAlignment = Alignment.Center // todo test
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
    fun testComputeContainerVisibleRect() {
        val containerSize = Size(1000f, 2000f)

        var scale = 1f
        listOf(
            Offset(0f, 0f) to Rect(0f, 0f, 1000f, 2000f),
            Offset(250f, 500f) to Rect(0f, 0f, 750f, 1500f),
            Offset(750f, 500f) to Rect(0f, 0f, 250f, 1500f),
            Offset(250f, 1500f) to Rect(0f, 0f, 750f, 500f),
            Offset(750f, 1500f) to Rect(0f, 0f, 250f, 500f),
            Offset(1000f, 2000f) to Rect(0f, 0f, 0f, 0f),
            Offset(-250f, -500f) to Rect(250f, 500f, 1000f, 2000f),
            Offset(-750f, -500f) to Rect(750f, 500f, 1000f, 2000f),
            Offset(-250f, -1500f) to Rect(250f, 1500f, 1000f, 2000f),
            Offset(-750f, -1500f) to Rect(750f, 1500f, 1000f, 2000f),
            Offset(-1000f, -2000f) to Rect(0f, 0f, 0f, 0f),
        ).forEach { (translation, expectedVisibleRect) ->
            Assert.assertEquals(
                "containerSize=$containerSize, scale=$scale, translation=$translation",
                expectedVisibleRect,
                computeContainerVisibleRect(containerSize, scale, translation)
            )
        }

        scale = 2f
        listOf(
            Offset(0f, 0f) to Rect(0f, 0f, 500f, 1000f),
            Offset(250f, 500f) to Rect(0f, 0f, 375f, 750f),
            Offset(750f, 500f) to Rect(0f, 0f, 125f, 750f),
            Offset(250f, 1500f) to Rect(0f, 0f, 375f, 250f),
            Offset(750f, 1500f) to Rect(0f, 0f, 125f, 250f),
            Offset(1000f, 2000f) to Rect(0f, 0f, 0f, 0f),
            Offset(-250f, -500f) to Rect(125f, 250f, 625f, 1250f),
            Offset(-750f, -500f) to Rect(375f, 250f, 875f, 1250f),
            Offset(-250f, -1500f) to Rect(125f, 750f, 625f, 1750f),
            Offset(-750f, -1500f) to Rect(375f, 750f, 875f, 1750f),
            Offset(-1000f, -2000f) to Rect(500f, 1000f, 1000f, 2000f),
        ).forEach { (translation, expectedVisibleRect) ->
            Assert.assertEquals(
                "containerSize=$containerSize, scale=$scale, translation=$translation",
                expectedVisibleRect,
                computeContainerVisibleRect(containerSize, scale, translation)
            )
        }
    }

    @Test
    fun testComputeContentVisibleRect() {
        val containerSize = Size(1000f, 2000f)
        val contentSize = Size(800f, 1200f)

        var contentScale = ContentScale.Fit
        var contentAlignment = Alignment.Center
        var scale = 1f
        listOf(
            Offset(0f, 0f) to Rect(0f, 0f, 800f, 1200f),
            Offset(250f, 500f) to Rect(0f, 0f, 600f, 1000f),
            Offset(750f, 500f) to Rect(0f, 0f, 200f, 1000f),
            Offset(250f, 1500f) to Rect(0f, 0f, 600f, 200f),
            Offset(750f, 1500f) to Rect(0f, 0f, 200f, 200f),
            Offset(1000f, 2000f) to Rect(0f, 0f, 0f, 0f),
            Offset(-250f, -500f) to Rect(200f, 200f, 800f, 1200f),
            Offset(-750f, -500f) to Rect(600f, 200f, 800f, 1200f),
            Offset(-250f, -1500f) to Rect(200f, 1000f, 800f, 1200f),
            Offset(-750f, -1500f) to Rect(600f, 1000f, 800f, 1200f),
            Offset(-1000f, -2000f) to Rect(0f, 0f, 0f, 0f),
        ).forEach { (translation, expectedVisibleRect) ->
            Assert.assertEquals(
                "containerSize=$containerSize, contentSize=$contentSize, contentScale=$contentScale, contentAlignment=$contentAlignment, scale=$scale, translation=$translation",
                expectedVisibleRect,
                computeContentVisibleRect(
                    containerSize = containerSize,
                    contentSize = contentSize,
                    contentScale = contentScale,
                    contentAlignment = contentAlignment,
                    scale = scale,
                    translation = translation
                )
            )
        }

        contentScale = ContentScale.Fit
        contentAlignment = Alignment.Center
        scale = 2f
        listOf(
            Offset(0.0f, 0.0f) to Rect(0.0f, 0.0f, 400.0f, 600.0f),
            Offset(250.0f, 500.0f) to Rect(0.0f, 0.0f, 300.0f, 400.0f),
            Offset(750.0f, 500.0f) to Rect(0.0f, 0.0f, 100.0f, 400.0f),
            Offset(250.0f, 1500.0f) to Rect(0.0f, 0.0f, 0.0f, 0.0f),
            Offset(750.0f, 1500.0f) to Rect(0.0f, 0.0f, 0.0f, 0.0f),
            Offset(1000.0f, 2000.0f) to Rect(0.0f, 0.0f, 0.0f, 0.0f),
            Offset(-250.0f, -500.0f) to Rect(100.0f, 0.0f, 500.0f, 800.0f),
            Offset(-750.0f, -500.0f) to Rect(300.0f, 0.0f, 700.0f, 800.0f),
            Offset(-250.0f, -1500.0f) to Rect(100.0f, 400.0f, 500.0f, 1200.0f),
            Offset(-750.0f, -1500.0f) to Rect(300.0f, 400.0f, 700.0f, 1200.0f),
            Offset(-1000.0f, -2000.0f) to Rect(400.0f, 600.0f, 800.0f, 1200.0f)
        ).forEach { (translation, expectedVisibleRect) ->
            Assert.assertEquals(
                "containerSize=$containerSize, contentSize=$contentSize, contentScale=$contentScale, contentAlignment=$contentAlignment, scale=$scale, translation=$translation",
                expectedVisibleRect,
                computeContentVisibleRect(
                    containerSize = containerSize,
                    contentSize = contentSize,
                    contentScale = contentScale,
                    contentAlignment = contentAlignment,
                    scale = scale,
                    translation = translation
                )
            )
        }

        contentScale = ContentScale.Inside
        contentAlignment = Alignment.Center
        scale = 1f
        listOf(
            Offset(0.0f, 0.0f) to Rect(0.0f, 0.0f, 800.0f, 1200.0f),
            Offset(250.0f, 500.0f) to Rect(0.0f, 0.0f, 650.0f, 1100.0f),
            Offset(750.0f, 500.0f) to Rect(0.0f, 0.0f, 150.0f, 1100.0f),
            Offset(250.0f, 1500.0f) to Rect(0.0f, 0.0f, 650.0f, 100.0f),
            Offset(750.0f, 1500.0f) to Rect(0.0f, 0.0f, 150.0f, 100.0f),
            Offset(1000.0f, 2000.0f) to Rect(0.0f, 0.0f, 0.0f, 0.0f),
            Offset(-250.0f, -500.0f) to Rect(150.0f, 100.0f, 800.0f, 1200.0f),
            Offset(-750.0f, -500.0f) to Rect(650.0f, 100.0f, 800.0f, 1200.0f),
            Offset(-250.0f, -1500.0f) to Rect(150.0f, 1100.0f, 800.0f, 1200.0f),
            Offset(-750.0f, -1500.0f) to Rect(650.0f, 1100.0f, 800.0f, 1200.0f),
            Offset(-1000.0f, -2000.0f) to Rect(0.0f, 0.0f, 0.0f, 0.0f)
        ).forEach { (translation, expectedVisibleRect) ->
            Assert.assertEquals(
                "containerSize=$containerSize, contentSize=$contentSize, contentScale=$contentScale, contentAlignment=$contentAlignment, scale=$scale, translation=$translation",
                expectedVisibleRect,
                computeContentVisibleRect(
                    containerSize = containerSize,
                    contentSize = contentSize,
                    contentScale = contentScale,
                    contentAlignment = contentAlignment,
                    scale = scale,
                    translation = translation
                )
            )
        }

        contentScale = ContentScale.Inside
        contentAlignment = Alignment.Center
        scale = 2f
        listOf(
            Offset(0.0f, 0.0f) to Rect(0.0f, 0.0f, 400.0f, 600.0f),
            Offset(250.0f, 500.0f) to Rect(0.0f, 0.0f, 275.0f, 350.0f),
            Offset(750.0f, 500.0f) to Rect(0.0f, 0.0f, 25.0f, 350.0f),
            Offset(250.0f, 1500.0f) to Rect(0.0f, 0.0f, 0.0f, 0.0f),
            Offset(750.0f, 1500.0f) to Rect(0.0f, 0.0f, 0.0f, 0.0f),
            Offset(1000.0f, 2000.0f) to Rect(0.0f, 0.0f, 0.0f, 0.0f),
            Offset(-250.0f, -500.0f) to Rect(25.0f, 0.0f, 525.0f, 850.0f),
            Offset(-750.0f, -500.0f) to Rect(275.0f, 0.0f, 775.0f, 850.0f),
            Offset(-250.0f, -1500.0f) to Rect(25.0f, 350.0f, 525.0f, 1200.0f),
            Offset(-750.0f, -1500.0f) to Rect(275.0f, 350.0f, 775.0f, 1200.0f),
            Offset(-1000.0f, -2000.0f) to Rect(400.0f, 600.0f, 800.0f, 1200.0f)
        ).forEach { (translation, expectedVisibleRect) ->
            Assert.assertEquals(
                "containerSize=$containerSize, contentSize=$contentSize, contentScale=$contentScale, contentAlignment=$contentAlignment, scale=$scale, translation=$translation",
                expectedVisibleRect,
                computeContentVisibleRect(
                    containerSize = containerSize,
                    contentSize = contentSize,
                    contentScale = contentScale,
                    contentAlignment = contentAlignment,
                    scale = scale,
                    translation = translation
                )
            )
        }
//        listOf(
//            Offset(0f, 0f) to Rect(0f, 0f, 800f, 1200f),
//            Offset(250f, 500f) to Rect(0f, 0f, 600f, 1000f),
//            Offset(750f, 500f) to Rect(0f, 0f, 200f, 1000f),
//            Offset(250f, 1500f) to Rect(0f, 0f, 600f, 200f),
//            Offset(750f, 1500f) to Rect(0f, 0f, 200f, 200f),
//            Offset(1000f, 2000f) to Rect(0f, 0f, 0f, 0f),
//            Offset(-250f, -500f) to Rect(200f, 200f, 800f, 1200f),
//            Offset(-750f, -500f) to Rect(600f, 200f, 800f, 1200f),
//            Offset(-250f, -1500f) to Rect(200f, 1000f, 800f, 1200f),
//            Offset(-750f, -1500f) to Rect(600f, 1000f, 800f, 1200f),
//            Offset(-1000f, -2000f) to Rect(0f, 0f, 0f, 0f),
//        ).map { (translation, expectedVisibleRect) ->
//            translation to computeContentVisibleRect(
//                containerSize = containerSize,
//                contentSize = contentSize,
//                contentScale = contentScale,
//                contentAlignment = contentAlignment,
//                scale = scale,
//                translation = translation
//            )
//        }.apply {
//            val message = this.joinToString(separator = ", \n") {(translation, visibleRect) ->
//                "Offset(${translation.x}f, ${translation.y}f) to Rect(${visibleRect.left}f, ${visibleRect.top}f, ${visibleRect.right}f, ${visibleRect.bottom}f)"
//            }
//            Assert.fail(message)
//        }
    }


    @Test
    fun testComputeContainerCentroidByTouchPosition() {
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
                computeContainerCentroidByTouchPosition(
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
                computeContainerCentroidByTouchPosition(
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
                computeContainerCentroidByTouchPosition(
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
                computeContainerCentroidByTouchPosition(
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
                computeContainerCentroidByTouchPosition(
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
                computeContainerCentroidByTouchPosition(
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
                computeContainerCentroidByTouchPosition(
                    containerSize, scale, translation, touchPosition
                )
            )
        }
    }

    @Test
    fun testContainerCentroidToContentCentroid() {
        val containerSize = Size(1000f, 2000f)
        val contentSize = Size(800f, 200f)
        var contentScale: ContentScale = ContentScale.None
        val containerCentroid = Centroid(0.5f, 0.5f)
        listOf(
            Alignment.TopStart to Centroid(0.625f, 1.0f),
            Alignment.TopCenter to Centroid(0.5f, 1.0f),
            Alignment.TopEnd to Centroid(0.375f, 1.0f),
            Alignment.CenterStart to Centroid(0.625f, 0.5f),
            Alignment.Center to Centroid(0.5f, 0.5f),
            Alignment.CenterEnd to Centroid(0.375f, 0.5f),
            Alignment.BottomStart to Centroid(0.625f, 0.0f),
            Alignment.BottomCenter to Centroid(0.5f, 0.0f),
            Alignment.BottomEnd to Centroid(0.375f, 0.0f)
        ).forEach { (alignment, expected) ->
            Assert.assertEquals(
                "containerSize=${containerSize.toShortString()}, contentSize=${contentSize.toShortString()}, contentScale=${contentScale.name}, alignment=${alignment.name}, containerCentroid=${containerCentroid.toShortString()}",
                expected,
                containerCentroidToContentCentroid(
                    containerSize,
                    contentSize,
                    contentScale,
                    alignment,
                    containerCentroid
                )
            )
        }

        contentScale = ContentScale.Fit
        val alignment = Alignment.Center
        listOf(
            Centroid(0.2f, 0.2f) to (Centroid(0.2f, 0.0f) to Centroid(0.2f, 0.4375f)),
            Centroid(0.55f, 0.55f) to (Centroid(0.55f, 0.9f) to Centroid(0.55f, 0.55f)),
            Centroid(0.8f, 0.8f) to (Centroid(0.8f, 1.0f) to Centroid(0.8f, 0.5625f)),
        ).forEach { (containerCentroid, pair) ->
            val contentCentroid = containerCentroidToContentCentroid(
                containerSize,
                contentSize,
                contentScale,
                alignment,
                containerCentroid
            )
            Assert.assertEquals(
                "containerSize=${containerSize.toShortString()}, contentSize=${contentSize.toShortString()}, contentScale=${contentScale.name}, alignment=${alignment.name}, containerCentroid=${containerCentroid.toShortString()}",
                pair.first,
                contentCentroid
            )

            val restoredContainerCentroid = contentCentroidToContainerCentroid(
                containerSize,
                contentSize,
                contentScale,
                alignment,
                contentCentroid
            )
            Assert.assertEquals(
                "containerSize=${containerSize.toShortString()}, contentSize=${contentSize.toShortString()}, contentScale=${contentScale.name}, alignment=${alignment.name}, containerCentroid=${containerCentroid.toShortString()}",
                pair.second,
                restoredContainerCentroid
            )
        }
    }

    @Test
    fun testContentCentroidToContainerCentroid() {
        val containerSize = Size(1000f, 2000f)
        val contentSize = Size(800f, 200f)
        val contentCentroid = Centroid(0.5f, 0.5f)
        val contentScale: ContentScale = ContentScale.None
        listOf(
            Alignment.TopStart to Centroid(0.4f, 0.05f),
            Alignment.TopCenter to Centroid(0.5f, 0.05f),
            Alignment.TopEnd to Centroid(0.6f, 0.05f),
            Alignment.CenterStart to Centroid(0.4f, 0.5f),
            Alignment.Center to Centroid(0.5f, 0.5f),
            Alignment.CenterEnd to Centroid(0.6f, 0.5f),
            Alignment.BottomStart to Centroid(0.4f, 0.95f),
            Alignment.BottomCenter to Centroid(0.5f, 0.95f),
            Alignment.BottomEnd to Centroid(0.6f, 0.95f)
        ).forEach { (alignment, expected) ->
            Assert.assertEquals(
                "containerSize=${containerSize.toShortString()}, contentSize=${contentSize.toShortString()}, contentScale=${contentScale.name}, alignment=${alignment.name}, contentCentroid=${contentCentroid.toShortString()}",
                expected,
                contentCentroidToContainerCentroid(
                    containerSize,
                    contentSize,
                    contentScale,
                    alignment,
                    contentCentroid
                )
            )
        }
    }
}