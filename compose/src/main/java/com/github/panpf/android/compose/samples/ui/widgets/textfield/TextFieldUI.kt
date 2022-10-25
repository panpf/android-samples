package com.github.panpf.android.compose.samples.ui.widgets.textfield

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout

@Composable
fun TextFieldUI() {
    ExpandableLayout { allExpandFlow ->
        DefaultTextSample()

    }
}

//@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
//@Composable
//fun TextUIPreview() {
//    TextUI()
//}


@Composable
fun DefaultTextSample() {
//    TextField(
//        value = "",
//        onValueChange = {
//
//        }
//    )
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun DefaultTextSamplePreview() {
    DefaultTextSample()
}