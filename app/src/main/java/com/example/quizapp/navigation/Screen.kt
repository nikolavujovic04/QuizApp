package com.example.quizapp.navigation

sealed class Screen(val route: String){
    object Login : Screen("login")
    object Register: Screen("register")
    object Home: Screen("home")
    object Splash: Screen("splash")
    object Categories: Screen("categories")
    object Quiz: Screen("quiz/{category}"){
        fun createRoute(category: String) = "quiz/$category"
    }
}