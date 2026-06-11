package com.example.quizapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.quizapp.navigation.NavGraph
import com.example.quizapp.navigation.Screen
import com.example.quizapp.ui.BottomNavigationBar
import com.example.quizapp.ui.theme.QuizAppTheme
import dagger.hilt.android.AndroidEntryPoint
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QuizAppTheme {
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                // Ekrani gde se NE prikazuje bottom bar
                val hideBottomBar = listOf(
                    Screen.Splash.route,
                    Screen.Login.route,
                    Screen.Register.route,
                    Screen.Quiz.route
                )

                Scaffold(
                    bottomBar = {
                        if (currentRoute !in hideBottomBar) {
                            BottomNavigationBar(navController = navController)                       }
                    }
                ) { paddingValue ->
                    NavGraph(
                        navController = navController,
                        paddingValues = paddingValue
                    )
                }
            }
        }
    }
}