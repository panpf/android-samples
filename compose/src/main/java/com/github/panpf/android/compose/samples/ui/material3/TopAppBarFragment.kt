package com.github.panpf.android.compose.samples.ui.material3

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem3
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
import com.github.panpf.tools4a.toast.ktx.showShortToast
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class TopAppBarFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "TopAppBar - Material3"
    }

    @Composable
    override fun DrawContent() {
        ExpandableLayout { allExpandFlow ->
            TopAppBarSample(allExpandFlow)
            CenterAlignedTopAppBarSample(allExpandFlow)
            MediumTopAppBarSample(allExpandFlow)
            LargeTopAppBarSample(allExpandFlow)
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopAppBarSample(allExpandFlow: Flow<Boolean>) {
    val items = remember {
        listOf(
            "分享" to Icons.Filled.Share,
            "更多" to Icons.Filled.MoreVert,
        )
    }
    val context = LocalContext.current
    ExpandableItem3(title = "TopAppBar", allExpandFlow, padding = 20.dp) {
        Text(text = "Default")
        Spacer(modifier = Modifier.size(10.dp))
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

        Spacer(modifier = Modifier.size(20.dp))
        Text(text = "colors")
        Spacer(modifier = Modifier.size(10.dp))
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
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Blue.copy(alpha = 0.6f),
                navigationIconContentColor = Color.White,
                titleContentColor = Color.White,
                actionIconContentColor = Color.White,
            )
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun TopAppBarSamplePreview() {
    TopAppBarSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CenterAlignedTopAppBarSample(allExpandFlow: Flow<Boolean>) {
    val items = remember {
        listOf(
            "分享" to Icons.Filled.Share,
            "更多" to Icons.Filled.MoreVert,
        )
    }
    val context = LocalContext.current
    ExpandableItem3(title = "CenterAlignedTopAppBar", allExpandFlow, padding = 20.dp) {
        Text(text = "Default")
        Spacer(modifier = Modifier.size(10.dp))
        CenterAlignedTopAppBar(
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

        Spacer(modifier = Modifier.size(20.dp))
        Text(text = "colors")
        Spacer(modifier = Modifier.size(10.dp))
        CenterAlignedTopAppBar(
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
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Blue.copy(alpha = 0.6f),
                navigationIconContentColor = Color.White,
                titleContentColor = Color.White,
                actionIconContentColor = Color.White,
            )
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun CenterAlignedTopAppBarSamplePreview() {
    CenterAlignedTopAppBarSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MediumTopAppBarSample(allExpandFlow: Flow<Boolean>) {
    val items = remember {
        listOf(
            "分享" to Icons.Filled.Share,
            "更多" to Icons.Filled.MoreVert,
        )
    }
    val context = LocalContext.current
    ExpandableItem3(title = "MediumTopAppBar", allExpandFlow, padding = 20.dp) {
        Text(text = "Default")
        Spacer(modifier = Modifier.size(10.dp))
        MediumTopAppBar(
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

        Spacer(modifier = Modifier.size(20.dp))
        Text(text = "colors")
        Spacer(modifier = Modifier.size(10.dp))
        MediumTopAppBar(
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
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Blue.copy(alpha = 0.6f),
                navigationIconContentColor = Color.White,
                titleContentColor = Color.White,
                actionIconContentColor = Color.White,
            )
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun MediumTopAppBarSamplePreview() {
    MediumTopAppBarSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LargeTopAppBarSample(allExpandFlow: Flow<Boolean>) {
    val items = remember {
        listOf(
            "分享" to Icons.Filled.Share,
            "更多" to Icons.Filled.MoreVert,
        )
    }
    val context = LocalContext.current
    ExpandableItem3(title = "LargeTopAppBar", allExpandFlow, padding = 20.dp) {
        Text(text = "Default")
        Spacer(modifier = Modifier.size(10.dp))
        LargeTopAppBar(
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

        Spacer(modifier = Modifier.size(20.dp))
        Text(text = "colors")
        Spacer(modifier = Modifier.size(10.dp))
        LargeTopAppBar(
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
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Blue.copy(alpha = 0.6f),
                navigationIconContentColor = Color.White,
                titleContentColor = Color.White,
                actionIconContentColor = Color.White,
            )
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun LargeTopAppBarSamplePreview() {
    LargeTopAppBarSample(remember { MutableStateFlow(true) })
}