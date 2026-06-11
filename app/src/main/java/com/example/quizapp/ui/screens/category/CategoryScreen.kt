package com.example.quizapp.ui.screens.category

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.quizapp.data.local.Categories
import com.example.quizapp.navigation.Screen
import com.example.quizapp.ui.screens.category.components.CategoryCard
import com.example.quizapp.ui.theme.Background
import com.example.quizapp.ui.theme.GreenPrimary
import com.example.quizapp.ui.theme.Surface
import com.example.quizapp.ui.theme.TextPrimary
import com.example.quizapp.ui.theme.TextSecondary
import com.example.quizapp.ui.theme.TextTertiary
import com.example.quizapp.ui.theme.Typography

@Composable
fun CategoryScreen(
    modifier: Modifier = Modifier,
    navController: NavController) {
    var searchQuery by remember { mutableStateOf("") }
    val categories = Categories.list
    val featured = categories.first()
    val others = categories.drop(1)

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier
            .background(color = Background)
            .fillMaxSize(),
        contentPadding = androidx.compose.foundation.layout.PaddingValues(20.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item(span = { GridItemSpan(maxLineSpan) }) {
            Column {
                Text(
                    text = "Explore Categories",
                    color = TextPrimary,
                    style = Typography.headlineLarge
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text = "Choose a topic and start challenging yourself.",
                    color = TextTertiary,
                    style = Typography.bodyLarge
                )
                Spacer(Modifier.height(20.dp))
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = {
                        Text(
                            text = "Search topics (e.g., Space, History)",
                            style = Typography.bodyLarge,
                            color = TextTertiary
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search",
                            tint = TextTertiary
                        )
                    },
                    singleLine = true,
                    shape = RoundedCornerShape(size = 16.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Surface,
                        unfocusedContainerColor = Surface,
                        focusedTextColor = TextPrimary,
                        unfocusedTextColor = TextPrimary,
                        focusedBorderColor = GreenPrimary,
                        unfocusedBorderColor = Surface,
                        cursorColor = GreenPrimary
                    )
                )
                Spacer(Modifier.height(20.dp))
            }
        }

        item(span = { GridItemSpan(maxLineSpan) }) {
            CategoryCard(
                color = featured.color,
                categoryName = featured.name,
                description = featured.description,
                isFeatured = true,
                onClick = {
                    navController.navigate(Screen.Quiz.createRoute(featured.id))
                }
            )
        }

        items(others) { category ->
            CategoryCard(
                color = category.color,
                categoryName = category.name,
                description = category.description,
                onClick = {
                    navController.navigate(Screen.Quiz.createRoute(category.id))
                }
            )
        }

        item(span = { GridItemSpan(maxLineSpan) }) {
            Column {
                Spacer(Modifier.height(20.dp))
                Text(
                    text = "Jump Back In",
                    color = TextPrimary,
                    style = Typography.titleLarge
                )
                Spacer(Modifier.height(12.dp))
            }
        }

        item(span = { GridItemSpan(maxLineSpan) }) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(size = 24.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Surface
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    androidx.compose.foundation.layout.Box(
                        modifier = Modifier
                            .size(48.dp)
                            .background(
                                color = featured.color,
                                shape = RoundedCornerShape(size = 12.dp)
                            )
                    )
                    Spacer(Modifier.width(12.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = featured.name,
                            color = TextPrimary,
                            style = Typography.titleMedium
                        )
                        Text(
                            text = "Level 3 • 40% Complete",
                            color = TextSecondary,
                            style = Typography.bodyLarge
                        )
                    }
                }
            }
        }
    }
}

@Preview(heightDp = 1100)
@Composable
private fun CategoryScreenPrev() {
    CategoryScreen(navController = rememberNavController())
}
