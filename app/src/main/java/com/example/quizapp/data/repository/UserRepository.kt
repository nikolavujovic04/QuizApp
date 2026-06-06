package com.example.quizapp.data.repository

import com.example.quizapp.data.model.User
import com.example.quizapp.utils.Resource

interface UserRepository {
    suspend fun getUser(uid: String): Resource<User>
}