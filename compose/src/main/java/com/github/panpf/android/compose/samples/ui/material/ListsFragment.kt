package com.github.panpf.android.compose.samples.ui.material

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.ListItem
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.MaterialComposeAppBarFragment
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class ListsFragment : MaterialComposeAppBarFragment() {

    override fun getTitle(): String {
        return "Lists - Material"
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
                    imageVector = Icons.Filled.Phone,
                    contentDescription = "phone"
                )
            },
            trailing = {
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowRight,
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
                    imageVector = Icons.Filled.Phone,
                    contentDescription = "phone"
                )
            },
            trailing = {
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowRight,
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
                    imageVector = Icons.Filled.Phone,
                    contentDescription = "phone"
                )
            },
            trailing = {
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowRight,
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