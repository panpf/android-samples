package com.github.panpf.android.compose.samples.ui.material

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.panpf.android.compose.samples.ui.base.MaterialComposeAppBarFragment

class DividerFragment : MaterialComposeAppBarFragment() {

    override fun getTitle(): String {
        return "Divider - Material"
    }

    @Composable
    override fun DrawContent() {
        DividerSample()
    }
}


@Composable
private fun DividerSample() {
    Column(
        Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
    ) {
        Text(text = "Default")
        Spacer(modifier = Modifier.size(10.dp))
        Divider()

        Spacer(modifier = Modifier.size(20.dp))
        Text(text = "color")
        Spacer(modifier = Modifier.size(10.dp))
        Divider(color = Color.Red)

        Spacer(modifier = Modifier.size(20.dp))
        Text(text = "thickness=5.dp")
        Spacer(modifier = Modifier.size(10.dp))
        Divider(thickness = 5.dp)

        Spacer(modifier = Modifier.size(20.dp))
        Text(text = "startIndent=20.dp")
        Spacer(modifier = Modifier.size(10.dp))
        Divider(startIndent = 20.dp)
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun DividerSamplePreview() {
    DividerSample()
}