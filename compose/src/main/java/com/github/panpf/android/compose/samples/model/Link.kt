package com.github.panpf.android.compose.samples.model

import androidx.navigation.NavDirections

data class Link(val title: String, val nav: Any? = null) {
    constructor(title: String, nav: Int) : this(title, nav as Any?)
    constructor(title: String, nav: NavDirections) : this(title, nav as Any?)
}