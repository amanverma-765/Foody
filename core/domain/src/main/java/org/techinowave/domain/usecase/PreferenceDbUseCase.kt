package org.techinowave.domain.usecase

import org.techinowave.domain.usecase.preference.GetFromPreference
import org.techinowave.domain.usecase.preference.SaveToPreference

data class PreferenceDbUseCase(

    val saveToPreference: SaveToPreference,
    val getFromPreference: GetFromPreference

)