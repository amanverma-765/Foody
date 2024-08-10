package org.techinowave.domain.usecase.preference

import kotlinx.coroutines.flow.Flow
import org.techinowave.domain.repo.PreferenceDbRepository
import org.techinowave.utils.ApiResponse

class GetFromPreference(
    private val preferenceDbRepository: PreferenceDbRepository
) {
    operator fun invoke(key: String): Flow<ApiResponse<Boolean>> {
        return preferenceDbRepository.getData(key)
    }
}