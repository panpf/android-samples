package com.github.panpf.android.compose.samples.ui.graphics

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment

class DrawScopeTransformFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "DrawScope - transform"
    }

    @Composable
    override fun DrawContent() {
        ExpandableLayout { allExpandFlow ->
            Text(
                text = "transform 包括 scale、rotate、translate、clip 等一系列对画布进行直接操作的方法",
                modifier = Modifier.padding(20.dp)
            )
            // todo inset
            // todo translate
            // todo rotate
            // todo rotateRad
            // todo scale
            // todo clipRect
            // todo clipPath
            // todo withTransform
        }
    }
}