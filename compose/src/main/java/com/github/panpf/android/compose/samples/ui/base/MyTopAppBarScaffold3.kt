package com.github.panpf.android.compose.samples.ui.base

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.github.panpf.android.compose.samples.R
import com.github.panpf.android.compose.samples.ui.base.theme.MyTheme3

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBarScaffold3(title: String? = null, content: @Composable () -> Unit) {
    val context = LocalContext.current
    val finalTitle = remember {
        title ?: context.resources.getString(R.string.app_name)
    }
    MyTheme3 {
        Surface {
            Column(modifier = Modifier.fillMaxSize()) {
                val colorScheme = MaterialTheme.colorScheme
                TopAppBar(
                    title = {
                        Text(text = finalTitle)
                    },
                    colors = TopAppBarDefaults.smallTopAppBarColors(
                        containerColor = colorScheme.primary,
                        scrolledContainerColor = colorScheme.primary,
                        navigationIconContentColor = colorScheme.onPrimary,
                        titleContentColor = colorScheme.onPrimary,
                        actionIconContentColor = colorScheme.onPrimary,
                    )
                )
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
fun MyTopAppBarScaffold3Preview() {
    MyTopAppBarScaffold3 {

    }
}