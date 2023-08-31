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
import android.util.Log
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.davemorrissey.labs.subscaleview.ImageSource
import com.github.panpf.android.view.samples.databinding.SubsamplingViewFragmentBinding
import com.github.panpf.android.view.samples.ui.base.BindingFragment
import com.github.panpf.assemblyadapter.pager.FragmentItemFactory
import com.github.panpf.sketch.request.Depth.NETWORK
import com.github.panpf.sketch.request.DownloadData
import com.github.panpf.sketch.request.DownloadRequest
import com.github.panpf.sketch.request.DownloadResult
import com.github.panpf.sketch.sketch
import kotlinx.coroutines.launch

class SubsamplingViewFragment : BindingFragment<SubsamplingViewFragmentBinding>() {

    private val args by navArgs<SubsamplingViewFragmentArgs>()

    override fun onViewCreated(
        binding: SubsamplingViewFragmentBinding,
        savedInstanceState: Bundle?
    ) {
        viewLifecycleOwner.lifecycleScope.launch {
            val imageSource = newImageSource(args.imageUri)
            if (imageSource != null) {
                binding.subsamplingView.setImage(imageSource)
            }
        }
    }

    private suspend fun newImageSource(sketchImageUri: String): ImageSource? {
        return when {
            sketchImageUri.startsWith("asset://") -> {
                ImageSource.asset(sketchImageUri.replace("asset://", ""))
            }

            sketchImageUri.startsWith("android.resource://") -> {
                val resId =
                    sketchImageUri.toUri().getQueryParameters("resId").firstOrNull()?.toIntOrNull()
                if (resId != null) {
                    ImageSource.resource(resId)
                } else {
                    Log.e(
                        "ZoomImageViewFragment",
                        "newImageSource failed, invalid resource uri: '$sketchImageUri'"
                    )
                    null
                }
            }

            sketchImageUri.startsWith("http://") || sketchImageUri.startsWith("https://") -> {
                val request = DownloadRequest(requireContext(), args.imageUri) {
                    lifecycle(viewLifecycleOwner.lifecycle)
                    depth(NETWORK)
                }
                val result = requireContext().sketch.execute(request)
                if (result is DownloadResult.Success) {
                    val data = result.data.data
                    if (data is DownloadData.DiskCacheData) {
                        ImageSource.uri(data.snapshot.file.toUri())
                    } else {
                        Log.e(
                            "ZoomImageViewFragment",
                            "newImageSource failed, data is byte array. uri: '$sketchImageUri'"
                        )
                        null
                    }
                } else {
                    val errorMessage = (result as DownloadResult.Error).throwable.toString()
                    Log.e(
                        "ZoomImageViewFragment",
                        "newImageSource failed, image download failed: $errorMessage. uri: '$sketchImageUri'"
                    )
                    null
                }
            }

            else -> {
                ImageSource.uri(sketchImageUri)
            }
        }
    }

    class ItemFactory : FragmentItemFactory<String>(String::class) {

        override fun createFragment(
            bindingAdapterPosition: Int,
            absoluteAdapterPosition: Int,
            data: String
        ): Fragment = SubsamplingViewFragment().apply {
            arguments = SubsamplingViewFragmentArgs(data).toBundle()
        }
    }
}