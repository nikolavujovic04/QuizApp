package com.example.quizapp.data.model

data class Question(
    val id: String = "",
    val quizId: String = "",
    val text: String = "",
    val options: List<String> = emptyList(),
    val correctIndex: Int = -0,
    val points: Int = 10
)