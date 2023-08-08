package com.github.panpf.android.view.samples.ui.image

import android.annotation.SuppressLint
import android.graphics.Matrix
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.github.panpf.android.samples.model.util.BitmapScaleTransformation
import com.github.panpf.android.view.samples.R
import com.github.panpf.android.view.samples.databinding.ImageMatrixFragmentBinding
import com.github.panpf.android.view.samples.ui.base.ToolbarBindingFragment
import com.github.panpf.android.view.samples.ui.util.getRotation
import com.github.panpf.android.view.samples.ui.util.getScale
import com.github.panpf.android.view.samples.ui.util.getTranslation
import com.github.panpf.android.view.samples.util.toShortString
import com.github.panpf.sketch.displayImage
import kotlin.math.min

class ImageMatrixFragment : ToolbarBindingFragment<ImageMatrixFragmentBinding>() {

    private val matrix = Matrix()
    private val scaleStep = 0.2f
    private val offsetStep = 50
    private val rotateStep = 90

    override fun onViewCreated(
        toolbar: Toolbar,
        binding: ImageMatrixFragmentBinding,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(toolbar, binding, savedInstanceState)
        toolbar.title = "Image Matrix"

        binding.imageMatrixFragmentImageView.apply {
        }

        binding.imageMatrixFragmentHorizontalButton.setOnClickListener {
            setImage(binding, true)
        }

        binding.imageMatrixFragmentVerticalButton.setOnClickListener {
            setImage(binding, false)
        }

        binding.imageMatrixFragmentResetButton.setOnClickListener {
            matrix.reset()
            updateMatrix(binding)
        }

        binding.imageMatrixFragmentScalePlusButton.setOnClickListener {
            matrix.postScale(1 + scaleStep, 1 + scaleStep)
            updateMatrix(binding)
        }

        binding.imageMatrixFragmentScaleMinusButton.setOnClickListener {
            matrix.postScale(1 - scaleStep, 1 - scaleStep)
            updateMatrix(binding)
        }

        binding.imageMatrixFragmentOffsetUpButton.setOnClickListener {
            matrix.postTranslate(0f, -offsetStep.toFloat())
            updateMatrix(binding)
        }

        binding.imageMatrixFragmentOffsetDownButton.setOnClickListener {
            matrix.postTranslate(0f, offsetStep.toFloat())
            updateMatrix(binding)
        }

        binding.imageMatrixFragmentOffsetRightButton.setOnClickListener {
            matrix.postTranslate(offsetStep.toFloat(), 0f)
            updateMatrix(binding)
        }

        binding.imageMatrixFragmentOffsetLeftButton.setOnClickListener {
            matrix.postTranslate(-offsetStep.toFloat(), 0f)
            updateMatrix(binding)
        }

        binding.imageMatrixFragmentRotatePlusButton.setOnClickListener {
            matrix.postRotate(rotateStep.toFloat())
            updateMatrix(binding)
        }

        binding.imageMatrixFragmentRotateMinusButton.setOnClickListener {
            matrix.postRotate(-rotateStep.toFloat())
            updateMatrix(binding)
        }

        updateMatrix(binding)
        setImage(binding, true)
    }

    private fun updateMatrix(binding: ImageMatrixFragmentBinding) {
        val matrix = matrix
        binding.imageMatrixFragmentImageView.imageMatrix = matrix
        binding.imageMatrixFragmentScaleValueText.text = matrix.getScale().toShortString()
        binding.imageMatrixFragmentOffsetValueText.text = matrix.getTranslation().toShortString()
        binding.imageMatrixFragmentRotateValueText.text = matrix.getRotation().toString()
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun setImage(binding: ImageMatrixFragmentBinding, hor: Boolean) {
        binding.imageMatrixFragmentImageView.displayImage(if (hor) R.drawable.dog_hor else R.drawable.dog_ver) {
            val resources = requireContext().resources
            val maxSize =
                min(resources.displayMetrics.widthPixels, resources.displayMetrics.heightPixels) / 4
            addTransformations(BitmapScaleTransformation(maxSize))
        }
    }
}