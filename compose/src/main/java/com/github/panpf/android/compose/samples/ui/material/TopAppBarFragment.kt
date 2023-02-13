package com.github.panpf.android.compose.samples.ui.material

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.MaterialComposeAppBarFragment
import com.github.panpf.tools4a.toast.ktx.showShortToast
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class TopAppBarFragment : MaterialComposeAppBarFragment() {

    override fun getTitle(): String {
        return "TopAppBar - Material"
    }

    @Composable
    override fun DrawContent() {
        ExpandableLayout { allExpandFlow ->
            TopAppBarSample(allExpandFlow)
        }
    }
}


@Composable
private fun TopAppBarSample(allExpandFlow: Flow<Boolean>) {
    val items = remember {
        listOf(
            "分享" to Icons.Filled.Share,
            "更多" to Icons.Filled.MoreVert,
        )
    }
    val context = LocalContext.current
    ExpandableItem(title = "TopAppBar", allExpandFlow, padding = 20.dp) {
        TopAppBar(
            title = {
                Text(text = "Title")
            },
            navigationIcon = {
                IconButton(
                    modifier = Modifier.fillMaxHeight(),
                    onClick = { context.showShortToast("back") }
                ) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "back")
                }
            },
            actions = {
                items.forEach {
                    IconButton(
                        modifier = Modifier.fillMaxHeight(),
                        onClick = { context.showShortToast(it.first) }
                    ) {
                        Icon(imageVector = it.second, contentDescription = it.first)
                    }
                }
            }
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun TopAppBarSamplePreview() {
    TopAppBarSample(remember { MutableStateFlow(true) })
}


@Composable
private fun TopAppBarColorsSample(allExpandFlow: Flow<Boolean>) {
    val items = remember {
        listOf(
            "分享" to Icons.Filled.Share,
            "更多" to Icons.Filled.MoreVert,
        )
    }
    val context = LocalContext.current
    ExpandableItem(title = "TopAppBar（colors）", allExpandFlow, padding = 20.dp) {
        TopAppBar(
            title = {
                Text(text = "Title")
            },
            navigationIcon = {
                IconButton(
                    modifier = Modifier.fillMaxHeight(),
                    onClick = { context.showShortToast("back") }
                ) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "back")
                }
            },
            actions = {
                items.forEach {
                    IconButton(
                        modifier = Modifier.fillMaxHeight(),
                        onClick = { context.showShortToast(it.first) }
                    ) {
                        Icon(imageVector = it.second, contentDescription = it.first)
                    }
                }
            },
            backgroundColor = Color.Blue.copy(alpha = 0.6f),
            contentColor = Color.White,
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun TopAppBarColorsSamplePreview() {
    TopAppBarColorsSample(remember { MutableStateFlow(true) })
}