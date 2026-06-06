package com.example.quizapp.ui.screens.auth

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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.quizapp.navigation.Screen
import com.example.quizapp.ui.screens.auth.components.EmailComponent
import com.example.quizapp.ui.screens.auth.components.GoogleSignInButton
import com.example.quizapp.ui.screens.auth.components.PasswordComponent
import com.example.quizapp.ui.theme.Background
import com.example.quizapp.ui.theme.OrangePrimary
import com.example.quizapp.ui.theme.Surface
import com.example.quizapp.ui.theme.TextTertiary
import com.example.quizapp.ui.theme.Typography
import com.example.quizapp.ui.viewModel.AuthViewModel
import com.example.quizapp.utils.Resource

@Composable
fun RegisterScreen(
    navController: NavController,
    viewModel: AuthViewModel = hiltViewModel()
) {
    var email by remember {mutableStateOf("")}
    var password by remember { mutableStateOf("") }
    var displayName by remember { mutableStateOf("") }
    val signUpState by viewModel.signUpState.collectAsState()
    LaunchedEffect(
        signUpState
    ) {
        when(signUpState){
            is Resource.Idle -> {}
            is Resource.Success -> {
                navController.navigate(Screen.Home.route){
                    popUpTo(Screen.Login.route){ inclusive = true }
                }
            }
            is Resource.Error -> {}
            else -> {}
        }
    }

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
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp)
                    .shadow(
                        elevation = 4.dp,
                        shape = RoundedCornerShape(10.dp),
                        clip = false,
                        ambientColor = TextTertiary,
                        spotColor = TextTertiary
                    ),
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
                    OutlinedTextField(
                        value = displayName,
                        onValueChange = {displayName = it},
                        label = {Text(text = "Display name")},
                        placeholder = {Text(text = "Display name")},
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(size = 8.dp),

                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email
                        ),

                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = OrangePrimary,
                            focusedLabelColor = OrangePrimary,
                            unfocusedBorderColor = TextTertiary
                        )
                    )
                    EmailComponent(
                        value = email,
                        onValueChange = { email = it }
                    )
                    Spacer(Modifier.height(10.dp))
                    PasswordComponent(
                        value = password,
                        onValueChange = { password = it })
                    Spacer(Modifier.height(30.dp))
                    Button(
                        modifier = Modifier
                            .width(400.dp)
                            .height(50.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = OrangePrimary
                        ),
                        onClick = {
                            viewModel.signUp(email, password, displayName)
                        },
                        enabled = signUpState !is Resource.Loading
                    ) {
                        if(signUpState is Resource.Loading){
                            CircularProgressIndicator(
                                modifier = Modifier.height(20.dp),
                                color = Color.White
                            )
                        }else{
                            Text(text = "Let's play")
                        }
                    }
                    if(signUpState is Resource.Error){
                        Text(
                            text = (signUpState as Resource.Error).message,
                            color = Color.Red,
                            style = Typography.bodySmall
                        )

                    }
                    Spacer(Modifier.height(20.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
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
private fun RegisterScreenPrev() {
    RegisterScreen(navController = rememberNavController())
}

