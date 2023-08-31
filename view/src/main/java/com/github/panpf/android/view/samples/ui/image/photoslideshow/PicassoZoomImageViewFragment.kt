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

import android.net.Uri
import android.os.Bundle
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.github.panpf.android.view.samples.databinding.PicassoZoomImageViewFragmentBinding
import com.github.panpf.android.view.samples.ui.base.BindingFragment
import com.github.panpf.assemblyadapter.pager.FragmentItemFactory
import com.squareup.picasso.RequestCreator

class PicassoZoomImageViewFragment : BindingFragment<PicassoZoomImageViewFragmentBinding>() {

    private val args by navArgs<PicassoZoomImageViewFragmentArgs>()

    override fun onViewCreated(
        binding: PicassoZoomImageViewFragmentBinding,
        savedInstanceState: Bundle?
    ) {
        val config: RequestCreator.() -> Unit = {
            fit()
            centerInside()
        }
        val sketchImageUri = args.imageUri
        when {
            sketchImageUri.startsWith("asset://") ->
                binding.picassoZoomImageViewImage.loadImage(
                    path = sketchImageUri.replace("asset://", "file:///android_asset/"),
                    config = config
                )

            sketchImageUri.startsWith("android.resource://") -> {
                val resId =
                    sketchImageUri.toUri().getQueryParameters("resId").firstOrNull()
                        ?.toIntOrNull()
                        ?: throw IllegalArgumentException("Can't use Subsampling, invalid resource uri: '$sketchImageUri'")
                binding.picassoZoomImageViewImage.loadImage(
                    resourceId = resId,
                    config = config
                )
            }

            else ->
                binding.picassoZoomImageViewImage.loadImage(
                    uri = Uri.parse(sketchImageUri),
                    config = config
                )
        }
    }

    class ItemFactory : FragmentItemFactory<String>(String::class) {

        override fun createFragment(
            bindingAdapterPosition: Int,
            absoluteAdapterPosition: Int,
            data: String
        ): Fragment = PicassoZoomImageViewFragment().apply {
            arguments = PicassoZoomImageViewFragmentArgs(data).toBundle()
        }
    }
}