package com.example.quizapp.data.mapper

import com.example.quizapp.data.model.User
import com.google.firebase.firestore.DocumentSnapshot

fun User.toMap(): Map<String, Any> = mapOf(
    "displayName" to displayName,
    "email" to email,
    "avatarUrl" to avatarUrl,
    "totalPoints" to totalPoints,
    "gamesPlayed" to gamesPlayed,
    "correctAnswers" to correctAnswers,
    "totalAnswers" to totalAnswers,
    "currentStreak" to currentStreak,
    "createdAt" to createdAt
)

fun DocumentSnapshot.toUser(): User{
    return User(
        id = id,
        displayName = getString("displayName")?: "",
        email = getString("emai")?: "",
        avatarUrl = getString("avatarUrl") ?: "",
        totalPoints = getLong("totalPoints")?.toInt() ?: 0,
        gamesPlayed = getLong("gamesPlayed")?.toInt() ?: 0,
        correctAnswers = getLong("correctAnswers")?.toInt() ?: 0,
        totalAnswers = getLong("totalAnswers")?.toInt() ?: 0,
        currentStreak = getLong("currentStreak")?.toInt()?: 0,
        createdAt = getLong("createdAt") ?: 0L
    )
}