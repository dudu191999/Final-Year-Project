package com.example.mycity.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.example.mycity.MyCityScreen
import com.example.mycity.icons.ContactMail
import com.example.mycity.icons.Gallery

// Define the BottomBarItem data class
data class BottomBarItem(
    val title: String,
    val icon: ImageVector,
    val route: String
)

// Create a list of bottom bar items
val bottomBarItems = listOf(
    BottomBarItem("Home", Icons.Filled.Home, MyCityScreen.Home.name),
    BottomBarItem("Gallery", Icons.Filled.Gallery, MyCityScreen.Onboarding.name),
    BottomBarItem("Tour Guide", Icons.Filled.Person, MyCityScreen.TourGuide.name),
    BottomBarItem("Contact Us", Icons.Filled.ContactMail, MyCityScreen.ContactUs.name),// Change route here
    // Change route here

)

// Create the BottomBar Composable
@Composable
fun BottomBar(navController: NavController, items: List<BottomBarItem>) {
    BottomAppBar {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.forEach { item ->
                IconButton(
                    onClick = {
                        if (item.route == MyCityScreen.Home.name) {
                            navController.popBackStack(MyCityScreen.Home.name, inclusive = false)
                        } else {
                            navController.navigate(item.route)
                        }
                    }
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(imageVector = item.icon, contentDescription = item.title)
                        Text(text = item.title, textAlign = TextAlign.Center)
                    }
                }
            }
        }
    }
}
