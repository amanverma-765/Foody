package org.techinowave.foody.koin

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import org.techinowave.foody.presentation.features.auth.viewmodel.AuthViewmodel
import org.techinowave.foody.presentation.features.home.viewmodel.HomeViewModel
import org.techinowave.foody.presentation.features.onboarding.viewmodel.OnboardingViewModel

val presentationModule = module {

    viewModel {
        HomeViewModel(
            menuItemsUseCase = get(),
            preferenceDbUseCase = get(),
            userAuthUseCase = get()
        )
    }

    viewModel {
        OnboardingViewModel(preferenceDbUseCase = get())
    }

    viewModel {
        AuthViewmodel(userAuthUseCase = get())
    }
}