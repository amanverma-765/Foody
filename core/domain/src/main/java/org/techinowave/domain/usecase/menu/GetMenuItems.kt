package org.techinowave.domain.usecase.menu

import kotlinx.coroutines.flow.Flow
import org.techinowave.domain.model.FoodMenuItem
import org.techinowave.domain.repo.MenuItemsRepository
import org.techinowave.utils.ApiResponse

class GetMenuItems(
    private val menuItemsRepository: MenuItemsRepository
) {
    operator fun invoke(): Flow<ApiResponse<List<FoodMenuItem>>> {
        return menuItemsRepository.fetchMenuItems()
    }
}