package com.github.panpf.android.compose.samples.ui.material3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.ToolbarFragment
import com.github.panpf.android.compose.samples.ui.base.theme.MyTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class CardFragment : ToolbarFragment() {

    override fun createView(
        toolbar: Toolbar,
        inflater: LayoutInflater,
        parent: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        toolbar.title = "Card"
        return ComposeView(inflater.context).apply {
            setContent {
                MyTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
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
            }
        }
    }
}


@Composable
fun CardSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem(title = "Card", allExpandFlow, padding = 20.dp) {
        Card(modifier = Modifier.size(160.dp)) {
            Box(modifier = Modifier.fillMaxSize()) {
                Text(text = "这是一个卡片", modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun CardSamplePreview() {
    CardSample(remember { MutableStateFlow(true) })
}


@Composable
fun CardShapeSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem(title = "Card（shape）", allExpandFlow, padding = 20.dp) {
        Card(
            modifier = Modifier.size(160.dp),
            shape = CircleShape
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                Text(text = "这是一个卡片", modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun CardShapeSamplePreview() {
    CardShapeSample(remember { MutableStateFlow(true) })
}


@Composable
fun CardColorsSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem(title = "Card（colors）", allExpandFlow, padding = 20.dp) {
        Card(
            modifier = Modifier.size(160.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.Red.copy(alpha = 0.5f),
                contentColor = Color.White,
            )
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                Text(text = "这是一个卡片", modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun CardColorsSamplePreview() {
    CardColorsSample(remember { MutableStateFlow(true) })
}


@Composable
fun CardElevationSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem(title = "Card（elevation）", allExpandFlow, padding = 20.dp) {
        Card(
            modifier = Modifier.size(160.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp, pressedElevation = 0.dp)
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                Text(text = "这是一个卡片", modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun CardElevationSamplePreview() {
    CardElevationSample(remember { MutableStateFlow(true) })
}


@Composable
fun CardBorderSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem(title = "Card（border）", allExpandFlow, padding = 20.dp) {
        Card(
            modifier = Modifier.size(160.dp),
            border = BorderStroke(2.dp, Color.Red)
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                Text(text = "这是一个卡片", modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun CardBorderSamplePreview() {
    CardBorderSample(remember { MutableStateFlow(true) })
}


@Composable
fun ElevatedCardSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem(title = "ElevatedCard", allExpandFlow, padding = 20.dp) {
        ElevatedCard(modifier = Modifier.size(160.dp)) {
            Box(modifier = Modifier.fillMaxSize()) {
                Text(text = "这是一个卡片", modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun ElevatedCardSamplePreview() {
    ElevatedCardSample(remember { MutableStateFlow(true) })
}


@Composable
fun OutlinedCardSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem(title = "OutlinedCard", allExpandFlow, padding = 20.dp) {
        OutlinedCard(modifier = Modifier.size(160.dp)) {
            Box(modifier = Modifier.fillMaxSize()) {
                Text(text = "这是一个卡片", modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun OutlinedCardSamplePreview() {
    OutlinedCardSample(remember { MutableStateFlow(true) })
}