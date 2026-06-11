package com.example.quizapp.ui.screens.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.quizapp.ui.viewModel.ProfileViewModel
import com.example.quizapp.utils.Resource

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val user by viewModel.loggedUser.collectAsState()
    val userForShow = (user as Resource.Success).data
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 40.dp, start = 20.dp, end = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (user is Resource.Success) {
            val displayName = (user as Resource.Success).data.displayName
            Text(text = displayName)
        }
        Row(

        ) {
            Text(
                text = "Member since ${userForShow.createdAt}"
            )
        }

        Button(
            modifier = Modifier
                .fillMaxWidth(),
            onClick = {}
        ) {
            Text(text = "Edit profile")
        }
    }
}

@Preview
@Composable
private fun ProfileScreenPrev() {
    ProfileScreen(navController = rememberNavController())
}


