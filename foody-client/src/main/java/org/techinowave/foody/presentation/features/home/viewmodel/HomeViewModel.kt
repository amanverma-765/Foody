package org.techinowave.foody.presentation.features.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.techinowave.foody.domain.usecase.MenuItemUseCase

class HomeViewModel(
    private val menuItemsUseCase: MenuItemUseCase
): ViewModel() {

    private val _homeUiState = MutableStateFlow(HomeUiStates())
    val homeUiState =  _homeUiState.asStateFlow()

    fun onEvent(event: HomeUiEvents) {
        when (event) {
            HomeUiEvents.GetMenuItems -> getMenuItems()
        }
    }

    private fun getMenuItems() {
        viewModelScope.launch {
            menuItemsUseCase.getMenuItems().collectLatest { response ->
                _homeUiState.update { state ->
                    state.copy(foodMenuItemResponse = response)
                }
            }
        }
    }

}