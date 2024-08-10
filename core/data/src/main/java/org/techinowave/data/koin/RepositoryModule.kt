package org.techinowave.data.koin

import org.koin.dsl.module
import org.techinowave.data.repo.MenuItemsRepositoryImpl
import org.techinowave.domain.repo.MenuItemsRepository

val repositoryModule = module {

    single<MenuItemsRepository> {
        MenuItemsRepositoryImpl(
            supabaseDataSource = get()
        )
    }

}