package com.austinauyeung.nyuma.c9.accessibility.ui

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.PixelFormat
import android.graphics.Rect
import android.os.Build
import android.os.Looper
import android.view.Gravity
import android.view.WindowManager
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.setViewTreeLifecycleOwner
import androidx.savedstate.SavedStateRegistryOwner
import androidx.savedstate.setViewTreeSavedStateRegistryOwner
import com.austinauyeung.nyuma.c9.accessibility.coordinator.AccessibilityServiceManager
import com.austinauyeung.nyuma.c9.common.domain.OrientationHandler
import com.austinauyeung.nyuma.c9.common.ui.C9Theme
import com.austinauyeung.nyuma.c9.core.logs.Logger
import com.austinauyeung.nyuma.c9.cursor.ui.CursorOverlay
import com.austinauyeung.nyuma.c9.gesture.ui.GestureVisualization
import com.austinauyeung.nyuma.c9.grid.ui.GridOverlay
import com.austinauyeung.nyuma.c9.settings.domain.OverlaySettings
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

/**
 * Creates and updates the overlay according to observed state changes.
 */
class OverlayUIManager(
    private val context: Context,
    private val backgroundScope: CoroutineScope,
    private val mainScope: CoroutineScope,
    private val windowManager: WindowManager,
    private val settingsFlow: StateFlow<OverlaySettings>,
    private val orientationHandler: OrientationHandler,
    private val accessibilityManager: AccessibilityServiceManager,
    private val lifecycleOwner: LifecycleOwner,
    private val savedStateRegistryOwner: SavedStateRegistryOwner
) {
    private var overlayView: ComposeView? = null
    private var currentPaths: Int? = null
    private var isOrientationChanging = false

    fun initialize() {
        try {
            Logger.i("Initializing OverlayUIManager")
            observeStateChanges()
        } catch (e: Exception) {
            Logger.e("Error initializing OverlayUIManager", e)
        }
    }

    private fun observeStateChanges() {
        accessibilityManager.currentGrid
            .onEach { grid ->
                Logger.d("Grid state changed: ${grid != null}")
                mainScope.launch {
                    updateOverlayUI()
                }
            }
            .launchIn(backgroundScope)

        accessibilityManager.currentCursor
            .onEach { cursor ->
                Logger.d("Cursor state changed: ${cursor != null}")
                mainScope.launch {
                    updateOverlayUI()
                }
            }
            .launchIn(backgroundScope)

        accessibilityManager.getGesturePaths()
            .onEach { paths ->
                if (currentPaths != paths.size) {
                    Logger.d("Gesture paths changed: ${paths.size} paths")
                }
                currentPaths = paths.size
                mainScope.launch {
                    updateOverlayUI()
                }
            }
            .launchIn(backgroundScope)

        settingsFlow
            .onEach { settings ->
                Logger.d("Settings changed")
                accessibilityManager.updateGestureVisualization(settings.showGestureVisualization)
                mainScope.launch {
                    updateOverlayUI()
                }
            }
            .launchIn(backgroundScope)
    }

    fun updateOverlayUI() {
        try {
            if (Looper.myLooper() != Looper.getMainLooper()) {
                Logger.e("updateOverlayUI was called from a non-main thread")
                mainScope.launch { updateOverlayUI() }
                return
            }

//            if (isOrientationChanging) {
//                Logger.d("Skipping overlay update during orientation change")
//                return
//            }

            val grid = accessibilityManager.currentGrid.value
            val cursor = accessibilityManager.currentCursor.value
            val gesturePaths = accessibilityManager.getGesturePaths().value
            val settings = settingsFlow.value

            val shouldShowOverlay = grid != null ||
                    cursor != null ||
                    (gesturePaths.isNotEmpty() && settings.showGestureVisualization)

            if (!shouldShowOverlay) {
                removeOverlayView()
                return
            }

            if (overlayView == null) {
                createOverlayView()
            } else {
                try {
                    windowManager.updateViewLayout(overlayView, createOverlayLayoutParams())
                } catch (e: Exception) {
                    Logger.e("Failed to update overlay layout", e)
                }
            }

            overlayView?.setContent {
                val currentSettings by settingsFlow.collectAsState()
                val currentGesturePaths by accessibilityManager.getGesturePaths().collectAsState()
                val currentOrientation by orientationHandler.currentOrientation.collectAsState()
                val dimensions by orientationHandler.screenDimensions.collectAsState()

                C9Theme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = Color.Transparent,
                    ) {
                        val currentGrid by accessibilityManager.currentGrid.collectAsState()
                        currentGrid?.let { activeGrid ->
                            GridOverlay(
                                grid = activeGrid,
                                opacity = currentSettings.overlayOpacity,
                                hideNumbers = currentSettings.hideNumbers,
                                orientation = currentOrientation,
                                useRotatedNumbers = currentSettings.rotateButtonsWithOrientation,
                                gridLineVisibility = currentSettings.gridLineVisibility
                            )
                        }

                        val currentCursor by accessibilityManager.currentCursor.collectAsState()
                        currentCursor?.let { activeCursor ->
                            CursorOverlay(
                                cursorState = activeCursor,
                                settings = currentSettings,
                                dimensions = dimensions
                            )
                        }

                        if (currentSettings.showGestureVisualization && currentGesturePaths.isNotEmpty()) {
                            GestureVisualization(
                                gesturePaths = currentGesturePaths,
                                settings = currentSettings,
                                dimensions = dimensions
                            )
                        }
                    }
                }
            }
        } catch (e: Exception) {
            Logger.e("Error updating overlay UI", e)
            try {
                mainScope.launch {
                    removeOverlayView()
                    if (accessibilityManager.currentGrid.value != null ||
                        accessibilityManager.currentCursor.value != null
                    ) {
                        createOverlayView()
                    }
                }
            } catch (innerE: Exception) {
                Logger.e("Failed to recover overlay UI", innerE)
            }
        }
    }

    @SuppressLint("ObsoleteSdkInt")
    private fun createOverlayView() {
        try {
            if (Looper.myLooper() != Looper.getMainLooper()) {
                Logger.e("createOverlayView was called from a non-main thread")
                mainScope.launch { createOverlayView() }
                return
            }

            val composeView = ComposeView(context)
            composeView.setViewTreeLifecycleOwner(lifecycleOwner)
            composeView.setViewTreeSavedStateRegistryOwner(savedStateRegistryOwner)
            composeView.setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnDetachedFromWindow)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                composeView.setOnApplyWindowInsetsListener { _, insets ->
                    mainScope.launch { updateOverlayUI() }
                    insets
                }
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                composeView.setOnApplyWindowInsetsListener { _, insets ->
                    mainScope.launch { updateOverlayUI() }
                    insets
                }
            }

            overlayView = composeView

            windowManager.addView(overlayView, createOverlayLayoutParams())
            Logger.d("Overlay view created and added to window manager")
        } catch (e: Exception) {
            Logger.e("Failed to add overlay view", e)
            overlayView = null
        }
    }

    private fun removeOverlayView() {
        try {
            if (Looper.myLooper() != Looper.getMainLooper()) {
                Logger.e("removeOverlayView was called from a non-main thread")
                mainScope.launch { removeOverlayView() }
                return
            }

            overlayView?.let { view ->
                windowManager.removeView(view)
                overlayView = null
                Logger.d("Overlay view removed")
            }
        } catch (e: Exception) {
            Logger.e("Failed to remove overlay view", e)
            overlayView = null
        }
    }

    @SuppressLint("ObsoleteSdkInt")
    @Suppress("Deprecation")
    fun getInsetsFromView(): Rect {
        val view = overlayView ?: return Rect(0, 0, 0, 0)

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            view.rootWindowInsets?.let {
                Rect(
                    it.systemWindowInsetLeft,
                    it.systemWindowInsetTop,
                    it.systemWindowInsetRight,
                    it.systemWindowInsetBottom
                )
            } ?: Rect(0, 0, 0, 0)
        } else {
            Rect(0, 0, 0, 0)
        }
    }

    private fun createOverlayLayoutParams(): WindowManager.LayoutParams {
        val settings = settingsFlow.value
        val insets = if (!settings.usePhysicalSize) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                orientationHandler.getSystemInsets()
            } else {
                getInsetsFromView()
            }
        } else {
            Rect(0, 0, 0, 0)
        }

        return WindowManager.LayoutParams().apply {
            type = WindowManager.LayoutParams.TYPE_ACCESSIBILITY_OVERLAY
            flags = (
                    WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                            or WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
                            or WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN
                            or WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
                    )
            format = PixelFormat.TRANSLUCENT
            gravity = Gravity.TOP or Gravity.START

            x = insets.left
            y = insets.top
            width = WindowManager.LayoutParams.MATCH_PARENT
            height = WindowManager.LayoutParams.MATCH_PARENT
        }
    }

    fun cleanup() {
        overlayView?.disposeComposition()
        mainScope.launch {
            removeOverlayView()
        }
        Logger.d("OverlayUIManager cleanup completed")
    }
}