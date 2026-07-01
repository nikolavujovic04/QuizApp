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

    override suspend fun updateUser(user: User): Resource<Unit> {
        return try {
            userDataSource.updateUser(user)
            Resource.Success(Unit)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Greska")
        }
    }

    override suspend fun getAllUsers(): Resource<List<User>> {
        return try{
            val users = userDataSource.getAllUsers()
            Resource.Success(users)
        } catch(e: Exception){
            Resource.Error(e.message ?: "Greska")
        }
    }

    override suspend fun getBestUser(): Resource<User?> {
        return try{
            val best_user = userDataSource.getBestUser()
            Resource.Success(best_user)
        }catch(e: Exception){
            Resource.Error(e.message ?: "Error")
        }
    }


}