package com.example.quizapp.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizapp.data.model.User
import com.example.quizapp.data.repository.AuthRepository
import com.example.quizapp.data.repository.ProfileRepository
import com.example.quizapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileRepository: ProfileRepository,
    private val authRepository: AuthRepository
): ViewModel(){
    private val _loggedUser = MutableStateFlow<Resource<User>>(Resource.Idle)
    val loggedUser: StateFlow<Resource<User>> = _loggedUser.asStateFlow()

    private val _gamesPlayed = MutableStateFlow<Int>(0)
    val gamesPlayed: StateFlow<Int> = _gamesPlayed.asStateFlow()

    private val _averageScore = MutableStateFlow<Int?>(0)
    val averageScore: StateFlow<Int?> = _averageScore.asStateFlow()

    private val _totalPoints = MutableStateFlow<Int?>(0)
    val totalPoints: StateFlow<Int?> = _totalPoints.asStateFlow()

    init {
        viewModelScope.launch {
            val userId = authRepository.getCurrentUserId()
            if (userId != null) loadUser(userId)
        }
    }

    fun loadUser(userId: String){
        viewModelScope.launch {
            _loggedUser.value = Resource.Loading
            _loggedUser.value = profileRepository.getLoggedUserInfo(userId)

            val user = (_loggedUser.value as? Resource.Success)?.data
            _averageScore.value = user?.accuracyPercentage
            _gamesPlayed.value = user?.gamesPlayed ?: 0
            _totalPoints.value = user?.totalPoints ?: 0
        }
    }

}