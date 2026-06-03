package com.example.quizapp.data.mapper

import com.example.quizapp.data.model.User

fun User.toMap(): Map<String, Any> = mapOf(
    "displayName" to displayName,
    "email" to email,
    "avatarUrl" to avatarUrl,
    "totalPoints" to totalPoints,
    "gamesPlayed" to gamesPlayed,
    "correctAnswers" to correctAnswers,
    "totalAnswers" to totalAnswers,
    "createdAt" to createdAt
)