package com.github.panpf.android.compose.samples.ui.material

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Snackbar
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.panpf.android.compose.samples.ui.base.MaterialComposeAppBarFragment
import com.github.panpf.tools4a.toast.ktx.showShortToast

class SnackbarsFragment : MaterialComposeAppBarFragment() {

    override fun getTitle(): String {
        return "Snackbars - Material"
    }

    @Composable
    override fun DrawContent() {
        SnackbarSample()
    }
}


@Composable
private fun SnackbarSample() {
    val context = LocalContext.current
    Column(
        Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
    ) {
        Text(text = "Default")
        Spacer(modifier = Modifier.size(10.dp))
        Snackbar {
            Text(text = "已发布")
        }

        Spacer(modifier = Modifier.size(20.dp))
        Text(text = "shape")
        Spacer(modifier = Modifier.size(10.dp))
        Snackbar(
            shape = RoundedCornerShape(50)
        ) {
            Text(text = "已发布")
        }

        Spacer(modifier = Modifier.size(20.dp))
        Text(text = "backgroundColor")
        Spacer(modifier = Modifier.size(10.dp))
        Snackbar(
            backgroundColor = Color.Red
        ) {
            Text(text = "已发布")
        }

        Spacer(modifier = Modifier.size(20.dp))
        Text(text = "contentColor")
        Spacer(modifier = Modifier.size(10.dp))
        Snackbar(
            contentColor = Color.Red
        ) {
            Text(text = "已发布")
        }

        Spacer(modifier = Modifier.size(20.dp))
        Text(text = "action")
        Spacer(modifier = Modifier.size(10.dp))
        Snackbar(
            action = {
                Text(
                    text = "撤销",
                    modifier = Modifier.clickable { context.showShortToast("撤销") })
            }
        ) {
            Text(text = "已发布")
        }

        Spacer(modifier = Modifier.size(20.dp))
        Text(text = "actionOnNewLine")
        Spacer(modifier = Modifier.size(10.dp))
        Snackbar(
            action = {
                Text(
                    text = "撤销",
                    modifier = Modifier.clickable { context.showShortToast("撤销") })
            },
            actionOnNewLine = true
        ) {
            Text(text = "已发布")
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun SnackbarSamplePreview() {
    SnackbarSample()
}