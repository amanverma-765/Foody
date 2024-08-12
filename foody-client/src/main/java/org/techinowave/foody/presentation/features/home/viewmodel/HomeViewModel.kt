package org.techinowave.foody.presentation.features.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.techinowave.domain.usecase.MenuItemUseCase
import org.techinowave.domain.usecase.PreferenceDbUseCase
import org.techinowave.domain.usecase.UserAuthUseCase
import org.techinowave.foody.utils.Constants.USER_ENTRY

class HomeViewModel(
    private val menuItemsUseCase: MenuItemUseCase,
    private val preferenceDbUseCase: PreferenceDbUseCase,
    private val userAuthUseCase: UserAuthUseCase
) : ViewModel() {

    private val _homeUiState = MutableStateFlow(HomeUiStates())
    val homeUiState = _homeUiState.asStateFlow()

    fun onEvent(event: HomeUiEvents) {
        when (event) {
            HomeUiEvents.GetMenuItems -> getMenuItems()
            HomeUiEvents.GetUserEntry -> getUserEntry()
        }
    }

    init {
        listenAuthStatus()
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

    private fun getUserEntry() {
        viewModelScope.launch {
            preferenceDbUseCase.getFromPreference(key = USER_ENTRY).collect { response ->
                _homeUiState.update { state ->
                    state.copy(userEntryResponse = response)
                }
            }
        }
    }

    private fun listenAuthStatus() {
        viewModelScope.launch {
            userAuthUseCase.listenAuthState().collect { response ->
                _homeUiState.update { state ->
                    state.copy(authStatusResponse = response)
                }
            }
        }
    }

}