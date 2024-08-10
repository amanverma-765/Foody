package org.techinowave.domain.usecase.preference

import kotlinx.coroutines.flow.Flow
import org.techinowave.domain.repo.PreferenceDbRepository
import org.techinowave.utils.ApiResponse


class SaveToPreference(
    private val preferenceDbRepository: PreferenceDbRepository
) {
    suspend operator fun invoke(data: Boolean?, key: String): Flow<ApiResponse<Unit>> {
        return preferenceDbRepository.saveData(
            data = data,
            key = key
        )
    }
}