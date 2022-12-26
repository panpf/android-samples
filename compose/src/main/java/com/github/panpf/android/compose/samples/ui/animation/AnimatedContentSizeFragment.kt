package com.github.panpf.android.compose.samples.ui.animation

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.panpf.android.compose.samples.R
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem3
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class AnimatedContentSizeFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "animatedContentSize"
    }

    @Composable
    override fun DrawContent() {
        ExpandableLayout { allExpandFlow ->
            AnimatedContentSizeSample(allExpandFlow)
        }
    }
}


@Composable
private fun AnimatedContentSizeSample(allExpandFlow: Flow<Boolean>) {
    var size by remember { mutableStateOf(60) }
    ExpandableItem3(title = "animatedContentSize", allExpandFlow, padding = 20.dp) {
        Box(
            modifier = Modifier
                .border(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.5f))
                .size(120.dp)
        ) {
            Box(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.primary)
                    .align(Alignment.Center)
                    .animateContentSize()
                    .size(size.dp)
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row {
            FilledIconButton(onClick = {
                size = (size + 20).coerceAtMost(120)
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_add),
                    contentDescription = "add"
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            FilledIconButton(onClick = {
                size = (size - 20).coerceAtLeast(20)
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_subtract),
                    contentDescription = "subtract"
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun AnimatedContentSizeSamplePreview() {
    AnimatedContentSizeSample(remember { MutableStateFlow(true) })
}