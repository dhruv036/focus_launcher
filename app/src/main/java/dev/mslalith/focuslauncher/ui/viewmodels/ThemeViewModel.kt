package dev.mslalith.focuslauncher.ui.viewmodels

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.mslalith.focuslauncher.data.di.modules.ThemeProvider
import dev.mslalith.focuslauncher.data.models.Theme
import dev.mslalith.focuslauncher.data.utils.AppCoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ThemeViewModel @Inject constructor(
    @ThemeProvider private val themeDataStore: DataStore<Preferences>,
    private val appCoroutineDispatcher: AppCoroutineDispatcher
) : ViewModel() {

    val currentThemeStateFlow = currentThemeFlow.withinScope(initialValue = null)

    private val currentThemeFlow: Flow<Theme?>
        get() = themeDataStore.data.map {
            val themeName = it[themePreferenceKey] ?: return@map null
            Theme.valueOf(themeName)
        }

    fun changeTheme(theme: Theme) {
        launch { themeDataStore.edit { it[themePreferenceKey] = theme.name } }
    }

    private fun launch(
        run: suspend () -> Unit
    ) = viewModelScope.launch(appCoroutineDispatcher.io) { run() }

    private fun <T> Flow<T>.withinScope(
        initialValue: T
    ) = stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = initialValue
    )

    companion object {
        val themePreferenceKey = stringPreferencesKey("theme_preference_key")

        val DEFAULT_THEME = Theme.SAID_DARK
        val allThemes = Theme.values().toList()
    }
}
