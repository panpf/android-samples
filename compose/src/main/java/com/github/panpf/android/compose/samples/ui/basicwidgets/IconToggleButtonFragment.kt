package com.github.panpf.android.compose.samples.ui.basicwidgets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.FilledIconToggleButton
import androidx.compose.material3.FilledTonalIconToggleButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconToggleButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.panpf.android.compose.samples.R
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.ToolbarFragment
import com.github.panpf.android.compose.samples.ui.base.theme.MyTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class IconToggleButtonFragment : ToolbarFragment() {

    override fun createView(
        toolbar: Toolbar,
        inflater: LayoutInflater,
        parent: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        toolbar.title = "IconToggleButton"
        return ComposeView(inflater.context).apply {
            setContent {
                MyTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        ExpandableLayout { allExpandFlow ->
                            IconToggleButtonSample(allExpandFlow)
                            FilledIconToggleButtonSample(allExpandFlow)
                            FilledTonalIconToggleButtonSample(allExpandFlow)
                            OutlinedIconToggleButtonSample(allExpandFlow)
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun IconToggleButtonSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem("IconToggleButton", allExpandFlow, padding = 20.dp) {
        val checked = remember { mutableStateOf(false) }
        IconToggleButton(
            checked = checked.value,
            onCheckedChange = { checked.value = it }
        ) {
            val iconResId =
                if (checked.value) R.drawable.ic_expand_less else R.drawable.ic_expand_more
            Image(
                painterResource(id = iconResId),
                contentDescription = "",
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun IconToggleButtonSamplePreview() {
    IconToggleButtonSample(remember { MutableStateFlow(true) })
}


@Composable
fun FilledIconToggleButtonSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem("FilledIconToggleButton", allExpandFlow, padding = 20.dp) {
        val checked = remember { mutableStateOf(false) }
        FilledIconToggleButton(
            checked = checked.value,
            onCheckedChange = { checked.value = it }
        ) {
            val iconResId =
                if (checked.value) R.drawable.ic_expand_less else R.drawable.ic_expand_more
            Image(
                painterResource(id = iconResId),
                contentDescription = "",
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun FilledIconToggleButtonSamplePreview() {
    FilledIconToggleButtonSample(remember { MutableStateFlow(true) })
}


@Composable
fun FilledTonalIconToggleButtonSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem("FilledTonalIconToggleButton", allExpandFlow, padding = 20.dp) {
        val checked = remember { mutableStateOf(false) }
        FilledTonalIconToggleButton(
            checked = checked.value,
            onCheckedChange = { checked.value = it }
        ) {
            val iconResId =
                if (checked.value) R.drawable.ic_expand_less else R.drawable.ic_expand_more
            Image(
                painterResource(id = iconResId),
                contentDescription = "",
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun FilledTonalIconToggleButtonSamplePreview() {
    FilledTonalIconToggleButtonSample(remember { MutableStateFlow(true) })
}


@Composable
fun OutlinedIconToggleButtonSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem("OutlinedIconToggleButton", allExpandFlow, padding = 20.dp) {
        val checked = remember { mutableStateOf(false) }
        OutlinedIconToggleButton(
            checked = checked.value,
            onCheckedChange = { checked.value = it }
        ) {
            val iconResId =
                if (checked.value) R.drawable.ic_expand_less else R.drawable.ic_expand_more
            Image(
                painterResource(id = iconResId),
                contentDescription = "",
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun OutlinedIconToggleButtonSamplePreview() {
    OutlinedIconToggleButtonSample(remember { MutableStateFlow(true) })
}