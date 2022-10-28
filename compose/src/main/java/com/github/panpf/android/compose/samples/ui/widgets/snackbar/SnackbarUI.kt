package com.github.panpf.android.compose.samples.ui.widgets.snackbar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.tools4a.toast.ktx.showShortToast
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun SnackbarUI() {
    ExpandableLayout { allExpandFlow ->
        SnackbarSample(allExpandFlow)
        SnackbarActionSample(allExpandFlow)
        SnackbarActionOnNewLineSample(allExpandFlow)
        SnackbarShapeSample(allExpandFlow)
        SnackbarContainerColorSample(allExpandFlow)
        SnackbarContentColorSample(allExpandFlow)
    }
}


@Composable
fun SnackbarSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem(title = "Snackbar", allExpandFlow, padding = 20.dp) {
        Snackbar {
            Text(text = "已发布")
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun SnackbarSamplePreview() {
    SnackbarSample(remember { MutableStateFlow(true) })
}


@Composable
fun SnackbarActionSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem(title = "Snackbar（action）", allExpandFlow, padding = 20.dp) {
        Snackbar(
            action = {
                Text(
                    text = "撤销",
                    modifier = Modifier.clickable { context.showShortToast("撤销") })
            }
        ) {
            Text(text = "已发布")
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun SnackbarActionSamplePreview() {
    SnackbarActionSample(remember { MutableStateFlow(true) })
}


@Composable
fun SnackbarActionOnNewLineSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem(title = "Snackbar（actionOnNewLine）", allExpandFlow, padding = 20.dp) {
        Snackbar(
            action = {
                Text(
                    text = "撤销",
                    modifier = Modifier.clickable { context.showShortToast("撤销") })
            },
            actionOnNewLine = true
        ) {
            Text(text = "已发布")
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun SnackbarActionOnNewLineSamplePreview() {
    SnackbarActionOnNewLineSample(remember { MutableStateFlow(true) })
}


@Composable
fun SnackbarShapeSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem(title = "Snackbar（shape）", allExpandFlow, padding = 20.dp) {
        Snackbar(
            shape = RoundedCornerShape(50)
        ) {
            Text(text = "已发布")
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun SnackbarShapeSamplePreview() {
    SnackbarShapeSample(remember { MutableStateFlow(true) })
}


@Composable
fun SnackbarContainerColorSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem(title = "Snackbar（containerColor）", allExpandFlow, padding = 20.dp) {
        Snackbar(
            containerColor = Color.Red
        ) {
            Text(text = "已发布")
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun SnackbarContainerColorSamplePreview() {
    SnackbarContainerColorSample(remember { MutableStateFlow(true) })
}


@Composable
fun SnackbarContentColorSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem(title = "Snackbar（contentColor）", allExpandFlow, padding = 20.dp) {
        Snackbar(
            contentColor = Color.Red
        ) {
            Text(text = "已发布")
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun SnackbarContentColorSamplePreview() {
    SnackbarContentColorSample(remember { MutableStateFlow(true) })
}