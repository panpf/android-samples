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

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment

@Suppress("MemberVisibilityCanBePrivate")
abstract class ComposeFragment : Fragment() {

    protected var toolbar: Toolbar? = null

    final override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = onCreateComposeView(inflater.context).apply {
        layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
    }

    abstract fun onCreateComposeView(context: Context): ComposeView
//
//    @SuppressLint("ObsoleteSdkInt")
//    private fun setTransparentStatusBar(toolbar: Toolbar) {
//        val window = requireActivity().window
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
//            && window.decorView.systemUiVisibility == View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//        ) {
//            toolbar.updateLayoutParams<ViewGroup.MarginLayoutParams> {
//                topMargin += requireContext().getStatusBarHeight()
//            }
//        }
//    }
//
//    protected abstract fun createView(
//        toolbar: Toolbar,
//        inflater: LayoutInflater,
//        parent: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View
//
//    final override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        onViewCreated(this.toolbar!!, savedInstanceState)
//    }
//
//    protected open fun onViewCreated(
//        toolbar: Toolbar,
//        savedInstanceState: Bundle?
//    ) {
//
//    }
//
//    override fun onDestroyView() {
//        this.toolbar = null
//        super.onDestroyView()
//    }
}