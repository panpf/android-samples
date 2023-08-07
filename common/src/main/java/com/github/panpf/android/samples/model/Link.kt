package com.github.panpf.android.samples.model

import androidx.navigation.NavDirections

data class Link(
    val title: String,
    val nav: Any? = null,
    val minSdk: Int? = null,
    val permissions: List<String>? = null
) {
    constructor(
        title: String,
        nav: Int,
        minSdk: Int? = null,
        permissions: List<String>? = null
    ) : this(title, nav as Any?, minSdk, permissions)

    constructor(
        title: String,
        nav: NavDirections,
        minSdk: Int? = null,
        permissions: List<String>? = null
    ) : this(title, nav as Any?, minSdk, permissions)
}

data class LinkGroup(val title: String, val links: List<Link>)