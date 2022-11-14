package com.github.panpf.android.compose.samples.ui.components.material

import android.graphics.drawable.BitmapDrawable
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.res.ResourcesCompat
import com.github.panpf.android.compose.samples.R
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.MaterialComposeAppBarFragment
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class IconFragment : MaterialComposeAppBarFragment() {

    override fun getTitle(): String {
        return "Icon - Material"
    }

    @Composable
    override fun DrawContent() {
        ExpandableLayout { allExpandFlow ->
            IconResourceSample(allExpandFlow)
            IconVectorSample(allExpandFlow)
            IconBitmapSample(allExpandFlow)
            IconTintSample(allExpandFlow)
        }
    }
}


@Composable
private fun IconResourceSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem(title = "Icon（Resource）", allExpandFlow, padding = 20.dp) {
        Icon(painter = painterResource(id = R.drawable.ic_arrow_down), contentDescription = "")
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun IconResourceSamplePreview() {
    IconResourceSample(remember { MutableStateFlow(true) })
}


@Composable
private fun IconVectorSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem(title = "Icon（Vector）", allExpandFlow, padding = 20.dp) {
        Icon(imageVector = Icons.Filled.List, contentDescription = "")
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun IconVectorSamplePreview() {
    IconVectorSample(remember { MutableStateFlow(true) })
}


@Composable
private fun IconBitmapSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    val imageBitmap = remember {
        (ResourcesCompat.getDrawable(
            context.resources,
            android.R.drawable.ic_delete,
            null
        ) as BitmapDrawable).bitmap.asImageBitmap()
    }
    ExpandableItem(title = "Icon（Bitmap）", allExpandFlow, padding = 20.dp) {
        Icon(bitmap = imageBitmap, contentDescription = "")
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun IconBitmapSamplePreview() {
    IconBitmapSample(remember { MutableStateFlow(true) })
}


@Composable
private fun IconTintSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem(title = "Icon（tint）", allExpandFlow, padding = 20.dp) {
        Icon(
            imageVector = Icons.Filled.List,
            contentDescription = "",
            tint = Color.Red
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun IconTintSamplePreview() {
    IconTintSample(remember { MutableStateFlow(true) })
}