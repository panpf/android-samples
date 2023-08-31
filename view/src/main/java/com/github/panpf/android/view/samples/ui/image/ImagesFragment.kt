package com.github.panpf.android.view.samples.ui.image

import android.Manifest
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.github.panpf.android.samples.model.Link
import com.github.panpf.android.view.samples.NavMainDirections
import com.github.panpf.android.view.samples.R
import com.github.panpf.android.view.samples.databinding.RecyclerBinding
import com.github.panpf.android.view.samples.model.ListSeparator
import com.github.panpf.android.view.samples.ui.link.BaseLinkListFragment

class ImagesFragment : BaseLinkListFragment() {

    override fun onViewCreated(
        toolbar: Toolbar,
        binding: RecyclerBinding,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(toolbar, binding, savedInstanceState)
        toolbar.title = "Image"
    }

    override fun buildData(): List<Any> {
        return listOf(
            ListSeparator("Zoom"),
            Link(
                title = "SketchZoomImageView",
                nav = NavMainDirections.actionGlobalZoomViewFragment(ZoomViewType.SketchZoomImageView.name),
            ),
            Link(
                title = "CoilZoomImageView",
                nav = NavMainDirections.actionGlobalZoomViewFragment(ZoomViewType.CoilZoomImageView.name),
            ),
            Link(
                title = "GlideZoomImageView",
                nav = NavMainDirections.actionGlobalZoomViewFragment(ZoomViewType.GlideZoomImageView.name),
            ),
            Link(
                title = "PicassoZoomImageView",
                nav = NavMainDirections.actionGlobalZoomViewFragment(ZoomViewType.PicassoZoomImageView.name),
            ),
            Link(
                title = "PhotoView",
                nav = NavMainDirections.actionGlobalZoomViewFragment(ZoomViewType.PhotoView.name),
            ),
            Link(
                title = "SubsamplingScaleImageView",
                nav = NavMainDirections.actionGlobalZoomViewFragment(ZoomViewType.SubsamplingScaleImageView.name),
            ),

            ListSeparator("Photo Album"),
            Link(
                title = "PhotoAlbum - SketchList + SketchZoom",
                nav = NavMainDirections.actionGlobalPhotoAlbumViewFragment(ZoomViewType.SketchZoomImageView.name),
                permissions = listOf(Manifest.permission.READ_EXTERNAL_STORAGE)
            ),
            Link(
                title = "PhotoAlbum - CoilList + CoilZoom",
                nav = NavMainDirections.actionGlobalPhotoAlbumViewFragment(ZoomViewType.CoilZoomImageView.name),
                permissions = listOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                minSdk = 21
            ),
            Link(
                title = "PhotoAlbum - GlideList + GlideZoom",
                nav = NavMainDirections.actionGlobalPhotoAlbumViewFragment(ZoomViewType.GlideZoomImageView.name),
                permissions = listOf(Manifest.permission.READ_EXTERNAL_STORAGE)
            ),
            Link(
                title = "PhotoAlbum - PicassoList + PicassoZoom",
                nav = NavMainDirections.actionGlobalPhotoAlbumViewFragment(ZoomViewType.PicassoZoomImageView.name),
                permissions = listOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                minSdk = 21
            ),
            Link(
                title = "PhotoAlbum - SketchList + PhotoView",
                nav = NavMainDirections.actionGlobalPhotoAlbumViewFragment(ZoomViewType.PhotoView.name),
                permissions = listOf(Manifest.permission.READ_EXTERNAL_STORAGE)
            ),
            Link(
                title = "PhotoAlbum - SketchList + SubsamplingScaleImageView",
                nav = NavMainDirections.actionGlobalPhotoAlbumViewFragment(ZoomViewType.SubsamplingScaleImageView.name),
                permissions = listOf(Manifest.permission.READ_EXTERNAL_STORAGE)
            ),

            ListSeparator("Test"),
            Link(
                title = "Image Matrix",
                nav = R.id.action_global_imageMatrixFragment,
            ),
        )
    }
}