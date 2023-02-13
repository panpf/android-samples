package com.github.panpf.android.compose.samples.ui.material3

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem3
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class BadgeFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "Badge - Material3"
    }

    @Composable
    override fun DrawContent() {
        ExpandableLayout { allExpandFlow ->
            BadgeSample(allExpandFlow)
            BadgeColorsSample(allExpandFlow)
            BadgeIconSample(allExpandFlow)
            BadgePointSample(allExpandFlow)
            BadgeBoxSample(allExpandFlow)
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BadgeSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "Badge", allExpandFlow, padding = 20.dp) {
        Badge {
            Text(text = "99+")
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun BadgeSamplePreview() {
    BadgeSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BadgeColorsSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "Badge（colors）", allExpandFlow, padding = 20.dp) {
        Badge(containerColor = Color.Blue, contentColor = Color.Green) {
            Text(text = "99+")
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun BadgeColorsSamplePreview() {
    BadgeColorsSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BadgeIconSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "Badge（Icon）", allExpandFlow, padding = 20.dp) {
        Badge {
            Icon(
                imageVector = Icons.Filled.Close,
                contentDescription = null,
                tint = Color.White
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun BadgeIconSamplePreview() {
    BadgeIconSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BadgePointSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "Badge（Point）", allExpandFlow, padding = 20.dp) {
        Badge()
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun BadgePointSamplePreview() {
    BadgePointSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BadgeBoxSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3(title = "Badge（Box）", allExpandFlow, padding = 20.dp) {
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
private fun BadgeBoxSamplePreview() {
    BadgeBoxSample(remember { MutableStateFlow(true) })
}