package com.github.panpf.android.compose.samples.ui.material3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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

class ListItemFragment : ToolbarFragment() {

    override fun createView(
        toolbar: Toolbar,
        inflater: LayoutInflater,
        parent: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        toolbar.title = "ListItem"
        return ComposeView(inflater.context).apply {
            setContent {
                MyTheme3 {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        ExpandableLayout { allExpandFlow ->
                            ListItemOneLineSample(allExpandFlow)
                            ListItemTwoLineSample(allExpandFlow)
                            ListItemThreeLineSample(allExpandFlow)
                            ListItemColorsSample(allExpandFlow)
                        }
                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListItemOneLineSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem(title = "ListItem（one-line）", allExpandFlow, padding = 20.dp) {
        ListItem(
            headlineText = {
                Text(text = "One line list item with trailing")
            },
            leadingContent = {
                Box {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_phone),
                        contentDescription = "phone"
                    )
                }
            },
            trailingContent = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_right),
                    contentDescription = "more"
                )
            },
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun ListItemOneLineSamplePreview() {
    ListItemOneLineSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListItemTwoLineSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem(title = "ListItem（Two-line）", allExpandFlow, padding = 20.dp) {
        ListItem(
            headlineText = {
                Text(text = "Two line list item with trailing")
            },
            supportingText = {
                Text(text = "Secondary text")
            },
            leadingContent = {
                Box {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_phone),
                        contentDescription = "phone"
                    )
                }
            },
            trailingContent = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_right),
                    contentDescription = "more"
                )
            },
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun ListItemTwoLineSamplePreview() {
    ListItemTwoLineSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListItemThreeLineSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem(title = "ListItem（Three-line）", allExpandFlow, padding = 20.dp) {
        ListItem(
            headlineText = {
                Text(text = "Three line list item with trailing")
            },
            overlineText = {
                Text(text = "Over line")
            },
            supportingText = {
                Text(text = "Secondary text")
            },
            leadingContent = {
                Box {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_phone),
                        contentDescription = "phone"
                    )
                }
            },
            trailingContent = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_right),
                    contentDescription = "more"
                )
            },
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun ListItemThreeLineSamplePreview() {
    ListItemThreeLineSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListItemColorsSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem(title = "ListItem（colors）", allExpandFlow, padding = 20.dp) {
        ListItem(
            headlineText = {
                Text(text = "Three line list item with trailing")
            },
            overlineText = {
                Text(text = "Over line")
            },
            supportingText = {
                Text(text = "Secondary text")
            },
            leadingContent = {
                Box {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_phone),
                        contentDescription = "phone"
                    )
                }
            },
            trailingContent = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_right),
                    contentDescription = "more"
                )
            },
            colors = ListItemDefaults.colors(
                containerColor = Color(0xFF7F7FFF),
                headlineColor = Color.White,
                leadingIconColor = Color.White,
                overlineColor = Color.White,
                supportingColor = Color.White,
                trailingIconColor = Color.White,
            )
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun ListItemColorsSamplePreview() {
    ListItemColorsSample(remember { MutableStateFlow(true) })
}