package com.github.panpf.android.compose.samples.ui.base

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.TextUnit

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ExpandableText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    fontSize: TextUnit = TextUnit.Unspecified,
    fontStyle: FontStyle? = null,
    fontWeight: FontWeight? = null,
    fontFamily: FontFamily? = null,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign? = null,
    lineHeight: TextUnit = TextUnit.Unspecified,
    softWrap: Boolean = true,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    style: TextStyle = LocalTextStyle.current
) {
    val inspectionMode = LocalInspectionMode.current
    var expanded by remember { mutableStateOf(inspectionMode) }
    AnimatedContent(
        targetState = expanded,
        contentAlignment = Alignment.TopEnd,
        transitionSpec = {
            ContentTransform(
                targetContentEnter = fadeIn(animationSpec = tween(0, 0)),
                initialContentExit = fadeOut(animationSpec = tween(0))
            ).using(
                SizeTransform { initialSize, targetSize ->
                    if (targetState) {
                        keyframes {
                            // Expand horizontally first.
                            IntSize(targetSize.width, initialSize.height) at 150
                            durationMillis = 300
                        }
                    } else {
                        keyframes {
                            // Shrink vertically first.
                            IntSize(initialSize.width, targetSize.height) at 150
                            durationMillis = 300
                        }
                    }
                }
            )
        }
    ) { targetExpanded ->
        if (targetExpanded) {
            Text(
                text = text,
                modifier = modifier.clickable { expanded = !expanded },
                color = color,
                fontSize = fontSize,
                fontStyle = fontStyle,
                fontWeight = fontWeight,
                fontFamily = fontFamily,
                letterSpacing = letterSpacing,
                textDecoration = textDecoration,
                textAlign = textAlign,
                lineHeight = lineHeight,
                overflow = TextOverflow.Clip,
                softWrap = softWrap,
                maxLines = Int.MAX_VALUE,
                onTextLayout = onTextLayout,
                style = style,
            )
        } else {
            Text(
                text = text,
                modifier = modifier.clickable { expanded = !expanded },
                color = color,
                fontSize = fontSize,
                fontStyle = fontStyle,
                fontWeight = fontWeight,
                fontFamily = fontFamily,
                letterSpacing = letterSpacing,
                textDecoration = textDecoration,
                textAlign = textAlign,
                lineHeight = lineHeight,
                overflow = TextOverflow.Ellipsis,
                softWrap = softWrap,
                maxLines = 3,
                onTextLayout = onTextLayout,
                style = style,
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun ExpandableTextPreview() {
    val text =
        "????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????\n          ----???????????????????????? - ?????????????????????????????? ????????????????????????http://www.daomubiji.com/yun-ding-tian-gong-15.html"
    ExpandableText(text = text)
}