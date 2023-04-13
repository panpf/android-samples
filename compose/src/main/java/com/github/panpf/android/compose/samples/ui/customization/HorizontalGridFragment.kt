package com.github.panpf.android.compose.samples.ui.customization

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem3
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
import com.github.panpf.android.compose.samples.ui.customization.grid.HorizontalGrid
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class HorizontalGridFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "HorizontalGrid"
    }

    @Composable
    override fun DrawContent() {
        ExpandableLayout { allExpandFlow ->
            HorizontalGridSample(allExpandFlow)
            // todo 增加更多属性的示例
        }
    }
}


@Composable
fun HorizontalGridSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "HorizontalGrid", allExpandFlow, padding = 20.dp) {
        HorizontalGrid(
//        rows = GridCells.Fixed(2),
            rows = GridCells.Adaptive(100.dp),
            modifier = Modifier.background(Color.Blue.copy(alpha = 0.2f)),
//        crossAxisSize = 100.dp
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            repeat(7) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .width(80.dp)
                        .aspectRatio(1f)
//                    .height(((it + 1) * 10).dp)
                        .border(width = 1.dp, color = Color.Red)
                ) {
                    Text("Item ${it + 1}")
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun HorizontalGridSamplePreview() {
    HorizontalGridSample(remember { MutableStateFlow(true) })
}