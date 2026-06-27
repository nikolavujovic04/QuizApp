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
class EditProfileViewModel @Inject constructor(
    val userRepository: UserRepository
): ViewModel(){
    private val _currentUser = MutableStateFlow<Resource<User>?>(Resource.Idle)
    val currentUser: StateFlow<Resource<User>?> = _currentUser.asStateFlow()

    fun loadUser(uid: String){
        viewModelScope.launch {
            val user = userRepository.getUser(uid)
            _currentUser.value = Resource.Loading
            _currentUser.value = user
        }
    }
}