package com.github.panpf.android.compose.samples.ui.widgets.text

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
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
import com.github.panpf.android.compose.samples.R
import com.github.panpf.android.compose.samples.ui.base.list.ExpandableListItem
import com.github.panpf.tools4a.toast.ktx.showLongToast
import kotlinx.coroutines.flow.MutableStateFlow

const val text =
    "躲过了暴风雪之后，我们再次起程赶路，在一处斜坡下发现了阿宁他们的马队，同时也发现了海底墓穴影画之中的那一座神秘雪山，赫然出现在了我们的视野尽头。就在我们询问向导如何才能到达那里的时候，顺子却摇头，说我们绝对无法过去。\n          ----摘自《盗墓笔记》 - 云顶天宫（下）第一章 五圣雪山，网址：http://www.daomubiji.com/yun-ding-tian-gong-15.html"

@Composable
fun TextUI() {
    val allExpandFlow = remember { MutableStateFlow(true) }
    Column(
        Modifier
            .verticalScroll(rememberScrollState())
            .padding(bottom = 100.dp)
    ) {
        ExpandableListItem("默认样式", allExpandFlow) {
            Text(text = text)
        }

        ExpandableListItem("加大边距", allExpandFlow) {
            Text(text = text, modifier = Modifier.padding(16.dp))
        }

        ExpandableListItem("改为紫色", allExpandFlow) {
            Text(text = text, color = Color.Magenta)
        }

        ExpandableListItem("字体加大", allExpandFlow) {
            Text(text = text, fontSize = 18.sp)
        }

        ExpandableListItem("改为斜体", allExpandFlow) {
            Text(text = text, fontStyle = FontStyle.Italic)
        }

        ExpandableListItem("字重加大", allExpandFlow) {
            Text(text = text, fontWeight = FontWeight.Bold)
        }

        ExpandableListItem("换个字体", allExpandFlow) {
            Text(text = text, fontFamily = FontFamily.Cursive)
        }

        ExpandableListItem("字间距加大", allExpandFlow) {
            Text(text = text, letterSpacing = 8.sp)
        }

        ExpandableListItem("装饰（下划线）", allExpandFlow) {
            Text(text = text, textDecoration = TextDecoration.Underline)
        }

        ExpandableListItem("装饰（删除线）", allExpandFlow) {
            Text(text = text, textDecoration = TextDecoration.LineThrough)
        }

        ExpandableListItem("对齐（靠近头部）", allExpandFlow) {
            Text(text = text, textAlign = TextAlign.Start)
        }

        ExpandableListItem("对齐（居中）", allExpandFlow) {
            Text(text = text, textAlign = TextAlign.Center)
        }

        ExpandableListItem("对齐（靠近尾部）", allExpandFlow) {
            Text(text = text, textAlign = TextAlign.End)
        }

        ExpandableListItem("对齐（两端对齐）", allExpandFlow) {
            Text(text = text, textAlign = TextAlign.Justify)
        }

        ExpandableListItem("行高加大", allExpandFlow) {
            Text(text = text, lineHeight = 28.sp)
        }

        ExpandableListItem("内容溢出（...）", allExpandFlow) {
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
                    Text(
                        text = "占位",
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

        ExpandableListItem("内容溢出（裁剪）", allExpandFlow) {
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

        ExpandableListItem("内容溢出（可见）", allExpandFlow) {
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

        ExpandableListItem("换行关闭", allExpandFlow) {
            Text(
                text = text,
                softWrap = false,
            )
        }

        ExpandableListItem("最多两行（...）", allExpandFlow) {
            Text(
                text = text,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
        }

        // TextStyle(fontSynthesis:FontSynthesis)
        // TextStyle(fontFeatureSettings:String)

        ExpandableListItem("基线偏移（2f）", allExpandFlow) {
            Text(
                text = text,
                style = TextStyle(baselineShift = BaselineShift(2f)),
            )
        }

        ExpandableListItem("变换（放大 2 倍并倾斜）", allExpandFlow) {
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

        ExpandableListItem("背景", allExpandFlow) {
            Text(
                text = text,
                style = TextStyle(background = Color.Blue),
            )
        }

        ExpandableListItem("阴影", allExpandFlow) {
            Text(
                text = text,
                style = TextStyle(
                    shadow = Shadow(
                        Color.Black,
                        offset = Offset(4f, 4f),
                        blurRadius = 5f
                    )
                ),
            )
        }

        ExpandableListItem("方向（右到左）", allExpandFlow) {
            Text(
                text = text,
                style = TextStyle(textDirection = TextDirection.Rtl),
            )
        }

        ExpandableListItem("缩进", allExpandFlow) {
            Text(
                text = text,
                style = TextStyle(textIndent = TextIndent(20.sp, restLine = 10.sp)),
            )
        }

        val words = "《盗墓笔记》"
        val wordsIndex = text.indexOf(words)
        ExpandableListItem("部分高亮", allExpandFlow) {
            Text(
                text = buildAnnotatedString {
                    withStyle(SpanStyle()) {
                        append(text.substring(0, wordsIndex))
                    }
                    withStyle(SpanStyle(color = Color.Red)) {
                        append(words)
                    }
                    withStyle(SpanStyle()) {
                        append(text.substring(wordsIndex + words.length))
                    }
                },
            )
        }

        val context = LocalContext.current
        ExpandableListItem("部分高亮（可点击）", allExpandFlow) {
            ClickableText(
                text = buildAnnotatedString {
                    withStyle(SpanStyle()) {
                        append(text.substring(0, wordsIndex))
                    }
                    withStyle(SpanStyle(color = Color.Red)) {
                        append(words)
                    }
                    withStyle(SpanStyle()) {
                        append(text.substring(wordsIndex + words.length))
                    }
                },
            ) {
                if (it in wordsIndex until wordsIndex + words.length) {
                    context.showLongToast("点击了盗墓笔记")
                }
            }
        }

        ExpandableListItem("可选择复制", allExpandFlow) {
            SelectionContainer {
                Text(text = text)
            }
        }
    }
    Box(modifier = Modifier.fillMaxSize()) {
        FloatingActionButton(
            onClick = { allExpandFlow.value = !allExpandFlow.value },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(20.dp)
        ) {
            val allExpand by allExpandFlow.collectAsState()
            val iconId =
                if (allExpand) R.drawable.ic_expand_less else R.drawable.ic_expand_more
            Image(painterResource(id = iconId), contentDescription = "")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TextUIPreview() {
    TextUI()
}