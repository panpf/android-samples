package com.github.panpf.android.compose.samples.ui.material3

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.text.style.TextGeometricTransform
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem3
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
import com.github.panpf.android.compose.samples.ui.base.MyColor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class TextFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "Text - Material3"
    }

    @Composable
    override fun DrawContent() {
        ExpandableLayout { allExpandFlow ->
            TextSample(allExpandFlow)
            TextColorSample(allExpandFlow)
            TextFontSizeSample(allExpandFlow)
            TextFontStyleSample(allExpandFlow)
            TextFontWeightSample(allExpandFlow)
            TextFontFamilySample(allExpandFlow)
            TextLetterSpacingSample(allExpandFlow)
            TextTextDecorationSample(allExpandFlow)
            TextTextAlignSample(allExpandFlow)
            TextLineHeightSample(allExpandFlow)
            TextOverflowSample(allExpandFlow)
            TextSoftWrapSample(allExpandFlow)
            TextMaxLinesSample(allExpandFlow)
            TextBaselineShiftSample(allExpandFlow)
            TextTextGeometricTransformSample(allExpandFlow)
            TextBackgroundSample(allExpandFlow)
            TextShadowSample(allExpandFlow)
            TextTextDirectionSample(allExpandFlow)
            TextTextIndentSample(allExpandFlow)
            TextAnnotatedStringSample(allExpandFlow)
            TextClickableAnnotatedStringSample(allExpandFlow)
            TextSelectableSample(allExpandFlow)
        }
    }
}

private const val text =
    "????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????\n          ----???????????????????????? - ?????????????????????????????? ????????????????????????http://www.daomubiji.com/yun-ding-tian-gong-15.html"
private const val words = "??????????????????"
private const val words1 = "http://www.daomubiji.com/yun-ding-tian-gong-15.html"
private val wordsIndex = text.indexOf(words).apply { require(this != -1) }
private val words1Index = text.indexOf(words1).apply { require(this != -1 && this > wordsIndex) }

@Composable
private fun MyTextContainer(content: @Composable BoxScope.() -> Unit) {
    Box(
        modifier = Modifier
            .background(Color.Blue.copy(alpha = 0.2f))
    ) {
        content()
    }
}

