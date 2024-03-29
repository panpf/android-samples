package com.github.panpf.android.compose.samples.ui.material3

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
import com.github.panpf.android.compose.samples.ui.customization.grid.VerticalGrid

class BadgeFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "Badge - Material3"
    }

    @Composable
    override fun DrawContent() {
        BadgeSample()
    }
}


@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
private fun BadgeSample() {
    VerticalGrid(
        columns = GridCells.Fixed(3),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        contentPadding = PaddingValues(20.dp),
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
    ) {
        Column {
            Text(text = "Default")
            Spacer(modifier = Modifier.size(10.dp))
            Badge {
                Text(text = "99+")
            }
        }

        Column {
            Text(text = "containerColor")
            Spacer(modifier = Modifier.size(10.dp))
            Badge(containerColor = Color.Blue) {
                Text(text = "99+")
            }
        }

        Column {
            Text(text = "contentColor")
            Spacer(modifier = Modifier.size(10.dp))
            Badge(contentColor = Color.Green) {
                Text(text = "99+")
            }
        }

        Column {
            Text(text = "Icon")
            Spacer(modifier = Modifier.size(10.dp))
            Badge {
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }

        Column {
            Text(text = "Point")
            Spacer(modifier = Modifier.size(10.dp))
            Badge()
        }

        Column {
            Text(text = "Box")
            Spacer(modifier = Modifier.size(10.dp))
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
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun BadgeSamplePreview() {
    BadgeSample()
}