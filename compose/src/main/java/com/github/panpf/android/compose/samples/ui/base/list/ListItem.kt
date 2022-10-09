package com.github.panpf.android.compose.samples.ui.base.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun BoxListItem(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier.then(
            Modifier
                .clickable { onClick?.invoke() }
                .padding(
                    start = ListItemPaddingStart,
                    top = ListItemPaddingTop,
                    end = ListItemPaddingStart,
                    bottom = ListItemPaddingTop
                )
        )
    ) {
        content()
    }
}

@Composable
fun ColumnListItem(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = modifier.then(
            Modifier
                .clickable { onClick?.invoke() }
                .padding(
                    start = ListItemPaddingStart,
                    top = ListItemPaddingTop,
                    end = ListItemPaddingStart,
                    bottom = ListItemPaddingTop
                )
        )
    ) {
        content()
    }
}

@Composable
fun RowListItem(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    content: @Composable RowScope.() -> Unit
) {
    Row(
        modifier = modifier.then(
            Modifier
                .clickable { onClick?.invoke() }
                .padding(
                    start = ListItemPaddingStart,
                    top = ListItemPaddingTop,
                    end = ListItemPaddingStart,
                    bottom = ListItemPaddingTop
                )
        )
    ) {
        content()
    }
}