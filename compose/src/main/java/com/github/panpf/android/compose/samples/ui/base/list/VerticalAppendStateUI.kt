package com.github.panpf.android.compose.samples.ui.base.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import com.github.panpf.android.compose.samples.R

@Composable
fun VerticalAppendStateUI(loadState: LoadState, onClick: () -> Unit) {
    val message: String
    var click: (() -> Unit)? = null
    when (loadState) {
        is LoadState.Loading -> {
            message = stringResource(R.string.text_loading)
        }
        is LoadState.Error -> {
            message = stringResource(R.string.text_load_error)
            click = onClick
        }
        is LoadState.NotLoading -> {
            message = if (loadState.endOfPaginationReached) {
                stringResource(R.string.text_load_end)
            } else {
                stringResource(R.string.text_loading)
            }
        }
        else -> {
            message = "Unknown"
        }
    }
    Box(modifier = Modifier
        .fillMaxHeight()
        .padding(20.dp)
        .clickable { click?.invoke() }
    ) {
        Text(
            text = message,
            modifier = Modifier.align(Alignment.Center),
            fontSize = 12.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun VerticalAppendStateUINotLoadingTruePreview() {
    VerticalAppendStateUI(LoadState.NotLoading(true)) {

    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun VerticalAppendStateUINotLoadingFalsePreview() {
    VerticalAppendStateUI(LoadState.NotLoading(false)) {

    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun VerticalAppendStateUILoadingPreview() {
    VerticalAppendStateUI(LoadState.Loading) {

    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun VerticalAppendStateUIErrorPreview() {
    VerticalAppendStateUI(LoadState.Error(Exception("from preview"))) {

    }
}