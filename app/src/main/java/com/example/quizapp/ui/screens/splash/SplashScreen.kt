package com.example.quizapp.ui.screens.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.quizapp.navigation.Screen
import com.example.quizapp.ui.theme.Background
import com.example.quizapp.ui.theme.GreenPrimary
import com.example.quizapp.ui.viewModel.AuthViewModel

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: AuthViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        if(viewModel.isLoggedIn()){
            navController.navigate(Screen.Home.route){
                popUpTo(Screen.Splash.route){inclusive = true}
            }
        }else{
            navController.navigate(Screen.Login.route){
                popUpTo(Screen.Splash.route){inclusive = true}
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Background),
        contentAlignment = Alignment.Center
    ){
        CircularProgressIndicator(color = GreenPrimary)
    }
}

@Preview(showSystemUi = true)
@Composable
private fun SplashScreenPrev() {

}