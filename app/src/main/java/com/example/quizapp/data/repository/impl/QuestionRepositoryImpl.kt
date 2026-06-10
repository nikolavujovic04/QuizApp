package com.example.quizapp.data.repository.impl

import com.example.quizapp.data.model.Question
import com.example.quizapp.data.remote.QuestionDataSource
import com.example.quizapp.data.repository.QuestionRepository
import com.example.quizapp.utils.Resource
import javax.inject.Inject

class QuestionRepositoryImpl @Inject constructor(
    private val questionDataSource: QuestionDataSource
): QuestionRepository{
    override suspend fun getQuestionByCategory(category: String): Resource<List<Question>> {
        return try{
            val questions = questionDataSource.getQuestionByCategory(category)
            if(questions.isNotEmpty()){
                Resource.Success(questions)
            }else{
                Resource.Error("Nema pitanja za ovu kategoriju")
            }
        }catch(e: Exception){
            Resource.Error(e.message ?: "Greska pri ucitavanju pitanja")
        }
    }

}