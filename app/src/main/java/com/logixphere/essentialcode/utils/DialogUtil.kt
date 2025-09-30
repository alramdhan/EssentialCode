package com.logixphere.essentialcode.utils

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.logixphere.essentialcode.ui.theme.Purple80

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowLoading(onDismissRequest: () -> Unit) {
    BasicAlertDialog(
        onDismissRequest = onDismissRequest,
        modifier = Modifier.padding(8.dp),
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false
        ),
        content = {
            Row(
                modifier = Modifier.size(100.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                content = {
                    CircularProgressIndicator(
                        modifier = Modifier.size(50.dp),
                        strokeWidth = 5.dp,
                        color = Purple80
                    )
                }
            )
        }
    )
}

@Composable
fun ShowDefaultDialog(title: String, text: String, openDialog: Boolean = true) {
    val dialog = remember { mutableStateOf(openDialog) }

    if(dialog.value) {
        AlertDialog(
            onDismissRequest = { dialog.value = false },
            properties = DialogProperties(),
            modifier = Modifier.padding(8.dp),
            title = {
                Text(title)
            },
            text = {
                Text(text)
            },
            confirmButton = {
                Button(onClick = { dialog.value = false }) {
                    Text("Ok")
                }
            }
        )
    }
}

fun closeLoading() {

}