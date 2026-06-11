package com.example.quizapp.ui.viewModel

import androidx.lifecycle.ViewModel
import com.example.quizapp.data.model.User
import com.example.quizapp.data.repository.ProfileRepository
import com.example.quizapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileRepository: ProfileRepository
): ViewModel(){
    val _loggedUser = MutableStateFlow<Resource<User>>(Resource.Idle)
    val loggedUser: StateFlow<Resource<User>> = _loggedUser.asStateFlow()

    val _gamesPlayed = MutableStateFlow<Int>(0)
    val gamesPlayed: StateFlow<Int> = _gamesPlayed.asStateFlow()

    val _averageScore = MutableStateFlow<Int>(0)
}