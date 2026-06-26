package com.example.quizapp.ui.screens.leadebord.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.quizapp.data.model.User
import com.example.quizapp.ui.theme.GreenPrimary

@Composable
fun LeadebordCard(
    modifier: Modifier = Modifier,
    user: User,
    rank: Int) {
    var number = 1
    Card(
        modifier = Modifier.background(GreenPrimary)
            .padding(20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = (number++).toString()
            )
            Spacer(Modifier.weight(1f))
            Column(

            ) {
                Text(
                    text = user.displayName
                )
                Text(
                    text = user.currentStreak.toString()
                )
            }
            Spacer(Modifier.weight(1f))
            Text(
                text = user.totalPoints.toString()
            )
        }
    }
}