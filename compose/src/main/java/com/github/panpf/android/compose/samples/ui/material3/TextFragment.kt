package com.github.panpf.android.compose.samples.ui.material3

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.platform.ComposeView
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
import com.github.panpf.android.compose.samples.ui.base.ToolbarFragment
import com.github.panpf.android.compose.samples.ui.base.theme.MyTheme3
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class TextFragment : ToolbarFragment() {

    override fun createView(
        toolbar: Toolbar,
        inflater: LayoutInflater,
        parent: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        toolbar.title = "Text"
        return ComposeView(inflater.context).apply {
            setContent {
                MyTheme3 {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        ExpandableLayout { allExpandFlow ->
                            DefaultTextSample(allExpandFlow)
                            ColorTextSample(allExpandFlow)
                            FontSizeTextSample(allExpandFlow)
                            FontStyleSample(allExpandFlow)
                            FontWeightTextSample(allExpandFlow)
                            FontFamilyTextSample(allExpandFlow)
                            LetterSpacingTextSample(allExpandFlow)
                            TextDecorationUnderlineTextSample(allExpandFlow)
                            TextDecorationLineThroughTextSample(allExpandFlow)
                            TextAlignStartTextSample(allExpandFlow)
                            TextAlignCenterTextSample(allExpandFlow)
                            TextAlignEndTextSample(allExpandFlow)
                            TextAlignJustifyTextSample(allExpandFlow)
                            LineHeightTextSample(allExpandFlow)
                            OverflowEllipsisTextSample(allExpandFlow)
                            OverflowClipTextSample(allExpandFlow)
                            OverflowVisibleTextSample(allExpandFlow)
                            SoftWrapTextSample(allExpandFlow)
                            MaxLinesTextSample(allExpandFlow)
                            BaselineShiftTextSample(allExpandFlow)
                            TextGeometricTransformTextSample(allExpandFlow)
                            BackgroundTextSample(allExpandFlow)
                            ShadowTextSample(allExpandFlow)
                            TextDirectionTextSample(allExpandFlow)
                            TextIndentTextSample(allExpandFlow)
                            AnnotatedStringTextSample(allExpandFlow)
                            ClickableAnnotatedStringTextSample(allExpandFlow)
                            SelectionTextSample(allExpandFlow)
                            // todo TextStyle(fontSynthesis:FontSynthesis)
                            // todo TextStyle(fontFeatureSettings:String)
                            // todo 自定义字体
                            // todo 可下载字体
                        }
                    }
                }
            }
        }
    }
}

private const val text =
    "躲过了暴风雪之后，我们再次起程赶路，在一处斜坡下发现了阿宁他们的马队，同时也发现了海底墓穴影画之中的那一座神秘雪山，赫然出现在了我们的视野尽头。就在我们询问向导如何才能到达那里的时候，顺子却摇头，说我们绝对无法过去。\n          ----摘自《盗墓笔记》 - 云顶天宫（下）第一章 五圣雪山，网址：http://www.daomubiji.com/yun-ding-tian-gong-15.html"
private const val words = "《盗墓笔记》"
private const val words1 = "http://www.daomubiji.com/yun-ding-tian-gong-15.html"
private val wordsIndex = text.indexOf(words).apply { require(this != -1) }
private val words1Index = text.indexOf(words1).apply { require(this != -1 && this > wordsIndex) }

//@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
//@Composable
//fun TextUIPreview() {
//    TextUI()
//}


