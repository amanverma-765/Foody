package org.techinowave.foody

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import org.koin.androidx.compose.KoinAndroidContext
import org.koin.core.annotation.KoinExperimentalAPI
import org.techinowave.foody.navigation.FoodyDestinations
import org.techinowave.foody.navigation.FoodyNavHost
import org.techinowave.foody.theme.FoodyTheme


class MainActivity : ComponentActivity() {
    @OptIn(KoinExperimentalAPI::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FoodyTheme {
                KoinAndroidContext {
                    FoodyNavHost(
                        startDestination = FoodyDestinations.Onboarding,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}