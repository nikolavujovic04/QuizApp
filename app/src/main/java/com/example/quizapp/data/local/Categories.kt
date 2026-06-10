package com.example.quizapp.data.local

import com.example.quizapp.data.model.Category
import com.example.quizapp.ui.theme.*

object Categories {
    val list = listOf(
        Category(
            id = "kotlin",
            name = "Kotlin",
            description = "Android & Kotlin fundamentals",
            color = KotlinColor,
            icon = "kotlin",
            questionCount = 20
        ),
        Category(
            id = "python",
            name = "Python",
            description = "Python programming basics",
            color = PythonColor,
            icon = "python",
            questionCount = 20
        ),
        Category(
            id = "javascript",
            name = "JavaScript",
            description = "JS fundamentals & ES6+",
            color = JavaScriptColor,
            icon = "javascript",
            questionCount = 20
        ),
        Category(
            id = "algorithms",
            name = "Algorithms",
            description = "Data structures & algorithms",
            color = AlgorithmsColor,
            icon = "algorithms",
            questionCount = 20
        ),
        Category(
            id = "web",
            name = "Web Dev",
            description = "HTML, CSS & web basics",
            color = WebColor,
            icon = "web",
            questionCount = 20
        ),
        Category(
            id = "database",
            name = "Databases",
            description = "SQL & NoSQL fundamentals",
            color = DatabaseColor,
            icon = "database",
            questionCount = 20
        )
    )
}