@Composable
private fun TextSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3("Text", allExpandFlow, padding = 20.dp) {
        MyTextContainer {
            Text(text = text)
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun TextSamplePreview() {
    TextSample(remember { MutableStateFlow(true) })
}


@Composable
private fun TextColorSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3("Text???color???", allExpandFlow, padding = 20.dp) {
        MyTextContainer {
            Text(text = text, color = Color.Magenta)
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun TextColorSamplePreview() {
    TextColorSample(remember { MutableStateFlow(true) })
}


@Composable
private fun TextFontSizeSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3("Text???fontSize???", allExpandFlow, padding = 20.dp) {
        MyTextContainer {
            Text(text = text, fontSize = 20.sp)
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun TextFontSizeSamplePreview() {
    TextFontSizeSample(MutableStateFlow(true))
}


@Composable
private fun TextFontStyleSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3("Text???fontStyle - Italic???", allExpandFlow, padding = 20.dp) {
        MyTextContainer {
            Text(text = text, fontStyle = FontStyle.Italic)
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun TextFontStyleSamplePreview() {
    TextFontStyleSample(MutableStateFlow(true))
}


@Composable
private fun TextFontWeightSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3("Text???fontWeight???", allExpandFlow, padding = 20.dp) {
        listOf(
            "Thin" to FontWeight.Thin,
            "Normal" to FontWeight.Normal,
            "Bold" to FontWeight.Bold,
        ).forEachIndexed { index, pair ->
            if (index > 0) {
                Spacer(modifier = Modifier.size(10.dp))
            }
            Text(text = pair.first)
            MyTextContainer {
                Text(text = text, fontWeight = pair.second)
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun TextFontWeightSamplePreview() {
    TextFontWeightSample(MutableStateFlow(true))
}


@Composable
private fun TextFontFamilySample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3("Text???fontFamily???", allExpandFlow, padding = 20.dp) {
        listOf(
            "SansSerif" to FontFamily.SansSerif,
            "Monospace" to FontFamily.Monospace,
            "Cursive" to FontFamily.Cursive,
        ).forEachIndexed { index, pair ->
            if (index > 0) {
                Spacer(modifier = Modifier.size(10.dp))
            }
            Text(text = pair.first)
            MyTextContainer {
                Text(text = text, fontFamily = pair.second)
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun TextFontFamilySamplePreview() {
    TextFontFamilySample(MutableStateFlow(true))
}


@Composable
private fun TextLetterSpacingSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3("Text???letterSpacing???", allExpandFlow, padding = 20.dp) {
        MyTextContainer {
            Text(text = text, letterSpacing = 8.sp)
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun TextLetterSpacingSamplePreview() {
    TextLetterSpacingSample(MutableStateFlow(true))
}


@Composable
private fun TextTextDecorationSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3("Text???textDecoration???", allExpandFlow, padding = 20.dp) {
        Text(text = "Underline")
        MyTextContainer {
            Text(text = text, textDecoration = TextDecoration.Underline)
        }

        Spacer(modifier = Modifier.size(10.dp))
        Text(text = "LineThrough")
        MyTextContainer {
            Text(text = text, textDecoration = TextDecoration.LineThrough)
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun TextTextDecorationUnderlineSamplePreview() {
    TextTextDecorationSample(MutableStateFlow(true))
}


@Composable
private fun TextTextAlignSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3("Text???textAlign???", allExpandFlow, padding = 20.dp) {
        Text(text = "Start")
        MyTextContainer {
            Text(text = text, textAlign = TextAlign.Start)
        }

        Spacer(modifier = Modifier.size(10.dp))
        Text(text = "Center")
        MyTextContainer {
            Text(text = text, textAlign = TextAlign.Center)
        }

        Spacer(modifier = Modifier.size(10.dp))
        Text(text = "End")
        MyTextContainer {
            Text(text = text, textAlign = TextAlign.End)
        }

        Spacer(modifier = Modifier.size(10.dp))
        Text(text = "Justify")
        MyTextContainer {
            Text(text = text, textAlign = TextAlign.Justify)
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun TextTextAlignSamplePreview() {
    TextTextAlignSample(MutableStateFlow(true))
}


@Composable
private fun TextLineHeightSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3("Text???lineHeight???", allExpandFlow, padding = 20.dp) {
        MyTextContainer {
            Text(text = text, lineHeight = 28.sp)
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun TextLineHeightSamplePreview() {
    TextLineHeightSample(MutableStateFlow(true))
}


@Composable
private fun TextOverflowSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3("Text???overflow???", allExpandFlow, padding = 20.dp) {
        listOf(
            "Ellipsis" to TextOverflow.Ellipsis,
            "Clip" to TextOverflow.Clip,
            "Visible" to TextOverflow.Visible,
        ).forEachIndexed { index, pair ->
            if (index > 0) {
                Spacer(modifier = Modifier.size(10.dp))
            }
            Text(text = pair.first)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MyColor.TranslucenceRed)
            ) {
                Text(
                    text = text,
                    modifier = Modifier.alpha(0f)
                )    // Not visible, in order for Box to get full display height
                Text(
                    text = text,
                    overflow = pair.second,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(46.dp)
                        .background(MyColor.TranslucenceBlue)
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun TextOverflowEllipsisSamplePreview() {
    TextOverflowSample(MutableStateFlow(true))
}


@Composable
private fun TextSoftWrapSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3("Text???softWrap???", allExpandFlow, padding = 20.dp) {
        Text(text = "true")
        MyTextContainer {
            Text(text = text, softWrap = true)
        }

        Spacer(modifier = Modifier.size(10.dp))
        Text(text = "false")
        MyTextContainer {
            Text(text = text, softWrap = false)
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun TextSoftWrapSamplePreview() {
    TextSoftWrapSample(MutableStateFlow(true))
}


@Composable
private fun TextMaxLinesSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3("Text???maxLines???", allExpandFlow, padding = 20.dp) {
        listOf(
            "maxLines + TextOverflow.Ellipsis" to TextOverflow.Ellipsis,
            "maxLines + TextOverflow.Clip" to TextOverflow.Clip,
            "maxLines + TextOverflow.Visible" to TextOverflow.Visible,
        ).forEachIndexed { index, pair ->
            if (index > 0) {
                Spacer(modifier = Modifier.size(10.dp))
            }
            Text(text = pair.first)
            MyTextContainer {
                Text(
                    text = text,
                    maxLines = 2,
                    overflow = pair.second,
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun TextMaxLinesSamplePreview() {
    TextMaxLinesSample(MutableStateFlow(true))
}


@Composable
private fun TextBaselineShiftSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3("Text???baselineShift???", allExpandFlow, padding = 20.dp) {
        MyTextContainer {
            Text(
                text = text,
                style = TextStyle(baselineShift = BaselineShift(2f)),
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun TextBaselineShiftSamplePreview() {
    TextBaselineShiftSample(MutableStateFlow(true))
}


@Composable
private fun TextTextGeometricTransformSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3("Text???textGeometricTransform???", allExpandFlow, padding = 20.dp) {
        MyTextContainer {
            Text(
                text = text,
                style = TextStyle(
                    textGeometricTransform = TextGeometricTransform(
                        scaleX = 1.5f,
                        skewX = 0.3f
                    )
                ),
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun TextTextGeometricTransformSamplePreview() {
    TextTextGeometricTransformSample(MutableStateFlow(true))
}


@Composable
private fun TextBackgroundSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3("Text???background???", allExpandFlow, padding = 20.dp) {
        MyTextContainer {
            Text(
                text = text,
                style = TextStyle(background = Color.Red),
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun TextBackgroundSamplePreview() {
    TextBackgroundSample(MutableStateFlow(true))
}


@Composable
private fun TextShadowSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3("Text???shadow???", allExpandFlow, padding = 20.dp) {
        MyTextContainer {
            Text(
                text = text,
                style = TextStyle(
                    shadow = Shadow(
                        MyColor.TranslucenceBlack,
                        offset = Offset(4f, 4f),
                        blurRadius = 2f
                    )
                ),
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun TextShadowSamplePreview() {
    TextShadowSample(MutableStateFlow(true))
}


@Composable
private fun TextTextDirectionSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3("Text???textDirection???", allExpandFlow, padding = 20.dp) {
        MyTextContainer {
            Text(
                text = text,
                style = TextStyle(textDirection = TextDirection.Rtl),
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun TextTextDirectionSamplePreview() {
    TextTextDirectionSample(MutableStateFlow(true))
}


@Composable
private fun TextTextIndentSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3("Text???textIndent???", allExpandFlow, padding = 20.dp) {
        MyTextContainer {
            Text(
                text = text,
                style = TextStyle(textIndent = TextIndent(firstLine = 40.sp, restLine = 10.sp)),
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun TextTextIndentSamplePreview() {
    TextTextIndentSample(MutableStateFlow(true))
}


@Composable
private fun TextAnnotatedStringSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3("Text???AnnotatedString - PartialHighlighting???", allExpandFlow, padding = 20.dp) {
        MyTextContainer {
            Text(
                text = buildAnnotatedString {
                    withStyle(SpanStyle()) {
                        append(text.substring(0, wordsIndex))
                    }
                    withStyle(SpanStyle(color = Color.Red)) {
                        append(words)
                    }
                    withStyle(SpanStyle()) {
                        append(text.substring(wordsIndex + words.length, words1Index))
                    }
                    withStyle(SpanStyle(color = Color.Red)) {
                        append(words1)
                    }
                },
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun TextAnnotatedStringSamplePreview() {
    TextAnnotatedStringSample(MutableStateFlow(true))
}


@Composable
private fun TextClickableAnnotatedStringSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem3(
        "Text???AnnotatedString - PartialHighlighting - Clickable???",
        allExpandFlow,
        padding = 20.dp
    ) {
        val annotatedText = buildAnnotatedString {
            withStyle(SpanStyle()) {
                append(text.substring(0, wordsIndex))
            }

            pushStringAnnotation(tag = "URL", annotation = "http://www.daomubiji.com")
            withStyle(
                SpanStyle(
                    color = Color.Blue,
                    textDecoration = TextDecoration.Underline
                )
            ) {
                append(words)
            }
            pop()

            withStyle(SpanStyle()) {
                append(text.substring(wordsIndex + words.length, words1Index))
            }

            pushStringAnnotation(tag = "URL", annotation = words1)
            withStyle(
                SpanStyle(
                    color = Color.Blue,
                    textDecoration = TextDecoration.Underline
                )
            ) {
                append(words1)
            }
            pop()
        }
        MyTextContainer {
            ClickableText(
                text = annotatedText,
            ) { offset ->
                annotatedText.getStringAnnotations(tag = "URL", offset, offset).firstOrNull()?.let {
                    context.startActivity(Intent(Intent.ACTION_VIEW).apply {
                        data = Uri.parse(it.item)
                        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    })
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun TextClickableAnnotatedStringSamplePreview() {
    TextClickableAnnotatedStringSample(MutableStateFlow(true))
}


@Composable
private fun TextSelectableSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem3("Text???Selectable???", allExpandFlow, padding = 20.dp) {
        MyTextContainer {
            SelectionContainer {
                Text(text = text)
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun TextSelectableSamplePreview() {
    TextSelectableSample(MutableStateFlow(true))
}