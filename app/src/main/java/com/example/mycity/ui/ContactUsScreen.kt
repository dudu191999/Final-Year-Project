import android.content.Context
import android.content.Intent
import android.net.Uri
import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mycity.R
import com.example.mycity.ui.BottomBar
import com.example.mycity.ui.bottomBarItems

@Composable
fun ContactUsScreen(navController: NavController) {
    val context = LocalContext.current

    Scaffold(
        bottomBar = { BottomBar(navController, bottomBarItems) }
    ) { innerPadding ->
        Surface(
            color = Color.White, // Set background color to white
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            val configuration = LocalConfiguration.current

            Column(
                modifier = Modifier
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // Portrait layout
                if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                    PortraitContent(context)
                } else {
                    // Landscape layout
                    LandscapeContent(context)
                }
            }
        }
    }
}

@Composable
private fun PortraitContent(context: Context) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Portrait-specific UI content
        Card(
            modifier = Modifier
                .size(180.dp),
            shape = CircleShape,
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.carlos),
                contentDescription = stringResource(R.string.contact_image_description),
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Carlos De Ceita",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(8.dp))

        ContactInfoList(context)
    }
}

@Composable
private fun LandscapeContent(context: Context) {
    Row(
        modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        // Landscape-specific UI content
        Card(
            modifier = Modifier
                .size(180.dp),
            shape = CircleShape,
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.carlos),
                contentDescription = stringResource(R.string.contact_image_description),
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        ContactInfoList(context)
    }
}

@Composable
private fun ContactInfoList(context: Context) {
    Column {
        ContactInfoItem(
            icon = Icons.Default.Email,
            label = stringResource(R.string.email),
            value = "denegociosparceirosa@gmail.com"
        ) { launchEmailIntent(context, "denegociosparceirosa@gmail.com") }
        Divider(color = Color.LightGray, thickness = 1.dp)
        ContactInfoItem(
            icon = Icons.Default.Phone,
            label = stringResource(R.string.phone),
            value = "+1-555-123-4567"
        ) { launchDialerIntent(context, "+1-555-123-4567") }
        Divider(color = Color.LightGray, thickness = 1.dp)
        ContactInfoItem(
            icon = Icons.Default.LocationOn,
            label = stringResource(R.string.address),
            value = "123 Main Street, Cityville"
        )
    }
}

@Composable
private fun ContactInfoItem(icon: ImageVector, label: String, value: String, onClick: () -> Unit = {}) {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(containerColor = Color.LightGray) // Set background color to light gray
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(28.dp)
            )
            Column {
                Text(
                    text = label,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black // Set label text color to black
                )
                Text(
                    text = value,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black // Set value text color to black
                )
            }
        }
    }
}

fun launchEmailIntent(context: Context, email: String) {
    val intent = Intent(Intent.ACTION_SENDTO).apply {
        data = Uri.parse("mailto:")
        putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
        putExtra(Intent.EXTRA_SUBJECT, "Inquiry from MyCity App")
    }
    context.startActivity(Intent.createChooser(intent, "Send email..."))
}

fun launchDialerIntent(context: Context, phoneNumber: String) {
    val intent = Intent(Intent.ACTION_DIAL).apply {
        data = Uri.parse("tel:$phoneNumber")
    }
    context.startActivity(intent)
}
