package dev.mslalith.focuslauncher.navigator

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.mslalith.focuslauncher.data.models.Screen
import dev.mslalith.focuslauncher.data.providers.LocalNavController
import dev.mslalith.focuslauncher.ui.screens.EditFavoritesScreen
import dev.mslalith.focuslauncher.ui.screens.HideAppsScreen
import dev.mslalith.focuslauncher.ui.screens.LauncherScreen
import dev.mslalith.focuslauncher.ui.screens.PickPlaceForLunarPhaseScreen

@Composable
fun AppNavigator() {
    val navController = LocalNavController.current

    NavHost(
        navController = navController,
        startDestination = Screen.Launcher.id
    ) {
        launcherScreen()
        editFavoritesScreen()
        hideAppsScreen()
        pickPlaceForLunarPhase()
    }
}

@OptIn(ExperimentalMaterialApi::class)
private fun NavGraphBuilder.launcherScreen() {
    composable(Screen.Launcher.id) {
        LauncherScreen(
            appsViewModel = hiltViewModel(it),
            homeViewModel = hiltViewModel(it),
            themeViewModel = hiltViewModel(it),
            settingsViewModel = hiltViewModel(it),
            widgetsViewModel = hiltViewModel(it)
        )
    }
}

private fun NavGraphBuilder.editFavoritesScreen() {
    composable(Screen.EditFavorites.id) {
        EditFavoritesScreen(
            appsViewModel = hiltViewModel(it)
        )
    }
}

private fun NavGraphBuilder.hideAppsScreen() {
    composable(Screen.HideApps.id) {
        HideAppsScreen(
            appsViewModel = hiltViewModel(it)
        )
    }
}

private fun NavGraphBuilder.pickPlaceForLunarPhase() {
    composable(Screen.PickPlaceForLunarPhase.id) {
        PickPlaceForLunarPhaseScreen(
            pickPlaceViewModel = hiltViewModel(it)
        )
    }
}
