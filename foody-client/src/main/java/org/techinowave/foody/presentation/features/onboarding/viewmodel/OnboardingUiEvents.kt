package org.techinowave.foody.presentation.features.onboarding.viewmodel

sealed interface OnboardingUiEvents {

    data object SaveUserEntry: OnboardingUiEvents

}