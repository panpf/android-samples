package com.github.panpf.android.compose.samples.ui.base.list

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.panpf.android.compose.samples.ui.base.theme.MyTheme

@Composable
fun MyDivider() {
    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = ListItemPaddingStart, end = ListItemPaddingStart),
        thickness = 0.5.dp
    )
}

@Preview(showBackground = true)
@Composable
fun MyDividerPreview() {
    MyTheme {
        Surface(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 100.dp, bottom = 100.dp)) {
            MyDivider()
        }
    }
}