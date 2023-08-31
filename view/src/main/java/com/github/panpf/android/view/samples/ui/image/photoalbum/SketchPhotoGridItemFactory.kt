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
import com.github.panpf.android.view.samples.R
import com.github.panpf.sketch.displayImage
import com.github.panpf.sketch.resize.LongImageClipPrecisionDecider
import com.github.panpf.sketch.resize.LongImageScaleDecider
import com.github.panpf.sketch.resize.Precision
import com.github.panpf.sketch.stateimage.IconStateImage
import com.github.panpf.sketch.stateimage.ResColor

class SketchPhotoGridItemFactory : BasePhotoGridItemFactory() {

    override fun displayImage(imageView: ImageView, sketchImageUri: String) {
        imageView.displayImage(sketchImageUri) {
            val bgColor = ResColor(R.color.placeholder_bg)
            placeholder(IconStateImage(R.drawable.ic_image_outline, bgColor))
            error(IconStateImage(R.drawable.ic_error, bgColor))
            crossfade()
            resizeApplyToDrawable()
            resizePrecision(LongImageClipPrecisionDecider(Precision.SAME_ASPECT_RATIO))
            resizeScale(LongImageScaleDecider())
        }
    }
}