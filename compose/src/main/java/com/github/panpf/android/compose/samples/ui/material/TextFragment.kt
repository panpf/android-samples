package com.github.panpf.android.compose.samples.ui.material

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
import androidx.compose.material.Text
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
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.MaterialComposeAppBarFragment
import com.github.panpf.android.compose.samples.ui.base.MyColor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class TextFragment : MaterialComposeAppBarFragment() {

    override fun getTitle(): String {
        return "Text - Material"
    }

    @Composable
    override fun DrawContent() {
        ExpandableLayout { allExpandFlow ->
            TextSample(allExpandFlow)

            // todo TextStyle - brush
            // todo TextStyle - alpha
            TextTextStyleColorSample(allExpandFlow)
            TextTextStyleFontSizeSample(allExpandFlow)
            TextTextStyleFontWeightSample(allExpandFlow)
            TextTextStyleFontStyleSample(allExpandFlow)
            // todo TextStyle - fontSynthesis
            TextTextStyleFontFamilySample(allExpandFlow)
            // todo TextStyle - fontFeatureSettings
            TextTextStyleLetterSpacingSample(allExpandFlow)
            TextTextStyleBaselineShiftSample(allExpandFlow)
            TextTextStyleTextGeometricTransformSample(allExpandFlow)
            // todo TextStyle - localList
            TextTextStyleBackgroundSample(allExpandFlow)
            TextTextStyleTextDecorationSample(allExpandFlow)
            TextTextStyleShadowSample(allExpandFlow)
            // todo TextStyle - drawStyle
            TextTextStyleTextAlignSample(allExpandFlow)
            TextTextStyleTextDirectionSample(allExpandFlow)
            TextTextStyleLineHeightSample(allExpandFlow)
            TextTextStyleTextIndentSample(allExpandFlow)
            // todo TextStyle - platformStyle
            // todo TextStyle - lineHeightStyle
            // todo TextStyle - lineBreak
            // todo TextStyle - hyphens
            // todo TextStyle - textMotion

            TextOverflowSample(allExpandFlow)
            TextSoftWrapSample(allExpandFlow)
            TextMaxLinesSample(allExpandFlow)
            TextMinLinesSample(allExpandFlow)
            TextAnnotatedStringSample(allExpandFlow)
            TextClickableAnnotatedStringSample(allExpandFlow)
            TextSelectableSample(allExpandFlow)
            // todo emoji
        }
    }
}

private const val TEXT =
    "躲过了暴风雪之后，我们再次起程赶路，在一处斜坡下发现了阿宁他们的马队，同时也发现了海底墓穴影画之中的那一座神秘雪山，赫然出现在了我们的视野尽头。就在我们询问向导如何才能到达那里的时候，顺子却摇头，说我们绝对无法过去。\n          ----摘自《盗墓笔记》 - 云顶天宫（下）第一章 五圣雪山，网址：http://www.daomubiji.com/yun-ding-tian-gong-15.html"
