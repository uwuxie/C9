package com.austinauyeung.nyuma.c9.settings.repository

import com.austinauyeung.nyuma.c9.settings.domain.OverlaySettings
import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    fun getSettings(): Flow<OverlaySettings>
    suspend fun updateSettings(settings: OverlaySettings)
    suspend fun validateAndUpdateSettings(settings: OverlaySettings): OverlaySettings.ValidationResult
}
