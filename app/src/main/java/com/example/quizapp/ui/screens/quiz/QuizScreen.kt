package com.example.quizapp.ui.screens.quiz

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.quizapp.data.model.Question
import com.example.quizapp.ui.theme.AccentOrange
import com.example.quizapp.ui.theme.Background
import com.example.quizapp.ui.theme.BorderColor
import com.example.quizapp.ui.theme.Error
import com.example.quizapp.ui.theme.GreenPrimary
import com.example.quizapp.ui.theme.Surface
import com.example.quizapp.ui.theme.SurfaceVariant
import com.example.quizapp.ui.theme.Success
import com.example.quizapp.ui.theme.TextPrimary
import com.example.quizapp.ui.theme.TextSecondary
import com.example.quizapp.ui.theme.Typography
import com.example.quizapp.ui.theme.Warning
import com.example.quizapp.ui.viewModel.QuizViewModel
import com.example.quizapp.utils.Resource

@Composable
fun QuizScreen(
    modifier: Modifier = Modifier,
    category: String,
    navController: NavController,
    viewModel: QuizViewModel = hiltViewModel()
) {
    LaunchedEffect(category) {
        viewModel.loadQuestions(category)
    }

    val questionsState by viewModel.questions.collectAsState()
    val currentQuestionIndex by viewModel.currentQuestionIndex.collectAsState()
    val selectedAnswerIndex by viewModel.selectedAnswerIndex.collectAsState()
    val totalPoints by viewModel.totalPoints.collectAsState()
    val timeLeft by viewModel.timeLeft.collectAsState()
    val isAnswered by viewModel.isAnswered.collectAsState()
    val isQuizFinished by viewModel.isQuizFinished.collectAsState()

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = Background)
    ) {
        when (val state = questionsState) {
            is Resource.Idle, is Resource.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = GreenPrimary
                )
            }

            is Resource.Error -> {
                Text(
                    text = state.message,
                    color = Error,
                    style = Typography.bodyLarge,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(20.dp)
                )
            }

            is Resource.Success -> {
                val questions = state.data
                if (questions.isEmpty()) {
                    Text(
                        text = "No questions available for this category.",
                        color = TextSecondary,
                        style = Typography.bodyLarge,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(20.dp)
                    )
                } else {
                    val question = questions[currentQuestionIndex]

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(20.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            IconButton(onClick = { navController.popBackStack() }) {
                                Icon(
                                    imageVector = Icons.Default.Close,
                                    contentDescription = "Close",
                                    tint = TextPrimary
                                )
                            }
                            Text(
                                text = "Question ${currentQuestionIndex + 1} of ${questions.size}",
                                color = TextPrimary,
                                style = Typography.titleSmall,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.weight(1f)
                            )
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(50))
                                    .background(SurfaceVariant)
                                    .padding(horizontal = 12.dp, vertical = 6.dp)
                            ) {
                                Text(
                                    text = "🪙 $totalPoints",
                                    color = Warning,
                                    style = Typography.labelLarge
                                )
                            }
                        }
                        Spacer(Modifier.height(12.dp))
                        LinearProgressIndicator(
                            progress = { (currentQuestionIndex + 1f) / questions.size },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(8.dp)
                                .clip(RoundedCornerShape(4.dp)),
                            color = GreenPrimary,
                            trackColor = SurfaceVariant
                        )
                        Spacer(Modifier.height(20.dp))
                        Text(
                            text = "⏱ 00:${timeLeft.toString().padStart(2, '0')}",
                            color = AccentOrange,
                            style = Typography.titleMedium,
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                        )
                        Spacer(Modifier.height(16.dp))
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(size = 20.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Surface
                            )
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(20.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(50))
                                        .background(AccentOrange.copy(alpha = 0.15f))
                                        .padding(horizontal = 12.dp, vertical = 4.dp)
                                ) {
                                    Text(
                                        text = question.category.uppercase(),
                                        color = AccentOrange,
                                        style = Typography.bodySmall
                                    )
                                }
                                Spacer(Modifier.height(12.dp))
                                Text(
                                    text = question.text,
                                    color = TextPrimary,
                                    style = Typography.headlineMedium
                                )
                            }
                        }
                        Spacer(Modifier.height(20.dp))
                        question.options.forEachIndexed { index, option ->
                            AnswerOption(
                                index = index,
                                option = option,
                                question = question,
                                selectedAnswerIndex = selectedAnswerIndex,
                                isAnswered = isAnswered,
                                onClick = { viewModel.selectAnswer(index) }
                            )
                            Spacer(Modifier.height(12.dp))
                        }
                        Spacer(Modifier.weight(1f))
                        if (isAnswered) {
                            Button(
                                onClick = { viewModel.nextQuestion() },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(50.dp),
                                shape = RoundedCornerShape(50),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = AccentOrange
                                )
                            ) {
                                Text(
                                    text = "Next Question",
                                    color = Color.White,
                                    style = Typography.labelLarge
                                )
                                Spacer(Modifier.width(8.dp))
                                Icon(
                                    imageVector = Icons.Default.ArrowForward,
                                    contentDescription = null,
                                    tint = Color.White
                                )
                            }
                        }
                        if(isQuizFinished){
                            //TODO: Implementirati sta se desava nakon sto se quiz zavrsi
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun AnswerOption(
    index: Int,
    option: String,
    question: Question,
    selectedAnswerIndex: Int?,
    isAnswered: Boolean,
    onClick: () -> Unit
) {
    val isSelected = selectedAnswerIndex == index
    val isCorrect = index == question.correctIndex
    val showAsCorrect = isAnswered && isCorrect
    val showAsWrong = isAnswered && isSelected && !isCorrect

    val containerColor = when {
        showAsCorrect -> Success.copy(alpha = 0.15f)
        showAsWrong -> Error.copy(alpha = 0.15f)
        else -> Surface
    }
    val borderColor = when {
        showAsCorrect -> Success
        showAsWrong -> Error
        else -> BorderColor
    }
    val textColor = when {
        showAsCorrect -> Success
        showAsWrong -> Error
        else -> TextPrimary
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(enabled = !isAnswered) { onClick() },
        shape = RoundedCornerShape(size = 16.dp),
        colors = CardDefaults.cardColors(
            containerColor = containerColor
        ),
        border = BorderStroke(width = 1.dp, color = borderColor)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .background(
                        when {
                            showAsCorrect -> Success
                            showAsWrong -> Error
                            else -> SurfaceVariant
                        }
                    ),
                contentAlignment = Alignment.Center
            ) {
                when {
                    showAsCorrect -> Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(18.dp)
                    )
                    showAsWrong -> Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(18.dp)
                    )
                    else -> Text(
                        text = ('A' + index).toString(),
                        color = TextSecondary,
                        style = Typography.bodyMedium
                    )
                }
            }
            Spacer(Modifier.width(12.dp))
            Text(
                text = option,
                color = textColor,
                style = Typography.bodyLarge
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun QuizScreenPrev() {
    QuizScreen(category = "general", navController = rememberNavController())
}
