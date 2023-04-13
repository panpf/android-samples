package com.github.panpf.android.compose.samples.ui.customization.grid

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.panpf.android.compose.samples.R


@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun VerticalGridDefaultPreview() {
    Surface(
        Modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        val colorScheme = MaterialTheme.colorScheme
        VerticalGrid(
            rows = GridCells.Fixed(3),
            modifier = Modifier.background(colorScheme.primaryContainer)
        ) {
            repeat(7) {
                Text(
                    text = "Item ${it + 1}",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .border(width = 1.dp, color = colorScheme.tertiary)
                        .padding(10.dp)
                )
            }
        }
    }
}


@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun VerticalGridRowsFixedPreview() {
    Surface(
        Modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        val colorScheme = MaterialTheme.colorScheme
        Column(Modifier.fillMaxWidth()) {
            listOf(3, 2, 1).forEachIndexed { index, rows ->
                if (index > 0) {
                    Spacer(modifier = Modifier.size(20.dp))
                }
                Text(text = "Rows=Fixed($rows)")
                Spacer(modifier = Modifier.size(10.dp))
                VerticalGrid(
                    rows = GridCells.Fixed(rows),  // todo GridCells.Fixed(1) 时有 bug
                    modifier = Modifier.background(colorScheme.primaryContainer)
                ) {
                    repeat(7) {
                        Text(
                            text = "Item ${it + 1}",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                                .border(width = 1.dp, color = colorScheme.tertiary)
                                .padding(10.dp)
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun VerticalGridRowsAdaptivePreview() {
    Surface(
        Modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        val colorScheme = MaterialTheme.colorScheme
        Column(Modifier.fillMaxWidth()) {
            Text(text = "Rows=Adaptive(100.dp)")
            Spacer(modifier = Modifier.size(10.dp))
            var fillMaxWidthFraction by remember { mutableStateOf(0.6f) }
            VerticalGrid(
                rows = GridCells.Adaptive(100.dp),
                modifier = Modifier
                    .fillMaxWidth(fraction = fillMaxWidthFraction)
                    .background(colorScheme.primaryContainer)
            ) {
                repeat(7) {
                    Text(
                        text = "Item ${it + 1}",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .border(width = 1.dp, color = colorScheme.tertiary)
                            .padding(10.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row {
                FilledIconButton(
                    modifier = Modifier.weight(1f),
                    onClick = {
                        fillMaxWidthFraction = (fillMaxWidthFraction - 0.1f).coerceAtLeast(0.2f)
                    }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_subtract),
                        contentDescription = "subtract"
                    )
                }
                Spacer(modifier = Modifier.size(20.dp))
                Text(
                    text = "fillMaxWidthFraction=$fillMaxWidthFraction",
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
                Spacer(modifier = Modifier.size(20.dp))
                FilledIconButton(
                    modifier = Modifier.weight(1f),
                    onClick = {
                        fillMaxWidthFraction = (fillMaxWidthFraction + 0.1f).coerceAtMost(1f)
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = "add"
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun VerticalGridContentPaddingPreview() {
    Surface(
        Modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        val colorScheme = MaterialTheme.colorScheme
        Column(Modifier.fillMaxWidth()) {
            Text(text = "PaddingValues(20.dp)")
            Spacer(modifier = Modifier.size(10.dp))
            VerticalGrid(
                rows = GridCells.Fixed(3),
                modifier = Modifier.background(colorScheme.primaryContainer),
                contentPadding = PaddingValues(20.dp) // todo Invalid
            ) {
                repeat(7) {
                    Text(
                        text = "Item ${it + 1}",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .border(width = 1.dp, color = colorScheme.tertiary)
                            .padding(10.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.size(20.dp))
            Text(text = "PaddingValues(horizontal = 10.dp, vertical = 20.dp)")
            Spacer(modifier = Modifier.size(10.dp))
            VerticalGrid(
                rows = GridCells.Fixed(3),
                modifier = Modifier.background(colorScheme.primaryContainer),
                contentPadding = PaddingValues(horizontal = 10.dp, vertical = 20.dp) // todo Invalid
            ) {
                repeat(7) {
                    Text(
                        text = "Item ${it + 1}",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .border(width = 1.dp, color = colorScheme.tertiary)
                            .padding(10.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.size(20.dp))
            Text(text = "PaddingValues(start=10.dp, top=20.dp, end=30.dp, bottom=40.dp)")
            Spacer(modifier = Modifier.size(10.dp))
            VerticalGrid(
                rows = GridCells.Fixed(3),
                modifier = Modifier.background(colorScheme.primaryContainer),
                contentPadding = PaddingValues(
                    start = 10.dp,
                    top = 20.dp,
                    end = 30.dp,
                    bottom = 40.dp
                ) // todo Invalid
            ) {
                repeat(7) {
                    Text(
                        text = "Item ${it + 1}",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .border(width = 1.dp, color = colorScheme.tertiary)
                            .padding(10.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun VerticalGridArrangementPreview() {
    Surface(
        Modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        val colorScheme = MaterialTheme.colorScheme
        Column(Modifier.fillMaxWidth()) {
            Text(text = "verticalArrangement=Arrangement.spacedBy(10.dp)")
            Spacer(modifier = Modifier.size(10.dp))
            VerticalGrid(
                rows = GridCells.Fixed(3),
                modifier = Modifier.background(colorScheme.primaryContainer),
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                repeat(7) {
                    Text(
                        text = "Item ${it + 1}",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .border(width = 1.dp, color = colorScheme.tertiary)
                            .padding(10.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.size(20.dp))
            Text(text = "horizontalArrangement=Arrangement.spacedBy(10.dp)")
            Spacer(modifier = Modifier.size(10.dp))
            VerticalGrid(
                rows = GridCells.Fixed(3),
                modifier = Modifier.background(colorScheme.primaryContainer),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                repeat(7) {
                    Text(
                        text = "Item ${it + 1}",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .border(width = 1.dp, color = colorScheme.tertiary)
                            .padding(10.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.size(20.dp))
            Text(text = "verticalArrangement=Arrangement.spacedBy(10.dp)\nhorizontalArrangement=Arrangement.spacedBy(10.dp)")
            Spacer(modifier = Modifier.size(10.dp))
            VerticalGrid(
                rows = GridCells.Fixed(3),
                modifier = Modifier.background(colorScheme.primaryContainer),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                repeat(7) {
                    Text(
                        text = "Item ${it + 1}",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .border(width = 1.dp, color = colorScheme.tertiary)
                            .padding(10.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun VerticalGridReverseLayoutPreview() {
    Surface(
        Modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        val colorScheme = MaterialTheme.colorScheme
        VerticalGrid(
            rows = GridCells.Fixed(3),
            modifier = Modifier.background(colorScheme.primaryContainer),
            reverseLayout = true,   // todo Invalid
        ) {
            repeat(7) {
                Text(
                    text = "Item ${it + 1}",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .border(width = 1.dp, color = colorScheme.tertiary)
                        .padding(10.dp)
                )
            }
        }
    }
}