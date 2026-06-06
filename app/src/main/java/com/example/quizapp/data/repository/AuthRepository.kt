package com.example.quizapp.data.repository

import com.example.quizapp.data.model.User
import com.example.quizapp.utils.Resource

interface AuthRepository{
    suspend fun signIn(email: String, password: String): Resource<User>
    suspend fun signUp(email: String, password: String, displayName: String): Resource<User>
    suspend fun signOut()
    suspend fun isLoggedIn(): Boolean
    suspend fun getCurrentUserId(): String?

    suspend fun getCurrentUser(): User?
}