package org.techinowave.domain.koin

import org.koin.dsl.module
import org.techinowave.domain.repo.UserAuthRepository
import org.techinowave.domain.usecase.MenuItemUseCase
import org.techinowave.domain.usecase.PreferenceDbUseCase
import org.techinowave.domain.usecase.UserAuthUseCase
import org.techinowave.domain.usecase.auth.ListenAuthState
import org.techinowave.domain.usecase.auth.SignInWithGoogle
import org.techinowave.domain.usecase.auth.UserSignOut
import org.techinowave.domain.usecase.menu.GetMenuItems
import org.techinowave.domain.usecase.preference.GetFromPreference
import org.techinowave.domain.usecase.preference.SaveToPreference

val useCaseModule = module {

    single {
        MenuItemUseCase(
            getMenuItems = GetMenuItems(menuItemsRepository = get()),
        )
    }

    single {
        PreferenceDbUseCase(
            getFromPreference = GetFromPreference(preferenceDbRepository = get()),
            saveToPreference = SaveToPreference(preferenceDbRepository = get())
        )
    }

    single {
        UserAuthUseCase(
            signInWithGoogle = SignInWithGoogle(userAuthRepository = get()),
            userSignOut = UserSignOut(userAuthRepository = get()),
            listenAuthState = ListenAuthState(userAuthRepository = get())
        )
    }

}