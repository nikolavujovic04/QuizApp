package com.example.quizapp.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.quizapp.data.model.Question
import com.example.quizapp.data.repository.QuestionRepository
import com.example.quizapp.data.repository.UserRepository
import com.example.quizapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(
    private val questionRepository: QuestionRepository,
    private val userRepository: UserRepository
): ViewModel(){
    private val _questions = MutableStateFlow<Resource<List<Question>>>(Resource.Idle)
    val questions: StateFlow<Resource<List<Question>>> = _questions.asStateFlow()

    private val _currentQuestionIndex = MutableStateFlow(0)
    val currentQuestionIndex: StateFlow<Int> = _currentQuestionIndex.asStateFlow()

    private val _selectedAnswerIndex = MutableStateFlow<Int?>(null)
    val selectedAnswerIndex: StateFlow<Int?> = _selectedAnswerIndex.asStateFlow()

    private val _totalPoints = MutableStateFlow(0)
    val totalPoints: StateFlow<Int> = _totalPoints.asStateFlow()

    // Broj tacnih odgovora
    private val _correctAnswers = MutableStateFlow(0)
    val correctAnswers: StateFlow<Int> = _correctAnswers.asStateFlow()

    // Timer — sekundi po pitanju
    private val _timeLeft = MutableStateFlow(30)
    val timeLeft: StateFlow<Int> = _timeLeft.asStateFlow()

    // Da li je kviz zavrsen
    private val _isQuizFinished = MutableStateFlow(false)
    val isQuizFinished: StateFlow<Boolean> = _isQuizFinished.asStateFlow()

    // Da li je odgovor vec dat
    private val _isAnswered = MutableStateFlow(false)
    val isAnswered: StateFlow<Boolean> = _isAnswered.asStateFlow()

    private var timerJob: Job? = null

    fun loadQuestions(category: String){
        viewModelScope.launch {
            _questions.value = Resource.Loading
            _questions.value = questionRepository.getQuestionByCategory(category)
            if(_questions.value is Resource.Success){
                startTimer()
            }
        }
    }

    fun selectAnswer(index: Int){
        if (_isAnswered.value) return

        _selectedAnswerIndex.value = index
        _isAnswered.value = true
        timerJob?.cancel()

        val questions = (_questions.value as? Resource.Success)?.data ?: return
        val currentQuestion = questions[_currentQuestionIndex.value]

        if(index == currentQuestion.correctIndex){
            val timeBonus = when{
                _timeLeft.value > 20 -> 5
                _timeLeft.value > 10 -> 2
                else -> 0
            }

            _totalPoints.value += currentQuestion.points + timeBonus
            _correctAnswers.value++
        }
    }

    fun nextQuestion(){
        val questions = (_questions.value as? Resource.Success)?.data ?: return

        if(_currentQuestionIndex.value < questions.size - 1){
            _currentQuestionIndex.value++
            _selectedAnswerIndex.value = null
            _isAnswered.value = false
            _timeLeft.value = 30
            startTimer()
        }else{
            _isQuizFinished.value = true
        }
    }
    private fun startTimer(){
        timerJob?.cancel()
        timerJob = viewModelScope.launch {
            while(_timeLeft.value>0){
                delay(1000)
                _timeLeft.value--
            }

            if(!_isAnswered.value){
                _isAnswered.value = true
                nextQuestion()
            }
        }
    }

    private fun finishedQuiz(){
        if(_isQuizFinished.value){
            userRepository.
        }
    }

    override fun onCleared() {
        super.onCleared()
        timerJob?.cancel()
    }
}