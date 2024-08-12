package org.techinowave.data.mapper

import org.techinowave.data.model.SupabaseAuthStatus
import org.techinowave.domain.model.AuthStatus

object AuthStatusMapper {

    fun SupabaseAuthStatus.toAuthStatus(): AuthStatus {
        return when (this) {
            is SupabaseAuthStatus.Authenticated -> AuthStatus.Authenticated(this.metadata)
            is SupabaseAuthStatus.NotAuthenticated -> AuthStatus.NotAuthenticated
        }
    }
}