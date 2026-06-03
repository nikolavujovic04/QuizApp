package com.example.quizapp.data.remote

import com.example.quizapp.data.mapper.toMap
import com.example.quizapp.data.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthDataSource @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
){
    suspend fun signIn(email: String, password: String): User{
        val result = auth.signInWithEmailAndPassword(email, password).await()
        val firebaseUser = result.user ?: throw Exception("Korisnik nije pronadjen")
        return getUserFromFirestore(firebaseUser.uid)
    }

    suspend fun signUp(email: String, password: String, displayName: String): User{
        val result = auth.createUserWithEmailAndPassword(email,password).await()
        val firebaseUser = result.user ?: throw Exception("Greska pri registraciji")

        val user = User(
            id = firebaseUser.uid,
            email = email,
            displayName = displayName,
            createdAt = System.currentTimeMillis()
        )

        firestore.collection("users")
            .document(user.id)
            .set(user.toMap())
            .await()

        return user
    }

    suspend fun signOut(){
        auth.signOut()
    }

    suspend fun isLoggedIn(): Boolean{
        return auth.currentUser != null
    }

    suspend fun getCurrentUserId(): String?{
        return auth.currentUser?.uid
    }

    private suspend fun getUserFromFirestore(uid: String): User{
        val doc = firestore.collection("users")
            .document(uid)
            .get()
            .await()

        return User(
            id = doc.id,
            displayName = doc.getString("displayName")?: "",
            email = doc.getString("email")?: "",
            avatarUrl = doc.getString("avatarUrl")?: "",
            totalPoints = doc.getLong("totalPoints")?.toInt() ?: 0,
            gamesPlayed = doc.getLong("gamesPlayed")?.toInt() ?: 0,
            correctAnswers = doc.getLong("correctAnswers")?.toInt() ?: 0,
            totalAnswers = doc.getLong("totalAnswers")?.toInt() ?: 0,
            createdAt = doc.getLong("createdAt") ?: 0L
        )
    }
}