package org.techinowave.domain.repo

import kotlinx.coroutines.flow.Flow
import org.techinowave.domain.model.FoodMenuItem
import org.techinowave.utils.ApiResponse

interface MenuItemsRepository {

    fun fetchMenuItems(): Flow<ApiResponse<List<FoodMenuItem>>>

}