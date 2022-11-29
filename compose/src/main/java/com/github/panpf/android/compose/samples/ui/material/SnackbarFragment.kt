package com.github.panpf.android.compose.samples.ui.material

import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Snackbar
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.MaterialComposeAppBarFragment
import com.github.panpf.tools4a.toast.ktx.showShortToast
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class SnackbarFragment : MaterialComposeAppBarFragment() {

    override fun getTitle(): String {
        return "Snackbar - Material"
    }

    @Composable
    override fun DrawContent() {
        ExpandableLayout { allExpandFlow ->
            SnackbarSample(allExpandFlow)
            SnackbarActionSample(allExpandFlow)
            SnackbarActionOnNewLineSample(allExpandFlow)
            SnackbarShapeSample(allExpandFlow)
            SnackbarBackgroundColorSample(allExpandFlow)
            SnackbarContentColorSample(allExpandFlow)
        }
    }
}


@Composable
private fun SnackbarSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem(title = "Snackbar", allExpandFlow, padding = 20.dp) {
        Snackbar {
            Text(text = "已发布")
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun SnackbarSamplePreview() {
    SnackbarSample(remember { MutableStateFlow(true) })
}


@Composable
private fun SnackbarActionSample(allExpandFlow: Flow<Boolean>) {
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
private fun SnackbarActionSamplePreview() {
    SnackbarActionSample(remember { MutableStateFlow(true) })
}


@Composable
private fun SnackbarActionOnNewLineSample(allExpandFlow: Flow<Boolean>) {
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
private fun SnackbarActionOnNewLineSamplePreview() {
    SnackbarActionOnNewLineSample(remember { MutableStateFlow(true) })
}


@Composable
private fun SnackbarShapeSample(allExpandFlow: Flow<Boolean>) {
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
private fun SnackbarShapeSamplePreview() {
    SnackbarShapeSample(remember { MutableStateFlow(true) })
}


@Composable
private fun SnackbarBackgroundColorSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem(title = "Snackbar（backgroundColor）", allExpandFlow, padding = 20.dp) {
        Snackbar(
            backgroundColor = Color.Red
        ) {
            Text(text = "已发布")
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun SnackbarBackgroundColorSamplePreview() {
    SnackbarBackgroundColorSample(remember { MutableStateFlow(true) })
}


@Composable
private fun SnackbarContentColorSample(allExpandFlow: Flow<Boolean>) {
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
private fun SnackbarContentColorSamplePreview() {
    SnackbarContentColorSample(remember { MutableStateFlow(true) })
}