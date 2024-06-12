import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mycity.MyCityScreen
import com.example.mycity.R
import com.example.mycity.ui.BottomBar
import com.example.mycity.ui.bottomBarItems

data class CategoryItem(
    val title: String,
    val imageResId: Int
)

val categories = listOf(
    CategoryItem("History", R.drawable.image1),
    CategoryItem("Culture", R.drawable.image2),
    CategoryItem("Food", R.drawable.image3)
)

@Composable
fun HomeScreen(navController: NavController) {
    val configuration = LocalConfiguration.current
    val scrollState = rememberScrollState()

    Scaffold(
        bottomBar = { BottomBar(navController, bottomBarItems) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.White)
                .verticalScroll(scrollState)
        ) {
            // Choose layout based on orientation
            if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                        .background(Color.White),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    FeaturedImage(modifier = Modifier.weight(1f))

                    Spacer(modifier = Modifier.width(16.dp))

                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        categories.forEach { categoryItem ->
                            CategoryItemCard(categoryItem, navController)
                        }
                    }
                }
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                        .background(Color.White),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    FeaturedImage(modifier = Modifier.fillMaxWidth())

                    categories.forEach { categoryItem ->
                        CategoryItemCard(categoryItem, navController)
                    }
                }
            }
        }
    }
}

@Composable
fun FeaturedImage(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .height(250.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.yerevan),
                contentDescription = "Yerevan",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.6f)),
                            startY = 300f
                        )
                    )
            )
        }
    }
}

@Composable
fun CategoryItemCard(categoryItem: CategoryItem, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clickable {
                when (categoryItem.title) {
                    "History" -> navController.navigate(MyCityScreen.History.name)
                    "Culture" -> navController.navigate(MyCityScreen.Culture.name)
                    "Food" -> navController.navigate(MyCityScreen.Food.name)
                    else -> navController.navigate(MyCityScreen.Onboarding.name)
                }
            },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = categoryItem.imageResId),
                contentDescription = categoryItem.title,
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .background(Color.White)
                    .padding(0.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(20.dp))

            Text(
                text = categoryItem.title,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = if (categoryItem.title == "Culture" || categoryItem.title == "History" || categoryItem.title == "Food") Color.Black else Color.Unspecified
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    val navController = rememberNavController()
    HomeScreen(navController = navController)
}
