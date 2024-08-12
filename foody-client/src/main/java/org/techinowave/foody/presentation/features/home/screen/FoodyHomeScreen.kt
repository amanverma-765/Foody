package org.techinowave.foody.presentation.features.home.screen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import kotlinx.serialization.json.jsonObject
import org.techinowave.domain.model.AuthStatus
import org.techinowave.domain.model.FoodMenuItem
import org.techinowave.foody.R
import org.techinowave.foody.presentation.features.details.screen.FoodyDetailScreen
import org.techinowave.foody.presentation.features.home.components.FilterChipsRow
import org.techinowave.foody.presentation.features.home.components.FoodySearchBar
import org.techinowave.foody.presentation.features.home.components.FoodyTopAppBar
import org.techinowave.foody.presentation.features.home.components.MenuItemCard
import org.techinowave.foody.presentation.features.home.viewmodel.HomeUiEvents
import org.techinowave.foody.presentation.features.home.viewmodel.HomeUiStates
import org.techinowave.utils.ApiResponse

private const val TAG = "Home Screen"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodyHomeScreen(
    modifier: Modifier = Modifier,
    uiEvent: (HomeUiEvents) -> Unit,
    uiState: HomeUiStates,
    navigateToOnboarding: () -> Unit,
    navigateToLogin: () -> Unit,
    navigateToCart: () -> Unit
) {

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }
    var selectedMenuItem by remember { mutableStateOf<FoodMenuItem?>(null) }
    val imgUrl = remember { mutableStateOf<String?>(null) }
    var isAuthenticated by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = Unit) {
        uiEvent(HomeUiEvents.GetMenuItems)
        uiEvent(HomeUiEvents.GetUserEntry)
    }

    LaunchedEffect(key1 = uiState.userEntryResponse) {
        when (val response = uiState.userEntryResponse) {
            is ApiResponse.Error -> navigateToOnboarding()
            is ApiResponse.Loading -> Unit
            is ApiResponse.Success -> {
                if (response.data.not()) navigateToOnboarding()
            }
        }
    }

    LaunchedEffect(key1 = uiState.authStatusResponse) {
        when (val response = uiState.authStatusResponse) {
            is ApiResponse.Error -> Unit
            is ApiResponse.Loading -> Unit
            is ApiResponse.Success -> {
                when (val data = response.data) {
                    is AuthStatus.Authenticated -> {
                        imgUrl.value = data.metadata?.get("avatar_url").toString()
                        isAuthenticated = true
                    }

                    is AuthStatus.NotAuthenticated -> {
                        isAuthenticated = false
                    }
                }
            }
        }
    }

    Scaffold(
        topBar = {
            FoodyTopAppBar(
                imgUrl = imgUrl.value,
                navigateToCart = navigateToCart,
                navigateToProfile = {
                    if (isAuthenticated) {
                        Unit
                    } else {
                        navigateToLogin()
                    }
                }
            )
        }
    ) { innerPadding ->

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(12.dp),
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {

            item(
                span = { GridItemSpan(maxLineSpan) }
            ) {
                Column {
                    FoodySearchBar(modifier = Modifier.fillMaxWidth())
                    Spacer(modifier = Modifier.height(8.dp))
                    FilterChipsRow()
                }
            }

            when (val response = uiState.foodMenuItemResponse) {
                is ApiResponse.Error -> {}
                is ApiResponse.Loading -> {
                    item(
                        span = { GridItemSpan(maxLineSpan) }
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            CircularProgressIndicator(modifier = Modifier.size(50.dp))
                        }
                    }
                }

                is ApiResponse.Success -> {
                    items(
                        items = response.data,
                        key = { menuItem -> menuItem.id }
                    ) { menuItem ->
                        MenuItemCard(
                            foodMenuItem = menuItem,
                            context = context,
                            onClick = {
                                coroutineScope.launch {
                                    selectedMenuItem = menuItem
                                    showBottomSheet = true
                                }
                            }
                        )
                    }
                }
            }
        }
        if (showBottomSheet) {
            ModalBottomSheet(
                sheetState = sheetState,
                windowInsets = WindowInsets(top = 0.dp),
                dragHandle = {},
                shape = RoundedCornerShape(16.dp),
                onDismissRequest = {
                    showBottomSheet = false
                }
            ) {
                selectedMenuItem?.let { menuItem ->
                    FoodyDetailScreen(
                        context = context,
                        menuItem = menuItem,
                        navigateToLogin = {
                            coroutineScope.launch { sheetState.hide() }.invokeOnCompletion {
                                if (!sheetState.isVisible) {
                                    showBottomSheet = false
                                }
                                if (isAuthenticated) {
                                    Unit
                                } else {
                                    navigateToLogin()
                                }
                            }
                        }
                    )
                }
            }
        }
    }
}