package org.techinowave.data.koin

import org.koin.dsl.module
import org.techinowave.data.repo.MenuItemsRepositoryImpl
import org.techinowave.data.repo.PreferenceDbRepositoryImpl
import org.techinowave.domain.repo.MenuItemsRepository
import org.techinowave.domain.repo.PreferenceDbRepository

val repositoryModule = module {

    single<MenuItemsRepository> {
        MenuItemsRepositoryImpl(
            supabaseDataSource = get()
        )
    }

    single<PreferenceDbRepository> {
        PreferenceDbRepositoryImpl(
            dataStoreManager = get()
        )
    }

}