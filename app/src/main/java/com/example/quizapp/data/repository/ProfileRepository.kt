package com.example.quizapp.data.repository

import com.example.quizapp.data.model.User
import com.example.quizapp.utils.Resource

interface ProfileRepository {
    suspend fun getLoggedUserInfo(userId: String): Resource<User>
}