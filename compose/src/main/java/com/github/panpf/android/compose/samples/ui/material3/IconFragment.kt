package com.github.panpf.android.compose.samples.ui.material3

import android.graphics.drawable.BitmapDrawable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Icon
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
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem3
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class IconFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "Icon - Material3"
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
    ExpandableItem3(title = "Icon（Resource）", allExpandFlow, padding = 20.dp) {
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
    ExpandableItem3(title = "Icon（Vector）", allExpandFlow, padding = 20.dp) {
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
    ExpandableItem3(title = "Icon（Bitmap）", allExpandFlow, padding = 20.dp) {
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
    ExpandableItem3(title = "Icon（tint）", allExpandFlow, padding = 20.dp) {
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