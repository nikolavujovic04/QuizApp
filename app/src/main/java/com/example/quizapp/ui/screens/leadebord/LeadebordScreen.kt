package com.example.quizapp.ui.screens.leadebord

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.quizapp.data.model.User
import com.example.quizapp.ui.screens.leadebord.components.LeadebordCard
import com.example.quizapp.ui.viewModel.LeadebordViewModel
import com.example.quizapp.utils.Resource

@Composable
fun LeadebordScreen(
    modifier: Modifier = Modifier,
    viewModel: LeadebordViewModel = hiltViewModel()
) {
    val usersState by viewModel.allUsers.collectAsState()
    Column(
        modifier = Modifier.padding(20.dp)
    ) {
        when(usersState){
            is Resource.Loading -> {
                CircularProgressIndicator()
            }
            is Resource.Success -> {
                val users = (usersState as Resource.Success).data
                users.forEachIndexed { index, user ->
                    LeadebordCard(user = user, rank = index + 1)
                }
            }
            is Resource.Error -> {
                Text(text = (usersState as Resource.Error).message)
            }
            else -> {}
        }
    }
}