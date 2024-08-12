package org.techinowave.data.koin

import org.koin.dsl.module
import org.techinowave.data.repo.MenuItemsRepositoryImpl
import org.techinowave.data.repo.PreferenceDbRepositoryImpl
import org.techinowave.data.repo.UserAuthRepositoryImpl
import org.techinowave.domain.repo.MenuItemsRepository
import org.techinowave.domain.repo.PreferenceDbRepository
import org.techinowave.domain.repo.UserAuthRepository

val repositoryModule = module {

    single<MenuItemsRepository> {
        MenuItemsRepositoryImpl(
            supabaseDatabase = get()
        )
    }

    single<PreferenceDbRepository> {
        PreferenceDbRepositoryImpl(
            dataStoreManager = get()
        )
    }

    single<UserAuthRepository> {
        UserAuthRepositoryImpl(
            supabaseAuth = get()
        )
    }

}