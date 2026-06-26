package com.example.quizapp.navigation

sealed class Screen(val route: String){
    object Login : Screen("login")
    object Register: Screen("register")
    object Home: Screen("home")
    object Splash: Screen("splash")
    object Stats : Screen("stats")
    object Profile : Screen("profile")
    object Categories: Screen("categories")
    object Leaderboard: Screen("leadebord")
    object Quiz: Screen("quiz/{category}"){
        fun createRoute(category: String) = "quiz/$category"
    }
    object Result: Screen("result/{points}/{correct}"){
        fun createRoot(points: Int, correct: Int) = "result/$points/$correct"
    }
}