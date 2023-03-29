package com.github.panpf.android.compose.samples.ui.base

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SubtitleTextOld(
    text: String,
    line: Int = 2
) {
    val lineHeight = 15
    Text(
        text = text,
        modifier = Modifier.height(((lineHeight * line) + 5).dp),
        lineHeight = lineHeight.sp,
        fontSize = (lineHeight - 2).sp
    )
}

@Composable
fun SubtitleText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    fontSize: TextUnit = 13.sp,
    fontStyle: FontStyle? = null,
    fontWeight: FontWeight? = null,
    fontFamily: FontFamily? = null,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign? = null,
    lineHeight: TextUnit = fontSize * 1.2,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    softWrap: Boolean = true,
    line: Int = 2,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    style: TextStyle = LocalTextStyle.current,
) {
    Text(
        text = text,
        modifier = modifier,
        color = color,
        fontSize = fontSize,
        fontStyle = fontStyle,
        fontWeight = fontWeight,
        fontFamily = fontFamily,
        letterSpacing = letterSpacing,
        textDecoration = textDecoration,
        textAlign = textAlign,
        lineHeight = lineHeight,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = line,
        minLines = line,
        onTextLayout = onTextLayout,
        style = style,
    )
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun SubtitleTextPreview() {
    Column {
        SubtitleTextOld(
            text = "躲过了暴风雪之后，我们再次起程赶路躲过了暴风雪之后，我们再次起程赶路躲过了暴风雪之后，我们再次起程赶路躲过了暴风雪之后，我们再次起程赶路",
            line = 3
        )

        Spacer(modifier = Modifier.size(10.dp))
        SubtitleText(
            text = "躲过了暴风雪之后，我们再次起程赶路躲过了暴风雪之后，我们再次起程赶路躲过了暴风雪之后，我们再次起程赶路躲过了暴风雪之后，我们再次起程赶路",
            line = 3
        )
    }
}