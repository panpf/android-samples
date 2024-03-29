package com.github.panpf.android.compose.samples.ui.material3

import android.graphics.drawable.BitmapDrawable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.res.ResourcesCompat
import com.github.panpf.android.compose.samples.R
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
import com.github.panpf.android.compose.samples.ui.customization.grid.VerticalGrid

class IconFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "Icon - Material3"
    }

    @Composable
    override fun DrawContent() {
        IconSample()
    }
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun IconSample() {
    VerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        contentPadding = PaddingValues(20.dp),
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
    ) {
        Column {
            Text(text = "painter")
            Spacer(modifier = Modifier.size(10.dp))
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_down),
                contentDescription = ""
            )
        }

        Column {
            Text(text = "imageVector")
            Spacer(modifier = Modifier.size(10.dp))
            Icon(imageVector = Icons.Filled.List, contentDescription = "")
        }

        Column {
            Text(text = "bitmap")
            Spacer(modifier = Modifier.size(10.dp))
            val context = LocalContext.current
            val imageBitmap = remember {
                (ResourcesCompat.getDrawable(
                    context.resources,
                    android.R.drawable.ic_delete,
                    null
                ) as BitmapDrawable).bitmap.asImageBitmap()
            }
            Icon(bitmap = imageBitmap, contentDescription = "")
        }

        Column {
            Text(text = "tint")
            Spacer(modifier = Modifier.size(10.dp))
            Icon(
                imageVector = Icons.Filled.List,
                contentDescription = "",
                tint = Color.Red
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun IconSamplePreview() {
    IconSample()
}