package com.example.quizapp.di

import com.example.quizapp.data.repository.AuthRepository
import com.example.quizapp.data.repository.UserRepository
import com.example.quizapp.data.repository.impl.AuthRepositoryImpl
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

    abstract  fun bindUserRepository(
        userRepositoryImpl: UserRepositoryImpl
    ): UserRepository
}