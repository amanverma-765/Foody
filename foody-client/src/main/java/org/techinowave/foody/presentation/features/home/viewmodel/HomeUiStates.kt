package org.techinowave.foody.presentation.features.home.viewmodel

import org.techinowave.domain.model.AuthStatus
import org.techinowave.domain.model.FoodMenuItem
import org.techinowave.utils.ApiResponse

data class HomeUiStates(
    val foodMenuItemResponse: ApiResponse<List<FoodMenuItem>> = ApiResponse.Loading,
    val userEntryResponse: ApiResponse<Boolean> = ApiResponse.Loading,
    val authStatusResponse: ApiResponse<AuthStatus> = ApiResponse.Loading
)
