package com.github.panpf.android.compose.samples.ui.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem3
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
import com.github.panpf.android.compose.samples.ui.base.MyColor
import com.google.accompanist.flowlayout.FlowRow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class SpacerFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "Spacer"
    }

    @Composable
    override fun DrawContent() {
        ExpandableLayout { allExpandFlow ->
            SpacerSample(allExpandFlow)
            SpacerColorSample(allExpandFlow)
        }
    }
}


@Composable
private fun SpacerSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "Spacer", allExpandFlow, padding = 20.dp) {
        FlowRow(mainAxisSpacing = 20.dp, crossAxisSpacing = 20.dp) {
            Column {
                Text(text = "no Spacer")
                Box(
                    modifier = Modifier
                        .size(100.dp, 50.dp)
                        .background(MyColor.HalfBlue)
                )
                Box(
                    modifier = Modifier
                        .size(100.dp, 50.dp)
                        .background(MyColor.HalfMagenta)
                )
            }

            Column {
                Text(text = "size - 10.dp")
                Box(
                    modifier = Modifier
                        .size(100.dp, 50.dp)
                        .background(MyColor.HalfBlue)
                )
                Spacer(modifier = Modifier.size(10.dp))
                Box(
                    modifier = Modifier
                        .size(100.dp, 50.dp)
                        .background(MyColor.HalfMagenta)
                )
            }

            Column {
                Text(text = "size - 20.dp")
                Box(
                    modifier = Modifier
                        .size(100.dp, 50.dp)
                        .background(MyColor.HalfBlue)
                )
                Spacer(modifier = Modifier.size(20.dp))
                Box(
                    modifier = Modifier
                        .size(100.dp, 50.dp)
                        .background(MyColor.HalfMagenta)
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun SpacerSamplePreview() {
    SpacerSample(remember { MutableStateFlow(true) })
}


@Composable
private fun SpacerColorSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "Spacer - Color", allExpandFlow, padding = 20.dp) {
        FlowRow(mainAxisSpacing = 20.dp, crossAxisSpacing = 20.dp) {
            Column {
                Text(text = "no color")
                Box(
                    modifier = Modifier
                        .size(100.dp, 50.dp)
                        .background(MyColor.HalfBlue)
                )
                Spacer(modifier = Modifier.size(10.dp))
                Box(
                    modifier = Modifier
                        .size(100.dp, 50.dp)
                        .background(MyColor.HalfMagenta)
                )
            }

            Column {
                Text(text = "color - yellow")
                Box(
                    modifier = Modifier
                        .size(100.dp, 50.dp)
                        .background(MyColor.HalfBlue)
                )
                Spacer(modifier = Modifier
                    .size(10.dp)
                    .background(MyColor.HalfYellow))
                Box(
                    modifier = Modifier
                        .size(100.dp, 50.dp)
                        .background(MyColor.HalfMagenta)
                )
            }

            Column {
                Text(text = "color - green")
                Box(
                    modifier = Modifier
                        .size(100.dp, 50.dp)
                        .background(MyColor.HalfBlue)
                )
                Spacer(modifier = Modifier
                    .size(10.dp)
                    .background(MyColor.HalfGreen))
                Box(
                    modifier = Modifier
                        .size(100.dp, 50.dp)
                        .background(MyColor.HalfMagenta)
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun SpacerColorSamplePreview() {
    SpacerColorSample(remember { MutableStateFlow(true) })
}