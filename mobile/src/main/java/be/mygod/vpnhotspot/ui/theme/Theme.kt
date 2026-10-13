package be.mygod.vpnhotspot.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MotionScheme
import androidx.compose.material3.Surface
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

/** Brand palette for devices without Material You, such as Android 11. */
private val LightPalette = lightColorScheme(
    primary = Color(0xFF006C66),
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFFCEEFEB),
    onPrimaryContainer = Color(0xFF0C3532),
    secondary = Color(0xFF466762),
    secondaryContainer = Color(0xFFD0E8E2),
    background = Color(0xFFF5FAF9),
    surface = Color(0xFFF5FAF9),
    surfaceVariant = Color(0xFFDDE8E5),
    surfaceContainer = Color(0xFFEBF3F1),
    onSurface = Color(0xFF162522),
    onSurfaceVariant = Color(0xFF46605A),
    outlineVariant = Color(0xFFCAD9D4),
)

private val DarkPalette = darkColorScheme(
    primary = Color(0xFF68DBCC),
    onPrimary = Color(0xFF003731),
    primaryContainer = Color(0xFF164943),
    onPrimaryContainer = Color(0xFFCBF3EB),
    secondary = Color(0xFFA1CEC6),
    secondaryContainer = Color(0xFF28534E),
    background = Color(0xFF0C1719),
    surface = Color(0xFF0C1719),
    surfaceVariant = Color(0xFF344D4A),
    surfaceContainer = Color(0xFF152528),
    onSurface = Color(0xFFE0F0EB),
    onSurfaceVariant = Color(0xFFA8C3BC),
    outlineVariant = Color(0xFF3B5551),
)

@Composable
@OptIn(ExperimentalMaterial3ExpressiveApi::class)
fun VpnHotspotTheme(dynamicColor: Boolean = true, content: @Composable () -> Unit) {
    val darkTheme = isSystemInDarkTheme()
    val colorScheme = if (dynamicColor && Build.VERSION.SDK_INT >= 31) {
        val context = LocalContext.current
        if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
    } else if (darkTheme) DarkPalette else LightPalette
    MaterialTheme(
        colorScheme = colorScheme,
        motionScheme = MotionScheme.expressive(),
        content = content,
    )
}

@Composable
fun VpnHotspotPreviewSurface(content: @Composable () -> Unit) {
    VpnHotspotTheme(dynamicColor = false) {
        Surface(content = content)
    }
}
