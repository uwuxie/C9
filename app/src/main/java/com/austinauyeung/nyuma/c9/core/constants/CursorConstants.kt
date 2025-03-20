package com.austinauyeung.nyuma.c9.core.constants

object CursorConstants {
    // Speed
    const val MIN_SPEED = 1
    const val MAX_SPEED = 5
    const val DEFAULT_SPEED = 3
    const val DEFAULT_SPEED_MULTIPLIER = 30
    const val DEFAULT_EXPONENT = 2

    // Accelerated speed
    const val MIN_ACCELERATION = 1
    const val MAX_ACCELERATION = 5
    const val DEFAULT_ACCELERATION = 1
    const val DEFAULT_ACCELERATION_MULTIPLIER = 60
    const val ACCELERATION_THRESHOLD = 500L

    // Size
    const val MIN_SIZE = 1
    const val MAX_SIZE = 5
    const val DEFAULT_SIZE = 3
    const val SIZE_MULTIPLIER = 10f

    private const val TARGET_FPS = 60
    const val FRAME_DURATION_MS = 1000f / TARGET_FPS
    const val OPACITY = 0.8f

}