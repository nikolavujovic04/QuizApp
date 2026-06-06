package com.example.quizapp.ui.screens.home.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun QuizCard(
    modifier: Modifier = Modifier,
    color: Color,
    quizName: String) {

    Card(
        modifier = Modifier.fillMaxWidth()
            .padding(10.dp),
        shape = RoundedCornerShape(size = 24.dp)
    ) { }
}