package com.github.panpf.android.compose.samples.ui.widgets.badge

import androidx.compose.foundation.Image
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Badge
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun BadgeUI() {
    ExpandableLayout { allExpandFlow ->
        BadgeSample(allExpandFlow)
        BadgeContainerColorSample(allExpandFlow)
        BadgeContentColorSample(allExpandFlow)
        BadgeImageSample(allExpandFlow)
        BadgePointSample(allExpandFlow)
    }
}


@OptIn(ExperimentalMaterial3Api::class)
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BadgeContainerColorSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem(title = "Badge（containerColor）", allExpandFlow, padding = 20.dp) {
        Badge(containerColor = Color.Blue) {
            Text(text = "99+")
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun BadgeContainerColorSamplePreview() {
    BadgeContainerColorSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BadgeContentColorSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem(title = "Badge（contentColor）", allExpandFlow, padding = 20.dp) {
        Badge(contentColor = Color.Cyan) {
            Text(text = "99+")
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun BadgeContentColorSamplePreview() {
    BadgeContentColorSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BadgeImageSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem(title = "Badge（Image）", allExpandFlow, padding = 20.dp) {
        Badge {
            Image(
                imageVector = Icons.Default.Close,
                contentDescription = null,
                colorFilter = ColorFilter.tint(Color.White)
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun BadgeImageSamplePreview() {
    BadgeImageSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterial3Api::class)
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