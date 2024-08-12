package org.techinowave.domain.usecase.auth

import kotlinx.coroutines.flow.Flow
import org.techinowave.domain.model.AuthStatus
import org.techinowave.domain.repo.UserAuthRepository
import org.techinowave.utils.ApiResponse

class ListenAuthState(
    private val userAuthRepository: UserAuthRepository
) {
    operator fun invoke(): Flow<ApiResponse<AuthStatus>> {
        return userAuthRepository.listenAuthStatus()
    }
}
