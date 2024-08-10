package org.techinowave.domain.repo

import org.techinowave.utils.ApiResponse
import kotlinx.coroutines.flow.Flow


interface PreferenceDbRepository {

    suspend fun saveData(data: Boolean?, key: String): Flow<ApiResponse<Unit>>

    fun getData(key: String): Flow<ApiResponse<Boolean>>

}