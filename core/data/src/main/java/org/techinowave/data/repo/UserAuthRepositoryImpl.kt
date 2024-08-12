package org.techinowave.data.repo

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.techinowave.data.mapper.AuthStatusMapper.toAuthStatus
import org.techinowave.data.remote.supabase.SupabaseAuth
import org.techinowave.domain.model.AuthStatus
import org.techinowave.domain.repo.UserAuthRepository
import org.techinowave.utils.ApiResponse

class UserAuthRepositoryImpl(
    private val supabaseAuth: SupabaseAuth
) : UserAuthRepository {

    override fun signInWithGoogle(idToken: String, nonce: String): Flow<ApiResponse<Unit>> {
        return supabaseAuth.signInWithGoogle(idToken, nonce)
    }

    override fun userSignOut(): Flow<ApiResponse<Unit>> {
        return supabaseAuth.userSignOut()
    }

    override fun listenAuthStatus(): Flow<ApiResponse<AuthStatus>> {
        return supabaseAuth.listenAuthStatus().map { response ->
            when(response) {
                is ApiResponse.Success -> ApiResponse.Success(response.data.toAuthStatus())
                is ApiResponse.Loading -> ApiResponse.Loading
                is ApiResponse.Error -> ApiResponse.Error(response.message)
            }
        }
    }
}