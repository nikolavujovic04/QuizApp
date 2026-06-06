package com.example.quizapp.ui.screens.auth.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.quizapp.ui.theme.OrangePrimary
import com.example.quizapp.ui.theme.TextTertiary

@Composable
fun EmailComponent(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text("Email Address") },
        placeholder = {Text("Email address")},
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
}

@Preview
@Composable
private fun EmailComponentPrev() {
    EmailComponent(value = "",
        onValueChange = {})
}