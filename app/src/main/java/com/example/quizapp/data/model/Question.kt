package com.example.quizapp.data.model

data class Question(
    val id: String = "",
    val text: String = "",
    val options: List<String> = emptyList(),
    val correctIndex: Int = 0,
    val category: String = "",
    val difficulty: String = "",
    val points: Int = 10,
    val explanation: String = ""
)