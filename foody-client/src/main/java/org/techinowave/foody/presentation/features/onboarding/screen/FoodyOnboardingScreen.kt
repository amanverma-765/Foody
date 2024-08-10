package org.techinowave.foody.presentation.features.onboarding.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.captionBar
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowForwardIos
import androidx.compose.material.icons.rounded.ArrowForwardIos
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.techinowave.foody.R
import org.techinowave.foody.presentation.features.home.viewmodel.HomeUiEvents
import org.techinowave.foody.presentation.features.home.viewmodel.HomeUiStates
import org.techinowave.foody.presentation.features.onboarding.viewmodel.OnboardingUiEvents
import org.techinowave.foody.presentation.features.onboarding.viewmodel.OnboardingUiStates
import org.techinowave.utils.ApiResponse


@Composable
fun FoodyOnboardingScreen(
    modifier: Modifier = Modifier,
    uiEvent: (OnboardingUiEvents) -> Unit,
    uiState: OnboardingUiStates,
    navigateToHome: () -> Unit
) {

    LaunchedEffect(key1 = uiState.userEntryResponse) {
        when (uiState.userEntryResponse) {
            is ApiResponse.Error -> Unit
            is ApiResponse.Loading -> Unit
            is ApiResponse.Success -> {
                navigateToHome()
            }
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxHeight(.5f)
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.delivery_service),
                contentDescription = "Delivery Service",
                modifier = Modifier
                    .size(360.dp)
                    .graphicsLayer(
                        rotationY = 180f,
                    )
            )
        }
        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(topStartPercent = 10, topEndPercent = 10))
                .background(MaterialTheme.colorScheme.background)
                .padding(32.dp)
        ) {

            Text(
                text = "Get The Fastest\nFood Delivery",
                style = MaterialTheme.typography.displaySmall.copy(
                    fontWeight = FontWeight.ExtraBold
                ),
                textAlign = TextAlign.Center
            )

            Text(
                text = "Welcome to Foody, where your favorite food and meals are just a tap away. Enjoy your fresh food delivery!",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.ExtraBold,
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier.alpha(.5f)
            )

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .border(2.dp, MaterialTheme.colorScheme.primary, CircleShape)
                    .size(80.dp)
                    .padding(6.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary)
                    .clickable {
                        uiEvent(OnboardingUiEvents.SaveUserEntry)
                    }
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.ArrowForwardIos,
                    contentDescription = "Get Started",
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.size(30.dp)
                )
            }
        }
    }
}
