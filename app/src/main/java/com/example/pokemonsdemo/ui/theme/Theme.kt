package com.example.pokemonsdemo.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = Green700,
    primaryVariant = Blue500,
    secondary = LightBlue300,
    secondaryVariant = LightBlue500,
    background = Green200,
    surface = Green700,
    onPrimary = LightBlue300,
    onSecondary = Green700,
    onBackground = LightBlue300,
    onSurface = LightBlue300
)

private val LightColorPalette = lightColors(
    primary = Blue500,
    primaryVariant = Blue700,
    secondary = Pink200,
    secondaryVariant = Pink700,
    background = LightBlue100,
    surface = LightBlue500,
    onPrimary = LightBlue100,
    onSecondary = Blue700,
    onBackground = Blue700,
    onSurface = Blue700
)

@Composable
fun PokemonsDemoTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}