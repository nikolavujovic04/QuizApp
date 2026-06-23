package com.example.quizapp.ui.screens.result

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.quizapp.ui.theme.Background

@Composable
fun ResultScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    totalPoints: Int,
    correctAnswers: Int) {

    Column(
        modifier = Modifier.fillMaxWidth()
            .background(color = Background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Cestitamo, osvojili ste ${totalPoints}",
            color = Color.White
        )
        Text(
            text = "Broj tacnih odgovora je ${correctAnswers}",
            color = Color.White
        )
    }
}