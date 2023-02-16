package com.github.panpf.android.compose.samples.ui.base

import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SubtitleText(
    text: String,
    line: Int = 2
) {
    val lineHeight = 15
    Text(
        text = text,
        modifier = Modifier.height(((lineHeight * line) + 5).dp),
        lineHeight = lineHeight.sp,
        fontSize = (lineHeight - 2).sp
    )
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun SubtitleTextPreview() {
    SubtitleText(text = "躲过了暴风雪之后，我们再次起程赶路")
}