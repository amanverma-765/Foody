package org.techinowave.data.local.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import org.techinowave.utils.ApiResponse

const val preferenceName = "foody_preference"

class DataStoreManager(
    private val context: Context,
) {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = preferenceName)

    suspend fun saveData(data: Boolean?, key: String): Flow<ApiResponse<Unit>> {
        return flow {
            emit(ApiResponse.Loading)
            try {
                context.dataStore.edit { store ->
                    store[booleanPreferencesKey(key)] = data ?: true
                }
                emit(ApiResponse.Success(Unit))
            } catch (e: Exception) {
                e.printStackTrace()
                emit(ApiResponse.Error(e.message))
            }
        }
    }

    fun getData(key: String): Flow<ApiResponse<Boolean>> {
        return flow {
            emit(ApiResponse.Loading)
            try {
                val dataStore = context.dataStore.data.map { data ->
                    data[booleanPreferencesKey(key)] ?: false
                }
                dataStore.collect { data ->
                    emit(ApiResponse.Success(data))
                }
            } catch (e: Exception) {
                e.printStackTrace()
                emit(ApiResponse.Error(e.message))
            }
        }
    }
}