package com.example.mycity.ui

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class MyCityViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(MyCityUiState())
    val uiState: StateFlow<MyCityUiState> = _uiState

    fun updateQrBitmap(bitmap: Bitmap) {
        _uiState.update {
            it.copy(
                qrBitmap = bitmap
            )
        }
    }
}
