package com.example.quizapp.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizapp.data.model.User
import com.example.quizapp.data.repository.UserRepository
import com.example.quizapp.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class LeadebordViewModel @Inject constructor(
    userRepository: UserRepository
): ViewModel() {

    private val _allUsers = MutableStateFlow< Resource<List<User>>>(Resource.Idle)
    val allUsers: StateFlow<Resource<List<User>>> = _allUsers.asStateFlow()

    init {
        viewModelScope.launch {
            _allUsers.value = Resource.Loading
            _allUsers.value = userRepository.getAllUsers()
        }
    }


}