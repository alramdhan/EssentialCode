package com.logixphere.essentialcode.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.logixphere.essentialcode.utils.AppScreen

@Composable
fun DetailActivity(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        content = {
            Box(
                modifier = Modifier.size(100.dp)
                    .align(Alignment.CenterHorizontally),
                content = {
                    Text("Welcome back,")
                }
            )
            Button({
                navController.popBackStack()
            }) {
                Text("Back")
            }
        }
    )
}