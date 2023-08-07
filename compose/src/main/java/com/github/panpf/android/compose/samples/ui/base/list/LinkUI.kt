package com.github.panpf.android.compose.samples.ui.base.list

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.panpf.android.samples.model.Link
import com.github.panpf.android.samples.model.LinkGroup
import com.github.panpf.android.compose.samples.ui.base.theme.MyTheme3

@Composable
fun LinkGroup(linkGroup: LinkGroup, onClick: () -> Unit) {
    BoxListItem(onClick = onClick) {
        Text(modifier = Modifier.fillMaxWidth(), text = linkGroup.title)
    }
}

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

@Composable
fun LinkGroupList(
    linkGroupList: List<LinkGroup>,
    allExpandFlow: State<Boolean>,
    onChildClick: (Int, Link) -> Unit
) {
    var expandedMap by remember {
        mutableStateOf(mapOf<Int, Boolean>())
    }
    LazyColumn {
        itemsIndexed(linkGroupList) { index, linkGroup ->
            Column(modifier = Modifier.fillMaxWidth()) {
                LinkGroup(linkGroup) {
                    val expanded = (expandedMap[index] ?: false || allExpandFlow.value)
                    expandedMap = expandedMap.plus(index to !expanded)
                }

                val expanded = (expandedMap[index] ?: false || allExpandFlow.value)
                if (expanded) {
                    MyDivider()
                }
                AnimatedVisibility(visible = expanded, modifier = Modifier.padding(start = 20.dp)) {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        linkGroup.links.forEachIndexed { index, link ->
                            LinkItem(link) {
                                onChildClick(index, link)
                            }
                            if (index < linkGroup.links.size - 1) {
                                MyDivider()
                            }
                        }
                    }
                }
                MyDivider()
            }
        }
    }
}