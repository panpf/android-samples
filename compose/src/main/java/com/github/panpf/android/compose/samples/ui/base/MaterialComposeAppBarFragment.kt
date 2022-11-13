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
package com.github.panpf.android.compose.samples.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.Fragment
import com.github.panpf.android.compose.samples.R
import com.github.panpf.android.compose.samples.ui.base.theme.MyTheme

abstract class MaterialComposeAppBarFragment : Fragment() {

    final override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(inflater.context).apply {
            setContent {
                MyTopAppBarScaffold(getTitle()) {
                    DrawContent()
                }
            }
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        }
    }

    open fun getTitle(): String {
        return requireContext().resources.getString(R.string.app_name)
    }

    @Composable
    abstract fun DrawContent()
}


@Composable
fun MyTopAppBarScaffold(title: String? = null, content: @Composable () -> Unit) {
    val context = LocalContext.current
    val finalTitle = remember {
        title ?: context.resources.getString(R.string.app_name)
    }
//    val statusBarHeight = remember {
//        context.getStatusBarHeight().px2dp.dp
//    }
    MyTheme {
        Surface {
            Column(
                modifier = Modifier.fillMaxSize()
//                .padding(top = statusBarHeight)
            ) {
                TopAppBar(title = {
                    Text(text = finalTitle)
                })
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    content()
                }
            }
        }
    }
}

@Preview
@Composable
fun MyTopAppBarScaffoldPreview() {
    MyTopAppBarScaffold {

    }
}