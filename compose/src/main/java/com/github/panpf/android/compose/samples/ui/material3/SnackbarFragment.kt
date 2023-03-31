package com.github.panpf.android.compose.samples.ui.material3

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
import com.github.panpf.tools4a.toast.ktx.showShortToast

class SnackbarFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "Snackbar - Material3"
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
        Text(text = "containerColor")
        Spacer(modifier = Modifier.size(10.dp))
        Snackbar(
            containerColor = Color.Red
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