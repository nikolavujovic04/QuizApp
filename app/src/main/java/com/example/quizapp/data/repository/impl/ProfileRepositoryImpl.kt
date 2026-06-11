package com.example.quizapp.data.repository.impl

import com.example.quizapp.data.model.User
import com.example.quizapp.data.remote.ProfileDataSource
import com.example.quizapp.data.repository.ProfileRepository
import com.example.quizapp.utils.Resource
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val profileDataSource: ProfileDataSource
): ProfileRepository{
    override suspend fun getLoggedUserInfo(userId: String): Resource<User> {
        val user = profileDataSource.getLoggedUserInfo(userId)

        if(user == null) return Resource.Error("Doslo je do greske")

        return Resource.Success(user)
    }
}