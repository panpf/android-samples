package com.github.panpf.android.compose.samples.ui.material3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.panpf.android.compose.samples.R
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem3
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.ToolbarFragment
import com.github.panpf.android.compose.samples.ui.base.theme.MyTheme3
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class ProgressIndicatorFragment : ToolbarFragment() {

    override fun createView(
        toolbar: Toolbar,
        inflater: LayoutInflater,
        parent: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        toolbar.title = "ProgressIndicator - Material3"
        return ComposeView(inflater.context).apply {
            setContent {
                MyTheme3 {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        ExpandableLayout { allExpandFlow ->
                            CircularProgressIndicatorSample(allExpandFlow)
                            CircularProgressIndicatorSizeSample(allExpandFlow)
                            CircularProgressIndicatorColorSample(allExpandFlow)
                            LinearProgressIndicatorIndeterminateSample(allExpandFlow)
                            LinearProgressIndicatorIndeterminateSizeSample(allExpandFlow)
                            LinearProgressIndicatorIndeterminateColorSample(allExpandFlow)
                            LinearProgressIndicatorIndeterminateClipSample(allExpandFlow)
                            LinearProgressIndicatorDeterminateSample(allExpandFlow)
                            LinearProgressIndicatorDeterminateSizeSample(allExpandFlow)
                            LinearProgressIndicatorDeterminateColorSample(allExpandFlow)
                            LinearProgressIndicatorDeterminateClipSample(allExpandFlow)
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun CircularProgressIndicatorSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "CircularProgressIndicator", allExpandFlow, padding = 20.dp) {
        CircularProgressIndicator()
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun CircularProgressIndicatorSamplePreview() {
    CircularProgressIndicatorSample(remember { MutableStateFlow(true) })
}


@Composable
fun CircularProgressIndicatorSizeSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "CircularProgressIndicator（size）", allExpandFlow, padding = 20.dp) {
        CircularProgressIndicator(modifier = Modifier.size(70.dp))
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun CircularProgressIndicatorSizeSamplePreview() {
    CircularProgressIndicatorSizeSample(remember { MutableStateFlow(true) })
}


@Composable
fun CircularProgressIndicatorColorSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "CircularProgressIndicator（color）", allExpandFlow, padding = 20.dp) {
        CircularProgressIndicator(color = Color.Red)
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun CircularProgressIndicatorColorSamplePreview() {
    CircularProgressIndicatorColorSample(remember { MutableStateFlow(true) })
}


@Composable
fun CircularProgressIndicatorStrokeWidthSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(
        title = "CircularProgressIndicator（strokeWidth）",
        allExpandFlow,
        padding = 20.dp
    ) {
        CircularProgressIndicator(strokeWidth = 10.dp)
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun CircularProgressIndicatorStrokeWidthSamplePreview() {
    CircularProgressIndicatorStrokeWidthSample(remember { MutableStateFlow(true) })
}


@Composable
fun LinearProgressIndicatorIndeterminateSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(
        title = "LinearProgressIndicator（Indeterminate）",
        allExpandFlow,
        padding = 20.dp
    ) {
        LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun LinearProgressIndicatorIndeterminateSamplePreview() {
    LinearProgressIndicatorIndeterminateSample(remember { MutableStateFlow(true) })
}


@Composable
fun LinearProgressIndicatorIndeterminateSizeSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(
        title = "LinearProgressIndicator（Indeterminate - size）",
        allExpandFlow,
        padding = 20.dp
    ) {
        LinearProgressIndicator(
            modifier = Modifier
                .fillMaxWidth()
                .height(20.dp)
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun LinearProgressIndicatorIndeterminateSizeSamplePreview() {
    LinearProgressIndicatorIndeterminateSizeSample(remember { MutableStateFlow(true) })
}


@Composable
fun LinearProgressIndicatorIndeterminateColorSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(
        title = "LinearProgressIndicator（Indeterminate - color）",
        allExpandFlow,
        padding = 20.dp
    ) {
        LinearProgressIndicator(
            modifier = Modifier.fillMaxWidth(),
            color = Color.Red,
            trackColor = Color.Red.copy(alpha = 0.3f)
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun LinearProgressIndicatorIndeterminateColorSamplePreview() {
    LinearProgressIndicatorIndeterminateColorSample(remember { MutableStateFlow(true) })
}


@Composable
fun LinearProgressIndicatorIndeterminateClipSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(
        title = "LinearProgressIndicator（Indeterminate - clip）",
        allExpandFlow,
        padding = 20.dp
    ) {
        LinearProgressIndicator(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(50))
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun LinearProgressIndicatorIndeterminateClipSamplePreview() {
    LinearProgressIndicatorIndeterminateClipSample(remember { MutableStateFlow(true) })
}


@Composable
fun LinearProgressIndicatorDeterminateSample(allExpandFlow: Flow<Boolean>) {
    val progress = remember { mutableStateOf(0.3f) }
    ExpandableItem3(title = "LinearProgressIndicator（Determinate）", allExpandFlow, padding = 20.dp) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            IconButton(
                onClick = {
                    progress.value = (progress.value - 0.1f).coerceAtLeast(0f)
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_subtract),
                    contentDescription = "subtract"
                )
            }
            LinearProgressIndicator(progress = progress.value, modifier = Modifier.weight(1f))
            IconButton(
                onClick = {
                    progress.value = (progress.value + 0.1f).coerceAtMost(1.0f)
                }
            ) {
                Icon(painter = painterResource(id = R.drawable.ic_add), contentDescription = "add")
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun LinearProgressIndicatorDeterminateSamplePreview() {
    LinearProgressIndicatorDeterminateSample(remember { MutableStateFlow(true) })
}


@Composable
fun LinearProgressIndicatorDeterminateSizeSample(allExpandFlow: Flow<Boolean>) {
    val progress = remember { mutableStateOf(0.3f) }
    ExpandableItem3(
        title = "LinearProgressIndicator（Determinate - size）",
        allExpandFlow,
        padding = 20.dp
    ) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            IconButton(
                onClick = {
                    progress.value = (progress.value - 0.1f).coerceAtLeast(0f)
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_subtract),
                    contentDescription = "subtract"
                )
            }
            LinearProgressIndicator(
                progress = progress.value, modifier = Modifier
                    .weight(1f)
                    .height(20.dp)
            )
            IconButton(
                onClick = {
                    progress.value = (progress.value + 0.1f).coerceAtMost(1.0f)
                }
            ) {
                Icon(painter = painterResource(id = R.drawable.ic_add), contentDescription = "add")
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun LinearProgressIndicatorDeterminateSizeSamplePreview() {
    LinearProgressIndicatorDeterminateSizeSample(remember { MutableStateFlow(true) })
}


@Composable
fun LinearProgressIndicatorDeterminateColorSample(allExpandFlow: Flow<Boolean>) {
    val progress = remember { mutableStateOf(0.3f) }
    ExpandableItem3(
        title = "LinearProgressIndicator（Determinate - color）",
        allExpandFlow,
        padding = 20.dp
    ) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            IconButton(
                onClick = {
                    progress.value = (progress.value - 0.1f).coerceAtLeast(0f)
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_subtract),
                    contentDescription = "subtract"
                )
            }
            LinearProgressIndicator(
                progress = progress.value,
                color = Color.Red,
                trackColor = Color.Red.copy(alpha = 0.3f),
                modifier = Modifier.weight(1f)
            )
            IconButton(
                onClick = {
                    progress.value = (progress.value + 0.1f).coerceAtMost(1.0f)
                }
            ) {
                Icon(painter = painterResource(id = R.drawable.ic_add), contentDescription = "add")
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun LinearProgressIndicatorDeterminateColorSamplePreview() {
    LinearProgressIndicatorDeterminateColorSample(remember { MutableStateFlow(true) })
}


@Composable
fun LinearProgressIndicatorDeterminateClipSample(allExpandFlow: Flow<Boolean>) {
    val progress = remember { mutableStateOf(0.3f) }
    ExpandableItem3(
        title = "LinearProgressIndicator（Determinate - clip）",
        allExpandFlow,
        padding = 20.dp
    ) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            IconButton(
                onClick = {
                    progress.value = (progress.value - 0.1f).coerceAtLeast(0f)
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_subtract),
                    contentDescription = "subtract"
                )
            }
            LinearProgressIndicator(
                progress = progress.value,
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(50))
            )
            IconButton(
                onClick = {
                    progress.value = (progress.value + 0.1f).coerceAtMost(1.0f)
                }
            ) {
                Icon(painter = painterResource(id = R.drawable.ic_add), contentDescription = "add")
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun LinearProgressIndicatorDeterminateClipSamplePreview() {
    LinearProgressIndicatorDeterminateClipSample(remember { MutableStateFlow(true) })
}