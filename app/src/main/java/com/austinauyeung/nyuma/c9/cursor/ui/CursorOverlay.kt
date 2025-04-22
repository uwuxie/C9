package com.austinauyeung.nyuma.c9.cursor.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.austinauyeung.nyuma.c9.R
import com.austinauyeung.nyuma.c9.common.domain.ScreenDimensions
import com.austinauyeung.nyuma.c9.core.constants.CursorConstants
import com.austinauyeung.nyuma.c9.cursor.domain.CursorState
import com.austinauyeung.nyuma.c9.settings.domain.OverlaySettings

/**
 * Renders the cursor overlay using an animated GIF.
 */
@Composable
fun CursorOverlay(
    cursorState: CursorState,
    settings: OverlaySettings? = null,
    modifier: Modifier = Modifier,
    dimensions: ScreenDimensions
) {
    // Access the current density
    val density = LocalDensity.current

    // Calculate cursor size
    var cursorSize = settings?.cursorSize?.toFloat() ?: CursorConstants.DEFAULT_SIZE.toFloat()
    cursorSize *= CursorConstants.SIZE_MULTIPLIER * dimensions.getScreenScaleFactor()

    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        val position = cursorState.position

        // Convert position from pixels to dp
        val offsetX = with(density) { (position.x - cursorSize / 2).toDp() } // Align left edge
        val offsetY = with(density) { position.y.toDp() }

        // Draw the animated GIF at the cursor's position
        AsyncImage(
            model = R.drawable.animated_cursor, // Use the resource ID directly
            contentDescription = "Animated Cursor",
            modifier = Modifier
                .offset(x = offsetX, y = offsetY)
                .size(cursorSize.dp)
        )

        // Scroll Indicator
        if (cursorState.inScrollMode) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                val lineX = position.x - cursorSize * 0.28f
                val lineLength = cursorSize
                val lineHeight = cursorSize * 0.1f

                drawLine(
                    color = Color(0xFFFFD2DF), // New color: #FFD2DF
                    start = Offset(x = lineX, y = position.y),
                    end = Offset(x = lineX, y = position.y + lineLength),
                    strokeWidth = lineHeight,
                )
            }
        }
    }
}