package com.github.panpf.android.view.samples.ui.image

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.github.panpf.android.view.samples.databinding.ImageMatrixFragmentBinding
import com.github.panpf.android.view.samples.ui.base.ToolbarBindingFragment

class ImageMatrixFragment : ToolbarBindingFragment<ImageMatrixFragmentBinding>() {

    override fun onViewCreated(
        toolbar: Toolbar,
        binding: ImageMatrixFragmentBinding,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(toolbar, binding, savedInstanceState)
        toolbar.title = "Image Matrix"
    }
}