private const val WORDS = "《盗墓笔记》"
private const val WORDS1 = "http://www.daomubiji.com/yun-ding-tian-gong-15.html"
private val wordsIndex = TEXT.indexOf(WORDS).apply { require(this != -1) }
private val words1Index = TEXT.indexOf(WORDS1).apply { require(this != -1 && this > wordsIndex) }

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
    ExpandableItem("Text", allExpandFlow, padding = 20.dp) {
        MyTextContainer {
            Text(text = TEXT)
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun TextSamplePreview() {
    TextSample(remember { MutableStateFlow(true) })
}


// todo TextStyle - brush


// todo TextStyle - alpha


@Composable
private fun TextTextStyleColorSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem("Text（TextStyle - color）", allExpandFlow, padding = 20.dp) {
        MyTextContainer {
            Text(text = TEXT, color = Color.Magenta)
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun TextTextStyleColorSamplePreview() {
    TextTextStyleColorSample(remember { MutableStateFlow(true) })
}


@Composable
private fun TextTextStyleFontSizeSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem("Text（TextStyle - fontSize）", allExpandFlow, padding = 20.dp) {
        MyTextContainer {
            Text(text = TEXT, fontSize = 20.sp)
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun TextTextStyleFontSizeSamplePreview() {
    TextTextStyleFontSizeSample(MutableStateFlow(true))
}


@Composable
private fun TextTextStyleFontWeightSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem("Text（TextStyle - fontWeight）", allExpandFlow, padding = 20.dp) {
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
                Text(text = TEXT, fontWeight = pair.second)
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun TextTextStyleFontWeightSamplePreview() {
    TextTextStyleFontWeightSample(MutableStateFlow(true))
}


@Composable
private fun TextTextStyleFontStyleSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem("Text（TextStyle - fontStyle - Italic）", allExpandFlow, padding = 20.dp) {
        MyTextContainer {
            Text(text = TEXT, fontStyle = FontStyle.Italic)
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun TextTextStyleFontStyleSamplePreview() {
    TextTextStyleFontStyleSample(MutableStateFlow(true))
}


// todo TextStyle - fontSynthesis


@Composable
private fun TextTextStyleFontFamilySample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem("Text（TextStyle - fontFamily）", allExpandFlow, padding = 20.dp) {
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
                Text(text = TEXT, fontFamily = pair.second)
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun TextTextStyleFontFamilySamplePreview() {
    TextTextStyleFontFamilySample(MutableStateFlow(true))
}


// todo TextStyle - fontFeatureSettings


@Composable
private fun TextTextStyleLetterSpacingSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem("Text（TextStyle - letterSpacing）", allExpandFlow, padding = 20.dp) {
        MyTextContainer {
            Text(text = TEXT, letterSpacing = 8.sp)
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun TextTextStyleLetterSpacingSamplePreview() {
    TextTextStyleLetterSpacingSample(MutableStateFlow(true))
}


@Composable
private fun TextTextStyleBaselineShiftSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem("Text（TextStyle - baselineShift）", allExpandFlow, padding = 20.dp) {
        MyTextContainer {
            Text(
                text = TEXT,
                style = TextStyle(baselineShift = BaselineShift(2f)),
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun TextTextStyleBaselineShiftSamplePreview() {
    TextTextStyleBaselineShiftSample(MutableStateFlow(true))
}


@Composable
private fun TextTextStyleTextGeometricTransformSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem("Text（TextStyle - textGeometricTransform）", allExpandFlow, padding = 20.dp) {
        MyTextContainer {
            Text(
                text = TEXT,
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
private fun TextTextStyleTextGeometricTransformSamplePreview() {
    TextTextStyleTextGeometricTransformSample(MutableStateFlow(true))
}


// todo TextStyle - localList


@Composable
private fun TextTextStyleBackgroundSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem("Text（TextStyle - background）", allExpandFlow, padding = 20.dp) {
        MyTextContainer {
            Text(
                text = TEXT,
                style = TextStyle(background = Color.Red),
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun TextTextStyleBackgroundSamplePreview() {
    TextTextStyleBackgroundSample(MutableStateFlow(true))
}


@Composable
private fun TextTextStyleTextDecorationSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem("Text（TextStyle - textDecoration）", allExpandFlow, padding = 20.dp) {
        Text(text = "Underline")
        MyTextContainer {
            Text(text = TEXT, textDecoration = TextDecoration.Underline)
        }

        Spacer(modifier = Modifier.size(10.dp))
        Text(text = "LineThrough")
        MyTextContainer {
            Text(text = TEXT, textDecoration = TextDecoration.LineThrough)
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun TextTextStyleTextDecorationUnderlineSamplePreview() {
    TextTextStyleTextDecorationSample(MutableStateFlow(true))
}


@Composable
private fun TextTextStyleShadowSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem("Text（TextStyle - shadow）", allExpandFlow, padding = 20.dp) {
        MyTextContainer {
            Text(
                text = TEXT,
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
private fun TextTextStyleShadowSamplePreview() {
    TextTextStyleShadowSample(MutableStateFlow(true))
}


// todo TextStyle - drawStyle


@Composable
private fun TextTextStyleTextAlignSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem("Text（TextStyle - textAlign）", allExpandFlow, padding = 20.dp) {
        Text(text = "Start")
        MyTextContainer {
            Text(text = TEXT, textAlign = TextAlign.Start)
        }

        Spacer(modifier = Modifier.size(10.dp))
        Text(text = "Center")
        MyTextContainer {
            Text(text = TEXT, textAlign = TextAlign.Center)
        }

        Spacer(modifier = Modifier.size(10.dp))
        Text(text = "End")
        MyTextContainer {
            Text(text = TEXT, textAlign = TextAlign.End)
        }

        Spacer(modifier = Modifier.size(10.dp))
        Text(text = "Justify")
        MyTextContainer {
            Text(text = TEXT, textAlign = TextAlign.Justify)
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun TextTextStyleTextAlignSamplePreview() {
    TextTextStyleTextAlignSample(MutableStateFlow(true))
}


@Composable
private fun TextTextStyleTextDirectionSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem("Text（TextStyle - textDirection）", allExpandFlow, padding = 20.dp) {
        MyTextContainer {
            Text(
                text = TEXT,
                style = TextStyle(textDirection = TextDirection.Rtl),
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun TextTextStyleTextDirectionSamplePreview() {
    TextTextStyleTextDirectionSample(MutableStateFlow(true))
}


@Composable
private fun TextTextStyleLineHeightSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem("Text（TextStyle - lineHeight）", allExpandFlow, padding = 20.dp) {
        MyTextContainer {
            Text(text = TEXT, lineHeight = 28.sp)
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun TextTextStyleLineHeightSamplePreview() {
    TextTextStyleLineHeightSample(MutableStateFlow(true))
}


@Composable
private fun TextTextStyleTextIndentSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem("Text（TextStyle - textIndent）", allExpandFlow, padding = 20.dp) {
        MyTextContainer {
            Text(
                text = TEXT,
                style = TextStyle(textIndent = TextIndent(firstLine = 40.sp, restLine = 10.sp)),
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun TextTextStyleTextIndentSamplePreview() {
    TextTextStyleTextIndentSample(MutableStateFlow(true))
}

// todo TextStyle - platformStyle
// todo TextStyle - lineHeightStyle
// todo TextStyle - lineBreak
// todo TextStyle - hyphens
// todo TextStyle - textMotion


@Composable
private fun TextOverflowSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem("Text（overflow）", allExpandFlow, padding = 20.dp) {
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
                    text = TEXT,
                    modifier = Modifier.alpha(0f)
                )    // Not visible, in order for Box to get full display height
                Text(
                    text = TEXT,
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
    ExpandableItem("Text（softWrap）", allExpandFlow, padding = 20.dp) {
        Text(text = "true")
        MyTextContainer {
            Text(text = TEXT, softWrap = true)
        }

        Spacer(modifier = Modifier.size(10.dp))
        Text(text = "false")
        MyTextContainer {
            Text(text = TEXT, softWrap = false)
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
    ExpandableItem("Text（maxLines）", allExpandFlow, padding = 20.dp) {
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
                    text = TEXT,
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
private fun TextMinLinesSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem("Text（minLines）", allExpandFlow, padding = 20.dp) {
        Text(text = "minLines=2，但内容很短")
        MyTextContainer {
            Text(
                text = "这是一段很短的内容",
                minLines = 2,
            )
        }

        Spacer(modifier = Modifier.size(10.dp))
        Text(text = "minLines=2，但内容很长")
        MyTextContainer {
            Text(
                text = "这是一段很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长超过 2 行的内容",
                minLines = 2,
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun TextMinLinesSamplePreview() {
    TextMinLinesSample(MutableStateFlow(true))
}


@Composable
private fun TextAnnotatedStringSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem("Text（AnnotatedString - PartialHighlighting）", allExpandFlow, padding = 20.dp) {
        MyTextContainer {
            Text(
                text = buildAnnotatedString {
                    withStyle(SpanStyle()) {
                        append(TEXT.substring(0, wordsIndex))
                    }
                    withStyle(SpanStyle(color = Color.Red)) {
                        append(WORDS)
                    }
                    withStyle(SpanStyle()) {
                        append(TEXT.substring(wordsIndex + WORDS.length, words1Index))
                    }
                    withStyle(SpanStyle(color = Color.Red)) {
                        append(WORDS1)
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
    ExpandableItem(
        "Text（AnnotatedString - PartialHighlighting - Clickable）",
        allExpandFlow,
        padding = 20.dp
    ) {
        val annotatedText = buildAnnotatedString {
            withStyle(SpanStyle()) {
                append(TEXT.substring(0, wordsIndex))
            }

            pushStringAnnotation(tag = "URL", annotation = "http://www.daomubiji.com")
            withStyle(
                SpanStyle(
                    color = Color.Blue,
                    textDecoration = TextDecoration.Underline
                )
            ) {
                append(WORDS)
            }
            pop()

            withStyle(SpanStyle()) {
                append(TEXT.substring(wordsIndex + WORDS.length, words1Index))
            }

            pushStringAnnotation(tag = "URL", annotation = WORDS1)
            withStyle(
                SpanStyle(
                    color = Color.Blue,
                    textDecoration = TextDecoration.Underline
                )
            ) {
                append(WORDS1)
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
    ExpandableItem("Text（Selectable）", allExpandFlow, padding = 20.dp) {
        MyTextContainer {
            SelectionContainer {
                Text(text = TEXT)
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun TextSelectableSamplePreview() {
    TextSelectableSample(MutableStateFlow(true))
}