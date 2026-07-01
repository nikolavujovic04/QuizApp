package com.example.quizapp.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.quizapp.data.local.Categories
import com.example.quizapp.navigation.Screen
import com.example.quizapp.ui.screens.category.components.CategoryCard
import com.example.quizapp.ui.screens.leadebord.components.LeadebordCard
import com.example.quizapp.ui.theme.Background
import com.example.quizapp.ui.theme.GreenPrimary
import com.example.quizapp.ui.theme.Surface
import com.example.quizapp.ui.theme.TextPrimary
import com.example.quizapp.ui.theme.TextSecondary
import com.example.quizapp.ui.theme.TextTertiary
import com.example.quizapp.ui.theme.Typography
import com.example.quizapp.ui.viewModel.HomeViewModel
import com.example.quizapp.utils.Resource

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel()
    ) {
    val bestUser by viewModel.bestUser.collectAsState()
    val userState by viewModel.userState.collectAsState()
    var displayName: String = ""
    val categories = Categories.list
    when(userState){
        is Resource.Loading -> {}
        is Resource.Success -> {
            val user = (userState as Resource.Success).data
            displayName = user.displayName
        }
        is Resource.Error -> { displayName = "Unknown" }
        else -> {}
    }
    Column(
        modifier = Modifier
            .background(color = Background)
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, top = 40.dp, end = 20.dp)
        ) {
            Text(
                text = "Ready to play ${displayName}",
                color = TextPrimary,
                style = Typography.headlineMedium
            )
            Text(
                text = "Your current strak is 5 days",
                style = Typography.bodyMedium,
                color = TextTertiary
            )
            Spacer(Modifier.height(20.dp))
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(size = 24.dp),
                colors = CardDefaults.cardColors(
                    containerColor = GreenPrimary
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = "DAILY CHALLENGE",
                        color = Color.White,
                        style = Typography.bodyLarge
                    )
                    Spacer(Modifier.height(10.dp))
                    Text(
                        text = "Science & Space Trivia",
                        color = Color.White,
                        style = Typography.headlineMedium
                    )
                    Spacer(Modifier.height(16.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "+500 XP Points",
                            color = TextSecondary,
                            style = Typography.bodyMedium
                        )
                        Spacer(Modifier.weight(1f))
                        Button(
                            modifier = Modifier
                                .padding(6.dp)
                                .height(34.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.White,
                                contentColor = GreenPrimary
                            ),
                            onClick = {}
                        ) {
                            Text(
                                text = "Play now"
                            )
                        }
                    }
                }

            }
            Spacer(Modifier.height(30.dp))
            Text(
                text = "Last played",
                color = TextPrimary,
                style = Typography.bodyLarge
            )
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(size = 24.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Surface
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Row(

                    ) { }
                    Text(
                        text = "DAILY CHALLENGE",
                        color = TextSecondary,
                        style = Typography.bodyLarge
                    )
                    Spacer(Modifier.height(10.dp))
                    Text(
                        text = "Science & Space Trivia",
                        color = TextPrimary,
                        style = Typography.headlineMedium
                    )
                    Spacer(Modifier.height(16.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "+500 XP Points",
                            color = TextSecondary,
                            style = Typography.bodyMedium
                        )
                        Spacer(Modifier.weight(1f))
                        Button(
                            modifier = Modifier
                                .padding(6.dp)
                                .height(34.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = GreenPrimary,
                                contentColor = TextPrimary
                            ),
                            onClick = {}
                        ) {
                            Text(
                                text = "Play now"
                            )
                        }
                    }
                }

            }
            Spacer(Modifier.height(20.dp))
            Text(
                text = "Most Featured Quizes",
                color = Color.White,
                style = Typography.titleMedium
            )
        }

        LazyRow(
            modifier = modifier
                .fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(categories.drop(1).take(3)) { category ->
                CategoryCard(
                    color = category.color,
                    categoryName = category.name,
                    description = category.description,
                    onClick = {
                        navController.navigate(Screen.Quiz.createRoute(category.id))
                    }
                )
            }
        }
        Spacer(Modifier.height(20.dp))
        when (bestUser) {
            is Resource.Success -> {
                Column(
                    modifier = Modifier.padding(20.dp)
                ) {
                    Text(
                        text = "The best in Quiz's",
                        color = Color.White,
                        style = Typography.titleMedium
                    )
                    LeadebordCard(
                        user = (bestUser as Resource.Success).data,
                        rank = 1
                    )
                }
            }
            is Resource.Loading -> CircularProgressIndicator()
            is Resource.Error -> Text(text = "Greska", color = TextPrimary)
            else -> {}
        }
    }

}

@Preview(showSystemUi = true)
@Composable
private fun HomeScreenPrev() {
    HomeScreen(navController = rememberNavController())
}