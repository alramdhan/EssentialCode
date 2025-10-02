package com.logixphere.essentialcode.utils

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.logixphere.essentialcode.ui.theme.Purple40
import com.logixphere.essentialcode.viewmodels.AuthViewModel
import com.logixphere.essentialcode.viewmodels.LoginEvent

@Composable
fun LPTextField(
    value: String,
    onValueChange: (String) -> Unit,
    isError: Boolean = false,
    label: String? = null,
    supportingText: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    trailingIcon: @Composable (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp),
        enabled = true,
        isError = isError,
        label = if(label != null) {
            { Text(label, style = TextStyle.Default.copy(background = Color.Transparent)) }
        } else null,
        supportingText = supportingText,
        singleLine = true,
        visualTransformation = visualTransformation,
        trailingIcon = trailingIcon,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions
    )
}

@Composable
fun LPOutlTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String? = null,
    isError: Boolean = false,
    label: String? = null,
    supportingText: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    trailingIcon: @Composable (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = Color(0xFFDEDEDE),
            unfocusedContainerColor = Color(0xFFDEDEDE),
            focusedBorderColor = Purple40,
            unfocusedBorderColor = Color.Gray,
            unfocusedTextColor = Color.Gray,
            focusedTextColor = Color.Black
        ),
        enabled = true,
        isError = isError,
        label = if(label != null) {
            { Text(label, style = TextStyle.Default.copy(background = Color.Transparent)) }
        } else null,
        shape = CircleShape,
        placeholder = if(placeholder != null) {
            { Text(placeholder) }
        } else null,
        supportingText = supportingText,
        singleLine = true,
        visualTransformation = visualTransformation,
        trailingIcon = trailingIcon,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions
    )
}

@Composable
fun LPOutlButton(onClick: () -> Unit) {
    OutlinedButton(
        modifier = Modifier
            .fillMaxWidth(),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = Purple40,
            contentColor = Color.White
        ),
        onClick = onClick
    ) {
        Text("Login")
    }
}