package com.example.quizapp.data.mapper

import com.example.quizapp.data.model.Question
import com.google.firebase.firestore.DocumentSnapshot

fun Question.toMap(): Map<String, Any> = mapOf(
    "id" to id,
    "quizId" to quizId ,
    "text" to text,
    "options" to options,
    "correctIndex" to correctIndex,
    "points" to points
)

fun DocumentSnapshot.toQuestion(): Question?{
    return Question(
        id = id,
        quizId = getString("quizId")?: "",
        text = getString("text")?: "",
        options = getString("options") ?: "",
        correctIndex = getLong("correctIndex")?.toInt() ?: 0,
        points = getLong("points")?.toInt() ?: 0,
    )
}