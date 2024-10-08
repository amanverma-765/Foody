package org.techinowave.data.repo

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.techinowave.data.mapper.MenuItemsMapper.toMenuItem
import org.techinowave.data.remote.supabase.SupabaseDatabase
import org.techinowave.domain.model.FoodMenuItem
import org.techinowave.domain.repo.MenuItemsRepository
import org.techinowave.utils.ApiResponse

class MenuItemsRepositoryImpl(
    private val supabaseDatabase: SupabaseDatabase
): MenuItemsRepository {

    override fun fetchMenuItems(): Flow<ApiResponse<List<FoodMenuItem>>> {
        return supabaseDatabase.fetchMenuItems().map { response ->
            when(response) {
                is ApiResponse.Success -> {
                    val menuItems = response.data.map { it.toMenuItem() }
                    ApiResponse.Success(menuItems)
                }
                is ApiResponse.Loading -> ApiResponse.Loading
                is ApiResponse.Error -> ApiResponse.Error(response.message)
            }
        }
    }

}