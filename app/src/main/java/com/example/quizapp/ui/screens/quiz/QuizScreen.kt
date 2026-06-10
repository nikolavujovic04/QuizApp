package com.example.quizapp.ui.screens.quiz

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.quizapp.ui.viewModel.QuizViewModel

@Composable
fun QuizScreen(
    modifier: Modifier = Modifier,
    category: String,
    navController: NavController,
    viewModel: QuizViewModel = hiltViewModel()
) {
    LaunchedEffect(category) {
        viewModel.loadQuestions(category)
    }
}

@Preview(showSystemUi = true)
@Composable
private fun QuizScreenPrev() {
    
}