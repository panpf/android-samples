package com.github.panpf.android.compose.samples.ui.base.list

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.panpf.android.compose.samples.ui.base.theme.MyTheme3

@Composable
fun MyDivider() {
    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = ListItemPaddingStart, end = ListItemPaddingStart),
    )
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun MyDividerPreview() {
    MyTheme3 {
        Surface(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 100.dp, bottom = 100.dp)) {
            MyDivider()
        }
    }
}