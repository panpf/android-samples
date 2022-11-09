package com.github.panpf.android.compose.samples.ui.material

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Badge
import androidx.compose.material.BadgedBox
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.panpf.android.compose.samples.R
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.ToolbarFragment
import com.github.panpf.android.compose.samples.ui.base.theme.MyTheme3
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class BadgeFragment : ToolbarFragment() {

    override fun createView(
        toolbar: Toolbar,
        inflater: LayoutInflater,
        parent: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        toolbar.title = "Badge"
        return ComposeView(inflater.context).apply {
            setContent {
                MyTheme3 {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colors.background
                    ) {
                        ExpandableLayout { allExpandFlow ->
                            BadgeSample(allExpandFlow)
                            BadgeColorsSample(allExpandFlow)
                            BadgeIconSample(allExpandFlow)
                            BadgePointSample(allExpandFlow)
                            BadgeBoxSample(allExpandFlow)
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun BadgeSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem(title = "Badge", allExpandFlow, padding = 20.dp) {
        Badge {
            Text(text = "99+")
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun BadgeSamplePreview() {
    BadgeSample(remember { MutableStateFlow(true) })
}


@Composable
fun BadgeColorsSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem(title = "Badge（colors）", allExpandFlow, padding = 20.dp) {
        Badge(backgroundColor = Color.Blue, contentColor = Color.Green) {
            Text(text = "99+")
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun BadgeColorsSamplePreview() {
    BadgeColorsSample(remember { MutableStateFlow(true) })
}


@Composable
fun BadgeIconSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem(title = "Badge（Icon）", allExpandFlow, padding = 20.dp) {
        Badge {
            Icon(
                painter = painterResource(id = R.drawable.ic_close),
                contentDescription = null,
                tint = Color.White
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun BadgeIconSamplePreview() {
    BadgeIconSample(remember { MutableStateFlow(true) })
}


@Composable
fun BadgePointSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem(title = "Badge（Point）", allExpandFlow, padding = 20.dp) {
        Badge()
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun BadgePointSamplePreview() {
    BadgePointSample(remember { MutableStateFlow(true) })
}


@Composable
fun BadgeBoxSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem(title = "Badge（Box）", allExpandFlow, padding = 20.dp) {
        BadgedBox(badge = {
            Badge {
                Text(text = "99+")
            }
        }) {
            Icon(
                Icons.Filled.Settings,
                contentDescription = "Favorite"
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun BadgeBoxSamplePreview() {
    BadgeBoxSample(remember { MutableStateFlow(true) })
}