package com.github.panpf.android.compose.samples.ui.material

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.ListItem
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
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
            ListItemSample(allExpandFlow)
        }
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun ListItemSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem(title = "ListItem", allExpandFlow, padding = 20.dp) {
        Text(text = "One-line")
        Spacer(modifier = Modifier.size(10.dp))
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

        Spacer(modifier = Modifier.size(20.dp))
        Text(text = "Two-line")
        Spacer(modifier = Modifier.size(10.dp))
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

        Spacer(modifier = Modifier.size(20.dp))
        Text(text = "Three-line")
        Spacer(modifier = Modifier.size(10.dp))
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
private fun ListItemSamplePreview() {
    ListItemSample(remember { MutableStateFlow(true) })
}