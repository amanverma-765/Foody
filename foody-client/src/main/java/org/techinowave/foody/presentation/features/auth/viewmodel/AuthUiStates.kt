package org.techinowave.foody.presentation.features.auth.viewmodel

import org.techinowave.domain.model.AuthStatus
import org.techinowave.utils.ApiResponse

data class AuthUiStates(

    val authStatusResponse: ApiResponse<AuthStatus> = ApiResponse.Loading,
    val googleSignInResponse: ApiResponse<Unit> = ApiResponse.Loading,

)
