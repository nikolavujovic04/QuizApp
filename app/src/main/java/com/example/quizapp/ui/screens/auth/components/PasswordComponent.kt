package com.example.quizapp.ui.screens.auth.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.quizapp.ui.theme.GreenPrimary
import com.example.quizapp.ui.theme.TextTertiary

@Composable
fun PasswordComponent(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text("Password") },
        placeholder = {Text("Password")},
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(size = 8.dp),

        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password
        ),

        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = GreenPrimary,
            focusedLabelColor = GreenPrimary,
            unfocusedBorderColor = TextTertiary
        )
    )
}