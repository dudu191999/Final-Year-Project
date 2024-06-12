package com.example.mycity.ui

import android.content.ContentValues
import android.content.Context
import android.content.res.Configuration
import android.graphics.Bitmap
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.qrcode.QRCodeWriter
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign

@Composable
fun QrScreen(viewModel: MyCityViewModel = viewModel(), modifier: Modifier = Modifier) {
    val uiState by viewModel.uiState.collectAsState()  // Collect the uiState


    val context = LocalContext.current
    val configuration = LocalConfiguration.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            PortraitContent(context, uiState) // Pass uiState
        } else {
            LandscapeContent(context, uiState) // Pass uiState
        }
    }
}

// Portrait Layout
@Composable
private fun PortraitContent(context: Context, uiState: MyCityUiState) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DisplayQrCode(uiState.qrBitmap) // Display the bitmap from uiState

        Spacer(modifier = Modifier.height(24.dp))

        SaveToGalleryButton(context, uiState.qrBitmap) // Use the bitmap from uiState
    }
}

// Landscape Layout
@Composable
private fun LandscapeContent(context: Context, uiState: MyCityUiState) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        DisplayQrCode(uiState.qrBitmap) // Display the bitmap from uiState

        Spacer(modifier = Modifier.width(24.dp))

        SaveToGalleryButton(context, uiState.qrBitmap) // Use the bitmap from uiState
    }
}

// Reusable QR Code Display
@Composable
private fun DisplayQrCode(qrBitmap: Bitmap?) { // Handle nullable Bitmap
    qrBitmap?.let { // Only display if the bitmap is not null
        Card(modifier = Modifier.padding(8.dp)) {
            Image(
                bitmap = it.asImageBitmap(),
                contentDescription = "QR Code",
                modifier = Modifier
                    .size(200.dp)
                    .padding(16.dp)
            )
        }
    }
}

// Reusable Save to Gallery Button
@Composable
private fun SaveToGalleryButton(context: Context, qrBitmap: Bitmap?) {
    Button(
        onClick = { saveQrCodeToGallery(context, qrBitmap) }, // Pass the bitmap
        modifier = Modifier.width(200.dp),
        enabled = qrBitmap != null // Disable button if bitmap is null
    ) {
        Text("Save to Gallery", style = MaterialTheme.typography.labelLarge)
    }
}

fun generateQrCode(text: String, width: Int, height: Int): Bitmap {
    val hints = mapOf(EncodeHintType.MARGIN to 1)
    val bitMatrix = QRCodeWriter().encode(text, BarcodeFormat.QR_CODE, width, height, hints)
    val bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888) // Use ARGB_8888 config

    for (x in 0 until width) {
        for (y in 0 until height) {
            val color = if (bitMatrix[x, y]) android.graphics.Color.BLACK else android.graphics.Color.WHITE
            bmp.setPixel(x, y, color)
        }
    }
    return bmp
}


fun saveQrCodeToGallery(context: Context, bitmap: Bitmap?) {
    bitmap ?: return // If bitmap is null, don't proceed

    val contentValues = ContentValues().apply {
        put(MediaStore.MediaColumns.DISPLAY_NAME, "qr_code_${System.currentTimeMillis()}.jpg")
        put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg") // Use JPEG for better compatibility
        put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
    }

    val resolver = context.contentResolver
    val uri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)

    uri?.let {
        resolver.openOutputStream(it)?.use { outputStream ->
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, outputStream) // Compress as JPEG
        }

        Toast.makeText(context, "QR code saved to gallery!", Toast.LENGTH_SHORT).show()
    }
}
