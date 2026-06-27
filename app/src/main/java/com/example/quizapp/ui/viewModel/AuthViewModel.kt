package com.example.quizapp.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizapp.data.model.User
import com.example.quizapp.data.repository.AuthRepository
import com.example.quizapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
): ViewModel(){
    private val _signInState = MutableStateFlow<Resource<User>>(Resource.Idle)
    val signInState: StateFlow<Resource<User>> = _signInState.asStateFlow()

    private val _signUpState = MutableStateFlow<Resource<User>>(Resource.Idle)
    val signUpState: StateFlow<Resource<User>> = _signUpState.asStateFlow()

    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser = _currentUser.asStateFlow()

    fun signIn(email: String, password: String){
        viewModelScope.launch {
            _signInState.value = Resource.Loading
            val result = authRepository.signIn(email,password)
            _signInState.value = result

            if(result is Resource.Success){
                _currentUser.value = result.data
            }
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

    fun isLoggedIn(): Boolean{
        return authRepository.isLoggedIn()
    }

    suspend fun getCurrentUser(): Resource<User>?{
        return authRepository.getCurrentUser()
    }
}