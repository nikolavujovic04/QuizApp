package com.example.quizapp.di

import com.example.quizapp.data.repository.AuthRepository
import com.example.quizapp.data.repository.ProfileRepository
import com.example.quizapp.data.repository.QuestionRepository
import com.example.quizapp.data.repository.QuizRepository
import com.example.quizapp.data.repository.UserRepository
import com.example.quizapp.data.repository.impl.AuthRepositoryImpl
import com.example.quizapp.data.repository.impl.ProfileRepositoryImpl
import com.example.quizapp.data.repository.impl.QuestionRepositoryImpl
import com.example.quizapp.data.repository.impl.QuizRepositoryImpl
import com.example.quizapp.data.repository.impl.UserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule{
    @Binds
    @Singleton
    abstract fun bindAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository

    @Binds
    @Singleton
    abstract fun bindUserRepository(
        userRepositoryImpl: UserRepositoryImpl
    ): UserRepository

    @Binds
    @Singleton
    abstract fun bindQuizRepository(
        quizRepositoryImpl: QuizRepositoryImpl
    ): QuizRepository

    @Binds
    @Singleton
    abstract fun bindQuestionRepository(
        questionRepositoryImpl: QuestionRepositoryImpl
    ): QuestionRepository

    @Binds
    @Singleton
    abstract fun bindProfileRepository(
        profileRepositoryImpl: ProfileRepositoryImpl
    ): ProfileRepository
}