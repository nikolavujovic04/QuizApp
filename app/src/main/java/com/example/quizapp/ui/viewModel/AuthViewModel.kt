package com.example.quizapp.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizapp.data.model.User
import com.example.quizapp.data.repository.AuthRepository
import com.example.quizapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class AuthViewModel(
    private val authRepository: AuthRepository
): ViewModel(){
    private val _signInState = MutableStateFlow<Resource<User>>(Resource.Loading)
    val signInState: StateFlow<Resource<User>> = _signInState.asStateFlow()

    private val _signUpState = MutableStateFlow<Resource<User>>(Resource.Loading)
    val signUpState: StateFlow<Resource<User>> = _signUpState.asStateFlow()

    fun signIn(email: String, password: String){
        viewModelScope.launch {
            _signInState.value = Resource.Loading
            _signInState.value = authRepository.signIn(email, password)
        }
    }

    fun signUp(email: String, password: String, displayName: String){
        viewModelScope.launch {
            _signUpState.value = Resource.Loading
            _signUpState.value = authRepository.signUp(email, password, displayName)
        }
    }

    fun signOut(){
        viewModelScope.launch {
            authRepository.signOut()
        }
    }

    suspend fun isLoggedIn(): Boolean{
        return authRepository.isLoggedIn()
    }
}