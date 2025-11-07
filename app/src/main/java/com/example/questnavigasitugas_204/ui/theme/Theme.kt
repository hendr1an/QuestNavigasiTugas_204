package com.example.questnavigasitugas_204.ui.theme // <- Ganti dengan package name Anda

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

// Ini adalah skema warna untuk Dark Mode (bisa diabaikan jika Anda tidak menggunakannya)
private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

// --- INI BAGIAN PENTING ---
// Skema warna untuk Light Mode yang kita sesuaikan
private val LightColorScheme = lightColorScheme(
    primary = AppPurpleDark,      // Warna utama (tombol, appbar)
    onPrimary = AppWhite,         // Teks di atas warna utama
    secondary = AppPurpleLight,   // Warna sekunder (tombol kedua)
    onSecondary = Color.Black,    // Teks di atas warna sekunder
    background = AppBackground,   // Background seluruh aplikasi
    onBackground = Color.Black,   // Teks di atas background
    surface = Color.White,        // Warna untuk Card
    onSurface = Color.Black       // Teks di atas Card
)

@Composable
fun QuestNavigasiTugas_204Theme( // <- Ganti nama Tema ini jika nama proyek Anda beda
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        // --- Gunakan skema warna terang kita ---
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb() // Set warna status bar
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography, // Ini mengambil dari file Typography.kt
        content = content
    )
}