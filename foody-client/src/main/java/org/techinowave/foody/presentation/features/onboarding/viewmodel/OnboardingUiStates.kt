package org.techinowave.foody.presentation.features.onboarding.viewmodel

import org.techinowave.utils.ApiResponse

data class OnboardingUiStates(
    val userEntryResponse: ApiResponse<Unit> = ApiResponse.Loading
)
