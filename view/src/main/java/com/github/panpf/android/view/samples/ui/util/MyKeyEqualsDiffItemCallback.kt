package com.github.panpf.android.view.samples.ui.util

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil

class MyKeyEqualsDiffItemCallback<DATA : Any>(
    val getKey: (DATA) -> Any?
) : DiffUtil.ItemCallback<DATA>() {

    override fun areItemsTheSame(oldItem: DATA, newItem: DATA): Boolean {
        val oldKey = getKey(oldItem)
        val newKey = getKey(newItem)
        return oldKey == newKey
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: DATA, newItem: DATA): Boolean {
        return oldItem == newItem
    }
}