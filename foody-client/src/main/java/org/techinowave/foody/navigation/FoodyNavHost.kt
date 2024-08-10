package org.techinowave.foody.navigation

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.koin.androidx.compose.koinViewModel
import org.techinowave.foody.presentation.features.home.screen.FoodyHomeScreen
import org.techinowave.foody.presentation.features.home.viewmodel.HomeViewModel
import org.techinowave.foody.presentation.features.onboarding.screen.FoodyOnboardingScreen

@Composable
fun FoodyNavHost(
    modifier: Modifier = Modifier,
    startDestination: FoodyDestinations
) {

    val rootNavigator = rememberNavController()

    NavHost(
        navController = rootNavigator,
        startDestination = startDestination,
        enterTransition = { slideInHorizontally { it } },
        exitTransition = { slideOutHorizontally { -it } },
        popEnterTransition = { slideInHorizontally { -it } },
        popExitTransition = { slideOutHorizontally { it } },
        modifier = modifier.fillMaxSize()
    ) {

        composable<FoodyDestinations.Onboarding> {
            FoodyOnboardingScreen(
                navigateToHome = {
                    rootNavigator.navigate(FoodyDestinations.Home)
                }
            )
        }

        composable<FoodyDestinations.Home> {

            val homeVm = koinViewModel<HomeViewModel>()
            val homeUiState by homeVm.homeUiState.collectAsState()

            FoodyHomeScreen(
                uiEvent = homeVm::onEvent,
                uiState = homeUiState
            )
        }
    }
}