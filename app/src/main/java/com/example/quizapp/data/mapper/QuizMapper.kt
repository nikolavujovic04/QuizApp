package com.example.quizapp.data.mapper

import com.example.quizapp.data.model.Quiz
import com.google.firebase.firestore.DocumentSnapshot

fun Quiz.toMap(): Map<String, Any> = mapOf(
    "title" to title,
    "category" to category,
    "difficulty" to difficulty,
    "questionCount" to questionCount,
    "imageUrl" to imageUrl,
    "playCount" to playCount
)

fun DocumentSnapshot.toQuiz(): Quiz {
    return Quiz(
        id = id,
        title = getString("title") ?: "",
        category = getString("category") ?: "",
        difficulty = getString("difficulty") ?: "",
        questionCount = getLong("questionCount")?.toInt() ?: 0,
        imageUrl = getString("imageUrl") ?: "",
        playCount = getLong("playCount")?.toInt() ?: 0
    )
}