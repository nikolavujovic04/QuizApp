package com.example.quizapp.data.repository

import com.example.quizapp.data.model.Question
import com.example.quizapp.utils.Resource

interface QuestionRepository {
    suspend fun getQuestionByCategory(category: String): Resource<List<Question>>
}