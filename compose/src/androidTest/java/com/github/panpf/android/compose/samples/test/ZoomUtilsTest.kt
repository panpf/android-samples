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
import com.github.panpf.android.compose.samples.ui.customization.zoomimage.my.computeContentInContainerVisibleRect
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

    data class Item(
        val contentScale: ContentScale,
        val contentAlignment: Alignment,
        val expected: Rect
    ) {
        fun getMessage(containerSize: Size, contentSize: Size): String {
            return "Item(containerSize=${containerSize.toShortString()}, contentSize=${contentSize.toShortString()}, contentScale=${contentScale.name}, contentAlignment=${contentAlignment.name})"
        }

        fun getMessage(containerSize: Size, contentSize: Size, scale: Float): String {
            return "Item(containerSize=${containerSize.toShortString()}, contentSize=${contentSize.toShortString()}, contentScale=${contentScale.name}, contentAlignment=${contentAlignment.name}, scale=${scale})"
        }
    }

    private fun List<Item>.printlnExpectedMessage(computeExpected: (Item) -> Rect): List<Item> {
        this.map {
            val expected = computeExpected(it)
            "Item(ContentScale.${it.contentScale.name}, Alignment.${it.contentAlignment.name}, Rect(${expected.run { "${left}f,${top}f,${right}f,${bottom}f" }}))"
        }.apply {
            Assert.fail(joinToString(separator = ", \n", postfix = ","))
        }
        return this
    }

    @Test
    fun testComputeContentInContainerRect() {
        val containerSize = Size(1000f, 1000f)

        var contentSize = Size(800f, 400f)
        listOf(
            Item(ContentScale.None, Alignment.TopStart, Rect(0f, 0f, 800f, 400f)),
            Item(ContentScale.None, Alignment.TopCenter, Rect(100f, 0f, 900f, 400f)),
            Item(ContentScale.None, Alignment.TopEnd, Rect(200f, 0f, 1000f, 400f)),
            Item(ContentScale.None, Alignment.CenterStart, Rect(0f, 300f, 800f, 700f)),
            Item(ContentScale.None, Alignment.Center, Rect(100f, 300f, 900f, 700f)),
            Item(ContentScale.None, Alignment.CenterEnd, Rect(200f, 300f, 1000f, 700f)),
            Item(ContentScale.None, Alignment.BottomStart, Rect(0f, 600f, 800f, 1000f)),
            Item(ContentScale.None, Alignment.BottomCenter, Rect(100f, 600f, 900f, 1000f)),
            Item(ContentScale.None, Alignment.BottomEnd, Rect(200f, 600f, 1000f, 1000f)),
            Item(ContentScale.Inside, Alignment.TopStart, Rect(0f, 0f, 800f, 400f)),
            Item(ContentScale.Inside, Alignment.TopCenter, Rect(100f, 0f, 900f, 400f)),
            Item(ContentScale.Inside, Alignment.TopEnd, Rect(200f, 0f, 1000f, 400f)),
            Item(ContentScale.Inside, Alignment.CenterStart, Rect(0f, 300f, 800f, 700f)),
            Item(ContentScale.Inside, Alignment.Center, Rect(100f, 300f, 900f, 700f)),
            Item(ContentScale.Inside, Alignment.CenterEnd, Rect(200f, 300f, 1000f, 700f)),
            Item(ContentScale.Inside, Alignment.BottomStart, Rect(0f, 600f, 800f, 1000f)),
            Item(ContentScale.Inside, Alignment.BottomCenter, Rect(100f, 600f, 900f, 1000f)),
            Item(ContentScale.Inside, Alignment.BottomEnd, Rect(200f, 600f, 1000f, 1000f)),
            Item(ContentScale.Fit, Alignment.TopStart, Rect(0f, 0f, 1000f, 500f)),
            Item(ContentScale.Fit, Alignment.TopCenter, Rect(0f, 0f, 1000f, 500f)),
            Item(ContentScale.Fit, Alignment.TopEnd, Rect(0f, 0f, 1000f, 500f)),
            Item(ContentScale.Fit, Alignment.CenterStart, Rect(0f, 250f, 1000f, 750f)),
            Item(ContentScale.Fit, Alignment.Center, Rect(0f, 250f, 1000f, 750f)),
            Item(ContentScale.Fit, Alignment.CenterEnd, Rect(0f, 250f, 1000f, 750f)),
            Item(ContentScale.Fit, Alignment.BottomStart, Rect(0f, 500f, 1000f, 1000f)),
            Item(ContentScale.Fit, Alignment.BottomCenter, Rect(0f, 500f, 1000f, 1000f)),
            Item(ContentScale.Fit, Alignment.BottomEnd, Rect(0f, 500f, 1000f, 1000f)),
            Item(ContentScale.FillWidth, Alignment.TopStart, Rect(0f, 0f, 1000f, 500f)),
            Item(ContentScale.FillWidth, Alignment.TopCenter, Rect(0f, 0f, 1000f, 500f)),
            Item(ContentScale.FillWidth, Alignment.TopEnd, Rect(0f, 0f, 1000f, 500f)),
            Item(ContentScale.FillWidth, Alignment.CenterStart, Rect(0f, 250f, 1000f, 750f)),
            Item(ContentScale.FillWidth, Alignment.Center, Rect(0f, 250f, 1000f, 750f)),
            Item(ContentScale.FillWidth, Alignment.CenterEnd, Rect(0f, 250f, 1000f, 750f)),
            Item(ContentScale.FillWidth, Alignment.BottomStart, Rect(0f, 500f, 1000f, 1000f)),
            Item(ContentScale.FillWidth, Alignment.BottomCenter, Rect(0f, 500f, 1000f, 1000f)),
            Item(ContentScale.FillWidth, Alignment.BottomEnd, Rect(0f, 500f, 1000f, 1000f)),
            Item(ContentScale.FillHeight, Alignment.TopStart, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.FillHeight, Alignment.TopCenter, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.FillHeight, Alignment.TopEnd, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.FillHeight, Alignment.CenterStart, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.FillHeight, Alignment.Center, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.FillHeight, Alignment.CenterEnd, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.FillHeight, Alignment.BottomStart, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.FillHeight, Alignment.BottomCenter, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.FillHeight, Alignment.BottomEnd, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.FillBounds, Alignment.TopStart, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.FillBounds, Alignment.TopCenter, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.FillBounds, Alignment.TopEnd, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.FillBounds, Alignment.CenterStart, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.FillBounds, Alignment.Center, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.FillBounds, Alignment.CenterEnd, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.FillBounds, Alignment.BottomStart, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.FillBounds, Alignment.BottomCenter, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.FillBounds, Alignment.BottomEnd, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.Crop, Alignment.TopStart, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.Crop, Alignment.TopCenter, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.Crop, Alignment.TopEnd, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.Crop, Alignment.CenterStart, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.Crop, Alignment.Center, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.Crop, Alignment.CenterEnd, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.Crop, Alignment.BottomStart, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.Crop, Alignment.BottomCenter, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.Crop, Alignment.BottomEnd, Rect(0f, 0f, 1000f, 1000f)),
//        ).printlnExpectedMessage(
//            computeExpected =  {
//                computeContentInContainerRect(
//                    containerSize = containerSize,
//                    contentSize = contentSize,
//                    contentScale = it.contentScale,
//                    contentAlignment = it.contentAlignment,
//                )
//            }
        ).forEach {
            Assert.assertEquals(
                it.getMessage(containerSize, contentSize),
                it.expected,
                computeContentInContainerRect(
                    containerSize = containerSize,
                    contentSize = contentSize,
                    contentScale = it.contentScale,
                    contentAlignment = it.contentAlignment,
                )
            )
        }

        contentSize = Size(400f, 800f)
        listOf(
            Item(ContentScale.None, Alignment.TopStart, Rect(0f, 0f, 400f, 800f)),
            Item(ContentScale.None, Alignment.TopCenter, Rect(300f, 0f, 700f, 800f)),
            Item(ContentScale.None, Alignment.TopEnd, Rect(600f, 0f, 1000f, 800f)),
            Item(ContentScale.None, Alignment.CenterStart, Rect(0f, 100f, 400f, 900f)),
            Item(ContentScale.None, Alignment.Center, Rect(300f, 100f, 700f, 900f)),
            Item(ContentScale.None, Alignment.CenterEnd, Rect(600f, 100f, 1000f, 900f)),
            Item(ContentScale.None, Alignment.BottomStart, Rect(0f, 200f, 400f, 1000f)),
            Item(ContentScale.None, Alignment.BottomCenter, Rect(300f, 200f, 700f, 1000f)),
            Item(ContentScale.None, Alignment.BottomEnd, Rect(600f, 200f, 1000f, 1000f)),
            Item(ContentScale.Inside, Alignment.TopStart, Rect(0f, 0f, 400f, 800f)),
            Item(ContentScale.Inside, Alignment.TopCenter, Rect(300f, 0f, 700f, 800f)),
            Item(ContentScale.Inside, Alignment.TopEnd, Rect(600f, 0f, 1000f, 800f)),
            Item(ContentScale.Inside, Alignment.CenterStart, Rect(0f, 100f, 400f, 900f)),
            Item(ContentScale.Inside, Alignment.Center, Rect(300f, 100f, 700f, 900f)),
            Item(ContentScale.Inside, Alignment.CenterEnd, Rect(600f, 100f, 1000f, 900f)),
            Item(ContentScale.Inside, Alignment.BottomStart, Rect(0f, 200f, 400f, 1000f)),
            Item(ContentScale.Inside, Alignment.BottomCenter, Rect(300f, 200f, 700f, 1000f)),
            Item(ContentScale.Inside, Alignment.BottomEnd, Rect(600f, 200f, 1000f, 1000f)),
            Item(ContentScale.Fit, Alignment.TopStart, Rect(0f, 0f, 500f, 1000f)),
            Item(ContentScale.Fit, Alignment.TopCenter, Rect(250f, 0f, 750f, 1000f)),
            Item(ContentScale.Fit, Alignment.TopEnd, Rect(500f, 0f, 1000f, 1000f)),
            Item(ContentScale.Fit, Alignment.CenterStart, Rect(0f, 0f, 500f, 1000f)),
            Item(ContentScale.Fit, Alignment.Center, Rect(250f, 0f, 750f, 1000f)),
            Item(ContentScale.Fit, Alignment.CenterEnd, Rect(500f, 0f, 1000f, 1000f)),
            Item(ContentScale.Fit, Alignment.BottomStart, Rect(0f, 0f, 500f, 1000f)),
            Item(ContentScale.Fit, Alignment.BottomCenter, Rect(250f, 0f, 750f, 1000f)),
            Item(ContentScale.Fit, Alignment.BottomEnd, Rect(500f, 0f, 1000f, 1000f)),
            Item(ContentScale.FillWidth, Alignment.TopStart, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.FillWidth, Alignment.TopCenter, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.FillWidth, Alignment.TopEnd, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.FillWidth, Alignment.CenterStart, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.FillWidth, Alignment.Center, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.FillWidth, Alignment.CenterEnd, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.FillWidth, Alignment.BottomStart, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.FillWidth, Alignment.BottomCenter, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.FillWidth, Alignment.BottomEnd, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.FillHeight, Alignment.TopStart, Rect(0f, 0f, 500f, 1000f)),
            Item(ContentScale.FillHeight, Alignment.TopCenter, Rect(250f, 0f, 750f, 1000f)),
            Item(ContentScale.FillHeight, Alignment.TopEnd, Rect(500f, 0f, 1000f, 1000f)),
            Item(ContentScale.FillHeight, Alignment.CenterStart, Rect(0f, 0f, 500f, 1000f)),
            Item(ContentScale.FillHeight, Alignment.Center, Rect(250f, 0f, 750f, 1000f)),
            Item(ContentScale.FillHeight, Alignment.CenterEnd, Rect(500f, 0f, 1000f, 1000f)),
            Item(ContentScale.FillHeight, Alignment.BottomStart, Rect(0f, 0f, 500f, 1000f)),
            Item(ContentScale.FillHeight, Alignment.BottomCenter, Rect(250f, 0f, 750f, 1000f)),
            Item(ContentScale.FillHeight, Alignment.BottomEnd, Rect(500f, 0f, 1000f, 1000f)),
            Item(ContentScale.FillBounds, Alignment.TopStart, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.FillBounds, Alignment.TopCenter, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.FillBounds, Alignment.TopEnd, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.FillBounds, Alignment.CenterStart, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.FillBounds, Alignment.Center, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.FillBounds, Alignment.CenterEnd, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.FillBounds, Alignment.BottomStart, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.FillBounds, Alignment.BottomCenter, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.FillBounds, Alignment.BottomEnd, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.Crop, Alignment.TopStart, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.Crop, Alignment.TopCenter, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.Crop, Alignment.TopEnd, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.Crop, Alignment.CenterStart, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.Crop, Alignment.Center, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.Crop, Alignment.CenterEnd, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.Crop, Alignment.BottomStart, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.Crop, Alignment.BottomCenter, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.Crop, Alignment.BottomEnd, Rect(0f, 0f, 1000f, 1000f)),
//        ).printlnExpectedMessage(
//            computeExpected =  {
//                computeContentInContainerRect(
//                    containerSize = containerSize,
//                    contentSize = contentSize,
//                    contentScale = it.contentScale,
//                    contentAlignment = it.contentAlignment,
//                )
//            }
        ).forEach {
            Assert.assertEquals(
                it.getMessage(containerSize, contentSize),
                it.expected,
                computeContentInContainerRect(
                    containerSize = containerSize,
                    contentSize = contentSize,
                    contentScale = it.contentScale,
                    contentAlignment = it.contentAlignment,
                )
            )
        }

        contentSize = Size(1600f, 1200f)
        listOf(
            Item(ContentScale.None, Alignment.TopStart, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.None, Alignment.TopCenter, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.None, Alignment.TopEnd, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.None, Alignment.CenterStart, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.None, Alignment.Center, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.None, Alignment.CenterEnd, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.None, Alignment.BottomStart, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.None, Alignment.BottomCenter, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.None, Alignment.BottomEnd, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.Inside, Alignment.TopStart, Rect(0f, 0f, 1000f, 750f)),
            Item(ContentScale.Inside, Alignment.TopCenter, Rect(0f, 0f, 1000f, 750f)),
            Item(ContentScale.Inside, Alignment.TopEnd, Rect(0f, 0f, 1000f, 750f)),
            Item(ContentScale.Inside, Alignment.CenterStart, Rect(0f, 125f, 1000f, 875f)),
            Item(ContentScale.Inside, Alignment.Center, Rect(0f, 125f, 1000f, 875f)),
            Item(ContentScale.Inside, Alignment.CenterEnd, Rect(0f, 125f, 1000f, 875f)),
            Item(ContentScale.Inside, Alignment.BottomStart, Rect(0f, 250f, 1000f, 1000f)),
            Item(ContentScale.Inside, Alignment.BottomCenter, Rect(0f, 250f, 1000f, 1000f)),
            Item(ContentScale.Inside, Alignment.BottomEnd, Rect(0f, 250f, 1000f, 1000f)),
            Item(ContentScale.Fit, Alignment.TopStart, Rect(0f, 0f, 1000f, 750f)),
            Item(ContentScale.Fit, Alignment.TopCenter, Rect(0f, 0f, 1000f, 750f)),
            Item(ContentScale.Fit, Alignment.TopEnd, Rect(0f, 0f, 1000f, 750f)),
            Item(ContentScale.Fit, Alignment.CenterStart, Rect(0f, 125f, 1000f, 875f)),
            Item(ContentScale.Fit, Alignment.Center, Rect(0f, 125f, 1000f, 875f)),
            Item(ContentScale.Fit, Alignment.CenterEnd, Rect(0f, 125f, 1000f, 875f)),
            Item(ContentScale.Fit, Alignment.BottomStart, Rect(0f, 250f, 1000f, 1000f)),
            Item(ContentScale.Fit, Alignment.BottomCenter, Rect(0f, 250f, 1000f, 1000f)),
            Item(ContentScale.Fit, Alignment.BottomEnd, Rect(0f, 250f, 1000f, 1000f)),
            Item(ContentScale.FillWidth, Alignment.TopStart, Rect(0f, 0f, 1000f, 750f)),
            Item(ContentScale.FillWidth, Alignment.TopCenter, Rect(0f, 0f, 1000f, 750f)),
            Item(ContentScale.FillWidth, Alignment.TopEnd, Rect(0f, 0f, 1000f, 750f)),
            Item(ContentScale.FillWidth, Alignment.CenterStart, Rect(0f, 125f, 1000f, 875f)),
            Item(ContentScale.FillWidth, Alignment.Center, Rect(0f, 125f, 1000f, 875f)),
            Item(ContentScale.FillWidth, Alignment.CenterEnd, Rect(0f, 125f, 1000f, 875f)),
            Item(ContentScale.FillWidth, Alignment.BottomStart, Rect(0f, 250f, 1000f, 1000f)),
            Item(ContentScale.FillWidth, Alignment.BottomCenter, Rect(0f, 250f, 1000f, 1000f)),
            Item(ContentScale.FillWidth, Alignment.BottomEnd, Rect(0f, 250f, 1000f, 1000f)),
            Item(ContentScale.FillHeight, Alignment.TopStart, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.FillHeight, Alignment.TopCenter, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.FillHeight, Alignment.TopEnd, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.FillHeight, Alignment.CenterStart, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.FillHeight, Alignment.Center, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.FillHeight, Alignment.CenterEnd, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.FillHeight, Alignment.BottomStart, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.FillHeight, Alignment.BottomCenter, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.FillHeight, Alignment.BottomEnd, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.FillBounds, Alignment.TopStart, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.FillBounds, Alignment.TopCenter, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.FillBounds, Alignment.TopEnd, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.FillBounds, Alignment.CenterStart, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.FillBounds, Alignment.Center, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.FillBounds, Alignment.CenterEnd, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.FillBounds, Alignment.BottomStart, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.FillBounds, Alignment.BottomCenter, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.FillBounds, Alignment.BottomEnd, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.Crop, Alignment.TopStart, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.Crop, Alignment.TopCenter, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.Crop, Alignment.TopEnd, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.Crop, Alignment.CenterStart, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.Crop, Alignment.Center, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.Crop, Alignment.CenterEnd, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.Crop, Alignment.BottomStart, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.Crop, Alignment.BottomCenter, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.Crop, Alignment.BottomEnd, Rect(0f, 0f, 1000f, 1000f)),
//        ).printlnExpectedMessage(
//            computeExpected =  {
//                computeContentInContainerRect(
//                    containerSize = containerSize,
//                    contentSize = contentSize,
//                    contentScale = it.contentScale,
//                    contentAlignment = it.contentAlignment,
//                )
//            }
        ).forEach {
            Assert.assertEquals(
                it.getMessage(containerSize, contentSize),
                it.expected,
                computeContentInContainerRect(
                    containerSize = containerSize,
                    contentSize = contentSize,
                    contentScale = it.contentScale,
                    contentAlignment = it.contentAlignment,
                )
            )
        }

        contentSize = Size(1200f, 1600f)
        listOf(
            Item(ContentScale.None, Alignment.TopStart, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.None, Alignment.TopCenter, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.None, Alignment.TopEnd, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.None, Alignment.CenterStart, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.None, Alignment.Center, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.None, Alignment.CenterEnd, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.None, Alignment.BottomStart, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.None, Alignment.BottomCenter, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.None, Alignment.BottomEnd, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.Inside, Alignment.TopStart, Rect(0f, 0f, 750f, 1000f)),
            Item(ContentScale.Inside, Alignment.TopCenter, Rect(125f, 0f, 875f, 1000f)),
            Item(ContentScale.Inside, Alignment.TopEnd, Rect(250f, 0f, 1000f, 1000f)),
            Item(ContentScale.Inside, Alignment.CenterStart, Rect(0f, 0f, 750f, 1000f)),
            Item(ContentScale.Inside, Alignment.Center, Rect(125f, 0f, 875f, 1000f)),
            Item(ContentScale.Inside, Alignment.CenterEnd, Rect(250f, 0f, 1000f, 1000f)),
            Item(ContentScale.Inside, Alignment.BottomStart, Rect(0f, 0f, 750f, 1000f)),
            Item(ContentScale.Inside, Alignment.BottomCenter, Rect(125f, 0f, 875f, 1000f)),
            Item(ContentScale.Inside, Alignment.BottomEnd, Rect(250f, 0f, 1000f, 1000f)),
            Item(ContentScale.Fit, Alignment.TopStart, Rect(0f, 0f, 750f, 1000f)),
            Item(ContentScale.Fit, Alignment.TopCenter, Rect(125f, 0f, 875f, 1000f)),
            Item(ContentScale.Fit, Alignment.TopEnd, Rect(250f, 0f, 1000f, 1000f)),
            Item(ContentScale.Fit, Alignment.CenterStart, Rect(0f, 0f, 750f, 1000f)),
            Item(ContentScale.Fit, Alignment.Center, Rect(125f, 0f, 875f, 1000f)),
            Item(ContentScale.Fit, Alignment.CenterEnd, Rect(250f, 0f, 1000f, 1000f)),
            Item(ContentScale.Fit, Alignment.BottomStart, Rect(0f, 0f, 750f, 1000f)),
            Item(ContentScale.Fit, Alignment.BottomCenter, Rect(125f, 0f, 875f, 1000f)),
            Item(ContentScale.Fit, Alignment.BottomEnd, Rect(250f, 0f, 1000f, 1000f)),
            Item(ContentScale.FillWidth, Alignment.TopStart, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.FillWidth, Alignment.TopCenter, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.FillWidth, Alignment.TopEnd, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.FillWidth, Alignment.CenterStart, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.FillWidth, Alignment.Center, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.FillWidth, Alignment.CenterEnd, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.FillWidth, Alignment.BottomStart, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.FillWidth, Alignment.BottomCenter, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.FillWidth, Alignment.BottomEnd, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.FillHeight, Alignment.TopStart, Rect(0f, 0f, 750f, 1000f)),
            Item(ContentScale.FillHeight, Alignment.TopCenter, Rect(125f, 0f, 875f, 1000f)),
            Item(ContentScale.FillHeight, Alignment.TopEnd, Rect(250f, 0f, 1000f, 1000f)),
            Item(ContentScale.FillHeight, Alignment.CenterStart, Rect(0f, 0f, 750f, 1000f)),
            Item(ContentScale.FillHeight, Alignment.Center, Rect(125f, 0f, 875f, 1000f)),
            Item(ContentScale.FillHeight, Alignment.CenterEnd, Rect(250f, 0f, 1000f, 1000f)),
            Item(ContentScale.FillHeight, Alignment.BottomStart, Rect(0f, 0f, 750f, 1000f)),
            Item(ContentScale.FillHeight, Alignment.BottomCenter, Rect(125f, 0f, 875f, 1000f)),
            Item(ContentScale.FillHeight, Alignment.BottomEnd, Rect(250f, 0f, 1000f, 1000f)),
            Item(ContentScale.FillBounds, Alignment.TopStart, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.FillBounds, Alignment.TopCenter, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.FillBounds, Alignment.TopEnd, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.FillBounds, Alignment.CenterStart, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.FillBounds, Alignment.Center, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.FillBounds, Alignment.CenterEnd, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.FillBounds, Alignment.BottomStart, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.FillBounds, Alignment.BottomCenter, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.FillBounds, Alignment.BottomEnd, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.Crop, Alignment.TopStart, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.Crop, Alignment.TopCenter, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.Crop, Alignment.TopEnd, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.Crop, Alignment.CenterStart, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.Crop, Alignment.Center, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.Crop, Alignment.CenterEnd, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.Crop, Alignment.BottomStart, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.Crop, Alignment.BottomCenter, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.Crop, Alignment.BottomEnd, Rect(0f, 0f, 1000f, 1000f)),
//        ).printlnExpectedMessage(
//            computeExpected =  {
//                computeContentInContainerRect(
//                    containerSize = containerSize,
//                    contentSize = contentSize,
//                    contentScale = it.contentScale,
//                    contentAlignment = it.contentAlignment,
//                )
//            }
        ).forEach {
            Assert.assertEquals(
                it.getMessage(containerSize, contentSize),
                it.expected,
                computeContentInContainerRect(
                    containerSize = containerSize,
                    contentSize = contentSize,
                    contentScale = it.contentScale,
                    contentAlignment = it.contentAlignment,
                )
            )
        }
    }

    @Test
    fun testComputeContentInContainerVisibleRect() {
        val containerSize = Size(1000f, 1000f)

        var contentSize = Size(800f, 400f)
        listOf(
            Item(ContentScale.None, Alignment.TopStart, Rect(0f, 0f, 800f, 400f)),
            Item(ContentScale.None, Alignment.TopCenter, Rect(0f, 0f, 800f, 400f)),
            Item(ContentScale.None, Alignment.TopEnd, Rect(0f, 0f, 800f, 400f)),
            Item(ContentScale.None, Alignment.CenterStart, Rect(0f, 0f, 800f, 400f)),
            Item(ContentScale.None, Alignment.Center, Rect(0f, 0f, 800f, 400f)),
            Item(ContentScale.None, Alignment.CenterEnd, Rect(0f, 0f, 800f, 400f)),
            Item(ContentScale.None, Alignment.BottomStart, Rect(0f, 0f, 800f, 400f)),
            Item(ContentScale.None, Alignment.BottomCenter, Rect(0f, 0f, 800f, 400f)),
            Item(ContentScale.None, Alignment.BottomEnd, Rect(0f, 0f, 800f, 400f)),
            Item(ContentScale.Inside, Alignment.TopStart, Rect(0f, 0f, 800f, 400f)),
            Item(ContentScale.Inside, Alignment.TopCenter, Rect(0f, 0f, 800f, 400f)),
            Item(ContentScale.Inside, Alignment.TopEnd, Rect(0f, 0f, 800f, 400f)),
            Item(ContentScale.Inside, Alignment.CenterStart, Rect(0f, 0f, 800f, 400f)),
            Item(ContentScale.Inside, Alignment.Center, Rect(0f, 0f, 800f, 400f)),
            Item(ContentScale.Inside, Alignment.CenterEnd, Rect(0f, 0f, 800f, 400f)),
            Item(ContentScale.Inside, Alignment.BottomStart, Rect(0f, 0f, 800f, 400f)),
            Item(ContentScale.Inside, Alignment.BottomCenter, Rect(0f, 0f, 800f, 400f)),
            Item(ContentScale.Inside, Alignment.BottomEnd, Rect(0f, 0f, 800f, 400f)),
            Item(ContentScale.Fit, Alignment.TopStart, Rect(0f, 0f, 800f, 400f)),
            Item(ContentScale.Fit, Alignment.TopCenter, Rect(0f, 0f, 800f, 400f)),
            Item(ContentScale.Fit, Alignment.TopEnd, Rect(0f, 0f, 800f, 400f)),
            Item(ContentScale.Fit, Alignment.CenterStart, Rect(0f, 0f, 800f, 400f)),
            Item(ContentScale.Fit, Alignment.Center, Rect(0f, 0f, 800f, 400f)),
            Item(ContentScale.Fit, Alignment.CenterEnd, Rect(0f, 0f, 800f, 400f)),
            Item(ContentScale.Fit, Alignment.BottomStart, Rect(0f, 0f, 800f, 400f)),
            Item(ContentScale.Fit, Alignment.BottomCenter, Rect(0f, 0f, 800f, 400f)),
            Item(ContentScale.Fit, Alignment.BottomEnd, Rect(0f, 0f, 800f, 400f)),
            Item(ContentScale.FillWidth, Alignment.TopStart, Rect(0f, 0f, 800f, 400f)),
            Item(ContentScale.FillWidth, Alignment.TopCenter, Rect(0f, 0f, 800f, 400f)),
            Item(ContentScale.FillWidth, Alignment.TopEnd, Rect(0f, 0f, 800f, 400f)),
            Item(ContentScale.FillWidth, Alignment.CenterStart, Rect(0f, 0f, 800f, 400f)),
            Item(ContentScale.FillWidth, Alignment.Center, Rect(0f, 0f, 800f, 400f)),
            Item(ContentScale.FillWidth, Alignment.CenterEnd, Rect(0f, 0f, 800f, 400f)),
            Item(ContentScale.FillWidth, Alignment.BottomStart, Rect(0f, 0f, 800f, 400f)),
            Item(ContentScale.FillWidth, Alignment.BottomCenter, Rect(0f, 0f, 800f, 400f)),
            Item(ContentScale.FillWidth, Alignment.BottomEnd, Rect(0f, 0f, 800f, 400f)),
            Item(ContentScale.FillHeight, Alignment.TopStart, Rect(0f, 0f, 400f, 400f)),
            Item(ContentScale.FillHeight, Alignment.TopCenter, Rect(200f, 0f, 600f, 400f)),
            Item(ContentScale.FillHeight, Alignment.TopEnd, Rect(400f, 0f, 800f, 400f)),
            Item(ContentScale.FillHeight, Alignment.CenterStart, Rect(0f, 0f, 400f, 400f)),
            Item(ContentScale.FillHeight, Alignment.Center, Rect(200f, 0f, 600f, 400f)),
            Item(ContentScale.FillHeight, Alignment.CenterEnd, Rect(400f, 0f, 800f, 400f)),
            Item(ContentScale.FillHeight, Alignment.BottomStart, Rect(0f, 0f, 400f, 400f)),
            Item(ContentScale.FillHeight, Alignment.BottomCenter, Rect(200f, 0f, 600f, 400f)),
            Item(ContentScale.FillHeight, Alignment.BottomEnd, Rect(400f, 0f, 800f, 400f)),
            Item(ContentScale.FillBounds, Alignment.TopStart, Rect(0f, 0f, 800f, 400f)),
            Item(ContentScale.FillBounds, Alignment.TopCenter, Rect(0f, 0f, 800f, 400f)),
            Item(ContentScale.FillBounds, Alignment.TopEnd, Rect(0f, 0f, 800f, 400f)),
            Item(ContentScale.FillBounds, Alignment.CenterStart, Rect(0f, 0f, 800f, 400f)),
            Item(ContentScale.FillBounds, Alignment.Center, Rect(0f, 0f, 800f, 400f)),
            Item(ContentScale.FillBounds, Alignment.CenterEnd, Rect(0f, 0f, 800f, 400f)),
            Item(ContentScale.FillBounds, Alignment.BottomStart, Rect(0f, 0f, 800f, 400f)),
            Item(ContentScale.FillBounds, Alignment.BottomCenter, Rect(0f, 0f, 800f, 400f)),
            Item(ContentScale.FillBounds, Alignment.BottomEnd, Rect(0f, 0f, 800f, 400f)),
            Item(ContentScale.Crop, Alignment.TopStart, Rect(0f, 0f, 400f, 400f)),
            Item(ContentScale.Crop, Alignment.TopCenter, Rect(200f, 0f, 600f, 400f)),
            Item(ContentScale.Crop, Alignment.TopEnd, Rect(400f, 0f, 800f, 400f)),
            Item(ContentScale.Crop, Alignment.CenterStart, Rect(0f, 0f, 400f, 400f)),
            Item(ContentScale.Crop, Alignment.Center, Rect(200f, 0f, 600f, 400f)),
            Item(ContentScale.Crop, Alignment.CenterEnd, Rect(400f, 0f, 800f, 400f)),
            Item(ContentScale.Crop, Alignment.BottomStart, Rect(0f, 0f, 400f, 400f)),
            Item(ContentScale.Crop, Alignment.BottomCenter, Rect(200f, 0f, 600f, 400f)),
            Item(ContentScale.Crop, Alignment.BottomEnd, Rect(400f, 0f, 800f, 400f)),
//        ).printlnExpectedMessage(
//            computeExpected =  {
//                computeContentInContainerVisibleRect(
//                    containerSize = containerSize,
//                    contentSize = contentSize,
//                    contentScale = it.contentScale,
//                    contentAlignment = it.contentAlignment,
//                )
//            }
        ).forEach {
            Assert.assertEquals(
                it.getMessage(containerSize, contentSize),
                it.expected,
                computeContentInContainerVisibleRect(
                    containerSize = containerSize,
                    contentSize = contentSize,
                    contentScale = it.contentScale,
                    contentAlignment = it.contentAlignment,
                )
            )
        }

        contentSize = Size(400f, 800f)
        listOf(
            Item(ContentScale.None, Alignment.TopStart, Rect(0f, 0f, 400f, 800f)),
            Item(ContentScale.None, Alignment.TopCenter, Rect(0f, 0f, 400f, 800f)),
            Item(ContentScale.None, Alignment.TopEnd, Rect(0f, 0f, 400f, 800f)),
            Item(ContentScale.None, Alignment.CenterStart, Rect(0f, 0f, 400f, 800f)),
            Item(ContentScale.None, Alignment.Center, Rect(0f, 0f, 400f, 800f)),
            Item(ContentScale.None, Alignment.CenterEnd, Rect(0f, 0f, 400f, 800f)),
            Item(ContentScale.None, Alignment.BottomStart, Rect(0f, 0f, 400f, 800f)),
            Item(ContentScale.None, Alignment.BottomCenter, Rect(0f, 0f, 400f, 800f)),
            Item(ContentScale.None, Alignment.BottomEnd, Rect(0f, 0f, 400f, 800f)),
            Item(ContentScale.Inside, Alignment.TopStart, Rect(0f, 0f, 400f, 800f)),
            Item(ContentScale.Inside, Alignment.TopCenter, Rect(0f, 0f, 400f, 800f)),
            Item(ContentScale.Inside, Alignment.TopEnd, Rect(0f, 0f, 400f, 800f)),
            Item(ContentScale.Inside, Alignment.CenterStart, Rect(0f, 0f, 400f, 800f)),
            Item(ContentScale.Inside, Alignment.Center, Rect(0f, 0f, 400f, 800f)),
            Item(ContentScale.Inside, Alignment.CenterEnd, Rect(0f, 0f, 400f, 800f)),
            Item(ContentScale.Inside, Alignment.BottomStart, Rect(0f, 0f, 400f, 800f)),
            Item(ContentScale.Inside, Alignment.BottomCenter, Rect(0f, 0f, 400f, 800f)),
            Item(ContentScale.Inside, Alignment.BottomEnd, Rect(0f, 0f, 400f, 800f)),
            Item(ContentScale.Fit, Alignment.TopStart, Rect(0f, 0f, 400f, 800f)),
            Item(ContentScale.Fit, Alignment.TopCenter, Rect(0f, 0f, 400f, 800f)),
            Item(ContentScale.Fit, Alignment.TopEnd, Rect(0f, 0f, 400f, 800f)),
            Item(ContentScale.Fit, Alignment.CenterStart, Rect(0f, 0f, 400f, 800f)),
            Item(ContentScale.Fit, Alignment.Center, Rect(0f, 0f, 400f, 800f)),
            Item(ContentScale.Fit, Alignment.CenterEnd, Rect(0f, 0f, 400f, 800f)),
            Item(ContentScale.Fit, Alignment.BottomStart, Rect(0f, 0f, 400f, 800f)),
            Item(ContentScale.Fit, Alignment.BottomCenter, Rect(0f, 0f, 400f, 800f)),
            Item(ContentScale.Fit, Alignment.BottomEnd, Rect(0f, 0f, 400f, 800f)),
            Item(ContentScale.FillWidth, Alignment.TopStart, Rect(0f, 0f, 400f, 400f)),
            Item(ContentScale.FillWidth, Alignment.TopCenter, Rect(0f, 0f, 400f, 400f)),
            Item(ContentScale.FillWidth, Alignment.TopEnd, Rect(0f, 0f, 400f, 400f)),
            Item(ContentScale.FillWidth, Alignment.CenterStart, Rect(0f, 200f, 400f, 600f)),
            Item(ContentScale.FillWidth, Alignment.Center, Rect(0f, 200f, 400f, 600f)),
            Item(ContentScale.FillWidth, Alignment.CenterEnd, Rect(0f, 200f, 400f, 600f)),
            Item(ContentScale.FillWidth, Alignment.BottomStart, Rect(0f, 400f, 400f, 800f)),
            Item(ContentScale.FillWidth, Alignment.BottomCenter, Rect(0f, 400f, 400f, 800f)),
            Item(ContentScale.FillWidth, Alignment.BottomEnd, Rect(0f, 400f, 400f, 800f)),
            Item(ContentScale.FillHeight, Alignment.TopStart, Rect(0f, 0f, 400f, 800f)),
            Item(ContentScale.FillHeight, Alignment.TopCenter, Rect(0f, 0f, 400f, 800f)),
            Item(ContentScale.FillHeight, Alignment.TopEnd, Rect(0f, 0f, 400f, 800f)),
            Item(ContentScale.FillHeight, Alignment.CenterStart, Rect(0f, 0f, 400f, 800f)),
            Item(ContentScale.FillHeight, Alignment.Center, Rect(0f, 0f, 400f, 800f)),
            Item(ContentScale.FillHeight, Alignment.CenterEnd, Rect(0f, 0f, 400f, 800f)),
            Item(ContentScale.FillHeight, Alignment.BottomStart, Rect(0f, 0f, 400f, 800f)),
            Item(ContentScale.FillHeight, Alignment.BottomCenter, Rect(0f, 0f, 400f, 800f)),
            Item(ContentScale.FillHeight, Alignment.BottomEnd, Rect(0f, 0f, 400f, 800f)),
            Item(ContentScale.FillBounds, Alignment.TopStart, Rect(0f, 0f, 400f, 800f)),
            Item(ContentScale.FillBounds, Alignment.TopCenter, Rect(0f, 0f, 400f, 800f)),
            Item(ContentScale.FillBounds, Alignment.TopEnd, Rect(0f, 0f, 400f, 800f)),
            Item(ContentScale.FillBounds, Alignment.CenterStart, Rect(0f, 0f, 400f, 800f)),
            Item(ContentScale.FillBounds, Alignment.Center, Rect(0f, 0f, 400f, 800f)),
            Item(ContentScale.FillBounds, Alignment.CenterEnd, Rect(0f, 0f, 400f, 800f)),
            Item(ContentScale.FillBounds, Alignment.BottomStart, Rect(0f, 0f, 400f, 800f)),
            Item(ContentScale.FillBounds, Alignment.BottomCenter, Rect(0f, 0f, 400f, 800f)),
            Item(ContentScale.FillBounds, Alignment.BottomEnd, Rect(0f, 0f, 400f, 800f)),
            Item(ContentScale.Crop, Alignment.TopStart, Rect(0f, 0f, 400f, 400f)),
            Item(ContentScale.Crop, Alignment.TopCenter, Rect(0f, 0f, 400f, 400f)),
            Item(ContentScale.Crop, Alignment.TopEnd, Rect(0f, 0f, 400f, 400f)),
            Item(ContentScale.Crop, Alignment.CenterStart, Rect(0f, 200f, 400f, 600f)),
            Item(ContentScale.Crop, Alignment.Center, Rect(0f, 200f, 400f, 600f)),
            Item(ContentScale.Crop, Alignment.CenterEnd, Rect(0f, 200f, 400f, 600f)),
            Item(ContentScale.Crop, Alignment.BottomStart, Rect(0f, 400f, 400f, 800f)),
            Item(ContentScale.Crop, Alignment.BottomCenter, Rect(0f, 400f, 400f, 800f)),
            Item(ContentScale.Crop, Alignment.BottomEnd, Rect(0f, 400f, 400f, 800f)),
//        ).printlnExpectedMessage(
//            computeExpected =  {
//                computeContentInContainerVisibleRect(
//                    containerSize = containerSize,
//                    contentSize = contentSize,
//                    contentScale = it.contentScale,
//                    contentAlignment = it.contentAlignment,
//                )
//            }
        ).forEach {
            Assert.assertEquals(
                it.getMessage(containerSize, contentSize),
                it.expected,
                computeContentInContainerVisibleRect(
                    containerSize = containerSize,
                    contentSize = contentSize,
                    contentScale = it.contentScale,
                    contentAlignment = it.contentAlignment,
                )
            )
        }

        contentSize = Size(1600f, 1200f)
        listOf(
            Item(ContentScale.None, Alignment.TopStart, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.None, Alignment.TopCenter, Rect(300f, 0f, 1300f, 1000f)),
            Item(ContentScale.None, Alignment.TopEnd, Rect(600f, 0f, 1600f, 1000f)),
            Item(ContentScale.None, Alignment.CenterStart, Rect(0f, 100f, 1000f, 1100f)),
            Item(ContentScale.None, Alignment.Center, Rect(300f, 100f, 1300f, 1100f)),
            Item(ContentScale.None, Alignment.CenterEnd, Rect(600f, 100f, 1600f, 1100f)),
            Item(ContentScale.None, Alignment.BottomStart, Rect(0f, 200f, 1000f, 1200f)),
            Item(ContentScale.None, Alignment.BottomCenter, Rect(300f, 200f, 1300f, 1200f)),
            Item(ContentScale.None, Alignment.BottomEnd, Rect(600f, 200f, 1600f, 1200f)),
            Item(ContentScale.Inside, Alignment.TopStart, Rect(0f, 0f, 1600f, 1200f)),
            Item(ContentScale.Inside, Alignment.TopCenter, Rect(0f, 0f, 1600f, 1200f)),
            Item(ContentScale.Inside, Alignment.TopEnd, Rect(0f, 0f, 1600f, 1200f)),
            Item(ContentScale.Inside, Alignment.CenterStart, Rect(0f, 0f, 1600f, 1200f)),
            Item(ContentScale.Inside, Alignment.Center, Rect(0f, 0f, 1600f, 1200f)),
            Item(ContentScale.Inside, Alignment.CenterEnd, Rect(0f, 0f, 1600f, 1200f)),
            Item(ContentScale.Inside, Alignment.BottomStart, Rect(0f, 0f, 1600f, 1200f)),
            Item(ContentScale.Inside, Alignment.BottomCenter, Rect(0f, 0f, 1600f, 1200f)),
            Item(ContentScale.Inside, Alignment.BottomEnd, Rect(0f, 0f, 1600f, 1200f)),
            Item(ContentScale.Fit, Alignment.TopStart, Rect(0f, 0f, 1600f, 1200f)),
            Item(ContentScale.Fit, Alignment.TopCenter, Rect(0f, 0f, 1600f, 1200f)),
            Item(ContentScale.Fit, Alignment.TopEnd, Rect(0f, 0f, 1600f, 1200f)),
            Item(ContentScale.Fit, Alignment.CenterStart, Rect(0f, 0f, 1600f, 1200f)),
            Item(ContentScale.Fit, Alignment.Center, Rect(0f, 0f, 1600f, 1200f)),
            Item(ContentScale.Fit, Alignment.CenterEnd, Rect(0f, 0f, 1600f, 1200f)),
            Item(ContentScale.Fit, Alignment.BottomStart, Rect(0f, 0f, 1600f, 1200f)),
            Item(ContentScale.Fit, Alignment.BottomCenter, Rect(0f, 0f, 1600f, 1200f)),
            Item(ContentScale.Fit, Alignment.BottomEnd, Rect(0f, 0f, 1600f, 1200f)),
            Item(ContentScale.FillWidth, Alignment.TopStart, Rect(0f, 0f, 1600f, 1200f)),
            Item(ContentScale.FillWidth, Alignment.TopCenter, Rect(0f, 0f, 1600f, 1200f)),
            Item(ContentScale.FillWidth, Alignment.TopEnd, Rect(0f, 0f, 1600f, 1200f)),
            Item(ContentScale.FillWidth, Alignment.CenterStart, Rect(0f, 0f, 1600f, 1200f)),
            Item(ContentScale.FillWidth, Alignment.Center, Rect(0f, 0f, 1600f, 1200f)),
            Item(ContentScale.FillWidth, Alignment.CenterEnd, Rect(0f, 0f, 1600f, 1200f)),
            Item(ContentScale.FillWidth, Alignment.BottomStart, Rect(0f, 0f, 1600f, 1200f)),
            Item(ContentScale.FillWidth, Alignment.BottomCenter, Rect(0f, 0f, 1600f, 1200f)),
            Item(ContentScale.FillWidth, Alignment.BottomEnd, Rect(0f, 0f, 1600f, 1200f)),
            Item(ContentScale.FillHeight, Alignment.TopStart, Rect(0f, 0f, 1200f, 1200f)),
            Item(ContentScale.FillHeight, Alignment.TopCenter, Rect(199.99995f, 0f, 1400f, 1200f)),
            Item(ContentScale.FillHeight, Alignment.TopEnd, Rect(399.9999f, 0f, 1600f, 1200f)),
            Item(ContentScale.FillHeight, Alignment.CenterStart, Rect(0f, 0f, 1200f, 1200f)),
            Item(ContentScale.FillHeight, Alignment.Center, Rect(199.99995f, 0f, 1400f, 1200f)),
            Item(ContentScale.FillHeight, Alignment.CenterEnd, Rect(399.9999f, 0f, 1600f, 1200f)),
            Item(ContentScale.FillHeight, Alignment.BottomStart, Rect(0f, 0f, 1200f, 1200f)),
            Item(
                ContentScale.FillHeight,
                Alignment.BottomCenter,
                Rect(199.99995f, 0f, 1400f, 1200f)
            ),
            Item(ContentScale.FillHeight, Alignment.BottomEnd, Rect(399.9999f, 0f, 1600f, 1200f)),
            Item(ContentScale.FillBounds, Alignment.TopStart, Rect(0f, 0f, 1600f, 1200f)),
            Item(ContentScale.FillBounds, Alignment.TopCenter, Rect(0f, 0f, 1600f, 1200f)),
            Item(ContentScale.FillBounds, Alignment.TopEnd, Rect(0f, 0f, 1600f, 1200f)),
            Item(ContentScale.FillBounds, Alignment.CenterStart, Rect(0f, 0f, 1600f, 1200f)),
            Item(ContentScale.FillBounds, Alignment.Center, Rect(0f, 0f, 1600f, 1200f)),
            Item(ContentScale.FillBounds, Alignment.CenterEnd, Rect(0f, 0f, 1600f, 1200f)),
            Item(ContentScale.FillBounds, Alignment.BottomStart, Rect(0f, 0f, 1600f, 1200f)),
            Item(ContentScale.FillBounds, Alignment.BottomCenter, Rect(0f, 0f, 1600f, 1200f)),
            Item(ContentScale.FillBounds, Alignment.BottomEnd, Rect(0f, 0f, 1600f, 1200f)),
            Item(ContentScale.Crop, Alignment.TopStart, Rect(0f, 0f, 1200f, 1200f)),
            Item(ContentScale.Crop, Alignment.TopCenter, Rect(199.99995f, 0f, 1400f, 1200f)),
            Item(ContentScale.Crop, Alignment.TopEnd, Rect(399.9999f, 0f, 1600f, 1200f)),
            Item(ContentScale.Crop, Alignment.CenterStart, Rect(0f, 0f, 1200f, 1200f)),
            Item(ContentScale.Crop, Alignment.Center, Rect(199.99995f, 0f, 1400f, 1200f)),
            Item(ContentScale.Crop, Alignment.CenterEnd, Rect(399.9999f, 0f, 1600f, 1200f)),
            Item(ContentScale.Crop, Alignment.BottomStart, Rect(0f, 0f, 1200f, 1200f)),
            Item(ContentScale.Crop, Alignment.BottomCenter, Rect(199.99995f, 0f, 1400f, 1200f)),
            Item(ContentScale.Crop, Alignment.BottomEnd, Rect(399.9999f, 0f, 1600f, 1200f)),
//        ).printlnExpectedMessage(
//            computeExpected =  {
//                computeContentInContainerVisibleRect(
//                    containerSize = containerSize,
//                    contentSize = contentSize,
//                    contentScale = it.contentScale,
//                    contentAlignment = it.contentAlignment,
//                )
//            }
        ).forEach {
            Assert.assertEquals(
                it.getMessage(containerSize, contentSize),
                it.expected,
                computeContentInContainerVisibleRect(
                    containerSize = containerSize,
                    contentSize = contentSize,
                    contentScale = it.contentScale,
                    contentAlignment = it.contentAlignment,
                )
            )
        }

        contentSize = Size(1200f, 1600f)
        listOf(
            Item(ContentScale.None, Alignment.TopStart, Rect(0f, 0f, 1000f, 1000f)),
            Item(ContentScale.None, Alignment.TopCenter, Rect(100f, 0f, 1100f, 1000f)),
            Item(ContentScale.None, Alignment.TopEnd, Rect(200f, 0f, 1200f, 1000f)),
            Item(ContentScale.None, Alignment.CenterStart, Rect(0f, 300f, 1000f, 1300f)),
            Item(ContentScale.None, Alignment.Center, Rect(100f, 300f, 1100f, 1300f)),
            Item(ContentScale.None, Alignment.CenterEnd, Rect(200f, 300f, 1200f, 1300f)),
            Item(ContentScale.None, Alignment.BottomStart, Rect(0f, 600f, 1000f, 1600f)),
            Item(ContentScale.None, Alignment.BottomCenter, Rect(100f, 600f, 1100f, 1600f)),
            Item(ContentScale.None, Alignment.BottomEnd, Rect(200f, 600f, 1200f, 1600f)),
            Item(ContentScale.Inside, Alignment.TopStart, Rect(0f, 0f, 1200f, 1600f)),
            Item(ContentScale.Inside, Alignment.TopCenter, Rect(0f, 0f, 1200f, 1600f)),
            Item(ContentScale.Inside, Alignment.TopEnd, Rect(0f, 0f, 1200f, 1600f)),
            Item(ContentScale.Inside, Alignment.CenterStart, Rect(0f, 0f, 1200f, 1600f)),
            Item(ContentScale.Inside, Alignment.Center, Rect(0f, 0f, 1200f, 1600f)),
            Item(ContentScale.Inside, Alignment.CenterEnd, Rect(0f, 0f, 1200f, 1600f)),
            Item(ContentScale.Inside, Alignment.BottomStart, Rect(0f, 0f, 1200f, 1600f)),
            Item(ContentScale.Inside, Alignment.BottomCenter, Rect(0f, 0f, 1200f, 1600f)),
            Item(ContentScale.Inside, Alignment.BottomEnd, Rect(0f, 0f, 1200f, 1600f)),
            Item(ContentScale.Fit, Alignment.TopStart, Rect(0f, 0f, 1200f, 1600f)),
            Item(ContentScale.Fit, Alignment.TopCenter, Rect(0f, 0f, 1200f, 1600f)),
            Item(ContentScale.Fit, Alignment.TopEnd, Rect(0f, 0f, 1200f, 1600f)),
            Item(ContentScale.Fit, Alignment.CenterStart, Rect(0f, 0f, 1200f, 1600f)),
            Item(ContentScale.Fit, Alignment.Center, Rect(0f, 0f, 1200f, 1600f)),
            Item(ContentScale.Fit, Alignment.CenterEnd, Rect(0f, 0f, 1200f, 1600f)),
            Item(ContentScale.Fit, Alignment.BottomStart, Rect(0f, 0f, 1200f, 1600f)),
            Item(ContentScale.Fit, Alignment.BottomCenter, Rect(0f, 0f, 1200f, 1600f)),
            Item(ContentScale.Fit, Alignment.BottomEnd, Rect(0f, 0f, 1200f, 1600f)),
            Item(ContentScale.FillWidth, Alignment.TopStart, Rect(0f, 0f, 1200f, 1200f)),
            Item(ContentScale.FillWidth, Alignment.TopCenter, Rect(0f, 0f, 1200f, 1200f)),
            Item(ContentScale.FillWidth, Alignment.TopEnd, Rect(0f, 0f, 1200f, 1200f)),
            Item(ContentScale.FillWidth, Alignment.CenterStart, Rect(0f, 199.99995f, 1200f, 1400f)),
            Item(ContentScale.FillWidth, Alignment.Center, Rect(0f, 199.99995f, 1200f, 1400f)),
            Item(ContentScale.FillWidth, Alignment.CenterEnd, Rect(0f, 199.99995f, 1200f, 1400f)),
            Item(ContentScale.FillWidth, Alignment.BottomStart, Rect(0f, 399.9999f, 1200f, 1600f)),
            Item(ContentScale.FillWidth, Alignment.BottomCenter, Rect(0f, 399.9999f, 1200f, 1600f)),
            Item(ContentScale.FillWidth, Alignment.BottomEnd, Rect(0f, 399.9999f, 1200f, 1600f)),
            Item(ContentScale.FillHeight, Alignment.TopStart, Rect(0f, 0f, 1200f, 1600f)),
            Item(ContentScale.FillHeight, Alignment.TopCenter, Rect(0f, 0f, 1200f, 1600f)),
            Item(ContentScale.FillHeight, Alignment.TopEnd, Rect(0f, 0f, 1200f, 1600f)),
            Item(ContentScale.FillHeight, Alignment.CenterStart, Rect(0f, 0f, 1200f, 1600f)),
            Item(ContentScale.FillHeight, Alignment.Center, Rect(0f, 0f, 1200f, 1600f)),
            Item(ContentScale.FillHeight, Alignment.CenterEnd, Rect(0f, 0f, 1200f, 1600f)),
            Item(ContentScale.FillHeight, Alignment.BottomStart, Rect(0f, 0f, 1200f, 1600f)),
            Item(ContentScale.FillHeight, Alignment.BottomCenter, Rect(0f, 0f, 1200f, 1600f)),
            Item(ContentScale.FillHeight, Alignment.BottomEnd, Rect(0f, 0f, 1200f, 1600f)),
            Item(ContentScale.FillBounds, Alignment.TopStart, Rect(0f, 0f, 1200f, 1600f)),
            Item(ContentScale.FillBounds, Alignment.TopCenter, Rect(0f, 0f, 1200f, 1600f)),
            Item(ContentScale.FillBounds, Alignment.TopEnd, Rect(0f, 0f, 1200f, 1600f)),
            Item(ContentScale.FillBounds, Alignment.CenterStart, Rect(0f, 0f, 1200f, 1600f)),
            Item(ContentScale.FillBounds, Alignment.Center, Rect(0f, 0f, 1200f, 1600f)),
            Item(ContentScale.FillBounds, Alignment.CenterEnd, Rect(0f, 0f, 1200f, 1600f)),
            Item(ContentScale.FillBounds, Alignment.BottomStart, Rect(0f, 0f, 1200f, 1600f)),
            Item(ContentScale.FillBounds, Alignment.BottomCenter, Rect(0f, 0f, 1200f, 1600f)),
            Item(ContentScale.FillBounds, Alignment.BottomEnd, Rect(0f, 0f, 1200f, 1600f)),
            Item(ContentScale.Crop, Alignment.TopStart, Rect(0f, 0f, 1200f, 1200f)),
            Item(ContentScale.Crop, Alignment.TopCenter, Rect(0f, 0f, 1200f, 1200f)),
            Item(ContentScale.Crop, Alignment.TopEnd, Rect(0f, 0f, 1200f, 1200f)),
            Item(ContentScale.Crop, Alignment.CenterStart, Rect(0f, 199.99995f, 1200f, 1400f)),
            Item(ContentScale.Crop, Alignment.Center, Rect(0f, 199.99995f, 1200f, 1400f)),
            Item(ContentScale.Crop, Alignment.CenterEnd, Rect(0f, 199.99995f, 1200f, 1400f)),
            Item(ContentScale.Crop, Alignment.BottomStart, Rect(0f, 399.9999f, 1200f, 1600f)),
            Item(ContentScale.Crop, Alignment.BottomCenter, Rect(0f, 399.9999f, 1200f, 1600f)),
            Item(ContentScale.Crop, Alignment.BottomEnd, Rect(0f, 399.9999f, 1200f, 1600f)),
//        ).printlnExpectedMessage(
//            computeExpected =  {
//                computeContentInContainerVisibleRect(
//                    containerSize = containerSize,
//                    contentSize = contentSize,
//                    contentScale = it.contentScale,
//                    contentAlignment = it.contentAlignment,
//                )
//            }
        ).forEach {
            Assert.assertEquals(
                it.getMessage(containerSize, contentSize),
                it.expected,
                computeContentInContainerVisibleRect(
                    containerSize = containerSize,
                    contentSize = contentSize,
                    contentScale = it.contentScale,
                    contentAlignment = it.contentAlignment,
                )
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
        val containerSize = Size(1000f, 1000f)

        var contentSize = Size(800f, 400f)
        var scale = 1f
        listOf(
            Item(ContentScale.None, Alignment.TopStart, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.None, Alignment.TopCenter, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.None, Alignment.TopEnd, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.None, Alignment.CenterStart, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.None, Alignment.Center, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.None, Alignment.CenterEnd, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.None, Alignment.BottomStart, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.None, Alignment.BottomCenter, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.None, Alignment.BottomEnd, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.Inside, Alignment.TopStart, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.Inside, Alignment.TopCenter, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.Inside, Alignment.TopEnd, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.Inside, Alignment.CenterStart, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.Inside, Alignment.Center, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.Inside, Alignment.CenterEnd, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.Inside, Alignment.BottomStart, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.Inside, Alignment.BottomCenter, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.Inside, Alignment.BottomEnd, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.Fit, Alignment.TopStart, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.Fit, Alignment.TopCenter, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.Fit, Alignment.TopEnd, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.Fit, Alignment.CenterStart, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.Fit, Alignment.Center, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.Fit, Alignment.CenterEnd, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.Fit, Alignment.BottomStart, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.Fit, Alignment.BottomCenter, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.Fit, Alignment.BottomEnd, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.FillWidth, Alignment.TopStart, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.FillWidth, Alignment.TopCenter, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.FillWidth, Alignment.TopEnd, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.FillWidth, Alignment.CenterStart, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.FillWidth, Alignment.Center, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.FillWidth, Alignment.CenterEnd, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.FillWidth, Alignment.BottomStart, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.FillWidth, Alignment.BottomCenter, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.FillWidth, Alignment.BottomEnd, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.FillHeight, Alignment.TopStart, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.FillHeight, Alignment.TopCenter, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.FillHeight, Alignment.TopEnd, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.FillHeight, Alignment.CenterStart, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.FillHeight, Alignment.Center, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.FillHeight, Alignment.CenterEnd, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.FillHeight, Alignment.BottomStart, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.FillHeight, Alignment.BottomCenter, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.FillHeight, Alignment.BottomEnd, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.FillBounds, Alignment.TopStart, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.FillBounds, Alignment.TopCenter, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.FillBounds, Alignment.TopEnd, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.FillBounds, Alignment.CenterStart, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.FillBounds, Alignment.Center, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.FillBounds, Alignment.CenterEnd, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.FillBounds, Alignment.BottomStart, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.FillBounds, Alignment.BottomCenter, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.FillBounds, Alignment.BottomEnd, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.Crop, Alignment.TopStart, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.Crop, Alignment.TopCenter, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.Crop, Alignment.TopEnd, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.Crop, Alignment.CenterStart, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.Crop, Alignment.Center, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.Crop, Alignment.CenterEnd, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.Crop, Alignment.BottomStart, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.Crop, Alignment.BottomCenter, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.Crop, Alignment.BottomEnd, Rect(0.0f,0.0f,0.0f,0.0f)),
//        ).printlnExpectedMessage(
//            computeExpected =  {
//                computeTranslationBounds(
//                    containerSize = containerSize,
//                    contentSize = contentSize,
//                    contentScale = it.contentScale,
//                    contentAlignment = it.contentAlignment,
//                    scale = scale,
//                )
//            }
        ).forEach {
            Assert.assertEquals(
                it.getMessage(containerSize, contentSize, scale),
                it.expected,
                computeTranslationBounds(
                    containerSize = containerSize,
                    contentSize = contentSize,
                    contentScale = it.contentScale,
                    contentAlignment = it.contentAlignment,
                    scale = scale,
                )
            )
        }

        contentSize = Size(400f, 800f)
        scale = 1f
        listOf(
            Item(ContentScale.None, Alignment.TopStart, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.None, Alignment.TopCenter, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.None, Alignment.TopEnd, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.None, Alignment.CenterStart, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.None, Alignment.Center, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.None, Alignment.CenterEnd, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.None, Alignment.BottomStart, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.None, Alignment.BottomCenter, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.None, Alignment.BottomEnd, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.Inside, Alignment.TopStart, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.Inside, Alignment.TopCenter, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.Inside, Alignment.TopEnd, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.Inside, Alignment.CenterStart, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.Inside, Alignment.Center, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.Inside, Alignment.CenterEnd, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.Inside, Alignment.BottomStart, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.Inside, Alignment.BottomCenter, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.Inside, Alignment.BottomEnd, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.Fit, Alignment.TopStart, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.Fit, Alignment.TopCenter, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.Fit, Alignment.TopEnd, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.Fit, Alignment.CenterStart, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.Fit, Alignment.Center, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.Fit, Alignment.CenterEnd, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.Fit, Alignment.BottomStart, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.Fit, Alignment.BottomCenter, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.Fit, Alignment.BottomEnd, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.FillWidth, Alignment.TopStart, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.FillWidth, Alignment.TopCenter, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.FillWidth, Alignment.TopEnd, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.FillWidth, Alignment.CenterStart, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.FillWidth, Alignment.Center, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.FillWidth, Alignment.CenterEnd, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.FillWidth, Alignment.BottomStart, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.FillWidth, Alignment.BottomCenter, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.FillWidth, Alignment.BottomEnd, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.FillHeight, Alignment.TopStart, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.FillHeight, Alignment.TopCenter, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.FillHeight, Alignment.TopEnd, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.FillHeight, Alignment.CenterStart, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.FillHeight, Alignment.Center, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.FillHeight, Alignment.CenterEnd, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.FillHeight, Alignment.BottomStart, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.FillHeight, Alignment.BottomCenter, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.FillHeight, Alignment.BottomEnd, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.FillBounds, Alignment.TopStart, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.FillBounds, Alignment.TopCenter, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.FillBounds, Alignment.TopEnd, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.FillBounds, Alignment.CenterStart, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.FillBounds, Alignment.Center, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.FillBounds, Alignment.CenterEnd, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.FillBounds, Alignment.BottomStart, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.FillBounds, Alignment.BottomCenter, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.FillBounds, Alignment.BottomEnd, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.Crop, Alignment.TopStart, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.Crop, Alignment.TopCenter, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.Crop, Alignment.TopEnd, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.Crop, Alignment.CenterStart, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.Crop, Alignment.Center, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.Crop, Alignment.CenterEnd, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.Crop, Alignment.BottomStart, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.Crop, Alignment.BottomCenter, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.Crop, Alignment.BottomEnd, Rect(0.0f,0.0f,0.0f,0.0f)),
//        ).printlnExpectedMessage(
//            computeExpected =  {
//                computeTranslationBounds(
//                    containerSize = containerSize,
//                    contentSize = contentSize,
//                    contentScale = it.contentScale,
//                    contentAlignment = it.contentAlignment,
//                    scale = scale,
//                )
//            }
        ).forEach {
            Assert.assertEquals(
                it.getMessage(containerSize, contentSize, scale),
                it.expected,
                computeTranslationBounds(
                    containerSize = containerSize,
                    contentSize = contentSize,
                    contentScale = it.contentScale,
                    contentAlignment = it.contentAlignment,
                    scale = scale,
                )
            )
        }

        contentSize = Size(1600f, 1200f)
        scale = 1f
        listOf(
            Item(ContentScale.None, Alignment.TopStart, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.None, Alignment.TopCenter, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.None, Alignment.TopEnd, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.None, Alignment.CenterStart, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.None, Alignment.Center, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.None, Alignment.CenterEnd, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.None, Alignment.BottomStart, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.None, Alignment.BottomCenter, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.None, Alignment.BottomEnd, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.Inside, Alignment.TopStart, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.Inside, Alignment.TopCenter, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.Inside, Alignment.TopEnd, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.Inside, Alignment.CenterStart, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.Inside, Alignment.Center, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.Inside, Alignment.CenterEnd, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.Inside, Alignment.BottomStart, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.Inside, Alignment.BottomCenter, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.Inside, Alignment.BottomEnd, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.Fit, Alignment.TopStart, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.Fit, Alignment.TopCenter, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.Fit, Alignment.TopEnd, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.Fit, Alignment.CenterStart, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.Fit, Alignment.Center, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.Fit, Alignment.CenterEnd, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.Fit, Alignment.BottomStart, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.Fit, Alignment.BottomCenter, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.Fit, Alignment.BottomEnd, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.FillWidth, Alignment.TopStart, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.FillWidth, Alignment.TopCenter, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.FillWidth, Alignment.TopEnd, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.FillWidth, Alignment.CenterStart, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.FillWidth, Alignment.Center, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.FillWidth, Alignment.CenterEnd, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.FillWidth, Alignment.BottomStart, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.FillWidth, Alignment.BottomCenter, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.FillWidth, Alignment.BottomEnd, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.FillHeight, Alignment.TopStart, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.FillHeight, Alignment.TopCenter, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.FillHeight, Alignment.TopEnd, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.FillHeight, Alignment.CenterStart, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.FillHeight, Alignment.Center, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.FillHeight, Alignment.CenterEnd, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.FillHeight, Alignment.BottomStart, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.FillHeight, Alignment.BottomCenter, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.FillHeight, Alignment.BottomEnd, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.FillBounds, Alignment.TopStart, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.FillBounds, Alignment.TopCenter, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.FillBounds, Alignment.TopEnd, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.FillBounds, Alignment.CenterStart, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.FillBounds, Alignment.Center, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.FillBounds, Alignment.CenterEnd, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.FillBounds, Alignment.BottomStart, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.FillBounds, Alignment.BottomCenter, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.FillBounds, Alignment.BottomEnd, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.Crop, Alignment.TopStart, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.Crop, Alignment.TopCenter, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.Crop, Alignment.TopEnd, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.Crop, Alignment.CenterStart, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.Crop, Alignment.Center, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.Crop, Alignment.CenterEnd, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.Crop, Alignment.BottomStart, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.Crop, Alignment.BottomCenter, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.Crop, Alignment.BottomEnd, Rect(0.0f,0.0f,0.0f,0.0f)),
//        ).printlnExpectedMessage(
//            computeExpected =  {
//                computeTranslationBounds(
//                    containerSize = containerSize,
//                    contentSize = contentSize,
//                    contentScale = it.contentScale,
//                    contentAlignment = it.contentAlignment,
//                    scale = scale,
//                )
//            }
        ).forEach {
            Assert.assertEquals(
                it.getMessage(containerSize, contentSize, scale),
                it.expected,
                computeTranslationBounds(
                    containerSize = containerSize,
                    contentSize = contentSize,
                    contentScale = it.contentScale,
                    contentAlignment = it.contentAlignment,
                    scale = scale,
                )
            )
        }

        contentSize = Size(1200f, 1600f)
        scale = 1f
        listOf(
            Item(ContentScale.None, Alignment.TopStart, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.None, Alignment.TopCenter, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.None, Alignment.TopEnd, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.None, Alignment.CenterStart, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.None, Alignment.Center, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.None, Alignment.CenterEnd, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.None, Alignment.BottomStart, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.None, Alignment.BottomCenter, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.None, Alignment.BottomEnd, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.Inside, Alignment.TopStart, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.Inside, Alignment.TopCenter, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.Inside, Alignment.TopEnd, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.Inside, Alignment.CenterStart, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.Inside, Alignment.Center, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.Inside, Alignment.CenterEnd, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.Inside, Alignment.BottomStart, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.Inside, Alignment.BottomCenter, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.Inside, Alignment.BottomEnd, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.Fit, Alignment.TopStart, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.Fit, Alignment.TopCenter, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.Fit, Alignment.TopEnd, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.Fit, Alignment.CenterStart, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.Fit, Alignment.Center, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.Fit, Alignment.CenterEnd, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.Fit, Alignment.BottomStart, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.Fit, Alignment.BottomCenter, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.Fit, Alignment.BottomEnd, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.FillWidth, Alignment.TopStart, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.FillWidth, Alignment.TopCenter, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.FillWidth, Alignment.TopEnd, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.FillWidth, Alignment.CenterStart, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.FillWidth, Alignment.Center, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.FillWidth, Alignment.CenterEnd, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.FillWidth, Alignment.BottomStart, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.FillWidth, Alignment.BottomCenter, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.FillWidth, Alignment.BottomEnd, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.FillHeight, Alignment.TopStart, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.FillHeight, Alignment.TopCenter, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.FillHeight, Alignment.TopEnd, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.FillHeight, Alignment.CenterStart, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.FillHeight, Alignment.Center, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.FillHeight, Alignment.CenterEnd, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.FillHeight, Alignment.BottomStart, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.FillHeight, Alignment.BottomCenter, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.FillHeight, Alignment.BottomEnd, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.FillBounds, Alignment.TopStart, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.FillBounds, Alignment.TopCenter, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.FillBounds, Alignment.TopEnd, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.FillBounds, Alignment.CenterStart, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.FillBounds, Alignment.Center, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.FillBounds, Alignment.CenterEnd, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.FillBounds, Alignment.BottomStart, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.FillBounds, Alignment.BottomCenter, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.FillBounds, Alignment.BottomEnd, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.Crop, Alignment.TopStart, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.Crop, Alignment.TopCenter, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.Crop, Alignment.TopEnd, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.Crop, Alignment.CenterStart, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.Crop, Alignment.Center, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.Crop, Alignment.CenterEnd, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.Crop, Alignment.BottomStart, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.Crop, Alignment.BottomCenter, Rect(0.0f,0.0f,0.0f,0.0f)),
            Item(ContentScale.Crop, Alignment.BottomEnd, Rect(0.0f,0.0f,0.0f,0.0f)),
//        ).printlnExpectedMessage(
//            computeExpected =  {
//                computeTranslationBounds(
//                    containerSize = containerSize,
//                    contentSize = contentSize,
//                    contentScale = it.contentScale,
//                    contentAlignment = it.contentAlignment,
//                    scale = scale,
//                )
//            }
        ).forEach {
            Assert.assertEquals(
                it.getMessage(containerSize, contentSize, scale),
                it.expected,
                computeTranslationBounds(
                    containerSize = containerSize,
                    contentSize = contentSize,
                    contentScale = it.contentScale,
                    contentAlignment = it.contentAlignment,
                    scale = scale,
                )
            )
        }

        contentSize = Size(800f, 400f)
        scale = 2f
        listOf(
            Item(ContentScale.None, Alignment.TopStart, Rect(-600.0f,0.0f,-0.0f,0.0f)),
            Item(ContentScale.None, Alignment.TopCenter, Rect(-800.0f,0.0f,-200.0f,0.0f)),
            Item(ContentScale.None, Alignment.TopEnd, Rect(-1000.0f,0.0f,-400.0f,0.0f)),
            Item(ContentScale.None, Alignment.CenterStart, Rect(-600.0f,-500.0f,-0.0f,-500.0f)),
            Item(ContentScale.None, Alignment.Center, Rect(-800.0f,-500.0f,-200.0f,-500.0f)),
            Item(ContentScale.None, Alignment.CenterEnd, Rect(-1000.0f,-500.0f,-400.0f,-500.0f)),
            Item(ContentScale.None, Alignment.BottomStart, Rect(-600.0f,-1000.0f,-0.0f,-1000.0f)),
            Item(ContentScale.None, Alignment.BottomCenter, Rect(-800.0f,-1000.0f,-200.0f,-1000.0f)),
            Item(ContentScale.None, Alignment.BottomEnd, Rect(-1000.0f,-1000.0f,-400.0f,-1000.0f)),
            Item(ContentScale.Inside, Alignment.TopStart, Rect(-600.0f,0.0f,-0.0f,0.0f)),
            Item(ContentScale.Inside, Alignment.TopCenter, Rect(-800.0f,0.0f,-200.0f,0.0f)),
            Item(ContentScale.Inside, Alignment.TopEnd, Rect(-1000.0f,0.0f,-400.0f,0.0f)),
            Item(ContentScale.Inside, Alignment.CenterStart, Rect(-600.0f,-500.0f,-0.0f,-500.0f)),
            Item(ContentScale.Inside, Alignment.Center, Rect(-800.0f,-500.0f,-200.0f,-500.0f)),
            Item(ContentScale.Inside, Alignment.CenterEnd, Rect(-1000.0f,-500.0f,-400.0f,-500.0f)),
            Item(ContentScale.Inside, Alignment.BottomStart, Rect(-600.0f,-1000.0f,-0.0f,-1000.0f)),
            Item(ContentScale.Inside, Alignment.BottomCenter, Rect(-800.0f,-1000.0f,-200.0f,-1000.0f)),
            Item(ContentScale.Inside, Alignment.BottomEnd, Rect(-1000.0f,-1000.0f,-400.0f,-1000.0f)),
            Item(ContentScale.Fit, Alignment.TopStart, Rect(-1000.0f,0.0f,-0.0f,0.0f)),
            Item(ContentScale.Fit, Alignment.TopCenter, Rect(-1000.0f,0.0f,-0.0f,0.0f)),
            Item(ContentScale.Fit, Alignment.TopEnd, Rect(-1000.0f,0.0f,-0.0f,0.0f)),
            Item(ContentScale.Fit, Alignment.CenterStart, Rect(-1000.0f,-500.0f,-0.0f,-500.0f)),
            Item(ContentScale.Fit, Alignment.Center, Rect(-1000.0f,-500.0f,-0.0f,-500.0f)),
            Item(ContentScale.Fit, Alignment.CenterEnd, Rect(-1000.0f,-500.0f,-0.0f,-500.0f)),
            Item(ContentScale.Fit, Alignment.BottomStart, Rect(-1000.0f,-1000.0f,-0.0f,-1000.0f)),
            Item(ContentScale.Fit, Alignment.BottomCenter, Rect(-1000.0f,-1000.0f,-0.0f,-1000.0f)),
            Item(ContentScale.Fit, Alignment.BottomEnd, Rect(-1000.0f,-1000.0f,-0.0f,-1000.0f)),
            Item(ContentScale.FillWidth, Alignment.TopStart, Rect(-1000.0f,0.0f,-0.0f,0.0f)),
            Item(ContentScale.FillWidth, Alignment.TopCenter, Rect(-1000.0f,0.0f,-0.0f,0.0f)),
            Item(ContentScale.FillWidth, Alignment.TopEnd, Rect(-1000.0f,0.0f,-0.0f,0.0f)),
            Item(ContentScale.FillWidth, Alignment.CenterStart, Rect(-1000.0f,-500.0f,-0.0f,-500.0f)),
            Item(ContentScale.FillWidth, Alignment.Center, Rect(-1000.0f,-500.0f,-0.0f,-500.0f)),
            Item(ContentScale.FillWidth, Alignment.CenterEnd, Rect(-1000.0f,-500.0f,-0.0f,-500.0f)),
            Item(ContentScale.FillWidth, Alignment.BottomStart, Rect(-1000.0f,-1000.0f,-0.0f,-1000.0f)),
            Item(ContentScale.FillWidth, Alignment.BottomCenter, Rect(-1000.0f,-1000.0f,-0.0f,-1000.0f)),
            Item(ContentScale.FillWidth, Alignment.BottomEnd, Rect(-1000.0f,-1000.0f,-0.0f,-1000.0f)),
            Item(ContentScale.FillHeight, Alignment.TopStart, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.FillHeight, Alignment.TopCenter, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.FillHeight, Alignment.TopEnd, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.FillHeight, Alignment.CenterStart, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.FillHeight, Alignment.Center, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.FillHeight, Alignment.CenterEnd, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.FillHeight, Alignment.BottomStart, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.FillHeight, Alignment.BottomCenter, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.FillHeight, Alignment.BottomEnd, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.FillBounds, Alignment.TopStart, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.FillBounds, Alignment.TopCenter, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.FillBounds, Alignment.TopEnd, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.FillBounds, Alignment.CenterStart, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.FillBounds, Alignment.Center, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.FillBounds, Alignment.CenterEnd, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.FillBounds, Alignment.BottomStart, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.FillBounds, Alignment.BottomCenter, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.FillBounds, Alignment.BottomEnd, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.Crop, Alignment.TopStart, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.Crop, Alignment.TopCenter, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.Crop, Alignment.TopEnd, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.Crop, Alignment.CenterStart, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.Crop, Alignment.Center, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.Crop, Alignment.CenterEnd, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.Crop, Alignment.BottomStart, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.Crop, Alignment.BottomCenter, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.Crop, Alignment.BottomEnd, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
//        ).printlnExpectedMessage(
//            computeExpected =  {
//                computeTranslationBounds(
//                    containerSize = containerSize,
//                    contentSize = contentSize,
//                    contentScale = it.contentScale,
//                    contentAlignment = it.contentAlignment,
//                    scale = scale,
//                )
//            }
        ).forEach {
            Assert.assertEquals(
                it.getMessage(containerSize, contentSize, scale),
                it.expected,
                computeTranslationBounds(
                    containerSize = containerSize,
                    contentSize = contentSize,
                    contentScale = it.contentScale,
                    contentAlignment = it.contentAlignment,
                    scale = scale,
                )
            )
        }

        contentSize = Size(400f, 800f)
        scale = 2f
        listOf(
            Item(ContentScale.None, Alignment.TopStart, Rect(0.0f,-600.0f,0.0f,-0.0f)),
            Item(ContentScale.None, Alignment.TopCenter, Rect(-500.0f,-600.0f,-500.0f,-0.0f)),
            Item(ContentScale.None, Alignment.TopEnd, Rect(-1000.0f,-600.0f,-1000.0f,-0.0f)),
            Item(ContentScale.None, Alignment.CenterStart, Rect(0.0f,-800.0f,0.0f,-200.0f)),
            Item(ContentScale.None, Alignment.Center, Rect(-500.0f,-800.0f,-500.0f,-200.0f)),
            Item(ContentScale.None, Alignment.CenterEnd, Rect(-1000.0f,-800.0f,-1000.0f,-200.0f)),
            Item(ContentScale.None, Alignment.BottomStart, Rect(0.0f,-1000.0f,0.0f,-400.0f)),
            Item(ContentScale.None, Alignment.BottomCenter, Rect(-500.0f,-1000.0f,-500.0f,-400.0f)),
            Item(ContentScale.None, Alignment.BottomEnd, Rect(-1000.0f,-1000.0f,-1000.0f,-400.0f)),
            Item(ContentScale.Inside, Alignment.TopStart, Rect(0.0f,-600.0f,0.0f,-0.0f)),
            Item(ContentScale.Inside, Alignment.TopCenter, Rect(-500.0f,-600.0f,-500.0f,-0.0f)),
            Item(ContentScale.Inside, Alignment.TopEnd, Rect(-1000.0f,-600.0f,-1000.0f,-0.0f)),
            Item(ContentScale.Inside, Alignment.CenterStart, Rect(0.0f,-800.0f,0.0f,-200.0f)),
            Item(ContentScale.Inside, Alignment.Center, Rect(-500.0f,-800.0f,-500.0f,-200.0f)),
            Item(ContentScale.Inside, Alignment.CenterEnd, Rect(-1000.0f,-800.0f,-1000.0f,-200.0f)),
            Item(ContentScale.Inside, Alignment.BottomStart, Rect(0.0f,-1000.0f,0.0f,-400.0f)),
            Item(ContentScale.Inside, Alignment.BottomCenter, Rect(-500.0f,-1000.0f,-500.0f,-400.0f)),
            Item(ContentScale.Inside, Alignment.BottomEnd, Rect(-1000.0f,-1000.0f,-1000.0f,-400.0f)),
            Item(ContentScale.Fit, Alignment.TopStart, Rect(0.0f,-1000.0f,0.0f,-0.0f)),
            Item(ContentScale.Fit, Alignment.TopCenter, Rect(-500.0f,-1000.0f,-500.0f,-0.0f)),
            Item(ContentScale.Fit, Alignment.TopEnd, Rect(-1000.0f,-1000.0f,-1000.0f,-0.0f)),
            Item(ContentScale.Fit, Alignment.CenterStart, Rect(0.0f,-1000.0f,0.0f,-0.0f)),
            Item(ContentScale.Fit, Alignment.Center, Rect(-500.0f,-1000.0f,-500.0f,-0.0f)),
            Item(ContentScale.Fit, Alignment.CenterEnd, Rect(-1000.0f,-1000.0f,-1000.0f,-0.0f)),
            Item(ContentScale.Fit, Alignment.BottomStart, Rect(0.0f,-1000.0f,0.0f,-0.0f)),
            Item(ContentScale.Fit, Alignment.BottomCenter, Rect(-500.0f,-1000.0f,-500.0f,-0.0f)),
            Item(ContentScale.Fit, Alignment.BottomEnd, Rect(-1000.0f,-1000.0f,-1000.0f,-0.0f)),
            Item(ContentScale.FillWidth, Alignment.TopStart, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.FillWidth, Alignment.TopCenter, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.FillWidth, Alignment.TopEnd, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.FillWidth, Alignment.CenterStart, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.FillWidth, Alignment.Center, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.FillWidth, Alignment.CenterEnd, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.FillWidth, Alignment.BottomStart, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.FillWidth, Alignment.BottomCenter, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.FillWidth, Alignment.BottomEnd, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.FillHeight, Alignment.TopStart, Rect(0.0f,-1000.0f,0.0f,-0.0f)),
            Item(ContentScale.FillHeight, Alignment.TopCenter, Rect(-500.0f,-1000.0f,-500.0f,-0.0f)),
            Item(ContentScale.FillHeight, Alignment.TopEnd, Rect(-1000.0f,-1000.0f,-1000.0f,-0.0f)),
            Item(ContentScale.FillHeight, Alignment.CenterStart, Rect(0.0f,-1000.0f,0.0f,-0.0f)),
            Item(ContentScale.FillHeight, Alignment.Center, Rect(-500.0f,-1000.0f,-500.0f,-0.0f)),
            Item(ContentScale.FillHeight, Alignment.CenterEnd, Rect(-1000.0f,-1000.0f,-1000.0f,-0.0f)),
            Item(ContentScale.FillHeight, Alignment.BottomStart, Rect(0.0f,-1000.0f,0.0f,-0.0f)),
            Item(ContentScale.FillHeight, Alignment.BottomCenter, Rect(-500.0f,-1000.0f,-500.0f,-0.0f)),
            Item(ContentScale.FillHeight, Alignment.BottomEnd, Rect(-1000.0f,-1000.0f,-1000.0f,-0.0f)),
            Item(ContentScale.FillBounds, Alignment.TopStart, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.FillBounds, Alignment.TopCenter, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.FillBounds, Alignment.TopEnd, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.FillBounds, Alignment.CenterStart, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.FillBounds, Alignment.Center, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.FillBounds, Alignment.CenterEnd, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.FillBounds, Alignment.BottomStart, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.FillBounds, Alignment.BottomCenter, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.FillBounds, Alignment.BottomEnd, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.Crop, Alignment.TopStart, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.Crop, Alignment.TopCenter, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.Crop, Alignment.TopEnd, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.Crop, Alignment.CenterStart, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.Crop, Alignment.Center, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.Crop, Alignment.CenterEnd, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.Crop, Alignment.BottomStart, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.Crop, Alignment.BottomCenter, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.Crop, Alignment.BottomEnd, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
//        ).printlnExpectedMessage(
//            computeExpected =  {
//                computeTranslationBounds(
//                    containerSize = containerSize,
//                    contentSize = contentSize,
//                    contentScale = it.contentScale,
//                    contentAlignment = it.contentAlignment,
//                    scale = scale,
//                )
//            }
        ).forEach {
            Assert.assertEquals(
                it.getMessage(containerSize, contentSize, scale),
                it.expected,
                computeTranslationBounds(
                    containerSize = containerSize,
                    contentSize = contentSize,
                    contentScale = it.contentScale,
                    contentAlignment = it.contentAlignment,
                    scale = scale,
                )
            )
        }

        contentSize = Size(1600f, 1200f)
        scale = 2f
        listOf(
            Item(ContentScale.None, Alignment.TopStart, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.None, Alignment.TopCenter, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.None, Alignment.TopEnd, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.None, Alignment.CenterStart, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.None, Alignment.Center, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.None, Alignment.CenterEnd, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.None, Alignment.BottomStart, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.None, Alignment.BottomCenter, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.None, Alignment.BottomEnd, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.Inside, Alignment.TopStart, Rect(-1000.0f,-500.0f,-0.0f,-0.0f)),
            Item(ContentScale.Inside, Alignment.TopCenter, Rect(-1000.0f,-500.0f,-0.0f,-0.0f)),
            Item(ContentScale.Inside, Alignment.TopEnd, Rect(-1000.0f,-500.0f,-0.0f,-0.0f)),
            Item(ContentScale.Inside, Alignment.CenterStart, Rect(-1000.0f,-750.0f,-0.0f,-250.0f)),
            Item(ContentScale.Inside, Alignment.Center, Rect(-1000.0f,-750.0f,-0.0f,-250.0f)),
            Item(ContentScale.Inside, Alignment.CenterEnd, Rect(-1000.0f,-750.0f,-0.0f,-250.0f)),
            Item(ContentScale.Inside, Alignment.BottomStart, Rect(-1000.0f,-1000.0f,-0.0f,-500.0f)),
            Item(ContentScale.Inside, Alignment.BottomCenter, Rect(-1000.0f,-1000.0f,-0.0f,-500.0f)),
            Item(ContentScale.Inside, Alignment.BottomEnd, Rect(-1000.0f,-1000.0f,-0.0f,-500.0f)),
            Item(ContentScale.Fit, Alignment.TopStart, Rect(-1000.0f,-500.0f,-0.0f,-0.0f)),
            Item(ContentScale.Fit, Alignment.TopCenter, Rect(-1000.0f,-500.0f,-0.0f,-0.0f)),
            Item(ContentScale.Fit, Alignment.TopEnd, Rect(-1000.0f,-500.0f,-0.0f,-0.0f)),
            Item(ContentScale.Fit, Alignment.CenterStart, Rect(-1000.0f,-750.0f,-0.0f,-250.0f)),
            Item(ContentScale.Fit, Alignment.Center, Rect(-1000.0f,-750.0f,-0.0f,-250.0f)),
            Item(ContentScale.Fit, Alignment.CenterEnd, Rect(-1000.0f,-750.0f,-0.0f,-250.0f)),
            Item(ContentScale.Fit, Alignment.BottomStart, Rect(-1000.0f,-1000.0f,-0.0f,-500.0f)),
            Item(ContentScale.Fit, Alignment.BottomCenter, Rect(-1000.0f,-1000.0f,-0.0f,-500.0f)),
            Item(ContentScale.Fit, Alignment.BottomEnd, Rect(-1000.0f,-1000.0f,-0.0f,-500.0f)),
            Item(ContentScale.FillWidth, Alignment.TopStart, Rect(-1000.0f,-500.0f,-0.0f,-0.0f)),
            Item(ContentScale.FillWidth, Alignment.TopCenter, Rect(-1000.0f,-500.0f,-0.0f,-0.0f)),
            Item(ContentScale.FillWidth, Alignment.TopEnd, Rect(-1000.0f,-500.0f,-0.0f,-0.0f)),
            Item(ContentScale.FillWidth, Alignment.CenterStart, Rect(-1000.0f,-750.0f,-0.0f,-250.0f)),
            Item(ContentScale.FillWidth, Alignment.Center, Rect(-1000.0f,-750.0f,-0.0f,-250.0f)),
            Item(ContentScale.FillWidth, Alignment.CenterEnd, Rect(-1000.0f,-750.0f,-0.0f,-250.0f)),
            Item(ContentScale.FillWidth, Alignment.BottomStart, Rect(-1000.0f,-1000.0f,-0.0f,-500.0f)),
            Item(ContentScale.FillWidth, Alignment.BottomCenter, Rect(-1000.0f,-1000.0f,-0.0f,-500.0f)),
            Item(ContentScale.FillWidth, Alignment.BottomEnd, Rect(-1000.0f,-1000.0f,-0.0f,-500.0f)),
            Item(ContentScale.FillHeight, Alignment.TopStart, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.FillHeight, Alignment.TopCenter, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.FillHeight, Alignment.TopEnd, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.FillHeight, Alignment.CenterStart, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.FillHeight, Alignment.Center, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.FillHeight, Alignment.CenterEnd, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.FillHeight, Alignment.BottomStart, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.FillHeight, Alignment.BottomCenter, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.FillHeight, Alignment.BottomEnd, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.FillBounds, Alignment.TopStart, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.FillBounds, Alignment.TopCenter, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.FillBounds, Alignment.TopEnd, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.FillBounds, Alignment.CenterStart, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.FillBounds, Alignment.Center, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.FillBounds, Alignment.CenterEnd, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.FillBounds, Alignment.BottomStart, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.FillBounds, Alignment.BottomCenter, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.FillBounds, Alignment.BottomEnd, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.Crop, Alignment.TopStart, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.Crop, Alignment.TopCenter, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.Crop, Alignment.TopEnd, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.Crop, Alignment.CenterStart, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.Crop, Alignment.Center, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.Crop, Alignment.CenterEnd, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.Crop, Alignment.BottomStart, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.Crop, Alignment.BottomCenter, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.Crop, Alignment.BottomEnd, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
//        ).printlnExpectedMessage(
//            computeExpected =  {
//                computeTranslationBounds(
//                    containerSize = containerSize,
//                    contentSize = contentSize,
//                    contentScale = it.contentScale,
//                    contentAlignment = it.contentAlignment,
//                    scale = scale,
//                )
//            }
        ).forEach {
            Assert.assertEquals(
                it.getMessage(containerSize, contentSize, scale),
                it.expected,
                computeTranslationBounds(
                    containerSize = containerSize,
                    contentSize = contentSize,
                    contentScale = it.contentScale,
                    contentAlignment = it.contentAlignment,
                    scale = scale,
                )
            )
        }

        contentSize = Size(1200f, 1600f)
        scale = 2f
        listOf(
            Item(ContentScale.None, Alignment.TopStart, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.None, Alignment.TopCenter, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.None, Alignment.TopEnd, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.None, Alignment.CenterStart, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.None, Alignment.Center, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.None, Alignment.CenterEnd, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.None, Alignment.BottomStart, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.None, Alignment.BottomCenter, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.None, Alignment.BottomEnd, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.Inside, Alignment.TopStart, Rect(-500.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.Inside, Alignment.TopCenter, Rect(-750.0f,-1000.0f,-250.0f,-0.0f)),
            Item(ContentScale.Inside, Alignment.TopEnd, Rect(-1000.0f,-1000.0f,-500.0f,-0.0f)),
            Item(ContentScale.Inside, Alignment.CenterStart, Rect(-500.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.Inside, Alignment.Center, Rect(-750.0f,-1000.0f,-250.0f,-0.0f)),
            Item(ContentScale.Inside, Alignment.CenterEnd, Rect(-1000.0f,-1000.0f,-500.0f,-0.0f)),
            Item(ContentScale.Inside, Alignment.BottomStart, Rect(-500.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.Inside, Alignment.BottomCenter, Rect(-750.0f,-1000.0f,-250.0f,-0.0f)),
            Item(ContentScale.Inside, Alignment.BottomEnd, Rect(-1000.0f,-1000.0f,-500.0f,-0.0f)),
            Item(ContentScale.Fit, Alignment.TopStart, Rect(-500.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.Fit, Alignment.TopCenter, Rect(-750.0f,-1000.0f,-250.0f,-0.0f)),
            Item(ContentScale.Fit, Alignment.TopEnd, Rect(-1000.0f,-1000.0f,-500.0f,-0.0f)),
            Item(ContentScale.Fit, Alignment.CenterStart, Rect(-500.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.Fit, Alignment.Center, Rect(-750.0f,-1000.0f,-250.0f,-0.0f)),
            Item(ContentScale.Fit, Alignment.CenterEnd, Rect(-1000.0f,-1000.0f,-500.0f,-0.0f)),
            Item(ContentScale.Fit, Alignment.BottomStart, Rect(-500.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.Fit, Alignment.BottomCenter, Rect(-750.0f,-1000.0f,-250.0f,-0.0f)),
            Item(ContentScale.Fit, Alignment.BottomEnd, Rect(-1000.0f,-1000.0f,-500.0f,-0.0f)),
            Item(ContentScale.FillWidth, Alignment.TopStart, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.FillWidth, Alignment.TopCenter, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.FillWidth, Alignment.TopEnd, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.FillWidth, Alignment.CenterStart, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.FillWidth, Alignment.Center, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.FillWidth, Alignment.CenterEnd, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.FillWidth, Alignment.BottomStart, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.FillWidth, Alignment.BottomCenter, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.FillWidth, Alignment.BottomEnd, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.FillHeight, Alignment.TopStart, Rect(-500.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.FillHeight, Alignment.TopCenter, Rect(-750.0f,-1000.0f,-250.0f,-0.0f)),
            Item(ContentScale.FillHeight, Alignment.TopEnd, Rect(-1000.0f,-1000.0f,-500.0f,-0.0f)),
            Item(ContentScale.FillHeight, Alignment.CenterStart, Rect(-500.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.FillHeight, Alignment.Center, Rect(-750.0f,-1000.0f,-250.0f,-0.0f)),
            Item(ContentScale.FillHeight, Alignment.CenterEnd, Rect(-1000.0f,-1000.0f,-500.0f,-0.0f)),
            Item(ContentScale.FillHeight, Alignment.BottomStart, Rect(-500.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.FillHeight, Alignment.BottomCenter, Rect(-750.0f,-1000.0f,-250.0f,-0.0f)),
            Item(ContentScale.FillHeight, Alignment.BottomEnd, Rect(-1000.0f,-1000.0f,-500.0f,-0.0f)),
            Item(ContentScale.FillBounds, Alignment.TopStart, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.FillBounds, Alignment.TopCenter, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.FillBounds, Alignment.TopEnd, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.FillBounds, Alignment.CenterStart, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.FillBounds, Alignment.Center, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.FillBounds, Alignment.CenterEnd, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.FillBounds, Alignment.BottomStart, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.FillBounds, Alignment.BottomCenter, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.FillBounds, Alignment.BottomEnd, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.Crop, Alignment.TopStart, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.Crop, Alignment.TopCenter, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.Crop, Alignment.TopEnd, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.Crop, Alignment.CenterStart, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.Crop, Alignment.Center, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.Crop, Alignment.CenterEnd, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.Crop, Alignment.BottomStart, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.Crop, Alignment.BottomCenter, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
            Item(ContentScale.Crop, Alignment.BottomEnd, Rect(-1000.0f,-1000.0f,-0.0f,-0.0f)),
//        ).printlnExpectedMessage(
//            computeExpected =  {
//                computeTranslationBounds(
//                    containerSize = containerSize,
//                    contentSize = contentSize,
//                    contentScale = it.contentScale,
//                    contentAlignment = it.contentAlignment,
//                    scale = scale,
//                )
//            }
        ).forEach {
            Assert.assertEquals(
                it.getMessage(containerSize, contentSize, scale),
                it.expected,
                computeTranslationBounds(
                    containerSize = containerSize,
                    contentSize = contentSize,
                    contentScale = it.contentScale,
                    contentAlignment = it.contentAlignment,
                    scale = scale,
                )
            )
        }
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
            Offset(0f, 0f) to Rect(0f, 0f, 400f, 600f),
            Offset(250f, 500f) to Rect(0f, 0f, 300f, 400f),
            Offset(750f, 500f) to Rect(0f, 0f, 100f, 400f),
            Offset(250f, 1500f) to Rect(0f, 0f, 0f, 0f),
            Offset(750f, 1500f) to Rect(0f, 0f, 0f, 0f),
            Offset(1000f, 2000f) to Rect(0f, 0f, 0f, 0f),
            Offset(-250f, -500f) to Rect(100f, 0f, 500f, 800f),
            Offset(-750f, -500f) to Rect(300f, 0f, 700f, 800f),
            Offset(-250f, -1500f) to Rect(100f, 400f, 500f, 1200f),
            Offset(-750f, -1500f) to Rect(300f, 400f, 700f, 1200f),
            Offset(-1000f, -2000f) to Rect(400f, 600f, 800f, 1200f)
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
            Offset(0f, 0f) to Rect(0f, 0f, 800f, 1200f),
            Offset(250f, 500f) to Rect(0f, 0f, 650f, 1100f),
            Offset(750f, 500f) to Rect(0f, 0f, 150f, 1100f),
            Offset(250f, 1500f) to Rect(0f, 0f, 650f, 100f),
            Offset(750f, 1500f) to Rect(0f, 0f, 150f, 100f),
            Offset(1000f, 2000f) to Rect(0f, 0f, 0f, 0f),
            Offset(-250f, -500f) to Rect(150f, 100f, 800f, 1200f),
            Offset(-750f, -500f) to Rect(650f, 100f, 800f, 1200f),
            Offset(-250f, -1500f) to Rect(150f, 1100f, 800f, 1200f),
            Offset(-750f, -1500f) to Rect(650f, 1100f, 800f, 1200f),
            Offset(-1000f, -2000f) to Rect(0f, 0f, 0f, 0f)
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
            Offset(0f, 0f) to Rect(0f, 0f, 400f, 600f),
            Offset(250f, 500f) to Rect(0f, 0f, 275f, 350f),
            Offset(750f, 500f) to Rect(0f, 0f, 25f, 350f),
            Offset(250f, 1500f) to Rect(0f, 0f, 0f, 0f),
            Offset(750f, 1500f) to Rect(0f, 0f, 0f, 0f),
            Offset(1000f, 2000f) to Rect(0f, 0f, 0f, 0f),
            Offset(-250f, -500f) to Rect(25f, 0f, 525f, 850f),
            Offset(-750f, -500f) to Rect(275f, 0f, 775f, 850f),
            Offset(-250f, -1500f) to Rect(25f, 350f, 525f, 1200f),
            Offset(-750f, -1500f) to Rect(275f, 350f, 775f, 1200f),
            Offset(-1000f, -2000f) to Rect(400f, 600f, 800f, 1200f)
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

//        val containerSize = Size(1000f, 2000f)
//        val contentSize = Size(800f, 1200f)
//        val scaledContentSize = Size(1000f, 1500f)
        // todo test crop, FillWidth, FillHeight, None
        contentScale = ContentScale.Crop
        contentAlignment = Alignment.Center
        scale = 1f
        listOf(
            Offset(0f, 0f) to Rect(99.99998f, 0f, 700f, 1200f),
            Offset(250f, 500f) to Rect(99.99998f, 0f, 550f, 900f),
            Offset(750f, 500f) to Rect(99.99998f, 0f, 249.99997f, 900f),
            Offset(250f, 1500f) to Rect(99.99998f, 0f, 550f, 300f),
            Offset(750f, 1500f) to Rect(99.99998f, 0f, 249.99997f, 300f),
            Offset(1000f, 2000f) to Rect(0f, 0f, 0f, 0f),
            Offset(-250f, -500f) to Rect(249.99997f, 300f, 700f, 1200f),
            Offset(-750f, -500f) to Rect(550f, 300f, 700f, 1200f),
            Offset(-250f, -1500f) to Rect(249.99997f, 900f, 700f, 1200f),
            Offset(-750f, -1500f) to Rect(550f, 900f, 700f, 1200f),
            Offset(-1000f, -2000f) to Rect(0f, 0f, 0f, 0f)
        ).forEach { (translation, expectedVisibleRect) ->
            Assert.assertEquals(
                "containerSize=$containerSize, contentSize=$contentSize, contentScale=${contentScale.name}, contentAlignment=${contentAlignment.name}, scale=$scale, translation=$translation",
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
//            Offset(250f, 500f) to Rect(0f, 0f, 275f, 350f),
//            Offset(750f, 500f) to Rect(0f, 0f, 25f, 350f),
//            Offset(250f, 1500f) to Rect(0f, 0f, 0f, 0f),
//            Offset(750f, 1500f) to Rect(0f, 0f, 0f, 0f),
//            Offset(1000f, 2000f) to Rect(0f, 0f, 0f, 0f),
//            Offset(-250f, -500f) to Rect(25f, 0f, 525f, 850f),
//            Offset(-750f, -500f) to Rect(275f, 0f, 775f, 850f),
//            Offset(-250f, -1500f) to Rect(25f, 350f, 525f, 1200f),
//            Offset(-750f, -1500f) to Rect(275f, 350f, 775f, 1200f),
//            Offset(-1000f, -2000f) to Rect(400f, 600f, 800f, 1200f)
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
            Alignment.TopStart to Centroid(0.625f, 1f),
            Alignment.TopCenter to Centroid(0.5f, 1f),
            Alignment.TopEnd to Centroid(0.375f, 1f),
            Alignment.CenterStart to Centroid(0.625f, 0.5f),
            Alignment.Center to Centroid(0.5f, 0.5f),
            Alignment.CenterEnd to Centroid(0.375f, 0.5f),
            Alignment.BottomStart to Centroid(0.625f, 0f),
            Alignment.BottomCenter to Centroid(0.5f, 0f),
            Alignment.BottomEnd to Centroid(0.375f, 0f)
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
            Centroid(0.2f, 0.2f) to (Centroid(0.2f, 0f) to Centroid(0.2f, 0.4375f)),
            Centroid(0.55f, 0.55f) to (Centroid(0.55f, 0.9f) to Centroid(0.55f, 0.55f)),
            Centroid(0.8f, 0.8f) to (Centroid(0.8f, 1f) to Centroid(0.8f, 0.5625f)),
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

        // todo test crop, FillWidth, FillHeight, None
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

        // todo test crop, FillWidth, FillHeight, None
    }
}