package com.github.panpf.android.compose.samples.ui.base

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonColors
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TitleRadioButton(
    selected: Boolean,
    onClick: (() -> Unit),
    title: (@Composable () -> Unit),
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    colors: RadioButtonColors = RadioButtonDefaults.colors(),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {
    Row(
        modifier = Modifier
            .heightIn(min = 40.dp)
            .clickable { onClick() }
            .then(modifier),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = selected,
            onClick = null,
            enabled = enabled,
            colors = colors,
            interactionSource = interactionSource
        )
        Spacer(modifier = Modifier.size(8.dp))
        title()
    }
}

@Composable
fun TitleRadioButton(
    selected: Boolean,
    onClick: (() -> Unit),
    title: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    colors: RadioButtonColors = RadioButtonDefaults.colors(),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {
    TitleRadioButton(
        selected = selected,
        onClick = onClick,
        title = { androidx.compose.material3.Text(text = title) },
        modifier = modifier,
        enabled = enabled,
        colors = colors,
        interactionSource = interactionSource
    )
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun TitleRadioButtonPreview() {
    Column {
        Row {
            TitleRadioButton(true, title = "横图", onClick = {})
            Spacer(modifier = Modifier.size(20.dp))
            TitleRadioButton(false, title = "竖图", onClick = {})
        }
        Row {
            TitleRadioButton(true, title = "小图", onClick = {})
            Spacer(modifier = Modifier.size(20.dp))
            TitleRadioButton(false, title = "大图", onClick = {})
        }
    }
}