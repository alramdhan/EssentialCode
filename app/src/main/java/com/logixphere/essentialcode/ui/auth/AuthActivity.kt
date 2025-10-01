package com.logixphere.essentialcode.ui.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.GMobiledata
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.rounded.Visibility
import androidx.compose.material3.Checkbox
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.logixphere.essentialcode.R
import com.logixphere.essentialcode.utils.LPTextField
import com.logixphere.essentialcode.utils.ScreenUtil
import com.logixphere.essentialcode.utils.ShowLoading
import com.logixphere.essentialcode.viewmodels.AuthViewModel
import com.logixphere.essentialcode.viewmodels.LoginEvent

@Composable
fun SignInView(navController: NavController) {
    val viewModel: AuthViewModel = hiltViewModel()

    LoginScreen(viewModel)
}

@Composable
private fun LoginScreen(viewModel: AuthViewModel) {
    val loginState by viewModel.uiState.collectAsState()

    if(viewModel.loading.value == true) ShowLoading {}

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(R.drawable.bg_login),
                contentDescription = "background_login",
                contentScale = ContentScale.FillBounds
            )
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 30.dp),
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        content = {
                            Column(modifier = Modifier.width(ScreenUtil.getScreenWidth(.5f))) {
                                Text(
                                    "Login 1",
                                    fontSize = TextUnit(
                                        value = 32f,
                                        type = TextUnitType.Sp
                                    ),
                                    fontFamily = FontFamily(
                                        Font(R.font.nicomoji_regular, FontWeight.Normal)
                                    )
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text("Signin by doing it self and lorem ipsum")
                            }
                            Spacer(modifier = Modifier.width(30.dp))
                            Image(
                                modifier = Modifier.width(80.dp), painter = painterResource(
                                    R.mipmap.ic_launcher_foreground
                                ), contentDescription = "Logo App"
                            )
                        }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    LPTextField(
                        value = loginState.userLogin,
                        onValueChange = { viewModel.onEvent(LoginEvent.UserLoginChanged(it)) },
                        label = "Username",
                        isError = loginState.userLoginError,
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Email
                        )
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    LPTextField(
                        value = loginState.userPwd,
                        onValueChange = { viewModel.onEvent(LoginEvent.UserPWDChanged(it)) },
                        label = "Password",
                        isError = loginState.userPwdError,
                        visualTransformation = if (!loginState.pwdFieldVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                        trailingIcon = {
                            val image = if (loginState.pwdFieldVisibility)
                                Icons.Rounded.Visibility
                            else
                                Icons.Filled.VisibilityOff

                            IconButton({ viewModel.changeVisiblePassword(loginState.pwdFieldVisibility) }) {
                                Icon(image, "Visibility")
                            }
                        },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Password
                        )
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {

                            },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = false,
                            onCheckedChange = {

                            }
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Remember Me")
                    }
                    OutlinedButton(
                        modifier = Modifier
                            .fillMaxWidth(),
                        onClick = {
                        viewModel.onEvent(LoginEvent.Loading(true))
                    }) {
                        Text("Login")
                    }
                    Spacer(modifier = Modifier.height(30.dp))
                    Text("Or sign in with")
                    Spacer(modifier = Modifier.height(30.dp))
                    FilledIconButton(
                        onClick = {

                        },
                        shape = RoundedCornerShape(14.dp),
                        modifier = Modifier.size(64.dp)
                    ) {
                        Icon(Icons.Default.GMobiledata, "google")
                    }
                }
            }
        }
    }
}