package org.techinowave.domain.usecase.auth

import kotlinx.coroutines.flow.Flow
import org.techinowave.domain.model.FoodMenuItem
import org.techinowave.domain.repo.MenuItemsRepository
import org.techinowave.domain.repo.UserAuthRepository
import org.techinowave.utils.ApiResponse

class UserSignOut(
    private val userAuthRepository: UserAuthRepository
) {
    operator fun invoke(): Flow<ApiResponse<Unit>> {
        return userAuthRepository.userSignOut()
    }
}
