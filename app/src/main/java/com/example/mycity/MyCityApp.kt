package com.example.mycity

import ContactUsScreen
import HomeScreen
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.mycity.ui.MyCityViewModel
import com.example.mycity.ui.OnboardingScreen
import com.example.mycity.ui.SplashScreen
import com.example.mycity.ui.theme.MyCityTheme
import com.example.mycity.utils.WindowStateContentType
import com.example.mycity.ui.CultureScreen
import com.example.mycity.ui.FoodScreen
import com.example.mycity.ui.HistoryScreen
import com.example.mycity.ui.TourGuideScreen
import com.example.mycity.ui.QrScreen

enum class MyCityScreen {
    Splash,
    Home,
    Onboarding,
    History,
    Culture,
    Food,
    TourGuide,
    ContactUs,
    QrScreen
}

@Composable
fun MyCityApp(
    windowSize: WindowWidthSizeClass,
    navController: NavHostController = rememberNavController(),
    viewModel: MyCityViewModel = viewModel()
) {

    val contentType = when (windowSize) {
        WindowWidthSizeClass.Expanded -> WindowStateContentType.ListDetail
        else -> WindowStateContentType.ListOnly
    }
    val backStackEntry by navController.currentBackStackEntryAsState()

    val currentScreen = MyCityScreen.valueOf(
        backStackEntry?.destination?.route ?: MyCityScreen.Home.name
    )
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        bottomBar = {
            if (shouldDisplayBottomBar(currentScreen, contentType)) {
                // ... (Potentially no BottomAppBar needed here if not using Start anymore)
            }
        }
    ) { innerPadding ->
        NavHost(navController = navController, startDestination = MyCityScreen.Splash.name){
            composable(MyCityScreen.Splash.name) { SplashScreen(navController = navController) }
            composable(MyCityScreen.Home.name) { HomeScreen(navController = navController) }
            composable(MyCityScreen.History.name) {
                HistoryScreen(navController = navController)
            }
            composable(MyCityScreen.Culture.name) {
                CultureScreen(navController = navController)
            }
            composable(MyCityScreen.Food.name) {
                FoodScreen(navController = navController)
            }
            composable(MyCityScreen.Onboarding.name) {
                OnboardingScreen(navController = navController)
            }
            composable(MyCityScreen.TourGuide.name) {
                TourGuideScreen(navController = navController, viewModel = viewModel)
            }
            composable(MyCityScreen.ContactUs.name) {
                ContactUsScreen(navController = navController)
            }
            composable(MyCityScreen.QrScreen.name) {
                QrScreen(viewModel = viewModel)
            }
        }
    }
}

fun shouldDisplayBottomBar(
    currentScreen: MyCityScreen,
    contentType: WindowStateContentType
) = currentScreen.name in listOf(
    MyCityScreen.Home.name,
    MyCityScreen.History.name,
    MyCityScreen.Culture.name,
    MyCityScreen.Food.name,
    MyCityScreen.Onboarding.name,
    MyCityScreen.TourGuide.name,
    MyCityScreen.ContactUs.name,
    MyCityScreen.QrScreen.name,
)

@Composable
fun NavButtonsAppBar(
    nextFunction: () -> Unit,
    modifier: Modifier = Modifier,
    nextImageId: Int = -1,
    previousImageId: Int = -1,
    hasPreviousButton: Boolean,
    previousFunction: () -> Unit = {}
) {
    BottomAppBar {
        Row(horizontalArrangement = Arrangement.End, modifier = modifier.fillMaxWidth()) {
            if (hasPreviousButton) {
                PreviousButton(onClick = previousFunction, imageId = previousImageId)
                Spacer(modifier = Modifier.weight(1f))
            }
        }
    }
}

@Composable
fun PreviousButton(onClick: () -> Unit, imageId: Int, modifier: Modifier = Modifier) {
    Button(
        onClick = { onClick() },
        modifier = modifier.padding(dimensionResource(id = R.dimen.padding_medium))
    ) {
        Icon(
            imageVector = Icons.Default.KeyboardArrowLeft,
            contentDescription = null,
        )
        if (imageId != -1) {
            Icon(
                imageVector = ImageVector.vectorResource(id = imageId),
                contentDescription = null,
                modifier = Modifier.padding(end = dimensionResource(id = R.dimen.padding_small))
            )
        }
        Text(
            text = stringResource(id = R.string.previous_button),
            style = MaterialTheme.typography.labelMedium
        )

    }
}

@Preview
@Composable
fun MyAppPreview() {
    MyCityTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            MyCityApp(windowSize = WindowWidthSizeClass.Compact)
        }
    }
}

@Preview(device = Devices.TABLET)
@Composable
fun MyAppExpandedPreview() {
    MyCityTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            MyCityApp(windowSize = WindowWidthSizeClass.Expanded)
        }
    }
}

