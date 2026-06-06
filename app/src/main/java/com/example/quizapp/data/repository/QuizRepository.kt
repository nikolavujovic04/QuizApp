package com.example.quizapp.data.repository

import com.example.quizapp.data.model.Quiz
import com.example.quizapp.utils.Resource

interface QuizRepository {
    suspend fun getAllQuizes(): Resource<Quiz>
}