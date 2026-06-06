package com.example.quizapp.data.model

data class Result (
    val id: String = "",
    val userId: String = "",
    val quizId: String = "",
    val quizTitle: String = "",
    val category: String = "",
    val score: Int = 0,
    val totalQuestions: Int = 0,
    val playedAt: Long = 0L
)