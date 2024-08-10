package org.techinowave.data.supabase

import android.util.Log
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.techinowave.data.model.MenuItemDto
import org.techinowave.utils.ApiResponse

class SupabaseDataSource(
    private val supabaseClient: SupabaseClient
) {

    fun fetchMenuItems(): Flow<ApiResponse<List<MenuItemDto>>> {

        return flow {

            emit(ApiResponse.Loading)
            try {

                val res = supabaseClient.postgrest["menu_item_listing"].select()
                val data = res.decodeList<MenuItemDto>()
                emit(ApiResponse.Success(data))

            } catch (e: Exception) {

                e.printStackTrace()
                emit(ApiResponse.Error(e.message))
            }
        }
    }
}