package com.example.quizapp.data.mapper

import com.example.quizapp.data.model.Question
import com.google.firebase.firestore.DocumentSnapshot

fun DocumentSnapshot.toQuestion(): Question {
    return Question(
        id = id,
        text = getString("text") ?: "",
        options = get("options") as? List<String> ?: emptyList(),
        correctIndex = getLong("correctIndex")?.toInt() ?: 0,
        category = getString("category") ?: "",
        difficulty = getString("difficulty") ?: "",
        points = getLong("points")?.toInt() ?: 10,
        explanation = getString("explanation") ?: ""
    )
}

fun Question.toMap(): Map<String, Any> = mapOf(
    "text" to text,
    "options" to options,
    "correctIndex" to correctIndex,
    "category" to category,
    "difficulty" to difficulty,
    "points" to points,
    "explanation" to explanation
)