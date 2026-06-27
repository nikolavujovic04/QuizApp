package com.example.quizapp.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavArgumentBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.quizapp.ui.screens.auth.LoginScreen
import com.example.quizapp.ui.screens.auth.RegisterScreen
import com.example.quizapp.ui.screens.category.CategoryScreen
import com.example.quizapp.ui.screens.home.HomeScreen
import com.example.quizapp.ui.screens.leadebord.LeadebordScreen
import com.example.quizapp.ui.screens.profile.EditProfileScreen
import com.example.quizapp.ui.screens.profile.ProfileScreen
import com.example.quizapp.ui.screens.quiz.QuizScreen
import com.example.quizapp.ui.screens.result.ResultScreen
import com.example.quizapp.ui.screens.splash.SplashScreen
import com.example.quizapp.ui.theme.Background

@Composable
fun NavGraph(
    navController: NavHostController,
    paddingValues: PaddingValues) {
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
        composable(Screen.Leaderboard.route){
            LeadebordScreen()
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
        composable(Screen.Profile.route){
            ProfileScreen(navController = navController)
        }

        composable(
            route = Screen.Result.route,
            arguments =  listOf(
                navArgument("points") { type = NavType.IntType},
                navArgument("correct") { type = NavType.IntType}
            )){ backStackEntry ->
            val points = backStackEntry.arguments?.getInt("points") ?: 0
            val correct = backStackEntry.arguments?.getInt("correct") ?: 0

            ResultScreen(
                navController = navController,
                totalPoints = points,
                correctAnswers = correct
            )
        }
        composable(
            route = Screen.EditProfile.route,
            arguments = listOf(navArgument("userId"){type = NavType.StringType}),
        ){ backStackEntry ->
            val userid = backStackEntry.arguments?.getString("userId") ?: ""

            EditProfileScreen(
                navController = navController,
                userId = userid
            )
        }
    }
}