package com.example.quizapp.data.repository.impl

import com.example.quizapp.data.model.User
import com.example.quizapp.data.remote.AuthDataSource
import com.example.quizapp.data.repository.AuthRepository
import com.example.quizapp.utils.Resource
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authDataSource: AuthDataSource
): AuthRepository{

    override suspend fun signIn(
        email: String,
        password: String
    ): Resource<User> {
        return try{
            val user = authDataSource.signIn(email,password)
            Resource.Success(user)
        } catch(e: Exception){
            Resource.Error(e.message ?: "Greska pri prijavljivanju")
        }
    }

    override suspend fun signUp(
        email: String,
        password: String,
        displayName: String
    ): Resource<User> {
        return try{
            val user = authDataSource.signUp(email, password, displayName)
            Resource.Success(user)
        } catch(e: Exception){
            Resource.Error(e.message ?: "Greska pri registraciji")
        }
    }

    override suspend fun signOut() {
        authDataSource.signOut()
    }

    override suspend fun isLoggedIn(): Boolean {
        return authDataSource.isLoggedIn()
    }

    override suspend fun getCurrentUserId(): String? {
        return authDataSource.getCurrentUserId()
    }

}