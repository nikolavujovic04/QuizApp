package com.example.quizapp.data.model

import androidx.compose.ui.graphics.Color

data class Category(
    val id: String,
    val name:String,
    val description: String,
    val color: Color,
    val icon: String,
    val questionCount: Int
)