package com.github.panpf.android.compose.samples.ui.components.material3

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.panpf.android.compose.samples.R
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem3
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class ListItemFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "ListItem - Material3"
    }

    @Composable
    override fun DrawContent() {
        ExpandableLayout { allExpandFlow ->
            ListItemOneLineSample(allExpandFlow)
            ListItemTwoLineSample(allExpandFlow)
            ListItemThreeLineSample(allExpandFlow)
            ListItemColorsSample(allExpandFlow)
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ListItemOneLineSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "ListItem（one-line）", allExpandFlow, padding = 20.dp) {
        ListItem(
            headlineText = {
                Text(text = "One line list item with trailing")
            },
            leadingContent = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_phone),
                    contentDescription = "phone"
                )
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
private fun ListItemOneLineSamplePreview() {
    ListItemOneLineSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ListItemTwoLineSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "ListItem（Two-line）", allExpandFlow, padding = 20.dp) {
        ListItem(
            headlineText = {
                Text(text = "Two line list item with trailing")
            },
            supportingText = {
                Text(text = "Secondary text")
            },
            leadingContent = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_phone),
                    contentDescription = "phone"
                )
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
private fun ListItemTwoLineSamplePreview() {
    ListItemTwoLineSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ListItemThreeLineSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "ListItem（Three-line）", allExpandFlow, padding = 20.dp) {
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
                Icon(
                    painter = painterResource(id = R.drawable.ic_phone),
                    contentDescription = "phone"
                )
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
private fun ListItemThreeLineSamplePreview() {
    ListItemThreeLineSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ListItemColorsSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "ListItem（colors）", allExpandFlow, padding = 20.dp) {
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
                Icon(
                    painter = painterResource(id = R.drawable.ic_phone),
                    contentDescription = "phone"
                )
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
private fun ListItemColorsSamplePreview() {
    ListItemColorsSample(remember { MutableStateFlow(true) })
}