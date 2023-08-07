package com.github.panpf.android.view.samples.ui.image

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.github.panpf.android.samples.model.Link
import com.github.panpf.android.view.samples.R
import com.github.panpf.android.view.samples.databinding.RecyclerBinding
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
            Link(
                title = "Image Matrix",
                nav = R.id.action_global_imageMatrixFragment,
            ),
        )
    }
}