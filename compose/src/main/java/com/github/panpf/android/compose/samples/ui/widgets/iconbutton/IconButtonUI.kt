package com.github.panpf.android.compose.samples.ui.widgets.iconbutton

import androidx.compose.foundation.Image
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.github.panpf.android.compose.samples.R
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.tools4a.toast.ktx.showLongToast
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun IconButtonUI() {
    ExpandableLayout { allExpandFlow ->
        IconButtonSample(allExpandFlow)
        FilledIconButtonSample(allExpandFlow)
        FilledTonalIconButtonSample(allExpandFlow)
        OutlinedIconButtonSample(allExpandFlow)
    }
}


@Composable
fun IconButtonSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem("IconButton", allExpandFlow) {
        IconButton(onClick = { context.showLongToast("你点了我！") }) {
            Image(
                painterResource(id = R.drawable.ic_expand_more),
                contentDescription = "",
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun IconButtonSamplePreview() {
    IconButtonSample(remember { MutableStateFlow(true) })
}


@Composable
fun FilledIconButtonSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem("FilledIconButton", allExpandFlow) {
        FilledIconButton(onClick = { context.showLongToast("你点了我！") }) {
            Image(
                painterResource(id = R.drawable.ic_expand_more),
                contentDescription = "",
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun FilledIconButtonSamplePreview() {
    FilledIconButtonSample(remember { MutableStateFlow(true) })
}


@Composable
fun FilledTonalIconButtonSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem("FilledTonalIconButton", allExpandFlow) {
        FilledTonalIconButton(onClick = { context.showLongToast("你点了我！") }) {
            Image(
                painterResource(id = R.drawable.ic_expand_more),
                contentDescription = "",
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun FilledTonalIconButtonSamplePreview() {
    FilledTonalIconButtonSample(remember { MutableStateFlow(true) })
}


@Composable
fun OutlinedIconButtonSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem("OutlinedIconButton", allExpandFlow) {
        OutlinedIconButton(onClick = { context.showLongToast("你点了我！") }) {
            Image(
                painterResource(id = R.drawable.ic_expand_more),
                contentDescription = "",
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun OutlinedIconButtonSamplePreview() {
    OutlinedIconButtonSample(remember { MutableStateFlow(true) })
}