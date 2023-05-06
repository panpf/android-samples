package com.github.panpf.android.compose.samples.ui.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
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
import com.github.panpf.android.compose.samples.ui.customization.grid.VerticalGrid
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


@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun SpacerSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "Spacer", allExpandFlow, padding = 20.dp) {
        VerticalGrid(
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            Column {
                Text(text = "no Spacer")
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .background(MyColor.HalfBlue)
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .background(MyColor.HalfMagenta)
                )
            }

            Column {
                Text(text = "size - 10.dp")
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .background(MyColor.HalfBlue)
                )
                Spacer(modifier = Modifier.size(10.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .background(MyColor.HalfMagenta)
                )
            }

            Column {
                Text(text = "size - 20.dp")
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .background(MyColor.HalfBlue)
                )
                Spacer(modifier = Modifier.size(20.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
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


@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun SpacerColorSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "Spacer - Color", allExpandFlow, padding = 20.dp) {
        VerticalGrid(
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            Column {
                Text(text = "no color")
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .background(MyColor.HalfBlue)
                )
                Spacer(modifier = Modifier.size(10.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .background(MyColor.HalfMagenta)
                )
            }

            Column {
                Text(text = "color - yellow")
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .background(MyColor.HalfBlue)
                )
                Spacer(
                    modifier = Modifier
                        .size(10.dp)
                        .background(MyColor.HalfYellow)
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .background(MyColor.HalfMagenta)
                )
            }

            Column {
                Text(text = "color - green")
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .background(MyColor.HalfBlue)
                )
                Spacer(
                    modifier = Modifier
                        .size(10.dp)
                        .background(MyColor.HalfGreen)
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
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