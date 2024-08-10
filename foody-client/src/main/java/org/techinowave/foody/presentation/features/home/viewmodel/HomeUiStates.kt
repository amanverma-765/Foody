package org.techinowave.foody.presentation.features.home.viewmodel

import org.techinowave.domain.model.FoodMenuItem
import org.techinowave.utils.ApiResponse

data class HomeUiStates(
    val foodMenuItemResponse: ApiResponse<List<FoodMenuItem>> = ApiResponse.Loading
)
