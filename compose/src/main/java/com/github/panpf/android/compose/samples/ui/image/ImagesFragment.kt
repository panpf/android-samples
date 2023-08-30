package com.github.panpf.android.compose.samples.ui.image

import android.Manifest
import com.github.panpf.android.compose.samples.NavMainDirections
import com.github.panpf.android.compose.samples.R
import com.github.panpf.android.compose.samples.ui.base.list.BaseLinkListFragment
import com.github.panpf.android.samples.model.Link

class ImagesFragment : BaseLinkListFragment() {

    override fun getTitle(): String {
        return "Images"
    }

    override fun buildLinkList(): List<Link> = listOf(
        Link("Image", R.id.action_global_imageFragment),

        Link("AsyncImage - Coil", R.id.action_global_asyncImageCoilFragment),
        Link("AsyncImage - Sketch", R.id.action_global_asyncImageSketchFragment),

        Link(
            title = "SketchZoomAsyncImage",
            nav = NavMainDirections.actionGlobalZoomImageFragment(
                ZoomImageType.SketchZoomAsyncImage.name
            ),
            permissions = listOf(Manifest.permission.READ_EXTERNAL_STORAGE)
        ),
        Link(
            title = "CoilZoomAsyncImage",
            nav = NavMainDirections.actionGlobalZoomImageFragment(
                ZoomImageType.CoilZoomAsyncImage.name
            ),
            permissions = listOf(Manifest.permission.READ_EXTERNAL_STORAGE)
        ),
        Link(
            title = "GlideZoomAsyncImage",
            nav = NavMainDirections.actionGlobalZoomImageFragment(
                ZoomImageType.GlideZoomAsyncImage.name
            ),
            permissions = listOf(Manifest.permission.READ_EXTERNAL_STORAGE)
        ),
        Link(
            title = "ZoomableAsyncImage",
            nav = NavMainDirections.actionGlobalZoomImageFragment(
                ZoomImageType.TelephotoZoomableAsyncImage.name
            ),
            minSdk = 23,
            permissions = listOf(Manifest.permission.READ_EXTERNAL_STORAGE)
        ),

        Link(
            title = "PhotoAlbum - SketchList + SketchZoom",
            nav = NavMainDirections.actionGlobalPhotoAlbumComposeFragment(
                ZoomImageType.SketchZoomAsyncImage.name
            ),
            permissions = listOf(Manifest.permission.READ_EXTERNAL_STORAGE)
        ),
        Link(
            title = "PhotoAlbum - CoilList + CoilZoom",
            nav = NavMainDirections.actionGlobalPhotoAlbumComposeFragment(
                ZoomImageType.CoilZoomAsyncImage.name
            ),
            permissions = listOf(Manifest.permission.READ_EXTERNAL_STORAGE)
        ),
        Link(
            title = "PhotoAlbum - GlideList + GlideZoom",
            nav = NavMainDirections.actionGlobalPhotoAlbumComposeFragment(
                ZoomImageType.GlideZoomAsyncImage.name
            ),
            permissions = listOf(Manifest.permission.READ_EXTERNAL_STORAGE)
        ),
        Link(
            title = "PhotoAlbum - CoilList + TelephotoZoom",
            nav = NavMainDirections.actionGlobalPhotoAlbumComposeFragment(
                ZoomImageType.TelephotoZoomableAsyncImage.name
            ),
            minSdk = 23,
            permissions = listOf(Manifest.permission.READ_EXTERNAL_STORAGE)
        ),
    )
}