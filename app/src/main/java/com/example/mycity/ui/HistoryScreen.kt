package com.example.mycity.ui

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mycity.R

@Composable
fun HistoryScreen(navController: NavController) {
    val images = listOf(R.drawable.history_image1, R.drawable.history_image2)
    val scrollState = rememberScrollState()
    val configuration = LocalConfiguration.current

    // Choose layout based on orientation
    if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
        Row( // Landscape layout with Row
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Gallery(
                images = images,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
            )

            Card(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                shape = RoundedCornerShape(8.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Text(
                    text = stringResource(R.string.history_content),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier
                        .padding(16.dp)
                        .verticalScroll(scrollState) // Keep text scrollable in landscape
                )
            }
        }
    } else {
        Column( // Portrait layout with Column
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 24.dp)
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Title
            AnimatedVisibility(visible = true) {
                Text(
                    text = stringResource(R.string.history_title),
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(bottom = 12.dp)
                )
            }

            // Image Gallery with adjusted height
            Gallery(
                images = images,
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 250.dp) // Experiment with the max value here
            )

            // Text below
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(2f), // Adjusted weight based on orientation
                shape = RoundedCornerShape(8.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Text(
                    text = stringResource(R.string.history_content),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}


