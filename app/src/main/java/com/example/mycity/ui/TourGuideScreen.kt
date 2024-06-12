package com.example.mycity.ui

import android.graphics.Bitmap
import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mycity.MyCityScreen
import com.example.mycity.R

@Composable
fun TourGuideScreen(
    navController: NavController,
    viewModel: MyCityViewModel = viewModel()
) {
    var qrBitmap by remember { mutableStateOf<Bitmap?>(null) }

    val configuration = LocalConfiguration.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT) 16.dp else 64.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally // Align content horizontally to the center
    ) {
        Text(
            text = stringResource(id = R.string.tour_guide_title),
            style = MaterialTheme.typography.h6.copy(
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.primary
            ),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = stringResource(id = R.string.Qr_description),
            modifier = Modifier.padding(bottom = 16.dp),
            style = MaterialTheme.typography.body1.copy(
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colors.onBackground
            )
        )

        Spacer(modifier = Modifier.height(40.dp)) // Add space between title and button

        Button(
            onClick = {
                qrBitmap = generateQrCode("https://get.smart-guide.org/SmlOLc6zeKb", 512, 512)
                viewModel.updateQrBitmap(qrBitmap!!)
                navController.navigate(MyCityScreen.QrScreen.name) {
                    popUpTo(MyCityScreen.TourGuide.name) { inclusive = true }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.primary
            ),
            shape = MaterialTheme.shapes.medium,
            contentPadding = PaddingValues(horizontal = 24.dp, vertical = 16.dp),
            elevation = ButtonDefaults.elevation(defaultElevation = 4.dp, pressedElevation = 8.dp)
        ) {
            Text(
                text = "Generate QR Code",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.onPrimary
            )
        }
    }
}