@Composable
fun DefaultTextSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem("默认", allExpandFlow) {
        Text(text = text)
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun DefaultTextSamplePreview() {
    DefaultTextSample(remember { MutableStateFlow(true) })
}


@Composable
fun ColorTextSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem("颜色（紫色）", allExpandFlow) {
        Text(text = text, color = Color.Magenta)
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun ColorTextSamplePreview() {
    ColorTextSample(remember { MutableStateFlow(true) })
}


@Composable
fun FontSizeTextSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem("字体大小（20.sp）", allExpandFlow) {
        Text(text = text, fontSize = 20.sp)
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun FontSizeTextSamplePreview() {
    FontSizeTextSample(MutableStateFlow(true))
}


@Composable
fun FontStyleSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem("字体样式（斜体）", allExpandFlow) {
        Text(text = text, fontStyle = FontStyle.Italic)
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun FontStyleSamplePreview() {
    FontStyleSample(MutableStateFlow(true))
}


@Composable
fun FontWeightTextSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem("字重（Bold-700）", allExpandFlow) {
        Text(text = text, fontWeight = FontWeight.Bold)
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun FontWeightTextSamplePreview() {
    FontWeightTextSample(MutableStateFlow(true))
}


@Composable
fun FontFamilyTextSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem("字体（Cursive）", allExpandFlow) {
        Text(text = text, fontFamily = FontFamily.Cursive)
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun FontFamilyTextSamplePreview() {
    FontFamilyTextSample(MutableStateFlow(true))
}


@Composable
fun LetterSpacingTextSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem("字间距加大", allExpandFlow) {
        Text(text = text, letterSpacing = 8.sp)
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun LetterSpacingTextSamplePreview() {
    LetterSpacingTextSample(MutableStateFlow(true))
}


@Composable
fun TextDecorationUnderlineTextSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem("装饰（下划线）", allExpandFlow) {
        Text(text = text, textDecoration = TextDecoration.Underline)
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun TextDecorationUnderlineTextSamplePreview() {
    TextDecorationUnderlineTextSample(MutableStateFlow(true))
}


@Composable
fun TextDecorationLineThroughTextSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem("装饰（删除线）", allExpandFlow) {
        Text(text = text, textDecoration = TextDecoration.LineThrough)
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun TextDecorationLineThroughTextSamplePreview() {
    TextDecorationLineThroughTextSample(MutableStateFlow(true))
}


@Composable
fun TextAlignStartTextSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem("对齐（靠近头部）", allExpandFlow) {
        Text(text = text, textAlign = TextAlign.Start)
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun TextAlignStartTextSamplePreview() {
    TextAlignStartTextSample(MutableStateFlow(true))
}


@Composable
fun TextAlignCenterTextSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem("对齐（居中）", allExpandFlow) {
        Text(text = text, textAlign = TextAlign.Center)
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun TextAlignCenterTextSamplePreview() {
    TextAlignCenterTextSample(MutableStateFlow(true))
}


@Composable
fun TextAlignEndTextSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem("对齐（靠近尾部）", allExpandFlow) {
        Text(text = text, textAlign = TextAlign.End)
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun TextAlignEndTextSamplePreview() {
    TextAlignEndTextSample(MutableStateFlow(true))
}


@Composable
fun TextAlignJustifyTextSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem("对齐（两端对齐）", allExpandFlow) {
        Text(text = text, textAlign = TextAlign.Justify)
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun TextAlignJustifyTextSamplePreview() {
    TextAlignJustifyTextSample(MutableStateFlow(true))
}


@Composable
fun LineHeightTextSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem("行高加大", allExpandFlow) {
        Text(text = text, lineHeight = 28.sp)
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun LineHeightTextSamplePreview() {
    LineHeightTextSample(MutableStateFlow(true))
}


@Composable
fun OverflowEllipsisTextSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem("内容溢出（...）", allExpandFlow) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .background(Color.Red)
            ) {
                Text(
                    text = text,
                    overflow = TextOverflow.Ellipsis,
                )
            }
            Text(text = "内容溢出占位内容溢出占位内容溢出占位")
            Text(text = "内容溢出占位内容溢出占位内容溢出占位")
            Text(text = "内容溢出占位内容溢出占位内容溢出占位")
            Text(text = "内容溢出占位内容溢出占位内容溢出占位")
            Text(text = "内容溢出占位内容溢出占位内容溢出占位")
            Text(text = "内容溢出占位内容溢出占位内容溢出占位")
            Text(text = "内容溢出占位内容溢出占位内容溢出占位")
            Text(text = "内容溢出占位内容溢出占位内容溢出占位")
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun OverflowEllipsisTextSamplePreview() {
    OverflowEllipsisTextSample(MutableStateFlow(true))
}


@Composable
fun OverflowClipTextSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem("内容溢出（裁剪）", allExpandFlow) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .background(Color.Red)
            ) {
                Text(
                    text = text,
                    overflow = TextOverflow.Clip,
                )
            }
            Text(text = "内容溢出占位内容溢出占位内容溢出占位")
            Text(text = "内容溢出占位内容溢出占位内容溢出占位")
            Text(text = "内容溢出占位内容溢出占位内容溢出占位")
            Text(text = "内容溢出占位内容溢出占位内容溢出占位")
            Text(text = "内容溢出占位内容溢出占位内容溢出占位")
            Text(text = "内容溢出占位内容溢出占位内容溢出占位")
            Text(text = "内容溢出占位内容溢出占位内容溢出占位")
            Text(text = "内容溢出占位内容溢出占位内容溢出占位")
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun OverflowClipTextSamplePreview() {
    OverflowClipTextSample(MutableStateFlow(true))
}


@Composable
fun OverflowVisibleTextSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem("内容溢出（可见）", allExpandFlow) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .background(Color.Red)
            ) {
                Text(
                    text = text,
                    overflow = TextOverflow.Visible,
                )
            }
            Text(text = "内容溢出占位内容溢出占位内容溢出占位")
            Text(text = "内容溢出占位内容溢出占位内容溢出占位")
            Text(text = "内容溢出占位内容溢出占位内容溢出占位")
            Text(text = "内容溢出占位内容溢出占位内容溢出占位")
            Text(text = "内容溢出占位内容溢出占位内容溢出占位")
            Text(text = "内容溢出占位内容溢出占位内容溢出占位")
            Text(text = "内容溢出占位内容溢出占位内容溢出占位")
            Text(text = "内容溢出占位内容溢出占位内容溢出占位")
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun OverflowVisibleTextSamplePreview() {
    OverflowVisibleTextSample(MutableStateFlow(true))
}


@Composable
fun SoftWrapTextSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem("换行关闭", allExpandFlow) {
        Text(
            text = text,
            softWrap = false,
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun SoftWrapTextSamplePreview() {
    SoftWrapTextSample(MutableStateFlow(true))
}


@Composable
fun MaxLinesTextSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem("最多两行（...）", allExpandFlow) {
        Text(
            text = text,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun MaxLinesTextSamplePreview() {
    MaxLinesTextSample(MutableStateFlow(true))
}


@Composable
fun BaselineShiftTextSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem("基线偏移（2f）", allExpandFlow) {
        Text(
            text = text,
            style = TextStyle(baselineShift = BaselineShift(2f)),
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun BaselineShiftTextSamplePreview() {
    BaselineShiftTextSample(MutableStateFlow(true))
}


@Composable
fun TextGeometricTransformTextSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem("变换（放大 2 倍并倾斜）", allExpandFlow) {
        Text(
            text = text,
            style = TextStyle(
                textGeometricTransform = TextGeometricTransform(
                    scaleX = 2.0f,
                    skewX = 2f
                )
            ),
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun TextGeometricTransformTextSamplePreview() {
    TextGeometricTransformTextSample(MutableStateFlow(true))
}


@Composable
fun BackgroundTextSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem("背景", allExpandFlow) {
        Text(
            text = text,
            style = TextStyle(background = Color.Blue),
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun BackgroundTextSamplePreview() {
    BackgroundTextSample(MutableStateFlow(true))
}


@Composable
fun ShadowTextSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem("阴影", allExpandFlow) {
        Text(
            text = text,
            style = TextStyle(
                shadow = Shadow(
                    Color.Black,
                    offset = Offset(4f, 8f),
                    blurRadius = 5f
                )
            ),
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun ShadowTextSamplePreview() {
    ShadowTextSample(MutableStateFlow(true))
}


@Composable
fun TextDirectionTextSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem("方向（右到左）", allExpandFlow) {
        Text(
            text = text,
            style = TextStyle(textDirection = TextDirection.Rtl),
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun TextDirectionTextSamplePreview() {
    TextDirectionTextSample(MutableStateFlow(true))
}


@Composable
fun TextIndentTextSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem("缩进", allExpandFlow) {
        Text(
            text = text,
            style = TextStyle(textIndent = TextIndent(20.sp, restLine = 10.sp)),
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun TextIndentTextSamplePreview() {
    TextIndentTextSample(MutableStateFlow(true))
}


@Composable
fun AnnotatedStringTextSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem("部分高亮", allExpandFlow) {
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

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun AnnotatedStringTextSamplePreview() {
    AnnotatedStringTextSample(MutableStateFlow(true))
}


@Composable
fun ClickableAnnotatedStringTextSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    ExpandableItem("部分高亮（可点击）", allExpandFlow) {
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

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun ClickableAnnotatedStringTextSamplePreview() {
    ClickableAnnotatedStringTextSample(MutableStateFlow(true))
}


@Composable
fun SelectionTextSample(allExpandFlow: Flow<Boolean>) {
    ExpandableItem("可选择复制", allExpandFlow) {
        SelectionContainer {
            Text(text = text)
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun SelectionTextSamplePreview() {
    SelectionTextSample(MutableStateFlow(true))
}