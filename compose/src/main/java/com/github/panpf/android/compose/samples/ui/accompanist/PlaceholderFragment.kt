package com.github.panpf.android.compose.samples.ui.accompanist

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.panpf.android.compose.samples.R
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.fade
import com.google.accompanist.placeholder.placeholder
import com.google.accompanist.placeholder.shimmer
import kotlinx.coroutines.delay

class PlaceholderFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "Placeholder"
    }

    @Composable
    override fun DrawContent() {
        PlaceholderSample()
    }
}


@Composable
private fun PlaceholderSample() {
    val loadingState = remember() { mutableStateOf(true) }
    LaunchedEffect(key1 = Unit) {
        delay(5000)
        loadingState.value = false
    }

    LazyColumn(Modifier.fillMaxWidth()) {
        items(count = 30) { index ->
            PlaceholderItem(index = index, loading = loadingState.value)
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun PlaceholderSamplePreview() {
    PlaceholderSample()
}


@Composable
private fun PlaceholderItem(index: Int, loading: Boolean) {
    val placeholderHighlights = when (index % 3) {
        0 -> null to "这是默认没有高亮动画的"
        1 -> PlaceholderHighlight.fade(highlightColor = Color.White) to "这是 fade 高亮动画"
        else -> PlaceholderHighlight.shimmer(highlightColor = Color.White) to "这是 shimmer 高亮动画"
    }
    val placeholder = Modifier.placeholder(
        visible = loading,
        color = Color.Gray.copy(0.3f),
        highlight = placeholderHighlights.first
    )
    Row(
        Modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_avatar),
            contentDescription = "",
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
                .then(placeholder)
        )
        Spacer(modifier = Modifier.size(12.dp))
        Column(
            Modifier
                .fillMaxWidth()
                .align(Alignment.CenterVertically)
        ) {
            Text(
                text = "Placeholder",
                modifier = Modifier
                    .fillMaxWidth()
                    .then(placeholder)
            )
            Spacer(modifier = Modifier.size(6.dp))
            Text(
                text = placeholderHighlights.second,
                modifier = Modifier
                    .fillMaxWidth()
                    .then(placeholder)
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun PlaceholderPreview() {
    Column {
        PlaceholderItem(index = 0, loading = false)
        PlaceholderItem(index = 0, loading = true)
        PlaceholderItem(index = 1, loading = true)
        PlaceholderItem(index = 2, loading = true)
    }
}