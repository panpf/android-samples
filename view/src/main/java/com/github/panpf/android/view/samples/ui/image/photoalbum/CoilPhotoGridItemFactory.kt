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

package com.github.panpf.android.view.samples.ui.image.photoalbum

import android.widget.ImageView
import coil3.load
import coil3.request.error
import coil3.request.placeholder
import com.github.panpf.android.samples.utils.sketchUri2CoilModel
import com.github.panpf.android.view.samples.R

class CoilPhotoGridItemFactory : BasePhotoGridItemFactory() {

    override fun displayImage(imageView: ImageView, sketchImageUri: String) {
        imageView.load(sketchUri2CoilModel(imageView.context, sketchImageUri)) {
            placeholder(R.drawable.im_placeholder)
            error(R.drawable.im_error)
//            crossfade(true)
        }
    }
}