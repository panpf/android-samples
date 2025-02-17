/*
 * Copyright (C) 2023 panpf <panpfpanpf@outlook.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.panpf.android.view.samples.ui.image.photoslideshow

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.github.panpf.android.view.samples.databinding.SketchZoomImageViewFragmentBinding
import com.github.panpf.android.view.samples.ui.base.BindingFragment
import com.github.panpf.assemblyadapter.pager.FragmentItemFactory
import com.github.panpf.sketch.loadImage

class SketchZoomImageViewFragment : BindingFragment<SketchZoomImageViewFragmentBinding>() {

    private val args by navArgs<SketchZoomImageViewFragmentArgs>()

    override fun onViewCreated(
        binding: SketchZoomImageViewFragmentBinding,
        savedInstanceState: Bundle?
    ) {
        binding.sketchZoomImageViewImage.loadImage(args.imageUri) {
            crossfade()
        }
    }

    class ItemFactory : FragmentItemFactory<String>(String::class) {

        override fun createFragment(
            bindingAdapterPosition: Int,
            absoluteAdapterPosition: Int,
            data: String
        ): Fragment = SketchZoomImageViewFragment().apply {
            arguments = SketchZoomImageViewFragmentArgs(data).toBundle()
        }
    }
}