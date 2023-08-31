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

package com.github.panpf.android.view.samples.ui.image

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.navArgs
import com.github.panpf.android.samples.SampleImages
import com.github.panpf.android.view.samples.databinding.FragmentContainerBinding
import com.github.panpf.android.view.samples.ui.base.ToolbarBindingFragment

class ZoomViewFragment : ToolbarBindingFragment<FragmentContainerBinding>() {

    private val args by navArgs<ZoomViewFragmentArgs>()
    private val zoomViewType by lazy { ZoomViewType.valueOf(args.zoomViewType) }

    override fun onViewCreated(
        toolbar: Toolbar,
        binding: FragmentContainerBinding,
        savedInstanceState: Bundle?
    ) {
        toolbar.apply {
            title = zoomViewType.title
        }

        childFragmentManager.beginTransaction()
            .replace(
                binding.fragmentContainerView.id,
                zoomViewType.createPageItemFactory()
                    .dispatchCreateFragment(0, 0, SampleImages.Asset.CHINA.uri)
            )
            .commit()
    }
}
