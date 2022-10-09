package com.github.panpf.android.compose.samples.ui.widgets.text

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

const val text =
    "躲过了暴风雪之后，我们再次起程赶路，在一处斜坡下发现了阿宁他们的马队，同时也发现了海底墓穴影画之中的那一座神秘雪山，赫然出现在了我们的视野尽头。就在我们询问向导如何才能到达那里的时候，顺子却摇头，说我们绝对无法过去。\n          ----摘自《盗墓笔记》 - 云顶天宫（下）第一章 五圣雪山，网址：http://www.daomubiji.com/yun-ding-tian-gong-15.html"

@Composable
fun TextUI() {
    Column(Modifier.verticalScroll(rememberScrollState())) {
        Title("无任何额外属性设置")
        Text(text = text)

        Title("加点边距")
        Text(text = text, modifier = Modifier.padding(16.dp))

        Title("改为紫色")
        Text(text = text, color = Color.Magenta)

        Title("字体大一些")
        Text(text = text, fontSize = 16.sp)

        Title("改为斜体")
        Text(text = text, fontStyle = FontStyle.Italic)

        Title("字重大一些")
        Text(text = text, fontWeight = FontWeight.Bold)

        Title("换个字体")
        Text(text = text, fontFamily = FontFamily.Cursive)

        Title("加大字间距")
        Text(text = text, letterSpacing = 8.sp)

        Title("下划线")
        Text(text = text, textDecoration = TextDecoration.Underline)

        Title("删除线")
        Text(text = text, textDecoration = TextDecoration.LineThrough)

        Title("文本居中对齐")
        Text(text = text, textAlign = TextAlign.Center)

        Title("加大行高")
        Text(text = text, lineHeight = 28.sp)

        Title("内容溢出（...）")
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

        Title("内容溢出（裁剪）")
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

        Title("内容溢出（可见）")
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
        Spacer(modifier = Modifier.height(170.dp))

        Title("关闭换行")
        Text(
            text = text,
            softWrap = false,
        )

        Title("最多两行（...）")
        Text(
            text = text,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Composable
fun Title(title: String) {
    Text(
        text = title,
        modifier = Modifier.padding(top = 20.dp, start = 16.dp, end = 16.dp, bottom = 8.dp),
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Italic,
    )
}

@Preview(showBackground = true)
@Composable
fun TextUIPreview() {
    TextUI()
}