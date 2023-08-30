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

package com.github.panpf.android.samples.photos

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn

class PhotoAlbumViewModel(application: Application) : AndroidViewModel(application) {
    val pagingFlow = Pager(
        config = PagingConfig(
            pageSize = 60,
            enablePlaceholders = false,
        ),
        initialKey = 0,
        pagingSourceFactory = {
            PhotoAlbumPagingSource(application)
        }
    ).flow.cachedIn(viewModelScope)
}