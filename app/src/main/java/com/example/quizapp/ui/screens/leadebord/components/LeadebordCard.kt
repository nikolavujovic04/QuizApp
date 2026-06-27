package com.example.quizapp.ui.screens.leadebord.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.quizapp.data.model.User
import com.example.quizapp.ui.theme.GreenPrimary
import com.example.quizapp.ui.theme.Surface
import com.example.quizapp.ui.theme.TextPrimary
import com.example.quizapp.ui.theme.TextTertiary
import com.example.quizapp.ui.theme.Typography

@Composable
fun LeadebordCard(
    modifier: Modifier = Modifier,
    user: User,
    rank: Int
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Surface
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = rank.toString(),
                color = GreenPrimary,
                style = Typography.titleMedium
            )
            Spacer(Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = user.displayName,
                    color = TextPrimary,
                    style = Typography.bodyLarge
                )
                Text(
                    text = "🔥 ${user.currentStreak} day streak",
                    color = TextTertiary,
                    style = Typography.bodySmall
                )
            }
            Text(
                text = "${user.totalPoints} pts",
                color = GreenPrimary,
                style = Typography.titleMedium
            )
        }
    }
}