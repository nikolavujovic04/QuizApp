package com.example.quizapp.data.remote

import com.example.quizapp.data.mapper.toUser
import com.example.quizapp.data.model.User
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserDataSource @Inject constructor(
    private val firestore: FirebaseFirestore
){
    suspend fun getUser(userId: String): User?{
        val doc = firestore.collection("users")
            .document(userId)
            .get()
            .await()
        return if (doc.exists()) doc.toUser() else null
    }
}