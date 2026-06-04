package com.example.quizapp.ui.theme // Prilagodi paket ako se zove drugačije

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.quizapp.R

// 1. Registracija Poppins font familije iz resursa (res/font)
val Poppins = FontFamily(
    Font(R.font.poppins_regular, FontWeight.Normal),
    Font(R.font.poppins_medium, FontWeight.Medium),
    Font(R.font.poppins_semibold, FontWeight.SemiBold),
    Font(R.font.poppins_bold, FontWeight.Bold)
)

// 2. Definisanje tipografije prilagođene tvojim ekranima
val Typography = Typography(

    // Za glavne, velike naslove na ekranima (npr. "Explore Categories", "Ready to play, Alex?")
    headlineLarge = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Bold,
        fontSize = 26.sp,
        lineHeight = 32.sp
    ),

    // Za podnaslove i kategorije (npr. "Science", "General Knowledge")
    headlineMedium = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
        lineHeight = 26.sp
    ),

    // Za nazive sekcija na početnoj (npr. "Popular Quizzes", "Top Players", "Last Played")
    titleLarge = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp,
        lineHeight = 24.sp
    ),

    // Za naslove unutar samih kviz kartica (npr. "Basic Astronomy", "Cellular Biology")
    titleMedium = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        lineHeight = 22.sp
    ),

    // Za imena igrača na tabeli i filtere na vrhu (Easy, Medium, Hard, "Sarah J.")
    titleSmall = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp
    ),

    // Za opise kvizova i tekstove ispod naslova ("Test your knowledge...", "Level 3 • 40% Complete")
    bodyLarge = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp
    ),

    // Za tekstove unutar bedževa (EASY, MEDIUM, HARD, rezultate poput "Score: 8/10")
    bodyMedium = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 16.sp
    ),

    // Za male meta-podatke (npr. vreme trajanja kviza "5m", broj pitanja "10 Qs")
    bodySmall = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp
    ),

    // Za tekst na dugmićima (npr. "Play", "Play Now", "Play Again", "Leagues" u donjem meniju)
    labelLarge = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
        lineHeight = 20.sp
    )
)