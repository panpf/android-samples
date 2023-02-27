package com.github.panpf.android.compose.samples.ui.animation

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.panpf.android.compose.samples.R
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem3
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.ExpandableText
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class CrossfadeFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "Crossfade"
    }

    @Composable
    override fun DrawContent() {
        val desc = """
            Crossfade 可使用淡入淡出动画在两个布局之间添加动画效果。通过切换传递给 current 参数的值，可以使用淡入淡出动画来切换内容。
        """.trimIndent()
        ExpandableLayout { allExpandFlow ->
            ExpandableText(text = desc, modifier = Modifier.padding(20.dp))
            CrossfadeSample(allExpandFlow)
        }
    }
}

@Composable
private fun CrossfadeSample(allExpandFlow: Flow<Boolean>) {
    var enabled by remember { mutableStateOf(true) }
    ExpandableItem3(title = "Crossfade", allExpandFlow, padding = 20.dp) {
        Crossfade(targetState = enabled) {
            val size = 160.dp
            if (it) {
                val text =
                    "躲过了暴风雪之后，我们再次起程赶路，在一处斜坡下发现了阿宁他们的马队，同时也发现了海底墓穴影画之中的那一座神秘雪山，赫然出现在了我们的视野尽头。就在我们询问向导如何才能到达那里的时候，顺子却摇头，说我们绝对无法过去。"
                Text(
                    text = text,
                    modifier = Modifier.height(size),
                    overflow = TextOverflow.Ellipsis
                )
            } else {
                Image(
                    painter = painterResource(id = R.drawable.dog_hor),
                    contentDescription = "dog",
                    modifier = Modifier
                        .size(size)
                        .clip(RoundedCornerShape(50)),
                    contentScale = ContentScale.Crop,
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = { enabled = !enabled }) {
            Text(text = "Change Crossfade")
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun CrossfadeSamplePreview() {
    CrossfadeSample(remember { MutableStateFlow(true) })
}