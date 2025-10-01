package com.logixphere.essentialcode.utils

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.logixphere.essentialcode.ui.theme.Purple40
import com.logixphere.essentialcode.ui.theme.Purple80

@Composable
fun LPTextField(
    value: String,
    onValueChange: (String) -> Unit,
    isError: Boolean = false,
    label: String? = null,
    supportingText: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    trailingIcon: @Composable (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
//        label = { Text(label!!) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = Purple80,
            unfocusedContainerColor = Purple80,
            focusedTextColor = Purple40,
            unfocusedTextColor = Color.Gray,
            disabledBorderColor = Color.LightGray,
            errorBorderColor = Color.Red,
        ),
        enabled = true,
        isError = isError,
        placeholder = { Text(label ?: "Form") },
        supportingText = supportingText,
        singleLine = true,
        visualTransformation = visualTransformation,
        trailingIcon = trailingIcon,
        shape = CircleShape,
        keyboardOptions = keyboardOptions,
    )
}