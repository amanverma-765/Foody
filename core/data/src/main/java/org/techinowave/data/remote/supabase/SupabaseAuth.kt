package org.techinowave.data.remote.supabase

import android.util.Log
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.gotrue.SessionStatus
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.Google
import io.github.jan.supabase.gotrue.providers.builtin.IDToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.techinowave.data.model.SupabaseAuthStatus
import org.techinowave.utils.ApiResponse


class SupabaseAuth(
    private val supabaseClient: SupabaseClient
) {

    fun signInWithGoogle(token: String, rawNonce: String): Flow<ApiResponse<Unit>> {
        return flow {
            try {

                emit(ApiResponse.Loading)

                supabaseClient.auth.signInWith(IDToken) {
                    provider = Google
                    idToken = token
                    nonce = rawNonce
                }

                emit(ApiResponse.Success(Unit))

            } catch (e: Exception) {
                e.printStackTrace()
                emit(ApiResponse.Error(e.message))
            }
        }
    }

    fun userSignOut(): Flow<ApiResponse<Unit>> {
        return flow {
            try {

                emit(ApiResponse.Loading)

                supabaseClient.auth.signOut()

                emit(ApiResponse.Success(Unit))

            } catch (e: Exception) {
                e.printStackTrace()
                emit(ApiResponse.Error(e.message))
            }
        }
    }

    fun listenAuthStatus(): Flow<ApiResponse<SupabaseAuthStatus>> {
        return flow {
            supabaseClient.auth.sessionStatus.collect { status ->
                try {

                    emit(ApiResponse.Loading)

                    when (status) {
                        is SessionStatus.LoadingFromStorage -> Unit
                        is SessionStatus.NetworkError -> emit(ApiResponse.Error("Network Error"))
                        is SessionStatus.Authenticated -> {

                            val metadata = status.session.user?.userMetadata
                            emit(
                                ApiResponse.Success(
                                    SupabaseAuthStatus.Authenticated(metadata)
                                )
                            )
                        }

                        is SessionStatus.NotAuthenticated -> emit(
                            ApiResponse.Success(
                                SupabaseAuthStatus.NotAuthenticated
                            )
                        )
                    }

                } catch (e: Exception) {
                    e.printStackTrace()
                    emit(ApiResponse.Error(e.message))
                }
            }
        }
    }
}