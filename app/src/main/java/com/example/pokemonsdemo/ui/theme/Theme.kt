package com.example.pokemonsdemo.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = PrDarkGreen,
    primaryVariant = PrVarDarkGreen,
    secondary = SecDarkWhite,
    secondaryVariant = SecVarDarkWhite,
    background = BackgroundDarkGreen,
    onPrimary = OnPrDarkWhite,
    onSecondary = OnSecDarkGreen,
    onBackground = OnBackgroundDarkWhite,
)

private val LightColorPalette = lightColors(
    primary = PrLightBlue,
    primaryVariant = PrVarLightBlue,
    secondary = SecLightPink,
    secondaryVariant = SecVarLightPink,
    background = BackgroundLightBlue,
    onPrimary = OnPrLightWhite,
    onSecondary = OnSecLightBlue,
    onBackground = OnBackgroundLightBlue,
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