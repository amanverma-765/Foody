package org.techinowave.foody.presentation.features.auth.viewmodel

sealed interface AuthUiEvents {

    data class SignInWithGoogle(val idToken: String, val nonce: String): AuthUiEvents

}