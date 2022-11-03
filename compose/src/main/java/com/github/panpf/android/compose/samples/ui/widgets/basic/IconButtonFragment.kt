package com.github.panpf.android.compose.samples.ui.widgets.basic

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.panpf.android.compose.samples.R
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.ToolbarFragment
import com.github.panpf.android.compose.samples.ui.base.theme.MyTheme
import com.github.panpf.tools4a.toast.ktx.showShortToast
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class IconButtonFragment : ToolbarFragment() {

    override fun createView(
        toolbar: Toolbar,
        inflater: LayoutInflater,
        parent: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        toolbar.title = "IconButton"
        return ComposeView(inflater.context).apply {
            setContent {
                MyTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        ExpandableLayout { allExpandFlow ->
                            IconButtonSample(allExpandFlow)
                            FilledIconButtonSample(allExpandFlow)
                            FilledTonalIconButtonSample(allExpandFlow)
                            OutlinedIconButtonSample(allExpandFlow)
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun IconButtonSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem("IconButton", allExpandFlow, padding = 20.dp) {
        IconButton(onClick = { context.showShortToast("你点了我！") }) {
            Image(
                painterResource(id = R.drawable.ic_arrow_down),
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
    ExpandableItem("FilledIconButton", allExpandFlow, padding = 20.dp) {
        FilledIconButton(onClick = { context.showShortToast("你点了我！") }) {
            Image(
                painterResource(id = R.drawable.ic_arrow_down),
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
    ExpandableItem("FilledTonalIconButton", allExpandFlow, padding = 20.dp) {
        FilledTonalIconButton(onClick = { context.showShortToast("你点了我！") }) {
            Image(
                painterResource(id = R.drawable.ic_arrow_down),
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
    ExpandableItem("OutlinedIconButton", allExpandFlow, padding = 20.dp) {
        OutlinedIconButton(onClick = { context.showShortToast("你点了我！") }) {
            Image(
                painterResource(id = R.drawable.ic_arrow_down),
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