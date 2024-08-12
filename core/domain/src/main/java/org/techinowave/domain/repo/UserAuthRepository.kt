package org.techinowave.domain.repo

import kotlinx.coroutines.flow.Flow
import org.techinowave.domain.model.AuthStatus
import org.techinowave.utils.ApiResponse

interface UserAuthRepository {

    fun signInWithGoogle(
        idToken: String,
        nonce: String
    ): Flow<ApiResponse<Unit>>

    fun userSignOut(): Flow<ApiResponse<Unit>>

    fun listenAuthStatus(): Flow<ApiResponse<AuthStatus>>

}