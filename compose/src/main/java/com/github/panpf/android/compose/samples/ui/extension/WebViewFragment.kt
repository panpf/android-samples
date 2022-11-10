package com.github.panpf.android.compose.samples.ui.extension

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import com.github.panpf.android.compose.samples.ui.base.ToolbarFragment
import com.github.panpf.android.compose.samples.ui.base.theme.MyTheme3
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewState

class WebViewFragment : ToolbarFragment() {

    override fun createView(
        toolbar: Toolbar,
        inflater: LayoutInflater,
        parent: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        toolbar.title = "WebView"
        return ComposeView(inflater.context).apply {
            setContent {
                MyTheme3 {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        WebViewSample()
                    }
                }
            }
        }
    }
}

@Composable
fun WebViewSample() {
    val state = rememberWebViewState("https://bilibili.com")
    WebView(state)
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun WebViewSamplePreview() {
    WebViewSample()
}