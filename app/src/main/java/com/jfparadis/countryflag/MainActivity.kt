package com.jfparadis.countryflag

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.darkColorScheme
import dagger.hilt.android.AndroidEntryPoint

// This is the main activity of the application
// For now, only the light color scheme is used. The template I used already contained the darkcolor
//  scheme, without using it.
// In fact, the light / dark primary / secondary are not even used throughout the application,
//      this should be added in a future step
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val lightColors = lightColorScheme(
            primary = androidx.compose.ui.graphics.Color(0xFF6200EE),
            secondary = androidx.compose.ui.graphics.Color(0xFF03DAC6)
        )

        val darkColors = darkColorScheme(
            primary = androidx.compose.ui.graphics.Color(0xFFBB86FC),
            secondary = androidx.compose.ui.graphics.Color(0xFF03DAC6)
        )

        setContent {
            MaterialTheme(
                colorScheme = lightColors, // Change this to `darkColors` for Dark Theme
                content = {
                    AppNavigation() // Your composable content
                }
            )
        }
    }
}
