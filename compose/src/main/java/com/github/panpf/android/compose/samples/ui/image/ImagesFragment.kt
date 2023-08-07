package com.github.panpf.android.compose.samples.ui.image

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
        Link("AsyncImage - List - Coil", R.id.action_global_asyncImageListCoilFragment),
        Link("AsyncImage - List - Sketch", R.id.action_global_asyncImageListSketchFragment),
        Link("ZoomImage", R.id.action_global_ZoomImageFragment),
    )
}