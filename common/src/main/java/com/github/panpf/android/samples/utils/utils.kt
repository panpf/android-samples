package com.github.panpf.android.samples.utils


inline fun <R> ifOrNull(value: Boolean, block: () -> R?): R? = if (value) block() else null