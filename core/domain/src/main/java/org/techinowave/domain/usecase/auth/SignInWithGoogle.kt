package org.techinowave.domain.usecase.auth

import kotlinx.coroutines.flow.Flow
import org.techinowave.domain.model.FoodMenuItem
import org.techinowave.domain.repo.MenuItemsRepository
import org.techinowave.domain.repo.UserAuthRepository
import org.techinowave.utils.ApiResponse

class SignInWithGoogle(
    private val userAuthRepository: UserAuthRepository
) {
    operator fun invoke(
        idToken: String,
        nonce: String
    ): Flow<ApiResponse<Unit>> {
        return userAuthRepository.signInWithGoogle(
            idToken = idToken,
            nonce = nonce
        )
    }
}
