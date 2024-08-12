package org.techinowave.foody.presentation.features.auth.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.techinowave.domain.model.AuthStatus
import org.techinowave.foody.R
import org.techinowave.foody.presentation.features.auth.viewmodel.AuthUiEvents
import org.techinowave.foody.presentation.features.auth.viewmodel.AuthUiStates
import org.techinowave.utils.ApiResponse

const val TAG = "Auth Screen"

@Composable
fun FoodyAuthScreen(
    modifier: Modifier = Modifier,
    uiEvent: (AuthUiEvents) -> Unit,
    uiState: AuthUiStates,
    navigateToCart: () -> Unit
) {

    LaunchedEffect(key2 = Unit, key1 = uiState.authStatusResponse) {
        when (val response = uiState.authStatusResponse) {
            is ApiResponse.Error -> Unit
            is ApiResponse.Loading -> Unit
            is ApiResponse.Success -> {
                when (response.data) {
                    is AuthStatus.Authenticated -> navigateToCart()
                    is AuthStatus.NotAuthenticated -> Unit
                }
            }
        }
    }

    Column(modifier = modifier.fillMaxSize()) {

        Image(
            painter = painterResource(id = R.drawable.food_wallpaper),
            contentDescription = "Food",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.6f)
        )

        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = "Get The Fastest\nFood Delivery",
                style = MaterialTheme.typography.displaySmall.copy(
                    fontWeight = FontWeight.ExtraBold
                ),
                textAlign = TextAlign.Center
            )

            Text(
                text = "Welcome to Foody, You will need a sign in to enjoy your fresh food delivery!",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.ExtraBold,
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier.alpha(.5f)
            )

            GoogleOneTap(
                onLoginClick = { token, nonce ->
                    uiEvent(AuthUiEvents.SignInWithGoogle(token, nonce))
                }
            )
        }
    }
}