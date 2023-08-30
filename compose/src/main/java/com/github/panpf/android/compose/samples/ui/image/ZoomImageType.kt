package com.github.panpf.android.compose.samples.ui.image

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

enum class ZoomImageType(
    val title: String,
    val subtitle: String?,
    val drawListContent: @Composable (sketchImageUri: String, modifier: Modifier) -> Unit,
    val drawContent: @Composable (sketchImageUri: String) -> Unit,
) {

    SketchZoomAsyncImage(
        title = "SketchZoomAsyncImage",
        subtitle = "SketchList + SketchZoom",
        drawListContent = { sketchImageUri, modifier ->
            SketchListImage(sketchImageUri, modifier)
        },
        drawContent = { sketchImageUri ->
            SketchZoomAsyncImageSample(sketchImageUri)
        },
    ),

    CoilZoomAsyncImage(
        title = "CoilZoomAsyncImage",
        subtitle = "CoilList + CoilZoom",
        drawListContent = { sketchImageUri, modifier ->
            CoilListImage(sketchImageUri, modifier)
        },
        drawContent = { sketchImageUri ->
            CoilZoomAsyncImageSample(sketchImageUri)
        },
    ),

    GlideZoomAsyncImage(
        title = "GlideZoomAsyncImage",
        subtitle = "GlideList + GlideZoom",
        drawListContent = { sketchImageUri, modifier ->
            GlideListImage(sketchImageUri, modifier)
        },
        drawContent = { sketchImageUri ->
            GlideZoomAsyncImageSample(sketchImageUri)
        },
    ),

    TelephotoZoomableAsyncImage(
        title = "ZoomableAsyncImage",
        subtitle = "CoilList + TelephotoZoom",
        drawListContent = { sketchImageUri, modifier ->
            CoilListImage(sketchImageUri, modifier)
        },
        drawContent = { sketchImageUri ->
            TelephotoZoomableAsyncImageSample(sketchImageUri)
        },
    ),
}