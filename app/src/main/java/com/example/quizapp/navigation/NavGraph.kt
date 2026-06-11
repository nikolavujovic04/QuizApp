package com.example.quizapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.quizapp.ui.screens.auth.LoginScreen
import com.example.quizapp.ui.screens.auth.RegisterScreen
import com.example.quizapp.ui.screens.category.CategoryScreen
import com.example.quizapp.ui.screens.home.HomeScreen
import com.example.quizapp.ui.screens.quiz.QuizScreen
import com.example.quizapp.ui.screens.splash.SplashScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ){
        composable(Screen.Splash.route){
            SplashScreen(navController = navController)
        }
        composable(Screen.Login.route){
            LoginScreen(navController = navController)
        }
        composable(Screen.Register.route){
            RegisterScreen(navController = navController)
        }
        composable(Screen.Home.route){
            HomeScreen(navController = navController)
        }
        composable(Screen.Categories.route){
            CategoryScreen(navController = navController)
        }
        composable(
            route = Screen.Quiz.route,
            arguments = listOf(
                navArgument("category") { type = NavType.StringType}
            )
        ) { backStackEntry ->
            val category = backStackEntry.arguments?.getString("category") ?: ""
            QuizScreen(navController = navController, category = category)
        }
    }
}