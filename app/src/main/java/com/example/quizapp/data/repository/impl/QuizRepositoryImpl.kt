package com.example.quizapp.data.repository.impl

import com.example.quizapp.data.model.Quiz
import com.example.quizapp.data.remote.QuizDataSource
import com.example.quizapp.data.repository.QuizRepository
import com.example.quizapp.utils.Resource
import javax.inject.Inject

class QuizRepositoryImpl @Inject constructor(
    private val quizDataSource: QuizDataSource
): QuizRepository {
    override suspend fun getAllQuizes(): Resource<Quiz> {
        return try{
            val quizes = quizDataSource.getAllQuizes()

            if(quizes!=null){
                Resource.Success(quizes)
            }else{
                Resource.Error("Doslo je do greske")
            }
        }catch(e: Exception){
            Resource.Error(e.message ?: "Greska")
        }

    }
}