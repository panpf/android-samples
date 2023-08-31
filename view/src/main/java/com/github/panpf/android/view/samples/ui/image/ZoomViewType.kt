package com.github.panpf.android.view.samples.ui.image

import com.github.panpf.android.samples.photos.Photo
import com.github.panpf.android.view.samples.ui.image.photoalbum.CoilPhotoGridItemFactory
import com.github.panpf.android.view.samples.ui.image.photoalbum.GlidePhotoGridItemFactory
import com.github.panpf.android.view.samples.ui.image.photoalbum.PicassoPhotoGridItemFactory
import com.github.panpf.android.view.samples.ui.image.photoalbum.SketchPhotoGridItemFactory
import com.github.panpf.android.view.samples.ui.image.photoslideshow.CoilZoomImageViewFragment
import com.github.panpf.android.view.samples.ui.image.photoslideshow.GlideZoomImageViewFragment
import com.github.panpf.android.view.samples.ui.image.photoslideshow.PhotoViewFragment
import com.github.panpf.android.view.samples.ui.image.photoslideshow.PicassoZoomImageViewFragment
import com.github.panpf.android.view.samples.ui.image.photoslideshow.SketchZoomImageViewFragment
import com.github.panpf.android.view.samples.ui.image.photoslideshow.SubsamplingViewFragment
import com.github.panpf.assemblyadapter.ItemFactory
import com.github.panpf.assemblyadapter.pager.FragmentItemFactory

enum class ZoomViewType(
    val title: String,
    val createListItemFactory: () -> ItemFactory<Photo>,
    val createPageItemFactory: () -> FragmentItemFactory<String>,
    val my: Boolean,
    val supportIgnoreExifOrientation: Boolean,
) {
    SketchZoomImageView(
        title = "SketchZoomImageView",
        createListItemFactory = { SketchPhotoGridItemFactory() },
        createPageItemFactory = { SketchZoomImageViewFragment.ItemFactory() },
        my = true,
        supportIgnoreExifOrientation = true,
    ),

    CoilZoomImageView(
        title = "CoilZoomImageView",
        createListItemFactory = { CoilPhotoGridItemFactory() },
        createPageItemFactory = { CoilZoomImageViewFragment.ItemFactory() },
        my = true,
        supportIgnoreExifOrientation = false,
    ),

    GlideZoomImageView(
        title = "GlideZoomImageView",
        createListItemFactory = { GlidePhotoGridItemFactory() },
        createPageItemFactory = { GlideZoomImageViewFragment.ItemFactory() },
        my = true,
        supportIgnoreExifOrientation = false,
    ),

    PicassoZoomImageView(
        title = "PicassoZoomImageView",
        createListItemFactory = { PicassoPhotoGridItemFactory() },
        createPageItemFactory = { PicassoZoomImageViewFragment.ItemFactory() },
        my = true,
        supportIgnoreExifOrientation = false,
    ),

    PhotoView(
        title = "PhotoView",
        createListItemFactory = { SketchPhotoGridItemFactory() },
        createPageItemFactory = { PhotoViewFragment.ItemFactory() },
        my = false,
        supportIgnoreExifOrientation = false,
    ),

    SubsamplingScaleImageView(
        title = "SubsamplingScaleImageView",
        createListItemFactory = { SketchPhotoGridItemFactory() },
        createPageItemFactory = { SubsamplingViewFragment.ItemFactory() },
        my = false,
        supportIgnoreExifOrientation = false,
    ),
}