package com.github.panpf.android.compose.samples.ui.base.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color

object MyThemeColors3 {

    private var colors: Colors? = null

    val current: Colors
        @Composable
        @ReadOnlyComposable
        get() {
            val oldColors = colors
            val currentColorScheme = MaterialTheme.colorScheme
            return if (oldColors != null && oldColors.colorScheme == currentColorScheme) {
                oldColors
            } else {
                val newColors = Colors(currentColorScheme)
                colors = newColors
                newColors
            }
        }
}

class Colors(val colorScheme: ColorScheme) {
    val primary: Color by lazy { colorScheme.primary }
    val primaryTranslucency: Color by lazy { colorScheme.primary.copy(alpha = 0.5f) }
    val primaryContainer: Color by lazy { colorScheme.primaryContainer }
    val onPrimary: Color by lazy { colorScheme.onPrimary }
    val inversePrimary: Color by lazy { colorScheme.inversePrimary }

    val tertiary: Color by lazy { colorScheme.tertiary }
    val tertiaryTranslucency: Color by lazy { colorScheme.tertiary.copy(alpha = 0.5f) }
    val tertiaryContainer: Color by lazy { colorScheme.tertiaryContainer }
    val onTertiary: Color by lazy { colorScheme.onTertiary }
}