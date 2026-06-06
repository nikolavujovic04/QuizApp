package com.example.quizapp.data.remote

import com.example.quizapp.data.mapper.toQuiz
import com.example.quizapp.data.model.Quiz
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class QuizDataSource @Inject constructor(
    private val firestore: FirebaseFirestore
) {
    suspend fun getAllQuizes(): Quiz? {
        val doc = firestore.collection("quizes")
            .document()
            .get()
            .await()

        return if(doc.exists()) doc.toQuiz() else null
    }
}