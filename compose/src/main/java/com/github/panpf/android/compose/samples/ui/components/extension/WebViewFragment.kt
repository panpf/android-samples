package com.github.panpf.android.compose.samples.ui.components.extension

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewState

class WebViewFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "WebView"
    }

    @Composable
    override fun DrawContent() {
        WebViewSample()
    }
}

@Composable
private fun WebViewSample() {
    val state = rememberWebViewState("https://bilibili.com")
    WebView(state)
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun WebViewSamplePreview() {
    WebViewSample()
}