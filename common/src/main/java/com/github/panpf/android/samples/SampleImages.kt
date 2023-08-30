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

package com.github.panpf.android.samples

import com.github.panpf.android.samples.common.R
import com.github.panpf.sketch.fetch.newAssetUri
import com.github.panpf.sketch.fetch.newResourceUri
import com.github.panpf.sketch.util.Size

object SampleImages {
    object Asset {
        val DOG = SampleImage(
            name = "DOG",
            fileName = "sample_dog.jpg",
            uri = newAssetUri("sample_dog.jpg"),
            size = Size(640, 427)
        )
        val CAT = SampleImage(
            name = "CAT",
            fileName = "sample_cat.jpg",
            uri = newAssetUri("sample_cat.jpg"),
            size = Size(150, 266)
        )
        val ELEPHANT = SampleImage(
            name = "ELEPHANT",
            fileName = "sample_elephant.jpg",
            uri = newAssetUri("sample_elephant.jpg"),
            size = Size(150, 266)
        )
        val WHALE = SampleImage(
            name = "WHALE",
            fileName = "sample_whale.jpg",
            uri = newAssetUri("sample_whale.jpg"),
            size = Size(150, 266)
        )
        val CHINA = SampleImage(
            name = "CHINA",
            fileName = "sample_huge_china.jpg",
            uri = newAssetUri("sample_huge_china.jpg"),
            size = Size(3964, 1920)
        )
        val CARD = SampleImage(
            name = "CARD",
            fileName = "sample_huge_card.jpg",
            uri = newAssetUri("sample_huge_card.jpg"),
            size = Size(7557, 5669)
        )
        val QMSHT = SampleImage(
            name = "QMSHT",
            fileName = "sample_long_qmsht.jpg",
            uri = newAssetUri("sample_long_qmsht.jpg"),
            size = Size(30000, 926)
        )
        val COMIC = SampleImage(
            name = "COMIC",
            fileName = "sample_long_comic.jpg",
            uri = newAssetUri("sample_long_comic.jpg"),
            size = Size(690, 12176)
        )
        val ALL = listOf(DOG, CAT, ELEPHANT, WHALE, CHINA, CARD, QMSHT, COMIC)
    }

    object Resource {
        val DOG = SampleImage(
            name = "DOG",
            fileName = "sample_dog.jpg",
            uri = newResourceUri(R.raw.sample_dog),
            size = Size(640, 427)
        )
        val CAT = SampleImage(
            name = "CAT",
            fileName = "sample_cat.jpg",
            uri = newResourceUri(R.raw.sample_cat),
            size = Size(150, 266)
        )
        val ALL = listOf(DOG, CAT)
    }

    object Http {
        private const val path = "http://img.panpengfei.com/"
        val DOG = Asset.DOG.let { it.copy(uri = it.uri.replace("asset://", path)) }
        val CAT = Asset.CAT.let { it.copy(uri = it.uri.replace("asset://", path)) }
        val ELEPHANT = Asset.ELEPHANT.let { it.copy(uri = it.uri.replace("asset://", path)) }
        val WHALE = Asset.WHALE.let { it.copy(uri = it.uri.replace("asset://", path)) }
        val CHINA = Asset.CHINA.let { it.copy(uri = it.uri.replace("asset://", path)) }
        val CARD = Asset.CARD.let { it.copy(uri = it.uri.replace("asset://", path)) }
        val QMSHT = Asset.QMSHT.let { it.copy(uri = it.uri.replace("asset://", path)) }
        val COMIC = Asset.COMIC.let { it.copy(uri = it.uri.replace("asset://", path)) }
        val ALL = listOf(DOG, CAT, ELEPHANT, WHALE, CHINA, CARD, QMSHT, COMIC)
    }
}

class SampleImage(val name: String, val fileName: String, val uri: String, val size: Size) {
    fun copy(
        name: String = this.name,
        fileName: String = this.fileName,
        uri: String = this.uri,
        size: Size = this.size
    ) = SampleImage(name, fileName, uri, size)

    val type: String by lazy {
        when {
            uri.startsWith("http") -> "Http"
            uri.startsWith("asset") -> "Asset"
            uri.startsWith("file") -> "File"
            uri.startsWith("content") -> "Content"
            uri.startsWith("android.resource") -> "Res"
            else -> "Unknown"
        }
    }
}