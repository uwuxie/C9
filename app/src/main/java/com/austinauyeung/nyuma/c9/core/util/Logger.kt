package com.austinauyeung.nyuma.c9.core.util

import android.util.Log
import com.austinauyeung.nyuma.c9.BuildConfig

object Logger {
    private const val TAG = "C9App"

    enum class Level {
        VERBOSE,
        DEBUG,
        INFO,
        WARNING,
        ERROR,
    }

    private val minLogLevel = if (BuildConfig.DEBUG) Level.VERBOSE else Level.VERBOSE

    fun v(
        message: String,
        tag: String? = null,
    ) {
        if (minLogLevel.ordinal <= Level.VERBOSE.ordinal) {
            Log.v(tag ?: TAG, message)
        }
    }

    fun d(
        message: String,
        tag: String? = null,
    ) {
        if (minLogLevel.ordinal <= Level.DEBUG.ordinal) {
            Log.d(tag ?: TAG, message)
        }
    }

    fun i(
        message: String,
        tag: String? = null,
    ) {
        if (minLogLevel.ordinal <= Level.INFO.ordinal) {
            Log.i(tag ?: TAG, message)
        }
    }

    fun w(
        message: String,
        throwable: Throwable? = null,
        tag: String? = null,
    ) {
        if (minLogLevel.ordinal <= Level.WARNING.ordinal) {
            if (throwable != null) {
                Log.w(tag ?: TAG, message, throwable)
            } else {
                Log.w(tag ?: TAG, message)
            }
        }
    }

    fun e(
        message: String,
        throwable: Throwable? = null,
        tag: String? = null,
    ) {
        if (minLogLevel.ordinal <= Level.ERROR.ordinal) {
            if (throwable != null) {
                Log.e(tag ?: TAG, message, throwable)
            } else {
                Log.e(tag ?: TAG, message)
            }
        }
    }

    fun getStackTraceString(throwable: Throwable): String {
        return Log.getStackTraceString(throwable)
    }
}
