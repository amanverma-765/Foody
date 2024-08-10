package org.techinowave.data.repo

import kotlinx.coroutines.flow.Flow
import org.techinowave.data.local.datastore.DataStoreManager
import org.techinowave.domain.repo.PreferenceDbRepository
import org.techinowave.utils.ApiResponse

class PreferenceDbRepositoryImpl(
    private val dataStoreManager: DataStoreManager
): PreferenceDbRepository {

    override suspend fun saveData(data: Boolean?, key: String): Flow<ApiResponse<Unit>> {
        return dataStoreManager.saveData(data = data, key = key)
    }

    override fun getData(key: String): Flow<ApiResponse<Boolean>> {
        return dataStoreManager.getData(key = key)
    }

}