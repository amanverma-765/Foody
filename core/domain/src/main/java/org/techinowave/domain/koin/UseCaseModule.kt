package org.techinowave.domain.koin

import org.koin.dsl.module
import org.techinowave.foody.domain.usecase.MenuItemUseCase
import org.techinowave.domain.usecase.menu.GetMenuItems

val useCaseModule = module {

    single {
        MenuItemUseCase(
            getMenuItems = GetMenuItems(menuItemsRepository = get()),
        )
    }

}