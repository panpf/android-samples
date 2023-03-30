package com.github.panpf.android.compose.samples.ui.material

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Badge
import androidx.compose.material.BadgedBox
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.panpf.android.compose.samples.ui.base.MaterialComposeAppBarFragment
import com.google.accompanist.flowlayout.FlowRow

class BadgeFragment : MaterialComposeAppBarFragment() {

    override fun getTitle(): String {
        return "Badge - Material"
    }

    @Composable
    override fun DrawContent() {
        BadgeSample()
    }
}


@Composable
private fun BadgeSample() {
    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp), mainAxisSpacing = 20.dp, crossAxisSpacing = 20.dp
    ) {
        Column {
            Text(text = "Default")
            Spacer(modifier = Modifier.size(10.dp))
            Badge {
                Text(text = "99+")
            }
        }

        Column {
            Text(text = "backgroundColor")
            Spacer(modifier = Modifier.size(10.dp))
            Badge(backgroundColor = Color.Blue) {
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