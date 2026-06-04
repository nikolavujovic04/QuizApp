package com.example.quizapp.ui.auth

import android.graphics.text.LineBreaker
import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.quizapp.ui.auth.components.EmailComponent
import com.example.quizapp.ui.auth.components.GoogleSignInButton
import com.example.quizapp.ui.auth.components.PasswordComponent
import com.example.quizapp.ui.theme.Background
import com.example.quizapp.ui.theme.OrangePrimary
import com.example.quizapp.ui.theme.Surface
import com.example.quizapp.ui.theme.TextTertiary
import com.example.quizapp.ui.theme.Typography

@Composable
fun LoginScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .background(color = Background)
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 120.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "QuizMaster",
                color = OrangePrimary,
                style = Typography.headlineLarge
            )

            Text(
                text = "Level up your knowledge",
                color = TextTertiary,
                style = Typography.bodyMedium
            )
            Spacer(Modifier.height(20.dp))
            Card(
                modifier = Modifier.fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp),
                shape = RoundedCornerShape(size = 10.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Surface
                ),

            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    EmailComponent()
                    Spacer(Modifier.height(10.dp))
                    PasswordComponent()
                    Spacer(Modifier.height(6.dp))
                    Text(modifier = Modifier.align(Alignment.End),
                        text = "Forgot Password?",
                        color = OrangePrimary,
                        style = Typography.bodyMedium)
                    Spacer(Modifier.height(30.dp))
                    Button(
                    modifier = Modifier.width(400.dp).height(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = OrangePrimary
                    ),
                    onClick = {},
                ) {
                        Text(text = "Let's play")
                    }
                    Spacer(Modifier.height(20.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth()
                            .padding(vertical = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        HorizontalDivider(
                            modifier = Modifier.weight(1f),
                            thickness = 1.dp,
                            color = TextTertiary.copy(alpha = 0.5f)
                        )
                        Text(text = "or continue with",
                            style = Typography.bodyMedium,
                            color = TextTertiary,
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )

                        HorizontalDivider(
                            modifier = Modifier.weight(1f),
                            thickness = 1.dp,
                            color = TextTertiary.copy(alpha = 0.5f)
                        )

                    }

                    Spacer(Modifier.height(20.dp))
                    GoogleSignInButton(onClick = {})
                }

            }
        }
    }

}

@Preview(showSystemUi = true)
@Composable
private fun LoginScreenPreview() {
    LoginScreen()
}