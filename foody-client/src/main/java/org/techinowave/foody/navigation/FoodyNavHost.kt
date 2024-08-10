package org.techinowave.foody.navigation

import androidx.compose.animation.core.tween
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
import org.techinowave.foody.presentation.features.auth.screen.FoodyAuthScreen
import org.techinowave.foody.presentation.features.home.screen.FoodyHomeScreen
import org.techinowave.foody.presentation.features.home.viewmodel.HomeViewModel
import org.techinowave.foody.presentation.features.onboarding.screen.FoodyOnboardingScreen
import org.techinowave.foody.presentation.features.onboarding.viewmodel.OnboardingViewModel

@Composable
fun FoodyNavHost(
    modifier: Modifier = Modifier,
    startDestination: FoodyDestinations
) {

    val rootNavigator = rememberNavController()

    NavHost(
        navController = rootNavigator,
        startDestination = startDestination,
        enterTransition = { slideInHorizontally(tween(600)) { it } },
        exitTransition = { slideOutHorizontally(tween(600)) { -it } },
        popEnterTransition = { slideInHorizontally(tween(600)) { -it } },
        popExitTransition = { slideOutHorizontally(tween(600)) { it } },
        modifier = modifier.fillMaxSize()
    ) {

        composable<FoodyDestinations.Onboarding> {

            val onboardingVm = koinViewModel<OnboardingViewModel>()
            val onboardingUiState by onboardingVm.onboardingState.collectAsState()

            FoodyOnboardingScreen(
                uiEvent = onboardingVm::onEvent,
                uiState = onboardingUiState,
                navigateToHome = {
                    rootNavigator.navigate(FoodyDestinations.Home) {
                        popUpTo(FoodyDestinations.Onboarding) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable<FoodyDestinations.Home> {

            val homeVm = koinViewModel<HomeViewModel>()
            val homeUiState by homeVm.homeUiState.collectAsState()

            FoodyHomeScreen(
                uiEvent = homeVm::onEvent,
                uiState = homeUiState,
                navigateToOnboarding = {
                    rootNavigator.navigate(FoodyDestinations.Onboarding) {
                        popUpTo(FoodyDestinations.Home) {
                            inclusive = true
                        }
                    }
                },
                navigateToLogin = {
                    rootNavigator.navigate(FoodyDestinations.Auth)
                }
            )
        }

        composable<FoodyDestinations.Auth> {
            FoodyAuthScreen()
        }
    }
}