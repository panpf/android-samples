package com.github.panpf.android.compose.samples.ui.material

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.panpf.android.compose.samples.ui.base.MaterialComposeAppBarFragment
import com.github.panpf.android.compose.samples.ui.base.MyColor
import com.google.accompanist.flowlayout.FlowRow

class CardFragment : MaterialComposeAppBarFragment() {

    override fun getTitle(): String {
        return "Card - Material"
    }

    @Composable
    override fun DrawContent() {
        CardSample()
    }
}


@Composable
private fun CardSample() {
    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        mainAxisSpacing = 20.dp,
        crossAxisSpacing = 20.dp,
    ) {
        Column(modifier = Modifier.fillMaxWidth(0.45f)) {
            Text(text = "Default")
            Spacer(modifier = Modifier.size(10.dp))
            Card(modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)) {
                Box(modifier = Modifier.fillMaxSize()) {
                    Text(text = "This is a card", modifier = Modifier.align(Alignment.Center))
                }
            }
        }

        Column(modifier = Modifier.fillMaxWidth(0.45f)) {
            Text(text = "shape")
            Spacer(modifier = Modifier.size(10.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f),
                shape = CircleShape
            ) {
                Box(modifier = Modifier.fillMaxSize()) {
                    Text(text = "This is a card", modifier = Modifier.align(Alignment.Center))
                }
            }
        }

        Column(modifier = Modifier.fillMaxWidth(0.45f)) {
            Text(text = "backgroundColor")
            Spacer(modifier = Modifier.size(10.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f),
                backgroundColor = MyColor.TranslucenceRed,
            ) {
                Box(modifier = Modifier.fillMaxSize()) {
                    Text(text = "This is a card", modifier = Modifier.align(Alignment.Center))
                }
            }
        }

        Column(modifier = Modifier.fillMaxWidth(0.45f)) {
            Text(text = "contentColor")
            Spacer(modifier = Modifier.size(10.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f),
                contentColor = Color.Red,
            ) {
                Box(modifier = Modifier.fillMaxSize()) {
                    Text(text = "This is a card", modifier = Modifier.align(Alignment.Center))
                }
            }
        }

        Column(modifier = Modifier.fillMaxWidth(0.45f)) {
            Text(text = "elevation")
            Spacer(modifier = Modifier.size(10.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f),
                elevation = 10.dp
            ) {
                Box(modifier = Modifier.fillMaxSize()) {
                    Text(text = "This is a card", modifier = Modifier.align(Alignment.Center))
                }
            }
        }

        Column(modifier = Modifier.fillMaxWidth(0.45f)) {
            Text(text = "border")
            Spacer(modifier = Modifier.size(10.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f),
                border = BorderStroke(2.dp, Color.Red)
            ) {
                Box(modifier = Modifier.fillMaxSize()) {
                    Text(text = "This is a card", modifier = Modifier.align(Alignment.Center))
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun CardSamplePreview() {
    CardSample()
}