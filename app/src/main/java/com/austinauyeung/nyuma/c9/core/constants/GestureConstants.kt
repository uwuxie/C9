package com.austinauyeung.nyuma.c9.core.constants

object GestureConstants {
    // Timing
    const val TAP_DURATION = 50L
    const val DRAG_SEGMENT_DURATION = 10L
    private const val FRAMES_PER_SECOND = 60
    private const val FRAME_DURATION_MS = 1000f / FRAMES_PER_SECOND

    const val MIN_GESTURE_DURATION = 100L
    const val MAX_GESTURE_DURATION = 500L
    const val DEFAULT_GESTURE_DURATION = 300L
    const val SCROLL_END_PAUSE = 50L
    const val CONTINUOUS_INITIAL_DELAY_FACTOR = 1.2
    const val CONTINUOUS_REPEAT_INTERVAL_FACTOR = 1.2

    // Scroll
    const val MIN_SCROLL_MULTIPLIER = 0.3f
    const val MAX_SCROLL_MULTIPLIER = 0.7f
    const val DEFAULT_SCROLL_MULTIPLIER = 0.5f
    const val USE_NATURAL_SCROLLING = false

    // Zoom
    const val ZOOM_DISTANCE_FACTOR = 0.15f
    const val ZOOM_DISTANCE_OFFSET = ZOOM_DISTANCE_FACTOR / 3

    // Visualization
    const val SHOW_GESTURE_VISUAL = true

    // Intercept
    const val ALLOW_PASSTHROUGH = false

    // Steps needed to maintain frame rate
    fun calculateSteps(duration: Long): Int {
        return ((duration / FRAME_DURATION_MS).toInt()).coerceAtLeast(1)
    }
}