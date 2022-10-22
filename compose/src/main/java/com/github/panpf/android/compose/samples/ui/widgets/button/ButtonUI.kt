package com.github.panpf.android.compose.samples.ui.widgets.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.panpf.android.compose.samples.R
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.tools4a.toast.ktx.showLongToast
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun ButtonUI() {
    ExpandableLayout { allExpandFlow ->
        TextButtonSample(allExpandFlow)
        ImageButtonSample(allExpandFlow)
        EnabledButtonSample(allExpandFlow)
        ShapeButtonSample(allExpandFlow)
        ColorButtonSample(allExpandFlow)
        DisabledColorButtonSample(allExpandFlow)
        ElevationButtonSample(allExpandFlow)
        BorderButtonSample(allExpandFlow)
        ContentPaddingButtonSample(allExpandFlow)
    }
}

//@Preview(showBackground = true)
//@Composable
//fun ButtonUIPreview() {
//    ButtonUI()
//}

@Composable
fun TextButtonSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem("文字按钮", allExpandFlow) {
        Button(onClick = {
            context.showLongToast("你点了我！")
        }) {
            Text(text = "按钮2")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TextButtonSamplePreview() {
    TextButtonSample(remember { MutableStateFlow(true) })
}

@Composable
fun ImageButtonSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem("图片按钮", allExpandFlow) {
        Button(onClick = {
            context.showLongToast("你点了我！")
        }) {
            Image(
                painterResource(id = R.drawable.ic_expand_more),
                contentDescription = "",
                colorFilter = ColorFilter.tint(Color.White)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ImageButtonSamplePreview() {
    ImageButtonSample(remember { MutableStateFlow(true) })
}

@Composable
fun EnabledButtonSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem("不可用", allExpandFlow) {
        Button(
            enabled = false,
            onClick = {
                context.showLongToast("你点了我！")
            }
        ) {
            Image(
                painterResource(id = R.drawable.ic_expand_more),
                contentDescription = "",
                colorFilter = ColorFilter.tint(Color.White)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EnabledButtonSamplePreview() {
    EnabledButtonSample(remember { MutableStateFlow(true) })
}

@Composable
fun ShapeButtonSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem("形状（圆角）", allExpandFlow) {
        Button(
            shape = RoundedCornerShape(10.dp),
            onClick = {
                context.showLongToast("你点了我！")
            }
        ) {
            Image(
                painterResource(id = R.drawable.ic_expand_more),
                contentDescription = "",
                colorFilter = ColorFilter.tint(Color.White)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ShapeButtonSamplePreview() {
    ShapeButtonSample(remember { MutableStateFlow(true) })
}

@Composable
fun ColorButtonSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem("颜色（可用）", allExpandFlow) {
        Button(
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Cyan,
                contentColor = Color.Black,
                disabledContainerColor = Color.Gray,
                disabledContentColor = Color.White,
            ),
            onClick = {
                context.showLongToast("你点了我！")
            }
        ) {
            Image(
                painterResource(id = R.drawable.ic_expand_more),
                contentDescription = "",
                colorFilter = ColorFilter.tint(Color.White)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ColorButtonSamplePreview() {
    ColorButtonSample(remember { MutableStateFlow(true) })
}

@Composable
fun DisabledColorButtonSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem("颜色（不可用）", allExpandFlow) {
        Button(
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Cyan,
                contentColor = Color.Black,
                disabledContainerColor = Color.Gray,
                disabledContentColor = Color.White,
            ),
            enabled = false,
            onClick = {
                context.showLongToast("你点了我！")
            }
        ) {
            Image(
                painterResource(id = R.drawable.ic_expand_more),
                contentDescription = "",
                colorFilter = ColorFilter.tint(Color.White)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DisabledColorButtonSamplePreview() {
    DisabledColorButtonSample(remember { MutableStateFlow(true) })
}

@Composable
fun ElevationButtonSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem("高度", allExpandFlow) {
        Button(
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 10.dp,
                pressedElevation = 0.dp
            ),
            onClick = {
                context.showLongToast("你点了我！")
            }
        ) {
            Image(
                painterResource(id = R.drawable.ic_expand_more),
                contentDescription = "",
                colorFilter = ColorFilter.tint(Color.White)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ElevationButtonSamplePreview() {
    ElevationButtonSample(remember { MutableStateFlow(true) })
}

@Composable
fun BorderButtonSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem("描边", allExpandFlow) {
        Button(
            border = BorderStroke(1.dp, Color.Cyan),
            onClick = {
                context.showLongToast("你点了我！")
            }
        ) {
            Image(
                painterResource(id = R.drawable.ic_expand_more),
                contentDescription = "",
                colorFilter = ColorFilter.tint(Color.White)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BorderButtonSamplePreview() {
    BorderButtonSample(remember { MutableStateFlow(true) })
}

@Composable
fun ContentPaddingButtonSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem("内边距", allExpandFlow) {
        Button(
            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 24.dp),
            onClick = {
                context.showLongToast("你点了我！")
            }
        ) {
            Image(
                painterResource(id = R.drawable.ic_expand_more),
                contentDescription = "",
                colorFilter = ColorFilter.tint(Color.White)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ContentPaddingButtonSamplePreview() {
    ContentPaddingButtonSample(remember { MutableStateFlow(true) })
}