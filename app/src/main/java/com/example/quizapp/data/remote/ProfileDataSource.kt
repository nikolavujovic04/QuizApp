package com.example.quizapp.data.remote

import com.example.quizapp.data.mapper.toUser
import com.example.quizapp.data.model.User
import com.example.quizapp.utils.Constants
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ProfileDataSource @Inject constructor(
    private val firestore: FirebaseFirestore
) {
    suspend fun getLoggedUserInfo(userId: String): User{
        return firestore.collection(Constants.COLLECTION_USERS)
            .document(userId)
            .get()
            .await()
            .toUser()
    }
}