package org.techinowave.foody.presentation.features.onboarding.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.techinowave.domain.usecase.PreferenceDbUseCase
import org.techinowave.foody.utils.Constants.USER_ENTRY

class OnboardingViewModel(
    private val preferenceDbUseCase: PreferenceDbUseCase
) : ViewModel() {

    private val _onboardingState = MutableStateFlow(OnboardingUiStates())
    val onboardingState = _onboardingState.asStateFlow()

    fun onEvent(event: OnboardingUiEvents) {
        when (event) {
            OnboardingUiEvents.SaveUserEntry -> saveUserEntry()
        }
    }

    private fun saveUserEntry() {
        viewModelScope.launch {
            preferenceDbUseCase.saveToPreference(data = true, key = USER_ENTRY).collect { result ->
                Log.e("TAG", "saveUserEntryState: $result")
                _onboardingState.update { uiState ->
                    uiState.copy(userEntryResponse = result)
                }
            }
        }
    }

}