package com.example.quizapp.data.model

data class User(
    val id: String = "",
    val displayName: String = "",
    val username: String = "",
    val email: String = "",
    val avatarUrl: String = "",
    val totalPoints: Int = 0,
    val gamesPlayed: Int = 0,
    val correctAnswers: Int = 0,
    val totalAnswers: Int = 0,
    val currentStreak: Int = 0,
    val createdAt: Long = 0L
){
    val accuracyPercentage: Int
        get() = if(totalAnswers == 0) 0
                else ((correctAnswers.toDouble()/totalAnswers)*100).toInt()

    val averagePointsPerGame: Int
        get() = if(gamesPlayed == 0) 0
                else totalPoints/gamesPlayed

    val initials: String
        get() = displayName
            .split(" ")
            .take(2)
            .mapNotNull { it.firstOrNull()?.uppercaseChar() }
            .joinToString()
}

