package com.example.quizapp.ui.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.quizapp.data.model.User
import com.example.quizapp.ui.theme.*
import com.example.quizapp.ui.viewModel.EditProfileViewModel
import com.example.quizapp.utils.Resource

@Composable
fun EditProfileScreen(
    navController: NavController,
    userId: String,
    viewModel: EditProfileViewModel = hiltViewModel()
) {
    LaunchedEffect(userId) {
        viewModel.loadUser(userId)
    }
    val userState by viewModel.currentUser.collectAsState()
    var currentUser by remember { mutableStateOf<User?>(null) }
    when(userState){
        is Resource.Success -> {
            currentUser = (userState as Resource.Success).data
        }

        is Resource.Loading -> {
            CircularProgressIndicator()
        }

        is Resource.Error -> {
            Text(
                text = "Doslo je do greske"
            )
        }

        else -> {}
    }
    var displayName by remember { mutableStateOf("")  }
    var email by remember { mutableStateOf("") }

    LaunchedEffect(currentUser) {
        currentUser?.let { user ->
            displayName = user.displayName
            email = user.email
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = TextPrimary
                )
            }
            Text(
                text = "Edit Profile",
                style = Typography.titleLarge,
                color = TextPrimary,
                modifier = Modifier.weight(1f)
            )
            TextButton(onClick = {

            }) {
                Text(
                    text = "Save",
                    color = GreenPrimary,
                    style = Typography.titleMedium
                )
            }
        }

        Spacer(Modifier.height(24.dp))

        // Avatar
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Box {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .background(SurfaceVariant)
                        .border(2.dp, GreenPrimary, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "A",
                        style = Typography.headlineLarge,
                        color = GreenPrimary
                    )
                }
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                        .background(GreenPrimary)
                        .align(Alignment.BottomEnd),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Phone,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        }

        Spacer(Modifier.height(8.dp))

        Text(
            text = "Tap to change photo",
            style = Typography.bodySmall,
            color = TextTertiary,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(Modifier.height(32.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Personal Info
            Text(
                text = "PERSONAL INFO",
                style = Typography.labelMedium,
                color = TextTertiary
            )

            EditProfileField(
                value = displayName ?: "",
                onValueChange = { displayName = it },
                label = "Display Name",
                icon = Icons.Default.Person,
                placeholder = "Enter your display name"
            )

            EditProfileField(
                value = email ?: "",
                onValueChange = { email = it },
                label = "Email",
                icon = Icons.Default.Email,
                placeholder = "Enter your email"
            )

            Spacer(Modifier.height(8.dp))

            // Account
            Text(
                text = "ACCOUNT",
                style = Typography.labelMedium,
                color = TextTertiary
            )

            OutlinedButton(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(12.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, BorderColor)
            ) {
                Text(
                    text = "Change Password",
                    color = TextPrimary,
                    style = Typography.bodyLarge
                )
            }

            Spacer(Modifier.height(8.dp))

            Button(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = GreenPrimary
                )
            ) {
                Text(
                    text = "Save Changes",
                    color = Color.White,
                    style = Typography.bodyLarge
                )
            }

            Spacer(Modifier.height(16.dp))

            // Danger Zone
            Text(
                text = "DANGER ZONE",
                style = Typography.labelMedium,
                color = Error
            )

            OutlinedButton(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(12.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, Error)
            ) {
                Text(
                    text = "Delete Account",
                    color = Error,
                    style = Typography.bodyLarge
                )
            }

            Spacer(Modifier.height(32.dp))
        }
    }
}

@Composable
private fun EditProfileField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    icon: ImageVector,
    placeholder: String
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        placeholder = { Text(text = placeholder, color = TextTertiary) },
        leadingIcon = {
            Icon(imageVector = icon, contentDescription = null, tint = TextTertiary)
        },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = GreenPrimary,
            unfocusedBorderColor = BorderColor,
            focusedLabelColor = GreenPrimary,
            unfocusedLabelColor = TextTertiary,
            focusedTextColor = TextPrimary,
            unfocusedTextColor = TextPrimary,
            cursorColor = GreenPrimary,
            focusedContainerColor = Surface,
            unfocusedContainerColor = Surface
        ),
        singleLine = true
    )
}

@Preview(showSystemUi = true)
@Composable
private fun EditProfileScreenPreview() {
}