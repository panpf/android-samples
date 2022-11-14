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
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import com.github.panpf.android.compose.samples.R
import com.github.panpf.android.compose.samples.ui.base.theme.MyTheme
import com.github.panpf.tools4a.dimen.ktx.px2dp
import com.github.panpf.tools4a.display.ktx.getStatusBarHeight

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
    val statusBarHeight = remember {
        context.getStatusBarHeight().px2dp.dp
    }
    MyTheme {
        Surface {
            val colors = MaterialTheme.colors
            Box(modifier = Modifier.fillMaxSize()) {
                Column(modifier = Modifier.fillMaxSize()) {
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(statusBarHeight)
                            .background(colors.primary)
                    )
                    TopAppBar(
                        title = {
                            Text(text = finalTitle)
                        },
                        backgroundColor = colors.primary,
                        contentColor = colors.onPrimary
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    ) {
                        content()
                    }
                }
                // 为何多次一举，不卸载 TopAppBar 前面？
                // 因为 Material 版本的 TopAppBar 顶部有一条黑色的线，Column 排列的话无法隐藏这条线
                // 只能这样将状态栏背景放在 TopAppBar 上面盖住那条线
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(statusBarHeight)
                        .background(colors.primary)
                )
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