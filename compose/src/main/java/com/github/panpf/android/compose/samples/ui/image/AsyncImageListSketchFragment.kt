package com.github.panpf.android.compose.samples.ui.image

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.fragment.app.viewModels
import com.github.panpf.android.compose.samples.R
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
import com.github.panpf.sketch.compose.AsyncImage
import com.github.panpf.sketch.fetch.newAppIconUri
import com.github.panpf.sketch.request.DisplayRequest

class AsyncImageListSketchFragment : Material3ComposeAppBarFragment() {

    private val viewModel by viewModels<AppListViewModel>()

    override fun getTitle(): String {
        return "AsyncImage - List - Sketch"
    }

    @Composable
    override fun DrawContent() {
        val appList by viewModel.appListFlow.collectAsState()
        LazyColumn {
            items(appList) {
                AppItem(it)
            }
        }
    }
}

@Composable
private fun AppItem(app: App) {
    val appIconUri = remember {
        newAppIconUri(app.packageName, app.versionCode)
    }
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        val (icon, name, versionName) = createRefs()

        AsyncImage(
            request = DisplayRequest(LocalContext.current, appIconUri) {
                placeholder(R.drawable.im_placeholder)
            },
            contentDescription = "app icon",
            modifier = Modifier
                .size(50.dp)
                .constrainAs(icon) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
        )

        Text(
            text = app.name,
            fontSize = 18.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.constrainAs(name) {
                width = Dimension.fillToConstraints
                start.linkTo(icon.end, margin = 12.dp)
                end.linkTo(parent.end)
                top.linkTo(icon.top)
            }
        )
        Text(
            text = app.versionName,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.constrainAs(versionName) {
                width = Dimension.fillToConstraints
                start.linkTo(icon.end, margin = 12.dp)
                end.linkTo(parent.end)
                bottom.linkTo(icon.bottom)
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun AppItemPreview() {
    AppItem(app = App("name", "packageName", "1.2.1", 121))
}