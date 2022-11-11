package com.github.panpf.android.compose.samples.ui.base

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.github.panpf.android.compose.samples.R
import com.github.panpf.android.compose.samples.ui.base.theme.MyTheme

@Composable
fun MyTopAppBarScaffold(title: String? = null, content: @Composable () -> Unit) {
    val context = LocalContext.current
    val finalTitle = remember {
        title ?: context.resources.getString(R.string.app_name)
    }
    MyTheme {
        Surface {
            Column(modifier = Modifier.fillMaxSize()) {
                TopAppBar(title = {
                    Text(text = finalTitle)
                })
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    content()
                }
            }
        }
    }
}

@Preview
@Composable
fun MyTopAppBarScaffoldPreview() {
    MyTopAppBarScaffold {

    }
}