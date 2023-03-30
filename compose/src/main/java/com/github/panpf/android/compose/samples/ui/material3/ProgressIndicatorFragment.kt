package com.github.panpf.android.compose.samples.ui.material3

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.panpf.android.compose.samples.R
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem3
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
import com.google.accompanist.flowlayout.FlowRow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class ProgressIndicatorFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "ProgressIndicator - Material3"
    }

    @Composable
    override fun DrawContent() {
        ExpandableLayout { allExpandFlow ->
            CircularProgressIndicatorSample(allExpandFlow)
            LinearProgressIndicatorIndeterminateSample(allExpandFlow)
            LinearProgressIndicatorDeterminateSample(allExpandFlow)
        }
    }
}


@Composable
private fun CircularProgressIndicatorSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "CircularProgressIndicator", allExpandFlow, padding = 20.dp) {
        FlowRow(mainAxisSpacing = 20.dp, crossAxisSpacing = 20.dp) {
            Column {
                Text(text = "Default")
                Spacer(modifier = Modifier.size(10.dp))
                CircularProgressIndicator()
            }
            Column {
                Text(text = "color")
                Spacer(modifier = Modifier.size(10.dp))
                CircularProgressIndicator(color = Color.Red)
            }
            Column {
                Text(text = "trackColor")
                Spacer(modifier = Modifier.size(10.dp))
                CircularProgressIndicator(trackColor = Color.Yellow)
            }
            Column {
                Text(text = "size")
                Spacer(modifier = Modifier.size(10.dp))
                CircularProgressIndicator(modifier = Modifier.size(50.dp))
            }
            Column {
                Text(text = "strokeWidth")
                Spacer(modifier = Modifier.size(10.dp))
                CircularProgressIndicator(modifier = Modifier.size(50.dp), strokeWidth = 10.dp)
            }
            Column {
                Text(text = "strokeCap")
                Spacer(modifier = Modifier.size(10.dp))
                CircularProgressIndicator(
                    modifier = Modifier.size(50.dp),
                    strokeWidth = 10.dp,
                    strokeCap = StrokeCap.Round
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun CircularProgressIndicatorSamplePreview() {
    CircularProgressIndicatorSample(remember { MutableStateFlow(true) })
}


@Composable
private fun LinearProgressIndicatorIndeterminateSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(
        title = "LinearProgressIndicator（Indeterminate）",
        allExpandFlow,
        padding = 20.dp
    ) {
        Text(text = "Default")
        LinearProgressIndicator(modifier = Modifier.fillMaxWidth())

        Spacer(modifier = Modifier.size(20.dp))
        Text(text = "color")
        LinearProgressIndicator(
            modifier = Modifier.fillMaxWidth(),
            color = Color.Red,
        )

        Spacer(modifier = Modifier.size(20.dp))
        Text(text = "trackColor")
        LinearProgressIndicator(
            modifier = Modifier.fillMaxWidth(),
            trackColor = Color.Yellow
        )

        Spacer(modifier = Modifier.size(20.dp))
        Text(text = "size")
        LinearProgressIndicator(
            modifier = Modifier
                .fillMaxWidth()
                .height(20.dp)
        )

        Spacer(modifier = Modifier.size(20.dp))
        Text(text = "strokeCap")
        LinearProgressIndicator(
            modifier = Modifier
                .fillMaxWidth()
                .height(20.dp),
            strokeCap = StrokeCap.Round
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun LinearProgressIndicatorIndeterminateSamplePreview() {
    LinearProgressIndicatorIndeterminateSample(remember { MutableStateFlow(true) })
}


@Composable
private fun LinearProgressIndicatorDeterminateSample(allExpandFlow: Flow<Boolean>) {
    val progress = remember { mutableStateOf(0.3f) }
    ExpandableItem3(
        title = "LinearProgressIndicator（Determinate）",
        allExpandFlow,
        padding = 20.dp
    ) {
        Text(text = "Default")
        LinearProgressIndicator(
            progress = progress.value,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.size(20.dp))
        Text(text = "color")
        LinearProgressIndicator(
            progress = progress.value,
            color = Color.Red,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.size(20.dp))
        Text(text = "trackColor")
        LinearProgressIndicator(
            progress = progress.value,
            trackColor = Color.Yellow,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.size(20.dp))
        Text(text = "size")
        LinearProgressIndicator(
            progress = progress.value,
            modifier = Modifier
                .fillMaxWidth()
                .height(20.dp)
        )

        Spacer(modifier = Modifier.size(20.dp))
        Text(text = "strokeCap")
        LinearProgressIndicator(
            progress = progress.value,
            modifier = Modifier
                .fillMaxWidth()
                .height(20.dp),
            strokeCap = StrokeCap.Round
        )

        Spacer(modifier = Modifier.size(20.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            IconButton(
                modifier = Modifier.weight(1f),
                onClick = {
                    progress.value = (progress.value - 0.1f).coerceAtLeast(0f)
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_subtract),
                    contentDescription = "subtract"
                )
            }
            IconButton(
                modifier = Modifier.weight(1f),
                onClick = {
                    progress.value = (progress.value + 0.1f).coerceAtMost(1.0f)
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "add"
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun LinearProgressIndicatorDeterminateSamplePreview() {
    LinearProgressIndicatorDeterminateSample(remember { MutableStateFlow(true) })
}