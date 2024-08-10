package org.techinowave.foody.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface FoodyDestinations {

    @Serializable
    data object Onboarding: FoodyDestinations

    @Serializable
    data object Home: FoodyDestinations

    @Serializable
    data object Auth: FoodyDestinations
}