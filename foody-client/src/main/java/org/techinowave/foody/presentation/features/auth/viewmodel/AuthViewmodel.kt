package org.techinowave.foody.presentation.features.auth.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.techinowave.domain.usecase.UserAuthUseCase

class AuthViewmodel(
    private val userAuthUseCase: UserAuthUseCase
) : ViewModel() {

    private val _authUiState = MutableStateFlow(AuthUiStates())
    val authUiState = _authUiState.asStateFlow()

    fun onEvent(event: AuthUiEvents) {
        when (event) {
            is AuthUiEvents.SignInWithGoogle -> signInWithGoogle(
                idToken = event.idToken,
                nonce = event.nonce
            )
        }
    }

    init {
        listenAuthState()
    }

    private fun signInWithGoogle(
        idToken: String,
        nonce: String
    ) {
        viewModelScope.launch {
            userAuthUseCase.signInWithGoogle(
                idToken = idToken,
                nonce = nonce
            ).collect { response ->
                _authUiState.value = _authUiState.value.copy(googleSignInResponse = response)
            }
        }
    }

    private fun listenAuthState() {
        viewModelScope.launch {
            userAuthUseCase.listenAuthState().collect { response ->
                _authUiState.value = _authUiState.value.copy(authStatusResponse = response)
            }
        }
    }
}