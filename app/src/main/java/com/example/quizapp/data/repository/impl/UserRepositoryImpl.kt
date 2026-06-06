package com.example.quizapp.data.repository.impl

import com.example.quizapp.data.model.User
import com.example.quizapp.data.remote.UserDataSource
import com.example.quizapp.data.repository.UserRepository
import com.example.quizapp.utils.Resource
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDataSource: UserDataSource
): UserRepository {
    override suspend fun getUser(userId: String): Resource<User> {
        return try{
            val user = userDataSource.getUser(userId)
            if(user!=null){
                Resource.Success(user)
            }else{
                Resource.Error("Korisnik nije pronadjen")
            }
        }catch (e: Exception){
            Resource.Error(e.message?: "Greska")
        }
    }
}