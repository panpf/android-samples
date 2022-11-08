package com.github.panpf.android.compose.samples.ui.base.list

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.github.panpf.android.compose.samples.model.Link
import com.github.panpf.android.compose.samples.ui.base.theme.MyTheme3

@Composable
fun LinkItem(link: Link, onClick: () -> Unit) {
    BoxListItem(onClick = onClick) {
        Text(modifier = Modifier.fillMaxWidth(), text = link.title)
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun LinkItemPreview() {
    MyTheme3 {
        LinkItem(link = Link("示例示例示例示例示例示例示例示例示例示例示例示例示例示例示例示例示例")) {

        }
    }
}

@Composable
fun LinkList(linkList: List<Link>, onClick: (Int, Link) -> Unit) {
    LazyColumn {
        itemsIndexed(linkList) { index, link ->
            LinkItem(link) {
                onClick(index, link)
            }
            MyDivider()
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun LinkListPreview() {
    val links = listOf(
        Link("Text"),
        Link("Button"),
        Link("Image"),
        Link("Radio Button"),
        Link("Checkbox")
    )
    MyTheme3 {
        LinkList(links) { _, _ ->

        }
    }
}