package com.example.quizapp.ui.screens.category.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.quizapp.ui.theme.Surface
import com.example.quizapp.ui.theme.TextPrimary
import com.example.quizapp.ui.theme.TextSecondary
import com.example.quizapp.ui.theme.Typography

@Composable
fun CategoryCard(
    modifier: Modifier = Modifier,
    color: Color,
    categoryName: String,
    description: String,
    isFeatured: Boolean = false,
    onClick: () -> Unit = {}
) {
    if (isFeatured) {
        Card(
            modifier = modifier
                .fillMaxWidth()
                .clickable { onClick() },
            shape = RoundedCornerShape(size = 24.dp),
            colors = CardDefaults.cardColors(
                containerColor = color
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Box(
                        modifier = Modifier
                            .background(
                                color = Color.White.copy(alpha = 0.2f),
                                shape = RoundedCornerShape(50)
                            )
                            .padding(horizontal = 12.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = "Featured",
                            color = Color.White,
                            style = Typography.bodySmall
                        )
                    }
                }
                Box(modifier = Modifier.size(40.dp))
                Text(
                    text = categoryName,
                    color = Color.White,
                    style = Typography.headlineMedium
                )
                Text(
                    text = description,
                    color = Color.White.copy(alpha = 0.8f),
                    style = Typography.bodyMedium
                )
            }
        }
    } else {
        Card(
            modifier = modifier
                .aspectRatio(1f)
                .clickable { onClick() },
            shape = RoundedCornerShape(size = 24.dp),
            colors = CardDefaults.cardColors(
                containerColor = Surface
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(color = color)
                )
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomStart) {
                    Column {
                        Text(
                            text = categoryName,
                            color = TextPrimary,
                            style = Typography.bodyLarge
                        )
                        Text(
                            text = "${description}",
                            color = TextSecondary,
                            style = Typography.bodySmall
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun CategoryCardPrev() {
    CategoryCard(
        color = Color(0xFF7F52FF),
        categoryName = "Kotlin",
        description = "Android & Kotlin fundamentals"
    )
}
