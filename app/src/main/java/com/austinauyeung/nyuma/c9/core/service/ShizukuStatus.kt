package com.austinauyeung.nyuma.c9.core.service

enum class ShizukuStatus {
    UNKNOWN,                // Initial state
    NOT_AVAILABLE,          // Shizuku not available
    PERMISSION_REQUIRED,    // Shizuku available but needs permission
    READY,                  // Shizuku ready
    ERROR,                  // Error during initialization
}
