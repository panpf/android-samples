package com.github.panpf.android.compose.samples.ui.components.material

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.ListItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.panpf.android.compose.samples.R
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.MaterialComposeAppBarFragment
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class ListItemFragment : MaterialComposeAppBarFragment() {

    override fun getTitle(): String {
        return "ListItem - Material"
    }

    @Composable
    override fun DrawContent() {
        ExpandableLayout { allExpandFlow ->
            ListItemOneLineSample(allExpandFlow)
            ListItemTwoLineSample(allExpandFlow)
            ListItemThreeLineSample(allExpandFlow)
        }
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun ListItemOneLineSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem(title = "ListItem（one-line）", allExpandFlow, padding = 20.dp) {
        ListItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_phone),
                    contentDescription = "phone"
                )
            },
            trailing = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_right),
                    contentDescription = "more"
                )
            },
        ) {
            Text(text = "One line list item with trailing")
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun ListItemOneLineSamplePreview() {
    ListItemOneLineSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun ListItemTwoLineSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem(title = "ListItem（Two-line）", allExpandFlow, padding = 20.dp) {
        ListItem(
            secondaryText = {
                Text(text = "Secondary text")
            },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_phone),
                    contentDescription = "phone"
                )
            },
            trailing = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_right),
                    contentDescription = "more"
                )
            },
        ) {
            Text(text = "Two line list item with trailing")
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun ListItemTwoLineSamplePreview() {
    ListItemTwoLineSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun ListItemThreeLineSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem(title = "ListItem（Three-line）", allExpandFlow, padding = 20.dp) {
        ListItem(
            overlineText = {
                Text(text = "Over line")
            },
            secondaryText = {
                Text(text = "Secondary text")
            },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_phone),
                    contentDescription = "phone"
                )
            },
            trailing = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_right),
                    contentDescription = "more"
                )
            },
        ) {
            Text(text = "Three line list item with trailing")
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun ListItemThreeLineSamplePreview() {
    ListItemThreeLineSample(remember { MutableStateFlow(true) })
}