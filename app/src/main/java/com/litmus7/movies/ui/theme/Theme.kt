package com.litmus7.movies.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = DarkBlue10,
    onPrimary = White90,
//    primaryContainer = Green90,
//    onPrimaryContainer = Green10,
//    inversePrimary = Green80,
    secondary = MediumDarkBlue20,
    onSecondary = White90,
    secondaryContainer = Red40,
//    onSecondaryContainer = DarkGreen10,
//    tertiary = Violet40,
//    onTertiary = Color.White,
//    tertiaryContainer = Violet90,
//    onTertiaryContainer = Violet10,
    error = Red40,
    onError = White90,
    errorContainer = Red90,
    onErrorContainer = Red10,
    background = DarkBlue10,
//    onBackground = Grey10,
//    surface = ,
//    onSurface = White90,
//    inverseSurface = Grey20,
//    inverseOnSurface = Grey95,
//    surfaceVariant = GreenGrey90,
//    onSurfaceVariant = GreenGrey30,
//    outline = GreenGrey50
)

private val LightColorScheme = lightColorScheme(
    primary = LightBlue90,
    onPrimary = White20,
//    primaryContainer = Green90,
//    onPrimaryContainer = Green10,
//    inversePrimary = Green80,
    secondary = LightBlue80,
    onSecondary = White20,
    secondaryContainer = Red40,
//    onSecondaryContainer = DarkGreen10,
//    tertiary = Violet40,
//    onTertiary = Color.White,
//    tertiaryContainer = Violet90,
//    onTertiaryContainer = Violet10,
    error = Red40,
    onError = White90,
    errorContainer = Red90,
    onErrorContainer = Red10,
    background = LightBlue90,
//    onBackground = Grey10,
//    surface = GreenGrey90,
//    onSurface = GreenGrey30,
//    inverseSurface = Grey20,
//    inverseOnSurface = Grey95,
//    surfaceVariant = GreenGrey90,
//    onSurfaceVariant = GreenGrey30,
//    outline = GreenGrey50
)

@Composable
fun MoviesTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}