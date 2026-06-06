package com.example.quizapp.data.model

data class Quiz (
    val id: String = "",
    val title: String = "",
    val category: String = "",
    val difficulty: String = "",
    val questionCount: Int = 0,
    val imageUrl: String = "",
    val playCount: Int = 0
)