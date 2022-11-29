package com.github.panpf.android.compose.samples.ui.material3

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem3
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
import com.github.panpf.android.compose.samples.ui.base.MyColor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class CardFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "Card - Material3"
    }

    @Composable
    override fun DrawContent() {
        ExpandableLayout { allExpandFlow ->
            CardSample(allExpandFlow)
            CardShapeSample(allExpandFlow)
            CardColorsSample(allExpandFlow)
            CardElevationSample(allExpandFlow)
            CardBorderSample(allExpandFlow)
            ElevatedCardSample(allExpandFlow)
            OutlinedCardSample(allExpandFlow)
        }
    }
}


@Composable
private fun CardSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "Card", allExpandFlow, padding = 20.dp) {
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
    ExpandableItem3(title = "Card（shape）", allExpandFlow, padding = 20.dp) {
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
    ExpandableItem3(title = "Card（colors）", allExpandFlow, padding = 20.dp) {
        Card(
            modifier = Modifier.size(160.dp),
            colors = CardDefaults.cardColors(
                containerColor = MyColor.TranslucenceRed,
                contentColor = Color.White,
            )
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
    ExpandableItem3(title = "Card（elevation）", allExpandFlow, padding = 20.dp) {
        Card(
            modifier = Modifier.size(160.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 10.dp,
                pressedElevation = 0.dp
            )
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
    ExpandableItem3(title = "Card（border）", allExpandFlow, padding = 20.dp) {
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


@Composable
private fun ElevatedCardSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "ElevatedCard", allExpandFlow, padding = 20.dp) {
        ElevatedCard(modifier = Modifier.size(160.dp)) {
            Box(modifier = Modifier.fillMaxSize()) {
                Text(text = "This is a card", modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun ElevatedCardSamplePreview() {
    ElevatedCardSample(remember { MutableStateFlow(true) })
}


@Composable
private fun OutlinedCardSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "OutlinedCard", allExpandFlow, padding = 20.dp) {
        OutlinedCard(modifier = Modifier.size(160.dp)) {
            Box(modifier = Modifier.fillMaxSize()) {
                Text(text = "This is a card", modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun OutlinedCardSamplePreview() {
    OutlinedCardSample(remember { MutableStateFlow(true) })
}