package com.example.quizapp.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
fun QuizAppTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        content = content
    )
}