package com.klenovn.finalspaceapp.presentation.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class ExtendedColors(
    val flowItemBackground: Color,
    val sectionHeaderBackground: Color,
    val sectionContentBackground: Color
)

val LocalExtendedColors = staticCompositionLocalOf {
    ExtendedColors(
        flowItemBackground = Color.Unspecified,
        sectionHeaderBackground = Color.Unspecified,
        sectionContentBackground = Color.Unspecified
    )
}

object ExtendedTheme {
    val colors: ExtendedColors
        @Composable
        get() = LocalExtendedColors.current
}