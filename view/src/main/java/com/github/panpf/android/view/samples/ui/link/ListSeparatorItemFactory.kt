/*
 *  2021 panpf <panpfpanpf@outlook.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/*
 * Copyright (C) 2022 panpf <panpfpanpf@outlook.com>
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
package com.github.panpf.android.view.samples.ui.link

import android.content.Context
import com.github.panpf.android.view.samples.model.ListSeparator
import com.github.panpf.android.view.samples.ui.base.MyBindingItemFactory
import com.github.panpf.android.view.samples.databinding.ListSeparatorItemBinding

class ListSeparatorItemFactory :
    MyBindingItemFactory<ListSeparator, ListSeparatorItemBinding>(ListSeparator::class) {

    override fun initItem(
        context: Context,
        binding: ListSeparatorItemBinding,
        item: BindingItem<ListSeparator, ListSeparatorItemBinding>
    ) {
    }

    override fun bindItemData(
        context: Context,
        binding: ListSeparatorItemBinding,
        item: BindingItem<ListSeparator, ListSeparatorItemBinding>,
        bindingAdapterPosition: Int,
        absoluteAdapterPosition: Int,
        data: ListSeparator
    ) {
        binding.listSeparatorItemTitleText.text = data.title
    }
}
