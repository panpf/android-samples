package com.github.panpf.android.compose.samples.ui.widgets.button

import androidx.compose.foundation.Image
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.github.panpf.android.compose.samples.R
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.tools4a.toast.ktx.showLongToast

@Composable
fun ButtonUI() {
    val context = LocalContext.current
    val onClick: (() -> Unit) = {
        context.showLongToast("你点了我！")
    }

    ExpandableLayout { allExpandFlow ->
        ExpandableItem("文字按钮", allExpandFlow) {
            Button(onClick = onClick) {
                Text(text = "按钮2")
            }
        }

        ExpandableItem("图片按钮", allExpandFlow) {
            Button(onClick = onClick) {
                Image(
                    painterResource(id = R.drawable.ic_expand_more),
                    contentDescription = "",
                    colorFilter = ColorFilter.tint(Color.White)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ButtonUIPreview() {
    ButtonUI()
}