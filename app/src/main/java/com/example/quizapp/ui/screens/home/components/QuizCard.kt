package com.example.quizapp.ui.screens.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.quizapp.R
import com.example.quizapp.ui.theme.Surface

@Composable
fun QuizCard(
    modifier: Modifier = Modifier,
    quizName: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        shape = RoundedCornerShape(size = 24.dp),
        colors = CardDefaults.cardColors(
            containerColor = Surface
        )
    ) {
        Image(
            painter = painterResource(id = R.drawable.coding_graphic),
            contentDescription = "Quiz picture",
            modifier = Modifier.
                fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun QuizCardPrev() {
    QuizCard(modifier = Modifier, quizName = "Android Kotlin")
}