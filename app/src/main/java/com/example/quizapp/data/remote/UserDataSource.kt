package com.example.quizapp.data.remote

import com.example.quizapp.data.mapper.toMap
import com.example.quizapp.data.mapper.toUser
import com.example.quizapp.data.model.User
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
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

    suspend fun updateUser(user: User){
        val doc = firestore.collection("users")
            .document(user.id)
            .set(user.toMap())
            .await()
    }

    suspend fun getAllUsers(): List<User>{
        return firestore.collection("users")
            .orderBy("totalPoints", Query.Direction.DESCENDING)
            .limit(10)
            .get()
            .await()
            .documents
            .mapNotNull { it.toUser() }
    }

    suspend fun getBestUser(): User? = firestore.collection("users")
        .orderBy("totalPoints", Query.Direction.DESCENDING)
        .limit(1)
        .get()
        .await()
        .documents
        .firstOrNull()
        ?.toUser()
}