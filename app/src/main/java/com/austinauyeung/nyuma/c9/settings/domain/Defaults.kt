package com.austinauyeung.nyuma.c9.settings.domain

import android.view.KeyEvent
import com.austinauyeung.nyuma.c9.common.domain.GestureStyle
import com.austinauyeung.nyuma.c9.core.constants.CursorConstants
import com.austinauyeung.nyuma.c9.core.constants.GestureConstants
import com.austinauyeung.nyuma.c9.core.constants.GridConstants

/**
 * Contains default values that can be modified by the user.
 */
object Defaults {
    object Settings {
        const val GRID_LEVELS = GridConstants.DEFAULT_LEVELS
        const val OVERLAY_OPACITY = GridConstants.DEFAULT_OPACITY
        const val PERSIST_OVERLAY = false
        const val HIDE_NUMBERS = false
        const val USE_NATURAL_SCROLLING = false
        const val SHOW_GESTURE_VISUAL = false
        const val CURSOR_SPEED = CursorConstants.DEFAULT_SPEED
        const val CURSOR_ACCELERATION = CursorConstants.DEFAULT_ACCELERATION
        const val CURSOR_SIZE = CursorConstants.DEFAULT_SIZE
        const val GRID_ACTIVATION_KEY = KeyEvent.KEYCODE_POUND
        const val CURSOR_ACTIVATION_KEY = KeyEvent.KEYCODE_STAR
        const val CURSOR_WRAP_AROUND = false
        val CONTROL_SCHEME = ControlScheme.STANDARD
        val GESTURE_STYLE = GestureStyle.FIXED
        const val TOGGLE_HOLD = false
        const val GESTURE_DURATION = GestureConstants.DEFAULT_GESTURE_DURATION
        const val SCROLL_MULTIPLIER = GestureConstants.DEFAULT_SCROLL_MULTIPLIER
    }
}
