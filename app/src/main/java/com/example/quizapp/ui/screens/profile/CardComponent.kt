package com.example.quizapp.ui.screens.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.quizapp.ui.theme.GreenPrimary
import com.example.quizapp.ui.theme.Surface
import com.example.quizapp.ui.theme.TextSecondary
import com.example.quizapp.ui.theme.TextTertiary
import com.example.quizapp.ui.theme.Typography

@Composable
fun CardComponent(
    text: String,
    number: Int,
    modifier: Modifier = Modifier,
    unit: String = ""
) {
    Card(
        modifier = modifier
            .padding(4.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(size = 16.dp),
        colors = CardDefaults.cardColors(containerColor = Surface)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = if (unit.isEmpty()) number.toString() else "$number$unit",
                color = GreenPrimary,
                style = Typography.headlineLarge
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = text,
                color = TextSecondary,
                style = Typography.bodyMedium
            )
        }
    }
}
