package com.example.mycity.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun Gallery(images: List<Int>, modifier: Modifier = Modifier) {  // Add modifier parameter
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 8.dp)
    ) {
        items(images) { imageId ->
            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .width(240.dp), // Set a fixed width for better visual consistency
                shape = MaterialTheme.shapes.medium,
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp) // Add elevation for a modern look
            ) {
                Image(
                    painter = painterResource(id = imageId),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxWidth() // Make the image fill the card width
                )
            }
        }
    }
}


