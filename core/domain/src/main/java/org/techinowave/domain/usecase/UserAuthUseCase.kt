package org.techinowave.domain.usecase

import org.techinowave.domain.usecase.auth.ListenAuthState
import org.techinowave.domain.usecase.auth.SignInWithGoogle
import org.techinowave.domain.usecase.auth.UserSignOut

data class UserAuthUseCase(

    val userSignOut: UserSignOut,
    val signInWithGoogle: SignInWithGoogle,
    val listenAuthState: ListenAuthState

)
