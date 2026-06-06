package com.example.quizapp.data.remote

import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class QuestionDataSource @Inject constructor(
    val firestore: FirebaseFirestore
) {

}