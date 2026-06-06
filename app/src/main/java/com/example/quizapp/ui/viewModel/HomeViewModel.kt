package com.example.quizapp.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizapp.data.model.User
import com.example.quizapp.data.repository.AuthRepository
import com.example.quizapp.data.repository.UserRepository
import com.example.quizapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val authRepository: AuthRepository
): ViewModel(){
    private val _userState = MutableStateFlow<Resource<User>>(Resource.Idle)
    val userState: StateFlow<Resource<User>> = _userState.asStateFlow()

    init {
        loadCurrentUser()
    }

    private fun loadCurrentUser(){
        viewModelScope.launch {
            _userState.value = Resource.Loading
            val userId = authRepository.getCurrentUserId()
            if(userId != null){
                _userState.value = userRepository.getUser(userId)
            }else{
                _userState.value = Resource.Error("Korisnil nije ulogovan")
            }
        }
    }
}