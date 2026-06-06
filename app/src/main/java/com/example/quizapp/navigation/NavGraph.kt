package com.example.quizapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.quizapp.ui.auth.LoginScreen
import com.example.quizapp.ui.auth.RegisterScreen
import com.example.quizapp.ui.home.HomeScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ){
        composable(Screen.Login.route){
            LoginScreen(navController = navController)
        }
        composable(Screen.Register.route){
            RegisterScreen(navController = navController)
        }
        composable(Screen.Home.route){
            HomeScreen(navController = navController)
        }
    }
}