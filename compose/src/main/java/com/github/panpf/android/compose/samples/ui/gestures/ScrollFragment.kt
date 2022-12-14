package com.github.panpf.android.compose.samples.ui.gestures

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.panpf.android.compose.samples.R
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem3
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ScrollFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "Scroll"
    }

    @Composable
    override fun DrawContent() {
        ExpandableLayout { allExpandFlow ->
            ScrollVerticalScrollSample(allExpandFlow)
            ScrollHorizontalScrollSample(allExpandFlow)
            ScrollScrollableSample(allExpandFlow)
            // todo ScrollNestedScrollSample(allExpandFlow)
        }
    }
}


private const val text =
    "????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????----???????????????????????? - ?????????????????????????????? ????????????????????????http://www.daomubiji.com/yun-ding-tian-gong-15.html"


@Composable
private fun ScrollVerticalScrollSample(allExpandFlow: Flow<Boolean>) {
    val desc = """
        verticalScroll ??????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
    """.trimIndent()
    val coroutineScope = rememberCoroutineScope()
    val scrollState = rememberScrollState()
    ExpandableItem3(
        title = "Scroll???verticalScroll???",
        allExpandFlow,
        padding = 20.dp,
        desc = desc,
    ) {
        Text(text = "reverseScrolling=false")
        Text(
            text = text,
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.5f))
                .verticalScroll(scrollState, reverseScrolling = false)
        )

        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "reverseScrolling=true")
        Text(
            text = text,
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.5f))
                .verticalScroll(scrollState, reverseScrolling = true)
        )

        Spacer(modifier = Modifier.height(20.dp))
        Column(modifier = Modifier.align(Alignment.CenterHorizontally)) {
            IconButton(onClick = {
                coroutineScope.launch {
                    scrollState.animateScrollBy(-100f)
                }
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_up),
                    contentDescription = "up"
                )
            }
            Text(
                text = "${scrollState.value}",
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            IconButton(onClick = {
                coroutineScope.launch {
                    scrollState.animateScrollBy(100f)
                }
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_down),
                    contentDescription = "down"
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun ScrollVerticalScrollSamplePreview() {
    ScrollVerticalScrollSample(remember { MutableStateFlow(true) })
}


@Composable
private fun ScrollHorizontalScrollSample(allExpandFlow: Flow<Boolean>) {
    val desc = """
        horizontalScroll ??????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
    """.trimIndent()
    val coroutineScope = rememberCoroutineScope()
    val scrollState = rememberScrollState()
    ExpandableItem3(
        title = "Scroll???horizontalScroll???",
        allExpandFlow,
        padding = 20.dp,
        desc = desc,
    ) {
        Text(text = "reverseScrolling=false")
        Text(
            text = text,
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.5f))
                .horizontalScroll(scrollState, reverseScrolling = false)
        )

        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "reverseScrolling=true")
        Text(
            text = text,
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.5f))
                .horizontalScroll(scrollState, reverseScrolling = true)
        )

        Spacer(modifier = Modifier.height(20.dp))
        Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
            IconButton(onClick = {
                coroutineScope.launch {
                    scrollState.animateScrollBy(-100f)
                }
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_left),
                    contentDescription = "left"
                )
            }
            Text(
                text = "${scrollState.value}",
                modifier = Modifier
                    .width(30.dp)
                    .align(Alignment.CenterVertically),
                textAlign = TextAlign.Center
            )
            IconButton(onClick = {
                coroutineScope.launch {
                    scrollState.animateScrollBy(100f)
                }
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_right),
                    contentDescription = "right"
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun ScrollHorizontalScrollSamplePreview() {
    ScrollHorizontalScrollSample(remember { MutableStateFlow(true) })
}


@Composable
private fun ScrollScrollableSample(allExpandFlow: Flow<Boolean>) {
    val desc = """
        |       scrollable ??????????????????????????????????????????????????????????????? ScrollableState ?????????????????????
        |       ?????? ScrollableState ??????????????????????????? consumeScrollDelta ??????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
        |       ???????????????????????????????????????????????????????????????????????? scrollable ???????????????????????????????????????????????????????????????
    """.trimMargin()
    var xDeltaTotal by remember { mutableStateOf(0f) }
    var yDeltaTotal by remember { mutableStateOf(0f) }
    ExpandableItem3(
        title = "Scroll???scrollable???",
        allExpandFlow,
        padding = 20.dp,
        desc = desc,
    ) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Column(modifier = Modifier.weight(1f)) {
                BoxWithConstraints(
                    Modifier
                        .size(150.dp, 50.dp)
                        .scrollable(
                            orientation = Orientation.Horizontal,
                            // Scrollable state: describes how to consume
                            // scrolling delta and update offset
                            state = rememberScrollableState { delta ->
                                xDeltaTotal += delta
                                delta
                            }
                        )
                        .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.5f))
                        .clipToBounds(),
                ) {
                    val maxWidthInt = with(LocalDensity.current) { maxWidth.toPx() }.toInt()
                    val maxHeightInt = with(LocalDensity.current) { maxHeight.toPx() }.toInt()
                    val xOffset by remember {
                        derivedStateOf {
                            xDeltaTotal
                                .toInt()
                                .coerceIn(0, maxWidthInt - maxHeightInt)
                        }
                    }
                    Row(
                        modifier = Modifier
                            .offset(x = with(LocalDensity.current) { xOffset.toDp() }, y = 0.dp)
                            .size(maxHeight)
                            .background(MaterialTheme.colorScheme.primary),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow_left),
                            contentDescription = "left",
                            modifier = Modifier.size(20.dp),
                            tint = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f)
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow_right),
                            contentDescription = "right",
                            modifier = Modifier.size(20.dp),
                            tint = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f)
                        )
                    }
                }
                Text("offset: $xDeltaTotal")
            }

            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                BoxWithConstraints(
                    Modifier
                        .size(50.dp, 150.dp)
                        .scrollable(
                            orientation = Orientation.Vertical,
                            // Scrollable state: describes how to consume
                            // scrolling delta and update offset
                            state = rememberScrollableState { delta ->
                                yDeltaTotal += delta
                                delta
                            }
                        )
                        .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.5f))
                        .clipToBounds(),
                ) {
                    val maxWidthInt = with(LocalDensity.current) { maxWidth.toPx() }.toInt()
                    val maxHeightInt = with(LocalDensity.current) { maxHeight.toPx() }.toInt()
                    val yOffset by remember {
                        derivedStateOf {
                            yDeltaTotal
                                .toInt()
                                .coerceIn(0, maxHeightInt - maxWidthInt)
                        }
                    }
                    Column(
                        modifier = Modifier
                            .offset(x = 0.dp, y = with(LocalDensity.current) { yOffset.toDp() })
                            .size(maxWidth)
                            .background(MaterialTheme.colorScheme.primary),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow_up),
                            contentDescription = "up",
                            modifier = Modifier.size(20.dp),
                            tint = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f)
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow_down),
                            contentDescription = "down",
                            modifier = Modifier.size(20.dp),
                            tint = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f)
                        )
                    }
                }
                Text("offset: $yDeltaTotal")
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun ScrollScrollableSamplePreview() {
    ScrollScrollableSample(remember { MutableStateFlow(true) })
}