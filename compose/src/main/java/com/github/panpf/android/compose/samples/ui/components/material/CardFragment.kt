package com.github.panpf.android.compose.samples.ui.components.material

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.MaterialComposeAppBarFragment
import com.github.panpf.android.compose.samples.ui.base.MyColor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class CardFragment : MaterialComposeAppBarFragment() {

    override fun getTitle(): String {
        return "Card - Material"
    }

    @Composable
    override fun DrawContent() {
        ExpandableLayout { allExpandFlow ->
            CardSample(allExpandFlow)
            CardShapeSample(allExpandFlow)
            CardColorsSample(allExpandFlow)
            CardElevationSample(allExpandFlow)
            CardBorderSample(allExpandFlow)
        }
    }
}


@Composable
private fun CardSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem(title = "Card", allExpandFlow, padding = 20.dp) {
        Card(modifier = Modifier.size(160.dp)) {
            Box(modifier = Modifier.fillMaxSize()) {
                Text(text = "This is a card", modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun CardSamplePreview() {
    CardSample(remember { MutableStateFlow(true) })
}


@Composable
private fun CardShapeSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem(title = "Card（shape）", allExpandFlow, padding = 20.dp) {
        Card(
            modifier = Modifier.size(160.dp),
            shape = CircleShape
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                Text(text = "This is a card", modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun CardShapeSamplePreview() {
    CardShapeSample(remember { MutableStateFlow(true) })
}


@Composable
private fun CardColorsSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem(title = "Card（colors）", allExpandFlow, padding = 20.dp) {
        Card(
            modifier = Modifier.size(160.dp),
            backgroundColor = MyColor.TranslucenceRed,
            contentColor = Color.White,
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                Text(text = "This is a card", modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun CardColorsSamplePreview() {
    CardColorsSample(remember { MutableStateFlow(true) })
}


@Composable
private fun CardElevationSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem(title = "Card（elevation）", allExpandFlow, padding = 20.dp) {
        Card(
            modifier = Modifier.size(160.dp),
            elevation = 10.dp
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                Text(text = "This is a card", modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun CardElevationSamplePreview() {
    CardElevationSample(remember { MutableStateFlow(true) })
}


@Composable
private fun CardBorderSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem(title = "Card（border）", allExpandFlow, padding = 20.dp) {
        Card(
            modifier = Modifier.size(160.dp),
            border = BorderStroke(2.dp, Color.Red)
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                Text(text = "This is a card", modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun CardBorderSamplePreview() {
    CardBorderSample(remember { MutableStateFlow(true) })
}