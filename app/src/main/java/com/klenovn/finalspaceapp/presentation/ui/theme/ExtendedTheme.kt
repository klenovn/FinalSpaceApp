package com.klenovn.finalspaceapp.presentation.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class ExtendedColors(
    val flowBackground: Color
)

val LocalExtendedColors = staticCompositionLocalOf {
    ExtendedColors(
        flowBackground = Color.Unspecified
    )
}

object ExtendedTheme {
    val colors: ExtendedColors
        @Composable
        get() = LocalExtendedColors.current
}