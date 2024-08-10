package org.techinowave.foody.koin

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import org.techinowave.foody.presentation.features.home.viewmodel.HomeViewModel

val presentationModule = module {

    viewModel {
        HomeViewModel(menuItemsUseCase = get())
    }
}