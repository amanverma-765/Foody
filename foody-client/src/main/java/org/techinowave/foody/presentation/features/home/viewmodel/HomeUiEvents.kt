package org.techinowave.foody.presentation.features.home.viewmodel

sealed interface HomeUiEvents {

    data object GetMenuItems : HomeUiEvents
    data object GetUserEntry: HomeUiEvents

}