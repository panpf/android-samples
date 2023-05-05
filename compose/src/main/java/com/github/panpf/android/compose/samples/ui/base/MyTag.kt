package com.github.panpf.android.compose.samples.ui.base

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedAssistChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun HorizontalTag(text: String, modifier: Modifier = Modifier) {
    Surface(
        shape = RoundedCornerShape(50),
        color = MaterialTheme.colorScheme.surface,
        tonalElevation = 1.dp,
        shadowElevation = 1.dp
    ) {
        Text(
            text = text,
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 6.dp)
                .then(modifier),
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
fun VerticalTag(text: String, modifier: Modifier = Modifier) {
    Surface(
        shape = RoundedCornerShape(50),
        color = MaterialTheme.colorScheme.surface,
        tonalElevation = 1.dp,
        shadowElevation = 1.dp
    ) {
        Text(
            text = text,
            modifier = Modifier
                .padding(horizontal = 4.dp, vertical = 12.dp)
                .then(modifier),
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun HorizontalTagPreview() {
    Row(
        Modifier
            .fillMaxWidth()
            .height(200.dp)
    ) {
        HorizontalTag(text = "游戏")
        HorizontalTag(text = "数码")
        HorizontalTag(text = "汽车")
        ElevatedAssistChip(onClick = { }, label = { Text(text = "动漫") })
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun VerticalTagPreview() {
    Row(
        Modifier
            .fillMaxWidth()
            .height(200.dp)
    ) {
        VerticalTag(text = "游戏")
        VerticalTag(text = "数码")
        VerticalTag(text = "汽车")
        VerticalTag(text = "动\n漫")
        VerticalTag(text = "摄\n影")
        VerticalTag(text = "舞\n蹈")
    }
}