package com.example.quizapp.ui.screens.auth.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.R
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.quizapp.ui.theme.Poppins
import com.example.quizapp.ui.theme.Surface
import com.example.quizapp.ui.theme.TextPrimary
import com.example.quizapp.ui.theme.TextTertiary

@Composable
fun GoogleSignInButton(onClick: () -> Unit) {
    OutlinedButton(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp),
        shape = CircleShape,
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = Surface,
            contentColor = TextPrimary
        ),
        border = BorderStroke(
            width = 1.dp,
            color = TextTertiary
        )
    ) {
        /*Icon(
            painterResource(id = R.drawable)
            contentDescription = "Google Login",
            tint = Color.Unspecified,
            modifier = Modifier.size(24.dp)
        )*/

        Spacer(modifier = Modifier.width(12.dp)) // Razmak između ikonice i teksta

        // TEKST
        Text(
            text = "Google",
            color = TextPrimary,
            fontFamily = Poppins
        )
    }
}