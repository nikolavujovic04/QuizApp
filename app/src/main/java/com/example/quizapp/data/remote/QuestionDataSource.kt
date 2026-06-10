package com.example.quizapp.data.remote

import com.example.quizapp.data.mapper.toQuestion
import com.example.quizapp.data.model.Question
import com.example.quizapp.utils.Constants
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class QuestionDataSource @Inject constructor(
    val firestore: FirebaseFirestore
) {
    suspend fun getQuestionByCategory(category: String): List<Question>{
        return firestore.collection(Constants.COLLECTION_QUESTIONS)
            .whereEqualTo("category", category)
            .get()
            .await()
            .documents
            .mapNotNull {
                it.toQuestion()
            }
            .shuffled()
            .take(10)
    }
}