package com.github.panpf.android.compose.samples.ui.material3

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem3
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class ListsFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "Lists - Material3"
    }

    @Composable
    override fun DrawContent() {
        ExpandableLayout { allExpandFlow ->
            ListItemSample(allExpandFlow)
        }
    }
}


@Composable
private fun ListItemSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "ListItem", allExpandFlow, padding = 20.dp) {
        Text(text = "One-line")
        Spacer(modifier = Modifier.size(10.dp))
        ListItem(
            headlineContent = {
                Text(text = "One line list item with trailing")
            },
            leadingContent = {
                Icon(
                    imageVector = Icons.Filled.Phone,
                    contentDescription = "phone"
                )
            },
            trailingContent = {
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowRight,
                    contentDescription = "more"
                )
            },
        )

        Spacer(modifier = Modifier.size(20.dp))
        Text(text = "Two-line")
        Spacer(modifier = Modifier.size(10.dp))
        ListItem(
            headlineContent = {
                Text(text = "Two line list item with trailing")
            },
            supportingContent = {
                Text(text = "Secondary text")
            },
            leadingContent = {
                Icon(
                    imageVector = Icons.Filled.Phone,
                    contentDescription = "phone"
                )
            },
            trailingContent = {
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowRight,
                    contentDescription = "more"
                )
            },
        )

        Spacer(modifier = Modifier.size(20.dp))
        Text(text = "Three-line")
        Spacer(modifier = Modifier.size(10.dp))
        ListItem(
            headlineContent = {
                Text(text = "Three line list item with trailing")
            },
            overlineContent = {
                Text(text = "Over line")
            },
            supportingContent = {
                Text(text = "Secondary text")
            },
            leadingContent = {
                Icon(
                    imageVector = Icons.Filled.Phone,
                    contentDescription = "phone"
                )
            },
            trailingContent = {
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowRight,
                    contentDescription = "more"
                )
            },
        )

        Spacer(modifier = Modifier.size(20.dp))
        Text(text = "colors")
        Spacer(modifier = Modifier.size(10.dp))
        ListItem(
            headlineContent = {
                Text(text = "Three line list item with trailing")
            },
            overlineContent = {
                Text(text = "Over line")
            },
            supportingContent = {
                Text(text = "Secondary text")
            },
            leadingContent = {
                Icon(
                    imageVector = Icons.Filled.Phone,
                    contentDescription = "phone"
                )
            },
            trailingContent = {
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowRight,
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
private fun ListItemSamplePreview() {
    ListItemSample(remember { MutableStateFlow(true) })
}