package com.example.anitalkprototype.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val AniTalkColors = darkColorScheme(
    primary = AniTalkBlue,
    onPrimary = AniTalkText,
    secondary = AniTalkTeal,
    background = AniTalkTeal,
    surface = AniTalkSurface,
    onSurface = AniTalkText,
    onBackground = AniTalkText
)

@Composable
fun AniTalkTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = AniTalkColors,
        typography = Typography,
        content = content
    )
}
