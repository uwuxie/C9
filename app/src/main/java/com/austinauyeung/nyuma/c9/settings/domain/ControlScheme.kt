package com.austinauyeung.nyuma.c9.settings.domain

/**
 * Represents standard cursor schemes.
 */
enum class ControlScheme {
    STANDARD,       // D-pad moves cursor, numpad scrolls
    SWAPPED,        // D-pad scrolls, numpad moves cursor
    TOGGLE_MODE,    // Press activation key to toggle between move and scroll modes
}
